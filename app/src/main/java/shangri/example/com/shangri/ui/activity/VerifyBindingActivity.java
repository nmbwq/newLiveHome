package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2017/12/30.  重新认证公会和添加公会布局一样 只是把选择公会隐藏了
 */

public class VerifyBindingActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {

    @BindView(R.id.user_login_remember_password_icon)
    ImageView user_login_remember_password_icon;

    @BindView(R.id.tv_title)
    TextView tv_title;
    boolean isRemember = false;

    @BindView(R.id.et_pingtai)
    TextView et_pingtai;

    @BindView(R.id.et_guild_name)
    EditText et_guild_name;
    @BindView(R.id.rl_clsan)
    RelativeLayout rlClsan;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_xishu)
    EditText et_xishu;

    @BindView(R.id.rl_now)
    RelativeLayout rl_now;
    @BindView(R.id.tv_now_content)
    TextView tv_now_content;

    @BindView(R.id.rl_pass)
    RelativeLayout rl_pass;
    //公会的名称
    String guildNameText = "";
    //公会的id
    String guild_id = "";

    boolean isFromReport;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {
        guildNameText = getIntent().getStringExtra("guildNameText");
        guild_id = getIntent().getStringExtra("guild_id");
        isFromReport = getIntent().getBooleanExtra("IsFromReport", false);

        rl_pass.setVisibility(View.GONE);
        tv_title.setText("平台认证");
        et_guild_name.setEnabled(false);
        et_guild_name.setText(guildNameText);
        if (guildNameText.contains("NOW")) {
            rl_now.setVisibility(View.VISIBLE);
            tv_now_content.setVisibility(View.VISIBLE);
        } else {
            rl_now.setVisibility(View.GONE);
            tv_now_content.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.binding;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.binding;
    }

    @OnClick({R.id.user_login_remember_password, R.id.setting_back,
            R.id.tv_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login_remember_password:        //返回
                if (!isRemember) {
                    user_login_remember_password_icon.setImageResource(R.mipmap.icon_xuanzhong);
                    isRemember = true;
                } else {
                    user_login_remember_password_icon.setImageResource(R.mipmap.icon_yuanjiao5);
                    isRemember = false;
                }
                break;
            case R.id.setting_back:        //返回
                finish();
                break;
            case R.id.tv_exit:        //下一步
                if (!isRemember) {
                    Toast.makeText(this, "请勾选保密协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_guild_name.getText().toString().trim())) {
                    Toast.makeText(this, "请输入公会名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etNumber.getText().toString().trim())) {
                    Toast.makeText(this, "请输入直播平台的后台账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
                    Toast.makeText(this, "请输入直播平台的后台登录密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (guildNameText.contains("NOW")) {
                    if (TextUtils.isEmpty(et_xishu.getText().toString().trim())) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                    if ((0 > Double.parseDouble(et_xishu.getText().toString())) || Double.parseDouble(et_xishu.getText().toString()) > 1) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                }
                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                if (et_pingtai.getText().toString().trim().contains("NOW")) {
                    mPresenter.againAuth(guild_id, etNumber.getText().toString().trim(), etPwd.getText().toString().trim(),
                            et_xishu.getText().toString().trim());
                } else {
                    mPresenter.againAuth(guild_id, etNumber.getText().toString().trim(), etPwd.getText().toString().trim(),
                            "");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            String pingtainame = data.getStringExtra("pingtainame");
            et_pingtai.setText(pingtainame);
            if (pingtainame.contains("NOW")) {
                rl_now.setVisibility(View.VISIBLE);
                tv_now_content.setVisibility(View.VISIBLE);
            } else {
                rl_now.setVisibility(View.GONE);
                tv_now_content.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        showPopupWindowSevenDays();
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {

    }

    @Override
    public void guildUpgrade(AddSeccussBean bean) {

    }

    @Override
    public void bingSuccess() {

    }



    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void Uploading(legalIndexBean resultBean) {

    }

    private PopupWindow mPopWindowSelectdays;

    /**
     * 升级成功以后的弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("主上，您已成功提交审核，我们会在1-2个工作日进行审核，请及时查看审核进度。");
        if (isFromReport) {
            tv_next.setText("确定");
        } else {
            tv_next.setText("查看进度");
        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
                if (isFromReport) {
                    GlobalApp.Guild_id = guild_id;
                    startActivity(new Intent(VerifyBindingActivity.this, MyFreagmentActivity.class));

                } else {
                    finish();
                }
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.upgrade_layout, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

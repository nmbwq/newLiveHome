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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 *
 */

public class BindingActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {

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


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {
        tv_title.setText("绑定公会");
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.binding;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.binding;
    }

    @OnClick({R.id.user_login_remember_password, R.id.iv_add_guild, R.id.setting_back, R.id.rl_pass,
            R.id.tv_exit, R.id.iv_select, R.id.tv_12})
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
            case R.id.iv_add_guild:        //返回
                finish();
                break;
            case R.id.setting_back:        //返回
                ActivityManager.getInstance().finishActivity(BindingGuildeTypectivity.class);
                finish();
                break;
            case R.id.tv_12:        //返回
                if (PointUtils.isFastClick()) {
                    Intent resultIntent5 = new Intent(GlobalApp.getAppContext(), symbolWebView.class);
                    resultIntent5.putExtra("url", GlobalApp.URL);
                    startActivity(resultIntent5);
                }
                break;
            case R.id.tv_exit:        //下一步
                if (!isRemember) {
                    Toast.makeText(this, "请勾选保密协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_pingtai.getText().toString().trim())) {
                    Toast.makeText(this, "请选择或输入直播平台", Toast.LENGTH_SHORT).show();
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
                if (et_pingtai.getText().toString().trim().contains("NOW")) {
                    if (TextUtils.isEmpty(et_xishu.getText().toString().trim())) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                    if ((0 > Double.parseDouble(et_xishu.getText().toString())) || Double.parseDouble(et_xishu.getText().toString()) > 1) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                }
                String pingtainame = et_pingtai.getText().toString().trim();
                String guildname = et_guild_name.getText().toString().trim();

                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                if (et_pingtai.getText().toString().trim().contains("NOW")) {
                    mPresenter.bingGuild(pingtainame, guildname, etNumber.getText().toString().trim(), etPwd.getText().toString().trim(),
                            et_xishu.getText().toString().trim());
                } else {
                    mPresenter.bingGuild(pingtainame, guildname, etNumber.getText().toString().trim(), etPwd.getText().toString().trim(),
                            "1");
                }
                break;
            case R.id.iv_select:
            case R.id.rl_pass://选择平台
                Intent mintent = new Intent(this, GuildListActivity.class);
                startActivityForResult(mintent, 1);
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
        EventBus.getDefault().post(new BrowseEventBean());
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

    /**
     *
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui1, null);
        PopupWindow mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("主上，您已成功提交审核，我们会在1-2个工作日进行审核，请及时查看审核进度。");
        tv_next.setText("查看进度");

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getInstance().finishActivity(BindingGuildeTypectivity.class);
                finish();
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.binding, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Created by Administrator on 2017/12/30.
 */

public class UpgradeGuildeActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.setting_pwd)
    TextView settingPwd;
    @BindView(R.id.et_pingtai)
    TextView etPingtai;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.guild_name)
    TextView guildName;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.rl_pwd)
    RelativeLayout rlPwd;
    @BindView(R.id.user_login_remember_password_icon)
    ImageView userLoginRememberPasswordIcon;
    @BindView(R.id.user_login_remember_password)
    LinearLayout userLoginRememberPassword;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private ProgressDialogFragment mProgressDialog;
    boolean isRemember = false;
    //公会的名称
    String guildNameText = "";
    //公会的id
    String guild_id = "";

    // 1 在添加工会过来 2在列表过来  3在报表过来
    String type = "";

    @Override

    protected void initViewsAndEvents() {
        tvTitle.setText("公会升级");
        type = getIntent().getStringExtra("type");
        guildNameText = getIntent().getStringExtra("guildNameText");
        guild_id = getIntent().getStringExtra("guild_id");
        //报表 页面升级公会可以选择已绑定的快速公会  绑定公会成功界面和公会列表界面进行升级  没有选择的操作
        if (type.equals("3")) {
            ivSelect.setVisibility(View.VISIBLE);
            mPresenter.requestListData();
        } else {
            ivSelect.setVisibility(View.GONE);
            etPingtai.setText(guildNameText);
        }

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.upgrade_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.upgrade_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Debug", "到达公会升级的回调方法里面");
        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            guildNameText = data.getStringExtra("guildNameText");
            guild_id = data.getStringExtra("guild_id");
            etPingtai.setText(guildNameText);
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
        if (resultBean.getQuick_gulid().size()>0){
            MyGuildListDataBean.GuildsBean guildsBean = resultBean.getQuick_gulid().get(0);
            etPingtai.setText(guildsBean.getGuild_name());
            guild_id = guildsBean.getGuild_id();
        }

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

    @OnClick({R.id.user_login_remember_password, R.id.setting_back, R.id.rl_select, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_login_remember_password:
                if (!isRemember) {
                    userLoginRememberPasswordIcon.setImageResource(R.mipmap.icon_xuanzhong);
                    isRemember = true;
                } else {
                    userLoginRememberPasswordIcon.setImageResource(R.mipmap.icon_yuanjiao5);
                    isRemember = false;
                }
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select:
                if (type.equals("3")) {
                    Intent intent = new Intent(this, FastGuileSelectListActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.tv_exit:
                if (!isRemember) {
                    Toast.makeText(this, "请勾选保密协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPingtai.getText().toString().trim())) {
                    Toast.makeText(this, "请选择或输入直播平台", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etAccount.getText().toString().trim())) {
                    Toast.makeText(this, "请输入后台账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
                    Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());

                ToastUtil.showShort("到达可以请求数据的时候");
                mPresenter.guildUpgrade(guild_id, etAccount.getText().toString(), etPwd.getText().toString());
                break;
        }
    }


    /**
     * 升级成功以后的弹窗
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
                // 1 在添加工会过来 2在列表过来  3在报表过来
                switch (type) {
                    case "1":
                        finish();
                        break;
                    case "2":
                        finish();
                        break;
                    case "3":
                        //发推送
                        GlobalApp.Guild_id = guild_id;
                        startActivity(new Intent(UpgradeGuildeActivity.this, MyFreagmentActivity.class));
                        finish();
                        break;
                }
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.upgrade_layout, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

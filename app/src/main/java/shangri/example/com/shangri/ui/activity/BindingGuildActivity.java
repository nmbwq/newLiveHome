package shangri.example.com.shangri.ui.activity;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import shangri.example.com.shangri.R;

import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;

import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * mvp复制类
 * Created by admin on 2017/12/22.
 */

public class BindingGuildActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {
    @BindView(R.id.user_login_remember_password_icon)
    ImageView user_login_remember_password_icon;

    @BindView(R.id.et_houtai)
    EditText et_houtai;
    private ProgressDialogFragment mProgressDialog;
    boolean isRemember = false;
    private String pingtainame, guildname, accountnumber, password;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.binding_guild;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.binding_guild;
    }

    @Override
    protected void initViewsAndEvents() {
        if (getIntent().getStringExtra("pingtainame") != null) {
            pingtainame = getIntent().getStringExtra("pingtainame");
            guildname = getIntent().getStringExtra("guildname");
            accountnumber = getIntent().getStringExtra("accountnumber");
            password = getIntent().getStringExtra("password");
        }
    }

    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
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

            case R.id.setting_back:
                //返回
                finish();
                break;
            case R.id.tv_exit:
                Log.d("Debug", "填写的系数" + et_houtai.getText().toString().trim());
                if (TextUtils.isEmpty(et_houtai.getText().toString().trim())) {
                    ToastUtil.showShort("请填写大于0小于等于1的系数");
                    return;
                }
                if ((0 > Double.parseDouble(et_houtai.getText().toString())) || Double.parseDouble(et_houtai.getText().toString()) > 1) {
                    ToastUtil.showShort("请填写大于0小于等于1的系数");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());

                Log.d("Debug", "账号为" + accountnumber + "密码为" + password);
                mPresenter.bingGuild(pingtainame, guildname, accountnumber, password,
                        et_houtai.getText().toString().trim());
                ActivityManager.getInstance().finishActivity(BindingActivity.class);
                ActivityManager.getInstance().finishActivity(BindingGuildeTypectivity.class);
                finish();
                break;

        }
    }

    @Override
    public void onSuccess() {
//        requestFailed("");
//        LookProgressDialog mLookPmrogressDialog = new LookProgressDialog("申请成功！", "查看进度 >>");
//        mLookPmrogressDialog.setCallBack(new LookProgressDialog.CallBack() {
//            @Override
//            public void confirm(String message) {
//                finish();
//            }
//        });
//        mLookPmrogressDialog.show(getSupportFragmentManager());
//        EventBus.getDefault().post(new BrowseEventBean());
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

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


}

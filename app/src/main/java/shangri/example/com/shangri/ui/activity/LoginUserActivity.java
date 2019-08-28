package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.LoginUserPresenter;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 用户登录
 * Created by mschen on 2017/6/23.
 */

public class LoginUserActivity extends BaseActivity<LoginUserView, LoginUserPresenter> implements LoginUserView {
    private boolean isHidden = true;
    @BindView(R.id.user_login_tel)
    EditText mTelText;
    @BindView(R.id.user_login_pass)
    EditText mPwdText;
    @BindView(R.id.user_login_pass_visible)
    ImageView mPwdVisible;
    @BindView(R.id.user_login_clear)
    ImageView mLoginClear;
    @BindView(R.id.user_login_remember_password_icon)
    ImageView mRememberPwdIcon;
    private String mTel = "";
    private String mPwd = "";
    private ProgressDialogFragment mProgressDialog;
    boolean isRemember = false;

    @Override
    protected LoginUserPresenter createPresenter() {
        return new LoginUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (UserConfig.getInstance().isRememberPwd() && !TextUtils.isEmpty(UserConfig.getInstance().getUserName())
                && !TextUtils.isEmpty(UserConfig.getInstance().getPwd())) {
            mRememberPwdIcon.setImageResource(R.mipmap.ic_land_remember2);
            mTelText.setText(UserConfig.getInstance().getUserName());
            mPwdText.setText(UserConfig.getInstance().getPwd());
            isRemember = true;
        }
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        mTelText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (!TextUtils.isEmpty(text)) {
                    mLoginClear.setVisibility(View.VISIBLE);
                } else {
                    mLoginClear.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.user_login_back, R.id.user_login_register, R.id.user_login_forget_password,
            R.id.user_login_pass_visible, R.id.user_login_clear, R.id.user_login,
            R.id.user_login_remember_password, R.id.iv_wechat_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login_back:        //返回
                finish();
                break;
            case R.id.user_login_register:   //注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.user_login_remember_password: //记住密码
                if (!isRemember) {
                    mRememberPwdIcon.setImageResource(R.mipmap.ic_land_remember2);
                    isRemember = true;
                } else {
                    mRememberPwdIcon.setImageResource(R.mipmap.ic_land_remember);
                    isRemember = false;
                }
                UserConfig.getInstance().setRememberPwd(isRemember);
                break;
            case R.id.user_login_forget_password:   //忘记密码
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.user_login_pass_visible:   //眼睛
                //显示密码
                setEyeVisity();
                break;
            case R.id.user_login_clear:   //手机号删除
                mTelText.setText("");
                break;
            case R.id.user_login: //登陆
                if (PointUtils.isFastClick()) {
//                String rid = JPushInterface.getRegistrationID(getApplicationContext());
//                if (!rid.isEmpty()) {
//                    Log.d("Debug", "初始化成功rid为" + rid);
//                } else {
//                    Toast.makeText(this, "Get registration fail, JPush init failed!", Toast.LENGTH_SHORT).show();
//                }

                    mTel = mTelText.getText().toString().trim();
                    mPwd = mPwdText.getText().toString().trim();
                    if (!RegexUtil.isMobile(mTel)) {
                        ToastUtil.showShort("手机格式不对,请重新输入");
                        return;
                    }
                    if (TextUtils.isEmpty(mTel)) {
                        ToastUtil.showShort("手机号不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(mPwd)) {
                        ToastUtil.showShort("密码不能为空");
                        return;
                    }
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getSupportFragmentManager());
                    mPresenter.onLogin(mTel, mPwd);
                }
                break;
            case R.id.iv_wechat_login://微信登录
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getSupportFragmentManager());

                    UMShareAPI.get(this).getPlatformInfo(LoginUserActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                }
                break;
        }
    }

    private static final String TAG = "LoginUserActivity";
    UMAuthListener authListener = new UMAuthListener() {//Umeng
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(LoginUserActivity.this, "成功了", Toast.LENGTH_LONG).show();
            WeChatBean weChatBean = new WeChatBean();
            for (String key : data.keySet()) {
                if (key.equals("openid")) {
                    weChatBean.openid = data.get(key);
                }
                if (key.equals("country")) {
                    weChatBean.country = data.get(key);
                }
                if (key.equals("unionid")) {
                    weChatBean.unionid = data.get(key);
                }
                if (key.equals("gender")) {
                    String gender = data.get(key);
                    if (gender.equals("男")) {
                        weChatBean.sex = "1";
                    } else {
                        weChatBean.sex = "2";
                    }
                }
                if (key.equals("city")) {
                    weChatBean.city = data.get(key);
                }
                if (key.equals("province")) {
                    weChatBean.province = data.get(key);
                }
                if (key.equals("name")) {
                    weChatBean.nickname = data.get(key);
                }
                if (key.equals("iconurl")) {
                    weChatBean.headimgurl = data.get(key);
                }
            }
            weChatBean.privilege = "";
            Log.i(TAG, "onComplete: weChatBean:" + weChatBean.toString());

            mPresenter.loginWX(weChatBean);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(LoginUserActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(LoginUserActivity.this, "取消了", Toast.LENGTH_LONG).show();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    };


    private void setEyeVisity() {
        if (isHidden) {
            //设置EditText文本为可见的
            mPwdText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mPwdVisible.setImageResource(R.mipmap.ic_land_eye);
        } else {
            //设置EditText文本为隐藏的
            mPwdText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mPwdVisible.setImageResource(R.mipmap.ic_land_eye2);
        }
        isHidden = !isHidden;
        mPwdText.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = mPwdText.getText();
        if (charSequence != null) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    @Override
    public void onLogin(UserInfoBean user) { //登陆成功
//        Loading.dismiss();
        Log.d("Debug", "LoginUserActivity返回的信息为" + user.getRegister().getRegister_role());
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (user != null) {
            //设置用户
            UserConfig.getInstance().setToken(user.getRegister().getTokenX().trim());
            UserConfig.getInstance().setMobile(mTel);
            UserConfig.getInstance().setUserName(mTel);
            UserConfig.getInstance().setWxinfoId(user.getRegister().getWxinfo_id());
            //0 无权限  1.工会长 2.直播 3.管理员
            UserConfig.getInstance().setRole(user.getRegister().getRegister_role() + "");
            if (UserConfig.getInstance().isRememberPwd()) {
                UserConfig.getInstance().setRememberPwd(true); //在登陆成功的情况下 记住密码
                UserConfig.getInstance().setPwd(mPwd);

            } else {
                UserConfig.getInstance().setRememberPwd(false); //在登陆成功的情况下 记住密码
                UserConfig.getInstance().setPwd(mPwd);
            }
            UserConfig.getInstance().setLoginModel(2); //设置为账号密码登录
            if (user.getRegister().getRegister_role() == 0) {
                ActivityUtils.startActivity(this, SoftwareActivity.class);
            } else {
                ActivityUtils.startActivity(this, MainActivity.class);
                ActivityManager.getInstance().finishAllActivity();
            }
            JPushInterface.setAlias(getApplicationContext(), GlobalApp.sequence++, mTel);
        }
    }

    @Override
    public void loginWX(WeChatInfoBean resultBean) {
        if (resultBean.getToken().isEmpty()) {
            Constants.WXINFO_ID = resultBean.getWxinfo_id();
            UserConfig.getInstance().setWxinfoId(resultBean.getWxinfo_id());
            ActivityUtils.startActivity(this, BoundTelActivity.class);
        } else {
            UserConfig.getInstance().setToken(resultBean.getToken());
            UserConfig.getInstance().setWxinfoId(resultBean.getWxinfo_id());
            ActivityUtils.startActivity(this, MainActivity.class);
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void signProtocol(WebBean resultBean) {

    }

    @Override
    public void memberLogin() {

    }

    @Override
    public void adIndex(AdDataBean resultBean) {

    }

    @Override
    public void privaryDeal(AdDataBean resultBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestFailed(String message) {
//        Loading.dismiss();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setRememberPwd(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_user_login;
    }


}

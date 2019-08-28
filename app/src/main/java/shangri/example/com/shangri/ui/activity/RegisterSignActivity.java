package shangri.example.com.shangri.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.RegigstBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.RegisterUserPresenter;
import shangri.example.com.shangri.presenter.view.RegisterUserView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.MD5Util;
import shangri.example.com.shangri.util.PwdCheckUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册 设置密码
 * Created by chengaofu on 2017/6/26.
 */

public class RegisterSignActivity extends BaseActivity<RegisterUserView, RegisterUserPresenter> implements RegisterUserView {

    @BindView(R.id.register_sign_password)
    EditText mInputPwdText;
    @BindView(R.id.register_sign_password2)
    EditText mConfirmPwdText;


    @BindView(R.id.register_sign)
    ImageView register_sign;


    private String verify_code;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected RegisterUserPresenter createPresenter() {
        return new RegisterUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        mConfirmPwdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    register_sign.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_kydj));
                } else {
                    register_sign.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_bkydj));
                }
            }
        });


        String mTel = getIntent().getStringExtra("tel");
        verify_code = getIntent().getStringExtra("verify_code");
    }


    @OnClick({R.id.register_sign_loginIn, R.id.register_sign, R.id.register_sign_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_sign_loginIn:  //登录
                ActivityUtils.startActivity(this, NewLoginUserActivity.class);
                break;
            case R.id.register_sign:       //注册 密码确认的下一步
                String mPwd = mInputPwdText.getText().toString().trim();
                String confirmPwd = mConfirmPwdText.getText().toString().trim();

                if (TextUtils.isEmpty(mPwd)) {
                    ToastUtil.showShort("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(confirmPwd)) {
                    ToastUtil.showShort("请确认密码");
                    return;
                }
                if (!mPwd.equals(confirmPwd)) {
                    ToastUtil.showShort("两次密码不一致,请重新输入！");
                    return;
                }
                if (mPwd.length() < 6 || mPwd.length() > 12 || !PwdCheckUtil.isLetterDigit(mPwd)) {
                    ToastUtil.showShort("建议使用6-12位数字,字母结合");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                String mTel = UserConfig.getInstance().getMobile();
                String mToken = UserConfig.getInstance().getToken();
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.registerUser("", mTel, MD5Util.getMD5(mPwd), MD5Util.getMD5(confirmPwd), verify_code);
                break;
            case R.id.register_sign_back:
                finish();
                break;

        }
    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_confirm_signup;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_confirm_signup;
    }


    @Override
    public void registerUser() {

    }

    @Override
    public void onLogin(UserInfoBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setPwd(MD5Util.getMD5(mInputPwdText.getText().toString().trim()));
        UserConfig.getInstance().setToken(resultBean.getRegister().getTokenX().trim());
        UserConfig.getInstance().setMobile(resultBean.getRegister().getTelephone());
        UserConfig.getInstance().setRole("0");
//        ActivityUtils.startActivity(this, SoftwareActivity.class);
        ActivityUtils.startActivity(this, MainActivity.class);
        ActivityManager.getInstance().finishAllActivity();
    }

    @Override
    public void regiestBean(RegigstBean resultBean) {
        mPresenter.onLogin(UserConfig.getInstance().getMobile(), MD5Util.getMD5(mInputPwdText.getText().toString().trim()));
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}

package shangri.example.com.shangri.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.ForgetPasswordPresenter;
import shangri.example.com.shangri.presenter.view.ForgetView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.PwdCheckUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码确认
 * Created by mschen on 2017/6/23.
 */

public class PasswordConfirmActivity extends BaseActivity<ForgetView, ForgetPasswordPresenter> implements ForgetView {
    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.et_password2)
    EditText et_password2;

    @BindView(R.id.tv_login)
    ImageView tv_login;
    private String mTel, mCode;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {

        et_password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    tv_login.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_kydj));
                } else {
                    tv_login.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_bkydj));
                }
            }
        });

        if (getIntent().getStringExtra("tel") != null) {
            mTel = getIntent().getStringExtra("tel");
            mCode = getIntent().getStringExtra("code");
        }
    }

    @OnClick({R.id.tv_login, R.id.password_confirm_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_confirm_back:
                finish();
                break;
            case R.id.tv_login:
                String mpassword = et_password.getText().toString().trim();
                String mrepassword = et_password2.getText().toString().trim();
                if (TextUtils.isEmpty(mpassword)) {
                    ToastUtil.showShort("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(mrepassword)) {
                    ToastUtil.showShort("请确认密码");
                    return;
                }
                if (!mpassword.equals(mrepassword)) {
                    ToastUtil.showShort("两次密码不一致,请重新输入！");
                    return;
                }
                if (mrepassword.length() < 6 || mrepassword.length() > 12 || !PwdCheckUtil.isLetterDigit(mrepassword)) {
                    ToastUtil.showShort("建议使用6-12位数字,字母结合");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.resetPassword(mTel, mpassword, mrepassword, mCode);
                break;
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_password_confirm;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_password_confirm;
    }

    @Override
    protected ForgetPasswordPresenter createPresenter() {
        return new ForgetPasswordPresenter(this, this);
    }


    @Override
    public void getSMSVerificationCode() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void checkCode() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("修改密码成功");
//        if(TextUtils.isEmpty(UserConfig.getInstance().getToken())){ //如果处于未登录状态
//            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
//        }else{ //处于登录状态
        ActivityManager.getInstance().finishActivity(PasswordConfirmActivity.class);
        ActivityManager.getInstance().finishActivity(ForgetPasswordActivity.class);
//        }

    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (!TextUtils.isEmpty(UserConfig.getInstance().getToken())) { //如果处于登录状态
            if (TextUtils.isEmpty(message)) return;
            if (message.contains(String.valueOf(Constant.CODE_100027))) {
                ToastUtil.showShort("token 失效，需重新登录");
                ActivityUtils.startActivity(this, NewLoginUserActivity.class);
                finish();
            }
        }

    }
}

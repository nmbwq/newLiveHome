package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.ForgetPasswordPresenter;
import shangri.example.com.shangri.presenter.view.ForgetView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.CountDownTimerText;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码 (步骤和注册类似)
 * Created by mschen on 2017/6/23.
 */

public class ForgetPasswordActivity extends BaseActivity<ForgetView, ForgetPasswordPresenter> implements ForgetView {

    @BindView(R.id.user_tel)
    EditText forget_user_tel;

    @BindView(R.id.forgot_code)
    EditText forget_verify_code;

    @BindView(R.id.forgot_send_sms)
    TextView forgetSendCode;

    @BindView(R.id.forgot_next)
    ImageView forgot_next;


    //手机号 验证码
    private String mTel;
    private String mCode;

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected ForgetPasswordPresenter createPresenter() {
        return new ForgetPasswordPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        forget_verify_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    forgot_next.setImageDrawable(getResources().getDrawable(R.mipmap.dl_xyb));
                } else {
                    forgot_next.setImageDrawable(getResources().getDrawable(R.mipmap.dl_wxyb));
                }
            }
        });
        forget_user_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 11) {
                    forget_verify_code.requestFocus();
                }
            }
        });
    }

    @OnClick({R.id.forgot_next, R.id.im_closephone,
            R.id.forgot_send_sms, R.id.forgot_back})
    public void onClick(View v) {

        mTel = forget_user_tel.getText().toString().trim();
        mCode = forget_verify_code.getText().toString().trim();
        switch (v.getId()) {
            case R.id.im_closephone:
                forget_user_tel.setText("");
                forget_user_tel.setHint("请输入手机号");
                break;
            case R.id.forgot_next:       //注册的下一步
                if (!RegexUtil.isMobile(mTel)) {
                    ToastUtil.showShort("手机格式不对,请重新输入");
                    return;
                }
                if (TextUtils.isEmpty(mTel)) {
                    ToastUtil.showShort("请输入手机号");
                    return;
                }

                if (!TextUtils.isEmpty(UserConfig.getInstance().getToken())) { //已登录的情况
                    String mobile = UserConfig.getInstance().getMobile();
                    if (mobile != null) {
                        if (!mobile.equals(mTel)) {
                            ToastUtil.showShort("输入的手机号不是当前登录的手机号");
                            return;
                        }
                    }
                }
                if (TextUtils.isEmpty(mCode)) {
                    ToastUtil.showShort("请输入验证码");
                    return;
                }
                if (!RegexUtil.isCode(mCode)) {
                    ToastUtil.showShort("请输入6位数字的正确验证码!");
                    return;
                }

                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.checkCode(mTel, mCode, "forget");
                break;
            case R.id.forgot_send_sms: //获取验证码
                if (!RegexUtil.isMobile(mTel)) {
                    ToastUtil.showShort("手机格式不对,请重新输入");
                    return;
                }
                mPresenter.getSMSVerificationCode(mTel, "forget"); //手机验证码
                CountDownTimerText mCountDownTimer = new CountDownTimerText(forgetSendCode, "%ds", getResources().getString(R.string.get_code),
                        1000 * 60, new CountDownTimerText.CountDownFinishListener() {
                    @Override
                    public void finish() {

                    }
                });
                mCountDownTimer.start();
                break;
            case R.id.forgot_back:
                finish();
                break;
        }
    }

    @Override
    public void getSMSVerificationCode() {
        ToastUtil.showShort("验证码已发送");
    }

    @Override
    public void checkCode() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.e("Wislie", "检验校验码 成功");
        Intent intent = new Intent(this, PasswordConfirmActivity.class);
        intent.putExtra("tel", mTel);
        intent.putExtra("code", mCode);
        startActivity(intent);
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


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_forget_password;
    }

}

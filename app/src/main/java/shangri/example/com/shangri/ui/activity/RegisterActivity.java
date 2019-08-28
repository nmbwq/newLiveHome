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

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.RegisterPresenter;
import shangri.example.com.shangri.presenter.view.RegisterView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.CountDownTimerText;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 注册
 * Created by mschen on 2017/6/23.
 */

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.register_send_sms)
    TextView mSendCodeText;
    @BindView(R.id.register_user_tel)
    EditText mTelText;
    @BindView(R.id.register_pass)
    EditText mCodeText;


    @BindView(R.id.register_next)
    ImageView register_next;


    //手机号 验证码
    private String mTel;
    private String mCode;

    private ProgressDialogFragment mProgressDialog;
    public static RegisterActivity finsh_one = null;

    @Override
    protected void initViewsAndEvents() {

        mCodeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    register_next.setImageDrawable(getResources().getDrawable(R.mipmap.dl_xyb));
                } else {
                    register_next.setImageDrawable(getResources().getDrawable(R.mipmap.dl_wxyb));
                }
            }
        });

        mTelText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 11) {
                    mCodeText.requestFocus();
                }
            }
        });

        mPresenter.weburl();

    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this, this);
    }


    @OnClick({R.id.im_closephone, R.id.register_loginIn, R.id.register_next,
            R.id.register_send_sms, R.id.register_back, R.id.ll_agree})
    public void onClick(View v) {
        mTel = mTelText.getText().toString().trim();
        mCode = mCodeText.getText().toString().trim();
        switch (v.getId()) {

            case R.id.im_closephone:
                mTelText.setText("");
                mTelText.setHint("请输入手机号");
                break;
            case R.id.ll_agree:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(this, symbolWebView.class).putExtra("url", url));
                }
                break;

            case R.id.register_loginIn:  //登录
                ActivityUtils.startActivity(this, NewLoginUserActivity.class);
                break;
            //注册的下一步
            case R.id.register_next:
                if (!RegexUtil.isMobile(mTel)) {
                    ToastUtil.showShort("手机格式不对,请重新输入");
                    return;
                }
                if (TextUtils.isEmpty(mTel)) {
                    ToastUtil.showShort("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(mCode)) {
                    ToastUtil.showShort("请输入验证码");
                    return;
                }
                if (!RegexUtil.isCode(mCode)) {
                    ToastUtil.showShort("请输入6位数字的正确验证码!");
                    return;
                }
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getSupportFragmentManager());
                    mPresenter.checkCode(mTel, mCode, "register");
                }
                break;
            //注册获取验证码
            case R.id.register_send_sms:
                if (!RegexUtil.isMobile(mTel)) {
                    ToastUtil.showShort("手机格式不对,请重新输入");
                    return;
                }
                mPresenter.getSMSVerificationCode(mTel, "register"); //手机验证码
                CountDownTimerText mCountDownTimer = new CountDownTimerText(mSendCodeText, "%ds", getResources().getString(R.string.get_code),
                        1000 * 60, new CountDownTimerText.CountDownFinishListener() {
                    @Override
                    public void finish() {

                    }
                });
                mCountDownTimer.start();
                break;
            case R.id.register_back:
                finish();
                break;
        }
    }

    @Override
    public void getSMSVerificationCode() { //获取验证码成功
        ToastUtil.showShort("验证码已发送");
        Log.e("Wislie", "获取验证码成功");
    }

    @Override
    public void checkCode(UserRegistrationNext resultBean) {   //检验校验码 成功
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.e("Wislie", "检验校验码 成功");
        Intent intent = new Intent(this, RegisterSignActivity.class);
        intent.putExtra("tel", mTel);
        intent.putExtra("verify_code", mCode);
        startActivity(intent);
    }

    String url;

    @Override
    public void signProtocol(WebBean resultBean) {
        url = resultBean.getUrl();
    }

    @Override
    public void checkPhone(String count) {

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected int getNormalLayoutId() {
        finsh_one = this;
        return R.layout.activity_register;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_register;
    }
}

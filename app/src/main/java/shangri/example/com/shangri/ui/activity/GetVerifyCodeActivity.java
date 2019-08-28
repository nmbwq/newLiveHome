package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.RegisterPresenter;
import shangri.example.com.shangri.presenter.view.RegisterView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.CountDownTimerText;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 获取验证码
 */
public class GetVerifyCodeActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.register_pass)
    EditText mCodeText;
    @BindView(R.id.register_send_sms)
    TextView mSendCodeText;
    @BindView(R.id.register_next)
    ImageView registerNext;
    private ProgressDialogFragment mProgressDialog;
    //手机号 验证码
    private String mTel;
    private String mCode;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_get_verify_code;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_get_verify_code;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (getIntent() != null) {
            mTel = getIntent().getStringExtra("mTel");
            tvTel.setText(mTel + "");
        }

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
                    registerNext.setImageDrawable(getResources().getDrawable(R.mipmap.dl_xyb));
                } else {
                    registerNext.setImageDrawable(getResources().getDrawable(R.mipmap.dl_wxyb));
                }
            }
        });


        getVerifyCode();
    }

    private void getVerifyCode() {
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
    }

    @Override
    public void getSMSVerificationCode() {
        ToastUtil.showShort("验证码已发送");
        Log.e("Wislie", "获取验证码成功");
    }

    @Override
    public void checkCode(UserRegistrationNext resultBean) {
        Log.e("Wislie", "检验校验码 成功");
        Intent intent = new Intent(this, SetPasswordActivity.class);
        intent.putExtra("mTel", mTel);
        intent.putExtra("verify_code", mCode);
        startActivity(intent);
    }

    @Override
    public void signProtocol(WebBean resultBean) {

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
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @OnClick({R.id.register_back, R.id.register_send_sms, R.id.register_next})
    public void onViewClicked(View view) {
        mCode = mCodeText.getText().toString().trim();
        switch (view.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_send_sms:
                getVerifyCode();
                break;
            case R.id.register_next:
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
                mPresenter.checkCode(mTel, mCode, "register");
                break;
        }
    }
}

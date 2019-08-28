package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.presenter.SetPwdPresenter;
import shangri.example.com.shangri.presenter.view.SetPwdView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.dialog.SetPwdOkDialog;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.MD5Util;
import shangri.example.com.shangri.util.PwdCheckUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 设置新密码
 * Created by chengaofu on 2017/7/2.
 */

public class SetPasswordActivity extends BaseActivity<SetPwdView, SetPwdPresenter> implements SetPwdView {
    @BindView(R.id.set_pwd_input)
    EditText mSetPwd;
    @BindView(R.id.re_set_pwd_input)
    EditText mReSetPwd;
    @BindView(R.id.register_user_tel)
    TextView tvTel;
    @BindView(R.id.set_pwd_confirm)
    ImageView setPwdConfirm;
    private ProgressDialogFragment mProgressDialog;
    private String mTel;
    private String mCode;

    @Override
    protected void initViewsAndEvents() {
        if (getIntent() != null) {
            mTel = getIntent().getStringExtra("mTel");
            mCode = getIntent().getStringExtra("verify_code");
            tvTel.setText(mTel + "");
        }
        KeyBoardUtil.KeyBoard(this, "open");

        mReSetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    setPwdConfirm.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_kydj));
                } else {
                    setPwdConfirm.setImageDrawable(getResources().getDrawable(R.mipmap.denglu_bkydj));
                }
            }
        });


    }

    @OnClick({R.id.set_pwd_back, R.id.set_pwd_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_pwd_back:
                finish();
                break;
            case R.id.set_pwd_confirm:
                String pwd = mSetPwd.getText().toString();
                String rePwd = mReSetPwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showShort("输入密码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(rePwd)) {
                    ToastUtil.showShort("再次输入密码不能为空");
                    return;
                }

                if (pwd.trim().length() < 6 || pwd.length() > 12 || !PwdCheckUtil.isLetterDigit(pwd)) {
                    ToastUtil.showShort("输入密码为6-12位数字字母结合");
                    return;
                }
                if (rePwd.trim().length() < 6 || rePwd.length() > 12 || !PwdCheckUtil.isLetterDigit(rePwd)) {
                    ToastUtil.showShort("输入密码为6-12位数字字母结合");
                    return;
                }

                if (!pwd.trim().equals(rePwd.trim())) {
                    ToastUtil.showShort("两次密码不一致");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                rePwd = MD5Util.getMD5(rePwd);
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.setPwd("", mTel, mCode, rePwd, Constants.WXINFO_ID);
                break;
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected SetPwdPresenter createPresenter() {
        return new SetPwdPresenter(this, this);
    }

    @Override
    public void setPwd(String token) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setToken(token);
        UserConfig.getInstance().setRole("0");
        SetPwdOkDialog dialog = new SetPwdOkDialog();
        dialog.show(getSupportFragmentManager());
        UserConfig.getInstance().pwdIsSet(true); //设置了密码
        //微信登录设置别名
        JPushInterface.setAlias(getApplicationContext(), GlobalApp.sequence++, mTel);
//        ActivityUtils.startActivity(this, SoftwareActivity.class);
        ActivityUtils.startActivity(this, MainActivity.class);

    }

    @Override
    public void bindWx(String token) {
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

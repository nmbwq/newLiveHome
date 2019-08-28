package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.SetPwdPresenter;
import shangri.example.com.shangri.presenter.view.SetPwdView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.MD5Util;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 绑定微信 输入密码
 * Created by chengaofu on 2017/7/2.
 */

public class InputPasswordActivity extends BaseActivity<SetPwdView, SetPwdPresenter> implements SetPwdView {
    @BindView(R.id.set_pwd_input)
    EditText mSetPwd;
    @BindView(R.id.register_user_tel)
    TextView tvTel;
    @BindView(R.id.set_pwd_confirm)
    ImageView setPwdConfirm;
    private ProgressDialogFragment mProgressDialog;
    private String mTel;

    @Override
    protected void initViewsAndEvents() {
        if (getIntent() != null) {
            mTel = getIntent().getStringExtra("mTel");
            tvTel.setText(mTel + "");
        }
        KeyBoardUtil.KeyBoard(this, "open");

        mSetPwd.addTextChangedListener(new TextWatcher() {
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

    @OnClick({R.id.set_pwd_back, R.id.set_pwd_confirm, R.id.user_login_forget_password})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login_forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.set_pwd_back:
                finish();
                break;
            case R.id.set_pwd_confirm:
                String pwd = mSetPwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showShort("输入密码不能为空");
                    return;
                }
                pwd = MD5Util.getMD5(pwd);
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.bindWX(mTel, pwd, Constants.WXINFO_ID);
                break;
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_input_password;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_input_password;
    }

    @Override
    protected SetPwdPresenter createPresenter() {
        return new SetPwdPresenter(this, this);
    }

    @Override
    public void setPwd(String token) {
    }

    @Override
    public void bindWx(String token) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setToken(token);
        UserConfig.getInstance().setRole("0");
        UserConfig.getInstance().pwdIsSet(true); //设置了密码
//        if (TextUtils.isEmpty(UserConfig.getInstance().getRole()) || UserConfig.getInstance().getRole().equals("0")) {
//            ActivityUtils.startActivity(this, SoftwareActivity.class);
//        } else {
        mPresenter.memberLogin();
        ActivityUtils.startActivity(this, MainActivity.class);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }


}

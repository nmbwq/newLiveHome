package shangri.example.com.shangri.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.UpdatePwdPresenter;
import shangri.example.com.shangri.presenter.view.UpdatePwdView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.MD5Util;
import shangri.example.com.shangri.util.PwdCheckUtil;
import shangri.example.com.shangri.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by chengaofu on 2017/7/2.
 */

public class UpdatePwdActivity extends BaseActivity<UpdatePwdView, UpdatePwdPresenter> implements UpdatePwdView {


    @BindView(R.id.old_pwd_input)
    EditText mOldPwdInput;
    @BindView(R.id.update_new_pwd)
    EditText mNewPwdInput;
    @BindView(R.id.update_re_new_pwd)
    EditText mReNewPwdInput;

    private String mNewPwd;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {


    }

    @OnClick({R.id.update_hint, R.id.set_pwd_back,
            R.id.update_pwd_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_pwd_back:
                finish();
                break;
            case R.id.update_hint:
                ActivityUtils.startActivity(this, ForgetPasswordActivity.class);
                break;
            case R.id.update_pwd_submit:
                String oldPwd = mOldPwdInput.getText().toString();
                mNewPwd = mNewPwdInput.getText().toString();
                String reNewPwd = mReNewPwdInput.getText().toString();
                if (TextUtils.isEmpty(oldPwd)) {
                    ToastUtil.showShort("当前密码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(mNewPwd)) {
                    ToastUtil.showShort("新密码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(reNewPwd)) {
                    ToastUtil.showShort("新密码不能为空");
                    return;
                }
                if (mNewPwd.trim().length() < 6 || mNewPwd.length() > 12 || !PwdCheckUtil.isLetterDigit(mNewPwd)) {
                    ToastUtil.showShort("新密码为6-12位数字字母结合");
                    return;
                }

                if (reNewPwd.trim().length() < 6 || reNewPwd.length() > 12 || !PwdCheckUtil.isLetterDigit(reNewPwd)) {
                    ToastUtil.showShort("确认密码为6-12位数字字母结合");
                    return;
                }

                if (!mNewPwd.trim().equals(reNewPwd.trim())) {
                    ToastUtil.showShort("新密码和确认密码不一致");
                    return;
                }

                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.updatePwd(MD5Util.getMD5(oldPwd), MD5Util.getMD5(mNewPwd), MD5Util.getMD5(reNewPwd));
                break;

        }

    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected UpdatePwdPresenter createPresenter() {
        return new UpdatePwdPresenter(this, this);
    }


    @Override
    public void updatePwd() {
        ToastUtil.showShort("修改密码成功");
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setPwd(mNewPwd);
//        if(UserConfig.getInstance().isRememberPwd()){ //记住密码的情况下 更新密码
//            UserConfig.getInstance().setPwd(MD5Util.getMD5(mNewPwd));
//        }
        finish();
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (TextUtils.isEmpty(message)) return;
        if (message.contains(String.valueOf(Constant.CODE_100027))) {
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }

}

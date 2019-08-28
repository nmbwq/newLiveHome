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
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 绑定手机号
 */
public class BoundTelActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @BindView(R.id.register_user_tel)
    EditText mTelText;
    @BindView(R.id.register_next)
    ImageView registerNext;
    private String mTel;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_bound_tel;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_bound_tel;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        KeyBoardUtil.KeyBoard(this, "open");

        mTelText.addTextChangedListener(new TextWatcher() {
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
    }

    @OnClick({R.id.register_back, R.id.register_next})
    public void onViewClicked(View view) {
        mTel = mTelText.getText().toString().trim();
        switch (view.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_next:
                if (!RegexUtil.isMobile(mTel)) {
                    ToastUtil.showShort("手机格式不对,请重新输入");
                    return;
                }
                if (TextUtils.isEmpty(mTel)) {
                    ToastUtil.showShort("请输入手机号");
                    return;
                }
                mPresenter.checkPhone(mTel);
                break;
        }
    }

    @Override
    public void getSMSVerificationCode() {

    }

    @Override
    public void checkCode(UserRegistrationNext resultBean) {

    }

    @Override
    public void signProtocol(WebBean resultBean) {

    }

    private static final String TAG = "BoundTelActivity";

    @Override
    public void checkPhone(String count) {
        Log.i(TAG, "checkPhone: count:" + count);
        int c = Integer.parseInt(count);
        if (c == 0) {//	已绑定为1 未绑定是0
            Intent intent = new Intent(this, GetVerifyCodeActivity.class);
            intent.putExtra("mTel", mTel);
            startActivity(intent);
        } else if (c == 1) {
            Intent intent = new Intent(this, InputPasswordActivity.class);
            intent.putExtra("mTel", mTel);
            startActivity(intent);
        }
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
        ToastUtil.showShort(message);
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}

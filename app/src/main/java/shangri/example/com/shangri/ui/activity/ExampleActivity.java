package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;

/**
 * 签到以及任务界面
 */

public class ExampleActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {

    @BindView(R.id.user_login_remember_password_icon)
    ImageView user_login_remember_password_icon;

    @BindView(R.id.tv_title)
    TextView tv_title;
    boolean isRemember = false;

    @BindView(R.id.et_pingtai)
    TextView et_pingtai;

    @BindView(R.id.et_guild_name)
    EditText et_guild_name;
    @BindView(R.id.rl_clsan)
    RelativeLayout rlClsan;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_xishu)
    EditText et_xishu;

    @BindView(R.id.rl_now)
    RelativeLayout rl_now;
    @BindView(R.id.tv_now_content)
    TextView tv_now_content;


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {
        tv_title.setText("绑定公会");
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.binding;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.binding;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {

    }

    @Override
    public void guildUpgrade(AddSeccussBean bean) {

    }

    @Override
    public void bingSuccess() {

    }


    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void Uploading(legalIndexBean resultBean) {

    }




}

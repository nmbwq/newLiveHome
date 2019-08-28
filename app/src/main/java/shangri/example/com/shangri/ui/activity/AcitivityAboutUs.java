package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.AboutOursBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.presenter.AddTaskPresenter;
import shangri.example.com.shangri.presenter.CostomerServicesPresenter;
import shangri.example.com.shangri.presenter.view.costomerServiceView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToolsVersions;

/**
 * 关于我们界面
 */

public class AcitivityAboutUs extends BaseActivity<costomerServiceView, CostomerServicesPresenter> implements costomerServiceView {
    @BindView(R.id.tv_Versions)
    TextView tv_Versions;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_wx_gzh)
    TextView tvWxGzh;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_tel)
    TextView tvTel;


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.acitivity_about_us;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.acitivity_about_us;
    }

    @Override
    protected CostomerServicesPresenter createPresenter() {
        return new CostomerServicesPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tv_Versions.setText("版本号" + ToolsVersions.getVersionName(this));
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        mPresenter.aboutOurs();
    }

    @OnClick({R.id.aboutus_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutus_back:        //返回
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void vipQaList(RequestListBean resultBean) {

    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }

    /**
     * 关于我们的信息
     *
     * @param resultBean
     */

    @Override
    public void aboutOurs(AboutOursBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        tvDes.setText(resultBean.getDes() + "");
        tvWxGzh.setText("微信公众号：" + resultBean.getWx_gzh());
        tvWx.setText("客服微信号：" + resultBean.getWx());
        tvEmail.setText("咨询邮箱：" + resultBean.getEmail());
        tvTel.setText("客服热线：" + resultBean.getTel());
    }

    @Override
    public void vipQaUseful() {

    }

    @Override
    public void requestFailed(String message) {

    }
}

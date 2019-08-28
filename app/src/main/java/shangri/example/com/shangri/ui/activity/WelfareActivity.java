package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AddCompanyBean;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;
import shangri.example.com.shangri.presenter.WelfarePresenter;
import shangri.example.com.shangri.presenter.view.WelfareView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 会长福利
 */
public class WelfareActivity extends BaseActivity<WelfareView, WelfarePresenter> implements WelfareView {

    @Override
    public void accountData(AccountDataBean dataBean) {
        tv2ci.setText(" "+dataBean.getRecruit_num());
        tvC.setText(" "+dataBean.getLicence_day_num());
        tv_16.setText(" "+dataBean.getVip_day_num());
    }

    private boolean isVip;
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_ci)
    TextView tvCi;
    @BindView(R.id.tv_bo)
    TextView tvBo;
    @BindView(R.id.tv_2ci)
    TextView tv2ci;
    @BindView(R.id.tv_post_job)
    TextView tvPostJob;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_c)
    TextView tvC;
    @BindView(R.id.tv_vip)
    TextView tv_vip;
    @BindView(R.id.tv_16)
    TextView tv_16;
    @BindView(R.id.tv_certification)
    TextView tvCertification;
    private int is_company;
    private String is_issue;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_welfare;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_welfare;
    }

    @Override
    protected WelfarePresenter createPresenter() {
        return new WelfarePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.accountData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.addCompany(UserConfig.getInstance().getToken(), "2");
        mPresenter.certification(UserConfig.getInstance().getToken());
        mPresenter.getVIPPackagesList();
    }

    @OnClick({R.id.back, R.id.tv_certification, R.id.tv_post_job,R.id.tv_vip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_certification:
                if (PointUtils.isFastClick()) {
                    if (is_company == 0) {
//              未认证
                        startActivity(new Intent(this, CompanyCertificationActivity.class));
                    } else {
//              已经认证
                        ToastUtil.showShort("您已经认证过");
                    }
                }
                break;
            case R.id.tv_post_job:
                if (PointUtils.isFastClick()) {
                    switch (is_issue) {
                        case "0":
//                   未发布
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(this.getSupportFragmentManager());
                            mPresenter.companyAdd(2);
                            break;
                        case "1":
//                   发布过
                            ToastUtil.showShort("您已经领取过发布职位机会");
                            break;
                        case "2":
//                   审核中
                            ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
                            break;
                    }
                }
                break;
            case R.id.tv_vip:
                if (PointUtils.isFastClick()) {
                    if (isVip) {
                        //已充值vip
                        ToastUtil.showShort("您已经充值过vip");
                    } else {
                        //未充值vip
                        startActivity(new Intent(this,VIPActivity.class));
                    }
                }
                break;

        }
    }

    @Override
    public void getVIPPackagesList(VIPCardBean bean) {
        List<VIPCardBean.Packages> packages = bean.getPackages();
        if (packages.size()>0){
            String expire_time = packages.get(0).getExpire_time();
            if (expire_time==null||expire_time.isEmpty()) {
                isVip = false;
            } else {
                isVip = true;
                tv_vip.setText("已领取");
            }
        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void addSuccess(AddCompanyBean resultBean) {
        is_issue = resultBean.getCompany().getIs_issue();
        if ("1".equals(is_issue)) {
            tvPostJob.setText("已领取");
        }
    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getCompany().getIs_perfect() == 1) {
            startActivity(new Intent(WelfareActivity.this, AddJobActivity.class));
        } else {
            startActivity(new Intent(WelfareActivity.this, AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
        }
    }

    @Override
    public void certificationSuccess(CertificationBean resultBean) {
        is_company = resultBean.getIs_company();
        if (is_company == 1) {
            tvCertification.setText("已领取");
        }
    }

}

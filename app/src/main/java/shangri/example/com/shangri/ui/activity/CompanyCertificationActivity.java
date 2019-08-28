package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyCertificationPresenter;
import shangri.example.com.shangri.presenter.view.CompanyCertificationView;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司认证
 */
public class CompanyCertificationActivity extends BaseActivity<CompanyCertificationView, CompanyCertificationPresenter>
        implements CompanyCertificationView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.cl_progress)
    ConstraintLayout clProgress;
    @BindView(R.id.tv_legal_person)
    TextView tvLegalPerson;
    @BindView(R.id.tv_legal_person2)
    TextView tvLegalPerson2;
    @BindView(R.id.tv_legal_name)
    TextView tvLegalName;
    @BindView(R.id.tv_legal_person_certificate)
    TextView tvLegalPersonCertificate;
    @BindView(R.id.cl_legal_person)
    ConstraintLayout clLegalPerson;
    @BindView(R.id.tv_business_license)
    TextView tvBusinessLicense;
    @BindView(R.id.tv_business_license2)
    TextView tvBusinessLicense2;
    @BindView(R.id.tv_business_license_certification)
    TextView tvBusinessLicenseCertification;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.iv_certification_status)
    ImageView ivCertificationStatus;
    private int is_company;
    private int is_face;


    //第一次发布职位  没有完善公司信息跳转到这里界面（）
    boolean IsFirstFabuJob = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_company_certification;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_company_certification;
    }

    @Override
    protected CompanyCertificationPresenter createPresenter() {
        return new CompanyCertificationPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.certification(UserConfig.getInstance().getToken());
        IsFirstFabuJob = getIntent().getBooleanExtra("IsFirstFabuJob", false);
        if (IsFirstFabuJob) {
            tv_commit.setVisibility(View.VISIBLE);
            tv_title.setText("公司认证（2/3）");
        } else {
            tv_commit.setVisibility(View.GONE);
            tv_title.setText("公司认证");
        }
    }
    @Override
    public void requestFailed(String message) {

    }


    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.certification(UserConfig.getInstance().getToken());
    }

    @OnClick({R.id.tv_commit, R.id.back, R.id.tv_legal_person_certificate, R.id.tv_business_license_certification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_commit:
                Intent intent = new Intent(CompanyCertificationActivity.this, AddJobActivity.class);
                intent.putExtra("IsFirstFabuJob", true);
                startActivity(intent);
                break;
            case R.id.tv_legal_person_certificate:
                if (is_face == 0) {
                    startActivity(new Intent(this, IdentIdCardOneetail.class));
                } else {
                    ToastUtil.showShort("您已认证通过");
                }
                break;
            case R.id.tv_business_license_certification:
                if (is_company > 0) {
                    ToastUtil.showShort("您已认证通过");
                } else {
                    if (is_face > 0) {
                        startActivity(new Intent(this, BusinesIdentsetail.class));
                    } else {
                        ToastUtil.showShort("请先进行法人认证");
                    }
                }
                break;
        }
    }

    @Override
    public void certificationSuccess(CertificationBean resultBean) {
        is_company = resultBean.getIs_company();
        is_face = resultBean.getIs_face();


        if (0 == is_company && 0 == is_face) {
//               都没有认证
            tvLegalPerson.setVisibility(View.GONE);
            tvLegalPerson2.setVisibility(View.VISIBLE);
            tvLegalName.setVisibility(View.GONE);

            tvCompanyName.setVisibility(View.GONE);
            tvBusinessLicense.setVisibility(View.GONE);
            tvBusinessLicense2.setVisibility(View.VISIBLE);
            tvLegalPersonCertificate.setText("去认证");
            tvBusinessLicenseCertification.setText("去认证");
            ivCertificationStatus.setImageResource(R.mipmap.gsrz_rzlcth);
            tv_commit.setText("下一步（暂不认证）");
        } else if (1 == is_company && 1 == is_face) {
//              都认证了
//            resultBean.getCompany().get
            String company_name = resultBean.getCompany_name();
            String legal_name = resultBean.getLegal_name();
            tvLegalPerson.setVisibility(View.VISIBLE);
            tvLegalPerson2.setVisibility(View.GONE);
            tvLegalName.setVisibility(View.VISIBLE);

            tvCompanyName.setVisibility(View.VISIBLE);
            tvBusinessLicense.setVisibility(View.VISIBLE);
            tvBusinessLicense2.setVisibility(View.GONE);
            tvLegalPersonCertificate.setText("已认证");
            tvBusinessLicenseCertification.setText("已认证");
            tvLegalName.setText(legal_name);
            tvCompanyName.setText(company_name);
            ivCertificationStatus.setImageResource(R.mipmap.gsrz_rzlct);
            tv_commit.setText("下一步");
        } else if (1 == is_face && 0 == is_company) {

            String legal_name = resultBean.getLegal_name();
            tvLegalPerson.setVisibility(View.VISIBLE);
            tvLegalPerson2.setVisibility(View.GONE);
            tvLegalName.setVisibility(View.VISIBLE);
            tvLegalName.setText(legal_name);
            tvCompanyName.setVisibility(View.GONE);
            tvBusinessLicense.setVisibility(View.GONE);
            tvBusinessLicense2.setVisibility(View.VISIBLE);
            tvLegalPersonCertificate.setText("已认证");
            tvBusinessLicenseCertification.setText("未认证");

            ivCertificationStatus.setImageResource(R.mipmap.gsrz_rzlco);
            tv_commit.setText("下一步（暂不认证）");
        }

    }
}

package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 合同详情界面
 * Created by admin on 2017/12/22.
 */

public class CompanyIdent extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv_name_four)
    TextView tvNameFour;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv_id_name)
    TextView tvIdName;
    @BindView(R.id.ll_id_nocommit)
    LinearLayout llIdNocommit;
    @BindView(R.id.ll_commit_nocommit)
    LinearLayout llCommitNocommit;

    @BindView(R.id.tv_company_type)
    TextView tv_company_type;

    private ProgressDialogFragment mProgressDialog;
    int is_face = 0;
    int is_company = 0;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_company_ident;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_company_ident;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialogFragment();
//        }
//        mProgressDialog.show(this.getSupportFragmentManager());
//        mPresenter.legalIsface();

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.legalIsface();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        is_face = resultBean.getIs_face();
        is_company = resultBean.getIs_company();
//       	法人认证 0未认证 1已认证
        if (resultBean.getIs_face() == 0) {
            llIdNocommit.setVisibility(View.VISIBLE);
            tvIdName.setVisibility(View.GONE);
        } else {
            tvIdName.setVisibility(View.VISIBLE);
            llIdNocommit.setVisibility(View.GONE);
            tvIdName.setText(resultBean.getLegal_name() + "");
        }
        if (resultBean.getIs_company() == 0) {
            llCommitNocommit.setVisibility(View.VISIBLE);
            tvCompanyName.setVisibility(View.GONE);
            if (resultBean.getIs_face() > 0) {
                tv_company_type.setText("去认证");
            } else {
                tv_company_type.setText("请先进行法人认证");
            }
        } else {
            tvCompanyName.setVisibility(View.VISIBLE);
            llCommitNocommit.setVisibility(View.GONE);
            tvCompanyName.setText(resultBean.getCompany_name() + "");
        }
    }

    @Override
    public void legalTemplate(IsFaceResponse resultBean) {

    }

    @Override
    public void legalContract_signature(ChairmanSignResponse resultBean) {

    }

    @Override
    public void legalContractVerify() {

    }

    @Override
    public void legalanchorVerify() {

    }

    @Override
    public void ontractVerifyCode() {

    }

    @Override
    public void legalTxcontractPush() {

    }

    @Override
    public void legalAnchor_signature(ChairmanSignResponse resultBean) {

    }



    @Override
    public void legalTxcontract() {

    }

    @Override
    public void legalContractRepeal(ChairmanSignResponse resultBean) {

    }


    @Override
    public void legalContractBase(pdfResponse resultBean) {

    }

    @Override
    public void signNumber(pdfResponse resultBean) {

    }

    @OnClick({R.id.setting_back, R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl1:
                if (is_face > 0) {
                    ToastUtil.showShort("您已认证通过");
                } else {
                    startActivity(new Intent(CompanyIdent.this, IdentIdCardOneetail.class));
                }

//                startActivity(new Intent(CompanyIdent.this, IdentIdCardOneetail.class));

                break;
            case R.id.rl2:
                if (is_company > 0) {
                    ToastUtil.showShort("您已认证通过");
                } else {
                    if (is_face > 0) {
                        startActivity(new Intent(CompanyIdent.this, BusinesIdentsetail.class));
                    } else {
                        ToastUtil.showShort("请先进行法人认证");
                    }
                }
//
//                if (is_face > 0) {
//                    startActivity(new Intent(CompanyIdent.this, BusinesIdentsetail.class));
//                } else {
//                    ToastUtil.showShort("请先进行法人认证");
//                }

                break;
        }
    }
}

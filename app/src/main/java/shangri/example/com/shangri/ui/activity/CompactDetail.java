package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
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

public class CompactDetail extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_beizhu)
    TextView tvBeizhu;
    @BindView(R.id.tv_name_one)
    TextView tvNameOne;
    @BindView(R.id.tv_name_two)
    TextView tvNameTwo;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_down_state)
    TextView tvDownState;
    @BindView(R.id.tv_name_three)
    TextView tvNameThree;
    @BindView(R.id.tv_name_four)
    TextView tvNameFour;
    @BindView(R.id.tv_time1)
    TextView tvTime1;

    private ProgressDialogFragment mProgressDialog;

    int id;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_compact_detail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_compact_detail;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        id = getIntent().getIntExtra("id", -1);
        if (id > 0) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.legalDetail(id);
        }

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }

    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }

        if (resultBean.getContract() != null) {
            tvName.setText(resultBean.getContract().getTitle() + "");
            tvEndTime.setText("签署截止日期：" + resultBean.getContract().getEnd_date() + "");
            tvBeizhu.setText("备注：" + resultBean.getContract().getNote());
            switch (resultBean.getContract().getStatus()) {
//            1已签署 2拒签 3待签 4已撤销 5已失效 6草稿
                case 1:
                    tvState.setText("已签署");
                    tvDownState.setText("已签署");
                    break;
                case 2:
                    tvState.setText("已拒签");
                    tvDownState.setText("已拒签");
                    break;
                case 3:
                    tvState.setText("待签");
                    tvDownState.setText("待签");
                    break;
                case 4:
                    tvState.setText("已撤销");
                    tvDownState.setText("已撤销");
                    break;
                case 5:
                    tvState.setText("已失效");
                    tvDownState.setText("已失效");
                    break;
                case 6:
                    tvState.setText("草稿");
                    tvDownState.setText("草稿");
                    break;
            }

            tvTime1.setText(resultBean.getContract().getCreate_date() + "");
            if (resultBean.getContract().getStatus() == 1) {
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(resultBean.getContract().getQ_date() + "");
            } else {
                tvTime.setVisibility(View.GONE);

            }
//tvNameThreetvNameFour
            if (resultBean.getContract().getJ_name().length() > 0) {
                tvNameThree.setText(resultBean.getContract().getJ_name().substring(0, 1));
                if (resultBean.getContract().getJ_name().length() > 1) {
                    tvNameFour.setText(resultBean.getContract().getJ_name().substring(1, resultBean.getContract().getJ_name().length()));
                }
            }
            if (resultBean.getContract().getY_name().length() > 0) {
                tvNameOne.setText(resultBean.getContract().getY_name().substring(0, 1));
                if (resultBean.getContract().getJ_name().length() > 1) {
                    tvNameTwo.setText(resultBean.getContract().getY_name().substring(1, resultBean.getContract().getY_name().length()));
                }
            }
        } else {
            ToastUtil.showShort("数据解析出错");
        }

    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {

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
}

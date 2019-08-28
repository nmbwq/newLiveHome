package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AboutOursBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.presenter.CostomerServicesPresenter;
import shangri.example.com.shangri.presenter.view.costomerServiceView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 我的客服
 * Created by admin on 2017/12/22.
 */

public class DetailQuestionActivity extends BaseActivity<costomerServiceView, CostomerServicesPresenter> implements costomerServiceView {


    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.tv_answer)
    TextView tv_answer;

    private ProgressDialogFragment mProgressDialog;


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_detailquestion;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_detailquestion;
    }

    @Override
    protected CostomerServicesPresenter createPresenter() {
        return new CostomerServicesPresenter(this, this);
    }

    RequestListBean.QaBean bean;

    @Override
    protected void initViewsAndEvents() {
        bean = (RequestListBean.QaBean) getIntent().getSerializableExtra("bean");
        if (bean != null) {
            tvQuestion.setText(bean.getQuestion() + "");
            tv_answer.setText(bean.getAnswer() + "");
        }
    }


    @Override
    public void requestFailed(String message) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void vipQaList(RequestListBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }

    @Override
    public void aboutOurs(AboutOursBean resultBean) {

    }

    @Override
    public void vipQaUseful() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        finish();
    }


    @OnClick({R.id.setting_back, R.id.ll_failed, R.id.ll_success})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.ll_failed:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(this, UserFeedBackActivity.class);
                    intent.putExtra("IsFromQuestion", true);
                    startActivity(intent);
                }
                break;
            case R.id.ll_success:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.vipQaUseful(bean.getId(), 1);
                }
                break;
        }
    }
}

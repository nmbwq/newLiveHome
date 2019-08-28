package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.Config;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AddCompanyBean;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.MessagesBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.LookAnchorPresenter;
import shangri.example.com.shangri.presenter.LookAnchorView;
import shangri.example.com.shangri.presenter.MessagesPresent;
import shangri.example.com.shangri.presenter.WelfarePresenter;
import shangri.example.com.shangri.presenter.view.BaseView;
import shangri.example.com.shangri.presenter.view.MessagesListView;
import shangri.example.com.shangri.presenter.view.WelfareView;
import shangri.example.com.shangri.util.ActivityUtils;

/**
 * 剩余次数
 */
public class RemainderActivity extends BaseActivity<LookAnchorView,LookAnchorPresenter> implements LookAnchorView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.commit)
    TextView commit;

//    @BindView(R.id.detail)
//    TextView detail;

    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.xsfl)
    TextView xsfl;
    @BindView(R.id.tv_01)
    TextView tv_01;
    @BindView(R.id.tv_02)
    TextView tv_02;
    @BindView(R.id.tv_03)
    TextView tv_03;
    @BindView(R.id.tv_04)
    TextView tv_04;
    @Override
    protected void initViewsAndEvents() {
        String num = getIntent().getStringExtra("residue_num");
        tv_1.setText(num);
        mPresenter.leaveAnchor("1", "","","","");
        mPresenter.accountData();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_remainder;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_remainder;
    }
    @Override
    public void leaveAnchor(wantListBean resultBean) {
        tv_3.setText(resultBean.getRe_number()+"");
    }

    @Override
    public void accountData(AccountDataBean dataBean) {
        xsfl.setText("完成公司认证，多增"+dataBean.getLicence_num()+"次拨电话");
        tv_01.setText("1次消耗"+dataBean.getHf_bd()+"波豆");
        tv_02.setText("赠"+dataBean.getRecruit_num()+"次");
        tv_03.setText(dataBean.getLicence_day_num()+"次/每天");
        tv_04.setText(dataBean.getVip_day_num()+"次/每天");
    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }

    @Override
    protected LookAnchorPresenter createPresenter() {
        return new LookAnchorPresenter(this,this);
    }

    @Override
    public void requestFailed(String message) {

    }

    @OnClick({R.id.back, R.id.commit,R.id.detail})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                startActivity(new Intent(this,SelectPayNumberActivity.class));
                break;
//            case R.id.detail:
//                startActivity(new Intent(this,BuyDetailActivity.class));
//                break;
        }
    }
    @Override
    public void getResumeDetailBean(ResumeDetailBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {

    }

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void judgeGrade(JudgeGradeBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

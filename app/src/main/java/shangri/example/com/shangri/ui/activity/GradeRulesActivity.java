package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.presenter.UpGradePresenter;
import shangri.example.com.shangri.presenter.view.UpgradeView;
import shangri.example.com.shangri.ui.adapter.RulesAdapter;
import shangri.example.com.shangri.ui.adapter.WelfareAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;


/**
 * 等级规则界面
 */

public class GradeRulesActivity extends BaseActivity<UpgradeView, UpGradePresenter> implements UpgradeView {


    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;
    private RulesAdapter mAdapter;
    private List<liftRuleBean.RuleBean> mPatrolList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_graderules_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_graderules_layout;
    }

    @Override
    protected UpGradePresenter createPresenter() {
        return new UpGradePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(GradeRulesActivity.this.getSupportFragmentManager());
        mPresenter.liftRule();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }


    @Override
    public void liftRule(liftRuleBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mAdapter == null) {
            mPatrolList.add(new liftRuleBean.RuleBean("等级", "邀请主播注册", "邀请主播上传简历"));
            for (int i = 0; i < resultBean.getRule().size(); i++) {
                mPatrolList.add(resultBean.getRule().get(i));
            }
            rvPartol.setLayoutManager(new LinearLayoutManager(GradeRulesActivity.this, LinearLayoutManager.VERTICAL, false));
            mAdapter = new RulesAdapter(GradeRulesActivity.this, R.layout.new_grade_rules, mPatrolList);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rvPartol.setAdapter(mAdapter);
        }
    }

    @Override
    public void handUpgrade() {

    }

    @OnClick({R.id.setting_back, R.id.appbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.appbar:
                break;
        }
    }
}

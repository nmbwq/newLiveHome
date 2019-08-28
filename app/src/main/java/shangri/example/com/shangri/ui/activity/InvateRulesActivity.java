package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
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
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.UpGradePresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.presenter.view.UpgradeView;
import shangri.example.com.shangri.ui.adapter.WelfareAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;

/**
 * 邀请规则
 */

public class InvateRulesActivity extends BaseActivity<UpgradeView, UpGradePresenter> implements UpgradeView {


    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;
    private ProgressDialogFragment mProgressDialog;

    private WelfareAdapter mAdapter;
    private List<liftRuleBean.WelfareBean> mPatrolList = new ArrayList<>();

    @Override
    protected void initViewsAndEvents() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.liftRule();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.invaterules_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.invaterules_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected UpGradePresenter createPresenter() {
        return new UpGradePresenter(this, this);
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
    public void liftRule(liftRuleBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mAdapter == null) {
            mPatrolList.add(new liftRuleBean.WelfareBean("奖励（波币）", "邀请主播注册", "邀请主播上传简历", "个人简历被查看"));
            for (int i = 0; i < resultBean.getWelfare().size(); i++) {
                mPatrolList.add(resultBean.getWelfare().get(i));
            }
            rvPartol.setLayoutManager(new LinearLayoutManager(InvateRulesActivity.this, LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new WelfareAdapter(InvateRulesActivity.this, R.layout.new_item_welfare, mPatrolList,true);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rvPartol.setAdapter(mAdapter);
        }
    }

    @Override
    public void handUpgrade() {

    }
}

package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.presenter.CompanyPresenter;
import shangri.example.com.shangri.presenter.view.companyView;
import shangri.example.com.shangri.ui.adapter.AlertDateAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 到期提醒界面
 * Created by admin on 2017/12/22.
 */

public class AlertDateListActivity extends BaseActivity<companyView, CompanyPresenter> implements companyView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    private List<companyListBean.CompanysBean> mPatrolList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_alertdate_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_alertdate_list;
    }

    @Override
    protected CompanyPresenter createPresenter() {
        return new CompanyPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        companyListBean.CompanysBean all = new companyListBean.CompanysBean(0, "不提醒");
        companyListBean.CompanysBean one = new companyListBean.CompanysBean(1, "截止前1天提醒");
        companyListBean.CompanysBean two = new companyListBean.CompanysBean(2, "截止前2天提醒");
        companyListBean.CompanysBean three = new companyListBean.CompanysBean(3, "截止前3天提醒");
        mPatrolList.add(all);
        mPatrolList.add(one);
        mPatrolList.add(two);
        mPatrolList.add(three);
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        currPage = 1;
        rv_partol.setLayoutManager(new FastLinearScrollManger(AlertDateListActivity.this));
        AlertDateAdapter mAdapter = new AlertDateAdapter(this, R.layout.item_select_alert, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                ActivityAddNewTask.expire_remind = position;
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(AlertDateListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
//
                }
                sv_partol.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(AlertDateListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;

                }
                sv_partol.onFinishFreshAndLoad();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;

        }
    }


    @Override
    public void companySearch(companyListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void companyDetail(companyDetailBean resultBean) {

    }

    @Override
    public void adminApply() {

    }

    @Override
    public void partSelect(partSelectBean resultBean) {

    }

    @Override
    public void personalSearch(personalSearchBean resultBean) {

    }

    @Override
    public void adminSelect(SearchBean resultBean) {

    }

}

package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.request.StructureBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.LawWorksPayPresenter;
import shangri.example.com.shangri.presenter.view.LowWorksPayView;
import shangri.example.com.shangri.ui.adapter.SendResumeAdapter;
import shangri.example.com.shangri.ui.adapter.ZhiweListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播投递列表
 * Created by admin on 2017/12/22.
 */

public class AnchorDropListActivity extends BaseActivity<LowWorksPayView, LawWorksPayPresenter> implements LowWorksPayView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    TextView right;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    //1 正常 2审核中 3审核失败 4已关闭  0全部
    int type = 0;
    int mPageNum = 0;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private SendResumeAdapter mAdapter;
    private List<sendResumeBean.ResumeBean> mList = new ArrayList<>();


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected LawWorksPayPresenter createPresenter() {
        return new LawWorksPayPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(AnchorDropListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.sendResume();
        }
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        reload();
        title.setText("主播投递");
        right.setVisibility(View.GONE);
        rv_partol.setLayoutManager(new FastLinearScrollManger(AnchorDropListActivity.this));
        mAdapter = new SendResumeAdapter(this, R.layout.item_toudi_item, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
    }

    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(AnchorDropListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.sendResume();
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }

            @Override
            public void onLoadmore() {
                requestTastList();
            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.sendResume();
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.right, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                reload();
                break;
            case R.id.right:
                break;

        }
    }


    @Override
    public void legalmyPackage(legalMypagerBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void positionList(PositionListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        mPageNum = resultBean.getList().getLast_page();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void sendResume(sendResumeBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            mList.clear();
            if (resultBean.getResume().size() > 0) {
                sv_partol.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                sv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getResume());
        mList = mAdapter.getAll();
    }

    @Override
    public void upList(upListBean resultBean) {

    }

    @Override
    public void anchorDetail(anchorDetailBean resultBean) {

    }

    @Override
    public void noLikeList(BossDataBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void upAnchor() {

    }
}

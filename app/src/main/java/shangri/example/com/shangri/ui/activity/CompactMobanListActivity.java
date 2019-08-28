package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

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
import shangri.example.com.shangri.ui.adapter.CompactMobanAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司模板界面
 * Created by admin on 2017/12/22.
 */

public class CompactMobanListActivity extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private CompactMobanAdapter mAdapter;
    private List<CompactMobanResponse.TemplatesBean> mPatrolList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        title.setText("选择模板");
        if (!NetWorkUtil.isNetworkConnected(CompactMobanListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.templateList();
            rv_partol.setLayoutManager(new FastLinearScrollManger(CompactMobanListActivity.this));
            mAdapter = new CompactMobanAdapter(this, R.layout.item_compactmoban_layout, mPatrolList);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rv_partol.setAdapter(mAdapter);
        }
    }

    @Override
    public void requestFailed(String message) {

    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(CompactMobanListActivity.this)) {
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
            mPresenter.templateList();
            rv_partol.setLayoutManager(new FastLinearScrollManger(CompactMobanListActivity.this));
            mAdapter = new CompactMobanAdapter(this, R.layout.item_compactmoban_layout, mPatrolList);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rv_partol.setAdapter(mAdapter);
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
                loadData();
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(CompactMobanListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
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

    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {

    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            mPatrolList.clear();
            if (resultBean.getTemplates().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                rv_partol.setVisibility(View.GONE);
            }
        }
        mAdapter.addAll(resultBean.getTemplates());
        mPatrolList = mAdapter.getAll();
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


    @OnClick({R.id.reload, R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.setting_back:
                finish();
                break;
        }
    }
}

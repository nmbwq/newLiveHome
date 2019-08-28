package shangri.example.com.shangri.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.event.DateRefush;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.adapter.CompactAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的合同列表界面
 * Created by admin on 2017/12/22.
 */


public class MyCompactFreagment extends BaseLazyFragment<CompactlistView, CompactListPresenter> implements CompactlistView {

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    private Unbinder unbinder;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private CompactAdapter Adapter;
    private ProgressDialogFragment mProgressDialog;
    private List<CompactListResponse.ContractsBean> mList = new ArrayList<>();
    //页码 默认0 0全部 1待签 2已签 3草稿 4其他
    private int type = 0;

    public static MyCompactFreagment getInstance(int type) {
        MyCompactFreagment fragment = new MyCompactFreagment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        type = arguments.getInt("type");
        initSpringView();
        rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
        Adapter = new CompactAdapter(getActivity(), R.layout.new_item_compact, mList, type);
        Adapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(Adapter);
        onDataRefresh();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void loadData() {

    }


    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                ACTION_TYPE = ACTION_FRESH;
                onDataRefresh();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                requestPatolsList();

            }
        });
    }

    private void onDataRefresh() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.legalGuildContract(type, currPage);
        }

    }

    private void requestPatolsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());

            if (currPage < mPageNum) {
                currPage++;
                mPresenter.legalGuildContract(type, currPage);
            } else {
                ToastUtil.showShort("已加载全部");
                sv_partol.onFinishFreshAndLoad(); //停止加载
            }
        }


    }


    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        onDataRefresh();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_all_collect2;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_all_collect2;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(getActivity(), this);
    }

    @Override
    public void legalGuildContract(CompactListResponse resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        if (currPage == 1) {
            Adapter.setData(resultBean.getContracts());
            if (resultBean.getContracts().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        } else {
            Adapter.addAll(resultBean.getContracts());
        }

        mList = Adapter.getAll();
        mPageNum = resultBean.getTotal_page();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DateRefush event) {
            ACTION_TYPE = ACTION_FRESH;
            onDataRefresh();
            Log.d("Debug","到达草稿箱eventbus里面");
    }


    @Override
    public void legalDetail(CompactDetailResponse resultBean) {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.reload, R.id.appbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                onDataRefresh();
                break;
            case R.id.appbar:
                break;
        }
    }
}

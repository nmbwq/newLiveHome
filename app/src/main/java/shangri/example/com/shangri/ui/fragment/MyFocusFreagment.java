package shangri.example.com.shangri.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.adapter.anchorListAdapter;
import shangri.example.com.shangri.ui.adapter.guildListAdapter;
import shangri.example.com.shangri.ui.adapter.platfromListAdapter;
import shangri.example.com.shangri.ui.listener.AbstractClass.OnRecyclerItemClickListener;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的直播平台
 * Created by admin on 2017/12/22.
 */

public class MyFocusFreagment extends BaseLazyFragment<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    LinearLayout rl_net_info;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private Unbinder unbinder;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private anchorListAdapter anchormAdapter;
    private guildListAdapter guildmAdapter;
    private platfromListAdapter platfrommAdapter;

    private List<EncyclopediaList.AnchorBean> AnchorBeanList = new ArrayList<>();
    private List<EncyclopediaList.GuildBean> GuildlList = new ArrayList<>();
    private List<EncyclopediaList.PlatfromBean> PlatfromList = new ArrayList<>();
    //1是找平台 2找公会 3找主播
    private int type = -1;

    public static MyFocusFreagment getInstance(int type) {
        MyFocusFreagment fragment = new MyFocusFreagment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.guanzhu_kong));
        tv_text1_empty.setText("当你关注了一些内容时");
        tv_text2_empty.setText("这里会发生微妙变化吆~~");
        Bundle arguments = getArguments();
        type = arguments.getInt("type");
        switch (type) {
            case 1:
                rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
                platfrommAdapter = new platfromListAdapter(getActivity(), R.layout.platfrom_item_layout, PlatfromList);
                platfrommAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(platfrommAdapter);
                break;
            case 2:
                rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
                guildmAdapter = new guildListAdapter(getActivity(), R.layout.society_item_layout, GuildlList);
                guildmAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(guildmAdapter);
                break;
            case 3:
                rv_partol.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                anchormAdapter = new anchorListAdapter(getActivity(), R.layout.zhubo_item_layout, AnchorBeanList);
                rv_partol.addItemDecoration(new GridSpacingItemDecoration(10));
                anchormAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(anchormAdapter);
                break;
            default:
                break;
        }
        initSpringView();
        loadData();
    }


    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
//        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                onDataRefresh();
            }

            @Override
            public void onLoadmore() {
                onDataRefresh();
            }
        });
        rv_partol.addOnItemTouchListener(new OnRecyclerItemClickListener(rv_partol) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
            }

            @Override
            public void onItemLOngClick(RecyclerView.ViewHolder viewHolder) {

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
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.wikiFocus(type);
        }
    }

    protected void loadData() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialogFragment();
//        }
//        mProgressDialog.show(getActivity().getSupportFragmentManager());
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.wikiFocus(type);
        }


    }

    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_all_collect;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_all_collect;
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(getActivity(), this);
    }


    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {

    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {
        AnchorBeanList = resultBean.getAnchor();
        GuildlList = resultBean.getGuild();
        PlatfromList = resultBean.getPlatfrom();
        Log.d("Debug", "当前的数量为" + PlatfromList.size() + "--" + GuildlList.size() + "--" + AnchorBeanList.size());

        switch (type) {
            case 1:
                platfrommAdapter.setData(PlatfromList);
                if (PlatfromList.size() == 0) {
                    activity_empty_linshi.setVisibility(View.VISIBLE);
                    rv_partol.setVisibility(View.GONE);
                } else {
                    activity_empty_linshi.setVisibility(View.GONE);
                    rv_partol.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                guildmAdapter.setData(GuildlList);
                if (GuildlList.size() == 0) {
                    activity_empty_linshi.setVisibility(View.VISIBLE);
                    rv_partol.setVisibility(View.GONE);
                } else {
                    activity_empty_linshi.setVisibility(View.GONE);
                    rv_partol.setVisibility(View.VISIBLE);
                }
                break;
            case 3:
                anchormAdapter.setData(AnchorBeanList);
                if (AnchorBeanList.size() == 0) {
                    activity_empty_linshi.setVisibility(View.VISIBLE);
                    rv_partol.setVisibility(View.GONE);
                } else {
                    activity_empty_linshi.setVisibility(View.GONE);
                    rv_partol.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {

    }

    @Override
    public void consultShare() {

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
    }

    @OnClick({R.id.reload, R.id.appbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.appbar:
                break;
        }
    }
}

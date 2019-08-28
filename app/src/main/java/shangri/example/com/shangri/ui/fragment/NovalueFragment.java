package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.event.SelectManBean;
import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;
import shangri.example.com.shangri.presenter.SelectManPresenter;
import shangri.example.com.shangri.presenter.view.SelectmanView;
import shangri.example.com.shangri.ui.activity.AnchoDetailActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.ui.view.MyTextView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.VerticalScrollView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;


/**
 * 资讯基类fragment
 * Created by chengaofu on 2017/6/21.
 */

public class NovalueFragment extends BaseLazyFragment<SelectmanView, SelectManPresenter> implements SelectmanView {

    @BindView(R.id.rv_partol)
    ListViewForScrollView rv_partol;
    @BindView(R.id.verticalScrollView)
    VerticalScrollView verticalScrollView;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rl_net_info)
    RelativeLayout rlNetInfo;
    private ProgressDialogFragment mProgressDialog;

    private List<AnchorSerchBean.AnchorBean> mList = new ArrayList<>();
    private CommonAdapter<AnchorSerchBean.AnchorBean> mAdapter;

    String guild_name;
    String guildid;
    String mSearchContent;

    //    private ProgressDialogFragment mProgressDialog;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.novalue_freagment;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.novalue_freagment;
    }

    @Override
    protected SelectManPresenter createPresenter() {
        return new SelectManPresenter(getActivity(), this);
    }

    @Override
    protected void initView() {
        initSpringView();
        loadData();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectManBean event) {
        Log.d("Debug", "搜索主播接收到推送");
        guild_name = event.getGuild_name();
        guildid = event.getGuildid();
        mSearchContent = event.getmSearchContent();
        //有搜索内容发请求
        if (mSearchContent.length() > 0) {
            if (getUserVisibleHint()){
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
            }
            mPresenter.anchorOplayer(guildid, mSearchContent);
        }


    }


    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                }
                sv_partol.onFinishFreshAndLoad();

            }
        });

    }


    public void loadData() {
        mAdapter = new CommonAdapter<AnchorSerchBean.AnchorBean>(getActivity(), mList, R.layout.anchor_select_item) {
            @Override
            public void convert(final ViewHolder helper, final AnchorSerchBean.AnchorBean item) {
                MyTextView tv_name = (MyTextView) helper.getView(R.id.tv_name);
                LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                tv_name.setSpecifiedTextsColor(item.getAnchor_name(), "哈哈", Color.parseColor("#d0a76c"));
                ll_parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent7 = new Intent(getActivity(), AnchoDetailActivity.class);
                        intent7.putExtra("platfrom_name", guild_name);
                        intent7.putExtra("anchor_uid", item.getAnchor_id());
                        intent7.putExtra("guild_id", guildid);
                        ChuandiBean.Detailtime_slot = "week";
                        startActivity(intent7);
                    }
                });
            }
        };
        rv_partol.setAdapter(mAdapter);
    }


//

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void taskSelectanchor(taskSelectListBean resultBean) {

    }

    @Override
    public void anchorOplayer(AnchorSerchBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mList.clear();
        if (resultBean.getAnchor().size() > 0) {
            sv_partol.setVisibility(View.VISIBLE);
            mSearchNotFound.setVisibility(View.GONE);
        } else {
            sv_partol.setVisibility(View.GONE);
            mSearchNotFound.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < resultBean.getAnchor().size(); i++) {
            mList.add(resultBean.getAnchor().get(i));
        }
        mAdapter.setmDatas(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void cacheAdd() {

    }

    @Override
    public void Add() {

    }
}

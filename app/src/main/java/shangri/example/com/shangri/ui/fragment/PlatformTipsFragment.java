package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.response.PageInfoBean;
import shangri.example.com.shangri.model.bean.response.TipsPageDataBean;
import shangri.example.com.shangri.presenter.TipsPresenter;
import shangri.example.com.shangri.presenter.view.TipsView;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.ui.activity.MsgDetailActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.adapter.PlatformTipsAdapter;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 平台公告
 * Created by chengaofu on 2017/6/26.
 */

public class PlatformTipsFragment extends BaseLazyFragment<TipsView, TipsPresenter> implements TipsView {

    @BindView(R.id.platform_tips_springview)
    SpringView mPlatformTipSpringView;
    @BindView(R.id.platform_tips_irv)
    RecyclerView mTipsRecycler;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;

    private PlatformTipsAdapter mAdapter;
    private List<TipsPageDataBean> mPageDataList = new ArrayList<>();
    private int currPage = 1;
    private final int PAGE_SIZE = 3;
    private int mPageNum = 0; //总页数

    @Override
    protected void initView() {
        initSpringView();
        mPageDataList.clear();
        mTipsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new PlatformTipsAdapter(getActivity(), R.layout.item_platformtips, mPageDataList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mTipsRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                TipsPageDataBean tipsData = mPageDataList.get(position);
                if (tipsData != null) {
                    long id = tipsData.getId();
                    Intent intent = new Intent(getActivity(), MsgDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initSpringView() {
        mPlatformTipSpringView.setType(SpringView.Type.FOLLOW);
        mPlatformTipSpringView.setHeader(new SpringViewHeader(getActivity()));
        mPlatformTipSpringView.setFooter(new SpringViewFooter(getActivity()));
        mPlatformTipSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mPlatformTipSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    loadData();
                }
            }

            @Override
            public void onLoadmore() {
                if(!NetWorkUtil.isNetworkConnected(getActivity())){
                    mPlatformTipSpringView.onFinishFreshAndLoad(); //停止加载
                }else{
                    queryMessageList();
                }

            }
        });

    }

    @OnClick({R.id.reload})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.reload:
                loadData();
                break;
        }
    }

    @Override
    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            mPlatformTipSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            mPlatformTipSpringView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }

        currPage = 1;
        mPageDataList.clear();
        mAdapter.notifyDataSetChanged();
        mPresenter.queryMessageList(String.valueOf(1), currPage, PAGE_SIZE); //消息类型(1公告，2消息)

    }

    private void queryMessageList(){
        if (currPage < mPageNum) {
            currPage++;
            mPlatformTipSpringView.onFinishFreshAndLoad();
            mPresenter.queryMessageList(String.valueOf(1), currPage, PAGE_SIZE);
        } else {
            mPlatformTipSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    @Override
    public void queryMessageList(List<TipsPageDataBean> pageData, PageInfoBean pageInfo) {
        mPlatformTipSpringView.onFinishFreshAndLoad();
        mAdapter.addAllAt(mPageDataList.size(), pageData);
        mPageNum = Integer.parseInt(pageInfo.getPageNum());
    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_platform_tips;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_platform_tips;
    }

    @Override
    protected TipsPresenter createPresenter() {
        return new TipsPresenter(getActivity(), this);
    }


    @Override
    public void requestFailed(String message) {
        if(TextUtils.isEmpty(message)) return;
        if(message.contains(String.valueOf(Constant.CODE_100027))){
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
            getActivity().finish();
        }
    }
}

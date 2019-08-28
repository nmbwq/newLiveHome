package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.response.BannerInfoBean;
import shangri.example.com.shangri.model.bean.response.PageDataBean;
import shangri.example.com.shangri.model.bean.response.PageInfoBean;
import shangri.example.com.shangri.model.bean.response.PraiseInfoBean;
import shangri.example.com.shangri.presenter.NewsListPresenter;
import shangri.example.com.shangri.presenter.view.NewsListView;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.activity.NewsDetailActivity;
import shangri.example.com.shangri.ui.adapter.VideoNewsAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.FastStaggeredGridScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TakeTurns;
import shangri.example.com.shangri.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 巡查Fragment
 * Created by chengaofu on 2017/6/22.
 */

public class VideoNewsFragment extends BaseLazyFragment<NewsListView, NewsListPresenter> implements NewsListView {
    @BindView(R.id.news_video_irv)
    RecyclerView mNewsVideoRecycler;
    @BindView(R.id.news_video_springview)
    SpringView mNewsVideoSpringView;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.float_toTop)
    ImageButton mFloatToTop;
    //轮播图
    private ConvenientBanner mBanner;
    private List<BannerInfoBean> mBannerInfoBeanList = new ArrayList<>();

    //用来添加消息头
    private List<PageDataBean> mNewsList = new ArrayList<>();
    private VideoNewsAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数
    private int mPraisePos = 0; //点赞的位置

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    @Override
    protected void initView() {
        initSpringView();
        mNewsList.clear();
        mNewsVideoRecycler.setLayoutManager(new FastStaggeredGridScrollManger(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new VideoNewsAdapter(getActivity(), R.layout.item_video_news, mNewsList);
        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                if (position <= 0) return;
                mPraisePos = position - 1;
                PageDataBean pageData = mNewsList.get(mPraisePos);
                long infoId = pageData.getId();
                byte praiseOrNot = 0;
                if (pageData.getIsPraise() == 0) {
                    praiseOrNot = 1;// 未点赞，则点赞
                } else if (pageData.getIsPraise() == 1) {
                    praiseOrNot = 0; // 已点赞，则取消点赞
                }
                mPresenter.requestPraise(infoId, praiseOrNot);
            }

            @Override
            public void onColltion(int position) {

            }
        });
        mAdapter.setNewsListListener(new NewsListListener() {

            @Override
            public void onItemClickListener(int position) {
                if (position <= 0) return;
                PageDataBean bean = mNewsList.get(position - 1);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("id", bean.getId());
                intent.putExtra("pos", position - 1);
                intent.putExtra("type", Constant.TYPE_VIDEO);
                startActivity(intent);
            }
        });

        mNewsVideoRecycler.addItemDecoration(new GridSpacingItemDecoration(20));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mNewsVideoRecycler.setAdapter(mAdapter);
        mNewsVideoRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int[] lastPositions = new int[manager.getSpanCount()];
                int firstVisibleItemPosition = manager.findFirstVisibleItemPositions(lastPositions)[0];
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition == 0) {
                        mFloatToTop.setVisibility(View.INVISIBLE);
                    } else {
                        mFloatToTop.setVisibility(View.VISIBLE);
                    }

                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
                    mFloatToTop.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        setHeaderPage();
//        EventBus.getDefault().register(this);
    }

    private void initSpringView() {
        mNewsVideoSpringView.setType(SpringView.Type.FOLLOW);
        mNewsVideoSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsVideoSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsVideoSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mNewsVideoSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    loadData();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mNewsVideoSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    requestNewsList();
                }

            }
        });

    }

    private void setHeaderPage() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.widget_news_header, null);
        mBanner = header.findViewById(R.id.convenientBanner);
        mAdapter.setHeaderView(header);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(getActivity(), this);
    }


    @Override
    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            mNewsVideoSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            mNewsVideoSpringView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }
        currPage = 1;
        mPresenter.getBannerDetailByTypeId(String.valueOf(2));
        mPresenter.requestNewsList(2, currPage, PAGE_SIZE);
    }

    private void requestNewsList() {

        if (currPage < mPageNum) {
            currPage++;
            mNewsVideoSpringView.onFinishFreshAndLoad();
            mPresenter.requestNewsList(1, currPage, PAGE_SIZE);
        } else {
            mNewsVideoSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    @OnClick({R.id.reload, R.id.float_toTop})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.float_toTop:
                mNewsVideoRecycler.smoothScrollToPosition(0);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        if (mBanner != null) {
            mBanner.startTurning(3000);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (mBanner != null) {
            mBanner.stopTurning();
        }
    }

    @Override
    public void requestNewsList(List<PageDataBean> pageData, PageInfoBean pageInfo) {
        mNewsVideoSpringView.onFinishFreshAndLoad();
        if(ACTION_TYPE == ACTION_FRESH){
            mNewsList = pageData;
            mAdapter.setData(pageData);
        }else if(ACTION_TYPE == ACTION_LOAD_MORE){
            mAdapter.addAllAt(mNewsList.size(), pageData);
        }
        mPageNum = Integer.parseInt(pageInfo.getPageNum());
    }

    @Override
    public void praise(PraiseInfoBean resultBean) {
        refreshPraiseCount(mPraisePos, resultBean.getPraiseCount()); //点赞刷新
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(BaseEvent baseEvent) {

        if(baseEvent.getEventId() == Constant.TYPE_PRAISE){   //详情点赞后 列表局部刷新
            PraiseEventBean messageEvent = (PraiseEventBean) baseEvent.getData();
            if(messageEvent == null) return;
            if (messageEvent.getType() == Constant.TYPE_VIDEO) {
                int praisePos = messageEvent.getPraisePosition();
                long praiseCount = messageEvent.getPraiseCount();
                refreshPraiseCount(praisePos, praiseCount);
            }
        }else if(baseEvent.getEventId() == Constant.TYPE_BROWSE){ //详情中浏览 列表中刷新
            BrowseEventBean messageEvent = (BrowseEventBean) baseEvent.getData();
            if(messageEvent == null) return;
            if (messageEvent.getType() == Constant.TYPE_VIDEO) {
                int browsePos = messageEvent.getBrowsePosition();
                long browseCount = messageEvent.getBrowseCount();
                refreshBrowseCount(browsePos, browseCount);
            }
        }
    }


    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        PageDataBean pageData = mNewsList.get(pos);
        if (pageData.getIsPraise() == 0) {
            pageData.setIsPraise((byte) 1);
            //点赞+1
        } else if (pageData.getIsPraise() == 1) {
            pageData.setIsPraise((byte) 0);
            //点赞-1
        }
        pageData.setPraiseCount(count);
        //mPraisePos+1 是因为有头部
        mAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
    }

    private void refreshBrowseCount(int pos, long count) {//更新浏览数量

        PageDataBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            pageData.setBrowseCount(String.valueOf(count));
            //+1 是因为有头部
            mAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部
        }
    }

    @Override
    public void getBannerDetailByTypeId(List<BannerInfoBean> bannerInfoBeanList) {
        mBannerInfoBeanList.clear();
        mBannerInfoBeanList.addAll(bannerInfoBeanList);
        //滚动图
        new TakeTurns(getActivity(), mBannerInfoBeanList, mBanner, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BannerInfoBean bannerInfo = mBannerInfoBeanList.get(position);
                if (bannerInfo == null) return;
                if (bannerInfo.getLink_type() == 1) { //链接类型(1资讯,2活动URL)
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    long id = bannerInfo.getLink_info_id();
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (bannerInfo.getLink_type() == 2) {
                    Intent intent = new Intent(getActivity(), ActivityWebView.class);
                    intent.putExtra("title", "活动");
                    intent.putExtra("url", bannerInfo.getLink_address());
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

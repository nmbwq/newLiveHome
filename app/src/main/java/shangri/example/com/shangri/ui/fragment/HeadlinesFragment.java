package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.event.RegisterCollect;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.presenter.HeadPresenter;
import shangri.example.com.shangri.presenter.view.HeadLinesView;
import shangri.example.com.shangri.ui.activity.AudioPlayActivity;
import shangri.example.com.shangri.ui.activity.InterlocutionActivity;
import shangri.example.com.shangri.ui.adapter.HeadLinesAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.ui.webview.ActivityWebView1;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.HeadTakeTurn;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 头条Fragment
 * Created by chengaofu on 2017/6/22.
 */

public class HeadlinesFragment extends BaseLazyFragment<HeadLinesView, HeadPresenter> implements HeadLinesView, View.OnClickListener {

    @BindView(R.id.news_entertain_irv)
    RecyclerView mNewsEntertainRecycler;
    @BindView(R.id.news_entertain_springview)
    SpringView mNewsEntertainSpringView;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.float_toTop)
    ImageButton mFloatToTop;
    //轮播图
    private ConvenientBanner mBanner;
    private List<HeadLinesData.BannersBean> mBannerInfoBeanList = new ArrayList<>();

    //列表
    private List<HeadLinesData.ArticlesBean> mNewsList = new ArrayList<>();
    private HeadLinesAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数
    private int mPraisePos = 0; //点赞的位置
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private String type = "1";
    private HeadLinesData mresultBean;
    private TextView headiv1, headiv2, headiv3, headiv4;

    @Override
    protected void initView() {
        Log.d("Debug", "到达initView方法");
        initSpringView();
        mNewsList.clear();
        mNewsEntertainRecycler.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new HeadLinesAdapter(getActivity(), R.layout.item_consultation, mNewsList);
        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                mPraisePos = position;
                HeadLinesData.ArticlesBean pageData = mNewsList.get(mPraisePos);
                String infoId = pageData.getId();
                if (pageData.getRegister_good() == 0) {
                    mPresenter.requestPraise(infoId, mPraisePos);// 未点赞，则点赞
                } else if (pageData.getRegister_good() == 1) {
                    mPresenter.requestCancel(infoId, mPraisePos); // 已点赞，则取消点赞
                }

            }

            @Override
            public void onColltion(int position) {

            }
        });

        mAdapter.setNewsListListener(new NewsListListener() {

            @Override
            public void onItemClickListener(int position) {
                if (PointUtils.isFastClick()) {
                    HeadLinesData.ArticlesBean bean = mNewsList.get(position - 1);
                    if (TextUtils.isEmpty(bean.getAudio_url())) {
                        Intent intent = new Intent(getActivity(), ActivityWebView.class);
                        intent.putExtra("title", bean.getTitle());
                        intent.putExtra("imageurl", bean.getCover_url());
                        intent.putExtra("url", bean.getArticle_url());
                        intent.putExtra("id", bean.getId() + "");
                        //浏览量
                        intent.putExtra("browse_amount", bean.getBrowse_amount());
                        //点击量
                        intent.putExtra("good_amount", bean.getGood_amount());
                        //收藏量
                        intent.putExtra("collect_amount", bean.getCollect_amount());
                        //0为未点赞 1为已点赞
                        intent.putExtra("register_good", bean.getRegister_good());
                        //0为未收藏 1为已收藏
                        intent.putExtra("register_collect", bean.getRegister_collect());
                        //当前列表的下标
                        intent.putExtra("position", position - 1);
                        intent.putExtra("type", "2");
                        //分享需要的参数
                        intent.putExtra("isFormType", "article");
                        mPresenter.requestBrowse(bean.getId(), position - 1);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
                        intent.putExtra("html", bean.getArticle_url());     //页面点赞
                        intent.putExtra("imageurl", bean.getCover_url());   //图片地址
                        intent.putExtra("audiourl", bean.getAudio_url());   //音频地址
                        intent.putExtra("title", bean.getTitle());          //标题
                        intent.putExtra("register", bean.getRegister_good());
                        intent.putExtra("position", position - 1);
                        intent.putExtra("pageid", "ConsultationFragment");
                        intent.putExtra("type", "2");
                        intent.putExtra("id", bean.getId());
                        //分享需要的参数
                        intent.putExtra("isFormType", "article");
                        startActivity(intent);
                    }
                }
            }
        });
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mNewsEntertainRecycler.setAdapter(mAdapter);
        mNewsEntertainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
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
        loadData();
        setHeaderPage();
        EventBus.getDefault().register(this);
    }

    private void initSpringView() {
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                requestNewsList();
            }
        });

    }

    private void setHeaderPage() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.headlines_header, null);
        mBanner = header.findViewById(R.id.convenientBanner);
        headiv1 = header.findViewById(R.id.iv1);
        headiv2 = header.findViewById(R.id.iv2);
        headiv3 = header.findViewById(R.id.iv3);
        headiv4 = header.findViewById(R.id.iv4);
        headiv1.setOnClickListener(this);
        headiv2.setOnClickListener(this);
        headiv3.setOnClickListener(this);
        headiv4.setOnClickListener(this);
        mBanner.setMargin();
        mAdapter.setHeaderView(header);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_entertain;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_entertain;
    }

    @Override
    protected HeadPresenter createPresenter() {
        return new HeadPresenter(getActivity(), this);
    }


    @Override
    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            return;
        } else {
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }
        mNewsList.clear();
        currPage = 1;
        mPresenter.bannerDetail(String.valueOf(currPage), type);
    }

    @OnClick({R.id.reload, R.id.float_toTop})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    loadData();
                }
                break;
            case R.id.float_toTop:
                if (PointUtils.isFastClick()) {
                    mNewsEntertainRecycler.smoothScrollToPosition(0);
                }
                break;
            case R.id.iv1:
                if (PointUtils.isFastClick()) {
                    setTextColor();
                    headiv1.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                    type = mresultBean.getCatagorys().get(0).getId();
                    loadData();
                }
                break;
            case R.id.iv2:
                if (PointUtils.isFastClick()) {
                    setTextColor();
                    headiv2.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                    type = mresultBean.getCatagorys().get(1).getId();
                    loadData();
                }
                break;
            case R.id.iv3:
                if (PointUtils.isFastClick()) {
                    setTextColor();
                    headiv3.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                    type = mresultBean.getCatagorys().get(2).getId();
                    loadData();
                }
                break;
            case R.id.iv4:
                if (PointUtils.isFastClick()) {
                    setTextColor();
                    headiv4.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                    ActivityUtils.startActivity(getActivity(), InterlocutionActivity.class);
                }
                break;
        }
    }

    private void setTextColor() {
        headiv1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        headiv2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        headiv3.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        headiv4.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    private void requestNewsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            if (currPage < mPageNum) {
                currPage++;
                mPresenter.bannerDetail(String.valueOf(currPage), type);
            } else {
                ToastUtil.showShort("已加载全部");
                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
            }
        }
    }

    private void newsList(HeadLinesData pageInfo) {
        mPageNum = pageInfo.getTotal_page();
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
            mAdapter.setData(pageInfo.getArticles());
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAllAt(mNewsList.size(), pageInfo.getArticles());
        }
        mNewsList = mAdapter.getAll();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(BaseEvent baseEvent) {
        Log.d("Debug", "首页面数据" + baseEvent.getData().toString());
        if (baseEvent.getEventId() == Constant.TYPE_HEAD_PRAISE) {   //详情点赞后 列表局部刷新
            PraiseEventBean messageEvent = (PraiseEventBean) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getPraisePosition();
                HeadLinesData.ArticlesBean pageData = mNewsList.get(praisePos);
                if (pageData.getRegister_good() == 0) {
                    pageData.setRegister_good(1);
                    pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) + 1) + "");
                    mAdapter.notifyDataSetChanged();
                } else if (pageData.getRegister_good() == 1) {
                    pageData.setRegister_good(0);
                    pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) - 1) + "");
                    mAdapter.notifyDataSetChanged();
                }
            }
        } else if (baseEvent.getEventId() == Constant.TYPE_HEAD_COllECT) { //详情中收藏
            RegisterCollect messageEvent = (RegisterCollect) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getCollectPosition();
                HeadLinesData.ArticlesBean pageData = mNewsList.get(praisePos);
                if (pageData.getRegister_collect() == 0) {
                    pageData.setRegister_collect(1);
                    pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) + 1) + "");
                    mAdapter.notifyDataSetChanged();
                } else if (pageData.getRegister_collect() == 1) {
                    pageData.setRegister_collect(0);
                    pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) - 1) + "");
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void refreshPeiCollect(int pos) { //收藏刷新
        HeadLinesData.ArticlesBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
            }
//            mAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
        }
    }

    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        HeadLinesData.ArticlesBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
            }
            pageData.setGood_amount(String.valueOf(count));
            mAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void requestFailed(String message) {
        mNewsEntertainSpringView.onFinishFreshAndLoad();

    }

    @Override
    public void bannerDetail(HeadLinesData resultBean) {
        mresultBean = resultBean;
        setHeadText();
        mBannerInfoBeanList.clear();
        mBannerInfoBeanList = resultBean.getBanners();
        newsList(resultBean);
        mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
        //滚动图
        new HeadTakeTurn(getActivity(), mBannerInfoBeanList, mBanner, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "头条轮播图点击事件");
                    Intent intent = new Intent(getActivity(), ActivityWebView1.class);
                    intent.putExtra("title", mBannerInfoBeanList.get(position).getTitle());
                    intent.putExtra("url", mBannerInfoBeanList.get(position).getArticle_url());
                    intent.putExtra("imageurl", mBannerInfoBeanList.get(position).getBanner_url());

                    intent.putExtra("id", mBannerInfoBeanList.get(position).getId() + "");
                    //浏览量
                    intent.putExtra("browse_amount", mBannerInfoBeanList.get(position).getBrowse_amount());
                    //点击量
                    intent.putExtra("good_amount", mBannerInfoBeanList.get(position).getGood_amount());
                    //收藏量
                    intent.putExtra("collect_amount", mBannerInfoBeanList.get(position).getCollect_amount());
                    //0为未点赞 1为已点赞
                    intent.putExtra("register_good", mBannerInfoBeanList.get(position).getRegister_good());
                    //0为未收藏 1为已收藏
                    intent.putExtra("register_collect", mBannerInfoBeanList.get(position).getRegister_collect());

                    intent.putExtra("isfrom_banaer", true);
                    //分享需要的参数
                    intent.putExtra("isFormType", "article");
                    Log.d("Debug", "点赞" + mBannerInfoBeanList.get(position).getRegister_good() + "---" + "收藏" + mBannerInfoBeanList.get(position).getRegister_collect());
                    intent.putExtra("type", "2");
                    startActivity(intent);
                }
            }
        });
    }

    private void setHeadText() {
        headiv1.setText(mresultBean.getCatagorys().get(0).getName());
        headiv2.setText(mresultBean.getCatagorys().get(1).getName());
        headiv3.setText(mresultBean.getCatagorys().get(2).getName());
    }

    @Override
    public void praise(int praisePos) {
        int Good_amount = 0;
        try {
            if (praisePos == -1) {
                return;
            }
            if (mNewsList.get(praisePos).getRegister_good() == 0) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount + 1;
                mNewsList.get(praisePos).setGood_amount(String.valueOf(Good_amount));
            } else if (mNewsList.get(praisePos).getRegister_good() == 1) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount - 1;
                mNewsList.get(praisePos).setGood_amount(String.valueOf(Good_amount));// 已点赞，则取消点赞
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        refreshPraiseCount(praisePos, Good_amount);
    }

    @Override
    public void Browse(int position) {
        int mBrowse = Integer.parseInt(mNewsList.get(position).getBrowse_amount());
        mBrowse = mBrowse + 1;
        mNewsList.get(position).setBrowse_amount(String.valueOf(mBrowse));
        mAdapter.notifyItemRangeChanged(position + 1, 1, "");//局部刷新
    }

    @Override
    public void search(HeadLinesData resultBean) {

    }


    @Override
    public void Collect(int position) {

    }

    @Override
    public void mineCount(CountBean resultBean) {

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

    public void onRefresh() {
        int position = GlobalApp.playPosition;
        if (position == -1) {
            return;
        }
        Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
        intent.putExtra("html", GlobalApp.html);     //页面点赞
        intent.putExtra("imageurl", GlobalApp.imageurl);   //图片地址
        intent.putExtra("audiourl", GlobalApp.audiourl);   //音频地址
        intent.putExtra("title", GlobalApp.title);          //标题
        intent.putExtra("register", GlobalApp.register);
        intent.putExtra("position", GlobalApp.playPosition);
        intent.putExtra("pageid", GlobalApp.pageid);
        intent.putExtra("id", GlobalApp.id);
        startActivity(intent);
    }
}

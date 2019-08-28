package shangri.example.com.shangri.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.ScaleInAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.ArticleAdapter;
import shangri.example.com.shangri.adapter.CultivateAdapter;
import shangri.example.com.shangri.adapter.NewsAdapter;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.eventmessage.NewsEvent;
import shangri.example.com.shangri.manage.WrapContentLinearLayoutManager;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.model.bean.response.LookBannerBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.presenter.ConsultationPresenter;
import shangri.example.com.shangri.presenter.view.ConsultationView;
import shangri.example.com.shangri.ui.activity.AudioPlayActivity;
import shangri.example.com.shangri.ui.activity.InterlocutionActivity;
import shangri.example.com.shangri.ui.activity.MoreArticlesActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.activity.TitleArticleActivity;
import shangri.example.com.shangri.ui.adapter.CultivateDialogAdapter;
import shangri.example.com.shangri.ui.adapter.TypeListDialogAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.ui.webview.ActivityWebView1;
import shangri.example.com.shangri.ui.webview.ShareWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.DpPxUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TakeTurn;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 培训Fragment
 * Created by chengaofu on 2017/6/22.
 */

public class ConsultationFragment extends BaseLazyFragment<ConsultationView, ConsultationPresenter> implements ConsultationView {

    @BindView(R.id.news_entertain_irv)
    RecyclerView mNewsEntertainRecycler;
    @BindView(R.id.news_entertain_springview)
    SpringView mNewsEntertainSpringView;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.float_toTop)
    ImageButton mFloatToTop;

    @BindView(R.id.reload)
    Button reload;
    Unbinder unbinder;
    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    @BindView(R.id.rv_cultivate)
    RecyclerView rvCultivate;


    private List<LookBannerBean.BannersBean> mBannerInfoBeanList = new ArrayList<>();


    private int currPage = 1;
    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数
    private int mPraisePos = 0; //点赞的位置
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private String type;
    private BannerHomeLookBean mresultBean;

    private ProgressDialogFragment mProgressDialog;


    private String id;
    int status = 1;
    //请求的页数
    private int onLoadMore = 1;
    //一共有几页数据
    private int total;
    //文章数据接收体以及适配器
    private NewsAdapter newsAdapter;
    List<NewsBean.ArticlesBean.DataBean> newsData = new ArrayList<>();
    //培新数据接收体以及适配器
    private List<TrainingArticleBean.TrainsBean> trains = new ArrayList<>();
    private ArticleAdapter articleAdapter;

    //dialog获取分类数据
    private List<TrainingArticleBean.TrainsBean> Dialogtrains = new ArrayList<>();
    private TypeListDialogAdapter DialogarticleAdapter;

    //导航分类
    private CultivateAdapter cultivateAdapter;
    List<CultivateBean.CatagoryBean> cultivateData = new ArrayList<>();
    //dialog 获取导航分类
    private CultivateDialogAdapter cultivateDialogAdapter;
    List<CultivateBean.CatagoryBean> cultivateDialogData = new ArrayList<>();
    //是否是dialog获取分类信息
    boolean isDialogRequest;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initSpringView();
        WrapContentLinearLayoutManager layoutManager =
                new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mNewsEntertainRecycler.setHasFixedSize(true);
        mNewsEntertainRecycler.setNestedScrollingEnabled(false);
        mNewsEntertainRecycler.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity(), R.layout.item_train, newsData);
        articleAdapter = new ArticleAdapter(getActivity(), R.layout.item_consultation_peixun, trains);
        mNewsEntertainRecycler.setAdapter(newsAdapter);

//        mPresenter.newInfo(1);
        mPresenter.catagory();
        mPresenter.lookBanner();
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

        newsAdapter.setOnItemClickListener(new com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (PointUtils.isFastClick()) {
                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    } else {
                        if (TextUtils.isEmpty(newsData.get(position).getAudio_url())) {
                            Intent intent = new Intent(getActivity(), ActivityWebView.class);
                            intent.putExtra("title", newsData.get(position).getTitle());
                            intent.putExtra("url", newsData.get(position).getArticle_url());
                            intent.putExtra("imageurl", newsData.get(position).getCover_url());
                            intent.putExtra("create_time", newsData.get(position).getPublish_time());
                            Log.d("Debug", "当前的图片地址是" + newsData.get(position).getCover_url());
                            intent.putExtra("id", newsData.get(position).getId());
                            //浏览量
                            intent.putExtra("browse_amount", newsData.get(position).getBrowse_amount());
                            //点击量
                            intent.putExtra("good_amount", newsData.get(position).getGood_amount());
                            //收藏量
                            intent.putExtra("collect_amount", newsData.get(position).getCollect_amount());
                            //0为未点赞 1为已点赞
                            intent.putExtra("register_good", newsData.get(position).getRegister_good());
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", newsData.get(position).getRegister_collect());
                            intent.putExtra("type", "2");
                            intent.putExtra("isMore", false);
                            //分享需要的参数
                            intent.putExtra("isFormType", "train");
                            intent.putExtra("position", position);
                            mPresenter.requestBrowse(newsData.get(position).getId(), position);
                            Log.d("Debug", "当前的url地址为" + newsData.get(position).getArticle_url());
                            startActivity(intent);
                            return;
                        } else {
                            Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
                            intent.putExtra("html", newsData.get(position).getArticle_url());     //页面点赞
                            intent.putExtra("imageurl", newsData.get(position).getCover_url());   //图片地址
                            Log.d("Debug", "音频地址" + newsData.get(position).getAudio_url());
                            intent.putExtra("audiourl", newsData.get(position).getAudio_url());   //音频地址
//                        intent.putExtra("audiourl", "https://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-04/5aec154d05973.mp3");
                            intent.putExtra("title", newsData.get(position).getTitle());          //标题
                            intent.putExtra("register", newsData.get(position).getRegister_good());
                            intent.putExtra("position", position);
                            intent.putExtra("pageid", "ConsultationFragment");
                            intent.putExtra("id", newsData.get(position).getId());
                            intent.putExtra("type", "2");
                            intent.putExtra("isMore", false);
                            Log.d("Debug", "穿过去的时间为" + newsData.get(position).getPublish_time());
                            intent.putExtra("create_time", newsData.get(position).getPublish_time() + "");
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", newsData.get(position).getRegister_collect());
                            startActivity(intent);
                            return;
                        }
                    }
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void jumpTo(String mType, int position) {

        if ("1".equals(mType)) {
//                  培训
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            id = cultivateData.get(position).getId();
            status = 2;
            type = "1";
            mPresenter.articleList(id);
        } else if ("2".equals(mType)) {
//   头条
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            status = 1;
            type = "2";
            mPresenter.newInfo(1);
        } else if ("3".equals(mType)) {
//   问答
            startActivity(new Intent(getActivity(), InterlocutionActivity.class));
        }
    }

    private void initSpringView() {
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                onLoadMore = 1;
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                if (status == 1) {
                    mPresenter.newInfo(1);
                    mNewsEntertainSpringView.onFinishFreshAndLoad();
                } else if (status == 2) {
                    mPresenter.articleList(id);
                    mNewsEntertainSpringView.onFinishFreshAndLoad();
                }

            }

            @Override
            public void onLoadmore() {
                onLoadMore++;
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                if (status == 1) {
                    mPresenter.newInfo(onLoadMore);
                    mNewsEntertainSpringView.onFinishFreshAndLoad();
                } else if (status == 2) {
                    mPresenter.articleList(id);
                    mNewsEntertainSpringView.onFinishFreshAndLoad();
                    ToastUtil.showShort("没有更多数据");
                }
//                ACTION_TYPE = ACTION_LOAD_MORE;
            }
        });


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
    protected ConsultationPresenter createPresenter() {
        return new ConsultationPresenter(getActivity(), this);
    }


    @Override
    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            Log.d("Debug", "到达没网络这里");
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            Log.d("Debug", "到达有网络这里");
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            currPage = 1;
//            mPresenter.bannerDetail(String.valueOf(currPage), type);
        }

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

        }
    }


    private void newsList(BannerHomeLookBean pageInfo) {
        mPageNum = pageInfo.getTotal_page();
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
//            mAdapter.setData(pageInfo.getTrains());
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
//            mAdapter.addAllAt(mNewsList.size(), pageInfo.getTrains());
        }
//        mNewsList = mAdapter.getAll();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(NewsEvent baseEvent) {


        if (baseEvent.advicesType == Constant.TYPE_HEAD_PRAISE) {
//            ToastUtil.showLong("消息过来了.....");//详情点赞后 资讯列表局部刷新
            int praisePos = baseEvent.position;
//                ToastUtil.showShort("消息过来了"+praisePos);
            NewsBean.ArticlesBean.DataBean pageData = newsData.get(praisePos);
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
                pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) + 1) + "");
                newsAdapter.notifyDataSetChanged();
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
                pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) - 1) + "");
                newsAdapter.notifyDataSetChanged();
            }

        } else if (baseEvent.advicesType == Constant.TYPE_HEAD_COllECT) { //资讯详情中收藏


            int praisePos = baseEvent.position;
            NewsBean.ArticlesBean.DataBean pageData = newsData.get(praisePos);
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
                pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) + 1) + "");
                newsAdapter.notifyDataSetChanged();
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
                pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) - 1) + "");
                newsAdapter.notifyDataSetChanged();
            }

        }
        if (baseEvent.advicesType == Constant.TYPE_PRAISE) {//   培训点赞 局部刷新

            int praisePos = baseEvent.position;
            TrainingArticleBean.TrainsBean.ListBean listBean = trains.get(praisePos).getList().get(praisePos);
            int register_good = trains.get(praisePos).getList().get(praisePos).getRegister_good();
            if (register_good == 0) {
                listBean.setRegister_good(1);
                String good_amount = listBean.getGood_amount();
                int valueOf = Integer.valueOf(good_amount);
                int value = valueOf + 1;

                listBean.setGood_amount(value + "");
                articleAdapter.notifyDataSetChanged();
            } else if (register_good == 1) {
                listBean.setRegister_good(0);
                String good_amount = listBean.getGood_amount();
                int valueOf = Integer.valueOf(good_amount);
                int value = valueOf - 1;

                listBean.setGood_amount(value + "");
                articleAdapter.notifyDataSetChanged();
            }


        } else if (baseEvent.advicesType == Constant.TYPE_COllECT) {

            int praisePos = baseEvent.position;

            TrainingArticleBean.TrainsBean.ListBean listBean = trains.get(praisePos).getList().get(praisePos);
            int register_collect = listBean.getRegister_collect();
            if (register_collect == 0) {
                listBean.setRegister_collect(1);
                String collect_amount = listBean.getCollect_amount();
                Integer valueOf = Integer.valueOf(collect_amount);
                int value = valueOf + 1;
                listBean.setCollect_amount(value + "");
                articleAdapter.notifyDataSetChanged();

            } else if (register_collect == 1) {
                listBean.setRegister_collect(0);
                String collect_amount = listBean.getCollect_amount();
                Integer valueOf = Integer.valueOf(collect_amount);
                int value = valueOf - 1;
                listBean.setCollect_amount(value + "");
                articleAdapter.notifyDataSetChanged();
            }
        }

    }


    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        NewsBean.ArticlesBean.DataBean pageData = newsData.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
            }
            pageData.setGood_amount(String.valueOf(count));
            newsAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
        }
    }

    private void refreshPeiCollect(int pos) { //收藏刷新
        NewsBean.ArticlesBean.DataBean pageData = newsData.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
            }
            newsAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
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
//        if(TextUtils.isEmpty(message)) return;
//        if(message.contains(String.valueOf(Constant.CODE_100027))){
//            ToastUtil.showShort("token 失效，需重新登录");
//            ActivityUtils.startActivity(getActivity(), LoginActivity.class);
//            getActivity().finish();
//        }
    }

    @Override
    public void bannerDetail(BannerHomeLookBean resultBean) {

    }


    @Override
    public void praise(int praisePos) {
        int Good_amount = 0;
        try {
            if (praisePos == -1) {
                return;
            }
            if (newsData.get(praisePos).getRegister_good() == 0) {
                Good_amount = Integer.valueOf(newsData.get(praisePos).getGood_amount());
                Good_amount = Good_amount + 1;
                newsData.get(praisePos).setGood_amount(String.valueOf(Good_amount));
            } else if (newsData.get(praisePos).getRegister_good() == 1) {
                Good_amount = Integer.valueOf(newsData.get(praisePos).getGood_amount());
                Good_amount = Good_amount - 1;
                newsData.get(praisePos).setGood_amount(String.valueOf(Good_amount));// 已点赞，则取消点赞
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        refreshPraiseCount(praisePos, Good_amount);
    }

    @Override
    public void Browse(int position) {

        if (position == -1) {
            return;
        }
        if (status == 1) {
            String browse_amount = newsData.get(position).getBrowse_amount();
            Integer value = Integer.valueOf(browse_amount);
            newsData.get(position).setBrowse_amount(value + "");
            newsAdapter.notifyItemRangeChanged(position + 1, 1, "");
        } else if (status == 2) {
            if (position > trains.get(position).getList().size()) {
                return;
            }
            String browse_amount2 = trains.get(position).getList().get(position).getBrowse_amount();
            Integer value = Integer.valueOf(browse_amount2);
            trains.get(position).getList().get(position).setBrowse_amount(value + "");
            articleAdapter.notifyItemRangeChanged(position + 1, 1, "");
        }


    }

    @Override
    public void search(HeadLinesData resultBean) {

    }

    @Override
    public void Collect(int praisePos) {

    }

    @Override
    public void PeixunSearch(BannerHomeLookBean resultBean) {

    }

    @Override
    public void newsSucceed(NewsBean newsBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        total = newsBean.getArticles().getTotal();
        if (onLoadMore <= total) {
            if (onLoadMore == 1) {
                newsData.clear();
            }
        } else if (onLoadMore > total) {
            mNewsEntertainSpringView.onFinishFreshAndLoad();
            ToastUtil.showShort("没有更多数据");
        }
        newsAdapter.addAll(newsBean.getArticles().getData());
        mNewsEntertainRecycler.setAdapter(newsAdapter);
        newsData = newsAdapter.getAll();
    }

    /**
     * 导航列表
     */
    @Override
    public void cultivateSucceed(CultivateBean cultivateBean) {
        if (cultivateData.size() > 0) {
            cultivateData.clear();
        }
        if (cultivateDialogData.size() > 0) {
            cultivateDialogData.clear();
        }

        Log.d("Debug", "没有遍历之前数据个数" + cultivateData.size());
        Log.d("Debug", "返回数据个数" + cultivateBean.getCatagory().size());
        for (int i = 0; i < cultivateBean.getCatagory().size(); i++) {
            CultivateBean.CatagoryBean catagoryBean = cultivateBean.getCatagory().get(i);
            if (i == 0) {
                catagoryBean.setClick(true);
            } else {
                catagoryBean.setClick(false);
            }
            cultivateData.add(catagoryBean);
            //dialog筛选   头条消息不能放在第一个    1 跳培训 2 跳头条 3 跳问答  现在去除 状态为2的   加载完洗面在循环加载 2的
            if (i == 0 && catagoryBean.getType().equals("2")) {

            } else {
                cultivateDialogData.add(catagoryBean);
            }
        }
        for (int i = 0; i < cultivateBean.getCatagory().size(); i++) {
            if (i == 0 && cultivateBean.getCatagory().get(i).getType().equals("2")) {
                cultivateDialogData.add(cultivateBean.getCatagory().get(i));
            }
        }


        rvCultivate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        if (cultivateData.size() > 4) {
            cultivateAdapter = new CultivateAdapter(getActivity(), true, R.layout.cultivate_item, cultivateData);
        } else {
            cultivateAdapter = new CultivateAdapter(getActivity(), false, R.layout.cultivate_item, cultivateData);
        }
        rvCultivate.setAdapter(cultivateAdapter);
        cultivateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < cultivateAdapter.getData().size(); i++) {
                    cultivateAdapter.getData().get(i).setClick(false);
                }
                cultivateAdapter.getData().get(position).setClick(true);
                cultivateAdapter.notifyDataSetChanged();
                String mType = cultivateData.get(position).getType();
                jumpTo(mType, position);
            }
        });
//        Log.d("Debug", "数据个数" + cultivateData.size());
//        cultivateAdapter.setNewData(cultivateData);
//        int size = cultivateData.size();
//        Log.d("Debug", "..." + size);
//        cultivateAdapter.notifyDataSetChanged();
        id = cultivateBean.getCatagory().get(0).getId();
//        1 跳培训 2 跳头条 3 跳问答
        if (Integer.parseInt(cultivateBean.getCatagory().get(0).getType()) == 2) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            status = 1;
            type = "2";
            mPresenter.newInfo(onLoadMore);
        } else if (Integer.parseInt(cultivateBean.getCatagory().get(0).getType()) == 1) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            status = 2;
            type = "1";
            mPresenter.articleList(id);
        }
    }

    //   轮播数据
    @Override
    public void lookBannerSucceed(LookBannerBean resultBean) {
        mBannerInfoBeanList.clear();
        mBannerInfoBeanList = resultBean.getBanners();
        Log.d("Debug", "返回的培训数量量为" + mBannerInfoBeanList.size());
        mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
        //滚动图
        if (mBanner != null) {

            new TakeTurn(getContext(), mBannerInfoBeanList, mBanner, new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (PointUtils.isFastClick()) {
                        if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                            ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                            return;
                        }
//                        article_url和relation_url一样的  取哪个参数判断都可以的  article_url大于0 做跳转操作   小于零  不做什么跳转
                        if (mBannerInfoBeanList.get(position).getArticle_url().length() > 0) {
//                            http://testh5.ilivehome.net/single/lottery/index.html 大转盘的url
                            if (mBannerInfoBeanList.get(position).getArticle_url().contains("single/lottery")) {
                                startActivity(new Intent(getActivity(), ShareWebView.class).putExtra("url", mBannerInfoBeanList.get(position).getArticle_url() + ""));
                            } else {
                                if (TextUtils.isEmpty(mBannerInfoBeanList.get(position).getAudio_url())) {
                                    Intent intent = new Intent(getActivity(), ActivityWebView1.class);
                                    intent.putExtra("title", mBannerInfoBeanList.get(position).getTitle());
                                    intent.putExtra("imageurl", mBannerInfoBeanList.get(position).getBanner_url());
                                    intent.putExtra("url", mBannerInfoBeanList.get(position).getArticle_url());
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
                                    intent.putExtra("type", "1");
                                    intent.putExtra("isfrom_banaer", true);
                                    //分享需要的参数
                                    intent.putExtra("isFormType", "train");
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
//                    intent.putExtra("bean", (Serializable) bean);
                                    intent.putExtra("html", mBannerInfoBeanList.get(position).getArticle_url());     //页面点赞
                                    intent.putExtra("imageurl", mBannerInfoBeanList.get(position).getBanner_url());   //图片地址
                                    intent.putExtra("audiourl", mBannerInfoBeanList.get(position).getRelation_url());   //音频地址
                                    intent.putExtra("title", mBannerInfoBeanList.get(position).getTitle());          //标题
                                    intent.putExtra("register", mBannerInfoBeanList.get(position).getRegister_good());
                                    intent.putExtra("position", position - 1);
                                    intent.putExtra("pageid", "bannerDetail");
                                    intent.putExtra("id", mBannerInfoBeanList.get(position).getId());
                                    intent.putExtra("type", "1");
                                    intent.putExtra("isfrom_banaer", true);
                                    //分享需要的参数
                                    intent.putExtra("isFormType", "train");
                                    //0为未点赞 1为已点赞
                                    intent.putExtra("register_good", mBannerInfoBeanList.get(position).getRegister_good());
                                    //0为未收藏 1为已收藏
                                    intent.putExtra("register_collect", mBannerInfoBeanList.get(position).getRegister_collect());
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            });
        }

    }

    @Override
    public void trainingArticleSucceed(TrainingArticleBean trainingArticleBean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (isDialogRequest) {
            isDialogRequest = false;
            if (Dialogtrains.size() > 0) {
                Dialogtrains.clear();
            }
            Dialogtrains = trainingArticleBean.getTrains();
            DialogarticleAdapter.setData(Dialogtrains);
            DialogarticleAdapter.notifyDataSetChanged();
        } else {
            mNewsEntertainSpringView.onFinishFreshAndLoad();
            trains.clear();
            articleAdapter.notifyItemRangeRemoved(0, trains.size());
            trains = trainingArticleBean.getTrains();
//trains
            articleAdapter.openLoadAnimation(new ScaleInAnimation());
            articleAdapter.setNewData(trains);
            mNewsEntertainRecycler.setAdapter(articleAdapter);
            articleAdapter.notifyItemRangeChanged(0, trains.size());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
//        loadData();
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

//    public void onRefresh() {
//        int position = GlobalApp.playPosition;
//        if (position == -1) {
//            return;
//        }
//        Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
//        intent.putExtra("html", GlobalApp.html);     //页面点赞
//        intent.putExtra("imageurl", GlobalApp.imageurl);   //图片地址
//        intent.putExtra("audiourl", GlobalApp.audiourl);   //音频地址
//        intent.putExtra("title", GlobalApp.title);          //标题
//        intent.putExtra("register", GlobalApp.register);
//        intent.putExtra("position", GlobalApp.playPosition);
//        intent.putExtra("pageid", GlobalApp.pageid);
//        intent.putExtra("id", GlobalApp.id);
//        intent.putExtra("type", "1");
//        startActivity(intent);
//    }

    //    主播升级弹窗
    AlertDialog dialog4;

    /**
     * 弹出类型框
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showDialog() {
        Log.d("Debug", "弹出类型框");
        dialog4 = WelfareDialog4(getActivity(), "", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog4.dismiss();
            }
        });
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

    /**
     * 筛选分类弹窗
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AlertDialog WelfareDialog4(Context context, String signMoney, View.OnClickListener commitListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_showmessagetype_dialog, null);
        RecyclerView rv_type_symbol = view.findViewById(R.id.rv_type_symbol);
        RecyclerView rv_type_content = view.findViewById(R.id.rv_type_content);
        ImageView im_close = view.findViewById(R.id.im_close);
        LinearLayout ll_info = view.findViewById(R.id.ll_info);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        final int height = wm.getDefaultDisplay().getHeight();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll_info.getLayoutParams();
//获取当前控件的布局对象
        params.height = height - DpPxUtils.dip2px(getActivity(), 100);//设置当前控件布局的高度
        ll_info.setLayoutParams(params);

        final TextView tv_type_name = view.findViewById(R.id.tv_type_name);
        im_close.setOnClickListener(commitListener);
        //左面布局 导航列表需要的操作
        rv_type_symbol.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cultivateDialogAdapter = new CultivateDialogAdapter(getActivity(), R.layout.cultivate_dailog_item, cultivateDialogData);
        rv_type_symbol.setAdapter(cultivateDialogAdapter);
        for (int i = 0; i < cultivateDialogAdapter.getAll().size(); i++) {
            cultivateDialogAdapter.getAll().get(i).setClick(false);
        }
        //设置当前item为点击
        cultivateDialogAdapter.getAll().get(0).setClick(true);
        cultivateDialogAdapter.notifyDataSetChanged();
        tv_type_name.setText(cultivateDialogAdapter.getAll().get(0).getName() + "");
        //右面分类布局
        rv_type_content.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        DialogarticleAdapter = new TypeListDialogAdapter(getActivity(), R.layout.typelist_dailog_item, Dialogtrains);
        rv_type_content.setAdapter(DialogarticleAdapter);
        isDialogRequest = true;
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());
        mPresenter.articleList(cultivateDialogAdapter.getAll().get(0).getId());


        DialogarticleAdapter.setOnItemClickListener(new com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                TrainingArticleBean.TrainsBean trainsBean = DialogarticleAdapter.getAll().get(position);
                startActivity(new Intent(getActivity(), MoreArticlesActivity.class)
                        .putExtra("catagory_id", trainsBean.getId())
                        .putExtra("title", trainsBean.getName())
                        .putExtra("type", "2")
                        .putExtra("img_url", trainsBean.getImg_url()));
                dialog4.dismiss();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        cultivateDialogAdapter.setOnItemClickListener(new com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击事件");
                //先将所有设置为未点击(清除之前点击数据)
                for (int i = 0; i < cultivateDialogAdapter.getAll().size(); i++) {
                    cultivateDialogAdapter.getAll().get(i).setClick(false);
                }
                //设置当前item为点击
                cultivateDialogAdapter.getAll().get(position).setClick(true);
                cultivateDialogAdapter.notifyDataSetChanged();
                tv_type_name.setText(cultivateDialogAdapter.getAll().get(position).getName() + "");
//                1 跳培训 2 跳头条 3 跳问答
                switch (cultivateDialogAdapter.getAll().get(position).getType()) {
                    //更新弹窗右面列表数据
                    case "1":
                        isDialogRequest = true;
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.articleList(cultivateDialogAdapter.getAll().get(position).getId());
                        break;
                    //跳转到行业热点列表
                    case "2":
                        Intent intent = new Intent(getActivity(), TitleArticleActivity.class);
                        intent.putExtra("name", cultivateDialogAdapter.getAll().get(position).getName());
                        startActivity(intent);
                        dialog4.dismiss();
                        break;
                    case "3":
                        //跳转到问答列表
                        startActivity(new Intent(getActivity(), InterlocutionActivity.class));
                        dialog4.dismiss();
                        break;
                }

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -2);
        //显示在底部  （布局 much_parent 布局居中显示也可以的）
        window.setGravity(Gravity.BOTTOM);
        //设置动画
        window.setWindowAnimations(R.style.dialog_anim);

        window.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        // 方法二：
        dialog.setCancelable(true);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }


}

package shangri.example.com.shangri.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.presenter.EncyclopediaPresenter;
import shangri.example.com.shangri.presenter.view.EncyclopediaView;
import shangri.example.com.shangri.ui.activity.InterlocutionActivity;
import shangri.example.com.shangri.ui.adapter.PlatFromAdapter;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TakeTurn2;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 百科Fragment
 * Created by chengaofu on 2017/6/22.
 */

public class EncyclopediaFragment extends BaseLazyFragment<EncyclopediaView, EncyclopediaPresenter> implements EncyclopediaView, View.OnClickListener {

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

    //列表
    private List<EncyclopediaHomeBean.PlatfromBean> mNewsList = new ArrayList<>();
    private PlatFromAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 15;
    private int mPraisePos = 0; //点赞的位置
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private String type = "1";
    private EncyclopediaHomeBean mresultBean;
    private TextView headiv1, headiv2, headiv3, headiv4;

    @Override
    protected void initView() {
        initSpringView();
        mNewsList.clear();
        mNewsEntertainRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAdapter = new PlatFromAdapter(getActivity(), R.layout.item_consultation, mNewsList);
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
    }

    private void initSpringView() {
        mNewsEntertainSpringView.setGive(SpringView.Give.TOP);
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    loadData();
                }
            }

            @Override
            public void onLoadmore() {
            }
        });

    }

    private void setHeaderPage() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.widget_encyclopedia_header, null);
        mBanner = header.findViewById(R.id.convenientBanner);
        headiv1 = header.findViewById(R.id.iv1);
        headiv2 = header.findViewById(R.id.iv2);
        headiv3 = header.findViewById(R.id.iv3);
        headiv4 = header.findViewById(R.id.iv4);
        headiv1.setOnClickListener(this);
        headiv2.setOnClickListener(this);
        headiv3.setOnClickListener(this);
        headiv4.setOnClickListener(this);
        mAdapter.setHeaderView(header);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_encyclopedia;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_encyclopedia;
    }

    @Override
    protected EncyclopediaPresenter createPresenter() {
        return new EncyclopediaPresenter(getActivity(), this);
    }


    @Override
    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }

        mNewsList.clear();
        currPage = 1;
        mPresenter.bannerDetail();
    }

    @OnClick({R.id.reload, R.id.float_toTop})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.float_toTop:
                mNewsEntertainRecycler.smoothScrollToPosition(0);
                break;
                //找平台
            case R.id.iv1:
                setTextColor();
                headiv1.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                break;
                //找工会
            case R.id.iv2:
                setTextColor();
                headiv2.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                break;
                //找主播
            case R.id.iv3:
                setTextColor();
                headiv3.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                break;
                //我要入驻
            case R.id.iv4:
                setTextColor();
                headiv4.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_little_orange));
                ActivityUtils.startActivity(getActivity(), InterlocutionActivity.class);
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

        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            mPresenter.bannerDetail();
        } else {
            ToastUtil.showShort("已加载全部");
            mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    private void newsList(EncyclopediaHomeBean pageInfo) {
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
            mAdapter.setData(pageInfo.getPlatfrom());
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAllAt(mNewsList.size(), pageInfo.getPlatfrom());
        }
        mNewsList = mAdapter.getAll();
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
    }

    @Override
    public void requestFailed(String message) {
        mNewsEntertainSpringView.onFinishFreshAndLoad();
    }

    
    @Override
    public void onResume() {
        super.onResume();
        loadData();
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

    }

    @Override
    public void bannerDetail(EncyclopediaHomeBean resultBean) {

        newsList(resultBean);
        List<EncyclopediaHomeBean.BannersBean> mBannerInfoBeanList = resultBean.getBanners();
        mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
//        //滚动图
        new TakeTurn2(getActivity(), mBannerInfoBeanList, mBanner, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("Debug","轮播图点击事件");
            }
        });
    }
}

package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.presenter.EncyclopediaPresenter;
import shangri.example.com.shangri.presenter.view.EncyclopediaView;
import shangri.example.com.shangri.ui.activity.BkplatfromActivity;
import shangri.example.com.shangri.ui.activity.JoinEncyclopediaActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.adapter.PingTaiAdapter;
import shangri.example.com.shangri.ui.adapter.PingTaiAdapterTwo;
import shangri.example.com.shangri.ui.adapter.PlatFromAdapter;
import shangri.example.com.shangri.ui.adapter.ZhuBoAdapter;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TakeTurn2;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 百科Fragment
 * Created by chengaofu on 2017/6/22.
 */

public class NewEncyclopediaFragment extends BaseLazyFragment<EncyclopediaView, EncyclopediaPresenter> implements EncyclopediaView {
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;

    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.iv1)
    TextView iv1;
    @BindView(R.id.iv2)
    TextView iv2;
    @BindView(R.id.iv3)
    TextView iv3;
    @BindView(R.id.iv4)
    TextView iv4;
    @BindView(R.id.im_1)
    ImageView im1;
    @BindView(R.id.news_entertain_irv)
    RecyclerView newsEntertainIrv;
    @BindView(R.id.news_entertain_springview)
    SpringView mNewsEntertainSpringView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.news_entertain_irv_gonghui_one)
    RecyclerView news_entertain_irv_gonghui_one;
    @BindView(R.id.news_entertain_irv_gonghui_two)
    RecyclerView news_entertain_irv_gonghui_two;
    @BindView(R.id.news_entertain_irv_zhubo)
    RecyclerView news_entertain_irv_zhubo;
//    @BindView(R.id.news_entertain_irv_text)
//    RecyclerView news_entertain_irv_text;


    private List<EncyclopediaHomeBean.BannersBean> mBannerInfoBeanList = new ArrayList<>();

    //列表
    private List<EncyclopediaHomeBean.PlatfromBean> mNewsList = new ArrayList<>();
    private List<EncyclopediaHomeBean.GuildBean> mNewsListOne = new ArrayList<>();
    private List<EncyclopediaHomeBean.GuildBean> mNewsListneTwo = new ArrayList<>();
    private List<EncyclopediaHomeBean.AnchorBean> mNewsListneThree = new ArrayList<>();
    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    //直播平台适配器
    private PlatFromAdapter mAdapter;
    //公会上面一行
    private PingTaiAdapter GonghuimAdapterOne;
    private PingTaiAdapterTwo GonghuimAdapterTwo;
    private ZhuBoAdapter ZhuBomAdapter;

    @Override
    protected void initView() {
        initSpringView();
        loadData();
        newsEntertainIrv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        news_entertain_irv_gonghui_one.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        news_entertain_irv_gonghui_two.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        news_entertain_irv_gonghui_two.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        news_entertain_irv_zhubo.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        mAdapter = new PlatFromAdapter(getActivity(), R.layout.item_platfrom, mNewsList);
        GonghuimAdapterOne = new PingTaiAdapter(getActivity(), R.layout.item_gongghui, mNewsListOne);
        GonghuimAdapterTwo = new PingTaiAdapterTwo(getActivity(), R.layout.item_gongghui, mNewsListneTwo);
        ZhuBomAdapter = new ZhuBoAdapter(getActivity(), R.layout.item_zhubo, mNewsListneThree);


        mAdapter.openLoadAnimation(new ScaleInAnimation());
        GonghuimAdapterOne.openLoadAnimation(new ScaleInAnimation());
        ZhuBomAdapter.openLoadAnimation(new ScaleInAnimation());
        GonghuimAdapterTwo.openLoadAnimation(new ScaleInAnimation());

        newsEntertainIrv.setAdapter(mAdapter);
        news_entertain_irv_gonghui_one.setAdapter(GonghuimAdapterOne);
        news_entertain_irv_gonghui_two.setAdapter(GonghuimAdapterTwo);
        news_entertain_irv_zhubo.setAdapter(ZhuBomAdapter);

    }

    private void initSpringView() {
        mNewsEntertainSpringView.setGive(SpringView.Give.TOP);
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
            }
        });

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
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            return;
        } else {
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }
        mNewsList.clear();
        mNewsListOne.clear();
        mNewsListneTwo.clear();
        mNewsListneThree.clear();
        int currPage = 1;
        mPresenter.bannerDetail();
    }


    private void setTextColor() {
        iv1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        iv2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        iv3.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        iv4.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
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
//        loadData();
        //开始自动翻页
        if (convenientBanner != null) {
            convenientBanner.startTurning(3000);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (convenientBanner != null) {
            convenientBanner.stopTurning();
        }
    }

    public void onRefresh() {
        int position = GlobalApp.playPosition;
    }

    @Override
    public void bannerDetail(EncyclopediaHomeBean resultBean) {
        Log.d("Debug", "到达这里返回的数据是" + resultBean);

        newsList(resultBean);
        mBannerInfoBeanList = resultBean.getBanners();
        mNewsEntertainSpringView.onFinishFreshAndLoad();


            new TakeTurn2(getActivity(), mBannerInfoBeanList, convenientBanner, new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Log.d("Debug", "轮播图点击事件");
                    if (PointUtils.isFastClick()) {

                        if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                            ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                        }else {
                            Intent intent = new Intent(getActivity(), EncyclopediaActivityWebView.class);
                            intent.putExtra("type", 1);
                            intent.putExtra("id", mBannerInfoBeanList.get(position).getId());
                            intent.putExtra("url", mBannerInfoBeanList.get(position).getRelation_url());
                            intent.putExtra("is_collect", mBannerInfoBeanList.get(position).getBanners_collect());
                            intent.putExtra("title", mBannerInfoBeanList.get(position).getTitle());
                            intent.putExtra("isfrom_banaer", true);
                            intent.putExtra("imageurl", mBannerInfoBeanList.get(position).getBanner_url());
                            startActivity(intent);
                        }

                    }
                }
            });

//        //滚动图

    }

    private void newsList(EncyclopediaHomeBean pageInfo) {
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        //分割公会信息  上面一部分  下面一部分
        if (pageInfo.getGuild().size() <= 3) {
            news_entertain_irv_gonghui_two.setVisibility(View.GONE);
        } else {
            news_entertain_irv_gonghui_two.setVisibility(View.VISIBLE);
        }
        Log.d("Debug", "数据的个数为" + pageInfo.getGuild().size());
        mNewsListOne.clear();
        mNewsListneTwo.clear();
        mNewsListneThree.clear();
        //加下角标  判断前三名
        for (int i = 0; i < pageInfo.getAnchor().size(); i++) {
            pageInfo.getAnchor().get(i).setNumber(i);
            mNewsListneThree.add(pageInfo.getAnchor().get(i));
        }
        for (int i = 0; i < 3; i++) {
            mNewsListOne.add(pageInfo.getGuild().get(i));
        }
        for (int i = 3; i < pageInfo.getGuild().size(); i++) {
            mNewsListneTwo.add(pageInfo.getGuild().get(i));
        }
        Log.d("Debug", "mNewsListOne的长度为" + mNewsListOne.size() + "mNewsListneTwo的长度为" + mNewsListneTwo.size());
        mAdapter.setData(pageInfo.getPlatfrom());
        GonghuimAdapterOne.setData(mNewsListOne);
        GonghuimAdapterTwo.setData(mNewsListneTwo);
        ZhuBomAdapter.setData(mNewsListneThree);
        mNewsList = mAdapter.getAll();
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
    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.ll_plat_more, R.id.reload, R.id.ll_guild_more, R.id.ll_zhubo_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_plat_more:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 1));
                    }

                }
                break;
            case R.id.ll_guild_more:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 2));
                    }

                }
                break;
            case R.id.ll_zhubo_more:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 3));
                    }

                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        loadData();
                    }

                }
                break;
            //找平台
            case R.id.iv1:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        setTextColor();
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 1));
                    }

                }
                break;
            //找工会
            case R.id.iv2:
                if (PointUtils.isFastClick()) {
                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        setTextColor();
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 2));
                    }

                }
                break;
            //找主播
            case R.id.iv3:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        setTextColor();
                        startActivity(new Intent(getActivity(), BkplatfromActivity.class).putExtra("type", 3));
                    }

                }
                break;
            //我要入驻
            case R.id.iv4:
                if (PointUtils.isFastClick()) {

                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(getActivity(), NewLoginUserActivity.class);
                    }else {
                        setTextColor();
                        ActivityUtils.startActivity(getActivity(), JoinEncyclopediaActivity.class);
                    }

                }
                break;
        }
    }
}

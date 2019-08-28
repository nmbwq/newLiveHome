package shangri.example.com.shangri.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.response.NewsTypeInfoBean;
import shangri.example.com.shangri.presenter.NewsTypePresenter;
import shangri.example.com.shangri.presenter.view.NewsTypeView;
import shangri.example.com.shangri.ui.activity.PeixunSearchActivity;
import shangri.example.com.shangri.ui.activity.ToutiaoSearchActivity;
import shangri.example.com.shangri.ui.view.FrequencyView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TableUtils;


/**
 * 资讯基类fragment
 * Created by chengaofu on 2017/6/21.
 */

public class NewsFragment extends BaseLazyFragment<NewsTypeView, NewsTypePresenter> implements NewsTypeView {

    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.news_layout)
    LinearLayout mNewsLayout;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.tabs)
    TabLayout tabs;


    @BindView(R.id.iv_stade)
    ImageView iv_stade;
    @BindView(R.id.right_image)
    ImageView right_image;


    private BaseFragmentAdapter fragmentAdapter;
    private ConsultationFragment fragment;
    private int onPageSelected;

    //    private ProgressDialogFragment mProgressDialog;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected NewsTypePresenter createPresenter() {
        return new NewsTypePresenter(getActivity(), this);
    }

    @Override
    protected void initView() {
        onRefresh();
    }

    @Override
    protected void loadData() {
        createPager();
    }


    private void createPager() {
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        channelNames.add(getResources().getString(R.string.tab_news_hot));
        channelNames.add("百科");
//       channelNames.add(getResources().getString(R.string.tab_news_headlines));
        mNewsFragmentList.add(createListFragments());
        mNewsFragmentList.add(createEncyclopediaFragments());
//        mNewsFragmentList.add(createHeadlinesFragments());

        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }
        mViewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(mViewPager);
        TableUtils.dynamicSetTabLayoutMode(tabs);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    right_image.setVisibility(View.GONE);
                    iv_stade.setVisibility(View.GONE);
                } else {
                    right_image.setVisibility(View.VISIBLE);
                    iv_stade.setVisibility(View.VISIBLE);
                }
                Log.d("Debug", "position的位置为" + position);
                onPageSelected = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Fragment createHeadlinesFragments() {
        HeadlinesFragment fragment1 = new HeadlinesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment1.setArguments(bundle);
        return fragment1;
    }

    private Fragment createListFragments() {
        fragment = new ConsultationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment.setArguments(bundle);
        return fragment;
    }

    private Fragment createEncyclopediaFragments() {
        NewEncyclopediaFragment fragment2 = new NewEncyclopediaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment2.setArguments(bundle);
        return fragment2;
    }


    @Override
    public void getType(List<NewsTypeInfoBean> newsTypeInfoBeanList) {

        for (NewsTypeInfoBean bean : newsTypeInfoBeanList) {
            switch (bean.getId()) {

            }
        }
    }

    @OnClick({ R.id.right_image,
            R.id.reload, R.id.iv_stade})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.right_image:
                if (PointUtils.isFastClick()) {
                    if (onPageSelected == 0) {
                        ActivityUtils.startActivity(getActivity(), PeixunSearchActivity.class);
                    } else {
                        ActivityUtils.startActivity(getActivity(), ToutiaoSearchActivity.class);
                    }
                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    loadData();
                }
                break;
            case R.id.iv_stade:

                if (PointUtils.isFastClick()) {
                    fragment.showDialog();
                }

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        tabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabs, 20, 20);
            }
        });
    }


    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) {
        }
    }


    public void onRefresh() {

    }

    private void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}

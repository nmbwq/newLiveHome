package shangri.example.com.shangri.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.response.NewsTypeInfoBean;
import shangri.example.com.shangri.presenter.NewsTypePresenter;
import shangri.example.com.shangri.presenter.view.NewsTypeView;
import shangri.example.com.shangri.util.TableUtils;


/**
 * 资讯基类fragment
 * Created by chengaofu on 2017/6/21.
 */

public class SumBossCollectFragment extends BaseLazyFragment<NewsTypeView, NewsTypePresenter> implements NewsTypeView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.news_viewpager)
    ViewPager newsViewpager;
    @BindView(R.id.news_layout)
    LinearLayout newsLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;

    //    @BindView(R.id.reload)
//    Button reload;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;


    private BaseFragmentAdapter fragmentAdapter;
    private int onPageSelected;

    //    private ProgressDialogFragment mProgressDialog;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.my_collect_freagment;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.my_collect_freagment;
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
        channelNames.add("已收藏");
        channelNames.add("已沟通");
        tv_title.setText("职位收藏");
//        mNewsFragmentList.add(new CollectNewBossFragment());
        mNewsFragmentList.add(new AnchorCollectNewBossFragment());
        mNewsFragmentList.add(new GouTongNewBossFragment());
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }

        newsViewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(newsViewpager);
        TableUtils.dynamicSetTabLayoutMode(tabs);

        newsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPageSelected = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void getType(List<NewsTypeInfoBean> newsTypeInfoBeanList) {

    }


    @Override
    public void onStart() {
        super.onStart();
        tabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabs, 10, 10);
            }
        });
    }


    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) {
        }

    }


    private void onRefresh() {

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
}

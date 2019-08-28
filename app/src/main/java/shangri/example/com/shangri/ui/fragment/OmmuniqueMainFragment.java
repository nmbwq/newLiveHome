package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.presenter.OmmuniqueMainPresenter;
import shangri.example.com.shangri.presenter.view.OmmuniqueMainView;
import shangri.example.com.shangri.ui.activity.NewMessageActivity;
import shangri.example.com.shangri.ui.popupwindow.CommonPopupWindow;
import shangri.example.com.shangri.ui.view.smart_tab.SmartTabLayout;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TableUtils;

/**
 * 管理
 * Created by admin on 2017/12/22.
 */

public class OmmuniqueMainFragment extends BaseLazyFragment<OmmuniqueMainView, OmmuniqueMainPresenter> implements OmmuniqueMainView {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.news_layout)
    LinearLayout mNewsLayout;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.right_image)
    ImageView right_image;

    @BindView(R.id.rl1)
    RelativeLayout rl1;


    private BaseFragmentAdapter fragmentAdapter;
    private SmartTabLayout mSmartTabLayout;
    private FragmentPagerItems mFragmentPagerItems;
    private CommonPopupWindow window;
    private CommonPopupWindow.LayoutGravity layoutGravity;

    @Override
    protected OmmuniqueMainPresenter createPresenter() {
        return new OmmuniqueMainPresenter(getActivity(), this);
    }

    @Override
    protected void initView() {
        createPager();
    }


    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Debug", "到達OmmuniqueMainFragment的onResume裏面");
        mPresenter.mineCount();
    }


    @OnClick({R.id.right_image, R.id.reload})
    public void onClick(View v) {
        switch (v.getId()) {
            //发布巡查
            case R.id.right_image:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), NewMessageActivity.class));
                }
                break;
            case R.id.reload:
//                loadData();
                break;
        }
    }

    private void createPager() {
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        if (UserConfig.getInstance().getRole().equals("1") || UserConfig.getInstance().getRole().equals("3")) {
            channelNames.add(getResources().getString(R.string.tab_partol));
            right_image.setVisibility(View.VISIBLE);
        } else {
            channelNames.add(getResources().getString(R.string.tab_anchor));
            right_image.setVisibility(View.VISIBLE);
        }
        channelNames.add(getResources().getString(R.string.tab_task));
        mNewsFragmentList.add(createListFragments());
        mNewsFragmentList.add(createTaskFragment());
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }
        mViewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(mViewPager);

        TableUtils.dynamicSetTabLayoutMode(tabs);
    }

    private Fragment createListFragments() {
        PatrolFragment fragment = new PatrolFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment.setArguments(bundle);
        return fragment;
    }

    private Fragment createTaskFragment() {
        NewTaskFragment mTaskFragment = new NewTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        mTaskFragment.setArguments(bundle);
        return mTaskFragment;
    }

    @Override
    public void mvpExpleme() {

    }

    @Override
    public void mineCount(CountBean resultBean) {
        int company = resultBean.getAnchors() + resultBean.getAdmins();
        int messages = resultBean.getMessages();
        Log.d("Debug", "返回的company" + company + "messages" + messages);

        if (messages > 0) {
            right_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiaoxi_qipao));
        } else {
            right_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.comments));
        }
        if (company + messages > 0) {
            EventBus.getDefault().post(new FourshowEventBean(true));
        } else {
            EventBus.getDefault().post(new FourshowEventBean(false));
        }

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_ommunique_main;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_ommunique_main;
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
    public void onStart() {
        super.onStart();
        tabs.post(new Runnable() {
            @Override
            public void run() {
                if (UserConfig.getInstance().getRole().equals("1")) {
                    setIndicator(tabs, 40, 40);
                } else {
                    right_image.setVisibility(View.VISIBLE);
                    setIndicator(tabs, 20, 20);
                }

            }
        });
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

        } else {  // 在最前端显示 相当于调用了onResume();
            mPresenter.mineCount();
            createPager();
            Log.d("Debug", "在最前端显示 管理界面相当于调用了onResume()");
        }
    }

}

package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.activity.BindingAnchorGuildeTypectivity;
import shangri.example.com.shangri.ui.fragment.CollectFragment;
import shangri.example.com.shangri.ui.fragment.FocusFragment;
import shangri.example.com.shangri.ui.fragment.MyAnchorBindingFreagment;
import shangri.example.com.shangri.ui.fragment.MyGuildFreagment;
import shangri.example.com.shangri.ui.view.CustomScrollViewPager;
import shangri.example.com.shangri.util.TableUtils;

public class AnchorNewMyGuildActivityChangeFreagment extends BaseFragment<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.setting_back)
    ImageView setting_back;
    @BindView(R.id.viewpager)
    CustomScrollViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_collectandfocus_layout2;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_collectandfocus_layout2;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(getActivity(), this);
    }


    @Override
    protected void initViewsAndEvents() {
        initFragment();
    }

    private void initFragment() {
        List<String> channelNames = new ArrayList<>();
        channelNames.add("公会绑定");
        channelNames.add("快速绑定");
        fragments.add(new MyGuildFreagment());
        fragments.add(new MyAnchorBindingFreagment());
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), fragments, channelNames);
        }
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
        TableUtils.dynamicSetTabLayoutMode(tabs);
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
        initFragment();
    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void guildRatio() {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }

    @Override
    public void requestFailed(String message) {

    }



    @OnClick({R.id.setting_back, R.id.iv_add_guild})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
//                finish();
                break;
            case R.id.iv_add_guild:
                Intent intent = new Intent(getActivity(), BindingAnchorGuildeTypectivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;


        }
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



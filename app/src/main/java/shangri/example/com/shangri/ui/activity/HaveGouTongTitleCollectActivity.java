package shangri.example.com.shangri.ui.activity;

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
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.fragment.SendResumeFragment;
import shangri.example.com.shangri.util.TableUtils;


/**
 * 已沟通
 * Created by admin on 2017/12/22.
 */

public class HaveGouTongTitleCollectActivity extends BaseActivity<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.tl_link_up)
    TabLayout tlLinkUp;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    //    @BindView(R.id.setting_back)
//    ImageView settingBack;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.my_collect_activity12;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.my_collect_activity12;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mFragmentList = new ArrayList<>();
//        channelNames.add("发简历");
        channelNames.add("拨电话");
        channelNames.add("留电话");
//        1发简历 2留电话 3拨打电话 默认1
//        mFragmentList.add( SendResumeFragment.getInstance(1));
        mFragmentList.add( SendResumeFragment.getInstance(3));
        mFragmentList.add( SendResumeFragment.getInstance(2));

        
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), mFragmentList, channelNames);
        }
        vp.setAdapter(fragmentAdapter);
        tlLinkUp.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(1);
    }


    @Override
    protected void onStart() {
        super.onStart();

        tlLinkUp.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tlLinkUp, 30, 30);
            }
        });

    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {
        finish();
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

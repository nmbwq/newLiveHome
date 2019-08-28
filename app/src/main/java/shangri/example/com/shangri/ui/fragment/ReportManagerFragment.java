package shangri.example.com.shangri.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.TabEntity;

/**
 * 底导5的子类
 */
public class ReportManagerFragment extends BaseFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @BindView(R.id.tl)
    CommonTabLayout mTl;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] mTitles = {"昨日", "本月"};
    private String[] mType = {"yesterday", "month"};
    private int[] mIconUnselectIds = {
            R.mipmap.zuori1, R.mipmap.benyue};
    private int[] mIconSelectIds = {
            R.mipmap.zuori, R.mipmap.benyue1};
    private int mShowType =1;//0团队收益，1团队管理
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private static final String TAG = "ReportManagerFragment";
    public static ReportManagerFragment getInstance(String type, int show_type) {
        ReportManagerFragment sf = new ReportManagerFragment();
        sf.mShowType = show_type;
        return sf;
    }
    public ReportManagerFragment() {
    }
    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_report_child;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_report_child;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void initViewsAndEvents() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        for (String type : mType) {
            mFragments.add(ReportChildManagerFragment.getInstance(type,mShowType));
        }
        mTl.setTabData(mTabEntities);
        vp.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

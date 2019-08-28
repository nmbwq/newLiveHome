package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.RefushBean;
import shangri.example.com.shangri.model.bean.response.CardRequestBean;
import shangri.example.com.shangri.presenter.MycardPresenter;
import shangri.example.com.shangri.presenter.view.MyCardView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.MyCompactFreagment;


/**
 * 我的和同全部界面
 */

public class MyCompactActivity extends BaseActivity<MyCardView, MycardPresenter> implements MyCardView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tl_2)
    SlidingTabLayout tl2;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;


    private ProgressDialogFragment mProgressDialog;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    List<String> data = new ArrayList<>();

    //用来记录跳转的位置
    int poi = -1;


    MyCompactFreagment instance;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_message_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_message_layout;
    }

    @Override
    protected MycardPresenter createPresenter() {
        return new MycardPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        data.add("全部合同");
        if (UserConfig.getInstance().getRole().equals("1")) {
            data.add("待他人签");
        } else {
            data.add("待自己签");
        }
        data.add("已完成");
        if (UserConfig.getInstance().getRole().equals("1")) {
            data.add("草稿箱");
        }
        data.add("其他合同");

        Log.d("Debug", "返回的数据长度为" + data.size());
        for (int i = 0; i < data.size(); i++) {
            if (data.size() == 4) {
                if (i < 3) {
                    instance = MyCompactFreagment.getInstance(i);
                } else {
                    instance = MyCompactFreagment.getInstance(4);
                }
            } else {
                instance = MyCompactFreagment.getInstance(i);
            }
            mFragments.add(instance);
        }
        View decorView = getWindow().getDecorView();
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tl2.setViewPager(vp);
        //为-1时点击全部进来  默认是显示第一个界面  不为-1点击其他地方进来
        poi = getIntent().getIntExtra("poi", -1);
        if (poi >= 0) {
            //默认显示第一个界面 下面是跳转到置顶界面
            vp.setCurrentItem(poi);
            //默认第一个标题变色   下面是让置顶标题变色
            tl2.onPageScrollStateChanged(poi);
        }
        // 注册订阅者
//        EventBus.getDefault().register(this);
    }

    /**
     * 点击撤销  刷新之前点击进入的freagment
     *
     * @param event
     */
    @SuppressWarnings("JavaDoc")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefushBean event) {
        Log.d("Debug", "到达合同类表界面");
        //默认显示第一个界面 下面是跳转到置顶界面
        vp.setCurrentItem(event.getPor());
        //默认第一个标题变色   下面是让置顶标题变色
        tl2.onPageScrollStateChanged(event.getPor());
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }


    @OnClick({R.id.setting_back, R.id.tl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tl_2:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        // 注销订阅者
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void personalPetail(CardRequestBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void otherDetail(CardRequestBean bean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

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
            return data.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}

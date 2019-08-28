package shangri.example.com.shangri.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导图界面
 * Created by fan on 2017/4/26.
 */

public class SplashActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    int[] imageViews = new int[]{R.mipmap.s1, R.mipmap.s2, R.mipmap.s3, R.mipmap.s4};

    @BindView(R.id.splash)
    ViewPager splash;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    TextView login;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 3) {
//            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
        } else {
//            register.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        initPage();
        //不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
    }

    private void initPage() {
        //实例化图片资源
        ArrayList<View> viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageViews.length;
        for (int imageView1 : imageViews) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageView1);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        splash.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        splash.setOnPageChangeListener(this);
    }

    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                SharedPreferenceUtil.put(this, Constant.IS_GUIDED, "guide");//没有引导页了
                ActivityUtils.startActivity(this, RegisterActivity.class);
                break;
            case R.id.login:
                SharedPreferenceUtil.put(this, Constant.IS_GUIDED, "guide");
                ActivityUtils.startActivity(this, MainActivity.class);
                break;
        }
    }

    class GuidePageAdapter extends PagerAdapter {

        private List<View> viewList;

        public GuidePageAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
            if (viewList != null) {
                return viewList.size();
            }
            return 0;
        }

        /**
         * 判断对象是否生成界面
         *
         * @param view
         * @param object
         * @return
         */
        @SuppressWarnings("JavaDoc")
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         *
         * @param container
         * @param position
         * @return
         */
        @SuppressWarnings("JavaDoc")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferenceUtil.put(this, Constant.IS_GUIDED, "guide");
    }
}

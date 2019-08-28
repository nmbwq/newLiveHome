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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CardRequestBean;
import shangri.example.com.shangri.presenter.MycardPresenter;
import shangri.example.com.shangri.presenter.view.MyCardView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.MonthFreagment;
import shangri.example.com.shangri.ui.fragment.NewDayFreagment;
import shangri.example.com.shangri.ui.fragment.NewDayFreagment1;
import shangri.example.com.shangri.ui.fragment.WeekFreagment;
import shangri.example.com.shangri.util.TimeUtil;


/**
 * 选择时间界面
 */

public class MydateActivity extends BaseActivity<MyCardView, MycardPresenter> implements MyCardView {


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

    boolean isFromDeatil;
    //    是否来自钱点击界面
    boolean isFromMoney;

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
        String platfrom_name = getIntent().getStringExtra("platfrom_name");
        if (platfrom_name == null) {
            platfrom_name = "";
        }
        Log.d("Debug", "平台信息是" + platfrom_name);
        String time_slot = getIntent().getStringExtra("time_slot");
        String start_date = getIntent().getStringExtra("start_date");
        String end_date = getIntent().getStringExtra("end_date");
        isFromDeatil = getIntent().getBooleanExtra("isFromDeatil", false);
        isFromMoney = getIntent().getBooleanExtra("isFromMoney", false);


        int NowWeekNumber = 0;
        String Styyyy = "";
        String Stmm = "";
        String Stdd = "";

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendar.getTime());
            int Nowyear = calendar.get(Calendar.YEAR);
            String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
            //设置为传来的时间
            calendar.setTime(TimeUtil.stringToDate(s, "yyyy-MM-dd"));
            //美国默认一周的开始为周日   设置为一周的开始为周一
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            //传过来的时间在当前属于第几周
            Styyyy = TimeUtil.longToString(Long.parseLong(start_date), "yyyy");
            Stmm = TimeUtil.longToString(Long.parseLong(start_date), "MM");
            Stdd = TimeUtil.longToString(Long.parseLong(start_date), "dd");
            //当前的时间
            Log.d("Debug", "当前年为" + Nowyear);
            //本年的当前周是正确的   其余的都是多1
            if (Nowyear == Integer.parseInt(Styyyy)) {
                Log.d("Debug", "到达相同");
                NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
            } else {
                Log.d("Debug", "到达不相同");
                NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
            }
            Log.d("Debug", "传过来的年月日" + Styyyy + Stmm + Stdd + "第几周" + NowWeekNumber + "第几个月" + Integer.parseInt(Stmm));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mSwipeBackLayout.setEnableGesture(false);
        tvTitle.setText("选择日期");
        data.add("日数据");
        data.add("周数据");
        data.add("月数据");
        data.add("自定义");
        Log.d("Debug", "返回的数据长度为" + data.size());

        NewDayFreagment instance1 = NewDayFreagment.getInstance(platfrom_name, isFromDeatil, start_date, isFromMoney);
        WeekFreagment instance2 = WeekFreagment.getInstance(platfrom_name, Styyyy, NowWeekNumber, isFromDeatil,isFromMoney);
        MonthFreagment instance3 = MonthFreagment.getInstance(platfrom_name, Styyyy, Integer.parseInt(Stmm), isFromDeatil,isFromMoney);
        NewDayFreagment1 instance4 = NewDayFreagment1.getInstance(platfrom_name, isFromDeatil, start_date, end_date,isFromMoney);

        mFragments.add(instance1);
        mFragments.add(instance2);
        mFragments.add(instance3);
        mFragments.add(instance4);


        View decorView = getWindow().getDecorView();
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tl2.setViewPager(vp);

        switch (time_slot) {
            case "week":
                vp.setCurrentItem(0);
                //默认第一个标题变色   下面是让置顶标题变色
                tl2.onPageScrollStateChanged(0);
                break;
            case "whole_week":
                vp.setCurrentItem(1);
                //默认第一个标题变色   下面是让置顶标题变色
                tl2.onPageScrollStateChanged(1);
                break;
            case "whole_month":
                vp.setCurrentItem(2);
                //默认第一个标题变色   下面是让置顶标题变色
                tl2.onPageScrollStateChanged(2);
                break;
            default:
                vp.setCurrentItem(3);
                //默认第一个标题变色   下面是让置顶标题变色
                tl2.onPageScrollStateChanged(3);
                break;
        }

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

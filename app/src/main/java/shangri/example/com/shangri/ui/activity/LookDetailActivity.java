package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.ChartMarkViewBean;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.DetailBean;
import shangri.example.com.shangri.presenter.DetailedDataPresenter;
import shangri.example.com.shangri.presenter.view.DetailedDataView;
import shangri.example.com.shangri.ui.adapter.DetailedAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.MyMarkerView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 发布任务详情界面
 * Created by admin on 2017/12/22.
 */

public class LookDetailActivity extends BaseActivity<DetailedDataView, DetailedDataPresenter> implements DetailedDataView, View.OnTouchListener {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.tv_fensi2)
    TextView tvFensi2;
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.iv_icon3)
    ImageView ivIcon3;
    @BindView(R.id.tv_fensi3)
    TextView tvFensi3;
    @BindView(R.id.tv_Fans)
    TextView tvFans;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_charm)
    TextView tvCharm;
    @BindView(R.id.tv_begin)
    TextView tvBegin;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.llll)
    LinearLayout llll;
    @BindView(R.id.lll)
    LinearLayout lll;
    @BindView(R.id.ll_isziding)
    LinearLayout ll_isziding;
    @BindView(R.id.r1)
    RelativeLayout r1;


    @BindView(R.id.news_entertain_irv)
    RecyclerView mNewsAnchorRecycler;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.Ho_info)
    HorizontalScrollView HoInfo;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.im3)
    ImageView im3;
    @BindView(R.id.im4)
    ImageView im4;
    private ProgressDialogFragment mProgressDialog;

    private DetailedAdapter mAdapter;
    private List<DetailBean.DetailDataBean> mNewsList = new ArrayList<>();

    //
    String mGuildid = "";
    String start_date = "";
    String end_date = "";
    String time_slot = "week";
    String platfrom_name = "";

    GestureDetector gestureDetector;

    String tv21 = "";
    String tv22 = "";
    String tv23 = "";
    String tv25 = "";
    String tv26 = "";
    String tv_meili = "";


    //礼物收入
    ArrayList<Entry> mValues1;
    //开播主播人数  会长有 主播没有
    ArrayList<Entry> mValues2;
    //        开播时长
    ArrayList<Entry> mValues3;
    //粉丝增长
    ArrayList<Entry> mValues4;
    //一直播需要的参数
    //礼物收入
    ArrayList<Entry> mValues6;
    //钻石收入
    ArrayList<Entry> mValues7;
    //小圆点的位置
    int positions = 0;


    //曲线图弹出时的数据
    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";
    //返回数据个数
    int  number;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_lookdetail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_lookdetail;
    }

    @Override
    protected DetailedDataPresenter createPresenter() {
        return new DetailedDataPresenter(this, this);
    }

    /*
 * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，将捕捉到的MotionEvent交给GestureDetector
 * 来分析是否有合适的callback函数来处理用户的手势
 */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    @Override
    protected void initViewsAndEvents() {
        //主页不允许左滑返回
//        mSwipeBackLayout.setEnableGesture(false);
        gestureDetector = new GestureDetector(new SufaceControll());//使用派生自OnGestureListener
        //绑定控件才能触发触摸时间   点击事件才才能其效果的 必须加上前三句话才有用
        chart1.setFocusable(true);
        chart1.setClickable(true);
        chart1.setLongClickable(true);
        chart1.setOnTouchListener(this);

        initChartView(chart1);
        initSpringView();
        mGuildid = getIntent().getStringExtra("mGuildid");
        platfrom_name = getIntent().getStringExtra("platfrom_name");
        tv21 = getIntent().getStringExtra("tv21");
        tv22 = getIntent().getStringExtra("tv22");
        tv23 = getIntent().getStringExtra("tv23");
        tv_meili = getIntent().getStringExtra("tv_meili");
        tv25 = getIntent().getStringExtra("tv25");
        tv26 = getIntent().getStringExtra("tv26");


        if (UserConfig.getInstance().getRole().equals("2")) {
            s1 = tv_meili;
            s3 = tv25;
            s4 = tv26;
            tvData.setText(tv_meili);
            tvCharm.setText(tv25);
            tvFensi.setText(tv26);
            tvBegin.setVisibility(View.GONE);
            tvFensi2.setText(tv_meili);
            im4.setVisibility(View.GONE);
        } else {
            s1 = tv_meili;
            s2 = tv21;
            s3 = tv22;
            s4 = tv23;
            tvBegin.setVisibility(View.VISIBLE);
            tvData.setText(tv_meili);
            tvCharm.setText(tv21);
            tvBegin.setText(tv22);
            tvFensi.setText(tv23);
            tvFensi2.setText(tv_meili);
            im4.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 请求数据
     */
    public void requestDate() {
        start_date = ChuandiBean.Moneystart_date;
        end_date = ChuandiBean.Moneyend_date;
        time_slot = ChuandiBean.Moneytime_slot;
        mPresenter.detail(end_date, mGuildid, start_date, time_slot);
    }

    private void initSpringView() {
        mNewsAnchorRecycler.setLayoutManager(new FastLinearScrollManger(LookDetailActivity.this));
        mAdapter = new DetailedAdapter(LookDetailActivity.this, R.layout.item_anchor_news, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mNewsAnchorRecycler.setAdapter(mAdapter);
        mNewsAnchorRecycler.setNestedScrollingEnabled(false);// 解决滑动冲突
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void topDta(TopTenBean resultBean) {

    }

    @Override
    public void detail(DetailBean resultBean) {
        if (mAdapter != null) {
            mAdapter.clear();
        }
        Log.d("Debug", "返回的是请求数据还是---" + time_slot);
        mAdapter.setData(resultBean.getDetail_data());
        try {
            switch (time_slot) {
                case "week":
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月dd日");
                    tvSelect.setText("日数据" + s);
                    ll_isziding.setVisibility(View.VISIBLE);
                    break;
                case "whole_week": {
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
                    tvSelect.setText("周数据" + st + "-" + en);
                    ll_isziding.setVisibility(View.VISIBLE);
                    break;
                }
                case "whole_month": {
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "yyyy年MM月");
                    tvSelect.setText("月数据" + st);
                    ll_isziding.setVisibility(View.VISIBLE);
                    break;
                }
                case "ziding": {
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
                    tvSelect.setText("自定义" + st + "-" + en);
                    ll_isziding.setVisibility(View.GONE);
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //礼物收入 属于第一张图
        mValues1 = new ArrayList<>();
        //开播主播人数  会长有 主播没有  属于第二张图
        mValues2 = new ArrayList<>();
//        开播时长 属于 第三张图
        mValues3 = new ArrayList<>();
        //粉丝增长  属于 第四张图
        mValues4 = new ArrayList<>();
        //一直播需要的参数
        //礼物收入  属于第一张图
        mValues6 = new ArrayList<>();
        //钻石收入  属于 第四张图
        mValues7 = new ArrayList<>();

        List<DetailBean.DetailDataBean> detail_data = resultBean.getDetail_data();
        Log.d("Debug", "返回的detail_data的长度为" + detail_data.size());
        if (detail_data.size()>2){
            number=detail_data.size();
        }else {
            number=7;
        }

        for (int i = 0; i < detail_data.size(); i++) {
            DetailBean.DetailDataBean detailDataBean = detail_data.get(i);
//            mValues.add(new Entry(0, 434, new ChartMarkViewBean("08-2", "08-1", "魅力值")));
            // 日数据  横坐标显示 mark弹出的都是myDate   周和月   横坐标显示的是myDate mark弹出的是weekDate
            String myDate = "";
            String weekDate = "";
            if (time_slot.equals("week")) {
                Log.d("Debug", "到达日数据这里");
                XAxis xAxis = chart1.getXAxis();       //获取x轴线
                xAxis.setLabelCount(number-2);  //设置X轴的显示个数
                myDate = TimeUtil.getMyDate(detailDataBean.getDate());
                if (UserConfig.getInstance().getRole().equals("1")) {
                    mValues2.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAnchors() + ""), new ChartMarkViewBean(myDate, myDate, s2,detailDataBean.getAnchors())));
                }
                mValues3.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getLive_time() + ""), new ChartMarkViewBean(myDate, myDate, s3,detailDataBean.getLive_time())));
                if (platfrom_name.indexOf("一直播") != -1) {
                    mValues6.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_gift() + ""), new ChartMarkViewBean(myDate, myDate, s1,detailDataBean.getIncome_gift())));
                    mValues7.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_diamond() + ""), new ChartMarkViewBean(myDate, myDate, s4,detailDataBean.getIncome_diamond())));
                } else {
                    mValues1.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getGifts() + ""), new ChartMarkViewBean(myDate, myDate, s1,detailDataBean.getGifts())));
                    mValues4.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAdded_fans() + ""), new ChartMarkViewBean(myDate, myDate, s4,detailDataBean.getAdded_fans())));
                }
            } else if (time_slot.equals("whole_week")) {
                Log.d("Debug", "到达周数据这里");
                XAxis xAxis = chart1.getXAxis();       //获取x轴线
                xAxis.setLabelCount(number-2);  //设置X轴的显示个数
                String[] strings = stringCut(detailDataBean.getDate());
                int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
                myDate = weekNumByTime + "周";
                weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
                //返回12个月  显示六个月
                if (i % 2 == 0) {

                    if (UserConfig.getInstance().getRole().equals("2")) {
                    } else {
                        mValues2.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAnchors() + ""), new ChartMarkViewBean("", weekDate, s2,detailDataBean.getAnchors())));
                    }
                    mValues3.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getLive_time() + ""), new ChartMarkViewBean("", weekDate, s3,detailDataBean.getLive_time())));
                    if (platfrom_name.indexOf("一直播") != -1) {
                        mValues6.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_gift() + ""), new ChartMarkViewBean("", weekDate, s1,detailDataBean.getIncome_gift())));
                        mValues7.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_diamond() + ""), new ChartMarkViewBean("", weekDate, s4,detailDataBean.getIncome_diamond())));
                    } else {
                        mValues1.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getGifts() + ""), new ChartMarkViewBean("", weekDate, s1,detailDataBean.getGifts())));
                        mValues4.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAdded_fans() + ""), new ChartMarkViewBean("", weekDate, s4,detailDataBean.getAdded_fans())));
                    }
                } else {

                    if (UserConfig.getInstance().getRole().equals("2")) {
                    } else {
                        mValues2.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAnchors() + ""), new ChartMarkViewBean(myDate, weekDate, s2,detailDataBean.getAnchors())));
                    }
                    mValues3.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getLive_time() + ""), new ChartMarkViewBean(myDate, weekDate, s3,detailDataBean.getLive_time())));
                    if (platfrom_name.indexOf("一直播") != -1) {
                        mValues6.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_gift() + ""), new ChartMarkViewBean(myDate, weekDate, s1,detailDataBean.getIncome_gift())));
                        mValues7.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_diamond() + ""), new ChartMarkViewBean(myDate, weekDate, s4,detailDataBean.getIncome_diamond())));
                    } else {
                        mValues1.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getGifts() + ""), new ChartMarkViewBean(myDate, weekDate, s1,detailDataBean.getGifts())));
                        mValues4.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAdded_fans() + ""), new ChartMarkViewBean(myDate, weekDate, s4,detailDataBean.getAdded_fans())));
                    }

                }
            } else if (time_slot.equals("whole_month")) {
                Log.d("Debug", "到达月份这里");
                XAxis xAxis = chart1.getXAxis();       //获取x轴线
                xAxis.setLabelCount(number-2);  //设置X轴的显示个数
                int monthNumByTime = TimeUtil.getMonthNumByTime(detailDataBean.getDate());
                myDate = monthNumByTime + "月";
                weekDate = TimeUtil.getMyDate1(detailDataBean.getDate());
                //返回12个月  显示六个月
                if (i % 2 == 0) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                    } else {
                        mValues2.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAnchors() + ""), new ChartMarkViewBean("", weekDate, s2,detailDataBean.getAnchors())));
                    }
                    mValues3.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getLive_time() + ""), new ChartMarkViewBean("", weekDate, s3,detailDataBean.getLive_time())));
                    if (platfrom_name.indexOf("一直播") != -1) {
                        mValues6.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_gift() + ""), new ChartMarkViewBean("", weekDate, s1,detailDataBean.getIncome_gift())));
                        mValues7.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_diamond() + ""), new ChartMarkViewBean("", weekDate, s4,detailDataBean.getIncome_diamond())));
                    } else {
                        mValues1.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getGifts() + ""), new ChartMarkViewBean("", weekDate, s1,detailDataBean.getGifts())));
                        mValues4.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAdded_fans() + ""), new ChartMarkViewBean("", weekDate, s4,detailDataBean.getAdded_fans())));
                    }
                } else {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                    } else {
                        mValues2.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAnchors() + ""), new ChartMarkViewBean(myDate, weekDate, s2,detailDataBean.getAnchors())));
                    }
                    mValues3.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getLive_time() + ""), new ChartMarkViewBean(myDate, weekDate, s3,detailDataBean.getLive_time())));
                    if (platfrom_name.indexOf("一直播") != -1) {
                        mValues6.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_gift() + ""), new ChartMarkViewBean(myDate, weekDate, s1,detailDataBean.getIncome_gift())));
                        mValues7.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getIncome_diamond() + ""), new ChartMarkViewBean(myDate, weekDate, s4,detailDataBean.getIncome_diamond())));
                    } else {
                        mValues1.add(new Entry(i, (float) Double.parseDouble(detailDataBean.getGifts()), new ChartMarkViewBean(myDate, weekDate, s1,detailDataBean.getGifts())));
                        mValues4.add(new Entry(i, (int) Double.parseDouble(detailDataBean.getAdded_fans() + ""), new ChartMarkViewBean(myDate, weekDate, s4,detailDataBean.getAdded_fans())));
                    }

                }
            } else if (ChuandiBean.Moneytime_slot.equals("ziding")) {

            }
        }

        Log.d("Debug", "返回的数据个数为" + mValues1.size());
        if (platfrom_name.indexOf("一直播") != -1) {
            setLineChartDate(chart1, mValues6);
        } else {
            setLineChartDate(chart1, mValues1);
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        im2.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im3.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im4.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im1.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(LookDetailActivity.this.getSupportFragmentManager());
        requestDate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.ll_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.ll_select:
                Intent intent = new Intent(LookDetailActivity.this, MydateActivity.class);
                intent.putExtra("platfrom_name", "");
                intent.putExtra("time_slot", time_slot);
                intent.putExtra("start_date", start_date);
                intent.putExtra("end_date", end_date);
                intent.putExtra("isFromMoney", true);
                startActivity(intent);
                break;
        }
    }


    class SufaceControll implements GestureDetector.OnGestureListener {
        int FLIP_DISTANCE = 5;


        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                    && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
//                ToastUtil.showShort("左下滑动");
//                setLineChartDate(positions, 1);
                return true;
            }
            if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                    && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
//                ToastUtil.showShort("左上滑动");
//                setLineChartDate(positions, 1);
                return true;
            }
            if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                    && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
//                ToastUtil.showShort("右下滑动");
//                setLineChartDate(positions, 0);
                return true;
            }
            if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                    && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
//                ToastUtil.showShort("右上滑动");
//                setLineChartDate(positions, 0);
                return true;
            }
            if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
//                ToastUtil.showShort("向左滑动");
                if (PointUtils.isFastClick1()) {
                    setLineChartDate(positions, 1);
                }
                return true;
            }
            if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
//                ToastUtil.showShort("向右滑动");
                if (PointUtils.isFastClick1()) {
                    setLineChartDate(positions, 0);
                }
                return true;
            }
            //                ToastUtil.showShort("向上滑动");
            return e1.getY() - e2.getY() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5 || e2.getY() - e1.getY() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }


    /**
     * 初始化折线图
     */
    public void initChartView(LineChart mLineChart) {

        mLineChart.setLogEnabled(true);//打印日志
        //取消描述文字
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setNoDataText("没有数据");//没有数据时显示的文字
        mLineChart.setNoDataTextColor(Color.WHITE);//没有数据时显示文字的颜色
        mLineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        mLineChart.setDrawBorders(false);//是否禁止绘制图表边框的线
        mLineChart.setBorderColor(Color.WHITE); //设置 chart 边框线的颜色。
        mLineChart.setBorderWidth(3f); //设置 chart 边界线的宽度，单位 dp。
        mLineChart.setTouchEnabled(true);     //能否点击
        mLineChart.setDragEnabled(false);   //能否拖拽
        mLineChart.setScaleEnabled(false);  //能否缩放
        mLineChart.animateX(1000);//绘制动画 从左到右
        mLineChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
        mLineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        mLineChart.setDragDecelerationEnabled(false);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）

        MyMarkerView mv = new MyMarkerView(this);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv);        //设置 marker ,点击后显示的功能 ，布局可以自定义

        XAxis xAxis = mLineChart.getXAxis();       //获取x轴线
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setTextSize(10f);//设置文字大小
        xAxis.setAvoidFirstLastClipping(true);//图表第一个和最后一个label数据不超出左边和右边的Y轴
        xAxis.setAxisMinimum(0f);//设置x轴的最小值 //`
//        xAxis.setAxisMaximum(31f);//设置最大值 //
        xAxis.setLabelCount(12);  //设置X轴的显示个数
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setTextColor(Color.WHITE);   //设置字体颜色
        xAxis.setAxisLineColor(Color.WHITE);//设置x轴线颜色
        xAxis.setAxisLineWidth(0.5f);//设置x轴线宽度
        YAxis leftAxis = mLineChart.getAxisLeft();
        YAxis axisRight = mLineChart.getAxisRight();
//        leftAxis.setInverted(true);  y轴翻转
        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
        leftAxis.setGranularity(1f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#40999999"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setStartAtZero(true);        //设置Y轴数值 从零开始
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setTextSize(10f);            //设置Y轴刻度字体
        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(0.5f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setMinWidth(0f);
//        leftAxis.setMaxWidth(200f);
        Legend l = mLineChart.getLegend();//图例
        l.setEnabled(false);   //是否使用 图例

        //y轴自定义样式
    }


    /**
     * 放数据
     */
    private void setLineChartDate(LineChart mLineChart, final ArrayList<Entry> mValues) {
//        //自定义x轴标签数据
        mLineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                switch (ChuandiBean.Moneytime_slot) {
                    case "week":
                        if (value < 7) {
                            ChartMarkViewBean data = (ChartMarkViewBean) mValues.get((int) value).getData();
                            return data.getDate();
                        }
                        break;
                    case "whole_week":
                        if (value < 12) {
                            ChartMarkViewBean data = (ChartMarkViewBean) mValues.get((int) value).getData();
                            return data.getDate();
                        }
                        break;
                    case "whole_month":
                        if (value < 12) {
                            ChartMarkViewBean data = (ChartMarkViewBean) mValues.get((int) value).getData();
                            return data.getDate();
                        }
                        break;
                }
                return null;
            }
        });

        if (mValues.size() == 0) return;
        LineDataSet set1;
        //设置数据1  参数1：数据源 参数2：图例名称
        set1 = new LineDataSet(mValues, "1");
//            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置曲线展示为圆滑曲线（如果不设置则默认折线）
        //曲线的颜色
        set1.setColor(getResources().getColor(R.color.color_d0a76c));
        //曲线上面点的颜色
//            set1.setCircleColor(Color.parseColor("#AAFFFFFF"));
        set1.setCircleColor(getResources().getColor(R.color.color_d0a76c));
        //设置曲线值的圆点是实心还是空心
//            set1.setDrawCircleHole(true);
        set1.setHighLightColor(getResources().getColor(R.color.color_d0a76c));//设置点击交点后显示交高亮线的颜色
        set1.setHighlightEnabled(true);//是否使用点击高亮线
        set1.setDrawCircles(true);

        set1.setValueTextColor(Color.WHITE);
        set1.setLineWidth(0f);//设置线宽
        set1.setCircleRadius(2f);//设置焦点圆心的大小
        set1.setHighlightLineWidth(0.5f);//设置点击交点后显示高亮线宽
        set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
        set1.setValueTextSize(10f);//设置显示值的文字大小
        set1.setDrawFilled(true);//设置使用 范围背景填充
        set1.setDrawValues(false);
        //格式化显示数据
        final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
        set1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return mFormat.format(value);
            }
        });
        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.color.doa76c);
            set1.setFillDrawable(drawable);//设置范围背景填充
        } else {
            set1.setFillColor(R.color.doa76c);
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the datasets
        //创建LineData对象 属于LineChart折线图的数据集合
        LineData data = new LineData(dataSets);
        // 添加到图表中
        mLineChart.setData(data);
        //绘制图表
        mLineChart.invalidate();
    }

    /**
     * 字符串截取
     */

    public String[] stringCut(String s) {
        return s.split("~");
    }

    /**
     * 滑动请求数据   position是当前小圆点的位置  type 0 是向右滑 1向左滑
     */
    private void setLineChartDate(int position, int type) {

//        /**
//         * 放数据
//         */
//        //礼物收入
//        mValues1 = new ArrayList<>();
//        //开播主播人数  会长有 主播没有
//        mValues2 = new ArrayList<>();
////        开播时长
//        mValues3 = new ArrayList<>();
//        //粉丝增长
//        mValues4 = new ArrayList<>();
//        //一直播需要的参数
//        //礼物收入
//        mValues6 = new ArrayList<>();
//        //钻石收入
//        mValues7 = new ArrayList<>();
//        if (platfrom_name.indexOf("一直播") != -1) {
//            setLineChartDate(chart1, mValues6);
//        } else {
//            setLineChartDate(chart1, mValues1);
//        }

        if (UserConfig.getInstance().getRole().equals("2")) {

            if (type == 0) {
                switch (position) {
                    case 0:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues7);
                        } else {
                            setLineChartDate(chart1, mValues4);
                        }
                        positions = 2;
                        resush(positions);
                        break;
                    case 1:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues6);
                        } else {
                            setLineChartDate(chart1, mValues1);
                        }
                        positions = 0;
                        resush(positions);
                        break;
                    case 2:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues3);
                        } else {
                            setLineChartDate(chart1, mValues3);
                        }
                        positions = 1;
                        resush(positions);
                        break;
                }
            } else {
//                /**
//                 * 放数据
//                 */
//                //礼物收入 属于第一张图
//                mValues1 = new ArrayList<>();
//                //开播主播人数  会长有 主播没有  属于第二张图
//                mValues2 = new ArrayList<>();
////        开播时长 属于 第三张图
//                mValues3 = new ArrayList<>();
//                //粉丝增长  属于 第四张图
//                mValues4 = new ArrayList<>();
//                //一直播需要的参数
//                //礼物收入  属于第一张图
//                mValues6 = new ArrayList<>();
//                //钻石收入  属于 第四张图
//                mValues7 = new ArrayList<>();
                switch (position) {
                    case 0:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues3);
                        } else {
                            setLineChartDate(chart1, mValues3);
                        }
                        positions = 1;
                        resush(positions);
                        break;
                    case 1:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues7);
                        } else {
                            setLineChartDate(chart1, mValues4);
                        }
                        positions = 2;
                        resush(positions);
                        break;
                    case 2:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues6);
                        } else {
                            setLineChartDate(chart1, mValues1);
                        }
                        positions = 0;
                        resush(positions);
                        break;
                }
            }

        } else {

            if (type == 0) {
                switch (position) {
                    case 0:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues7);
                        } else {
                            setLineChartDate(chart1, mValues4);
                        }
                        positions = 3;
                        resush(positions);
                        break;
                    case 1:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues6);
                        } else {
                            setLineChartDate(chart1, mValues1);
                        }
                        positions = 0;
                        resush(positions);
                        break;
                    case 2:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues2);
                        } else {
                            setLineChartDate(chart1, mValues2);
                        }
                        positions = 1;
                        resush(positions);
                        break;
                    case 3:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues3);
                        } else {
                            setLineChartDate(chart1, mValues3);
                        }
                        positions = 2;
                        resush(positions);
                        break;
                }
            } else {
                switch (position) {
                    case 0:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues2);
                        } else {
                            setLineChartDate(chart1, mValues2);
                        }
                        positions = 1;
                        resush(positions);
                        break;
                    case 1:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues3);
                        } else {
                            setLineChartDate(chart1, mValues3);
                        }
                        positions = 2;
                        resush(positions);
                        break;
                    case 2:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues7);
                        } else {
                            setLineChartDate(chart1, mValues4);
                        }
                        positions = 3;
                        resush(positions);
                        break;
                    case 3:
                        if (platfrom_name.indexOf("一直播") != -1) {
                            setLineChartDate(chart1, mValues6);
                        } else {
                            setLineChartDate(chart1, mValues1);
                        }
                        positions = 0;
                        resush(positions);
                        break;
                }
            }
        }


    }

    /**
     * 滑动改变数据
     */
    public void resush(int position) {
        im1.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im2.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im3.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));
        im4.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan2));

        if (UserConfig.getInstance().getRole().equals("2")) {
            switch (position) {
                case 0:
                    im1.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv_meili);
                    break;
                case 1:
                    im2.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv25);
                    break;
                case 2:
                    im3.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv26);
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    im1.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv_meili);
                    break;
                case 1:
                    im2.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv21);
                    break;
                case 2:
                    im3.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv22);
                    break;
                case 3:
                    im4.setImageDrawable(getResources().getDrawable(R.mipmap.lunbo_yuan1));
                    tvFensi2.setText(tv23);
                    break;
            }

        }


    }

}

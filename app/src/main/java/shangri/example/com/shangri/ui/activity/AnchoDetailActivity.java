package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.ChartMarkViewBean;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.RespAnchoDetailBean;
import shangri.example.com.shangri.presenter.AnchoDetailPresenter;
import shangri.example.com.shangri.presenter.view.ArchoDetailView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.MyMarkerView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 主播明细详情
 */
public class AnchoDetailActivity extends BaseActivity<ArchoDetailView, AnchoDetailPresenter> implements ArchoDetailView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.ancho_name)
    TextView anchoName;
    @BindView(R.id.tv_platform)
    TextView tvPlatform;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_income_count)
    TextView tvIncomeCount;
    @BindView(R.id.tv_incomt_name)
    TextView tvIncomtName;
    @BindView(R.id.tv_incomt_hint)
    TextView tvIncomtHint;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.im_left_sanjiao)
    ImageView imLeftSanjiao;
    @BindView(R.id.tv_left_time)
    TextView tvLeftTime;
    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_right_time)
    TextView tvRightTime;
    @BindView(R.id.im_right_sanjiao)
    ImageView imRightSanjiao;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.tv_now_day)
    TextView tvNowDay;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_number_wan)
    TextView tvNumberWan;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_meili)
    TextView tvMeili;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv25)
    TextView tv25;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv26)
    TextView tv26;
    @BindView(R.id.ll6)
    LinearLayout ll6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv27)
    TextView tv27;
    @BindView(R.id.ll7)
    LinearLayout ll7;
    @BindView(R.id.ll_anchor_biaoge)
    LinearLayout llAnchorBiaoge;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.fensiw)
    TextView fensiw;
    @BindView(R.id.cv_chartView)
    LineChart cvChartView;
    @BindView(R.id.ll_chart)
    LinearLayout llChart;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.tv_fensi2)
    TextView tvFensi2;
    @BindView(R.id.fensiw2)
    TextView fensiw2;
    @BindView(R.id.cv_chartView2)
    LineChart cvChartView2;
    @BindView(R.id.iv_icon3)
    ImageView ivIcon3;
    @BindView(R.id.tv_fensi3)
    TextView tvFensi3;
    @BindView(R.id.fensiw3)
    TextView fensiw3;
    @BindView(R.id.cv_chartView3)
    LineChart cvChartView3;
    @BindView(R.id.iv_icon4)
    ImageView ivIcon4;
    @BindView(R.id.tv_fensi4)
    TextView tvFensi4;
    @BindView(R.id.fensiw4)
    TextView fensiw4;
    @BindView(R.id.cv_chartView4)
    LineChart cvChartView4;


    @BindView(R.id.tv_yi)
    TextView tv_yi;
    @BindView(R.id.tv_number_qianwan)
    TextView tv_number_qianwan;

    int index = -1;
    @BindView(R.id.no_chart)
    RelativeLayout noChart;
    @BindView(R.id.chart_info)
    LinearLayout chartInfo;
    @BindView(R.id.tv_send)
    TextView tv_send;

    private String time_slot = "week";
    private String end_date = "";
    private String start_date = "";
    private String platfrom_name = "platfrom_name";

    private String mGuildid = "";
    private String anchor_uid = "";

    RespAnchoDetailBean mresultBean;
    //是否来自主播管理界面   用来判断右面删除图标显示不显示的
    public boolean isFromAnchor;

    //曲线图弹出时的数据
    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";

    /**
     * 我爱中国
     *
     * @return
     */
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_ancho_detail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_ancho_detail;
    }

    @Override
    protected AnchoDetailPresenter createPresenter() {
        return new AnchoDetailPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        boolean booleanExtra = getIntent().getBooleanExtra("isFromCard", false);
        isFromAnchor = getIntent().getBooleanExtra("isFromAnchor", false);
        platfrom_name = getIntent().getStringExtra("platfrom_name");
        anchor_uid = getIntent().getStringExtra("anchor_uid");
        mGuildid = getIntent().getStringExtra("guild_id");
        initChartView(cvChartView, 1);
        initChartView(cvChartView2, 2);
        initChartView(cvChartView3, 3);
        initChartView(cvChartView4, 4);
        if (isFromAnchor) {
            tv_send.setVisibility(View.VISIBLE);
        } else {
            tv_send.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        //销毁到前两个界面
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        requstData();
    }

    @Override
    public void loadData(RespAnchoDetailBean listBean) {
        if (ChuandiBean.Detailtime_slot.length() == 0) {
            ChuandiBean.Detailtime_slot = "week";
        }
        setHeadData(listBean);
        if (ChuandiBean.Detailtime_slot.equals("ziding")) {
            //不加载折线图
        } else {
            Log.d("Debug", "请求的类型为" + ChuandiBean.Detailtime_slot);
            ArrayList<Entry> mValues1 = new ArrayList<>();
            ArrayList<Entry> mValues2 = new ArrayList<>();
            ArrayList<Entry> mValues3 = new ArrayList<>();
            ArrayList<Entry> mValues4 = new ArrayList<>();
            List<RespAnchoDetailBean.ChartDataBean.SortBean> sort = listBean.getChart_data().getSort();
            for (int i = 0; i < sort.size(); i++) {
                RespAnchoDetailBean.ChartDataBean.SortBean sortBean = sort.get(i);
//            mValues.add(new Entry(0, 434, new ChartMarkViewBean("08-2", "08-1", "魅力值")));
                // 日数据  横坐标显示 mark弹出的都是myDate   周和月   横坐标显示的是myDate mark弹出的是weekDate
                String myDate = "";
                String weekDate = "";
                switch (ChuandiBean.Detailtime_slot) {
                    case "week": {
                        XAxis xAxis = cvChartView.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(5);  //设置X轴的显示个数
                        myDate = TimeUtil.getMyDate(sortBean.getDate());
                        mValues1.add(new Entry(i, (int) Double.parseDouble(sortBean.getCount() + ""), new ChartMarkViewBean(myDate, myDate, s1)));
                        break;
                    }
                    case "whole_week": {
                        XAxis xAxis = cvChartView.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        String[] strings = stringCut(sortBean.getDate());
                        int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
                        myDate = weekNumByTime + "周";
                        weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues1.add(new Entry(i, (int) Double.parseDouble(sortBean.getCount() + ""), new ChartMarkViewBean("", weekDate, s1)));
                        } else {
                            mValues1.add(new Entry(i, (int) Double.parseDouble(sortBean.getCount() + ""), new ChartMarkViewBean(myDate, weekDate, s1)));
                        }
                        break;
                    }
                    case "whole_month": {
                        XAxis xAxis = cvChartView.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        int monthNumByTime = TimeUtil.getMonthNumByTime(sortBean.getDate());
                        myDate = monthNumByTime + "月";
                        weekDate = TimeUtil.getMyDate1(sortBean.getDate());
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues1.add(new Entry(i, (int) Double.parseDouble(sortBean.getCount() + ""), new ChartMarkViewBean("", weekDate, s1)));
                        } else {
                            mValues1.add(new Entry(i, (int) Double.parseDouble(sortBean.getCount() + ""), new ChartMarkViewBean(myDate, weekDate, s1)));
                        }
                        break;
                    }
                    case "ziding":

                        break;
                }
            }
            List<RespAnchoDetailBean.ChartDataBean.GiftsBean> gifts;
            if (listBean.getChart_data().getGifts() == null) {
                gifts = listBean.getChart_data().getIncome_gift();

            } else {
                gifts = listBean.getChart_data().getGifts();
            }


            for (int i = 0; i < gifts.size(); i++) {
                RespAnchoDetailBean.ChartDataBean.GiftsBean GiftsBean = gifts.get(i);
//            mValues.add(new Entry(0, 434, new ChartMarkViewBean("08-2", "08-1", "魅力值")));
                // 日数据  横坐标显示 mark弹出的都是myDate   周和月   横坐标显示的是myDate mark弹出的是weekDate
                String myDate = "";
                String weekDate = "";
                switch (ChuandiBean.Detailtime_slot) {
                    case "week": {
                        XAxis xAxis = cvChartView2.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(5);  //设置X轴的显示个数

                        myDate = TimeUtil.getMyDate(GiftsBean.getDate());
                        mValues2.add(new Entry(i, (int) Double.parseDouble(GiftsBean.getCount()), new ChartMarkViewBean(myDate, myDate, s2)));
                        break;
                    }
                    case "whole_week": {
                        XAxis xAxis = cvChartView2.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        String[] strings = stringCut(GiftsBean.getDate());
                        int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
                        myDate = weekNumByTime + "周";
                        weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues2.add(new Entry(i, (int) Double.parseDouble(GiftsBean.getCount()), new ChartMarkViewBean("", weekDate, s2)));
                        } else {
                            mValues2.add(new Entry(i, (int) Double.parseDouble(GiftsBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s2)));
                        }
                        break;
                    }
                    case "whole_month": {
                        XAxis xAxis = cvChartView2.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        int monthNumByTime = TimeUtil.getMonthNumByTime(GiftsBean.getDate());
                        myDate = monthNumByTime + "月";
                        weekDate = TimeUtil.getMyDate1(GiftsBean.getDate());
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues2.add(new Entry(i, (int) Double.parseDouble(GiftsBean.getCount()), new ChartMarkViewBean("", weekDate, s2)));
                        } else {
                            mValues2.add(new Entry(i, (int) Double.parseDouble(GiftsBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s2)));
                        }
                        break;
                    }
                }
            }

            List<RespAnchoDetailBean.ChartDataBean.LiveTimeBean> live_time = listBean.getChart_data().getLive_time();


            for (int i = 0; i < live_time.size(); i++) {
                RespAnchoDetailBean.ChartDataBean.LiveTimeBean LiveTimeBean = live_time.get(i);
//            mValues.add(new Entry(0, 434, new ChartMarkViewBean("08-2", "08-1", "魅力值")));
                // 日数据  横坐标显示 mark弹出的都是myDate   周和月   横坐标显示的是myDate mark弹出的是weekDate
                String myDate = "";
                String weekDate = "";
                switch (ChuandiBean.Detailtime_slot) {
                    case "week": {
                        XAxis xAxis = cvChartView3.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(5);  //设置X轴的显示个数

                        myDate = TimeUtil.getMyDate(LiveTimeBean.getDate());
                        mValues3.add(new Entry(i, (int) Double.parseDouble(LiveTimeBean.getCount()), new ChartMarkViewBean(myDate, myDate, s3)));
                        break;
                    }
                    case "whole_week": {
                        XAxis xAxis = cvChartView3.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        String[] strings = stringCut(LiveTimeBean.getDate());
                        int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
                        myDate = weekNumByTime + "周";
                        weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues3.add(new Entry(i, (int) Double.parseDouble(LiveTimeBean.getCount()), new ChartMarkViewBean("", weekDate, s3)));
                        } else {
                            mValues3.add(new Entry(i, (int) Double.parseDouble(LiveTimeBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s3)));
                        }
                        break;
                    }
                    case "whole_month": {
                        XAxis xAxis = cvChartView3.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(9);  //设置X轴的显示个数

                        int monthNumByTime = TimeUtil.getMonthNumByTime(LiveTimeBean.getDate());
                        myDate = monthNumByTime + "月";
                        weekDate = TimeUtil.getMyDate1(LiveTimeBean.getDate());
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues3.add(new Entry(i, (int) Double.parseDouble(LiveTimeBean.getCount()), new ChartMarkViewBean("", weekDate, s3)));
                        } else {
                            mValues3.add(new Entry(i, (int) Double.parseDouble(LiveTimeBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s3)));
                        }
                        break;
                    }
                }
            }
            List<RespAnchoDetailBean.ChartDataBean.AddedFansBean> added_fans;
            if (listBean.getChart_data().getAdded_fans() == null) {
                added_fans = listBean.getChart_data().getIncome_diamond();
            } else {
                added_fans = listBean.getChart_data().getAdded_fans();
            }
            for (int i = 0; i < added_fans.size(); i++) {
                RespAnchoDetailBean.ChartDataBean.AddedFansBean AddedFansBean = added_fans.get(i);
//            mValues.add(new Entry(0, 434, new ChartMarkViewBean("08-2", "08-1", "魅力值")));
                // 日数据  横坐标显示 mark弹出的都是myDate   周和月   横坐标显示的是myDate mark弹出的是weekDate
                String myDate = "";
                String weekDate = "";
                switch (ChuandiBean.Detailtime_slot) {
                    case "week": {
                        XAxis xAxis = cvChartView4.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(5);  //设置X轴的显示个数

                        myDate = TimeUtil.getMyDate(AddedFansBean.getDate());
                        mValues4.add(new Entry(i, (int) Double.parseDouble(AddedFansBean.getCount()), new ChartMarkViewBean(myDate, myDate, s4)));
                        break;
                    }
                    case "whole_week": {
                        XAxis xAxis = cvChartView4.getXAxis();
                        xAxis.setLabelCount(10);  //设置X轴的显示个数

                        String[] strings = stringCut(AddedFansBean.getDate());
                        int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
                        myDate = weekNumByTime + "周";
                        weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues4.add(new Entry(i, (int) Double.parseDouble(AddedFansBean.getCount()), new ChartMarkViewBean("", weekDate, s4)));
                        } else {
                            mValues4.add(new Entry(i, (int) Double.parseDouble(AddedFansBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s4)));
                        }
                        break;
                    }
                    case "whole_month": {
                        XAxis xAxis = cvChartView4.getXAxis();       //获取x轴线

                        xAxis.setLabelCount(9);  //设置X轴的显示个数

                        int monthNumByTime = TimeUtil.getMonthNumByTime(AddedFansBean.getDate());
                        myDate = monthNumByTime + "月";
                        weekDate = TimeUtil.getMyDate1(AddedFansBean.getDate());
                        //返回12个月  显示六个月
                        if (i % 2 == 0) {
                            mValues4.add(new Entry(i, (int) Double.parseDouble(AddedFansBean.getCount()), new ChartMarkViewBean("", weekDate, s4)));
                        } else {
                            mValues4.add(new Entry(i, (int) Double.parseDouble(AddedFansBean.getCount()), new ChartMarkViewBean(myDate, weekDate, s4)));
                        }
                        break;
                    }
                }
            }

            for (int i = 0; i < mValues4.size(); i++) {
                ChartMarkViewBean data = (ChartMarkViewBean) mValues4.get(i).getData();
                Log.d("Debug", "横坐标值为--------" + data.getDate());
            }


            Log.d("Debug", "返回的数据长度为+----" + mValues1);

            setLineChartDate(cvChartView, mValues1, 1);
            setLineChartDate(cvChartView2, mValues2, 2);
            setLineChartDate(cvChartView3, mValues3, 3);
            setLineChartDate(cvChartView4, mValues4, 4);
        }

    }

    @Override
    public void anchorDelete() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("移除成功");
        finish();
    }

    public void requstData() {
        //选择时间返回直接去实体类里面的数据
        if (ChuandiBean.Detailplatfrom_name.equals(platfrom_name)) {
            start_date = ChuandiBean.Detailstart_date;
            end_date = ChuandiBean.Detailend_date;
            time_slot = ChuandiBean.Detailtime_slot;
            Log.d("Debug", "开始时间和结束时间" + start_date + "djhsjfhkah" + end_date + "传过来time_slot" + time_slot);
//            if (time_slot.length() == 0) {
//                time_slot = "week";
//            }
            int i = 100;
            String now = null;
            try {
                now = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
                if (start_date.length() > 0) {
                    i = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd").compareTo(now);
                }
                if (i == 0) {
                    tvNowDay.setVisibility(View.VISIBLE);
                } else {
                    tvNowDay.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("Debug", "公会名称相同");
        } else {
            Log.d("Debug", "公会名称不相同----------");
            //菠萝街的首次进入获取的是 今日实时数据  其他公会进来获取的是昨日数据
            if (platfrom_name == null) {
                platfrom_name = "";
            }
            if (platfrom_name.indexOf("菠萝街") != -1) {
                int i = 100;
                try {
                    start_date = TimeUtil.getCurrentTime() + "";
                    end_date = TimeUtil.getCurrentTime() + "";
                    String now = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
                    i = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd").compareTo(now);
                    if (i == 0) {
                        tvNowDay.setVisibility(View.VISIBLE);
                    } else {
                        tvNowDay.setVisibility(View.GONE);
                    }
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月dd日");
                    tvSelect.setText("日数据" + s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                tvNowDay.setVisibility(View.GONE);
                //获取昨天的时间
                String ourSelData = TimeUtil.getOurSelData(-1);
                tvSelect.setText("日数据" + ourSelData);
                start_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
                end_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
            }
        }
        mPresenter.loadData(mGuildid, anchor_uid, time_slot, start_date, end_date);
    }


    private void setHeadData(RespAnchoDetailBean listBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }

        mresultBean = listBean;
        Glide.with(this).load(listBean.getAnchor().getAvatar_url()).error(R.mipmap.bg_touxiang).into(ivAvatar);
        anchoName.setText(listBean.getAnchor().getAnchor_name());
        tvPlatform.setText(listBean.getAnchor().getPlatfrom_name());
        tvId.setText("ID: " + anchor_uid + "");
        tvIncomeCount.setText(listBean.getTotal_all().getGifts() + "");
        if (listBean.getTotal_data().getGifts() == null) {
            tvIncomtName.setText("总" + listBean.getTotal_data().getIncome_gift().getName().replace("增长", ""));
        } else {
            tvIncomtName.setText("总" + listBean.getTotal_data().getGifts().getName().replace("增长", ""));
        }
        tvIncomtHint.setText("主播在此直播平台的" + tvIncomtName.getText().toString());
        if (listBean.getTotal_data().getGifts() == null) {
            tvFensi.setText("公会" + listBean.getTotal_data().getIncome_gift().getName().replace("增长", "") + "排名趋势");
        } else {
            tvFensi.setText("公会" + listBean.getTotal_data().getGifts().getName().replace("增长", "") + "排名趋势");
        }
        if (listBean.getTotal_data().getGifts() == null) {
            tvFensi2.setText(listBean.getTotal_data().getIncome_gift().getName());
        } else {
            tvFensi2.setText(listBean.getTotal_data().getGifts().getName());
        }
        tvFensi3.setText(listBean.getTotal_data().getLive_time().getName());

        if (listBean.getTotal_data().getAdded_fans() == null) {
            tvFensi4.setText(listBean.getTotal_data().getIncome_diamond().getName());
        } else {
            tvFensi4.setText(listBean.getTotal_data().getAdded_fans().getName());
        }
        if (listBean.getTotal_data().getGifts() == null) {
            fensiw2.setText(listBean.getTotal_data().getIncome_gift().getData());
        } else {
            fensiw2.setText(listBean.getTotal_data().getGifts().getData());
        }
        fensiw3.setText(listBean.getTotal_data().getLive_time().getData());
        if (listBean.getTotal_data().getAdded_fans() == null) {
            fensiw4.setText(listBean.getTotal_data().getIncome_diamond().getData());
        } else {
            fensiw4.setText(listBean.getTotal_data().getAdded_fans().getData());
        }
        if (listBean.getTotal_data().getGifts() == null) {
            tvMeili.setText(listBean.getTotal_data().getIncome_gift().getName());
        } else {
            tvMeili.setText(listBean.getTotal_data().getGifts().getName());
        }
        String data1 = "";
        if (listBean.getTotal_data().getGifts() == null) {
            data1 = listBean.getTotal_data().getIncome_gift().getData();
        } else {
            data1 = listBean.getTotal_data().getGifts().getData();
        }

        String str = Integer.parseInt(data1) + "";
        Log.d("Debug", "数量为" + str);

        if (Double.parseDouble(data1) > 100000000) {
            String substring = str.substring(str.length() - 4, str.length());
            String substring1 = str.substring(str.length() - 8, str.length() - 4);
            String substring3 = str.substring(0, str.length() - 8);
            Log.d("Debug", "个数" + substring + "");
            Log.d("Debug", "万数" + substring1);
            Log.d("Debug", "亿数" + substring3);
            tvNumber.setText(substring3);
            tv_number_qianwan.setText(substring1 + ".");
            tvNumberWan.setText(substring + "万");
            tv_yi.setVisibility(View.VISIBLE);
            tv_number_qianwan.setVisibility(View.VISIBLE);
        } else {
            if (Integer.parseInt(data1) > 10000) {
                tv_yi.setVisibility(View.GONE);
                tv_number_qianwan.setVisibility(View.GONE);
                tvNumberWan.setVisibility(View.VISIBLE);
                String substring = str.substring(str.length() - 4, str.length());
                String substring1 = str.substring(0, str.length() - 4);
                tvNumberWan.setText(substring + "万");
                tvNumber.setText(substring1 + ".");
            } else {
                tv_yi.setVisibility(View.GONE);
                tv_number_qianwan.setVisibility(View.GONE);
                tvNumberWan.setVisibility(View.GONE);
                tvNumber.setText(data1);
            }
        }

        //主播身份粉丝增长
        if (listBean.getTotal_data().getAdded_fans() == null) {
            tv6.setText(listBean.getTotal_data().getIncome_diamond().getData());
            tv26.setText(listBean.getTotal_data().getIncome_diamond().getName());
        } else {
            tv6.setText(listBean.getTotal_data().getAdded_fans().getData());
            tv26.setText(listBean.getTotal_data().getAdded_fans().getName());
        }

        //主播开播时长
        tv5.setText(listBean.getTotal_data().getLive_time().getData());
        tv25.setText(listBean.getTotal_data().getLive_time().getName());
//        //主播本月值
        String data = listBean.getTotal_data().getGiftsmouth().getData();
        Double aDouble = PointUtils.KeepPoint1(Double.parseDouble(data) / 10000, 1);
        tv7.setText(aDouble + "万");
        tv27.setText(listBean.getTotal_data().getGiftsmouth().getName());
        if (listBean.getTotal_data().getGifts() == null) {
            s1 = listBean.getTotal_data().getIncome_gift().getName().replace("增长", "") + "排名";
            s2 = listBean.getTotal_data().getIncome_gift().getName();
        } else {
            s1 = listBean.getTotal_data().getGifts().getName().replace("增长", "") + "排名";
            s2 = listBean.getTotal_data().getGifts().getName();
        }

        s3 = listBean.getTotal_data().getLive_time().getName();
        if (listBean.getTotal_data().getAdded_fans() == null) {
            s4 = listBean.getTotal_data().getIncome_diamond().getName();
        } else {
            s4 = listBean.getTotal_data().getAdded_fans().getName();
        }
        //左右点击判断
        if (platfrom_name.indexOf("菠萝街") != -1) {
            try {
                long currentTime = TimeUtil.getCurrentTime();
                int i = 100;
                switch (time_slot) {
                    case "week":
                        String s = TimeUtil.longToString(currentTime, "yyyy-MM-dd");
                        i = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd").compareTo(s);
                        break;
                    case "whole_week":
                        //返回过来和当前周进行对比
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(calendar.getTime());
                        int NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
                        if (NowWeekNumber == Integer.parseInt(ChuandiBean.weekNumber)) {
                            i = 0;
                        } else {
                            i = 100;
                        }
                        break;
                    case "whole_month":
                        Calendar cal = Calendar.getInstance();
                        int month = cal.get(Calendar.MONTH) + 1;
                        if (month == Integer.parseInt(ChuandiBean.month)) {
                            i = 0;
                        } else {
                            i = 100;
                        }
                        break;
                }
                if (time_slot.equals("ziding")) {
                    rlLeft.setVisibility(View.GONE);
                    rlRight.setVisibility(View.GONE);
                } else {
                    if (i == 0) {
                        imRightSanjiao.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_xiangyou));
                        tvRightTime.setTextColor(getResources().getColor(R.color.text_color_little_black));
                        rlRight.setEnabled(false);
                    } else {
                        imRightSanjiao.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_xiangyou2));
                        tvRightTime.setTextColor(getResources().getColor(R.color.white));
                        rlRight.setEnabled(true);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String ourSelData = TimeUtil.getOurSelData(-1);
                //当前时间前一天时间戳
                long l = TimeUtil.convertTimeToLong8(ourSelData) / 1000;
                int i = 100;
                switch (time_slot) {
                    case "week":
                        String s = null;
                        s = TimeUtil.longToString(l, "yyyy-MM-dd");
                        i = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd").compareTo(s);
                        break;
                    case "whole_week":
                        //返回过来和当前周进行对比
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(calendar.getTime());
                        int NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
                        if (NowWeekNumber == Integer.parseInt(ChuandiBean.weekNumber)) {
                            i = 0;
                        } else {
                            i = 100;
                        }
                        break;
                    case "whole_month":
                        Calendar cal = Calendar.getInstance();
                        int month = cal.get(Calendar.MONTH) + 1;
                        if (month == Integer.parseInt(ChuandiBean.month)) {
                            i = 0;
                        } else {
                            i = 100;
                        }
                        break;
                    default:
                        rlLeft.setVisibility(View.GONE);
                        rlRight.setVisibility(View.GONE);
                        break;
                }

                if (time_slot.equals("ziding")) {

                } else {
                    if (i == 0) {
                        imRightSanjiao.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_xiangyou));
                        tvRightTime.setTextColor(getResources().getColor(R.color.text_color_little_black));
                        rlRight.setEnabled(false);
                    } else {
                        imRightSanjiao.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_xiangyou2));
                        tvRightTime.setTextColor(getResources().getColor(R.color.white));
                        rlRight.setEnabled(true);
                    }
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        //点击左右按钮刷新界面
        try {
            switch (time_slot) {
                case "week": {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一天");
                    tvRightTime.setText("后一天");
                    noChart.setVisibility(View.GONE);
                    chartInfo.setVisibility(View.VISIBLE);
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月dd日");
                    tvSelect.setText("日数据" + s);
                    break;
                }
                case "whole_week": {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    noChart.setVisibility(View.GONE);
                    chartInfo.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一周");
                    tvRightTime.setText("后一周");
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
                    tvSelect.setText("周数据" + st + "-" + en);
                    break;
                }
                case "whole_month": {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一月");
                    tvRightTime.setText("后一月");
                    noChart.setVisibility(View.GONE);
                    chartInfo.setVisibility(View.VISIBLE);
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "yyyy年MM月");
                    tvSelect.setText("月数据" + st);
                    break;
                }
                case "ziding": {
                    rlLeft.setVisibility(View.GONE);
                    rlRight.setVisibility(View.GONE);
                    noChart.setVisibility(View.VISIBLE);
                    chartInfo.setVisibility(View.GONE);
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
                    tvSelect.setText("自定义" + st + "-" + en);
                    break;
                }
                default: {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    noChart.setVisibility(View.GONE);
                    chartInfo.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一天");
                    tvRightTime.setText("后一天");
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月dd日");
                    tvSelect.setText("日数据" + s);
                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 左右点击今日实时显示不显示的
     */

    protected void judgeIsShow1() {
        //菠萝街的首次进入获取的是 今日实时数据  其他公会进来获取的是昨日数据
        if (platfrom_name.indexOf("菠萝街") != -1) {
            int i = 100;
            try {
                String now = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
                i = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd").compareTo(now);
                if (i == 0) {
                    tvNowDay.setVisibility(View.VISIBLE);
                } else {
                    tvNowDay.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            tvNowDay.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_send, R.id.iv_back, R.id.ll_select, R.id.rl_left, R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                if (isFromAnchor) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getSupportFragmentManager());
                    mPresenter.anchorDelete(mGuildid, anchor_uid);
                }
                break;
            case R.id.iv_back:
                ChuandiBean.Detailplatfrom_name = "";
                finish();
                break;
            case R.id.ll_select:
                Intent intent = new Intent(AnchoDetailActivity.this, MydateActivity.class);
                intent.putExtra("time_slot", time_slot);
                intent.putExtra("start_date", start_date);
                intent.putExtra("end_date", end_date);
                intent.putExtra("platfrom_name", platfrom_name);
                intent.putExtra("isFromDeatil", true);
                startActivity(intent);
                break;
            case R.id.rl_left:
                try {
                    switch (time_slot) {
                        case "week":
                            String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
                            start_date = TimeUtil.getSpecifiedDayBefore(s) / 1000 + "";
                            end_date = TimeUtil.getSpecifiedDayBefore(s) / 1000 + "";
                            break;
                        case "whole_week": {
                            String st = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
                            String en = TimeUtil.longToString(Long.parseLong(end_date), "yyyy-MM-dd");
                            //获得下个星期的日期
                            String stoldDate = TimeUtil.getOldDate(-7, st);
                            String enoldDate = TimeUtil.getOldDate(-7, en);
                            start_date = TimeUtil.convertTimeToLong10(stoldDate, "yyyy-MM-dd") / 1000 + "";
                            end_date = TimeUtil.convertTimeToLong10(enoldDate, "yyyy-MM-dd") / 1000 + "";
                            ChuandiBean.weekNumber = (Integer.parseInt(ChuandiBean.weekNumber) - 1) + "";
                            break;
                        }
                        case "whole_month": {
                            String year;
                            if (Integer.parseInt(ChuandiBean.month) == 1) {
                                String s1 = TimeUtil.longToString(Long.parseLong(start_date), "yyyy");
                                year = (Integer.parseInt(s1) - 1) + "";
                                ChuandiBean.month = 13 + "";
                            } else {
                                year = TimeUtil.longToString(Long.parseLong(start_date), "yyyy");
                            }
                            ChuandiBean.month = (Integer.parseInt(ChuandiBean.month) - 1) + "";
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String beginStr = year + "-" + ChuandiBean.month + "-01";
                            Date beginTo = null;
                            Date endTo = null;
                            try {
                                beginTo = dateFormat.parse(beginStr);
                                Log.d("Debug", "开始时间" + TimeUtil.dateToString(beginTo, "yyyy-MM-dd"));
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(dateFormat.parse(beginStr));
                                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                                endTo = calendar.getTime();
                                Log.d("Debug", "结束时间" + TimeUtil.dateToString(endTo, "yyyy-MM-dd"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Long st = TimeUtil.dateToLong(beginTo);
                            Long en = TimeUtil.dateToLong(endTo);
                            start_date = st + "";
                            end_date = en + "";
                            break;
                        }
                    }
                    judgeIsShow1();
                    mPresenter.loadData(mGuildid, anchor_uid, time_slot, start_date, end_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rl_right:
                try {
                    switch (time_slot) {
                        case "week":
                            String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
                            start_date = TimeUtil.getSpecifiedDayAfter(s) / 1000 + "";
                            end_date = TimeUtil.getSpecifiedDayAfter(s) / 1000 + "";
                            break;
                        case "whole_week": {
                            String st = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
                            String en = TimeUtil.longToString(Long.parseLong(end_date), "yyyy-MM-dd");
                            //获得下个星期的日期
                            String stoldDate = TimeUtil.getOldDate(7, st);
                            String enoldDate = TimeUtil.getOldDate(7, en);
                            start_date = TimeUtil.convertTimeToLong10(stoldDate, "yyyy-MM-dd") / 1000 + "";
                            end_date = TimeUtil.convertTimeToLong10(stoldDate, "yyyy-MM-dd") / 1000 + "";
                            ChuandiBean.weekNumber = (Integer.parseInt(ChuandiBean.weekNumber) + 1) + "";
                            break;
                        }
                        case "whole_month": {
                            String year;
                            if (Integer.parseInt(ChuandiBean.month) == 12) {
                                String s1 = TimeUtil.longToString(Long.parseLong(start_date), "yyyy");
                                year = (Integer.parseInt(s1) + 1) + "";
                                ChuandiBean.month = 0 + "";
                            } else {
                                year = TimeUtil.longToString(Long.parseLong(start_date), "yyyy");
                            }
                            ChuandiBean.month = (Integer.parseInt(ChuandiBean.month) + 1) + "";
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String beginStr = year + "-" + ChuandiBean.month + "-01";
                            Date beginTo = null;
                            Date endTo = null;
                            try {
                                beginTo = dateFormat.parse(beginStr);
                                Log.d("Debug", "开始时间" + TimeUtil.dateToString(beginTo, "yyyy-MM-dd"));
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(dateFormat.parse(beginStr));
                                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                                endTo = calendar.getTime();
                                Log.d("Debug", "结束时间" + TimeUtil.dateToString(endTo, "yyyy-MM-dd"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Long st = TimeUtil.dateToLong(beginTo);
                            Long en = TimeUtil.dateToLong(endTo);
                            start_date = st + "";
                            end_date = en + "";
                            break;
                        }
                    }
                    judgeIsShow1();
                    mPresenter.loadData(mGuildid, anchor_uid, time_slot, start_date, end_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
        }
    }


    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ChuandiBean.Detailplatfrom_name = "";
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    // =========================下面是折线图的操作

    /**
     * 初始化折线图
     */
    public void initChartView(LineChart mLineChart, int type) {
        mLineChart.setFocusable(true);
        mLineChart.setClickable(true);
        mLineChart.setLongClickable(true);

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
        if (type == 1) {
            leftAxis.setInverted(true);
        } else {
            leftAxis.setInverted(false);
        }
//
        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
//        leftAxis.setGranularity(1f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#40999999"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        leftAxis.setTextSize(10f);            //设置Y轴刻度字体
        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(0f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
        if (type == 1) {
            leftAxis.setStartAtZero(false);        //设置Y轴数值 从零开始
            leftAxis.setMinWidth(1f);
        } else {
            leftAxis.setStartAtZero(true);        //设置Y轴数值 从零开始
            leftAxis.setMinWidth(0f);
        }
//        leftAxis.setMaxWidth(200f);
        Legend l = mLineChart.getLegend();//图例
        l.setEnabled(false);   //是否使用 图例

        //y轴自定义样式
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return "￥" + value;
//            }
//        });

    }


    /**
     * 放数据
     */
    private void setLineChartDate(LineChart mLineChart, final ArrayList<Entry> mValues, final int type) {
//        //自定义x轴标签数据

        mLineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                ChartMarkViewBean data = null;
                switch (ChuandiBean.Detailtime_slot) {
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
        set1.setLineWidth(1f);//设置线宽
        set1.setCircleRadius(2f);//设置焦点圆心的大小
        set1.setHighlightLineWidth(0f);//设置点击交点后显示高亮线宽
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
        if (type == 1) {
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.color.ffffff04);
                set1.setFillDrawable(drawable);//设置范围背景填充
            } else {
                set1.setFillColor(R.color.ffffff04);
            }
        } else {
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.color.doa76c);
                set1.setFillDrawable(drawable);//设置范围背景填充
            } else {
                set1.setFillColor(R.color.doa76c);
            }
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


}

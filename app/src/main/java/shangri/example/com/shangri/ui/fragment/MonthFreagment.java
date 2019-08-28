package shangri.example.com.shangri.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.model.bean.response.WeekAndMonthBean;
import shangri.example.com.shangri.model.bean.response.YearBean;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 消息界面
 * Created by admin on 2017/12/22.
 */


public class MonthFreagment extends BaseLazyFragment<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

    @BindView(R.id.rv_left)
    ListView rvLeft;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rv_right)
    ListView rvRight;
    private Unbinder unbinder;

    private CommonAdapter<YearBean> LeftAdapter;
    private CommonAdapter<WeekAndMonthBean> RightAdapter;

    private List<YearBean> LeftList = new ArrayList<>();
    private List<WeekAndMonthBean> RightlList = new ArrayList<>();


    //当前的年份
    private int Nowyear;
    //用来构造数据的参数  当前年数
    private int ConutilTimel;
    //传过来年份
    private String YearTime;
    //当前月
    private int NowMonthNumber;
    //传过来的月
    private int DateMonthNumber;
    private String platfrom_name = "";

    private boolean isFromDeatil;
    private boolean isFromMoney;



    public static MonthFreagment getInstance(String platfrom_name, String year, int number, Boolean isFromDeatil, Boolean isFromMoney) {
        MonthFreagment fragment = new MonthFreagment();
        Bundle bundle = new Bundle();
        bundle.putString("platfrom_name", platfrom_name);
        bundle.putString("year", year);
        bundle.putInt("number", number);
        bundle.putBoolean("isFromDeatil", isFromDeatil);
        bundle.putBoolean("isFromMoney", isFromMoney);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        platfrom_name = arguments.getString("platfrom_name");
        YearTime = arguments.getString("year");
        DateMonthNumber = arguments.getInt("number");
        isFromDeatil = arguments.getBoolean("isFromDeatil", false);
        isFromMoney = arguments.getBoolean("isFromMoney", false);
        Calendar calendar = Calendar.getInstance();
        //设置为传来的时间
        calendar.setTime(calendar.getTime());
        tvType.setText(YearTime + "年");
        //美国默认一周的开始为周日   设置为一周的开始为周一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //当前的时间
        Nowyear = calendar.get(Calendar.YEAR);
        ConutilTimel = Nowyear;
        Log.d("Debug", "当前的年份是" + Nowyear);
        NowMonthNumber = calendar.get(Calendar.MONTH) + 1;
        if (LeftList.size() > 0) {
            LeftList.clear();
        }
        for (int i = 0; i < 4; i++) {
            YearBean yearBean = new YearBean();
            yearBean.now = ConutilTimel + "";
            ConutilTimel--;
            LeftList.add(yearBean);
        }

        LeftAdapter = new CommonAdapter<YearBean>(getActivity(), null, R.layout.left_item) {
            @Override
            public void convert(ViewHolder helper, final YearBean item) {
                TextView tv_year = helper.getView(R.id.tv_year);
                LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                tv_year.setText(item.getNow());
                if (item.isClick) {
                    ll_parent.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_juxing));
                    tv_year.setTextColor(getActivity().getResources().getColor(R.color.color_d0a76c));
                } else {
                    ll_parent.setBackground(getActivity().getResources().getDrawable(R.color.bg_04ffffff));
                    tv_year.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.setClick(true);
                        tvType.setText(item.getNow() + "年");
                        LeftAdapter.notifyDataSetChanged();
                        RequestData(item.getNow());
                    }
                });
            }
        };
        rvLeft.setAdapter(LeftAdapter);

        RightAdapter = new CommonAdapter<WeekAndMonthBean>(getActivity(), null, R.layout.right_item) {
            @Override
            public void convert(ViewHolder helper, final WeekAndMonthBean item) {
                TextView tv_year = helper.getView(R.id.tv_year);
                ImageView im_select = helper.getView(R.id.im_select);
                if (item.isNow) {
                    helper.setText(R.id.tv_year, item.getMonth() + "月" + "本月");
                } else {
                    helper.setText(R.id.tv_year, item.getMonth() + "月");
                }
                if (item.IsClick) {
                    im_select.setVisibility(View.VISIBLE);
                    tv_year.setTextColor(getActivity().getResources().getColor(R.color.color_d0a76c));
                } else {
                    im_select.setVisibility(View.GONE);
                    tv_year.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < RightlList.size(); i++) {
                            RightlList.get(i).IsClick = false;
                        }
                        item.setClick(true);
                        RightAdapter.notifyDataSetChanged();
                        String year = YearTime;
                        for (int i = 0; i < LeftList.size(); i++) {
                            if (LeftList.get(i).getClick()) {
                                year = LeftList.get(i).getNow();
                            }
                        }
                        Log.d("Debug", "当前的年份是" + year);
                        //获取每个月的开始时间和结束时间
                        String s = year + "-" + item.getMonth();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String beginStr = s + "-01";
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
                        Long start_date = TimeUtil.dateToLong(beginTo);
                        Long end_date = TimeUtil.dateToLong(endTo);

                        if (isFromMoney) {
                            ChuandiBean.Moneystart_date = start_date + "";
                            ChuandiBean.Moneyend_date = end_date + "";
                            ChuandiBean.Moneytime_slot = "whole_month";
                        } else {
                            if (isFromDeatil) {
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = start_date + "";
                                ChuandiBean.Detailend_date = end_date + "";
                                ChuandiBean.Detailtime_slot = "whole_month";
                            } else {
                                ChuandiBean.platfrom_name = platfrom_name;
                                ChuandiBean.start_date = start_date + "";
                                ChuandiBean.end_date = end_date + "";
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = start_date + "";
                                ChuandiBean.Detailend_date = end_date + "";
                                ChuandiBean.time_slot = "whole_month";
                            }
                        }
                        ChuandiBean.month = item.getMonth();
                        getActivity().finish();
                    }
                });
            }

        };
        rvRight.setAdapter(RightAdapter);
        RequestData(YearTime);
    }


    @Override
    protected void loadData() {
    }

    /**
     * 请求数据
     */
    private void RequestData(String year) {
        if (RightlList.size() > 0) {
            RightlList.clear();
        }
        RightlList = getMonthByYear(Integer.parseInt(year));
        if (Nowyear == Integer.parseInt(year)) {
            for (int i = 0; i < RightlList.size(); i++) {
                RightlList.get(i).isNow = RightlList.get(i).getMonth().equals(NowMonthNumber + "");
                RightlList.get(i).IsClick = year.equals(YearTime) && RightlList.get(i).getMonth().equals(DateMonthNumber + "");

            }
        } else {
            for (int i = 0; i < RightlList.size(); i++) {
                RightlList.get(i).isNow = false;
                RightlList.get(i).IsClick = year.equals(YearTime) && RightlList.get(i).getMonth().equals(DateMonthNumber + "");
            }
        }
        for (int i = 0; i < LeftList.size(); i++) {
            LeftList.get(i).isClick = LeftList.get(i).now.equals(year);
        }
        LeftAdapter.setmDatas(LeftList);
        RightAdapter.setmDatas(RightlList);
    }


    private List<WeekAndMonthBean> getMonthByYear(final int year) {
        List<WeekAndMonthBean> result = new ArrayList<>();
        for (int i = 12; i >= 1; i--) {
            if (Nowyear == year) {
                if (i <= NowMonthNumber) {
                    WeekAndMonthBean tempWeek = new WeekAndMonthBean();
                    tempWeek.month = i + "";
                    result.add(tempWeek);
                }

            } else {
                WeekAndMonthBean tempWeek = new WeekAndMonthBean();
                tempWeek.month = i + "";
                result.add(tempWeek);
            }

        }
        return result;

    }


    @Override
    public void onResume() {
        super.onResume();
//        onDataRefresh();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.freagment_weekandmonth_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.freagment_weekandmonth_layout;
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(getActivity(), this);
    }


    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {

    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {

    }

    @Override
    public void consultShare() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void requestFailed(String message) {

    }
}

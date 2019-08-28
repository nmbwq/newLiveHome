package shangri.example.com.shangri.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import shangri.example.com.shangri.NewCalndarSelect.CalendarPickerView;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

import static shangri.example.com.shangri.util.TimeUtil.dateToString;

/**
 * 消息界面
 * Created by admin on 2017/12/22.
 */


public class NewDayFreagment extends BaseLazyFragment<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {


    private String platfrom_name = "";
    private String start_date = "";
    @BindView(R.id.calendar_view)
    CalendarPickerView dayPickerView;
    private Unbinder unbinder;
    //是不是来自详细界面
    private Boolean isFromDeatil;
    //是不是来自详细界面
    private Boolean isFromMoney;
    //用来存放被选中日期
    ArrayList<Date> dates = new ArrayList<>();

    //被选中的日期
    private Date Selectdate = null;

    public static NewDayFreagment getInstance(String platfrom_name, Boolean isFromDeatil, String start_date, Boolean isFromMoney) {
        NewDayFreagment fragment = new NewDayFreagment();
        Bundle bundle = new Bundle();
        bundle.putString("platfrom_name", platfrom_name);
        bundle.putBoolean("isFromDeatil", isFromDeatil);
        bundle.putBoolean("isFromMoney", isFromMoney);
        bundle.putString("start_date", start_date);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();

        platfrom_name = arguments.getString("platfrom_name");
        isFromDeatil = arguments.getBoolean("isFromDeatil", false);
        isFromMoney = arguments.getBoolean("isFromMoney", false);
        start_date = arguments.getString("start_date");
        if (start_date == null) {
            Selectdate = new Date();
        } else {
            if (start_date.length() > 0) {
                try {
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy-MM-dd");
                    Selectdate = TimeUtil.stringToDate(s, "yyyy-MM-dd");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                Selectdate = new Date();
            }
        }


        //设置日历的开始结束时间
        String s = "2015-01-02";
        Date date = null;
        try {
            date = TimeUtil.stringToDate(s, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        dayPickerView.init(date, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(Selectdate);
        dayPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                List<Date> selectedDates = dayPickerView.getSelectedDates();
                String now = null;
                try {
                    if (platfrom_name.indexOf("菠萝街") != -1) {
                        now = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
                    } else {
                        String ourSelData = TimeUtil.getOurSelData(-1);
                        //当前时间前一天时间戳
                        long l = TimeUtil.convertTimeToLong8(ourSelData) / 1000;
                        now = TimeUtil.longToString(l, "yyyy-MM-dd");
                    }
                    String startTime = dateToString(selectedDates.get(0), "yyyy-MM-dd");
                    int i = startTime.compareTo(now);
                    if (i > 0) {
                        if (platfrom_name.indexOf("菠萝街") != -1) {
                            ToastUtil.showShort("请选择包括今天之前的时间");
                        } else {
                            ToastUtil.showShort("请选择包括昨天之前的时间");
                        }

                    } else {
                        Long aLong = TimeUtil.convertTimeToLong10(startTime, "yyyy-MM-dd") / 1000;
                        Log.d("Debug", "赋值时候平台名称为--------" + platfrom_name);
                        if (isFromMoney) {
                            ChuandiBean.Moneystart_date = aLong + "";
                            ChuandiBean.Moneyend_date = aLong + "";
                            ChuandiBean.Moneytime_slot = "week";
                        } else {
                            if (isFromDeatil) {
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = aLong + "";
                                ChuandiBean.Detailend_date = aLong + "";
                                ChuandiBean.Detailtime_slot = "week";
                            } else {
                                ChuandiBean.platfrom_name = platfrom_name;
                                ChuandiBean.start_date = aLong + "";
                                ChuandiBean.end_date = aLong + "";
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = aLong + "";
                                ChuandiBean.Detailend_date = aLong + "";
                                ChuandiBean.time_slot = "week";
                            }
                        }
                        getActivity().finish();
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_freagment_day_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_freagment_day_layout;
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

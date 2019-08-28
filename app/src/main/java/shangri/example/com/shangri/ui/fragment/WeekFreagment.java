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

import java.util.ArrayList;
import java.util.Calendar;
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


public class WeekFreagment extends BaseLazyFragment<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

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
    //传过来的数据
    private String YearTime;
    //当前时间在全年属于第几周     用来判断是不是本月或是本周
    private int NowWeekNumber;
    //传过来的时间在当前属于第几周  用来判断有没有选中
    private int DateWeekNumber;
    private String platfrom_name = "";
    private boolean isFromDeatil;
    private boolean isFromMoney;


    public static WeekFreagment getInstance(String platfrom_name, String year, int number, boolean isFromDeatil, Boolean isFromMoney) {
        WeekFreagment fragment = new WeekFreagment();
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
        DateWeekNumber = arguments.getInt("number");
        platfrom_name = arguments.getString("platfrom_name");
        isFromDeatil = arguments.getBoolean("isFromDeatil", false);
        isFromMoney = arguments.getBoolean("isFromMoney", false);
        YearTime = arguments.getString("year");
        Log.d("Debug", "返回的平台名称为" + platfrom_name);

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
        //传过来的时间在当前属于第几周
        NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);


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


                String StartDate = TimeUtil.getMyDate(item.getStartTime(), "yyyy年MM月dd日", "MM月dd日");
                String EndDate = TimeUtil.getMyDate(item.getEndTime(), "yyyy年MM月dd日", "MM月dd日");
                if (item.isNow) {
                    helper.setText(R.id.tv_year, item.getWeek() + "周" + "(" + StartDate + "-" + EndDate + ")" + "本周");
                } else {
                    helper.setText(R.id.tv_year, item.getWeek() + "周" + "(" + StartDate + "-" + EndDate + ")");
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
                        Long start_date = TimeUtil.convertTimeToLong10(item.getStartTime(), "yyyy年MM月dd日") / 1000;
                        Long end_date = TimeUtil.convertTimeToLong10(item.getEndTime(), "yyyy年MM月dd日") / 1000;

                        if (isFromMoney) {
                            ChuandiBean.Moneystart_date = start_date + "";
                            ChuandiBean.Moneyend_date = end_date + "";
                            ChuandiBean.Moneytime_slot = "whole_week";
                        } else {
                            if (isFromDeatil) {
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = start_date + "";
                                ChuandiBean.Detailend_date = end_date + "";
                                ChuandiBean.Detailtime_slot = "whole_week";
                            } else {
                                ChuandiBean.platfrom_name = platfrom_name;
                                ChuandiBean.start_date = start_date + "";
                                ChuandiBean.end_date = end_date + "";
                                ChuandiBean.Detailplatfrom_name = platfrom_name;
                                ChuandiBean.Detailstart_date = start_date + "";
                                ChuandiBean.Detailend_date = end_date + "";
                                ChuandiBean.time_slot = "whole_week";
                            }
                        }
                        ChuandiBean.weekNumber = item.getWeek();
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
        RightlList = TimeUtil.getWeeksByYear(Integer.parseInt(year));

        if (Nowyear == Integer.parseInt(year)) {
            for (int i = 0; i < RightlList.size(); i++) {
                RightlList.get(i).isNow = RightlList.get(i).getWeek().equals(NowWeekNumber + "");
                RightlList.get(i).IsClick = year.equals(YearTime) && RightlList.get(i).getWeek().equals(DateWeekNumber + "");

            }
        } else {
            for (int i = 0; i < RightlList.size(); i++) {
                RightlList.get(i).isNow = false;
                RightlList.get(i).IsClick = year.equals(YearTime) && RightlList.get(i).getWeek().equals(DateWeekNumber + "");
            }
        }
        for (int i = 0; i < LeftList.size(); i++) {
            LeftList.get(i).isClick = LeftList.get(i).now.equals(year);
        }
        LeftAdapter.setmDatas(LeftList);
        RightAdapter.setmDatas(RightlList);
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

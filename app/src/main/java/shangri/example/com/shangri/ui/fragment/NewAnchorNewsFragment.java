package shangri.example.com.shangri.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.event.ReportRefushDate;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.ManagementChartBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.presenter.AnchorNewsPresenter;
import shangri.example.com.shangri.presenter.view.AnchorNewsView;
import shangri.example.com.shangri.ui.activity.LookDetailActivity;
import shangri.example.com.shangri.ui.activity.MyGuildActivity;
import shangri.example.com.shangri.ui.activity.MydateActivity;
import shangri.example.com.shangri.ui.activity.VerifyBindingActivity;
import shangri.example.com.shangri.ui.dialog.CommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.ChartView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.AndroidInterface.RefushFace;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播Fragment
 * Created by chengaofu on 2017/6/22.
 */
public class NewAnchorNewsFragment extends BaseLazyFragment<AnchorNewsView, AnchorNewsPresenter> implements AnchorNewsView, View.OnClickListener, RefushFace {

    private String mGuildid;
    //    ‘gifts’魅力值,’live_time’直播时长,’added_fans’粉丝增量,’anchor_total’主播人数,’income_gift’礼物收入,’income_diamond’钻石收入
    private String chart_type = "";
    //    默认计算start_date到end_date(week:日,whole_week:周,whole_month:月)
    private String time_slot = "week";
    //公会的名称
    private String platfrom_name = "platfrom_name";

    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    private Unbinder unbinder;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.news_layout)
    LinearLayout newsLayout;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.ll_anchor_info)
    LinearLayout llAnchorInfo;
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
    @BindView(R.id.tv_meili)
    TextView tvMeili;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.ll4)
    LinearLayout ll4;
    @BindView(R.id.ll_gilde_info)
    LinearLayout llGildeInfo;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.ll6)
    LinearLayout ll6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.ll7)
    LinearLayout ll7;
    @BindView(R.id.ll_anchor_biaoge)
    LinearLayout llAnchorBiaoge;
    @BindView(R.id.tv21)
    TextView tv21;
    @BindView(R.id.tv22)
    TextView tv22;
    @BindView(R.id.tv23)
    TextView tv23;
    @BindView(R.id.tv24)
    TextView tv24;
    @BindView(R.id.tv25)
    TextView tv25;
    @BindView(R.id.tv26)
    TextView tv26;
    @BindView(R.id.tv27)
    TextView tv27;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_yi)
    TextView tv_yi;
    @BindView(R.id.tv_number_qianwan)
    TextView tv_number_qianwan;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.im_show)
    ImageView imShow;
    @BindView(R.id.rl1)
    RelativeLayout rl1;


    private ProgressDialogFragment mProgressDialog;
    //用来添加消息头

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;

    // 正常公会 公会审核中 或是是认证失败时候的界面
    @BindView(R.id.new_add_gonghui1)
    RelativeLayout new_add_gonghui1;


    //过期或是审核中的界面面布局文字以及操作
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_content)
    TextView tv_content;


    @BindView(R.id.co_layout)
    CoordinatorLayout co_layout;

    //只有点击请求才弹出加载框  实例化不加载 （解决推送跳转过来加载框不消失问题）
    private boolean isOnclick = false;

    private ChartView cvView;


    private List<String> channelNames = new ArrayList<>();
    private List<Fragment> mNewsFragmentList = new ArrayList<>();

    int index = -1;
    private int time_span = 0;
    private String end_date;
    private String start_date;

    //图标需要的数据
    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",
            "Sun", "Sun", "Sun", "Sun", "Sun", "Sun", "Sun", "Sun",};
    private int numberOfLines = 1; // 线的条数
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 15;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];


    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_fragment_anchor;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_fragment_anchor;
    }

    @Override
    protected AnchorNewsPresenter createPresenter() {
        return new AnchorNewsPresenter(getActivity(), this);
    }

    //快速公会1 非快速公会0
    private String type;
    //    非快速公会 状态 1 认证，2 失效  0 审核中  快速公会 状态 1 认证，3 升级中
    private String states;
    private String table_flag;

    @Override
    protected void initView() {
        CompanyInterfaceUtils.setRefushBack(this);
        if (getArguments() != null) {
            mGuildid = getArguments().getString(Constants.GUILD_ID);
            chart_type = getArguments().getString(Constants.CHART_TYPE);
            platfrom_name = getArguments().getString(Constants.PLATFROM_NAME);
            type = getArguments().getString("type");
            states = getArguments().getString("states");
            table_flag = getArguments().getString("table_flag");
        }
        tvMeili.setTextColor(getActivity().getResources().getColor(R.color.color_d0a76c));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                EventBus.getDefault().post(new ReportRefushDate(position + "", mGuildid, start_date, end_date));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (UserConfig.getInstance().getRole().equals("2")) {
            llGildeInfo.setVisibility(View.VISIBLE);
        } else {
            llGildeInfo.setVisibility(View.GONE);
        }
        if (type.equals("0")) {
            switch (Integer.parseInt(states)) {
                case 0:
                    new_add_gonghui1.setVisibility(View.VISIBLE);
                    tv_next.setVisibility(View.VISIBLE);
                    tv_content.setText("主上，公会正在升级中，我们会在1-2个工作日进行审核，审核通过后即可查看数据。");
                    tv_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), MyGuildActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;
                case 1:
                    new_add_gonghui1.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    new_add_gonghui1.setVisibility(View.VISIBLE);
                    tv_next.setVisibility(View.VISIBLE);
                    tv_content.setText("主上，您的公会" + platfrom_name + "平台验证已过期，小播无法为您提供服务了。请重新进行验证！");
                    tv_next.setText("重新认证");
                    tv_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), VerifyBindingActivity.class);
                            intent.putExtra("guildNameText", platfrom_name);
                            intent.putExtra("guild_id", mGuildid);
                            intent.putExtra("IsFromReport", true);
                            getActivity().startActivity(intent);
                        }
                    });

                    break;

            }
        }
        if (getUserVisibleHint()) {
            judgeIsShow();
        }
    }

    /**
     * 判断今日实时显示不显示
     */
    private void judgeIsShow() {

        //选择时间返回直接去实体类里面的数据
        if (ChuandiBean.platfrom_name.equals(platfrom_name)) {
            start_date = ChuandiBean.start_date;
            end_date = ChuandiBean.end_date;
            time_slot = ChuandiBean.time_slot;
            Log.d("Debug", "返回的开始时间为" + start_date);
            int i = 100;
            String now = null;
            try {
                now = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
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
            //菠萝街的首次进入获取的是 今日实时数据  其他公会进来获取的是昨日数据
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
//                    tvSelect.setText("日数据" + s);
                    tvSelect.setText("" + s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                tvNowDay.setVisibility(View.GONE);
                //获取昨天的时间
                String ourSelData = TimeUtil.getOurSelData(-1);
//                tvSelect.setText("日数据" + ourSelData);
                tvSelect.setText("" + ourSelData);
                start_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
                end_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
            }
            ChuandiBean.Detailplatfrom_name = platfrom_name;
        }
    }

    /**
     * 左右点击今日实时显示不显示的
     */

    private void judgeIsShow1() {
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


    @Override
    protected void loadData() {
        if (getUserVisibleHint()) {
            if (type.equals("0")) {
                switch (Integer.parseInt(states)) {
                    case 1:
                        requstData();
                        break;
                }
            } else {
                requstData();
            }

        }

    }

    /**
     * end_date   结束日期
     * mGuildid   b公会ID
     * time_slot  yesterday:昨日,day:天, week:近7天,month:近30天,whole_week:周,whole_month:月 today:今日实时
     * time_span   默认为0 时间跨度 选择周和月时使用，+1代表多提早一周或一个月
     */
    private void requstData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            co_layout.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            return;
        } else {
            co_layout.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
        judgeIsShow();
        Log.d("aaaa", "当前事周还是-------" + time_slot);

        //现在是无论是会长还是主播身份   左上角主播信息不显示
        if (UserConfig.getInstance().getRole().equals("2")) {
            llAnchorInfo.setVisibility(View.GONE);
            llGildeInfo.setVisibility(View.GONE);
            llAnchorBiaoge.setVisibility(View.VISIBLE);
        } else {
            llAnchorInfo.setVisibility(View.GONE);
            llGildeInfo.setVisibility(View.VISIBLE);
            llAnchorBiaoge.setVisibility(View.GONE);
        }

        if (platfrom_name.indexOf("一直播") != -1) {
            chart_type = "income_gift";
        } else {
            chart_type = "gifts";
        }

        if (isOnclick) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
        }
        mPresenter.topData(end_date, mGuildid, start_date, 1 + "", type + "", table_flag);
        mPresenter.indexMain(end_date, mGuildid, start_date, type, table_flag);
//        //在上面两个接口请求成功以后在进行请求 不造成阻塞
        isOnclick = false;
    }

    /**
     * vipager创建freagment
     *
     * @param resultBean
     */
    @Override
    public void topDta(TopTenBean resultBean) {
        if (channelNames.size() > 0) {
            channelNames.clear();
        }
        if (mNewsFragmentList.size() > 0) {
            mNewsFragmentList.clear();
        }

        TopTenFragment mTopTenFragment;
        if (type.equals("0")) {
            for (int i = 0; i < resultBean.getTop_data().size(); i++) {
                channelNames.add(resultBean.getTop_data().get(i).getName() + "榜");
                mTopTenFragment = new TopTenFragment(i + "", mGuildid, start_date, end_date, platfrom_name, type, table_flag);
                mNewsFragmentList.add(mTopTenFragment);
            }
        } else {
            if (UserConfig.getInstance().getRole().equals("2")) {
                for (int i = 0; i < resultBean.getAnchor_top().size(); i++) {
                    channelNames.add(resultBean.getAnchor_top().get(i).getName() + "榜");
                    mTopTenFragment = new TopTenFragment(i + "", mGuildid, start_date, end_date, platfrom_name, type, table_flag);
                    mNewsFragmentList.add(mTopTenFragment);
                }
                for (int i = 0; i < resultBean.getAnchor_data().size(); i++) {
                    channelNames.add(resultBean.getAnchor_data().get(i).getName());
                    mTopTenFragment = new TopTenFragment(i + 1 + "", mGuildid, start_date, end_date, platfrom_name, type, table_flag);
                    mNewsFragmentList.add(mTopTenFragment);
                }

            } else {
                for (int i = 0; i < resultBean.getQuick_data().size(); i++) {
                    channelNames.add(resultBean.getQuick_data().get(i).getName() + "榜");
                    mTopTenFragment = new TopTenFragment(i + "", mGuildid, start_date, end_date, platfrom_name, type, table_flag);
                    mNewsFragmentList.add(mTopTenFragment);
                }
            }
        }
        MyPagerAdapter mAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(1);
        tabs.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        //代码设置tab的涨肚高度   数据多不慢全屏
        if (UserConfig.getInstance().getRole().equals("2")) {
            if (type.equals("1")) {
//                //就一个数据  靠左显示  让布局有多大显示多大 就可以的
                //自定义tablayout的布局
                //设置可以滑动
                tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
                for (int i = 0; i < mAdapter.getCount(); i++) {

                    TabLayout.Tab tab = tabs.getTabAt(i);//获得每一个tab
                    tab.setCustomView(R.layout.icon_view2);//给每一个tab设置view
                    ImageView im = tab.getCustomView().findViewById(R.id.tabicon);
                    TextView toptext = tab.getCustomView().findViewById(R.id.toptext);
                    if (i == 0) {
                        // 设置第一个tab的TextView是被选择的样式
                        TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);

                        viewById.setSelected(true);//第一个tab被选中
                        toptext.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        viewById.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    }
                    if (channelNames.get(i).contains("土豪")) {
                        im.setVisibility(View.VISIBLE);
                        toptext.setVisibility(View.GONE);
                    } else {
                        im.setVisibility(View.GONE);
                        toptext.setVisibility(View.VISIBLE);
                    }
                    TextView textView = tab.getCustomView().findViewById(R.id.tabtext);
                    textView.setText(channelNames.get(i));//设置tab上的文字

                }
                if (tabs != null) {
                    tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            if (tab.getCustomView() != null) {
                                tab.getCustomView().findViewById(R.id.tabtext).setSelected(true);
                                TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                                viewById.setTextColor(getResources().getColor(R.color.color_d0a76c));
                                TextView toptext = tab.getCustomView().findViewById(R.id.toptext);
                                toptext.setTextColor(getResources().getColor(R.color.color_d0a76c));
                                mViewPager.setCurrentItem(tab.getPosition());
                            }

                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {
                            if (tab.getCustomView() != null) {
                                tab.getCustomView().findViewById(R.id.tabtext).setSelected(false);
                                TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                                viewById.setTextColor(getResources().getColor(R.color.color_999999));
                                TextView toptext = tab.getCustomView().findViewById(R.id.toptext);
                                toptext.setTextColor(getResources().getColor(R.color.color_999999));
                            }
                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                }


            }
        }
        Log.d("Debug", "传过去的时间为" + start_date + "-------------" + end_date);
        EventBus.getDefault().post(new ReportRefushDate(0 + "", mGuildid, start_date, end_date));
    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void Refush() {

    }

    private boolean isYanzheng;

    //重新认证以后的回调方法
    @Override
    public void ReportRefush() {
        isYanzheng = true;
        Log.d("Debug", "到达AndroidReportRefush方法里面============================");

    }

    /**
     * view的是适配器
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mNewsFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return channelNames.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mNewsFragmentList.get(position);
        }
    }


    /**
     * 请求返回
     *
     * @param resultBean
     */

    @Override
    public void indexHead(ManagementDataBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ManagementDataBean mresultBean = resultBean;
        initHeadView(mresultBean);
    }

    /**
     * 上面数据赋值
     *
     * @param bean
     */
    private void initHeadView(ManagementDataBean bean) {
        String data1;
        //上面中间布局
        if (platfrom_name.indexOf("一直播") != -1) {
            data1 = bean.getTotal_data().getIncome_gift().getData();
        } else {
            data1 = bean.getTotal_data().getGifts().getData();
        }

        String str = Double.parseDouble(data1) + "";
//        String str = 1234567892 + "";
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
            if (Double.parseDouble(data1) > 10000) {
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

        if (platfrom_name.indexOf("一直播") != -1) {
            tvMeili.setText(bean.getTotal_data().getIncome_gift().getName().replace("增长", ""));
            //会长身份粉丝增长
            tv3.setText(bean.getTotal_data().getIncome_diamond().getData());
            tv23.setText(bean.getTotal_data().getIncome_diamond().getName());
            //主播身份粉丝增长
            tv6.setText(bean.getTotal_data().getIncome_diamond().getData());
            tv26.setText(bean.getTotal_data().getIncome_diamond().getName());
        } else {
            tvMeili.setText(bean.getTotal_data().getGifts().getName().replace("增长", ""));
            //会长粉丝增长
            tv3.setText(bean.getTotal_data().getAdded_fans().getData());
            tv23.setText(bean.getTotal_data().getAdded_fans().getName());
            //主播身份粉丝增长
            tv6.setText(bean.getTotal_data().getAdded_fans().getData());
            tv26.setText(bean.getTotal_data().getAdded_fans().getName());
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            tvName.setText(bean.getTotal_data().getAnchor_name().getData());
            tvId.setText(bean.getTotal_data().getAnchor_uid().getData());
        } else {
            //会长开播人数
            tv1.setText(bean.getTotal_data().getAnchors().getData());
            tv21.setText(bean.getTotal_data().getAnchors().getName());
        }
        //会长开播时长
        tv2.setText(bean.getTotal_data().getLive_time().getData());
        tv22.setText(bean.getTotal_data().getLive_time().getName());
        //会长本月值
        String data = bean.getTotal_data().getGiftsmouth().getData();
        Double aDouble = PointUtils.KeepPoint1(Double.parseDouble(data) / 10000, 1);
        tv4.setText(aDouble + "万");
        tv24.setText("(" + bean.getTotal_data().getGiftsmouth().getName().replace("本月", "") + ")");

        //主播开播时长
        tv5.setText(bean.getTotal_data().getLive_time().getData());
        tv25.setText(bean.getTotal_data().getLive_time().getName());
        //主播本月值
        tv7.setText(aDouble + "万");
        tv27.setText("(" + bean.getTotal_data().getGiftsmouth().getName().replace("本月", "") + ")");
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
                    case "ziding":
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
                case "week":
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    tv_1.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一天");
                    tvRightTime.setText("后一天");
                    String s = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月dd日");
//                    tvSelect.setText("日数据" + s);
                    tvSelect.setText("" + s);
                    break;
                case "whole_week": {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    tv_1.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一周");
                    tvRightTime.setText("后一周");
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
//                    tvSelect.setText("周数据" + st + "-" + en);
                    tvSelect.setText("" + st + "-" + en);
                    break;
                }
                case "whole_month": {
                    rlLeft.setVisibility(View.VISIBLE);
                    rlRight.setVisibility(View.VISIBLE);
                    tv_1.setVisibility(View.VISIBLE);
                    tvLeftTime.setText("前一月");
                    tvRightTime.setText("后一月");
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "yyyy年MM月");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "yyyy年MM月");
//                    tvSelect.setText("月数据" + st);
                    tvSelect.setText("" + st);
                    break;
                }
                case "ziding": {
                    rlLeft.setVisibility(View.GONE);
                    rlRight.setVisibility(View.GONE);
                    tv_1.setVisibility(View.GONE);
                    String st = TimeUtil.longToString(Long.parseLong(start_date), "MM月dd日");
                    String en = TimeUtil.longToString(Long.parseLong(end_date), "MM月dd日");
//                    tvSelect.setText("自定义" + st + "-" + en);
                    tvSelect.setText("" + st + "-" + en);
                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void ChartBean(ManagementChartBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @OnClick({R.id.rl1, R.id.reload, R.id.im_show, R.id.ll_number, R.id.news_layout, R.id.ll_select, R.id.rl_left, R.id.rl_right, R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
//                requstData();
                break;
            case R.id.rl1:
                break;
            case R.id.news_layout:
                break;
            case R.id.im_show:
                CommontDialog.showHuiBaoDialog(getActivity(), "点击数据可以看增长趋势呢", "");
                break;
            case R.id.ll_number:
                Intent intent1 = new Intent(getActivity(), LookDetailActivity.class);
                intent1.putExtra("mGuildid", mGuildid);
                intent1.putExtra("platfrom_name", platfrom_name);
                intent1.putExtra("tv21", tv21.getText().toString());
                intent1.putExtra("tv22", tv22.getText().toString());
                intent1.putExtra("tv23", tv23.getText().toString());
                intent1.putExtra("tv_meili", tvMeili.getText().toString() + "增长");
                intent1.putExtra("tv25", tv25.getText().toString());
                intent1.putExtra("tv26", tv26.getText().toString());
                ChuandiBean.Moneyend_date = end_date;
                ChuandiBean.Moneystart_date = start_date;
                if (time_slot == null || time_slot.length() == 0) {
                    time_slot = "week";
                }
                ChuandiBean.Moneytime_slot = time_slot;
                startActivity(intent1);
                break;
            case R.id.ll_select:
                Log.d("Debug", "点击传递过去的平台名称为" + platfrom_name);
                Intent intent = new Intent(getActivity(), MydateActivity.class);
                intent.putExtra("platfrom_name", platfrom_name);
                intent.putExtra("time_slot", time_slot);
                intent.putExtra("start_date", start_date);
                intent.putExtra("end_date", end_date);
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
                    mPresenter.indexMain(end_date, mGuildid, start_date, type, table_flag);
                    mPresenter.chart(end_date, mGuildid, start_date, time_slot, chart_type);
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
                            end_date = TimeUtil.convertTimeToLong10(enoldDate, "yyyy-MM-dd") / 1000 + "";
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
                    mPresenter.indexMain(end_date, mGuildid, start_date, type, table_flag);
                    mPresenter.chart(end_date, mGuildid, start_date, time_slot, chart_type);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll1:
                break;
            case R.id.ll2:
                break;
            case R.id.ll3:

            case R.id.ll4:
                break;
            case R.id.ll5:
                break;
            case R.id.ll6:
                break;
            case R.id.ll7:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Debug", "到达new_add_gonghui的onResume中");
//        if (ChuandiBean.platfrom_name.equals(platfrom_name)) {
        if (getUserVisibleHint()) {
            if (isYanzheng) {
                new_add_gonghui1.setVisibility(View.VISIBLE);
                new_add_gonghui1.setEnabled(false);
                tv_next.setVisibility(View.GONE);
                tv_content.setText("主上，公会正在升级中，我们会在1-2个工作日进行审核，审核通过后即可查看数据。");
                isYanzheng = false;
            }
            if (type.equals("0")) {
                switch (Integer.parseInt(states)) {
                    case 1:
                        requstData();
                        break;
                }
            } else {
                requstData();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}

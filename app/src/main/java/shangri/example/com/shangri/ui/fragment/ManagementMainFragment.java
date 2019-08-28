package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.ManagementMainPresenter;
import shangri.example.com.shangri.presenter.view.ManagementMainView;
import shangri.example.com.shangri.ui.activity.AddAnchorActivity;
import shangri.example.com.shangri.ui.activity.BindingAnchorGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingManagerTypectivity;
import shangri.example.com.shangri.ui.activity.SelectPayActivity;
import shangri.example.com.shangri.ui.activity.SerchAnchorActivity;
import shangri.example.com.shangri.ui.activity.UpgradeGuildeActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.RefushFace;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TableUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;


/**
 * * 公报
 * Created by admin on 2017/12/22.
 */


public class ManagementMainFragment extends BaseFragment<ManagementMainView, ManagementMainPresenter> implements ManagementMainView, RefushFace {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.news_layout)
    LinearLayout mNewsLayout;
    @BindView(R.id.titil)
    TextView titil;
    @BindView(R.id.setting_back)
    ImageView setting_back;
    @BindView(R.id.iv_no_guild)
    ImageView newTvLayoutImage;


    @BindView(R.id.left_image)
    ImageView left_image;

    @BindView(R.id.fr_info)
    FrameLayout fr_info;
    @BindView(R.id.activity_guoqi_layout)
    LinearLayout activity_guoqi_layout;
    @BindView(R.id.tv_time)
    TextView tvTime;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private Unbinder unbinder;
    private BaseFragmentAdapter fragmentAdapter;
    private ProgressDialogFragment mProgressDialog;
    //用来判断显示张站位图
    private int Symbol = 1;
    //传递到支付界面的到期时间
    private String endtime = "";
    //1增加到7为true   大于7设置为false  为了区分  在第六页跳转到第七页 不弹popwindow 在第七页点击的时候弹popwindow
    private boolean isTrue = false;
    //跳转到那个界面
    private int position = 0;

    //当前的时间戳
    private long currentTime;

    private boolean isHaveFast;


    @Override
    protected ManagementMainPresenter createPresenter() {
        return new ManagementMainPresenter(getActivity(), this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 注销订阅者
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    protected void initViewsAndEvents() {
        if (UserConfig.getInstance().getRole().equals("1")) {
            titil.setText("公会报表");
            left_image.setVisibility(View.VISIBLE);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            titil.setText("主播报表");
            left_image.setVisibility(View.GONE);
        } else {
            titil.setText("管理员报表");
            left_image.setVisibility(View.GONE);
        }
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getChildFragmentManager());
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            try {
                currentTime = TimeUtil.getCurrentTime();
                Log.d("Debug", "当前的时间戳为" + currentTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mPresenter.Management(currentTime + "", "", currentTime + "");
        }
        // 注册订阅者
        EventBus.getDefault().register(this);
    }

    //跳转到那个界面的标志
    private int PagerSymbol = 0;
    private ManagementDataBean result;


    private List<ManagementDataBean.GuildsBean> GuildList;
    //能搜索主播的公会
    private List<ManagementDataBean.GuildsBean> GuildList1;

    @Override
    public void managementData(ManagementDataBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        result = resultBean;
        if (resultBean.getGuilds().size() > 0) {
            if (UserConfig.getInstance().getRole().equals("2")) {
                Log.d("Debug", "到达发请求界面");
                newTvLayoutImage.setVisibility(View.GONE);
                mPresenter.memberLate();
            } else {
                Log.d("Debug", "没有工会信息");
                newTvLayoutImage.setVisibility(View.GONE);
            }
        }
        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        GuildList = new ArrayList<>();
        GuildList1 = new ArrayList<>();
        for (int i = 0; i < resultBean.getGuilds().size(); i++) {
            ManagementDataBean.GuildsBean guildsBean = resultBean.getGuilds().get(i);
            guildsBean.setType(0 + "");
            GuildList.add(guildsBean);
        }
        if (resultBean.getQuick_guild().size() > 0) {
            for (int i = 0; i < resultBean.getQuick_guild().size(); i++) {
                ManagementDataBean.GuildsBean guildsBean = resultBean.getQuick_guild().get(i);
                guildsBean.setType(1 + "");
                GuildList.add(guildsBean);
            }
        }

//     非快速公会   状态 1 认证，2 失效   快速公会 状态 1 认证，2 失效  只有状态为1 可以到下个主播搜索界面
        for (int i = 0; i < GuildList.size(); i++) {
            ManagementDataBean.GuildsBean guildsBean = GuildList.get(i);
            //非快速公会
            if (guildsBean.getStatus().equals("1")) {
                GuildList1.add(guildsBean);
            }
        }

        isHaveFast = resultBean.getQuick_guild().size() > 0;

        Log.d("Debug", "返回的平台名称为" + ChuandiBean.platfrom_name);
        for (int i = 0; i < GuildList.size(); i++) {
            channelNames.add(GuildList.get(i).getGuild_name());
//            tabs.addTab(tabs.newTab().setCustomView(tab_icon("周边", R.mipmap.baobiao_guoqi)));
            mNewsFragmentList.add(createListFragments(resultBean, i, GuildList.get(i).getType(), GuildList.get(i).getStatus(), GuildList.get(i).getTable_flag()));

        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(0);
        tabs.setupWithViewPager(mViewPager);
        tabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabs, 10, 10);
            }
        });
        if (UserConfig.getInstance().getRole().equals("1")) {
            for (int i = 0; i < fragmentAdapter.getCount(); i++) {
                TabLayout.Tab tab = tabs.getTabAt(i);//获得每一个tab
                assert tab != null;
                tab.setCustomView(R.layout.icon_view);//给每一个tab设置view

                ImageView im = tab.getCustomView().findViewById(R.id.tabicon);
                if (GuildList.get(i).getType().equals("0")) {
                    im.setVisibility(View.VISIBLE);
                    if (GuildList.get(i).getStatus().equals("2")) {
                        im.setImageDrawable(getResources().getDrawable(R.mipmap.gonghui_renzheng_guoqi));
                    } else if (GuildList.get(i).getStatus().equals("1")) {
                        im.setImageDrawable(getResources().getDrawable(R.mipmap.gonghui_renzheng));
                    } else {
                        im.setVisibility(View.GONE);
                    }
                } else {
                    im.setVisibility(View.GONE);
                }
                if (i == 0) {
                    // 设置第一个tab的TextView是被选择的样式
                    TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                    viewById.setSelected(true);//第一个tab被选中
                    viewById.setTextColor(getResources().getColor(R.color.color_d0a76c));
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
                            mViewPager.setCurrentItem(tab.getPosition());
                        }

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        if (tab.getCustomView() != null) {
                            TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                            viewById.setTextColor(getResources().getColor(R.color.color_999999));
                            tab.getCustomView().findViewById(R.id.tabtext).setSelected(false);
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
        }
        if (GlobalApp.Guild_id.length() > 0) {
            for (int i = 0; i < GuildList.size(); i++) {
                if (GuildList.get(i).getGuild_id().equals(GlobalApp.Guild_id)) {
                    position = i;
                    break;
                }
            }
            mViewPager.setCurrentItem(position);
            GlobalApp.Guild_id = "";
        }
//        //为-1时点击全部进来  默认是显示第一个界面  不为-1点击其他地方进来
//        int  poi = getActivity().getIntent().getIntExtra("poi", -1);
//        if (poi >= 0) {
//            //默认显示第一个界面 下面是跳转到置顶界面
//            mViewPager.setCurrentItem(1);
//        }
        TableUtils.dynamicSetTabLayoutMode(tabs);
        setPageChangeListener();
    }

    @Override
    public void companyMy(companyMyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * * 判断是否过期
     *
     * @param shareBean
     */


    @Override
    public void memberLate(timeBean shareBean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        //第一次
        if (shareBean.getExist().equals("1")) {
            try {
                endtime = TimeUtil.longToString(Long.parseLong(shareBean.getMember().getMember_time()), "yyyy.MM.dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //弹窗显示
            showPopupWindowSelect();
            activity_guoqi_layout.setVisibility(View.GONE);
            fr_info.setVisibility(View.VISIBLE);
        } else {
            //获取当前时间
            if (shareBean.getMember().getMember_time() != null && shareBean.getMember().getMember_time().length() > 0) {
                long time = System.currentTimeMillis() / 1000;
                try {
                    endtime = TimeUtil.longToString(Long.parseLong(shareBean.getMember().getMember_time()), "yyyy.MM.dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("Debug", "321654返回的到期时间wei" + Long.parseLong(shareBean.getMember().getMember_time()));
                if (time > Long.parseLong(shareBean.getMember().getMember_time())) {
                    //过期操作
                    activity_guoqi_layout.setVisibility(View.VISIBLE);
                    fr_info.setVisibility(View.GONE);
                    Log.d("Debug", "已过期");
                    tvTime.setText("到期时间：" + endtime);
                } else {
                    activity_guoqi_layout.setVisibility(View.GONE);
                    fr_info.setVisibility(View.VISIBLE);
                    Log.d("Debug", "没有过期");
                }
            } else {
                Log.d("Debug", "没有返回当前时间");
            }

        }

    }

    private void setPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private NewAnchorNewsFragment createListFragments(ManagementDataBean resultBean, int i, String type, String states, String table_flag) {
        NewAnchorNewsFragment fragment = new NewAnchorNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.GUILD_ID, GuildList.get(i).getGuild_id());
        String id = "added_fans";
        if (resultBean.getTotal_data().getGifts() == null) {
            id = "income_gift";
        } else {
            id = "gifts";
        }
        bundle.putString(Constants.CHART_TYPE, id);
        bundle.putString(Constants.PLATFROM_NAME, GuildList.get(i).getGuild_name());
        bundle.putString("type", type);
        bundle.putString("states", states);
        bundle.putString("table_flag", table_flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (message.contains("1001") || message.contains("没有公会")) {
            newTvLayoutImage.setVisibility(View.VISIBLE);
            selectPager();
        }

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_management_main;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_management_main;
    }

    public void onRefresh() {
        Log.d("Debug", "刷新操作");
        for (int i = 0; i < GuildList.size(); i++) {
            if (ChuandiBean.platfrom_name.equals(GuildList.get(i).getGuild_name())) {
                PagerSymbol = i;
            }
        }
        Log.d("Debug", "返回的PagerSymbol-----------" + PagerSymbol);
        mViewPager.setCurrentItem(PagerSymbol);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

        } else {  // 在最前端显示 相当于调用了onResume();
//            isTrue = true;
//            mPresenter.Management(currentTime + "", "", currentTime + "");
//            Log.d("aaaaa", "在最前端显示 相当于调用了onResume()");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BrowseEventBean event) {
        Log.d("Debug", "到达报表的BrowseEventBean中");
        mPresenter.Management(currentTime + "", "", currentTime + "");
    }


    @Override
    public void Refush() {
        mPresenter.Management(currentTime + "", "", currentTime + "");
    }

    @Override
    public void ReportRefush() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.left_image, R.id.right_image, R.id.tv_pay, R.id.reload, R.id.iv_no_guild})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_image:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    Intent intent = new Intent(getActivity(), SerchAnchorActivity.class);
                    intent.putExtra("GuildList", (Serializable) GuildList1);
                    startActivity(intent);
                }
                break;
            case R.id.iv_no_guild:
                if (PointUtils.isFastClick1()) {
                    if (Symbol < 7) {
                        isTrue = true;
                        Symbol++;
                    } else {
                        isTrue = false;
                        Symbol = 7;
                    }
                    selectPager();
                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.Management(currentTime + "", "", currentTime + "");
                    }
                }
                break;
            case R.id.right_image:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    if (isHaveFast) {
                        showPopupWindowSevenDays();
                    } else {
                        if (PointUtils.isFastClick()) {
                            ActivityUtils.startActivity(getActivity(), BindingGuildeTypectivity.class);
                        }
                    }
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    if (PointUtils.isFastClick()) {
                        ActivityUtils.startActivity(getActivity(), BindingAnchorGuildeTypectivity.class);
                    }
                } else {
                    //管理员跳转
                    ActivityUtils.startActivity(getActivity(), BindingManagerTypectivity.class);
                }

                break;
            case R.id.tv_pay:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), SelectPayActivity.class).putExtra("time", endtime));
                }
                break;
        }
    }

    /**
     * * 根据不同页面显示不同报表 模拟图
     */


    private void selectPager() {
        if (UserConfig.getInstance().getRole().equals("2")) {
            switch (Symbol) {
                case 1:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_1));
                    break;
                case 2:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_2));
                    break;
                case 3:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_3));
                    break;
                case 4:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_4));
                    break;
                case 5:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_5));
                    break;
                case 6:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhu_6));
                    break;
                case 7:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.moni));
                    if (isTrue) {

                    } else {
                        showPopupWindow();
                    }
                    break;
            }
        } else {
            switch (Symbol) {
                case 1:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_1));
                    break;
                case 2:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_2));
                    break;
                case 3:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_3));
                    break;
                case 4:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_4));
                    break;
                case 5:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_5));
                    break;
                case 6:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.hui_6));
                    break;
                case 7:
                    newTvLayoutImage.setImageDrawable(getResources().getDrawable(R.mipmap.moni));
                    if (isTrue) {

                    } else {
                        showPopupWindow();
                    }
                    break;
            }
        }
    }

    private PopupWindow mPopWindowSelect;

    /**
     * * 获得七天的弹窗
     */
    private void showPopupWindowSelect() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_rl_select2, null);
        mPopWindowSelect = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelect.setContentView(contentView);
//        //设置各个控件的点击响应
        ImageView im_close = contentView.findViewById(R.id.im_close);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        TextView tv_time = contentView.findViewById(R.id.tv_time);
        tv_time.setText("到期时间：" + endtime);
//        //显示PopupWindow
        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindowSelect.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow;


    /**
     * * 模拟报表弹出加入不加入公会选择弹窗
     */


    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_content.setText("绑定公会后就可以看到您公会的专属数据哦！");
            tv_next.setText("好,绑定公会");
        } else {
            tv_content.setText("加入公会后就可以看到您公会的专属数据哦！");
            tv_next.setText("好,加入公会");
        }
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserConfig.getInstance().getRole().equals("1")) {
                    //会长跳转
                    ActivityUtils.startActivity(getActivity(), BindingGuildeTypectivity.class);
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    //主播跳转
                    ActivityUtils.startActivity(getActivity(), BindingAnchorGuildeTypectivity.class);
                } else {
                    //管理员跳转
                    ActivityUtils.startActivity(getActivity(), BindingManagerTypectivity.class);
                }
                isTrue = false;
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private PopupWindow mPopWindowSelectdays;


    /**
     * 获得七天的弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_rl_select5, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应

//        添加公会  公会升级  添加主播
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        RelativeLayout rl2 = contentView.findViewById(R.id.rl2);
        RelativeLayout rl3 = contentView.findViewById(R.id.rl3);

        RelativeLayout rl_parent = contentView.findViewById(R.id.rl_parent);

        final ImageView im1 = contentView.findViewById(R.id.im1);
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final ImageView im2 = contentView.findViewById(R.id.im2);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        final ImageView im3 = contentView.findViewById(R.id.im3);
        final TextView tv3 = contentView.findViewById(R.id.tv3);


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv1.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv1.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_1));
                        tv1.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji1));
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji1));
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji2));
                        tv2.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_1));
                        tv3.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });

        rl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });


        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //会长跳转
                ActivityUtils.startActivity(getActivity(), BindingGuildeTypectivity.class);
                mPopWindowSelectdays.dismiss();
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpgradeGuildeActivity.class);
                intent.putExtra("type", "3");
                intent.putExtra("guildNameText", "");
                intent.putExtra("guild_id", "");
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAnchorActivity.class);
                intent.putExtra("guildNameText", "");
                intent.putExtra("guild_id", "");
                intent.putExtra("type", 3 + "");
                startActivity(intent);
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }


}

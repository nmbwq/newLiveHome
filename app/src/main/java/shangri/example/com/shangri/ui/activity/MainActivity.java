package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loveplusplus.update.UpdateChecker;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.response.ChoiceAnchorsBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.ChoiceAnchorsPresenter;
import shangri.example.com.shangri.presenter.view.ChoiceAnchorsView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.MakeMoneyFreagment;
import shangri.example.com.shangri.ui.fragment.ManagementMainFragment;
import shangri.example.com.shangri.ui.fragment.NewBossFragment;
import shangri.example.com.shangri.ui.fragment.NewMineFragment1;
import shangri.example.com.shangri.ui.fragment.NewsFragment;
import shangri.example.com.shangri.ui.fragment.ReportFragment;
import shangri.example.com.shangri.ui.view.CircleProgressImageView;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.StartActivityUtils;
import shangri.example.com.shangri.util.StatusBarUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.WelfareDialog;

/**
 * Created by Administrator on 2018/1/2.
 */

public class MainActivity extends BaseActivity<ChoiceAnchorsView, ChoiceAnchorsPresenter> implements ChoiceAnchorsView {
    @BindView(R.id.nav_news_img)
    ImageView mNavNewsImg;
    @BindView(R.id.nav_communique_img)
    ImageView mNav_communique_img;
    @BindView(R.id.nav_management_img)
    ImageView mNav_management_img;
    @BindView(R.id.nav_mine_img)
    ImageView mNavMineImg;
    @BindView(R.id.nav_news_text)
    TextView mNavNewsText;
    @BindView(R.id.nav_communique_text)
    TextView mNav_communique_text;
    @BindView(R.id.nav_management_text)
    TextView mNav_management_text;
    @BindView(R.id.nav_mine_text)
    TextView mNavMineText;

    @BindView(R.id.nav_boss_img)
    ImageView navBossImg;
    @BindView(R.id.nav_boss_text)
    TextView navBossText;
    @BindView(R.id.rl10)
    RelativeLayout rl10;
    @BindView(R.id.nav_boss_tab)
    RelativeLayout navBossTab;
    @BindView(R.id.tv_no_readmessage_number)
    TextView tv_no_readmessage_number;
    @BindView(R.id.nav_news_tab)
    RelativeLayout navNewsTab;
    @BindView(R.id.rl9)
    RelativeLayout rl9;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.nav_communique_tab)
    RelativeLayout nav_communique_tab;


    @BindView(R.id.im_welfare)
    ImageView imWelfare;


    @BindView(R.id.ll_musice_info)
    RelativeLayout ll_musice_info;
    @BindView(R.id.im_close)
    ImageView im_close;
    @BindView(R.id.im_image)
    ImageView im_image;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.iv)
    CircleProgressImageView circleProgressView;


    private ProgressDialogFragment mProgressDialog;
    //手机连续按返回键两次的间隔时间
    private long mExitTime;
    private static final String[] FRAGMENT_TAG = {"mBossFragment", "mNewsFragment", "mAnchorMakeMoneyFragment",
            "mMineFragment"};
    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private int selindex = 0;

    int type = 0;
    //广告业传过来的web页面链接
    String url = "";
    //判断是否在广告页面过来的
    boolean IsFromAd;

    private NewsFragment mNewsFragment;
    private NewBossFragment mNewBossFragment;
    private ManagementMainFragment mManagementMainFragment;
    private NewMineFragment1 mMineFragment;
    //报表模块
    private ReportFragment mReportFragment;
    //赚钱模块
    private MakeMoneyFreagment mAnchorMakeMoneyFragment;


    public static MainActivity mainActivity;
    //    注册好礼领取弹窗
    AlertDialog dialog1;
    //    注册好礼提现弹窗
    AlertDialog dialog2;
    //    主播升级弹窗
    AlertDialog dialog3;
    //    登录是否弹出赠送好礼
    AlertDialog dialog5;
    //    全局弹窗  小播推荐
    AlertDialog dialog6;

    AnimationDrawable animation1;
    //音频播放时间
    String SumNumberTime = "";
    String NowTime = "";

    public MainActivity() {
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isShowImage();
        UpdateChecker.checkForDialog(this);

        if (UserConfig.getInstance().getToken().length() > 0) {


//            //主播升级接口请求
//            if (UserConfig.getInstance().getRole().equals("2")) {
//                mPresenter.upgradeAlert();
//            }
            //登录是否弹出赠送好礼
            if (UserConfig.getInstance().getRole().equals("1")) {
                mPresenter.popGives();
                //现在是只有会长弹注册好礼弹窗（200波豆） 主播不弹的（之前主播也是弹的（500波币） ）
                mPresenter.invitationAlert("");
            }
        }

        if (UserConfig.getInstance().getRole().equals("1")) {
            nav_communique_tab.setVisibility(View.GONE);
        } else {
            nav_communique_tab.setVisibility(View.VISIBLE);
        }
        circleProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Debug", "进度条点击事件" + AudioPlayActivity.isplay);
                //向音频界面发推送
                if (AudioPlayActivity.isplay) {
                    circleProgressView.pause();
                    im_close.setVisibility(View.VISIBLE);
                    CompanyInterfaceUtils.videoFace.stop();
                } else {
//                    Log.d("Debug", "总时间为" + SumNumberTime);
//                    Log.d("Debug", "进度事件为" + NowTime);
                    if (SumNumberTime.length() > 0 && NowTime.length() > 0) {
                        circleProgressView.setDuration(Integer.parseInt(SumNumberTime));
                        circleProgressView.play(Integer.parseInt(NowTime));
                    }
                    im_close.setVisibility(View.GONE);
                    CompanyInterfaceUtils.videoFace.startOrPause();
                }

            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        }, 300, 1000);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            if (msg.what == 0) {
                //这里可以进行UI操作，如Toast，Dialog等
                if (imWelfare != null) {
                    imWelfare.setImageResource(R.drawable.anim_show_gift);
                    animation1 = (AnimationDrawable) imWelfare.getDrawable();
                    animation1.start();
                }
            }
        }
    };

    /**
     * 主播端领取福利按钮（我的页面隐藏,剩下其他三个模块显示）
     *
     * @param
     */
    public void isShowImage() {
        if (UserConfig.getInstance().getRole().equals("2")) {
            //主播端 前三个底导显示领现金的动画   现在去除掉这个功能（直接隐藏）
//            imWelfare.setVisibility(View.VISIBLE);
            imWelfare.setVisibility(View.GONE);
        } else {
            imWelfare.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.nav_news_tab, R.id.nav_mine_tab, R.id.nav_boss_tab,
            R.id.nav_communique_tab, R.id.nav_management_tab})
    public void onClick(View v) {
        switch (v.getId()) {
            //主播（招聘）
            case R.id.nav_boss_tab:
                setSelect(0);
                isShowImage();
                mPresenter.oncilckBottom("1");
                break;
            //看看
            case R.id.nav_news_tab:
                Log.d("Debug", "看看模块点击事件");
                setSelect(1);
                isShowImage();
                mPresenter.oncilckBottom("2");
                break;
            //会报（第五底导） (没登录点击显示的招聘模块)
            case R.id.nav_communique_tab:
                Log.d("Debug", "赚钱模块点击事件");
                if (UserConfig.getInstance().getToken().length() == 0) {
                    StartActivityUtils.startActivity();
                    setSelect(0);
                } else {
                    if (UserConfig.getInstance().getRole().length() == 0 || UserConfig.getInstance().getRole().equals("0")) {
                        StartActivityUtils.startSelectRoleActivity(this);
                        Intent intent = new Intent(this, SoftwareActivity.class);
                        startActivity(intent);
                    } else {
                        setSelect(2);
                    }
                }
                isShowImage();
                mPresenter.oncilckBottom("3");
                break;
//            //公报
//            case R.id.nav_management_tab:
//                setSelect(3);
//                break;
            //我的界面 (没登录点击显示的招聘模块)
            case R.id.nav_mine_tab:
                if (UserConfig.getInstance().getToken().length() == 0) {
                    StartActivityUtils.startActivity();
                    setSelect(0);
                    isShowImage();
                } else {
                    if (UserConfig.getInstance().getRole().length() == 0 || UserConfig.getInstance().getRole().equals("0")) {
                        Intent intent = new Intent(this, SoftwareActivity.class);
                        startActivity(intent);
                    } else {
                        setSelect(3);
                    }
                    imWelfare.setVisibility(View.GONE);
                }
                mPresenter.oncilckBottom("4");
                break;
        }
    }

    public void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragments(ft);
        resetImgs();
        selindex = i;
        switch (i) {
            case 0:
                if (mNewBossFragment == null) {
                    mNewBossFragment = new NewBossFragment();
                    ft.add(R.id.main_content, mNewBossFragment, FRAGMENT_TAG[i]).commit();
                } else {
                    ft.show(mNewBossFragment);
                    ft.commitAllowingStateLoss();
                }
                navBossImg.setImageResource(R.mipmap.zhaopin_2);
//                navBossImg.setImageResource(R.mipmap.zhaopinx);
                navBossText.setTextColor(ContextCompat.getColor(this, R.color.text_color_light_yellow));
                break;
            case 1:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    ft.add(R.id.main_content, mNewsFragment, FRAGMENT_TAG[i]).commit();
                } else {
                    ft.show(mNewsFragment);
                    ft.commitAllowingStateLoss();
                }
                mNavNewsImg.setImageResource(R.mipmap.icon_kankani);

//                mNavNewsImg.setImageResource(R.mipmap.kankanx);

                mNavNewsText.setTextColor(ContextCompat.getColor(this, R.color.text_color_light_yellow));
                break;
            case 2:
                if (mAnchorMakeMoneyFragment == null) {
                    mAnchorMakeMoneyFragment = new MakeMoneyFreagment();
                    ft.add(R.id.main_content, mAnchorMakeMoneyFragment, FRAGMENT_TAG[i]).commit();
                } else {
                    ft.show(mAnchorMakeMoneyFragment);
                    ft.commitAllowingStateLoss();
                }
                mNav_communique_img.setImageResource(R.mipmap.zqx);

//                mNav_communique_img.setImageResource(R.mipmap.baobiaox);

                mNav_communique_text.setTextColor(ContextCompat.getColor(this, R.color.text_color_light_yellow));
                break;
//            case 3:
//                if (mManagementMainFragment == null) {
//                    mManagementMainFragment = new ManagementMainFragment();
//                    ft.add(R.id.main_content, mManagementMainFragment, FRAGMENT_TAG[i]).commit();
//                } else {
//                    ft.show(mManagementMainFragment);
//                    ft.commitAllowingStateLoss();
//                }
//                mNav_management_img.setImageResource(R.mipmap.icon_gongbao);
//                mNav_management_text.setTextColor(ContextCompat.getColor(this, R.color.text_color_light_yellow));
//                break;
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = new NewMineFragment1();
                    ft.add(R.id.main_content, mMineFragment, FRAGMENT_TAG[i]).commit();
                } else {
                    ft.show(mMineFragment);
                    ft.commitAllowingStateLoss();
                }
                mNavMineImg.setImageResource(R.mipmap.icon_wode2);
//                mNavMineImg.setImageResource(R.mipmap.wodex);

                mNavMineText.setTextColor(ContextCompat.getColor(this, R.color.text_color_light_yellow));
                break;
        }
//
    }

    private void hideFragments(FragmentTransaction ft) {
        if (mNewsFragment != null) {
            ft.hide(mNewsFragment);
        }
        if (mNewBossFragment != null) {
            ft.hide(mNewBossFragment);
        }
//        if (mManagementMainFragment != null) {
//            ft.hide(mManagementMainFragment);
//        }
        if (mMineFragment != null) {
            ft.hide(mMineFragment);
        }
//        if (mReportFragment != null) {
//            ft.hide(mReportFragment);
//        }

        if (mAnchorMakeMoneyFragment != null) {
            ft.hide(mAnchorMakeMoneyFragment);
        }
    }

    private void resetImgs() {
        mNavNewsImg.setImageResource(R.mipmap.icon_kankani2);
        navBossImg.setImageResource(R.mipmap.zhaopin_1);
        mNav_communique_img.setImageResource(R.mipmap.zq);
//        mNav_management_img.setImageResource(R.mipmap.icon_gongbao2);
        mNavMineImg.setImageResource(R.mipmap.icon_wode);

/*        mNavNewsImg.setImageResource(R.mipmap.kankan);
        navBossImg.setImageResource(R.mipmap.zhaopin);
        mNav_communique_img.setImageResource(R.mipmap.baobiao);
//        mNav_management_img.setImageResource(R.mipmap.icon_gongbao2);
        mNavMineImg.setImageResource(R.mipmap.wode);*/

        mNavNewsText.setTextColor(ContextCompat.getColor(this, R.color.white));
        navBossText.setTextColor(ContextCompat.getColor(this, R.color.white));
        mNav_communique_text.setTextColor(ContextCompat.getColor(this, R.color.white));
//        mNav_management_text.setTextColor(ContextCompat.getColor(this, R.color.white));
        mNavMineText.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PRV_SELINDEX, selindex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销订阅者
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_main;
    }

    //用来判断管理的气泡显示不显示
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FourshowEventBean baseEvent) {
        if (baseEvent.isIsfromMakeMoney()) {
//            主播端   是否来自第三底导 赚钱 点击简历投递去完成 跳转到招聘的界面
            setSelect(0);
        } else {
            if (baseEvent.isFromVideo) {
                if (baseEvent.stop) {
                    ll_musice_info.setVisibility(View.GONE);
                    im_close.setVisibility(View.GONE);
//                Log.d("Debug", "到达音乐停止");
                } else {
//                Log.d("Debug", "到达音乐展示");
                    ll_musice_info.setVisibility(View.VISIBLE);
                    im_close.setVisibility(View.VISIBLE);
                    Glide.with(this)
                            .load(baseEvent.image)
                            .placeholder(R.mipmap.gongzuo_hao)
                            .transform(new CornersTransform(this, 10))
                            .crossFade()
                            .into(im_image);
                    tv_title.setText(baseEvent.name + "");
//                Log.d("Debug", "发布时间为" + baseEvent.time);
                    try {
                        String s = TimeUtil.longToString(Long.parseLong(baseEvent.time), "yyyy-MM-dd");
                        tv_time.setText(s + "");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (baseEvent.SumNumberTime.length() > 0) {
                        SumNumberTime = baseEvent.SumNumberTime;
                    }
                    if (baseEvent.NowTime.length() > 0) {
                        NowTime = baseEvent.NowTime;
                    }
                    circleProgressView.setDuration(Integer.parseInt(SumNumberTime));
                    circleProgressView.play(Integer.parseInt(NowTime));
//                tv_time.setText(baseEvent.time + "");
                }
            } else {
                //是否来自极光推送  状态为10  弹出小播推荐全局弹窗
                if (baseEvent.isFromTuisongAllDialog()) {
                    mPresenter.alertSeting();
                    dialog6 = WelfareDialog.WelfareDialog6(MainActivity.this, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //url代表跳转消息的类型  跳转的第几个
                            Intent intent_msg = new Intent(MainActivity.this, MessagesActivityNew.class);
                            intent_msg.putExtra("url", "1");
                            startActivity(intent_msg);
                            dialog6.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog6.dismiss();
                        }
                    });
                } else {
                    //我的页面消息查看已读或是未读
                    if (baseEvent.getFromMine()) {
                        Log.d("Debug", "MainActivity接受到eventbus" + baseEvent.getIsshow());
                        mPresenter.myNoread();
                    }
                }
            }


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "到达onresume中");
//        if (IsFromAd == false) {
//            type = getIntent().getIntExtra("type", 4);
//            setSelect(type);
//        }
//        if (mManagementMainFragment != null) {
//            if (getVisibleFragment() == mManagementMainFragment) {
//                mManagementMainFragment.onRefresh();
//            }
//        }
        if (mNewsFragment != null) {
            if (getVisibleFragment() == mNewsFragment) {
                mNewsFragment.onRefresh();

            }
        }
    }

    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出直播之家", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                MobclickAgent.onKillProcess(MainActivity.this);
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected ChoiceAnchorsPresenter createPresenter() {
        return new ChoiceAnchorsPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        mainActivity = this;
        // 注册订阅者
//        EventBus.getDefault().register(this);
        Unbinder mUnbinder = ButterKnife.bind(this);
        //设置状态栏
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.StatusBarLightMode(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();
//        if (savedInstanceState != null) {
//            //读取上一次界面Save的时候tab选中的状态
//            selindex = savedInstanceState.getInt(PRV_SELINDEX, selindex);
//            mNewsFragment = (NewsFragment) fm.findFragmentByTag(FRAGMENT_TAG[0]);
//            mOmmuniqueMainFragment = (OmmuniqueMainFragment) fm.findFragmentByTag(FRAGMENT_TAG[1]);
//            mManagementMainFragment = (ManagementMainFragment) fm.findFragmentByTag(FRAGMENT_TAG[2]);
//            mMineFragment = (NewMineFragment) fm.findFragmentByTag(FRAGMENT_TAG[3]);
//            mReportFragment = (ReportFragment) fm.findFragmentByTag(FRAGMENT_TAG[4]);
//        }
        IsFromAd = getIntent().getBooleanExtra("IsFromAd", false);
        if (IsFromAd) {
            // 广告牌页面返回的参数落地页面 1看看2报表3管理4会报（和项目的位置有冲突需要手动替换位置）
            //type 默认数据 就是默认到达的界面  主播进来默认是主播招聘界面   会长管理员进来默认是汇报界面
            if (UserConfig.getInstance().getRole().equals("2")) {
                type = getIntent().getIntExtra("type", 0);
            } else {
                type = getIntent().getIntExtra("type", 0);
            }
            Log.d("Debug", "没赋值type值为" + type);
            switch (type) {
                case 1:
                    type = 0;
                    break;
                case 2:
                    type = 0;
                    break;
                case 3:
                    type = 0;
                    break;
                case 4:
                    type = 2;
                    break;
            }
            url = getIntent().getStringExtra("url") + "";
            Log.d("Debug", "赋值以后的type值" + type);
            setSelect(type);
            if (url.length() > 0 && url != null) {
                startActivity(new Intent(MainActivity.this, symbolWebView.class).putExtra("url", url));
                url = "";
            }
        } else {
            //发极光推送过来的 和项目位置没有冲突
            if (UserConfig.getInstance().getRole().equals("2")) {
                type = getIntent().getIntExtra("type", 0);
            } else {
                type = getIntent().getIntExtra("type", 0);
            }
            setSelect(type);
            if (GlobalApp.htmlUrl.length() > 0) {
                startActivity(new Intent(MainActivity.this, symbolWebView.class).putExtra("url", GlobalApp.htmlUrl));
            }
        }
        if (UserConfig.getInstance().getToken().length() > 0) {
            mPresenter.noticePop();
            mPresenter.myNoread();
        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void listAnchorsData(ChoiceAnchorsBean resultBean) {

    }

    @Override
    public void noticePop(NoticesResponseBean resultBean) {
        if (resultBean.getNotices().size() > 0) {
            notices.clear();
            notices = resultBean.getNotices();
            count = notices.size() - 1;
            SharedPreferences sharedPreferences = getSharedPreferences("getnowTime", Context.MODE_PRIVATE); //私有数据
            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
            String date = sharedPreferences.getString("date", "");
            Log.d("Debug", "之前预存的时间" + date);
            if (date.length() > 0) {
                try {
                    boolean sameDayOfMillis = TimeUtil.isSameDayOfMillis(Long.parseLong(date), TimeUtil.getCurrentTime());
                    Log.d("Debug", "到达这里" + sameDayOfMillis);
                    if (sameDayOfMillis) {
//                        ToastUtil.showShort("同一天");
//                        popupHandler.sendEmptyMessageDelayed(0, 1000);
                    } else {
//                        ToastUtil.showShort("不是同一天");
                        popupHandler.sendEmptyMessageDelayed(0, 1000);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                popupHandler.sendEmptyMessageDelayed(0, 1000);
            }
            try {
                editor.putString("date", TimeUtil.getCurrentTime() + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            editor.commit();//提交修改
        }
    }

    @Override
    public void myNoread(NoticesResponseBean resultBean) {
        if (resultBean.getCount() > 0) {
            tv_no_readmessage_number.setVisibility(View.VISIBLE);
        } else {
            tv_no_readmessage_number.setVisibility(View.GONE);
        }
    }

    /**
     * 弹出福利推送提示 resultBean.getIs_alert() != null 获取是否已弹功能  resultBean.getIs_alert() == null 设置已弹功能
     *
     * @param resultBean
     */
    @Override
    public void invitationAlert(NoticesResponseBean resultBean) {
        //获取是否已弹
        if (resultBean.getIs_alert() != null) {
//            是否已弹出 1已弹出 0未弹出
            if (resultBean.getIs_alert().equals("0")) {
                dialog1 = WelfareDialog.WelfareDialog1(MainActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.invitationAlert("1");
                        dialog1.dismiss();
                    }
                });
            }
        } else {/*设置已弹*/
            Log.d("Debug", "已领取接口调用");
            dialog2 = WelfareDialog.WelfareDialog2(MainActivity.this, resultBean, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                        startActivity(new Intent(MainActivity.this, BoBiActivity.class));
                    } else {
                        startActivity(new Intent(MainActivity.this, VirtualCoinActivity.class));
                    }
                    dialog2.dismiss();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.dismiss();
                }
            });
        }
    }

    @Override
    public void upgradeAlert(upgradeAlertBean resultBean) {
        if (resultBean.getIs_upgrade() == 1) {
            dialog3 = WelfareDialog.WelfareDialog3(MainActivity.this, resultBean.getLevel(), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, UpgradeActivity1.class));
                    dialog3.dismiss();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog3.dismiss();
                }
            });
        }
    }

    //    登录是否弹出赠送好礼
    @Override
    public void popGives(upgradeAlertBean resultBean) {
        if (resultBean.getIs_pop() == 1) {
            dialog5 = WelfareDialog.WelfareDialog5(MainActivity.this, resultBean.getGives() + "", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(MainActivity.this.getSupportFragmentManager());
                    mPresenter.givesGet("1");
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(MainActivity.this.getSupportFragmentManager());
                    mPresenter.givesGet("0");
                }
            });
        }
    }

    @Override
    public void givesGet() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        dialog5.dismiss();
    }

    @Override
    public void oncilckBottom() {

    }

    private Handler popupHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.d("Debug", "到达展示popwindow这里");
                    showPopupWindow();
                    break;
            }
        }

    };
    private PopupWindow mPopWindow;
    List<NoticesResponseBean.NoticesBean> notices = new ArrayList<>();
    //确定是展示那张图片的参数
    int count;
    //pop的链接地址
    String popurl;

    //跳转到位置 	跳转到（conn_url优先） 1报表 2管理 3会报 4用户福利 5上传简历
    int url_to;

    /**
     * 主播拒签操作
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_comtent, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        ImageView im_delete = contentView.findViewById(R.id.im_delete);
        final ImageView im_image = contentView.findViewById(R.id.im_image);
        popurl = notices.get(0).getConn_url();
        url_to = notices.get(0).getUrl_to();
        Glide.with(MainActivity.this)
                .load(notices.get(0).getImg_url())
                .placeholder(R.mipmap.zhanwei_tu)
                .crossFade()
                .transform(new CornersTransform(MainActivity.this, 20))
                .into(im_image);

        im_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    if (popurl.length() > 0) {
                        Intent resultIntent5 = new Intent(GlobalApp.getAppContext(), symbolWebView.class);
                        resultIntent5.putExtra("url", popurl);
                        startActivity(resultIntent5);
                    } else {
                        //主播操作
                        if (UserConfig.getInstance().getRole().equals("2")) {
                            switch (url_to) {
                                case 4:
                                    startActivity(new Intent(MainActivity.this, AnchorInvateActivity.class));
                                    break;
                                case 5:
                                    startActivity(new Intent(MainActivity.this, AddInviteActivity.class));
                                    break;
                            }
                        }
                    }
                }
            }
        });
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if (count < 0) {
                    mPopWindow.dismiss();
                } else {
                    int i1 = (notices.size() - 1) - count;
                    popurl = notices.get(i1).getConn_url();
                    url_to = notices.get(i1).getUrl_to();
                    Glide.with(MainActivity.this)
                            .load(notices.get(i1).getImg_url())
                            .placeholder(R.mipmap.zhanwei_tu)
                            .crossFade()
                            .transform(new CornersTransform(MainActivity.this, 20))
                            .into(im_image);
                }
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @OnClick({R.id.im_welfare, R.id.im_close})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.im_welfare:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(MainActivity.this, AnchorInvateActivity.class));
                }
                break;
            //播放音乐消失按钮
            case R.id.im_close:
                CompanyInterfaceUtils.videoFace.stop();
                ll_musice_info.setVisibility(View.GONE);
                circleProgressView.stop();
                break;
        }
    }
}

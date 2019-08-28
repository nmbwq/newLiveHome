package shangri.example.com.shangri.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.CardSelectView.RenRenCallback;
import shangri.example.com.shangri.CitySelect.bean.AdressBean;
import shangri.example.com.shangri.CitySelect.bean.City;
import shangri.example.com.shangri.CitySelect.bean.County;
import shangri.example.com.shangri.CitySelect.bean.Province;
import shangri.example.com.shangri.CitySelect.bean.Street;
import shangri.example.com.shangri.CitySelect.db.manager.AddressDictManager;
import shangri.example.com.shangri.CitySelect.widget.AddressSelector;
import shangri.example.com.shangri.CitySelect.widget.OnAddressSelectedListener;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossMoneyBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.CycleBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.BossFragmentPresenter;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.ui.activity.AddCompanyInfoActivity2;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.AddJobActivity;
import shangri.example.com.shangri.ui.activity.InviteTypeActivity;
import shangri.example.com.shangri.ui.activity.MyBossCollectActivity;
import shangri.example.com.shangri.ui.activity.NoLikeListListActivity;
import shangri.example.com.shangri.ui.activity.ResumeManageActivity;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.activity.VirtualCoinActivity;
import shangri.example.com.shangri.ui.activity.ZhiweiListActivity;
import shangri.example.com.shangri.ui.adapter.BossCycleAdapter;
import shangri.example.com.shangri.ui.adapter.BossMoneyAdapter;
import shangri.example.com.shangri.ui.adapter.BossPlatAdapter;
import shangri.example.com.shangri.ui.adapter.ChairLookJob;
import shangri.example.com.shangri.ui.adapter.LianxirenAdapter;
import shangri.example.com.shangri.ui.adapter.anchorRecruitListAdapter;
import shangri.example.com.shangri.ui.dialog.BossShareCommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CansCrollRecycle.RecyclerViewPager;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView2;
import shangri.example.com.shangri.ui.webview.ShareWebView;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.CallPhoneFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.BossTurn;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.CustomGridLayoutManager;
import shangri.example.com.shangri.util.FileUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ScrollLinearLayoutManager;
import shangri.example.com.shangri.util.StartActivityUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;
import shangri.example.com.shangri.util.WelfareDialog;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 主播招聘界面
 * Created by chengaofu on 2017/6/21.
 */

public class NewBossFragment extends BaseFragment<BossFragmentView, BossFragmentPresenter> implements BossFragmentView, View.OnClickListener, CallPhoneFace {
    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }


    private static final String TAG = "Debug";
    @BindView(R.id.news_entertain_irv)
    ListView newsEntertainIrv;
    @BindView(R.id.card_recycler_view)
    RecyclerViewPager card_recycler_view;
//    @BindView(R.id.tonggao_recycler_view)
//    RecyclerView tonggao_recycler_view;

    @BindView(R.id.news_entertain_springview)
    SpringView mNewsEntertainSpringView;
    @BindView(R.id.rl_lookjob_springview)
    SpringView rl_lookjob_springview;
    @BindView(R.id.rl_three_info)
    RelativeLayout rl_three_info;

    @BindView(R.id.look_job_left)
    RecyclerViewPager look_job_left;

    @BindView(R.id.reload)
    Button reload;


    Unbinder unbinder;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    TextView tv1, tv2, tv4, tv5;
    ImageView im1, im2, im4, im5;
    LinearLayout ll1, ll2, ll4, ll5, ll_select, ll_recycle_head;

    //    主播招聘列表导航接口
    RecyclerView rv_cultivate;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.fl)
    FrameLayout SelectCity;
    @BindView(R.id.tv_dismiss)
    TextView tvDismiss;
    @BindView(R.id.ll_select_sum)
    LinearLayout llSelectSum;

    @BindView(R.id.tv_fabu)
    TextView tv_fabu;
    @BindView(R.id.tv_fabu_image)
    ImageView tv_fabu_image;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.re_cycle)
    RecyclerView reCycle;
    @BindView(R.id.re_cycle_type)
    RecyclerView re_cycle_type;
    @BindView(R.id.re_cycle_date)
    RecyclerView re_cycle_date;
    @BindView(R.id.re_cycle_date_upanddown)
    RecyclerView re_cycle_date_upanddown;


    @BindView(R.id.ll_card_info)
    LinearLayout ll_card_info;


    @BindView(R.id.activity_cycle_select)
    FrameLayout activity_cycle_select;
    @BindView(R.id.activity_money_select)
    FrameLayout activity_money_select;
    @BindView(R.id.activity_zuixin_select)
    LinearLayout activity_zuixin_select;


    @BindView(R.id.re_money)
    RecyclerView reMoney;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.re_money1)
    RecyclerView reMoney1;


    @BindView(R.id.rl_list)
    RelativeLayout rl_list;
    @BindView(R.id.rl_card)
    RelativeLayout rl_card;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_plat_text)
    TextView llPlatText;
    @BindView(R.id.ll_plat_image)
    ImageView llPlatImage;
    @BindView(R.id.ll_plat_show)
    LinearLayout llPlatShow;
    @BindView(R.id.ll_type_text)
    TextView llTypeText;
    @BindView(R.id.ll_type_image)
    ImageView llTypeImage;
    @BindView(R.id.ll_type_show)
    LinearLayout llTypeShow;
    @BindView(R.id.rl_info)
    RelativeLayout rl_info;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.im11)
    ImageView im11;
    @BindView(R.id.ll11)
    LinearLayout ll11;
    @BindView(R.id.tv21)
    TextView tv21;
    @BindView(R.id.im21)
    ImageView im21;
    @BindView(R.id.ll21)
    LinearLayout ll21;

    @BindView(R.id.tv41)
    TextView tv41;
    @BindView(R.id.im41)
    ImageView im41;
    @BindView(R.id.ll41)
    LinearLayout ll41;

    @BindView(R.id.tv51)
    TextView tv51;
    @BindView(R.id.im51)
    ImageView im51;
    @BindView(R.id.ll51)
    LinearLayout ll51;

    @BindView(R.id.ll_select1)
    LinearLayout llSelect1;


    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    @BindView(R.id.sc)
    ScrollView sc;
    @BindView(R.id.tv_cycle_cncle)
    TextView tvCycleCncle;
    @BindView(R.id.tv_commit_cycle)
    TextView tvCommitCycle;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_money_cncle)
    TextView tvMoneyCncle;
    @BindView(R.id.tv_money_commit)
    TextView tvMoneyCommit;
    @BindView(R.id.im_boss_show)
    ImageView im_boss_show;
    @BindView(R.id.tv_state_type)
    TextView tv_state_type;
    @BindView(R.id.im_type)
    ImageView im_type;
    @BindView(R.id.rl_anchor)
    LinearLayout rlAnchor;
    @BindView(R.id.tv_title_zhaopin)
    TextView tvTitleZhaopin;
    @BindView(R.id.tv_title_tonggao)
    TextView tvTitleTonggao;
    @BindView(R.id.ll_zhaopin_info)
    RelativeLayout llZhaopinInfo;
    @BindView(R.id.rl_tonggao_info)
    RelativeLayout rlTonggaoInfo;

    /**
     * 添加左右滑动画
     */
    @BindView(R.id.rl_right_to_left)
    RelativeLayout rl_right_to_left;
    @BindView(R.id.iv_right_to_left)
    ImageView iv_right_to_left;
    /**
     * 添加上下滑动画
     */
    @BindView(R.id.rl_down_to_up)
    RelativeLayout rl_down_to_up;
    @BindView(R.id.iv_down_to_up)
    ImageView iv_down_to_up;

    @BindView(R.id.im_zuixin)
    ImageView im_zuixin;
    @BindView(R.id.tv_zuixin)
    TextView tv_zuixin;
    @BindView(R.id.im_tuijian)
    ImageView im_tuijian;
    @BindView(R.id.tv_tuijian)
    TextView tv_tuijian;
    @BindView(R.id.tv_empty_zhanwei)
    TextView tv_empty_zhanwei;
    @BindView(R.id.rl_change_title)
    RelativeLayout rl_change_title;

    @BindView(R.id.tv_40dp)
    TextView tv_40dp;


    //是否需要左右滑
    public static boolean RIGHT_TO_LEFT = false;
    //是否需要上下滑
    public static boolean DOWN_TO_UP = false;

    private int currPage = 1;
    private int CardcurrPage = 1;
    private int lookjobcurrPage = 1;
    private int lookjobtotal_page = 1;

    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数
    CustomGridLayoutManager layoutManager;
    private ProgressDialogFragment mProgressDialog;
    //轮播图
    private ConvenientBanner mBanner;
    //列表
    private List<BossDataBean.ListBean.DataBean> mNewsList = new ArrayList<>();
    //列表界面是不是滑动到最后一行
    public boolean isLastRow;

    //列表适配器
    private LianxirenAdapter mAdapter;
    //招聘卡片布局数据
    private List<newBossDataBean.DataBean> mDatas = new ArrayList<>();

    //看职位数据
    public static List<BossDataBean.ListBean.DataBean> LookJobmDatas = new ArrayList<>();

    //通告卡片布局数据
    private List<BossTongGaoBean.ConscribeBean> mTongGaoDatas = new ArrayList<>();

    //滑动没有 要求是倒序展示数据  mHunTongGaoDatas记录本次已划的数据  在划完应该倒序 直接将mHunTongGaoDatas倒序复制到mTongGaoDatas
    //相当于备份数据   mTongGaoDatas滑动以后直接就删除了
    private List<BossTongGaoBean.ConscribeBean> mHunTongGaoDatas = new ArrayList<>();

    // 1 选择地址操作   2 选择平台操作  3 选择类型  4 选择薪资  5 结算周期
    int clickType = 0;
    //    城市名称
    String area_name = "";
    //    平台id 逗号隔开(1,2,3)
    String plat_id = "";
    //    平台id 逗号隔开(1,2,3)
    String anchor_type = "";
    //    薪资区间底
    String pay_low = "";
    //    薪资区间高
    String pay_high = "";

    //    分享中薪资区间底
    String Sharepay_low = "";
    //    分享中薪资区间高
    String Sharepay_high = "";
    //    结算方式 1月结 2周结 3日结
    String salary_type = "";


    //    工作方式 1 线上 2 线下 3线上/线下 0所有
    String job_method = "";
    //    底薪(3000-5000)
    String keep_pay = "0";
    //    3只看官方
    String publish_type = "";

    //选择的市 区地址
    String city1 = "";
    String county1 = "";
    //    是否有简历 1是 0否
    int is_resume;
    //是否是card模式
    boolean isCard = true;
    //有没有点击查看更过的操作 点击查看更多 clickLike传值为1
    boolean isMore = false;

    RenRenCallback callback;
    RenRenCallback TongGaocallback;
    //列表或是卡片刷新条件改变之后  替换需要重新请求数据
    boolean isChange = false;


    //选择平台做展开操作  还是收起操作       展开为false 收起为true
    boolean llplatshow = false;
    boolean lltypeshow = false;

    //    只看日和月是  true为选中状态   false为非选中状态
    boolean isDefaltDayAndWeek;
    //    是否是通知
    boolean isTongzhi;
    ///当前第几条数据 卡片现在是第几个数据   位置是数据下标 从0开始
    String offset = "0";
    //卡片滑动的位置   用来看是第一个位置  还是一页的最后位置（最后一个位置在滑动请求接口 做分页操作）
    String ScrollPosition = "0";

    //点击电话操作的时候   选中的是那个招聘信息
    String id;
    //    排序 1推荐 2最新
    String recommend_new = "1";
    //形象卡需要数据
    List<ReadPhotoBean.ResumeBean> PhoneList = new ArrayList<>();
    ResumeIndexBean.ResumeBean resume;

    //电话  拨打电话需要的
    String phone = "";
    //公司名称
    String company;
    //类型名字
    String type_name;
    String url = "";
    String work_position;
    //分享qq微信数据
    String qq = "";
    String weixin = "";
    String email = "";


    //会长看职位适配器
    ChairLookJob LookJobleftAdapter;
    //会长看职位界面是不是滑动到最后一行
    public boolean isLastSelectjobRow;
    int height;
    private ViewPagerLayoutManager mLayoutManager;

    //    主播招聘列表导航适配器
    private anchorRecruitListAdapter cultivateAdapter;
    List<anchorRecruitListBean.ListBean> list = new ArrayList<>();
    //调用公司信息是否完善    点击拨打电话以及拨电话调用   true为点击发布职位时候调用
    boolean isCollectCheck = false;


    @Override
    protected void initViewsAndEvents() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕的高度
        height = wm.getDefaultDisplay().getHeight();
        CompanyInterfaceUtils.setcallPhoneFace(this);
        if (UserConfig.getInstance().getIsBossShow().equals("1")) {
            im_boss_show.setVisibility(View.GONE);
        } else {
            im_boss_show.setVisibility(View.VISIBLE);
        }
        Log.d("Debug", "当前的状态为" + UserConfig.getInstance().getRole());
        if (UserConfig.getInstance().getRole().equals("1")) {

            mPresenter.conscribeIndex();
            rlAnchor.setVisibility(View.VISIBLE);
            if (UserConfig.getInstance().getIsScrollShow().equals("0")) {
                if (DOWN_TO_UP) {
                    if (scrollLayoutManager != null)
                        startRecycViewScroll();
                    rl_down_to_up.setVisibility(View.GONE);
                } else {
                    if (scrollLayoutManager != null)
                        stopRecycViewScroll();
                    rl_down_to_up.setVisibility(View.VISIBLE);
                    iv_down_to_up.setImageResource(R.drawable.animlis_up);
                    animation1 = (AnimationDrawable) iv_down_to_up.getDrawable();
                    animation1.start();
                }
            }
            //tvTitleZhaopin.setText("主播");
            tvTitleTonggao.setText("最新");
            rl_three_info.setVisibility(View.GONE);
            rl_lookjob_springview.setVisibility(View.VISIBLE);
            tv_fabu.setText("职位发布");
            tv_state_type.setText("看职位");
            tv_fabu_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.fabu_zhiwei));
            im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.kan_zhiwei));
        } else {
            //右上角选择隐藏
            rlAnchor.setVisibility(View.GONE);
            //tvTitleZhaopin.setText("招聘");
            tvTitleTonggao.setText("全部");
            rl_three_info.setVisibility(View.VISIBLE);
            rl_lookjob_springview.setVisibility(View.GONE);
            tv_fabu.setText("职位收藏");
            tv_state_type.setText("列表显示");
            im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.libiao_zhanshi));
            tv_fabu_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.zhiwei_shoucang));
        }
        mAdapter = new LianxirenAdapter(getActivity(), mNewsList);
        newsEntertainIrv.setAdapter(mAdapter);
        //主播身份不请求求职列表
        if (UserConfig.getInstance().getRole().equals("1")) {
            mPresenter.wantList(lookjobcurrPage + "");
        }
        EventBus.getDefault().register(this);
        request();
        //卡片布局适配器
        card_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        listAdapter = new anchorListAdapter(getActivity(), R.layout.item_renren_layout, mDatas);
        card_recycler_view.setAdapter(listAdapter);

        //设置每次滑动一张图片   true为滑动一张  false 快速滑动 可以滑动过好几张图片
        card_recycler_view.setSinglePageFling(true);


        card_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
//                updateState(scrollState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                int childCount = card_recycler_view.getChildCount();
                int width = card_recycler_view.getChildAt(0).getWidth();
                int padding = (card_recycler_view.getWidth() - width) / 2;
//                mCountText.setText("Count: " + childCount);

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    ;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });


        card_recycler_view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (card_recycler_view.getChildCount() < 3) {
                    if (card_recycler_view.getChildAt(1) != null) {
                        if (card_recycler_view.getCurrentPosition() == 0) {
                            View v1 = card_recycler_view.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = card_recycler_view.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (card_recycler_view.getChildAt(0) != null) {
                        View v0 = card_recycler_view.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (card_recycler_view.getChildAt(2) != null) {
                        View v2 = card_recycler_view.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });


        card_recycler_view.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("Debug", "老位置是" + oldPosition + "新位置是" + newPosition);
                if (Integer.parseInt(ScrollPosition) == 0) {
                    if (oldPosition == -1 && newPosition == -1) {
                        //刷新不需要
//                        if (PointUtils.isFastClick()) {
//                            CardcurrPage = 1;
//                            request();
//                        }
                    }
                } else if (Integer.parseInt(ScrollPosition) == (listAdapter.getAll().size() - 1)) {
                    if (oldPosition == -1 && newPosition == -1) {
                        Log.d("Debug", "总页数为" + mPageNum + "当前的页数为" + CardcurrPage);
                        if (mPageNum > CardcurrPage) {
                            Log.d("Debug", "到达这里yes");
                            if (PointUtils.isFastClick()) {
                                CardcurrPage++;
                                if (mProgressDialog == null) {
                                    mProgressDialog = new ProgressDialogFragment();
                                }
                                mProgressDialog.show(getActivity().getSupportFragmentManager());
                                request();
                            }
                        } else {
                            Log.d("Debug", "到达这里else");
                            if (PointUtils.isFastClick()) {
                                llSelectSum.setVisibility(View.VISIBLE);
                                activity_empty_linshi.setVisibility(View.VISIBLE);
                                rl_card.setVisibility(View.VISIBLE);
                                rl_list.setVisibility(View.GONE);
                                SelectCity.setVisibility(View.GONE);
                                activity_cycle_select.setVisibility(View.GONE);
                                activity_money_select.setVisibility(View.GONE);
                            }
                        }
                    }
                }
                if (newPosition >= 0) {
                    //当前位置
                    ScrollPosition = newPosition + "";
                    //15个数据一页 offset取余取出当前页的下标位置
                    int i = newPosition % 15;
                    offset = i + "";
                    //点击当前位置属于第列表中第几页  传当前页数和上面的offset做卡片看到的当前数据 在列表中显示第一个功能
                    int page = newPosition / 15;
                    //正好整除的时候 直接是当前页数   当不整除的时候第一数据  返回是 零点几 第二页返回的是 1点几  所以页数要加 1
                    if (newPosition % 15 == 0) {
                        currPage = page;
                    } else {
                        //page 取整数
                        currPage = page + 1;
                    }
                }
            }
        });


        layoutManager = new CustomGridLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        initSpringView();
        initCityDate();
        setHeaderPage();
        initCycleDate();
        initMoneyDate();
        initSpringViewJob();
        mPresenter.recruitBanner();
        //        主播招聘列表导航
        mPresenter.anchorRecruitList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossBean event) {
        Log.d("Debug", "到达招聘界面");
        if (event.getRefush()) {
            for (int i = 0; i < LookJobleftAdapter.getAll().size(); i++) {
                wantListBean.ResumesBean dataBean = (wantListBean.ResumesBean) LookJobleftAdapter.get(i);
                String id = dataBean.getId() + "";
                Log.d("Debug", "到达更改以沟通循环里面----------------------------------------------------");
                if (id.equals(event.getRecruit_id())) {
                    if (event.getPhone()) {
                        //是否沟通更改状态 1是已沟通  2是没有沟通
                        dataBean.setIs_linkup(1);
                        Log.d("Debug", "到达更改以沟通这里----------------------------------------------------");
                    }
                    if (event.getQiangTa()) {
                        Log.d("Debug", "到达这里----------------------------------------------------");
                        //是否沟通更改状态 0是没有抢过  1是已经抢过
                        dataBean.setIs_rob(1);
                        dataBean.setChat_id(event.getChat_id());
                    }
                }
                LookJobleftAdapter.notifyDataSetChanged();
            }
        } else {
            for (int i = 0; i < mAdapter.getAll().size(); i++) {
                BossDataBean.ListBean.DataBean dataBean = (BossDataBean.ListBean.DataBean) mAdapter.getItem(i);
                if (dataBean.getId().equals(event.getRecruit_id())) {
                    if (event.getType() == 1) {
//                    是否收藏 1是 0否
                        dataBean.setIs_collect(1 + "");
                    } else {
                        dataBean.setIs_collect(0 + "");
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    //禁止scrllview滑动
    public void stopRecycViewScroll() {
        scrollLayoutManager.setScrollEnabled(false);
        look_job_left.setLayoutManager(scrollLayoutManager);
    }

    //开启scrllview滑动
    public void startRecycViewScroll() {
        scrollLayoutManager.setScrollEnabled(true);
        look_job_left.setLayoutManager(scrollLayoutManager);
    }

    private void supportRequestWindowFeature(int featureActionBarOverlay) {
    }


    private void setHeaderPage() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.widget_boss_header, null);
        mBanner = header.findViewById(R.id.convenientBanner);
        rv_cultivate = header.findViewById(R.id.rv_cultivate);
        mBanner.setMargin();
        tv1 = header.findViewById(R.id.tv1);
        tv2 = header.findViewById(R.id.tv2);
        tv4 = header.findViewById(R.id.tv4);
        tv5 = header.findViewById(R.id.tv5);
        im1 = header.findViewById(R.id.im1);
        im2 = header.findViewById(R.id.im2);
        im4 = header.findViewById(R.id.im4);
        im5 = header.findViewById(R.id.im5);
        ll1 = header.findViewById(R.id.ll1);
        ll2 = header.findViewById(R.id.ll2);
        ll4 = header.findViewById(R.id.ll4);
        ll5 = header.findViewById(R.id.ll5);

        ll_select = header.findViewById(R.id.ll_select);
        ll_recycle_head = header.findViewById(R.id.ll_recycle_head);
        ll_select.setBackground(getResources().getDrawable(R.mipmap.bg_heise));

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        //默认尽力是卡片布局
        mBanner.setVisibility(View.GONE);
        mNewsEntertainSpringView.setVisibility(View.GONE);
        ll_card_info.setVisibility(View.VISIBLE);
        newsEntertainIrv.addHeaderView(header);

    }

    /**
     * 招聘列表界面刷新操作
     */

    private void initSpringView() {
        mNewsEntertainSpringView.setGive(SpringView.Give.TOP);
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
//                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
                //offset只有在卡片跳转到列表时候调用  在列表界面进行请求操作 次参数不传
                offset = "0";
                loadData();
            }

            @Override
            public void onLoadmore() {
                //分页加载放在滑动监听里面 （解决上拉加载 rl_lookjob_springview会使布局上移一段后加载成功在下来的情况（一般不会做这个判断，为这个页面特殊做的额这个操作））

                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
//                ACTION_TYPE = ACTION_LOAD_MORE;
//                offset = "0";
//                requestNewsList();
            }
        });

    }

    /**
     * 看职位界面刷新操作  （卡片也在里面，卡片没有上拉加载下拉刷新操作）
     */
    private void initSpringViewJob() {
        rl_lookjob_springview.setGive(SpringView.Give.TOP);
        rl_lookjob_springview.setType(SpringView.Type.FOLLOW);
        rl_lookjob_springview.setHeader(new SpringViewHeader(getActivity()));
        rl_lookjob_springview.setFooter(new SpringViewFooter(getActivity()));
        rl_lookjob_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                lookjobcurrPage = 1;
                mPresenter.wantList(lookjobcurrPage + "");
            }

            @Override
            public void onLoadmore() {
                rl_lookjob_springview.onFinishFreshAndLoad(); //停止加载

                //分页加载放在滑动监听里面 （解决上拉加载 rl_lookjob_springview会使布局上移一段后加载成功在下来的情况（一般不会做这个判断，为这个页面特殊做的额这个操作））
//                if (lookjobcurrPage < lookjobtotal_page) {
//                    lookjobcurrPage++;
//                    mPresenter.wantList(lookjobcurrPage + "");
//                } else {
//                    ToastUtil.showShort("已加载全部");
//                    rl_lookjob_springview.onFinishFreshAndLoad(); //停止加载
//                }
            }
        });
    }

    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            Log.d("Debug", "到达没网络这里");
            rl_info.setVisibility(View.INVISIBLE);
            layout_network_break.setVisibility(View.VISIBLE);
            mNewsEntertainSpringView.onFinishFreshAndLoad();
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            Log.d("Debug", "到达有网络这里");
            rl_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.INVISIBLE);
            mNewsList.clear();
            currPage = 1;
            request();
        }
    }

    private void requestNewsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            Log.d("Debug", "到达没网络这里");
            rl_info.setVisibility(View.INVISIBLE);
            layout_network_break.setVisibility(View.VISIBLE);
            mNewsEntertainSpringView.onFinishFreshAndLoad();
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.INVISIBLE);
            Log.d("Debug", "");
            if (mPageNum == 1) {
                mNewsEntertainSpringView.onFinishFreshAndLoad();
            } else {
                if (currPage < mPageNum) {
                    currPage++;
                    request();
                } else {
                    //循环请求  当请求到最后一页数据，从第一页数据重新请求添加
                    currPage = 1;
                    request();
                }
            }

        }
    }

    public void request() {
        if (isCard) {
            mPresenter.NewrecruitList(recommend_new, "", publish_type, job_method, isCard, offset, CardcurrPage + "", area_name, plat_id, anchor_type, pay_low, pay_high, salary_type, keep_pay);
        } else {
            mPresenter.recruitList(recommend_new, "", publish_type, job_method, isCard, offset, currPage + "", area_name, plat_id, anchor_type, pay_low, pay_high, salary_type, keep_pay);
        }
    }

    @Override
    public void onResume() {
        //开始自动翻页
        if (mBanner != null) {
            mBanner.startTurning(3000);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        if (mBanner != null) {
            mBanner.stopTurning();
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_fragment_boss;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_fragment_boss;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(getContext(), this);
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
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
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.ll_zuixin, R.id.ll_tuijian, R.id.ll_nolike_more, R.id.tv_title_zhaopin, R.id.tv_title_tonggao, R.id.im_boss_show, R.id.tv_text, R.id.ll51, R.id.ll41, R.id.ll21, R.id.ll11, R.id.ll_plat_show, R.id.ll_type_show, R.id.rl_anchor, R.id.ll_collect, R.id.tv_cycle_cncle, R.id.tv_money_cncle, R.id.tv_commit, R.id.tv_dismiss, R.id.tv_city_dismiss, R.id.tv_commit_cycle, R.id.tv_money_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            //查看不考虑操作
            case R.id.ll_nolike_more:
                startActivity(new Intent(getActivity(), NoLikeListListActivity.class));
                break;
            case R.id.ll_tuijian:
                tv_tuijian.setTextColor(this.getResources().getColor(R.color.color_d0a76c));
                tv_zuixin.setTextColor(this.getResources().getColor(R.color.white));
                im_tuijian.setVisibility(View.VISIBLE);
                im_zuixin.setVisibility(View.GONE);
                llSelectSum.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                tv5.setText("推荐");
                tv51.setText("推荐");
                recommend_new = "1";
                setTextColor();
                request();
                break;
            case R.id.ll_zuixin:
                llSelectSum.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                tv_tuijian.setTextColor(this.getResources().getColor(R.color.white));
                tv_zuixin.setTextColor(this.getResources().getColor(R.color.color_d0a76c));
                im_tuijian.setVisibility(View.GONE);
                im_zuixin.setVisibility(View.VISIBLE);
                recommend_new = "2";
                tv5.setText("最新");
                tv51.setText("最新");
                setTextColor();
                request();
                break;
            case R.id.tv_title_zhaopin:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    rlAnchor.setVisibility(View.VISIBLE);
                    llCollect.setVisibility(View.VISIBLE);
                    isTongzhi = false;
                    rl_change_title.setBackground(getResources().getDrawable(R.mipmap.zhubo_xxd));
                    // 通告  跳转到招聘   主播 rl_three_info 显示  看职位页面不显示
                    if (UserConfig.getInstance().getRole().equals("1")) {
//                    会长根据状态   isCard=true 说明现在是看职位界面  通告 跳转过来  rl_three_info 隐藏  看职位界面显示
//                                  isCard=true  说明现在是招聘列表界面  通告 跳转过来rl_three_info 显示  看职位界面隐藏
                        if (isCard) {
                            rl_three_info.setVisibility(View.GONE);
                            rl_lookjob_springview.setVisibility(View.VISIBLE);
                        } else {
                            rl_three_info.setVisibility(View.VISIBLE);
                            rl_lookjob_springview.setVisibility(View.GONE);
                        }
                    } else {
                        rl_three_info.setVisibility(View.VISIBLE);
                        rl_lookjob_springview.setVisibility(View.GONE);
                    }
                    llZhaopinInfo.setVisibility(View.VISIBLE);
                    rlTonggaoInfo.setVisibility(View.GONE);
                } else {
                    rl_change_title.setBackground(getResources().getDrawable(R.mipmap.zhubo_xxd));

//                    tvTitleZhaopin.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
//                    tvTitleTonggao.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape3));
                    isCard = true;
                    mBanner.setVisibility(View.GONE);
                    mNewsEntertainSpringView.setVisibility(View.GONE);
                    ll_card_info.setVisibility(View.VISIBLE);
                    llSelectSum.setVisibility(View.GONE);
//                    招聘列表跳转  会长身份跳转  看职位界面  会长身份跳转 看卡片界面
                    //列表切换过来  主播看的是卡片   会长管理员看到是  看职位
                    //tvTitleZhaopin.setText("招聘");
                    tv_state_type.setText("列表显示");
                    llSelect1.setVisibility(View.GONE);
                    im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.libiao_zhanshi));
                    if (isChange) {
                        request();
                        isChange = false;
                    }
                    rl_three_info.setVisibility(View.VISIBLE);
                    rl_lookjob_springview.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_title_tonggao:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    rlAnchor.setVisibility(View.VISIBLE);
                    llCollect.setVisibility(View.VISIBLE);
                    isTongzhi = true;
                    rl_change_title.setBackground(getResources().getDrawable(R.mipmap.zp_xxly));
//                    tvTitleZhaopin.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape3));
//                    tvTitleTonggao.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
                    llZhaopinInfo.setVisibility(View.GONE);
                    rlTonggaoInfo.setVisibility(View.VISIBLE);
                    // 招聘或是主播 跳转到通告  rl_three_info 显示  看职位页面不显示
                    rl_three_info.setVisibility(View.VISIBLE);
                    rl_lookjob_springview.setVisibility(View.GONE);
                } else {
                    rl_change_title.setBackground(getResources().getDrawable(R.mipmap.zp_xxly));
//                    tvTitleZhaopin.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape3));
//                    tvTitleTonggao.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
                    isCard = false;
                    mBanner.setVisibility(View.VISIBLE);
                    mNewsEntertainSpringView.setVisibility(View.VISIBLE);
                    ll_card_info.setVisibility(View.GONE);
                    llSelectSum.setVisibility(View.GONE);
                    //看列表不论是会长还是主播   看职位界面隐藏  列表以及卡片界面显示
                    rl_three_info.setVisibility(View.VISIBLE);
                    rl_lookjob_springview.setVisibility(View.GONE);
                    //tvTitleZhaopin.setText("招聘");
                    tv_state_type.setText("卡片显示");
                    im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.kapian_xianshi));
                    ACTION_TYPE = ACTION_FRESH;
                    //这里要判断下  是卡片列表有数据跳转列表 当前页数 以及位置 反之 页数传1 position 0
                    if (mDatas.size() == 0) {
                        currPage = 1;
                        offset = "0";
                    }
                    if (UserConfig.getInstance().getIsScrollShow().equals("0")) {
                        rl_down_to_up.setVisibility(View.GONE);
                        rl_right_to_left.setVisibility(View.GONE);
                    }

                    //放在初始化里面   卡片布局 筛选条件没有（因为执行下面代码firstVisibleItem不大于等于1 执行的是 llSelect1.setVisibility(View.GONE)）
                    newsEntertainIrv.setOnScrollListener(new AbsListView.OnScrollListener() {

                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                            //当滚到最后一行且停止滚动时，执行加载
                            if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                                //加载元素
                                Log.d("Debug", "最后一页做加载操作");
                                ACTION_TYPE = ACTION_LOAD_MORE;
                                offset = "0";
                                requestNewsList();

                                isLastRow = false;
                            }
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if (firstVisibleItem >= 1) {
                                llSelect1.setVisibility(View.VISIBLE);
                            } else {
                                llSelect1.setVisibility(View.GONE);
                            }
                            //判断是否滚到最后一行
                            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                                isLastRow = true;
                            }
                        }
                    });
                    request();
                }


                break;
            case R.id.im_boss_show:
                UserConfig.getInstance().pwdIsBossShow("1");
                im_boss_show.setVisibility(View.GONE);
                break;
            //卡片列表点击查看更多操作
            case R.id.tv_text:
                Log.d("Debug", "查看更多操作");
                isMore = true;
                CardcurrPage = 1;
                currPage = 1;
                offset = "";
                request();
                break;

            case R.id.ll_plat_show:
                List<BossPlatBean.PlatfromBean> date = new ArrayList<>();
                //收起  展开操作
                if (llplatshow) {
                    //默认展示两行  数据大于8条  去八条数据   小于8条 取全部数据
                    if (mNewsList1.size() >= 8) {
                        for (int i = 0; i < 8; i++) {
                            date.add(mNewsList1.get(i));
                        }
                    } else {
                        for (int i = 0; i < mNewsList1.size(); i++) {
                            date.add(mNewsList1.get(i));
                        }
                    }
                    llPlatText.setText("更多");
                    llPlatImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangxia_jiantou));
                    llplatshow = false;
                } else {
                    for (int i = 0; i < mNewsList1.size(); i++) {
                        date.add(mNewsList1.get(i));
                    }
                    llPlatText.setText("收起");
                    llPlatImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangshang_jiantou));
                    llplatshow = true;
                }
                TypeAdapter.setData(date);
                break;
            case R.id.ll_type_show:
                List<BossPlatBean.PlatfromBean> date1 = new ArrayList<>();
                //收起  展开操作
                if (lltypeshow) {
                    //默认展示两行  数据大于8条  去八条数据   小于8条 取全部数据
                    if (mNewsList2.size() >= 8) {
                        for (int i = 0; i < 8; i++) {
                            date1.add(mNewsList2.get(i));
                        }
                    } else {
                        for (int i = 0; i < mNewsList2.size(); i++) {
                            date1.add(mNewsList2.get(i));
                        }
                    }
                    llTypeText.setText("更多");
                    llTypeImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangxia_jiantou));
                    lltypeshow = false;
                } else {
                    for (int i = 0; i < mNewsList2.size(); i++) {
                        date1.add(mNewsList2.get(i));
                    }
                    llTypeText.setText("收起");
                    llTypeImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangshang_jiantou));
                    lltypeshow = true;
                }
                TypeAdapter1.setData(date1);
                break;
            //我的形象
            case R.id.rl_anchor:
                rlAnchor.setVisibility(View.VISIBLE);
                llCollect.setVisibility(View.VISIBLE);
                isTongzhi = false;

                rl_change_title.setBackground(getResources().getDrawable(R.mipmap.zhubo_xxd));

//                tvTitleZhaopin.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
//                tvTitleTonggao.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape3));
                llZhaopinInfo.setVisibility(View.VISIBLE);
                rlTonggaoInfo.setVisibility(View.GONE);

                if (isCard) {
                    isCard = false;
                    mBanner.setVisibility(View.VISIBLE);
                    mNewsEntertainSpringView.setVisibility(View.VISIBLE);
                    ll_card_info.setVisibility(View.GONE);
                    llSelectSum.setVisibility(View.GONE);
                    //看列表不论是会长还是主播   看职位界面隐藏  列表以及卡片界面显示
                    rl_three_info.setVisibility(View.VISIBLE);
                    rl_lookjob_springview.setVisibility(View.GONE);
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //tvTitleZhaopin.setText("招聘");
                        tv_state_type.setText("找主播");
                        im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.zhao_zhubo));
                    } else {
                        //tvTitleZhaopin.setText("招聘");
                        tv_state_type.setText("卡片显示");
                        im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.kapian_xianshi));
                    }
                    ACTION_TYPE = ACTION_FRESH;
                    //这里要判断下  是卡片列表有数据跳转列表 当前页数 以及位置 反之 页数传1 position 0
                    if (mDatas.size() == 0) {
                        currPage = 1;
                        offset = "0";
                    }
                    if (UserConfig.getInstance().getIsScrollShow().equals("0")) {
                        rl_down_to_up.setVisibility(View.GONE);
                        rl_right_to_left.setVisibility(View.GONE);
                    }

                    //放在初始化里面   卡片布局 筛选条件没有（因为执行下面代码firstVisibleItem不大于等于1 执行的是 llSelect1.setVisibility(View.GONE)）
                    newsEntertainIrv.setOnScrollListener(new AbsListView.OnScrollListener() {

                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                            //当滚到最后一行且停止滚动时，执行加载
                            if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                                //加载元素
                                Log.d("Debug", "最后一页做加载操作");
                                ACTION_TYPE = ACTION_LOAD_MORE;
                                offset = "0";
                                requestNewsList();

                                isLastRow = false;
                            }
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if (firstVisibleItem >= 1) {
                                llSelect1.setVisibility(View.VISIBLE);
                            } else {
                                llSelect1.setVisibility(View.GONE);
                            }
                            //判断是否滚到最后一行
                            if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                                isLastRow = true;
                            }
                        }
                    });

                    request();
                } else {
                    isCard = true;
                    mBanner.setVisibility(View.GONE);
                    mNewsEntertainSpringView.setVisibility(View.GONE);
                    ll_card_info.setVisibility(View.VISIBLE);
                    llSelectSum.setVisibility(View.GONE);
//                    招聘列表跳转  会长身份跳转  看职位界面  会长身份跳转 看卡片界面
                    //列表切换过来  主播看的是卡片   会长管理员看到是  看职位
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        if (UserConfig.getInstance().getIsScrollShow().equals("0")) {
                            if (RIGHT_TO_LEFT) {
                                if (scrollLayoutManager != null)
                                    startRecycViewScroll();
                                rl_right_to_left.setVisibility(View.GONE);
                            } else {
                                if (scrollLayoutManager != null)
                                    stopRecycViewScroll();
                                rl_right_to_left.setVisibility(View.VISIBLE);
                                iv_right_to_left.setImageResource(R.drawable.animlis);
                                AnimationDrawable animation1 = (AnimationDrawable) iv_right_to_left.getDrawable();
                                animation1.start();
                            }
                        }
                        tv_state_type.setText("看职位");
                        //tvTitleZhaopin.setText("主播");
                        rl_three_info.setVisibility(View.GONE);
                        rl_lookjob_springview.setVisibility(View.VISIBLE);
                        llSelect1.setVisibility(View.GONE);
                        im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.kan_zhiwei));
                    } else {
                        //tvTitleZhaopin.setText("招聘");
                        tv_state_type.setText("列表显示");
                        llSelect1.setVisibility(View.VISIBLE);
                        im_type.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.libiao_zhanshi));
                        if (isChange) {
                            request();
                            isChange = false;
                        }
                        rl_three_info.setVisibility(View.VISIBLE);
                        rl_lookjob_springview.setVisibility(View.GONE);

                    }
                }
                break;
            case R.id.ll_collect:
                if (UserConfig.getInstance().getToken().length() == 0) {
                    StartActivityUtils.startActivity();
                    return;
                }
                if (UserConfig.getInstance().getRole().equals("0")) {
                    StartActivityUtils.startSelectRoleActivity(getActivity());
                    return;
                }

                if (UserConfig.getInstance().getRole().equals("1")) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    isCollectCheck = true;
                    mPresenter.companyAdd(2);
                } else {
                    startActivity(new Intent(getActivity(), MyBossCollectActivity.class));
                }
                break;
//                主播类型重置操作
            case R.id.tv_cycle_cncle:
                anchor_type = "";
                plat_id = "";
                TypeAdapter1.get(0).setClick(true);
                for (int i = 1; i < TypeAdapter1.getAll().size(); i++) {
                    TypeAdapter1.get(i).setClick(false);
                }
                TypeAdapter1.notifyDataSetChanged();
                TypeAdapter.get(0).setClick(true);
                for (int i = 1; i < TypeAdapter.getAll().size(); i++) {
                    TypeAdapter.get(i).setClick(false);
                }
                TypeAdapter.notifyDataSetChanged();
                job_method = "";
                UpAndDownmAdapter.get(1).setIsfocks(false);
                UpAndDownmAdapter.get(2).setIsfocks(false);
                UpAndDownmAdapter.get(0).setIsfocks(true);
                UpAndDownmAdapter.notifyDataSetChanged();
                tv2.setText("直播类型");
                tv21.setText("直播类型");
                break;
            //薪资要求重置操作
            case R.id.tv_money_cncle:
                for (int i = 0; i < MoneymAdapter1.getAll().size(); i++) {
                    MoneymAdapter1.getAll().get(i).setIsfocks(false);
                }
                MoneymAdapter1.get(0).setIsfocks(true);
                MoneymAdapter1.notifyDataSetChanged();
                keep_pay = "3";
                for (int i = 0; i < MoneymAdapter.getAll().size(); i++) {
                    MoneymAdapter.getAll().get(i).setIsfocks(false);
                }
                MoneymAdapter.get(0).setIsfocks(true);
                MoneymAdapter.notifyDataSetChanged();
                pay_low = "";
                pay_high = "";
                CyclemAdapter.get(1).setIsfocks(false);
                CyclemAdapter.get(2).setIsfocks(false);
                CyclemAdapter.get(3).setIsfocks(false);
                CyclemAdapter.get(0).setIsfocks(true);
                CyclemAdapter.notifyDataSetChanged();
                salary_type = "";
                tv4.setText("薪资要求");
                tv41.setText("薪资要求");
                break;

            case R.id.tv_commit:
//                Log.d("Debug", "dapda 测试这里llSelect1状态是" + llSelect1.getVisibility());
//                llSelect1.setVisibility(View.VISIBLE);
//                Log.d("Debug", "操作以后llSelect1状态是" + llSelect1.getVisibility());
                if (!isCard) {
                    isChange = true;
                }
                ll_select.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_heise));
                llSelectSum.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                area_name = "";
                tv1.setText("全国");
                tv11.setText("全国");
                offset = "0";
                CardcurrPage = 1;
                currPage = 1;
                setTextColor();
                request();
                break;
            //薪资选择确定按钮
            case R.id.tv_money_commit:
                if (!isCard) {
                    isChange = true;
                }
                int number = 0;
                //薪资的判断
                if (et1.getText().toString().length() > 0 || et2.getText().toString().length() > 0) {
                    if (et1.getText().toString().length() == 0 || et2.getText().toString().length() == 0) {
                        ToastUtil.showShort("自定义薪资请补充完整");
                        return;
                    } else {
                        Log.d("Debug", "et2的数据为" + et2.getText().toString());
                        if (Integer.parseInt(et2.getText().toString()) > Integer.parseInt(et1.getText().toString())) {
                            pay_low = et1.getText().toString();
                            pay_high = et2.getText().toString();
                            number++;
                        } else {
                            ToastUtil.showShort("请输入大于前面的薪资");
                            return;
                        }
                    }
                } else {
                    for (int i = 0; i < MoneymAdapter.getAll().size(); i++) {
                        if (MoneymAdapter.getAll().get(i).isIsfocks()) {
                            if (MoneymAdapter.getAll().get(i).getName().equals("不限")) {
                                pay_low = "";
                                pay_high = "";
                            } else {
                                pay_low = MoneymAdapter.getAll().get(i).getLow() + "";
                                pay_high = MoneymAdapter.getAll().get(i).getHigh() + "";
                                if (Integer.parseInt(pay_low) == 0) {
                                    pay_low = 3000 + "";
                                    pay_high = "";
                                }
                                if (Integer.parseInt(pay_low) == 100000) {
                                    pay_low = "";
                                    pay_high = 100000 + "";
                                }
                                number++;
                            }

                        }
                    }
                }
                for (int i = 0; i < MoneymAdapter1.getAll().size(); i++) {
                    if (MoneymAdapter1.getAll().get(i).isIsfocks()) {
                        if (MoneymAdapter1.getAll().get(i).getName().equals("不限")) {
                            keep_pay = "0";
                        } else {
                            if (MoneymAdapter1.getAll().get(i).getName().equals("有底薪")) {
                                keep_pay = "1";
                            } else if (MoneymAdapter1.getAll().get(i).getName().equals("无底薪")) {
                                keep_pay = "2";
                            }
                            number++;
                        }
                    }
                }
                //结算周期
                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < CyclemAdapter.getAll().size(); i++) {
                    if (CyclemAdapter.get(0).isIsfocks()) {
                    } else {
                        if (CyclemAdapter.get(i).isIsfocks()) {
                            if (sb2.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                                sb2.append(",");
                            }
                            number++;
                            sb2.append(CyclemAdapter.getAll().get(i).getSymbol());
                        }
                    }

                }
                salary_type = sb2 + "";
                ll_select.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_heise));
                llSelectSum.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                if (number > 0) {
                    tv4.setText("要求" + "(" + number + ")");
                    tv41.setText("要求" + "(" + number + ")");
                } else {
                    tv4.setText("要求");
                    tv41.setText("要求");
                }
                offset = "0";
                CardcurrPage = 1;
                currPage = 1;
                setTextColor();
                request();
                Log.d("Debug", "选择的薪资是" + pay_low + "---" + pay_high + "底薪是" + keep_pay);
                break;
            //主播类型确定
            case R.id.tv_commit_cycle:
                if (!isCard) {
                    isChange = true;
                }
                //选中的数量
                int number1 = 0;
                ll_select.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_heise));
                llSelectSum.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);

                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < TypeAdapter.getAll().size(); i++) {
                    if (TypeAdapter.getAll().get(0).isClick()) {

                    } else {
                        if (TypeAdapter.get(i).isClick()) {
                            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                                sb.append(",");
                            }
                            number1++;
                            sb.append(TypeAdapter.getAll().get(i).getId());
                        }
                    }
                }
                plat_id = sb + "";
                //类型选择
                StringBuilder sb1 = new StringBuilder();
                for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                    if (TypeAdapter1.getAll().get(0).isClick()) {

                    } else {
                        if (TypeAdapter1.get(i).isClick()) {
                            if (sb1.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                                sb1.append(",");
                            }
                            number1++;
                            sb1.append(TypeAdapter1.getAll().get(i).getId());
                        }
                    }
                }
                anchor_type = sb1 + "";

                //线上线下选择
                StringBuilder sb3 = new StringBuilder();
                for (int i = 0; i < UpAndDownmAdapter.getAll().size(); i++) {
                    if (UpAndDownmAdapter.get(0).isIsfocks()) {
                    } else {
                        if (UpAndDownmAdapter.get(i).isIsfocks()) {
                            if (sb3.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                                sb3.append(",");
                            }
                            number1++;
                            sb3.append(UpAndDownmAdapter.getAll().get(i).getSymbol());
                        }
                    }

                }
                job_method = sb3 + "";

                if (number1 > 0) {
                    tv2.setText("直播类型" + "(" + number1 + ")");
                    tv21.setText("直播类型" + "(" + number1 + ")");
                } else {
                    tv2.setText("直播类型");
                    tv21.setText("直播类型");
                }
                offset = "0";
                CardcurrPage = 1;
                currPage = 1;
                setTextColor();
                request();
                break;
            case R.id.tv_dismiss:
            case R.id.tv_city_dismiss:
                ll_select.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_heise));
                llSelectSum.setVisibility(View.GONE);
                setTextColor();
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                break;
            case R.id.ll11:
            case R.id.ll1:
                setTextColor();
                if (llSelect1.getVisibility() == View.GONE) {
                    llSelect1.setVisibility(View.VISIBLE);
                }
                if (SelectCity.getVisibility() == View.VISIBLE) {
                    llSelectSum.setVisibility(View.GONE);
                    SelectCity.setVisibility(View.GONE);
                } else {
                    //列表布局 点击筛选  筛选条件自动滑动到上面
                    if (!isCard) {
                        newsEntertainIrv.setSelection(1);
                        llSelect1.setVisibility(View.VISIBLE);
                    }
                    tv_empty_zhanwei.setVisibility(View.GONE);
                    llSelectSum.setVisibility(View.VISIBLE);
                    SelectCity.setVisibility(View.VISIBLE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    tvDismiss.setVisibility(View.VISIBLE);
                    tv1.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im1.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                    tv11.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im11.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;
            case R.id.ll21:
            case R.id.ll2:
                setTextColor();
                if (activity_cycle_select.getVisibility() == View.VISIBLE) {
                    llSelectSum.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                } else {
                    if (!isCard) {
                        newsEntertainIrv.setSelection(1);
                        llSelect1.setVisibility(View.VISIBLE);
                    }
                    tv_empty_zhanwei.setVisibility(View.GONE);
                    llSelectSum.setVisibility(View.VISIBLE);
                    mPresenter.platfromType();
                    initupAndDownDate();
                    SelectCity.setVisibility(View.GONE);
                    tvDismiss.setVisibility(View.VISIBLE);
                    activity_cycle_select.setVisibility(View.VISIBLE);
                    activity_money_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    tv2.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im2.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                    tv21.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im21.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;

            case R.id.ll41:
            case R.id.ll4:
                setTextColor();
                if (activity_money_select.getVisibility() == View.VISIBLE) {
                    llSelectSum.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                } else {
                    if (!isCard) {
                        newsEntertainIrv.setSelection(1);
                        llSelect1.setVisibility(View.VISIBLE);
                    }
                    tv_empty_zhanwei.setVisibility(View.GONE);
                    initMoneyDate();
                    initCycleDate();
                    llSelectSum.setVisibility(View.VISIBLE);
                    tvDismiss.setVisibility(View.VISIBLE);
                    SelectCity.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.VISIBLE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    tv4.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im4.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                    tv41.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im41.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;

            case R.id.ll51:
            case R.id.ll5:
                setTextColor();
                if (activity_zuixin_select.getVisibility() == View.VISIBLE) {
                    llSelectSum.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                } else {
                    if (!isCard) {
                        newsEntertainIrv.setSelection(1);
                        llSelect1.setVisibility(View.VISIBLE);
                    }
                    tv_empty_zhanwei.setVisibility(View.GONE);
                    llSelectSum.setVisibility(View.VISIBLE);
                    tvDismiss.setVisibility(View.VISIBLE);
                    SelectCity.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.VISIBLE);
                    tv5.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im5.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                    tv51.setTextColor(ContextCompat.getColor(getContext(), R.color.color_d0a76c));
                    im51.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;
        }
    }

    private void setTextColor() {
        tv1.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv2.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv4.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv5.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv11.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv21.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv41.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        tv51.setTextColor(ContextCompat.getColor(getContext(), R.color.color_999999));
        im1.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im2.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im4.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im5.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im11.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im21.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im41.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
        im51.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.huise_sanjiao));
    }

    BossCycleAdapter CyclemAdapter;

    /**
     * 初始化结算日期数据
     */
    private void initCycleDate() {
        List<CycleBean> mNewsList1 = new ArrayList<>();
        CycleBean c1;
        if (salary_type.length() > 0) {
            c1 = new CycleBean(4 + "", "全部", false);
        } else {
            c1 = new CycleBean(4 + "", "全部", true);
        }
        CycleBean c2 = new CycleBean(3 + "", "日结", false);
        CycleBean c3 = new CycleBean(2 + "", "周结", false);
        CycleBean c4 = new CycleBean(1 + "", "月结", false);
        mNewsList1.add(c1);
        mNewsList1.add(c2);
        mNewsList1.add(c3);
        mNewsList1.add(c4);
        if (salary_type.length() > 0) {
            String[] split = salary_type.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList1.size(); j++) {
                    if (mNewsList1.get(j).getSymbol().equals(split[i])) {
                        mNewsList1.get(j).setIsfocks(true);
                    }
                }
            }
        }
        if (CyclemAdapter == null) {
            CyclemAdapter = new BossCycleAdapter(getActivity(), R.layout.item_four, mNewsList1);
            CyclemAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            CyclemAdapter.setData(mNewsList1);
        }

        re_cycle_date.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        re_cycle_date.setAdapter(CyclemAdapter);
        CyclemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (CyclemAdapter.get(position).isIsfocks()) {
                    CyclemAdapter.get(position).setIsfocks(false);
                } else {
                    CyclemAdapter.get(position).setIsfocks(true);
                }
                if (CyclemAdapter.get(position).getSymbol().equals("4")) {
                    CyclemAdapter.get(1).setIsfocks(false);
                    CyclemAdapter.get(2).setIsfocks(false);
                    CyclemAdapter.get(3).setIsfocks(false);
                } else {
                    CyclemAdapter.get(0).setIsfocks(false);
                }
                for (int i = 0; i < CyclemAdapter.getSize(); i++) {
                    Log.d("Debug", "有没有选中状态" + CyclemAdapter.get(i).isIsfocks());
                }
                CyclemAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }

    BossCycleAdapter UpAndDownmAdapter;

    /**
     * 线上或是线下
     */
    private void initupAndDownDate() {
        List<CycleBean> mNewsList1 = new ArrayList<>();
        CycleBean c1;
        if (job_method.length() > 0) {
            c1 = new CycleBean(4 + "", "全部", false);
        } else {
            c1 = new CycleBean(4 + "", "全部", true);
        }
        CycleBean c2 = new CycleBean(1 + "", "线上", false);
        CycleBean c3 = new CycleBean(2 + "", "线下", false);
        mNewsList1.add(c1);
        mNewsList1.add(c2);
        mNewsList1.add(c3);

        if (job_method.length() > 0) {
            String[] split = job_method.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList1.size(); j++) {
                    if (mNewsList1.get(j).getSymbol().equals(split[i])) {
                        mNewsList1.get(j).setIsfocks(true);
                    }
                }
            }
        }
        if (UpAndDownmAdapter == null) {
            UpAndDownmAdapter = new BossCycleAdapter(getActivity(), R.layout.item_four, mNewsList1);
            UpAndDownmAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            UpAndDownmAdapter.setData(mNewsList1);
        }

        re_cycle_date_upanddown.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        re_cycle_date_upanddown.setAdapter(UpAndDownmAdapter);
        UpAndDownmAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < UpAndDownmAdapter.getSize(); i++) {
                    UpAndDownmAdapter.get(i).setIsfocks(false);
                }
                UpAndDownmAdapter.get(position).setIsfocks(true);
                UpAndDownmAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }


    BossMoneyAdapter MoneymAdapter;
    BossMoneyAdapter MoneymAdapter1;

    /**
     * 初始化结算日期数据
     */
    private void initMoneyDate() {
        List<BossMoneyBean> mNewsList1 = new ArrayList<>();
        List<BossMoneyBean> mNewsList2 = new ArrayList<>();
//         public BossMoneyBean(String symbol, String name, String low, String high, boolean isfocks) {
        BossMoneyBean c0;
        if (pay_low.length() > 0 || pay_high.length() > 0) {
            c0 = new BossMoneyBean(0, "不限", 0, 0, false);
        } else {
            c0 = new BossMoneyBean(0, "不限", 0, 0, true);
        }
        BossMoneyBean c1 = new BossMoneyBean(1, "3k以下", 0, 3000, false);
        BossMoneyBean c2 = new BossMoneyBean(2, "3k-5k", 3000, 5000, false);
        BossMoneyBean c3 = new BossMoneyBean(3, "5k-10k", 5000, 10000, false);
        BossMoneyBean c4 = new BossMoneyBean(4, "10k-20k", 10000, 20000, false);
        BossMoneyBean c5 = new BossMoneyBean(5, "20k以上", 20000, 50000, false);
        mNewsList1.add(c0);
        mNewsList1.add(c1);
        mNewsList1.add(c2);
        mNewsList1.add(c3);
        mNewsList1.add(c4);
        mNewsList1.add(c5);

        if (pay_low.length() > 0 || pay_high.length() > 0) {
            for (int j = 0; j < mNewsList1.size(); j++) {
                if (pay_low.length() > 0 && pay_high.length() > 0) {
                    if (mNewsList1.get(j).getLow() == Integer.parseInt(pay_low) && mNewsList1.get(j).getHigh() == Integer.parseInt(pay_high)) {
                        mNewsList1.get(j).setIsfocks(true);
                    }
                } else if (pay_high.length() == 0 && mNewsList1.get(j).getHigh() == 3000) {
                    mNewsList1.get(j).setIsfocks(true);
                } else if (pay_low.length() == 0 && mNewsList1.get(j).getLow() == 20000) {
                    mNewsList1.get(j).setIsfocks(true);
                }
            }
        }
        if (MoneymAdapter == null) {
            MoneymAdapter = new BossMoneyAdapter(getActivity(), R.layout.item_four, mNewsList1);
            MoneymAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            MoneymAdapter.setData(mNewsList1);
        }

        reMoney.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        reMoney.setAdapter(MoneymAdapter);
        MoneymAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < MoneymAdapter.getAll().size(); i++) {
                    MoneymAdapter.getAll().get(i).setIsfocks(false);
                }
                MoneymAdapter.get(position).setIsfocks(true);
                et1.setText("");
                et2.setText("");
                MoneymAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });


        BossMoneyBean c8 = new BossMoneyBean(0, "不限", 0, 0, true);
        BossMoneyBean c9 = new BossMoneyBean(1, "有底薪", 0, 3000, false);
        BossMoneyBean c10 = new BossMoneyBean(2, "无底薪", 3000, 5000, false);
        mNewsList2.add(c8);
        mNewsList2.add(c9);
        mNewsList2.add(c10);

        if (keep_pay.equals("0")) {
            mNewsList2.get(0).setIsfocks(true);
            mNewsList2.get(1).setIsfocks(false);
            mNewsList2.get(2).setIsfocks(false);
        } else if (keep_pay.equals("1")) {
            mNewsList2.get(0).setIsfocks(false);
            mNewsList2.get(1).setIsfocks(true);
            mNewsList2.get(2).setIsfocks(false);
        } else {
            mNewsList2.get(0).setIsfocks(false);
            mNewsList2.get(1).setIsfocks(false);
            mNewsList2.get(2).setIsfocks(true);
        }

        if (MoneymAdapter1 == null) {
            MoneymAdapter1 = new BossMoneyAdapter(getActivity(), R.layout.item_four, mNewsList2);
            MoneymAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            MoneymAdapter1.setData(mNewsList2);
        }
        reMoney1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        reMoney1.setAdapter(MoneymAdapter1);

        MoneymAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < MoneymAdapter1.getAll().size(); i++) {
                    MoneymAdapter1.getAll().get(i).setIsfocks(false);
                }
                MoneymAdapter1.get(position).setIsfocks(true);
                MoneymAdapter1.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });


    }


    /**
     * 地址选择数据初始化
     */
    private void initCityDate() {
        AddressSelector selector = new AddressSelector(getActivity());
        //获取地址管理数据库
        selector.setTextSize(13);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
        selector.setIndicatorBackgroundColor(getActivity().getResources().getColor(R.color.white));//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景
        selector.setTextSelectedColor(getActivity().getResources().getColor(R.color.white));//设置字体获得焦点的颜色
        selector.setTextUnSelectedColor(android.R.color.holo_orange_light);//设置字体没有获得焦点的颜色
////        //获取数据库管理
        AddressDictManager addressDictManager = selector.getAddressDictManager();
        AdressBean.ChangeRecordsBean changeRecordsBean = new AdressBean.ChangeRecordsBean();
        changeRecordsBean.parentId = 0;
        changeRecordsBean.name = "";
        changeRecordsBean.id = 35;
        addressDictManager.inserddress(changeRecordsBean);//对数据库里增加一个数据
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                        (street == null ? "" : street.name);
                city1 = city == null ? "" : city.name;
                county1 = county == null ? "" : county.name;
                Log.d("Debug", "返回的地址信息是" + s);
                ll_select.setBackground(getActivity().getResources().getDrawable(R.mipmap.bg_heise));
                llSelectSum.setVisibility(View.GONE);
                area_name = city1;
                tv1.setText(area_name);
                tv11.setText(area_name);
                isChange = true;
                setTextColor();
                request();
            }
        });
        View view = selector.getView();
        content.addView(view);
    }

    anchorListAdapter listAdapter;

    /**
     * 招聘列表接口
     *
     * @param mAccountDataBean
     */
    @Override
    public void recruitList(BossDataBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setIsresume(mAccountDataBean.getIs_resume() + "");
        UserConfig.getInstance().setResumeTelephone(mAccountDataBean.getResume_telephone());
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        mPageNum = mAccountDataBean.getList().getLast_page();
        Log.d("Debug", "当前的页数为" + mPageNum);
        is_resume = mAccountDataBean.getIs_resume();
        if (ACTION_TYPE == ACTION_FRESH) {
            if (mNewsList.size() > 0) {
                mNewsList.clear();
            }
            //添加广告位
            for (int i = 0; i < mAccountDataBean.getRecruit().size(); i++) {
                mNewsList.add(mAccountDataBean.getRecruit().get(i));
            }

            for (int i = 0; i < mAccountDataBean.getList().getData().size(); i++) {
                mNewsList.add(mAccountDataBean.getList().getData().get(i));
            }
            mAdapter.setData(mNewsList);
            Log.d("Debug", "qingqiu shuju  操作");
            if (mAccountDataBean.getList().getData().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                llSelectSum.setVisibility(View.GONE);
                tv_empty_zhanwei.setVisibility(View.GONE);
            } else {
                llSelectSum.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
                tv_empty_zhanwei.setVisibility(View.VISIBLE);
                rl_card.setVisibility(View.GONE);
                rl_list.setVisibility(View.VISIBLE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                tv_40dp.setVisibility(View.VISIBLE);
            }
        } else {
            mAdapter.addAllAt(mNewsList.size(), mAccountDataBean.getList().getData());
        }
        mAdapter.notifyDataSetChanged();
        mNewsList = mAdapter.getAll();
    }

    @Override
    public void NewrecruitList(newBossDataBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPageNum = mAccountDataBean.getTotal_page();
        card_recycler_view.setVisibility(View.VISIBLE);
        tv_empty_zhanwei.setVisibility(View.GONE);
        if (mAccountDataBean.getRecruit().size() > 0) {
            activity_empty_linshi.setVisibility(View.GONE);
            llSelectSum.setVisibility(View.GONE);
        } else {
            llSelectSum.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
            rl_card.setVisibility(View.VISIBLE);
            rl_list.setVisibility(View.GONE);
            SelectCity.setVisibility(View.GONE);
            activity_cycle_select.setVisibility(View.GONE);
            activity_money_select.setVisibility(View.GONE);
            tv_40dp.setVisibility(View.GONE);

        }
        if (CardcurrPage == 1) {
            mDatas.clear();
        }
        listAdapter.addAll(mAccountDataBean.getRecruit());
        mDatas = listAdapter.getAll();
    }


    //    发布招聘总数
    private int send_count;
    //    免费发布职位数
    private int free_send_recruit;
    //    发布职位上限数
    private int send_recruit_upper;
    //    发布职位消耗波豆数
    private int send_recruit_bd;
    //    可用波豆数
    private int usable_bd;

    /**
     * 职位列表请求接口  （对点击志职位发布做判断的）
     *
     * @param resultBean
     */
    @Override
    public void positionList(PositionListBean resultBean) {
        send_count = resultBean.getSend_count();
        free_send_recruit = resultBean.getFree_send_recruit();
        send_recruit_upper = resultBean.getSend_recruit_upper();
        send_recruit_bd = resultBean.getSend_recruit_bd();
        usable_bd = resultBean.getUsable_bd();
        if (send_count >= send_recruit_upper) {
            ToastUtil.showShort("职位发布数量已用完，暂不能发布新的职位");
        } else {
            if (send_count >= free_send_recruit) {
                if (usable_bd >= send_recruit_bd) {
                    mPopWindowPhone3(2);
                } else {
                    mPopWindowPhone3(1);
                }
            } else {
                startActivity(new Intent(getActivity(), AddJobActivity.class));
            }
        }
    }

    BossPlatAdapter TypeAdapter = null;
    BossPlatAdapter TypeAdapter1 = null;
    List<BossPlatBean.PlatfromBean> mNewsList1;
    List<BossPlatBean.PlatfromBean> mNewsList2;

    /**
     * 直播平台、直播类型接口
     *
     * @param mAccountDataBean
     */
    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {
        mNewsList1 = new ArrayList<>();
        mNewsList2 = new ArrayList<>();
        if (plat_id.length() > 0) {
            mNewsList1.add(new BossPlatBean.PlatfromBean("全部", false));
        } else {
            mNewsList1.add(new BossPlatBean.PlatfromBean("全部", true));
        }
        for (int i = 0; i < mAccountDataBean.getPlatfrom().size(); i++) {
            mNewsList1.add(mAccountDataBean.getPlatfrom().get(i));
        }
        if (plat_id.length() > 0) {
            String[] split = plat_id.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 1; j < mNewsList1.size(); j++) {
                    if (mNewsList1.get(j).getId().equals(split[i])) {
                        mNewsList1.get(j).setClick(true);
                    }
                }
            }
        }
        List<BossPlatBean.PlatfromBean> date = new ArrayList<>();
        if (llplatshow) {
            for (int i = 0; i < mNewsList1.size(); i++) {
                date.add(mNewsList1.get(i));
            }
            llPlatText.setText("收起");
            llPlatImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangshang_jiantou));
            llplatshow = true;
        } else {
            if (mNewsList1.size() >= 8) {
                for (int i = 0; i < 8; i++) {
                    date.add(mNewsList1.get(i));
                }
            } else {
                for (int i = 0; i < mNewsList1.size(); i++) {
                    date.add(mNewsList1.get(i));
                }
            }
            llPlatText.setText("更多");
            llPlatImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangxia_jiantou));

            llplatshow = false;
        }
        if (TypeAdapter == null) {
            TypeAdapter = new BossPlatAdapter(getActivity(), R.layout.item_four, date);
            TypeAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter.setData(date);
        }


        reCycle.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        reCycle.setAdapter(TypeAdapter);

        TypeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);


                BossPlatBean.PlatfromBean bean = (BossPlatBean.PlatfromBean) view.getTag(R.id.tag_second); //得到每个item绑定的数据
                if (bean.getPlat_name().equals("全部")) {
                    if (bean.isClick()) {
                        bean.setClick(false);
                    } else {
                        bean.setClick(true);
                        for (int i = 1; i < TypeAdapter1.getAll().size(); i++) {
                            bean.setClick(false);
                        }
                    }
                } else {
                    if (bean.isClick()) {
                        bean.setClick(false);
                    } else {
                        bean.setClick(true);
                    }
                    TypeAdapter1.get(0).setClick(false);
                }
                TypeAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });


        if (anchor_type.length() > 0) {
            mNewsList2.add(new BossPlatBean.PlatfromBean("全部", false));
        } else {
            mNewsList2.add(new BossPlatBean.PlatfromBean("全部", true));
        }
        for (int i = 0; i < mAccountDataBean.getRecuitType().size(); i++) {
            BossPlatBean.RecuitTypeBean recuitTypeBean = mAccountDataBean.getRecuitType().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(recuitTypeBean.getId(), recuitTypeBean.getType_name(), recuitTypeBean.getStatus(), false);
            mNewsList2.add(platfromBean);
        }
        if (anchor_type.length() > 0) {
            String[] split = anchor_type.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 1; j < mNewsList2.size(); j++) {
                    if (mNewsList2.get(j).getId().equals(split[i])) {
                        mNewsList2.get(j).setClick(true);
                    }
                }
            }
        }
        List<BossPlatBean.PlatfromBean> date1 = new ArrayList<>();
        if (lltypeshow) {
            for (int i = 0; i < mNewsList2.size(); i++) {
                date1.add(mNewsList2.get(i));
            }
            llTypeText.setText("收起");
            llTypeImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangshang_jiantou));
            lltypeshow = true;
        } else {
            if (mNewsList2.size() >= 8) {
                for (int i = 0; i < 8; i++) {
                    date1.add(mNewsList2.get(i));
                }
            } else {
                for (int i = 0; i < mNewsList2.size(); i++) {
                    date1.add(mNewsList2.get(i));
                }
            }
            llTypeText.setText("更多");
            llTypeImage.setImageDrawable(getResources().getDrawable(R.mipmap.zhxiangxia_jiantou));
            lltypeshow = false;
        }


        if (TypeAdapter1 == null) {
            TypeAdapter1 = new BossPlatAdapter(getActivity(), R.layout.item_four, date1);
            TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter1.setData(date1);
        }

        re_cycle_type.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        re_cycle_type.setAdapter(TypeAdapter1);

        TypeAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                BossPlatBean.PlatfromBean bean = (BossPlatBean.PlatfromBean) view.getTag(R.id.tag_second); //得到每个item绑定的数据
                if (bean.getPlat_name().equals("全部")) {
                    if (bean.isClick()) {
                        bean.setClick(false);
                    } else {
                        bean.setClick(true);
                        for (int i = 1; i < TypeAdapter1.getAll().size(); i++) {
                            bean.setClick(false);
                        }
                    }
                } else {
                    if (bean.isClick()) {
                        bean.setClick(false);
                    } else {
                        bean.setClick(true);
                    }
                    TypeAdapter1.get(0).setClick(false);
                }
                TypeAdapter1.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }

    /**
     * 抢ta会长抢主播
     *
     * @param mAccountDataBean
     */
    @Override
    public void grabAnchor(final GrabAnchorBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        RobHimeDialog = WelfareDialog.RobHimeDialog(getActivity(), mAccountDataBean, yuliaoItemDate.getNickname(), yuliaoItemDate.getType_name(), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断波币够不够
                //        是否有活动 1是 2否
                if (mAccountDataBean.getIs_activity() == 1) {
                    //每天找抢ta 超过次数  活动图片以及文字隐藏(就和没活动处理一样)
                    if (mAccountDataBean.getXh_number() >= mAccountDataBean.getActivity().getNumber_days()) {
                        noActivity(mAccountDataBean);
                    } else {
                        if (mAccountDataBean.getUsable_bd() >= mAccountDataBean.getActivity().getG_price()) {
//                            抢主播支付波豆（活动）
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(getActivity().getSupportFragmentManager());
                            mPresenter.grabAnchorOrder(yuliaoresume_id + "", 1 + "", mAccountDataBean.getActivity().getId() + "", mAccountDataBean.getActivity().getG_price() + "");
                        } else {
//                            没有波币
                            mPopWindowPhone3(1);
                            RobHimeDialog.dismiss();
                        }
                    }
                } else {
                    noActivity(mAccountDataBean);
                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RobHimeDialog.dismiss();
            }
        });
    }

    /**
     * 不是活动做的处理方式
     */
    public void noActivity(GrabAnchorBean mAccountDataBean) {
        if (mAccountDataBean.getUsable_bd() >= mAccountDataBean.getXf_bd()) {
//                            抢主播支付波豆
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.grabAnchorOrder(yuliaoresume_id + "", 2 + "", "0", mAccountDataBean.getXf_bd() + "");
        } else {
//                            没有波币
            mPopWindowPhone3(1);
            RobHimeDialog.dismiss();
        }
    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        RobHimeDialog.dismiss();
        //        约聊ID 为0时不跳转约聊界面
        Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
        intent.putExtra("chat_id", mAccountDataBean.getChat_id() + "");
        startActivity(intent);
        //抢ta状态刷新
        BossBean bossBean = new BossBean();
        bossBean.setRefush(true);
        bossBean.setQiangTa(true);
        bossBean.setRecruit_id(yuliaoItemDate.getId() + "");
        bossBean.setChat_id(mAccountDataBean.getChat_id());
        EventBus.getDefault().post(bossBean);
    }

    @Override
    public void resumeAllList(AllListBean resultBean) {

    }

    /**
     * 主播招聘列表导航接口
     *
     * @param mAccountDataBean
     */

    @Override
    public void anchorRecruitList(anchorRecruitListBean mAccountDataBean) {
        //        主播招聘列表导航
        rv_cultivate.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        cultivateAdapter = new anchorRecruitListAdapter(getActivity(), R.layout.cultivate_item1, list);
//        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_cultivate.setAdapter(cultivateAdapter);
        cultivateAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//                1全部职位 2标签 3官方 4日周结
                if (PointUtils.isFastClick()) {
                    anchorRecruitListBean.ListBean listBean = cultivateAdapter.get(position);
                    String type = listBean.getType();
                    String name = listBean.getName();
                    String anchor_type = listBean.getAnchor_type();
                    Intent intent = new Intent(getActivity(), InviteTypeActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("name", name);
                    intent.putExtra("anchor_type", anchor_type);
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        cultivateAdapter.addAll(mAccountDataBean.getList());
        list = cultivateAdapter.getAll();
    }

    //点击我要报名  点击的是第几个公告  返回来 到相对应的位置
    int symbolPosition = 0;
    boolean isfromAddRefysh = false;

    /**
     * 招募首页接口
     *
     * @param mAccountDataBean
     */

    List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentAdapter fragmentAdapter;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;


    @Override
    public void conscribeIndex(BossTongGaoBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (fragments.size() > 0) {
            fragments.clear();
        }
        List<String> channelNames = new ArrayList<>();
        channelNames.add("全部");
        channelNames.add("热门");
        channelNames.add("最新");
        channelNames.add("未沟通");

        fragments.add(new BossResumeAllListFreagment().getInstance(""));
        fragments.add(new BossResumeAllListFreagment().getInstance("hot"));
        fragments.add(new BossResumeAllListFreagment().getInstance("new"));
        fragments.add(new BossResumeAllListFreagment().getInstance("onlink"));

        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getActivity().getSupportFragmentManager(), fragments, channelNames);
        }
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
    }

    wantListBean mAccountDataBean1;
    List<wantListBean.ResumesBean> leftDate = new ArrayList<>();

    /**
     * 获取求职列表
     *
     * @param mAccountDataBean
     */
//    true 为向上 false 为向下
    boolean upandDown = false;
    int oldposition;
    int newposition;
    ScrollLinearLayoutManager scrollLayoutManager;
    AnimationDrawable animation1 = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void wantList(wantListBean mAccountDataBean) {

        mAccountDataBean1 = mAccountDataBean;
        rl_lookjob_springview.onFinishFreshAndLoad(); //停止加载
        lookjobtotal_page = mAccountDataBean.getTotal_page();
        leftDate = new ArrayList<>();
        Log.d("Debug", "请求借口成功");
        for (int i = 0; i < mAccountDataBean1.getResumes().size(); i++) {
            wantListBean.ResumesBean resumesBean = mAccountDataBean1.getResumes().get(i);
            leftDate.add(resumesBean);
            Log.d("Debug", "到达循环里面");
        }
        Log.d("Debug", "到达这里数据长度为" + leftDate.size());
        if (lookjobcurrPage == 1) {
            if (mAccountDataBean1.getResumes().size() > 0) {
//                LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                scrollLayoutManager = new ScrollLinearLayoutManager(getActivity(), ScrollLinearLayoutManager.VERTICAL, false);
                //预加载功能实现（显示的是当前的item，将下一个item加载好，解决 滑动的时候下面item黑屏的问题）
                scrollLayoutManager.setItemPrefetchEnabled(true);

                look_job_left.setLayoutManager(scrollLayoutManager);
                //设置每次滑动一张图片   true为滑动一张  false 快速滑动 可以滑动过好几张图片
                look_job_left.setSinglePageFling(true);

                LookJobleftAdapter = new ChairLookJob(getActivity(), R.layout.look_job_item, leftDate, new ChairLookJob.RightToLeft() {
                    @Override
                    public void goTo(int old, int pos) {
                        Log.e(TAG, old + "现在的位置是: " + pos);
                        if (scrollLayoutManager != null)
                            startRecycViewScroll();
                        rl_right_to_left.setVisibility(View.GONE);
                        RIGHT_TO_LEFT = true;
                    }
                });
                look_job_left.setAdapter(LookJobleftAdapter);
                look_job_left.addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        LinearLayoutManager l = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int itemPos = l.findFirstVisibleItemPosition();
                        Log.e(TAG, "现在的位置是: " + itemPos + "滑动状态" + UserConfig.getInstance().getIsScrollShow());
                        if (UserConfig.getInstance().getIsScrollShow().equals("0")) {
                            if (itemPos >= 1) {
                                if (animation1 != null && animation1.isRunning()) {
                                    rl_down_to_up.setVisibility(View.GONE);
                                    DOWN_TO_UP = true;
                                    rl_right_to_left.setVisibility(View.VISIBLE);
                                    iv_right_to_left.setImageResource(R.drawable.animlis);
                                    AnimationDrawable animation1 = (AnimationDrawable) iv_right_to_left.getDrawable();
                                    animation1.start();
                                }
                                UserConfig.getInstance().pwdIsScrollShow("1");
                            }
                        }
                        //得到当前显示的最后一个item的view
                        View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                        //得到lastChildView的bottom坐标值
                        int lastChildBottom = lastChildView.getBottom();
                        //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                        int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                        //通过这个lastChildView得到这个view当前的position值
                        int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
                        //判断lastChildView的bottom值跟recyclerBottom
                        //判断lastPosition是不是最后一个position
                        //如果两个条件都满足则说明是真正的滑动到了底部
                        if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                            Toast.makeText(RecyclerActivity.this, "滑动到底了", Toast.LENGTH_SHORT).show();
                            isLastSelectjobRow = true;
                        }

                    }

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        //滑动到最底部做分页记载操作
                        if (isLastSelectjobRow) {
                            if (lookjobcurrPage < lookjobtotal_page) {
                                lookjobcurrPage++;
                                mPresenter.wantList(lookjobcurrPage + "");
                            } else {
                                ToastUtil.showShort("已加载全部");
                                rl_lookjob_springview.onFinishFreshAndLoad(); //停止加载
                            }
                            isLastSelectjobRow = false;
                        }
                    }

                });
                LookJobleftAdapter.setData(leftDate);
            } else {
                Log.d("Debug", "展位图显示");
            }
        } else {
            LookJobleftAdapter.addAll(leftDate);
        }
    }

    @Override
    public void url(BossPlatBean mAccountDataBean) {

    }

    /**
     * 卡片向右收藏
     */
    @Override
    public void doCollect() {
        ToastUtil.showShort("收藏成功");
    }

    @Override
    public void noLike() {
        listAdapter.getAll().remove(nolikePosition);
        listAdapter.notifyDataSetChanged();
        mDatas = listAdapter.getAll();
    }

    @Override
    public void cancelCollect() {
        ToastUtil.showShort("取消收藏成功");
    }

    @Override
    public void sendSucceed(sendSucceedResonse resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("发送成功");

        if (phonePopWindow != null) {
            if (phonePopWindow.isShowing()) {
                phonePopWindow.dismiss();
            }
        }
        if (mCardPopWindow1 != null) {
            if (mCardPopWindow1.isShowing()) {
                mCardPopWindow1.dismiss();
            }
        }
//        约聊ID 为0时不跳转约聊界面
        if (resultBean.chat_id > 0) {
            Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
            intent.putExtra("chat_id", resultBean.getChat_id() + "");
            getActivity().startActivity(intent);
        }

    }

    /**
     * 会长约聊剩余次数
     *
     * @param resultBean
     */
    @Override
    public void guildNumber(sendSucceedResonse resultBean) {
        if (resultBean.count > 0) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.chatAnchor(yuliaoresume_id, 1 + "");
        } else {
            ToastUtil.showShort("每日只可约聊" + resultBean.getAll_total() + "位主播呢");
        }
    }

    /**
     * 会长约聊主播接口
     *
     * @param resultBean
     */
    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        //        约聊ID 为0时不跳转约聊界面
        Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
        intent.putExtra("chat_id", resultBean.getChat_id());
        startActivity(intent);
    }

    @Override
    public void readPhoto(ReadPhotoBean resultBean) {
        if (PhoneList.size() > 0) {
            PhoneList.clear();
        }
        PhoneList = resultBean.getResume();
        mPresenter.resumeIndex();
    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        resume = resultBean.getResume();
        showCardPopupWindow1();
    }

    @Override
    public void anchorShare() {
        //分享成功以后的弹窗
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.surplusMakeCall(id);
    }

    @Override
    public void anchorMakeTelephone() {

    }

    @Override
    public void makeCall(BanagetBean resultBean) {
        Log.d("Debug", "返回拨打电话次数为" + resultBean.getNumber());
        //刷新列表   变成已沟通
        if (resultBean.getIs_call() == 1) {
            showPopupWindow1();
        } else {
            if (resultBean.getNumber() > 0) {
                showPopupWindow1();
            } else {
                showPopupWindow(2);
            }
        }
    }

    /**
     * 分享成功剩余的拨打电话的次数
     */
    int number;

    @Override
    public void surplusMakeCall(BanagetBean resultBean) {
        if (resultBean.getNumber() <= 0) {
            number = 0;
        } else {
            number = resultBean.getNumber();
        }
        showPopupWindow(3);
    }

    @Override
    public void getCollect(CollectBean resultBean) {

    }

    @Override
    public void recruitBanner(final BanagetBean resultBean) {
        //滚动图
        new BossTurn(getActivity(), resultBean.getBanner(), mBanner, new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                resultBean.getBanner().get(position).getAudio_url()
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getToken().length() == 0) {
                        StartActivityUtils.startActivity();
                        return;
                    }
                    if (resultBean.getBanner().get(position).getRelation_url().length() > 0) {
                        if (resultBean.getBanner().get(position).getRelation_url().contains("single/lottery")) {
                            //跳转大转盘
                            startActivity(new Intent(getActivity(), ShareWebView.class).putExtra("url", resultBean.getBanner().get(position).getRelation_url() + ""));
                        } else {
//                            1招聘信息 2其他
                            if (resultBean.getBanner().get(position).getType() == 1) {
                                Intent intent = new Intent(getActivity(), AnchorBossWebView2.class);
                                intent.putExtra("need_id", resultBean.getBanner().get(position).getId() + "");
                                getActivity().startActivity(intent);
                            } else {
                                Intent intent = new Intent(getActivity(), symbolWebView.class);
                                intent.putExtra("url", resultBean.getBanner().get(position).getBanner_url());
                                startActivity(intent);
                            }
                        }
                    }


                }
            }
        });
    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗
     */
    private void showPopupWindowSevenDays(String s) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.add_boss_info, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_content.setText(s + "");

        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);


//        //显示PopupWindow
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + tv1.getText().toString());
                intent.setData(data);
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
            }
        });
        tv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(tv2.getText().toString() + ""); // 复制
                ToastUtil.showShort("已复制成功");
                mPopWindowSelectdays.dismiss();
            }
        });
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.new_fragment_boss, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 主播列表练级拨电话回调方法
     *
     * @param phone
     */
    //传过来电话
    String yuliuPhone;
    //传过来名字
    String name1;
    //zhuboid
    String anchor_id1;
    //招聘id
    String resume_id1;
    //1是已沟通  0是没有沟通
    String is_linkup1;
    //对那个item做操作
    int position1;

    @Override
    public void TakePhone(int position, String phone, String name, String telphone, String anchor_id, String resume_id, String is_linkup) {
        Log.d("Debug", "拨打电话操作");
        position1 = position;
        yuliuPhone = phone;
        name1 = name;
        anchor_id1 = anchor_id;
        resume_id1 = resume_id;
        is_linkup1 = is_linkup;
        Log.d("Debug", "点击拨打电话有没有沟通过的" + is_linkup1);
        isLiuDainhua = false;
        mPresenter.companyAdd(2);
    }


    //简历收藏适配器器的位置  请求完以后刷新用到
    int SelectPosition;


    @Override
    public void Takecllock(String position, int is_collect, String resume_id) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());
        SelectPosition = Integer.parseInt(position);
//        是否收藏 1是 2否
        if (is_collect == 1) {
            mPresenter.resumeCancelCollect(resume_id);
        } else {
            mPresenter.resumeDoCollect(resume_id);
        }
        ToastUtil.showShort("收藏操作" + position + "有没有收藏" + is_collect);
    }

    /**
     * 招聘点击刷新
     */
    @Override
    public void refush() {
//        look_job_left.smoothScrollToPosition(0);
        lookjobcurrPage = 1;
        mPresenter.wantList(lookjobcurrPage + "");
    }

    /**
     * 点击约聊
     *
     * @param anchor_id
     */
    //点击约聊传过来的id
    String yuliaoresume_id = "";
    //点击约聊判断
    boolean IsfromYueliao;
    //    是否约聊过 1是 0否
    int ischat;
    //    注册好礼领取弹窗
    AlertDialog RobHimeDialog;

    wantListBean.ResumesBean yuliaoItemDate = new wantListBean.ResumesBean();

    @Override
    public void yueliao(String resume_id, int is_chat, wantListBean.ResumesBean bean) {
        Log.d("Debug", "返回的chatid" + is_chat);
        if (bean.getIs_rob() > 0) {
            Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
            intent.putExtra("chat_id", bean.getChat_id() + "");
            startActivity(intent);
        } else {
            yuliaoresume_id = resume_id;
            ischat = is_chat;
            yuliaoItemDate = bean;
            IsfromYueliao = true;
            mPresenter.companyAdd(2);
        }
    }

    @Override
    public void yueliao(String resume_id, int is_chat, AllListBean.ListBean bean) {

    }

    //点击不喜欢位置的下标
    private int nolikePosition;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class anchorListAdapter extends BaseListAdapter<newBossDataBean.DataBean> {
        private Context mContext;
        private Animation mLikeAnim;

        public anchorListAdapter(Context context, int layoutId, List<newBossDataBean.DataBean> datas) {
            super(context, layoutId, datas);
            mContext = context;
            mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        }

        @Override
        public void convert(final ViewHolder holder, final newBossDataBean.DataBean dataBean) {
            RelativeLayout rl_card_info = (RelativeLayout) holder.itemView.findViewById(R.id.rl_card_info);

            ImageView im_is_goutong = (ImageView) holder.itemView.findViewById(R.id.im_is_goutong);
            ImageView im_company_image = (ImageView) holder.itemView.findViewById(R.id.im_company_image);
            TextView tv_yes_text = (TextView) holder.itemView.findViewById(R.id.tv_yes_text);
            TextView tv_active_status = (TextView) holder.itemView.findViewById(R.id.tv_active_status);
            if (dataBean.getActive_status().length() > 0) {
                tv_active_status.setVisibility(View.VISIBLE);
                tv_active_status.setText(dataBean.getActive_status() + "");
            } else {
                tv_active_status.setVisibility(View.GONE);
            }

            if (dataBean.getCompanyInfo().getLogo() != null) {
                Glide.with(getActivity())
                        .load(dataBean.getCompanyInfo().getLogo() + "")
                        .placeholder(R.mipmap.zbzp_qylogo)
                        .crossFade()
                        .transform(new CornersTransform(mContext, 10))
                        .into(im_company_image);
            }
            if (dataBean.getIs_collect().equals("1")) {
                im_is_goutong.setVisibility(View.VISIBLE);
                tv_yes_text.setText("取消收藏");
            } else {
                im_is_goutong.setVisibility(View.GONE);
                tv_yes_text.setText("收藏");
            }
            final ImageView im_click = (ImageView) holder.itemView.findViewById(R.id.im_click);
            if (dataBean.isFlag()) {
                im_click.setVisibility(View.VISIBLE);
            } else {
                im_click.setVisibility(View.GONE);
            }


            ImageView im_is_vip = holder.getView(R.id.im_is_vip);
            //是不是vip
            if (dataBean.getIs_vip() == 1) {
                im_is_vip.setVisibility(View.VISIBLE);
            } else {
                im_is_vip.setVisibility(View.GONE);
            }

//            card_recycler_view.getAdapter().g
            ImageView im_is_hot = (ImageView) holder.itemView.findViewById(R.id.im_is_hot);
            if (dataBean.getHot().equals("1")) {
                im_is_hot.setVisibility(View.VISIBLE);
            } else {
                im_is_hot.setVisibility(View.GONE);
            }
            TextView tv_name_type = (TextView) holder.itemView.findViewById(R.id.tv_name_type);
            TextView tv_time = (TextView) holder.itemView.findViewById(R.id.tv_time);

            try {
                String s = TimeUtil.longToString(Long.parseLong(dataBean.getPublish_time() + ""), "yyyy.MM.dd");
                tv_time.setText(s + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dataBean.getTitle().length() > 0) {
                tv_name_type.setText(dataBean.getTitle() + "");
            } else {
                tv_name_type.setText(dataBean.getType_name());
            }

            //底薪
            TextView tv_low_money = (TextView) holder.itemView.findViewById(R.id.tv_low_money);
            if (Double.parseDouble(dataBean.getKeep_pay()) == 0) {
                tv_low_money.setText("2K");
            } else {
                if (Double.parseDouble(dataBean.getKeep_pay()) >= 1000) {
                    tv_low_money.setText(Integer.parseInt(dataBean.getKeep_pay()) / 1000 + "K");
                } else {
                    tv_low_money.setText(Integer.parseInt(dataBean.getKeep_pay()) + "");
                }
                tv_low_money.setVisibility(View.VISIBLE);
            }
            //直播平台
            TextView tv_plat_name = (TextView) holder.itemView.findViewById(R.id.tv_plat_name);
            if (dataBean.getPlat_name().size() == 0) {
                tv_plat_name.setText("未知平台");
            } else {
                tv_plat_name.setText(dataBean.getPlat_name().get(0).getPlat_name());
            }
            //线上或是线下
            TextView tv_upanddown = (TextView) holder.itemView.findViewById(R.id.tv_upanddown);
            if (dataBean.getJob_method() != null) {
                tv_upanddown.setVisibility(View.VISIBLE);
                switch (dataBean.getJob_method()) {
                    case "1":
                        tv_upanddown.setText("线上");
                        break;
                    case "2":
                        tv_upanddown.setText("线下");
                        break;
                    case "3":
                        tv_upanddown.setText("全部");
                        break;
                }
            } else {
                tv_upanddown.setVisibility(View.GONE);
            }
            //结算方式
            TextView tv_money_type = (TextView) holder.itemView.findViewById(R.id.tv_money_type);
            if (dataBean.getSalary_type() != null) {
                tv_money_type.setVisibility(View.VISIBLE);
                switch (dataBean.getSalary_type()) {
                    case "1":
                        tv_money_type.setText("月结");
                        break;
                    case "2":
                        tv_money_type.setText("周结");
                        break;
                    case "3":
                        tv_money_type.setText("日结");
                        break;
                    case "4":
                        tv_money_type.setText("全部");
                        break;
                }
            } else {
                tv_money_type.setVisibility(View.GONE);
            }
            //薪资
            TextView tv_money = (TextView) holder.itemView.findViewById(R.id.tv_money);
            tv_money.setText(Integer.parseInt(dataBean.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(dataBean.getPay_high()) / 1000 + "K");
            //福利
            TextView tv_fuli = (TextView) holder.itemView.findViewById(R.id.tv_fuli);
            if (dataBean.getWelfare().size() == 0) {
                tv_fuli.setText("");
            } else {
                if (dataBean.getWelfare().size() == 1) {
                    tv_fuli.setText(dataBean.getWelfare().get(0));
                } else {
                    tv_fuli.setText(dataBean.getWelfare().get(0) + " | " + dataBean.getWelfare().get(1));
                }
            }
            //亮点
            TextView tv_light_point = (TextView) holder.itemView.findViewById(R.id.tv_light_point);
            if (dataBean.getLightspot() == null || dataBean.getLightspot().length() == 0) {
                tv_light_point.setText("无");
            } else {
                tv_light_point.setText(dataBean.getLightspot());
            }
            //区域地址
            TextView tv_adress = (TextView) holder.itemView.findViewById(R.id.tv_adress);
            tv_adress.setText(dataBean.getWork_position());
            //公司名称
            TextView tv_company_name = (TextView) holder.itemView.findViewById(R.id.tv_company_name);
            tv_company_name.setText(dataBean.getCompanyInfo().getCompany_short_name() + "");
//        发布类型 1系统（爬取的）2 官方（手动发布）
            ImageView tv_is_guanfang = (ImageView) holder.itemView.findViewById(R.id.tv_is_guanfang);
            if (dataBean.getPublish_type() == 3) {
                tv_is_guanfang.setVisibility(View.VISIBLE);
            } else {
                tv_is_guanfang.setVisibility(View.GONE);
            }

            ImageView im_no = (ImageView) holder.itemView.findViewById(R.id.im_no);
            ImageView im_phone = (ImageView) holder.itemView.findViewById(R.id.im_phone);
            ImageView im_yes = (ImageView) holder.itemView.findViewById(R.id.im_yes);
            //不喜欢点击
            im_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserConfig.getInstance().getToken().length() == 0) {
                        StartActivityUtils.startActivity();
                        return;
                    }
                    if (UserConfig.getInstance().getRole().equals("0")) {
                        StartActivityUtils.startSelectRoleActivity(getActivity());
                        return;
                    }
                    //点击不喜欢位置的下标
                    nolikePosition = holder.getPosition();
                    listAdapter.get(nolikePosition).setFlag(true);
                    listAdapter.notifyDataSetChanged();
                    im_click.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.bukaolv_2));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.noLike(dataBean.getId());
                        }
                    }, 500);
                }
            });
            //电话号码点击
            im_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserConfig.getInstance().getToken().length() == 0) {
                        StartActivityUtils.startActivity();
                        return;
                    }
                    if (UserConfig.getInstance().getRole().equals("0")) {
                        StartActivityUtils.startSelectRoleActivity(getActivity());
                        return;
                    }
                    url = dataBean.getInfo_url();
                    company = dataBean.getCompany();
                    type_name = dataBean.getType_name();
                    id = dataBean.getId();
                    phone = dataBean.getContact_phone();
                    Sharepay_low = dataBean.getPay_low();
                    Sharepay_high = dataBean.getPay_high();
                    work_position = dataBean.getWork_position();
                    Log.d("Debug", "qq为" + dataBean.getQq() + "微信为" + dataBean.getWeixin() + "邮箱为" + dataBean.getEmail());
                    qq = dataBean.getQq();
                    weixin = dataBean.getWeixin();
                    email = dataBean.getEmail();

                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    mPresenter.resumeState();
                }
            });
            //收藏点击事件
            im_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UserConfig.getInstance().getToken().length() == 0) {
                        StartActivityUtils.startActivity();
                        return;
                    }
                    if (UserConfig.getInstance().getRole().equals("0")) {
                        StartActivityUtils.startSelectRoleActivity(getActivity());
                        return;
                    }
                    Log.d("Debug", "收藏点击事件");
                    if (dataBean.getIs_collect().equals("1")) {
//                        listAdapter.get(holder.getPosition()).setFlag(false);
                        listAdapter.get(holder.getPosition()).setIs_collect("0");
                        listAdapter.notifyDataSetChanged();
                        mPresenter.cancelCollect(dataBean.getId());
                    } else {
//                        listAdapter.get(holder.getPosition()).setFlag(true);
                        listAdapter.get(holder.getPosition()).setIs_collect("1");
                        listAdapter.notifyDataSetChanged();
                        im_click.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.card_shoucang));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPresenter.doCollect(dataBean.getId());
                            }
                        }, 500);
                    }

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PointUtils.isFastClick()) {
                        if (UserConfig.getInstance().getToken().length() == 0) {
                            StartActivityUtils.startActivity();
                            return;
                        }

                        Intent intent = new Intent(getActivity(), AnchorBossWebView2.class);
                        intent.putExtra("need_id", dataBean.getId() + "");
                        getActivity().startActivity(intent);

                    }
                }
            });
        }

        @Override
        public void convert(ViewHolder helper, newBossDataBean.DataBean anchorBean, List<Object> payloads) {

        }

    }

    boolean isFirst;
    boolean isFirstClick;


    private static PopupWindow phonePopWindow;
    /**
     * * 预留电话
     */
    //false 没有点击修改
    boolean ischangge = false;

    private void phoneWindow() {
        //设置contentView
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui4, null);
        phonePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        phonePopWindow.setContentView(contentView);

        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final RelativeLayout rl_et = contentView.findViewById(R.id.rl_et);
        final RelativeLayout rl_tv = contentView.findViewById(R.id.rl_tv);
        final TextView tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        tv_phone_number.setText(UserConfig.getInstance().getResumeTelephone());
        TextView tv_update = contentView.findViewById(R.id.tv_update);
        final EditText et_number = contentView.findViewById(R.id.et_number);
        ImageView im_clear = contentView.findViewById(R.id.im_clear);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_tv.setVisibility(View.GONE);
                rl_et.setVisibility(View.VISIBLE);
                et_number.setText(tv_phone_number.getText().toString());
                ischangge = true;
            }
        });
        im_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_number.setText("");
            }
        });


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonePopWindow.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ischangge) {
                    if (et_number.getText().toString().length() == 0) {
                        ToastUtil.showShort("请填写电话号码");
                        return;
                    }
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    mPresenter.sendSucceed("1", id, et_number.getText().toString(), "");
                } else {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    mPresenter.sendSucceed("1", id, tv_phone_number.getText().toString(), "");
                }
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        phonePopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mCardPopWindow1;

    /**
     * 展示形象卡
     */
    private void showCardPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.have_resume_card_layout, null);
        mCardPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCardPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final ScrollView sc = contentView.findViewById(R.id.sc);
        final RelativeLayout rl_info = contentView.findViewById(R.id.rl_info);

        ImageView im1 = contentView.findViewById(R.id.im1);
        ImageView im2 = contentView.findViewById(R.id.im2);
        ImageView im3 = contentView.findViewById(R.id.im3);

        TextView tv_name = contentView.findViewById(R.id.tv_name);
        TextView tv_age = contentView.findViewById(R.id.tv_age);
        ImageView image_sex = contentView.findViewById(R.id.image_sex);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);


        if (PhoneList.size() == 1) {
            Glide.with(getActivity())
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(getActivity(), 10))
                    // 设置高斯模糊
                    .into(im1);
        } else if (PhoneList.size() == 2) {
            Glide.with(getActivity())
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(getActivity(), 10))
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(getActivity())
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(getActivity(), 10))
                    // 设置高斯模糊
                    .into(im2);
        } else if (PhoneList.size() == 3) {
            Glide.with(getActivity())
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(getActivity(), 10))
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(getActivity())
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(getActivity(), 10))
                    // 设置高斯模糊
                    .into(im2);
            Glide.with(getActivity())
                    .load(PhoneList.get(2).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(getActivity(), 10))
                    .crossFade()
                    // 设置高斯模糊
                    .into(im3);
        }


        tv_name.setText(resume.getNickname() + "");
        tv_age.setText(resume.getAge() + "岁");
//        性别 1男2女
        if ("1".equals(resume.getSex())) {
            image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nan));
        } else {
            image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nv));
        }
        tv_phone.setText(resume.getTelephone() + "");


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPopWindow1.dismiss();
                CutImage(rl_info);
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mCardPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 截图实现
     */
    private void CutImage(final View view) {
        // 获取图片某布局
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = view.getDrawingCache();
                // 获取图片
//                savePicture(bmp, "test.jpg");
//                String s = saveBitmap(getActivity(), bmp);
//                Log.d("Debug", "返回的图片地址是" + s);
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                String s = FileUtil.saveFileToGallery(getActivity(), bmp);
                mPresenter.sendSucceed("2", id, "", "");
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] bytes = baos.toByteArray();
//                Im_text.setVisibility(View.VISIBLE);
//                Glide.with(getActivity())
//                        .load(bytes)
//                        .placeholder(R.mipmap.bg_touxiang)
//                        .into(Im_text);
                // 保存图片
                view.destroyDrawingCache(); // 保存过后释放资源 } },100); }
            }
        }, 100);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui2, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        if (phone == null) {
            phone = "0571-87081736";
        } else {
            if (UserConfig.getInstance().getRole().equals("2")) {
                phone = "0571-87081736";
            } else {
                if (phone.length() > 0) {
                } else {
                    phone = "0571-87081736";
                }
            }
        }
        tv_content.setText(phone);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Debug", "两个年份之间相差" + TimeUtil.yearChect("1994"));
//                CutImage(ll_text);
                mPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
//                callPhone("18668121550");
                mPresenter.anchorMakeTelephone(id);
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow;

    /**
     * 1 分享举报 2 沟通10次提醒 3.分享成功以后弹窗 4。弹出跳转形象卡
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_comtent_share, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        if (type == 4) {
            tv_next.setTextColor(getActivity().getResources().getColor(R.color.color_d0a76c));
            tv_next.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape12));
        } else {
            tv_next.setTextColor(getActivity().getResources().getColor(R.color.white));
            tv_next.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape1));
        }

        TextView tv_content = contentView.findViewById(R.id.tv_content);
        switch (type) {
            case 1:
                tv_content.setText("您已举报成功");
                tv_next.setText("知道了");
                break;
            case 2:
                tv_content.setText("小主，您今天已沟通了10个招聘岗位了，分享沟通更多吧"
                );
                tv_next.setText("立即分享");
                break;
            case 3:
                tv_content.setText("您已分享成功,您还有" + number + "次沟通机会");
                tv_next.setText("知道了");
                break;
            case 4:
                tv_content.setText("各位主播宝宝们，添加形象卡更容易抢单成功哦~");
                tv_next.setText("填写形象卡");
                break;
        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 2) {
                    showDialog();
                }
                if (type == 4) {
                    startActivity(new Intent(getActivity(), AddInviteActivity.class));
                }
                mPopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mNoHavePopWindow1;

    /**
     * * 拨打电话
     */
    private void showNoHavePopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui2, null);
        mNoHavePopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mNoHavePopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_content.setText("上传简历，更容易c位出道哦！");
        tv_cancle.setText("放弃");
        tv_next.setText("c位出道");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNoHavePopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddInviteActivity.class));
                mNoHavePopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mNoHavePopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        String tvcontext = "薪资：" + Integer.parseInt(Sharepay_low) / 1000 + "k" + "-" + Integer.parseInt(Sharepay_high) / 1000 + "k" + "\n" + "区域:" + work_position;
        String title = "【" + "直播之家" + "】" + company + "热招" + type_name;
        alertDialog = BossShareCommontDialog.ShareDialog(getActivity(), url + "?share=1", tvcontext, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }


    private static PopupWindow mCopePopWindow;

    /**
     * 粘贴复制
     */
    private void showCopePopupWindow(final int type, String context) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_comtent_share3, null);
        mCopePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCopePopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        TextView tv_content = contentView.findViewById(R.id.tv_content);

        ImageView image_type = contentView.findViewById(R.id.image_type);
        TextView tv_type = contentView.findViewById(R.id.tv_type);
        tv_type.setText(context + "");
        switch (type) {
            case 1:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.qq_fuzhi));
                tv_content.setText("已成功复制QQ号码");
                break;
            case 2:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.weixin));
                tv_content.setText("已成功复制微信账号"
                );
                break;
            case 3:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.youxiang_fuzhi));
                tv_content.setText("已成功复制邮箱账号");
                break;
        }
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCopePopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mCopePopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    public void showPhoneDialog(final String qq1, final String weixin1, final String email1) {
        final Dialog dialog = new Dialog(getContext(), R.style.BottomDialogTheme);
        View mContentView = View.inflate(getContext(), R.layout.popupwindow_select_photo3, null);

        RelativeLayout rl1 = mContentView.findViewById(R.id.rl1);
        RelativeLayout rl2 = mContentView.findViewById(R.id.rl2);
        RelativeLayout rl3 = mContentView.findViewById(R.id.rl3);
        RelativeLayout rl4 = mContentView.findViewById(R.id.rl4);
        RelativeLayout rl5 = mContentView.findViewById(R.id.rl5);
        RelativeLayout rl6 = mContentView.findViewById(R.id.rl6);
        //留电话功能隐藏
        rl1.setVisibility(View.GONE);
        if (qq1 == null) {
            rl3.setVisibility(View.GONE);
        } else {
            if (qq1.length() == 0) {
                rl3.setVisibility(View.GONE);
            } else {
                rl3.setVisibility(View.VISIBLE);
            }
        }

        if (weixin1 == null) {
            rl4.setVisibility(View.GONE);
        } else {
            if (weixin1.length() == 0) {
                rl4.setVisibility(View.GONE);
            } else {
                rl4.setVisibility(View.VISIBLE);
            }

        }

        if (email1 == null) {
            rl5.setVisibility(View.GONE);
        } else {
            if (email1.length() == 0) {
                rl5.setVisibility(View.GONE);
            } else {
                rl5.setVisibility(View.VISIBLE);
            }
        }


        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                phoneWindow();
                Log.d("Debug", "留电话操作");
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Log.d("Debug", "拨打电话操作");
                mPresenter.makeCall(id);
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showCopePopupWindow(1, qq1);
                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(qq1 + ""); // 复制
            }
        });
        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showCopePopupWindow(2, weixin1);
                ClipboardManager clip1 = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip1.getText(); // 粘贴
                clip1.setText(weixin1 + ""); // 复制
            }
        });
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showCopePopupWindow(2, email1);
                ClipboardManager clip1 = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip1.getText(); // 粘贴
                clip1.setText(email1 + ""); // 复制
            }
        });

        rl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                mPresenter.sendSucceed("2", id, "", "");
            }
        });


        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.PopupAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(mContentView);
        dialog.show();
    }


    private static PopupWindow mPopWindowPhone1;

    /**
     * 1。会长没有发布职位
     */
    private void mPopWindowPhone1() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui7, null);
        mPopWindowPhone1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = contentView.findViewById(R.id.tv1);
        TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        tv2.setVisibility(View.GONE);
        String str = "新用户发布职位即可联系主播";
        tv1.setTextSize(15);
        tv1.setText(Html.fromHtml(str));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_perfect == 1) {
                    startActivity(new Intent(getActivity(), AddJobActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
                }
                mPopWindowPhone1.dismiss();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowPhone1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone2;

    /**
     * 1。isHaveCallNumber true有拨打电话次数查看 false 没有拨打电话次数
     */
    private void mPopWindowPhone2(final boolean isHaveCallNumber, final boolean islinkup) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui8, null);
        mPopWindowPhone2 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone2.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = contentView.findViewById(R.id.tv1);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        if (isHaveCallNumber) {
            String str1 = "你还剩余" + "<font color='#d0a76c'>" + residue_num + "</font>" + "次拨打电话的机会，点击拨打电话将扣除" + "<font color='#d0a76c'>" + 1 + "</font>" + "次机会";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
            if (islinkup) {
                tv_content.setVisibility(View.GONE);
            } else {
                tv_content.setVisibility(View.VISIBLE);
            }
        } else {
            String str1 = "点击“拨电话”将消耗" + "<font color='#d0a76c'>" + hf_bd + "</font>" + "波豆";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
        }
        tv1.setText(name1 + ":" + yuliuPhone.substring(0, 3) + "****" + yuliuPhone.substring(yuliuPhone.length() - 4, yuliuPhone.length()));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "拨打电话");
                if (isHaveCallNumber) {
                    BossBean bossBean = new BossBean();
                    bossBean.setRefush(true);
                    bossBean.setPhone(true);
                    bossBean.setRecruit_id(resume_id1 + "");
                    EventBus.getDefault().post(bossBean);

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + yuliuPhone);
                    intent.setData(data);
                    startActivity(intent);
                    mPresenter.linkUpAnchor(anchor_id1, resume_id1);
                    //拨打过电话不请求已沟通以及减少次数接口
                    if (islinkup) {
                    } else {
                        mPresenter.residueNumber(resume_id1, "");
                    }
                } else {
                    //播豆数大于每次消耗次数
                    if (bd >= hf_bd) {
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(resume_id1 + "");
                        EventBus.getDefault().post(bossBean);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + yuliuPhone);
                        intent.setData(data);
                        startActivity(intent);
                        mPresenter.linkUpAnchor(anchor_id1, resume_id1);
                        //拨打电话减少播豆
                        mPresenter.residueNumber(resume_id1, hf_bd + "");
                    } else {
                        //提示去购买
                        mPopWindowPhone3(1);
                    }
                }
                mPopWindowPhone2.dismiss();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowPhone2.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private static PopupWindow mPopWindowPhone3;

    /**
     * 1。拨打电话没有播豆 2 发布职位需要波豆数
     */
    private void mPopWindowPhone3(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui9, null);
        mPopWindowPhone3 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone3.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        if (type == 1) {
            tv_content1.setText("余额不足");
            String str1 = "波豆余额不足，请充值";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
            tv_next.setText("充值");
            tv_cancle.setText("取消");
        } else {
            tv_content1.setText("会长您好");
            String str1 = "即将发布的职位信息为热门职位(让更多的主播看到）需消耗" + send_recruit_bd + "波豆";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
            tv_next.setText("确认发布");
            tv_cancle.setText("取消");
        }
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    if (type == 1) {
                        ActivityUtils.startActivity(getActivity(), VirtualCoinActivity.class);
                    } else {
                        startActivity(new Intent(getActivity(), AddJobActivity.class));
                    }
                }
                mPopWindowPhone3.dismiss();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowPhone3.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone4;

    /**
     * * 预留电话
     */

    //查看次数和预留电话一个借口    true为预留电话借口
    boolean isLiuDainhua = false;

    private void mPopWindowPhone4(final boolean isHaveLeaveNumber, final boolean islinkup) {
        //设置contentView
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui11, null);
        mPopWindowPhone4 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone4.setContentView(contentView);

        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final RelativeLayout rl_tv = contentView.findViewById(R.id.rl_tv);
        final EditText tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        TextView tv_update = contentView.findViewById(R.id.tv_update);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_phone_number.setText(UserConfig.getInstance().getMobile() + "");
        if (isHaveLeaveNumber) {
            String str1 = "今天还剩余" + "<font color='#d0a76c'>" + re_number + "</font>" + "次留电话的机会，点击下方留电话将扣除" + "<font color='#d0a76c'>" + 1 + "</font>" + "次机会";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
        } else {
            String str1 = "点击“留电话”将消耗" + "<font color='#d0a76c'>" + hf_bd2 + "</font>" + "波豆";
            tv_content.setTextSize(15);
            tv_content.setText(Html.fromHtml(str1));
        }

        if (islinkup) {
            tv_content.setVisibility(View.GONE);
        } else {
            tv_content.setVisibility(View.VISIBLE);
        }
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_phone_number.setText("");
                tv_phone_number.setHint("请输入电话号码");
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowPhone4.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLiuDainhua = true;
                if (tv_phone_number.getText().toString().length() == 0) {
                    ToastUtil.showShort("请填写电话号码");
                    return;
                }
                mPopWindowPhone4.dismiss();
                if (islinkup) {
                    //已留电话不做任何操作
                    ToastUtil.showShort("您已对该主播留过电话");
                } else {
                    Log.d("Debug", "电话为" + tv_phone_number.getText().toString());
                    if (isHaveLeaveNumber) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.leaveAnchor(2 + "", anchor_id1, resume_id1, tv_phone_number.getText().toString(), "");
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(resume_id1 + "");
                        EventBus.getDefault().post(bossBean);
                    } else {
                        //播豆数大于每次消耗次数
                        if (bd >= hf_bd2) {
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(getActivity().getSupportFragmentManager());
                            mPresenter.leaveAnchor(2 + "", anchor_id1, resume_id1, tv_phone_number.getText().toString(), hf_bd2 + "");
                            BossBean bossBean = new BossBean();
                            bossBean.setRefush(true);
                            bossBean.setPhone(true);
                            bossBean.setRecruit_id(resume_id1 + "");
                            EventBus.getDefault().post(bossBean);
                        } else {
                            //提示去购买
                            mPopWindowPhone3(1);
                        }
                    }

                }
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone4.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private static PopupWindow mPopWindowPhone5;

    /**
     * 1。当天留电话次数没有
     */
    private void mPopWindowPhone5() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui10, null);
        mPopWindowPhone5 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone5.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        String str1 = "今日留电话次数您已用完，请明日再给该主播留电话";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopWindowPhone5.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone5.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone6;

    /**
     * 1.拨打电话  没有次数消耗波豆弹窗 状态为1 弹窗点击下一步 状态2    已经拨打过弹窗  状态3 （新改的需求）
     */
    private void mPopWindowPhone6(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui9, null);
        mPopWindowPhone6 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone6.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        switch (type) {
            case 1:
                tv_content1.setText("获取联系方式");
                tv_content.setText("获取ta的联系方式需要送给ta" + hf_bd + "波豆哦~（请在联系时，说明来源于直播之家哦）");
                tv_cancle.setText("取消");
                tv_next.setText("获取");
                break;
            case 2:
                tv_content1.setText("恭喜您");
                tv_content.setText("已经成功获取ta联系方式，可立即联系ta哦，也可以在简历管理中查看");
                tv_cancle.setText("去查看");
                tv_next.setText("立即联系");
                break;
            case 3:
                tv_content1.setText("温馨提示");
                tv_content.setText("您已经获取过了ta的联系方式，可继续联系ta哦，也可在简历管理中进行查看");
                tv_cancle.setText("去查看");
                tv_next.setText("立即联系");
                break;
        }


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                        //播豆数大于每次消耗次数
                        Log.d("Debug", "剩余波豆" + bd + "点击一次消耗博豆" + hf_bd);
                        if (bd >= hf_bd) {
                            //拨打电话减少播豆
                            mPresenter.residueNumber(resume_id1, hf_bd + "");
                            mPopWindowPhone6.dismiss();
                            //跳转到下一个弹窗
                            mPopWindowPhone6(2);
                        } else {
                            //提示去购买
                            mPopWindowPhone3(1);
                            mPopWindowPhone6.dismiss();
                        }
                        break;
                    case 2:
                        //改变为已沟通的状态
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(resume_id1 + "");
                        EventBus.getDefault().post(bossBean);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + yuliuPhone);
                        intent.setData(data);
                        startActivity(intent);
                        mPresenter.linkUpAnchor(anchor_id1, resume_id1);
                        mPopWindowPhone6.dismiss();
                        break;
                    case 3:
                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                        Uri data1 = Uri.parse("tel:" + yuliuPhone);
                        intent1.setData(data1);
                        startActivity(intent1);
                        mPresenter.linkUpAnchor(anchor_id1, resume_id1);
                        mPopWindowPhone6.dismiss();
                        break;
                }

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                        //点击直接做消失操作
                        break;
                    case 2:
                        //跳转到简历管理拨电话界面
                        Intent intent = new Intent(getActivity(), ResumeManageActivity.class);
                        intent.putExtra("positon", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳转到简历管理拨电话界面
                        Intent intent1 = new Intent(getActivity(), ResumeManageActivity.class);
                        intent1.putExtra("positon", 2);
                        startActivity(intent1);
                        break;
                }
                mPopWindowPhone6.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.bosswebview, null);
        mPopWindowPhone6.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    //是否发布过职位 1 发布过，0未发布
    int is_issue;
    //剩余拨打电话次数
    int residue_num;
    //公司信息是否完善 1完善 0不完善
    int is_perfect;
    //剩余波豆数
    int bd;
    //留电话消耗波豆数
    int hf_bd2;
    //拨电话消耗波豆数
    int hf_bd;
    //发布职位赠送次数
    int recruit_num;


    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_issue = resultBean.getCompany().getIs_issue();
        is_perfect = resultBean.getCompany().getIs_perfect();
        residue_num = resultBean.getCompany().getResidue_num();
        bd = resultBean.getCompany().getBd();
        hf_bd = resultBean.getCompany().getHf_bd();
        hf_bd2 = resultBean.getCompany().getHf_bd2();
        recruit_num = resultBean.getCompany().getRecruit_num();
        //点击发布职位   对公司信息有没有完善做判断
        if (isCollectCheck) {
            if (is_perfect == 1) {
                //判断有没有发布次数
                mPresenter.positionList();
            } else {
                startActivity(new Intent(getActivity(), AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
            }
            isCollectCheck = false;
        } else {
            if (IsfromYueliao) {
                IsfromYueliao = false;
//                是否发布过职位 1 发布过，0未发布, 2审核中
                if (is_issue == 0) {
                    mPopWindowPhone1();
                } else if (is_issue == 1) {
                    mPresenter.judgeGrade(yuliaoresume_id);
                /*    //约聊的判断
                    if (ischat == 1) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.chatAnchor(yuliaoresume_id, 1 + "");
                    } else {
                    //判断每天约聊的次数
                        mPresenter.guildNumber();
                    }*/
                } else {
                    ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
                }
            } else {
                mPresenter.leaveAnchor(1 + "", "", "", "", "");
            }

        }
    }

    /**
     * 安卓专用判断是否抢过接口
     *
     * @param resultBean
     */
    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {
        if (resultBean.getIs_rob() > 0) {
            Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
            intent.putExtra("chat_id", resultBean.getChat_id() + "");
            startActivity(intent);
        } else {
            //抢ta的判断
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.grabAnchor(yuliaoresume_id);
        }
    }

    /**
     * 发布职位次数
     *
     * @param resultBean
     */
    @Override
    public void limitNumber(IsFaceResponse resultBean) {
//        if (resultBean.getIs_send() > 0) {
//            startActivity(new Intent(getActivity(), AddJobActivity.class));
//        } else {
//            ToastUtil.showShort("职位发布数量已用完，暂不能发布新的职位");
//        }
    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }


    @Override
    public void residueNumber() {

    }

    /**
     * 简历收藏接口
     */
    @Override
    public void resumeDoCollect() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("收藏成功");
        LookJobleftAdapter.get(SelectPosition).setIs_collect(1);
//        LookJobleftAdapter.notifyDataSetChanged();
    }

    /**
     * 简历取消收藏接口
     */
    @Override
    public void resumeCancelCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        LookJobleftAdapter.get(SelectPosition).setIs_collect(0);
//        LookJobleftAdapter.notifyDataSetChanged();
        ToastUtil.showShort("取消收藏成功");

    }

    //留电话次数
    int re_number;

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.d("Debug", "返回的isLiuDainhua的状态" + isLiuDainhua);
        if (isLiuDainhua) {
            ToastUtil.showShort("预留电话成功");
            isLiuDainhua = false;
        } else {
            if (is_linkup1.equals("1")) {
                //已沟通过
                if (yuliuPhone.length() == 0) {
//                   有留电话次数
                    mPopWindowPhone4(true, true);
                } else {
                    //已沟通过不判断次数，  相当于有次数
//                    mPopWindowPhone2(true, true);  (之前的需求)
                    mPopWindowPhone6(3);
                }
            } else {
                re_number = mAccountDataBean.getRe_number();
                if (is_issue == 0) {
                    mPopWindowPhone1();
//            ToastUtil.showShort("没有发布职位");
                } else {
                    if (is_issue == 2 && residue_num == 0) {
                        ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
                    } else {
                        //留电话操作
                        if (yuliuPhone.length() == 0) {
                            if (re_number > 0) {
//                          有留电话次数
                                mPopWindowPhone4(true, false);
//                    ToastUtil.showShort("留电话有次数");
                            } else {
                                mPopWindowPhone4(false, false);
//                    ToastUtil.showShort("没有留电话次数");
                            }
                        } else {
                            if (residue_num > 0) {
                                mPopWindowPhone2(true, false);
//                    ToastUtil.showShort("拨打电话次数大于0");
                            } else {
                                mPopWindowPhone6(1);
//                                mPopWindowPhone2(false, false);
//                    ToastUtil.showShort("没有拨打电话次数，判断播豆够不够");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 查看简历的状态
     *
     * @param resultBean
     */
    @Override
    public void resumeState(ResumeIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (UserConfig.getInstance().getRole().equals("1")) {
            showPopupWindowSevenDays("请联系直播之家官方客服，拥有自己的专属招聘渠道");
        } else {
            switch (resultBean.getStatus()) {
                case -1:
                    showNoHavePopupWindow1();
                    break;
                case 0:
                    ToastUtil.showShort("您的简历正在审核中，暂不能操作");
                    break;
                case 1:
                    showPhoneDialog(qq, weixin, email);
                    break;
                case 2:
                    ToastUtil.showShort("您的简历审核失败，请重新上传简历");
                    break;
            }
        }
    }
}

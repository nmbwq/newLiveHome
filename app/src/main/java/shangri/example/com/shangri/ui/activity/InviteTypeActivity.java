package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.CitySelect.bean.AdressBean;
import shangri.example.com.shangri.CitySelect.bean.City;
import shangri.example.com.shangri.CitySelect.bean.County;
import shangri.example.com.shangri.CitySelect.bean.Province;
import shangri.example.com.shangri.CitySelect.bean.Street;
import shangri.example.com.shangri.CitySelect.db.manager.AddressDictManager;
import shangri.example.com.shangri.CitySelect.widget.AddressSelector;
import shangri.example.com.shangri.CitySelect.widget.OnAddressSelectedListener;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossMoneyBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.CycleBean;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
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
import shangri.example.com.shangri.ui.adapter.BossAdapter;
import shangri.example.com.shangri.ui.adapter.BossCycleAdapter;
import shangri.example.com.shangri.ui.adapter.BossMoneyAdapter;
import shangri.example.com.shangri.ui.adapter.BossPlatAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 到期提醒界面
 * Created by admin on 2017/12/22.
 */

public class InviteTypeActivity extends BaseActivity<BossFragmentView, BossFragmentPresenter> implements BossFragmentView {
    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.im4)
    ImageView im4;
    @BindView(R.id.ll4)
    LinearLayout ll4;

    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.im5)
    ImageView im5;
    @BindView(R.id.ll5)
    LinearLayout ll5;

    @BindView(R.id.ll_show_info)
    LinearLayout ll_show_info;


    @BindView(R.id.fl)
    FrameLayout SelectCity;
    @BindView(R.id.activity_cycle_select)
    FrameLayout activity_cycle_select;
    @BindView(R.id.activity_money_select)
    FrameLayout activity_money_select;

    @BindView(R.id.activity_zuixin_select)
    LinearLayout activity_zuixin_select;


    @BindView(R.id.content)
    LinearLayout content;

    @BindView(R.id.re_cycle)
    RecyclerView reCycle;
    @BindView(R.id.re_cycle_type)
    RecyclerView re_cycle_type;
    @BindView(R.id.re_cycle_date)
    RecyclerView re_cycle_date;
    @BindView(R.id.re_cycle_date_upanddown)
    RecyclerView re_cycle_date_upanddown;

    @BindView(R.id.re_money)
    RecyclerView reMoney;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.re_money1)
    RecyclerView reMoney1;

    @BindView(R.id.ll_type_image)
    ImageView llTypeImage;
    @BindView(R.id.ll_type_show)
    LinearLayout llTypeShow;

    @BindView(R.id.ll_plat_text)
    TextView llPlatText;
    @BindView(R.id.ll_plat_image)
    ImageView llPlatImage;
    @BindView(R.id.ll_type_text)
    TextView llTypeText;

    @BindView(R.id.im_zuixin)
    ImageView im_zuixin;
    @BindView(R.id.tv_zuixin)
    TextView tv_zuixin;
    @BindView(R.id.im_tuijian)
    ImageView im_tuijian;
    @BindView(R.id.tv_tuijian)
    TextView tv_tuijian;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.rl_select_zhouqi)
    LinearLayout rl_select_zhouqi;


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
    String offset = "";
    //    标签id 当时优质精选传此参数
    String anchor_tab = "";
    //1列表 2卡片 默认  列表传1
    boolean isCard = false;


    //选择的市 区地址
    String city1 = "";
    String county1 = "";

    //选择平台做展开操作  还是收起操作       展开为false 收起为true
    boolean llplatshow = false;
    boolean lltypeshow = false;

    //    排序 1推荐 2最新
    String recommend_new = "1";
    //    快速回复 1快速回复（导航类型 type == 5） 否 不传
    String response_level = "";

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private BossAdapter mAdapter;
    //列表
    private List<BossDataBean.ListBean.DataBean> mNewsList = new ArrayList<>();

    String type = "";

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_alertdate_list1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_alertdate_list1;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//        1全部职位 2标签 3官方 4日周结 5快速回复
        type = getIntent().getStringExtra("type");
        Log.d("Debug", "type的值为" + type);
        switch (type) {
            case "2":
                anchor_tab = getIntent().getStringExtra("anchor_type");
                break;
            case "3":
                publish_type = "3";
                break;
            case "4":
                salary_type = "2,3";
                break;
            case "5":
                response_level = "1";
                break;

        }
        String name = getIntent().getStringExtra("name");
        title.setText(name + "");

        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");
        initSpringView();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        rv_partol.setLayoutManager(new FastLinearScrollManger(InviteTypeActivity.this));
        mAdapter = new BossAdapter(this, R.layout.item_boss, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());

        rv_partol.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                ActivityAddNewTask.expire_remind = position;
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        initCityDate();
        initCycleDate();
        initMoneyDate();
        request();
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void request() {
        mPresenter.recruitList(response_level, recommend_new, anchor_tab + "", publish_type, job_method, isCard, offset, currPage + "", area_name, plat_id, anchor_type, pay_low, pay_high, salary_type, keep_pay);
    }


    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(InviteTypeActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
                    request();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(InviteTypeActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    if (currPage < mPageNum) {
                        currPage++;
                        request();
                    } else {
                        sv_partol.onFinishFreshAndLoad();
                    }
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void recruitList(BossDataBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        sv_partol.onFinishFreshAndLoad();
        mPageNum = mAccountDataBean.getList().getLast_page();
        Log.d("Debug", "当前的页数为" + mPageNum);
        if (currPage == 1) {
            mAdapter.setData(mAccountDataBean.getList().getData());
            if (mAccountDataBean.getList().getData().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                sv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                sv_partol.setVisibility(View.GONE);
            }
        } else {
            mAdapter.addAllAt(mNewsList.size(), mAccountDataBean.getList().getData());
        }
        mAdapter.notifyDataSetChanged();
        mNewsList = mAdapter.getAll();
    }

    @Override
    public void NewrecruitList(newBossDataBean mAccountDataBean) {

    }

    @Override
    public void positionList(PositionListBean resultBean) {

    }


    @Override
    public void anchorRecruitList(anchorRecruitListBean mAccountDataBean) {

    }

    @Override
    public void conscribeIndex(BossTongGaoBean mAccountDataBean) {

    }

    @Override
    public void wantList(wantListBean mAccountDataBean) {

    }

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {

    }

    @Override
    public void resumeState(ResumeIndexBean resultBean) {

    }

    @Override
    public void url(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void doCollect() {

    }

    @Override
    public void noLike() {

    }

    @Override
    public void cancelCollect() {

    }

    @Override
    public void sendSucceed(sendSucceedResonse resultBean) {

    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }


    @Override
    public void readPhoto(ReadPhotoBean resultBean) {

    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {

    }

    @Override
    public void anchorShare() {

    }

    @Override
    public void anchorMakeTelephone() {

    }

    @Override
    public void makeCall(BanagetBean resultBean) {

    }

    @Override
    public void surplusMakeCall(BanagetBean resultBean) {

    }

    @Override
    public void getCollect(CollectBean resultBean) {

    }

    @Override
    public void recruitBanner(BanagetBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {
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
            TypeAdapter = new BossPlatAdapter(this, R.layout.item_four, date);
            TypeAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter.setData(date);
        }


        reCycle.setLayoutManager(new GridLayoutManager(this, 4));
        reCycle.setAdapter(TypeAdapter);

        TypeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                if (position == 0) {
                    if (TypeAdapter.get(0).isClick()) {
                        TypeAdapter.get(0).setClick(false);
                    } else {
                        TypeAdapter.get(0).setClick(true);
                        for (int i = 1; i < TypeAdapter.getAll().size(); i++) {
                            TypeAdapter.get(i).setClick(false);
                        }
                    }
                } else {
                    if (TypeAdapter.get(position).isClick()) {
                        TypeAdapter.get(position).setClick(false);
                    } else {
                        TypeAdapter.get(position).setClick(true);
                    }
                    TypeAdapter.get(0).setClick(false);
                }
                TypeAdapter.notifyDataSetChanged();
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
            TypeAdapter1 = new BossPlatAdapter(this, R.layout.item_four, date1);
            TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter1.setData(date1);
        }

        re_cycle_type.setLayoutManager(new GridLayoutManager(this, 4));
        re_cycle_type.setAdapter(TypeAdapter1);

        TypeAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                if (position == 0) {
                    if (TypeAdapter1.get(0).isClick()) {
                        TypeAdapter1.get(0).setClick(false);
                    } else {
                        TypeAdapter1.get(0).setClick(true);
                        for (int i = 1; i < TypeAdapter1.getAll().size(); i++) {
                            TypeAdapter1.get(i).setClick(false);
                        }
                    }
                } else {
                    if (TypeAdapter1.get(position).isClick()) {
                        TypeAdapter1.get(position).setClick(false);
                    } else {
                        TypeAdapter1.get(position).setClick(true);
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

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void resumeAllList(AllListBean resultBean) {

    }


    @OnClick({R.id.ll_zuixin, R.id.ll_tuijian, R.id.tv_dismiss, R.id.tv_commit_cycle, R.id.tv_commit, R.id.setting_back, R.id.ll1, R.id.ll2, R.id.ll4, R.id.ll5, R.id.ll_plat_show, R.id.ll_type_show, R.id.tv_cycle_cncle, R.id.tv_money_cncle, R.id.tv_money_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dismiss:
                ll_show_info.setVisibility(View.GONE);
                break;

            case R.id.ll_tuijian:
                tv_tuijian.setTextColor(this.getResources().getColor(R.color.color_d0a76c));
                tv_zuixin.setTextColor(this.getResources().getColor(R.color.white));
                im_tuijian.setVisibility(View.VISIBLE);
                im_zuixin.setVisibility(View.GONE);
                ll_show_info.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                tv5.setText("推荐");
                recommend_new = "1";
                setTextColor();
                request();
                break;
            case R.id.ll_zuixin:
                ll_show_info.setVisibility(View.GONE);
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
                setTextColor();
                request();
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.ll1:
                setTextColor();
                if (SelectCity.getVisibility() == View.VISIBLE) {
                    ll_show_info.setVisibility(View.GONE);
                    SelectCity.setVisibility(View.GONE);
                } else {
                    ll_show_info.setVisibility(View.VISIBLE);
                    SelectCity.setVisibility(View.VISIBLE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    tv1.setTextColor(ContextCompat.getColor(this, R.color.color_d0a76c));
                    im1.setImageDrawable(this.getResources().getDrawable(R.mipmap.xiangshang_14));
                }

                break;
            case R.id.ll2:
                setTextColor();
                if (activity_cycle_select.getVisibility() == View.VISIBLE) {
                    ll_show_info.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                } else {
                    ll_show_info.setVisibility(View.VISIBLE);
                    mPresenter.platfromType();
                    initupAndDownDate();
                    SelectCity.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.VISIBLE);
                    activity_money_select.setVisibility(View.GONE);
                    tv2.setTextColor(ContextCompat.getColor(this, R.color.color_d0a76c));
                    im2.setImageDrawable(this.getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;
            case R.id.ll4:
                setTextColor();
                if (activity_money_select.getVisibility() == View.VISIBLE) {
                    ll_show_info.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                } else {
                    ll_show_info.setVisibility(View.VISIBLE);
                    initMoneyDate();
                    initCycleDate();
                    if (type.equals("4")) {
                        rl_select_zhouqi.setVisibility(View.GONE);
                    } else {
                        rl_select_zhouqi.setVisibility(View.VISIBLE);
                    }
                    SelectCity.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.VISIBLE);
                    tv4.setTextColor(ContextCompat.getColor(this, R.color.color_d0a76c));
                    im4.setImageDrawable(this.getResources().getDrawable(R.mipmap.xiangshang_14));
                }
                break;

            case R.id.ll5:
                setTextColor();
                if (activity_zuixin_select.getVisibility() == View.VISIBLE) {
                    ll_show_info.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.GONE);
                } else {
                    ll_show_info.setVisibility(View.VISIBLE);
                    SelectCity.setVisibility(View.GONE);
                    activity_cycle_select.setVisibility(View.GONE);
                    activity_money_select.setVisibility(View.GONE);
                    activity_zuixin_select.setVisibility(View.VISIBLE);
                    tv5.setTextColor(ContextCompat.getColor(this, R.color.color_d0a76c));
                    im5.setImageDrawable(this.getResources().getDrawable(R.mipmap.xiangshang_14));
                }
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

//            主播类型重置操作
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
                tv2.setText("主播类型");
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
                tv4.setText("要求");
                break;
            case R.id.tv_commit:
                ll_show_info.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                area_name = "";
                tv1.setText("全国");
                setTextColor();
                request();
                break;
            //薪资选择确定按钮
            case R.id.tv_money_commit:
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

                if (number > 0) {
                    tv4.setText("要求" + "(" + number + ")");
                } else {
                    tv4.setText("要求");
                }
                ll_show_info.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                setTextColor();
                request();
                Log.d("Debug", "选择的薪资是" + pay_low + "---" + pay_high + "底薪是" + keep_pay);
                break;
            //主播类型确定
            case R.id.tv_commit_cycle:
                ll_show_info.setVisibility(View.GONE);
                SelectCity.setVisibility(View.GONE);
                activity_cycle_select.setVisibility(View.GONE);
                activity_money_select.setVisibility(View.GONE);
                activity_zuixin_select.setVisibility(View.GONE);
                //选中的数量
                int number1 = 0;
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
                    tv2.setText("主播类型" + "(" + number1 + ")");
                } else {
                    tv2.setText("主播类型");
                }
                setTextColor();
                request();
                break;
        }
    }


    /**
     * 地址选择数据初始化
     */
    private void initCityDate() {
        AddressSelector selector = new AddressSelector(this);
        //获取地址管理数据库
        selector.setTextSize(13);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
        selector.setIndicatorBackgroundColor(this.getResources().getColor(R.color.white));//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景
        selector.setTextSelectedColor(this.getResources().getColor(R.color.white));//设置字体获得焦点的颜色
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
                ll_show_info.setVisibility(View.GONE);
                Log.d("Debug", "返回的地址信息是" + s);
                area_name = city1;
                tv1.setText(area_name);
//                isChange = true;
                setTextColor();
                request();
            }
        });
        View view = selector.getView();
        content.addView(view);
    }

    private void setTextColor() {
        tv1.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
        tv2.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
        tv4.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
        tv5.setTextColor(ContextCompat.getColor(this, R.color.color_999999));
        im1.setImageDrawable(this.getResources().getDrawable(R.mipmap.huise_sanjiao));
        im2.setImageDrawable(this.getResources().getDrawable(R.mipmap.huise_sanjiao));
        im4.setImageDrawable(this.getResources().getDrawable(R.mipmap.huise_sanjiao));
        im5.setImageDrawable(this.getResources().getDrawable(R.mipmap.huise_sanjiao));
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
            CyclemAdapter = new BossCycleAdapter(this, R.layout.item_four, mNewsList1);
            CyclemAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            CyclemAdapter.setData(mNewsList1);
        }

        re_cycle_date.setLayoutManager(new GridLayoutManager(this, 4));
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
            UpAndDownmAdapter = new BossCycleAdapter(this, R.layout.item_four, mNewsList1);
            UpAndDownmAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            UpAndDownmAdapter.setData(mNewsList1);
        }

        re_cycle_date_upanddown.setLayoutManager(new GridLayoutManager(this, 4));
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
            MoneymAdapter = new BossMoneyAdapter(this, R.layout.item_four, mNewsList1);
            MoneymAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            MoneymAdapter.setData(mNewsList1);
        }

        reMoney.setLayoutManager(new GridLayoutManager(this, 4));
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
            MoneymAdapter1 = new BossMoneyAdapter(this, R.layout.item_four, mNewsList2);
            MoneymAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            MoneymAdapter1.setData(mNewsList2);
        }
        reMoney1.setLayoutManager(new GridLayoutManager(this, 4));
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


}

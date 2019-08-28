package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
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
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.BossFragmentPresenter;
import shangri.example.com.shangri.presenter.LawWorksPayPresenter;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.presenter.view.LowWorksPayView;
import shangri.example.com.shangri.ui.adapter.AllResumeListAdapter;
import shangri.example.com.shangri.ui.adapter.ChairLookJob;
import shangri.example.com.shangri.ui.adapter.ChairLookJob1;
import shangri.example.com.shangri.ui.adapter.ComBoAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CansCrollRecycle.RecyclerViewPager;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.CallPhoneFace;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ScrollLinearLayoutManager;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 会长端点击全部 热门 最新  的item跳转的页面
 * Created by admin on 2017/12/22.
 */

public class ClickItemListActivity extends BaseActivity<BossFragmentView, BossFragmentPresenter> implements BossFragmentView, CallPhoneFace {
    @BindView(R.id.rv_partol)
    RecyclerViewPager rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi1;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    //会长看职位适配器
    ChairLookJob1 mAdapter;
    private List<AllListBean.ListBean> mPatrolList = new ArrayList<>();
    //    筛选条件 hot:最热 new:最新 onlink:未沟通
    String type = "";
    int mPageNum = 0;
    //上个页面点击那个 item  默认的显示那个item
    int position = 0;
    //会长看职位界面是不是滑动到最后一行
    public boolean isLastSelectjobRow;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_list_notitle1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_list_notitle1;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        type = getIntent().getStringExtra("type");
        mPageNum = getIntent().getIntExtra("mPageNum", 0);
        position = getIntent().getIntExtra("position", 0);
        currPage = getIntent().getIntExtra("currPage", 0);
        mPatrolList = (List<AllListBean.ListBean>) getIntent().getSerializableExtra("mPatrolList");
        ScrollLinearLayoutManager scrollLayoutManager = new ScrollLinearLayoutManager(this, ScrollLinearLayoutManager.VERTICAL, false);
        //预加载功能实现（显示的是当前的item，将下一个item加载好，解决 滑动的时候下面item黑屏的问题）
        scrollLayoutManager.setItemPrefetchEnabled(true);
        rv_partol.setLayoutManager(scrollLayoutManager);
        //设置每次滑动一张图片   true为滑动一张  false 快速滑动 可以滑动过好几张图片
        rv_partol.setSinglePageFling(true);
        mAdapter = new ChairLookJob1(this, R.layout.look_job_itemlistitem, mPatrolList, new ChairLookJob1.RightToLeft() {
            @Override
            public void goTo(int old, int pos) {
            }
        });
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        rv_partol.smoothScrollToPosition(position);
        rv_partol.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动到最底部做分页记载操作
                if (isLastSelectjobRow) {
                    requestTastList();
                    isLastSelectjobRow = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
        });
    }


    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(ClickItemListActivity.this)) {
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.resumeAllList(currPage + "", type);
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }

            @Override
            public void onLoadmore() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            mPresenter.resumeAllList(currPage + "", type);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                reload();
                break;
        }
    }


    @Override
    public void recruitList(BossDataBean mAccountDataBean) {

    }

    @Override
    public void NewrecruitList(newBossDataBean mAccountDataBean) {

    }

    @Override
    public void positionList(PositionListBean resultBean) {

    }

    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void resumeAllList(AllListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            mPatrolList.clear();
            if (resultBean.getList().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                rv_partol.setVisibility(View.GONE);
            }
        }
        mPageNum = resultBean.getTotal_page();
        mAdapter.addAll(resultBean.getList());
        mPatrolList = mAdapter.getAll();
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
        if (IsfromYueliao) {
            IsfromYueliao = false;
//                是否发布过职位 1 发布过，0未发布, 2审核中
            if (is_issue == 0) {
                mPopWindowPhone1();
            } else if (is_issue == 1) {
                mPresenter.judgeGrade(yuliaoresume_id);
            } else {
                ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
            }
        } else {
            mPresenter.leaveAnchor(1 + "", "", "", "", "");
        }

    }


    //留电话次数
    int re_number;

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
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


    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {

    }

    @Override
    public void residueNumber() {

    }


    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossBean event) {
        Log.d("Debug", "到达招聘界面");
        if (event.getRefush()) {
            for (int i = 0; i < mAdapter.getAll().size(); i++) {
                AllListBean.ListBean dataBean = (AllListBean.ListBean) mAdapter.get(i);
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
                        dataBean.setChat_id(event.getChat_id() + "");
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
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
        mAdapter.get(SelectPosition).setIs_collect(1);
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
        mAdapter.get(SelectPosition).setIs_collect(0);
//        LookJobleftAdapter.notifyDataSetChanged();
        ToastUtil.showShort("取消收藏成功");

    }


    //简历收藏适配器器的位置  请求完以后刷新用到
    int SelectPosition;

    @Override
    public void Takecllock(String position, int is_collect, String resume_id) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
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
//        isLiuDainhua = false;
        mPresenter.companyAdd(2);
    }


    @Override
    public void refush() {
        reload();
    }

    @Override
    public void yueliao(String resume_id, int is_chat, wantListBean.ResumesBean bean) {

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

    AllListBean.ListBean yuliaoItemDate = new AllListBean.ListBean();

    @Override
    public void yueliao(String resume_id, int is_chat, AllListBean.ListBean bean) {
        Log.d("Debug", "返回的chatid" + is_chat);
        if (bean.getIs_rob() > 0) {
            Intent intent = new Intent(this, TellAboutListActivity.class);
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


    /**
     * 下面是 各种判断的弹窗
     */

    private static PopupWindow mPopWindowPhone1;

    /**
     * 1。会长没有发布职位
     */
    private void mPopWindowPhone1() {
        //设置contentView
        View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui7, null);
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
                    startActivity(new Intent(ClickItemListActivity.this, AddJobActivity.class));
                } else {
                    startActivity(new Intent(ClickItemListActivity.this, AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
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
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone2;

    /**
     * 1。isHaveCallNumber true有拨打电话次数查看 false 没有拨打电话次数
     */
    private void mPopWindowPhone2(final boolean isHaveCallNumber, final boolean islinkup) {
        //设置contentView
        View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui8, null);
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
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private static PopupWindow mPopWindowPhone3;

    /**
     * 1。拨打电话没有播豆
     */
    private void mPopWindowPhone3(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui9, null);
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
        }
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    if (type == 1) {
                        ActivityUtils.startActivity(ClickItemListActivity.this, VirtualCoinActivity.class);
                    } else {
                        startActivity(new Intent(ClickItemListActivity.this, AddJobActivity.class));
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
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
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
        final View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui11, null);
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
                        mProgressDialog.show(ClickItemListActivity.this.getSupportFragmentManager());
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
                            mProgressDialog.show(ClickItemListActivity.this.getSupportFragmentManager());
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
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone4.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private static PopupWindow mPopWindowPhone5;

    /**
     * 1。当天留电话次数没有
     */
    private void mPopWindowPhone5() {
        //设置contentView
        View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui10, null);
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
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone5.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone6;

    /**
     * 1.拨打电话  没有次数消耗波豆弹窗 状态为1 弹窗点击下一步 状态2    已经拨打过弹窗  状态3 （新改的需求）
     */
    private void mPopWindowPhone6(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.compact_add_gonghui9, null);
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
                        Intent intent = new Intent(ClickItemListActivity.this, ResumeManageActivity.class);
                        intent.putExtra("positon", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳转到简历管理拨电话界面
                        Intent intent1 = new Intent(ClickItemListActivity.this, ResumeManageActivity.class);
                        intent1.putExtra("positon", 2);
                        startActivity(intent1);
                        break;
                }
                mPopWindowPhone6.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(ClickItemListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone6.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

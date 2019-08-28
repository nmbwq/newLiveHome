package shangri.example.com.shangri.ui.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
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
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.BossFragmentPresenter;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.ui.activity.AddCompanyInfoActivity2;
import shangri.example.com.shangri.ui.activity.AddJobActivity;
import shangri.example.com.shangri.ui.activity.ClickItemListActivity;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.activity.VirtualCoinActivity;
import shangri.example.com.shangri.ui.adapter.AllResumeListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.WelfareDialog;

/**
 * 主播审核列表
 * Created by admin on 2017/12/22.
 */

public class BossResumeAllListFreagment extends BaseFragment<BossFragmentView, BossFragmentPresenter> implements BossFragmentView, AnchorChectFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;

    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private AllResumeListAdapter mAdapter;
    private List<AllListBean.ListBean> mPatrolList = new ArrayList<>();
    //    筛选条件 hot:最热 new:最新 onlink:未沟通
    String type = "";

    //弹窗
    AlertDialog dialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_list_notitle;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_list_notitle;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(getActivity(), this);
    }


    public static BossResumeAllListFreagment getInstance(String type) {
        BossResumeAllListFreagment fragment = new BossResumeAllListFreagment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        Bundle arguments = getArguments();
        type = arguments.getString("type");
        CompanyInterfaceUtils.setChectBack(this);
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.guanliyuan_kong));
        tv_text1_empty.setText("您还没有主播呢");
        tv_text2_empty.setText("邀请主播加入公会吧~");
        initSpringView();
        rv_partol.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new AllResumeListAdapter(getActivity(), R.layout.item_allresumelist, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击最新的item数据的长度" + mPatrolList.size());
                Intent intent = new Intent(getActivity(), ClickItemListActivity.class);
                intent.putExtra("mPageNum", mPageNum);
                //点击的那个item   下个页面先显示这个item
                intent.putExtra("position", position);
                intent.putExtra("currPage", currPage);
                intent.putExtra("mPatrolList", (Serializable) mPatrolList);
                intent.putExtra("type", type);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.resumeAllList(currPage + "", type);
        }

    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    requestTastList();
                }

            }
        });

    }

    int mPageNum = 0;

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            mPresenter.resumeAllList(currPage + "", type);
            sv_partol.onFinishFreshAndLoad();
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(getActivity());
    }

    @OnClick({R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
        }
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
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
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                rv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        mPageNum = resultBean.getTotal_page();
        mAdapter.addAll(resultBean.getList());
        mPatrolList = mAdapter.getAll();
    }

    //    注册好礼领取弹窗
    AlertDialog RobHimeDialog;

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
        RobHimeDialog = WelfareDialog.RobHimeDialog(getActivity(), mAccountDataBean, ListBeandata.getNickname() + "", ListBeandata.getType_name(), new View.OnClickListener() {
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
                            mPresenter.grabAnchorOrder(ListBeandata.getId() + "", 1 + "", mAccountDataBean.getActivity().getId() + "", mAccountDataBean.getActivity().getG_price() + "");
                        } else {
//                            没有波币
                            mPopWindowPhone3();
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
        bossBean.setRecruit_id(ListBeandata.getId() + "");
        bossBean.setChat_id(mAccountDataBean.getChat_id());
        EventBus.getDefault().post(bossBean);

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
            mPresenter.grabAnchorOrder(ListBeandata.getId() + "", 2 + "", "0", mAccountDataBean.getXf_bd() + "");
        } else {
//                            没有波币
            mPopWindowPhone3();
            RobHimeDialog.dismiss();
        }
    }

    @Override
    public void anchorCheckFace(String register_guild_id, String check_status, String check_mark) {
    }

    @Override
    public void adminPass(String admin_reg_id, String guild_id, String status, String mask) {

    }

    AllListBean.ListBean ListBeandata;

    @Override
    public void robhim(AllListBean.ListBean bean) {
        Log.d("Debug", "点击全部 点击事件");
        ListBeandata = bean;
        mPresenter.companyAdd(2);
    }


    //是否发布过职位 1 发布过，0未发布
    int is_issue;
    //公司信息是否完善 1完善 0不完善
    int is_perfect;

    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_issue = resultBean.getCompany().getIs_issue();
        is_perfect = resultBean.getCompany().getIs_perfect();
//                是否发布过职位 1 发布过，0未发布, 2审核中
        if (is_issue == 0) {
            mPopWindowPhone1();
        } else if (is_issue == 1) {
            mPresenter.judgeGrade(ListBeandata.getId() + "");
        } else {
            ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
        }
    }

    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {
//            是否已抢 0否 1已抢
        if (resultBean.getIs_rob() > 0) {
            //        约聊ID 为0时不跳转约聊界面
            Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
            intent.putExtra("chat_id", resultBean.getChat_id() + "");
            startActivity(intent);
        } else {
            //抢ta的判断
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.grabAnchor(ListBeandata.getId() + "");
        }
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

    private static PopupWindow mPopWindowPhone3;

    /**
     * 1。拨打电话没有播豆
     */
    private void mPopWindowPhone3() {
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
        tv_content1.setText("余额不足");
        String str1 = "波豆余额不足，请充值";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), VirtualCoinActivity.class);
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
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {

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


}

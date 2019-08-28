package shangri.example.com.shangri.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.github.mikephil.charting.formatter.IFillFormatter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.event.ResushNumberBean;
import shangri.example.com.shangri.model.bean.request.StructureBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.LawWorksPayPresenter;
import shangri.example.com.shangri.presenter.view.LowWorksPayView;
import shangri.example.com.shangri.ui.adapter.ComBoAdapter;
import shangri.example.com.shangri.ui.adapter.ZhiweListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 职位列表数据
 * Created by admin on 2017/12/22.
 */

public class ZhiweiListActivity extends BaseActivity<LowWorksPayView, LawWorksPayPresenter> implements LowWorksPayView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.rl_select)
    LinearLayout rl_select;
    @BindView(R.id.rv_select)
    ListView rv_select;
    @BindView(R.id.tv_dismiss)
    TextView tv_dismiss;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    //1 正常 2审核中 3审核失败 4已关闭  0全部
    int type = 0;
    int mPageNum = 0;
    //false  刷新发布职位次数等参数 （不加载列表数据的）
    boolean isRefushList = true;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private ZhiweListAdapter mAdapter;
    private List<PositionListBean.ListBean.DataBean> mList = new ArrayList<>();
    //选择数据
    private List<StructureBean> mListSelect = new ArrayList<>();
    private CommonAdapter<StructureBean> OtherAdapter;


    private Timer timer = null;

    @SuppressLint("HandlerLeak")
    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            if (msg.what == 0) {
                Log.d("Debug", "职位类表刷新数据");
                for (int i = 0; i < mAdapter.getAll().size(); i++) {
                    //返回的时间戳
                    long l = Long.parseLong(mAdapter.getAll().get(i).getExpire_time());
                    if (l > 0) {
                        try {
                            mAdapter.getAll().get(i).setTime(l - TimeUtil.getCurrentTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_zhiwei_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_zhiwei_list;
    }

    @Override
    protected LawWorksPayPresenter createPresenter() {
        return new LawWorksPayPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(ZhiweiListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.positionList(type + "", currPage + "");
        }
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        reload();
        rv_partol.setLayoutManager(new FastLinearScrollManger(ZhiweiListActivity.this));
        mAdapter = new ZhiweListAdapter(this, R.layout.item_zhiwei_item, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);

        mListSelect.add(new StructureBean(0, "全部", true));
        mListSelect.add(new StructureBean(1, "在线职位", false));
        mListSelect.add(new StructureBean(2, "审核中", false));
        mListSelect.add(new StructureBean(4, "已关闭", false));
        mListSelect.add(new StructureBean(3, "审核失败", false));

        OtherAdapter = new CommonAdapter<StructureBean>(ZhiweiListActivity.this, mListSelect, R.layout.company_number_layout1) {
            @Override
            public void convert(final ViewHolder helper, final StructureBean item) {
                TextView tv_name = helper.getView(R.id.tv_name);
                tv_name.setText(item.getText());
                if (item.isClick()) {
                    tv_name.setTextColor(getResources().getColor(R.color.color_d0a76c));
                } else {
                    tv_name.setTextColor(getResources().getColor(R.color.white));
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < OtherAdapter.getmDatas().size(); i++) {
                            OtherAdapter.getmDatas().get(i).setClick(false);
                        }
                        OtherAdapter.getmDatas().get(helper.getPosition()).setClick(true);
                        title.setText(item.getText());
                        OtherAdapter.notifyDataSetChanged();
                        rl_select.setVisibility(View.GONE);
                        type = item.getPosition();
                        reload();
                    }
                });
            }
        };
        rv_select.setAdapter(OtherAdapter);
    }

    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(ZhiweiListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.positionList(type + "", currPage + "");
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
                requestTastList();
            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.positionList(type + "", currPage + "");
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

    @OnClick({R.id.title, R.id.setting_back, R.id.right, R.id.reload, R.id.tv_commit, R.id.tv_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                reload();
                break;
            case R.id.tv_commit:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.companyAdd(2);
                break;
            case R.id.right:
                break;
            case R.id.tv_dismiss:
                rl_select.setVisibility(View.GONE);
                break;
            case R.id.title:
                rl_select.setVisibility(View.VISIBLE);
                break;

        }
    }


    @Override
    public void legalmyPackage(legalMypagerBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
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

    @Override
    public void positionList(PositionListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        send_count = resultBean.getSend_count();
        free_send_recruit = resultBean.getFree_send_recruit();
        send_recruit_upper = resultBean.getSend_recruit_upper();
        send_recruit_bd = resultBean.getSend_recruit_bd();
        usable_bd = resultBean.getUsable_bd();
        if (isRefushList) {
            mPageNum = resultBean.getList().getLast_page();

            if (currPage == 1) {
                mList.clear();
                if (resultBean.getList().getData().size() > 0) {
                    sv_partol.setVisibility(View.VISIBLE);
                    activity_empty_linshi.setVisibility(View.GONE);
                } else {
                    sv_partol.setVisibility(View.GONE);
                    activity_empty_linshi.setVisibility(View.VISIBLE);
                }
            }
            mAdapter.addAll(resultBean.getList().getData());
            mList = mAdapter.getAll();
            if (timer == null) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 0;
                        mHandler.sendMessage(message);
                    }
                }, 300, 1000);
            }
        } else {
            isRefushList = true;
        }

    }

    @Override
    public void sendResume(sendResumeBean resultBean) {

    }

    @Override
    public void upList(upListBean resultBean) {

    }

    @Override
    public void anchorDetail(anchorDetailBean resultBean) {

    }

    @Override
    public void noLikeList(BossDataBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getCompany().getIs_perfect() == 1) {


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
                    startActivity(new Intent(ZhiweiListActivity.this, AddJobActivity.class));
                }
            }
//            mPresenter.limitNumber();
        } else {
            startActivity(new Intent(ZhiweiListActivity.this, AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ResushNumberBean event) {
        Log.d("Debug", "发布完职位  刷新发布职位次数操作");
        isRefushList = false;
        reload();
    }


    @Override
    public void limitNumber(IsFaceResponse resultBean) {
//        if (resultBean.getIs_send() > 0) {
//            startActivity(new Intent(ZhiweiListActivity.this, AddJobActivity.class));
//        } else {
//            ToastUtil.showShort("职位发布数量已用完，暂不能发布新的职位");
//        }
    }

    @Override
    public void upAnchor() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
        }
    }


    private static PopupWindow mPopWindowPhone3;

    /**
     * 1。拨打电话没有播豆 2 发布职位需要波豆数
     */
    private void mPopWindowPhone3(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(ZhiweiListActivity.this).inflate(R.layout.compact_add_gonghui9, null);
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
                        ActivityUtils.startActivity(ZhiweiListActivity.this, VirtualCoinActivity.class);
                    } else {
                        startActivity(new Intent(ZhiweiListActivity.this, AddJobActivity.class));
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
        View rootview = LayoutInflater.from(ZhiweiListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

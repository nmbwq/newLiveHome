package shangri.example.com.shangri.ui.activity;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AboutOursBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.presenter.CostomerServicesPresenter;
import shangri.example.com.shangri.presenter.view.costomerServiceView;
import shangri.example.com.shangri.ui.adapter.CostomerServiceAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 我的客服
 * Created by admin on 2017/12/22.
 */

public class MyCustomerServiceActivity extends BaseActivity<costomerServiceView, CostomerServicesPresenter> implements costomerServiceView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.tv_touch_services)
    TextView tvTouchServices;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    CostomerServiceAdapter mAdapter;

    private List<RequestListBean.QaBean> mPatrolList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_mycustomeservice_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_mycustomeservice_list;
    }

    @Override
    protected CostomerServicesPresenter createPresenter() {
        return new CostomerServicesPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        tvName.setText("Hi~  " + UserConfig.getInstance().getNickname());
        if (UserConfig.getInstance().getRole().equals("1")) {
            tvVip.setVisibility(View.GONE);
            tvLine.setVisibility(View.GONE);
        } else {
            tvVip.setVisibility(View.GONE);
            tvLine.setVisibility(View.GONE);
        }
        loadData();
        mPresenter.customLink();
    }


    @Override
    public void requestFailed(String message) {

    }

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        currPage = 1;
        mPresenter.vipQaList();
        rv_partol.setLayoutManager(new FastLinearScrollManger(MyCustomerServiceActivity.this));
        mAdapter = new CostomerServiceAdapter(this, R.layout.item_select_alert1, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                Intent intent = new Intent(MyCustomerServiceActivity.this, DetailQuestionActivity.class);
                intent.putExtra("bean", mAdapter.getAll().get(position));
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(MyCustomerServiceActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
                }
                sv_partol.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(MyCustomerServiceActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;

                }
                sv_partol.onFinishFreshAndLoad();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.setting_back, R.id.tv_question, R.id.tv_vip, R.id.tv_touch_services})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_question:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(this, UserFeedBackActivity.class);
                }
                break;
            case R.id.tv_vip:
                if (PointUtils.isFastClick()) {
                    if (expire_time.length() > 0) {
                        try {
                            long currentTime = TimeUtil.getCurrentTime();
                            if (Long.parseLong(expire_time) > currentTime) {
//                                ToastUtil.showShort("会员没有过期");
                                showPopupWindowSevenDays(true);
                            } else {
                                mPopWindowPhone3();
//                                ToastUtil.showShort("会员已过期");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        mPopWindowPhone3();
//                        ToastUtil.showShort("不是会员");
                    }
                }
                break;
            case R.id.tv_touch_services:
                if (PointUtils.isFastClick()) {
                    showPopupWindowSevenDays(false);
                }
                break;
        }
    }

    //会员到期时间
    String expire_time = "";

    @Override
    public void vipQaList(RequestListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        expire_time = resultBean.getExpire_time();
        mAdapter.addAll(resultBean.getQa());
    }

    //    VIP客服电话
    String custom_vip_tel = "";
    //    VIP客服微信
    String custom_vip_wx = "";
    //    客服电话
    String custom_tel = "";
    //    客服微信
    String custom_wx = "";

    @Override
    public void customLink(RequestListBean resultBean) {
        custom_vip_tel = resultBean.getCustom_vip_tel();
        custom_vip_wx = resultBean.getCustom_vip_wx();
        custom_tel = resultBean.getCustom_tel();
        custom_wx = resultBean.getCustom_wx();
    }

    @Override
    public void aboutOurs(AboutOursBean resultBean) {

    }

    @Override
    public void vipQaUseful() {

    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗  isVipShow true是vip专属客服弹窗 false是联系客服弹窗
     */
    private void showPopupWindowSevenDays(Boolean isVipShow) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.add_boss_info, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        final TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        final TextView tv_content = contentView.findViewById(R.id.tv_content);

        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        if (isVipShow) {
            tv_content1.setText("VIP专属通道");
            tv_content.setText("添加微信号，我们将会有专属客服竭诚为您服务");
            tv1.setText(custom_vip_tel + "");
            tv2.setText(custom_vip_wx + "");
        } else {
            tv_content1.setText("联系客服");
            tv_content.setText("请联系直播之家官方客服");
            tv1.setText(custom_tel + "");
            tv2.setText(custom_wx + "");
        }
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
        View rootview = LayoutInflater.from(this).inflate(R.layout.new_fragment_boss, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone3;

    /**
     * 1.会员过期
     */
    private void mPopWindowPhone3() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.compact_add_gonghui9, null);
        mPopWindowPhone3 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone3.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        tv_content1.setText("VIP专属通道");
        tv_cancle.setText("取消");
        tv_next.setText("立即购买");
        String str1 = "您还不是平台的VIP用户";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(MyCustomerServiceActivity.this, VIPActivity.class);
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
        View rootview = LayoutInflater.from(this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

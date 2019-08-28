package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.ElementListener;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

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
import shangri.example.com.shangri.model.bean.event.TellAboutBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.tellAboutResponseBean;
import shangri.example.com.shangri.model.bean.response.transferBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.TellAboutPresenter;
import shangri.example.com.shangri.presenter.view.TellAboutView;
import shangri.example.com.shangri.ui.adapter.TellAboutAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 约聊界面
 * Created by admin on 2017/12/22.
 */

public class TellAboutListActivity extends BaseActivity<TellAboutView, TellAboutPresenter> implements TellAboutView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.rl_noempty_info)
    RelativeLayout rl_noempty_info;


    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
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
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    private TellAboutAdapter mAdapter;
    private List<tellAboutResponseBean.ListBean.DataBean> mPatrolList = new ArrayList<>();
    private List<String> text = new ArrayList<>();
    private List<String> text1 = new ArrayList<>();
    //存数据用的
    private List<tellAboutResponseBean.ListBean.DataBean> mList = new ArrayList<>();

    //弹窗
    AlertDialog dialog;
    //约聊id
    String chat_id = "";
    //true 点击拨打电话以及点击简历图片时候 ，在onresume中刷新数据  false  不刷新数据
    public Boolean IsClickPhoneAndResume = false;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_tellabout_list1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_tellabout_list1;
    }

    @Override
    protected TellAboutPresenter createPresenter() {
        return new TellAboutPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        chat_id = getIntent().getStringExtra("chat_id");
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");

        initSpringView();
        loadData();
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(TellAboutBean messageEvent) {
        Log.d("Debug", "越聊接到拨打电话通知");
        //1拨打电话  2 点击简历图片
        if (messageEvent.getType().equals("1")) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.telephoneResumet(chat_id);
        }
        IsClickPhoneAndResume = true;
    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(TellAboutListActivity.this)) {
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
            mPresenter.chatWithList(chat_id, currPage + "");
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
                if (!NetWorkUtil.isNetworkConnected(TellAboutListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    requestTastList();
                }
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            mPresenter.chatWithList(chat_id, currPage + "");
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
            ToastUtil.showShort("已加载全部");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (IsClickPhoneAndResume) {
            loadData();
            IsClickPhoneAndResume = false;
        }

    }

    // 用到eventbus页面消除的时候Eventbus一定要消除的,baseActivity中已写好的,所以一定要集成super.onDestroy();,才可以用的
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销订阅者
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.reload, R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                loadData();
                break;
            case R.id.tv1:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长继续邀请操作
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.chatAnchor(resume_id + "", 2 + "");
                    } else {
                        //主播发送简历
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.sendResume(chat_id + "");
                    }
                }
                break;
            case R.id.tv2:
                if (PointUtils.isFastClick()) {

                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长端做拨打电话留电话操作
                        if (is_sign == 1) {
                            ToastUtil.showShort("该主播已签约");
                        } else {
                            if (anchor_telephone.length() == 0) {
                                mPopWindowPhone4();
                            } else {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                Uri data = Uri.parse("tel:" + anchor_telephone);
                                intent.setData(data);
                                startActivity(intent);
                                mPresenter.dialTelephoneMessage(chat_id, 0 + "");
                                loadData();
                            }
                        }
                    } else {
                        //主播端做交换电话操作
                        if (anchor_is_resume == 1) {
                            //主播发送简历
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
//                    交换类型 1电话 2微信
                            mProgressDialog.show(this.getSupportFragmentManager());
                            if (UserConfig.getInstance().getRole().equals("1")) {
                                mPresenter.telephoneWechat(chat_id + "", 1 + "", UserConfig.getInstance().getMobile(), "");
                            } else {
                                mPresenter.telephoneWechat(chat_id + "", 1 + "", UserConfig.getInstance().getMobile(), "");
                            }
                        } else {
                            ToastUtil.showShort("投递简历后才能交换呢");
                        }
                    }

                }
                break;
            case R.id.tv3:
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "交换微信点击事件");
                    if (anchor_is_resume == 1) {
                        WeixinCahangemPopWindowPhone5();
                    } else {
                        ToastUtil.showShort("投递简历后才能交换呢");
                    }
                }
                break;
        }
    }

    //简历id
    int resume_id;
    //    0是没有投递简历  1是投递简历
    int anchor_is_resume;
    //主播电话
    String anchor_telephone = "";
    //主播微信
    String anchor_wx = "";
    //主播昵称
    String anchor_nickname = "";
    //会长电话
    String guild_telephone = "";
    //会长微信
    String guild_wx = "";

    //    是否沟通 1是 2否(只对会长角色)
    private String is_linkup;
    //    是否签约 1签约2未签约
    private int is_sign;

    @Override
    public void chatWithList(tellAboutResponseBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        anchor_nickname = resultBean.getAnchor_nickname();
        is_linkup = resultBean.getIs_linkup() + "";
        is_sign = resultBean.getIs_sign();
        resume_id = resultBean.getResume_id();
        anchor_is_resume = resultBean.getAnchor_is_resume();
        anchor_telephone = resultBean.getAnchor_telephone();
        anchor_wx = resultBean.getAnchor_wx();
        guild_telephone = resultBean.getGuild_telephone();
        guild_wx = resultBean.getGuild_wx();
        transferBean transferBean = new transferBean();
        transferBean.anchor_is_resume = resultBean.getAnchor_is_resume() + "";
        transferBean.guild_avatar = resultBean.getGuild_avatar() + "";
        transferBean.anchor_avatar = resultBean.getAnchor_avatar() + "";
        transferBean.guild_token = resultBean.getGuild_token() + "";
        transferBean.resume_id = resultBean.getResume_id() + "";
        transferBean.guild_nickname = resultBean.getGuild_nickname() + "";
        transferBean.anchor_nickname = resultBean.getAnchor_nickname() + "";
        transferBean.anchor_telephone = resultBean.getAnchor_telephone() + "";
        transferBean.anchor_wx = resultBean.getAnchor_wx() + "";
        transferBean.guild_telephone = resultBean.getGuild_telephone() + "";
        transferBean.guild_wx = resultBean.getGuild_wx() + "";
        if (UserConfig.getInstance().getRole().equals("1")) {
            title.setText(resultBean.getAnchor_nickname() + "");
        } else {
            title.setText(resultBean.getGuild_nickname() + "");
        }
        if (UserConfig.getInstance().getRole().equals("1")) {
            tv2.setText("联系ta");
            tv1.setVisibility(View.GONE);
        } else {
            tv2.setText("交换电话");
            tv1.setText("投递简历");
            tv1.setVisibility(View.VISIBLE);
        }
        if (anchor_is_resume == 1) {
            tv2.setTextColor(getResources().getColor(R.color.white));
            tv3.setTextColor(getResources().getColor(R.color.white));
        } else {
//            没有投递简历 主播不能操作是灰色的按钮   会长 有没有投递简历都可以拨打电话和留电话 所以是白色的
            if (UserConfig.getInstance().getRole().equals("1")) {
                tv2.setTextColor(getResources().getColor(R.color.white));
            } else {
                tv2.setTextColor(getResources().getColor(R.color.text_color_little_black));
            }
            tv3.setTextColor(getResources().getColor(R.color.text_color_little_black));
        }
        if (mAdapter == null) {
            rv_partol.setLayoutManager(new FastLinearScrollManger(TellAboutListActivity.this));
            mAdapter = new TellAboutAdapter(this, transferBean, R.layout.tellabout_item_layout, mPatrolList, CompanyInterfaceUtils.anchorChectFace);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rv_partol.setAdapter(mAdapter);
        } else {
            mAdapter.upDateInfo(transferBean);
        }
        if (currPage == 1) {
            if (resultBean.getList().getData().size() > 0) {
                rl_noempty_info.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                rl_noempty_info.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
            if (mList.size() > 0) {
                mList.clear();
            }
            for (int i = 0; i < resultBean.getList().getData().size(); i++) {
                mList.add(0, resultBean.getList().getData().get(i));
            }
            rv_partol.smoothScrollToPosition(resultBean.getList().getData().size() - 1);
        } else {
            for (int i = 0; i < resultBean.getList().getData().size(); i++) {
                mList.add(0, resultBean.getList().getData().get(i));
            }
        }
        mAdapter.setData(mList);
        mPatrolList = mAdapter.getAll();
        mPageNum = resultBean.getList().getLast_page();
        Log.d("Debug", "总页数为" + mPageNum);
    }

    @Override
    public void sendResume() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        loadData();
    }

    @Override
    public void telephoneWechat() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        loadData();
    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        loadData();
    }

    @Override
    public void telephoneResumet() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void dialTelephoneMessage() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        loadData();
    }


    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    private static PopupWindow WeixinCahangemPopWindowPhone5;

    /**
     * 交换微信
     */
    private void WeixinCahangemPopWindowPhone5() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.compact_add_gonghui12, null);
        WeixinCahangemPopWindowPhone5 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        WeixinCahangemPopWindowPhone5.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final EditText tv_content = contentView.findViewById(R.id.tv_content);
        if (UserConfig.getInstance().getRole().equals("1")) {
            if (guild_wx.length() > 0) {
                tv_content.setText(guild_wx);
            }
        } else {
            if (anchor_wx.length() > 0) {
                tv_content.setText(anchor_wx);
            }
        }
        tv_content.setSelection(tv_content.getText().length());
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_content.getText().toString().length() > 0) {
                    //主播发送简历
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
//                    交换类型 1电话 2微信
                    mProgressDialog.show(TellAboutListActivity.this.getSupportFragmentManager());
                    mPresenter.telephoneWechat(chat_id + "", 2 + "", "", tv_content.getText().toString());
                    WeixinCahangemPopWindowPhone5.dismiss();
                } else {
                    ToastUtil.showShort("微信号不能为空");
                }
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeixinCahangemPopWindowPhone5.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_tellabout_list1, null);
        WeixinCahangemPopWindowPhone5.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone4;

    /**
     * * 预留电话
     */

    //查看次数和预留电话一个借口    true为预留电话借口
    boolean isLiuDainhua = false;

    private void mPopWindowPhone4() {
        //设置contentView
        final View contentView = LayoutInflater.from(TellAboutListActivity.this).inflate(R.layout.compact_add_gonghui11, null);
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
        TextView tv_leave_title = contentView.findViewById(R.id.tv_leave_title);
        tv_phone_number.setText(UserConfig.getInstance().getMobile() + "");
        tv_leave_title.setText("会长您好");
        String str1 = "该主播暂时无法拨打电话，您可对ta进行预留电话";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));

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
                if (tv_phone_number.getText().toString().length() == 0) {
                    ToastUtil.showShort("请填写电话号码");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(TellAboutListActivity.this.getSupportFragmentManager());
                mPresenter.dialTelephoneMessage(chat_id, 1 + "");
                mPopWindowPhone4.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(TellAboutListActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone4.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.LookAnchorPresenter;
import shangri.example.com.shangri.presenter.LookAnchorView;
import shangri.example.com.shangri.ui.adapter.ImageMuchAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CansCrollRecycle.RecyclerViewPager;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.WelfareDialog;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by Administrator on 2017/7/3.
 * do what to 2017/7/3
 */

public class LookAnchorDetailActivity extends BaseActivity<LookAnchorView, LookAnchorPresenter> implements LookAnchorView {
    @BindView(R.id.recy_image)
    RecyclerViewPager recy_image;

    @BindView(R.id.tv_liu_image)
    ImageView tvLiuImage;
    @BindView(R.id.tv_is_liu)
    TextView tvIsLiu;
    @BindView(R.id.ll_click_phone)
    LinearLayout llClickPhone;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_photo_positon)
    TextView tv_photo_positon;

    @BindView(R.id.im_sex)
    ImageView imSex;

    @BindView(R.id.tv_jingyan)
    TextView tvJingyan;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_type1)
    TextView tv_type1;
    @BindView(R.id.tv_type2)
    TextView tv_type2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.im_cllock)
    ImageView im_cllock;

    @BindView(R.id.tv_active_status)
    TextView tv_active_status;
    @BindView(R.id.tv_rob_num)
    TextView tv_rob_num;

    @BindView(R.id.im_qiangta)
    ImageView im_qiangta;
    @BindView(R.id.tv_qiangta)
    TextView tv_qiangta;
    @BindView(R.id.ll_rob_number)
    LinearLayout ll_rob_number;


    //请求的数据体
    ResumeDetailBean.ResumeBean bean;

    int imageposition = 1;

    //有没有收藏  0是没有收藏  1是已收藏
    int is_collect = 0;
    //点击约聊操作判断
    boolean IsfromYueliao;
    String id;
    //来自主播收藏和来自拨电话打电话，以及小播推荐界面判断不一样   我的收藏要判断有没有发布过职位，以及拨打电话次数等  来自拨打电话界面不用判断直接拨打
    Boolean IsFromAnchorClloect;

    //来自约聊界面简历图片查看  拨打电话调用的是另外的借口 和上面其他情况判断不一样
    Boolean IsfromTalkabout = false;
    //约聊界面跳转过来要穿chat_id
    String chat_id = "";

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.look_job_item1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.look_job_item1;
    }

    @Override
    protected LookAnchorPresenter createPresenter() {
        return new LookAnchorPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        id = getIntent().getStringExtra("id");
        IsFromAnchorClloect = getIntent().getBooleanExtra("IsFromAnchorClloect", false);
        IsfromTalkabout = getIntent().getBooleanExtra("IsfromTalkabout", false);
        chat_id = getIntent().getStringExtra("chat_id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
        if (IsfromTalkabout) {
            if (chat_id != null) {
                if (chat_id.length() > 0) {
                    mPresenter.getResumeDetailBean(id, chat_id);
                } else {
                    mPresenter.getResumeDetailBean(id, chat_id);
                }
            } else {
                mPresenter.getResumeDetailBean(id, "");
            }
        } else {
            mPresenter.getResumeDetailBean(id, "");
        }
    }

    public void initEvent() {
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        tvName.setText(bean.getNickname() + "");
        tvAge.setText(bean.getAge() + "岁");

        if (bean.getSex().equals("1")) {
            imSex.setImageDrawable(this.getResources().getDrawable(R.mipmap.nan));
        } else {
            imSex.setImageDrawable(this.getResources().getDrawable(R.mipmap.nv));
        }
        tvJingyan.setText(bean.getLive_age() + "年直播经验");
        List<String> Imagedatas = new ArrayList<>();
        for (int i = 0; i < bean.getPhoto().size(); i++) {
            Imagedatas.add(bean.getPhoto().get(i).getImg_url());
        }
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_image.setLayoutManager(layout);
        ImageMuchAdapter imageMuchAdapter;
        imageMuchAdapter = new ImageMuchAdapter(this, R.layout.look_image_item, Imagedatas);
        recy_image.setAdapter(imageMuchAdapter);
        imageMuchAdapter.setData(Imagedatas);

        if (bean.getPhoto().size() > 0) {
            tv_photo_positon.setVisibility(View.VISIBLE);
            tv_photo_positon.setText("1/" + bean.getPhoto().size());
        } else {
            tv_photo_positon.setVisibility(View.GONE);
        }
        if (bean.getIs_rob() > 0) {
            tv_qiangta.setText("已抢");
            im_qiangta.setImageDrawable(this.getResources().getDrawable(R.mipmap.zbzp_yqzt));
        } else {
            tv_qiangta.setText("抢ta");
            im_qiangta.setImageDrawable(this.getResources().getDrawable(R.mipmap.zbzp_qta));
        }

        recy_image.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("Debug", "现在的位置是" + newPosition);
                if (newPosition >= 0) {
                    tv_photo_positon.setText((newPosition + 1) + "/" + bean.getPhoto().size());
                }

            }
        });
        if (bean.getRob_num() > 0) {
            tv_rob_num.setText(bean.getRob_num() + "人正在抢ta");
        } else {
            tv_rob_num.setText("快来抢我");
        }
        if (bean.getType_name().size() == 0) {
            tv_type.setVisibility(View.GONE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (bean.getType_name().size() == 1) {
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (bean.getType_name().size() == 2) {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type1.setText(bean.getType_name().get(1) + "");
        } else {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.VISIBLE);
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type1.setText(bean.getType_name().get(1) + "");
            tv_type2.setText(bean.getType_name().get(2) + "");
        }

        if (bean.getWanted_status_name().length() > 0) {
            tv_state.setVisibility(View.VISIBLE);
            tv_state.setText(bean.getWanted_status_name());
        } else {
            tv_state.setVisibility(View.GONE);
        }

        if (bean.getPay_low().equals("0") && bean.getPay_high().equals("0")) {
            tvMoney.setText("期望底薪：待议");
        } else {
            tvMoney.setText("期望底薪：" + Integer.parseInt(bean.getPay_low()) / 1000 + "k" + "-" + Integer.parseInt(bean.getPay_high()) / 1000 + "k");
        }

        //没有电话显示  预留电话   有电话显示拨打电话
        if (bean.getIs_sign() == 1) {
            tvIsLiu.setVisibility(View.VISIBLE);
            tvLiuImage.setVisibility(View.VISIBLE);
            if (bean.getTelephone() == null) {
                tvIsLiu.setText("留电话");
                tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.liudianhuah));
            } else {
                if (bean.getTelephone().length() == 0) {
                    tvIsLiu.setText("留电话");
                    tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.liudianhuah));
                } else {
                    tvIsLiu.setText("拨电话");
                    tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.bodianhuah));
                }
            }
        } else {
            tvIsLiu.setVisibility(View.VISIBLE);
            tvLiuImage.setVisibility(View.VISIBLE);
            if (bean.getTelephone() == null) {
                tvIsLiu.setText("留电话");
                tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.liudianhua));
            } else {
                if (bean.getTelephone().length() == 0) {
                    tvIsLiu.setText("留电话");
                    tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.liudianhua));
                } else {
                    tvIsLiu.setText("拨电话");
                    tvLiuImage.setImageDrawable(this.getResources().getDrawable(R.mipmap.bodianhua));
                }
            }
        }

//        1已签约 2未签约
        if (bean.getIs_sign() == 1) {
            tv1.setVisibility(View.VISIBLE);
            ll_rob_number.setVisibility(View.GONE);
        } else {
            tv1.setVisibility(View.GONE);
            ll_rob_number.setVisibility(View.VISIBLE);
        }
//        1已沟通 2未沟通 （新需求  拨打电话以及留电去掉  现在已沟通图标隐藏）
        if (bean.getIs_linkup() == 1) {
            tv2.setVisibility(View.GONE);
        } else {
            tv2.setVisibility(View.GONE);
        }

        is_collect = bean.getIs_collect();
        if (is_collect == 0) {
            im_cllock.setImageResource(R.mipmap.shoucang);
        } else {
            im_cllock.setImageResource(R.mipmap.c17);
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llInfo.getLayoutParams();
//获取当前控件的布局对象
        params.height = height;//设置当前控件布局的高度
        llInfo.setLayoutParams(params);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void accountData(AccountDataBean dataBean) {

    }

    /*
     发布约聊次数
     */
    @Override
    public void guildNumber(sendSucceedResonse resultBean) {
        if (resultBean.getCount() > 0) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
            mPresenter.chatAnchor(id, "1");
        } else {
            ToastUtil.showShort("每日只可约聊" + resultBean.getAll_total() + "位主播呢");
        }
    }

    /**
     * 会长约聊主播
     *
     * @param resultBean
     */
    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        //        约聊ID 为0时不跳转约聊界面
        Intent intent = new Intent(this, TellAboutListActivity.class);
        intent.putExtra("chat_id", resultBean.getChat_id());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getResumeDetailBean(ResumeDetailBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        bean = resultBean.getResume();
        chat_id = resultBean.getResume().getChat_id();
        initEvent();
    }

    @OnClick({R.id.webview_back, R.id.ll_click_phone, R.id.rl_click_collect, R.id.ll_click_yueliao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webview_back:
                finish();
                break;
            //约聊
            case R.id.ll_click_yueliao:
                if (PointUtils.isFastClick()) {
                    IsfromYueliao = true;
                    mPresenter.companyAdd(2);
//                    Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
//                    intent.putExtra("chat_id", yuliaoItemDate.getChat_id() + "");
//                    startActivity(intent);

                }
                break;
            case R.id.ll_click_phone:
                if (PointUtils.isFastClick()) {
                    if (IsfromTalkabout) {
                        if (bean.getIs_sign() == 1) {
                            ToastUtil.showShort("该主播已签约公会");
                        } else {
                            mPresenter.telephoneResumet(chat_id);
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + bean.getTelephone().toString());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    } else {
                        isLiuDainhua = false;
                        mPresenter.companyAdd(2);
                    }
                }
                break;
            case R.id.rl_click_collect:
                if (PointUtils.isFastClick()) {
                    //收藏操作
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
//        是否收藏 1是 0否
                    if (is_collect == 1) {
                        im_cllock.setImageResource(R.drawable.anim_cancel_collect);
                        AnimationDrawable animation1 = (AnimationDrawable) im_cllock.getDrawable();
                        animation1.start();
                        mPresenter.resumeCancelCollect(id);
                    } else {
                        im_cllock.setImageResource(R.drawable.anim_collect);
                        AnimationDrawable animation1 = (AnimationDrawable) im_cllock.getDrawable();
                        animation1.start();
                        mPresenter.resumeDoCollect(id);
                    }
                }
                break;
        }
    }

    private static PopupWindow mPopWindowPhone1;

    /**
     * 1。会长没有发布职位
     */
    private void mPopWindowPhone1() {
        //设置contentView
        View contentView = LayoutInflater.from(LookAnchorDetailActivity.this).inflate(R.layout.compact_add_gonghui7, null);
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
                    startActivity(new Intent(LookAnchorDetailActivity.this, AddJobActivity.class));
                } else {
                    startActivity(new Intent(LookAnchorDetailActivity.this, AddCompanyInfoActivity2.class).putExtra("IsFirstFabuJob", true));
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
        View rootview = LayoutInflater.from(LookAnchorDetailActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone2;

    /**
     * 1。isHaveCallNumber true有拨打电话次数查看（islinkupt有没有沟通过）  false 没有拨打电话次数 (判断播豆够不够)
     */
    private void mPopWindowPhone2(final boolean isHaveCallNumber, final boolean islinkup) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.compact_add_gonghui8, null);
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
        String telephone = bean.getTelephone();
        String number = telephone.substring(0, 3) + "****" + telephone.substring(telephone.length() - 4, telephone.length());
        tv1.setText(bean.getNickname() + "：" + number);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "拨打电话");

                if (isHaveCallNumber) {
                    BossBean bossBean = new BossBean();
                    bossBean.setRefush(true);
                    bossBean.setPhone(true);
                    bossBean.setRecruit_id(bean.getId() + "");
                    EventBus.getDefault().post(bossBean);
                    mPresenter.getResumeDetailBean(id, chat_id);

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + bean.getTelephone().toString());
                    intent.setData(data);
                    startActivity(intent);
                    mPresenter.linkUpAnchor(bean.getRegister_id() + "", bean.getId());
                    //拨打过电话不请求已沟通以及减少次数接口
                    if (islinkup) {
                    } else {
                        mPresenter.residueNumber(bean.getId() + "", "");
                    }
                } else {
                    //播豆数大于每次消耗次数
                    if (bd >= hf_bd) {
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(bean.getId() + "");
                        EventBus.getDefault().post(bossBean);

                        mPresenter.getResumeDetailBean(id, chat_id);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + bean.getTelephone().toString());
                        intent.setData(data);
                        startActivity(intent);
                        mPresenter.linkUpAnchor(bean.getRegister_id() + "", bean.getId());
                        //拨打电话减少播豆
                        mPresenter.residueNumber(bean.getId() + "", hf_bd + "");

                    } else {
                        //提示去购买
                        mPopWindowPhone3();
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
        View rootview = LayoutInflater.from(this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone3;

    /**
     * 1。拨打电话次数没有
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
        tv_content.setText("余额不足");
        String str1 = "波豆余额不足，请充值";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(LookAnchorDetailActivity.this, VirtualCoinActivity.class);
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
        View rootview = LayoutInflater.from(this).inflate(R.layout.bosswebview, null);
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
        final View contentView = LayoutInflater.from(LookAnchorDetailActivity.this).inflate(R.layout.compact_add_gonghui11, null);
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

                Log.d("Debug", "电话为" + tv_phone_number.getText().toString());
                if (islinkup) {
                    //已留电话不做任何操作
                    ToastUtil.showShort("您已对该主播留过电话");
                } else {
                    if (isHaveLeaveNumber) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
                        mPresenter.leaveAnchor(2 + "", bean.getRegister_id(), bean.getId(), tv_phone_number.getText().toString(), "");
                        //刷新本页面是否沟通状态
                        mPresenter.getResumeDetailBean(id, chat_id);
                        //发推送  boss页面状态刷新
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(bean.getId() + "");
                        EventBus.getDefault().post(bossBean);
                    } else {
                        //播豆数大于每次消耗次数
                        if (bd >= hf_bd2) {
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
                            mPresenter.leaveAnchor(2 + "", bean.getRegister_id(), bean.getId(), tv_phone_number.getText().toString(), hf_bd2 + "");
                            mPresenter.getResumeDetailBean(id, chat_id);
                            //发推送  boss页面状态刷新
                            BossBean bossBean = new BossBean();
                            bossBean.setRefush(true);
                            bossBean.setPhone(true);
                            bossBean.setRecruit_id(bean.getId() + "");
                            EventBus.getDefault().post(bossBean);
                        } else {
                            //提示去购买
                            mPopWindowPhone3();
                        }
                    }

                }


            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(LookAnchorDetailActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone4.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindowPhone5;

    /**
     * 1。当天留电话次数没有
     */
    private void mPopWindowPhone5() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.compact_add_gonghui10, null);
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
        View rootview = LayoutInflater.from(this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone5.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //是否发布过职位 1 发布过，0未发布
    int is_issue;
    //剩余拨打电话次数
    int residue_num;
    //    公司信息是否完善 1完善 0不完善
    int is_perfect;

    //剩余波豆数
    int bd;
    //拨打电话消耗的波豆数
    int hf_bd;
    //留电话消耗波豆数
    int hf_bd2;
    //发布职位赠送次数
    int recruit_num;

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_issue = resultBean.getCompany().getIs_issue();
        residue_num = resultBean.getCompany().getResidue_num();
        is_perfect = resultBean.getCompany().getIs_perfect();
        bd = resultBean.getCompany().getBd();
        hf_bd2 = resultBean.getCompany().getHf_bd2();
        hf_bd = resultBean.getCompany().getHf_bd();
        recruit_num = resultBean.getCompany().getRecruit_num();
        if (IsfromYueliao) {
            IsfromYueliao = false;
//            是否发布过职位 1 发布过，0未发布, 2审核中
            if (is_issue == 0) {
                mPopWindowPhone1();
            } else if (is_issue == 1) {
                mPresenter.judgeGrade(id);

//                if (bean.getIs_chat() == 1) {
//                    if (mProgressDialog == null) {
//                        mProgressDialog = new ProgressDialogFragment();
//                    }
//                    mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
//                    mPresenter.chatAnchor(id, "1");
//                } else {
//                    mPresenter.guildNumber();
//                }
            } else {
                ToastUtil.showShort("职位正在审核中，审核通过即可获得赠送福利");
            }
        } else {
            mPresenter.leaveAnchor(1 + "", "", "", "", "");
        }
    }

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {

    }

    //    注册好礼领取弹窗
    AlertDialog RobHimeDialog;

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
        RobHimeDialog = WelfareDialog.RobHimeDialog(LookAnchorDetailActivity.this, mAccountDataBean, bean.getNickname(), bean.getType_name(), new View.OnClickListener() {
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
                            mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
                            mPresenter.grabAnchorOrder(id + "", 1 + "", mAccountDataBean.getActivity().getId() + "", mAccountDataBean.getActivity().getG_price() + "");
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
    public void judgeGrade(JudgeGradeBean mAccountDataBean) {
        if (mAccountDataBean.getIs_rob() > 0) {
            //        约聊ID 为0时不跳转约聊界面
            Intent intent = new Intent(this, TellAboutListActivity.class);
            intent.putExtra("chat_id", mAccountDataBean.getChat_id() + "");
            startActivity(intent);
        } else {
            //抢ta的判断
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(LookAnchorDetailActivity.this.getSupportFragmentManager());
            mPresenter.grabAnchor(id);
        }
    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        RobHimeDialog.dismiss();
        //        约聊ID 为0时不跳转约聊界面
        Intent intent = new Intent(this, TellAboutListActivity.class);
        intent.putExtra("chat_id", mAccountDataBean.getChat_id() + "");
        startActivity(intent);
        //抢ta状态刷新
        BossBean bossBean = new BossBean();
        bossBean.setRefush(true);
        bossBean.setQiangTa(true);
        bossBean.setRecruit_id(bean.getId() + "");
        bossBean.setChat_id(mAccountDataBean.getChat_id());
        EventBus.getDefault().post(bossBean);
        mPresenter.getResumeDetailBean(id, chat_id);

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
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.grabAnchorOrder(id + "", 2 + "", "0", mAccountDataBean.getXf_bd() + "");
        } else {
//                            没有波币
            mPopWindowPhone3();
            RobHimeDialog.dismiss();
        }
    }

    @Override
    public void residueNumber() {
        mPresenter.getResumeDetailBean(id, chat_id);
        //发推送  boss页面状态刷新
    }

    @Override
    public void resumeDoCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_collect = 1;
        ToastUtil.showShort("收藏成功");
    }

    @Override
    public void resumeCancelCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_collect = 0;
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
            if (bean.getIs_sign() == 1) {
                ToastUtil.showShort("该主播已签约公会");
            } else {
                if (IsFromAnchorClloect) {
                    //已沟通过
                    if (bean.getIs_linkup() == 1) {
                        if (bean.getTelephone().length() == 0) {
//                   有留电话次数
                            mPopWindowPhone4(true, true);
                        } else {
//                        //已沟通过不判断次数，  相当于有次数
//                        mPopWindowPhone2(true, true);
////                    ToastUtil.showShort("拨打电话次数大于0");
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
                                if (bean.getTelephone().length() == 0) {
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
//                                    mPopWindowPhone2(false, false);
//                    ToastUtil.showShort("没有拨打电话次数，判断播豆够不够");
                                        mPopWindowPhone6(1);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (bean.getTelephone() != null) {
                        //来自拨打电话界面判断 （直接做拨打电话操作）
                        if (bean.getTelephone().length() == 0) {
                            mPopWindowPhone4(true, true);
                        } else {
                            mPopWindowPhone2(true, true);
                        }
                    }
                }
            }
        }

    }

    private static PopupWindow mPopWindowPhone6;

    /**
     * 拨打电话  没有次数消耗波豆弹窗 状态为1 弹窗点击下一步 状态2    已经拨打过弹窗  状态3 （新改的需求）
     */
    private void mPopWindowPhone6(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.compact_add_gonghui9, null);
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
                            mPresenter.residueNumber(bean.getId(), hf_bd + "");
                            mPopWindowPhone6.dismiss();
                            //跳转到下一个弹窗
                            mPopWindowPhone6(2);
                        } else {
                            //提示去购买
                            mPopWindowPhone3();
                            mPopWindowPhone6.dismiss();
                        }
                        break;
                    case 2:
                        BossBean bossBean = new BossBean();
                        bossBean.setRefush(true);
                        bossBean.setPhone(true);
                        bossBean.setRecruit_id(bean.getId() + "");
                        EventBus.getDefault().post(bossBean);
                        mPresenter.getResumeDetailBean(id, chat_id);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + bean.getTelephone().toString());
                        intent.setData(data);
                        startActivity(intent);
                        mPresenter.linkUpAnchor(bean.getRegister_id() + "", bean.getId());
                        mPopWindowPhone6.dismiss();
                        break;
                    case 3:
                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                        Uri data1 = Uri.parse("tel:" + bean.getTelephone().toString());
                        intent1.setData(data1);
                        startActivity(intent1);
                        mPresenter.linkUpAnchor(bean.getRegister_id() + "", bean.getId());
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
                        Intent intent = new Intent(LookAnchorDetailActivity.this, ResumeManageActivity.class);
                        intent.putExtra("positon", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        //跳转到简历管理拨电话界面
                        Intent intent1 = new Intent(LookAnchorDetailActivity.this, ResumeManageActivity.class);
                        intent1.putExtra("positon", 2);
                        startActivity(intent1);
                        break;
                }
                mPopWindowPhone6.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(LookAnchorDetailActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone6.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

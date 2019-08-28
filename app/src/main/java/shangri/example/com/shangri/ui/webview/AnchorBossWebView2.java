package shangri.example.com.shangri.ui.webview;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.formatter.IFillFormatter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.event.BossShareBean;
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
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.adapter.PlatSelect1Adapter;
import shangri.example.com.shangri.ui.adapter.PlatSelectAdapter;
import shangri.example.com.shangri.ui.dialog.BossShareCommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.StartActivityUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class AnchorBossWebView2 extends BaseActivity<BossFragmentView, BossFragmentPresenter> implements BossFragmentView {

    //    @BindView(R.id.webView)
//    BridgeWebView webView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.webview_back)
    ImageView webviewBack;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    String url = "";
    @BindView(R.id.im_jubao)
    ImageView imJubao;
    @BindView(R.id.im_collect)
    ImageView imCollect;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rl_pop)
    RelativeLayout rlPop;


    @BindView(R.id.Im_text)
    ImageView Im_text;
    @BindView(R.id.ll_bottom)
    RelativeLayout ll_bottom;


    @BindView(R.id.tv_chairman_phone)
    TextView tv_chairman_phone;
    @BindView(R.id.ll_anchor_click)
    LinearLayout ll_anchor_click;
    @BindView(R.id.im_hot)
    ImageView imHot;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_plat_name)
    TextView tvPlatName;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.im_company_photo)
    ImageView imCompanyPhoto;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_number)
    TextView tvCompanyNumber;
    @BindView(R.id.tv_anchor_sendresume)
    TextView tvAnchorSendresume;
    @BindView(R.id.tv_anchor_phone)
    TextView tvAnchorPhone;
    @BindView(R.id.tv_low_money)
    TextView tv_low_money;
    @BindView(R.id.tv_is_guanfang)
    ImageView tv_is_guanfang;
    @BindView(R.id.tv_leixing)
    TextView tv_leixing;


    //    是否收藏 1是 0否
    String is_colloect = "";
    //q安全页面提示
    String questUrl = "";
    //招聘的id
    String need_id = "";
    //电话
    String phone = "";
    //公司名称
    String company;
    //类型名字
    String type_name;
    //薪资高低
    String pay_low;
    String pay_high;
    //区域
    String work_position;
    String qq;
    String weixin;
    String youxiang;
    private ProgressDialogFragment mProgressDialog;


    @Override
    protected int getNormalLayoutId() {
        return R.layout.anchor_bosswebview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.anchor_bosswebview;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        need_id = getIntent().getStringExtra("need_id");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AnchorBossWebView2.this.getSupportFragmentManager());
        mPresenter.recruitPage();
        //获取微信号以及电话号
        mPresenter.customLink();
//        url = getIntent().getStringExtra("url")+"?"+id;
        Log.e("Debug", "传过去的id" + need_id);
        mPresenter.getRecruitDetail(need_id);
        if (UserConfig.getInstance().getRole().equals("1")) {
            imJubao.setVisibility(View.GONE);
            imCollect.setVisibility(View.GONE);
            tv_chairman_phone.setVisibility(View.VISIBLE);
            ll_anchor_click.setVisibility(View.GONE);
        } else {
            imJubao.setVisibility(View.VISIBLE);
            imCollect.setVisibility(View.VISIBLE);
            tv_chairman_phone.setVisibility(View.GONE);
            ll_anchor_click.setVisibility(View.VISIBLE);
        }

    }

    //点击跳转公司 所传的token参数
    String Companytoken = "";
    PlatSelect1Adapter TypeAdapter1 = null;
    List<String> mNewsList2 = new ArrayList<>();

    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {
        RecruitDetailBean.XcxBean xxx = resultBean.getXcx().get(0);
        url = RxApi.getBaseUrl() + "api/read/recruit/detail/" + xxx.getId();//getIntent().getStringExtra("url");
        is_colloect = resultBean.getXcx().get(0).getIs_collect() + "";//getIntent().getStringExtra("is_colloect");
        phone = xxx.getContact_phone();//getIntent().getStringExtra("phone");
        Log.d("Debug", "传递过来的电话为" + phone);
        company = xxx.getCompany();//getIntent().getStringExtra("company");
        type_name = xxx.getType_name();//getIntent().getStringExtra("type_name");
        pay_low = xxx.getPay_low();//getIntent().getStringExtra("pay_low");
        pay_high = xxx.getPay_high();//getIntent().getStringExtra("pay_high");
        work_position = xxx.getCompanyInfo().getLocation();//getIntent().getStringExtra("work_position");
        qq = xxx.getQq();//getIntent().getStringExtra("qq");
        weixin = xxx.getWeixin();//getIntent().getStringExtra("weixin");
        youxiang = xxx.getEmail();//getIntent().getStringExtra("youxiang");

        if ("1".equals(is_colloect)) {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.zwxq_ysc));
        } else {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.zwxq_sc));
        }
        Im_text.setVisibility(View.GONE);
        Companytoken = xxx.getToken();
        if (xxx.getIs_ggw() == 1 || xxx.getHot().equals("1")) {
            imHot.setVisibility(View.VISIBLE);
        } else {
            imHot.setVisibility(View.GONE);
        }
        tv_leixing.setText(xxx.getType_name());
        //        发布类型 1系统（爬取的）3 官方（手动发布）
        if (xxx.getPublish_type().equals("3")) {
            tv_is_guanfang.setVisibility(View.VISIBLE);
        } else {
            tv_is_guanfang.setVisibility(View.GONE);
        }
        if (xxx.getTitle().length() > 0) {
            tvTypeName.setText(xxx.getTitle());
        } else {
            tvTypeName.setText(xxx.getType_name());
        }


        //底薪最高价和最低价
        if (Double.parseDouble(xxx.getKeep_pay()) == 0) {
            tv_low_money.setVisibility(View.GONE);
        } else {
            if (Double.parseDouble(xxx.getKeep_pay()) >= 1000) {
                tv_low_money.setText("底薪" + Integer.parseInt(xxx.getKeep_pay()) / 1000 + "K");
            } else {
                tv_low_money.setText("底薪" + Integer.parseInt(xxx.getKeep_pay()));
            }
            tv_low_money.setVisibility(View.VISIBLE);
        }

        tvMoney.setText(Integer.parseInt(xxx.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(xxx.getPay_high()) / 1000 + "K");
        tvPlatName.setText(xxx.getPlat_name() + "");
        try {
            tvCreatetime.setText("发布时间：" + TimeUtil.longToString(Long.parseLong(xxx.getPublish_time() + ""), "yyyy.MM.dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (xxx.getWork_position() == null) {
            tvAddress.setText(xxx.getCompanyInfo().getCompany_name() + "");
        } else {
            tvAddress.setText(xxx.getWork_position() + "    " + xxx.getCompanyInfo().getCompany_name() + "");
        }
        tvDescription.setText(xxx.getJob_description() + "");
        tvCompanyName.setText(xxx.getCompanyInfo().getCompany_name() + "");
        tvCompanyNumber.setText(xxx.getCompanyInfo().getCompany_scale() + "." + xxx.getCompanyInfo().getAnchor_scale() + "位主播");
        Glide.with(this)
                .load(xxx.getCompanyInfo().getLogo())
                .placeholder(R.mipmap.tianjia_tupian)
                .transform(new CornersTransform(this, 10))
                .crossFade()
                .into(imCompanyPhoto);

        if (mNewsList2.size() > 0) {
            mNewsList2.clear();
        }
        if (xxx.getJob_method().length() > 0) {
            String[] split = xxx.getJob_method().split(",");
            for (int i = 0; i < split.length; i++) {
                //            工作方式 1 线上 2 线下 3全部
                if (split[i].equals("1")) {
                    mNewsList2.add("线上");
                } else if (split[i].equals("2")) {
                    mNewsList2.add("线下");
                } else if (split[i].equals("3")) {
                    mNewsList2.add("全部");
                }
            }
        }
        if (xxx.getSalary_type().length() > 0) {
            String[] split2 = xxx.getJob_method().split(",");
//            结算方式 1月结 2周结 3日结 4全部
            for (int i = 0; i < split2.length; i++) {
                if (split2[i].equals("1")) {
                    mNewsList2.add("月结");
                } else if (split2[i].equals("2")) {
                    mNewsList2.add("周结");
                } else if (split2[i].equals("3")) {
                    mNewsList2.add("日结");
                } else if (split2[i].equals("4")) {
                    mNewsList2.add("全部");
                }
            }
        }
        if (xxx.getWelfare() != null) {
            if (xxx.getWelfare().length() > 0) {
                String[] split1 = xxx.getWelfare().split(",");
                for (int i = 0; i < split1.length; i++) {
                    mNewsList2.add(split1[i]);
                }
            }
        }
        TypeAdapter1 = new PlatSelect1Adapter(this, R.layout.item_four2, mNewsList2);
        TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        rvPartol.setLayoutManager(new GridLayoutManager(this, 4));
        rvPartol.setAdapter(TypeAdapter1);
    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossShareBean event) {
        Log.d("Debug", "分享成功");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AnchorBossWebView2.this.getSupportFragmentManager());
        mPresenter.anchorShare(need_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    //主播身份点击拨打电话为 true 一键投递为false
    boolean isAnchorClickPhone = false;

    @OnClick({R.id.rl_company_info, R.id.tv_anchor_phone, R.id.tv_anchor_sendresume, R.id.rl_info, R.id.ll_jubao, R.id.ll_tishi, R.id.reload, R.id.webview_back, R.id.im_jubao, R.id.ll_fenxiang, R.id.im_collect, R.id.tv_chairman_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击公司跳转
            case R.id.rl_company_info:
//                if (UserConfig.getInstance().getRole().equals("0")) {
//                    StartActivityUtils.startSelectRoleActivity(this);
//                    return;
//                }
                Intent intent1 = new Intent(AnchorBossWebView2.this, CompanyHomepageActivityTwo.class);
                intent1.putExtra("COMPANY_TOKEN", Companytoken);
                startActivity(intent1);
                break;
            //主播一键投递
            case R.id.tv_anchor_sendresume:
                if (UserConfig.getInstance().getRole().equals("0")) {
                    StartActivityUtils.startSelectRoleActivity(this);
                    return;
                }
                isAnchorClickPhone = false;
                mPresenter.resumeState();
                break;
            //主播拨打电话
            case R.id.tv_anchor_phone:
                if (UserConfig.getInstance().getRole().equals("0")) {
                    StartActivityUtils.startSelectRoleActivity(this);
                    return;
                }
                isAnchorClickPhone = true;
                mPresenter.resumeState();
                break;
            //会长留电话  弹出弹窗
            case R.id.tv_chairman_phone:
                showPopupWindowSevenDays();
                break;
            //举报界面
            case R.id.ll_jubao:
                showPopupWindow(1);
                rlPop.setVisibility(View.GONE);
                break;
            //安全提示界面
            case R.id.ll_tishi:
                Intent intent = new Intent(AnchorBossWebView2.this, symbolWebView.class);
                intent.putExtra("url", questUrl);
                startActivity(intent);
                rlPop.setVisibility(View.GONE);
                break;
            case R.id.reload:
                break;
            case R.id.rl_info:
                rlPop.setVisibility(View.GONE);
                break;
            case R.id.webview_back:
                finish();
                break;
            //弹窗弹出
            case R.id.im_jubao:
                if (rlPop.getVisibility() == View.VISIBLE) {
                    rlPop.setVisibility(View.GONE);
                } else {
                    rlPop.setVisibility(View.VISIBLE);
                }
                break;
            //分享操作
            case R.id.ll_fenxiang:
                showDialog();
                rlPop.setVisibility(View.GONE);
                break;
            //收藏
            case R.id.im_collect:
                if (UserConfig.getInstance().getRole().equals("0")) {
                    StartActivityUtils.startSelectRoleActivity(this);
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AnchorBossWebView2.this.getSupportFragmentManager());
                if (is_colloect.equals("1")) {
                    mPresenter.cancelCollect(need_id);
                } else {
                    mPresenter.doCollect(need_id);
                }
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

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//            状态 -1没有简历 0:未审核 1:审核通过 2:审核失败
        switch (resultBean.getStatus()) {
            case -1:
                showNoHavePopupWindow1();
                break;
            case 0:
                ToastUtil.showShort("您的简历正在审核中，暂不能操作");
                break;
            case 1:
                Log.d("Debug", "isAnchorClickPhone的状态为" + isAnchorClickPhone);
                if (isAnchorClickPhone) {
                    mPresenter.makeCall(need_id);
                } else {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(AnchorBossWebView2.this.getSupportFragmentManager());
                    //不用展示形象卡   现在直接发送简历
                    mPresenter.sendSucceed("2", need_id, "", "");
                }
                break;
            case 2:
                ToastUtil.showShort("您的简历审核失败，请重新上传简历");
                break;
        }

    }


    @Override
    public void url(BossPlatBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        questUrl = mAccountDataBean.getUrl();
    }

    @Override
    public void doCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_colloect = "1";
        imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.shoucang_2));
        EventBus.getDefault().post(new BossBean(need_id, Integer.parseInt(is_colloect)));
    }

    @Override
    public void noLike() {

    }

    @Override
    public void cancelCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_colloect = "0";
        imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.yinpin_shoucang1));
        EventBus.getDefault().post(new BossBean(need_id, Integer.parseInt(is_colloect)));
    }

    @Override
    public void sendSucceed(sendSucceedResonse resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("发送成功");
        //刷新列表   变成已沟通
        EventBus.getDefault().post(new BossBean(need_id, true));
//        约聊ID 为0时不跳转约聊界面
        if (resultBean.chat_id > 0) {
            Intent intent = new Intent(this, TellAboutListActivity.class);
            intent.putExtra("chat_id", resultBean.getChat_id() + "");
            startActivity(intent);
        }
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
        //分享成功以后的弹窗
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.surplusMakeCall(need_id);
    }

    @Override
    public void anchorMakeTelephone() {

    }

    //是否已拨打电话
    boolean isCall;

    @Override
    public void makeCall(BanagetBean resultBean) {
        Log.d("Debug", "返回拨打电话次数为" + resultBean.getNumber());
        //刷新列表   变成已沟通
        EventBus.getDefault().post(new BossBean(need_id, true));
        if (resultBean.getIs_call() == 1) {
            isCall = false;
            showPopupWindow1();
        } else {
            if (resultBean.getNumber() > 0) {
                isCall = true;
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

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        String tvcontext = "薪资：" + Integer.parseInt(pay_low) / 1000 + "k" + "-" + Integer.parseInt(pay_high) / 1000 + "k" + "\n" + "区域:" + work_position;
        String title = "【" + "直播之家" + "】" + company + "热招" + type_name;
        alertDialog = BossShareCommontDialog.ShareDialog(AnchorBossWebView2.this, url + "?share=1", tvcontext, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.add_boss_info1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        final TextView tv_content = contentView.findViewById(R.id.tv_content);
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        tv1.setText(custom_tel + "");
        tv2.setText(custom_wx + "");
        tv_content1.setText("温馨提示");
        tv_content.setText("该职位联系方式目前暂未对公会开放哦~如有疑问，可联系直播之家客服");
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
        View rootview = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.anchor_bosswebview, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private static PopupWindow mPopWindow;

    /**
     * 1 分享举报 2 沟通10次提醒 3.分享成功以后弹窗
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.compact_comtent_share, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);

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

        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 2) {
                    showDialog();
                }
                mPopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.compact_add_gonghui2, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_title = contentView.findViewById(R.id.tv_title);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_title.setText("拨打电话");
        if (phone == null) {
            phone = "0571-87081736";
        } else {
            if (UserConfig.getInstance().getRole().equals("1")) {
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
                if (isCall) {
                    mPresenter.anchorMakeTelephone(need_id);
                }
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mNoHavePopWindow1;

    /**
     * * 拨打电话
     */


    private void showNoHavePopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.compact_add_gonghui2, null);
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
                startActivity(new Intent(AnchorBossWebView2.this, AddInviteActivity.class));
                mNoHavePopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView2.this).inflate(R.layout.bosswebview, null);
        mNoHavePopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

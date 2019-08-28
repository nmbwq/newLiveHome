package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.InviteListDataBean;
import shangri.example.com.shangri.model.bean.response.PriceListBean;
import shangri.example.com.shangri.model.bean.response.SevenDayEarningsBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.UserBenefitsPresenter;
import shangri.example.com.shangri.presenter.view.UserBenefitsView;
import shangri.example.com.shangri.ui.adapter.InviteListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.DialogUtils;
import shangri.example.com.shangri.util.DpPxUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

import static shangri.example.com.shangri.util.ViewChangeImageUtils.loadBitmapFromView;

/**
 * 用户福利
 */
public class UserBenefitsActivity extends BaseActivity<UserBenefitsView, UserBenefitsPresenter> implements UserBenefitsView {


    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.im_image_erweima)
    ImageView imImageErweima;
    @BindView(R.id.rl_rl)
    RelativeLayout rlRl;
    @BindView(R.id.im1)
    CircleImageView im1;
    //    @BindView(R.id.tv1)
//    TextView tv1;
    @BindView(R.id.tv_bb_rate)
    TextView tvBbRate;
    @BindView(R.id.tv_withdrawal_rules)
    TextView tvWithdrawalRules;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.bt_immediate_withdrawal)
    Button btImmediateWithdrawal;
    @BindView(R.id.cl_withdraw)
    RelativeLayout clWithdraw;
    @BindView(R.id.tv_how)
    TextView tvHow;
    @BindView(R.id.bt_perfect_resume)
    Button btPerfectResume;
    @BindView(R.id.rl_zhuce)
    RelativeLayout rlZhuce;
    @BindView(R.id.im_2)
    ImageView im2;
    @BindView(R.id.bt_commit_jianli)
    Button btCommitJianli;
    @BindView(R.id.rl_jianli)
    RelativeLayout rlJianli;
    @BindView(R.id.im_3)
    ImageView im3;
    @BindView(R.id.bt_invited)
    Button btInvited;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    @BindView(R.id.tv_all_line)
    TextView tvAllLine;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_no_up)
    TextView tvNoUp;
    @BindView(R.id.ll_no_up)
    LinearLayout llNoUp;
    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;

    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.ll_scrollview)
    ScrollView ll_scrollview;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;

    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.im_state)
    ImageView im_state;

    @BindView(R.id.tv_11)
    TextView tv_11;
    @BindView(R.id.tv_12)
    TextView tv_12;
    @BindView(R.id.tv_13)
    TextView tv_13;

    private String balance;
    private int has_resume;
    private int has_apply;
    //微信分享弹窗
    AlertDialog dialog;
    private int has_receive;
    private String share_url;
    //邀请码
    String inviteCode1;
    //    默认0 0-全部 1-无简历
    String no_resume = "0";

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    private InviteListAdapter mAdapter;
    private List<InviteListDataBean.BillsBean> mPatrolList = new ArrayList<>();


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_user_benefits;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_user_benefits;
    }

    @Override
    protected UserBenefitsPresenter createPresenter() {
        return new UserBenefitsPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        rvPartol.setLayoutManager(new FastLinearScrollManger(UserBenefitsActivity.this));
        mAdapter = new InviteListAdapter(UserBenefitsActivity.this, R.layout.new_item_patrol1, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rvPartol.setAdapter(mAdapter);
        inviteListRequest();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getCredits(UserConfig.getInstance().getToken());
    }

    /**
     * 主播邀请详细列表
     */
    private void inviteListRequest() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(UserBenefitsActivity.this.getSupportFragmentManager());
        mPresenter.invitationMyInvite(no_resume, currPage + "");
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                inviteListRequest();
            }

            @Override
            public void onLoadmore() {
                if (currPage < mPageNum) {
                    currPage++;
                    inviteListRequest();
                } else {
                    ToastUtil.showShort("已加载全部");
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                }

            }
        });

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    //    提现条件1 200波币）
    int need_base;
    //    提现条件2 需要注册数3
    int need_cks;
    //    提现条件3 需要查看数5
    int need_zcs;

    int bb_balance;
    //比率
    int bb_rate;
    //背景图片地址
    String invitation_img_url = "";
    //二维码地址
    String qrcode_url = "";

    @Override
    public void Success(AmountBean resultBean) {
        balance = resultBean.getBalance();
        String code = resultBean.getCode();
        has_resume = resultBean.getHas_resume();
        has_receive = resultBean.getHas_receive();
        has_apply = resultBean.getHas_apply();
        share_url = resultBean.getShare_url();
        inviteCode1 = resultBean.getCode();
        need_base = resultBean.getNeed_base();
        need_cks = resultBean.getNeed_cks();
        need_zcs = resultBean.getNeed_zcs();
        bb_balance = resultBean.getBb_balance();
        bb_rate = resultBean.getBb_rate();

//        是否有简历 0否 1正常 2简历审核中 3简历审核失败
        if (has_resume == 1) {
            btCommitJianli.setText("查看");
        } else {
            btCommitJianli.setText("发布简历");
        }
        tvAmount.setText(resultBean.getBb_balance() + "");
        tvBbRate.setText(resultBean.getBb_rate() + "波币=1元");
        tvMoney.setText("可提现￥" + resultBean.getBb_balance() / resultBean.getBb_rate());
        invitation_img_url = resultBean.getInvitation_img_url();
        Log.d("Debug", "返回的image地址是" + resultBean.getInvitation_img_url());
        qrcode_url = resultBean.getQrcode_url();
        tv_11.setText("完成注册获取" + resultBean.getRegister_nice_gift() + "波币(新用户仅1次获得机会)");
        tv_12.setText("简历被公会获取1次可得" + resultBean.getResume_viewed() + "波币");
        tv_13.setText("邀请好友进行注册可得" + resultBean.getInvite_register() + "波币；好友上传简历更可多得" + resultBean.getInvite_resume() + "波币，邀请越多，奖励越多");

        //圆形图片要用下面这个方法加载地址
        BitmapCache.getInstance().loadBitmaps(im1, UserConfig.getInstance().getAvatar() + "", null);
        tv_state.setText(resultBean.getLevel_name() + "");
        switch (resultBean.getLevel_id()) {
            case 0:
                im_state.setVisibility(View.GONE);
                break;
            case 1:
                im_state.setVisibility(View.VISIBLE);
                im_state.setImageDrawable(this.getResources().getDrawable(R.mipmap.chuji));
                break;
            case 2:
                im_state.setVisibility(View.VISIBLE);
                im_state.setImageDrawable(this.getResources().getDrawable(R.mipmap.zhongji));
                break;
            case 3:
                im_state.setVisibility(View.VISIBLE);
                im_state.setImageDrawable(this.getResources().getDrawable(R.mipmap.gaoji));
                break;
        }
    }

    @Override
    public void codeSuccess(InvitationCodeBean codeBean) {

    }

    @Override
    public void applyPondition(InvitationCodeBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//        类型值 0正常 1无简历 2历未审核 3简历审核失败 4波币不足 5邀请好友不足 6简历被看次数不足
        switch (codeBean.getType()) {
            case "0":
                startActivity(new Intent(this, RedEnvelopeWelfareActivity.class)
                        .putExtra("bb_balance", bb_balance + "")
                        .putExtra("bb_rate", bb_rate + "")
                        .putExtra("need_base", need_base + "")
                        .putExtra("need_cks", need_cks + "")
                        .putExtra("need_zcs", need_zcs + ""));
                break;
            case "2":
                ToastUtil.showShort("简历正在审核中，请稍后...");
                break;
            case "7":
                ToastUtil.showShort("您已经有一笔正在提现的金额审核中");
                break;
//            case "1":
//                ToastUtil.showShort("你还没有简历。请先上传简历");
//                break;
//            case "3":
//                ToastUtil.showShort("简历审核失败，请重新上传简历...");
//                break;
//            case "4":
//                ToastUtil.showShort("满200波币可提现，您的波币不足...");
//                break;
//            case "5":
//                ToastUtil.showShort("至少成功邀请3个好友注册才可以提现吆...");
//                break;
//            case "6":
//                ToastUtil.showShort("简历至少被5个公会获取过才可以提现吆...");
//                break;

            case "1":
            case "3":
            case "4":
            case "5":
            case "6":
                DialogUtils.showDialog1(this,1, need_base, need_zcs, need_cks);
                break;
        }

    }

    @Override
    public void invitationMyInvite(InviteListDataBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        sv_partol.onFinishFreshAndLoad();

        if (currPage == 1) {
            mAdapter.setData(codeBean.getBills());
            if (codeBean.getBills().size() > 0) {
                ll_scrollview.setVisibility(View.VISIBLE);
                ll_empty.setVisibility(View.GONE);
            } else {
                ll_scrollview.setVisibility(View.GONE);
                ll_empty.setVisibility(View.VISIBLE);
            }
        } else {
            mAdapter.addAll(codeBean.getBills());
        }
        mPatrolList = mAdapter.getAll();
        mPageNum = codeBean.getTotal_page();
    }

    @Override
    public void sevenDayEarnings(SevenDayEarningsBean codeBean) {

    }

    @Override
    public void userPriceList(PriceListBean codeBean) {

    }

    @Override
    public void upgradeAlert(upgradeAlertBean codeBean) {

    }

    @Override
    public void receiveSuccess(Object codeBean) {

    }


    /**
     * IsimageShare true为图片分享用的是友盟分享（图片过大问题，微信原生分享大于32k不能分享） false 地址分享用的微信原生分享
     *
     * @return
     */
    public AlertDialog YouMengShareDialog(final Boolean IsimageShare, final Bitmap bitmap, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(UserBenefitsActivity.this).inflate(R.layout.share_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        LinearLayout ll1 = view.findViewById(R.id.ll1);
        LinearLayout ll5 = view.findViewById(R.id.ll5);
        LinearLayout ll3 = view.findViewById(R.id.ll3);
        LinearLayout ll4 = view.findViewById(R.id.ll4);
        ll3.setVisibility(View.GONE);
        ll4.setVisibility(View.GONE);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        final AlertDialog dialog = new AlertDialog.Builder(UserBenefitsActivity.this).create();
        dialog.show();
        Window window = dialog.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(DpPxUtils.dip2px(UserBenefitsActivity.this, -1), -1);
        dialog.setCanceledOnTouchOutside(false);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(UserBenefitsActivity.this, bitmap);//bitmap文件
                    new ShareAction(UserBenefitsActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(UserBenefitsActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(UserBenefitsActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {
                    Bitmap myBitmap = BitmapFactory.decodeResource(UserBenefitsActivity.this.getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + inviteCode1, 1, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(UserBenefitsActivity.this, bitmap);//bitmap文件
                    new ShareAction(UserBenefitsActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(UserBenefitsActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(UserBenefitsActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {
                    Bitmap myBitmap = BitmapFactory.decodeResource(UserBenefitsActivity.this.getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + inviteCode1, 2, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) UserBenefitsActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }


    private static UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };


    /**
     * 微信分享
     * // type  1 微信分享  2分享到朋友圈
     *
     * @param httpUrl
     * @param type
     * @param icon
     * @param title
     * @param description
     */

    public static void shareWebPage(String httpUrl, int type, Bitmap icon, String title, String description) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = httpUrl;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        msg.setThumbImage(icon);
        int bitmapSize = getBitmapSize(bmpToByteArray(icon, 32000));
        Log.d("Debug", "压缩后bitmapSize的大小为" + bitmapSize);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        GlobalApp.mWxApi.sendReq(req);
    }

    /**
     * 构建一个唯一标志
     *
     * @param type 分享的类型分字符串
     * @return 返回唯一字符串
     */
    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static Bitmap bmpToByteArray(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, output);
        int options = 80;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return getBitmapFromByte(output.toByteArray());
    }

    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }


    private static PopupWindow mPopWindow;

    /**
     * 分享选择
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(UserBenefitsActivity.this).inflate(R.layout.compact_comtent_share1, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        RelativeLayout ll_info = contentView.findViewById(R.id.ll_info);
        LinearLayout ll_image = contentView.findViewById(R.id.ll_image);
        LinearLayout ll_url = contentView.findViewById(R.id.ll_url);
        //绘制分享图片
        Glide.with(UserBenefitsActivity.this)
                .load(invitation_img_url + "")
                .crossFade()
                .into(imImage);
        Glide.with(UserBenefitsActivity.this)
                .load(qrcode_url + "")
                .crossFade()
                .into(imImageErweima);
        ll_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        ll_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = loadBitmapFromView(rlRl);
                dialog = YouMengShareDialog(true, bitmap, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                mPopWindow.dismiss();
            }
        });
        ll_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = YouMengShareDialog(false, null, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(UserBenefitsActivity.this).inflate(R.layout.activity_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    @OnClick({R.id.ll_myinfo, R.id.im_update_page, R.id.tv_detail, R.id.tv_withdrawal_rules, R.id.bt_immediate_withdrawal, R.id.iv_go_back, R.id.rl_zhuce, R.id.bt_commit_jianli, R.id.bt_invited, R.id.ll_all, R.id.ll_no_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击图片以及我的头像 姓名 跳转到升等级界面
            case R.id.ll_myinfo:
            case R.id.im_update_page:
                break;
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.rl_zhuce:
                break;
            case R.id.tv_detail:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(this, DetailsActivity.class);
                }
                break;
            case R.id.bt_commit_jianli:
//                0否 1正常 2简历审核中 3简历审核失败
                if (has_resume == 1) {
                    //有简历跳转 看过的公司
                    ActivityUtils.startActivity(this, LookMeCompanyListActivity.class);

                } else if (has_resume == 2) {
                    ToastUtil.showShort("您的简历正在审核中，请稍后...");
                } else {
                    //跳转到添加简历
                    ActivityUtils.startActivity(this, AddInviteActivity.class);
                }
                break;
            case R.id.bt_invited:
                if (PointUtils.isFastClick()) {
//                       分享到微信
                    showPopupWindow();
                }
                break;
            case R.id.ll_all:
                tvAllLine.setVisibility(View.VISIBLE);
                tvNoUp.setVisibility(View.GONE);
                no_resume = "0";
                currPage = 1;
                inviteListRequest();
                break;
            case R.id.ll_no_up:
                tvAllLine.setVisibility(View.GONE);
                tvNoUp.setVisibility(View.VISIBLE);
                no_resume = "1";
                currPage = 1;
                inviteListRequest();
                break;
            case R.id.tv_withdrawal_rules:
                DialogUtils.showDialog(this, need_base, need_zcs, need_cks);
                break;
            case R.id.bt_immediate_withdrawal:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(UserBenefitsActivity.this.getSupportFragmentManager());
                    mPresenter.applyPondition(UserConfig.getInstance().getToken());

                }
                break;
        }
    }

}

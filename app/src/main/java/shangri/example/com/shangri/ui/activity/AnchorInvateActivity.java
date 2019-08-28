package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.DpPxUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.WelfareDialog;

import static shangri.example.com.shangri.util.ViewChangeImageUtils.loadBitmapFromView;

/**
 * 邀请界面
 */

public class AnchorInvateActivity extends BaseActivity<UserBenefitsView, UserBenefitsPresenter> implements UserBenefitsView {


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money1)
    TextView tvMoney1;
    @BindView(R.id.tv_invite_number)
    TextView tv_invite_number;
    @BindView(R.id.tv_have_number)
    TextView tv_have_number;

    @BindView(R.id.tv_invite_rules)
    TextView tvInviteRules;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_noup)
    TextView tvNoup;
    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;
    @BindView(R.id.ll_scrollview)
    ScrollView ll_scrollview;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.im_image_erweima)
    ImageView imImageErweima;
    @BindView(R.id.rl_rl)
    RelativeLayout rlRl;
    @BindView(R.id.im_invite)
    ImageView imInvite;
    private ProgressDialogFragment mProgressDialog;

    private InviteListAdapter mAdapter;
    private List<InviteListDataBean.BillsBean> mPatrolList = new ArrayList<>();

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private int currPage = 1;
    private int mPageNum = 0; //总页数
    //    默认0 0-全部 1-无简历
    String no_resume = "0";
    //微信分享弹窗
    AlertDialog dialog;

    //    主播升级弹窗
    AlertDialog dialog3;

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        rvPartol.setLayoutManager(new FastLinearScrollManger(AnchorInvateActivity.this));
        mAdapter = new InviteListAdapter(AnchorInvateActivity.this, R.layout.new_item_patrol1, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rvPartol.setAdapter(mAdapter);
        inviteListRequest();
        if (UserConfig.getInstance().getToken().length() > 0) {
            //主播升级接口请求
            if (UserConfig.getInstance().getRole().equals("2")) {
                mPresenter.upgradeAlert();
            }
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
                currPage = 1;
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


    /**
     * 主播邀请详细列表
     */
    private void inviteListRequest() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AnchorInvateActivity.this.getSupportFragmentManager());
        mPresenter.invitationMyInvite(no_resume, currPage + "");
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.anchor_invite_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.anchor_invite_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected UserBenefitsPresenter createPresenter() {
        return new UserBenefitsPresenter(this, this);
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


    @OnClick({R.id.setting_back, R.id.im_invite, R.id.tv_invite_rules, R.id.tv_all, R.id.tv_noup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.im_invite:
                if (PointUtils.isFastClick()) {
//                       分享到微信
                    showPopupWindow();
                }
                break;
            case R.id.tv_invite_rules:
                if (PointUtils.isFastClick()) {
//                       分享到微信
                    startActivity(new Intent(this, InvateRulesActivity.class));
                }
                break;
            case R.id.tv_all:
                no_resume = "0";
                currPage = 1;
                tvAll.setBackground(this.getResources().getDrawable(R.mipmap.yqhy_qhh));
                tvAll.setTextColor(this.getResources().getColor(R.color.white));
                tvNoup.setBackground(this.getResources().getDrawable(R.mipmap.yqhy_qhb));
                tvNoup.setTextColor(this.getResources().getColor(R.color.text_color_8a4801));
                inviteListRequest();
                break;
            case R.id.tv_noup:
                no_resume = "1";
                currPage = 1;
                tvNoup.setBackground(this.getResources().getDrawable(R.mipmap.yqhy_qhh));
                tvNoup.setTextColor(this.getResources().getColor(R.color.white));
                tvAll.setBackground(this.getResources().getDrawable(R.mipmap.yqhy_qhb));
                tvAll.setTextColor(this.getResources().getColor(R.color.text_color_8a4801));

                inviteListRequest();
                break;
        }
    }

    @Override
    public void Success(AmountBean resultBean) {

    }

    @Override
    public void codeSuccess(InvitationCodeBean codeBean) {

    }

    @Override
    public void applyPondition(InvitationCodeBean codeBean) {

    }

    //    分享底图
    String invitation_img_url = "";
    //    二维码地址
    String qrcode_url = "";
    //    邀请码（没有时为空）
    String code = "";
    //    分享的h5地址URL
    String share_url = "";

    @Override
    public void invitationMyInvite(InviteListDataBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        sv_partol.onFinishFreshAndLoad();

        tvMoney.setText(codeBean.getHeight_rewards() + "");
        tvMoney1.setText("好友可得" + codeBean.getFriend_rewards() + "元");
        tv_invite_number.setText(codeBean.getInvite_number() + "");
        tv_have_number.setText(codeBean.getHave_to_earn() + "");
        invitation_img_url = codeBean.getInvitation_img_url();
        qrcode_url = codeBean.getQrcode_url();
        share_url = codeBean.getShare_url();
        code = codeBean.getCode();
        Log.d("Debug", "分享的地市是" + share_url);

//        //绘制分享图片
//        Glide.with(AnchorInvateActivity.this)
//                .load(invitation_img_url + "")
//                .crossFade()
//                .into(imImage);
//        Glide.with(AnchorInvateActivity.this)
//                .load(qrcode_url + "")
//                .crossFade()
//                .into(imImageErweima);

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
    public void upgradeAlert(upgradeAlertBean resultBean) {
        if (resultBean.getIs_upgrade() == 1) {
            dialog3 = WelfareDialog.WelfareDialog3(AnchorInvateActivity.this, resultBean.getLevel(), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AnchorInvateActivity.this, UpgradeActivity1.class));
                    dialog3.dismiss();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog3.dismiss();
                }
            });
        }
    }


    @Override
    public void receiveSuccess(Object codeBean) {

    }

    private static PopupWindow mPopWindow;

    /**
     * 分享选择
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorInvateActivity.this).inflate(R.layout.compact_comtent_share1, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        RelativeLayout ll_info = contentView.findViewById(R.id.ll_info);
        LinearLayout ll_image = contentView.findViewById(R.id.ll_image);
        LinearLayout ll_url = contentView.findViewById(R.id.ll_url);
        //绘制分享图片
        Glide.with(AnchorInvateActivity.this)
                .load(invitation_img_url + "")
                .crossFade()
                .into(imImage);
        Glide.with(AnchorInvateActivity.this)
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
        View rootview = LayoutInflater.from(AnchorInvateActivity.this).inflate(R.layout.activity_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    /**
     * IsimageShare true为图片分享用的是友盟分享（图片过大问题，微信原生分享大于32k不能分享） false 地址分享用的微信原生分享
     *
     * @return
     */
    public AlertDialog YouMengShareDialog(final Boolean IsimageShare, final Bitmap bitmap, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(AnchorInvateActivity.this).inflate(R.layout.share_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        LinearLayout ll1 = view.findViewById(R.id.ll1);
        LinearLayout ll5 = view.findViewById(R.id.ll5);
        LinearLayout ll3 = view.findViewById(R.id.ll3);
        LinearLayout ll4 = view.findViewById(R.id.ll4);
        ll3.setVisibility(View.GONE);
        ll4.setVisibility(View.GONE);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        final AlertDialog dialog = new AlertDialog.Builder(AnchorInvateActivity.this).create();
        dialog.show();
        Window window = dialog.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(DpPxUtils.dip2px(AnchorInvateActivity.this, -1), -1);
        dialog.setCanceledOnTouchOutside(false);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(AnchorInvateActivity.this, bitmap);//bitmap文件
                    new ShareAction(AnchorInvateActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(AnchorInvateActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(AnchorInvateActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {
                    Bitmap myBitmap = BitmapFactory.decodeResource(AnchorInvateActivity.this.getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + code, 1, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(AnchorInvateActivity.this, bitmap);//bitmap文件
                    new ShareAction(AnchorInvateActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(AnchorInvateActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(AnchorInvateActivity.this)
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {
                    Bitmap myBitmap = BitmapFactory.decodeResource(AnchorInvateActivity.this.getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + code, 2, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) AnchorInvateActivity.this
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


}

package shangri.example.com.shangri.ui.fragment;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.HowMakeMoneyBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.SignBean;
import shangri.example.com.shangri.model.bean.response.everydayMissionBean;
import shangri.example.com.shangri.presenter.SignPresenter;
import shangri.example.com.shangri.presenter.view.SignView;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.AnchorInvateActivity;
import shangri.example.com.shangri.ui.activity.MainActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.activity.RedEnvelopeWelfareActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.DpPxUtils;
import shangri.example.com.shangri.util.ToastUtil;

import static shangri.example.com.shangri.util.ViewChangeImageUtils.loadBitmapFromView;

/**
 * 赚钱界面
 */


public class MakeMoneyFreagment extends BaseFragment<SignView, SignPresenter> implements SignView {


    @BindView(R.id.tv_bb_number)
    TextView tvBbNumber;
    @BindView(R.id.tv_exchange_Money)
    TextView tvExchangeMoney;
    @BindView(R.id.tv_draw)
    TextView tvDraw;
    @BindView(R.id.im_5)
    ImageView im5;
    @BindView(R.id.tv_15_title)
    TextView tv15Title;
    @BindView(R.id.tv_15)
    TextView tv15;
    @BindView(R.id.bt_sendresume)
    Button btSendresume;
    @BindView(R.id.im_3)
    ImageView im3;
    @BindView(R.id.tv_invite_text)
    TextView tvInviteText;
    @BindView(R.id.tv_invite_total)
    TextView tvInviteTotal;
    @BindView(R.id.tv_13)
    TextView tv13;
    @BindView(R.id.tv_invite_maxmoney)
    TextView tvInviteMaxmoney;
    @BindView(R.id.bt_invited)
    Button btInvited;
    @BindView(R.id.rl_invite)
    RelativeLayout rlInvite;
    @BindView(R.id.im_1)
    ImageView im1;
    @BindView(R.id.tv_11_title)
    TextView tv11Title;
    @BindView(R.id.tv_invite_sendresume_total)
    TextView tvInviteSendresumeTotal;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.tv_inviteprople_sendresume_maxmoney)
    TextView tvInvitepropleSendresumeMaxmoney;
    @BindView(R.id.bt_perfect_resume)
    Button btPerfectResume;
    @BindView(R.id.rl_invitepeople_send_resume)
    RelativeLayout rlInvitepeopleSendResume;
    @BindView(R.id.im_12)
    ImageView im12;
    @BindView(R.id.tv_wexinfoces_text)
    TextView tvWexinfocesText;
    @BindView(R.id.bt_weixin_focus)
    Button btWeixinFocus;
    @BindView(R.id.rl_addweixin)
    RelativeLayout rlAddweixin;
    @BindView(R.id.im_2)
    ImageView im2;
    @BindView(R.id.tv_beviewed_text)
    TextView tvBeviewedText;
    @BindView(R.id.tv_beviewed_total)
    TextView tvBeviewedTotal;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.bt_commit_jianli)
    Button btCommitJianli;
    @BindView(R.id.tv_beviewed_maxmoney)
    TextView tvBeviewedMaxmoney;
    @BindView(R.id.rl_jianli)
    RelativeLayout rlJianli;
    @BindView(R.id.rl_task_layout)
    RelativeLayout rlTaskLayout;

    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.im_image_erweima)
    ImageView imImageErweima;
    @BindView(R.id.rl_rl)
    RelativeLayout rlRl;

    Unbinder unbinder;
    private ProgressDialogFragment mProgressDialog;
    //微信分享弹窗
    AlertDialog dialog;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.makemoney_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.makemoney_layout;
    }


    @Override
    protected SignPresenter createPresenter() {
        return new SignPresenter(getActivity(), this);
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void signInList(SignBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void inSign(SignBean resultBean) {

    }


    HowMakeMoneyBean howMakeMoneyBean;

    //    添加的微信号
    String wx = "";
    //    关注的公众号
    String gzh = "";

    //    分享底图
    String invitation_img_url = "";
    //    二维码地址
    String qrcode_url = "";
    //    邀请码（没有时为空）
    String code = "";
    //    分享的h5地址URL
    String share_url = "";

    /**
     * 主播赚钱接口
     *
     * @param resultBean
     */
    @Override
    public void makeMoney(HowMakeMoneyBean resultBean) {
        howMakeMoneyBean = resultBean;
        gzh = resultBean.getFocus_wx().getGzh() + "";
        wx = resultBean.getFocus_wx().getWx() + "";
        code = resultBean.getInvite_anchor().getCode() + "";
        invitation_img_url = resultBean.getInvite_anchor().getInvitation_img_url() + "";
        qrcode_url = resultBean.getInvite_anchor().getQrcode_url() + "";
        share_url = resultBean.getInvite_anchor().getShare_url() + "";
        tvBbNumber.setText(resultBean.getBase().getUse_bb() + "");

        if (howMakeMoneyBean.getBase().getStatus() == 0) {
            tvDraw.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape));
            tvDraw.setTextColor(getActivity().getResources().getColor(R.color.white));
            tvDraw.setText("立即提现");
        } else {
            tvDraw.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
            tvDraw.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
            tvDraw.setText("提现中");
        }

        tvExchangeMoney.setText("可提现" + resultBean.getBase().getUse_bb() / resultBean.getBase().getBb_rate() + "元");
//        提现状态 0未申请 1申请中 2已领取
        switch (resultBean.getSend_resume().getApply_status()) {
            case 0:
                if (resultBean.getSend_resume().getIs_resume()>0&&resultBean.getSend_resume().getSend_status()>0){
                    btSendresume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape43));
                    btSendresume.setTextColor(getActivity().getResources().getColor(R.color.white));
                    btSendresume.setText("立即提现1元");
                }else {
                    btSendresume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                    btSendresume.setTextColor(getActivity().getResources().getColor(R.color.color_e3be84));
                    btSendresume.setText("立即提现1元");
                }

                break;
            case 1:
                btSendresume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
                btSendresume.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
                btSendresume.setText("提现申请中");
                break;
            case 2:
                btSendresume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
                btSendresume.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
                btSendresume.setText("已领取");
                break;
        }

        //邀请好友注册
        if (resultBean.getInvite_anchor().getStatus() == 1) {
            btInvited.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
            btInvited.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
            btInvited.setText("已领取");
        } else {
            if (resultBean.getInvite_anchor().getHas_total() >= resultBean.getInvite_anchor().getNeed_total()) {
                btInvited.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape43));
                btInvited.setTextColor(getActivity().getResources().getColor(R.color.white));
                btInvited.setText("领取" + resultBean.getInvite_anchor().getGet_bb() + "波币");
            } else {
                btInvited.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                btInvited.setTextColor(getActivity().getResources().getColor(R.color.color_e3be84));
                btInvited.setText("领取" + resultBean.getInvite_anchor().getGet_bb() + "波币");
            }
        }
        tvInviteTotal.setText(resultBean.getInvite_anchor().getHas_total() + "/" + resultBean.getInvite_anchor().getNeed_total());
        tvInviteMaxmoney.setText("最高可获得" + resultBean.getInvite_anchor().getGet_max_bb() + "波币");

        //好友投递简历
        if (resultBean.getInvite_send().getStatus() == 1) {
            btPerfectResume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
            btPerfectResume.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
            btPerfectResume.setText("已领取");
        } else {
            if (resultBean.getInvite_send().getHas_total() >= resultBean.getInvite_send().getNeed_total()) {
                btPerfectResume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape43));
                btPerfectResume.setTextColor(getActivity().getResources().getColor(R.color.white));
                btPerfectResume.setText("领取" + resultBean.getInvite_send().getGet_bb() + "波币");
            } else {
                btPerfectResume.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                btPerfectResume.setTextColor(getActivity().getResources().getColor(R.color.color_e3be84));
                btPerfectResume.setText("领取" + resultBean.getInvite_send().getGet_bb() + "波币");
            }
        }
        tvInviteSendresumeTotal.setText(resultBean.getInvite_send().getHas_total() + "/" + resultBean.getInvite_send().getNeed_total());
        tvInvitepropleSendresumeMaxmoney.setText("最高可获得" + resultBean.getInvite_send().getGet_max_bb() + "波币");
        //微信公众号是否关注
//        是否关注 1关注 0未关注
        if (resultBean.getFocus_wx().getIs_focus() > 0) {
            btWeixinFocus.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
            btWeixinFocus.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
            btWeixinFocus.setText("已领取");
        } else {
            btWeixinFocus.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
            btWeixinFocus.setTextColor(getActivity().getResources().getColor(R.color.color_e3be84));
            btWeixinFocus.setText("领取" + resultBean.getFocus_wx().getGet_bb() + "波币");
        }

        //简历被工会查看
        if (resultBean.getResume_view().getStatus() == 1) {
            btCommitJianli.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
            btCommitJianli.setTextColor(getActivity().getResources().getColor(R.color.color_999999));
            btCommitJianli.setText("已领取");
        } else {
            if (resultBean.getResume_view().getHas_total() >= resultBean.getResume_view().getNeed_total()) {
                btCommitJianli.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape43));
                btCommitJianli.setTextColor(getActivity().getResources().getColor(R.color.white));
                btCommitJianli.setText("领取" + resultBean.getResume_view().getGet_bb() + "波币");
            } else {
                btCommitJianli.setBackground(getActivity().getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                btCommitJianli.setTextColor(getActivity().getResources().getColor(R.color.color_e3be84));
                btCommitJianli.setText("领取" + resultBean.getResume_view().getGet_bb() + "波币");
            }
        }
        tvBeviewedTotal.setText(resultBean.getResume_view().getHas_total() + "/" + resultBean.getResume_view().getNeed_total());
        tvBeviewedMaxmoney.setText("最高可获得" + resultBean.getResume_view().getGet_max_bb() + "波币");
    }

    /**
     * 赚钱页面领取波币接口
     */
    @Override
    public void makeMoneyGain() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.makeMoney();
        ToastUtil.showShort("领取成功");
    }

    @Override
    public void getCredits(AmountBean resultBean) {

    }

    @Override
    public void everydayMission(everydayMissionBean resultBean) {

    }


    @Override
    public void resumeDraw() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    //在本页面  点击操作跳转到其他界面返回来  刷新操作
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.makeMoney();
    }


    //    底导切换回来的时候刷新数据
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

        } else {  // 在最前端显示 相当于调用了onResume();
            mPresenter.makeMoney();
        }
    }


    /**
     * * 登录好礼领取
     */


    @Override
    public void givesGet() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void applyPondition(InvitationCodeBean codeBean) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_draw, R.id.bt_sendresume, R.id.bt_invited, R.id.bt_perfect_resume, R.id.bt_weixin_focus, R.id.bt_commit_jianli})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //体现
            case R.id.tv_draw:
//                状态 1体现中 0跳转
                if (howMakeMoneyBean.getBase().getStatus() == 0) {
                    //跳转体现界面
                    startActivity(new Intent(getActivity(), RedEnvelopeWelfareActivity.class)
                            .putExtra("bb_balance", howMakeMoneyBean.getBase().getUse_bb() + "")
                            .putExtra("bb_rate", howMakeMoneyBean.getBase().getBb_rate() + ""));
                } else {
                    ToastUtil.showShort("您已经有一笔正在提现的金额审核中");
                }
                break;
            //试着简历投递
            case R.id.bt_sendresume:
                switch (howMakeMoneyBean.getSend_resume().getApply_status()) {
                    case 0:
                        //判断有没有简历  0是没有简历  1是有简历
                        if (howMakeMoneyBean.getSend_resume().getIs_resume() > 0) {
//                            简历投递状态 0未投递 1已投递
                            if (howMakeMoneyBean.getSend_resume().getSend_status() > 0) {
                                //跳转体现界面
                                startActivity(new Intent(getActivity(), RedEnvelopeWelfareActivity.class)
                                        .putExtra("bb_balance", howMakeMoneyBean.getBase().getUse_bb() + "")
                                        .putExtra("bb_rate", howMakeMoneyBean.getBase().getBb_rate() + ""));
                            } else {
                                //跳转mainactivity页面
                                FourshowEventBean fourshowEventBean = new FourshowEventBean();
                                fourshowEventBean.setIsfromMakeMoney(true);
                                EventBus.getDefault().post(fourshowEventBean);
                            }
                        } else {
                            //跳转导上传简历界面
                            startActivity(new Intent(getActivity(), AddInviteActivity.class));
                        }
                        break;
                }
                break;
            //邀请好友
            case R.id.bt_invited:
                if (howMakeMoneyBean.getInvite_anchor().getStatus() == 1) {
                    ToastUtil.showShort("已领取");
                } else {
                    if (howMakeMoneyBean.getInvite_anchor().getHas_total() >= howMakeMoneyBean.getInvite_anchor().getNeed_total()) {
                        //波币领取接口
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.makeMoneyGain(1, howMakeMoneyBean.getInvite_anchor().getStage(), howMakeMoneyBean.getInvite_anchor().getGet_bb());
                    } else {
                        //跳转分享
                        showPopupWindow();
                    }
                }

                break;
//            好友投递简历
            case R.id.bt_perfect_resume:
                if (howMakeMoneyBean.getInvite_send().getStatus() == 1) {
                    ToastUtil.showShort("已领取");
                } else {
                    if (howMakeMoneyBean.getInvite_send().getHas_total() >= howMakeMoneyBean.getInvite_send().getNeed_total()) {
                        //波币领取接口
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.makeMoneyGain(2, howMakeMoneyBean.getInvite_send().getStage(), howMakeMoneyBean.getInvite_send().getGet_bb());
                    } else {
                        ToastUtil.showShort("未达到领取条件");
                    }
                }
                break;
            //微信公众号关注
            case R.id.bt_weixin_focus:
//                1关注 0未关注
                if (howMakeMoneyBean.getFocus_wx().getIs_focus() > 0) {
                    ToastUtil.showShort("已关注");
                } else {
                    showPopupWindowSevenDays();
                }
                break;
//            简历被公会获取
            case R.id.bt_commit_jianli:
                if (howMakeMoneyBean.getResume_view().getStatus() == 1) {
                    ToastUtil.showShort("已领取");
                } else {
                    if (howMakeMoneyBean.getResume_view().getHas_total() >= howMakeMoneyBean.getResume_view().getNeed_total()) {
                        //波币领取接口
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.makeMoneyGain(3, howMakeMoneyBean.getResume_view().getStage(), howMakeMoneyBean.getResume_view().getGet_bb());
                    } else {
                        ToastUtil.showShort("未达到领取条件");
                    }
                }
                break;
        }
    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * * 发布职位弹窗
     */


    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.add_boss_info2, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        tv1.setText(gzh + "");
        tv2.setText(wx + "");
//        //显示PopupWindow
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(tv1.getText().toString() + ""); // 复制
                ToastUtil.showShort("已复制成功");
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
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_signandtask_layout, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow;

    /**
     * 分享选择
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_comtent_share1, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        RelativeLayout ll_info = contentView.findViewById(R.id.ll_info);
        LinearLayout ll_image = contentView.findViewById(R.id.ll_image);
        LinearLayout ll_url = contentView.findViewById(R.id.ll_url);
        //绘制分享图片
        Glide.with(getActivity())
                .load(invitation_img_url + "")
                .crossFade()
                .into(imImage);
        Glide.with(getActivity())
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
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    /**
     * IsimageShare true为图片分享用的是友盟分享（图片过大问题，微信原生分享大于32k不能分享） false 地址分享用的微信原生分享
     *
     * @return
     */
    public AlertDialog YouMengShareDialog(final Boolean IsimageShare, final Bitmap bitmap, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.share_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        LinearLayout ll1 = view.findViewById(R.id.ll1);
        LinearLayout ll5 = view.findViewById(R.id.ll5);
        LinearLayout ll3 = view.findViewById(R.id.ll3);
        LinearLayout ll4 = view.findViewById(R.id.ll4);
        ll3.setVisibility(View.GONE);
        ll4.setVisibility(View.GONE);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        Window window = dialog.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(DpPxUtils.dip2px(getActivity(), -1), -1);
        dialog.setCanceledOnTouchOutside(false);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(getActivity(), bitmap);//bitmap文件
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {

                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImageErweima);

                    Bitmap myBitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + code, 1, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (IsimageShare) {
                    UMImage image = new UMImage(getActivity(), bitmap);//bitmap文件
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                } else {
                    //因为分页向上滑动会显示 绘制的图片，所以分享以后把绘制图片清除
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImage);
                    Glide.with(getActivity())
                            .load("")
                            .crossFade()
                            .into(imImageErweima);
                    Bitmap myBitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.all_android);
                    shareWebPage(share_url + code, 2, myBitmap, "上直播之家找工作，赢取百万现金", "直播之家，专业的直播行业招聘平台");
                }
            }
        });
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) getActivity()
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

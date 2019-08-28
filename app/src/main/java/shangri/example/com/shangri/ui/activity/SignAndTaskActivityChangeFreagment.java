package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
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
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.DialogUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * * 签到以及任务界面
 */


public class SignAndTaskActivityChangeFreagment extends BaseFragment<SignView, SignPresenter> implements SignView {


    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.tv_11_title)
    TextView tv_11_title;
    @BindView(R.id.bt_perfect_resume)
    Button btPerfectResume;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.bt_commit_jianli)
    Button btCommitJianli;
    @BindView(R.id.tv_13)
    TextView tv13;
    @BindView(R.id.bt_invited)
    Button btInvited;
    @BindView(R.id.tv_14)
    TextView tv14;
    @BindView(R.id.bt_sign)
    Button btSign;
    @BindView(R.id.tv_15)
    TextView tv15;
    @BindView(R.id.tv_is_login_welfare)
    TextView tv_is_login_welfare;


    @BindView(R.id.tv_15_title)
    TextView tv_15_title;
    @BindView(R.id.im_5)
    ImageView im_5;


    @BindView(R.id.bt_sendresume)
    Button btSendresume;
    @BindView(R.id.rl_task_layout)
    RelativeLayout rlTaskLayout;

    @BindView(R.id.rl_jianli)
    RelativeLayout rl_jianli;
    @BindView(R.id.rl_invite)
    RelativeLayout rl_invite;

    @BindView(R.id.tv_wexinfoces_text)
    TextView tv_wexinfoces_text;
    @BindView(R.id.bt_weixin_focus)
    Button bt_weixin_focus;

    @BindView(R.id.iv_fuli)
    ImageView iv_fuli;
    @BindView(R.id.tv_bb_number)
    TextView tv_bb_number;

    @BindView(R.id.tv_exchange_Money)
    TextView tv_exchange_Money;


    private ProgressDialogFragment mProgressDialog;
    //    true 默认显示的是 任务界面  false 是显示的签到界面
    boolean isFromAnchorBobi = false;

    @Override
    protected void initViewsAndEvents() {
        //任务布局 上半部分需要的数据
        mPresenter.getCredits(UserConfig.getInstance().getToken() + "");
        //任务布局 下半部分需要的数据
        mPresenter.everydayMission();
        tv_is_login_welfare.setVisibility(View.GONE);
        tv_15_title.setText("简历投递");
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_signandtask_layout1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_signandtask_layout1;
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


    @OnClick({R.id.bt_weixin_focus, R.id.tv_draw, R.id.iv_fuli, R.id.bt_perfect_resume, R.id.bt_commit_jianli, R.id.bt_invited, R.id.bt_sign, R.id.bt_sendresume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //提现
            case R.id.tv_draw:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    mPresenter.applyPondition(UserConfig.getInstance().getToken());
                }
                break;
            //注册好礼
            case R.id.bt_perfect_resume:
                ToastUtil.showShort("已领取");
                break;
            //添加微信、关注公众号
            case R.id.bt_weixin_focus:
                if (PointUtils.isFastClick()) {
                    if (is_focus_wx == 0) {
                        showPopupWindowSevenDays();
                    }
                }
                break;
            case R.id.bt_commit_jianli:
//                0否 1正常 2简历审核中 3简历审核失败
                if (PointUtils.isFastClick()) {
                    if (has_resume == 1) {
                        //有简历跳转 看过的公司
                        ActivityUtils.startActivity(getActivity(), LookMeCompanyListActivity.class);
                    } else if (has_resume == 2) {
                        ToastUtil.showShort("您的简历正在审核中，请稍后...");
                    } else {
                        //跳转到添加简历
                        ActivityUtils.startActivity(getActivity(), AddInviteActivity.class);
                    }
                }
                break;
            //邀请好友
            case R.id.bt_invited:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), AnchorInvateActivity.class);
                }
                break;
            //签到
            case R.id.bt_sign:
                if (PointUtils.isFastClick()) {
                    if (is_sign_task == 0) {
                        btSign.setText("已签到");
                        btSign.setTextColor(getResources().getColor(R.color.color_e3be84));
                        btSign.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                        is_sign_task = 1;
                        Intent intent = new Intent(getActivity(), SignAndTaskActivity.class);
                        intent.putExtra("isFromAnchorBobi", false);
                        startActivity(intent);
                    } else {
                        //跳转到签到页面直接签到成功    所以这面直接赋值
                        ToastUtil.showShort("已签到");
                    }
                }
                break;
            //发送简历
            case R.id.bt_sendresume:
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "is_send_resume的值为" + is_send_resume);
                    switch (is_send_resume) {
                        case 0:
                            //跳转mainactivity页面
                            FourshowEventBean fourshowEventBean = new FourshowEventBean();
                            fourshowEventBean.setIsfromMakeMoney(true);
                            EventBus.getDefault().post(fourshowEventBean);
                            break;
                        case 1:
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(getActivity().getSupportFragmentManager());
                            mPresenter.resumeDraw();
                            break;
                        case 2:
                            //已领取
                            ToastUtil.showShort("已领取");
                            break;
                    }
                }
                //点击福利
            case R.id.iv_fuli:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), AnchorInvateActivity.class));
                }
                break;


        }
    }

    @Override
    public void signInList(SignBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    //   签到弹窗
    AlertDialog dialog4;

    @Override
    public void inSign(SignBean resultBean) {
    }

    @Override
    public void makeMoney(HowMakeMoneyBean resultBean) {

    }

    @Override
    public void makeMoneyGain() {

    }

    //        是否有简历 0否 1正常 2简历审核中 3简历审核失败
    int has_resume;
    //    是否关注微信 1是 0否
    int is_focus_wx;
    //    添加的微信号
    String wx = "";
    //    关注的公众号
    String gzh = "";

    @Override
    public void getCredits(AmountBean resultBean) {
//        是否有简历 0否 1正常 2简历审核中 3简历审核失败
        has_resume = resultBean.getHas_resume();
        is_focus_wx = resultBean.getIs_focus_wx();
        wx = resultBean.getWx();
        gzh = resultBean.getGzh();
        if (has_resume == 1) {
            btCommitJianli.setText("查看");
        } else {
            btCommitJianli.setText("发布简历");
        }
        if (is_focus_wx == 1) {
            bt_weixin_focus.setText("已关注");
            bt_weixin_focus.setTextColor(getResources().getColor(R.color.color_999999));
            bt_weixin_focus.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
        } else {
            bt_weixin_focus.setText("去关注");
            bt_weixin_focus.setTextColor(getResources().getColor(R.color.color_e3be84));
            bt_weixin_focus.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape35));

        }
        tv11.setText("完成注册获取" + resultBean.getRegister_nice_gift() + "波币(新用户仅1次获得机会)");
        tv12.setText("简历被公会获取1次可得" + resultBean.getResume_viewed() + "波币");
        tv13.setText("邀请好友进行注册可得" + resultBean.getInvite_register() + "波币；好友上传简历更可多得" + resultBean.getInvite_resume() + "波币，邀请越多，奖励越多");
        tv_wexinfoces_text.setText("任务完成后获取" + resultBean.getFocus_gift() + "波币");
    }

    //    是否签到 0未签到 1已签到
    int is_sign_task;
    //    投递状态 0去完成 1领取 2已领取
    int is_send_resume;
    //    今日是否已登录领取 0未领取 1已领取
    int is_today_gives;
    //    每日登录活动状态 0结束 1正常
    int is_login_welfare;

    int bb_balance;
    //比率
    int bb_rate;
    //    提现条件1 200波币）
    int need_base;
    //    提现条件2 需要注册数3
    int need_cks;
    //    提现条件3 需要查看数5
    int need_zcs;


    @Override
    public void everydayMission(everydayMissionBean resultBean) {
        is_sign_task = resultBean.getIs_sign();
        is_send_resume = resultBean.getIs_send_resume();
        is_today_gives = resultBean.getIs_today_gives();
        is_login_welfare = resultBean.getIs_login_welfare();
        tv14.setText("签到最高可得" + resultBean.getSign_height() + "波币");
        if (is_sign_task == 1) {
            btSign.setText("已签到");
            btSign.setTextColor(getResources().getColor(R.color.color_999999));
            btSign.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
        } else {
            btSign.setText("去签到");
            btSign.setTextColor(getResources().getColor(R.color.color_e3be84));
            btSign.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
        }
        switch (is_send_resume) {
            case 0:
                btSendresume.setText("去完成");
                btSendresume.setTextColor(getResources().getColor(R.color.color_e3be84));
                btSendresume.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                break;
            case 1:
                btSendresume.setText("领取");
                btSendresume.setTextColor(getResources().getColor(R.color.color_e3be84));
                btSendresume.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape35));
                break;
            case 2:
                btSendresume.setText("已领取");
                btSendresume.setTextColor(getResources().getColor(R.color.color_999999));
                btSendresume.setBackground(getResources().getDrawable(R.drawable.color_dialog_commit_shape34));
                break;
        }
        tv15.setText("投递简历即可获得" + resultBean.getDeliver_resume() + "波币");
        if (resultBean.getImg_url().length() > 0) {
            iv_fuli.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(resultBean.getImg_url()).into(iv_fuli);
        } else {
            iv_fuli.setVisibility(View.GONE);
        }
        bb_balance = resultBean.getUse_bb();
        bb_rate = resultBean.getBb_rate();

        tv_bb_number.setText(resultBean.getUse_bb() + "");
        tv_exchange_Money.setText("可提现" + resultBean.getUse_bb() / resultBean.getBb_rate() + "元");
    }

    @Override
    public void resumeDraw() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.everydayMission();
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

    /**
     * 提现接口判断
     *
     * @param codeBean
     */
    @Override
    public void applyPondition(InvitationCodeBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        need_base = codeBean.getNeed_base();
        need_cks = codeBean.getReg_register();
        need_zcs = codeBean.getCall_count();

//        类型值 0正常 1无简历 2历未审核 3简历审核失败 4波币不足 5邀请好友不足 6简历被看次数不足
        switch (codeBean.getType()) {
            case "0":
                startActivity(new Intent(getActivity(), RedEnvelopeWelfareActivity.class)
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
            case "1":
            case "3":
            case "4":
            case "5":
            case "6":
                DialogUtils.showDialog1(getActivity(), 2, need_base, need_zcs, need_cks);
                break;
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

        } else {  // 在最前端显示 相当于调用了onResume();
//            isTrue = true;
            Log.d("Debug","点击SignAndTaskActivityChangeFreagment刷新");
//    //任务布局 下半部分需要的数据
            mPresenter.everydayMission();
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

}

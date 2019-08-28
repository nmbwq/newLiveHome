package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.HowMakeMoneyBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.SignBean;
import shangri.example.com.shangri.model.bean.response.everydayMissionBean;
import shangri.example.com.shangri.presenter.SignPresenter;
import shangri.example.com.shangri.presenter.view.SignView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.WelfareDialog;

/**
 * 签到以及任务界面
 */

public class SignAndTaskActivity extends BaseActivity<SignView, SignPresenter> implements SignView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.rl_appbarlayout)
    RelativeLayout rl_appbarlayout;
    @BindView(R.id.tv_sign)
    TextView tv_sign;
    @BindView(R.id.tv_day_number)
    TextView tvDayNumber;
    @BindView(R.id.im_sign_1)
    ImageView imSign1;
    @BindView(R.id.tv_sign_line1)
    TextView tvSignLine1;
    @BindView(R.id.tv_sign_number1)
    TextView tvSignNumber1;
    @BindView(R.id.im_sign_2)
    ImageView imSign2;
    @BindView(R.id.tv_sign_line2)
    TextView tvSignLine2;
    @BindView(R.id.tv_sign_number2)
    TextView tvSignNumber2;
    @BindView(R.id.im_sign_3)
    ImageView imSign3;
    @BindView(R.id.tv_sign_line3)
    TextView tvSignLine3;
    @BindView(R.id.tv_sign_number3)
    TextView tvSignNumber3;
    @BindView(R.id.im_sign_4)
    ImageView imSign4;
    @BindView(R.id.tv_sign_line4)
    TextView tvSignLine4;
    @BindView(R.id.tv_sign_number4)
    TextView tvSignNumber4;
    @BindView(R.id.im_sign_5)
    ImageView imSign5;
    @BindView(R.id.tv_sign_line5)
    TextView tvSignLine5;
    @BindView(R.id.tv_sign_number5)
    TextView tvSignNumber5;
    @BindView(R.id.im_sign_6)
    ImageView imSign6;
    @BindView(R.id.tv_sign_line6)
    TextView tvSignLine6;
    @BindView(R.id.tv_sign_number6)
    TextView tvSignNumber6;
    @BindView(R.id.im_sign_7)
    ImageView imSign7;
    @BindView(R.id.tv_sign_number7)
    TextView tvSignNumber7;
    @BindView(R.id.cv_qiandao)
    RelativeLayout cvQiandao;
    @BindView(R.id.rl_sign_info)
    RelativeLayout rlSignInfo;
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
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_wexinfoces_text)
    TextView tv_wexinfoces_text;
    @BindView(R.id.bt_weixin_focus)
    Button bt_weixin_focus;


    private ProgressDialogFragment mProgressDialog;
    //    true 默认显示的是 任务界面  false 是显示的签到界面
    boolean isFromAnchorBobi = false;

    @Override
    protected void initViewsAndEvents() {
        isFromAnchorBobi = getIntent().getBooleanExtra("isFromAnchorBobi", false);
        if (isFromAnchorBobi) {
            tv_title.setText("任务中心");
        } else {
            tv_title.setText("签到");
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            if (this.isFromAnchorBobi) {
                //波币界面过来界面默认显示的是任务界面
                rlTaskLayout.setVisibility(View.VISIBLE);
                rlSignInfo.setVisibility(View.GONE);
                rl_appbarlayout.setBackground(SignAndTaskActivity.this.getResources().getDrawable(R.drawable.color_dialog_commit_shape27));
                //任务布局 上半部分需要的数据
                mPresenter.getCredits(UserConfig.getInstance().getToken() + "");
                //任务布局 下半部分需要的数据
                mPresenter.everydayMission();
            } else {
                rlTaskLayout.setVisibility(View.GONE);
                rlSignInfo.setVisibility(View.VISIBLE);
                rl_appbarlayout.setBackground(SignAndTaskActivity.this.getResources().getDrawable(R.mipmap.rwzx_bgd));
                mPresenter.inSign();
            }
            tv_is_login_welfare.setVisibility(View.GONE);
            tv_15_title.setText("简历投递");
            im_5.setImageDrawable(this.getResources().getDrawable(R.mipmap.bd_jltd));
        } else {
            if (isFromAnchorBobi) {
                rlTaskLayout.setVisibility(View.VISIBLE);
                rlSignInfo.setVisibility(View.GONE);
                rl_appbarlayout.setBackground(SignAndTaskActivity.this.getResources().getDrawable(R.drawable.color_dialog_commit_shape27));
                mPresenter.getCredits(UserConfig.getInstance().getToken() + "");
                mPresenter.everydayMission();
            } else {
                rlTaskLayout.setVisibility(View.GONE);
                rlSignInfo.setVisibility(View.VISIBLE);
                rl_appbarlayout.setBackground(SignAndTaskActivity.this.getResources().getDrawable(R.mipmap.rwzx_bgd));
                mPresenter.inSign();
            }
            rl_jianli.setVisibility(View.GONE);
            rl_invite.setVisibility(View.GONE);
            tv_is_login_welfare.setVisibility(View.VISIBLE);
            tv_15_title.setText("每日登陆");
            im_5.setImageDrawable(this.getResources().getDrawable(R.mipmap.bd_mrdl));
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(SignAndTaskActivity.this.getSupportFragmentManager());
        mPresenter.signInList();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_signandtask_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_signandtask_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected SignPresenter createPresenter() {
        return new SignPresenter(this, this);
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


    @OnClick({R.id.bt_weixin_focus, R.id.setting_back, R.id.tv_sign, R.id.tv_sign_rules, R.id.bt_perfect_resume, R.id.bt_commit_jianli, R.id.bt_invited, R.id.bt_sign, R.id.bt_sendresume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_sign:
                if (is_today_sign == 1) {
                    ToastUtil.showShort("今天您已经签到啦");
                } else {
                    tv_title.setText("签到");
                    rlTaskLayout.setVisibility(View.GONE);
                    rlSignInfo.setVisibility(View.VISIBLE);
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(SignAndTaskActivity.this.getSupportFragmentManager());
                    mPresenter.inSign();
                }
                break;
            case R.id.tv_sign_rules:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(this, SignRulesActivity.class);
                    intent.putExtra("list", (Serializable) invitationType);
                    startActivity(intent);
                }
                break;
            case R.id.bt_perfect_resume:
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
                        ActivityUtils.startActivity(this, LookMeCompanyListActivity.class);

                    } else if (has_resume == 2) {
                        ToastUtil.showShort("您的简历正在审核中，请稍后...");
                    } else {
                        //跳转到添加简历
                        ActivityUtils.startActivity(this, AddInviteActivity.class);
                    }
                }
                break;
            case R.id.bt_invited:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(this, AnchorInvateActivity.class);
                }
                break;
            case R.id.bt_sign:
                if (PointUtils.isFastClick()) {
                    if (is_sign_task == 1) {
                        ToastUtil.showShort("您已经签到过啦");
                    } else {
                        rlTaskLayout.setVisibility(View.GONE);
                        rlSignInfo.setVisibility(View.VISIBLE);
                        rl_appbarlayout.setBackground(SignAndTaskActivity.this.getResources().getDrawable(R.mipmap.rwzx_bgd));
                    }
                }
                break;
            case R.id.bt_sendresume:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                        switch (is_send_resume) {
                            case 0:
                                //跳转mainactivity页面
                                Intent intent1 = new Intent(this, MainActivity.class);
                                intent1.putExtra("type", 0);
                                startActivity(intent1);
                                break;
                            case 1:
                                if (mProgressDialog == null) {
                                    mProgressDialog = new ProgressDialogFragment();
                                }
                                mProgressDialog.show(SignAndTaskActivity.this.getSupportFragmentManager());
                                mPresenter.resumeDraw();
                                break;
                            case 2:
                                //已领取
                                break;
                        }
                    } else {
                        if (is_login_welfare == 1) {
                            if (is_today_gives == 1) {
                                ToastUtil.showShort("您已经领取了");
                            } else {
                                if (mProgressDialog == null) {
                                    mProgressDialog = new ProgressDialogFragment();
                                }
                                mProgressDialog.show(SignAndTaskActivity.this.getSupportFragmentManager());
                                mPresenter.givesGet();
                            }
                        } else {
                            ToastUtil.showShort("活动已结束");
                        }
                    }

                }
                break;
        }
    }

    //        今日是否签到 0否 1是
    int is_today_sign;
    //    是否断签 1是 0否
    int is_sign;
    //    连续签到次数(7天一周期)
    int sitcom_sign_number;
    //每次签到获得奖励列表
    List<SignBean.InvitationTypeBean> invitationType;

    @Override
    public void signInList(SignBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_today_sign = resultBean.getIs_today_sign();
        is_sign = resultBean.getIs_sign();
        sitcom_sign_number = resultBean.getSitcom_sign_number();
        tvDayNumber.setText(resultBean.getAdd_sign_day() + "");
        if (is_today_sign == 0) {
            tv_sign.setText("立即签到");
        } else {
            tv_sign.setText("连续签到" + resultBean.getContinuous_sign_day() + "天");
        }
        invitationType = resultBean.getInvitationType();
        tvSignNumber1.setText("+" + resultBean.getInvitationType().get(0).getNum() + "");
        tvSignNumber2.setText("+" + resultBean.getInvitationType().get(1).getNum() + "");
        tvSignNumber3.setText("+" + resultBean.getInvitationType().get(2).getNum() + "");
        tvSignNumber4.setText("+" + resultBean.getInvitationType().get(3).getNum() + "");
        tvSignNumber5.setText("+" + resultBean.getInvitationType().get(4).getNum() + "");
        tvSignNumber6.setText("+" + resultBean.getInvitationType().get(5).getNum() + "");
        tvSignNumber7.setText("+" + resultBean.getInvitationType().get(6).getNum() + "");
        if (is_sign == 1) {
            //图标以及横线都为灰色
            refushView();
        } else {
            //先全部设置成原色
            refushView();
//            没有断签  sitcom_sign_number返回数  就是布局有几个变黄色
            switch (sitcom_sign_number) {
                case 1:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 2:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 3:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 4:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign4.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine4.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 5:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign4.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine4.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign5.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine5.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 6:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign4.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine4.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign5.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine5.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign6.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine6.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    break;
                case 7:
                    imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign4.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine4.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign5.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    tvSignLine5.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign6.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bb));
                    tvSignLine6.setBackgroundColor(this.getResources().getColor(R.color.text_color_725819));
                    imSign7.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jl));
                    break;
            }
        }
    }

    //   签到弹窗
    AlertDialog dialog4;

    @Override
    public void inSign(SignBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.signInList();
        dialog4 = WelfareDialog.WelfareDialog4(this, resultBean.getCurrent_num(), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog4.dismiss();
            }
        });

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
        } else {
            bt_weixin_focus.setText("去关注");
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            tv11.setText("完成注册获取" + resultBean.getRegister_nice_gift() + "波币(新用户仅1次获得机会)");
        } else {
            tv11.setText("完成注册获取" + resultBean.getRegister_nice_gift() + "波币");
        }
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

    @Override
    public void everydayMission(everydayMissionBean resultBean) {
        is_sign_task = resultBean.getIs_sign();
        is_send_resume = resultBean.getIs_send_resume();
        is_today_gives = resultBean.getIs_today_gives();
        is_login_welfare = resultBean.getIs_login_welfare();
        tv14.setText("签到最高可得" + resultBean.getSign_height() + "波币");
        if (is_sign_task == 1) {
            btSign.setText("已签到");
        } else {
            btSign.setText("去签到");
        }

        if (UserConfig.getInstance().getRole().equals("2")) {
            switch (is_send_resume) {
                case 0:
                    btSendresume.setText("去完成");
                    break;
                case 1:
                    btSendresume.setText("领取");
                    break;
                case 2:
                    btSendresume.setText("已领取");
                    break;
            }
            tv15.setText("投递简历即可获得" + resultBean.getDeliver_resume() + "波币");
        } else {
            if (is_login_welfare == 1) {
                tv_is_login_welfare.setText("限时");
                if (is_today_gives == 1) {
                    btSendresume.setText("已领取");
                } else {
                    btSendresume.setText("领取");
                }
            } else {
                tv_is_login_welfare.setText("已结束");
                btSendresume.setText("已结束");
            }
            tv15.setText("活动期间每日登陆即可获得" + resultBean.getWelfare_gives() + "波豆");

        }

    }

    @Override
    public void resumeDraw() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.everydayMission();
    }

    /**
     * 登录好礼领取
     */
    @Override
    public void givesGet() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.everydayMission();
    }

    @Override
    public void applyPondition(InvitationCodeBean codeBean) {

    }

    /**
     * 签到图片以及横线全部还原
     */
    public void refushView() {
        imSign1.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bbh));
        imSign2.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bbh));
        imSign3.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jlh));
        imSign4.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bbh));
        imSign5.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jlh));
        imSign6.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_bbh));
        imSign7.setImageDrawable(this.getResources().getDrawable(R.mipmap.rwzx_jlh));
        tvSignLine1.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
        tvSignLine2.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
        tvSignLine3.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
        tvSignLine4.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
        tvSignLine5.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
        tvSignLine6.setBackgroundColor(this.getResources().getColor(R.color.text_color_787878));
    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(SignAndTaskActivity.this).inflate(R.layout.add_boss_info2, null);
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
        View rootview = LayoutInflater.from(SignAndTaskActivity.this).inflate(R.layout.activity_signandtask_layout, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

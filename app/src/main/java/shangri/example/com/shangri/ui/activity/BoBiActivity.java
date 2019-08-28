package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.InviteListDataBean;
import shangri.example.com.shangri.model.bean.response.PriceListBean;
import shangri.example.com.shangri.model.bean.response.SevenDayEarningsBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.UserBenefitsPresenter;
import shangri.example.com.shangri.presenter.view.UserBenefitsView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.DialogUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 会长波币界面
 */
public class BoBiActivity extends BaseActivity<UserBenefitsView, UserBenefitsPresenter> implements UserBenefitsView {


    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_bbrate)
    TextView tvBbrate;
    @BindView(R.id.tv_bb_number)
    TextView tvBbNumber;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;
    @BindView(R.id.tv_makemoney)
    TextView tvMakemoney;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R.id.tv_shoyi)
    TextView tvShoyi;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_tixian_rules)
    TextView tvTixianRules;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_user_benefits3;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_user_benefits3;
    }

    @Override
    protected UserBenefitsPresenter createPresenter() {
        return new UserBenefitsPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        if (UserConfig.getInstance().getRole().equals("2")) {
            tvMoney.setVisibility(View.VISIBLE);
            tvWithdraw.setText("立即提现");
            tvTixianRules.setText("查看提现规则");
            tvTixian.setVisibility(View.VISIBLE);
            rlInfo.setBackground(this.getResources().getDrawable(R.mipmap.sy_bg));
        } else {
            tvMoney.setVisibility(View.GONE);
            tvWithdraw.setText("兑换波豆");
            tvTixianRules.setText("兑换波豆功能即将上线");
            tvTixian.setVisibility(View.INVISIBLE);
            rlInfo.setBackground(this.getResources().getDrawable(R.mipmap.sy_bghz));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.getCredits(UserConfig.getInstance().getToken());
    }

    /**
     * 获取提现记录
     */
    private void inviteListRequest() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

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
    public void Success(AmountBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        bb_balance = resultBean.getBb_balance();
        bb_rate = resultBean.getBb_rate();
        tvBbNumber.setText(resultBean.getBb_balance() + "");
        tvBbrate.setText(resultBean.getBb_rate() + "波币=1元");
        //保留后面两位小数点
//        Double aDouble = PointUtils.KeepPoint1(Double.parseDouble(resultBean.getBb_balance() / resultBean.getBb_rate() + ""), 3);
        tvMoney.setText("可提现" + resultBean.getBb_balance() / resultBean.getBb_rate() + "元");
        String str1 = "累计收益：" + "<font color='#ffffff'>" + resultBean.getSum_bb() + "</font>" + "波币";
        tvShoyi.setTextSize(11);
        tvShoyi.setText(Html.fromHtml(str1));
        String str2 = "累计提现：" + "<font color='#ffffff'>" + resultBean.getGrand_total() + "</font>" + "元";
        tvTixian.setTextSize(11);
        tvTixian.setText(Html.fromHtml(str2));
    }

    @Override
    public void codeSuccess(InvitationCodeBean codeBean) {

    }

    @Override
    public void invitationMyInvite(InviteListDataBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void sevenDayEarnings(SevenDayEarningsBean codeBean) {
    }

    @Override
    public void userPriceList(PriceListBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void upgradeAlert(upgradeAlertBean codeBean) {

    }

    @Override
    public void receiveSuccess(Object codeBean) {

    }

    @Override
    public void applyPondition(InvitationCodeBean codeBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        need_base = codeBean.getNeed_base();
        need_cks = codeBean.getReg_register();
        need_zcs = codeBean.getCall_count();

//        类型值 0正常 1无简历 2历未审核 3简历审核失败 4波币不足 5邀请好友不足 6简历被看次数不足
        if (codeBean.getType().equals("7")){
            ToastUtil.showShort("您已经有一笔正在提现的金额审核中");
        }else {
            startActivity(new Intent(this, RedEnvelopeWelfareActivity.class)
                    .putExtra("bb_balance", bb_balance + "")
                    .putExtra("bb_rate", bb_rate + "")
                    .putExtra("need_base", need_base + "")
                    .putExtra("need_cks", need_cks + "")
                    .putExtra("need_zcs", need_zcs + ""));
        }


    }

    @OnClick({R.id.iv_go_back, R.id.tv_detail, R.id.tv_withdraw, R.id.tv_makemoney, R.id.tv_tixian_rules})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            //明细界面
            case R.id.tv_detail:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(this, DetailsActivity.class);
                }
                break;
            case R.id.tv_withdraw:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.applyPondition(UserConfig.getInstance().getToken());
                    } else {
                        ToastUtil.showShort("兑换波豆功能即将上线");
                    }
                }
                break;
            case R.id.tv_makemoney:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }
                break;
            case R.id.tv_tixian_rules:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                        DialogUtils.showDialog1(this, 1, need_base, need_zcs, need_cks);
                    }
                }
                break;
        }
    }
}

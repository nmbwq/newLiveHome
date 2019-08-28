package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.LawWorksPresenter;
import shangri.example.com.shangri.presenter.view.LowWorksView;
import shangri.example.com.shangri.ui.activity.CompactWebView.AddCompactlWebviewOne;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 法务首页面
 * Created by mschen on 2017/6/23.
 */

public class SignActivity extends BaseActivity<LowWorksView, LawWorksPresenter> implements LowWorksView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_finish_number)
    TextView tvFinishNumber;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.head_info)
    LinearLayout headInfo;
    @BindView(R.id.tv_anchor_renzheng_type)
    TextView tvAnchorRenzhengType;
    @BindView(R.id.rl_anchor_my_identification)
    RelativeLayout rlAnchorMyIdentification;
    @BindView(R.id.rl_anchor_qianzhang)
    TextView rlAnchorQianzhang;
    @BindView(R.id.ll_anchor)
    LinearLayout llAnchor;
    @BindView(R.id.rl_moban)
    RelativeLayout rlMoban;
    @BindView(R.id.tv_manager_renzheng_type)
    TextView tvManagerRenzhengType;
    @BindView(R.id.rl_company_identification)
    RelativeLayout rlCompanyIdentification;
    @BindView(R.id.rl_manager_qianzhang)
    RelativeLayout rlManagerQianzhang;
    @BindView(R.id.rl_manager_taocan)
    RelativeLayout rlManagerTaocan;
    @BindView(R.id.ll_manager)
    LinearLayout llManager;
    @BindView(R.id.im4)
    ImageView im4;
    @BindView(R.id.im8)
    ImageView im8;
    @BindView(R.id.tv_dianji)
    TextView tv_dianji;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    //url地址
    String url;

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected LawWorksPresenter createPresenter() {
        return new LawWorksPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        reload();
        if (UserConfig.getInstance().getRole().equals("1")) {
            llAnchor.setVisibility(View.GONE);
            llManager.setVisibility(View.VISIBLE);
            tvType.setText("待他人签");
        } else {
            llAnchor.setVisibility(View.VISIBLE);
            llManager.setVisibility(View.GONE);
            tvType.setText("待自己签");
        }

        if (UserConfig.getInstance().getIsFawuShow().equals("1")) {
            tv_dianji.setVisibility(View.GONE);
        } else {
            tv_dianji.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void requestFailed(String message) {
//        Loading.dismiss();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(SignActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.legalIndex();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(SignActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(SignActivity.this.getSupportFragmentManager());
            mPresenter.legalIndex();
        }
    }

    @OnClick({R.id.setting_back, R.id.tv_dianji, R.id.web, R.id.reload, R.id.user_login, R.id.ll_url, R.id.ll1, R.id.ll2, R.id.tv_more, R.id.rl_anchor_my_identification, R.id.rl_anchor_qianzhang, R.id.rl_moban, R.id.rl_company_identification, R.id.rl_manager_qianzhang, R.id.rl_manager_taocan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    reload();
                }
                break;
            case R.id.tv_dianji:
                if (PointUtils.isFastClick()) {
                    UserConfig.getInstance().pwdIsFawuShow("1");
                    tv_dianji.setVisibility(View.GONE);
                }
                break;
            case R.id.web:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.signProcess();
                }
                break;

            case R.id.setting_back:
                finish();
                break;
            case R.id.user_login:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, AddCompactlWebviewOne.class).putExtra("table_name", "legal_contract"));
                }
                break;
            case R.id.ll_url:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.legalProtocol();
                }
                break;
            case R.id.tv_more:
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "点击查看更多");
                    startActivity(new Intent(SignActivity.this, MyCompactActivity.class));
                }
                break;
            case R.id.rl_anchor_my_identification:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, IdentIdCardOneetail.class));
                }
                break;
            case R.id.rl_anchor_qianzhang:
                break;
            case R.id.rl_moban:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, CompactMobanListActivity.class));
                }
                break;
            //已完成跳转
            case R.id.ll1:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, MyCompactActivity.class).putExtra("poi", 2));
                }
                break;
            //待他人签或是自己签跳转
            case R.id.ll2:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, MyCompactActivity.class).putExtra("poi", 1));

                }
                break;
            case R.id.rl_company_identification:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, CompanyIdent.class));
                }
                Log.d("Debug", "公司认证点击事件");
                break;
            case R.id.rl_manager_qianzhang:
                break;
            case R.id.rl_manager_taocan:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(SignActivity.this, ComboListActivity.class));
                }
                break;
        }
    }

    @Override
    public void legalIndex(legalIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        tvFinishNumber.setText(resultBean.getComplete_count() + "");
        tvNumber.setText(resultBean.getWait_count() + "");

        if (UserConfig.getInstance().getRole().equals("1")) {
            if (resultBean.getIs_face() > 0 && resultBean.getIs_company() > 0) {
                tvManagerRenzhengType.setText("已认证");
                rlCompanyIdentification.setEnabled(false);
                im8.setVisibility(View.GONE);
            } else {
                tvManagerRenzhengType.setText("去认证");
                rlCompanyIdentification.setEnabled(true);
                im8.setVisibility(View.VISIBLE);
            }

        } else {
            if (resultBean.getIs_face() == 0) {
                tvAnchorRenzhengType.setText("去认证");
                rlAnchorMyIdentification.setEnabled(true);
                im4.setVisibility(View.VISIBLE);
            } else {
                tvAnchorRenzhengType.setText("已认证");
                im4.setVisibility(View.GONE);
                rlAnchorMyIdentification.setEnabled(false);
            }
        }

    }

    @Override
    public void legalProtocol(companyMyBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        String s = bean.getUrl() + "?token=" + UserConfig.getInstance().getToken() + "";
        startActivity(new Intent(SignActivity.this, symbolWebView.class).putExtra("url", s + ""));
    }

    @Override
    public void signProcess(companyMyBean bean) {
        String url = bean.getUrl();
        startActivity(new Intent(SignActivity.this, symbolWebView.class).putExtra("url", url + ""));
    }
}

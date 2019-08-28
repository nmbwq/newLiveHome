package shangri.example.com.shangri.ui.activity.CompactWebView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.activity.CompactDetail;
import shangri.example.com.shangri.ui.activity.IdentIdCardOneetail;
import shangri.example.com.shangri.ui.activity.MyCompactActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.CountDownTimerText;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 合同详情web界面
 */
public class CompactDetailWebview extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {


    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.rl_info)
    LinearLayout rl_info;
    @BindView(R.id.ll_do)
    LinearLayout ll_do;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    String url = "";
    //传递过来的实体类对象
    CompactListResponse.ContractsBean bean;

    private ProgressDialogFragment mProgressDialog;
    //    那个freagment传过来的
    int type;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.compact_detail_webview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.compact_detail_webview;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//        EventBus.getDefault().post(new RefushBean(type));
        type = getIntent().getIntExtra("type", 0);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());

        //先判断合同状态  未签章显示底部布局 marge bottom  在判断身份  会长只有撤销   主播有 拒签和立即签署
        bean = (CompactListResponse.ContractsBean) getIntent().getSerializableExtra("bean");
//        1已签署 2拒签 3待签 4已撤销 5已失效 6草稿
        if (bean.getStatus() == 3) {
            ll_do.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(rl_info.getLayoutParams());
            lp.setMargins(0, 120, 0, 100);
            rl_info.setLayoutParams(lp);

            if (UserConfig.getInstance().getRole().equals("1")) {
                tv1.setText("");
                tv2.setText("撤销");
            } else {
                tv1.setText("");
                tv2.setText("立即签署");
            }
        } else {
            ll_do.setVisibility(View.GONE);
        }
        mPresenter.legalContractBase(bean.getId());
//
    }


    private void init(String url) {
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm
                (WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);

        webView.getSettings().setBlockNetworkImage(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }


        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient() {//监听网页加载
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                try {
                    if (pb_progress == null) {
                        return;
                    }
                    if (newProgress == 100) {
                        // 网页加载完成
                        pb_progress.setVisibility(View.GONE);
                        if (mProgressDialog != null && mProgressDialog.isResumed()) {
                            mProgressDialog.dismiss();
                        }
                    } else {
                        // 加载中
                        pb_progress.setProgress(newProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        loadData(url);
    }

    private void loadData(String mUrl) {

        if (!NetWorkUtil.isNetworkConnected(this)) {
            webView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            webView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }
        if (mUrl == null) return;
        if (mUrl.endsWith(".jpg") || mUrl.endsWith(".png")) { //加载图片
            String data = "<HTML><Div align=\"center\"  " +
                    "margin=\"0px\"><IMG  width='100%' height='auto' src=\"" + mUrl + "\" margin=\"0px\"/></Div>";
            webView.loadDataWithBaseURL("", data, "text/html", "utf-8", null);
        } else {
            webView.loadUrl(mUrl); //加载一般网页
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }


    @OnClick({R.id.reload, R.id.setting_back, R.id.iv_add_guild, R.id.tv1, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                init(url);
                break;
            case R.id.setting_back:
                finish();
                break;
            //跳转到详情界面
            case R.id.iv_add_guild:
                Log.d("Debug", "跳转到详情界面" + bean.getId());
                startActivity(new Intent(CompactDetailWebview.this, CompactDetail.class).putExtra("id", bean.getId()));
                break;
            //主播进行拒签操作
            case R.id.tv1:
                break;
            //会长进行撤销操作  主播做立即签署操作
            case R.id.tv2:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    Log.d("Debug", "会长做撤销操作");
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.legalContractRepeal(bean.getId());
                } else {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.legalIsface();
                }
                break;
        }
    }


    private PopupWindow mPopWindow;

    /**
     * 主播拒签操作
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(CompactDetailWebview.this).inflate(R.layout.compact_comtent, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        ImageView im_delete = contentView.findViewById(R.id.im_delete);


        tv_content.setText("您还没有进行主播认证，签署合同需要进行主播认证呢");
        tv_next.setText("去认证");
        //显示PopupWindow
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主播认证界面
                mPopWindow.dismiss();
                startActivity(new Intent(CompactDetailWebview.this, IdentIdCardOneetail.class));
            }
        });

        View rootview = LayoutInflater.from(CompactDetailWebview.this).inflate(R.layout.compact_detail_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {

    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {
        Log.d("Debug", "主播做立即签署操作");
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getIs_face() > 0) {
            showPopupWindow1();
        } else {
            showPopupWindow();
        }
    }


    @Override
    public void legalTemplate(IsFaceResponse resultBean) {

    }

    @Override
    public void legalContract_signature(ChairmanSignResponse resultBean) {

    }

    @Override
    public void legalContractVerify() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void legalanchorVerify() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void ontractVerifyCode() {

        mPresenter.legalAnchor_signature(bean.getId());
    }

    @Override
    public void legalTxcontractPush() {

    }

    @Override
    public void legalAnchor_signature(ChairmanSignResponse resultBean) {
        Log.d("Debug", "到达签署成功的这里");
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPopWindow1.dismiss();
        if (resultBean.getOk() == 1) {
            ToastUtil.showShort("签署成功");
            ActivityManager.getInstance().finishActivity(MyCompactActivity.class);
            startActivity(new Intent(CompactDetailWebview.this, MyCompactActivity.class).putExtra("poi", 2));
            ActivityManager.getInstance().finishActivity(CompactDetailWebview.class);
        } else {
            ToastUtil.showShort("会长合同已撤销，签署失败");
        }

    }


    @Override
    public void legalTxcontract() {

    }

    @Override
    public void legalContractRepeal(ChairmanSignResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getOk() == 1) {
            ToastUtil.showShort("撤销成功");
            ActivityManager.getInstance().finishActivity(MyCompactActivity.class);
            startActivity(new Intent(CompactDetailWebview.this, MyCompactActivity.class).putExtra("poi", type));
            finish();
        } else {
            ToastUtil.showShort("主播已签署合同，撤销失败");
        }

    }


    @Override
    public void legalContractBase(pdfResponse resultBean) {
        url = resultBean.getContract().getPdf_url();
        if (url != null && url.length() > 0) {
            init(url);
        }
    }

    @Override
    public void signNumber(pdfResponse resultBean) {
    }


    private PopupWindow mPopWindow1;

    /**
     * 选择公会弹窗
     */
    private void showPopupWindow1() {
        //设置contentView
        View contentView = LayoutInflater.from(CompactDetailWebview.this).inflate(R.layout.compact_next, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_close = contentView.findViewById(R.id.tv_close);
        final TextView tv_getcode = contentView.findViewById(R.id.tv_getcode);
        final TextView tv_text = contentView.findViewById(R.id.tv_text);
        Log.d("Debug", "返回的电话信息" + UserConfig.getInstance().getMobile());
        Log.d("Debug", "返回的电话信息" + UserConfig.getInstance().getMobile().substring(0, 3));
        Log.d("Debug", "返回的电话信息" + UserConfig.getInstance().getMobile().substring(7, 11));
        tv_text.setText("验证码已发送至" + UserConfig.getInstance().getMobile().substring(0, 3) + "****" + UserConfig.getInstance().getMobile().substring(7, 11));
        final EditText et_code = contentView.findViewById(R.id.et_code);
        final TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        //获取验证码操作
        mPresenter.legalanchorVerify(bean.getId());
        CountDownTimerText mCountDownTimer = new CountDownTimerText(tv_getcode, "%ds", getResources().getString(R.string.get_code),
                1000 * 60, new CountDownTimerText.CountDownFinishListener() {
            @Override
            public void finish() {
                tv_getcode.setText("获取验证码");
            }
        });
        mCountDownTimer.start();

        //验证验证码
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                if (et_code.getText().length() == 0) {
                    ToastUtil.showShort("请填写验证码");
                    return;
                }
                mProgressDialog.show(CompactDetailWebview.this.getSupportFragmentManager());
                mPresenter.ontractVerifyCode(bean.getId(), et_code.getText().toString());
            }
        });

        tv_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(CompactDetailWebview.this.getSupportFragmentManager());
                mPresenter.legalanchorVerify(bean.getId());
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(CompactDetailWebview.this).inflate(R.layout.compact_detail_webview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}

package shangri.example.com.shangri.ui.activity.CompactWebView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.DateRefush;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.WebDates;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.activity.CompactMobanListActivity;
import shangri.example.com.shangri.ui.activity.MyCompactActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow1;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.CountDownTimerText;
import shangri.example.com.shangri.util.LoadingDialog;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 合同详情web界面
 */
public class CompactSignWebview extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {


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
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.rl_qianzhang)
    RelativeLayout rl_qianzhang;


    String url = "";
    @BindView(R.id.ll_time)
    LinearLayout llTime;

    //传递过来的数据
    WebDates bean;
    private ProgressDialogFragment mProgressDialog;
    //合同id
    int id = 0;
    // 0是没有
    int pdfnumber = 0;

    LoadingDialog dialog;
    //传递给web端的string类型
    String jsonString;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.compact_sign_webview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.compact_sign_webview;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    CompactListResponse.ContractsBean contractsBean;
    boolean isFromOther = false;

    @Override
    protected void initViewsAndEvents() {
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        if (UserConfig.getInstance().getRole().equals("1")) {
            rl_qianzhang.setVisibility(View.VISIBLE);
            tv2.setText("下一步");
        } else {
            rl_qianzhang.setVisibility(View.GONE);
            tv2.setText("完成签署");
        }
        isFromOther = getIntent().getBooleanExtra("IsFromOther", false);
        if (isFromOther) {
            //先判断合同状态  未签章显示底部布局 marge bottom  在判断身份  会长只有撤销   主播有 拒签和立即签署
            contractsBean = (CompactListResponse.ContractsBean) getIntent().getSerializableExtra("bean");
            //没有生成pdf  进行详情界面请求 生成pdf进行pdf请求
            id = contractsBean.getId();
            Log.d("Debug", "合同的id是" + id);
            pdfnumber = contractsBean.getIs_pdf();
            if (contractsBean.getIs_pdf() == 0) {
                mPresenter.legalTemplate(contractsBean.getTable_name(), 2);
            } else {
                mPresenter.legalContractBase(contractsBean.getId());
            }
        } else {
            bean = (WebDates) getIntent().getSerializableExtra("bean");
            String webString = getIntent().getStringExtra("webString");
            Log.d("Debug", "返回的劲松数据为" + webString);
            jsonString = webString;
            mPresenter.legalTemplate(bean.getTable_name(), 2);
        }
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
                        Log.d("Debug", "当前的数据为" + jsonString);
                        webView.loadUrl("javascript:ReceiveAndroidMessage('" + jsonString + "')");
                        if (pdfnumber > 0) {
                            if (dialog != null) {
                                dialog.close();
                            }
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


    @OnClick({R.id.reload, R.id.setting_back, R.id.tv2, R.id.ll_time, R.id.ll_qianzhang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                init(url);
                break;
            case R.id.setting_back:
                if (isFromOther) {
                    EventBus.getDefault().post(new DateRefush());
                    finish();
                } else {
                    if (pdfnumber > 0) {
                        ActivityManager.getInstance().finishActivity(AddCompactlWebviewOne.class);
                        ActivityManager.getInstance().finishActivity(AddCompactTwo.class);
                        ActivityManager.getInstance().finishActivity(CompactMobanListActivity.class);
                        startActivity(new Intent(CompactSignWebview.this, MyCompactActivity.class).putExtra("poi", 3));
                        EventBus.getDefault().post(new DateRefush());
                        finish();
                    } else {
                        showPop();
                    }
                }
                break;
            //会长进行撤销操作  助农做立即签署操作
            case R.id.tv2:
                if (pdfnumber > 0) {
                    showPopupWindow();
                } else {
                    ToastUtil.showShort("请先完成签章功能");
                }
                break;
            case R.id.ll_time:
                ToastUtil.showShort("选择日历操作");
                break;
            case R.id.ll_qianzhang:
                if (pdfnumber > 0) {
                    ToastUtil.showShort("您已完成签章，点击下一步完成签署");
                } else {
                    if (isFromOther) {
                    } else {
                        bean.setQ_time(TimeUtil.convertTimeToLong1(bean.getQ_time()) / 1000 + "");
                        bean.setToken(UserConfig.getInstance().getToken());
                        bean.h5_str = jsonString;
                    }
                    mPresenter.legalContract_signature(bean);
                    dialog = new LoadingDialog(CompactSignWebview.this, "签章中，请稍后...");
                    dialog.show();
                }
                break;
        }
    }

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        return gson.toJson(map);

    }


    private PopupWindow mPopWindow;

    /**
     * 选择公会弹窗
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(CompactSignWebview.this).inflate(R.layout.compact_next, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
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
        mPresenter.legalContractVerify(id);
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
                mProgressDialog.show(CompactSignWebview.this.getSupportFragmentManager());
                mPresenter.ontractVerifyCode(id, et_code.getText().toString());
            }
        });
        tv_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(CompactSignWebview.this.getSupportFragmentManager());
                mPresenter.legalContractVerify(id);
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(CompactSignWebview.this).inflate(R.layout.compact_detail_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 返回保存到草稿箱操作
     */

    public void showPop() {
        SelectPhotoPopopWindow1 ImagePop = new SelectPhotoPopopWindow1(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                finish();
            }

            @Override
            public void takePhoto() {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(CompactSignWebview.this.getSupportFragmentManager());
                bean.setQ_time(TimeUtil.convertTimeToLong1(bean.getQ_time()) / 1000 + "");
                bean.setToken(UserConfig.getInstance().getToken());
                bean.h5_str = jsonString;
                Log.d("Debug", "bean.setQ_time" + bean.q_time);
                Log.d("Debug", "bean.setQ_time" + bean.y_push);
                mPresenter.legalTxcontract(bean);
            }

            @Override
            public void savePhoto() {
            }
        }, "取消");
    }


    //返回键进行判断
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isFromOther) {
                finish();
            } else {
                if (pdfnumber > 0) {
                    ActivityManager.getInstance().finishActivity(AddCompactlWebviewOne.class);
                    ActivityManager.getInstance().finishActivity(AddCompactTwo.class);
                    ActivityManager.getInstance().finishActivity(CompactMobanListActivity.class);
                    startActivity(new Intent(CompactSignWebview.this, MyCompactActivity.class).putExtra("poi", 3));
                    EventBus.getDefault().post(new DateRefush());
                    finish();
                } else {
                    showPop();
                }

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    CompactDetailResponse DetailResponse;

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        DetailResponse = resultBean;

        bean = new WebDates();
        bean.id=id;
        bean.note = resultBean.getContract().getNote();
        bean.j_name = resultBean.getContract().getJ_name();
        bean.j_address = resultBean.getContract().getJ_address();
        bean.j_email = resultBean.getContract().getJ_code();
        bean.j_telephone = resultBean.getContract().getJ_telephone();
        bean.y_name = resultBean.getContract().getY_name();
        bean.y_telephone = resultBean.getContract().getY_telephone();
        bean.y_idcard = resultBean.getContract().getY_idcard();
        bean.y_address = resultBean.getContract().getY_address();
        bean.y_code = resultBean.getContract().getY_code();
        bean.validity = resultBean.getContract().getValidity() + "";
        bean.rmb = resultBean.getContract().getRmb();
        bean.number = resultBean.getContract().getNumber() + "";
        jsonString = resultBean.getContract().getH5_str();
        bean.title = DetailResponse.getContract().getTitle();
        bean.end_time = (TimeUtil.convertTimeToLong1(resultBean.getContract().getEnd_date()) / 1000 + "");
        bean.j_assign_addr = DetailResponse.getContract().getJ_assign_addr();
        bean.h5_str = jsonString;
        bean.setToken(UserConfig.getInstance().getToken());
        bean.y_push = resultBean.getContract().getY_push();
        bean.q_time = (TimeUtil.convertTimeToLong1(resultBean.getContract().getQ_date()) / 1000 + "");
        if (url != null && url.length() > 0) {
            init(url);
            Log.d("Debug", "返回的地址是" + url);
        }
    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {

    }

    @Override
    public void legalTemplate(IsFaceResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
//        url = "http://www.zhibohome.net/test/dierci_hetong.html";
        url = resultBean.getUrl() + "?token=" + UserConfig.getInstance().getToken() + "";
        //在列表界面过来  先不进行加载页面 请求完数据在进行加载界面
        Log.d("Debug", "返回的bollen" + isFromOther);
        if (isFromOther) {
            mPresenter.legalDetail(contractsBean.getId());
        } else {
            if (url != null && url.length() > 0) {
                init(url);
                Log.d("Debug", "返回的地址是" + url);
            }
        }

    }

    @Override
    public void legalContract_signature(ChairmanSignResponse resultBean) {
        pdfnumber = resultBean.getId();
        id = resultBean.getId();
        url = resultBean.getContract_url();
        if (url != null && url.length() > 0) {
            init(url);
            Log.d("Debug", "返回的地址是" + url);
        }
    }

    /**
     * 合同验证码获取
     */
    @Override
    public void legalContractVerify() {
        Log.d("Debug", "获取验证码成功");
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void legalanchorVerify() {

    }

    /**
     * 验证验证码
     */

    @Override
    public void ontractVerifyCode() {
        mPresenter.legalTxcontractPush(id);
    }

    /**
     * 完成签署操作
     */
    @Override
    public void legalTxcontractPush() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPopWindow.dismiss();
        ToastUtil.showShort("签署成功");
        ActivityManager.getInstance().finishActivity(AddCompactlWebviewOne.class);
        ActivityManager.getInstance().finishActivity(AddCompactTwo.class);
        ActivityManager.getInstance().finishActivity(CompactMobanListActivity.class);
        startActivity(new Intent(CompactSignWebview.this, MyCompactActivity.class).putExtra("poi", 1));
        EventBus.getDefault().post(new DateRefush());
        finish();
    }

    @Override
    public void legalAnchor_signature(ChairmanSignResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPopWindow.dismiss();
    }



    @Override
    public void legalTxcontract() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        ActivityManager.getInstance().finishActivity(AddCompactlWebviewOne.class);
        ActivityManager.getInstance().finishActivity(AddCompactTwo.class);
        ActivityManager.getInstance().finishActivity(CompactMobanListActivity.class);
        startActivity(new Intent(CompactSignWebview.this, MyCompactActivity.class).putExtra("poi", 3));
        EventBus.getDefault().post(new DateRefush());
        finish();
    }

    @Override
    public void legalContractRepeal(ChairmanSignResponse resultBean) {

    }

    @Override
    public void legalContractBase(pdfResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        url = resultBean.getContract().getPdf_url();
        if (url != null && url.length() > 0) {
            init(url);
            Log.d("Debug", "返回的地址是" + url);
        }
    }

    @Override
    public void signNumber(pdfResponse resultBean) {

    }

}

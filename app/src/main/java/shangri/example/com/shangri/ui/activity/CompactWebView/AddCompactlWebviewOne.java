package shangri.example.com.shangri.ui.activity.CompactWebView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;

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
import shangri.example.com.shangri.model.bean.response.WebDates;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.activity.BusinesIdentsetail;
import shangri.example.com.shangri.ui.activity.IdentIdCardOneetail;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.JsonUitl;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 填写合同web界面
 */
public class AddCompactlWebviewOne extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {


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
    private ProgressDialogFragment mProgressDialog;

    public boolean isSecond = false;
    //模板名称
    String table_name;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.compact_detail_webview1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.compact_detail_webview1;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
//        table_name
        table_name = getIntent().getStringExtra("table_name");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.legalTemplate(table_name, 1);
        mPresenter.legalIsface();

    }


    @Override
    protected void onResume() {

        super.onResume();
        isSecond = false;
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
        webView.addJavascriptInterface(new JsInteration(), "android");
        loadData(url);
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

    /**
     * 判断是否认证
     *
     * @param resultBean
     */

    @SuppressWarnings("JavaDoc")
    @Override
    public void legalIsface(IsFaceResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getIs_face() == 0) {
            showPopupWindow(2);
        } else {
            if (resultBean.getIs_company() == 0) {
                showPopupWindow(3);
            } else {
                webView.loadUrl("javascript:SendAndroidMessage()");
            }
        }


    }

    /**
     * 获取连接地址
     *
     * @param resultBean
     */
    @SuppressWarnings("JavaDoc")
    @Override
    public void legalTemplate(IsFaceResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        url = resultBean.getUrl() + "?token=" + UserConfig.getInstance().getToken() + "";
//        url=" http://www.zhibohome.net/test/hetong.html";
//        url = "http://www.zhibohome.net/test/hetong.html";
        if (url != null && url.length() > 0) {
            init(url);
        }

    }

    @Override
    public void legalContract_signature(ChairmanSignResponse resultBean) {

    }

    @Override
    public void legalContractVerify() {

    }

    @Override
    public void legalanchorVerify() {

    }

    @Override
    public void ontractVerifyCode() {

    }

    @Override
    public void legalTxcontractPush() {

    }

    @Override
    public void legalAnchor_signature(ChairmanSignResponse resultBean) {

    }



    @Override
    public void legalTxcontract() {

    }

    @Override
    public void legalContractRepeal(ChairmanSignResponse resultBean) {

    }



    @Override
    public void legalContractBase(pdfResponse resultBean) {

    }

    @Override
    public void signNumber(pdfResponse resultBean) {

    }

    /**
     * 声明交互接口
     */
    public class JsInteration {
        @JavascriptInterface
        public void android_getInfo(String message) {
            Log.d("Debug", "到达这里返回的数据是" + message);
            WebDates bean = (WebDates) JsonUitl.stringToObject(message, WebDates.class);
            bean.table_name = table_name;
            startActivity(new Intent(AddCompactlWebviewOne.this, AddCompactTwo.class).putExtra("bean", bean).putExtra("WebString",message));
        }

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
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.reload, R.id.setting_back, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                init(url);
                break;
            case R.id.setting_back:
                finish();
                break;
            //点击跳转下一步
            case R.id.tv2:
//                WebDates date = new WebDates("爱上飞机来说房价");
//                String s = JsonUitl.objectToString(date);
//                webView.loadUrl("javascript:ReceiveAndroidMessage('" + s + "')");

                isSecond = true;
                mPresenter.legalIsface();
                break;
        }
    }

    private PopupWindow mPopWindow;

    /**
     * 主播拒签操作
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(AddCompactlWebviewOne.this).inflate(R.layout.compact_comtent, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        ImageView im_delete = contentView.findViewById(R.id.im_delete);
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
                if (type==1){
                    startActivity(new Intent(AddCompactlWebviewOne.this, IdentIdCardOneetail.class));
                }else if (type==2){
                    startActivity(new Intent(AddCompactlWebviewOne.this, IdentIdCardOneetail.class));
                }else {
                    startActivity(new Intent(AddCompactlWebviewOne.this, BusinesIdentsetail.class));
                }
            }
        });
        //1 主播实名认证  2 会长实名认证 3会长公司认证
        if (type == 1) {
            tv_content.setText("您还没有进行实名认证，签署合同需要进行实名认证呢");
            tv_next.setText("去认证");
        } else if (type == 2) {
            tv_content.setText("您还没有进行实名认证，签署合同需要进行实名认证呢");
            tv_next.setText("去认证");
        } else {
            tv_content.setText("您还没有进行企业认证，签署合同需要进行企业认证呢");
            tv_next.setText("去认证");
        }
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddCompactlWebviewOne.this).inflate(R.layout.compact_detail_webview1, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

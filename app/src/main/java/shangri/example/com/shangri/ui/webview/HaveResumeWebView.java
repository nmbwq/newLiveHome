package shangri.example.com.shangri.ui.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.presenter.view.CultivatLikePresenter;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.HaveInviteActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class HaveResumeWebView extends BaseActivity<CultivateLikeView, CultivatLikePresenter> implements CultivateLikeView {


    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.webView)
    android.webkit.WebView webView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.webview_back)
    ImageView webviewBack;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    String url = "";
    String resume_id = "";

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.webview7;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.webview7;
    }

    @Override
    protected CultivatLikePresenter createPresenter() {
        return new CultivatLikePresenter(this, this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "到达onre素么中");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(HaveResumeWebView.this.getSupportFragmentManager());
        mPresenter.resumeDetail(resume_id);
        init(url);
    }

    @Override
    protected void initViewsAndEvents() {
        url = getIntent().getStringExtra("url");
        Log.d("Debug", "到达这里initViewsAndEvents");
        resume_id = getIntent().getStringExtra("resume_id");
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
            public void onProgressChanged(android.webkit.WebView view, int newProgress) {
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

        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                if (url.toString().contains("sina.cn")) {
                    view.loadUrl("http://ask.csdn.net/questions/178242");
                    return true;
                }

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")) {
                        view.loadUrl("http://ask.csdn.net/questions/178242");
                        return true;
                    }
                }

                return false;
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

    @Override
    public void praise() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void consultShare() {

    }

    @Override
    public void jobStatus() {

    }

    @Override
    public void comment() {

    }

    @Override
    public void upAnchor() {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void commentNum(BannerHomeLookBean resultBean) {

    }

    ResumeIndexBean.ResumeBean resume;

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (url != null && url.length() > 0) {
            init(url);
        }
        resume = resultBean.getResume();
    }

    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @OnClick({R.id.reload, R.id.webview_back, R.id.im_jubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                init(url);
                break;
            case R.id.webview_back:
                finish();
                break;
            case R.id.im_jubao:
                Intent intent = new Intent(HaveResumeWebView.this, AddInviteActivity.class);
                intent.putExtra("bean", resume);
                intent.putExtra("isFromUpdate", true);
                startActivity(intent);
                break;
        }
    }
}

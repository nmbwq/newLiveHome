package shangri.example.com.shangri.base;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.ui.view.TitleBar;

import java.io.File;

import butterknife.BindView;

import static android.content.ContentValues.TAG;

/**
 * WebView基类
 * Created by chengaofu on 2017/6/20.
 */

public abstract class BaseWebActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.pb_title)
    ProgressBar mProgressBar;
    @BindView(R.id.wb_content)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWebView();
        super.onCreate(savedInstanceState);

    }

    public void initWebView() {

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        // 开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        /**********************************/
        // 支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
//		webSettings.setSupportZoom(true);
        // 设置出现缩放工具
//		webSettings.setBuiltInZoomControls(true);

        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(true);
        // 扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        // 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        // final String mimeType = "text/html";
        // final String encoding = "utf-8";
        //mWebView.loadDataWithBaseURL("file:///sdcard/", webData, mimeType, encoding, "");
        mWebView.requestFocus(View.FOCUS_DOWN);

        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

    }


    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

            mWebView.goBack();
            //finish();
            return true;
        } else if (KeyEvent.KEYCODE_HOME == keyCode) {
            //写要执行的动作或者任务
            //android.os.Process.killProcess(android.os.Process.myPid());
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    protected void loadUrl(String webUrl) {
        if (!TextUtils.isEmpty(webUrl)) {

            mWebView.loadUrl(Uri.parse(webUrl).toString());
            mWebView.clearHistory();
            mWebView.setWebViewClient(webViewClient);

            WebChromeClient wcc = new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress == 100) {
                        mProgressBar.setProgress(newProgress);
                        mProgressBar.setVisibility(View.GONE);
                    } else {
                        mProgressBar.setVisibility(View.VISIBLE);
                        mProgressBar.setProgress(newProgress);
                    }
                }

                @Override
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg,
                                                 WebChromeClient.FileChooserParams fileChooserParams) {
//                    openFileChooserImplForAndroid5(uploadMsg);
                    return true;
                }
            };
            // 设置setWebChromeClient对象
            mWebView.setWebChromeClient(wcc);
            //mWebView.addJavascriptInterface();
        }
    }

    protected WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;
        }

        // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       android.net.http.SslError error) {
            handler.proceed();
        }

        // 重写此方法可以让webview处理https请求。
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        // 重写此方法才能够处理在浏览器中的按键事件。
        @Override
        public void onLoadResource(WebView view, String url) {

            super.onLoadResource(view, url);

        }

        // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        // 在页面加载开始时调用。
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG, "getIntentData: " + view.getTitle());
            String title = view.getTitle();
            mTitleBar.setTitle(title);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        clearWebViewCache();

    }

    private void clearWebViewCache() {
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //symbolWebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + "/webcache");

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");

        //删除webview 缓存目录
        if (webviewCacheDir != null && webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir != null && appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    private void deleteFile(File file) {
        if (file == null) return;
        //如果文件存在
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_web;
    }
}

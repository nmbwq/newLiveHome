package shangri.example.com.shangri.ui.webview;

import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.ShareJubao;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.dialog.ShareCommontDialog;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class EncyclopediaActivityWebView extends BaseActivity<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.tv_title_patol)
    TextView mTitleText;
    @BindView(R.id.right_image)
    TextView right_image;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;


    private String mUrl;
    //判断在哪个界面传过来的 平台1 公会2 主播 3
    int type = -1;
    String url = "";
    String id = "";
    String title = "";
    //1 关注 0未关注
    int is_collect = -1;
    String imageurl = "";
    //是否来自轮播图
    boolean isfrom_banaer = false;
    private ProgressDialogFragment mProgressDialog;


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_new_webview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_new_webview;
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url") + "";
        imageurl = intent.getStringExtra("imageurl") + "";
        id = intent.getStringExtra("id") + "";
        title = intent.getStringExtra("title") + "";
        type = intent.getIntExtra("type", -1);
        is_collect = intent.getIntExtra("is_collect", -1);
        isfrom_banaer = intent.getBooleanExtra("isfrom_banaer", false);
        Log.d("Debug", "返回的地址为" + url);
        initView();
        init(url);
    }

    /**
     * 界面布局
     *
     * @param
     */
    private void initView() {
        if (title.length()>10){
            String substring = title.substring(0, 10);
            mTitleText.setText(substring+"...");
        }else {
            mTitleText.setText(title);
        }

        if (is_collect == 1) {
            right_image.setText("已关注");
        } else {
            right_image.setText("关注");
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
        mUrl = url;
        loadData();

    }

    private void loadData() {

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


    @OnClick({R.id.left_image, R.id.reload, R.id.right_image, R.id.right_image1})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_image1:
                if (PointUtils.isFastClick()) {
                    if (isfrom_banaer) {
                        showDialog();
                    } else {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        switch (type) {
                            case 1:
                                mPresenter.consultShare(id, "platform");
                                break;
                            case 2:
                                mPresenter.consultShare(id, "guild");
                                break;
                            case 3:
                                mPresenter.consultShare(id, "anchor");
                                break;
                            default:
                                mPresenter.consultShare(id, "anchor");
                                break;
                        }

                    }
                }
                break;
            case R.id.left_image:
                finish();
                break;
            case R.id.right_image:
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    Log.d("Debug", "返回的状态为" + is_collect);
                    switch (is_collect) {
                        case 0:
                            mPresenter.wikidocollect(type, id);
                            break;
                        case 1:
                            mPresenter.wikiCancelcollect(type, id);
                            break;
                    }
                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    loadData();
                }
                break;
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
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {

    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {
        mProgressDialog.dismiss();
        right_image.setText("已关注");
        ToastUtil.showShort("关注成功");
        is_collect = 1;
    }

    @Override
    public void wikiCancelCollect() {
        mProgressDialog.dismiss();
        right_image.setText("关注");
        ToastUtil.showShort("取消关注成功");
        is_collect = 0;
    }

    @Override
    public void messageRead() {

    }

    /**
     * 分享计数的
     */
    @Override
    public void consultShare() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        showDialog();
    }

    private static PopupWindow mPopWindow;

    /**
     * 分享举报
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(EncyclopediaActivityWebView.this).inflate(R.layout.compact_comtent_share, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(EncyclopediaActivityWebView.this).inflate(R.layout.activity_new_webview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 分享点击举报
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ShareJubao event) {
        Log.d("Debug", "到达分享点击举报");
        //1 webview传过来的 2webview1传过来的 3 百科
        if (event.getType() == 3) {
            showPopupWindow();
        }
    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        alertDialog = ShareCommontDialog.ShareDialog(EncyclopediaActivityWebView.this, url, 3, imageurl, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }
}

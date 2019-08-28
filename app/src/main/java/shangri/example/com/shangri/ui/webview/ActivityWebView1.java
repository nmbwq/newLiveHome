package shangri.example.com.shangri.ui.webview;

import android.annotation.SuppressLint;
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
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.ShareJubao;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.presenter.view.CultivatLikePresenter;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.dialog.ShareCommontDialog;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class ActivityWebView1 extends BaseActivity<CultivateLikeView, CultivatLikePresenter> implements CultivateLikeView {


    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.webview_back)
    ImageView mWebViewBack;
    //    @BindView(R.id.webview_title)
//    TextView mTitleText;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.tv_read_number)
    TextView tvReadNumber;
    @BindView(R.id.im_collect_image)
    ImageView imCollectImage;
    @BindView(R.id.tv_collect_number)
    TextView tvCollectNumber;
    @BindView(R.id.im_like_image)
    ImageView imLikeImage;
    @BindView(R.id.tv_like_number)
    TextView tvLikeNumber;
    @BindView(R.id.rl_hiden)
    LinearLayout rl_hiden;
    private String mUrl;
    //传过来参数
    String title = "";
    String url = "";
    String url1 = "";
    String imageurl = "";
    String id = "";
    String browse_amount = "";
    String good_amount = "";
    String collect_amount = "";
    int register_good = 0;
    int register_collect = 0;
    boolean isfrom_banaer = false;
    //分享的区别
    String isFormType = "article";
    private String type;  // 1 咨询 2 今日头条
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_webview1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_webview1;
    }

    @Override
    protected CultivatLikePresenter createPresenter() {
        return new CultivatLikePresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        Intent intent = getIntent();

        imageurl = intent.getStringExtra("imageurl") + "";
        title = intent.getStringExtra("title") + "";
//        url = intent.getStringExtra("url") + "";
        id = intent.getStringExtra("id") + "";
        url = intent.getStringExtra("url") + "?token=" + UserConfig.getInstance().getToken() + "&article_id=" + id + "&show=true";
        url1 = intent.getStringExtra("url") + "?token=" + UserConfig.getInstance().getToken() + "&article_id=" + id;
        browse_amount = intent.getStringExtra("browse_amount") + "";
        good_amount = intent.getStringExtra("good_amount") + "";
        collect_amount = intent.getStringExtra("collect_amount") + "";
        register_good = intent.getIntExtra("register_good", 0);
        register_collect = intent.getIntExtra("register_collect", 0);
        isfrom_banaer = intent.getBooleanExtra("isfrom_banaer", false);
        isFormType = intent.getStringExtra("isFormType") + "";
        type = getIntent().getStringExtra("type");
        initView();
        init(url);
    }

    /**
     * 界面布局
     *
     * @param
     */
    private void initView() {
        tvReadNumber.setText(browse_amount + "");
        tvCollectNumber.setText(collect_amount + "");
        tvLikeNumber.setText(good_amount + "");
        if (register_good == 0) {
            imLikeImage.setImageDrawable(getResources().getDrawable(R.mipmap.icon_good));
        } else {
            imLikeImage.setImageDrawable(getResources().getDrawable(R.mipmap.icon_good4));
        }
        if (register_collect == 0) {
            imCollectImage.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang2));
        } else {
            imCollectImage.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang1));
        }
        if (isfrom_banaer) {
            rl_hiden.setVisibility(View.INVISIBLE);
        } else {
            rl_hiden.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
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

    @OnClick({R.id.webview_back, R.id.reload, R.id.rl_collect, R.id.ll_like, R.id.webview_share})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.webview_back:
                if (PointUtils.isFastClick()) {
                    finish();
                }
                break;
            case R.id.webview_share:
                if (PointUtils.isFastClick()) {
                    if (isfrom_banaer) {
                        showDialog();
                    } else {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.consultShare(id, isFormType);
                    }
                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    loadData();
                }
                break;
            case R.id.rl_collect:
                if (PointUtils.isFastClick()) {
                    if (type.equals("1")) {
                        if (register_collect == 0) {
                            //点赞操作
                            Log.d("Debug", "到达这里的");
                            mPresenter.collect(id);
                        } else {
                            mPresenter.deleteCollect(id);
                        }
                    } else if (type.equals("2")) {
                        if (register_collect == 0) {
                            //点赞操作
                            mPresenter.requestCollect(id);

                        } else {
                            mPresenter.requestCancelCollect(id);
                        }
                    }
                }
                break;
            case R.id.ll_like:
                if (PointUtils.isFastClick()) {
                    if (type.equals("1")) {
                        if (register_good == 0) {
                            //点赞操作
                            mPresenter.createPraise(id);


                        } else {
                            mPresenter.deletePraise(id);
                        }
                    } else if (type.equals("2")) {
                        if (register_good == 0) {
                            //点赞操作
                            mPresenter.requestPraise(id);

                        } else {
                            mPresenter.requestCancel(id);
                        }
                    }
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

    /**
     * 分享点击举报
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ShareJubao event) {
        Log.d("Debug", "到达分享点击举报");
        //1 有评论 2没有评论 3 百科
        if (event.getType() == 2) {
            showPopupWindow();
        }
    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        alertDialog = ShareCommontDialog.ShareDialog(ActivityWebView1.this, url1, 2, imageurl, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }


    @Override
    public void praise() {
        if (register_good == 0) {
            imLikeImage.setImageDrawable(getResources().getDrawable(R.mipmap.icon_good4));
            tvLikeNumber.setText((Integer.parseInt(tvLikeNumber.getText().toString()) + 1) + "");
            register_good = 1;
        } else {
            imLikeImage.setImageDrawable(getResources().getDrawable(R.mipmap.icon_good));
            tvLikeNumber.setText((Integer.parseInt(tvLikeNumber.getText().toString()) - 1) + "");
            register_good = 0;
        }
    }

    @Override
    public void collect() {
        if (register_collect == 0) {
            imCollectImage.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang1));
            tvCollectNumber.setText((Integer.parseInt(tvCollectNumber.getText().toString()) + 1) + "");
            register_collect = 1;
        } else {
            imCollectImage.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang2));
            tvCollectNumber.setText((Integer.parseInt(tvCollectNumber.getText().toString()) - 1) + "");
            register_collect = 0;
        }
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

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {

    }

    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    private static PopupWindow mPopWindow;

    /**
     * 分享举报
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ActivityWebView1.this).inflate(R.layout.compact_comtent_share, null);
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
        View rootview = LayoutInflater.from(ActivityWebView1.this).inflate(R.layout.activity_webview1, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}

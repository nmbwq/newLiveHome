//<editor-fold desc="Description">
package shangri.example.com.shangri.ui.webview;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.eventmessage.NewsEvent;
import shangri.example.com.shangri.model.bean.event.ShareJubao;
import shangri.example.com.shangri.model.bean.request.WebInfo;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.presenter.view.CultivatLikePresenter;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.dialog.ShareCommontDialog;
import shangri.example.com.shangri.util.JsonUitl;
import shangri.example.com.shangri.util.KeyMapDailog;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class    ActivityWebView extends BaseActivity<CultivateLikeView, CultivatLikePresenter> implements CultivateLikeView {
    @BindView(R.id.webView)
    BridgeWebView webView;
    @BindView(R.id.webview_back)
    ImageView mWebViewBack;
    //    @BindView(R.id.webview_title)
//    TextView mTitleText;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;

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
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    //传过来参数
    String title = "";
    String imageurl = "";
    String url = "";
    String url1 = "";
    String id = "";
    String browse_amount = "";
    String good_amount = "";
    String collect_amount = "";
    int register_good = 0;
    int register_collect = 0;
    boolean isfrom_banaer = false;
    //分享的区别
    String isFormType = "article";
    private String type;  // 1 咨询 2 一周咨询    3 公会解读
    //点击item的下标  收藏成功 头条列表界面局部刷新用
    int position;
    //true 为 添加评论  false为回复操作
    boolean isBoolHuifu;

    //    回复的评论ID
    String reply_to_id = "";
    //    内容
    String comment = "";
    //    被回复人昵称
    String to_nickname = "";
    //传给web的信息
    String index = "";


    private ProgressDialogFragment mProgressDialog;
    private boolean isMore;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_webview;
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
        id = intent.getStringExtra("id") + "";
//        id = 148 + "";
        url = intent.getStringExtra("url") + "?token=" + UserConfig.getInstance().getToken() + "&article_id=" + id + "&show=true";
        url1 = intent.getStringExtra("url") + "?token=" + UserConfig.getInstance().getToken() + "&article_id=" + id;
        Log.d("Debug", "qingqiu de dizhis hi " + url);
//        url = "http://www.zhibohome.net/headlines/#/?token=WTC0SIvJ19W7Se1m1S1S&article_id=148&show=true";
        browse_amount = intent.getStringExtra("browse_amount") + "";
        good_amount = intent.getStringExtra("good_amount") + "";
        collect_amount = intent.getStringExtra("collect_amount") + "";
        register_good = intent.getIntExtra("register_good", 0);
        register_collect = intent.getIntExtra("register_collect", 0);
        position = intent.getIntExtra("position", 0);
        isfrom_banaer = intent.getBooleanExtra("isfrom_banaer", false);
        isFormType = intent.getStringExtra("isFormType") + "";
        isMore = getIntent().getBooleanExtra("isMore", false);
        type = getIntent().getStringExtra("type");
        Log.d("Debug", "传过来的type是" + type);
        Log.d("Debug", "有没有点击关注" + register_good);
        Log.d("Debug", "有没有收藏" + register_collect);
        initView();
        if (!TextUtils.isEmpty(type)) {
            if ("1".equals(type)) {
                mPresenter.commentNum(2, id);
            } else {
                mPresenter.commentNum(1, id);
            }
        }

        init(url);
    }

    /**
     * 界面布局
     *
     * @param
     */
    private void initView() {
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
        /**
         * 回复操作
         */
        webView.registerHandler("ReplyContent", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                isBoolHuifu = false;
                showComment("留下您的评论");
                Log.e("Debug", "指定Handler接收来自web的数据：" + data);
                String[] all = data.split(",");
                reply_to_id = all[1];
                to_nickname = all[2];
                index = all[3];
//                function.onCallBack("指定Handler收到Web发来的数据，回传数据给你");
            }
        });

        webView.registerHandler("ReplyNumber", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("Debug", "接受评论总数量：" + data);
                tvReadNumber.setText(data + "");
            }
        });

        /**
         * 跳转评论详情界面操作
         */
        webView.registerHandler("replyhttp", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                startActivity(new Intent(ActivityWebView.this, CommentDetailWebView.class).putExtra("data", data).putExtra("type", type));
            }
        });
        loadData();
    }

    private void loadData() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            webView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            rl_hiden.setVisibility(View.GONE);
            return;
        } else {
            webView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
            rl_hiden.setVisibility(View.VISIBLE);
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

    KeyMapDailog dialog;

    /**
     * 弹出评论窗
     */
    public void showComment(String hint) {
        dialog = new KeyMapDailog(hint, new KeyMapDailog.SendBackListener() {
            @Override
            public void sendBack(final String inputText) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(ActivityWebView.this.getSupportFragmentManager());
                        comment = inputText;
                        Log.d("Debug","点击评论时候type值为"+type);
                        if (type.equals("1")) {
                            if (isBoolHuifu) {
                                mPresenter.trainComment(id, inputText, UserConfig.getInstance().getNickname() + "");
                            } else {
                                mPresenter.trainReply(id, inputText, UserConfig.getInstance().getNickname() + "", reply_to_id, to_nickname);
                            }
                        } else {
                            if (isBoolHuifu) {
                                mPresenter.articleComment(id, inputText, UserConfig.getInstance().getNickname() + "");
                            } else {
                                mPresenter.articleReply(id, inputText, UserConfig.getInstance().getNickname() + "", reply_to_id, to_nickname);
                            }
                        }


                    }
                }, 500);
            }
        });
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @OnClick({R.id.webview_back, R.id.rl_commit, R.id.reload, R.id.webview_share, R.id.rl_collect, R.id.ll_like})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_commit:
                isBoolHuifu = true;
                showComment("留下您的评论");
                break;
            case R.id.webview_back:

                finish();
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
//            private String type;  // 1 咨询（一周咨询后面两个的点击模块） 2 一周咨询
            case R.id.rl_collect:
                if (PointUtils.isFastClick()) {
//                CultivatLikePresenter.requestPraise();
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
                    } else if (type.equals("3")) {
                        if (register_collect == 0) {
                            //点赞操作
                            Log.d("Debug", "到达这里的");
                            mPresenter.collect(id);
                        } else {
                            mPresenter.deleteCollect(id);
                        }
                    }
                }
                break;
            case R.id.ll_like:
                if (PointUtils.isFastClick()) {
//                    ToastUtil.showShort("点击事件");
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
                    } else if (type.equals("3")) {
                        if (register_good == 0) {
                            //点赞操作
                            mPresenter.createPraise(id);
                        } else {
                            mPresenter.deletePraise(id);
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
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
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

//        if (isMore){
//            if (type.equals("1")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_PRAISE, position));
//                Log.d("tt","1");
//            } else if (type.equals("2")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_HEAD_PRAISE, position));
//                Log.d("tt","2");
//            } else if (type.equals("3")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_Guild, position));
//                Log.d("tt","3");
//            }
//        }
        if (type.equals("1")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_PRAISE, position));
        } else if (type.equals("2")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_HEAD_PRAISE, position));
        } else if (type.equals("3")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_Guild, position));
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


//        if (isMore){
//            if (type.equals("1")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_COllECT, position));
//            } else if (type.equals("2")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_HEAD_COllECT, position));
//            } else if (type.equals("3")) {
//                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_Guild_COllECT, position));
//            }
//        }
        if (type.equals("1")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_COllECT, position));
        } else if (type.equals("2")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_HEAD_COllECT, position));
        } else if (type.equals("3")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_Guild_COllECT, position));
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
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Toast.makeText(ActivityWebView.this, "发表成功", Toast.LENGTH_LONG).show();
//        webView.callHandler("sen", "1234", new CallBackFunction() {
//            @Override
//            public void onCallBack(String data) {
//                Log.e("Debug", "来自web的回传数据：" + data);
//            }
//        });
        //调用web端方法
        if (isBoolHuifu) {
            //评论成功
            webView.loadUrl("javascript:successful()");
        } else {
            //恢复成功

//            String message = "{\"index\":\"\"+index+\"\",\"name\":\"\"+to_nickname+\"\",\"comment\":\"\"+to_nickname+\"\"}";
            WebInfo webInfo = new WebInfo(index, to_nickname, comment);
            String message = JsonUitl.objectToString(webInfo);
            Log.d("Debug", "传过去的数据为" + message);
            webView.loadUrl("javascript:senlist(" + message + ")");
        }
        dialog.dismiss();
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
        tvReadNumber.setText(resultBean.getNum() + "");
    }

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {

    }

    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        alertDialog = ShareCommontDialog.ShareDialog(ActivityWebView.this, url1, 1, imageurl, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    private static PopupWindow mPopWindow;

    /**
     * 分享举报
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ActivityWebView.this).inflate(R.layout.compact_comtent_share, null);
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
        View rootview = LayoutInflater.from(ActivityWebView.this).inflate(R.layout.activity_webview, null);
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
        //1 有评论 2没有评论 3 百科
        if (event.getType() == 1) {
            showPopupWindow();
        }
    }

}
//</editor-fold>

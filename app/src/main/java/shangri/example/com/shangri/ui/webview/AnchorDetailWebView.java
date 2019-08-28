package shangri.example.com.shangri.ui.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.view.CultivatLikePresenter;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;
import shangri.example.com.shangri.ui.activity.AddCompanyInfoActivity;
import shangri.example.com.shangri.ui.activity.AddJobActivity;
import shangri.example.com.shangri.ui.activity.SelectPayNumberActivity;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class AnchorDetailWebView extends BaseActivity<CultivateLikeView, CultivatLikePresenter> implements CultivateLikeView {
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.webView)
    android.webkit.WebView webView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.webview_back)
    ImageView webviewBack;
    @BindView(R.id.rl_info)
    RelativeLayout rl_info;

    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    String url = "";
    wantListBean.ResumesBean bean;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.webview6;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.webview6;
    }

    @Override
    protected CultivatLikePresenter createPresenter() {
        return new CultivatLikePresenter(this, this);
    }

    String id = "";
    String Register_id = "";
    String Telephone = "";
    String Nickname = "";
    String Info_url = "";
    //来自主播投递以及会长已沟通  不进行公司完善以及一系列判断
    boolean isFromOther;

    @Override
    protected void initViewsAndEvents() {

        id = getIntent().getStringExtra("id");
        Register_id = getIntent().getStringExtra("Register_id");
        Telephone = getIntent().getStringExtra("Telephone");
        Nickname = getIntent().getStringExtra("Nickname");
        Info_url = getIntent().getStringExtra("Info_url");
        isFromOther = getIntent().getBooleanExtra("isFromOther", false);
        url = Info_url + "";
        Log.d("Debug","传过来的地址是"+url);
//        url = "http://h5.tooohappy.xyz/NewJbz/#/";
        if (url != null && url.length() > 0) {
            init(url);
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
            rl_info.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            rl_info.setVisibility(View.VISIBLE);
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
    protected void onResume() {
        super.onResume();
        mPresenter.companyAdd(2, "", "", "");
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

    NewCompanyBean.CompanyBean company;
    int is_perfect;
    int residue_num;
    int is_issue;
    int is_linkup;

    @Override
    public void companyAdd(NewCompanyBean resultBean) {
//        公司信息是否完善 1完善 0不完善
        is_perfect = resultBean.getCompany().getIs_perfect();
//        公司剩余查看简历数量
        residue_num = resultBean.getCompany().getResidue_num();
//        是否发布过职位 1 发布过，0未发布
        is_issue = resultBean.getCompany().getIs_issue();
//        是否沟通过 1已沟通，0未沟通
        is_linkup = resultBean.getCompany().getIs_linkup();
        company = resultBean.getCompany();
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

    @OnClick({R.id.reload, R.id.webview_back, R.id.im_jubao, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                init(url);
                break;
            case R.id.webview_back:
                finish();
                break;
            case R.id.im_jubao:
                showPopupWindow();
//                ToastUtil.showShort("举报操作");
                break;
            case R.id.tv_exit:

                if (isFromOther) {
                    showPopupWindow(2);
                } else {
                    if (is_perfect == 1) {
                        if (is_linkup == 1) {
                            showPopupWindow(2);
                        } else {
                            if (residue_num > 0) {
                                //发不过
//                            ToastUtil.showShort("直接做拨打电话操作");
                                showPopupWindow(2);
                                mPresenter.residueNumber(id + "");
                            } else {
                                if (is_issue == 1) {
//                                ToastUtil.showShort("直接充值界面");
                                    showPopupWindow(4);
                                } else {
                                    showPopupWindow(3);
//                                ToastUtil.showShort("弹出发布职位或是充值界面");
                                }
                            }
                        }
                    } else {
//                    ToastUtil.showShort("完善公司操作");
                        showPopupWindow(1);
                    }
                }

                break;
        }
    }

    private static PopupWindow mPopWindow;

    /**
     * 1 完善公司弹窗 2 有次数的情况，正常查看 3.完善公司信息的免费次数用完后 4.完善公司信息,和发布职位获得的免费次数用完后
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorDetailWebView.this).inflate(R.layout.compact_add_gonghui6, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        TextView tv_text_hint = contentView.findViewById(R.id.tv_text_hint);
        RelativeLayout rl_info = contentView.findViewById(R.id.rl_info);

        rl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        switch (type) {
            case 1:
                tv_cancle.setText("取消");
                tv_next.setText("去完善");
                tv_phone_number.setVisibility(View.GONE);
                tv_text_hint.setVisibility(View.GONE);
                tv_content.setText("您尚未完善公司信息，不能查看联系方式");
                break;
            case 2:
                tv_cancle.setText("取消");
                tv_next.setText("拨打");
                tv_content.setText(Nickname + "");
                tv_phone_number.setVisibility(View.VISIBLE);
                tv_text_hint.setVisibility(View.GONE);
                tv_phone_number.setText("联系电话：" + Telephone);
                break;
            case 3:
                tv_cancle.setText("付费购买");
                tv_next.setText("发布职位");
                tv_content.setText("您的免费查看次数已用完，是否购买查看次数（2元/次");
                tv_phone_number.setVisibility(View.GONE);
                tv_text_hint.setVisibility(View.VISIBLE);
                break;
            case 4:
                tv_cancle.setText("取消");
                tv_next.setText("付费购买");
                tv_content.setText("您的免费获取次数已用完，是否购买查看次数");
                tv_phone_number.setVisibility(View.GONE);
                tv_text_hint.setVisibility(View.GONE);
                break;

        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                        Intent intent1 = new Intent(AnchorDetailWebView.this, AddCompanyInfoActivity.class);
                        intent1.putExtra("company_name", company.getCompany_name() + "");
                        intent1.putExtra("company_description", company.getCompany_description() + "");
                        intent1.putExtra("mCompressPath", company.getLogo() + "");
                        intent1.putExtra("company_scale", company.getCompany_scale() + "");
                        intent1.putExtra("anchor_scale", company.getAnchor_scale() + "");
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + Telephone + "");
                        intent.setData(data);
                        startActivity(intent);
                        //会长沟通主播
                        mPresenter.linkUpAnchor(Register_id + "", id + "");
                        break;
                    case 3:
                        startActivity(new Intent(AnchorDetailWebView.this, AddJobActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(AnchorDetailWebView.this, SelectPayNumberActivity.class));
//                        ToastUtil.showShort("付费购买");
                        break;
                }

                mPopWindow.dismiss();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                    case 2:
                    case 4:
                        mPopWindow.dismiss();
                        break;
                    case 3:
//                        ToastUtil.showShort("付费购买");
                        startActivity(new Intent(AnchorDetailWebView.this, SelectPayNumberActivity.class));
                        break;
                }
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorDetailWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * 1 举报功能
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorDetailWebView.this).inflate(R.layout.compact_comtent_share, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_content.setText("您已举报成功");
        tv_next.setText("知道了");

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopWindow1.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorDetailWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

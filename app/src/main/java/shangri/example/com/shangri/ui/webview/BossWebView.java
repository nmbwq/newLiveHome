package shangri.example.com.shangri.ui.webview;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.event.BossShareBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.BossFragmentPresenter;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.dialog.BossShareCommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class BossWebView extends BaseActivity<BossFragmentView, BossFragmentPresenter> implements BossFragmentView {
    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }


    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    @BindView(R.id.webView)
    BridgeWebView webView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.webview_back)
    ImageView webviewBack;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    String url = "";
    @BindView(R.id.im_jubao)
    ImageView imJubao;
    @BindView(R.id.im_quest)
    ImageView imQuest;
    @BindView(R.id.im_collect)
    ImageView imCollect;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rl_pop)
    RelativeLayout rlPop;

    //    是否收藏 1是 0否
    String is_colloect = "";
    //q安全页面提示
    String questUrl = "";
    //招聘的id
    String id = "";
    //电话
    String phone = "";
    //公司名称
    String company;
    //类型名字
    String type_name;
    //薪资高低
    String pay_low;
    String pay_high;
    //区域
    String work_position;


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.bosswebview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.bosswebview;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(BossWebView.this.getSupportFragmentManager());
        mPresenter.recruitPage();
        url = getIntent().getStringExtra("url");
        is_colloect = getIntent().getStringExtra("is_colloect");
        id = getIntent().getStringExtra("id");
        phone = getIntent().getStringExtra("phone");

        company = getIntent().getStringExtra("company");
        type_name = getIntent().getStringExtra("type_name");
        pay_low = getIntent().getStringExtra("pay_low");
        pay_high = getIntent().getStringExtra("pay_high");
        work_position = getIntent().getStringExtra("work_position");


        if (url != null && url.length() > 0) {
            init(url);
        }
        if (is_colloect.equals("1")) {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.shoucang_2));
        } else {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.yinpin_shoucang1));
        }
        //获取微信号以及电话号
        mPresenter.customLink();
    }

    //是否是分享成功后调用接口
    boolean isFromEvent;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossShareBean event) {
        Log.d("Debug", "分享成功");
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialogFragment();
//        }
//        mProgressDialog.show(BossWebView.this.getSupportFragmentManager());
        isFromEvent = true;
        mPresenter.anchorShare(id);
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

        /**
         * 点击公司操作
         */
        webView.registerHandler("company", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent(BossWebView.this, CompanyHomepageActivityTwo.class);
                intent.putExtra("COMPANY_TOKEN", data);
                startActivity(intent);
            }
        });

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


    @OnClick({R.id.webView, R.id.rl_info, R.id.ll_jubao, R.id.ll_tishi, R.id.reload, R.id.webview_back, R.id.im_jubao, R.id.im_quest, R.id.im_collect, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //举报界面
            case R.id.ll_jubao:
                showPopupWindow(1);
                rlPop.setVisibility(View.GONE);
                break;

            //安全提示界面
            case R.id.ll_tishi:
                Intent intent = new Intent(BossWebView.this, symbolWebView.class);
                intent.putExtra("url", questUrl);
                startActivity(intent);
                rlPop.setVisibility(View.GONE);
                break;
            case R.id.reload:
                init(url);
                break;
            case R.id.rl_info:
                rlPop.setVisibility(View.GONE);
                break;
            case R.id.webview_back:
                finish();
                break;
            //弹窗弹出
            case R.id.im_jubao:
                if (rlPop.getVisibility() == View.VISIBLE) {
                    rlPop.setVisibility(View.GONE);
                } else {
                    rlPop.setVisibility(View.VISIBLE);
                }
                break;
            //分享操作
            case R.id.im_quest:
//                showDialog();
                mPresenter.surplusMakeCall(id);
                break;
            //收藏
            case R.id.im_collect:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(BossWebView.this.getSupportFragmentManager());
                if (is_colloect.equals("1")) {
                    mPresenter.cancelCollect(id);
                } else {
                    mPresenter.doCollect(id);
                }
                break;
            //拨打电话
            case R.id.tv_exit:
                showPopupWindowSevenDays();
                //直接弹出拨打电话的弹窗
//                showPopupWindow1();
                break;
        }
    }


    private static PopupWindow mPopWindow;

    /**
     * 1 分享举报 2 沟通10次提醒 3.分享成功以后弹窗
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(BossWebView.this).inflate(R.layout.compact_comtent_share, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        TextView tv_content = contentView.findViewById(R.id.tv_content);
        switch (type) {
            case 1:
                tv_content.setText("您已举报成功");
                tv_next.setText("知道了");
                break;
            case 2:
                tv_content.setText("小主，您今天已沟通了10个招聘岗位了，分享沟通更多吧"
                );
                tv_next.setText("立即分享");
                break;
            case 3:
                tv_content.setText("您已分享成功,您还有" + number + "次沟通机会");
                tv_next.setText("知道了");
                break;
            case 4:
                tv_content.setText("今天已经分享过这条了，换一条去分享试一试呀~");
                tv_next.setText("知道了");
                break;

        }

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 2) {
                    showDialog();
                }
                mPopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(BossWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1() {
        //设置contentView
        View contentView = LayoutInflater.from(BossWebView.this).inflate(R.layout.compact_add_gonghui5, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        if (phone == null) {
            phone = "0571-87081736";
        }
        tv_phone_number.setText(phone);
        tv_content.setText(company);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                startActivity(intent);
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(BossWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void recruitList(BossDataBean mAccountDataBean) {

    }

    @Override
    public void NewrecruitList(newBossDataBean mAccountDataBean) {

    }

    @Override
    public void positionList(PositionListBean resultBean) {

    }

    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void resumeAllList(AllListBean resultBean) {

    }

    @Override
    public void anchorRecruitList(anchorRecruitListBean mAccountDataBean) {

    }

    @Override
    public void conscribeIndex(BossTongGaoBean mAccountDataBean) {

    }

    @Override
    public void wantList(wantListBean mAccountDataBean) {

    }

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {

    }

    @Override
    public void resumeState(ResumeIndexBean resultBean) {

    }

    //    VIP客服电话
    String custom_vip_tel = "";
    //    VIP客服微信
    String custom_vip_wx = "";
    //    客服电话
    String custom_tel = "";
    //    客服微信
    String custom_wx = "";

    @Override
    public void customLink(RequestListBean resultBean) {
        custom_vip_tel = resultBean.getCustom_vip_tel();
        custom_vip_wx = resultBean.getCustom_vip_wx();
        custom_tel = resultBean.getCustom_tel();
        custom_wx = resultBean.getCustom_wx();
    }


    @Override
    public void url(BossPlatBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        questUrl = mAccountDataBean.getUrl();
    }

    @Override
    public void doCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_colloect = "1";
        imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.shoucang_2));
        EventBus.getDefault().post(new BossBean(id, Integer.parseInt(is_colloect)));
    }

    @Override
    public void noLike() {

    }

    @Override
    public void cancelCollect() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        is_colloect = "0";
        imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.yinpin_shoucang1));
        EventBus.getDefault().post(new BossBean(id, Integer.parseInt(is_colloect)));
    }

    @Override
    public void sendSucceed(sendSucceedResonse resultBean) {

    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }

    @Override
    public void readPhoto(ReadPhotoBean resultBean) {

    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {

    }

    @Override
    public void anchorShare() {
        //分享成功以后的弹窗
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
        mPresenter.surplusMakeCall(id);

    }

    @Override
    public void anchorMakeTelephone() {

    }

    @Override
    public void makeCall(BanagetBean resultBean) {
        Log.d("Debug", "返回拨打电话次数为" + resultBean.getNumber());
        //刷新列表   变成已沟通
        EventBus.getDefault().post(new BossBean(id, true));
        if (resultBean.getIs_call() == 1) {
            showPopupWindow1();
        } else {
            if (resultBean.getNumber() > 0) {
                showPopupWindow1();
            } else {
                showPopupWindow(2);
            }
        }
    }

    /**
     * 分享成功剩余的拨打电话的次数
     */
    int number;

    @Override
    public void surplusMakeCall(BanagetBean resultBean) {
        if (resultBean.getIs_share() == 1) {
            if (isFromEvent) {
                if (resultBean.getNumber() <= 0) {
                    number = 0;
                } else {
                    number = resultBean.getNumber();
                }
                showPopupWindow(3);
                isFromEvent = false;
            } else {
                showPopupWindow(4);
            }
        } else {
            showDialog();
        }

    }

    @Override
    public void getCollect(CollectBean resultBean) {

    }

    @Override
    public void recruitBanner(BanagetBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        String tvcontext = "薪资：" + Integer.parseInt(pay_low) / 1000 + "k" + "-" + Integer.parseInt(pay_high) / 1000 + "k" + "\n" + "区域:" + work_position;
        String title = "【" + "直播之家" + "】" + company + "热招" + type_name;
        alertDialog = BossShareCommontDialog.ShareDialog(BossWebView.this, url + "?share=1", tvcontext, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(BossWebView.this).inflate(R.layout.add_boss_info1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        final TextView tv_content = contentView.findViewById(R.id.tv_content);
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        tv1.setText(custom_tel + "");
        tv2.setText(custom_wx + "");
        tv_content1.setText("温馨提示");
        tv_content.setText("该职位联系方式目前暂未对公会开放哦~如有疑问，可联系直播之家客服");
//        //显示PopupWindow
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + tv1.getText().toString());
                intent.setData(data);
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
            }
        });
        tv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(tv2.getText().toString() + ""); // 复制
                ToastUtil.showShort("已复制成功");
                mPopWindowSelectdays.dismiss();
            }
        });
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });
        View rootview = LayoutInflater.from(BossWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}

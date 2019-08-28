package shangri.example.com.shangri.ui.webview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
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
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.dialog.BossShareCommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.FileUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class AnchorBossWebView extends BaseActivity<BossFragmentView, BossFragmentPresenter> implements BossFragmentView {
    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void customLink(RequestListBean resultBean) {

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
    @BindView(R.id.rl_pop_down)
    RelativeLayout rl_pop_down;


    @BindView(R.id.Im_text)
    ImageView Im_text;
    @BindView(R.id.ll_bottom)
    RelativeLayout ll_bottom;


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
    String qq;
    String weixin;
    String youxiang;

    List<ReadPhotoBean.ResumeBean> PhoneList = new ArrayList<>();
    ResumeIndexBean.ResumeBean resume;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.ll1)
    LinearLayout ll1;

    @BindView(R.id.ll4)
    LinearLayout ll4;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.ll6)
    LinearLayout ll6;


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.anchor_bosswebview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.anchor_bosswebview;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(this, this);
    }


    @Override
    protected void initViewsAndEvents() {
        Log.d("Debug", "到达这个页面");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
        mPresenter.recruitPage();
        is_colloect = getIntent().getStringExtra("is_colloect");
        id = getIntent().getStringExtra("id");
//        url = getIntent().getStringExtra("url") + "?id=" + id + "&share=0&token=" + UserConfig.getInstance().getToken();
//        Log.d("Debug", "传过去的url" + url);
        url = getIntent().getStringExtra("url");
        phone = getIntent().getStringExtra("phone");
        Log.d("Debug", "传递过来的电话为" + phone);
        company = getIntent().getStringExtra("company");
        type_name = getIntent().getStringExtra("type_name");
        pay_low = getIntent().getStringExtra("pay_low");
        pay_high = getIntent().getStringExtra("pay_high");
        work_position = getIntent().getStringExtra("work_position");
        qq = getIntent().getStringExtra("qq");
        weixin = getIntent().getStringExtra("weixin");
        youxiang = getIntent().getStringExtra("youxiang");
        //上面三个如果是没有  布局隐藏
        if (qq == null || qq.length() == 0) {
            ll4.setVisibility(View.GONE);
        }
        if (weixin == null || weixin.length() == 0) {
            ll5.setVisibility(View.GONE);
        }
        if (youxiang == null || youxiang.length() == 0) {
            ll6.setVisibility(View.GONE);
        }

        if (url != null && url.length() > 0) {
            init(url);
        }
        if ("1".equals(is_colloect)) {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.shoucang_2));
        } else {
            imCollect.setImageDrawable(getResources().getDrawable(R.mipmap.yinpin_shoucang1));
        }

        Im_text.setVisibility(View.GONE);
        //留电话功能隐藏
        ll1.setVisibility(View.GONE);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossShareBean event) {
        Log.d("Debug", "分享成功");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
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
                Log.d("Debug", "返回的token值为" + data);
                Intent intent = new Intent(AnchorBossWebView.this, CompanyHomepageActivityTwo.class);
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


    @OnClick({R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll1, R.id.ll2, R.id.ll3, R.id.webView, R.id.rl_info, R.id.ll_jubao, R.id.ll_tishi, R.id.reload, R.id.webview_back, R.id.im_jubao, R.id.im_quest, R.id.im_collect, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //qq沟通
            case R.id.ll4:
                showCopePopupWindow(1, qq);
                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(qq + ""); // 复制
                break;
            //微信沟通
            case R.id.ll5:
                showCopePopupWindow(2, weixin);
                ClipboardManager clip1 = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip1.getText(); // 粘贴
                clip1.setText(weixin + ""); // 复制
                break;
            //邮箱沟通
            case R.id.ll6:
                showCopePopupWindow(3, youxiang);
                ClipboardManager clip3 = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip3.getText(); // 粘贴
                clip3.setText(youxiang + ""); // 复制
                break;
            //留电话
            case R.id.ll1:
                phoneWindow();
                rl_pop_down.setVisibility(View.GONE);
                break;
            //拨打电话
            case R.id.ll2:
                mPresenter.makeCall(id);
                rl_pop_down.setVisibility(View.GONE);
                break;
            //发送形象卡
            case R.id.ll3:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
                //不用展示形象卡   现在直接发送简历
                mPresenter.sendSucceed("2", id, "", "");
//                mPresenter.readPhoto();

                break;
            //举报界面
            case R.id.ll_jubao:
                showPopupWindow(1);
                rlPop.setVisibility(View.GONE);
                break;

            //安全提示界面
            case R.id.ll_tishi:
                Intent intent = new Intent(AnchorBossWebView.this, symbolWebView.class);
                intent.putExtra("url", questUrl);
                startActivity(intent);
                rlPop.setVisibility(View.GONE);
                break;
            case R.id.reload:
                init(url);
                break;
            case R.id.rl_info:
                rlPop.setVisibility(View.GONE);
                rl_pop_down.setVisibility(View.GONE);
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
                showDialog();
                break;
            //收藏
            case R.id.im_collect:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
                if (is_colloect.equals("1")) {
                    mPresenter.cancelCollect(id);
                } else {
                    mPresenter.doCollect(id);
                }
                break;
            //拨打电话
            case R.id.tv_exit:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());

                mPresenter.resumeState();
                break;
        }
    }


    private static PopupWindow mPopWindow;

    /**
     * 1 分享举报 2 沟通10次提醒 3.分享成功以后弹窗
     */
    private void showPopupWindow(final int type) {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.compact_comtent_share, null);
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
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mCopePopWindow;

    /**
     * 粘贴复制
     */
    private void showCopePopupWindow(final int type, String context) {
        //设置contentView
        View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.compact_comtent_share3, null);
        mCopePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCopePopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        TextView tv_content = contentView.findViewById(R.id.tv_content);

        ImageView image_type = contentView.findViewById(R.id.image_type);
        TextView tv_type = contentView.findViewById(R.id.tv_type);
        tv_type.setText(context + "");
        switch (type) {
            case 1:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.qq_fuzhi));
                tv_content.setText("已成功复制QQ号码");
                break;
            case 2:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.weixin));
                tv_content.setText("已成功复制微信账号"
                );
                break;
            case 3:
                image_type.setImageDrawable(getResources().getDrawable(R.mipmap.youxiang_fuzhi));
                tv_content.setText("已成功复制邮箱账号");
                break;
        }
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCopePopWindow.dismiss();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        mCopePopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow1;

    /**
     * * 拨打电话
     */


    private void showPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.compact_add_gonghui2, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        if (phone == null) {
            phone = "0571-87081736";
        } else {
            if (UserConfig.getInstance().getRole().equals("2")) {
                if (phone.length() > 0) {
                } else {
                    phone = "0571-87081736";
                }
            } else {
                phone = "0571-87081736";
            }
        }
        tv_content.setText(phone);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("Debug", "两个年份之间相差" + TimeUtil.yearChect("1994"));
//                CutImage(ll_text);
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
                if (isCall) {
                    mPresenter.anchorMakeTelephone(id);
                }
//                callPhone("18668121550");
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mNoHavePopWindow1;

    /**
     * * 拨打电话
     */


    private void showNoHavePopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.compact_add_gonghui2, null);
        mNoHavePopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mNoHavePopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_content.setText("上传简历，更容易c位出道哦！");
        tv_cancle.setText("放弃");
        tv_next.setText("c位出道");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNoHavePopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnchorBossWebView.this, AddInviteActivity.class));
                mNoHavePopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        mNoHavePopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
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

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//            状态 -1没有简历 0:未审核 1:审核通过 2:审核失败
        switch (resultBean.getStatus()) {
            case -1:
                showNoHavePopupWindow1();
                break;
            case 0:
                ToastUtil.showShort("您的简历正在审核中，暂不能操作");
                break;
            case 1:
                if (rl_pop_down.getVisibility() == View.VISIBLE) {
                    rl_pop_down.setVisibility(View.GONE);
                } else {
                    rl_pop_down.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                ToastUtil.showShort("您的简历审核失败，请重新上传简历");
                break;
        }

    }

//    @Override
//    public void resumeDetail(ResumeIndexBean resultBean) {
//
//
//
//    }

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
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        rl_pop_down.setVisibility(View.GONE);
        ToastUtil.showShort("发送成功");
        //刷新列表   变成已沟通
        EventBus.getDefault().post(new BossBean(id, true));
        if (phonePopWindow != null) {
            if (phonePopWindow.isShowing()) {
                phonePopWindow.dismiss();
            }
        }
        if (mCardPopWindow1 != null) {
            if (mCardPopWindow1.isShowing()) {
                mCardPopWindow1.dismiss();
            }
        }
//        约聊ID 为0时不跳转约聊界面
        if (resultBean.chat_id > 0) {
            Intent intent = new Intent(this, TellAboutListActivity.class);
            intent.putExtra("chat_id", resultBean.getChat_id() + "");
            startActivity(intent);
        }

    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }

    @Override
    public void readPhoto(ReadPhotoBean resultBean) {

        if (PhoneList.size() > 0) {
            PhoneList.clear();
        }
        PhoneList = resultBean.getResume();
        mPresenter.resumeIndex();

    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        resume = resultBean.getResume();
        showCardPopupWindow1();

    }

    @Override
    public void anchorShare() {
        //分享成功以后的弹窗
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.surplusMakeCall(id);
    }

    @Override
    public void anchorMakeTelephone() {

    }

    //是否已拨打电话
    boolean isCall;

    @Override
    public void makeCall(BanagetBean resultBean) {
        Log.d("Debug", "返回拨打电话次数为" + resultBean.getNumber());
        //刷新列表   变成已沟通
        EventBus.getDefault().post(new BossBean(id, true));
        if (resultBean.getIs_call() == 1) {
            isCall = false;
            showPopupWindow1();
        } else {
            if (resultBean.getNumber() > 0) {
                isCall = true;
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
        if (resultBean.getNumber() <= 0) {
            number = 0;
        } else {
            number = resultBean.getNumber();
        }
        showPopupWindow(3);
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

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        String tvcontext = "薪资：" + Integer.parseInt(pay_low) / 1000 + "k" + "-" + Integer.parseInt(pay_high) / 1000 + "k" + "\n" + "区域:" + work_position;
        String title = "【" + "直播之家" + "】" + company + "热招" + type_name;
        alertDialog = BossShareCommontDialog.ShareDialog(AnchorBossWebView.this, url + "?share=1", tvcontext, title, new View.OnClickListener() {
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


    /**
     * 截图实现
     */
    private void CutImage(final View view) {
        // 获取图片某布局
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = view.getDrawingCache();
                // 获取图片
//                savePicture(bmp, "test.jpg");
//                String s = saveBitmap(getActivity(), bmp);
//                Log.d("Debug", "返回的图片地址是" + s);
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
                String s = FileUtil.saveFileToGallery(AnchorBossWebView.this, bmp);
                mPresenter.sendSucceed("2", id, "", s);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] bytes = baos.toByteArray();
//                Im_text.setVisibility(View.VISIBLE);
//                Glide.with(AnchorBossWebView.this)
//                        .load(bytes)
//                        .placeholder(R.mipmap.bg_touxiang)
//                        .into(Im_text);
                // 保存图片
                view.destroyDrawingCache(); // 保存过后释放资源 } },100); }
            }
        }, 100);
    }

    private static PopupWindow phonePopWindow;

    /**
     * * 预留电话
     */
    //false 没有点击修改
    boolean ischangge = false;

    private void phoneWindow() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.compact_add_gonghui4, null);
        phonePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        phonePopWindow.setContentView(contentView);

        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final RelativeLayout rl_et = contentView.findViewById(R.id.rl_et);
        final RelativeLayout rl_tv = contentView.findViewById(R.id.rl_tv);
        final TextView tv_phone_number = contentView.findViewById(R.id.tv_phone_number);
        tv_phone_number.setText(UserConfig.getInstance().getResumeTelephone());
        TextView tv_update = contentView.findViewById(R.id.tv_update);
        final EditText et_number = contentView.findViewById(R.id.et_number);
        ImageView im_clear = contentView.findViewById(R.id.im_clear);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_tv.setVisibility(View.GONE);
                rl_et.setVisibility(View.VISIBLE);
                et_number.setText(tv_phone_number.getText().toString());
                ischangge = true;
            }
        });
        im_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_number.setText("");
            }
        });


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonePopWindow.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ischangge) {
                    if (et_number.getText().toString().length() == 0) {
                        ToastUtil.showShort("请填写电话号码");
                        return;
                    }
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
                    mPresenter.sendSucceed("1", id, et_number.getText().toString(), "");
                } else {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(AnchorBossWebView.this.getSupportFragmentManager());
                    mPresenter.sendSucceed("1", id, tv_phone_number.getText().toString(), "");
                }
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        phonePopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mCardPopWindow1;

    /**
     * 展示形象卡
     */
    private void showCardPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.have_resume_card_layout, null);
        mCardPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mCardPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        final LinearLayout ll_text = contentView.findViewById(R.id.ll_text);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        final ScrollView sc = contentView.findViewById(R.id.sc);

        final RelativeLayout rl_info = contentView.findViewById(R.id.rl_info);


        ImageView im1 = contentView.findViewById(R.id.im1);
        ImageView im2 = contentView.findViewById(R.id.im2);
        ImageView im3 = contentView.findViewById(R.id.im3);

        TextView tv_name = contentView.findViewById(R.id.tv_name);
        TextView tv_age = contentView.findViewById(R.id.tv_age);
        ImageView image_sex = contentView.findViewById(R.id.image_sex);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);


        if (PhoneList.size() == 1) {
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))

                    // 设置高斯模糊
                    .into(im1);
        } else if (PhoneList.size() == 2) {
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))
                    // 设置高斯模糊
                    .into(im2);
        } else if (PhoneList.size() == 3) {
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))
                    // 设置高斯模糊
                    .into(im2);
            Glide.with(AnchorBossWebView.this)
                    .load(PhoneList.get(2).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(AnchorBossWebView.this, 10))

                    .crossFade()
                    // 设置高斯模糊
                    .into(im3);
        }


        tv_name.setText(resume.getNickname() + "");
        tv_age.setText(resume.getAge() + "岁");
//        性别 1男2女
        if ("1".equals(resume.getSex())) {
            image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nan));
        } else {
            image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nv));
        }
        tv_phone.setText(resume.getTelephone() + "");


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPopWindow1.dismiss();
                CutImage(rl_info);
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AnchorBossWebView.this).inflate(R.layout.bosswebview, null);
        mCardPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

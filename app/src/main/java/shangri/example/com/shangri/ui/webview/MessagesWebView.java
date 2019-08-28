package shangri.example.com.shangri.ui.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.presenter.RecruitDetailPresenter;
import shangri.example.com.shangri.presenter.view.RecruitDetailView;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 消息跳转H5界面
 */
public class MessagesWebView extends BaseActivity<RecruitDetailView, RecruitDetailPresenter> implements RecruitDetailView {
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.webview_back)
    ImageView webview_back;
    @BindView(R.id.webView)
    BridgeWebView webView;
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    String URL = "";

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_messages_web_view;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_messages_web_view;
    }

    @Override
    protected RecruitDetailPresenter createPresenter() {
        return new RecruitDetailPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//        URL = "https://testh5.ilivehome.net/single/recommend/recommend_anchor.html?rcd_id=64";
        URL = getIntent().getStringExtra("url");

        initWebView();
    }

    @Override
    public void getRecruitDetail(RecruitDetailBean recruitDetailBean) {
        URL = recruitDetailBean.getXcx().get(0).getSource_url();
        RecruitDetailBean.XcxBean xxx = recruitDetailBean.getXcx().get(0);
        if (UserConfig.getInstance().getRole().equals("1")) {
            Intent intent = new Intent(this, BossWebView.class);
            intent.putExtra("url", RxApi.getBaseUrl() + "api/read/recruit/detail/" + xxx.getId());
//        intent.putExtra("url", xxx.getSource_url()+xxx.getId());
            intent.putExtra("is_colloect", xxx.getIs_collect());
            intent.putExtra("id", xxx.getId());
            intent.putExtra("phone", xxx.getContact_phone());
            intent.putExtra("company", xxx.getCompany());
            intent.putExtra("type_name", xxx.getType_name());
            intent.putExtra("pay_low", xxx.getPay_low());
            intent.putExtra("pay_high", xxx.getPay_high());
            intent.putExtra("work_position", xxx.getCompanyInfo().getLocation());
            intent.putExtra("qq", xxx.getQq());
            intent.putExtra("weixin", xxx.getWeixin());
            intent.putExtra("youxiang", xxx.getEmail());
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AnchorBossWebView2.class);
            intent.putExtra("need_id", xxx.getId() + "");
            startActivity(intent);
        }

    }

    @Override
    public void getResumeDetailBean(ResumeDetailBean resumeDetailBean) {
        Intent intent = new Intent(this, LookAnchorDetailActivity.class);
        intent.putExtra("resumeDetailBean", (Serializable) resumeDetailBean.getResume());
        startActivity(intent);
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void initWebView() {
        Log.e("TAG", "initWebView: URL = " + URL);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        //支持https
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        /**
         *  主播找工作
         */
        webView.registerHandler("SEND_RECRUIT_ID", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("registerHandler1", "id =" + data);
                mPresenter.getRecruitDetail(data);
            }
        });
        /**
         *  公会找主播
         */
        webView.registerHandler("SEND_RESUME_ID", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {
                Log.e("registerHandler2", "id = " + data);
                Intent intent = new Intent(MessagesWebView.this, LookAnchorDetailActivity.class);
                intent.putExtra("id", data);
                startActivity(intent);
            }
        });
        if (!NetWorkUtil.isNetworkConnected(this)) {
            webView.setVisibility(View.GONE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            webView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.GONE);
        }
        if (URL.length() == 0) return;
        webView.loadUrl(URL);
    }

    @OnClick({R.id.reload, R.id.webview_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webview_back:
                finish();
                break;
            case R.id.reload:
                initWebView();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null)
            webView = null;
    }
}

package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.LoginUserPresenter;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 用户登录
 * Created by mschen on 2017/6/23.
 */

public class LoginTypeActivity extends BaseActivity<LoginUserView, LoginUserPresenter> implements LoginUserView {

    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.im_weixin_login)
    ImageView imWeixinLogin;
    @BindView(R.id.im_zhanghao_login)
    ImageView imZhanghaoLogin;
    private ProgressDialogFragment mProgressDialog;
    boolean isFromToken;

    @Override
    protected LoginUserPresenter createPresenter() {
        return new LoginUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        isFromToken = getIntent().getBooleanExtra("isFromToken", false);
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        mPresenter.weburl();
    }

    private static final String TAG = "LoginUserActivity";
    UMAuthListener authListener = new UMAuthListener() {//Umeng
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(LoginUserActivity.this, "成功了", Toast.LENGTH_LONG).show();
            WeChatBean weChatBean = new WeChatBean();
            for (String key : data.keySet()) {
                if (key.equals("openid")) {
                    weChatBean.openid = data.get(key) + "";
                }
                if (key.equals("country")) {
                    weChatBean.country = data.get(key) + "";
                }
                if (key.equals("unionid")) {
                    weChatBean.unionid = data.get(key) + "";
                }
                if (key.equals("gender")) {
                    String gender = data.get(key) + "";
                    if (gender.equals("男")) {
                        weChatBean.sex = "1";
                    } else {
                        weChatBean.sex = "2";
                    }
                }
                if (key.equals("city")) {
                    weChatBean.city = data.get(key) + "";
                }
                if (key.equals("province")) {
                    weChatBean.province = data.get(key) + "";
                }
                if (key.equals("name")) {
                    weChatBean.nickname = data.get(key) + "";
                }
                if (key.equals("iconurl")) {
                    weChatBean.headimgurl = data.get(key) + "";
                }
            }
            weChatBean.privilege = "";
            Log.i(TAG, "onComplete: weChatBean:" + weChatBean.toString());
            //记录当前时间，后面会有判断是不是同一天的操作
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getSupportFragmentManager());
            mPresenter.loginWX(weChatBean);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(LoginUserActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            SocializeUtils.safeCloseDialog(dialog);
//            Toast.makeText(LoginUserActivity.this, "取消了", Toast.LENGTH_LONG).show();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    };


    @Override
    public void onLogin(UserInfoBean user) { //登陆成功
//
    }

    @Override
    public void loginWX(WeChatInfoBean resultBean) {
        if (resultBean.getToken().isEmpty()) {
            Constants.WXINFO_ID = resultBean.getWxinfo_id();
            UserConfig.getInstance().setWxinfoId(resultBean.getWxinfo_id());
            ActivityUtils.startActivity(this, BoundTelActivity.class);
        } else {
            UserConfig.getInstance().setToken(resultBean.getToken());
            UserConfig.getInstance().setWxinfoId(resultBean.getWxinfo_id());
            ActivityUtils.startActivity(this, MainActivity.class);
            mPresenter.memberLogin();
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    String url;

    @Override
    public void signProtocol(WebBean resultBean) {
        url = resultBean.getUrl();
    }

    @Override
    public void memberLogin() {

    }

    @Override
    public void adIndex(AdDataBean resultBean) {

    }

    @Override
    public void privaryDeal(AdDataBean resultBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestFailed(String message) {
//        Loading.dismiss();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setRememberPwd(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_type_user_login;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_type_user_login;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_agree, R.id.user_login_back, R.id.im_weixin_login, R.id.im_zhanghao_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_login_back:
                if (isFromToken) {
                    Intent resultIntent6 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                    resultIntent6.putExtra("type", 0);
                    startActivity(resultIntent6);
                    finish();
                } else {
                    finish();
                }
                break;
            case R.id.tv_agree:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(LoginTypeActivity.this, symbolWebView.class).putExtra("url", url));
                }
                break;
            case R.id.im_weixin_login:
                if (PointUtils.isFastClick()) {
                    GlobalApp.WeinxinShare = true;
                    UMShareAPI.get(this).getPlatformInfo(LoginTypeActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                }
                break;
            case R.id.im_zhanghao_login:
                if (PointUtils.isFastClick()) {
                    //记录当前时间，后面会有判断是不是同一天的操作
                    startActivity(new Intent(LoginTypeActivity.this, NewLoginUserActivity.class));
                }
                break;
        }
    }

    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isFromToken) {
                Intent resultIntent6 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                resultIntent6.putExtra("type", 0);
                startActivity(resultIntent6);
                finish();
            } else {
                finish();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}

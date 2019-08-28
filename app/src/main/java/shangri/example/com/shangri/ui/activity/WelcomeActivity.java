package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.LoginUserPresenter;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.ui.view.CircleTextProgressbar;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.SharedPreferenceUtil;

/**
 * Created by Administrator on 2017/7/3.
 * do what to 2017/7/3
 */

public class WelcomeActivity extends BaseActivity<LoginUserView, LoginUserPresenter> implements LoginUserView {


    @BindView(R.id.await)
    CircleTextProgressbar await;
    @BindView(R.id.fl)
    FrameLayout fl;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected LoginUserPresenter createPresenter() {
        return new LoginUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.adIndex();
        mPresenter.privaryDeal();
        //不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
    }

    public void initEvent() {
        if (UserConfig.getInstance().getMobile() != null && UserConfig.getInstance().getMobile().length() > 0) {
            JPushInterface.setAlias(getApplicationContext(), GlobalApp.sequence++, UserConfig.getInstance().getMobile());
        }
        if (await != null) {
            await.setTimeMillis(100);
            await.setText("跳过");
            await.setInCircleColor(getResources().getColor(R.color.toast_bg));
            await.setProgressColor(getResources().getColor(R.color.toast_bg));
            await.start();
            await.setCountdownProgressListener(1, new CircleTextProgressbar.OnCountdownProgressListener() {
                @Override
                public void onProgress(int what, int progress) {
                    if (progress == 0) {
                        jumpMain();
                    }
                }
            });
        }
    }


    private void jumpMain() {
//        暂时隐藏引导页
        String isGuided = (String) SharedPreferenceUtil.get(this, Constant.IS_GUIDED, "");
        if (TextUtils.isEmpty(isGuided)) {
            //游客身份默认进来是主播身份
            UserConfig.getInstance().setRole("2");
            UserConfig.getInstance().setVisitor("1");
            ActivityUtils.startActivity(this, SplashActivity.class);
            finish();
            return;
        }

        //有touken就去请求用户资料
        String token = UserConfig.getInstance().getToken();
        String username = UserConfig.getInstance().getMobile();
        String pwd = UserConfig.getInstance().getPwd();
        String role = UserConfig.getInstance().getRole();

        //登录过的不是游客身份
        if (!TextUtils.isEmpty(token)) {
            mPresenter.memberLogin();
//            if (UserConfig.getInstance().getVisitor().equals("0")) {
            if (TextUtils.isEmpty(role)) {
                ActivityUtils.startActivity(this, SoftwareActivity.class);
                finish();
                return;
            }
//            }
        } else {
            //游客身份默认进来是主播身份
            UserConfig.getInstance().setRole("2");
            UserConfig.getInstance().setVisitor("1");
        }

//        //跳到登陆界面
////        if (TextUtils.isEmpty(token)) {
////            ActivityUtils.startActivity(this, LoginTypeActivity.class);
////            finish();
////            return;
////        }
////        //跳到主界面
////        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) { //pws ="" token有问题
////            ActivityUtils.startActivity(this, MainActivity.class);
////            finish();
////        }
        ActivityUtils.startActivity(this, MainActivity.class);
        finish();

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void onLogin(UserInfoBean resultBean) {

    }

    @Override
    public void loginWX(WeChatInfoBean resultBean) {

    }

    @Override
    public void signProtocol(WebBean resultBean) {

    }

    @Override
    public void memberLogin() {
//        fl.setBackground(getResources().getDrawable(R.mipmap.hei));
    }

    //本身份是否可以展示
    boolean flag = false;

    @Override
    public void adIndex(final AdDataBean resultBean) {
        if (resultBean.getAd().getDownym() == null) {
            Log.d("Debug", "返回数据为空");
            initEvent();
        } else {
            Log.d("Debug", "返回数据不为空");
//            1会长 2主播 3管理员 (项目里面身份的代表)
//            接收对象 1会长2管理员3主播（返回的身份代表）
            List<String> jsdx = resultBean.getAd().getJsdx();
            if (UserConfig.getInstance().getRole().equals("1")) {
                for (String s : jsdx) {
                    if (s.equals("1")) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                for (String s : jsdx) {
                    if (s.equals("3")) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            } else if (UserConfig.getInstance().getRole().equals("3")) {
                for (String s : jsdx) {
                    if (s.equals("2")) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            } else {
                //没有注册用户进来显示广告
                flag = false;
            }
            if (flag) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this, AdActivity.class).putExtra("bean", resultBean));
                        finish();
                    }
                }, 100);
            } else {
                initEvent();
            }
        }
    }

    @Override
    public void privaryDeal(AdDataBean resultBean) {
        if (resultBean.getUrl() == null) {
            GlobalApp.URL = "";
        } else {
            GlobalApp.URL = resultBean.getUrl();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        Uri uri = intent.getData();
//        if (uri != null) {
//            String mydata = uri.getQueryParameter("url");
//            GlobalApp.htmlUrl = mydata;
//        }
    }
}

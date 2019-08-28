package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

public class AdActivity extends BaseActivity<LoginUserView, LoginUserPresenter> implements LoginUserView {

    @BindView(R.id.await)
    CircleTextProgressbar await;
    @BindView(R.id.im_ad)
    ImageView imAd;

    AdDataBean bean;
    boolean isOnclick = false;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_ad;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_ad;
    }

    @Override
    protected LoginUserPresenter createPresenter() {
        return new LoginUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        //不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        bean = (AdDataBean) getIntent().getSerializableExtra("bean");
        initEvent();
        Glide.with(AdActivity.this)
                .load(bean.getAd().getPhoto())
                .placeholder(R.mipmap.hei)
                .crossFade()
                .into(imAd);
    }

    public void initEvent() {
        if (UserConfig.getInstance().getToken() != null && UserConfig.getInstance().getToken().length() > 0) {
            mPresenter.memberLogin();
        }
        findViewById(R.id.im_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserConfig.getInstance().getToken().length()>0){
                    isOnclick = true;
                    Log.d("Debug", "点击事件");
                    jumpMain();
                }
            }
        });
        if (UserConfig.getInstance().getMobile() != null && UserConfig.getInstance().getMobile().length() > 0) {
            JPushInterface.setAlias(getApplicationContext(), GlobalApp.sequence++, UserConfig.getInstance().getMobile());
        }
        if (await != null) {
            await.setTimeMillis(2000);
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
//        String isGuided = (String) SharedPreferenceUtil.get(this, Constant.IS_GUIDED, "");
//        if (TextUtils.isEmpty(isGuided)) {
//            ActivityUtils.startActivity(this, SplashActivity.class);
//            finish();
//            return;
//        }
//        //有touken就去请求用户资料
//        String token = UserConfig.getInstance().getToken();
//        String username = UserConfig.getInstance().getMobile();
//        String pwd = UserConfig.getInstance().getPwd();
//        String role = UserConfig.getInstance().getRole();
//
//        //当用户和密码token都有时，角色没有进入角色页面
//        if (!TextUtils.isEmpty(token) && TextUtils.isEmpty(role) || role.equals("0")) {
//            ActivityUtils.startActivity(this, SoftwareActivity.class);
//            finish();
//            return;
//        }
//        //跳到登陆界面
//        if (TextUtils.isEmpty(token)) {
//            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
//            finish();
//            return;
//        }


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
            //注册好之后 没有选择身份 跳转选择身份界面（这里只是提示 没有做判断）
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
        //跳到主界面
        GlobalApp.IsShowPop = true;
//                落地页面 1看看2报表3管理4会报
        Intent intent = new Intent(AdActivity.this, MainActivity.class);
        if (bean.getAd().getHttp_url().length() > 0) {
            intent.putExtra("type", Integer.parseInt("0"));
            if (isOnclick) {
                intent.putExtra("url", bean.getAd().getHttp_url()+"?token="+UserConfig.getInstance().getToken());
            } else {
                intent.putExtra("url", "");
            }
            intent.putExtra("IsFromAd", true);
        } else {
//            intent.putExtra("type", Integer.parseInt(bean.getAd().getDownym()));
            intent.putExtra("type", Integer.parseInt("0"));
            intent.putExtra("url", "");
            intent.putExtra("IsFromAd", true);
        }
        startActivity(intent);
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
    }

    @Override
    public void adIndex(AdDataBean resultBean) {

    }

    @Override
    public void privaryDeal(AdDataBean resultBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

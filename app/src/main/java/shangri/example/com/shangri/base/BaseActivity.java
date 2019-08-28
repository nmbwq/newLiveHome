package shangri.example.com.shangri.base;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;


import com.umeng.analytics.MobclickAgent;

import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by chengaofu on 2017/6/19.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends SwipeBackActivity {

    public BaseController mBaseController;
    protected Unbinder mUnbinder;
    protected T mPresenter;
    protected SwipeBackLayout mSwipeBackLayout;
    private static final int VIBRATE_DURATION = 20;
    /**
     * Notification管理
     */
    public static NotificationManager mNotificationManager;

    public BaseActivity() {
        super();
        //创建EventBus
        mBaseController = new BaseController();

    }


    /**
     * 初始化要用到的系统服务
     */
    private void initService() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate: ","this Activity is "+this.getClass() );
        // 把actvity放到application栈中管理
        ActivityManager.getInstance().onCreate(this);
        //设置状态栏
//        StatusBarUtil.transparencyBar(this);
//        StatusBarUtil.StatusBarLightMode(this);

        //注册EventBus
        mBaseController.register(this);
        //创建Presenter
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        if (NetWorkUtil.isNetworkConnected(this)) {
            setContentView(getNormalLayoutId());
        } else {
            setContentView(getErrorLayoutId());
        }

        //左滑返回上一级
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {

            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                vibrate(VIBRATE_DURATION);
            }

            @Override
            public void onScrollOverThreshold() {
                vibrate(VIBRATE_DURATION);
            }
        });

        //绑定控件
        mUnbinder = ButterKnife.bind(this);
        //初始化
        initViewsAndEvents();
//        initService();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        mUnbinder.unbind();
        //销毁EventBus
        mBaseController.unregister(this);
        //解除presenter与view之间的关系
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ActivityManager.getInstance().onDestroy(this);
    }

    private void vibrate(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }

    //接收EventBus的消息
    @Subscribe
    public void onMessageEventMain(Message message) {
        mBaseController.onMessageEventMain(this, message);
    }

    //正常的页面
    protected abstract int getNormalLayoutId();

    //没有网络后加载的页面
    protected abstract int getErrorLayoutId();

    protected abstract T createPresenter();

    //加载数据
    protected abstract void initViewsAndEvents();
}

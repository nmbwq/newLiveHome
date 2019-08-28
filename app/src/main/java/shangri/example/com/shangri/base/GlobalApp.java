package shangri.example.com.shangri.base;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;
import me.jessyan.autosize.AutoSizeConfig;
import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.util.SystemUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 全局Application
 * Created by chengaofu on 2017/6/19.
 */

public class GlobalApp extends Application {

    public static GlobalApp mAppContext;

    public static MediaPlayer mMediaPlayer;

    public static String html = "";
    //默认值是正方形  1上传简历 16:9的比例 2是上传公司图片比例
    public static int ImageScale = 1000;
    public static String imageurl = "";
    public static String audiourl = "";
    public static String title = "";
    public static int register = -1;
    public static int playPosition = -1;
    public static String pageid = "";
    public static String id = "";
    public static IWXAPI mWxApi;
    public static int sequence = 1000;
    public static Boolean IsFromAliyun = false;
    //是不是微信原生分享
    public static Boolean WeinxinShare = false;
    //h5启动app传过来的url地址
    public static String htmlUrl = "";

    public static NotificationManager mNotificationManager;
    //android 8.0 需要的资料
    public static int pushNum = 1;
    public static final int PUSH_NOTIFICATION_ID = (0x001);
    public static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    public static final String PUSH_CHANNEL_NAME = "直播之家";
    private boolean hasGotToken = false;
    //首次进入界面是否显示弹窗时候用的
    public static boolean IsShowPop = false;

    //公会的id  (第五底导点击跳转到报表有用)
    public static String Guild_id = "";
    //是选择的是月数据还是日数据
    public static String dateType = "";

    //我的页面 签约提示是否展示
    public static Boolean zhanShiShow = true;
    //    服务保密协议接口
    public static String URL = "";
    public static List<LivenessTypeEnum> livenessList = new ArrayList<LivenessTypeEnum>();
    public static boolean isLivenessRandom = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }

        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(true);
        AutoSizeConfig.getInstance().setCustomFragment(true);

        initUmeng();
        initJpush();
        //自动验证身份证
        initAccessTokenWithAkSk();
        CrashReport.initCrashReport(getApplicationContext(), "80290484bb", false);
        mWxApi = WXAPIFactory.createWXAPI(mAppContext, Constants.WECHAT_APP_ID);
        //        测试bugly
        //        CrashReport.testJavaCrash();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

//        //初始化LeakCanary(内存泄露检测神器)
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);

    }


    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                Log.d("Debug", "AK，SK方式获取token失败" + error.getMessage());
            }
        }, getApplicationContext(), "Hg4bsFShkOqoT5Lt6WXK5yH0", "OovXXZjTtb1Ois6NZvd7KCtjveS5QSV8");
    }


    /**
     * 以license文件方式初始化（智能识别身份证）
     */
    private void initAccessToken() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                ToastUtil.showShort("licence方式获取token失败" + error.getMessage());
            }
        }, getApplicationContext());
    }

    //友盟
    private void initUmeng() {
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "5af1061ef29d9872050001b2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                null);
        //        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5af1061ef29d9872050001b2");
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setScenarioType(mAppContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        PlatformConfig.setWeixin("wx030de8785dbafd41", "3625243beda966adeb75c964dfb5ce22");
    }

    /**
     * 推送
     */
    private void initJpush() {
        //        //极光推送
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        JPushInterface.resumePush(getApplicationContext());
//        CustomPushNotificationBuilder builder = new
//                CustomPushNotificationBuilder(GlobalApp.getAppContext(),
//                R.layout.customer_notitfication_layout,
//                R.id.icon,
//                R.id.title,
//                R.id.text);
//        // 指定最顶层状态栏小图标
//        builder.layoutIconDrawable = R.mipmap.logon_icon;
//        // 指定下拉状态栏时显示的通知图标
//        JPushInterface.setPushNotificationBuilder(2, builder);

    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }
}

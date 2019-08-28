package shangri.example.com.shangri.jpush;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.ui.activity.AnchorChectListActivity;
import shangri.example.com.shangri.ui.activity.AnchorDropListActivity;
import shangri.example.com.shangri.ui.activity.AnchorInvateActivity;
import shangri.example.com.shangri.ui.activity.CompamyInfoActivity;
import shangri.example.com.shangri.ui.activity.MainActivity;
import shangri.example.com.shangri.ui.activity.ManagerChectListActivity;
import shangri.example.com.shangri.ui.activity.MessagesActivityNew;
import shangri.example.com.shangri.ui.activity.MyGuildActivity;
import shangri.example.com.shangri.ui.activity.NewMessageActivity;
import shangri.example.com.shangri.ui.activity.UserBenefitsActivity;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.SystemUtil;
import shangri.example.com.shangri.util.TimeUtil;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;
import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";
    //==0 默认值，点击跳转内部消息列表
//==1 表示推送的是主播申请加入公会 推送点击跳转主播申请列表
//==2 表示推送的是管理员申请加入公会 推送点击跳转管理员申请列表
//==3 表示管理员添加主播 推送给会长和主播的消息 推送点击跳转组织架构
    public static int type = 0;
    /**
     * Notification 的ID
     */
    public static int notifyId = 101;

    /**
     * NotificationCompat 构造器
     */
    public static Notification.Builder mBuilder;

    //图片地址
    public static String image = "";
    public static String title = "";
    //web下面的内容
    public static String content = "";
    //web链接地址
    public static String url = "";

    //    极光推送收到信息  在自定义通知栏进行操作
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            for (String key : bundle.keySet()) {
                if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                    if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                        Logger.i(TAG, "This message has no Extra data");
                        continue;
                    }
                    try {
                        image = "";
                        title = "";
                        content = "";
                        url = "";
                        JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                        type = json.getInt("type");
                        Log.d("Debug", "传过来的type值我" + type);
                        if (type == 4) {
                            EventBus.getDefault().post(new BrowseEventBean());
                        }
                        //是否来自极光推送  状态为10  弹出小播推荐全局弹窗
                        if (type == 13) {
                            FourshowEventBean fourshowEventBean = new FourshowEventBean();
                            fourshowEventBean.setFromTuisongAllDialog(true);
                            EventBus.getDefault().post(fourshowEventBean);
                        }
//                        path = "http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/train/2018-06-07/5b18be8386f5e.jpg";
                        image = json.getString("image") + "";
                        title = json.getString("title") + "";
                        content = json.getString("content") + "";
                        url = json.getString("url") + "";
                        new Thread(saveFileRunnable).start();
                    } catch (JSONException e) {
                        Logger.e(TAG, "推送返回json解析出错");
                    }
                }
            }
            /**
             * 下面是极光推送状态栏操作
             */
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                Log.e("TAG", "==" + extra);
                try {
                    JSONObject json = new JSONObject(extra);
                    String type = json.getString("type");//获取添加的字段

                    String url = json.getString("url");
                    if (type.equals("5") && url.length() > 0) {
                        Intent intent_msg = new Intent(context, MessagesWebView.class);
                        intent_msg.putExtra("url", url);
                        context.startActivity(intent_msg);
                    } else if (type.equals("10")) {
                        //url代表跳转消息的类型  跳转的第几个
                        Intent intent_msg = new Intent(context, MessagesActivityNew.class);
                        intent_msg.putExtra("url", url);
                        context.startActivity(intent_msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


    private static Runnable saveFileRunnable = new Runnable() {
        public Bitmap myBitmap;

        @Override
        public void run() {
            try {
                Log.d("Debug", "image的长度为" + image.length());
                if (image.length() < 3) {
                    shwoNotify(myBitmap);
                } else {
                    byte[] data = getImage(image);
                    if (data != null) {
                        myBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
                    } else {
                        Toast.makeText(context, "Image error!", Toast.LENGTH_LONG).show();
                    }

                    if (myBitmap != null) {
                        Log.d("Debug", "下载成功" + myBitmap);
                        shwoNotify(bimapRound(myBitmap, 40));
                        myBitmap = null;
                    } else {
                        shwoNotify(myBitmap);
                        Log.d("Debug", "数据为空");
                    }
                }
//                saveFile(mBitmap, mFileName);
            } catch (IOException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /**
         * bitmap进行圆角设置
         * @param mBitmap
         * @param index
         * @return
         */
        private Bitmap bimapRound(Bitmap mBitmap, float index) {
            Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_4444);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            //设置矩形大小
            Rect rect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
            RectF rectf = new RectF(rect);

            // 相当于清屏
            canvas.drawARGB(0, 0, 0, 0);
            //画圆角
            canvas.drawRoundRect(rectf, index, index, paint);
            // 取两层绘制，显示上层
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            // 把原生的图片放到这个画布上，使之带有画布的效果
            canvas.drawBitmap(mBitmap, rect, rect, paint);
            return bitmap;

        }

        public void shwoNotify(Bitmap bitmap) {
            //先设定RemoteViews
            final RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.view_custom);
            if (bitmap != null && bitmap.toString().length() > 0) {
                view_custom.setViewVisibility(R.id.custom_icon1, View.VISIBLE);
                view_custom.setImageViewBitmap(R.id.custom_icon1, bitmap);
            } else {
                view_custom.setViewVisibility(R.id.custom_icon1, View.GONE);
            }

//结果为“0”是上午 结果为“1”是下午
            GregorianCalendar ca = new GregorianCalendar();
            int i = ca.get(GregorianCalendar.AM_PM);

            String nowTime = TimeUtil.getNowTime("HH:mm");
            if (i == 0) {
                view_custom.setTextViewText(R.id.tv_custom_time, "上午 " + nowTime);
            } else {
                view_custom.setTextViewText(R.id.tv_custom_time, "下午 " + nowTime);
            }

            view_custom.setImageViewResource(R.id.custom_icon, R.mipmap.logon_icon);
            view_custom.setTextViewText(R.id.tv_custom_title, "直播之家");
            if (title.length() < 3) {
                view_custom.setViewVisibility(R.id.tv_custom_content, View.GONE);
            } else {
                view_custom.setViewVisibility(R.id.tv_custom_content, View.VISIBLE);
                view_custom.setTextViewText(R.id.tv_custom_content, title + "");
            }
            //8.0版本添加背景颜色
            String systemVersion = SystemUtil.getSystemVersion();
            boolean b = SystemUtil.compareVersions(systemVersion, "7.9.9");
            if (b) {
                view_custom.setViewVisibility(R.id.tv1, View.VISIBLE);
            } else {
                view_custom.setViewVisibility(R.id.tv1, View.GONE);
            }

            if (content.length() < 3) {
                view_custom.setViewVisibility(R.id.tv_web_content, View.GONE);
            } else {
                view_custom.setViewVisibility(R.id.tv_web_content, View.VISIBLE);
                view_custom.setTextViewText(R.id.tv_web_content, content);
            }
            mBuilder = new Notification.Builder(GlobalApp.getAppContext());
            mBuilder.setContent(view_custom)
                    .setAutoCancel(true)
                    .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                    .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                    .setTicker("有新资讯")
                    .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                    .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                    .setSmallIcon(R.mipmap.logon_icon)
                    .setDefaults(Notification.DEFAULT_ALL); // Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
//        点击的意图  ACTION是跳转到Intent （mBuilder.setContentIntent(pendingIntent)，这句代码就是点击推送消息 跳转页面操作，如果点击不做任何操作，不添加此代码）
            switch (type) {
                case 0:
                    Intent resultIntent = new Intent(GlobalApp.getAppContext(), NewMessageActivity.class);
                    resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent);
                    break;
                case 1:
                    Intent resultIntent1 = new Intent(GlobalApp.getAppContext(), AnchorChectListActivity.class);
                    resultIntent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent1 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent1);
                    break;
                case 2:
                    Intent resultIntent2 = new Intent(GlobalApp.getAppContext(), ManagerChectListActivity.class);
                    resultIntent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent2 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent2);
                    break;
                case 3:
                    Intent resultIntent3 = new Intent(GlobalApp.getAppContext(), CompamyInfoActivity.class);
                    resultIntent3.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent3 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent3, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent3);
                    break;
                case 4:
                    Intent resultIntent4 = new Intent(GlobalApp.getAppContext(), MyGuildActivity.class);
                    resultIntent4.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent pendingIntent4 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent4, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent4);
                    break;
                case 5:
                    Intent resultIntent5 = new Intent(GlobalApp.getAppContext(), symbolWebView.class);
                    resultIntent5.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    resultIntent5.putExtra("url", url);
                    PendingIntent pendingIntent5 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent5, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent5);
                    break;
//                   2报表
//                跳转报表
                case 6:
                    Intent resultIntent6 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                    resultIntent6.putExtra("type", 0);
                    PendingIntent pendingIntent6 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent6, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent6);
//                  EventBus.getDefault().post(new FourshowEventBean(2));
                    break;
//                1管理
//                跳转管理（暂时去掉没有）
                case 7:
                    Intent resultIntent7 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                    resultIntent7.putExtra("type", 0);
                    PendingIntent pendingIntent7 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent7, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent7);
//                    EventBus.getDefault().post(new FourshowEventBean(1));
                    break;
//                    4是底导
//                跳转汇报
                case 8:
                    Intent resultIntent8 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                    resultIntent8.putExtra("type", 2);
                    PendingIntent pendingIntent8 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent8, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent8);
//                    EventBus.getDefault().post(new FourshowEventBean(4));
                    break;
//                主播投递界面
                case 9:
                    Intent resultIntent9 = new Intent(GlobalApp.getAppContext(), AnchorDropListActivity.class);
                    resultIntent9.putExtra("type", 2);
                    PendingIntent pendingIntent9 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent9, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent9);
//                    EventBus.getDefault().post(new FourshowEventBean(4));
                    break;
//                //跳转消息界面 （极光推送做了操作）
                case 10:
//                    Intent resultIntent10 = new Intent(GlobalApp.getAppContext(), MessagesActivityNew.class);
//                    resultIntent10.putExtra("url", url);
//                    PendingIntent pendingIntent10 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent10, PendingIntent.FLAG_UPDATE_CURRENT);
//                    mBuilder.setContentIntent(pendingIntent10);
                    break;
                //跳转主播邀请界面(体现成功以后的推送  现在跳转到赚钱界面)
                case 11:
                    Intent resultIntent11 = new Intent(GlobalApp.getAppContext(), MainActivity.class);
                    resultIntent11.putExtra("type", 2);
                    PendingIntent pendingIntent11 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent11, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent11);
                    break;
                //跳转越聊消息界面
                case 12:
                    Intent resultIntent12 = new Intent(GlobalApp.getAppContext(), MessagesActivityNew.class);
                    PendingIntent pendingIntent12 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent12, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent12);
                    break;
                //小波推荐 全局弹窗
                case 13:
                    Intent resultIntent13 = new Intent(GlobalApp.getAppContext(), MessagesActivityNew.class);
                    resultIntent13.putExtra("url", url);
                    PendingIntent pendingIntent13 = PendingIntent.getActivity(GlobalApp.getAppContext(), 0, resultIntent13, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent13);
                    break;
            }
            mBuilder.build().contentView = view_custom;
            Log.d("Debug", "到达这里");
//            //设置声音
//            Uri uri = Uri.parse("file:///mnt/sdcard/cat.mp3");
//            mBuilder.setSound(uri);
            mBuilder.build().flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mBuilder.setChannelId(GlobalApp.PUSH_CHANNEL_ID);
            }
            GlobalApp.mNotificationManager.notify(notifyId++, mBuilder.build());
        }

    };

    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性: 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public static PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(GlobalApp.getAppContext(), 1, new Intent(), flags);
        return pendingIntent;
    }

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return byte[]
     * @throws Exception
     */
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    /**
     * Get data from stream
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


}

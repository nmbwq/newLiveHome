package shangri.example.com.shangri.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.ShareJubao;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.ToastUtil;

import static com.umeng.analytics.pro.s.a.b.b;
import static com.umeng.socialize.utils.DeviceConfig.context;
import static shangri.example.com.shangri.jpush.MyReceiver.getImage;
import static shangri.example.com.shangri.jpush.MyReceiver.image;


/**
 * 创    建:  lt  2018/1/8--11:51
 * 作    用:  对话框工具类
 * 注意事项:
 */

public class ShareCommontDialog {

    //链接地址
    static String httpUrls;
    //图片地址
    static String Imageicons;
    //标题
    static String titles;
    //1 微信分享  2分享到朋友圈
    static int type;

    /**
     * 咨询分享弹窗 微信原生分享
     *
     * @return
     */
    public static AlertDialog ShareDialog(Context context, final String httpUrl, final int Pagetype, final String Imageicon, final String title, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.share_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        LinearLayout ll1 = view.findViewById(R.id.ll1);
        LinearLayout ll5 = view.findViewById(R.id.ll5);
        LinearLayout ll3 = view.findViewById(R.id.ll3);
        LinearLayout ll4 = view.findViewById(R.id.ll4);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(context, -1), -1);
        dialog.setCanceledOnTouchOutside(false);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "微信分享");
                httpUrls = httpUrl;
                Imageicons = Imageicon;
                titles = title;
                type = 1;
                dialog.dismiss();
                new Thread(saveFileRunnable).start();
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "朋友圈分享");
                httpUrls = httpUrl;
                Imageicons = Imageicon;
                titles = title;
                type = 2;
                dialog.dismiss();
                new Thread(saveFileRunnable).start();
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpUrl.length() == 0) {
                    ToastUtil.showShort("链接地址为空");
                } else {
                    ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    clip.getText(); // 粘贴
                    clip.setText(httpUrl + ""); // 复制
                    ToastUtil.showShort("链接已复制成功");
                }
                dialog.dismiss();
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                EventBus.getDefault().post(new ShareJubao(Pagetype));
            }
        });
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    /**
     * 构建一个唯一标志
     *
     * @param type 分享的类型分字符串
     * @return 返回唯一字符串
     */
    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    private static Runnable saveFileRunnable;

    static {
        saveFileRunnable = new Runnable() {
            public Bitmap myBitmap;

            @Override
            public void run() {
                try {
                    byte[] data = getImage(Imageicons);
                    if (data != null) {
                        myBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
                    } else {
                        Toast.makeText(context, "Image error!", Toast.LENGTH_LONG).show();
                    }
                    if (myBitmap != null) {
                        Log.d("Debug", "下载成功" + myBitmap);
                        if (type == 1) {
                            shareWebPage(httpUrls, type, myBitmap, titles, "直播之家-做专业的直播行业招聘平台");
                        } else {
                            shareWebPage(httpUrls, type, myBitmap, titles, "");
                        }
                    } else {
                        Log.d("Debug", "分享图片下载失败");
                    }

                } catch (IOException e) {

                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        };
    }


    /**
     * 微信分享
     *
     * @param httpUrl
     * @param type
     * @param icon
     * @param title
     * @param description
     */

    public static void shareWebPage(String httpUrl, int type, Bitmap icon, String title, String description) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = httpUrl;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        msg.setThumbImage(bmpToByteArray(icon, 32000));
        int bitmapSize = getBitmapSize(bmpToByteArray(icon, 32000));
        Log.d("Debug", "bitmapSize的大小为" + bitmapSize);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        GlobalApp.mWxApi.sendReq(req);
    }


    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    public static Bitmap bmpToByteArray(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, output);
        int options = 80;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return getBitmapFromByte(output.toByteArray());
    }


    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

}
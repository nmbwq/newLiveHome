package shangri.example.com.shangri.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.ShareJubao;
import shangri.example.com.shangri.ui.activity.BusinesIdentsetail;
import shangri.example.com.shangri.ui.activity.CompactWebView.AddCompactlWebviewOne;
import shangri.example.com.shangri.ui.activity.IdentIdCardOneetail;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.ToastUtil;

import static com.umeng.socialize.utils.DeviceConfig.context;
import static shangri.example.com.shangri.jpush.MyReceiver.getImage;
import static shangri.example.com.shangri.jpush.MyReceiver.image;
import static shangri.example.com.shangri.util.CommontDialog.dip2px;


/**
 * 创    建:  lt  2018/1/8--11:51
 * 作    用:  对话框工具类
 * 注意事项:
 */

public class CommontDialog {

    /**
     * 详情弹框显示
     *
     * @return
     */
    public static AlertDialog homeDialog1(Context context, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(context, 300), -1);
        dialog.setCanceledOnTouchOutside(false);
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
         * 将dip或dp值转换为px值，保证尺寸大小不变
         *
         * @param dipValue dp尺寸
         * @return
         */
        public static int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }

        /**
         * @param context
         * @param
         */
        public static AlertDialog showHuiBaoDialog(Context context, String title, String content) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_huibao, null);
            TextView tvTitle = view.findViewById(R.id.tv_huibao_title);
            TextView tvContent = view.findViewById(R.id.tv_huibao_content);
            tvTitle.setText(title + "");
            tvContent.setText(content + "");
            //点击事件
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setView(view);
            dialog.show();
            return dialog;
        }

        /**
         * @param context
         * @param stute   时间状态
         * @return
         */
        public static AlertDialog showTeamManegerDialog(Context context, int stute, int id) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_huibao, null);
            TextView tvTitle = view.findViewById(R.id.tv_huibao_title);
            TextView tvContent = view.findViewById(R.id.tv_huibao_content);
            switch (stute) {
                case 0://昨日
                    switch (id) {
                        case R.id.iv_wenti_tutor:
                            tvTitle.setText("该管理员昨日一天对其管理的所有主播发起的辅导总次数。");
                            tvContent.setText("注意：管理员对一个主播发起2次辅导，这里记为2次。");
                            break;
                        case R.id.iv_yi_income:
                            tvTitle.setText("公会在该直播平台，昨日一天的礼物总收益");
                            tvContent.setText("");
                            break;
                        case R.id.iv_wenti_kaibo:
                            tvTitle.setText("该管理员昨日一天辅导的主播总人数");
                            tvContent.setText("注意：管理员对一个主播发起2次辅导，这里记为1个主播");
                            break;
                    }
                    break;
                case 1://本月
                    switch (id) {
                        case R.id.iv_wenti_tutor:
                            tvTitle.setText("该管理员本月，对其管理的所有主播发起的辅导总次数");
                            tvContent.setText("注意：管理员对一个主播发起2次辅导，这里记为2次。");
                            break;
                        case R.id.iv_yi_income:
                            tvTitle.setText("公会在该直播平台，本月的礼物总收益");
                            tvContent.setText("注意：由于本月不是一个整月，所以这里的数据会根据开播情况而变化");
                            break;
                        case R.id.iv_wenti_kaibo:
                            tvTitle.setText("该管理员本月，辅导的主播总人数。");
                            tvContent.setText("注意：管理员对一个主播发起2次辅导，这里记为1个主播。");
                            break;
                    }
                    break;
            }
            //点击事件
            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setView(view);
            dialog.show();
            return dialog;
        }


        /**
         * 分享图片到朋友圈
         *
         * @param context
         * @return
         */
        public static AlertDialog showShareDialog(final Activity context, final Bitmap b) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_share_pic, null);
            ImageView ivPic = view.findViewById(R.id.iv_pic);
            ivPic.setImageBitmap(b);
            //点击事件
            AlertDialog dialog = new AlertDialog.Builder(context, R.style.AlertDialog).create();
            dialog.setView(view);
            dialog.show();
//        /*
//         * 设置dialog宽度全屏
//         */
            WindowManager m = context.getWindowManager();
            Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
            android.view.WindowManager.LayoutParams params = dialog.getWindow().getAttributes();  //获取对话框当前的参数值、
            params.width = d.getWidth();    //宽度设置全屏宽度
            params.height = d.getHeight();
            dialog.getWindow().setAttributes(params);     //设置生效;
            view.findViewById(R.id.rl_share_wx).setOnClickListener(new View.OnClickListener() {//分享到微信
                @Override
                public void onClick(View v) {
                    UMImage image = new UMImage(context, b);//bitmap文件
                    new ShareAction(context)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                }
            });
            view.findViewById(R.id.rl_share_wx_circle).setOnClickListener(new View.OnClickListener() {//分享到朋友圈
                @Override
                public void onClick(View v) {
                    UMImage image = new UMImage(context, b);//bitmap文件
                    new ShareAction(context)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(image)//分享内容
                            .setCallback(shareListener)//回调监听器
                            .share();
                }
            });
            return dialog;
        }

        private static UMShareListener shareListener = new UMShareListener() {
            /**
             * @descrption 分享开始的回调
             * @param platform 平台类型
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @descrption 分享成功的回调
             * @param platform 平台类型
             */
            @Override
            public void onResult(SHARE_MEDIA platform) {
            }

            /**
             * @descrption 分享失败的回调
             * @param platform 平台类型
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {

            }

            /**
             * @descrption 分享取消的回调
             * @param platform 平台类型
             */
            @Override
            public void onCancel(SHARE_MEDIA platform) {

            }
        };


}
package shangri.example.com.shangri.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.DpPxUtils;
import shangri.example.com.shangri.util.ViewChangeImageUtils;

import static com.umeng.analytics.pro.s.a.b.b;
import static shangri.example.com.shangri.util.ViewChangeImageUtils.loadBitmapFromView;

public class TextLayoutChangeImageView extends AppCompatActivity {


    @BindView(R.id.rl_rl)
    RelativeLayout rlRl;
    @BindView(R.id.showimge)
    ImageView showimge;
    @BindView(R.id.im_image)
    ImageView im_image;

    @BindView(R.id.tv_number)
    TextView tv_number;


    private WebView webView;
    private String url = "http://www.zhibohome.net/HostResume?code=61";
    private String Tag = "MQL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changimage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Glide.with(this)
                .load("http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/weixintu.png")
                .crossFade()
                .into(im_image);

    }

    AlertDialog dialog;

    @OnClick(R.id.showimge)
    public void onViewClicked() {
        tv_number.setText("邀请码：123456789");
        showimge.setVisibility(View.VISIBLE);
        Bitmap bitmap = loadBitmapFromView(rlRl);
        showimge.setImageBitmap(bitmap);
        dialog = YouMengShareDialog(bitmap, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 友盟分享
     *
     * @return
     */
    public AlertDialog YouMengShareDialog(final Bitmap bitmap, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(TextLayoutChangeImageView.this).inflate(R.layout.share_dialog_layout, null);
        ImageView im_cancel = view.findViewById(R.id.im_dismiss);
        ImageView im1 = view.findViewById(R.id.im1);
        ImageView im5 = view.findViewById(R.id.im5);
        ImageView im3 = view.findViewById(R.id.im3);
        ImageView im4 = view.findViewById(R.id.im4);
        im3.setVisibility(View.GONE);
        im4.setVisibility(View.GONE);
        im_cancel.setOnClickListener(cancelListener);
        //点击事件
        final AlertDialog dialog = new AlertDialog.Builder(TextLayoutChangeImageView.this).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(DpPxUtils.dip2px(TextLayoutChangeImageView.this, -1), -1);
        dialog.setCanceledOnTouchOutside(false);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMImage image = new UMImage(TextLayoutChangeImageView.this, bitmap);//bitmap文件
                new ShareAction(TextLayoutChangeImageView.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(image)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMImage image = new UMImage(TextLayoutChangeImageView.this, bitmap);//bitmap文件
                new ShareAction(TextLayoutChangeImageView.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(image)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
            }
        });
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) TextLayoutChangeImageView.this
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

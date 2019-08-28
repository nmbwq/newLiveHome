package shangri.example.com.shangri.ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;


/**
 * 选择相册
 * Created by chengaofu on 2017/7/1.
 */

public class SelectPhotoPopopWindow1 extends BasePopupWindow {

    private SelectPhotoListener mSelectPhotoListener;
    public String text1 = "";
    public String text2 = "";

    public SelectPhotoPopopWindow1(Context context, SelectPhotoListener selectPhotoListener, String bottomContent) {
        super(context, bottomContent);
        mSelectPhotoListener = selectPhotoListener;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        setOutsideTouchable(true);
        //设置可以点击
        setTouchable(true);
        //进入退出的动画
        setAnimationStyle(R.style.mypopwindow_anim_style);
        if (mContentView != null) {
            //从底部显示
            showAtLocation(mContentView, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.popupwindow_select_photo1;
    }

    @Override
    protected void confirmClick() {
        if (mContentView == null) return;
        TextView takePhoto = mContentView.findViewById(R.id.take_photo);
        TextView selectFromAlbum = mContentView.findViewById(R.id.select_from_album);
        TextView bottomText = mContentView.findViewById(R.id.save_photo);

        bottomText.setText(mBottomContent);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mSelectPhotoListener.takePhoto();
            }
        });

        selectFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mSelectPhotoListener.selectFromAlbum();
            }
        });

        bottomText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mSelectPhotoListener.savePhoto();
            }
        });
    }
}

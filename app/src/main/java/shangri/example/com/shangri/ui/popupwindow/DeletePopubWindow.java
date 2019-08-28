package shangri.example.com.shangri.ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shangri.example.com.shangri.R;

/**
 * Description:删除照片弹框
 * Data：2018/11/16-16:55
 * Author: lin
 */
public class DeletePopubWindow extends BasePopupWindow {
    public DeletePopubWindow(Context context, String bottomContent,DeleteFace face) {
        super(context, bottomContent);
        this.face = face;
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
        return R.layout.delete_photo_item;
    }

    @Override
    protected void confirmClick() {
        if (mContentView == null) return;
        final TextView delete = mContentView.findViewById(R.id.delete);
        TextView cancel = mContentView.findViewById(R.id.cancel);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                face.delete();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    DeleteFace face;
    public interface DeleteFace{
        void delete();
    }

}

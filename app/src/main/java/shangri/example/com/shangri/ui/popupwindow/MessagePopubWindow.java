package shangri.example.com.shangri.ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shangri.example.com.shangri.R;

/**
 * Description:选择支付方式
 * Data：2018/11/16-16:55
 * Author: lin
 */
public class MessagePopubWindow extends BasePopupWindow {
    public MessagePopubWindow(Context context, String bottomContent, String message) {
        super(context, bottomContent);
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
//        if (mContentView != null) {
//            //从底部显示
//            showAtLocation(mContentView, Gravity.CENTER, 0, 0);
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_message_pop;
    }

    TextView title1;
    TextView message1;

    @Override
    protected void confirmClick() {
        if (mContentView == null) return;
        title1 = mContentView.findViewById(R.id.title);
        message1 = mContentView.findViewById(R.id.message);
    }

    public void show(String title, String message) {
        if (mContentView != null) {
            //从底部显示
            title1.setText(title + "");
            message1.setText(message + "");
            showAtLocation(mContentView, Gravity.CENTER, 0, 0);
        }
    }

}

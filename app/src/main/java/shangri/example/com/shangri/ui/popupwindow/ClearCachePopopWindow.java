package shangri.example.com.shangri.ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.ui.listener.ClearCacheListener;


/**
 * Created by pc on 2017/7/1.
 */

public class ClearCachePopopWindow extends BasePopupWindow {


    private ClearCacheListener mClearCacheListener;
    public ClearCachePopopWindow(Context context, ClearCacheListener clearCacheListener) {
        super(context,"");
        mClearCacheListener = clearCacheListener;
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
        if(mContentView != null){
            //从底部显示
            showAtLocation(mContentView, Gravity.BOTTOM, 0, 0);
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.popupwindow_clear_cache;
    }

    @Override
    protected void confirmClick() {
        if(mContentView == null) return;
        TextView clearCacheOk = mContentView.findViewById(R.id.clear_cache_ok);
        TextView clearCacheCancel = mContentView.findViewById(R.id.clear_cache_cancel);
        clearCacheOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mClearCacheListener.clearCache();
            }
        });
        clearCacheCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}

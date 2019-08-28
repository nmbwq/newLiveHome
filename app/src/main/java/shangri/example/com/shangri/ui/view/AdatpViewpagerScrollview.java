package shangri.example.com.shangri.ui.view;

/**
 * Created by zong on 2017/4/12.
 */

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 屏蔽 滑动事件
 * Created by fc on 2015/7/16.
 */
public class AdatpViewpagerScrollview extends ScrollView {
    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;

    public AdatpViewpagerScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public AdatpViewpagerScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdatpViewpagerScrollview(Context context) {
        super(context);
    }

    /**
     * 在该方法中进行判断
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0.0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);

                if(xDistance > yDistance)
                    return false;

                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}


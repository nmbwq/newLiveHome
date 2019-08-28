package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2018/8/3.
 */
public class SufaceControll  implements GestureDetector.OnGestureListener {
    int FLIP_DISTANCE = 50;


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float v, float v1) {
        if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
            ToastUtil.showShort("左下滑动");
            return true;
        }
        if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
            ToastUtil.showShort("左上滑动");
            return true;
        }
        if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
            ToastUtil.showShort("右下滑动");
            return true;
        }
        if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
            ToastUtil.showShort("右上滑动");
            return true;
        }
        if (e1.getX() - e2.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
            ToastUtil.showShort("向左滑动");
            return true;
        }
        if (e2.getX() - e1.getX() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
            ToastUtil.showShort("向右滑动");
            return true;
        }
        if (e1.getY() - e2.getY() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5) {
            ToastUtil.showShort("向上滑动");
            return true;
        }
        if (e2.getY() - e1.getY() > FLIP_DISTANCE && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5) {
            ToastUtil.showShort("向上滑动");
            return true;
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
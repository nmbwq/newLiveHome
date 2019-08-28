package shangri.example.com.shangri.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

/**
 * Description: 心跳动画
 * Data：2018/11/15-13:51
 * Author: lin
 */
public class AnimatorUtils {

    /**
     * 点击收藏
     * @param imageView
     */
    public static void startAnimation(ImageView imageView){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView,"scaleX",1.0f,0.5f);
        anim1.setRepeatCount(1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,0.5f);
        anim2.setRepeatCount(1);
        AnimatorSet set = new AnimatorSet();
        set.play(anim1).with(anim2);
        set.setDuration(1000);
        set.start();
    }

    /**
     * 取消收藏
     * @param imageView
     */
    public static void cancelAnimation(ImageView imageView){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView,"scaleX",1.0f,0.5f);
        anim1.setRepeatCount(1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,0.5f);
        anim2.setRepeatCount(1);
        AnimatorSet set = new AnimatorSet();
        set.play(anim1).with(anim2);
        set.setDuration(1000);
        set.start();
    }
}

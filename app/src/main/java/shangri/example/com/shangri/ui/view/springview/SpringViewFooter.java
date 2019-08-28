package shangri.example.com.shangri.ui.view.springview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import shangri.example.com.shangri.R;

/**
 * 仿照美团
 * Created by ASUS on 2017/6/23.
 */

public class SpringViewFooter extends BaseFooter {

    private AnimationDrawable animationLoading;
    private Animation mCircleAnim;
    private ImageView footer_img;
    private int[] loadingAnimSrcs = new int[]{R.mipmap.ic_loading7};

    public SpringViewFooter(Context context){
        this(context,null);
    }
    public SpringViewFooter(Context context,int[] loadingAnimSrcs){
        if (loadingAnimSrcs!=null) this.loadingAnimSrcs = loadingAnimSrcs;
        animationLoading = new AnimationDrawable();
        for (int src: this.loadingAnimSrcs) {
            animationLoading.addFrame(ContextCompat.getDrawable(context, src),150);
            animationLoading.setOneShot(false);
        }
        mCircleAnim = AnimationUtils.loadAnimation(context, R.anim.anim_round_rotate);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.widget_springview_footer, viewGroup, true);
        footer_img = view.findViewById(R.id.springview_footer_img);
        if (animationLoading!=null)
            footer_img.setImageDrawable(animationLoading);
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
        animationLoading.stop();
        if (animationLoading!=null && animationLoading.getNumberOfFrames()>0)
            footer_img.setImageDrawable(animationLoading.getFrame(0));
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
    }

    @Override
    public void onStartAnim() {
        if (animationLoading!=null)
            footer_img.setImageDrawable(animationLoading);
        animationLoading.start();
        startAnim();
    }

    @Override
    public void onFinishAnim() {
        animationLoading.stop();
        if (animationLoading!=null && animationLoading.getNumberOfFrames()>0)
            footer_img.setImageDrawable(animationLoading.getFrame(0));
        stopAnim();
    }

    private void startAnim(){
       if(mCircleAnim != null){
           LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
           mCircleAnim.setInterpolator(interpolator);
           if (mCircleAnim != null) {
               footer_img.startAnimation(mCircleAnim);  //开始动画
           }
       }
    }

    private void stopAnim(){
        if(mCircleAnim != null){
            mCircleAnim.cancel();
        }
    }
}

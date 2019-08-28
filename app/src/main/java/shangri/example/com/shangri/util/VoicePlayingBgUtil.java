package shangri.example.com.shangri.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import shangri.example.com.shangri.R;

/**
 * 键盘收起 与展开
 * Created by chengaofu on 2017/7/2.
 */

public class VoicePlayingBgUtil {


    //语音动画控制器
    static Timer mTimer = null;
    //语音动画控制任务
    static TimerTask mTimerTask = null;
    //记录语音动画图片
    static int index = 1;
    static AudioAnimationHandler audioAnimationHandler = null;

    public static void playAudioAnimation(final ImageView imageView) {
        //定时器检查播放状态
        stopTimer();
        mTimer = new Timer();
        //将要关闭的语音图片归位
        if (audioAnimationHandler != null) {
            Message msg = new Message();
            msg.what = 3;
            audioAnimationHandler.sendMessage(msg);
        }

        audioAnimationHandler = new AudioAnimationHandler(imageView);
        mTimerTask = new TimerTask() {
            public boolean hasPlayed = false;

            @Override
            public void run() {
                hasPlayed = true;
                index = (index + 1) % 3;
                Message msg = new Message();
                msg.what = index;
                audioAnimationHandler.sendMessage(msg);
            }
        };
        //调用频率为500毫秒一次
        mTimer.schedule(mTimerTask, 0, 500);
    }

    static class AudioAnimationHandler extends Handler {
        ImageView imageView;
        //判断是左对话框还是右对话框
        boolean isleft;

        public AudioAnimationHandler(ImageView imageView) {
            this.imageView = imageView;
            //判断是左对话框还是右对话框 我这里是在前面设置ScaleType来表示的
            isleft = imageView.getScaleType() == ImageView.ScaleType.FIT_START;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //根据msg.what来替换图片，达到动画效果
            switch (msg.what) {
                case 0:
                    imageView.setImageResource(isleft ? R.mipmap.new_yuyin_1 : R.mipmap.new_yuyin_1);
                    break;
                case 1:
                    imageView.setImageResource(isleft ? R.mipmap.new_yuyin_2 : R.mipmap.new_yuyin_2);
                    break;
                case 2:
                    imageView.setImageResource(isleft ? R.mipmap.new_yuyin_wanzheng : R.mipmap.new_yuyin_wanzheng);
                    break;
                default:
                    imageView.setImageResource(isleft ? R.mipmap.new_yuyin_wanzheng : R.mipmap.new_yuyin_wanzheng);
                    break;
            }
        }

    }

    /**
     * 停止
     */
    public static void stopTimer() {

        if (audioAnimationHandler != null) {
            Message msg = new Message();
            msg.what = 3;
            audioAnimationHandler.sendMessage(msg);
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }

    }

}


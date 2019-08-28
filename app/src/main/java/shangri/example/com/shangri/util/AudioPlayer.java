package shangri.example.com.shangri.util;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.GlobalApp;

import java.io.IOException;

/**
 * Created by chengaofu on 2017/7/3.
 */

public class AudioPlayer {

    private ImageView mStartImage;
//    private ImageView mPauseImage;
    public SeekBar mSeekBar;
    private TextView mStartTimeText;
    private TextView mEndTimeText;
    private MediaPlayer mMediaPlayer;
//    private ImageButton mAudioFloatStart;
//    private ImageButton mAudioFloatPause;
//    private AnimationDrawable mAnimationDrawable;
//    private AnimationDrawable mFloatDrawable;
    private boolean isPause; //是否停止
//    private Animation mCircleAnim;

    public AudioPlayer(ImageView startImage,SeekBar seekBar, TextView startTimeText, TextView endTimeText) {
        mStartImage = startImage;
//        mPauseImage = pauseImage;
        mSeekBar = seekBar;
        mStartTimeText = startTimeText;
        mEndTimeText = endTimeText;
//        mAudioFloatStart = audioFloatStart;
//        mAudioFloatPause = audioFloatPause;
//        mAnimationDrawable = (AnimationDrawable) mStartImage.getDrawable();
//        mFloatDrawable = (AnimationDrawable)mStartImage.getDrawable();
        mSeekBar.setEnabled(true);
//        mCircleAnim = AnimationUtils.loadAnimation(context, R.anim.anim_round_rotate_90);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMediaPlayer.seekTo(mSeekBar.getProgress());// 歌曲找位置
                mStartTimeText.setText(formatTime(mMediaPlayer.getCurrentPosition()));
            }
        });


    }

    public void initMediaPlayer(final String url, String id) {
        mMediaPlayer = GlobalApp.getMediaPlayer();
        try {
            if (mMediaPlayer.isPlaying() && GlobalApp.id.equals(id)) {
                isPause = false;
                startAnim();
                new Thread(new SeekBarThread()).start();
            } else if (mMediaPlayer.isPlaying()) {
            } else {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(url);
                mMediaPlayer.prepare();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStartTimeText.setText("00:00");
        mEndTimeText.setText(formatTime(mMediaPlayer.getDuration()));
        mSeekBar.setMax(mMediaPlayer.getDuration());
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPause = true;
                if(mOnScroller != null){
                    mOnScroller.onPlayComplete();
                }
                stopAnim();
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public static String formatTime(int duration) {
        int musicTime = Math
                .round(duration / 1000);
        String showTime = String.format("%s%02d:%02d",
                "", musicTime / 60, musicTime % 60);
        return showTime;
    }

    public void startPlay(String url) {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            isPause = false;
            startAnim();
            mMediaPlayer.start();
            new Thread(new SeekBarThread()).start();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (mMediaPlayer != null) {
                mStartTimeText.setText(formatTime(mMediaPlayer.getCurrentPosition()));

                // 将SeekBar位置设置到当前播放位置
                mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            }
        }
    };


    private class SeekBarThread implements Runnable {

        @Override
        public void run() {
            while (mMediaPlayer != null && isPause == false) {
                mHandler.sendEmptyMessage(0x11);
                try {
                    // 每100毫秒更新一次位置
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pausePlay() {
        mMediaPlayer.pause();
        isPause = true;
        stopAnim();
    }


    public void stopPlay() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
            stopAnim();
        }

    }

    //开始动画
    private void startAnim() {
       /* if (mCircleAnim != null) {
            mCircleAnim.start();
        }*/
//        if (mAnimationDrawable != null) {
//            mAnimationDrawable.start();
//        }
//        if (mFloatDrawable != null) {
//            mFloatDrawable.start();
//        }
    }

    //停止动画
    private void stopAnim() {
       /* if (mCircleAnim != null) {
            mCircleAnim.cancel();
        }*/

//        if (mAnimationDrawable != null) {
//            mAnimationDrawable.stop();
//        }
//        if (mFloatDrawable != null) {
//            mFloatDrawable.stop();
//        }


    }

    public interface OnScroller{
        void onPlayComplete();
    }

    private OnScroller mOnScroller;

    public void setOnScroller(OnScroller onScroller) {
        this.mOnScroller = onScroller;
    }
}

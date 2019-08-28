package shangri.example.com.shangri.ui.activity;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;

import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.eventmessage.MoreArticlesEvent;
import shangri.example.com.shangri.eventmessage.NewsEvent;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.event.RegisterCollect;
import shangri.example.com.shangri.model.bean.response.NewsDetailInfoBean;
import shangri.example.com.shangri.model.bean.response.PraiseInfoBean;
import shangri.example.com.shangri.presenter.NewsDetailPresenter;
import shangri.example.com.shangri.presenter.view.NewsDetailView;
import shangri.example.com.shangri.ui.webview.ActivityWebView1;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.AndroidInterface.VideoFace;
import shangri.example.com.shangri.util.AudioPlayer;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.util.PointUtils;


/**
 * 音频资讯详情
 * Created by chengaofu on 2017/6/29.
 */

public class AudioPlayActivity extends BaseActivity<NewsDetailView, NewsDetailPresenter> implements NewsDetailView, VideoFace {
    @BindView(R.id.iv_audio)
    ImageView needleImage;

    @BindView(R.id.iv_dian)
    ImageView iv_dian;

    @BindView(R.id.iv_collect)
    ImageView iv_collect;


    @BindView(R.id.iv_start)
    ImageView iv_start;
    @BindView(R.id.audio_seekbar)
    SeekBar mSeekBar;
    private AudioPlayer mAudioPlayer; //音频

    @BindView(R.id.tv_start_time)
    TextView tv_start_time;

    @BindView(R.id.tv_end_time)
    TextView tv_end_time;

    @BindView(R.id.tv_title)
    TextView tv_title;

    private ObjectAnimator discObjectAnimator, neddleObjectAnimator;
    public static boolean isplay = false;
    private int mPosition;
    private String imageurl, audiourl, title, html;
    int register;
    private String pageid;
    private String id;
    private String type;  // 1 咨询 2 今日头条
    private int register_collect;  //  //0为未收藏 1为已收藏

    private String create_time = "";


    //分享的区别
    String isFormType = "article";
    private int position;
    int register_good = 0;
    private boolean isMore;

    protected void initViewsAndEvents() {
//            bean = (BannerHomeBean.ArticlesBean.DataBean) getIntent().getSerializableExtra("bean");
        imageurl = getIntent().getStringExtra("imageurl");
        audiourl = getIntent().getStringExtra("audiourl");

        Log.d("Debug", "传过来的图片地址" + imageurl + "和音频地址" + audiourl);
        html = getIntent().getStringExtra("html");
        create_time = getIntent().getStringExtra("create_time");
        Log.d("Debug", "传过来的开始时间为" + create_time);
        title = getIntent().getStringExtra("title");
        register = getIntent().getIntExtra("register", -1);
        mPosition = getIntent().getIntExtra("position", -1);
        pageid = getIntent().getStringExtra("pageid");
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        position = getIntent().getIntExtra("position", 0);
        register_good = getIntent().getIntExtra("register_good", 0);
        isFormType = getIntent().getStringExtra("isFormType") + "";
        isMore = getIntent().getBooleanExtra("isMore", false);
        register_collect = getIntent().getIntExtra("register_collect", 0);
        if (mAudioPlayer == null) {
        mAudioPlayer = new AudioPlayer(iv_start, mSeekBar,
                tv_start_time, tv_end_time);
    }
        Glide.with(this).load(imageurl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                setAudioAnimator(resource);
            }
        });
        setMipmapImage();
        setCollectImage();
        loadData();
        tv_title.setText(title);
        onBrowseEvent();
        mAudioPlayer.initMediaPlayer(audiourl, id);
        CompanyInterfaceUtils.setVideoFace(this);
    }

    private void onBrowseEvent() {
        BrowseEventBean browseEventBean = new BrowseEventBean();
        if (pageid.equals("ToutiaoSearchActivity")) {
            browseEventBean.setType(Constant.SearchActivity);
        } else if (pageid.equals("ConsultationFragment") || pageid.equals("bannerDetail")) {
            browseEventBean.setType(Constant.ConsultationFragment);
        }
        browseEventBean.setBrowsePosition(mPosition);
        BaseEvent<PraiseEventBean> baseEvent;
     /*   if (type.equals("1")) {
            baseEvent = new BaseEvent(Constant.TYPE_BROWSE, browseEventBean);
        } else {
            baseEvent = new BaseEvent(Constant.TYPE_HEAD_BROWSE, browseEventBean);
        }
        EventBus.getDefault().postSticky(baseEvent);*/  //发送通知
    }

    //每首音乐总时间 秒
    int sumTime;

    private void loadData() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mAudioPlayer.getMediaPlayer().seekTo(progress);
                }
                if (tv_end_time != null) {
                    String[] Sumbersplit = tv_end_time.getText().toString().split(":");
                    sumTime = Integer.parseInt(Sumbersplit[0]) * 60 + Integer.parseInt(Sumbersplit[1]);
                }
                String nowTime = AudioPlayer.formatTime(mAudioPlayer.getMediaPlayer().getCurrentPosition());
                String[] nowsplit1 = nowTime.split(":");
                int currentTime = Integer.parseInt(nowsplit1[0]) * 60 + Integer.parseInt(nowsplit1[1]);
                EventBus.getDefault().post(new FourshowEventBean(true, imageurl, title, create_time, sumTime + "", currentTime + ""));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mAudioPlayer.getMediaPlayer().seekTo(mSeekBar.getProgress());// 歌曲找位置
                tv_start_time.setText(AudioPlayer.formatTime(mAudioPlayer.getMediaPlayer().getCurrentPosition()));
            }
        });
    }


    //
    @OnClick({R.id.iv_collect, R.id.iv_start, R.id.iv_to_html, R.id.setting_back, R.id.iv_dian})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击收藏
            case R.id.iv_collect:

                Log.d("Debug", "点击收藏操作");
                if (register_collect == 0) { //未收藏
                    iv_collect.setImageResource(R.mipmap.xiangqing_shoucang2);
                    register_collect = 1;
                } else if (register_collect == 1) { //已收藏
                    iv_collect.setImageResource(R.mipmap.xiangqing_shoucang1);
                    register_collect = 0;
                }
                RegisterCollect collectEventBean = new RegisterCollect();
                if (pageid.equals("ToutiaoSearchActivity")) {
                    collectEventBean.setType(Constant.SearchActivity);
                } else if (pageid.equals("ConsultationFragment") || pageid.equals("bannerDetail")) {
                    collectEventBean.setType(Constant.ConsultationFragment);
                }
                collectEventBean.setCollectPosition(mPosition);
                if (type.equals("1")) {
                    if (register_collect == 0) {
                        //点赞操作
                        Log.d("Debug", "到达这里的");
                        mPresenter.collect(id);
                    } else {
                        mPresenter.deleteCollect(id);
                    }
                } else if (type.equals("2")) {
                    if (register_collect == 0) {
                        //点赞操作
                        mPresenter.requestCollect(id);
                    } else {
                        mPresenter.requestCancelCollect(id);
                    }
                } else if (type.equals("3")) {
                    if (register_collect == 0) {
                        //点赞操作
                        Log.d("Debug", "到达这里的");
                        mPresenter.collect(id);
                    } else {
                        mPresenter.deleteCollect(id);
                    }
                }
                break;
            case R.id.iv_start:
                if (!isplay) {
                    startPlay();
                } else {
                    stopPlay();
                    //延迟两秒跳转
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            EventBus.getDefault().post(new FourshowEventBean(true, true));
                        }
                    }, 500);
                }
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_to_html:
                if (PointUtils.isFastClick()) {
                    if (!TextUtils.isEmpty(imageurl)) {
                        Intent intent = new Intent(this, ActivityWebView1.class);
                        intent.putExtra("title", title);
                        intent.putExtra("url", html);
                        intent.putExtra("id", id);
                        intent.putExtra("imageurl", imageurl);
                        intent.putExtra("isfrom_banaer", true);
                        intent.putExtra("isFormType", isFormType);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.iv_dian:
                setMipmapImage();
                PraiseEventBean praiseEventBean = new PraiseEventBean();
                if (pageid.equals("ToutiaoSearchActivity")) {
                    praiseEventBean.setType(Constant.SearchActivity);
                } else if (pageid.equals("ConsultationFragment") || pageid.equals("bannerDetail")) {
                    praiseEventBean.setType(Constant.ConsultationFragment);
                }
                praiseEventBean.setPraisePosition(mPosition);


                if (type.equals("1")) {
                    if (register_good == 0) {
                        //点赞操作
                        mPresenter.createPraise(id);
                    } else {
                        mPresenter.deletePraise(id);
                    }
                } else if (type.equals("2")) {
                    if (register_good == 0) {
                        //点赞操作
                        mPresenter.requestPraise(id);
                    } else {
                        mPresenter.requestCancel(id);
                    }
                } else if (type.equals("3")) {
                    if (register_good == 0) {
                        //点赞操作
                        mPresenter.createPraise(id);
                    } else {
                        mPresenter.deletePraise(id);
                    }
                }
//                praiseEventBean.setPraiseCount(Long.valueOf(bean.getGood_amount()));
              /*  if (type.equals("1")) {
                    baseEvent = new BaseEvent(Constant.TYPE_PRAISE, praiseEventBean);
                } else if (type.equals("2")){
                    baseEvent = new BaseEvent(Constant.TYPE_HEAD_PRAISE, praiseEventBean);
                }else if (type.equals("3")){
                    baseEvent = new BaseEvent(Constant.TYPE_Guild, praiseEventBean);
                }*/
//                EventBus.getDefault().postSticky(baseEvent);  //发送通知

                break;
        }
    }

    /**
     * 点击图片或是初始化图片
     */
    private void setCollectImage() {
        if (register_collect == 0) { //未收藏
            iv_collect.setImageResource(R.mipmap.xiangqing_shoucang2);

        } else if (register_collect == 1) { //已收藏
            iv_collect.setImageResource(R.mipmap.xiangqing_shoucang1);
        }

    }

    private void setMipmapImage() {
//        int Good_amount = 0;
        if (register == 0) { //未点赞
            iv_dian.setImageResource(R.mipmap.icon_good3);
            register = 1;
        } else if (register == 1) { //已点赞
            iv_dian.setImageResource(R.mipmap.dianzan_11);
            register = 0;
        }

    }

    public void initView() {

    }


    private void startPlay() {
        if (mAudioPlayer.getMediaPlayer().isPlaying()) {
            mAudioPlayer.pausePlay();
            try {
                mAudioPlayer.getMediaPlayer().reset();
                mAudioPlayer.getMediaPlayer().setDataSource(audiourl);
                mAudioPlayer.getMediaPlayer().prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        isplay = true;
        if (iv_start != null) {
            iv_start.setImageResource(R.mipmap.icon_bofang);
        }
        discObjectAnimator.start();
        neddleObjectAnimator.start();
        if (mAudioPlayer.getMediaPlayer() != null && !mAudioPlayer.getMediaPlayer().isPlaying()) {
            mAudioPlayer.startPlay(audiourl);
            GlobalApp.html = html;
            GlobalApp.imageurl = imageurl;
            GlobalApp.audiourl = audiourl;
            GlobalApp.title = title;
            GlobalApp.register = register;
            GlobalApp.playPosition = mPosition;
            GlobalApp.pageid = pageid;
            GlobalApp.id = id;
        }
    }

    private void stopPlay() {
        isplay = false;
        if (iv_start != null) {
            iv_start.setImageResource(R.mipmap.icon_zanting);
        }
        discObjectAnimator.cancel();
        neddleObjectAnimator.reverse();
        if (mAudioPlayer.getMediaPlayer() != null && mAudioPlayer.getMediaPlayer().isPlaying()) {
            mAudioPlayer.pausePlay();
        }
        GlobalApp.html = "";
        GlobalApp.imageurl = "";
        GlobalApp.audiourl = "";
        GlobalApp.title = "";
        GlobalApp.register = -1;
        GlobalApp.playPosition = -1;
        GlobalApp.pageid = "";
        GlobalApp.id = "";

    }

    @Override
    public void getNewsDetail(NewsDetailInfoBean bean) {

    }

    @Override
    public void praise(PraiseInfoBean resultBean) {


    }

    @Override
    public void praise() {


        if (register_good == 0) {
            iv_dian.setImageDrawable(getResources().getDrawable(R.mipmap.dianzan_11));
            register_good = 1;
        } else {
            iv_dian.setImageDrawable(getResources().getDrawable(R.mipmap.icon_good3));
            register_good = 0;
        }

        if (isMore) {
            if (type.equals("1")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_PRAISE, position));
            } else if (type.equals("2")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_HEAD_PRAISE, position));
            } else if (type.equals("3")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_Guild, position));
            }
        }
        if (type.equals("1")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_PRAISE, position));
        } else if (type.equals("2")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_HEAD_PRAISE, position));
        } else if (type.equals("3")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_Guild, position));
        }

    }

    @Override
    public void collect() {

        if (register_collect == 0) {
            iv_collect.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang1));
//            tvCollectNumber.setText((Integer.parseInt(tvCollectNumber.getText().toString()) + 1) + "");
            register_collect = 1;
        } else {
            iv_collect.setImageDrawable(getResources().getDrawable(R.mipmap.xiangqing_shoucang2));
//            tvCollectNumber.setText((Integer.parseInt(tvCollectNumber.getText().toString()) - 1) + "");
            register_collect = 0;
        }


        if (isMore) {
            if (type.equals("1")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_COllECT, position));
            } else if (type.equals("2")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_HEAD_COllECT, position));
            } else if (type.equals("3")) {
                EventBus.getDefault().post(new MoreArticlesEvent(Constant.TYPE_Guild_COllECT, position));
            }
        }
        if (type.equals("1")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_COllECT, position));
        } else if (type.equals("2")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_HEAD_COllECT, position));
        } else if (type.equals("3")) {
            EventBus.getDefault().post(new NewsEvent(Constant.TYPE_Guild_COllECT, position));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_audio_play;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_audio_play;
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void requestFailed(String message) {

    }

    @SuppressLint("WrongConstant")
    private void setAudioAnimator(Bitmap resource) {
        //最外部的半透明边线
        OvalShape ovalShape0 = new OvalShape();
        ShapeDrawable drawable0 = new ShapeDrawable(ovalShape0);
        drawable0.getPaint().setColor(0x10000000);
        drawable0.getPaint().setStyle(Paint.Style.FILL);
        drawable0.getPaint().setAntiAlias(true);

        //黑色唱片边框
        RoundedBitmapDrawable drawable1 = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.bg_yuan));
        drawable1.setCircular(true);
        drawable1.setAntiAlias(true);

        //内层黑色边线
        OvalShape ovalShape2 = new OvalShape();
        ShapeDrawable drawable2 = new ShapeDrawable(ovalShape2);
        drawable2.getPaint().setColor(Color.BLACK);
        drawable2.getPaint().setStyle(Paint.Style.FILL);
        drawable2.getPaint().setAntiAlias(true);

        //最里面的图像
//        RoundedBitmapDrawable drawable3 = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.icon_fensi));
        RoundedBitmapDrawable drawable3 = RoundedBitmapDrawableFactory.create(getResources(), resource);
        drawable3.setCircular(true);
        drawable3.setAntiAlias(true);

        Drawable[] layers = new Drawable[4];
        layers[0] = drawable0;
        layers[1] = drawable1;
        layers[2] = drawable2;
        layers[3] = drawable3;

        LayerDrawable layerDrawable = new LayerDrawable(layers);

        int width = 10;
        //针对每一个图层进行填充，使得各个圆环之间相互有间隔，否则就重合成一个了。
        //layerDrawable.setLayerInset(0, width, width, width, width);
        layerDrawable.setLayerInset(1, width, width, width, width);
        layerDrawable.setLayerInset(2, width * 11, width * 11, width * 11, width * 11);
        layerDrawable.setLayerInset(3, width * 12, width * 12, width * 12, width * 12);

        final View discView = findViewById(R.id.myView);
        discView.setBackgroundDrawable(layerDrawable);

        discObjectAnimator = ObjectAnimator.ofFloat(discView, "rotation", 0, 360);
        discObjectAnimator.setDuration(20000);
        //使ObjectAnimator动画匀速平滑旋转
        discObjectAnimator.setInterpolator(new LinearInterpolator());
        //无限循环旋转
        discObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        discObjectAnimator.setRepeatMode(ValueAnimator.INFINITE);


        neddleObjectAnimator = ObjectAnimator.ofFloat(needleImage, "rotation", 0, 25);
        needleImage.setPivotX(0);
        needleImage.setPivotY(0);
        neddleObjectAnimator.setDuration(800);
        neddleObjectAnimator.setInterpolator(new LinearInterpolator());
        if (GlobalApp.getMediaPlayer().isPlaying() && GlobalApp.playPosition == mPosition) {
            discObjectAnimator.start();
            neddleObjectAnimator.start();
            isplay = true;
            iv_start.setImageResource(R.mipmap.icon_bofang);
        }
        if (!isplay) {
            startPlay();
        }

    }

    //按系统返回键时出发
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mAudioPlayer.getMediaPlayer().isPlaying()) {
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void startOrPause() {
//        if (!isplay) {
        startPlay();
//        } else {
//            stopPlay();
//        }
    }

    @Override
    public void stop() {
        stopPlay();
    }
}

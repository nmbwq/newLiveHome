package shangri.example.com.shangri.ui.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeBean;
import shangri.example.com.shangri.model.bean.response.NewsDetailInfoBean;
import shangri.example.com.shangri.model.bean.response.PraiseInfoBean;
import shangri.example.com.shangri.presenter.NewsDetailPresenter;
import shangri.example.com.shangri.presenter.view.NewsDetailView;
import shangri.example.com.shangri.ui.view.CustomJCVideoPlayerStandard;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AudioPlayer;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 资讯详情
 * Created by chengaofu on 2017/6/29.
 */

public class NewsDetailActivity extends BaseActivity<NewsDetailView, NewsDetailPresenter> implements NewsDetailView {

    @BindView(R.id.webview_content)
    com.tencent.smtt.sdk.WebView mContentWebView;
    @BindView(R.id.news_detail_scroller)
    ScrollView mScrollerView;
    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
//    NetworkErrorView mNetBreakLayout;


    @BindView(R.id.news_detail_title)
    TextView mTitle;
    @BindView(R.id.news_detail_date)
    TextView mDate;

    @BindView(R.id.news_detail_view_count)
    TextView mBrowseCount;
    @BindView(R.id.news_detail_like_num)
    TextView mLikeNum;
    @BindView(R.id.news_detail_like)
    ImageView mLikeIcon;
    @BindView(R.id.viewstub_video)
    ViewStub mVideoStub;
    @BindView(R.id.viewstub_audio)
    ViewStub mAudioStub;
    @BindView(R.id.float_audio_player_start)
    ImageButton mAudioFloatStart;
    @BindView(R.id.float_audio_player_pause)
    ImageButton mAudioFloatPause;

    private RelativeLayout mDetailAudio; //音频布局
//    private JCVideoPlayerStandard mJCVideoPlayer; //视频
    private CustomJCVideoPlayerStandard mJCVideoPlayer; //视频
    private AudioPlayer mAudioPlayer; //音频

    private ImageView mAudioPlayerStart;
    private ImageView mAudioPlayerPause;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;
    private boolean hasVideo; //判断是否有Video
    private boolean hasAudio; //判断是否有音频
    private NewsDetailInfoBean mNewsDetailInfoBean; //详情

//    private long mId;
//    private int mPos; //在列表中位于第几行
//    private int mType; //类型\
    private int mTempY;
    private BannerHomeBean.ArticlesBean.DataBean bean;
    private String css = "<style type=\"text/css\"  > img {" +
            "width:100%;" +//限定图片宽度填充屏幕
            "height:auto;" +//限定图片高度自动
            "}" +
            "body {" +
            //"margin-right:15px;" +//限定网页中的文字右边距为15px(可根据实际需要进行行管屏幕适配操作)
            //"margin-left:15px;" +//限定网页中的文字左边距为15px(可根据实际需要进行行管屏幕适配操作)
           // "margin-top:15px;" +//限定网页中的文字上边距为15px(可根据实际需要进行行管屏幕适配操作)
            "font-size:40px;" +//限定网页中文字的大小为40px,请务必根据各种屏幕分辨率进行适配更改  并无卵用
            "word-wrap:break-word;"+//允许自动换行(汉字网页应该不需要这一属性,这个用来强制英文单词换行,类似于word/wps中的西文换行)
            "}" +
            "</style>";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initViewsAndEvents() {
//        mId = getIntent().getLongExtra("id", 0);
//        mPos = getIntent().getIntExtra("pos", -1);
//        mType = getIntent().getIntExtra("type", 0);
        bean = (BannerHomeBean.ArticlesBean.DataBean) getIntent().getSerializableExtra("bean");
        mContentWebView.setBackgroundColor(getResources().getColor(R.color.bg_color_gray));
        WebSettings settings = mContentWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setTextSize(WebSettings.TextSize.LARGEST);
        settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(true);// support zoom
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mScrollerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldx, int oldy) {
                mTempY = y;
                if (mDetailAudio != null) { //监听音频的位置

                    if(y >= mDetailAudio.getBottom()){
                        if(mAudioPlayerStart.getVisibility() == View.VISIBLE){
                            mAudioFloatStart.setVisibility(View.VISIBLE);
                            mAudioFloatPause.setVisibility(View.INVISIBLE);
                        }else{
                            mAudioFloatStart.setVisibility(View.INVISIBLE);
                            mAudioFloatPause.setVisibility(View.VISIBLE);
                        }



                    }else{
                        mAudioFloatStart.setVisibility(View.INVISIBLE);
                        mAudioFloatPause.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        loadData();
    }

    private void loadData(){
        if (!NetWorkUtil.isNetworkConnected(this)) {
            mScrollerView.setVisibility(View.INVISIBLE);
            mNetBreakLayout.setVisibility(View.VISIBLE);
            return;
        } else {
            mScrollerView.setVisibility(View.VISIBLE);
            mNetBreakLayout.setVisibility(View.INVISIBLE);
        }
        initView();
//        mPresenter.getNewsDetail(mId);
    }


    @OnClick({R.id.news_detail_back, R.id.news_detail_like_click, R.id.reload,
            R.id.float_audio_player_start, R.id.float_audio_player_pause})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_detail_back:
                onBackPressed();
                finish();
                break;
            case R.id.news_detail_like_click: //点赞

                if (mNewsDetailInfoBean != null) {
                    long infoId = mNewsDetailInfoBean.getId();
                    byte praiseOrNot = 0;
                    if (mNewsDetailInfoBean.getIsPraise() == 0) { //返回的都是该资讯已点赞？
                        praiseOrNot = 1;// 未点赞，则点赞
                    } else if (mNewsDetailInfoBean.getIsPraise() == 1) {
                        praiseOrNot = 0; // 已点赞，则取消点赞
                    }
                    mPresenter.requestPraise(infoId+"");
                }

                break;
            case R.id.float_audio_player_start: //音频播放
                mAudioFloatStart.setVisibility(View.INVISIBLE);
                mAudioFloatPause.setVisibility(View.VISIBLE);
                startPlay();
                break;
            case R.id.float_audio_player_pause: //音频停止播放
                mAudioFloatStart.setVisibility(View.VISIBLE);
                mAudioFloatPause.setVisibility(View.INVISIBLE);
                stopPlay();
                break;
            case R.id.reload:
                loadData();
                break;
        }
    }

    public void initView() {
        mBrowseCount.setVisibility(View.VISIBLE);
        mLikeIcon.setVisibility(View.VISIBLE);
        mLikeNum.setVisibility(View.VISIBLE);
        mTitle.setText(bean.getTitle());
//        mDate.setText(RegexUtil.format(new Date(bean.getReleaseTime()), RegexUtil.defaultDatePattern));
//        mBrowseCount.setText(String.valueOf(bean.getBrowseCount()));
//        mSummary.setText(bean.getSummary());
//        if (mNewsDetailInfoBean.getPraiseCount() == null) {
//            mLikeNum.setText(String.valueOf(0));
//        } else {
//            mLikeNum.setText(String.valueOf(bean.getPraiseCount()));
//        }
//        if (bean.getIsPraise() == 0) {
//            mLikeIcon.setImageResource(R.mipmap.ic_zx_dianzan2);
//            mLikeNum.setTextColor(getResources().getColor(R.color.text_color_little_black));
//        } else if (bean.getIsPraise() == 1) {
//            mLikeIcon.setImageResource(R.mipmap.ic_zx_dianzan4);
//            mLikeNum.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
//        }
//        String html = "<html style='font-size:30px !important'><header>" + css + "</header> <style type=\"text/css\"> \n" +
//                "div,p,body{ font-size:25px !important; font-style:宋体 !important; font-color:#333333 !important; lineHeight:10px !important;}  \n" +
//                "</style>" +
//                "<body ><div  color = #f2f2f2 style= 'BACKGROUND-COLOR:#f2f2f2'>" + bean.getContent() + "</div></body></html>";
//        mContentWebView.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");


        if (!TextUtils.isEmpty(bean.getArticle_url())) {

            hasVideo = true;

            if (mVideoStub != null) {
                View view = mVideoStub.inflate();
//                mJCVideoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.news_detail_video);
                mJCVideoPlayer = view.findViewById(R.id.news_detail_video);
                //视频监听
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
            }

            mJCVideoPlayer.setUp(bean.getArticle_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

//            List<String> picList = bean.getPictureUrlList();
//            if (picList != null && picList.size() > 0) {
//                mJCVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                BitmapCache.getInstance().loadBitmaps(mJCVideoPlayer.thumbImageView, picList.get(0), null);
//            }
        }

        if (!TextUtils.isEmpty(bean.getAudio_url())) {

            if (mAudioStub != null) {
                hasAudio = true; //有音频
                View view = mAudioStub.inflate();
                mDetailAudio = view.findViewById(R.id.news_detail_audio);
                mAudioPlayerStart = view.findViewById(R.id.news_detail_audio_player_start);
                mAudioPlayerPause = view.findViewById(R.id.news_detail_audio_player_pause);
//                AnimationDrawable animationDrawable = (AnimationDrawable) audioPlayerPause.getDrawable();
//                AnimationDrawable floatDrawable = (AnimationDrawable) mAudioFloatBtn.getDrawable();
                SeekBar audioSeekBar = view.findViewById(R.id.audio_seekbar);
                TextView audioStartText = view.findViewById(R.id.news_detail_player_play_time);
                TextView audioEndText = view.findViewById(R.id.news_detail_player_total_time);

//                mAudioPlayer = new AudioPlayer(mAudioPlayerStart, mAudioPlayerPause, audioSeekBar, audioStartText, audioEndText,
//                        mAudioFloatStart, mAudioFloatPause);
                mAudioPlayer.initMediaPlayer(bean.getAudio_url(), "");
                mAudioPlayerStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAudioFloatStart.setVisibility(View.INVISIBLE);
                        mAudioFloatPause.setVisibility(View.INVISIBLE);
                        startPlay();
                    }
                });
                mAudioPlayerPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAudioFloatStart.setVisibility(View.INVISIBLE);
                        mAudioFloatPause.setVisibility(View.INVISIBLE);
                        stopPlay();
                    }
                });
                mAudioPlayer.setOnScroller(new AudioPlayer.OnScroller() {
                    @Override
                    public void onPlayComplete() {
                        if(mTempY > mDetailAudio.getBottom()){
                            mAudioFloatStart.setVisibility(View.VISIBLE);
                            mAudioFloatPause.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        }
    }

    private void startPlay(){
        if (mAudioPlayer.getMediaPlayer() != null && !mAudioPlayer.getMediaPlayer().isPlaying()) {
            mAudioPlayerStart.setVisibility(View.INVISIBLE);
            mAudioPlayerPause.setVisibility(View.VISIBLE);
            mAudioPlayer.startPlay(bean.getAudio_url());
        }
    }

    private void stopPlay(){
        if (mAudioPlayer.getMediaPlayer() != null && mAudioPlayer.getMediaPlayer().isPlaying()) {
            mAudioPlayerStart.setVisibility(View.VISIBLE);
            mAudioPlayerPause.setVisibility(View.INVISIBLE);
            mAudioPlayer.pausePlay();
        }
    }

    @Override
    public void getNewsDetail(NewsDetailInfoBean bean) {

    }

    @Override
    public void praise(PraiseInfoBean resultBean) {
        if (mNewsDetailInfoBean != null) {
            if (mNewsDetailInfoBean.getIsPraise() == 0) {
                mNewsDetailInfoBean.setIsPraise((byte) 1);
                if (mNewsDetailInfoBean.getPraiseCount() != null) {
                    mNewsDetailInfoBean.setPraiseCount(mNewsDetailInfoBean.getPraiseCount() + 1); //点赞+1
                } else {
                    mNewsDetailInfoBean.setPraiseCount((long) 1); //点赞+1
                }
                mLikeNum.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                mLikeIcon.setImageResource(R.mipmap.ic_zx_dianzan4);
            } else if (mNewsDetailInfoBean.getIsPraise() == 1) {
                mNewsDetailInfoBean.setIsPraise((byte) 0);
                if (mNewsDetailInfoBean.getPraiseCount() != null) {
                    mNewsDetailInfoBean.setPraiseCount(mNewsDetailInfoBean.getPraiseCount() - 1); //点赞-1
                } else {
                    mNewsDetailInfoBean.setPraiseCount((long) 0); //点赞-1
                }
                mLikeNum.setTextColor(getResources().getColor(R.color.text_color_little_black));
                mLikeIcon.setImageResource(R.mipmap.ic_zx_dianzan2);
            }
            mLikeNum.setText(String.valueOf(resultBean.getPraiseCount()));
            //携带点赞数量 点赞位置 点赞的fragment type
            PraiseEventBean praiseEventBean = new PraiseEventBean();
//            praiseEventBean.setType(mType);
            praiseEventBean.setPraiseCount(resultBean.getPraiseCount());
//            praiseEventBean.setPraisePosition(mPos);

            BaseEvent<PraiseEventBean> baseEvent = new BaseEvent(Constant.TYPE_PRAISE, praiseEventBean);
            EventBus.getDefault().postSticky(baseEvent);  //发送通知
        }
    }

    @Override
    public void praise() {

    }

    @Override
    public void collect() {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (hasVideo) {
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (hasVideo) {
            sensorManager.unregisterListener(sensorEventListener);
            JCVideoPlayer.releaseAllVideos();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (hasAudio) {
            if (mAudioPlayer != null) {
                mAudioPlayer.stopPlay();
            }
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        BrowseEventBean browseEventBean = new BrowseEventBean();
        if (mNewsDetailInfoBean != null) {
//            browseEventBean.setBrowsePosition(mPos);
//            browseEventBean.setType(mType);
            browseEventBean.setBrowseCount(mNewsDetailInfoBean.getBrowseCount());
            BaseEvent<PraiseEventBean> baseEvent = new BaseEvent(Constant.TYPE_BROWSE, browseEventBean);
            EventBus.getDefault().postSticky(baseEvent);  //发送通知
        }

    }

    @Override
    public void requestFailed(String message) {
        if(TextUtils.isEmpty(message)) return;
        if(message.contains(String.valueOf(Constant.CODE_100027))){
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }
}

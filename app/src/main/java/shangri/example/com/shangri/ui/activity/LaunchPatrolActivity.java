package shangri.example.com.shangri.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.czt.mp3recorder.MP3Recorder;
import com.fyales.tagcloud.library.TagCloudLayout;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.presenter.LaunchPatrolPresenter;
import shangri.example.com.shangri.presenter.view.LaunchPatrolView;
import shangri.example.com.shangri.ui.adapter.ImageAdapter;
import shangri.example.com.shangri.ui.adapter.TagLabelAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.NewSelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.MyScrollview;
import shangri.example.com.shangri.ui.view.WheelView;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.VoicePlayingBgUtil;

/**
 * 发起巡查
 * Created by admin on 2017/12/22.
 */

public class LaunchPatrolActivity extends BaseActivity<LaunchPatrolView, LaunchPatrolPresenter> implements LaunchPatrolView {
    @BindView(R.id.tv_content1)
    TextView tv_content1;
    @BindView(R.id.dialog_year)
    WheelView mWheelYear;
    @BindView(R.id.dialog_month)
    WheelView mWheelMonth;
    @BindView(R.id.dialog_data)
    WheelView mWheelDay;

    @BindView(R.id.dialog_data1)
    WheelView mWheelDay1;
    @BindView(R.id.dialog_data2)
    WheelView mWheelDay2;

    @BindView(R.id.time_save)
    TextView timeSave;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.tv_anchors_name)
    TextView tv_anchors_name;
    @BindView(R.id.tv_content3)
    TextView tv_content3;
    @BindView(R.id.tv_youxiu)
    TextView tv_youxiu;
    @BindView(R.id.tv_second)
    TextView tv_second;
    @BindView(R.id.tv_buzu)
    TextView tv_buzu;
    @BindView(R.id.tv_comment1)
    EditText tv_comment1;

    @BindView(R.id.tag_cloud_layout_good)
    TagCloudLayout tag_cloud_layout_good;
    @BindView(R.id.tag_cloud_layout1_bad)
    TagCloudLayout tag_cloud_layout1_bad;
    @BindView(R.id.tv_merit_number)
    TextView tv_merit_number;

    @BindView(R.id.tv_bad_number)
    TextView tv_bad_number;


    @BindView(R.id.im_click_start)
    ImageView im_click_start;

    @BindView(R.id.add_image)
    RecyclerView add_image;


    @BindView(R.id.rl_startRecord)
    LinearLayout rlStartRecord;
    @BindView(R.id.tv_second_video)
    TextView tvSecondVideo;
    @BindView(R.id.im_startPlay_start)
    ImageView imStartPlayStart;
    @BindView(R.id.im_quxiao)
    ImageView imQuxiao;
    @BindView(R.id.rl_startPlay)
    LinearLayout rlStartPlay;


    @BindView(R.id.tv_send_photo)
    CircleImageView tv_send_photo;
    @BindView(R.id.ll_video_show)
    LinearLayout ll_video_show;
    @BindView(R.id.tv_show_second)
    TextView tv_show_second;
    @BindView(R.id.im_text)
    ImageView im_text;
    @BindView(R.id.sc_scrollView)
    MyScrollview sc_scrollView;

    @BindView(R.id.rl_cancal_text)
    RelativeLayout rl_cancal_text;

    @BindView(R.id.ll_send_message)
    LinearLayout ll_send_message;

    @BindView(R.id.im_send_message)
    ImageView im_send_message;


    private String guildid = "";
    private String anchorsid = "";
    private String launchData = "";
    private String tagyouString = "";
    private String tagbuzuString = "";

    private ProgressDialogFragment mProgressDialog;
    //当前时间和分钟
    int time;
    int min;


    //    下面是tag需要信息
    private TagLabelAdapter TagmAdapter;
    private TagLabelAdapter TagmAdapter1;

    private String type;  //1优点 2 缺点
    private List<LabelDataBean.TagsBean> mresultBean = new ArrayList<>();
    private List<LabelDataBean.TagsBean> mresultBean1 = new ArrayList<>();
    @SuppressLint("UseSparseArrays")
    HashMap<Integer, Integer> selectList = new HashMap<>();
    HashMap<Integer, Integer> selectList1 = new HashMap<>();


    //优点记录选中的个数
    int goodsNumber = 0;
    //缺点记录选中的个数
    int goodsNumber1 = 0;


    //上传图片的信息
    private PhotoConfig mPhotoConfig;


    //选择图片时候的数据
    private List<String> imageList = new ArrayList<>();
    private ImageAdapter ImageAdapter;


    //语音操作对象
    private MediaPlayer mPlayer = null;

    MP3Recorder mp3Recorder;

    //    private MediaRecorder mRecorder = null;
    //语音文件保存路径
    private String FileName = null;
    //true为录制音频
    public boolean isStartRecord = true;
    //播放录制音频
    public boolean isStartPlay = true;

    //是否有录制音频的权限
    public boolean isQuanxian = false;
    CountDownTimer timer = null;
    //是否是第一点击上传图片
    public boolean IsFistPhoto = true;
    //多张图片可选择个数
    public int selectSize = 6;
    String imageString = "";
    //0不通知主播  1通知主播
    String alert_admin = "1";
    String month;
    String day;
    String Time;
    String Min;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this, false);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
    }

    @Override
    protected int getNormalLayoutId() {
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return R.layout.activity_launch_patrol;

    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_launch_patrol;
    }

    @Override
    protected LaunchPatrolPresenter createPresenter() {
        return new LaunchPatrolPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents()  {

//        PackageManager pm = getPackageManager();
//        boolean permission = (PackageManager.PERMISSION_GRANTED ==
//                pm.checkPermission("android.permission.RECORD_AUDIO", "shangri.example.com.shangri"));
//        if (permission) {
//            Toast.makeText(LaunchPatrolActivity.this,"有录音权限", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(LaunchPatrolActivity.this,"木有录音权限",Toast.LENGTH_SHORT).show();
//        }

        try {
            launchData=TimeUtil.longToString(TimeUtil.getCurrentTime(),"yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_content3.setText(launchData);

        methodRequiresTwoPermission();
        if (UserConfig.getInstance().getRole().equals("1")) {
            ll_send_message.setVisibility(View.VISIBLE);
        } else {
            ll_send_message.setVisibility(View.GONE);
        }
        im_text.setImageResource(R.mipmap.new_yuyin_wanzheng);
        rlStartRecord.setVisibility(View.VISIBLE);
        rlStartPlay.setVisibility(View.GONE);
        setDataUtil();
        mPhotoConfigListener();
        timeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(getMonth()) < 10) {
                    month = "0" + getMonth();
                }
                if (Integer.parseInt(getDay()) < 10) {
                    day = "0" + getDay();
                }
                if (Integer.parseInt(getDay()) < 10) {
                    Time = "0" + getDay();
                }
                if (Integer.parseInt(getDay()) < 10) {
                    Min = "0" + getDay();
                }
                tv_content3.setText(getYear() + "-" + month + "-" + day + " " + Time + ":" + Min);
                launchData = getYear() + "-" + month + "-" + day + " " + Time + ":" + Min;
                head.setVisibility(View.GONE);
            }
        });
        mPresenter.getLabelData("1");
        mPresenter.getLabelData("2");
        //优点
        TagmAdapter = new TagLabelAdapter(LaunchPatrolActivity.this, mresultBean);
        tag_cloud_layout_good.setAdapter(TagmAdapter);
        //缺点
        TagmAdapter1 = new TagLabelAdapter(LaunchPatrolActivity.this, mresultBean1);
        tag_cloud_layout1_bad.setAdapter(TagmAdapter1);

        //点击事件
        GoodItemSelect(tag_cloud_layout_good);
        BadItemSelect(tag_cloud_layout1_bad);

        for (int i = 0; i < 1; i++) {
            imageList.add("");
        }
        //初始化图片
        add_image.setLayoutManager(new GridLayoutManager(LaunchPatrolActivity.this, 3));
        ImageAdapter = new ImageAdapter(LaunchPatrolActivity.this, R.layout.item_image, imageList);
//        TagmAdapter.addData(mresultBean);
        add_image.setAdapter(ImageAdapter);
        ImageAdapter.setData(imageList);
        ImageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //图片加号  弹出选择图片   为图片的时候   点击放大操作
                //数据为1时  加号 弹出选择图片  数据为6  第一种情况 ：全部图片  点击放大操作 第二种情况 有五种图片 一个加号，做对应的操作
                //其他情况   最后一张是弹出选择图片操作  其余的是查看大图的操作
                if (ImageAdapter.getAll().size() == 1) {
                    showPop();
                } else if (ImageAdapter.getAll().size() == 6) {
                    //最后item Url的长度为0  说明是 加号 弹出选择图片操作
                    if (ImageAdapter.get(ImageAdapter.getAll().size() - 1).length() == 0) {

                        if (ImageAdapter.getAll().size() - 1 == position) {
                            showPop();
                        } else {
                            jumpBig(position);
                        }
                    } else {
                        jumpBig(position);
                    }
                } else {
                    if (ImageAdapter.getAll().size() - 1 == position) {
                        showPop();
                    } else {
                        jumpBig(position);
                    }
                }

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        im_click_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // TODO Auto-generated method stub
                Log.d("Debug", "当前的状态" + event.getAction());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        String[] perms = {Manifest.permission.RECORD_AUDIO};
                        if (EasyPermissions.hasPermissions(LaunchPatrolActivity.this, perms)) {
                            ToastUtil.showShort("开始录制");
                            Log.d("Debug", "kaishi录制");
                            rl_cancal_text.requestDisallowInterceptTouchEvent(true);
                            startRecord();
                        } else {
                            methodRequiresTwoPermission();
                            ToastUtil.showShort("请先同意录音权限");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Debug", "结束录制");
                        stopRecord();
                        break;
                }
                return true;
            }
        });


    }

    /**
     * 跳转到放大界面
     *
     * @param
     */
    @SuppressWarnings("JavaDoc")
    public void jumpBig(int positiono) {
        Intent intent = new Intent(LaunchPatrolActivity.this, ZoomImgActivity.class);
        intent.putStringArrayListExtra(ZoomImgActivity.URL, (ArrayList<String>) ImageAdapter.getAll());
        intent.putExtra(ZoomImgActivity.PAGEINDEX, positiono);
        intent.putExtra(ZoomImgActivity.ISREALFILEPATH, true);
        startActivity(intent);
    }


    @OnClick({R.id.ll_send_message, R.id.tv_merit_more, R.id.tv_merit_more1, R.id.rl_guild1, R.id.rl_guild2, R.id.setting_back,
            R.id.rl_guild3, R.id.rl_guild4, R.id.rl_guild5,
            R.id.time_disms, R.id.tv_send, R.id.im_startPlay_start, R.id.im_quxiao, R.id.ll_video_show})
    public void onClick(View v) {
        switch (v.getId()) {
            //选择通知主播
            case R.id.ll_send_message:
                if (alert_admin.equals("1")) {
                    im_send_message.setImageDrawable(getResources().getDrawable(R.mipmap.duihao2));
                    alert_admin = "0";
                } else {
                    im_send_message.setImageDrawable(getResources().getDrawable(R.mipmap.duihao));
                    alert_admin = "1";
                }

                break;
            case R.id.ll_video_show:
            case R.id.im_startPlay_start:
                if (isStartPlay) {
                    startPlay();
                } else {
                    stopPlay();
                }
                break;
            //取消按钮
            case R.id.im_quxiao:
                showSelectVideo();
                break;
            //选择公会
            case R.id.rl_guild1:
                Intent intent1 = new Intent(LaunchPatrolActivity.this, ChoiceGuildActivity.class);
                startActivityForResult(intent1, 1);
                break;
            //主播昵称
            case R.id.rl_guild2:
                if (TextUtils.isEmpty(guildid)) {
                    ToastUtil.showShort("请先选择公会");
                    return;
                }
                Intent intent2 = new Intent(LaunchPatrolActivity.this, ChoiceAnchorsActivity.class);
                intent2.putExtra("guildid", guildid);
                startActivityForResult(intent2, 3);
                break;
            //巡查日期
            case R.id.rl_guild3:
                head.setVisibility(View.VISIBLE);
                break;
            //优点
            case R.id.tv_merit_more:
                showPopupWindow(true);
                break;
            //不足
            case R.id.tv_merit_more1:
                showPopupWindow(false);
                break;
            case R.id.time_disms:
                head.setVisibility(View.GONE);
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_send:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());

                String tv_comment = tv_comment1.getText().toString().trim();

                String goodTagstring = getGoodTagstring();
                String badTagstring = getBadTagstring();
                if (launchData.length() == 0) {
                    ToastUtil.showShort("请选择辅导日期");
                    return;
                }
//                String s = "/storage/emulated/0/AudioRecorderMp3/recorder/20180509171832.mp3";
//                mPresenter.sendLaunch("",guildid, anchorsid, launchData, tagyouString, tagbuzuString, tv_comment);
                Log.d("Debug", "本地地址为" + FileName);
                mPresenter.sendLaunch(alert_admin, FileName, tv_show_second.getText().toString(), imageString, guildid, anchorsid, launchData, getGoodTagstring(), getBadTagstring(), tv_comment);
                break;
        }
    }


    /**
     * 下面是请求之前的准备
     *
     * @return
     */


    // 获得优点标间id集合
    @SuppressWarnings("JavaDoc")
    public String getGoodTagstring() {
        if (selectList.size() > 0) {
            String tagString = "";
            for (Integer key : selectList.keySet()) {
                tagString = tagString + selectList.get(key);
                tagString = tagString + ",";
            }
            tagString = tagString.substring(0, tagString.length() - 1);
            Log.d("Debug", "选择优点的标签为" + tagString);
            return tagString;
        }
        return "";
    }

    // 获得缺点标间id集合
    public String getBadTagstring() {
        if (selectList1.size() > 0) {
            String tagString = "";
            for (Integer key : selectList1.keySet()) {
                tagString = tagString + selectList1.get(key);
                tagString = tagString + ",";
            }
            tagString = tagString.substring(0, tagString.length() - 1);
            Log.d("Debug", "选择缺点的标签为" + tagString);
            return tagString;
        }
        return "";
    }


    @Override
    public void lacunch() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("发送成功");
        finish();


//        finish();
    }

    /**
     * 请求标签返回的数据
     *
     * @param resultBean
     */
    @SuppressWarnings("JavaDoc")
    @Override
    public void labelData(LabelDataBean resultBean) {
        //1优点 2缺点
        if (resultBean.getType().equals("1")) {
            mresultBean = resultBean.getTags();
            for (int i = 0; i < resultBean.getTags().size(); i++) {
                mresultBean.get(i).setSelect(false);
            }
            TagmAdapter.addData(mresultBean);
        } else {
            mresultBean1 = resultBean.getTags();
            for (int i = 0; i < resultBean.getTags().size(); i++) {
                mresultBean1.get(i).setSelect(false);
            }
            TagmAdapter1.addData(mresultBean1);
        }

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            guildid = data.getStringExtra("guildid");
            String content = data.getStringExtra("content");
            tv_content1.setText(content);

        } else if (requestCode == 3 && resultCode == 4) {
            // 获取返回的数据
            anchorsid = data.getStringExtra("anchorsid");
            String anchorsname = data.getStringExtra("anchorsname");
            tv_anchors_name.setText(anchorsname);
        } else {
            Log.d("Debug", "到达这里");
            mPhotoConfig.getTakePhoto(this).onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 开始录制音频
     */
    public void startRecord() {
//        //设置sdcard的路径
//        String path = Environment.getExternalStorageDirectory()
//                + "/AudioRecorderMp3/recorder/";
//        if (mp3Recorder.isRecording()) {
//            Log.d("Debug", "luzhi zhong");
//        }
//        if (mp3Recorder!=null){
//            mp3Recorder.stop();
//            mp3Recorder=null;
//        }
        isStartRecord = true;
        try {
//                FileName = path + FileUtils.getFileNameByTime() + ".mp3";
//                mp3Recorder = new MP3Recorder(new File(Environment.getExternalStorageDirectory(), FileName));
            FileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.mp3";
            mp3Recorder = new MP3Recorder(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "a.mp3"));
            mp3Recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //做倒计时操作
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(120 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                tv_second.setText(millisUntilFinished / 1000 + "″");
            }

            @Override
            public void onFinish() {
                //录制音频结束操作
                stopRecord();
            }
        }.start();
        sc_scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

    }


    /**
     * 停止录制音频
     */
    public void stopRecord() {
        if (mp3Recorder == null) {
            return;
        }
        ToastUtil.showShort("停止录制");
        //显示语音界面显示
        ll_video_show.setVisibility(View.VISIBLE);
        isStartPlay = true;
        try {
            mp3Recorder.stop();
        } catch (Exception e) {
            Log.e("Debug", "prepare() failed");
        }
        if (timer != null) {
            timer.cancel();
        }
        rlStartRecord.setVisibility(View.GONE);
        rlStartPlay.setVisibility(View.VISIBLE);
        //120秒减倒计时时间  获得是录制的时间
        String replace = tv_second.getText().toString().replace("″", "");
        tvSecondVideo.setText((120 - Integer.parseInt(replace)) + "秒");
        tv_show_second.setText((120 - Integer.parseInt(replace)) + "″");
        sc_scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return false;
            }
        });
    }

    /**
     * 开始播放音频
     */
    public void startPlay() {

        isStartPlay = false;
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        try {
            Log.d("Debug", "返回的地址是" + FileName);
            VoicePlayingBgUtil.playAudioAnimation(im_text);
            mPlayer.setDataSource(FileName);
            mPlayer.prepare();
            mPlayer.start();
            imStartPlayStart.setImageDrawable(getResources().getDrawable(R.mipmap.zanting));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isStartPlay) {
                        stopPlay();
                    }
                }
            }, Integer.parseInt(tvSecondVideo.getText().toString().replace("秒", "")) * 1000);
        } catch (IOException e) {
            Log.e("Debug", "播放失败");
        }
    }

    /**
     * 停止播放音频
     */
    public void stopPlay() {
        VoicePlayingBgUtil.stopTimer();
        isStartPlay = true;
        imStartPlayStart.setImageDrawable(getResources().getDrawable(R.mipmap.bofang));
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    /**
     * 音频重新录制或是删除操作选项
     */

    public void showSelectVideo() {
        NewSelectPhotoPopopWindow ImagePop = new NewSelectPhotoPopopWindow(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                rlStartRecord.setVisibility(View.VISIBLE);
                rlStartPlay.setVisibility(View.GONE);
                FileName = null;
                tv_second.setText("120″");
                ll_video_show.setVisibility(View.GONE);

            }

            @Override
            public void takePhoto() {
                rlStartRecord.setVisibility(View.VISIBLE);
                rlStartPlay.setVisibility(View.GONE);
                FileName = null;
                tv_second.setText("120″");
                ll_video_show.setVisibility(View.GONE);
            }

            @Override
            public void savePhoto() {
            }
        }, "取消");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    /**
     * 下面是选择图片的操作
     */

    public void showPop() {
        SelectPhotoPopopWindow ImagePop = new SelectPhotoPopopWindow(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                mPhotoConfig.selectFromAlbum(selectSize);
            }

            @Override
            public void takePhoto() {
                mPhotoConfig.takePhoto();
            }

            @Override
            public void savePhoto() {
            }
        }, "取消");
    }


    /**
     *
     */

    String mCompressPath;

    protected void mPhotoConfigListener() {
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {

                Log.d("Debug", "本地上传图片返回的列表地址" + result.getImages().toString());
                Log.d("Debug", "本地上传图片返回的单个地址" + result.getImage().toString());
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                //数据的最后一张总是加号，所以每次先移除
                imageList.remove(imageList.get(imageList.size() - 1));

                for (int i = 0; i < images.size(); i++) {
                    mCompressPath = images.get(i).getCompressPath();
                    imageList.add(mCompressPath);
                }
                if (IsFistPhoto) {
                    selectSize = 6 - images.size();
                } else {
//                    Log.d("Debug", "当前的列表数据为" + imageList.size());
                    selectSize = 6 - imageList.size();
                }
                //将图片用逗号分隔开
                if (imageList.size() > 0) {
                    for (String key : imageList) {
                        try {
                            imageString = imageString + Base64Utils.encodeFile(key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        imageString = imageString + ",";
                    }
                    imageString = imageString.substring(0, imageString.length() - 1);
                    Log.d("Debug", "选择缺点的标签为" + imageString);
                }
                imageList.add("");
                ImageAdapter.setData(imageList);
                IsFistPhoto = false;
                Log.d("Debug", "mCompressPath信息为" + mCompressPath);
                if (TextUtils.isEmpty(mCompressPath)) return;
//
            }

            @Override
            public void takeFail(TResult result, String msg) {
                Log.e("Wislie", "takeFail");
            }

            @Override
            public void takeCancel() {
                Log.e("Wislie", "takeCancel");
            }
        });

//        mUserAvatar.enable();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPhotoConfig.getTakePhoto(this).onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, mPhotoConfig.getInvokeParam(), mPhotoConfig);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    /**
     * 下面是时间选择器的选择
     */

    private void setDataUtil() {
        Calendar c = Calendar.getInstance();//
        Integer mYear = c.get(Calendar.YEAR); // 获取当前年份
        Integer mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        Integer mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期


        int time = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());

        mWheelDay1.setData(getDayData1());
        mWheelDay2.setData(getDayData2());

        mWheelYear.setDefault(2);
        mWheelMonth.setDefault(mMonth - 1);
        mWheelDay.setDefault(mDay - 1);
        mWheelDay1.setDefault(time - 1);
        mWheelDay2.setDefault(min);
    }

    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2020; i > 2015; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData1() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData2() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 60; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }


    public String getYear() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    public String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    public String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }


    public String getTime() {
        if (mWheelDay1 == null) {
            return null;
        }
        return mWheelDay1.getSelectedText();
    }

    public String getMin() {
        if (mWheelDay2 == null) {
            return null;
        }
        return mWheelDay2.getSelectedText();
    }


    /**
     * 权限问题
     */
    private static final int REQUEST_CODE = 1001;
    private static final int REQUEST_CONTACTS = 100;

    /**
     * 添加权限区域
     */

    @AfterPermissionGranted(REQUEST_CODE)//添加注解，是为了首次执行权限申请后，回调该方法
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.RECORD_AUDIO};

        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.d("Debug", "有这个权限");
        } else {
            ToastUtil.showShort("请先添加录音权限");
            EasyPermissions.requestPermissions(this, "需要获取权限",
                    REQUEST_CODE, perms);
        }
    }

    /**
     * 选择优缺点
     */
    private PopupWindow mPopWindow;

    private void showPopupWindow(final Boolean flag) {
        //设置contentView
        View contentView = LayoutInflater.from(LaunchPatrolActivity.this).inflate(R.layout.popwindow_select_layout, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //添加动画
//        mPopWindow.setAnimationStyle(R.style.showPopupAnimation);
        //设置各个控件的点击响应
        TextView tv_close = contentView.findViewById(R.id.tv_close);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);


        TagCloudLayout tag_cloud_layout_dialog = contentView.findViewById(R.id.tag_cloud_layout_dialog);

        if (flag) {
            tag_cloud_layout_dialog.setAdapter(TagmAdapter);
            TagmAdapter.addData(mresultBean);
            //点击事件
            GoodItemSelect(tag_cloud_layout_dialog);
        } else {
            tag_cloud_layout_dialog.setAdapter(TagmAdapter1);
            TagmAdapter1.addData(mresultBean1);
            BadItemSelect(tag_cloud_layout_dialog);
        }
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                取消操作 清除之前选择的标签
                if (flag) {
                    for (int i = 0; i < mresultBean.size(); i++) {
                        mresultBean.get(i).setSelect(false);
                    }
                    selectList.clear();
                    TagmAdapter.notifyDataSetChanged();
                    tv_merit_number.setText("");
                    tv_merit_number.setHint("请选择优点标签");
                    goodsNumber = 0;
                } else {
                    for (int i = 0; i < mresultBean1.size(); i++) {
                        mresultBean1.get(i).setSelect(false);
                    }
                    selectList1.clear();
                    TagmAdapter1.notifyDataSetChanged();
                    tv_bad_number.setText("");
                    tv_bad_number.setHint("请缺点优点标签");
                    goodsNumber1 = 0;
                }
                mPopWindow.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        View rootview = LayoutInflater.from(LaunchPatrolActivity.this).inflate(R.layout.activity_patrol, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 优点item点击事件
     */
    public void GoodItemSelect(TagCloudLayout TagCloudLayout) {
        TagCloudLayout.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (selectList.size() >= 6) {
                    if (mresultBean.get(position).getSelect()) {
                        mresultBean.get(position).setSelect(false);
                        selectList.remove(position);
                        TagmAdapter.addData(mresultBean);
                        goodsNumber--;
                        tv_merit_number.setText("已选择" + goodsNumber + "个");
                        return;
                    } else {
                        ToastUtil.showShort("最多选6个标签哦");
                        return;
                    }
                }
                if (mresultBean.get(position).getSelect()) {
                    mresultBean.get(position).setSelect(false);
                    selectList.remove(position);
                    goodsNumber--;
                    tv_merit_number.setText("已选择" + goodsNumber + "个");

                } else {
                    mresultBean.get(position).setSelect(true);
                    selectList.put(position, Integer.valueOf(mresultBean.get(position).getId()));
                    goodsNumber++;
                    tv_merit_number.setText("已选择" + goodsNumber + "个");

                }
                TagmAdapter.addData(mresultBean);
            }
        });
    }


    /**
     * 缺点item点击事件
     */
    public void BadItemSelect(TagCloudLayout TagCloudLayout) {

        TagCloudLayout.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (selectList1.size() >= 6) {
                    if (mresultBean1.get(position).getSelect()) {
                        mresultBean1.get(position).setSelect(false);
                        selectList1.remove(position);
                        TagmAdapter1.addData(mresultBean1);
                        goodsNumber1--;
                        tv_bad_number.setText("已选择" + goodsNumber1 + "个");
                        return;
                    } else {
                        ToastUtil.showShort("最多选6个标签哦");
                        return;
                    }
                }
                if (mresultBean1.get(position).getSelect()) {
                    mresultBean1.get(position).setSelect(false);
                    selectList1.remove(position);
                    goodsNumber1--;
                    tv_bad_number.setText("已选择" + goodsNumber1 + "个");

                } else {
                    mresultBean1.get(position).setSelect(true);
                    selectList1.put(position, Integer.valueOf(mresultBean1.get(position).getId()));
                    goodsNumber1++;
                    tv_bad_number.setText("已选择" + goodsNumber1 + "个");
                }
                TagmAdapter1.addData(mresultBean1);
            }
        });
    }


}

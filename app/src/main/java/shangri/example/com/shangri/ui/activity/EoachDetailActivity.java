package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.fyales.tagcloud.library.TagCloudLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.PatrolPresenter;
import shangri.example.com.shangri.presenter.view.PatrolView;
import shangri.example.com.shangri.ui.adapter.DetailImageAdapter;
import shangri.example.com.shangri.ui.adapter.TagLabelAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.VoicePlayingBgUtil;

/**
 * mvp复制类
 * Created by admin on 2017/12/22.
 */

public class EoachDetailActivity extends BaseActivity<PatrolView, PatrolPresenter> implements PatrolView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_warn)
    TextView tvWarn;
    @BindView(R.id.im_photo)
    CircleImageView imPhoto;
    @BindView(R.id.tv_anchors_name)
    TextView tvAnchorsName;
    @BindView(R.id.tv_guild_name)
    TextView tvGuildName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_send_type)
    TextView tvSendType;
    @BindView(R.id.tag_cloud_layout)
    TagCloudLayout tagCloudLayout;
    @BindView(R.id.tag_cloud_layout1)
    TagCloudLayout tagCloudLayout1;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_send_photo)
    CircleImageView tv_send_photo;
    @BindView(R.id.show_image)
    RecyclerView showImage;
    @BindView(R.id.tv_read_number)
    TextView tvReadNumber;
    @BindView(R.id.tv_time_second)
    TextView tv_time_second;
    @BindView(R.id.read_image)
    RecyclerView readImage;

    @BindView(R.id.ll_video_hiden)
    LinearLayout ll_video_hiden;


    @BindView(R.id.im_text)
    ImageView im_text;
    private ProgressDialogFragment mProgressDialog;


    private TagLabelAdapter TagmAdapter;
    private TagLabelAdapter TagmAdapter1;
    private List<LabelDataBean.TagsBean> mresultBean = new ArrayList<>();
    private List<LabelDataBean.TagsBean> mresultBean1 = new ArrayList<>();


    //选择图片时候的数据
    private List<String> imageList = new ArrayList<>();
    private DetailImageAdapter ImageAdapter;
    private DetailImageAdapter ImageAdapter1;


    private List<String> StringList = new ArrayList<>();
    PatrolDataBean.InspectsBean bean;

    //音频地址
    String path = "";

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_coachdetail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_coachdetail;
    }

    @Override
    protected void initViewsAndEvents() {

        //优点
        TagmAdapter = new TagLabelAdapter(EoachDetailActivity.this, mresultBean);
        tagCloudLayout.setAdapter(TagmAdapter);
        //缺点
        TagmAdapter1 = new TagLabelAdapter(EoachDetailActivity.this, mresultBean1);
        tagCloudLayout1.setAdapter(TagmAdapter1);


        //显示图片适配器
        showImage.setLayoutManager(new GridLayoutManager(EoachDetailActivity.this, 3));
        ImageAdapter = new DetailImageAdapter(EoachDetailActivity.this, R.layout.item_image, imageList);
//        TagmAdapter.addData(mresultBean);
        showImage.setAdapter(ImageAdapter);
        ImageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(EoachDetailActivity.this, ZoomImgActivity.class);
                intent.putStringArrayListExtra(ZoomImgActivity.URL, (ArrayList<String>) imageList);
                intent.putExtra(ZoomImgActivity.PAGEINDEX, position);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        //显示阅读人适配器
        readImage.setLayoutManager(new LinearLayoutManager(EoachDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter1 = new DetailImageAdapter(EoachDetailActivity.this, R.layout.item_read_image, StringList);
        readImage.setAdapter(ImageAdapter1);


        bean = (PatrolDataBean.InspectsBean) getIntent().getSerializableExtra("Bean");
        if (bean != null) {
            initEvrnt(bean);
        } else {
            String inspect_id = getIntent().getStringExtra("inspect_id");
            mPresenter.inspectDetail(inspect_id);
        }
        im_text.setImageResource(R.mipmap.new_yuyin_wanzheng);
    }

    /**
     * 做传过来值的操作
     */
    private void initEvrnt(PatrolDataBean.InspectsBean bean) {

        if (bean != null) {

            Glide.with(EoachDetailActivity.this)
                    .load(bean.getAvatar_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .into(imPhoto);
            Glide.with(EoachDetailActivity.this)
                    .load(bean.getCreater_avatar())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .into(tv_send_photo);

            tvAnchorsName.setText(bean.getAnchor_name() + "");
            tvGuildName.setText("公会名称：" + bean.getGuild_name());

            tvTime.setText("辅导日期：" + bean.getInspect_date() + "");
            Log.d("Debug", "返回的时间为" + bean.getInspect_date());
            if (UserConfig.getInstance().getRole().equals("1")) {
                //1是会长  2是管理员
                if (bean.getType() == 1) {
                    tvSendType.setText("我");
                    tvWarn.setVisibility(View.VISIBLE);
                } else {
                    tvSendType.setText("管理员");
                    tvWarn.setVisibility(View.GONE);
                }
//                tvWarn.setVisibility(View.VISIBLE);
                //已读  和未读的数量
                int size = bean.getRead().size();
                int size1 = bean.getNo_read().size();
                tvReadNumber.setText("已读 " + size + "/" + (size1 + size));
                if (size == 0) {
                    readImage.setVisibility(View.GONE);
                } else {
                    for (int i = 0; i < bean.getRead().size(); i++) {
                        StringList.add(bean.getRead().get(i).getAvatar_url());
                    }
                    readImage.setVisibility(View.VISIBLE);
                }
                ImageAdapter1.setData(StringList);

            } else if (UserConfig.getInstance().getRole().equals("2")) {
                //1是会长  2是管理员
                if (bean.getType() == 1) {
                    tvSendType.setText("会长");
                } else {
                    tvSendType.setText("管理员");
                }
                mPresenter.inspectRead(bean.getId() + "");
                tvWarn.setVisibility(View.GONE);

                tvReadNumber.setVisibility(View.GONE);


            } else {
                //1是会长  2是管理员
                if (bean.getType() == 1) {
                    tvSendType.setText("会长");
                    tvWarn.setVisibility(View.GONE);
                } else {
                    tvSendType.setText("我");
                    tvWarn.setVisibility(View.VISIBLE);
                }
                mPresenter.inspectRead(bean.getId() + "");
                tvWarn.setVisibility(View.VISIBLE);
                //已读  和未读的数量
                int size = bean.getRead().size();
                int size1 = bean.getNo_read().size();
                tvReadNumber.setText("已读 " + size + "/" + (size1 + size));
                if (size == 0) {
                    readImage.setVisibility(View.GONE);
                } else {
                    for (int i = 0; i < bean.getRead().size(); i++) {
                        StringList.add(bean.getRead().get(i).getAvatar_url());
                    }
                    readImage.setVisibility(View.VISIBLE);
                }
                ImageAdapter1.setData(StringList);
            }
            //缺点集合
            for (int i = 0; i < bean.getBad_tags().size(); i++) {
                LabelDataBean.TagsBean tagsBean = new LabelDataBean.TagsBean(bean.getBad_tags().get(i));
                mresultBean1.add(tagsBean);
            }
            for (int i = 0; i < bean.getGood_tags().size(); i++) {
                LabelDataBean.TagsBean tagsBean = new LabelDataBean.TagsBean(bean.getGood_tags().get(i));
                mresultBean.add(tagsBean);
            }

            TagmAdapter.addData(mresultBean);
            TagmAdapter1.addData(mresultBean1);

            if (bean.getComment().length() == 0) {
                tvComment.setVisibility(View.GONE);
            } else {
                tvComment.setVisibility(View.VISIBLE);
                tvComment.setText("评论:" + bean.getComment());
            }
            //图片显示
            path = bean.getAudio_url() + "";
            if (bean.getAudio_time() == null) {
                tv_time_second.setText("");
            } else {
                tv_time_second.setText(bean.getAudio_time() + "");
            }
            Log.d("Debug", "返回的音频地址是" + path + "返回时间" + bean.getAudio_time());
            if (path.length() == 0) {
                ll_video_hiden.setVisibility(View.GONE);
            } else {
                ll_video_hiden.setVisibility(View.VISIBLE);
            }

            if (bean.getPhoto_url() == null || bean.getPhoto_url().length() == 0) {
                showImage.setVisibility(View.GONE);
            } else {
                String s = bean.getPhoto_url();
                String a[] = s.split(",");
                for (String anA : a) {
                    imageList.add(anA);
                    Log.d("Debug", "返回的地址信息是" + anA);
                }
                showImage.setVisibility(View.VISIBLE);
            }
            ImageAdapter.setData(imageList);

            if (bean.getIs_alert() == 1) {
                tvWarn.setText("提醒查看");
            } else {
                tvWarn.setText("已提醒");
            }
        } else {
            ToastUtil.showShort("数据请求出错");
        }
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlay();
    }

    @OnClick({R.id.setting_back, R.id.tv_warn, R.id.ll_start_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_warn:
                if (bean.getIs_alert() == 1) {
                    mPresenter.getAlite(bean.getId() + "");
                }
                break;
            case R.id.ll_start_video:
                Log.d("Debug", "点击播放音频操作");
                if (IsPlay) {
                    stopPlay();
                } else {
                    startPlay();
                }
                break;

        }
    }

    //语音操作对象
    private MediaPlayer mPlayer = null;
    Boolean IsPlay = false;

    /**
     * 下面是播放音频的操作
     */
    public void startPlay() {
        if (path.length() == 0) {
            ToastUtil.showShort("音频出现问题，暂不能播放");
            return;
        }
        IsPlay = true;
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        if (mPlayer.isPlaying()) {
            return;
        }
        try {
            VoicePlayingBgUtil.playAudioAnimation(im_text);
            mPlayer.setDataSource(path);
            mPlayer.prepare();
            mPlayer.start();
            if (bean.getAudio_time() != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (IsPlay) {
                            stopPlay();
                        }
                    }
                }, Integer.parseInt(bean.getAudio_time().replace("″", "")) * 1000);
            }
        } catch (IOException e) {
            Log.e("Debug", "播放失败");
        }
    }

    /**
     * 停止播放音频
     */
    public void stopPlay() {
        IsPlay = false;
        if (mPlayer != null) {
            VoicePlayingBgUtil.stopTimer();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void PatrolsListData(PatrolDataBean resultBean) {

    }

    @Override
    public void requestTaskList(NewTaskDataBean resultBean) {

    }

    @Override
    public void listGuildData(ChoiceGuildBean resultBean) {

    }

    @Override
    public void companyMy(companyMyBean resultBean) {

    }

    //辅导详情
    @Override
    public void inspectDetail(PatrolDataBean.InspectsBean resultBean) {
        bean = resultBean;
        initEvrnt(bean);
    }

    @Override
    public void getalert() {
        tvWarn.setText("已提醒");
    }

    @Override
    public void inspectRead() {
        Log.d("Debug", "设置提醒请求成功");
    }

    @Override
    public void taskAoalert() {

    }

    @Override
    public void partIn() {

    }

    @Override
    public void taskRepeal() {

    }

    @Override
    public void memberLate(timeBean shareBean) {

    }

    @Override
    protected PatrolPresenter createPresenter() {
        return new PatrolPresenter(this, this);
    }
}

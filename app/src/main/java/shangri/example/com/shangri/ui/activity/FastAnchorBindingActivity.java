package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.adapter.PlatSelectAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2017/12/30.
 */

public class FastAnchorBindingActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {


    boolean isRemember = false;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_host_type)
    TextView tvHostType;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_select_type)
    ImageView ivSelectType;
    @BindView(R.id.rl_platform)
    RelativeLayout rlPlatform;
    @BindView(R.id.tv_panda)
    TextView tvPanda;
    @BindView(R.id.tv_platform)
    TextView tvPlatform;
    @BindView(R.id.iv_select_platform)
    ImageView ivSelectPlatform;

    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.im3)
    ImageView im3;
    @BindView(R.id.rl_type)
    RelativeLayout rlType;
    @BindView(R.id.et_live_id)
    EditText etLiveId;

    private PhotoConfig mPhotoConfig;
    private String mCompressPath = ""; //图片的压缩路径

    //图片的位置
    int PhotoPosition;
    String photo1 = "";
    String photo2 = "";
    String photo3 = "";
    private ProgressDialogFragment mProgressDialog;
    private PopupWindow mPopWindow2;
    private String anchor_type;
    private ArrayList<BossPlatBean.PlatfromBean> mNewsList2;
    BossPlatBean BossplatBean;
    PlatSelectAdapter TypeAdapter1 = null;
    private String plat_name;
    private List<String> photoData = new ArrayList<>();

    @Override
    protected void initViewsAndEvents() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mPresenter.platfromType();

        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.d("Debug", "本地上传图片返回的列表地址" + result.getImages().toString());
                Log.d("Debug", "本地上传图片返回的单个地址" + result.getImage().toString());
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                TImage image = images.get(0);
                mCompressPath = image.getCompressPath();
                photoData.add(mCompressPath);
                switch (PhotoPosition) {
                    case 1:
                        photo1 = mCompressPath;
                        break;
                    case 2:
                        photo2 = mCompressPath;
                        break;
                    case 3:
                        photo3 = mCompressPath;
                        break;
                }

                Log.d("Debug", "mCompressPath信息为" + mCompressPath);
                if (TextUtils.isEmpty(mCompressPath)) return;
                mProgressDialog.show(FastAnchorBindingActivity.this.getSupportFragmentManager());

                mPresenter.UploadingImg(PhotoPosition, mCompressPath, 1);


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
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fast_anchorbinding;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fast_anchorbinding;
    }


    String pingtainame = "";
    String platfrom_id = "";
    String table_flag = "";


    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPhotoConfig.getTakePhoto(this).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            pingtainame = data.getStringExtra("pingtainame");
            platfrom_id = data.getStringExtra("id");
            table_flag = data.getStringExtra("table_flag");
            Log.d("Debug", "返回的table_flag为" + table_flag);
            if (table_flag == null) {
                table_flag = "";
            }
            tvPlatform.setText(pingtainame);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, mPhotoConfig.getInvokeParam(), mPhotoConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPhotoConfig.getTakePhoto(this).onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this, true);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//        EventBus.getDefault().post(new BrowseEventBean());
//        showPopupWindowSevenDays();
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {

    }

    //绑定快速公会成功
    @Override
    public void guildUpgrade(AddSeccussBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void bingSuccess() {
        ToastUtil.showShort("绑定成功");
        finish();

    }

    /**
     * 平台直播类型接口
     */
    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {
        BossplatBean = mAccountDataBean;
    }

    @Override
    public void Uploading(legalIndexBean resultBean) {


        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        String file_url = resultBean.getFile_url();
        switch (PhotoPosition) {
            case 1:
                Glide.with(FastAnchorBindingActivity.this)
                        .load(file_url)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im1);
                break;
            case 2:
                Glide.with(FastAnchorBindingActivity.this)
                        .load(file_url)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im2);
                break;
            case 3:
                Glide.with(FastAnchorBindingActivity.this)
                        .load(file_url)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im3);
                break;


        }

    }

    private PopupWindow mPopWindowSelectdays;

    /**
     *
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("主上，您已成功绑定，快去体验一下吧。");
        tv_next.setText("我先体验");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getInstance().finishActivity(BindingAnchorGuildeTypectivity.class);
                finish();
                mPopWindowSelectdays.dismiss();
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.fast_anchorbinding, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    @OnClick({R.id.tv_submit, R.id.rl_platform, R.id.setting_back,
            R.id.rl_type, R.id.im1, R.id.im2, R.id.im3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (TextUtils.isEmpty(anchor_type)) {
                    ToastUtil.showShort("请选择直播类型");
                    return;
                }
                if (TextUtils.isEmpty(platfrom_id)) {
                    ToastUtil.showShort("请选择直播平台");
                    return;
                }

                String live_id = etLiveId.getText().toString().trim();

                if (TextUtils.isEmpty(live_id)) {
                    ToastUtil.showShort("请填写直播间ID");
                    return;
                }
                if (photoData.isEmpty()){
                    ToastUtil.showShort("上传图片不能为空");
                    return;
                }



                mProgressDialog.show(FastAnchorBindingActivity.this.getSupportFragmentManager());
                mPresenter.anchorfastAdd(platfrom_id, live_id, anchor_type);

                break;
            case R.id.rl_platform:
                Intent mintent = new Intent(this, FastGuildListActivity.class);
                mintent.putExtra("isfromAnchorFast", true);
                startActivityForResult(mintent, 1);

                break;
            case R.id.rl_type:
                showPopupWindow2();
                break;
            case R.id.im1:
                PhotoPosition = 1;
                photo();
                break;
            case R.id.im2:
                PhotoPosition = 2;
                photo();
                break;
            case R.id.im3:
                PhotoPosition = 3;
                photo();
                break;


            case R.id.setting_back:        //返回
                finish();
                break;
        }
    }


    public void photo() {
        new SelectPhotoPopopWindow(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                mPhotoConfig.selectFromAlbum(1);
            }

            @Override
            public void takePhoto() {
                mPhotoConfig.takePhoto();
            }

            @Override
            public void savePhoto() {
                //这里会改成Rxjava2加载
//
            }
        }, "取消");

    }


    /**
     * 主播类型
     */
    private void showPopupWindow2() {
        //设置contentView
        final View contentView = LayoutInflater.from(FastAnchorBindingActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow2 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow2.setContentView(contentView);
        final TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);

        tv_type.setText("选择主播类型");
        mNewsList2 = new ArrayList<>();

        for (int i = 0; i < BossplatBean.getRecuitType().size(); i++) {
            BossPlatBean.RecuitTypeBean recuitTypeBean = BossplatBean.getRecuitType().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(recuitTypeBean.getId(), recuitTypeBean.getType_name(), recuitTypeBean.getStatus(), false);
            mNewsList2.add(platfromBean);
        }
        if (!TextUtils.isEmpty(anchor_type)) {
            String[] split = anchor_type.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList2.size(); j++) {
                    if (mNewsList2.get(j).getId().equals(split[i])) {
                        mNewsList2.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter1 == null) {
            TypeAdapter1 = new PlatSelectAdapter(FastAnchorBindingActivity.this, R.layout.item_four, mNewsList2);
            TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter1.setData(mNewsList2);
        }

        card_recycler.setLayoutManager(new GridLayoutManager(FastAnchorBindingActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter1);
        final List<Integer> isClickData = new ArrayList<>();
        TypeAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);

                if (TypeAdapter1.get(position).isClick()) {
                    Log.d("Debug", "点击的位置" + position);

                    TypeAdapter1.get(position).setClick(false);

                    for (int j = 0; j < isClickData.size(); j++) {
                        if (isClickData.get(j) == position) {
                            isClickData.remove(j);
                            TypeAdapter1.get(j).setClick(false);
                            TypeAdapter1.notifyItemChanged(j);

                        }

                    }


                } else {


                    if (isClickData.size() < 3) {
                        isClickData.add(position);
                        TypeAdapter1.get(position).setClick(true);
                        TypeAdapter1.notifyDataSetChanged();

                    } else {
                        for (int j = 0; j < isClickData.size(); j++) {
                            if (isClickData.get(j) == position) {
                                isClickData.remove(j);
                                TypeAdapter1.notifyItemChanged(j);
                            }

                        }

                        TypeAdapter1.get(position).setClick(false);
                    }
//                    TypeAdapter1.get(position).setClick(true);
                }


                TypeAdapter1.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
//                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                    if (TypeAdapter1.get(i).isClick()) {
                        if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                            sb.append(",");
                        } else {
                            plat_name = TypeAdapter1.get(i).getPlat_name();
                            TypeAdapter1.notifyDataSetChanged();
                            if (isClickData.size()>1){
                                tvType.setText(TypeAdapter1.get(i).getPlat_name() + "...");
                            } else if (isClickData.size()==1){
                                tvType.setText(TypeAdapter1.get(i).getPlat_name());
                            }
//                            TypeAdapter1.notifyDataSetChanged();
                        }

                        sb.append(TypeAdapter1.getAll().get(i).getId());
                    }
                }
                anchor_type = sb + "";
                mPopWindow2.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(FastAnchorBindingActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}

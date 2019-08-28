package shangri.example.com.shangri.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.request.ImageInfo;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.wantedStatusBean;
import shangri.example.com.shangri.presenter.ResumePresenter;
import shangri.example.com.shangri.presenter.view.ResumeView;
import shangri.example.com.shangri.ui.adapter.PlatSelectAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.JsonUitl;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.wheelviewSelectDays.ChangeDatePopwindow;
import shangri.example.com.shangri.wheelviewSelectDays.MyChangeChangeDatePopwindow;
import shangri.example.com.shangri.wheelviewSelectDays.MyChangeChangeDatePopwindow1;

/**
 * 上传简历界面
 * Created by mschen on 2017/6/23.
 */

public class AddInviteActivity extends BaseActivity<ResumeView, ResumePresenter> implements ResumeView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.im3)
    ImageView im3;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.im_nv)
    ImageView imNv;
    @BindView(R.id.ll_select_nv)
    LinearLayout llSelectNv;
    @BindView(R.id.im_nan)
    ImageView imNan;
    @BindView(R.id.ll_select_nan)
    LinearLayout llSelectNan;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.rl_select_age)
    RelativeLayout rlSelectAge;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.im11)
    ImageView im11;
    @BindView(R.id.im_12)
    ImageView im12;
    @BindView(R.id.tv_stature)
    TextView tvStature;
    @BindView(R.id.iv_stature)
    ImageView ivStature;
    @BindView(R.id.rl_select_stature)
    RelativeLayout rlSelectStature;
    @BindView(R.id.tv_kg)
    TextView tvKg;
    @BindView(R.id.iv_kg)
    ImageView ivKg;
    @BindView(R.id.rl_kg)
    RelativeLayout rlKg;
    @BindView(R.id.im_13)
    ImageView im13;
    @BindView(R.id.ll_select_term)
    LinearLayout llSelectTerm;
    @BindView(R.id.im_14)
    ImageView im14;
    @BindView(R.id.ll_select_salary)
    LinearLayout llSelectSalary;
    @BindView(R.id.et_personal_strength)
    EditText etPersonalStrength;
    @BindView(R.id.iv_yes)
    ImageView ivYes;
    @BindView(R.id.iv_no)
    ImageView ivNo;
    @BindView(R.id.cl_biography)
    ConstraintLayout clBiography;
    @BindView(R.id.tv_term)
    TextView tvTerm;
    @BindView(R.id.rl_term)
    RelativeLayout rlTerm;
    @BindView(R.id.tv_basic_salary)
    TextView tvBasicSalary;
    @BindView(R.id.rl_basic_salary)
    RelativeLayout rlBasicSalary;
    @BindView(R.id.tv_job_objective)
    TextView tvJobObjective;
    @BindView(R.id.tv_job_type)
    TextView tv_job_type;
    @BindView(R.id.tv_skip)
    TextView tv_skip;
    //点击跳过的 引导图片
    @BindView(R.id.im_show_image)
    ImageView im_show_image;


    @BindView(R.id.im_fenmian)
    ImageView im_fenmian;


    //       是否公开 简历
    private int open = 1;

    private ProgressDialogFragment mProgressDialog;

    private PhotoConfig mPhotoConfig;
    private String mCompressPath = ""; //图片的压缩路径
    private String mCompressPath1 = ""; //图片的压缩路径
    private String mCompressPath2 = ""; //图片的压缩路径
    //图片的位置
    int PhotoPosition;
    String photo1 = "";
    String photo2 = "";
    String photo3 = "";


    //    性别 1男2女
    String sex = "2";
    String age;
    //生日的时间戳
    String TimeaLong;
    //    true 为更改  false为添加
    Boolean isFromUpdate;

    //    true 来自选择身份界面  返回键消失 跳过按钮显示
    Boolean isFromSelectRoles;
    //接收图片地址
    List<ReadPhotoBean.ResumeBean> PhoneList = new ArrayList<>();
    //上传图片地址
    List<ImageInfo> UploadList = new ArrayList<>();
    //    求职状态ID
    String wanted_status = "";
    ResumeIndexBean.ResumeBean resume;
    //编辑简历时候传的参数id
    String resume_id = "";
    private int year;
    private int stature;
    private int kg;
    private String low_pay;
    private String high_pay;
    private String plat_name;

    //是否是默认图片    默认图片 弹出上传图片的弹窗 不是默认图片弹出  设置封面删除图片界面
    public boolean isDefaultImage;

    @Override
    protected ResumePresenter createPresenter() {
        return new ResumePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        isFromSelectRoles = getIntent().getBooleanExtra("isFromSelectRoles", false);
        if (isFromSelectRoles) {
            settingBack.setVisibility(View.GONE);
            tv_skip.setVisibility(View.VISIBLE);
        } else {
            settingBack.setVisibility(View.VISIBLE);
            tv_skip.setVisibility(View.GONE);
        }
        isFromUpdate = getIntent().getBooleanExtra("isFromUpdate", false);
        mPresenter.platfromType();
        getPhotoList();
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.d("Debug", "本地上传图片返回的列表地址" + result.getImages().toString());
                Log.d("Debug", "本地上传图片返回的单个地址" + result.getImage().toString());
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                TImage image = images.get(0);
                mCompressPath = image.getCompressPath();
                switch (PhotoPosition) {
                    case 1:
                        photo1 = mCompressPath;
                        Glide.with(AddInviteActivity.this)
                                .load(photo1)
                                .placeholder(R.mipmap.tianjia_tupian)
                                .crossFade()
                                .into(im1);
                        break;
                    case 2:
                        photo2 = mCompressPath;
                        Glide.with(AddInviteActivity.this)
                                .load(photo2)
                                .placeholder(R.mipmap.tianjia_tupian)
                                .crossFade()
                                .into(im2);
                        break;
                    case 3:
                        photo3 = mCompressPath;
                        Glide.with(AddInviteActivity.this)
                                .load(photo3)
                                .placeholder(R.mipmap.tianjia_tupian)
                                .crossFade()
                                .into(im3);
                        break;
                }
                if (photo1.length() > 0) {
                    im_fenmian.setVisibility(View.VISIBLE);
                } else {
                    im_fenmian.setVisibility(View.GONE);
                }

                Log.d("Debug", "mCompressPath信息为" + mCompressPath);
                if (TextUtils.isEmpty(mCompressPath)) return;
//                if (mProgressDialog == null) {
//                    mProgressDialog = new ProgressDialogFragment();
//                }
//                mProgressDialog.show(AddInviteActivity.this.getSupportFragmentManager());
//                Log.d("Debug", "resume为" + resume_id);
//                mPresenter.resumeUploading(PhotoPosition, mCompressPath, 0, resume_id);
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


        if (isFromUpdate) {
            resume = (ResumeIndexBean.ResumeBean) getIntent().getSerializableExtra("bean");
            if (resume.getAnchor_type() == null) {
                anchor_type = "";
            } else {
                anchor_type = resume.getAnchor_type();
            }
            job_plat = resume.getJob_plat() + "";
            room_id = resume.getRoom_id() + "";
            wanted_status = resume.getWanted_status() + "";
            //性别
            sex = resume.getSex();
            if ("2".equals(sex)) {
                imNv.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                imNan.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
            } else {
                imNv.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
                imNan.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
            }
            age = resume.getAge();
            tvAge.setText(age + "岁");

            tv_job_type.setText(resume.getWanted_status_name() + "");

            tvStature.setText(resume.getHeight() + "cm");
            tvKg.setText(resume.getWeight() + "kg");
            tvTerm.setText(resume.getLive_age() + "年");
            int low_pay = Integer.valueOf(resume.getPay_low()) / 1000;
            int high_pay = Integer.valueOf(resume.getPay_high()) / 1000;
            tvBasicSalary.setText(low_pay + "K" + "-" + high_pay + "K");

            if (resume.getType_name().size() > 1) {
                String s = resume.getType_name().get(0);
                if (!TextUtils.isEmpty(s)) {
                    tvJobObjective.setText(s + "...");
                }
            }

            if (resume.getType_name().size() == 1) {
                String s = resume.getType_name().get(0);
                if (!TextUtils.isEmpty(s)) {
                    tvJobObjective.setText(s);
                }
            }

            etPersonalStrength.setText(resume.getPer_style());
            TimeaLong = resume.getDate_of_birth();
            etName.setText(resume.getNickname() + "");
            tvPhone.setText(resume.getTelephone() + "");
            tv_title.setText("编辑简历");
            resume_id = resume.getId() + "";
        } else {
            tv_title.setText("上传简历");
            tvPhone.setText(UserConfig.getInstance().getMobile() + "");
        }

    }

    /**
     * 获取图片
     *
     * @param
     */
    public void getPhotoList() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(AddInviteActivity.this.getSupportFragmentManager());
        mPresenter.readPhoto();
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyBoardUtil.KeyBoard(AddInviteActivity.this, "close");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_invite_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_invite_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        mPhotoConfig = new PhotoConfig(this, true);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mPhotoConfig.getTakePhoto(this).onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPhotoConfig.getTakePhoto(this).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, mPhotoConfig.getInvokeParam(), mPhotoConfig);
    }


    @OnClick({R.id.tv_skip, R.id.im_show_image, R.id.tv_job_type, R.id.setting_back, R.id.im1, R.id.im2, R.id.im3, R.id.tv_job_objective,
            R.id.iv_yes, R.id.iv_no, R.id.rl_select_stature, R.id.rl_basic_salary,
            R.id.ll_select_nv, R.id.ll_select_nan, R.id.rl_select_age,
            R.id.tv_commit, R.id.rl_kg, R.id.rl_term})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                if (isFromUpdate) {
                    showPopupWindow5(1);
                } else {
                    showPopupWindow5(2);
                }
                break;

            //跳过 只有当选完身份有跳过按钮
            case R.id.tv_skip:
                showPopupWindow5(2);
                break;
            //点击引导图 跳转到mainActivity
            case R.id.im_show_image:
                ActivityUtils.startActivity(this, MainActivity.class);
                break;
            //身高选择
            case R.id.rl_select_stature:
                selectStature();
                break;
            //公开简历
            case R.id.iv_yes:
                open = 1;
                ivYes.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                ivNo.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
                break;
            case R.id.iv_no:
                ivNo.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                ivYes.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
                open = 2;
                break;
            case R.id.rl_kg:
//                体重选择
                selectKg();
                break;
//                  直播年限
            case R.id.rl_term:
                selectTerm();
                break;
//     期望底薪
            case R.id.rl_basic_salary:
                selectBasicSalary();
                break;
            case R.id.im1:
                PhotoPosition = 1;
                if (photo1.length() > 0) {
                    isDefaultImage = false;
                } else {
                    isDefaultImage = true;
                }
                photo(isDefaultImage, PhotoPosition);
                break;
            case R.id.im2:
                PhotoPosition = 2;
                if (photo1.length() == 0) {
                    ToastUtil.showShort("请按顺序上传图片");
                    return;
                }
                if (photo2.length() > 0) {
                    isDefaultImage = false;
                } else {
                    isDefaultImage = true;
                }
                photo(isDefaultImage, PhotoPosition);
                break;
            case R.id.im3:
                PhotoPosition = 3;
                if (photo2.length() == 0) {
                    ToastUtil.showShort("请按顺序上传图片");
                    return;
                }
                if (photo3.length() > 0) {
                    isDefaultImage = false;
                } else {
                    isDefaultImage = true;
                }
                photo(isDefaultImage, PhotoPosition);
                break;
//           选择主播类型
            case R.id.tv_job_objective:
                showPopupWindow2();
                break;
            //求职状态
            case R.id.tv_job_type:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AddInviteActivity.this.getSupportFragmentManager());
                mPresenter.wantedStatus();
//                ToastUtil.showShort("求职状态");
                break;
            case R.id.ll_select_nv:
                imNv.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                imNan.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
                sex = "2";
                break;
            case R.id.ll_select_nan:
                imNv.setImageDrawable(getResources().getDrawable(R.mipmap.yuan_wei));
                imNan.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                sex = "1";
                break;
            case R.id.rl_select_age:

                KeyBoardUtil.KeyBoard(AddInviteActivity.this, "close");
                selectDate();
                Log.d("Debug", "时间戳为" + TimeaLong);
                break;
            case R.id.tv_commit:
                if (photo1.length() == 0) {
                    ToastUtil.showShort("至少上传一张图片");
                    return;
                }
//                if (photo2.length() == 0) {
//                    ToastUtil.showShort("第二张图片不能为空");
//                    return;
//                }
//                if (photo3.length() == 0) {
//                    ToastUtil.showShort("第三张图片不能为空");
//                    return;
//                }
                if (etName.getText().toString().length() == 0) {
                    ToastUtil.showShort("姓名不能为空");
                    return;
                }
                if (tvAge.getText().toString().length() == 0) {
                    ToastUtil.showShort("年龄不能为空");
                    return;
                }
                if (tvPhone.getText().toString().length() == 0) {
                    ToastUtil.showShort("联系方式不能为空");
                    return;
                }
                if (TextUtils.isEmpty(tvTerm.getText().toString().trim())) {
                    ToastUtil.showShort("直播年限不能为空");
                    return;
                }
                if (tvJobObjective.getText().toString().length() == 0) {
                    ToastUtil.showShort("求职意向不能为空");
                    return;
                }
                if (tv_job_type.getText().toString().length() == 0) {
                    ToastUtil.showShort("求职状态不能为空");
                    return;
                }


                ResumeAddBean resumeAddBean = new ResumeAddBean();
                resumeAddBean.setSex(sex);
                resumeAddBean.setAge(age);
                resumeAddBean.setIs_open(1);
                String s = tvTerm.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    String live_age = s.replace("年", "");

                    resumeAddBean.setLive_age(Integer.parseInt(live_age));
                }
                String s1 = tvKg.getText().toString().trim();
                if (!TextUtils.isEmpty(s1)) {
                    String kg = s1.replace("kg", "");
                    resumeAddBean.setWeight(Integer.parseInt(kg));
                }
                String s2 = tvStature.getText().toString().trim();
                if (!TextUtils.isEmpty(s2)) {
                    String cm = s2.replace("cm", "");
                    resumeAddBean.setHeight(Integer.parseInt(cm));
                }

                resumeAddBean.setJob_method(anchor_type);


                String s3 = tvBasicSalary.getText().toString().trim();
                if (!TextUtils.isEmpty(s3)) {
                    String[] split = s3.split("-");
                    String s4 = split[0];
                    String s5 = split[1];
                    String k = s4.replace("K", "");
                    String k2 = s5.replace("K", "");

                    resumeAddBean.setPay_low(Integer.valueOf(k) * 1000);
                    resumeAddBean.setPay_high(Integer.valueOf(k2) * 1000);
                } else {
                    resumeAddBean.setPay_low(0);
                    resumeAddBean.setPay_high(0);
                }

                resumeAddBean.setPer_style(etPersonalStrength.getText().toString());
                resumeAddBean.setDate_of_birth(TimeaLong + "");
                if (anchor_type.length() > 0) {
                    resumeAddBean.setAnchor_type(anchor_type);
                }
                if (job_plat.length() > 0) {
                    resumeAddBean.setJob_plat(job_plat);
                }

                resumeAddBean.setNickname(etName.getText().toString());
                resumeAddBean.setTelephone(tvPhone.getText().toString());
                resumeAddBean.setWanted_status(wanted_status);
                if (UploadList.size() > 0) {
                    UploadList.clear();
                }
                Log.d("Debug", "photo1值为" + photo1 + "photo2值为" + photo2 + "photo3值为" + photo3);

                if (photo1.length() > 0) {
                    //图片是网络地址  直接添加   图片是本地地址   传base64形式
                    if (photo1.contains("http")) {
                        UploadList.add(new ImageInfo("1", photo1));
                    } else {
                        try {
                            UploadList.add(new ImageInfo("1", Base64Utils.encodeFile(photo1)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (photo2.length() > 0) {
                    //图片是网络地址  直接添加   图片是本地地址   传base64形式
                    if (photo2.contains("http")) {
                        UploadList.add(new ImageInfo("2", photo2));
                    } else {
                        try {
                            UploadList.add(new ImageInfo("2", Base64Utils.encodeFile(photo2)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (photo3.length() > 0) {
                    //图片是网络地址  直接添加   图片是本地地址   传base64形式
                    if (photo3.contains("http")) {
                        UploadList.add(new ImageInfo("3", photo3));
                    } else {
                        try {
                            UploadList.add(new ImageInfo("3", Base64Utils.encodeFile(photo3)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                //上传图片
                resumeAddBean.setResume_photo(JsonUitl.objectToString(UploadList));
                Log.d("Debug", "图片的劲松数据是" + JsonUitl.objectToString(UploadList));

                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(AddInviteActivity.this.getSupportFragmentManager());
                if (isFromUpdate) {
                    resumeAddBean.setResume_id(resume_id);
                    mPresenter.resumeUpdate(resumeAddBean);
                } else {
                    mPresenter.resumeAdd(resumeAddBean);
                }
                break;
        }
    }

    /**
     * 判断是不是base64编码
     *
     * @param str
     * @return
     */
    private static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }


    /**
     * 选择底薪
     */

    private void selectBasicSalary() {
        ArrayList<String> arry_months = new ArrayList<String>();
        ArrayList<String> arry_days = new ArrayList<String>();
        //    0是选择薪资  1是选择底薪
        for (int i = 1; i < 50; i++) {
            arry_months.add(i + "K");
        }
        for (int i = 2; i < 51; i++) {
            arry_days.add(i + "K");
        }

        MyChangeChangeDatePopwindow1 mChangeBirthDialog = new MyChangeChangeDatePopwindow1(this, 0, arry_months, arry_days
        );
        mChangeBirthDialog.showAtLocation(llSelectTerm, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new MyChangeChangeDatePopwindow1.OnBirthListener() {

            @Override
            public void onClick(int symbol, String year, String month, String day) {
                Log.d("Debug", "返回的symbol" + symbol);
                Log.d("Debug", "返回的year" + year);
                Log.d("Debug", "返回的month" + month);
                Log.d("Debug", "返回的day" + day);
                tvBasicSalary.setText(month + "-" + day);
            }
        });
    }

    /**
     * 直播年限
     */
    @SuppressLint("ResourceAsColor")
    private void selectTerm() {

        cn.qqtheme.framework.picker.NumberPicker picker = new cn.qqtheme.framework.picker.NumberPicker(this);
        picker.setUseWeight(true);
        picker.setItemWidth(160);
        picker.setCycleDisable(false);
        picker.setDividerVisible(true);
        picker.setTopLineVisible(false);
        picker.setOffset(2);//偏移量
        picker.setDividerColor(getResources().getColor(R.color.text_color_light_black));

        picker.setTextColor(getResources().getColor(R.color.black));
        picker.setCancelTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setSubmitTextColor(getResources().getColor(R.color.text_color_light_black));
        picker.setTextSize(15);
        picker.setCancelTextSize(14);
        picker.setSubmitTextSize(14);
        picker.setSubmitText("完成");

        picker.setRange(1, 10, 1);//数字范围
        picker.setSelectedItem(1);
        picker.setLabelTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setLabel("年");

        picker.setOnNumberPickListener(new cn.qqtheme.framework.picker.NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                tvTerm.setText(item.intValue() + "年");
                year = item.intValue();
            }
        });
        picker.show();

    }

    /**
     * 选择体重
     */
    @SuppressLint("ResourceAsColor")
    private void selectKg() {
        cn.qqtheme.framework.picker.NumberPicker picker = new cn.qqtheme.framework.picker.NumberPicker(this);
        picker.setSubmitTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setCancelTextColor(getResources().getColor(R.color.text_color_light_black));
        picker.setUseWeight(true);
        picker.setItemWidth(160);
        picker.setCycleDisable(false);
        picker.setDividerVisible(true);
        picker.setTopLineVisible(false);
        picker.setOffset(2);//偏移量
        picker.setTextColor(getResources().getColor(R.color.black));
        picker.setLabelTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setDividerColor(getResources().getColor(R.color.text_color_light_black));
        picker.setTextSize(15);
        picker.setCancelTextSize(15);
        picker.setSubmitTextSize(15);
        picker.setSubmitText("完成");
        picker.setRange(50, 200, 1);//数字范围
        picker.setSelectedItem(50);
        picker.setLabel("kg");

        picker.setOnNumberPickListener(new cn.qqtheme.framework.picker.NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                kg = item.intValue();
                tvKg.setText(item.intValue() + "kg");
//                ToastUtil.showShort("index=" + index + ", item=" + item.intValue());
            }
        });
        picker.show();
    }

    /**
     * 选择身高
     */
    @SuppressLint("ResourceAsColor")
    private void selectStature() {
        cn.qqtheme.framework.picker.NumberPicker picker = new cn.qqtheme.framework.picker.NumberPicker(this);
        picker.setUseWeight(true);
        picker.setTopLineVisible(false);
        picker.setSubmitTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setCancelTextColor(getResources().getColor(R.color.text_color_light_black));
        picker.setCycleDisable(false);
        picker.setDividerVisible(false);
        picker.setItemWidth(160);
//        picker.setContentPadding(20, 10);
        picker.setOffset(2);//偏移量
        picker.setTextColor(getResources().getColor(R.color.black));
        picker.setTextSize(15);
        picker.setCancelTextSize(15);
        picker.setSubmitTextSize(15);
        picker.setSubmitText("完成");
//        picker.setTopPadding(20);
        picker.setLabelTextColor(getResources().getColor(R.color.text_color_light_yellow));
        picker.setDividerColor(getResources().getColor(R.color.text_color_light_black));
        picker.setRange(150, 200, 1);//数字范围
        picker.setSelectedItem(170);
        picker.setLabel("cm");

        picker.setOnNumberPickListener(new cn.qqtheme.framework.picker.NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
                stature = item.intValue();
                tvStature.setText(item.intValue() + "cm");
            }
        });
        picker.show();
    }

    public void photo(final Boolean isDefaultImage, final int imageposition) {
        if (isDefaultImage) {
            new SelectPhotoPopopWindow(this, new SelectPhotoListener() {

                @Override
                public void takePhoto() {
                    //和剪切比例有关  改变上传图片框架里面东西
                    GlobalApp.ImageScale = 1;
                    mPhotoConfig.takePhoto();
                }

                @Override
                public void selectFromAlbum() {
                    //和剪切比例有关  改变上传图片框架里面东西
                    GlobalApp.ImageScale = 1;
                    mPhotoConfig.selectFromAlbum(1);
                }

                @Override
                public void savePhoto() {
                    //这里会改成Rxjava2加载
//
                }
            }, "取消");
        } else {
            new SelectPhotoPopopWindow(this, "设置封面", "删除照片", new SelectPhotoListener() {

                @Override
                public void takePhoto() {
                    //设置封面
//                    mPresenter.resumePhotoFirst(imageposition);
                    switch (PhotoPosition) {
                        case 1:
                            break;
                        case 2:
                            //；两个字符串替换
                            Log.d("Debug", "没有替换之前的photo1" + photo1);
                            String s1 = photo1;
                            String s2 = photo2;
                            photo1 = s2;
                            photo2 = s1;
                            Log.d("Debug", "替换之后的photo1" + photo1);
                            reFushView();
                            break;
                        case 3:
                            String s4 = photo1;
                            String s5 = photo3;
                            photo1 = s5;
                            photo3 = s4;
                            reFushView();
                            break;
                    }
                }

                @Override
                public void selectFromAlbum() {
                    //删除照片
//                    mPresenter.resumePhotoDel(imageposition);
                    //删除对应位置  后面数据向上移
                    switch (PhotoPosition) {
                        case 1:
                            if (photo2.length() == 0 && photo3.length() == 0) {
                                photo1 = "";
                                photo2 = "";
                                photo3 = "";
//                        设置封面在这里操作（某一种可能）
                                reFushView();
                            } else if (photo2.length() > 0 && photo3.length() == 0) {
                                photo1 = photo2;
                                photo2 = "";
                                photo3 = "";
                                reFushView();
                            } else if (photo2.length() == 0 && photo3.length() > 0) {
                                photo1 = photo3;
                                photo2 = "";
                                photo3 = "";
                                reFushView();
                            } else if (photo2.length() > 0 && photo3.length() > 0) {
                                photo1 = photo2;
                                photo2 = photo3;
                                photo3 = "";
                                reFushView();
                            }
                            break;
                        case 2:
                            if (photo3.length() > 0) {
                                photo2 = photo3;
                                photo3 = "";
                                reFushView();
                            } else {
                                photo2 = "";
                                photo3 = "";
                                reFushView();
                            }
                            break;
                        case 3:
                            photo3 = "";
                            reFushView();
                            break;
                    }

                }

                @Override
                public void savePhoto() {
                    //这里会改成Rxjava2加载
//
                }
            }, "取消");
        }
    }

    @Override
    public void resumeUploading(legalIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void resumeAdd() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        UserConfig.getInstance().setIsresume("1");
        UserConfig.getInstance().setResumeTelephone(tvPhone.getText().toString());
        showPopupWindow5(3);
    }

    /**
     * 删除简历图片
     */
    @Override
    public void resumePhotoDel() {
    }


    /**
     * 设置封面或是删除以后界面本地刷新
     */
    public void reFushView() {

        if (photo1.length() > 0) {
            im_fenmian.setVisibility(View.VISIBLE);
        } else {
            im_fenmian.setVisibility(View.GONE);
        }

        Glide.with(AddInviteActivity.this)
                .load(photo1)
                .placeholder(R.mipmap.tianjia_tupian)
                .transform(new CornersTransform(AddInviteActivity.this, 10))
                .crossFade()
                .into(im1);
        Glide.with(AddInviteActivity.this)
                .load(photo2)
                .placeholder(R.mipmap.zhaopian_xuan)
                .transform(new CornersTransform(AddInviteActivity.this, 10))
                .crossFade()
                .into(im2);
        Glide.with(AddInviteActivity.this)
                .load(photo3)
                .placeholder(R.mipmap.gongzuo_hao)
                .transform(new CornersTransform(AddInviteActivity.this, 10))
                .crossFade()
                .into(im3);

    }


    /**
     * 设置封面
     */
    @Override
    public void resumePhotoFirst() {
    }

    @Override
    public void resumeUpdate() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("您已成功修改简历，并上传成功！");
        finish();
    }

    @Override
    public void readPhoto(ReadPhotoBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (PhoneList.size() > 0) {
            PhoneList.clear();
        }
        PhoneList = resultBean.getResume();
        if (PhoneList.size() == 0) {
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.tianjia_tupian)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im1);
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.zhaopian_xuan)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im2);
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.gongzuo_hao)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im3);
//             设置封面布局隐藏
            im_fenmian.setVisibility(View.GONE);
            photo1 = "";
            photo2 = "";
            photo3 = "";


        } else if (PhoneList.size() == 1) {
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.tianjia_tupian)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im1);
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.zhaopian_xuan)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im2);
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.gongzuo_hao)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im3);
            photo1 = PhoneList.get(0).getImg_url();
            im_fenmian.setVisibility(View.VISIBLE);

            photo2 = "";
            photo3 = "";
//            设置封面布局显示
        } else if (PhoneList.size() == 2) {
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.tianjia_tupian)
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .crossFade()
                    .into(im1);
            photo1 = PhoneList.get(0).getImg_url();
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.zhaopian_xuan)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im2);
            photo2 = PhoneList.get(1).getImg_url();
            Glide.with(AddInviteActivity.this)
                    .load("")
                    .placeholder(R.mipmap.gongzuo_hao)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im3);
            //            设置封面布局显示
            photo3 = "";
            im_fenmian.setVisibility(View.VISIBLE);
        } else {
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(0).getImg_url())
                    .placeholder(R.mipmap.tianjia_tupian)
                    .crossFade()
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .into(im1);
            photo1 = PhoneList.get(0).getImg_url();
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(1).getImg_url())
                    .placeholder(R.mipmap.zhaopian_xuan)
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .crossFade()
                    .into(im2);
            photo2 = PhoneList.get(1).getImg_url();
            Glide.with(AddInviteActivity.this)
                    .load(PhoneList.get(2).getImg_url())
                    .placeholder(R.mipmap.gongzuo_hao)
                    .transform(new CornersTransform(AddInviteActivity.this, 10))
                    .crossFade()
                    .into(im3);
            photo3 = PhoneList.get(2).getImg_url();
            im_fenmian.setVisibility(View.VISIBLE);
            //            设置封面布局显示
        }

    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {

    }


    private static PopupWindow mPopWindow5;

    /**
     * * 拨打电话
     */

//   1.简历修改返回键弹窗  2上传简历返回键弹窗(还有一种情况时   选择身份以后跳转过来  点击跳过按钮弹出)  3.上传简历成功弹窗
    private void showPopupWindow5(int type) {
        //设置contentView
        View contentView = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.compact_add_gonghui2, null);
        mPopWindow5 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow5.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        LinearLayout ll_commit = contentView.findViewById(R.id.ll_commit);

        //上传简历成功弹窗时候显示
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);

        switch (type) {
            case 1:
                ll_commit.setVisibility(View.VISIBLE);
                tv_commit.setVisibility(View.GONE);
                tv_cancle.setText("放弃");
                tv_next.setText("继续编辑");
                tv_content.setText("是否放弃修改？");
                break;
            case 2:
                ll_commit.setVisibility(View.VISIBLE);
                tv_commit.setVisibility(View.GONE);
                tv_cancle.setText("残忍拒绝");
                tv_next.setText("继续填写");
                tv_content.setText("成功上传自己的简历后，会给你50波币的奖励哦");
                break;
            case 3:
                ll_commit.setVisibility(View.GONE);
                tv_commit.setVisibility(View.VISIBLE);
                tv_commit.setText("确定");
                tv_content.setText("您已上传简历，待审核通过后，即可获得50波币的奖励！");
                break;
        }
        //状态为1 2  点击跳过按钮  时候有用
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击跳过按钮
                if (isFromSelectRoles) {
                    im_show_image.setVisibility(View.VISIBLE);
                    mPopWindow5.dismiss();
                } else {
                    finish();
                    mPopWindow5.dismiss();
                }

            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow5.dismiss();
            }
        });
        //状态为3 时候有用
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFromSelectRoles) {
                    ActivityUtils.startActivity(AddInviteActivity.this, MainActivity.class);
                }
                finish();
                mPopWindow5.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.activity_add_invite_layout, null);
        mPopWindow5.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isFromSelectRoles) {
                ToastUtil.showShort("请先上传简历");
            } else {
                if (isFromUpdate) {
                    showPopupWindow5(1);
                } else {
                    showPopupWindow5(2);
                }
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    private String[] selectDate() {
        final String[] str = new String[10];
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(
                this);
        mChangeBirthDialog.setDate("2018", "1", "1");
        mChangeBirthDialog.showAtLocation(llSelectNan, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                // TODO Auto-generated method stub
//                Toast.makeText(AddInviteActivity.this, year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
                Log.d("Debug", "选择的日期为" + year + "-" + month + "-" + day);
                age = TimeUtil.yearChect(year.replace("年", "")) + "";
                tvAge.setText(age + "岁");
                TimeaLong = TimeUtil.convertTimeToLong1(year.replace("年", "") + "-" + month.replace("月", "") + "-" + day.replace("日", "")) / 1000 + "";
                Log.d("Debug", "生日时间戳" + TimeaLong);
            }
        });
        return str;
    }

    PlatSelectAdapter TypeAdapter1 = null;
    BossPlatBean BossplatBean;
    List<BossPlatBean.PlatfromBean> mNewsList2;

    String anchor_type = "";
    String job_plat = "";
    String room_id = "";

    /**
     * 直播平台、直播类型接口
     *
     * @param mAccountDataBean
     */
    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {
        BossplatBean = mAccountDataBean;
        mNewsList2 = new ArrayList<>();
        for (int i = 0; i < BossplatBean.getRecuitType().size(); i++) {
            BossPlatBean.RecuitTypeBean recuitTypeBean = BossplatBean.getRecuitType().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(recuitTypeBean.getId(), recuitTypeBean.getType_name(), recuitTypeBean.getStatus(), false);
            mNewsList2.add(platfromBean);
        }
    }

    PlatSelectAdapter TypeAdapter2 = null;
    List<BossPlatBean.PlatfromBean> mNewsList3;

    /**
     * 获取求职状态
     *
     * @param resultBean
     */
    @Override
    public void wantedStatus(wantedStatusBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mNewsList3 = new ArrayList<>();
        for (int i = 0; i < resultBean.getList().size(); i++) {
            wantedStatusBean.ListBean listBean = resultBean.getList().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(listBean.getId() + "", listBean.getTitle(), false);
            mNewsList3.add(platfromBean);
        }
        showPopupWindow3();
    }


    private static PopupWindow mPopWindow2;
//    List<Integer> isClickData = new ArrayList<>();

    /**
     * 主播类型
     */
    private void showPopupWindow2() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow2 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow2.setContentView(contentView);
        final TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);

        tv_type.setText("选择主播类型");

        Log.d("Debug", "anchor_type值为" + anchor_type);

        if (anchor_type.length() > 0) {
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
            TypeAdapter1 = new PlatSelectAdapter(AddInviteActivity.this, R.layout.item_four, mNewsList2);
            TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter1.setData(mNewsList2);
        }
        card_recycler.setLayoutManager(new GridLayoutManager(AddInviteActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter1);
        TypeAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //点击选中的  取消掉
                int number = 0;
                for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                    if (TypeAdapter1.getAll().get(i).isClick()) {
                        number++;
                    }
                }
                if (number >= 3) {
                    if (TypeAdapter1.get(position).isClick()) {
                        TypeAdapter1.get(position).setClick(false);
                    } else {
                        ToastUtil.showShort("最多选择三个标签");
                    }
                } else {
                    if (TypeAdapter1.get(position).isClick()) {
                        TypeAdapter1.get(position).setClick(false);
                    } else {
                        TypeAdapter1.get(position).setClick(true);
                    }
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
                            tvJobObjective.setText(TypeAdapter1.get(i).getPlat_name() + "...");
                        }
                        TypeAdapter1.get(i).setClick(false);
                        TypeAdapter1.notifyDataSetChanged();
                        sb.append(TypeAdapter1.getAll().get(i).getId());
                    }
                }
                anchor_type = sb + "";
                mPopWindow2.dismiss();

            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow3;

    /**
     * 求职状态
     */
    private void showPopupWindow3() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow3 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow3.setContentView(contentView);
        final TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        RelativeLayout rl_commit = contentView.findViewById(R.id.rl_commit);


        rl_commit.setVisibility(View.GONE);
        tv_type.setText("选择求职状态");

        Log.d("Debug", "anchor_type值为" + anchor_type);

        if (wanted_status.length() > 0) {
            String[] split = wanted_status.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList3.size(); j++) {
                    if (mNewsList3.get(j).getId().equals(split[i])) {
                        mNewsList3.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter2 == null) {
            TypeAdapter2 = new PlatSelectAdapter(AddInviteActivity.this, R.layout.item_four, mNewsList3);
            TypeAdapter2.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter2.setData(mNewsList3);
        }
        card_recycler.setLayoutManager(new GridLayoutManager(AddInviteActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter2);
        TypeAdapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < TypeAdapter2.getAll().size(); i++) {
                    TypeAdapter2.getAll().get(i).setClick(false);
                }
                TypeAdapter2.get(position).setClick(true);
                TypeAdapter2.notifyDataSetChanged();
                wanted_status = TypeAdapter2.get(position).getId();
                Log.d("Debug", "状态的i的是" + wanted_status);
                tv_job_type.setText(TypeAdapter2.get(position).getPlat_name() + "");
                mPopWindow3.dismiss();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        View rootview = LayoutInflater.from(AddInviteActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

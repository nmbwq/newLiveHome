package shangri.example.com.shangri.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.ChairmanRelesePresenter;
import shangri.example.com.shangri.presenter.ChairmanRelesePresenter2;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.ChangeAddressPopwindow;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.InputUtil;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.PickerCityView;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description:基本信息
 * Data：2018/11/9-9:54
 * Author: lin
 */
public class AddCompanyInfoActivity2 extends BaseActivity<ChairmanReleseView, ChairmanRelesePresenter2> implements ChairmanReleseView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.et_des)
    EditText etDes;

    @BindView(R.id.et_company_number)
    TextView et_company_number;

    @BindView(R.id.et_anchor_number)
    EditText et_anchor_number;

    @BindView(R.id.im_photo)
    ImageView im_photo;


    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_numner)
    EditText etNumner;


    @BindView(R.id.et_company_name_short)
    EditText et_company_name_short;
    @BindView(R.id.tv_company_local)
    TextView tv_company_local;
    @BindView(R.id.rl_local)
    RelativeLayout rl_local;
    private ProgressDialogFragment mProgressDialog;
    int is_face = 0;
    int is_company = 0;

    //上传图片的信息
    private PhotoConfig mPhotoConfig;
    private String mCompressPath = ""; //图片的压缩路径
    //弹窗
    AlertDialog dialog;
    private List<String> mList = new ArrayList<>();
    private CommonAdapter<String> OtherAdapter;

    private String number = "";

    //    true 代表拍照或是相册  传图片参数   false 做更改操作 不穿
    public boolean isTakePhoto = false;


    String company_scale = "";
    String anchor_scale = "";
    //第一次发布职位  没有完善公司信息跳转到这里界面（）
    boolean IsFirstFabuJob = false;

    //来自我的页面  公司信息不完善跳转
    boolean IsFromMine = false;

    @Override
    protected ChairmanRelesePresenter2 createPresenter() {
        return new ChairmanRelesePresenter2(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        isTakePhoto = getIntent().getBooleanExtra("isTakePhoto", false);
        IsFirstFabuJob = getIntent().getBooleanExtra("IsFirstFabuJob", false);
        IsFromMine = getIntent().getBooleanExtra("IsFromMine", false);
        if (IsFromMine) {
            tvTitle.setText("公司信息");
            tvCommit.setText("提交");
        } else {
            if (IsFirstFabuJob) {
                tvTitle.setText("公司信息（1/3）");
                tvCommit.setText("下一步");
            } else {
                tvTitle.setText("公司信息");
                tvCommit.setText("提交");
                String company_name = getIntent().getStringExtra("company_name");
                String company_description = getIntent().getStringExtra("company_description");
                String location = getIntent().getStringExtra("location");
                String Company_short_name = getIntent().getStringExtra("Company_short_name");
                mCompressPath = getIntent().getStringExtra("mCompressPath");
                company_scale = getIntent().getStringExtra("company_scale");
                anchor_scale = getIntent().getStringExtra("anchor_scale");
                if (location.length() > 0) {
                    tv_company_local.setText(location);
                }

                if (Company_short_name.length() > 0) {
                    et_company_name_short.setText(Company_short_name);
                }
                if (company_name.length() > 0) {
                    if (company_name.equals("未设置")) {
                        etCompanyName.setText("");
                        etCompanyName.setHint("请输入公司名称");
                    } else {
                        etCompanyName.setText(company_name);
                    }
                }
                if (company_description.length() > 0) {
                    if (company_description.equals("未设置")) {
                        etDes.setText("");
                        etDes.setHint("该介绍将会在招聘信息详情页展示，起到公司推广、吸引优质主播的作用，请认真填写呦。");
                    } else {
                        etDes.setText(company_description);
                    }

                }

                if (company_scale.length() > 0) {
                    if (company_scale.equals("未设置")) {
                        et_company_number.setText("");
                        et_company_number.setHint("请选择公司规模");
                    } else {
                        et_company_number.setText(company_scale + "");
                    }
                }
                if (anchor_scale.length() > 0) {

                    if (anchor_scale.equals("未设置")) {
                        et_anchor_number.setText("");
                        et_anchor_number.setHint("请填写（单位：人）");
                    }
                    et_anchor_number.setText(anchor_scale + "");
                }
                if (mCompressPath.length() > 0) {
                    Glide.with(AddCompanyInfoActivity2.this)
                            .load(mCompressPath)
                            .placeholder(R.mipmap.bg_touxiang)
                            .transform(new CornersTransform(AddCompanyInfoActivity2.this, 10))
                            .crossFade()
                            .into(im_photo);
                }

            }
        }


        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.d("Debug", "本地上传图片返回的列表地址" + result.getImages().toString());
                Log.d("Debug", "本地上传图片返回的单个地址" + result.getImage().toString());
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                TImage image = images.get(0);
                mCompressPath = image.getCompressPath();
                Log.d("Debug", "mCompressPath信息为" + mCompressPath);
                isTakePhoto = true;
                if (TextUtils.isEmpty(mCompressPath)) return;
                Glide.with(AddCompanyInfoActivity2.this)
                        .load(mCompressPath)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .transform(new CornersTransform(AddCompanyInfoActivity2.this, 10))
                        .into(im_photo);
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
        mList.add("50人以下");
        mList.add("50人-100人");
        mList.add("100人-500人");
        mList.add("500人以上");
        etNumner.setText(UserConfig.getInstance().getMobile() + "");
    }

    @Override
    protected void onStart() {
        InputUtil.hideInputMethdView(this, et_company_name_short);
        InputUtil.hideInputMethdView(this, et_anchor_number);
        InputUtil.hideInputMethdView(this, etCompanyName);
        InputUtil.hideInputMethdView(this, etDes);
        InputUtil.hideInputMethdView(this, etNumner);
        super.onStart();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialogFragment();
//        }
//        mProgressDialog.show(this.getSupportFragmentManager());
//        mPresenter.legalIsface();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_companydes_layout2;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_companydes_layout2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this, true);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);


    }


    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (IsFromMine) {
            CompanyHomepageActivity.isRefresh = true;
            finish();
        } else {
            if (IsFirstFabuJob) {
                Intent intent = new Intent(AddCompanyInfoActivity2.this, CompanyCertificationActivity.class);
                intent.putExtra("IsFirstFabuJob", true);
                startActivity(intent);
            } else {
                CompanyHomepageActivity.isRefresh = true;
                finish();
            }
        }
        ToastUtil.showShort("添加成功");


    }

    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void welfareList(welfareListPlatBean mAccountDataBean) {

    }

    @Override
    public void changeLightspot(changeLightspotBean mAccountDataBean) {

    }

    @Override
    public void jobAdd() {

    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {
//        if (mProgressDialog != null && mProgressDialog.isResumed()) {
//            mProgressDialog.dismiss();
//        }
        is_face = resultBean.getIs_face();
        is_company = resultBean.getIs_company();
        if (is_face == 1 && is_company == 1) {
            tvCommit.setText("提交");
        }
//       	法人认证 0未认证 1已认证
        if (resultBean.getIs_face() == 0) {
        } else {
        }
        if (resultBean.getIs_company() == 0) {
        } else {
        }
    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @OnClick({R.id.setting_back, R.id.tv_commit, R.id.rl_select_image, R.id.ll_select_number, R.id.rl_local})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select_image:
                KeyBoardUtil.KeyBoard(AddCompanyInfoActivity2.this, "close");
                methodRequiresTwoPermission();
                break;
            case R.id.ll_select_number:
                KeyBoardUtil.KeyBoard(AddCompanyInfoActivity2.this, "close");
//                KeyBoardUtil.closeKeybord(etName, CreatCityActivity.this);
                homeDialog1();
                break;
            case R.id.tv_commit:
                KeyBoardUtil.KeyBoard(AddCompanyInfoActivity2.this, "close");
                if (etCompanyName.getText().length() == 0) {
                    ToastUtil.showShort("请输入公司名称");
                    return;
                }
                if (etNumner.getText().length() == 0) {
                    ToastUtil.showShort("请输入电话");
                    return;
                }
                if (etDes.getText().length() == 0) {
                    ToastUtil.showShort("请输入公司介绍");
                    return;
                }

                if (mCompressPath.length() == 0) {
                    ToastUtil.showShort("请上传公司logo");
                    return;
                }
                if (et_company_number.getText().length() == 0) {
                    ToastUtil.showShort("请选择公司规模");
                    return;
                }
                if (et_anchor_number.getText().length() == 0) {
                    ToastUtil.showShort("请填写旗下主播人数");
                    return;
                }
                if (tv_company_local.getText().length() == 0) {
                    ToastUtil.showShort("请选择公司地址");
                    return;
                }
                //修改 操作没有做拍照相册点击    不进行传参数
                if (!isTakePhoto) {
                    mCompressPath = "";
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.companyAdd(tv_company_local.getText().toString(),mCompressPath, et_company_number.getText().toString(), et_anchor_number.getText().toString(), 1, etCompanyName.getText().toString(), etNumner.getText().toString(), etDes.getText().toString(), et_company_name_short.getText().toString());
                break;
            case R.id.rl_local:
                KeyBoardUtil.KeyBoard(AddCompanyInfoActivity2.this, "close");
                //选择公司地址
                if (PointUtils.isFastClick()) {
                    ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(this);
                    mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
                    mChangeAddressPopwindow.showAtLocation(tv_company_local, Gravity.BOTTOM, 0, 0);
                    mChangeAddressPopwindow
                            .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                                @Override
                                public void onClick(String province, String city, String area) {
                                    // TODO Auto-generated method stub
                                    Log.d("Debug", "返回的地址是" + province + "-" + city + "-" + area);
                                    tv_company_local.setText(province + "-" + city + "-" + area);
                                }
                            });
                }
                break;
        }
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
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(this, perms)) {
            showPop();
        } else {
            ToastUtil.showShort("请先添加权限");
            EasyPermissions.requestPermissions(this, "需要获取权限",
                    REQUEST_CODE, perms);
        }
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
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public AlertDialog homeDialog1() {
        View view = LayoutInflater.from(AddCompanyInfoActivity2.this).inflate(R.layout.company_number_dialog_layout, null);
        final ListViewForScrollView tv_listview = view.findViewById(R.id.tv_listview);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        tv_commit.setVisibility(View.GONE);
        OtherAdapter = new CommonAdapter<String>(AddCompanyInfoActivity2.this, mList, R.layout.company_number_layout) {
            @Override
            public void convert(ViewHolder helper, final String item) {
                TextView tv_name = helper.getView(R.id.tv_name);
                tv_name.setText(item);
                if (number.equals(item)) {
                    tv_name.setTextColor(getResources().getColor(R.color.text_color_task));
                } else {
                    tv_name.setTextColor(getResources().getColor(R.color.text_color_little_black));
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        number = item;
                        et_company_number.setText(item + "");
                        OtherAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }

        };
        tv_listview.setAdapter(OtherAdapter);
        //点击事件
        dialog = new AlertDialog.Builder(AddCompanyInfoActivity2.this).create();
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(AddCompanyInfoActivity2.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) AddCompanyInfoActivity2.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 下面是选择图片的操作
     */
    public void showPop() {
        SelectPhotoPopopWindow ImagePop = new SelectPhotoPopopWindow(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                //拍照框架比例修改
                GlobalApp.ImageScale = 2;
                mPhotoConfig.selectFromAlbum(1);
            }

            @Override
            public void takePhoto() {
                //拍照框架比例修改
                GlobalApp.ImageScale = 2;
                mPhotoConfig.takePhoto();
            }

            @Override
            public void savePhoto() {
            }
        }, "取消");
    }

}
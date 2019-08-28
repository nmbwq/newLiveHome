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
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.ChairmanRelesePresenter;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 会长发布职位界面
 * Created by mschen on 2017/6/23.
 */

public class AddCompanyInfoActivity extends BaseActivity<ChairmanReleseView, ChairmanRelesePresenter> implements ChairmanReleseView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.et_des)
    EditText etDes;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv_name_four)
    TextView tvNameFour;
    @BindView(R.id.tv_id_name)
    TextView tvIdName;
    @BindView(R.id.ll_id_nocommit)
    LinearLayout llIdNocommit;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.et_company_number)
    TextView et_company_number;

    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.et_anchor_number)
    EditText et_anchor_number;

    @BindView(R.id.im_photo)
    ImageView im_photo;


    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_type)
    TextView tv_company_type;
    @BindView(R.id.ll_commit_nocommit)
    LinearLayout llCommitNocommit;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_numner)
    EditText etNumner;
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

    @Override
    protected ChairmanRelesePresenter createPresenter() {
        return new ChairmanRelesePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        String company_name = getIntent().getStringExtra("company_name");
        String company_description = getIntent().getStringExtra("company_description");
        mCompressPath = getIntent().getStringExtra("mCompressPath");
        company_scale = getIntent().getStringExtra("company_scale");
        anchor_scale = getIntent().getStringExtra("anchor_scale");
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
            Glide.with(AddCompanyInfoActivity.this)
                    .load(mCompressPath)
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(AddCompanyInfoActivity.this, 10))
                    .crossFade()
                    .into(im_photo);
        }
        etNumner.setText(UserConfig.getInstance().getMobile() + "");
        mList.add("50人以下");
        mList.add("50人-100人");
        mList.add("100人-500人");
        mList.add("500人以上");

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
                Glide.with(AddCompanyInfoActivity.this)
                        .load(mCompressPath)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .transform(new CornersTransform(AddCompanyInfoActivity.this, 10))
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
        mPresenter.legalIsface();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_companydes_layout1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_companydes_layout1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
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
        ToastUtil.showShort("添加成功");
        finish();
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
            llIdNocommit.setVisibility(View.VISIBLE);
            tvIdName.setVisibility(View.GONE);
        } else {
            tvIdName.setVisibility(View.VISIBLE);
            llIdNocommit.setVisibility(View.GONE);
            tvIdName.setText(resultBean.getLegal_name() + "");
        }
        if (resultBean.getIs_company() == 0) {
            llCommitNocommit.setVisibility(View.VISIBLE);
            tvCompanyName.setVisibility(View.GONE);
            if (resultBean.getIs_face() > 0) {
                tv_company_type.setText("去认证");
            } else {
                tv_company_type.setText("请先进行法人认证");
            }
        } else {
            tvCompanyName.setVisibility(View.VISIBLE);
            llCommitNocommit.setVisibility(View.GONE);
            tvCompanyName.setText(resultBean.getCompany_name() + "");
        }
    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @OnClick({R.id.setting_back, R.id.rl1, R.id.rl2, R.id.tv_commit, R.id.rl_select_image, R.id.ll_select_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select_image:
                methodRequiresTwoPermission();
                break;
            case R.id.ll_select_number:
//                KeyBoardUtil.closeKeybord(etName, CreatCityActivity.this);
                homeDialog1();
                break;
            case R.id.rl1:
                if (is_face > 0) {
                    ToastUtil.showShort("您已认证通过");
                } else {
                    startActivity(new Intent(AddCompanyInfoActivity.this, IdentIdCardOneetail.class));
                }
                break;
            case R.id.rl2:
                if (is_company > 0) {
                    ToastUtil.showShort("您已认证通过");
                } else {
                    if (is_face > 0) {
                        startActivity(new Intent(AddCompanyInfoActivity.this, BusinesIdentsetail.class));
                    } else {
                        ToastUtil.showShort("请先进行法人认证");
                    }
                }
                break;
            case R.id.tv_commit:
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
                //修改 操作没有做拍照相册点击    不进行传参数
                if (!isTakePhoto) {
                    mCompressPath = "";
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.companyAdd(mCompressPath, et_company_number.getText().toString(), et_anchor_number.getText().toString(), 1, etCompanyName.getText().toString(), etNumner.getText().toString(), etDes.getText().toString());
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
        View view = LayoutInflater.from(AddCompanyInfoActivity.this).inflate(R.layout.company_number_dialog_layout, null);
        final ListViewForScrollView tv_listview = view.findViewById(R.id.tv_listview);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        tv_commit.setVisibility(View.GONE);
        OtherAdapter = new CommonAdapter<String>(AddCompanyInfoActivity.this, mList, R.layout.company_number_layout) {
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
        dialog = new AlertDialog.Builder(AddCompanyInfoActivity.this).create();
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
        window.setLayout(dip2px(AddCompanyInfoActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) AddCompanyInfoActivity.this
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

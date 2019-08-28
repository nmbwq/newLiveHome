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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.companyAdminListBean;
import shangri.example.com.shangri.model.bean.response.companyBreaklistListBean;
import shangri.example.com.shangri.model.bean.response.companyOrgBean;
import shangri.example.com.shangri.presenter.CreatPresenter;
import shangri.example.com.shangri.presenter.view.CreatCompanyView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.ChangeAddressPopwindow;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.AndroidInterface.RefushFace;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;


/**
 *
 *
 */

public class CreatCityActivity extends BaseActivity<CreatCompanyView, CreatPresenter> implements CreatCompanyView, RefushFace {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.im_photo)
    ImageView imPhoto;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv_people_number)
    TextView tvPeopleNumber;
    @BindView(R.id.rl_select_number)
    RelativeLayout rlSelectNumber;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.rl_seklect_adress)
    RelativeLayout rlSeklectAdress;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    String number = "";

    //上传图片的信息
    private PhotoConfig mPhotoConfig;
    private String mCompressPath = ""; //图片的压缩路径
    //弹窗
    AlertDialog dialog;
    private List<String> mList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;
    private CommonAdapter<String> OtherAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_creatcity_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_creatcity_layout;
    }

    @Override
    protected CreatPresenter createPresenter() {
        return new CreatPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        CompanyInterfaceUtils.setRefushBack(this);
        mList.add("100人以下");
        mList.add("100人-300人");
        mList.add("300人-500人");
        mList.add("500人-1000人");
        mList.add("2000人-5000人");
        mList.add("50000人以上");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mPhotoConfig = new PhotoConfig(this, true);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
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
                if (TextUtils.isEmpty(mCompressPath)) return;
                Glide.with(CreatCityActivity.this)
                        .load(mCompressPath)
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(imPhoto);
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


    @OnClick({R.id.setting_back, R.id.rl_select_number, R.id.rl_seklect_adress, R.id.tv_commit, R.id.rl_select_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select_number:
                KeyBoardUtil.closeKeybord(etName, CreatCityActivity.this);
                homeDialog1();
                break;
            case R.id.rl_seklect_adress:
                KeyBoardUtil.closeKeybord(etName, CreatCityActivity.this);
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(CreatCityActivity.this);
                mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
                mChangeAddressPopwindow.showAtLocation(rlSeklectAdress, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city, String area) {
                                // TODO Auto-generated method stub
//                                if (area.length() > 0) {
//                                    tvSelectAddress.setText(province + "-" + city + "-" + area);
//                                } else {
//                                    tvSelectAddress.setText(province + "-" + city);
//                                }
//                                BaseInfoBean.company_province = province + "";
//                                BaseInfoBean.company_city = city + "";
//                                BaseInfoBean.company_area = area + "";
                                Log.d("Debug", "返回的地址是" + province + "-" + city + "-" + area);
                                tvAdress.setText(province + "-" + city + "-" + area);
                            }
                        });
                break;
            case R.id.rl_select_image:
                methodRequiresTwoPermission();
                break;
            case R.id.tv_commit:
                if (etName.getText().toString().length() == 0) {
                    ToastUtil.showShort("公司名称不能为空");
                    return;
                }
                if (tvPeopleNumber.getText().toString().length() == 0) {
                    ToastUtil.showShort("公司规模不能为空");
                    return;
                }
                if (tvAdress.getText().toString().length() == 0) {
                    ToastUtil.showShort("地址不能为空");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.sofwwareUser(mCompressPath, etName.getText().toString(), tvPeopleNumber.getText().toString(), tvAdress.getText().toString());
//                mPresenter.sofwwareUser(mCompressPath, "测试公司", "500-1000人", "浙江省杭州");
                break;
        }
    }

    @Override
    public void Create() {
        CompamyInfoActivity.type = 3;
        finish();

        Log.d("Debug", "添加成功");
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        EventBus.getDefault().post(new BrowseEventBean());
    }

    @Override
    public void update() {

    }

    @Override
    public void company(companyOrgBean resultBean) {

    }

    @Override
    public void adminsList(companyAdminListBean resultBean) {

    }

    @Override
    public void breaklist(companyBreaklistListBean resultBean) {

    }

    @Override
    public void AnchorLeave() {

    }

    @Override
    public void companyLizhi() {

    }

    @Override
    public void companyJiebang() {

    }

    @Override
    public void adminBreakanchor() {

    }

    @Override
    public void adminAddanchors() {

    }


    /**
     * 下面是选择图片的操作
     */

    public void showPop() {
        SelectPhotoPopopWindow ImagePop = new SelectPhotoPopopWindow(this, new SelectPhotoListener() {
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
            }
        }, "取消");
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
        View view = LayoutInflater.from(CreatCityActivity.this).inflate(R.layout.company_number_dialog_layout, null);
        final ListViewForScrollView tv_listview = view.findViewById(R.id.tv_listview);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        tv_commit.setVisibility(View.GONE);
        OtherAdapter = new CommonAdapter<String>(CreatCityActivity.this, mList, R.layout.company_number_layout) {
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
                        tvPeopleNumber.setText(number);
                        OtherAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
            }

        };
        tv_listview.setAdapter(OtherAdapter);

        //点击事件
        dialog = new AlertDialog.Builder(CreatCityActivity.this).create();
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
        window.setLayout(dip2px(CreatCityActivity.this, 330), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) CreatCityActivity.this
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


    @Override
    public void Refush() {
        Log.d("Debug", "到達這裡");
    }

    @Override
    public void ReportRefush() {

    }
}

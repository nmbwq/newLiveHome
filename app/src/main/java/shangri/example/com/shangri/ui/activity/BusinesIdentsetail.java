package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.IdentPresenter;
import shangri.example.com.shangri.presenter.view.IdentView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.Base64Utils1;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 营业执照认证界面
 * Created by admin on 2017/12/22.
 */

public class BusinesIdentsetail extends BaseActivity<IdentView, IdentPresenter> implements IdentView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.im_name_delete)
    ImageView imNameDelete;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.im_number_delete)
    ImageView imNumberDelete;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_name)
    EditText etName;
    private ProgressDialogFragment mProgressDialog;

    private PhotoConfig mPhotoConfig;
    private String mCompressPath; //图片的压缩路径
    //转化成base64
    String idString = "";


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_bussness_one;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_bussness_one;
    }

    @Override
    protected IdentPresenter createPresenter() {
        return new IdentPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.d("Debug", "本地上传图片返回的列表地址" + result.getImages().toString());
                Log.d("Debug", "本地上传图片返回的单个地址" + result.getImage().toString());
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                TImage image = images.get(0);
                mCompressPath = image.getCompressPath();

                try {
                    idString = Base64Utils.encodeFile(mCompressPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("Debug", "mCompressPath信息为" + mCompressPath);

                try {
                    imImage.setImageBitmap(Base64Utils1.base64ToBitmap(Base64Utils.encodeFile(mCompressPath)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        mPresenter.companyAdd(2);

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this, true);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);

    }


    @Override
    public void faceContrast(companyMyBean bean) {

    }

    @Override
    public void faceDiscern(companyMyBean bean) {

    }

    @Override
    public void legalUploading(companyMyBean bean) {

    }

    @Override
    public void licenceDiscern() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        finish();
    }

    @Override
    public void authIdcard(companyMyBean bean) {

    }

    @Override
    public void companyAdd(NewCompanyBean bean) {
        if (bean.getCompany().getCompany_name().length() > 0) {
            if (bean.getCompany().getCompany_name().equals("未设置")) {
            } else {
                etName.setText(bean.getCompany().getCompany_name() + "");
            }
        }
    }


    @Override
    public void face_num() {

    }

    @Override
    public void company_num() {
        mPresenter.licenceDiscern(etName.getText().toString(), etNumber.getText().toString(), mCompressPath);
    }

    @OnClick({R.id.setting_back, R.id.im_image, R.id.im_name_delete, R.id.im_number_delete, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.im_image:
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
                    }
                }, "取消");
                break;
            case R.id.im_name_delete:
                etName.setText("");
                etName.setHint("请输入营业执照公司名称");
                break;
            case R.id.im_number_delete:
                etNumber.setText("");
                etNumber.setHint("请输入统一信用代码");
                break;
            case R.id.tv_exit:

                if (etName.getText().toString().length() == 0) {
                    ToastUtil.showShort("请输入营业执照公司名称");
                    return;
                }
                if (etNumber.getText().toString().length() == 0) {
                    ToastUtil.showShort("请输入统一信用代码");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.company_num();
                break;
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
    }
}

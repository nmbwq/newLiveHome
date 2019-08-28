package shangri.example.com.shangri.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.IdentPresenter;
import shangri.example.com.shangri.presenter.view.IdentView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.Base64Utils1;
import shangri.example.com.shangri.util.FileUtil;
import shangri.example.com.shangri.util.JsonUitl;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 身份认证第一个界面
 * Created by admin on 2017/12/22.
 */

public class IdentIdCardOneetail extends BaseActivity<IdentView, IdentPresenter> implements IdentView {
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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    private ProgressDialogFragment mProgressDialog;

    private String mCompressPath; //图片的压缩路径
    //转化成base64
    String idString = "";
    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;
    String name;
    String idNumber;

    IdIdentBean idIdentBean;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_identidcard_one;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_identidcard_one;
    }

    @Override
    protected IdentPresenter createPresenter() {
        return new IdentPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        if (UserConfig.getInstance().getRole().equals("1")) {
            tvTitle.setText("法人认证");
            tv1.setText("法人姓名");
        } else {
            tvTitle.setText("身份认证");
            tv1.setText("真实姓名");
        }
        idIdentBean = new IdIdentBean();
        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
                    }
                });

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        PhotoConfig mPhotoConfig = new PhotoConfig(this, true);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
    }


    @Override
    public void faceContrast(companyMyBean bean) {

    }

    @Override
    public void faceDiscern(companyMyBean bean) {

    }

    //身份证图片地址
    String file_url;

    @Override
    public void legalUploading(companyMyBean bean) {
        Log.d("Debug", "请求后台返回的地址是" + bean.getFile_url());
        file_url = bean.getFile_url();
    }

    @Override
    public void licenceDiscern() {

    }

    /**
     * 认证身份证和姓名是否一致
     */
    @Override
    public void authIdcard(companyMyBean bean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        idIdentBean.face_id = bean.getFace_id();
        showPopupWindow();
    }

    @Override
    public void companyAdd(NewCompanyBean bean) {

    }

    @Override
    public void face_num() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void company_num() {

    }

    @OnClick({R.id.setting_back, R.id.im_image, R.id.im_name_delete, R.id.im_number_delete, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.im_image:
                Intent intent = new Intent(IdentIdCardOneetail.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                        true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                        true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            case R.id.im_name_delete:
                etName.setText("");
                etName.setHint("请输入真实姓名");
                break;
            case R.id.im_number_delete:
                etNumber.setText("");
                etNumber.setHint("请输入身份证号");
                break;
            case R.id.tv_exit:
                if (idString.length() == 0) {
                    ToastUtil.showShort("请上传身份证照片");
                    return;
                }
                if (etName.getText().toString().length() == 0) {
                    ToastUtil.showShort("真实姓名不能为空");
                    return;
                }
                if (etNumber.getText().toString().length() == 0) {
                    ToastUtil.showShort("身份证号不能为空");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(IdentIdCardOneetail.this.getSupportFragmentManager());
                mPresenter.authIdcard(etName.getText().toString(), etNumber.getText().toString(), idString);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                Log.d("Debug", "返回的图片地址是" + filePath);
                try {
                    idString = Base64Utils.encodeFile(filePath);
                    imImage.setImageBitmap(Base64Utils1.base64ToBitmap(idString));
//                    idIdentBean.id_img = idString;
                    mPresenter.legalUploading(idString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            } else {
                Log.d("Debug", "data返回为空");
            }
        }
    }

    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    Log.d("Debug", "返回的照相数据" + result.toString());
                    name = result.getName() + "";
                    idNumber = result.getIdNumber() + "";
                    etName.setText(name);
                    etNumber.setText(idNumber);
                }
            }

            @Override
            public void onError(OCRError error) {
            }
        });
    }


    private PopupWindow mPopWindow;

    /**
     * 主播拒签操作
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(IdentIdCardOneetail.this).inflate(R.layout.compact_comtent, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        ImageView im_delete = contentView.findViewById(R.id.im_delete);
        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        tv_content.setText(etName.getText().toString() + "    " + etNumber.getText().toString());
        tv_next.setText("确定");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
                startActivity(new Intent(IdentIdCardOneetail.this, IdentIdCardSecondetail.class).putExtra("bean", idIdentBean).putExtra("id_image", file_url));
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(IdentIdCardOneetail.this).inflate(R.layout.activity_identidcard_one, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

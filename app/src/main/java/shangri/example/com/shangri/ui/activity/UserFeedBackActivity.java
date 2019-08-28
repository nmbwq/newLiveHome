package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ali.AliOssManager;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.UserFeedBackPresenter;
import shangri.example.com.shangri.presenter.view.UserFeedBackView;
import shangri.example.com.shangri.ui.adapter.ImagesAdapter;
import shangri.example.com.shangri.ui.listener.ImagesAddListener;
import shangri.example.com.shangri.ui.listener.OssUploadListener;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.NoScrollGridView;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PhotoConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by chengaofu on 2017/6/23.
 * do what to 2017/6/23
 */

public class UserFeedBackActivity extends BaseActivity<UserFeedBackView, UserFeedBackPresenter> implements UserFeedBackView {

    @BindView(R.id.feedback_grid)
    NoScrollGridView mPicGrid;


    @BindView(R.id.feedback_input)
    EditText mFeedbackInput;
    @BindView(R.id.feedback_contact)
    EditText mFeedbackContact;

    private ImagesAdapter mAdapter;
    private PhotoConfig mPhotoConfig; //拍照 和 选取相片时的配置信息

    private static final int MAX_PICS = 4;
    private List<String> listUrls = new ArrayList<>(); //列表中的url
    private int mCurrentPos = 0;//添加相片 当前的位置(0-3)
    private String fbInput;
    private String contact;
    private ProgressDialogFragment mProgressDialog;
    //是否来自查看解决方案  true 要将查看解决方案界面finsh（）
    boolean isFromQuestion;
    //图片，多个以“;”隔开
    StringBuilder image = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    protected void initViewsAndEvents() {
//        IsFromQuestion
        isFromQuestion = getIntent().getBooleanExtra("IsFromQuestion", false);
        KeyBoardUtil.KeyBoard(UserFeedBackActivity.this, "close");
        mAdapter = new ImagesAdapter(this, listUrls, new ImagesAddListener() {
            @Override
            public void add(int position) {
                KeyBoardUtil.hide_keyboard_from(UserFeedBackActivity.this, mPicGrid);//
                mCurrentPos = position;
                if (mCurrentPos > 3) return; //这一步没作用
                choosePhoto(MAX_PICS - position);
            }
        });
        mPicGrid.setAdapter(mAdapter);
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.e("Wislie", "takeSuccess");
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                final List<String> newUrls = new ArrayList<>();
                for (TImage image : images) {
                    listUrls.add(0, image.getCompressPath()); //添加选中的图片
                }
                mAdapter.notifyDataSetChanged();
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

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_user_feedback;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_user_feedback;
    }

    @Override
    protected UserFeedBackPresenter createPresenter() {
        return new UserFeedBackPresenter(this, this);
    }

    @OnClick({R.id.feedback_submit, R.id.user_feedback_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_submit:
                fbInput = mFeedbackInput.getText().toString();
                if (TextUtils.isEmpty(fbInput)) {
                    ToastUtil.showShort("问题和建议不能为空");
                    return;
                }

                if (fbInput.trim().length() < 10) {
                    ToastUtil.showShort("问题和建议不能少于10个字");
                    return;
                }

                contact = mFeedbackContact.getText().toString();
                if (TextUtils.isEmpty(contact)) {
                    ToastUtil.showShort("QQ/手机号不能为空");
                    return;
                }

                boolean isContact = RegexUtil.isMobile(contact) || RegexUtil.isQQ(contact);
                if (!isContact) {
                    ToastUtil.showShort("输入的联系方式格式不对");
                    return;
                }

                if (listUrls == null || listUrls.size() == 1) {
                    ToastUtil.showShort("需要上传截图");
                    return;
                }
                for (int i = 0; i < listUrls.size() - 1; i++) {
                    Log.d("Debug", "没有进行base64处理的图片地址" + listUrls.get(i));
                    image.append(Base64Utils.imageToBase64(listUrls.get(i)));
                    if (listUrls.size() > 1) {
                        if (i != listUrls.size() - 2) {
                            image.append(";");
                        }
                    }
                }
                Log.d("Debug", "返回的图片地址是" + image.toString());
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.uploadFeedBackContent(contact, fbInput, image.toString());
                break;
            case R.id.user_feedback_back:
                finish();
                break;
        }
    }


    /**
     * 开启图片选择器
     */
    private void choosePhoto(final int limit) {
        new SelectPhotoPopopWindow(this, new SelectPhotoListener() {
            @Override
            public void selectFromAlbum() {
                mPhotoConfig.selectFromAlbum(limit);
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

    StringBuilder urlBuilder = new StringBuilder();

    @Override
    public void imageUpload(ImageUpload resultBean) {
    }

    @Override
    public void uploadFeedBcakContent() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("用户反馈上传成功");
        if (isFromQuestion) {
            ActivityManager.getInstance().finishActivity(DetailQuestionActivity.class);
        }
        finish();
    }


    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) return;
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
//        if (message.contains(String.valueOf(Constant.CODE_100027))) {
//            ToastUtil.showShort("token 失效，需重新登录");
//            ActivityUtils.startActivity(this, LoginActivity.class);
//            finish();
//        }
    }
}

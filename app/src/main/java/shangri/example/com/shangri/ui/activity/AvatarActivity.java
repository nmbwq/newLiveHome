package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.ui.view.PhotoView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.UpdateUserInfoPresenter;
import shangri.example.com.shangri.presenter.view.UpdateUserView;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.FileUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 头像
 * Created by chengaofu on 2017/7/1.
 */

public class AvatarActivity extends BaseActivity<UpdateUserView, UpdateUserInfoPresenter> implements UpdateUserView {

    @BindView(R.id.user_avatar)
    PhotoView mUserAvatar;

    private PhotoConfig mPhotoConfig;
    private String mCompressPath; //图片的压缩路径
    private String mAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAvatar = UserConfig.getInstance().getAvatar();
        mPhotoConfig = new PhotoConfig(this, true);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        if (!TextUtils.isEmpty(mAvatar)) {
            BitmapCache.getInstance().loadBitmaps(mUserAvatar, mAvatar, null);
        }
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
                Log.d("Debug", "mCompressPath信息为" + mCompressPath);
                if (TextUtils.isEmpty(mCompressPath)) return;
                new Thread(getGoingOrder).run();
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
        mUserAvatar.enable();
    }

    /**
     * 查看是否有进行中的订单
     */
    Runnable getGoingOrder = new Runnable() {
        @Override
        public void run() {
            mPresenter.updateUserInfo(mCompressPath);
        }
    };


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


    @OnClick({R.id.alert_avatar, R.id.avatar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_back:
                finish();
                break;
            case R.id.alert_avatar:
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
                        if (mCompressPath == null) { //头像图片
                            mCompressPath = mAvatar;
                        }
                        //这里会改成Rxjava2加载
//                      Bitmap bmp = BitmapFactory.decodeFile(mCompressPath);
                        Glide.with(AvatarActivity.this).load(mCompressPath).asBitmap().into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                if (resource == null) {
                                    return;
                                }
                                FileUtil.saveImageToGallery(AvatarActivity.this, resource);
                                ToastUtil.showShort("图片已保存");
                            }
                        });
                    }
                }, "保存图片");

                break;
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_avatar;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_avatar;
    }

    @Override
    protected UpdateUserInfoPresenter createPresenter() {
        return new UpdateUserInfoPresenter(this, this);
    }


    @Override
    public void updateUser(ImageUpload resultBean) { //用户头像更新成功
        Log.d("Debug", "到达这里");
        UserConfig.getInstance().setAvatar(resultBean.getFile_url());
        BitmapCache.getInstance().loadBitmaps(mUserAvatar, mCompressPath, null);
        ToastUtil.showShort("头像更新成功");
    }

    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) return;
        if (message.contains(String.valueOf(Constant.CODE_100027))) {
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }
}

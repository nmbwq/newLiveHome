package shangri.example.com.shangri.util;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;

import java.io.File;

/**
 * Created by chengaofu on 2017/7/1.
 */

public class PhotoConfig implements TakePhoto.TakeResultListener, InvokeListener {

    private Boolean mPick = false;  //是否裁剪
    private InvokeParam invokeParam;
    private TakePhoto mTakePhoto;
    //    private PhotoConfig mINSTANCE;
    private TakephotoFinishListener mListener;

    public PhotoConfig(Activity context) {
        mTakePhoto = getTakePhoto(context);
    }

    public PhotoConfig(Activity context, Boolean Pick) {
        mPick = Pick;
        mTakePhoto = getTakePhoto(context);
    }
    /*public static PhotoConfig getInstance(Activity context){
        if(mINSTANCE == null){
            mINSTANCE = new PhotoConfig(context);
        }
        return mINSTANCE;
    }*/

    public void selectFromAlbum(int limit) { //从相册选择
        Uri imageUri = initConfig(mTakePhoto);
//        if (limit > 1) { // limit大于1,多张图片
//            mTakePhoto.onPickMultipleWithCrop(limit, null); //裁剪
//            return;
//        }
        if (mPick == true) {
            if (GlobalApp.ImageScale == 1) {
                //上传图片切图比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(9, 16));//裁剪
            } else if (GlobalApp.ImageScale == 2) {
                //上传公司logo剪切比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(70, 58));//裁剪
            } else {
                //正方形剪切比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(1, 1));//裁剪
            }
            //没修改一次还原   当在次点击操作时重新赋值ImageScale
            GlobalApp.ImageScale = 1000;
        } else {
            mTakePhoto.onPickFromGallery();
        }
    }
    public void selectFromAlbum1(int limit) { //从相册选择
        Uri imageUri = initConfig(mTakePhoto);
        if (limit > 1) { // limit大于1,多张图片
            CropOptions cropOptions=new CropOptions.Builder().setAspectX(12).setAspectY(7).setWithOwnCrop(true).create();
            mTakePhoto.onPickMultipleWithCrop(limit, cropOptions); //裁剪
            return;
        }
        if (mPick == true) {
            if (GlobalApp.ImageScale == 1) {
                //上传图片切图比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(9, 16));//裁剪
            } else if (GlobalApp.ImageScale == 2) {
                //上传公司logo剪切比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(70, 58));//裁剪
            } else {
                //正方形剪切比例
                mTakePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(1, 1));//裁剪
            }
            //没修改一次还原   当在次点击操作时重新赋值ImageScale
            GlobalApp.ImageScale = 1000;
        } else {
            mTakePhoto.onPickFromGallery();
        }
    }


    public void takePhoto() { //拍照
        if (mPick == true) {
            Uri imageUri = initConfig(mTakePhoto);
            Log.d("Debug", "到达拍照界面");
            if (GlobalApp.ImageScale == 1) {
                //上传图片切图比例
                mTakePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(9, 16));//裁剪
            } else if (GlobalApp.ImageScale == 2) {
                //上传公司logo剪切比例    android:layout_width="70dp"
                //                    android:layout_height="58dp"
                mTakePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(70, 58));//裁剪
            } else {
                //正方形剪切比例
                mTakePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(1, 1));//裁剪
            }
            //没修改一次还原   当在次点击操作时重新赋值ImageScale
            GlobalApp.ImageScale = 1000;
        } else {
            Uri imageUri = initConfig(mTakePhoto);
            mTakePhoto.onPickFromCapture(imageUri);
        }
    }

    public void takePhoto1() { //拍照并裁剪
        CropOptions cropOptions=new CropOptions.Builder().setAspectX(12).setAspectY(7).setWithOwnCrop(false).create();
        Uri imageUri = initConfig(mTakePhoto);
        mTakePhoto.onPickFromCaptureWithCrop(imageUri,cropOptions);
    }

    private Uri initConfig(TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        return imageUri;
    }


    public TakePhoto getTakePhoto(Activity context) {
        if (mTakePhoto == null) {
            mTakePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(context, this));
        }
        return mTakePhoto;
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        Log.d("Debug", "到达configCompress方法里面");

        int maxSize = 102400;
        int width = 800;
        int height = 800;
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(height)
                .setMaxWidth(width)
                .setMaxSize(maxSize)
                .create();
        CompressConfig config = CompressConfig.ofLuban(option);
        takePhoto.onEnableCompress(config, showProgressBar);

    }

    /**
     * 设置剪切为1:1比例   builder.setAspectX(1).setAspectY(1).setWithOwnCrop(false);和这个设置有关   和 int height = 800; int width = 800;物管
     *
     * @return xy比例剪切
     */
    private CropOptions getCropOptions(int x, int y) {
        Log.d("Debug", "到达getCropOptions方法里面");
        int height = 800;
        int width = 800;
        boolean withWonCrop = false;
        CropOptions.Builder builder = new CropOptions.Builder();
        //根据图片形状大小进行切图样式  长方形或是正方向
//        builder.setOutputX(width).setOutputY(height);
        //下面只是正方形
        builder.setAspectX(x).setAspectY(y).setWithOwnCrop(false);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(
                TContextWrap.of(ActivityManager.getInstance().getLastActivity()), //得到当前的activity
                invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    public InvokeParam getInvokeParam() {
        return invokeParam;
    }

    @Override
    public void takeSuccess(TResult result) {
        if (mListener != null) {
            mListener.takeSuccess(result);
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        if (mListener != null) {
            mListener.takeFail(result, msg);
        }
    }

    @Override
    public void takeCancel() {
        if (mListener != null) {
            mListener.takeCancel();
        }
    }

    public void setListener(TakephotoFinishListener listener) {
        this.mListener = listener;
    }
}

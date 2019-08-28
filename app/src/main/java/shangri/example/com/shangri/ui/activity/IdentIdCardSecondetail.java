package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.Config;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.IdChangeBean;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.IdentPresenter;
import shangri.example.com.shangri.presenter.view.IdentView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.Base64Utils1;
import shangri.example.com.shangri.util.ToastUtil;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * 身份认证第二个界面
 * Created by admin on 2017/12/22.
 */

public class IdentIdCardSecondetail extends BaseActivity<IdentView, IdentPresenter> implements IdentView {
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
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private ProgressDialogFragment mProgressDialog;
    //人脸识别照片
    public String Liveimage = "";
    //身份证照片
    String idString = "";
    //请求需要的数据
    IdIdentBean idIdentBean;
    //人脸识别和身份证照片的相似度
    int score = 0;
    //判断是够点击了人脸识别
    boolean IsOnclick = false;
    //    上个页面传过来的身份证网络图片地址  （之前是通过bean传过来的，因为数据过大崩溃  现在是传过来身份证网络地址 要进行下载）
    String id_image;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_identidcard_second;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_identidcard_second;
    }

    @Override
    protected IdentPresenter createPresenter() {
        return new IdentPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        idIdentBean = (IdIdentBean) getIntent().getSerializableExtra("bean");
        id_image = getIntent().getStringExtra("id_image");
        new Thread(saveFileRunnable).start();
//       if (idIdentBean!=null){
//           idString=idIdentBean.getId_img();
//       }
        FaceSDKManager.getInstance().initialize(IdentIdCardSecondetail.this, Config.licenseID, Config.licenseFileName);
    }

    private Runnable saveFileRunnable = new Runnable() {
        public Bitmap myBitmap;

        @Override
        public void run() {
            try {
                byte[] data = getImage(id_image);
                if (data != null) {
                    myBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
                } else {
                    Toast.makeText(context, "Image error!", Toast.LENGTH_LONG).show();
                }
                if (myBitmap != null) {
                    Log.d("Debug", "下载成功" + myBitmap);
                    String s = Base64Utils1.bitmapToBase64(myBitmap);
                    idString = s;
                    idIdentBean.id_img = s;
                } else {
                    Log.d("Debug", "数据为空");
                }
//                saveFile(mBitmap, mFileName);
            } catch (IOException e) {

                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Get image from newwork
     *
     * @param path The path of image
     * @return byte[]
     * @throws Exception
     */
    public byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    /**
     * Get data from stream
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    /**
     * 点击撤销  刷新之前点击进入的freagment
     *
     * @param event
     */
    @SuppressWarnings("JavaDoc")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(IdChangeBean event) {
        Log.d("Debug", "人脸认证过接收到到event");
        //默认显示第一个界面 下面是跳转到置顶界面
        IsOnclick = true;
        Liveimage = event.getLiveimage();
        imImage.setImageBitmap(Base64Utils1.base64ToBitmap(Liveimage));
//
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
//        showPopupWindow();
    }

    @OnClick({R.id.setting_back, R.id.im_image, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.im_image:
                showPopupWindow();
                break;
            case R.id.tv_exit:
                if (IsOnclick) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(IdentIdCardSecondetail.this.getSupportFragmentManager());
                    mPresenter.faceContrast(Liveimage, idString);

                } else {
                    ToastUtil.showShort("请先进行人脸识别");
                }
                break;
        }
    }


    private void setFaceConfig() {
        FaceConfig config = FaceSDKManager.getInstance().getFaceConfig();
        // SDK初始化已经设置完默认参数（推荐参数），您也根据实际需求进行数值调整
        config.setLivenessTypeList(GlobalApp.livenessList);
        config.setLivenessRandom(GlobalApp.isLivenessRandom);
        config.setBlurnessValue(FaceEnvironment.VALUE_BLURNESS);
        config.setBrightnessValue(FaceEnvironment.VALUE_BRIGHTNESS);
        config.setCropFaceValue(FaceEnvironment.VALUE_CROP_FACE_SIZE);
        config.setHeadPitchValue(FaceEnvironment.VALUE_HEAD_PITCH);
        config.setHeadRollValue(FaceEnvironment.VALUE_HEAD_ROLL);
        config.setHeadYawValue(FaceEnvironment.VALUE_HEAD_YAW);
        config.setMinFaceSize(FaceEnvironment.VALUE_MIN_FACE_SIZE);
        config.setNotFaceValue(FaceEnvironment.VALUE_NOT_FACE_THRESHOLD);
        config.setOcclusionValue(FaceEnvironment.VALUE_OCCLUSION);
        config.setCheckFaceQuality(true);
        config.setFaceDecodeNumberOfThreads(2);
        FaceSDKManager.getInstance().setFaceConfig(config);
    }

    private void startItemActivity(Class itemClass) {
        setFaceConfig();
        startActivity(new Intent(IdentIdCardSecondetail.this, itemClass));
    }

    @Override
    public void faceContrast(companyMyBean bean) {
//        if (mProgressDialog != null && mProgressDialog.isResumed()) {
//            mProgressDialog.dismiss();
//        }
        score = bean.getScore();
        idIdentBean.similarity = score + "";
        idIdentBean.legal_photo = Liveimage;
        idIdentBean.setToken(UserConfig.getInstance().getToken());
        mPresenter.faceDiscern(idIdentBean);
//        ToastUtil.showShort("相似度为-----------" + bean.getScore());
    }

    @Override
    public void faceDiscern(companyMyBean bean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (bean.getOk() == 1) {
            ActivityManager.getInstance().finishActivity(IdentIdCardOneetail.class);
            ActivityManager.getInstance().finishActivity(IdentIdCardSecondetail.class);
            if (UserConfig.getInstance().getRole().equals("1")) {
                startActivity(new Intent(IdentIdCardSecondetail.this, BusinesIdentsetail.class));
            }
        } else {
            idIdentBean.face_id = bean.getFace_id();
            ToastUtil.showShort("认证失败");
        }

    }

    @Override
    public void legalUploading(companyMyBean bean) {

    }

    @Override
    public void licenceDiscern() {

    }

    @Override
    public void authIdcard(companyMyBean bean) {

    }

    @Override
    public void companyAdd(NewCompanyBean bean) {

    }

    @Override
    public void face_num() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        startItemActivity(FaceLivenessExpActivity.class);
    }

    @Override
    public void company_num() {

    }

    private PopupWindow mPopWindow;

    /**
     * 人脸是被注意事项
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(IdentIdCardSecondetail.this).inflate(R.layout.compact_comtent_photo, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(IdentIdCardSecondetail.this.getSupportFragmentManager());
                mPresenter.face_num();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(IdentIdCardSecondetail.this).inflate(R.layout.activity_identidcard_second, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}

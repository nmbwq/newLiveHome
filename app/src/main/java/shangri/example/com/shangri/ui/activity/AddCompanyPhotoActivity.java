package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 上传公司相册
 */
public class AddCompanyPhotoActivity extends BaseActivity<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.commit)
    TextView commit;
    //上传的图片列表

    List<String> photo = new ArrayList<>();
    private PhotoConfig mPhotoConfig;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_company_photo;
    }
    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_company_photo;
    }

    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(this,this);
    }

    @Override
    public void upPhotoAlbum() {
        ToastUtil.showShort("相册上传成功");
        finish();
    }

    @Override
    protected void initViewsAndEvents() {
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
//                Log.e("result", "takeSuccess");
//                ArrayList<TImage> images = result.getImages();
//                mList.get(mCurrentPos).setAnchor_logo(images.get(0).getCompressPath());
//                mList.get(mCurrentPos).setAnchor_name(mList.get(mCurrentPos).getAnchor_name());
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void takeFail(TResult result, String msg) {
                Log.e("result", "takeFail");
            }

            @Override
            public void takeCancel() {
                Log.e("result", "takeCancel");
            }
        });
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
    public void getCompanyMain(CompanyMainBean bean) {

    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void setStarAnchor() {

    }

    @OnClick({R.id.back, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_again:
                break;
            case R.id.commit:
//                mPresenter.upPhotoAlbum("");
                break;
        }
    }
    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
    }

    @Override
    public void deleteImg() {

    }
}

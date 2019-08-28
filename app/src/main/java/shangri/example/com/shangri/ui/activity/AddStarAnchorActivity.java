package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.AddStarAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.ImagesAddListener;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 明星主播
 */
public class AddStarAnchorActivity extends BaseActivity<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.re_star)
    RecyclerView re_star;
    @BindView(R.id.add_again)
    LinearLayout add_again;
    @BindView(R.id.ll_change)
    LinearLayout ll_change;
    @BindView(R.id.commit)
    TextView commit;
    AddStarAdapter mAdapter;

    List<CompanyMainBean.AnchorStar> mList = new ArrayList<>();
    CompanyMainBean.AnchorStar bean = new CompanyMainBean.AnchorStar();
    private ProgressDialogFragment mProgressDialog;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_star_anchor;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_star_anchor;
    }

    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(this, this);
    }

    int mCurrentPos = 0;

    private PhotoConfig mPhotoConfig;
    String mCompressPath;
    int i = 0;
    @Override
    protected void initViewsAndEvents() {
//        InputUtil.hideInputMethdView(this,star);
        mAdapter = new AddStarAdapter(this, R.layout.item_add_star, mList, new ImagesAddListener() {

            @Override
            public void add(int position) {
                mCurrentPos = position;
                Log.e("initViewsAndEvents1", "mCurrentPos = "+mCurrentPos+"  i = "+(i++));
                choosePhoto(position);
            }
        }, new AddStarAdapter.onClickItem() {
            @Override
            public void onClick(String text,int pos) {
                Log.e("initViewsAndEvents2", "mCurrentPos = "+mCurrentPos+"   pos = "+pos+"  i = "+(i++)+"  text = "+text);
                mList.get(pos).setAnchor_name(text+"");
                mList.get(pos).setAnchor_logo(mList.get(pos).getAnchor_logo()+"");
                mAdapter.notifyDataSetChanged();
//                textMsg = text+"";
            }
        });
        re_star.setLayoutManager(new FastLinearScrollManger(this));
        re_star.setAdapter(mAdapter);
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                Log.e("initViewsAndEvents3", "mCurrentPos = "+mCurrentPos+"  i = "+(i++));
                ArrayList<TImage> images = result.getImages();
                mList.get(mCurrentPos).setAnchor_logo(images.get(0).getCompressPath());
                mList.get(mCurrentPos).setAnchor_name(mList.get(mCurrentPos).getAnchor_name());
                mAdapter.notifyDataSetChanged();
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
        addStarAnchor("", "");
        re_star.clearFocus();
        add_again.requestFocus();
    }

    private void addStarAnchor(String log, String nickname) {
        CompanyMainBean.AnchorStar anchorStar = new CompanyMainBean.AnchorStar();
        anchorStar.setAnchor_logo(log);
        anchorStar.setAnchor_name(nickname);
        mList.add(anchorStar);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStarAnchor() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        CompanyHomepageActivity.isRefresh = true;
        ToastUtil.showShort("主播上传成功");
        finish();
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

    @OnClick({R.id.back, R.id.add_again, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_again:
                if (mList.get(mList.size() - 1).getAnchor_name().length() > 0 && mList.get(mList.size() - 1).getAnchor_logo().length() > 0) {
                    addStarAnchor("", "");
                } else {
                    ToastUtil.showShort("请先完成当前主播信息!");
                }
                break;
            case R.id.commit:
                if (PointUtils.isFastClick()) {
                    for (CompanyMainBean.AnchorStar b:mList){
                        Log.e("commit", "onViewClicked: b.name = "+b.getAnchor_name() );
                    }
                    if (isCommit()){
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.setStarAnchor(mList);
                    }else {
                        ToastUtil.showShort("请先完成当前主播信息!");
                    }
                }
                break;
        }
    }

    private boolean isCommit(){
        for (CompanyMainBean.AnchorStar anchorStar:mList){
            if (anchorStar.getAnchor_name().length()==0||anchorStar.getAnchor_logo().length()==0)
                return false;
        }
        return true;
    }

    @Override
    public void getCompanyMain(CompanyMainBean bean) {
    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void upPhotoAlbum() {

    }

    @Override
    public void requestFailed(String message) {
        Log.e("requestFailed", "requestFailed: message = " + message);
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
    protected void onCreate(Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void deleteImg() {

    }
}

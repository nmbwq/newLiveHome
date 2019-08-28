package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.wantedStatusBean;
import shangri.example.com.shangri.presenter.ResumePresenter;
import shangri.example.com.shangri.presenter.view.ResumeView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的接力界面
 * Created by mschen on 2017/6/23.
 */

public class HaveInviteActivity extends BaseActivity<ResumeView, ResumePresenter> implements ResumeView {


    @BindView(R.id.set_pwd_back)
    ImageView setPwdBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.im3)
    ImageView im3;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.image_sex)
    ImageView imageSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    private ProgressDialogFragment mProgressDialog;
    List<ReadPhotoBean.ResumeBean> PhoneList = new ArrayList<>();
    List<ReadPhotoBean.CaptureBean> capture = new ArrayList<>();
    ResumeIndexBean.ResumeBean resume;

    @Override
    protected ResumePresenter createPresenter() {
        return new ResumePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resumeIndex();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(HaveInviteActivity.this.getSupportFragmentManager());
        mPresenter.readPhoto();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.have_resume_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.have_resume_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public void reload() {
    }


    @Override
    public void resumeUploading(legalIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void resumeAdd() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void resumePhotoDel() {

    }

    @Override
    public void resumePhotoFirst() {

    }

    @Override
    public void resumeUpdate() {

    }

    @Override
    public void readPhoto(ReadPhotoBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (PhoneList != null) {
            PhoneList.clear();
        }
        if (capture != null) {
            capture.clear();
        }
        PhoneList = resultBean.getResume();
        capture = resultBean.getCapture();
        if (resultBean.getResume().size() == 1) {
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))
                    .crossFade()
                    // 设置高斯模糊
                    .into(im1);
        } else if (resultBean.getResume().size() == 2) {
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))
                    .crossFade()
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))

                    .crossFade()
                    // 设置高斯模糊
                    .into(im2);
        } else if (resultBean.getResume().size() == 3) {
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(0).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))

                    .crossFade()
                    // 设置高斯模糊
                    .into(im1);
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(1).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .crossFade()
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))
                    // 设置高斯模糊
                    .into(im2);
            Glide.with(HaveInviteActivity.this)
                    .load(resultBean.getResume().get(2).getImg_url())
                    .placeholder(R.mipmap.bg_touxiang)
                    .transform(new CornersTransform(HaveInviteActivity.this, 10))
                    .crossFade()
                    // 设置高斯模糊
                    .into(im3);
        }
    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {
        resume = resultBean.getResume();
        tvName.setText(resultBean.getResume().getNickname() + "");
        tvAge.setText(resultBean.getResume().getAge() + "岁");
//        性别 1男2女
        if ("1".equals(resultBean.getResume().getSex())) {
            imageSex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nan));
        } else {
            imageSex.setImageDrawable(getResources().getDrawable(R.mipmap.xing_nv));
        }
        tvPhone.setText(resultBean.getResume().getTelephone() + "");

    }

    @Override
    public void platfromType(BossPlatBean resultBean) {

    }

    @Override
    public void wantedStatus(wantedStatusBean resultBean) {

    }

    @OnClick({R.id.set_pwd_back, R.id.tv_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_pwd_back:
                finish();
                break;
            case R.id.tv_fabu:
                Intent intent = new Intent(HaveInviteActivity.this, AddInviteActivity.class);
                intent.putExtra("bean", (Serializable) resume);
                intent.putExtra("PhoneList", (Serializable) PhoneList);
                intent.putExtra("captureList", (Serializable) capture);
                intent.putExtra("isFromUpdate", true);
                startActivity(intent);
                break;
        }
    }
}

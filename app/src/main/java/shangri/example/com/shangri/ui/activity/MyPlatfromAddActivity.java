package shangri.example.com.shangri.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.model.bean.response.MyPlatfromList;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.presenter.MyPlatfromPresenter;
import shangri.example.com.shangri.presenter.view.MyPlatfromView;
import shangri.example.com.shangri.ui.adapter.InformationAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.CommonPopupWindow;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 添加平台
 * Created by admin on 2017/12/22.
 */

public class MyPlatfromAddActivity extends BaseActivity<MyPlatfromView, MyPlatfromPresenter> implements MyPlatfromView {

    private List<SortModelBean.PlatfromsBean> datas = new ArrayList<>();
    private String url;
    private String platfrom_id;
    private int type = 0;

    @Override
    protected MyPlatfromPresenter createPresenter() {
        return new MyPlatfromPresenter(this, this);
    }

    private CommonPopupWindow window;
    private InformationAdapter adapter;
    private RecyclerView dataList;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.iv_2)
    ImageView iv_2;
    @BindView(R.id.rl_nick_name)
    RelativeLayout rl_nick_name;

    @BindView(R.id.login_in)
    TextView login_in;

    @BindView(R.id.tv_title_patol)
    TextView tv_title_patol;
    @BindView(R.id.right_image)
    ImageView right_image;

    @BindView(R.id.tv_pingtai)
    TextView tv_pingtai;


    private PhotoConfig mPhotoConfig;
    private String mCompressPath;

    @Override
    protected void initViewsAndEvents() {
        mPhotoConfig = new PhotoConfig(this, false);
        mPresenter.getPlatfromList();
        if (getIntent().getStringExtra("platfrom_id") != null) {
            String platfrom_name = getIntent().getStringExtra("platfrom_name");
            String guild_name = getIntent().getStringExtra("guild_name");
            String anchor_content = getIntent().getStringExtra("anchor_content");
            String cover_url = getIntent().getStringExtra("cover_url");
            platfrom_id = getIntent().getStringExtra("platfrom_id");
            url = cover_url;
            type = 1;
            login_in.setBackgroundResource(R.mipmap.btn_c2);
            tv_title_patol.setText("编辑直播平台");
            right_image.setVisibility(View.VISIBLE);

            et1.setText(platfrom_name);
            et2.setText(guild_name);
            et3.setText(anchor_content);
            Glide.with(this)
                    .load(cover_url)
                    .placeholder(R.mipmap.ic_moren)
                    .crossFade()
                    .into(iv_2);
        }


        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                TImage image = images.get(0);
                mCompressPath = image.getCompressPath();
                if (TextUtils.isEmpty(mCompressPath)) return;
                mPresenter.updateUserInfo(mCompressPath);
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


        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_pingtai.setText("主播人数：");
            et3.setHint("该公会旗下有多少位主播呢？");
        }

    }

    @OnClick({R.id.right_image, R.id.left_image, R.id.iv1, R.id.mine_profile_image, R.id.login_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_image:
                finish();
                break;
            case R.id.right_image:
                mPresenter.deletePlatfrom(platfrom_id);
                break;
            case R.id.iv1:
                CommonPopupWindow.LayoutGravity layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.ALIGN_RIGHT | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
                window.showBashOfAnchor(rl_nick_name, layoutGravity, 0, 0);
//                window.showAsDropDown(rl_nick_name, 0, 0, Gravity.BOTTOM);
                break;
            case R.id.mine_profile_image:
                hintKeyboard();
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
            case R.id.login_in:
                if (TextUtils.isEmpty(et1.getText().toString().trim())) {
                    ToastUtil.showShort("请输入或选择直播平台");
                    return;
                }
                if (TextUtils.isEmpty(et2.getText().toString())) {
                    ToastUtil.showShort("请输入公会名称");
                    return;
                }
                if (TextUtils.isEmpty(et3.getText().toString())) {
                    ToastUtil.showShort("请输入直播平台ID");
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    ToastUtil.showShort("请上传封面");
                    return;
                }

                if (type == 1) {
                    mPresenter.updatePlatfrom(platfrom_id, et1.getText().toString().trim(), et3.getText().toString()
                            , et2.getText().toString().trim(), url);

                } else {
                    mPresenter.addPlatfrom(et1.getText().toString().trim(), et3.getText().toString()
                            , et2.getText().toString().trim(), url);
                }
                break;

        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void myPlatfromList(MyPlatfromList resultBean) {

    }

    @Override
    public void PlatfromList(SortModelBean resultBean) {
        datas = resultBean.getPlatfroms();
        initPopupWindow();
    }

    @Override
    public void uploadCover(ImageUpload resultBean) {
        url = resultBean.getFile_url();
        Glide.with(this)
                .load(resultBean.getFile_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .into(iv_2);
    }

    @Override
    public void addPlatfrom(String s) {
        finish();
    }

    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window

        window = new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.WRAP_CONTENT, (int) (screenHeight * 0.7)) {
            @Override
            protected void initView() {
                View view = getContentView();
                dataList = view.findViewById(R.id.data_list);
                dataList.setLayoutManager(new FastLinearScrollManger(MyPlatfromAddActivity.this));
                adapter = new InformationAdapter(MyPlatfromAddActivity.this, R.layout.item_popup_list, datas);
                adapter.setItemListListener(new NewsListListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        et1.setText(datas.get(position).getName());
                        window.getPopupWindow().dismiss();
                    }
                });
                dataList.setAdapter(adapter);
            }

            @Override
            protected void initEvent() {
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
        };

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.platfrom_add_details;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.platfrom_add_details;
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

    private void hintKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}

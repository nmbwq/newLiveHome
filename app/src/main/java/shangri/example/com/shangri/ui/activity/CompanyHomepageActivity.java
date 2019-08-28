package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.PlatFormAdapter;
import shangri.example.com.shangri.adapter.StarAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.DeletePopubWindow;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.TakeTurnPhotos;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司主页
 */
public class CompanyHomepageActivity extends BaseActivity<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.company_icon)
    ImageView company_icon;

    @BindView(R.id.iv_edit)
    ImageView iv_edit;
    @BindView(R.id.add1)
    ImageView add1;
    @BindView(R.id.add2)
    ImageView add2;
    @BindView(R.id.add3)
    ImageView add3;
    @BindView(R.id.show_company)
    TextView show_company;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.company_name)
    TextView company_name;
    @BindView(R.id.company_detail)
    TextView company_detail;
    @BindView(R.id.platform)
    RecyclerView re_platform;
    @BindView(R.id.star)
    RecyclerView re_star;
    @BindView(R.id.company_photos)
    ConvenientBanner company_photos;
    @BindView(R.id.company_location)
    TextView company_location;
    //是否认证
    @BindView(R.id.company_re)
    TextView company_re;
    //是否认证图标
    @BindView(R.id.iv_re)
    ImageView iv_re;
    //公司相册只有一张
    @BindView(R.id.iv_one)
    ImageView iv_one;
    @BindView(R.id.qurenzheng)
    LinearLayout qurenzheng;
    @BindView(R.id.rl_bg)
    RelativeLayout rl_bg;
    @BindView(R.id.view_line)
    View view_line;
    private StarAdapter starAdapter;
    private PlatFormAdapter platFormAdapter;

    List<CompanyMainBean.CompanyAnchor> anchorList = new ArrayList<>();
    List<CompanyMainBean.CompanyPlatfrom> platfromList = new ArrayList<>();
    List<CompanyMainBean.CompanyPhoto> photoList = new ArrayList<>();

    private ProgressDialogFragment mProgressDialog;
    //上传的图片列表
    List<String> photo = new ArrayList<>();
    private PhotoConfig mPhotoConfig;
    //更新信息
    public static boolean isRefresh = false;
    String location;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_company_homepage;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_company_homepage;
    }

    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initAdapter();
        refresh();
        mPhotoConfig.setListener(new TakephotoFinishListener() {
            @Override
            public void takeSuccess(TResult result) {
                ArrayList<TImage> images = result.getImages();
                if (images == null || images.size() == 0) return;
                if (photo.size()>0){
                    photo.clear();
                }
                for (int i = 0;i<images.size();i++){
                    photo.add(images.get(i).getCompressPath());
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }

                mProgressDialog.show(CompanyHomepageActivity.this.getSupportFragmentManager());
                mPresenter.upPhotoAlbum(photo);
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
    private void refresh(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.getCompanyMain();
    }
    @Override
    public void deleteImg() {
        refresh();
        ToastUtil.showShort("操作成功!");
    }

    /**
     * 开启图片选择器
     */
    private void choosePhoto(final int limit) {
        new SelectPhotoPopopWindow(this, new SelectPhotoListener() {

            @Override
            public void selectFromAlbum() {
                mPhotoConfig.selectFromAlbum1(limit);
            }

            @Override
            public void takePhoto() {
                mPhotoConfig.takePhoto1();
            }

            @Override
            public void savePhoto() {

            }
        }, "取消");

    }

    private void initAdapter(){
        starAdapter = new StarAdapter(this,R.layout.item_paltform_star,anchorList);
        re_star.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        re_platform.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        platFormAdapter = new PlatFormAdapter(this,R.layout.item_paltform_star2,platfromList);
        re_platform.setAdapter(platFormAdapter);
        re_star.setAdapter(starAdapter);
        starAdapter.setOnClick(new StarAdapter.onClick() {
            @Override
            public void onItemClick(int pos) {
                mPresenter.deleteImg("2",anchorList.get(pos).getId());
            }
        });
        platFormAdapter.setOnClick(new StarAdapter.onClick() {
            @Override
            public void onItemClick(int pos) {
                mPresenter.deleteImg("1",platfromList.get(pos).getId());
            }
        });
    }

    String Company_name ="";
    String Company_short_name ="";
    String Company_description = "";
    String company_scale = "";
    String anchor_scale = "";
    String mCompressPath = "";
    @Override
    public void getCompanyMain(CompanyMainBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (bean.getCompany().getLicense_status()!=null&&bean.getCompany().getFace_status()!=null){
            if (bean.getCompany().getLicense_status().equals("1")&&bean.getCompany().getFace_status().equals("1")){
                //已认证
                company_re.setText("已认证");
                iv_re.setImageResource(R.mipmap.gsjsyi);
                company_re.setTextColor(getResources().getColor(R.color.white));
            }else {
                //去认证
                company_re.setText("去认证");
                iv_re.setImageResource(R.mipmap.gsjsqu);
                company_re.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
            }
        }else {
            //去认证
            company_re.setText("去认证");
            iv_re.setImageResource(R.mipmap.gsjsqu);
            company_re.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
        }
        location = bean.getCompany().getLocation()+"";
        Company_short_name = bean.getCompany().getCompany_short_name()+"";
        Company_name = bean.getCompany().getCompany_name();
        Company_description = bean.getCompany().getCompany_description();
        anchor_scale = bean.getCompany().getAnchor_scale();
        company_scale = bean.getCompany().getCompany_scale();
        mCompressPath = bean.getCompany().getLogo();
        Glide.with(this).load(bean.getCompany().getLogo()).into(company_icon);
        company_name.setText(bean.getCompany().getCompany_short_name() + "");
        company_location.setText(bean.getCompany().getLocation() + "");
        company_detail.setText(bean.getCompany().getCompany_description() + "");

        tv1.setText("规模:"+bean.getCompany().getCompany_scale());
        tv2.setText("旗下主播:"+bean.getCompany().getAnchor_scale());
        if (bean.getCompany_anchor().size()>0) {
            re_star.setVisibility(View.VISIBLE);
            starAdapter.clear();
            starAdapter.addAll(bean.getCompany_anchor());
        }else {
            re_star.setVisibility(View.GONE);
        }
        if (bean.getCompany_platfrom().size()>0){
            re_platform.setVisibility(View.VISIBLE);
            platFormAdapter.clear();
            platFormAdapter.addAll(bean.getCompany_platfrom());
        }else {
            re_platform.setVisibility(View.GONE);
        }
        if (bean.getCompany_photo().isEmpty()){
            company_photos.setVisibility(View.GONE);
            iv_one.setVisibility(View.GONE);
        }else {
            photoList.clear();
            photoList.addAll(bean.getCompany_photo());
            if (bean.getCompany_photo().size()>1){
                iv_one.setVisibility(View.GONE);
                company_photos.setVisibility(View.VISIBLE);
                new TakeTurnPhotos(this, bean.getCompany_photo(), company_photos, new OnItemClickListener() {
                    @Override
                    public void onItemClick(final int position) {
                        Log.e("onItemClick", "点击了图片");
                        new DeletePopubWindow(CompanyHomepageActivity.this, "取消", new DeletePopubWindow.DeleteFace() {
                            @Override
                            public void delete() {
                                mPresenter.deleteImg("3",photoList.get(position).getId());
                            }
                        });
                    }
                });
            }else {
                iv_one.setVisibility(View.VISIBLE);
                Glide.with(this).load(bean.getCompany_photo().get(0).getImg_url()).fitCenter().into(iv_one);
                company_photos.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void setStarAnchor() {

    }

    @Override
    public void upPhotoAlbum() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.getCompanyMain();
        ToastUtil.showShort("相册上传成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPhotoConfig = new PhotoConfig(this,false);
        super.onCreate(savedInstanceState);
        mPhotoConfig.getTakePhoto(this).onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        view_line.requestFocus();
        rl_bg.requestFocus();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh){
            refresh();
            isRefresh = !isRefresh;
        }
    }

    @OnClick({R.id.back, R.id.add1, R.id.add2, R.id.add3, R.id.iv_edit, R.id.show_company,R.id.qurenzheng,R.id.iv_one})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add1:
                //入驻平台
                startActivity(new Intent(this, EnterPlatformActivity.class));
                break;
            case R.id.add2:
                //添加主播
                startActivity(new Intent(this, AddStarAnchorActivity.class));
                break;
            case R.id.add3:
                //选择公司相册
                choosePhoto(5);
                break;
            case R.id.iv_edit:
                //编辑公司信息
                Intent intent = new Intent(this, AddCompanyInfoActivity2.class);
                intent.putExtra("company_scale",company_scale);
                intent.putExtra("anchor_scale",anchor_scale);
                intent.putExtra("company_name",Company_name);
                intent.putExtra("company_description",Company_description);
                intent.putExtra("mCompressPath",mCompressPath);
                intent.putExtra("location",location);
                intent.putExtra("Company_short_name",Company_short_name);
                startActivity(intent);
                break;
            case R.id.show_company:
                //预览
                startActivity(new Intent(this,CompanyHomepageActivityTwo.class));
                break;
            case R.id.qurenzheng:
                //跳转到去认证页面
                startActivity(new Intent(this,CompanyCertificationActivity.class));
                break;
            case R.id.iv_one:
                //跳转到去认证页面
                new DeletePopubWindow(CompanyHomepageActivity.this, "取消", new DeletePopubWindow.DeleteFace() {
                            @Override
                            public void delete() {
                                mPresenter.deleteImg("3",photoList.get(0).getId());
                            }
                        });
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
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}

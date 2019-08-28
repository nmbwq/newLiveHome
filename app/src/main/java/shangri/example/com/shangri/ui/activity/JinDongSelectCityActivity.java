package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.PermissionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.CitySelect.bean.AdressBean;
import shangri.example.com.shangri.CitySelect.bean.City;
import shangri.example.com.shangri.CitySelect.bean.County;
import shangri.example.com.shangri.CitySelect.bean.Province;
import shangri.example.com.shangri.CitySelect.bean.Street;
import shangri.example.com.shangri.CitySelect.db.manager.AddressDictManager;
import shangri.example.com.shangri.CitySelect.widget.AddressSelector;
import shangri.example.com.shangri.CitySelect.widget.OnAddressSelectedListener;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.presenter.UpdateUserInfoPresenter;
import shangri.example.com.shangri.presenter.view.UpdateUserView;
import shangri.example.com.shangri.ui.listener.SelectPhotoListener;
import shangri.example.com.shangri.ui.listener.TakephotoFinishListener;
import shangri.example.com.shangri.ui.popupwindow.SelectPhotoPopopWindow;
import shangri.example.com.shangri.ui.view.PhotoView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.FileUtil;
import shangri.example.com.shangri.util.PhotoConfig;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 仿京东选择省市区
 * Created by chengaofu on 2017/7/1.
 */

public class JinDongSelectCityActivity extends BaseActivity<UpdateUserView, UpdateUserInfoPresenter> implements UpdateUserView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private LinearLayout content;

    @Override
    protected void initViewsAndEvents() {
        content = (LinearLayout) findViewById(R.id.content);
        AddressSelector selector = new AddressSelector(this);
        //获取地址管理数据库

        selector.setTextSize(14);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
        selector.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景

        selector.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色

        selector.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色

////        //获取数据库管理
        AddressDictManager addressDictManager = selector.getAddressDictManager();
        AdressBean.ChangeRecordsBean changeRecordsBean = new AdressBean.ChangeRecordsBean();
        changeRecordsBean.parentId = 0;
        changeRecordsBean.name = "";
        changeRecordsBean.id = 35;
        addressDictManager.inserddress(changeRecordsBean);//对数据库里增加一个数据
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                        (street == null ? "" : street.name);
                Log.d("Debug", "返回的地址信息是" + s);
            }
        });
        View view = selector.getView();
        content.addView(view);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_jigndong_select;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_jigndong_select;
    }

    @Override
    protected UpdateUserInfoPresenter createPresenter() {
        return null;
    }


    @Override
    public void updateUser(ImageUpload resultBean) { //用户头像更新成功

    }

    @Override
    public void requestFailed(String message) {
    }
}

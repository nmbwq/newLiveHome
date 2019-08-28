package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.presenter.SettingPresenter;
import shangri.example.com.shangri.presenter.view.SettingView;


/**
 *
 *
 */

public class ChectIsHaveActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    @BindView(R.id.tv_layout_contont2)
    TextView tvLayoutContont2;
    //   0是跳转公司界面  1 会长创建公司  2管理员绑定公会 3会长绑定公会 4主播绑定公会
    public static int type = 0;


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_checkishave_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_checkishave_layout;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                tvLayoutImage.setImageResource(R.mipmap.chuangjian_gongsi);
                break;
            case 2:
                tvLayoutImage.setImageResource(R.mipmap.bangding_gonghui);
                break;
            case 3:
                tvLayoutImage.setImageResource(R.mipmap.bangding_gonghui);
                break;
            case 4:
                tvLayoutImage.setImageResource(R.mipmap.bangding_gonghui);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void checkVersion(List<AppVersionBean> resultBean) {

    }

    @Override
    public void logout() {

    }

    @OnClick({R.id.setting_back, R.id.tv_layout_contont2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_layout_contont2:
                switch (type) {
                    //创建公司
                    case 1:
                        startActivity(new Intent(ChectIsHaveActivity.this, CreatCityActivity.class));
                        break;
                    //管理员绑定公会
                    case 2:
                        break;
                    //会长绑定公会
                    case 3:
                        break;

                }
                break;
        }
    }
}

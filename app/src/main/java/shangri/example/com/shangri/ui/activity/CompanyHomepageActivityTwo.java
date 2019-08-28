package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.ui.fragment.CompanyDisplayFragment;
import shangri.example.com.shangri.ui.fragment.CompanyIntroductionFragment;
import shangri.example.com.shangri.ui.fragment.HotFragment;
import shangri.example.com.shangri.util.ViewPagerForScrollView;

/**
 * 公司预览页
 */
public class CompanyHomepageActivityTwo extends BaseActivity<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.company_icon)
    ImageView company_icon;
    //是否认证
    @BindView(R.id.company_re)
    TextView company_re;
    @BindView(R.id.company_name)
    TextView company_name;
    //是否认证图标
    @BindView(R.id.iv_re)
    ImageView iv_re;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    @BindView(R.id.radio_btn1)
    RadioButton radio_btn1;
    @BindView(R.id.radio_btn2)
    RadioButton radio_btn2;
    @BindView(R.id.radio_btn3)
    RadioButton radio_btn3;
    @BindView(R.id.viewpager)
    ViewPagerForScrollView viewpager;
    private List<Fragment> mList;

    public static String COMPANY_TOKEN="";


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_company_homepage2;
    }


    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_company_homepage2;
    }

    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (getIntent()!=null){
            COMPANY_TOKEN = getIntent().getStringExtra("COMPANY_TOKEN");
        }
        mPresenter.getCompanyMain();
        mList = new ArrayList<>();
        mList.add(new CompanyIntroductionFragment());//公司介绍
        mList.add(new CompanyDisplayFragment());//公司展示
        mList.add(new HotFragment());//热门招聘
        initRadio();
    }

    private void changeView(int pos){
        switch (pos){
            case 0:
                radio_btn1.setBackgroundResource(R.drawable.shape_bottom_line);
                radio_btn2.setBackgroundResource(R.color.transparent);
                radio_btn3.setBackgroundResource(R.color.transparent);
                radio_btn1.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                radio_btn2.setTextColor(getResources().getColor(R.color.white));
                radio_btn3.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                radio_btn2.setBackgroundResource(R.drawable.shape_bottom_line);
                radio_btn1.setBackgroundResource(R.color.transparent);
                radio_btn3.setBackgroundResource(R.color.transparent);
                radio_btn2.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                radio_btn1.setTextColor(getResources().getColor(R.color.white));
                radio_btn3.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                radio_btn3.setBackgroundResource(R.drawable.shape_bottom_line);
                radio_btn1.setBackgroundResource(R.color.transparent);
                radio_btn2.setBackgroundResource(R.color.transparent);
                radio_btn3.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                radio_btn2.setTextColor(getResources().getColor(R.color.white));
                radio_btn1.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    private void initRadio() {
        radio_group.getChildAt(0).setSelected(true);
        viewpager.setCurrentItem(0);
        changeView(0);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn1:
                        changeView(0);
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.radio_btn2:
                        changeView(1);
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.radio_btn3:
                        changeView(2);
                        viewpager.setCurrentItem(2);
                        break;
                }

            }
        });
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }

        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeView(position);
                radio_group.getChildAt(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void getCompanyMain(CompanyMainBean bean) {
        radio_btn3.setText("热招职位("+bean.getCompany().getHot_number()+")");
        company_name.setText(bean.getCompany().getCompany_short_name()+"");
        if (bean.getCompany().getLicense_status()!=null&&bean.getCompany().getFace_status()!=null){
            if (bean.getCompany().getLicense_status().equals("1")&&bean.getCompany().getFace_status().equals("1")){
                //已认证
                company_re.setText("已认证");
                iv_re.setImageResource(R.mipmap.gsjsyi);
                company_re.setTextColor(getResources().getColor(R.color.white));
            }else {
                //未认证
                company_re.setText("未认证");
                iv_re.setImageResource(R.mipmap.gsjswei);
                company_re.setTextColor(getResources().getColor(R.color.white));
            }
        }

        title.setText(bean.getCompany().getCompany_name() + "");
        Glide.with(this).load(bean.getCompany().getLogo()).into(company_icon);
    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void setStarAnchor() {

    }

    @Override
    public void upPhotoAlbum() {

    }

    @Override
    public void requestFailed(String message) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void deleteImg() {

    }
}

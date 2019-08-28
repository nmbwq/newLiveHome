package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CardRequestBean;
import shangri.example.com.shangri.presenter.MycardPresenter;
import shangri.example.com.shangri.presenter.view.MyCardView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.MyMessageFreagment;


/**
 * 消息界面
 */

public class NewMessageActivity extends BaseActivity<MyCardView, MycardPresenter> implements MyCardView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tl_2)
    SlidingTabLayout tl2;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;


    private ProgressDialogFragment mProgressDialog;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    List<String> data = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_message_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_message_layout;
    }

    @Override
    protected MycardPresenter createPresenter() {
        return new MycardPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tvTitle.setText("我的消息");
        data.add("全部消息");
        data.add("辅导消息");
        data.add("任务消息");
        data.add("系统消息");
        Log.d("Debug", "返回的数据长度为" + data.size());
        for (int i = 0; i < data.size(); i++) {
            MyMessageFreagment instance = MyMessageFreagment.getInstance(i);
            mFragments.add(instance);
        }
        View decorView = getWindow().getDecorView();
        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tl2.setViewPager(vp);

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


    @OnClick({R.id.setting_back, R.id.tl_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tl_2:
                break;
        }
    }

    @Override
    public void personalPetail(CardRequestBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void otherDetail(CardRequestBean bean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}

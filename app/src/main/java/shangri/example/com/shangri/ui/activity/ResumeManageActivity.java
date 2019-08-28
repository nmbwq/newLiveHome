package shangri.example.com.shangri.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.ResumeManagePresent;
import shangri.example.com.shangri.presenter.view.ResumeManageView;
import shangri.example.com.shangri.ui.fragment.ResumeManageQiangtaFragment;
import shangri.example.com.shangri.ui.fragment.XIaoboTuijianFragment;
import shangri.example.com.shangri.ui.fragment.ResumeManage1Fragment;
import shangri.example.com.shangri.ui.fragment.ResumeManage3Fragment;
import shangri.example.com.shangri.ui.fragment.ResumeManage4Fragment;
import shangri.example.com.shangri.util.TableUtils;

/**
 * 会长-简历管理
 */
public class ResumeManageActivity extends BaseActivity<ResumeManageView, ResumeManagePresent> implements ResumeManageView {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tabs)
    TabLayout tabs;
    List<Fragment> fragments = new ArrayList<>();
    //跳转的位置
    int type = 0;

    @Override
    protected ResumeManagePresent createPresenter() {
        return new ResumeManagePresent(this, this);
    }

    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected void initViewsAndEvents() {
        type = getIntent().getIntExtra("positon", 0);
        List<String> channelNames = new ArrayList<>();
        channelNames.add("主播投递");
        channelNames.add("小播推荐");
//        channelNames.add("拨电话");
//        channelNames.add("留电话");
        channelNames.add("抢ta");
        fragments.add(new ResumeManage1Fragment());
        fragments.add(new XIaoboTuijianFragment());
//        fragments.add(new ResumeManage3Fragment());
//        fragments.add(new ResumeManage4Fragment());
        fragments.add(new ResumeManageQiangtaFragment());
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), fragments, channelNames);
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), fragments, channelNames);
        }

        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
        TableUtils.dynamicSetTabLayoutMode(tabs);
        viewpager.setCurrentItem(type);
    }

    @Override
    public void sendResume(sendResumeBean resultBean) {

    }

    @Override
    public void upList(upListBean resultBean) {

    }

    @Override
    public void gradeList(WelfareGuildBean resultBean) {

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_resume_manage;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_resume_manage;
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
    public void getGuildList(WelfareGuildBean welfareGuildBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

}

package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;

/**
 * 我的收藏 是freagment的载体
 * Created by admin on 2017/12/22.
 */

public class MyCollectResumeActivity extends BaseActivity<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.setting_back)
    ImageView settingBack;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.my_collect_activity1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.my_collect_activity1;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void guildRatio() {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        ImageView tv = (ImageView) findViewById(R.id.setting_back);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

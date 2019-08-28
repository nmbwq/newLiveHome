package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyTaskPresenter;
import shangri.example.com.shangri.presenter.view.MyTaskView;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 我的主播
 * Created by admin on 2017/12/22.
 */

public class MyvipActivity extends BaseActivity<MyTaskView, MyTaskPresenter> implements MyTaskView {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.im_icon)
    CircleImageView imIcon;
    @BindView(R.id.my_info)
    RelativeLayout myInfo;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    String s;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_vip_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_vip_layout;
    }

    @Override
    protected MyTaskPresenter createPresenter() {
        return new MyTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.memberLate();
        tvName.setText(UserConfig.getInstance().getUserName() + "");

        BitmapCache.getInstance().loadBitmaps(imIcon, UserConfig.getInstance().getAvatar(), null);

    }


    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void mvpExpleme(MyTaskBean resultBean) {

    }

    @Override
    public void AnchorList(MyAnchorListBeanResponse1 resultBean) {

    }

    @Override
    public void memberLate(timeBean resultBean) {
        String time = resultBean.getMember().getMember_time();
        try {
            s = TimeUtil.longToString(Long.parseLong(time), "yyyy.MM.dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (s.length() == 0) {
            tvText.setText("您还不是我们的vip宝宝哦");
        } else {
            tvText.setText("您的vip会员将于" + s + "到期");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.memberLate();
    }

    @OnClick({R.id.tv_commit, R.id.setting_back, R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.rl6, R.id.rl7, R.id.rl8, R.id.rl9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(MyvipActivity.this, SelectPayActivity.class).putExtra("time", s));
                }
                break;
            case R.id.setting_back:
                finish();
                break;
            //在线辅导
            case R.id.rl1:
                break;
            //任务分配
            case R.id.rl2:
                break;
            //业绩查看
            case R.id.rl3:
                break;
            //业绩分析
            case R.id.rl4:
                break;
            //主播TOP榜
            case R.id.rl5:
                break;
                //百科推广
            case R.id.rl6:
                break;
                //定制播单
            case R.id.rl7:
                break;
                //专属客服
            case R.id.rl8:
                break;
                //尊贵身份
            case R.id.rl9:
                break;
        }
    }
}

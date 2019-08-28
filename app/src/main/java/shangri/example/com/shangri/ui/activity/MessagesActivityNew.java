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

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.event.ReadBean;
import shangri.example.com.shangri.model.bean.response.NewMessageBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.presenter.NewMessagePresenter;
import shangri.example.com.shangri.presenter.view.NewMessageView;
import shangri.example.com.shangri.ui.fragment.MyAnchorBindingFreagment;
import shangri.example.com.shangri.ui.fragment.PresidentMsgFragment;
import shangri.example.com.shangri.ui.fragment.SystemNotificationFragment;
import shangri.example.com.shangri.ui.fragment.YueliaoFreagment;
import shangri.example.com.shangri.ui.view.CustomScrollViewPager;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.AndroidInterface.yueLiaoMessageFace;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 消息页面
 */
public class MessagesActivityNew extends BaseActivity<NewMessageView, NewMessagePresenter> implements NewMessageView,yueLiaoMessageFace {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    @BindView(R.id.rb_1)
    RadioButton rb_1;
    @BindView(R.id.rb_2)
    RadioButton rb_2;
    @BindView(R.id.rb_0)
    RadioButton rb_0;
    @BindView(R.id.viewpager)
    CustomScrollViewPager viewpager;
    private List<Fragment> mList;

    int type = 0;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_messages_new;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_messages_new;
    }

    @Override
    protected NewMessagePresenter createPresenter() {
        return new NewMessagePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        CompanyInterfaceUtils.setyueLiaoMessageFace(this);
        //url 为返回的跳转类型   职位推荐或是主播推荐返回 1 系统消息返回为2
        String s = getIntent().getStringExtra("url");
        if (s != null && s.length() == 1) {
            type = Integer.parseInt(s);
        }
        mList = new ArrayList<>();
        //每个消息未读数量
        mPresenter.getNewMessage(1, 1);
        mList.add(new YueliaoFreagment());//tab3
        mList.add(new PresidentMsgFragment());//tab1
        mList.add(new SystemNotificationFragment());//tab2
        viewpager.setCurrentItem(type);

        initRadio();
    }

    private void initRadio() {
        radio_group.getChildAt(type).setSelected(true);
        changeView(type);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_0:
                        changeView(0);
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_1:
                        changeView(1);
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.rb_2:
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
        viewpager.setCurrentItem(type);
    }

//
//    //刷新上面未读数量
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(ReadBean baseEvent) {
//        Log.d("Debug", "接受到刷新上面未读数量");
//        mPresenter.getNewMessage(1, 1);
//    }

    private void changeView(int pos) {
        switch (pos) {
            case 0:
                rb_0.setBackgroundResource(R.drawable.shape_bottom_line2);
                rb_1.setBackgroundResource(R.color.col39);
                rb_2.setBackgroundResource(R.color.col39);
                rb_0.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                rb_1.setTextColor(getResources().getColor(R.color.alpha_75_white));
                rb_2.setTextColor(getResources().getColor(R.color.alpha_75_white));
                break;
            case 1:
                rb_1.setBackgroundResource(R.drawable.shape_bottom_line2);
                rb_2.setBackgroundResource(R.color.col39);
                rb_0.setBackgroundResource(R.color.col39);
                rb_1.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                rb_2.setTextColor(getResources().getColor(R.color.alpha_75_white));
                rb_0.setTextColor(getResources().getColor(R.color.alpha_75_white));
                break;
            case 2:
                rb_2.setBackgroundResource(R.drawable.shape_bottom_line2);
                rb_1.setBackgroundResource(R.color.col39);
                rb_0.setBackgroundResource(R.color.col39);
                rb_2.setTextColor(getResources().getColor(R.color.text_color_light_yellow));
                rb_1.setTextColor(getResources().getColor(R.color.alpha_75_white));
                rb_0.setTextColor(getResources().getColor(R.color.alpha_75_white));
                break;

        }
    }

    @Override
    public void getMessage(NewMessageBean newMessageBean) {
        //职位推荐以及主播简历未读数量
        if (newMessageBean.getRecommend_noread_total() > 0) {
            if (UserConfig.getInstance().getRole().equals("1")) {
                rb_1.setText("主播简历(" + newMessageBean.getRecommend_noread_total() + ")");
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                rb_1.setText("职位推荐(" + newMessageBean.getRecommend_noread_total() + ")");
            }
        } else {
            if (UserConfig.getInstance().getRole().equals("1")) {
                rb_1.setText("主播简历");
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                rb_1.setText("职位推荐");
            }
        }
//        系统通知未读数
        if (newMessageBean.getSystem_noread_total() > 0) {
            rb_2.setText("系统消息(" + newMessageBean.getSystem_noread_total() + ")");
        } else {
            rb_2.setText("系统消息");
        }
//        约聊未读数
        if (newMessageBean.getChat_number() > 0) {
            rb_0.setText("聊天(" + newMessageBean.getChat_number() + ")");
        } else {
            rb_0.setText("聊天");
        }
    }

    @Override
    public void isReadMessage() {
        EventBus.getDefault().post(new ReadBean());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void anchorDetail(anchorDetailBean bean) {

    }

    /**
     * 点击列表中item  设置已读以后刷新上面title数据
     */
    @Override
    public void resush() {
        Log.d("Debug", "接受到刷新上面未读数量");
        mPresenter.getNewMessage(1, 1);
    }
}

package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.fragment.AnchorChectListFreagment;
import shangri.example.com.shangri.ui.fragment.GuidManagerFragment;
import shangri.example.com.shangri.ui.fragment.GuidManagerFragment2;
import shangri.example.com.shangri.ui.fragment.GuidManagerFragment3;
import shangri.example.com.shangri.ui.fragment.NewMyAnchorFreagment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.TableUtils;

public class GuildManagerActivity extends BaseActivity<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.setting_back)
    ImageView setting_back;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_guild_manager;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_guild_manager;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(this, this);
    }

    NewMyAnchorFreagment fragment1;
    GuidManagerFragment2 fragment2;
    GuidManagerFragment3 fragment3;

    @Override
    protected void initViewsAndEvents() {
        mPresenter.requestListData();
        fragment1 = new NewMyAnchorFreagment();
        fragment2 = new GuidManagerFragment2();
        fragment3 = new GuidManagerFragment3();

    }

    private void initFragment() {
        List<String> channelNames = new ArrayList<>();
        channelNames.add("线上公会");
        channelNames.add("审核中");
        channelNames.add("审核失败");
        channelNames.add("主播申请");
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(new AnchorChectListFreagment());

        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), fragments, channelNames);
        }
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
        TableUtils.dynamicSetTabLayoutMode(tabs);
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
        initFragment();
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
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.iv_add_guild})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_add_guild:
                showPopupWindowSevenDays();
                break;
        }
    }


    private PopupWindow mPopWindowSelectdays;


    /**
     * 添加公会弹窗判断
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(GuildManagerActivity.this).inflate(R.layout.layout_rl_select5, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应

//        添加公会  公会升级  添加主播
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        RelativeLayout rl2 = contentView.findViewById(R.id.rl2);
        RelativeLayout rl3 = contentView.findViewById(R.id.rl3);

        RelativeLayout rl_parent = contentView.findViewById(R.id.rl_parent);

        final ImageView im1 = contentView.findViewById(R.id.im1);
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final ImageView im2 = contentView.findViewById(R.id.im2);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        final ImageView im3 = contentView.findViewById(R.id.im3);
        final TextView tv3 = contentView.findViewById(R.id.tv3);


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv1.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv1.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im1.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_1));
                        tv1.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji1));
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji1));
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_shengji2));
                        tv2.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });


        rl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int ea = motionEvent.getAction();
//                   Log.i("TAG", "Touch:"+ea);
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_2));
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        break;
//                       return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        im3.setImageDrawable(getResources().getDrawable(R.mipmap.jiahao_1));
                        tv3.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        break;
//                       return true;
                    }

                }
                return false;
            }

        });

        rl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });


        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //会长跳转
                ActivityUtils.startActivity(GuildManagerActivity.this, BindingGuildeTypectivity.class);
                mPopWindowSelectdays.dismiss();
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuildManagerActivity.this, UpgradeGuildeActivity.class);
                intent.putExtra("type", "3");
                intent.putExtra("guildNameText", "");
                intent.putExtra("guild_id", "");
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuildManagerActivity.this, AddAnchorActivity.class);
                intent.putExtra("guildNameText", "");
                intent.putExtra("guild_id", "");
                intent.putExtra("type", 3 + "");
                startActivity(intent);
            }
        });
        View rootview = LayoutInflater.from(GuildManagerActivity.this).inflate(R.layout.activity_patrol, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

}

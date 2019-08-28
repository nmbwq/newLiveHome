package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseFragmentAdapter;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.event.SelectManBean;
import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;
import shangri.example.com.shangri.presenter.SelectManPresenter;
import shangri.example.com.shangri.presenter.view.SelectmanView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.FocusFragment;
import shangri.example.com.shangri.ui.fragment.NovalueFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.ui.view.MyTextView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.VerticalScrollView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 分配任务 选择界面
 * Created by admin on 2017/12/22.
 */

public class SerchAnchorActivity extends BaseActivity<SelectmanView, SelectManPresenter> implements SelectmanView {
    @BindView(R.id.search_content)
    EditText mSearchText;
    @BindView(R.id.search_delete)
    ImageView mDeleteImage;
    @BindView(R.id.search_cancel)
    TextView mCancelText;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.news_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.search_no)
    LinearLayout search_no;


    private BaseFragmentAdapter fragmentAdapter;

    private boolean mIsCancel = true; //是否取消, 默认可以取消
    private String mSearchContent = ""; //搜索内容

    String guildid;
    String guild_name;

    private ArrayList<ManagementDataBean.GuildsBean> GuildList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_serch_anchor_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_serch_anchor_list;
    }

    @Override
    protected SelectManPresenter createPresenter() {
        return new SelectManPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (GuildList.size() > 0) {
            GuildList.clear();
        }
        GuildList = (ArrayList<ManagementDataBean.GuildsBean>) getIntent().getSerializableExtra("GuildList");//通过key来获取你传输的list集
        //默认是第一个公会的id
        if (GuildList.size() > 0) {
            guildid = GuildList.get(0).getGuild_id();
            guild_name = GuildList.get(0).getGuild_name();
        }
        //不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        mSearchText.setFocusable(true);
//        KeyBoardUtil.KeyBoard(SerchAnchorActivity.this, "open");
        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (TextUtils.isEmpty(content)) {
                    mDeleteImage.setVisibility(View.GONE);
                    mIsCancel = true;
                    mCancelText.setText(getResources().getString(R.string.cancel));
                } else {
                    mDeleteImage.setVisibility(View.VISIBLE);
                    mIsCancel = false;
                    mCancelText.setText(getResources().getString(R.string.search));
                }
            }
        });

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mSearchContent = mSearchText.getText().toString();
                    if (TextUtils.isEmpty(mSearchContent)) {
                        ToastUtil.showShort("请输入内容后搜索");
                        return false;
                    }
                    KeyBoardUtil.hide_keyboard_from(SerchAnchorActivity.this, mSearchText);
                    if (!NetWorkUtil.isNetworkConnected(SerchAnchorActivity.this)) {
                        layout_network_break.setVisibility(View.VISIBLE);
                        search_no.setVisibility(View.GONE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        layout_network_break.setVisibility(View.GONE);
                        EventBus.getDefault().post(new SelectManBean(guildid, guild_name, mSearchText.getText().toString()));
                        search_no.setVisibility(View.GONE);
                    }

                    return true;
                }
                return false;
            }
        });


        List<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();

        for (int i = 0; i < GuildList.size(); i++) {
            channelNames.add(GuildList.get(i).getGuild_name());
            mNewsFragmentList.add(new NovalueFragment());
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(getSupportFragmentManager(), mNewsFragmentList, channelNames);
        }
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(0);
        tabs.setupWithViewPager(mViewPager);
        //设置可以滑动
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < fragmentAdapter.getCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);//获得每一个tab
            assert tab != null;
            tab.setCustomView(R.layout.icon_view);//给每一个tab设置view
            ImageView im = tab.getCustomView().findViewById(R.id.tabicon);
            if (GuildList.get(i).getType().equals("0")) {
                im.setVisibility(View.VISIBLE);
                im.setImageDrawable(getResources().getDrawable(R.mipmap.gonghui_renzheng));
            } else {
                im.setVisibility(View.GONE);
            }
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                viewById.setSelected(true);//第一个tab被选中
                viewById.setTextColor(getResources().getColor(R.color.color_d0a76c));
            }
            TextView textView = tab.getCustomView().findViewById(R.id.tabtext);
            textView.setText(channelNames.get(i));//设置tab上的文字
        }
        tabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabs, 10, 10);
            }
        });
        if (tabs != null) {
            tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getCustomView() != null) {
                        tab.getCustomView().findViewById(R.id.tabtext).setSelected(true);
                        TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                        viewById.setTextColor(getResources().getColor(R.color.color_d0a76c));
                        mViewPager.setCurrentItem(tab.getPosition());
                        guildid = GuildList.get(tab.getPosition()).getGuild_id();
                        guild_name = GuildList.get(tab.getPosition()).getGuild_name();
                        if (mSearchText.getText().toString().length() > 0) {
                            EventBus.getDefault().post(new SelectManBean(guildid, guild_name, mSearchText.getText().toString()));
                            search_no.setVisibility(View.GONE);
                        }
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    if (tab.getCustomView() != null) {
                        TextView viewById = tab.getCustomView().findViewById(R.id.tabtext);
                        viewById.setTextColor(getResources().getColor(R.color.color_999999));
                        tab.getCustomView().findViewById(R.id.tabtext).setSelected(false);
                    }
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
    }


    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.search_delete, R.id.search_cancel, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                mSearchContent = mSearchText.getText().toString();
                if (!NetWorkUtil.isNetworkConnected(SerchAnchorActivity.this)) {
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    search_no.setVisibility(View.GONE);
                } else {
                    layout_network_break.setVisibility(View.GONE);
                    EventBus.getDefault().post(new SelectManBean(guildid, guild_name, mSearchContent));
                    search_no.setVisibility(View.GONE);
                }
                break;
            case R.id.search_cancel:
                if (mIsCancel) { //取消
                    finish();
                } else {  //搜索
                    mSearchContent = mSearchText.getText().toString();
                    if (!NetWorkUtil.isNetworkConnected(SerchAnchorActivity.this)) {
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                        search_no.setVisibility(View.GONE);
                    } else {
                        layout_network_break.setVisibility(View.GONE);
                        EventBus.getDefault().post(new SelectManBean(guildid, guild_name, mSearchContent));
                        search_no.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.search_delete:
                mSearchText.setText("");
                break;
        }
    }

    @Override
    public void taskSelectanchor(taskSelectListBean resultBean) {

    }

    @Override
    public void anchorOplayer(AnchorSerchBean resultBean) {
    }

    @Override
    public void cacheAdd() {
    }

    @Override
    public void Add() {
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

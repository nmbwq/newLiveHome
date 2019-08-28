package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CardRequestBean;
import shangri.example.com.shangri.presenter.MycardPresenter;
import shangri.example.com.shangri.presenter.view.MyCardView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.CardFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;


/**
 * 我的名片界面
 */

public class CallingCardActivity extends BaseActivity<MyCardView, MycardPresenter> implements MyCardView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.mine_profile_image)
    CircleImageView mineProfileImage;
    @BindView(R.id.iv_role)
    ImageView ivRole;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.ll_code_info)
    LinearLayout llCodeInfo;
    @BindView(R.id.tl_2)
    SlidingTabLayout tl2;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;


    @BindView(R.id.ll_guild_info)
    LinearLayout ll_guild_info;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private ProgressDialogFragment mProgressDialog;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    List<CardRequestBean.GuildsBean> guilds = new ArrayList<>();

    private MyPagerAdapter mAdapter;
    //   false是查看自己的名片  true是查看其它的名片
    boolean flag = false;
    String register_id = "";

    String register_role = "";

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_callingcard_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_callingcard_layout;
    }

    @Override
    protected MycardPresenter createPresenter() {
        return new MycardPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        flag = getIntent().getBooleanExtra("flag", false);
        if (!NetWorkUtil.isNetworkConnected(CallingCardActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            if (flag) {
                register_id = getIntent().getStringExtra("register_id");
                mPresenter.otherDetail(register_id);
            } else {
                mPresenter.personalPetail();
            }
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


    @OnClick({R.id.setting_back, R.id.tl_2, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tl_2:
                break;
            case R.id.reload:
                if (!NetWorkUtil.isNetworkConnected(CallingCardActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    if (flag) {
                        register_id = getIntent().getStringExtra("register_id");
                        mPresenter.otherDetail(register_id);
                    } else {
                        mPresenter.personalPetail();
                    }
                }
                break;
        }
    }

    @Override
    public void personalPetail(CardRequestBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            llCodeInfo.setVisibility(View.GONE);
        } else {
            llCodeInfo.setVisibility(View.VISIBLE);
        }
        initView(bean);

        if (bean.getGuilds().size() > 0) {
            ll_guild_info.setVisibility(View.VISIBLE);
            guilds = bean.getGuilds();
            if (mFragments.size() != 0) {
                mFragments.clear();
            }
            for (int i = 0; i < guilds.size(); i++) {
                CardFragment mineFragment = CardFragment.getInstance(guilds.get(i).getGuild_id(), guilds.get(i).getGuild_name(), guilds.get(i).getUid(), guilds.get(i).getAnchor_name(), register_role, register_id);
                mFragments.add(mineFragment);
            }
            View decorView = getWindow().getDecorView();
            mAdapter = new MyPagerAdapter(getSupportFragmentManager());
            vp.setAdapter(mAdapter);
            tl2.setViewPager(vp);
        }

    }

    @Override
    public void otherDetail(CardRequestBean bean) {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        initView(bean);
        if (bean.getGuilds() == null) {
            ll_guild_info.setVisibility(View.GONE);
        } else {
            if (bean.getGuilds().size() == 0) {
                ll_guild_info.setVisibility(View.GONE);
            } else {
                ll_guild_info.setVisibility(View.VISIBLE);
                guilds = bean.getGuilds();
                if (mFragments.size() != 0) {
                    mFragments.clear();
                }
                for (int i = 0; i < guilds.size(); i++) {
                    CardFragment mineFragment = CardFragment.getInstance(guilds.get(i).getGuild_id(), guilds.get(i).getGuild_name(), guilds.get(i).getUid(), guilds.get(i).getAnchor_name(), register_role, register_id);
                    mFragments.add(mineFragment);
                }
                View decorView = getWindow().getDecorView();
                mAdapter = new MyPagerAdapter(getSupportFragmentManager());
                vp.setAdapter(mAdapter);
                tl2.setViewPager(vp);
            }
        }

    }


    private void initView(CardRequestBean bean) {


        Glide.with(CallingCardActivity.this)
                .load(bean.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(mineProfileImage);
        tvName1.setText(bean.getNickname());
        tv_title.setText(bean.getNickname());
        if (bean.getSex().equals("M")) {
            ivSex.setImageResource(R.mipmap.ic_wonan);
        } else {
            ivSex.setImageResource(R.mipmap.ic_wonv);
        }
        if (flag) {
            register_role = bean.getRegister_role();
            switch (bean.getRegister_role()) {
                case "1":
                    ivRole.setImageResource(R.mipmap.ic_wohuizhang);
                    break;
                case "2":
                    ivRole.setImageResource(R.mipmap.ic_wozhubo);
                    break;
                default:
                    ivRole.setImageResource(R.mipmap.ic_woguanli);
                    break;
            }

        } else {
            if (UserConfig.getInstance().getRole().equals("1")) {
                ivRole.setImageResource(R.mipmap.ic_wohuizhang);
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                ivRole.setImageResource(R.mipmap.ic_wozhubo);
            } else {
                ivRole.setImageResource(R.mipmap.ic_woguanli);
            }
        }

        tvCode.setText(bean.getTelephone() + "");


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
            return guilds.get(position).getGuild_name();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}

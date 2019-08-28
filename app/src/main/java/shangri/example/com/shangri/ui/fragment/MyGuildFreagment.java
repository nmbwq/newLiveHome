package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.ChangePoint;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.activity.BindingActivity;
import shangri.example.com.shangri.ui.activity.BindingGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingManagerTypectivity;
import shangri.example.com.shangri.ui.activity.FastAnchorBindingActivity;
import shangri.example.com.shangri.ui.activity.FastBindingActivity;
import shangri.example.com.shangri.ui.activity.JoinGuildActivity;
import shangri.example.com.shangri.ui.adapter.FastMyGuildAdapter;
import shangri.example.com.shangri.ui.adapter.MyGuildAdapter;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter1;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的公会(Freagment界面)
 * Created by admin on 2017/12/22.
 */

public class MyGuildFreagment extends BaseFragment<MyGuildView, MyGuildPresenter> implements MyGuildView {


    @BindView(R.id.re_my_guild_list)
    RecyclerView re_my_guild_list;
    @BindView(R.id.re_falest_guild_list)
    RecyclerView re_falest_guild_list;
    @BindView(R.id.iv_add_guild)
    ImageView iv_add_guild;
//    @BindView(R.id.my_guild_springview)
//    SpringView my_guild_springview;

    @BindView(R.id.empty_type_anchor)
    RelativeLayout empty_type_anchor;

    @BindView(R.id.empty_type_chairman)
    RelativeLayout empty_type_chairman;
    @BindView(R.id.empty_type_manager)
    RelativeLayout empty_type_manager;

    @BindView(R.id.ll_info)
    LinearLayout ll_info;

    @BindView(R.id.appbar)
    AppBarLayout appbar;


    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_support_platfrom)
    TextView tv_support_platfrom;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    @BindView(R.id.rl_fast)
    RelativeLayout rl_fast;


    List<SupportFromList.AuthPlatfromBean> auth_platfrom = new ArrayList<>();
    List<SupportFromList.QuickPlatfromBean> quick_platfrom = new ArrayList<>();

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private MyGuildAdapter mAdapter;
    private FastMyGuildAdapter FastmAdapter;
    //用来添加消息头
    private List<MyGuildListDataBean.GuildsBean> mNewsList = new ArrayList<>();
    private List<MyGuildListDataBean.GuildsBean> FastNewsList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;
    //是否来自公会升级选择快速认证的公会
    boolean isfromguildUpgrade;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.my_guild_activity;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.my_guild_activity;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        appbar.setVisibility(View.GONE);
        isfromguildUpgrade = getActivity().getIntent().getBooleanExtra("IsfromguildUpgrade", false);
//        initSpringView();
        re_my_guild_list.setLayoutManager(new FastLinearScrollManger(getActivity()));
        re_falest_guild_list.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new MyGuildAdapter(getActivity(), R.layout.my_guild_item, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        FastmAdapter = new FastMyGuildAdapter(getActivity(), R.layout.my_guild_item, FastNewsList);
        FastmAdapter.openLoadAnimation(new ScaleInAnimation());
        re_my_guild_list.setAdapter(mAdapter);
        re_falest_guild_list.setAdapter(FastmAdapter);
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.requestListData();
        }

        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_title.setText("公会管理");
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
        } else {
            tv_title.setText("我的公会");
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.tv_support_platfrom, R.id.tv_fast_ident_anchor, R.id.tv_platfrom_ident_anchor, R.id.tv_fast_ident_chairman, R.id.tv_platfrom_ident_chairman, R.id.tv_platfrom_ident_manager, R.id.iv_add_guild, /*R.id.setting_back,*/ R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_support_platfrom:
                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                mPresenter.supportPlatfrom();
                break;
            case R.id.iv_add_guild:
                if (PointUtils.isFastClick()) {
                    toActivity();
                }
                break;
           /* case R.id.setting_back:

                break;*/
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.requestListData();
                    }
                }
                break;
            case R.id.tv_fast_ident_anchor:
                startActivity(new Intent(getActivity(), FastAnchorBindingActivity.class));
                break;
            case R.id.tv_platfrom_ident_anchor:
                startActivity(new Intent(getActivity(), JoinGuildActivity.class));
                break;
            case R.id.tv_fast_ident_chairman:
                startActivity(new Intent(getActivity(), FastBindingActivity.class));
                break;
            case R.id.tv_platfrom_ident_chairman:
                startActivity(new Intent(getActivity(), BindingActivity.class));
                break;
            case R.id.tv_platfrom_ident_manager:
                ActivityUtils.startActivity(getActivity(), BindingManagerTypectivity.class);
                break;
        }
    }

    /**
     * 点击撤销  刷新之前点击进入的freagment
     *
     * @param event
     */
    @SuppressWarnings("JavaDoc")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangePoint event) {
        Log.d("Debug", "到达我的公会列表界面");
        showPopupWindow1(event.getGuild_id());
    }

    private void toActivity() {
        if (UserConfig.getInstance().getRole().equals("1")) {
            //会长跳转到
//            ActivityUtils.startActivity(this, BindingActivity.class);
            ActivityUtils.startActivity(getActivity(), BindingGuildeTypectivity.class);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            //主播跳转
//            Intent intent = new Intent(this, BindingAnchorGuildeTypectivity.class);
//            intent.putExtra("type", 2);
//            startActivity(intent);
            startActivity(new Intent(getActivity(), JoinGuildActivity.class));
        } else {
            //管理员绑定公会
            ActivityUtils.startActivity(getActivity(), BindingManagerTypectivity.class);
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

        if (resultBean.getGuilds().size() > 0 || resultBean.getQuick_gulid().size() > 0) {
            mNewsList.clear();
            FastNewsList.clear();
            if (mAdapter != null) {
                mAdapter.clear();
            }
            if (FastmAdapter != null) {
                FastmAdapter.clear();
            }
            mNewsList = resultBean.getGuilds();
            FastNewsList = resultBean.getQuick_gulid();
            ll_info.setVisibility(View.GONE);
            mAdapter.setData(mNewsList);
            FastmAdapter.setData(FastNewsList);
        } else {
            ll_info.setVisibility(View.VISIBLE);
            tv_support_platfrom.setVisibility(View.VISIBLE);
            if (UserConfig.getInstance().getRole().equals("1")) {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.VISIBLE);
                empty_type_manager.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                empty_type_anchor.setVisibility(View.VISIBLE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.GONE);
                rl_fast.setVisibility(View.GONE);
            } else {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void guildRatio() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPopWindow1.dismiss();
        mPresenter.requestListData();
        ToastUtil.showShort("系数修改成功,明日起生效吆~");
    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        auth_platfrom = resultBean.getAuth_platfrom();
        quick_platfrom = resultBean.getQuick_platfrom();
        showPopupWindowSevenDays();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.requestListData();
        }

    }


    private PopupWindow mPopWindow1;

    /**
     * 选择公会弹窗
     */
    private void showPopupWindow1(final String guild_id) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_next1, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        final EditText et_number = contentView.findViewById(R.id.et_number);
        final TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_number.getText().toString().trim())) {
                    ToastUtil.showShort("请填写大于0小于等于1的系数");
                    return;
                }
                if ((0 > Double.parseDouble(et_number.getText().toString())) || Double.parseDouble(et_number.getText().toString()) > 1) {
                    ToastUtil.showShort("请填写大于0小于等于1的系数");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                mPresenter.guildRatio(guild_id, et_number.getText().toString());
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.my_guild_activity, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(getActivity());
    }


    PopupWindow mPopWindowSelectdays;

    /**
     * 获得七天的弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.support_platfrom_list, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
//        //显示PopupWindow
        RecyclerView re_fast_list = contentView.findViewById(R.id.re_fast_list);
        RecyclerView re_list = contentView.findViewById(R.id.re_list);

        final RelativeLayout rl_12 = contentView.findViewById(R.id.rl_12);
        RelativeLayout rl_11 = contentView.findViewById(R.id.rl_11);
        rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopWindowSelectdays.dismiss();
            }
        });
        rl_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        re_fast_list.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        SupportplatfromAdapter1 supportplatfromAdapter1 = new SupportplatfromAdapter1(getActivity(), R.layout.item_support_platfrom, quick_platfrom);
        supportplatfromAdapter1.openLoadAnimation(new ScaleInAnimation());
        re_fast_list.setAdapter(supportplatfromAdapter1);
        supportplatfromAdapter1.setData(quick_platfrom);

        re_list.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        SupportplatfromAdapter supportplatfromAdapter = new SupportplatfromAdapter(getActivity(), R.layout.item_support_platfrom, auth_platfrom);
        supportplatfromAdapter.openLoadAnimation(new ScaleInAnimation());
        re_list.setAdapter(supportplatfromAdapter);
        supportplatfromAdapter.setData(auth_platfrom);

        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.binding_anchor_type, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

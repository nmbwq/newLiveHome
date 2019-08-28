package shangri.example.com.shangri.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.request.ReqReportBean;
import shangri.example.com.shangri.model.bean.request.ReqShare;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.ReportChildIncomePresenter;
import shangri.example.com.shangri.presenter.view.ReportChildIncomeView;
import shangri.example.com.shangri.ui.activity.BindingActivity;
import shangri.example.com.shangri.ui.activity.BindingGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingManagerTypectivity;
import shangri.example.com.shangri.ui.activity.CompanyListActivity;
import shangri.example.com.shangri.ui.activity.CreatCityActivity;
import shangri.example.com.shangri.ui.activity.FastAnchorBindingActivity;
import shangri.example.com.shangri.ui.activity.FastBindingActivity;
import shangri.example.com.shangri.ui.activity.JoinGuildActivity;
import shangri.example.com.shangri.ui.activity.SelectPayActivity;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter1;
import shangri.example.com.shangri.ui.adapter.TeamIncomeListAdapter;
import shangri.example.com.shangri.ui.dialog.CommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.RefushFace;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.sharepic.GeneratePictureManager;
import shangri.example.com.shangri.util.sharepic.SharePicModel;

/**
 * 5底导子 团队收益
 */
public class ReportChildIncomeFragment extends BaseFragment<ReportChildIncomeView, ReportChildIncomePresenter> implements ReportChildIncomeView, RefushFace {

    @BindView(R.id.rv)
    RecyclerView rv;
//    @BindView(R.id.sv_partol)
//    SpringView sv_partol;


    @BindView(R.id.iv_share_pic)
    ImageView ivSharePic;

    @BindView(R.id.empty_type_anchor)
    RelativeLayout empty_type_anchor;


    @BindView(R.id.rl_fast)
    RelativeLayout rl_fast;

    @BindView(R.id.rl_normal)
    RelativeLayout rl_normal;




    @BindView(R.id.empty_type_chairman)
    RelativeLayout empty_type_chairman;
    @BindView(R.id.empty_type_manager)
    RelativeLayout empty_type_manager;
    @BindView(R.id.tv_support_platfrom)
    TextView tv_support_platfrom;


    @BindView(R.id.rl_empty)
    LinearLayout rlEmpty;

    @BindView(R.id.fl_info)
    RelativeLayout fr_info;
    @BindView(R.id.activity_guoqi_layout)
    LinearLayout activity_guoqi_layout;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    LinearLayout rl_net_info;

    private boolean isFirst = true;

    private Unbinder unbinder;
    private String mType;
    private int mShowType;
    private TeamIncomeListAdapter mAdapter;
    private static final String TAG = "ReportChildIncomeFragme";
    private List<GuildListBean.GuildsBean> mList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;
    private ReqReportBean reqReportBean;
    private int Symbol = 0;
    int height;
    //传递到支付界面的到期时间
    private String endtime = "";
    List<SupportFromList.AuthPlatfromBean> auth_platfrom = new ArrayList<>();
    List<SupportFromList.QuickPlatfromBean> quick_platfrom = new ArrayList<>();

    public static ReportChildIncomeFragment getInstance(String type, int mShowType) {
        Log.i(TAG, "getInstance: ");
        ReportChildIncomeFragment sf = new ReportChildIncomeFragment();
        sf.mType = type;
        sf.mShowType = mShowType;
        Log.i(TAG, "sf.mShowType2 : " + sf.mShowType);
        return sf;
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_report_child2;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_report_child2;
    }

    @Override
    protected ReportChildIncomePresenter createPresenter() {
        Log.i(TAG, "createPresenter: ");
        return new ReportChildIncomePresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        Log.i(TAG, "initViewsAndEvents: ");
//        initSpringView();
        mList = new ArrayList<>();
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            return;
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.mineCount();
        }
        mAdapter = new TeamIncomeListAdapter(getActivity(), R.layout.item_report_income, mList, mType);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mAdapter);
        reqReportBean = new ReqReportBean();
        mAdapter.setOnShareListener(new TeamIncomeListAdapter.onClickShareListener() {
            @Override
            public void onShare(String guild_id) {
                if (mList != null && mList.size() > 0) {
                    ReqShare reqShare = new ReqShare();
                    reqShare.guild_id = guild_id;
                    reqShare.token = UserConfig.getInstance().getToken();
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getChildFragmentManager());
                    mPresenter.onShare(reqShare);//获取要分享的内容
                }
            }
        });
        reqReportBean.show_type = mShowType + "";
        reqReportBean.type = mType;
        reqReportBean.token = UserConfig.getInstance().getToken();
        // 注册订阅者
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadData(GuildListBean listBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mList.size() > 0) {
            mList.clear();
        }
        if (listBean.getQuick_guilds().size() > 0) {
            for (int i = 0; i < listBean.getQuick_guilds().size(); i++) {
                GuildListBean.GuildsBean guildsBean = listBean.getQuick_guilds().get(i);
                guildsBean.setType(1);
                mList.add(guildsBean);
            }
        }
        if (listBean.getGuilds().size() > 0) {
            for (int i = 0; i < listBean.getGuilds().size(); i++) {
                GuildListBean.GuildsBean guildsBean = listBean.getGuilds().get(i);
                guildsBean.setType(0);
                mList.add(guildsBean);
            }
        }
        if (mList.size() == 0) {
            if (isFirst) {
//                showPopupWindow();
            }
            isFirst = false;
            rlEmpty.setVisibility(View.VISIBLE);
            tv_support_platfrom.setVisibility(View.VISIBLE);
            if (UserConfig.getInstance().getRole().equals("1")) {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.VISIBLE);
                empty_type_manager.setVisibility(View.GONE);
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                empty_type_anchor.setVisibility(View.VISIBLE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.GONE);
                rl_fast.setVisibility(View.VISIBLE);
                rl_normal.setVisibility(View.GONE);
            } else {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.VISIBLE);
            }

            rv.setVisibility(View.GONE);
        } else {
            rlEmpty.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            if (UserConfig.getInstance().getRole().equals("2")) {
                Log.d("Debug", "到达发请求界面");
                mPresenter.memberLate();
            } else {
                Log.d("Debug", "没有到达发请求界面");
            }
        }

        Log.i(TAG, "loadData: mlist.size:" + mList.size());
        mAdapter.setData(mList);

    }

    @Override
    public void loadManagerData(RespAdminBean listBean) {
    }

    @Override
    public void onShare(final ResShareBean shareBean) {


        //获得分享内容
        Glide.with(getActivity()).load(shareBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {//获取bitmap传入SharePicModel
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                SharePicModel sharePicModel = new SharePicModel((ViewGroup) getActivity().getWindow().getDecorView());
                sharePicModel.setShareContent(shareBean, resource);
                GeneratePictureManager.getInstance().generate(sharePicModel, new GeneratePictureManager.OnGenerateListener() {
                    @Override
                    public void onGenerateFinished(Throwable throwable, Bitmap bitmap) {
                        if (throwable != null || bitmap == null) {
                            ToastUtil.showShort("分享失败");
                            if (mProgressDialog != null) {
                                mProgressDialog.dismiss();
                            }
                        } else {
                            if (mProgressDialog != null) {
                                mProgressDialog.dismiss();
                            }
                            CommontDialog.showShareDialog(getActivity(), bitmap);
                        }
                    }
                });
            }
        });

    }


    /**
     * 判断是否过期
     *
     * @param shareBean
     */
    @Override
    public void memberLate(timeBean shareBean) {
        //第一次
        if (shareBean.getExist().equals("1")) {
            try {
                endtime = TimeUtil.longToString(Long.parseLong(shareBean.getMember().getMember_time()), "yyyy.MM.dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //弹窗显示
            showPopupWindowSelect();
            activity_guoqi_layout.setVisibility(View.GONE);
            fr_info.setVisibility(View.VISIBLE);
        } else {
            //获取当前时间
            if (shareBean.getMember().getMember_time() != null && shareBean.getMember().getMember_time().length() > 0) {
                long time = System.currentTimeMillis() / 1000;
                try {
                    endtime = TimeUtil.longToString(Long.parseLong(shareBean.getMember().getMember_time()), "yyyy.MM.dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d("Debug", "到期时间为" + endtime);
                Log.d("Debug", "获取当前时间戳" + time);
                if (time > Long.parseLong(shareBean.getMember().getMember_time())) {
                    //过期操作
                    activity_guoqi_layout.setVisibility(View.VISIBLE);
                    fr_info.setVisibility(View.GONE);
                    Log.d("Debug", "已过期");
                    tvTime.setText("到期时间：" + endtime);
                } else {
                    activity_guoqi_layout.setVisibility(View.GONE);
                    fr_info.setVisibility(View.VISIBLE);
                    Log.d("Debug", "没有过期");
                }
            } else {
                Log.d("Debug", "没有返回当前时间");
            }

        }

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
    public void companyMy(companyMyBean resultBean) {
//        sv_partol.onFinishFreshAndLoad(); //停止加载
        if (resultBean.getHas_company() == 0) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (isFirst) {
//                showPopupWindow();
//                homeDialog1();
            }
            isFirst = false;
            rlEmpty.setVisibility(View.VISIBLE);
            tv_support_platfrom.setVisibility(View.VISIBLE);
            if (UserConfig.getInstance().getRole().equals("1")) {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.VISIBLE);
                empty_type_manager.setVisibility(View.GONE);
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                empty_type_anchor.setVisibility(View.VISIBLE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.GONE);
            } else {
                empty_type_anchor.setVisibility(View.GONE);
                empty_type_chairman.setVisibility(View.GONE);
                empty_type_manager.setVisibility(View.VISIBLE);
            }
            rv.setVisibility(View.GONE);
            Symbol = 1;
        } else {
            mPresenter.loadData(reqReportBean);
        }
    }

    @Override
    public void mineCount(CountBean resultBean) {
        Log.d("Debug", "获取数量请求成功请求成功");
        int i = resultBean.getAdmins() + resultBean.getAnchors() + resultBean.getMessages();
        Log.d("Debug", "总数量为" + i);
        if (i > 0) {
            EventBus.getDefault().post(new FourshowEventBean(true));
        } else {
            EventBus.getDefault().post(new FourshowEventBean(false));
        }
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
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getChildFragmentManager());
            if (UserConfig.getInstance().getRole().equals("1")) {
                mPresenter.companyMy();
            } else {
                mPresenter.loadData(reqReportBean);
            }
        }

    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("Debug", "到达团队收益onPause界面");
    }


    @Override
    public void Refush() {
        if (UserConfig.getInstance().getRole().equals("1")) {
            mPresenter.companyMy();
        } else {
            mPresenter.loadData(reqReportBean);
        }
    }

    @Override
    public void ReportRefush() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BrowseEventBean event) {
        if (UserConfig.getInstance().getRole().equals("1")) {
            mPresenter.companyMy();
        } else {
            mPresenter.loadData(reqReportBean);
        }
        Log.d("Debug", "到达第五底导eventbus界面");
    }

    private PopupWindow mPopWindowSelect;

    /**
     * 获得七天的弹窗
     */
    private void showPopupWindowSelect() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_rl_select2, null);
        mPopWindowSelect = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelect.setContentView(contentView);
//        //设置各个控件的点击响应
        ImageView im_close = contentView.findViewById(R.id.im_close);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        TextView tv_time = contentView.findViewById(R.id.tv_time);
        tv_time.setText("到期时间：" + endtime);
//        //显示PopupWindow
        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindowSelect.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @OnClick({R.id.tv_support_platfrom, R.id.tv_fast_ident_anchor, R.id.tv_platfrom_ident_anchor, R.id.tv_fast_ident_chairman, R.id.tv_platfrom_ident_chairman, R.id.tv_platfrom_ident_manager, R.id.tv_time, R.id.tv_pay, R.id.reload})
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
            case R.id.tv_time:
                break;
            case R.id.reload:
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    return;
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getChildFragmentManager());
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        mPresenter.companyMy();
                    } else {
                        mPresenter.loadData(reqReportBean);
                    }
                }
                break;
            case R.id.tv_pay:
                startActivity(new Intent(getActivity(), SelectPayActivity.class).putExtra("time", endtime));
                break;

            case R.id.tv_fast_ident_anchor:
                startActivity(new Intent(getActivity(), FastAnchorBindingActivity.class));
                break;
            case R.id.tv_platfrom_ident_anchor:
                startActivity(new Intent(getActivity(), JoinGuildActivity.class));

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

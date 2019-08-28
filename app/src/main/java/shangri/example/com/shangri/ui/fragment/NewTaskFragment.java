package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

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
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.PatrolPresenter;
import shangri.example.com.shangri.presenter.view.PatrolView;
import shangri.example.com.shangri.ui.activity.ActivityAddNewTask;
import shangri.example.com.shangri.ui.activity.BindingAnchorGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingGuildeTypectivity;
import shangri.example.com.shangri.ui.activity.BindingManagerTypectivity;
import shangri.example.com.shangri.ui.activity.ChectIsHaveActivity;
import shangri.example.com.shangri.ui.activity.CompamyInfoActivity;
import shangri.example.com.shangri.ui.activity.CreatCityActivity;
import shangri.example.com.shangri.ui.activity.LaunchPatrolActivity;
import shangri.example.com.shangri.ui.activity.NewTaskDetailActivity;
import shangri.example.com.shangri.ui.activity.SelectPayActivity;
import shangri.example.com.shangri.ui.adapter.NewTaskAdapter;
import shangri.example.com.shangri.ui.adapter.SocietyAdapter1;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.AndroidInterface.AlertFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.AndroidInterface.RefushFace;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewTaskFragment extends BaseLazyFragment<PatrolView, PatrolPresenter> implements PatrolView, AlertFace, RefushFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;

    @BindView(R.id.tv_down0_text)
    TextView tvDown0Text;
    @BindView(R.id.tv_down0)
    TextView tvDown0;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.tv_down1_text)
    TextView tvDown1Text;
    @BindView(R.id.tv_down1)
    TextView tvDown1;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.tv_down2_text)
    TextView tvDown2Text;
    @BindView(R.id.tv_down2)
    TextView tvDown2;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.rl_guanliyun)
    RelativeLayout rlGuanliyun;
    @BindView(R.id.ll_select_sociaty)
    RelativeLayout llSelectSociaty;

    @BindView(R.id.new_tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.new_tv_layout_contont1)
    TextView tvLayoutContont1;
    @BindView(R.id.new_tv_layout_contont2)
    TextView tvLayoutContont2;

    private int currPage = 1;
    private int mPageNum = 0; //总页数

    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.new_im_show)
    ImageView new_im_show;

    @BindView(R.id.no_city_layout)
    RelativeLayout no_city_layout;
    @BindView(R.id.fl_list)
    FrameLayout fl_list;
    @BindView(R.id.tv_shuaixun)
    TextView tv_shuaixun;

    //过期展示界面
    @BindView(R.id.activity_guoqi_layout)
    LinearLayout activity_guoqi_layout;
    @BindView(R.id.rl_info)
    RelativeLayout fr_info;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    FrameLayout rl_net_info;


    private ProgressDialogFragment mProgressDialog;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private NewTaskAdapter mAdapter;
    private List<NewTaskDataBean.TasksBean> mTaskList = new ArrayList<>();


    //选择图片时候的数据
    private List<ChoiceGuildBean.GuildsBean> imageList = new ArrayList<>();
    private SocietyAdapter1 societyAdapter1;
    //0全部 1 我发出的  会长或是管理员发出的
    private int type = 0;
    //    任务状态 -1 全部 0未开始 1进行中 2已结束
    private int task_status = -1;
    private String guildId = "";
    //    0是跳转公司界面  1 会长创建公司  2管理员绑定公会 3会长绑定公会 4.主播绑定公会
    private int Symbol = 0;
    //false 是判断有没有公会时的请求  true  是点击选择公会的操作
    private Boolean IsNomal = false;

    //传递到支付界面的到期时间
    private String endtime = "";
    //撤销传过来的id
    private String task_id;
    //撤销任务在适配器中的位置
    private int position;

    @Override
    protected void initView() {
        CompanyInterfaceUtils.setAlertBack(this);
        rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new NewTaskAdapter(getActivity(), R.layout.new_item_task, mTaskList, CompanyInterfaceUtils.alertFace);
        mAdapter.openLoadAnimation(new ScaleInAnimation());

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击事件");
                NewTaskDataBean.TasksBean tasksBean = mAdapter.get(position);
                //撤销订单不能点击进去详情
                if (tasksBean.getTask_status() != 3) {
                    startActivity(new Intent(getActivity(), NewTaskDetailActivity.class).putExtra("Bean", tasksBean));
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        rv_partol.setAdapter(mAdapter);

        initSpringView();
        viewInit();
        tv_shuaixun.setText("筛选");

        // 注册订阅者
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BrowseEventBean event) {
        if (event.isChexiao()) {
            task_id = event.getTask_id();
            position = event.getPosition();
            showPop();
        } else {
            if (UserConfig.getInstance().getRole().equals("1")) {
                mPresenter.companyMy();
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                mPresenter.getGuildList();
            } else {
                mPresenter.getGuildList();
            }
        }
    }

    private void viewInit() {

        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.renwu_kong));
        tv_text1_empty.setText("没有一条任务内容");
        tv_text2_empty.setText("这里只是一个空白页呀~");

        if (UserConfig.getInstance().getRole().equals("1")) {
            tvDown1Text.setText("我发出的");
            tvDown2Text.setText("管理员发出的");
            new_im_show.setVisibility(View.VISIBLE);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            tvDown1Text.setText("会长发出的");
            tvDown2Text.setText("管理员发出的");
            new_im_show.setVisibility(View.GONE);
        } else {
            tvDown1Text.setText("我发出的");
            tvDown2Text.setText("会长发出的");
            new_im_show.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void loadData() {


    }

    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                currPage = 1;
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                }
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                requestPatolsList();

            }
        });

    }

    private void requestPatolsList() {

        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            if (currPage < mPageNum) {
                currPage++;
                sv_partol.onFinishFreshAndLoad();
                mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
            } else {
                sv_partol.onFinishFreshAndLoad(); //停止加载
            }
        }

    }


    @OnClick({R.id.tv_pay, R.id.reload, R.id.new_tv_layout_contont2, R.id.rl_all, R.id.rl_my, R.id.rl_guanliyun, R.id.ll_select_sociaty, R.id.new_im_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                }
                break;
            case R.id.new_tv_layout_contont2:




                if (UserConfig.getInstance().getRole().equals("1")) {
                    if (Symbol == 1) {
//                        ToastUtil.showShort("会长创建公司操作");
                        startActivity(new Intent(getActivity(), CreatCityActivity.class));
                    } else if (Symbol == 3) {
                        ActivityUtils.startActivity(getActivity(), BindingGuildeTypectivity.class);
//                        ToastUtil.showShort("会长绑定公会操作");
                    }
                } else if (UserConfig.getInstance().getRole().equals("3")) {
//                    ToastUtil.showShort("管理员绑定公会操作");
                    ActivityUtils.startActivity(getActivity(), BindingManagerTypectivity.class);

                } else {
                    ActivityUtils.startActivity(getActivity(), BindingAnchorGuildeTypectivity.class);
//                    ToastUtil.showShort("主播绑定公会操作");
                }
                break;
            case R.id.new_im_show:
                showPopupWindowSelect();
                break;

            case R.id.tv_pay:
                startActivity(new Intent(getActivity(), SelectPayActivity.class).putExtra("time", endtime));
            case R.id.rl_all:
                currPage = 1;
                tvDown0.setVisibility(View.VISIBLE);
                tvDown1.setVisibility(View.GONE);
                tvDown2.setVisibility(View.GONE);
                tvDown0Text.setTextColor(getResources().getColor(R.color.text_color_task));
                tvDown1Text.setTextColor(getResources().getColor(R.color.color_999999));
                tvDown2Text.setTextColor(getResources().getColor(R.color.color_999999));
                type = 0;

                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                }

                break;
            case R.id.rl_my:
                currPage = 1;
                tvDown0.setVisibility(View.GONE);
                tvDown1.setVisibility(View.VISIBLE);
                tvDown2.setVisibility(View.GONE);
                tvDown0Text.setTextColor(getResources().getColor(R.color.color_999999));
                tvDown1Text.setTextColor(getResources().getColor(R.color.text_color_task));
                tvDown2Text.setTextColor(getResources().getColor(R.color.color_999999));
                type = 1;

                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                }

                break;
            case R.id.rl_guanliyun:
                currPage = 1;
                tvDown0.setVisibility(View.GONE);
                tvDown1.setVisibility(View.GONE);
                tvDown2.setVisibility(View.VISIBLE);
                tvDown0Text.setTextColor(getResources().getColor(R.color.color_999999));
                tvDown1Text.setTextColor(getResources().getColor(R.color.color_999999));
                tvDown2Text.setTextColor(getResources().getColor(R.color.text_color_task));
                type = 2;


                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                }
                break;
            case R.id.ll_select_sociaty:
                IsNomal = true;
                mPresenter.getGuildList();
                break;
        }
    }

    private PopupWindow mPopWindow;
    private PopupWindow mPopWindowSelect;


    @Override
    public void PatrolsListData(PatrolDataBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void requestTaskList(NewTaskDataBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        sv_partol.onFinishFreshAndLoad();
        Log.d("Debug", "任务列表请求数据成功" + resultBean.toString());

        Log.d("Debug", "返回size为" + resultBean.getTasks().size());
        fl_list.setVisibility(View.VISIBLE);
        no_city_layout.setVisibility(View.GONE);

        if (mAdapter == null) {
            Log.d("Debug", "适配器为空");
        } else {
            Log.d("Debug", "适配器不为空");
        }


        if (currPage == 1) {
            mAdapter.setData(resultBean.getTasks());
            if (resultBean.getTasks().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        } else {
            mAdapter.addAll(resultBean.getTasks());
        }
        mTaskList = mAdapter.getAll();
        mPageNum = resultBean.getTotal_page();

        CompamyInfoActivity.type = 0;
        IsNomal = false;
    }

    /**
     * 公会返回数据
     *
     * @param resultBean
     */
    @Override
    public void listGuildData(ChoiceGuildBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.d("Debug", "到达这里");
        if (resultBean.getGuilds().size() > 0) {
            imageList = resultBean.getGuilds();
            imageList.add(0, new ChoiceGuildBean.GuildsBean("", "全部公会"));
            if (IsNomal) {
                showPopupWindow();
            } else {
                mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
            }
            CompamyInfoActivity.type = 0;
            //发送是否过期接口
            if (UserConfig.getInstance().getRole().equals("2")) {
                Log.d("Debug", "到达发请求界面");
                mPresenter.memberLate();
            } else {
                Log.d("Debug", "没有到达发请求界面");
            }

        } else {
            fl_list.setVisibility(View.GONE);
            no_city_layout.setVisibility(View.VISIBLE);
            tvLayoutImage.setImageResource(R.mipmap.bangding_kong);
            if (UserConfig.getInstance().getRole().equals("1")) {
                tvLayoutContont1.setText("管理功能认证公会才能使用呢！");
                tvLayoutContont2.setText("绑定公会");
                Symbol = 3;
                CompamyInfoActivity.type = 3;
            } else if (UserConfig.getInstance().getRole().equals("3")) {
                tvLayoutContont1.setText("您还没有公会呢，赶紧加入一个吧！");
                tvLayoutContont2.setText("加入公会");
                Symbol = 2;
                CompamyInfoActivity.type = 2;
            } else if (UserConfig.getInstance().getRole().equals("2")) {
                Symbol = 4;
                CompamyInfoActivity.type = 4;
                tvLayoutContont1.setText("您还没有公会呢，赶紧加入一个吧！");
                tvLayoutContont2.setText("加入公会");
            }

        }
        IsNomal = false;

    }

    @Override
    public void companyMy(companyMyBean resultBean) {
        Log.d("Debug", "返回的状态为" + resultBean.getHas_company());
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getHas_company() == 0) {
            fl_list.setVisibility(View.GONE);
            no_city_layout.setVisibility(View.VISIBLE);
            tvLayoutImage.setImageResource(R.mipmap.gongsi_kong);
            tvLayoutContont1.setText("您还没有公司呢，赶紧创建一个吧！");
            tvLayoutContont2.setText("创建公司");
            Symbol = 1;
            CompamyInfoActivity.type = 1;
            IsNomal = false;
        } else {
            CompamyInfoActivity.type = 0;
            mPresenter.getGuildList();
            IsNomal = false;
        }
    }

    @Override
    public void inspectDetail(PatrolDataBean.InspectsBean resultBean) {

    }

    @Override
    public void requestFailed(String message) {

        if (UserConfig.getInstance().getRole().equals("2")) {
            sv_partol.onFinishFreshAndLoad();
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_patrol;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_patrol;
    }

    @Override
    protected PatrolPresenter createPresenter() {
        return new PatrolPresenter(getActivity(), this);
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
            ChectIsHaveActivity.type = 0;
            if (currPage == 1) {
                if (UserConfig.getInstance().getRole().equals("1")) {
                    mPresenter.companyMy();
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    mPresenter.getGuildList();
                } else {
                    mPresenter.getGuildList();
                }
            }
            IsNomal = false;

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 注销订阅者
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void getalert() {
        Log.d("Debug", "辅导提醒成功");
    }

    @Override
    public void inspectRead() {
    }


    @Override
    public void taskAoalert() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
    }

    @Override
    public void partIn() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
    }

    /**
     * 撤销操作
     */
    @Override
    public void taskRepeal() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mAdapter.get(position).setTask_status(3);
        mAdapter.notifyDataSetChanged();
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
            showPopupWindowSevenDays();
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

    //提醒操作
    @Override
    public void alert(String task_id) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());

        mPresenter.taskAoalert(task_id);
    }

    //添加操作
    @Override
    public void join(String task_id) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());
        mPresenter.partIn(task_id);
    }

    @Override
    public void Refush() {
        if (UserConfig.getInstance().getRole().equals("1")) {
            mPresenter.companyMy();
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            mPresenter.getGuildList();
        } else {
            mPresenter.getGuildList();
        }
    }

    @Override
    public void ReportRefush() {

    }

    private PopupWindow mPopWindowSelectdays;

    /**
     * 获得七天的弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_rl_select2, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        ImageView im_close = contentView.findViewById(R.id.im_close);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        TextView tv_time = contentView.findViewById(R.id.tv_time);
        tv_time.setText("到期时间：" + endtime);
//        //显示PopupWindow
        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 选择是发布任务还是辅导
     */
    private void showPopupWindowSelect() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_rl_select, null);
        mPopWindowSelect = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelect.setContentView(contentView);
//        //设置各个控件的点击响应
        RelativeLayout rl_down = contentView.findViewById(R.id.rl_down);
        RelativeLayout rl_up = contentView.findViewById(R.id.rl_up);
        ImageView im_show = contentView.findViewById(R.id.im_show);
        //显示PopupWindow
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWindowSelect != null && mPopWindowSelect.isShowing()) {
                    mPopWindowSelect.dismiss();
                    mPopWindowSelect = null;
                }
            }
        });
        im_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopWindowSelect != null && mPopWindowSelect.isShowing()) {
                    mPopWindowSelect.dismiss();
                    mPopWindowSelect = null;
                }
            }
        });
        rl_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityAddNewTask.class).putExtra("flag", false);
                intent.putExtra("type", "1");
                startActivity(intent);
                mPopWindowSelect.dismiss();
            }
        });
        rl_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LaunchPatrolActivity.class));
                mPopWindowSelect.dismiss();
            }
        });

        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindowSelect.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 公会弹窗
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.task_popwindow_layout, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_close = contentView.findViewById(R.id.tv_close);
        RecyclerView select_society = contentView.findViewById(R.id.select_society);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);


        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        final TextView tv3 = contentView.findViewById(R.id.tv3);
        final TextView tv4 = contentView.findViewById(R.id.tv4);
        final TextView tv5 = contentView.findViewById(R.id.tv5);

        final ImageView im1 = contentView.findViewById(R.id.im1);
        final ImageView im2 = contentView.findViewById(R.id.im2);
        final ImageView im3 = contentView.findViewById(R.id.im3);
        final ImageView im4 = contentView.findViewById(R.id.im4);
        final ImageView im5 = contentView.findViewById(R.id.im5);

        final RelativeLayout rl1 = contentView.findViewById(R.id.rl1);
        final RelativeLayout rl2 = contentView.findViewById(R.id.rl2);
        final RelativeLayout rl3 = contentView.findViewById(R.id.rl3);
        final RelativeLayout rl4 = contentView.findViewById(R.id.rl4);
        final RelativeLayout rl5 = contentView.findViewById(R.id.rl5);

        switch (task_status) {
            case -1:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.VISIBLE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
                break;
            case 0:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.VISIBLE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
                break;
            case 1:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.VISIBLE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
                break;
            case 2:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.VISIBLE);
                im5.setVisibility(View.GONE);
                break;
            case 3:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.VISIBLE);
                break;

        }


        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_status = -1;
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.VISIBLE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_status = 0;
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.VISIBLE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_status = 1;
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.VISIBLE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.GONE);
            }
        });

        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_status = 2;
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.VISIBLE);
                im5.setVisibility(View.GONE);
            }
        });


        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_status = 3;
                tv1.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv4.setTextColor(getActivity().getResources().getColor(R.color.text_color_light_black));
                tv5.setTextColor(getActivity().getResources().getColor(R.color.text_color_task));
                im1.setVisibility(View.GONE);
                im2.setVisibility(View.GONE);
                im3.setVisibility(View.GONE);
                im4.setVisibility(View.GONE);
                im5.setVisibility(View.VISIBLE);
            }
        });


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.requestTaskList(currPage, type, guildId, task_status + "", "");
                    mPopWindow.dismiss();
                }


            }
        });
        select_society.setLayoutManager(new LinearLayoutManager(getActivity()));
        societyAdapter1 = new SocietyAdapter1(getActivity(), R.layout.item_select_society, imageList);
        societyAdapter1.openLoadAnimation(new ScaleInAnimation());
        select_society.setAdapter(societyAdapter1);

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        societyAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                guildId = societyAdapter1.get(position).getGuild_id();
                SocietyAdapter1.id = guildId;
                societyAdapter1.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private PopupWindow ChexiaoPopwindow;

    /**
     * 做撤销操作弹窗
     */
    private void showPop() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_chexiao_pop1, null);
        ChexiaoPopwindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        ChexiaoPopwindow.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChexiaoPopwindow1();
                ChexiaoPopwindow.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        ChexiaoPopwindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private PopupWindow ChexiaoPopwindow1;

    /**
     * 做撤销操作弹窗
     */
    private void ChexiaoPopwindow1() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.compact_add_gonghui1, null);
        ChexiaoPopwindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        ChexiaoPopwindow1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChexiaoPopwindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                mPresenter.taskRepeal(task_id);
                ChexiaoPopwindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_patrol, null);
        ChexiaoPopwindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}

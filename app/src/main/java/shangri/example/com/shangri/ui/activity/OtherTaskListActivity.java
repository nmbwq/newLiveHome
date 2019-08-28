package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.PatrolPresenter;
import shangri.example.com.shangri.presenter.view.PatrolView;
import shangri.example.com.shangri.ui.adapter.NewTaskAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.AndroidInterface.AlertFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司列表界面
 * Created by admin on 2017/12/22.
 */

public class OtherTaskListActivity extends BaseActivity<PatrolView, PatrolPresenter> implements PatrolView, AlertFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    @BindView(R.id.search_not_found)
    LinearLayout searchNotFound;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    private NewTaskAdapter mAdapter;
    private List<NewTaskDataBean.TasksBean> mPatrolList = new ArrayList<>();


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_othercocah_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_othercocah_list;
    }

    //点击自己名片时候的值   0主播  会长管理员 1      点击其他名片 0（点击其他名片  此字段无用）
    int type;
    String register_id;
    String guild_id;
    String titleContext;
    //0发出  1收到
    public static int sym = 0;

    @Override
    protected PatrolPresenter createPresenter() {
        return new PatrolPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        CompanyInterfaceUtils.setAlertBack(this);
        register_id = getIntent().getStringExtra("register_id");
        guild_id = getIntent().getStringExtra("guild_id");
        titleContext = getIntent().getStringExtra("title");
        title.setText(titleContext);
        //register_id为空字符的时候  自己点击自己名片     反之点击其他人名片
        type = sym;

        initSpringView();
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }

    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(OtherTaskListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.requestTaskList(currPage, type, guild_id, "-1", register_id);
        }
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {

                ACTION_TYPE = ACTION_LOAD_MORE;
                requestTastList();
            }
        });

    }

    private void requestTastList() {
        if (!NetWorkUtil.isNetworkConnected(OtherTaskListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (currPage < mPageNum) {
                currPage++;
                sv_partol.onFinishFreshAndLoad();
                mPresenter.requestTaskList(currPage, type, guild_id, "-1", register_id);
            } else {
                sv_partol.onFinishFreshAndLoad(); //停止加载
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                loadData();
                break;
        }
    }


    @Override
    public void PatrolsListData(PatrolDataBean resultBean) {
    }

    @Override
    public void requestTaskList(NewTaskDataBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        sv_partol.onFinishFreshAndLoad();
        Log.d("Debug", "任务列表请求数据成功" + resultBean.toString());
        rv_partol.setLayoutManager(new FastLinearScrollManger(OtherTaskListActivity.this));
        mAdapter = new NewTaskAdapter(OtherTaskListActivity.this, R.layout.new_item_task, mPatrolList, CompanyInterfaceUtils.alertFace);
        mAdapter.openLoadAnimation(new ScaleInAnimation());

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击事件");
                NewTaskDataBean.TasksBean tasksBean = mAdapter.get(position);
                startActivity(new Intent(OtherTaskListActivity.this, NewTaskDetailActivity.class).putExtra("Bean", tasksBean));
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        rv_partol.setAdapter(mAdapter);
        if (mAdapter == null) {
            Log.d("Debug", "适配器为空");
        } else {
            Log.d("Debug", "适配器不为空");
        }
        if (ACTION_TYPE == ACTION_FRESH) {
            mAdapter.setData(resultBean.getTasks());
            if (resultBean.getTasks().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAll(resultBean.getTasks());
        }
        mPatrolList = mAdapter.getAll();
        mPageNum = resultBean.getTotal_page();
        CompamyInfoActivity.type = 0;
    }

    @Override
    public void listGuildData(ChoiceGuildBean resultBean) {

    }

    @Override
    public void companyMy(companyMyBean resultBean) {

    }

    @Override
    public void inspectDetail(PatrolDataBean.InspectsBean resultBean) {

    }

    @Override
    public void getalert() {

    }

    @Override
    public void inspectRead() {

    }

    @Override
    public void taskAoalert() {

    }

    @Override
    public void partIn() {

    }

    @Override
    public void taskRepeal() {

    }

    @Override
    public void memberLate(timeBean shareBean) {

    }

    @Override
    public void alert(String task_id) {

    }

    @Override
    public void join(String task_id) {

    }

}

package shangri.example.com.shangri.ui.activity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyTaskPresenter;
import shangri.example.com.shangri.presenter.view.MyTaskView;
import shangri.example.com.shangri.ui.adapter.MyTaskAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastStaggeredGridScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 我的任务
 * Created by admin on 2017/12/22.
 */

public class MyTaskActivity extends BaseActivity<MyTaskView, MyTaskPresenter> implements MyTaskView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_no_patrol)
    LinearLayout activity_no_patrol;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.title)
    TextView title;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private MyTaskAdapter mAdapter;
    private int currPage = 1;
    private List<MyTaskBean.TasksBean> mPatrolList = new ArrayList<>();
    private String type = "1";
    private String register_id,guild_id;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_mytask_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_mytask_list;
    }

    @Override
    protected MyTaskPresenter createPresenter() {
        return new MyTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        if (getIntent().getStringExtra("type")!=null){
            type = getIntent().getStringExtra("type");
            register_id = getIntent().getStringExtra("register_id");
            guild_id = getIntent().getStringExtra("guild_id");
            title.setText("主播任务");
        }

        initSpringView();
        loadData();

    }


    @Override
    public void requestFailed(String message) {

    }

    @OnClick({R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
        }
    }

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        rv_partol.setLayoutManager(new FastStaggeredGridScrollManger(1, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyTaskAdapter(this, R.layout.item_my_task, mPatrolList);
        rv_partol.addItemDecoration(new GridSpacingItemDecoration(20));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        currPage = 1;

        if (type.equals("2")){
            mPresenter.myTaskList(currPage,register_id,guild_id);
        }
        mPresenter.myTaskList(currPage, register_id, guild_id);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//                Intent intent = new Intent(this, ActivityAddTask.class);
//                intent.putExtra("type", "2");
//                intent.putExtra("data", (Serializable) mPatrolList.get(position));
//                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(MyTaskActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
                    mPresenter.myTaskList(currPage, register_id, guild_id);
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(MyTaskActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
            mPresenter.myTaskList(currPage, register_id, guild_id);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }

    @Override
    public void mvpExpleme(MyTaskBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
//            try {
//                tv_title.setText(resultBean.getAnchor().getAnchor_name());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            mPatrolList.clear();
            if (resultBean.getTasks().size() > 0) {
                activity_no_patrol.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_no_patrol.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        }
//        mPageNum = resultBean.getTotal_page();
        mAdapter.addAll(resultBean.getTasks());
        mAdapter.notifyDataSetChanged();
        mPatrolList = mAdapter.getAll();
    }

    @Override
    public void AnchorList(MyAnchorListBeanResponse1 resultBean) {

    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

}

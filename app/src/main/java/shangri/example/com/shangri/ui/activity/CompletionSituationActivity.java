package shangri.example.com.shangri.ui.activity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.TaskDataBean;
import shangri.example.com.shangri.presenter.CompletionSituationPresenter;
import shangri.example.com.shangri.presenter.view.CompletionSituationView;
import shangri.example.com.shangri.ui.adapter.CompletionSituationAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastStaggeredGridScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 会长的完成情况
 * Created by admin on 2017/12/22.
 */

public class CompletionSituationActivity extends BaseActivity<CompletionSituationView, CompletionSituationPresenter> implements CompletionSituationView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_no_patrol)
    LinearLayout activity_no_patrol;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    private List<TaskDataBean.TasksBean> mPatrolList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.acitivity_completion_situation;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.acitivity_completion_situation;
    }

    @Override
    protected CompletionSituationPresenter createPresenter() {
        return new CompletionSituationPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        loadData();
    }


    @Override
    public void requestFailed(String message) {

    }

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        rv_partol.setLayoutManager(new FastStaggeredGridScrollManger(1, StaggeredGridLayoutManager.VERTICAL));
        CompletionSituationAdapter mAdapter = new CompletionSituationAdapter(this, R.layout.item_completion_situation, mPatrolList);
        rv_partol.addItemDecoration(new GridSpacingItemDecoration(20));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        currPage = 1;
//        mPresenter.requestList(currPage);
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
                if (!NetWorkUtil.isNetworkConnected(CompletionSituationActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    loadData();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(CompletionSituationActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    requestTastList();
                }

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
//            mPresenter.requestPatrolsList(currPage);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }

    @Override
    public void mvpExpleme() {
//        sv_partol.onFinishFreshAndLoad();
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
//        if (currPage==1){
//            mPatrolList.clear();
//            if (resultBean.getTasks().size()>0){
//                activity_no_patrol.setVisibility(View.GONE);
//                rv_partol.setVisibility(View.VISIBLE);
//            }
//            else {
//                activity_no_patrol.setVisibility(View.VISIBLE);
//                rv_partol.setVisibility(View.GONE);
//            }
//        }
//        mPageNum = resultBean.getTotal_page();
//        mAdapter.addAll(resultBean.getTasks());
//        mAdapter.notifyDataSetChanged();
//        mPatrolList = mAdapter.getAll();
    }

}

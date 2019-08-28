package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.model.bean.response.MyPlatfromList;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.presenter.MyPlatfromPresenter;
import shangri.example.com.shangri.presenter.view.MyPlatfromView;
import shangri.example.com.shangri.ui.adapter.MyPlatfromAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.AbstractClass.OnRecyclerItemClickListener;
import shangri.example.com.shangri.ui.view.FastStaggeredGridScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 我的直播平台
 * Created by admin on 2017/12/22.
 */

public class MyPlatfromActivity extends BaseActivity<MyPlatfromView, MyPlatfromPresenter> implements MyPlatfromView {

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    private ProgressDialogFragment mProgressDialog;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private MyPlatfromAdapter mAdapter;
    private List<MyPlatfromList.PlatfromsBean> mPatrolList = new ArrayList<>();

    @Override
    protected MyPlatfromPresenter createPresenter() {
        return new MyPlatfromPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        loadData();
    }

    @Override
    public void myPlatfromList(MyPlatfromList resultBean) {
        mPatrolList = resultBean.getPlatfroms();
        mAdapter.setData(mPatrolList);
    }

    @Override
    public void PlatfromList(SortModelBean resultBean) {

    }

    @Override
    public void uploadCover(ImageUpload resultBean) {

    }

    @Override
    public void addPlatfrom(String s) {

    }

    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
//        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(MyPlatfromActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    onDataRefresh();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(MyPlatfromActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    loadData();
                }

            }
        });
        rv_partol.addOnItemTouchListener(new OnRecyclerItemClickListener(rv_partol) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(MyPlatfromActivity.this, MyPlatfromAddActivity.class);
                intent.putExtra("platfrom_name", mPatrolList.get(position).getPlatfrom_name());
                intent.putExtra("guild_name", mPatrolList.get(position).getGuild_name());
                intent.putExtra("anchor_content", mPatrolList.get(position).getAnchor_content());
                intent.putExtra("cover_url", mPatrolList.get(position).getCover_url());
                intent.putExtra("platfrom_id", mPatrolList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onItemLOngClick(RecyclerView.ViewHolder viewHolder) {

            }
        });

    }

    private void onDataRefresh() {
        currPage = 1;
        mPresenter.MyPlatfromList();
    }

    @OnClick({R.id.left_image, R.id.right_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_image:
                finish();
                break;
            case R.id.right_image:
                Intent intent3 = new Intent(this, MyPlatfromAddActivity.class);
                intent3.putExtra("", "");
                startActivity(intent3);

                break;

        }
    }

    protected void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        rv_partol.setLayoutManager(new FastStaggeredGridScrollManger(1, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyPlatfromAdapter(this, R.layout.my_platfrom_item, mPatrolList);
        rv_partol.addItemDecoration(new GridSpacingItemDecoration(20));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        currPage = 1;
        mPresenter.MyPlatfromList();
    }

    //    private void requestPatolsList() {
//        if (currPage < mPageNum) {
//            currPage++;
//            sv_partol.onFinishFreshAndLoad();
//            mPresenter.MyPlatfromList();
//        } else {
//            sv_partol.onFinishFreshAndLoad(); //停止加载
//        }
//
//    }
    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onDataRefresh();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_my_platfrom;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_my_platfrom;
    }
}

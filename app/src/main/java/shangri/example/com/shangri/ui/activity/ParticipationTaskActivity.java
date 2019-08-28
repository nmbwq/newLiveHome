package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ParticipationTaskBean;
import shangri.example.com.shangri.presenter.ParticipationTaskPresenter;
import shangri.example.com.shangri.presenter.view.ParticipationTaskView;
import shangri.example.com.shangri.ui.adapter.PartcipationAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastStaggeredGridScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 参与主播列表
 * Created by admin on 2017/12/22.
 */

public class ParticipationTaskActivity extends BaseActivity<ParticipationTaskView, ParticipationTaskPresenter> implements ParticipationTaskView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_no_patrol)
    LinearLayout activity_no_patrol;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.tv_layout_contont1)
    TextView tv_layout_contont1;
    @BindView(R.id.tv_layout_contont2)
    TextView tv_layout_contont2;

    @BindView(R.id.iv_user_item_icon)
    RoundImageView iv_user_item_icon;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private PartcipationAdapter mAdapter;
    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private List<ParticipationTaskBean.AnchorsBean> mPatrolList = new ArrayList<>();
    private String task_id = "";
    private String url;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_partcipation_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_partcipation_list;
    }

    @Override
    protected ParticipationTaskPresenter createPresenter() {
        return new ParticipationTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (getIntent().getStringExtra("task_id") != null) {
            task_id = getIntent().getStringExtra("task_id");
            url = getIntent().getStringExtra("url");
        }
        title.setText("完成情况 ");
        initSpringView();
        loadData();
        tv_layout_contont1.setVisibility(View.GONE);
        tv_layout_contont2.setVisibility(View.GONE);

        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                iv_user_item_icon.setImageBitmap(resource);
            }
        });
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
        mAdapter = new PartcipationAdapter(this, R.layout.participation_task_item, mPatrolList);
        rv_partol.addItemDecoration(new GridSpacingItemDecoration(20));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        currPage = 1;
        mPresenter.participationTaskList(currPage, task_id);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(ParticipationTaskActivity.this, MyTaskActivity.class);
                intent.putExtra("type","2");
                intent.putExtra("register_id",String.valueOf(mAdapter.get(position).getRegister_id()));
                intent.putExtra("guild_id",mAdapter.get(position).getGuild_id());
                startActivity(intent);

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
                if (!NetWorkUtil.isNetworkConnected(ParticipationTaskActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
                    mPresenter.participationTaskList(currPage, task_id);
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(ParticipationTaskActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    requestTastList();
                }

            }
        });

    }

    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
            mPresenter.participationTaskList(currPage, task_id);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Override
    public void mvpExpleme(ParticipationTaskBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            try {
                tv_title.setText(resultBean.getTask().getPlatfrom_name());
                try {
                    tv2.setText(TimeUtil.longToString(Long.parseLong(resultBean.getTask().getStart_time()), "yyyy.MM.dd HH:mm") + "~"
                            + TimeUtil.longToString(Long.parseLong(resultBean.getTask().getEnd_time()), "yyyy.MM.dd HH:mm"));

                    if (Long.valueOf(resultBean.getTask().getStart_time()) > TimeUtil.getCurrentTime()) {
                        tv3.setText("(预发布)");
                    } else if (Long.valueOf(resultBean.getTask().getStart_time()) < TimeUtil.getCurrentTime() && Long.valueOf(resultBean.getTask().getEnd_time()) > TimeUtil.getCurrentTime()) {
                        tv3.setText("(进行中)");
                    } else {
                        tv3.setText("(已结束)");
                    }
//
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mPatrolList.clear();
            if (resultBean.getAnchors().size() > 0) {
                activity_no_patrol.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_no_patrol.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        }
        mPageNum = resultBean.getTotal_page();
        mAdapter.addAll(resultBean.getAnchors());
        mAdapter.notifyDataSetChanged();
        mPatrolList = mAdapter.getAll();
    }
}

package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.presenter.SearchPresenter;
import shangri.example.com.shangri.presenter.view.SearchListView;
import shangri.example.com.shangri.ui.adapter.TaskStatePeopleAdapter;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * Created by Administrator on 2018/1/15.
 */

public class TaskStatePeopleFreagment extends BaseFragment<SearchListView, SearchPresenter> {
    @BindView(R.id.search_irv)
    RecyclerView mSearchRecycler;
    @BindView(R.id.search_springview)
    SpringView mSearchSpringView;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;
    @BindView(R.id.im_image)
    ImageView im_image;
    @BindView(R.id.tv_text)
    TextView tv_text;


    private int currPage = 1;
    private final int PAGE_SIZE = 5;
    private int mPageNum = 0; //总页数

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ChuandiBean tasksBean;


//    private CommonAdapter<NewTaskDataBean.TasksBean.NoJoinBean> OtherSelectAdapter;


    public static TaskStatePeopleFreagment getInstance(final ChuandiBean bean) {
        TaskStatePeopleFreagment fragment = new TaskStatePeopleFreagment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        List<NewTaskDataBean.TasksBean.NoJoinBean> mNewsList = new ArrayList<>();
        mNewsList.clear();
        Bundle arguments = getArguments();
        tasksBean = (ChuandiBean) arguments.getSerializable("bean");
        Log.d("Debug", "传过来的数据标题为" + tasksBean.getTitle());

        switch (tasksBean.getStatus()) {
            case 0:
                if (tasksBean.getTitle().contains("已参加")) {
                    for (int i = 0; i < tasksBean.getJoin().size(); i++) {
                        mNewsList.add(tasksBean.getJoin().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("尚有人参加任务");
                    }
                } else if (tasksBean.getTitle().contains("未参加")) {
                    for (int i = 0; i < tasksBean.getNo_join().size(); i++) {
                        mNewsList.add(tasksBean.getNo_join().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("所有人都参加任务");
                    }
                }
                break;
            case 1:
                if (tasksBean.getTitle().contains("进行中")) {
                    for (int i = 0; i < tasksBean.getIng_list().size(); i++) {
                        mNewsList.add(tasksBean.getIng_list().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("所有人都完成任务");
                    }

                } else if (tasksBean.getTitle().contains("已完成")) {
                    for (int i = 0; i < tasksBean.getOver_list().size(); i++) {
                        mNewsList.add(tasksBean.getOver_list().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("尚未有人完成任务");
                    }
                } else if (tasksBean.getTitle().contains("未参加")) {
                    for (int i = 0; i < tasksBean.getNo_join().size(); i++) {
                        mNewsList.add(tasksBean.getNo_join().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("所有人都参加了任务");
                    }
                }
                break;
            case 2:
                if (tasksBean.getTitle().contains("未完成")) {
                    for (int i = 0; i < tasksBean.getIng_list().size(); i++) {
                        mNewsList.add(tasksBean.getIng_list().get(i));
                    }

                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("所有人都完成任务");
                    }

                } else if (tasksBean.getTitle().contains("已完成")) {
                    for (int i = 0; i < tasksBean.getOver_list().size(); i++) {
                        mNewsList.add(tasksBean.getOver_list().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("尚未有人完成任务");
                    }
                } else if (tasksBean.getTitle().contains("未参加")) {
                    for (int i = 0; i < tasksBean.getNo_join().size(); i++) {
                        mNewsList.add(tasksBean.getNo_join().get(i));
                    }
                    if (mNewsList.size() > 0) {
                        mSearchRecycler.setVisibility(View.VISIBLE);
                        mSearchNotFound.setVisibility(View.GONE);
                    } else {
                        mSearchRecycler.setVisibility(View.GONE);
                        mSearchNotFound.setVisibility(View.VISIBLE);
                        im_image.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
                        tv_text.setText("所有人都参加了任务");
                    }
                }
                break;
        }
        initSpringView();

        mSearchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TaskStatePeopleAdapter mAdapter = new TaskStatePeopleAdapter(getActivity(), R.layout.item_taskstate_people_item, mNewsList, tasksBean.getTitle());
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mSearchRecycler.setAdapter(mAdapter);
    }


    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(getActivity()));
        mSearchSpringView.setFooter(new SpringViewFooter(getActivity()));
        mSearchSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载

                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_collect_interlocution;

    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_collect_interlocution;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return null;
    }
}

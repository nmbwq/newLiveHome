package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.RecommendAdapter;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.RecommendBean;
import shangri.example.com.shangri.presenter.RecommendPresent;
import shangri.example.com.shangri.presenter.view.RecommendView;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description: 小播推荐
 * Data：2018/11/13-17:20
 * Author: lin
 */
public class XIaoboTuijianFragment extends BaseFragment<RecommendView, RecommendPresent> implements RecommendView {
    private int mPage = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.appbar)
    AppBarLayout AppBar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    RecommendAdapter mAdapter;

    List<RecommendBean.Resumes> mList = new ArrayList<>();

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_host_collection;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_host_collection;
    }


    @Override
    protected void initViewsAndEvents() {
        AppBar.setVisibility(View.GONE);
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");

        mAdapter = new RecommendAdapter(getActivity(), R.layout.item_host_collection, mList, new RecommendAdapter.onItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), LookAnchorDetailActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                startActivity(intent);
            }
        });
        initSpringView();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mList.size() > 0) {
            mPage = 1;
            mList.clear();
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());
        LoadData(mPage);
    }

    private void initSpringView() {
        springView.setGive(SpringView.Give.TOP);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new SpringViewHeader(getActivity()));
        springView.setFooter(new SpringViewFooter(getActivity()));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                LoadData(mPage);
            }

            @Override
            public void onLoadmore() {
                LoadData(mPage);
            }
        });
    }

    @OnClick({R.id.reload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    mPage = 1;
                    LoadData(mPage);
                }
                break;
        }
    }

    private void LoadData(int page) {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            springView.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            springView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.getRecommendList(page);
        }
    }

    @Override
    protected RecommendPresent createPresenter() {
        return new RecommendPresent(getActivity(), this);
    }

    @Override
    public void getRecommendList(RecommendBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        springView.onFinishFreshAndLoad();
        List<RecommendBean.Resumes> data = bean.getResumes();
        if (mPage == 1) {
            mAdapter.clear();
            if (data.size() == 0) {
                springView.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            } else {
                springView.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            }
        }
        if (data.size() > 0) {
            mPage++;
            mAdapter.addAll(data);
        } else if (mPage > 1) {
            ToastUtil.showShort("没有更多数据");
        }
    }

    @Override
    public void requestFailed(String message) {

    }
}

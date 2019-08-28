package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.HostCollectionAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.CollecBean;
import shangri.example.com.shangri.presenter.HostCollectionPresent;
import shangri.example.com.shangri.presenter.view.HostCollectionView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 收藏的主播
 */
public class HostCollectionActivity extends BaseActivity<HostCollectionView, HostCollectionPresent> implements HostCollectionView {

    private int mPage = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
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

    HostCollectionAdapter mAdapter;
    List<CollecBean.Resume.DataBean> mList = new ArrayList<>();

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
    protected HostCollectionPresent createPresenter() {
        return new HostCollectionPresent(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.xiaoxi_kong));
        tv_text1_empty.setText("暂时还没有收藏主播呢");
        tv_text2_empty.setText("");

        mAdapter = new HostCollectionAdapter(this, R.layout.item_host_collection, mList, new HostCollectionAdapter.onItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(HostCollectionActivity.this, LookAnchorDetailActivity.class);
                intent.putExtra("id", mList.get(position).getGet_resume().getId());
                intent.putExtra("IsFromAnchorClloect", true);
                startActivity(intent);
            }
        });
        initSpringView();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mList.size() > 0) {
            mPage = 1;
            mList.clear();
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        LoadData(mPage);
    }

    private void initSpringView() {
        springView.setGive(SpringView.Give.TOP);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new SpringViewHeader(this));
        springView.setFooter(new SpringViewFooter(this));
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

    @Override
    public void getCollectList(CollecBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        springView.onFinishFreshAndLoad();
        List<CollecBean.Resume.DataBean> data = bean.getResume().getData();
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

    @OnClick({R.id.reload, R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    mPage = 1;
                    LoadData(mPage);
                }
                break;
        }
    }

    private void LoadData(int page) {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            springView.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            springView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.getCollectList(page);
        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

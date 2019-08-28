package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.adapter.FastMyGuildAdapter1;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * 公司列表界面
 * Created by admin on 2017/12/22.
 */

public class FastGuileSelectListActivity extends BaseActivity<MyGuildView, MyGuildPresenter> implements MyGuildView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.title)
    TextView title;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private FastMyGuildAdapter1 FastmAdapter;
    private List<MyGuildListDataBean.GuildsBean> FastNewsList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_alertdate_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_alertdate_list;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        title.setText("选择公会");
        initSpringView();
        loadData();
        rv_partol.setLayoutManager(new FastLinearScrollManger(this));
        FastmAdapter = new FastMyGuildAdapter1(this, R.layout.my_guild_item1, FastNewsList);
        FastmAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(FastmAdapter);
        FastmAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MyGuildListDataBean.GuildsBean guildsBean = FastmAdapter.get(position);
                Intent intent = new Intent();
                intent.putExtra("guildNameText", guildsBean.getGuild_name());
                intent.putExtra("guild_id", guildsBean.getGuild_id());
                setResult(2, intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    public void loadData() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        currPage = 1;
        mPresenter.requestListData();

    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(FastGuileSelectListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    currPage = 1;
//                    mPresenter.companySearch();
                    loadData();
                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(FastGuileSelectListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                    loadData();
                }

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;

        }
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
        sv_partol.onFinishFreshAndLoad(); //停止加载
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        FastNewsList = resultBean.getQuick_gulid();
        FastmAdapter.setData(FastNewsList);
    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void guildRatio() {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }
}

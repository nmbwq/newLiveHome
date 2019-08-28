package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.adapter.anchorListAdapter;
import shangri.example.com.shangri.ui.adapter.guildListAdapter;
import shangri.example.com.shangri.ui.adapter.platfromListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.listener.AbstractClass.OnRecyclerItemClickListener;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的直播平台
 * Created by admin on 2017/12/22.
 */

public class BkplatfromActivity extends BaseActivity<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.tv_title_patol)
    TextView tv_title_patol;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;




    private ProgressDialogFragment mProgressDialog;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private anchorListAdapter anchormAdapter;
    private guildListAdapter guildmAdapter;
    private platfromListAdapter platfrommAdapter;

    private List<EncyclopediaList.AnchorBean> AnchorBeanList = new ArrayList<>();
    private List<EncyclopediaList.GuildBean> GuildlList = new ArrayList<>();
    private List<EncyclopediaList.PlatfromBean> PlatfromList = new ArrayList<>();
    //1是找平台 2找公会 3找主播
    public int type = -1;


    @Override
    protected void initViewsAndEvents() {
        type = getIntent().getIntExtra("type", -1);

        initSpringView();
        loadData();
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
//        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                    ACTION_TYPE = ACTION_FRESH;
                    loadData();
            }

            @Override
            public void onLoadmore() {
                    ACTION_TYPE = ACTION_LOAD_MORE;

            }
        });
        rv_partol.addOnItemTouchListener(new OnRecyclerItemClickListener(rv_partol) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
            }

            @Override
            public void onItemLOngClick(RecyclerView.ViewHolder viewHolder) {

            }
        });

    }

    private void onDataRefresh() {
//        currPage = 1;
        mPresenter.GetEncyclopediaList(type, false, "");
    }

    @OnClick({R.id.left_image, R.id.right_image, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_image:
                finish();
                break;
            case R.id.reload:
                loadData();
                break;
            case R.id.right_image:
                Intent intent3 = new Intent(this, WikiSearchActivity.class);
                intent3.putExtra("type", type);
                startActivity(intent3);
                break;

        }
    }

    protected void loadData() {

        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
//        currPage = 1;
        mPresenter.GetEncyclopediaList(type, false, "");
    }

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
//        onDataRefresh();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_all_platfrom;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_all_platfrom;
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(this, this);
    }

    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {
        switch (type) {
            case 1:
                tv_title_patol.setText("直播平台");
                rv_partol.setLayoutManager(new FastLinearScrollManger(BkplatfromActivity.this));
                platfrommAdapter = new platfromListAdapter(this, R.layout.platfrom_item_layout, PlatfromList);
                platfrommAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(platfrommAdapter);
                break;
            case 2:
                tv_title_patol.setText("公会");
                rv_partol.setLayoutManager(new FastLinearScrollManger(BkplatfromActivity.this));
                guildmAdapter = new guildListAdapter(this, R.layout.society_item_layout, GuildlList);
                guildmAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(guildmAdapter);
                break;
            case 3:
                tv_title_patol.setText("主播");
                rv_partol.setLayoutManager(new GridLayoutManager(BkplatfromActivity.this, 2));
                anchormAdapter = new anchorListAdapter(this, R.layout.zhubo_item_layout, AnchorBeanList);
                rv_partol.addItemDecoration(new GridSpacingItemDecoration(10));
                anchormAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(anchormAdapter);
                break;
            default:
                tv_title_patol.setText("");
                break;
        }
        AnchorBeanList.clear();
        GuildlList.clear();
        PlatfromList.clear();
        AnchorBeanList = resultBean.getAnchor();
        GuildlList = resultBean.getGuild();
        PlatfromList = resultBean.getPlatfrom();

        switch (type) {
            case 1:
                platfrommAdapter.setData(PlatfromList);
                Log.d("Debug", "返回的数据we" + PlatfromList.size());
                break;
            case 2:
                guildmAdapter.setData(GuildlList);
                break;
            case 3:
                anchormAdapter.setData(AnchorBeanList);
                break;
        }

    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {

    }

    @Override
    public void consultShare() {

    }
}

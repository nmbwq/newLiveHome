package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.LookDetailBean;
import shangri.example.com.shangri.presenter.LookDetailsPresenter;
import shangri.example.com.shangri.presenter.view.LookDetailsView;
import shangri.example.com.shangri.ui.adapter.LookDetailedAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * mvp复制类
 * Created by admin on 2017/12/22.
 */

public class ActivityLookDetails extends BaseActivity<LookDetailsView, LookDetailsPresenter> implements LookDetailsView {
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_springview)
    SpringView mSearchSpringView;
    @BindView(R.id.news_entertain_irv)
    RecyclerView mNewsAnchorRecycler;

    @BindView(R.id.tv_gift)
    TextView tv_tv_gift;
    @BindView(R.id.tv_livetime)
    TextView tv_livetime;
    @BindView(R.id.tv_lncomediamond)
    TextView tv_lncomediamond;
    @BindView(R.id.ll_type)
    LinearLayout ll_type;
    @BindView(R.id.im_type)
    ImageView im_type;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.tv_Fans)
    TextView tvFans;
    @BindView(R.id.llll)
    LinearLayout llll;
    @BindView(R.id.lll)
    LinearLayout lll;

    private LookDetailedAdapter mAdapter;
    private List<LookDetailBean.AnchorsBean> mNewsList = new ArrayList<>();
    private int currPage = 1;
    private int mPageNum;
    private String guild_id;
    private String time_slot;
    private String gift, livetime, lncomediamond;
    private ProgressDialogFragment mProgressDialog;
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    //是否是降序
    Boolean IsDesc = true;
    //
    Boolean IsOnclick = false;
    //    结束时间
    String end_date = "";

    String start_date = "";
    String platfrom_name = "";


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_look_details;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_look_details;
    }

    @Override
    protected LookDetailsPresenter createPresenter() {
        return new LookDetailsPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        title_tv.setText("主播明细");
        toolbar.setNavigationIcon(R.mipmap.ic_land_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                onBackPressed();
            }
        });
        if (getIntent().getStringExtra("time_slot") != null) {
            guild_id = getIntent().getStringExtra("guild_id");
            time_slot = getIntent().getStringExtra("time_slot");
            gift = getIntent().getStringExtra("gift");
            livetime = getIntent().getStringExtra("livetime");
            lncomediamond = getIntent().getStringExtra("lncomediamond");
            //年月日
            end_date = getIntent().getStringExtra("end_date");
            //时间戳
            start_date = getIntent().getStringExtra("start_date");
            platfrom_name = getIntent().getStringExtra("platfrom_name");
            Log.d("Debug", "上一个界面传过来的最后时间是" + end_date);
        }

        tv_tv_gift.setText(gift);
        tv_livetime.setText(livetime);
        tv_lncomediamond.setText(lncomediamond);

        initSpringView();
        recyclerView();
        loadData();
    }

    private void recyclerView() {
        mNewsAnchorRecycler.setLayoutManager(new FastLinearScrollManger(this));
        mAdapter = new LookDetailedAdapter(this, R.layout.item_look_detail, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mNewsAnchorRecycler.setAdapter(mAdapter);
        mNewsAnchorRecycler.setNestedScrollingEnabled(false);
    }

    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(this));
        mSearchSpringView.setFooter(new SpringViewFooter(this));
        mSearchSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                searchNewsList();
            }
        });

    }

    private void searchNewsList() {

        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (currPage < mPageNum) {
                currPage++;
                mSearchSpringView.onFinishFreshAndLoad();
                if (IsDesc) {
                    mPresenter.LookDetail(currPage, guild_id, time_slot, "desc", end_date);
                } else {
                    mPresenter.LookDetail(currPage, guild_id, time_slot, "asc", end_date);
                }
            } else {
                mSearchSpringView.onFinishFreshAndLoad(); //停止加载
            }
        }
    }

    private void loadData() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getSupportFragmentManager());
            currPage = 1;
            Log.d("Debug", "请求返回的数据loadData" + IsOnclick);
            if (IsOnclick) {
                IsDesc = !IsDesc;
            }
            if (IsDesc) {
                mPresenter.LookDetail(currPage, guild_id, time_slot, "desc", end_date);
            } else {
                mPresenter.LookDetail(currPage, guild_id, time_slot, "asc", end_date);
            }
        }

    }

    @Override
    public void requestFailed(String message) {
        mSearchSpringView.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDetails(final LookDetailBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.d("Debug", "请求返回" + IsOnclick + "IsDesc" + IsDesc);
        if (IsDesc) {
            im_type.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_1));
        } else {
            im_type.setImageDrawable(getResources().getDrawable(R.mipmap.sanjiao_2));
        }
        IsOnclick = false;
        Log.d("Debug", "请求返回的数据IsOnclick" + false);
        mPageNum = resultBean.getTotal_page();
        mSearchSpringView.onFinishFreshAndLoad();
        if (currPage == 1) {
            mAdapter.setData(resultBean.getAnchors());
        } else {
            mAdapter.addAllAt(mNewsList.size(), resultBean.getAnchors());
        }
        mAdapter.setOnItemClickListener(new OnItemClickListener() {//查看个人详情
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                int role = Integer.parseInt(UserConfig.getInstance().getRole());//  角色0无权限 1公会长 2主播 3管理员
                if (role == 2) {
                    return;
                }
                if (ChuandiBean.time_slot.equals("week")){
                    ChuandiBean.Detailstart_date = start_date;
                    ChuandiBean.Detailend_date = start_date;
                    ChuandiBean.Detailplatfrom_name = platfrom_name;
                }else {
                    ChuandiBean.Detailstart_date = start_date;
                    ChuandiBean.Detailend_date = TimeUtil.convertTimeToLong10(end_date, "yyyy-MM-dd")/1000 + "";
                    ChuandiBean.Detailplatfrom_name = platfrom_name;
                }
                LookDetailBean.AnchorsBean anchorsBean = resultBean.getAnchors().get(position);
                Intent intent7 = new Intent(ActivityLookDetails.this, AnchoDetailActivity.class);
                intent7.putExtra("anchor_uid", anchorsBean.getBelong_platfrom_uid());
                intent7.putExtra("guild_id", anchorsBean.getGuild_id());
                intent7.putExtra("platfrom_name",platfrom_name);
                startActivity(intent7);


            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mNewsList = mAdapter.getAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.reload, R.id.ll_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.ll_type:
                IsOnclick = true;
                currPage = 1;
                loadData();
                break;
        }
    }
}

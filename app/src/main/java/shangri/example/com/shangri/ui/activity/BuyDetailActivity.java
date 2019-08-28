package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BuyDetailBean;
import shangri.example.com.shangri.presenter.BuyDetailPresent;
import shangri.example.com.shangri.presenter.view.BuyDetailView;
import shangri.example.com.shangri.ui.adapter.BuyDetailAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 购买明细
 */
public class BuyDetailActivity extends BaseActivity<BuyDetailView, BuyDetailPresent> implements BuyDetailView {

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.sv_buy)
    SpringView svBuy;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
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

    private List<BuyDetailBean.Detail> details = new ArrayList<>();

    private BuyDetailAdapter mAdapter;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_buy_detail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_buy_detail;
    }

    @Override
    protected BuyDetailPresent createPresenter() {
        return new BuyDetailPresent(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
        tv_text1_empty.setText("暂时还没有记录呢");
        tv_text2_empty.setText("");
        mAdapter = new BuyDetailAdapter(this, R.layout.buy_item, details);
        recyclerView.setLayoutManager(new FastLinearScrollManger(this));


        svBuy.setType(SpringView.Type.FOLLOW);
        svBuy.setHeader(new SpringViewHeader(this));
        svBuy.setFooter(new SpringViewFooter(this));
        svBuy.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                LoadData();
            }

            @Override
            public void onLoadmore() {
                ToastUtil.showShort("没有更多数据");
            }
        });
        LoadData();
    }

    private void LoadData() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            svBuy.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            svBuy.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.buyDetail();
        }
    }

    @Override
    public void buyDetail(BuyDetailBean detailBean) {
        svBuy.onFinishFreshAndLoad();
        List<BuyDetailBean.Detail> detail = detailBean.getDetail();
        if (detail.size() == 0) {
            svBuy.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        } else {
            svBuy.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
            mAdapter.setNewData(detailBean.getDetail());
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
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
                    LoadData();
                }
                break;
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

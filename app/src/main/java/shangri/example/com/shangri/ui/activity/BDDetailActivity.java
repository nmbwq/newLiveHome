package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
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
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.VirtualCoinPresenter;
import shangri.example.com.shangri.presenter.view.VirtualCoinView;
import shangri.example.com.shangri.ui.adapter.BdBillsAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 波豆详情
 */
public class BDDetailActivity extends BaseActivity<VirtualCoinView, VirtualCoinPresenter> implements VirtualCoinView {
    @BindView(R.id.sv_buy)
    SpringView springView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    @BindView(R.id.reload)
    Button reload;
    BdBillsAdapter mAdapter;

    List<BdBills.Bills> mList = new ArrayList<>();
    int mPage = 1;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_bddetail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_bddetail;
    }

    @Override
    protected VirtualCoinPresenter createPresenter() {
        return new VirtualCoinPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.zhixingren_kong));
        tv_text1_empty.setText("暂时还没有记录呢");
        tv_text2_empty.setText("");
        mAdapter = new BdBillsAdapter(this, R.layout.buy_item, mList);
        recyclerView.setLayoutManager(new FastLinearScrollManger(this));
        recyclerView.setAdapter(mAdapter);

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
        LoadData(mPage);

    }

    private void LoadData(int page) {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            recyclerView.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.myBdBills(page);
        }
    }


    @Override
    public void getVirtualCoin(VirtualCoinBean bean) {

    }

    @Override
    public void virtualCoinPay(PayResponseTaskBean resultBean) {

    }

    @Override
    public void accountData(AccountDataBean mAccountDataBean) {

    }

    @Override
    public void legalProtocol(companyMyBean bean) {

    }

    @Override
    public void myBdBills(BdBills bills) {
        springView.onFinishFreshAndLoad();
        List<BdBills.Bills> data = bills.getBills();
        if (mPage == 1) {
            mList.clear();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.ExpenditureDetailsAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.manage.WrapContentLinearLayoutManager;
import shangri.example.com.shangri.model.bean.response.DetailsBean;
import shangri.example.com.shangri.presenter.DetailsPresenter;
import shangri.example.com.shangri.presenter.view.DetailsView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 收支明细
 */
public class DetailsActivity extends BaseActivity<DetailsView, DetailsPresenter> implements DetailsView {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv_details)
    RecyclerView rvDetails;
    @BindView(R.id.sv_details)
    SpringView svDetails;
    @BindView(R.id.image_type_empty)
    ImageView imageTypeEmpty;
    @BindView(R.id.tv_text1_empty)
    TextView tvText1Empty;
    @BindView(R.id.tv_text2_empty)
    TextView tvText2Empty;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    private List<DetailsBean.BillsBean> bills = new ArrayList<>();
    private ExpenditureDetailsAdapter detailsAdapter;
    int loadMore = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.getDetails();
        imageTypeEmpty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tvText1Empty.setText("对不起,这里空空如也呀~");
        tvText2Empty.setText("");
        rvDetails.setHasFixedSize(true);
        WrapContentLinearLayoutManager layoutManager =
                new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        rvDetails.setLayoutManager(layoutManager);
        detailsAdapter = new ExpenditureDetailsAdapter(this, R.layout.item_expenditure_details, bills);
        svDetails.setType(SpringView.Type.FOLLOW);
        svDetails.setHeader(new SpringViewHeader(this));
        svDetails.setFooter(new SpringViewFooter(this));
        svDetails.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDetails();
            }

            @Override
            public void onLoadmore() {
                svDetails.onFinishFreshAndLoad();
            }
        });
    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {

        finish();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void Success(DetailsBean detailsBean) {
        svDetails.onFinishFreshAndLoad();
        bills.clear();
        bills = detailsBean.getBills();
        detailsAdapter.setNewData(bills);
        rvDetails.setAdapter(detailsAdapter);
        detailsAdapter.notifyDataSetChanged();
        if (bills.isEmpty()) {
            svDetails.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        } else {
            svDetails.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
        }

    }
}

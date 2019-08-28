package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.model.bean.response.PricePackageBean;
import shangri.example.com.shangri.presenter.RedEnvelopeWelfarePresenter;
import shangri.example.com.shangri.presenter.view.RedEnvelopeWelfareView;
import shangri.example.com.shangri.ui.adapter.MoneyAdapter;
import shangri.example.com.shangri.ui.adapter.PingTaiAdapter;
import shangri.example.com.shangri.ui.adapter.PingTaiAdapterTwo;
import shangri.example.com.shangri.ui.adapter.PlatFromAdapter;
import shangri.example.com.shangri.ui.adapter.ZhuBoAdapter;
import shangri.example.com.shangri.util.DialogUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 红包福利
 */
public class RedEnvelopeWelfareActivity extends BaseActivity<RedEnvelopeWelfareView, RedEnvelopeWelfarePresenter>
        implements RedEnvelopeWelfareView {


    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.cl_withdraw)
    RelativeLayout clWithdraw;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;
    @BindView(R.id.et_alipay)
    EditText etAlipay;
    @BindView(R.id.cl_alipay)
    ConstraintLayout clAlipay;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.cl_real_name)
    ConstraintLayout clRealName;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.bt_immediately_apply)
    Button btImmediatelyApply;
    @BindView(R.id.tv_bb_rate)
    TextView tv_bb_rate;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.recycler_moner_list)
    RecyclerView recycler_moner_list;


    //
    private String bb_balance;
    //比率
    String bb_rate;
    //提现金额
    String apply_amount;
    //    套餐ID
    String package_id;


    private MoneyAdapter mAdapter;
    private List<PricePackageBean.ListBean> mNewsList = new ArrayList<>();


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_red_envelope_welfare;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_red_envelope_welfare;
    }

    @Override
    protected RedEnvelopeWelfarePresenter createPresenter() {
        return new RedEnvelopeWelfarePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        bb_balance = getIntent().getStringExtra("bb_balance");
        bb_rate = getIntent().getStringExtra("bb_rate");
        tvAmount.setText(bb_balance);
        tv_bb_rate.setText(bb_rate + "波币=1元");
        tv_money.setText("可提现￥" + Integer.parseInt(bb_balance) / Integer.parseInt(bb_rate));
        recycler_moner_list.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new MoneyAdapter(this, R.layout.item_prices, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_moner_list.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                for (int i = 0; i < mAdapter.getAll().size(); i++) {
                    mAdapter.getAll().get(i).setIsselect(false);
                }
                mAdapter.getAll().get(position).setIsselect(true);
                apply_amount = mAdapter.getAll().get(position).getPrice() + "";
                package_id = mAdapter.getAll().get(position).getId() + "";
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mPresenter.withdrawDeposit();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_go_back, R.id.bt_immediately_apply})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;

            case R.id.bt_immediately_apply:

                String alipay = etAlipay.getText().toString().trim();
                String realName = etRealName.getText().toString().trim();
                Double money = Double.valueOf(bb_balance) / Double.valueOf(bb_rate);
                if (PointUtils.isFastClick()) {
                    if (TextUtils.isEmpty(alipay)) {
                        ToastUtil.showShort("支付宝账号不能为空");
                        return;
                    }
                    if (TextUtils.isEmpty(realName)) {
                        ToastUtil.showShort("收款账号姓名不能为空");
                        return;
                    }
                    if (package_id.equals("1")) {
                        mPresenter.withdrawDeposit(UserConfig.getInstance().getToken(), alipay, realName, apply_amount, package_id);
                    } else {
                        Double deposit = Double.valueOf(apply_amount + "");
                        if (deposit > money) {
                            ToastUtil.showShort("提现金额不足");
                            return;
                        }
                        if (deposit < 1) {
                            ToastUtil.showShort("提现金额不得小于1元");
                            return;
                        }
                        mPopWindowPhone3(alipay, realName, apply_amount, package_id);
                    }

                }
                break;
        }

    }

    @Override
    public void Success() {
        ToastUtil.showShort("提现成功");
        finish();
    }

    @Override
    public void withdrawDeposit(PricePackageBean bean) {
        if (mNewsList.size() > 0) {
            mNewsList.clear();
        }
        for (int i = 0; i < bean.getList().size(); i++) {
            PricePackageBean.ListBean listBean = bean.getList().get(i);
            if (i == 0) {
                listBean.setIsselect(true);
            } else {
                listBean.setIsselect(false);
            }
            mNewsList.add(listBean);
        }
        apply_amount = bean.getList().get(0).getPrice() + "";
        package_id = bean.getList().get(0).getId() + "";
        mAdapter.setData(mNewsList);
    }

    private static PopupWindow mPopWindowPhone3;

    /**
     * 提现弹窗提醒
     */
    private void mPopWindowPhone3(final String alipay, final String realName, final String Amount, final String package_id) {
        //设置contentView
        View contentView = LayoutInflater.from(RedEnvelopeWelfareActivity.this).inflate(R.layout.compact_add_gonghui9, null);
        mPopWindowPhone3 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowPhone3.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_content1 = contentView.findViewById(R.id.tv_content1);
        String str1 = "本次提现将消耗您" + Integer.parseInt(Amount) * Integer.parseInt(bb_rate) + "波币，是否确认提现？";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_content1.setText("提现申请");
        tv_next.setText("确认");
        tv_cancle.setText("取消");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.withdrawDeposit(UserConfig.getInstance().getToken(), alipay, realName, Amount, package_id);
                mPopWindowPhone3.dismiss();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowPhone3.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(RedEnvelopeWelfareActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindowPhone3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

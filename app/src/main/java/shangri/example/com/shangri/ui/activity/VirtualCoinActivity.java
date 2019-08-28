package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.BDAdapter;
import shangri.example.com.shangri.alipay.AliPayTools;
import shangri.example.com.shangri.alipay.OnRequestListener;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.VirtualCoinPresenter;
import shangri.example.com.shangri.presenter.view.VirtualCoinView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.fragment.NewMineFragment1;
import shangri.example.com.shangri.ui.popupwindow.SelectPayPopubWindow;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.weixin.WXPay;

/**
 * 波豆余额
 */
public class VirtualCoinActivity extends BaseActivity<VirtualCoinView, VirtualCoinPresenter> implements VirtualCoinView {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.bd_num)
    TextView bd_num;

    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.tv11)
    TextView tv11;
    private ProgressDialogFragment mProgressDialog;
    private int pay_type = 2;
    private BDAdapter mAdapter;
    private List<VirtualCoinBean.Packages> mList = new ArrayList<>();
    private List<VirtualCoinBean.Packages> mNewList = new ArrayList<>();
    private int SELECT_POSITION = -1;

    boolean isRefresh;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_virtual_coin;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_virtual_coin;
    }

    @Override
    protected VirtualCoinPresenter createPresenter() {
        return new VirtualCoinPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.getVirtualCoin();
        mPresenter.accountData();
        recycler_view.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new BDAdapter(this, R.layout.item_bd, mList, new BDAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                SELECT_POSITION = position;
                Log.e("onClick", "onClick: position = " + position);
                mNewList.clear();
                for (int i = 0; i < mList.size(); i++) {
                    VirtualCoinBean.Packages pack = new VirtualCoinBean.Packages();
                    if (i == position) {
                        pack.setChecked(true);
                    } else {
                        pack.setChecked(false);
                    }
                    pack.setCoin_num(mList.get(i).getCoin_num());
                    pack.setId(mList.get(i).getId());
                    pack.setPrice(mList.get(i).getPrice());
                    pack.setName(mList.get(i).getName());
                    pack.setIs_sales(mList.get(i).getIs_sales());
                    pack.setPrice_sales(mList.get(i).getPrice_sales());
                    pack.setPrice_sales_vip(mList.get(i).getPrice_sales_vip());
                    pack.setPrice_vip(mList.get(i).getPrice_vip());
                    mNewList.add(pack);
                }
                mList.clear();
                mAdapter.addAll(mNewList);
            }
        });
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void accountData(AccountDataBean data) {
        bd_num.setText("" + data.getBd());
        tv_message.setText("消耗"+data.getHf_bd()+"波豆可给1名主播“拨电话”");
    }

    @Override
    public void myBdBills(BdBills bills) {

    }

    @Override
    public void virtualCoinPay(PayResponseTaskBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (pay_type == 2) {
            WeChatPayInfo.DataBean dataBean = new WeChatPayInfo.DataBean();
            dataBean.setAppid(resultBean.getAppid());
            dataBean.setPartnerid(resultBean.getPartnerid());
            dataBean.setPrepayid(resultBean.getPrepayid());
            dataBean.setNoncestr(resultBean.getNoncestr());
            dataBean.setTimestamp(resultBean.getTimestamp() + "");
            dataBean.setSign(resultBean.getSign());
            weChatPay(dataBean);
        } else {
            alipay = resultBean.getPay();
            alipay();
        }
    }

    @Override
    public void getVirtualCoin(VirtualCoinBean bean) {
        mAdapter.addAll(bean.getPackages());
    }

    /**
     * 微信支付
     */
    private void weChatPay(WeChatPayInfo.DataBean data) {
        String wx_appid = data.getAppid();
        WXPay.init(getApplicationContext(), wx_appid);      //要在支付前调用
        WXPay.getInstance().doPay(data, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                isRefresh = true;
                ToastUtil.showShort("支付成功");
                EventBus.getDefault().post(new BrowseEventBean());
//                finish();
            }

            @Override
            public void onError(int error_code) {
//                isRefresh = true;
                switch (error_code) {
                    case WXPay.NO_OR_LOW_WX:
                        Toast.makeText(getApplication(), "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY_PARAM:
                        Toast.makeText(getApplication(), "参数错误", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 支付宝
     */
    String alipay = "";

    public void alipay() {

        AliPayTools.aliPay(this, alipay,//商品描述详情 (用于显示在 支付宝 的交易记录里)
                new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        EventBus.getDefault().post(new BrowseEventBean());
                        isRefresh = true;
                        ToastUtil.showShort("支付成功");
                    }

                    @Override
                    public void onError(String s) {
//                        isRefresh = true;
                        ToastUtil.showShort("支付失败");
                    }
                });
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRefresh){
            mPresenter.accountData();
            isRefresh = !isRefresh;
        }
    }

    @Override
    public void legalProtocol(companyMyBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        String s = bean.getUrl() + "?token=" + UserConfig.getInstance().getToken() + "";
        startActivity(new Intent(VirtualCoinActivity.this, symbolWebView.class).putExtra("url", s + ""));
    }

    @OnClick({R.id.back, R.id.detail, R.id.tv11, R.id.commit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.detail:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(this, BDDetailActivity.class));
                }
                break;
            case R.id.tv11:
                //服务协议
                if (PointUtils.isFastClick()) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(this.getSupportFragmentManager());
                    mPresenter.legalProtocol();
                }
                break;
            case R.id.commit:
                //确认充值
                if (SELECT_POSITION == -1) {
                    ToastUtil.showShort("请选择套餐");
                    return;
                }
                if (PointUtils.isFastClick()) {

                    new SelectPayPopubWindow(VirtualCoinActivity.this, "", new SelectPayPopubWindow.OnSelector() {
                        @Override
                        public void onSelector(int payType) {
                            pay_type = payType;
                            if (PointUtils.isFastClick()) {
                                if (mProgressDialog == null) {
                                    mProgressDialog = new ProgressDialogFragment();
                                }
                                mProgressDialog.show(getSupportFragmentManager());
                                /**
                                 * package_id  套餐ID
                                 * pay_price  支付金额
                                 * pay_method  支付方式
                                 */
                                String price = "";
                                if (mList.get(SELECT_POSITION).getIs_sales().equals("1")) {
                                    if (NewMineFragment1.IS_VIP == 1) {
                                        price = mList.get(SELECT_POSITION).getPrice_sales_vip();
                                    } else {
                                        price = mList.get(SELECT_POSITION).getPrice_sales();
                                    }
                                } else {
                                    if (NewMineFragment1.IS_VIP == 1) {
                                        price = mList.get(SELECT_POSITION).getPrice_vip();
                                    } else {
                                        price = mList.get(SELECT_POSITION).getPrice();
                                    }
                                }
                                mPresenter.virtualCoinPay(mList.get(SELECT_POSITION).getId(), price, payType + "");
                            }
                        }
                    });
                }
                break;
        }
    }
}

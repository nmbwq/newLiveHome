package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.alipay.AliPayTools;
import shangri.example.com.shangri.alipay.OnRequestListener;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;
import shangri.example.com.shangri.model.bean.response.DaysPriceBean;
import shangri.example.com.shangri.model.bean.response.LegalPayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.PayPresenter;
import shangri.example.com.shangri.presenter.view.PayView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.weixin.WXPay;

/**
 * 法务套餐支付界面
 * Created by admin on 2017/12/22.
 */

public class LegalPayActivity extends BaseActivity<PayView, PayPresenter> implements PayView {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_count_number)
    TextView tvCountNumber;
    @BindView(R.id.weixin_select)
    ImageView weixinSelect;
    @BindView(R.id.alipay_select)
    ImageView alipaySelect;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private ProgressDialogFragment mProgressDialog;

    //    1是微信 2是支付宝
    int pay_type = 1;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_legalpay_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_legalpay_layout;
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.rl_weixin, R.id.rl_alipay, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_weixin:
                weixinSelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_3));
                alipaySelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                pay_type = 1;
                break;
            case R.id.rl_alipay:
                weixinSelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                alipaySelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_3));
                pay_type = 2;
                break;
            case R.id.tv_exit:
                mPresenter.getLegalInfo("290", pay_type + "");
                break;
        }
    }

    @Override
    public void getorderInfo(PayResponseTaskBean resultBean) {

    }

    @Override
    public void getLegalInfo(LegalPayResponseTaskBean resultBean) {
        if (pay_type == 1) {
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
    public void recruitPay_package(LegalPayResponseTaskBean resultBean) {

    }

    @Override
    public void orderGuild(DaysPriceBean resultBean) {

    }

    @Override
    public void memberLate(timeBean shareBean) {

    }


    /**
     * 微信支付
     */
    private void weChatPay(WeChatPayInfo.DataBean data) {
        String wx_appid = data.getAppid();
//        String wx_appid = "wx030de8785dbafd41";
        //替换为自己的appid
        WXPay.init(getApplicationContext(), wx_appid);      //要在支付前调用
        WXPay.getInstance().doPay(data, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                ToastUtil.showShort("支付成功");
                finish();
            }

            @Override
            public void onError(int error_code) {
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
        AliPayTools.aliPay(LegalPayActivity.this, alipay,//商品描述详情 (用于显示在 支付宝 的交易记录里)
                new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        finish();
                        ToastUtil.showShort("支付成功");
                    }

                    @Override
                    public void onError(String s) {
                        ToastUtil.showShort("支付失败");
                    }
                });
    }


}

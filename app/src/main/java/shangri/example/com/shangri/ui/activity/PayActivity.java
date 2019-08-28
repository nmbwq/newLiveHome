package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.alipay.AliPayTools;
import shangri.example.com.shangri.alipay.OnRequestListener;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.LoginUserPresenter;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.weixin.WXPay;

/**
 * 用户登录
 * Created by mschen on 2017/6/23.
 */

public class PayActivity extends BaseActivity<LoginUserView, LoginUserPresenter> implements LoginUserView {

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected LoginUserPresenter createPresenter() {
        return new LoginUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
//        WeChatPayInfo.DataBean dataBean = new WeChatPayInfo.DataBean();
//        weChatPay(dataBean);

        AliPayTools.aliPay(PayActivity.this, "",//商品描述详情 (用于显示在 支付宝 的交易记录里)
                new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtil.showShort("支付成功");
                    }

                    @Override
                    public void onError(String s) {
                        ToastUtil.showShort("支付失败");
                    }
                });


    }

    /**
     * 微信支付
     */
    private void weChatPay(WeChatPayInfo.DataBean data) {
//        String wx_appid = data.getAppid();

        String wx_appid = "wx030de8785dbafd41";
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


    @Override
    public void onLogin(UserInfoBean user) { //登陆成功
//
    }

    @Override
    public void loginWX(WeChatInfoBean resultBean) {

    }


    @Override
    public void signProtocol(WebBean resultBean) {

    }

    @Override
    public void memberLogin() {

    }

    @Override
    public void adIndex(AdDataBean resultBean) {

    }

    @Override
    public void privaryDeal(AdDataBean resultBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestFailed(String message) {
//        Loading.dismiss();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_pay;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}

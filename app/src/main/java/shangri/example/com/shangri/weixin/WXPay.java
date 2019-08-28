package shangri.example.com.shangri.weixin;

import android.content.Context;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;

/**
 * 微信支付
 * Created by tsy on 16/6/1.
 */
public class WXPay {

    private static WXPay mWXPay;
    private IWXAPI mWXApi;
    private String mPayParam;
    private WXPayResultCallBack mCallback;

    public static final int NO_OR_LOW_WX = 1;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 2;  //支付参数错误
    public static final int ERROR_PAY = 3;  //支付失败

    public interface WXPayResultCallBack {
        void onSuccess(); //支付成功

        void onError(int error_code);   //支付失败

        void onCancel();    //支付取消
    }

    public WXPay(Context context, String wx_appid) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mWXApi.registerApp(wx_appid);
    }

    public static void init(Context context, String wx_appid) {
        if (mWXPay == null) {
            mWXPay = new WXPay(context, wx_appid);
        }
    }

    public static WXPay getInstance() {
        return mWXPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    /**
     * 发起微信支付
     */
    public void doPay(WeChatPayInfo.DataBean payInfo, WXPayResultCallBack callback) {

        mCallback = callback;
        if (!check()) {
            if (mCallback != null) {
                mCallback.onError(NO_OR_LOW_WX);
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfo.getAppid();
        req.partnerId = payInfo.getPartnerid();
        req.prepayId = payInfo.getPrepayid();
        req.packageValue = "Sign=WXPay";
        req.nonceStr = payInfo.getNoncestr();
        req.timeStamp = payInfo.getTimestamp();
        req.sign = payInfo.getSign();
        mWXApi.sendReq(req);

//        PayReq req = new PayReq();
//        req.appId = "wx030de8785dbafd41";
//        req.partnerId = "1495437042";
//        req.prepayId = "wx13180623880090c688cf60fd0182652413";
//        req.packageValue = "Sign=WXPay";
//        req.nonceStr = "5b20ec9fdcfac";
//        req.timeStamp = "1528884383";
//        req.sign = "182720DD96B9E7A1C63EC05CCAF326C5";
//        mWXApi.sendReq(req);
    }

    private static final String TAG = "WXPay";

    //支付回调响应
    public void onResp(int error_code) {
        if (mCallback == null) {
            return;
        }
        if (error_code == 0) {   //成功
            mCallback.onSuccess();
        } else if (error_code == -1) {   //错误
            mCallback.onError(ERROR_PAY);
        } else if (error_code == -2) {   //取消
            mCallback.onCancel();
        }
        mCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}

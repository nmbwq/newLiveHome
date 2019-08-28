package shangri.example.com.shangri.wxapi;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.umeng.socialize.weixin.view.WXCallbackActivity;


import org.greenrobot.eventbus.EventBus;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BossShareBean;

public class WXEntryActivity extends WXCallbackActivity {

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
//    @Override
//    public void onResp(BaseResp resp) {
//        if (GlobalApp.WeinxinShare) {
//            Log.d("Debug", "微信登录");
//            GlobalApp.WeinxinShare = false;
//        } else {
//            switch (resp.errCode) {
//                //分享成功
//                case BaseResp.ErrCode.ERR_OK:
////                result = R.string.errcode_success;
//                    Log.d("Debug", "分享成功");
//                    EventBus.getDefault().post(new BossShareBean());
//                    break;
//                //分享取消
//                case BaseResp.ErrCode.ERR_USER_CANCEL:
////                result = R.string.errcode_cancel;
//                    Log.d("Debug", "分享取消");
//
//                    break;
//                //分享被拒绝
//                case BaseResp.ErrCode.ERR_AUTH_DENIED:
////                result = R.string.errcode_deny;
//                    Log.d("Debug", "分享被拒绝");
//                    break;
//                //分享返回
//                default:
//                    Log.d("Debug", "分享返回");
////                result = R.string.errcode_unknown;
//                    break;
//            }
//            this.finish();
//        }
//    }


    @Override
    public void onReq(BaseReq req) {
        super.onReq(req);
    }

//    @Override
//    public void onResp(BaseResp resp) {

//        if (GlobalApp.WeinxinShare) {
//            Log.d("Debug", "微信登录");
//            GlobalApp.WeinxinShare = false;
//        } else {
//        String result;
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                result = "分享成功";
//                EventBus.getDefault().post(new BossShareBean());
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result = "取消分享";
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result = "分享被拒绝";
//                break;
//            default:
//                result = "发送返回";
//                break;
//        }
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//        finish();
//    }
//    }

}

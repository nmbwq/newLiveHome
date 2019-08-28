package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.CheckCodeBean;
import shangri.example.com.shangri.model.bean.request.GetSMSVerificationCodeBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.TelBean;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.TelResposeBean;
import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.view.RegisterView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    private RegisterView mRegisterView;

    public RegisterPresenter(Context context, RegisterView view) {
        super(context, view);
        mRegisterView = view;
    }

    /**
     * 注册获取短信验证码
     *
     * @param tel  手机
     * @param type 类型
     */
    public void getSMSVerificationCode(String tel, String type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mRegisterView.getSMSVerificationCode(); //成功获取验证码后
            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterView.requestFailed(message);
            }
        };
        GetSMSVerificationCodeBean codeBean = new GetSMSVerificationCodeBean();
        codeBean.setTelephone(tel);
        codeBean.setType(type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.getSMSVerificationCode(codeBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }



    /**
     * 注册下一步校验验证码
     *
     * @param tel  手机
     * @param code 验证码
     */
    public void checkCode(String tel, String code, String type) {
        RxObserver rxObserver = new RxObserver<UserRegistrationNext>() {
            @Override
            public void onHandleSuccess(UserRegistrationNext resultBean) {
                if (resultBean != null) {
                    //设置token值
//                    UserConfig.getInstance().setToken(resultBean.getToken().trim());
                    UserConfig.getInstance().setMobile(resultBean.getTelephone().trim());
                }
                mRegisterView.checkCode(resultBean); //检验校验码成功
            }

            @Override
            public void onHandleComplete() {
                mRegisterView.checkCode(null); //检验校验码成功
            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterView.requestFailed(message); //检验校验码成功
            }
        };
        CheckCodeBean codeBean = new CheckCodeBean();
        codeBean.setTelephone(tel);
        codeBean.setVerify_code(code);
        codeBean.setType(type);
//        BaseRequestEntity<CheckCodeBean> requestEntity =
//                new BaseRequestEntity<CheckCodeBean>();
//        requestEntity.setData(codeBean);
//
//        //加密后的entity
//        BaseRequestEntity<CheckCodeBean> entity =
//                (BaseRequestEntity<CheckCodeBean>) KeytUtil.getRSAKeyt(requestEntity, null);
        Observable<BaseResponseEntity<UserRegistrationNext>> observable = mRxSerivce.checkCode(codeBean);
        Disposable disposable = observable
                .compose(RxHelper.<UserRegistrationNext>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 检测手机是否注册
     *
     * @param tel
     */
    public void checkPhone(String tel) {
        RxObserver rxObserver = new RxObserver<TelResposeBean>() {
            @Override
            public void onHandleSuccess(TelResposeBean resultBean) {
                mRegisterView.checkPhone(resultBean.count);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterView.requestFailed(message);
            }
        };

        TelBean codeBean = new TelBean();
        codeBean.telephone = tel;
        Observable<BaseResponseEntity<TelResposeBean>> observable = mRxSerivce.checkPhone(codeBean);
        Disposable disposable = observable
                .compose(RxHelper.<TelResposeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void weburl() {
        RxObserver rxObserver = new RxObserver<WebBean>() {
            @Override
            public void onHandleSuccess(WebBean resultBean) {
                mRegisterView.signProtocol(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterView.requestFailed(message);
            }
        };

        BaseRequestEntity<WeChatBean> requestEntity =
                new BaseRequestEntity<WeChatBean>();
        WeChatBean weChatBean = new WeChatBean();
        Observable<BaseResponseEntity<WebBean>> observable = mRxSerivce.signProtocol(weChatBean);
        Disposable disposable = observable
                .compose(RxHelper.<WebBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

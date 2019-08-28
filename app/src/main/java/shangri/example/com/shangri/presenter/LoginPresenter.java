package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.LoginCodeBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.LoginView;
import shangri.example.com.shangri.util.KeytUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 登陆
 * Created by chengaofu on 2017/6/19.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginView mLoginView;

    public LoginPresenter(Context context, LoginView loginView) {
        super(context, loginView);
        mLoginView = loginView;
    }

    public void getSMSVerificationCode(String tel, String type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mLoginView.getSMSVerificationCode();
            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
//        GetSMSVerificationCodeBean codeBean = new GetSMSVerificationCodeBean();
//        codeBean.setPhone(tel);
//        codeBean.setType(type);
//        BaseRequestEntity<GetSMSVerificationCodeBean> requestEntity =
//                new BaseRequestEntity<GetSMSVerificationCodeBean>();
//        requestEntity.setData(codeBean);
//
//        //加密后的entity
//        BaseRequestEntity<GetSMSVerificationCodeBean> entity =
//                (BaseRequestEntity<GetSMSVerificationCodeBean>) KeytUtil.getRSAKeyt(requestEntity, null);
//        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.getSMSVerificationCode(entity);
//        Disposable disposable = observable
//                .compose(RxHelper.<Object>handleObservableResult())
//                .subscribeWith(rxObserver);
//        addSubscribe(disposable);
    }

    public void onLogin(String mTel, String code) {
        RxObserver rxObserver = new RxObserver<UserInfoBean>() {
            @Override
            public void onHandleSuccess(UserInfoBean resultBean) {
                mLoginView.loginCode(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginView.requestFailed(message);
            }
        };
        LoginCodeBean codeBean = new LoginCodeBean();
        codeBean.setMobile(mTel);
        codeBean.setCode(code);
        BaseRequestEntity<LoginCodeBean> requestEntity =
                new BaseRequestEntity<LoginCodeBean>();
        requestEntity.setData(codeBean);

        //加密后的entity
        BaseRequestEntity<LoginCodeBean> entity =
                (BaseRequestEntity<LoginCodeBean>) KeytUtil.getRSAKeyt(requestEntity, null);
        Observable<BaseResponseEntity<UserInfoBean>> observable = mRxSerivce.loginCode(entity);
        Disposable disposable = observable
                .compose(RxHelper.<UserInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}


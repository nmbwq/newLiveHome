package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.util.MD5Util;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.util.NetWorkUtil;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class LoginUserPresenter extends BasePresenter<LoginUserView> {

    private LoginUserView mLoginUserView;

    public LoginUserPresenter(Context context, LoginUserView view) {
        super(context, view);
        mLoginUserView = view;

    }

    public void onLogin(String username, String pwd) {
        RxObserver rxObserver = new RxObserver<UserInfoBean>() {
            @Override
            public void onHandleSuccess(UserInfoBean resultBean) {
                mLoginUserView.onLogin(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LoginBean loginBean = new LoginBean();
        loginBean.setTelephone(username);
        loginBean.setPassword(MD5Util.getMD5(pwd));
        loginBean.setFrom("android");
        BaseRequestEntity<LoginBean> requestEntity =
                new BaseRequestEntity<LoginBean>();
        requestEntity.setData(loginBean);
        Observable<BaseResponseEntity<UserInfoBean>> observable = mRxSerivce.onLogin(loginBean);
        Disposable disposable = observable
                .compose(RxHelper.<UserInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void loginWX(WeChatBean loginBean) {
        RxObserver rxObserver = new RxObserver<WeChatInfoBean>() {
            @Override
            public void onHandleSuccess(WeChatInfoBean resultBean) {
                mLoginUserView.loginWX(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };

        BaseRequestEntity<WeChatBean> requestEntity =
                new BaseRequestEntity<WeChatBean>();
        requestEntity.setData(loginBean);
        Observable<BaseResponseEntity<WeChatInfoBean>> observable = mRxSerivce.loginWX(loginBean);
        Disposable disposable = observable
                .compose(RxHelper.<WeChatInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void weburl() {
        RxObserver rxObserver = new RxObserver<WebBean>() {
            @Override
            public void onHandleSuccess(WebBean resultBean) {
                mLoginUserView.signProtocol(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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


    /**
     * 更新最后一次登陆时间
     */

    public void memberLogin() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
//                mLoginUserView.memberLogin();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResetPwdBean mResetPwdBean = new ResetPwdBean();
        mResetPwdBean.setToken(UserConfig.getInstance().getToken());
        mResetPwdBean.setFrom("android");
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.memberLogin(mResetPwdBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 判断有没有广告
     */

    public void adIndex() {
        RxObserver rxObserver = new RxObserver<AdDataBean>() {
            @Override
            public void onHandleSuccess(AdDataBean resultBean) {
                mLoginUserView.adIndex(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResetPwdBean mResetPwdBean = new ResetPwdBean();
        mResetPwdBean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<AdDataBean>> observable = mRxSerivce.adIndex(mResetPwdBean);
        Disposable disposable = observable
                .compose(RxHelper.<AdDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 判断有没有广告
     */

    public void privaryDeal() {
        RxObserver rxObserver = new RxObserver<AdDataBean>() {
            @Override
            public void onHandleSuccess(AdDataBean resultBean) {
                mLoginUserView.privaryDeal(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResetPwdBean mResetPwdBean = new ResetPwdBean();
        Observable<BaseResponseEntity<AdDataBean>> observable = mRxSerivce.privaryDeal(mResetPwdBean);
        Disposable disposable = observable
                .compose(RxHelper.<AdDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

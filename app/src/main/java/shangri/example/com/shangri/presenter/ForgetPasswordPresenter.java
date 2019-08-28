package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.CheckCodeBean;
import shangri.example.com.shangri.model.bean.request.GetSMSVerificationCodeBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ForgetBean;
import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.presenter.view.ForgetView;
import shangri.example.com.shangri.util.MD5Util;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by chengaofu on 2017/6/28.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetView> {

    private ForgetView mForgetView;

    public ForgetPasswordPresenter(Context context, ForgetView view) {
        super(context, view);
        mForgetView = view;
    }

    /**
     * 获取短信
     *
     * @param tel
     */

    public void getSMSVerificationCode(String tel, String type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mForgetView.getSMSVerificationCode(); //成功获取验证码后
            }

            @Override
            public void onHandleFailed(String message) {
                mForgetView.requestFailed(message);
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
                mForgetView.checkCode(); //检验校验码成功
            }

            @Override
            public void onHandleComplete() {
                mForgetView.checkCode(); //检验校验码成功
            }

            @Override
            public void onHandleFailed(String message) {
                mForgetView.requestFailed(message); //检验校验码成功
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
     * 重置密码
     *
     * @param tel
     * @param password
     * @param repassword
     * @param code
     */

    public void resetPassword(String tel, String password, String repassword, String code) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mForgetView.checkCode(); //密码设置成功
            }

            @Override
            public void onHandleFailed(String message) {
                mForgetView.requestFailed(message);
            }
        };
        ResetPwdBean mResetPwdBean = new ResetPwdBean();
        mResetPwdBean.setTelephone(tel);
        mResetPwdBean.setPassword(MD5Util.getMD5(password.trim()));
        mResetPwdBean.setRepet_password(MD5Util.getMD5(repassword.trim()));
        mResetPwdBean.setVerify_code(code);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resetPassword(mResetPwdBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

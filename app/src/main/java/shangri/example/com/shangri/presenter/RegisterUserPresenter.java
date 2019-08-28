package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.RegigstBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.RegisterUserView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class RegisterUserPresenter extends BasePresenter<RegisterUserView> {

    private RegisterUserView mRegisterUserView;

    public RegisterUserPresenter(Context context, RegisterUserView view) {
        super(context, view);
        mRegisterUserView = view;
    }

    //设置密码
    public void registerUser(String et_invite_code,String tel, String password, String rePassword, String Verify_code) {
        RxObserver rxObserver = new RxObserver<RegigstBean>() {
            @Override
            public void onHandleSuccess(RegigstBean resultBean) {
                mRegisterUserView.regiestBean(resultBean);
            }

            @Override
            public void onHandleComplete() {
//                mRegisterUserView.registerUser();
            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterUserView.requestFailed(message);
            }
        };
        RegisterUserBean bean = new RegisterUserBean();
        bean.setPassword(password);
        bean.setTelephone(tel);
        bean.setRepet_password(rePassword);
        bean.setVerify_code(Verify_code);
        bean.setRegister_from("Android");
        if (et_invite_code.length()>0){
            bean.setInvitation_code(et_invite_code);
        }


        Observable<BaseResponseEntity<RegigstBean>> observable = mRxSerivce.registerUser(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RegigstBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void onLogin(String username, String pwd) {
        RxObserver rxObserver = new RxObserver<UserInfoBean>() {
            @Override
            public void onHandleSuccess(UserInfoBean resultBean) {
                mRegisterUserView.onLogin(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mRegisterUserView.requestFailed(message);
            }
        };
        LoginBean loginBean = new LoginBean();
        loginBean.setTelephone(username);
        loginBean.setPassword(pwd);
        BaseRequestEntity<LoginBean> requestEntity =
                new BaseRequestEntity<LoginBean>();
        requestEntity.setData(loginBean);
        Observable<BaseResponseEntity<UserInfoBean>> observable = mRxSerivce.onLogin(loginBean);
        Disposable disposable = observable
                .compose(RxHelper.<UserInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

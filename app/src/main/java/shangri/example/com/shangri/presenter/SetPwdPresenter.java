package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.BindWXBean;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.SetPwdBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ResTokenBean;
import shangri.example.com.shangri.presenter.view.SetPwdView;
import shangri.example.com.shangri.util.KeytUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 设置新密码
 * Created by chengaofu on 2017/7/2.
 */

public class SetPwdPresenter extends BasePresenter<SetPwdView> {

    private SetPwdView mSetPwdView;

    public SetPwdPresenter(Context context, SetPwdView view) {
        super(context, view);
        mSetPwdView = view;
    }

    public void setPwd(String invitation_code, String tel, String mCode, String password, String wxinfo_id) {
        RxObserver rxObserver = new RxObserver<ResTokenBean>() {
            @Override
            public void onHandleSuccess(ResTokenBean bean) {
                mSetPwdView.setPwd(bean.getToken());
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSetPwdView.requestFailed(message);
            }
        };
        SetPwdBean bean = new SetPwdBean();
        bean.password = password;
        bean.telephone = tel;
        bean.wxinfo_id = wxinfo_id;
        bean.verify_code = mCode;
        bean.repet_password = password;
        bean.register_from = "Android";
        if (invitation_code.length() > 0) {
            bean.invitation_code = invitation_code;
        }

        Observable<BaseResponseEntity<ResTokenBean>> observable = mRxSerivce.setPwd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResTokenBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void bindWX(String tel, String password, String wxinfo_id) {
        RxObserver rxObserver = new RxObserver<ResTokenBean>() {
            @Override
            public void onHandleSuccess(ResTokenBean bean) {
                mSetPwdView.bindWx(bean.getToken());
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSetPwdView.requestFailed(message);
            }
        };
        BindWXBean bean = new BindWXBean();
        bean.password = password;
        bean.telephone = tel;
        bean.wxinfo_id = wxinfo_id;
        Observable<BaseResponseEntity<ResTokenBean>> observable = mRxSerivce.bindWX(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResTokenBean>handleObservableResult())
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
                mSetPwdView.requestFailed(message);
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


}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean1;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.CompletionSituationView;

/**
 * 会长的完成情况
 * Created by pc on 2017/6/27.
 */

public class CompletionSituationPresenter extends BasePresenter<CompletionSituationView> {

    private CompletionSituationView mSofwwareUserView;

    public CompletionSituationPresenter(Context context, CompletionSituationView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void mvpExplemeUser(String tel, String password, String rePassword, String mToken) {
        RxObserver rxObserver = new RxObserver<UserInfoBean>() {
            @Override
            public void onHandleSuccess(UserInfoBean resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.mvpExpleme();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        RegisterUserBean1 bean = new RegisterUserBean1();
        bean.setToken(mToken);
        bean.setPassword(password);
        bean.setTelephone(tel);
        bean.setRepeatpwd(rePassword);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.registerUser1(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

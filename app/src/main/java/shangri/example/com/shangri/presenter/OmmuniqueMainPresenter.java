package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean1;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.OmmuniqueMainView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class OmmuniqueMainPresenter extends BasePresenter<OmmuniqueMainView> {

    private OmmuniqueMainView mSofwwareUserView;
    public OmmuniqueMainPresenter(Context context, OmmuniqueMainView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void mvpExplemeUser(String tel, String password, String rePassword, String mToken){
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

    //查询消息以及框架里面主播管理员申请个数
    public void mineCount() {
        RxObserver rxObserver = new RxObserver<CountBean>() {
            @Override
            public void onHandleSuccess(CountBean resultBean) {
                mSofwwareUserView.mineCount(resultBean);
            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<CountBean>> observable = mRxSerivce.mineCount(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CountBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.view.EnterPlatformView;

/**
 * Description:
 * Data：2018/11/9-11:05
 * Author: lin
 */
public class EnterPlatformPresent extends BasePresenter<EnterPlatformView> {
    EnterPlatformView platformView;
    public EnterPlatformPresent(Context context, EnterPlatformView view) {
        super(context, view);
        platformView = view;
    }
    public void enterPlatfrom(String plats, String other_palt) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                platformView.enterPlatfrom();

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                platformView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.enterPlatfrom(UserConfig.getInstance().getToken(), plats, other_palt);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void platformType() {
        RxObserver rxObserver = new RxObserver<BossPlatBean>() {
            @Override
            public void onHandleSuccess(BossPlatBean resultBean) {
                platformView.platformType(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                platformView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<BossPlatBean>> observable = mRxSerivce.platfromType(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossPlatBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

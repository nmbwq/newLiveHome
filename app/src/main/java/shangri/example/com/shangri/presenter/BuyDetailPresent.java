package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BuyDetailBean;
import shangri.example.com.shangri.presenter.view.BuyDetailView;

/**
 * Description:
 * Data：2018/11/7-10:53
 * Author: lin
 */
public class BuyDetailPresent extends BasePresenter<BuyDetailView> {
    BuyDetailView detailView;

    public BuyDetailPresent(Context context, BuyDetailView view) {
        super(context, view);
        detailView = view;
    }

    public void buyDetail() {
        RxObserver rxObserver = new RxObserver<BuyDetailBean>() {
            @Override
            public void onHandleSuccess(BuyDetailBean mObject) {
                detailView.buyDetail(mObject);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                detailView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<BuyDetailBean>> observable = mRxSerivce.buyDetail(UserConfig.getInstance().getToken());
        Disposable disposable = observable
                .compose(RxHelper.<BuyDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

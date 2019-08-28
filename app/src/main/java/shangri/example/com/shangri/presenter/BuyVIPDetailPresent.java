package shangri.example.com.shangri.presenter;

import android.content.Context;

import com.umeng.socialize.utils.UmengText;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BuyDetailBean;
import shangri.example.com.shangri.model.bean.response.BuyVIPDetailBean;
import shangri.example.com.shangri.presenter.view.BuyDetailView;
import shangri.example.com.shangri.presenter.view.BuyVIPDetailView;

/**
 * Description:
 * Data：2018/11/7-10:53
 * Author: lin
 */
public class BuyVIPDetailPresent extends BasePresenter<BuyVIPDetailView> {
    BuyVIPDetailView detailView;

    public BuyVIPDetailPresent(Context context, BuyVIPDetailView view) {
        super(context, view);
        detailView = view;
    }

    public void buyDetail(int page) {
        RxObserver rxObserver = new RxObserver<BuyVIPDetailBean>() {
            @Override
            public void onHandleSuccess(BuyVIPDetailBean mObject) {
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
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        Observable<BaseResponseEntity<BuyVIPDetailBean>> observable = mRxSerivce.buyVIPDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BuyVIPDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

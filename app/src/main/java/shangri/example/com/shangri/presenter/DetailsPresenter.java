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
import shangri.example.com.shangri.model.bean.response.DetailsBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.view.DetailsView;

public class DetailsPresenter extends BasePresenter<DetailsView> {

    private DetailsView detailsView;

    public DetailsPresenter(Context context, DetailsView view) {
        super(context, view);

        this.detailsView = view;
    }


    public void getDetails() {
        RxObserver rxObserver = new RxObserver<DetailsBean>() {
            @Override
            public void onHandleSuccess(DetailsBean detailsBean) {

                detailsView.Success(detailsBean);


            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                detailsView.requestFailed(message);
            }
        };


        Observable<BaseResponseEntity<DetailsBean>> observable = mRxSerivce.getBills(UserConfig.getInstance().getToken());
        Disposable disposable = observable
                .compose(RxHelper.<DetailsBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }
}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.PricePackageBean;
import shangri.example.com.shangri.model.bean.response.WelfareWithdrawalBean;
import shangri.example.com.shangri.presenter.view.RedEnvelopeWelfareView;

public class RedEnvelopeWelfarePresenter extends BasePresenter<RedEnvelopeWelfareView> {


    private RedEnvelopeWelfareView redEnvelopeWelfareView;

    public RedEnvelopeWelfarePresenter(Context context, RedEnvelopeWelfareView view) {
        super(context, view);

        this.redEnvelopeWelfareView = view;
    }

    public void withdrawDeposit(String token, String zfb_account, String apply_user, String apply_amount , String  package_id) {


        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                redEnvelopeWelfareView.Success();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                redEnvelopeWelfareView.requestFailed(message);
            }
        };

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.welfareWithdrawal(token,
                zfb_account, apply_user, apply_amount,package_id);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void withdrawDeposit() {
        RxObserver rxObserver = new RxObserver<PricePackageBean>() {
            @Override
            public void onHandleSuccess(PricePackageBean resultBean) {
                redEnvelopeWelfareView.withdrawDeposit(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                redEnvelopeWelfareView.requestFailed(message);
            }
        };

        BaseBeen bean = new BaseBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<PricePackageBean>> observable = mRxSerivce.pricePackage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PricePackageBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.presenter.view.CompanyCertificationView;

/**  公司认证
 * */

public class CompanyCertificationPresenter extends BasePresenter<CompanyCertificationView> {

    private CompanyCertificationView companyCertificationView;


    public CompanyCertificationPresenter(Context context, CompanyCertificationView view) {
        super(context, view);
        this.companyCertificationView = view;
    }



    public void certification(String token) {
        RxObserver rxObserver = new RxObserver<CertificationBean>() {
            @Override
            public void onHandleSuccess(CertificationBean resultBean) {

                companyCertificationView.certificationSuccess(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyCertificationView.requestFailed(message);
            }
        };

//        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<CertificationBean>> observable = mRxSerivce.authentication(token);
        Disposable disposable = observable
                .compose(RxHelper.<CertificationBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

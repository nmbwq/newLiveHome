package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.CheckCodeBean;
import shangri.example.com.shangri.model.bean.request.ReqReportBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ReportBean;
import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.presenter.view.ReportView;

public class ReportPresenter extends BasePresenter<ReportView> {
    private ReportView reportView;

    public ReportPresenter(Context context, ReportView view) {
        super(context, view);
        reportView = view;
    }

    public void loadData(final ReqReportBean reportBean) {
//        RxObserver rxObserver = new RxObserver<ReportBean>() {
//            @Override
//            public void onHandleSuccess(ReportBean resultBean) {
//                reportView.loadData(reportBean);
//            }
//
//            @Override
//            public void onHandleComplete() {
//
//            }
//
//            @Override
//            public void onHandleFailed(String message) {
//                reportView.requestFailed(message); //检验校验码成功
//            }
//        };
//
//        Observable<BaseResponseEntity<ReportBean>> observable = mRxSerivce.reportData(reportBean);
//        Disposable disposable = observable
//                .compose(RxHelper.<ReportBean>handleObservableResult())
//                .subscribeWith(rxObserver);
//        addSubscribe(disposable);

    }
}

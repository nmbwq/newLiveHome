package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.CommunicationBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.presenter.view.SendResumeView;
import shangri.example.com.shangri.ui.fragment.SendResumeFragment;

public class SendResumePresenter extends BasePresenter<SendResumeView> {


    private SendResumeView sendResumeView;

    public SendResumePresenter(Context context, SendResumeView view) {
        super(context, view);
        this.sendResumeView = view;
    }


    public void getCommunicationList(String token, int type, String page) {

        RxObserver rxObserver = new RxObserver<BossDataBean>() {
            @Override
            public void onHandleSuccess(BossDataBean communicationBean) {
//                mSofwwareUserView.newsSucceed(newsBean);
                sendResumeView.succeed(communicationBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                sendResumeView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.getLinkUp(token, type, page);
        Disposable disposable = observable
                .compose(RxHelper.<BossDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

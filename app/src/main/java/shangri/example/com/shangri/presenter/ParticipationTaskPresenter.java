package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ParticipationBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ParticipationTaskBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.ParticipationTaskView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class ParticipationTaskPresenter extends BasePresenter<ParticipationTaskView> {

    private ParticipationTaskView mSofwwareUserView;

    public ParticipationTaskPresenter(Context context, ParticipationTaskView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void participationTaskList(int currPage, String Task_id) {
        RxObserver rxObserver = new RxObserver<ParticipationTaskBean>() {
            @Override
            public void onHandleSuccess(ParticipationTaskBean resultBean) {
                mSofwwareUserView.mvpExpleme(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ParticipationBean bean = new ParticipationBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTask_id(Task_id);
        bean.setPage(String.valueOf(currPage));
        Observable<BaseResponseEntity<ParticipationTaskBean>> observable = mRxSerivce.participationTaskList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ParticipationTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.PatrolBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.TaskDeleteBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.TaskBean;
import shangri.example.com.shangri.model.bean.response.TaskDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.TeskView;

/**
 * 列表
 * Created by pc on 2017/6/27.
 */

public class TaskPresenter extends BasePresenter<TeskView> {

    private TeskView mSofwwareUserView;

    public TaskPresenter(Context context, TeskView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void requestList(Integer currPage) {
        RxObserver rxObserver = new RxObserver<TaskDataBean>() {
            @Override
            public void onHandleSuccess(TaskDataBean resultBean) {
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
        TaskBean bean = new TaskBean();
        bean.setPage(String.valueOf(currPage));
        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<TaskDataBean>> observable = mRxSerivce.getTasklList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<TaskDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void taskDelete(String task_ids) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.taskDelete();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        TaskDeleteBean bean = new TaskDeleteBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTask_ids(task_ids);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskDelete(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }
}

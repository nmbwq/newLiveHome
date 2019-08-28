package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddTaskBean;
import shangri.example.com.shangri.model.bean.request.ParticipateTaskBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.UpdateTaskBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.AddTaskView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class AddTaskPresenter extends BasePresenter<AddTaskView> {

    private AddTaskView mSofwwareUserView;

    public AddTaskPresenter(Context context, AddTaskView view) {
        super(context, view);
        mSofwwareUserView = view;
    }


    /**
     * 加入
     */
    public void partIn(String task_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.partIn();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTask_id(task_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.partIn(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void sendLaunch(String guildid, String theme, String content,
                           String start_time, String end_time, String expect_aims) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.mvpExpleme();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddTaskBean bean = new AddTaskBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guildid);
        bean.setTheme(theme);
        bean.setContent(content);
        bean.setStart_time(start_time);
        bean.setEnd_time(end_time);
        bean.setExpect_aims(expect_aims);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void participate(String task_id, String Self_aims) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.mvpExpleme();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ParticipateTaskBean bean = new ParticipateTaskBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTask_id(task_id);
        bean.setSelf_aims(Self_aims);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.participate(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void taskUpdate(String task_id, String guild_id, String theme, String content
            , String start_time, String end_time, String expect_aims) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.mvpExpleme();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        UpdateTaskBean bean = new UpdateTaskBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTask_id(task_id);
        bean.setGuild_id(guild_id);

        bean.setTheme(theme);
        bean.setContent(content);
        bean.setStart_time(start_time);
        bean.setEnd_time(end_time);
        bean.setExpect_aims(expect_aims);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskUpdate(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取任务详情
     */
    public void taskDetail(String task_id) {
        RxObserver rxObserver = new RxObserver<NewTaskDataBean.TasksBean>() {
            @Override
            public void onHandleSuccess(NewTaskDataBean.TasksBean resultBean) {
                mSofwwareUserView.taskDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Log.d("Debug", "task_i值为" + task_id);
        bean.setTask_id(task_id);
        Observable<BaseResponseEntity<NewTaskDataBean.TasksBean>> observable = mRxSerivce.taskDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewTaskDataBean.TasksBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.content.IntentFilter;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LabelBean;
import shangri.example.com.shangri.model.bean.request.NewTaskBean;
import shangri.example.com.shangri.model.bean.request.PatrolBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.PatrolView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class PatrolPresenter extends BasePresenter<PatrolView> {

    private PatrolView mSofwwareUserView;

    public PatrolPresenter(Context context, PatrolView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void requestPatrolsList(int currPage, int type, String guild_id, String register_id) {
        RxObserver rxObserver = new RxObserver<PatrolDataBean>() {
            @Override
            public void onHandleSuccess(PatrolDataBean resultBean) {
                mSofwwareUserView.PatrolsListData(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PatrolBean bean = new PatrolBean();
        bean.setPage(String.valueOf(currPage));
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        if (guild_id.length() != 0) {
            bean.setGuild_id(guild_id);
        }
        if (register_id.length() > 0) {
            bean.setRegister_id(register_id);
        }
        Observable<BaseResponseEntity<PatrolDataBean>> observable = mRxSerivce.getPartolList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PatrolDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 我的任务列表
     *
     * @param currPage
     * @param type
     * @param guild_id
     * @param register_id
     */
    public void requestTaskList(int currPage, int type, String guild_id, String task_status, String register_id) {
        RxObserver rxObserver = new RxObserver<NewTaskDataBean>() {
            @Override
            public void onHandleSuccess(NewTaskDataBean resultBean) {
                mSofwwareUserView.requestTaskList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        NewTaskBean bean = new NewTaskBean();
        bean.setPage(String.valueOf(currPage));
        bean.setToken(UserConfig.getInstance().getToken());

        bean.setTask_status(task_status);
        bean.setType(type + "");
        if (guild_id.length() != 0) {
            bean.setGuild_id(guild_id);
        }
        if (register_id.length() != 0) {
            bean.setRegister_id(register_id);
        }
        Observable<BaseResponseEntity<NewTaskDataBean>> observable = mRxSerivce.getTaskList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewTaskDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void getGuildList() {
        RxObserver rxObserver = new RxObserver<ChoiceGuildBean>() {
            @Override
            public void onHandleSuccess(ChoiceGuildBean resultBean) {
                mSofwwareUserView.listGuildData(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MyGuildBean bean = new MyGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPurview("self");
        Observable<BaseResponseEntity<ChoiceGuildBean>> observable = mRxSerivce.selectguild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChoiceGuildBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 列表提醒
     */
    public void getAlite(String inspect_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.getalert();
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
        bean.setInspect_id(inspect_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.getAlite(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 撤销操作
     */
    public void taskRepeal(String task_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.taskRepeal();
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
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskRepeal(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 任务列表提醒
     */
    public void taskAoalert(String task_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.taskAoalert();
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
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskAoalert(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
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


    /**
     * 列表设置已读
     */
    public void inspectRead(String inspect_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.getalert();
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
        bean.setInspect_id(inspect_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.inspectRead(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 有没有公司
     */
    public void companyMy() {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mSofwwareUserView.companyMy(resultBean);
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
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.companyMy(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //下面是任务的操作

    /**
     * 有没有公司
     */
    public void inspectDetail(String inspect_id) {
        RxObserver rxObserver = new RxObserver<PatrolDataBean.InspectsBean>() {
            @Override
            public void onHandleSuccess(PatrolDataBean.InspectsBean resultBean) {
                mSofwwareUserView.inspectDetail(resultBean);
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
        bean.setInspect_id(inspect_id);
        Observable<BaseResponseEntity<PatrolDataBean.InspectsBean>> observable = mRxSerivce.inspectDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PatrolDataBean.InspectsBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 判断是否过期
     */
    public void memberLate() {
        RxObserver rxObserver = new RxObserver<timeBean>() {
            @Override
            public void onHandleSuccess(timeBean resultBean) {
                mSofwwareUserView.memberLate(resultBean);
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
        Observable<BaseResponseEntity<timeBean>> observable = mRxSerivce.memberLate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<timeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

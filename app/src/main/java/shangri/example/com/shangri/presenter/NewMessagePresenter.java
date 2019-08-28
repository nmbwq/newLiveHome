package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.LookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MessagesBean;
import shangri.example.com.shangri.model.bean.response.NewMessageBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.presenter.view.NewMessageView;

/**
 * Description:新消息
 * Data：2018/11/19-18:26
 * Author: lin
 */
public class NewMessagePresenter extends BasePresenter<NewMessageView> {
    NewMessageView newMessageView;
    public NewMessagePresenter(Context context, NewMessageView view) {
        super(context, view);
        newMessageView = view;
    }

    /**
     * 新消息列表
     * @param page
     * @param type
     */
    public void getNewMessage(int page,int type){
        RxObserver rxObserver = new RxObserver<NewMessageBean>() {
            @Override
            public void onHandleSuccess(NewMessageBean messagesBean) {
                newMessageView.getMessage(messagesBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                newMessageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page+"");
        bean.setType(type+"");
        Observable<BaseResponseEntity<NewMessageBean>> observable = mRxSerivce.getNewMessage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewMessageBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 设置已读
     * @param id
     */
    public void isReadMessage(String id){
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object messagesBean) {
                newMessageView.isReadMessage();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                newMessageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.isReadMessage(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 职位详情
     * @param recruit_id
     */
    public void anchorDetail(String recruit_id) {
        RxObserver rxObserver = new RxObserver<anchorDetailBean>() {
            @Override
            public void onHandleSuccess(anchorDetailBean resultBean) {
                newMessageView.anchorDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                newMessageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<anchorDetailBean>> observable = mRxSerivce.anchorDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<anchorDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

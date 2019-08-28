package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LookDetailBean;
import shangri.example.com.shangri.model.bean.response.MessagesBean;
import shangri.example.com.shangri.presenter.view.MessagesListView;

/**
 * Description:
 * Data：2018/11/4-9:58
 * Author: lin
 */
public class MessagesPresent extends BasePresenter<MessagesListView> {
    MessagesListView messagesListView;
    public MessagesPresent(Context context, MessagesListView view) {
        super(context, view);
        messagesListView = view;
    }

    /**
     * 获取消息列表
     * @param page
     */

    public void getMessage(int page){
        RxObserver rxObserver = new RxObserver<MessagesBean>() {
            @Override
            public void onHandleSuccess(MessagesBean messagesBean) {
                messagesListView.getMessage(messagesBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                messagesListView.requestFailed(message);
            }
        };
        LookBean bean = new LookBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page+"");
        Observable<BaseResponseEntity<MessagesBean>> observable = mRxSerivce.getMessage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MessagesBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 推送消息设置已读
     * @param rcd_id
     */
    public void setIsRead(int rcd_id){
        RxObserver rxObserver = new RxObserver<MessagesBean>() {
            @Override
            public void onHandleSuccess(MessagesBean messagesBean) {
                messagesListView.setIsRead(messagesBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                messagesListView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<MessagesBean>> observable = mRxSerivce.setIsRead(UserConfig.getInstance().getToken(),rcd_id+"");
        Disposable disposable = observable
                .compose(RxHelper.<MessagesBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.FeedbackBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.messageInfoDataBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;
import shangri.example.com.shangri.presenter.view.NewAnchorView;
import shangri.example.com.shangri.presenter.view.YueliaoMessageView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class YueliaoMessagePresenter extends BasePresenter<YueliaoMessageView> {

    private YueliaoMessageView mSofwwareUserView;

    public YueliaoMessagePresenter(Context context, YueliaoMessageView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 信息列表接口
     *
     * @param page
     */
    public void messageInfo(String page) {
        RxObserver rxObserver = new RxObserver<messageInfoDataBean>() {
            @Override
            public void onHandleSuccess(messageInfoDataBean resultBean) {
                mSofwwareUserView.messageInfo(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        FeedbackBean bean = new FeedbackBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page + "");
        Observable<BaseResponseEntity<messageInfoDataBean>> observable = mRxSerivce.messageInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.<messageInfoDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 信息列表删除接口
     */
    public void infoDel(String chat_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.infoDel();
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
        bean.setChat_id(chat_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.infoDel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 聊天消息设置已读接口
     */
    public void setingRead(String chat_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.setingRead();
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
        bean.setChat_id(chat_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.setingRead(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

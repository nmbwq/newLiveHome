package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.MessageBean;
import shangri.example.com.shangri.model.bean.request.addRuzhuBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.wikiCollectBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;

/**
 * 百科获取列表
 * Created by pc on 2017/6/27.
 */

public class EncyclopediaListPresenter extends BasePresenter<EncyclopedialistView> {

    private EncyclopedialistView mSofwwareUserView;

    public EncyclopediaListPresenter(Context context, EncyclopedialistView view) {
        super(context, view);
        mSofwwareUserView = view;
    }


    /**
     * 获取百科列表借口
     */

    public void GetEncyclopediaList(int type, boolean serch, String text) {
        RxObserver rxObserver = new RxObserver<EncyclopediaList>() {
            @Override
            public void onHandleSuccess(EncyclopediaList resultBean) {
                mSofwwareUserView.encyclopediaPlatfromList(resultBean);
                mSofwwareUserView.requestFailed("");

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (serch) {
            bean.setSearch(text);
        }
        bean.setType(type);
        Observable<BaseResponseEntity<EncyclopediaList>> observable = mRxSerivce.EncyclopediaList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<EncyclopediaList>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 消息列表
     *
     * @param type
     * @param page
     */
    public void messageList(String type, String page) {
        RxObserver rxObserver = new RxObserver<MessageResonse>() {
            @Override
            public void onHandleSuccess(MessageResonse resultBean) {
                mSofwwareUserView.messageList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MessageBean bean = new MessageBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        bean.setType(type);
        Observable<BaseResponseEntity<MessageResonse>> observable = mRxSerivce.messageList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MessageResonse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 我要入驻
     */

    public void inuser(int type, String name, String phone) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.addRuzhu();
                mSofwwareUserView.requestFailed("");

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        addRuzhuBean bean = new addRuzhuBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        bean.setName(name);
        bean.setTelephone(phone);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.wikiInuser(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 百科关注接口
     */

    public void wikidocollect(int type, String id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.wikiDoCollect();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        wikiCollectBean bean = new wikiCollectBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.wikiDoCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 百科取消关注
     */

    public void wikiCancelcollect(int type, String id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.wikiCancelCollect();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        wikiCollectBean bean = new wikiCollectBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.wikiCancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取百科列表借口
     */

    public void wikiFocus(int type) {
        RxObserver rxObserver = new RxObserver<EncyclopediaList>() {
            @Override
            public void onHandleSuccess(EncyclopediaList resultBean) {
                mSofwwareUserView.wikiFocus(resultBean);
                mSofwwareUserView.requestFailed("");

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        Observable<BaseResponseEntity<EncyclopediaList>> observable = mRxSerivce.wikiFocus(bean);
        Disposable disposable = observable
                .compose(RxHelper.<EncyclopediaList>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }



    /**
     * 这是消息已读
     */

    public void messageRead(int id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.messageRead();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.messageRead(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 资讯分享量增加
     *
     * @param
     */

    public void consultShare(String id, String share_type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.consultShare();
            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        bean.setShare_type(share_type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.consultShare(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

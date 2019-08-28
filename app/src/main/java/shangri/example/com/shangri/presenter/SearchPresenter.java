package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddGoodBean;
import shangri.example.com.shangri.model.bean.request.CollectRequestBean;
import shangri.example.com.shangri.model.bean.request.InterBBean;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.SearchListView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 搜索
 * Created by chengaofu on 2017/6/28.
 */

public class SearchPresenter extends BasePresenter<SearchListView> {

    private SearchListView mSearchListView;

    public SearchPresenter(Context context, SearchListView view) {
        super(context, view);
        mSearchListView = view;
    }


    //回答列表
    public void qaIndex(int currPage, String order, String search) {
        RxObserver rxObserver = new RxObserver<InterlocutionBean>() {
            @Override
            public void onHandleSuccess(InterlocutionBean resultBean) {
                mSearchListView.interlocution(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        InterBBean bean = new InterBBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        bean.setPage(String.valueOf(currPage));
        bean.setOrder(order);
        bean.setSearch(search);
        Observable<BaseResponseEntity<InterlocutionBean>> observable = mRxSerivce.qaIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<InterlocutionBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //点击有用
    public void addGood(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.good(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addGood(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //取消有用
    public void cancelGood(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSearchListView.good(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.cancelgood(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //收藏
    public void addCollect(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSearchListView.addRequestCollect(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addRequestCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //取消收藏
    public void deleteCollect(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSearchListView.DeleteRequestCollect(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.DeleteRequestCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //点击没用
    public void addBad(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSearchListView.bad(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addBad(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //取消没用
    public void cancelBad(String qaid, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSearchListView.bad(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        AddGoodBean bean = new AddGoodBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQaid(qaid);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.cancelbad(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //回答列表
    public void collectList(String currPage, String type) {
        RxObserver rxObserver = new RxObserver<CollectBean>() {
            @Override
            public void onHandleSuccess(CollectBean resultBean) {
                mSearchListView.getCollect(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        CollectRequestBean bean = new CollectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(currPage);
        bean.setOp(type);
        Observable<BaseResponseEntity<CollectBean>> observable = mRxSerivce.collectList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CollectBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //浏览量
    public void requestBrowse(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.browse(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void requestPraise(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.addPeixunLike(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.praise(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void requestCancel(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.DeletePeixunLike(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articlecancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //培训收藏
    public void requestCollect(String train_id, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.addPeixunColect(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        PeiCollect bean = new PeiCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.registerCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //培训取消收藏
    public void requestCancelCollect(String train_id, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.DeletePeixunColect(position);
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        PeiCollect bean = new PeiCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.cancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //我的头条

    public void headPraise(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.praise(position);
                mSearchListView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headPraise(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void headCancel(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.praise(position);
                mSearchListView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //浏览量
    public void headBrowse(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSearchListView.Browse(position);
                mSearchListView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSearchListView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headbrowse(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


}

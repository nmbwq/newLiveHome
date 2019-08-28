package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.NewsDetailView;

/**
 * 资讯详情
 * Created by chengaofu on 2017/6/29.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    private NewsDetailView mNewsDetailView;
    public NewsDetailPresenter(Context context, NewsDetailView view) {
        super(context, view);
        mNewsDetailView = view;
    }

    //头条点赞
    public void requestPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.praise();
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
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

    public void requestCancel(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.praise();
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
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

    public void createPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mNewsDetailView.praise();
            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
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




    public void deletePraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mNewsDetailView.praise();
            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
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


    public void deleteCollect(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mNewsDetailView.collect();
            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.deletecollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void collect(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mNewsDetailView.collect();
            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.collect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }





    //头条收藏
    public void requestCollect(String train_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.collect();
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //头条取消收藏
    public void requestCancelCollect(String train_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mNewsDetailView.collect();
                mNewsDetailView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mNewsDetailView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

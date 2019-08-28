package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;

/**
 * 点赞操作
 * Created by pc on 2017/6/27.
 */

public class CultivatLikePresenter extends BasePresenter<CultivateLikeView> {

    private CultivateLikeView mSofwwareUserView;
    public CultivatLikePresenter(Context context, CultivateLikeView view) {
        super(context, view);
        mSofwwareUserView = view;
    }
    public void createPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.praise();
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
                mSofwwareUserView.praise();
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
                mSofwwareUserView.collect();
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
                mSofwwareUserView.collect();
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
        bean.setTrain_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.collect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

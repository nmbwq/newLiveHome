package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.presenter.view.ConsultationView;
import shangri.example.com.shangri.presenter.view.HeadLinesView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class HeadPresenter extends BasePresenter<HeadLinesView> {

    private HeadLinesView mSofwwareUserView;

    public HeadPresenter(Context context, HeadLinesView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void bannerDetail(String page, String type) {
        RxObserver rxObserver = new RxObserver<HeadLinesData>() {
            @Override
            public void onHandleSuccess(HeadLinesData resultBean) {
                mSofwwareUserView.bannerDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {


            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BannerDetalBean bean = new BannerDetalBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setCatagory_id(type);
        bean.setPage(page);
        Observable<BaseResponseEntity<HeadLinesData>> observable = mRxSerivce.headLines(bean);
        Disposable disposable = observable
                .compose(RxHelper.<HeadLinesData>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //头条咨询
    public void search(int currPage, String content) {
        RxObserver rxObserver = new RxObserver<HeadLinesData>() {
            @Override
            public void onHandleSuccess(HeadLinesData resultBean) {
                mSofwwareUserView.search(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        SearchNewsBean bean = new SearchNewsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setSearch(content);
        bean.setPage(currPage + "");
        Observable<BaseResponseEntity<HeadLinesData>> observable = mRxSerivce.headSearch(bean);
        Disposable disposable = observable
                .compose(RxHelper.<HeadLinesData>handleObservableResult())
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
                mSofwwareUserView.praise(position);
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


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headPraise(bean);
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
                mSofwwareUserView.praise(position);
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


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
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
                mSofwwareUserView.Browse(position);
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


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headbrowse(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //头条收藏
    public void requestCollect(String train_id, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.Collect(position);
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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
    public void requestCancelCollect(String train_id, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.Collect(position);
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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


    //查询消息以及框架里面主播管理员申请个数
    public void mineCount() {
        RxObserver rxObserver = new RxObserver<CountBean>() {
            @Override
            public void onHandleSuccess(CountBean resultBean) {
                mSofwwareUserView.mineCount(resultBean);
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
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<CountBean>> observable = mRxSerivce.mineCount(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CountBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

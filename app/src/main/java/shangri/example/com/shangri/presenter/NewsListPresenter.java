package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerBean;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.NewsListBean;
import shangri.example.com.shangri.model.bean.response.BannerInfoBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.NewsListInfoBean;
import shangri.example.com.shangri.presenter.view.NewsListView;
import shangri.example.com.shangri.util.KeytUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 资讯列表
 * Created by chengaofu on 2017/6/28.
 */

public class NewsListPresenter extends BasePresenter<NewsListView>{

    private NewsListView mNewsListView;
    public NewsListPresenter(Context context, NewsListView view) {
        super(context, view);
        mNewsListView = view;
    }

    public void getBannerDetailByTypeId(String typeId){
        RxObserver rxObserver = new RxObserver<List<BannerInfoBean>>() {
            @Override
            public void onHandleSuccess(List<BannerInfoBean> bannerInfoBeanList) {
                mNewsListView.getBannerDetailByTypeId(bannerInfoBeanList);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mNewsListView.requestFailed(message);

            }
        };

        BannerBean bean = new BannerBean();
        bean.setTypeId(typeId);
        BaseRequestEntity<BannerBean> requestEntity =
                new BaseRequestEntity<BannerBean>();
        requestEntity.setData(bean);

        //加密后的entity
        BaseRequestEntity<BannerBean> entity =
                (BaseRequestEntity<BannerBean>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<List<BannerInfoBean>>> observable = mRxSerivce.getBannerDetailByTypeId(entity);
        Disposable disposable = observable
                .compose(RxHelper.<List<BannerInfoBean>>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void requestNewsList(long typeId, int currPage, int pageSize){
        RxObserver rxObserver = new RxObserver<NewsListInfoBean>() {
            @Override
            public void onHandleSuccess(NewsListInfoBean resultBean) {
                mNewsListView.requestNewsList(resultBean.getPageData(), resultBean.getPageInfo());

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mNewsListView.requestFailed(message);
            }
        };
        NewsListBean bean = new NewsListBean();
        bean.setTypeId(typeId);
        bean.setUserId(UserConfig.getInstance().getUserId());
        bean.setCurrPage(currPage);
        bean.setPageSize(pageSize);
        BaseRequestEntity<NewsListBean> requestEntity =
                new BaseRequestEntity<NewsListBean>();
        requestEntity.setData(bean);

        //加密后的entity
        BaseRequestEntity<NewsListBean> entity =
                (BaseRequestEntity<NewsListBean>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<NewsListInfoBean>> observable = mRxSerivce.requestNewsList(entity);
        Disposable disposable = observable
                .compose(RxHelper.<NewsListInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void requestPraise(long infoId, byte praiseOrNot){
//
//        RxObserver rxObserver = new RxObserver<PraiseInfoBean>() {
//            @Override
//            public void onHandleSuccess(PraiseInfoBean resultBean) {
//                mNewsListView.praise(resultBean);
//            }
//
//            @Override
//            public void onHandleComplete() {
//
//            }
//
//            @Override
//            public void onHandleFailed(String message) {
//                mNewsListView.requestFailed(message);
//            }
//        };
//        PraiseBean bean = new PraiseBean();
//        bean.setInfoId(infoId);
//        bean.setUserId((long) UserConfig.getInstance().getUserId());
//        bean.setPraiseOrNot(praiseOrNot);
//
//        BaseRequestEntity<PraiseBean> requestEntity =
//                new BaseRequestEntity<PraiseBean>();
//        requestEntity.setData(bean);
//
//        //加密后的entity
//        BaseRequestEntity<PraiseBean> entity =
//                (BaseRequestEntity<PraiseBean>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
//        Observable<BaseResponseEntity<PraiseInfoBean>> observable = mRxSerivce.praise(entity);
//        Disposable disposable = observable
//                .compose(RxHelper.<PraiseInfoBean>handleObservableResult())
//                .subscribeWith(rxObserver);
//        addSubscribe(disposable);

    }


}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.CollectRequestBean;
import shangri.example.com.shangri.model.bean.request.InformationBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.model.bean.response.LookBannerBean;
import shangri.example.com.shangri.model.bean.response.MoreBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.presenter.view.ConsultationView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class ConsultationPresenter extends BasePresenter<ConsultationView> {

    private ConsultationView mSofwwareUserView;

    public ConsultationPresenter(Context context, ConsultationView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void bannerDetail(String page, String type) {
        RxObserver rxObserver = new RxObserver<BannerHomeLookBean>() {
            @Override
            public void onHandleSuccess(BannerHomeLookBean resultBean) {
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
        Observable<BaseResponseEntity<BannerHomeLookBean>> observable = mRxSerivce.article(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BannerHomeLookBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //头条搜索
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

    //培训咨询
    public void PeixunSearch(int currPage, String content) {
        RxObserver rxObserver = new RxObserver<BannerHomeLookBean>() {
            @Override
            public void onHandleSuccess(BannerHomeLookBean resultBean) {
                mSofwwareUserView.PeixunSearch(resultBean);

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
        Observable<BaseResponseEntity<BannerHomeLookBean>> observable = mRxSerivce.PeixunSearch(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BannerHomeLookBean>handleObservableResult())
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


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articlecancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void createPraise(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.praise(position);
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


    public void deletePraise(String infoId, final int position) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.praise(position);
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


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.browse(bean);
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
                mSofwwareUserView.Collect(position);
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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
                mSofwwareUserView.Collect(position);
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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


    /**
     * 导航列表
     */


    public void catagory() {

        RxObserver rxObserver = new RxObserver<CultivateBean>() {
            @Override
            public void onHandleSuccess(CultivateBean cultivateBean) {
                mSofwwareUserView.cultivateSucceed(cultivateBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        LaunchPatrolBean bean = new LaunchPatrolBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<CultivateBean>> observable = mRxSerivce.navigationLists(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CultivateBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 一周资讯
     */


    public void newInfo(int page) {

        RxObserver rxObserver = new RxObserver<NewsBean>() {
            @Override
            public void onHandleSuccess(NewsBean newsBean) {
                mSofwwareUserView.newsSucceed(newsBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        LaunchPatrolBean bean = new LaunchPatrolBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        bean.setPage(page + "");
        Observable<BaseResponseEntity<NewsBean>> observable = mRxSerivce.news(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewsBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //看看banner图
    public void lookBanner() {
        RxObserver rxObserver = new RxObserver<LookBannerBean>() {
            @Override
            public void onHandleSuccess(LookBannerBean resultBean) {
                mSofwwareUserView.lookBannerSucceed(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        LaunchPatrolBean bean = new LaunchPatrolBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<LookBannerBean>> observable = mRxSerivce.lookBanner(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LookBannerBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


//      培训 文章

    /*  public  void  article(String token,String  catagory_id){

          RxObserver rxObserver = new RxObserver<TrainingArticleBean>() {
              @Override
              public void onHandleSuccess(TrainingArticleBean resultBean) {
                 mSofwwareUserView.trainingArticleSucceed(resultBean);
              }

              @Override
              public void onHandleComplete() {

              }

              @Override
              public void onHandleFailed(String message) {
                  mSofwwareUserView.requestFailed(message);
              }
          };
          Observable<BaseResponseEntity<TrainingArticleBean>>observable = mRxSerivce.train(token,catagory_id);
          Disposable disposable = observable
                  .compose(RxHelper.<TrainingArticleBean>handleObservableResult())
                  .subscribeWith(rxObserver);
          addSubscribe(disposable);
      }
  */
    public void articleList(String catagory_id) {

        RxObserver rxObserver = new RxObserver<TrainingArticleBean>() {
            @Override
            public void onHandleSuccess(TrainingArticleBean resultBean) {
                mSofwwareUserView.trainingArticleSucceed(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        LaunchPatrolBean bean = new LaunchPatrolBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        bean.setCatagory_id(catagory_id);
        Observable<BaseResponseEntity<TrainingArticleBean>> observable = mRxSerivce.trainList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<TrainingArticleBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

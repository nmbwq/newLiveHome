package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MoreBean;
import shangri.example.com.shangri.presenter.view.MoreArticlesView;
import shangri.example.com.shangri.ui.activity.MoreArticlesActivity;

public class MoreArticlesPresenter   extends BasePresenter<MoreArticlesView> {

        public MoreArticlesView  moreArticlesView;

    public MoreArticlesPresenter(Context context, MoreArticlesView view) {
        super(context, view);
        moreArticlesView=view;
    }




    //       看看 更多


    public  void articleMore(String token,String  catagory_id){

        RxObserver rxObserver = new RxObserver<MoreBean>() {
            @Override
            public void onHandleSuccess(MoreBean resultBean) {
                moreArticlesView.moreArticlesSucceed(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                moreArticlesView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<MoreBean>> observable = mRxSerivce.more(token,catagory_id);
        Disposable disposable = observable
                .compose(RxHelper.<MoreBean>handleObservableResult())
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
                moreArticlesView.Browse(position);
                moreArticlesView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                moreArticlesView.requestFailed(message);
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
}

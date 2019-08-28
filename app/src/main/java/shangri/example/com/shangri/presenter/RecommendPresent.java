package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CollecBean;
import shangri.example.com.shangri.model.bean.response.RecommendBean;
import shangri.example.com.shangri.presenter.view.RecommendView;

/**
 * Description:小播推荐
 * Data：2018/11/14-17:54
 * Author: lin
 */
public class RecommendPresent extends BasePresenter<RecommendView> {
    RecommendView recommendView;
    public RecommendPresent(Context context, RecommendView view) {
        super(context, view);
        recommendView = view;
    }

    public void getRecommendList(int page) {
        RxObserver rxObserver = new RxObserver<RecommendBean>() {
            @Override
            public void onHandleSuccess(RecommendBean resultBean) {
                recommendView.getRecommendList(resultBean);
            }

            @Override
            public void onHandleComplete() {


            }

            @Override
            public void onHandleFailed(String message) {
                recommendView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page + "");
        Observable<BaseResponseEntity<RecommendBean>> observable = mRxSerivce.getRecommendList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RecommendBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }
}

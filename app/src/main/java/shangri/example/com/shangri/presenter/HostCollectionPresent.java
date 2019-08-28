package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CollecBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.presenter.view.HostCollectionView;

/**
 * Description:会长收藏主播列表
 * Data：2018/11/13-10:25
 * Author: lin
 */
public class HostCollectionPresent extends BasePresenter<HostCollectionView> {
    HostCollectionView collectionView;
    public HostCollectionPresent(Context context, HostCollectionView view) {
        super(context, view);
        collectionView = view;
    }

    public void getCollectList(int page){
        RxObserver rxObserver = new RxObserver<CollecBean>() {
            @Override
            public void onHandleSuccess(CollecBean resultBean) {
                collectionView.getCollectList(resultBean);
            }

            @Override
            public void onHandleComplete() {


            }

            @Override
            public void onHandleFailed(String message) {
                collectionView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page+"");
        Observable<BaseResponseEntity<CollecBean>> observable = mRxSerivce.getCollectList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CollecBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

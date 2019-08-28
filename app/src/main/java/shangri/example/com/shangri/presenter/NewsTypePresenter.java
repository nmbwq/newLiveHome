package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.NewsTypeInfoBean;
import shangri.example.com.shangri.presenter.view.NewsTypeView;
import shangri.example.com.shangri.util.KeytUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 资讯类型
 * Created by chengaofu on 2017/6/29.
 */

public class NewsTypePresenter extends BasePresenter<NewsTypeView> {

    private NewsTypeView mNewsTypeView;
    public NewsTypePresenter(Context context, NewsTypeView view) {
        super(context, view);
        mNewsTypeView = view;
    }

    public void getType(){
        RxObserver rxObserver = new RxObserver<List<NewsTypeInfoBean>>() {
            @Override
            public void onHandleSuccess(List<NewsTypeInfoBean> newsTypeInfoBeanList) {
                mNewsTypeView.getType(newsTypeInfoBeanList);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mNewsTypeView.requestFailed(message);
            }
        };

        BaseRequestEntity<Object> requestEntity =
                new BaseRequestEntity<Object>();

        requestEntity.setData(new Object()); //{}空对象

        //加密后的entity
        BaseRequestEntity<Object> entity =
                (BaseRequestEntity<Object>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<List<NewsTypeInfoBean>>> observable = mRxSerivce.getType(entity);
        Disposable disposable = observable
                .compose(RxHelper.<List<NewsTypeInfoBean>>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

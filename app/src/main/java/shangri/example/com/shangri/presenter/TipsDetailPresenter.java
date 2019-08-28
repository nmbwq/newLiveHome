package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.TipsDetailBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.TipsDetailInfoBean;
import shangri.example.com.shangri.presenter.view.TipsDetailView;
import shangri.example.com.shangri.util.KeytUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 公告详情
 * Created by pc on 2017/6/29.
 */

public class TipsDetailPresenter extends BasePresenter<TipsDetailView> {

    private TipsDetailView mTipsDetailView;
    public TipsDetailPresenter(Context context, TipsDetailView view) {
        super(context, view);
        mTipsDetailView = view;
    }

    public void getMsgById(long id){
        RxObserver rxObserver = new RxObserver<TipsDetailInfoBean>() {
            @Override
            public void onHandleSuccess(TipsDetailInfoBean bean) {
                mTipsDetailView.getMsgById(bean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mTipsDetailView.requestFailed(message);
            }
        };

        TipsDetailBean bean = new TipsDetailBean();
        bean.setId(id);
        BaseRequestEntity<TipsDetailBean> requestEntity =
                new BaseRequestEntity<TipsDetailBean>();
        requestEntity.setData(bean);

        //加密后的entity
        BaseRequestEntity<TipsDetailBean> entity =
                (BaseRequestEntity<TipsDetailBean>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<TipsDetailInfoBean>> observable = mRxSerivce.getMsgById(entity);
        Disposable disposable = observable
                .compose(RxHelper.<TipsDetailInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }
}

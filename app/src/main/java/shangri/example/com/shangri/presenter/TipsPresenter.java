package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.TipsBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.TipsListInfoBean;
import shangri.example.com.shangri.presenter.view.TipsView;
import shangri.example.com.shangri.util.KeytUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 公告、系统消息
 * Created by chengaofu on 2017/6/29.
 */

public class TipsPresenter extends BasePresenter<TipsView> {

    private TipsView mTipsView;
    public TipsPresenter(Context context, TipsView view) {
        super(context, view);
        mTipsView = view;
    }

    public void queryMessageList(String type, int currPage, int pageSize){ //, 暂时不写

        RxObserver rxObserver = new RxObserver<TipsListInfoBean>() {
            @Override
            public void onHandleSuccess(TipsListInfoBean bean) {
                mTipsView.queryMessageList(bean.getPageData(), bean.getPageInfo());
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mTipsView.requestFailed(message);
            }
        };

        BaseRequestEntity<TipsBean> requestEntity =
                new BaseRequestEntity<TipsBean>();
        TipsBean bean = new TipsBean();
        bean.setType(type);
        bean.setCurrPage(currPage);
        bean.setPageSize(pageSize);
        requestEntity.setData(bean);

        //加密后的entity
        BaseRequestEntity<TipsBean> entity =
                (BaseRequestEntity<TipsBean>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<TipsListInfoBean>> observable = mRxSerivce.queryMessageList(entity);
        Disposable disposable = observable
                .compose(RxHelper.<TipsListInfoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

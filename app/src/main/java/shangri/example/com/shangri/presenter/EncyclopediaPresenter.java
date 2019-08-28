package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.presenter.view.ConsultationView;
import shangri.example.com.shangri.presenter.view.EncyclopediaView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class EncyclopediaPresenter extends BasePresenter<EncyclopediaView> {

    private EncyclopediaView mSofwwareUserView;

    public EncyclopediaPresenter(Context context, EncyclopediaView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void bannerDetail() {
        RxObserver rxObserver = new RxObserver<EncyclopediaHomeBean>() {
            @Override
            public void onHandleSuccess(EncyclopediaHomeBean resultBean) {
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
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<EncyclopediaHomeBean>> observable = null;
            observable = mRxSerivce.timingWikiIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<EncyclopediaHomeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

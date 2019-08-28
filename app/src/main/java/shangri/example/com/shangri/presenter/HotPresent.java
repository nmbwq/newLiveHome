package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.presenter.view.HotView;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;

/**
 * Description:热门职位
 * Data：2018/11/8-21:20
 * Author: lin
 */
public class HotPresent extends BasePresenter<HotView> {
    HotView hotView;

    public HotPresent(Context context, HotView view) {
        super(context, view);
        hotView = view;
    }

//    public void recruitList(String plat_id,String page) {
//        RxObserver rxObserver = new RxObserver<BossDataBean>() {
//            @Override
//            public void onHandleSuccess(BossDataBean mAccountDataBean) {
//                hotView.recruitList(mAccountDataBean);
//            }
//
//            @Override
//            public void onHandleComplete() {
//
//            }
//
//            @Override
//            public void onHandleFailed(String message) {
//                hotView.requestFailed(message);
//            }
//        };
//        BossBeen bean = new BossBeen();
//        bean.setPage(page);
//        bean.setToken(UserConfig.getInstance().getToken());
//        bean.setPlat_id(plat_id);
//
//        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.recruitList(bean);
//        Disposable disposable = observable
//                .compose(RxHelper.<BossDataBean>handleObservableResult())
//                .subscribeWith(rxObserver);
//        addSubscribe(disposable);
//    }

    public void getHot() {
        RxObserver rxObserver = new RxObserver<PositionListBean>() {
            @Override
            public void onHandleSuccess(PositionListBean resultBean) {
                hotView.getHotPosition(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                hotView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        String token = UserConfig.getInstance().getToken();
        if (CompanyHomepageActivityTwo.COMPANY_TOKEN != null) {
            if (CompanyHomepageActivityTwo.COMPANY_TOKEN.length() > 0) {
                token = CompanyHomepageActivityTwo.COMPANY_TOKEN;
            }
        }
        bean.setToken(token);
        Observable<BaseResponseEntity<PositionListBean>> observable = mRxSerivce.getHotPosition(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PositionListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

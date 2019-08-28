package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.request.upgradeBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.upgradePackageBean;
import shangri.example.com.shangri.presenter.view.PostbarView;
import shangri.example.com.shangri.presenter.view.VirtualCoinView;

/**
 * Description:
 * Data：2018/11/29-10:20
 * Author: lin
 */
public class PostbarPresenter extends BasePresenter<PostbarView> {

    PostbarView coinView;

    public PostbarPresenter(Context context, PostbarView view) {
        super(context, view);
        coinView = view;
    }

    /**
     * 职位升级套餐
     */
    public void upgradePackage() {
        RxObserver rxObserver = new RxObserver<upgradePackageBean>() {
            @Override
            public void onHandleSuccess(upgradePackageBean resultBean) {
                coinView.upgradePackage(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        BaseBeen bean = new BaseBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<upgradePackageBean>> observable = mRxSerivce.upgradePackage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<upgradePackageBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 职位升级
     */
    public void recruitUpgrade(String recruit_id, String package_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                coinView.recruitUpgrade();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        upgradeBean bean = new upgradeBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        bean.setPackage_id(package_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.recruitUpgrade(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

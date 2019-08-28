package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.request.PayrequestBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageExplain;
import shangri.example.com.shangri.model.bean.response.VIPBannerBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;
import shangri.example.com.shangri.presenter.view.VIPView;

/**
 * Description:vip模块
 * Data：2018/11/21-14:32
 * Author: lin
 */
public class VIPPresenter extends BasePresenter<VIPView> {
    VIPView vipView;

    public VIPPresenter(Context context, VIPView view) {
        super(context, view);
        this.vipView = view;
    }

    /**
     * VIP套餐列表
     */
    public void getVIPPackagesList() {
        RxObserver rxObserver = new RxObserver<VIPCardBean>() {
            @Override
            public void onHandleSuccess(VIPCardBean resultBean) {
                vipView.getVIPPackagesList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setSys("1");
        Observable<BaseResponseEntity<VIPCardBean>> observable = mRxSerivce.getVIPPackagesList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<VIPCardBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * VIP权益
     */
    public void getVIPAdvantage() {
        RxObserver rxObserver = new RxObserver<VIPAdvantageBean>() {
            @Override
            public void onHandleSuccess(VIPAdvantageBean resultBean) {
                vipView.getVIPAdvantage(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<VIPAdvantageBean>> observable = mRxSerivce.getVIPAdvantage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<VIPAdvantageBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * VIP权益说明
     */
    public void getVIPAdvantageExplain() {
        RxObserver rxObserver = new RxObserver<VIPAdvantageExplain>() {
            @Override
            public void onHandleSuccess(VIPAdvantageExplain resultBean) {
                vipView.getVIPAdvantageExplain(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<VIPAdvantageExplain>> observable = mRxSerivce.getVIPAdvantageExplain(bean);
        Disposable disposable = observable
                .compose(RxHelper.<VIPAdvantageExplain>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * VIP支付
     */
    public void VIPPay(String package_id, String package_duration, String pay_price,String pay_method) {
        RxObserver rxObserver = new RxObserver<PayResponseTaskBean>() {
            @Override
            public void onHandleSuccess(PayResponseTaskBean resultBean) {
                vipView.VIPPay(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPackage_id(package_id);
        bean.setPackage_duration(package_duration);
        bean.setPay_price(pay_price);
        bean.setPay_status("0");
        bean.setPay_method(pay_method);
        bean.setBought_channel("2");
        Observable<BaseResponseEntity<PayResponseTaskBean>> observable = mRxSerivce.VIPPay(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PayResponseTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void accountData(){
        RxObserver rxObserver = new RxObserver<AccountDataBean>() {
            @Override
            public void onHandleSuccess(AccountDataBean mAccountDataBean) {
                vipView.accountData(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        MineFragmentBeen bean = new MineFragmentBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<AccountDataBean>> observable = mRxSerivce.accountData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AccountDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * VIPBanner
     */
    public void VIPBanner(){
        RxObserver rxObserver = new RxObserver<VIPBannerBean>() {
            @Override
            public void onHandleSuccess(VIPBannerBean mAccountDataBean) {
                vipView.VIPBanner(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                vipView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<VIPBannerBean>> observable = mRxSerivce.VIPBanner(bean);
        Disposable disposable = observable
                .compose(RxHelper.<VIPBannerBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

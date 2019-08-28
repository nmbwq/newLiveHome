package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.view.VirtualCoinView;

/**
 * Description:
 * Data：2018/11/29-10:20
 * Author: lin
 */
public class VirtualCoinPresenter extends BasePresenter<VirtualCoinView> {

    VirtualCoinView coinView;

    public VirtualCoinPresenter(Context context, VirtualCoinView view) {
        super(context, view);
        coinView = view;
    }

    public void myBdBills(int page) {
        RxObserver rxObserver = new RxObserver<BdBills>() {
            @Override
            public void onHandleSuccess(BdBills resultBean) {
                coinView.myBdBills(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        Observable<BaseResponseEntity<BdBills>> observable = mRxSerivce.myBdBills(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BdBills>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void getVirtualCoin() {
        RxObserver rxObserver = new RxObserver<VirtualCoinBean>() {
            @Override
            public void onHandleSuccess(VirtualCoinBean resultBean) {
                coinView.getVirtualCoin(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setSys("1");
        Observable<BaseResponseEntity<VirtualCoinBean>> observable = mRxSerivce.getVirtualCoin(bean);
        Disposable disposable = observable
                .compose(RxHelper.<VirtualCoinBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 虚拟币支付
     */
    public void virtualCoinPay(String package_id, String pay_price, String pay_method) {//String package_duration,
        RxObserver rxObserver = new RxObserver<PayResponseTaskBean>() {
            @Override
            public void onHandleSuccess(PayResponseTaskBean resultBean) {
                coinView.virtualCoinPay(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        NormalRequestBean bean = new NormalRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPackage_id(package_id);
//        bean.setPackage_duration(package_duration);
        bean.setPay_price(pay_price);
        bean.setPay_status("0");
        bean.setPay_method(pay_method);
        bean.setBought_channel("2");
        Observable<BaseResponseEntity<PayResponseTaskBean>> observable = mRxSerivce.virtualCoinPay(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PayResponseTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void accountData() {
        RxObserver rxObserver = new RxObserver<AccountDataBean>() {
            @Override
            public void onHandleSuccess(AccountDataBean mAccountDataBean) {
                coinView.accountData(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
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
     * 免责协议
     */
    public void legalProtocol() {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                coinView.legalProtocol(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                coinView.requestFailed(message);
            }
        };
        IdIdentBean bean = new IdIdentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.legalProtocol(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

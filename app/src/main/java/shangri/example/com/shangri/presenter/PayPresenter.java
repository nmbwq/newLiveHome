package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ParticipationBean;
import shangri.example.com.shangri.model.bean.request.PayrequestBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.DaysPriceBean;
import shangri.example.com.shangri.model.bean.response.LegalPayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.ParticipationTaskBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.PayView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class PayPresenter extends BasePresenter<PayView> {

    private PayView mSofwwareUserView;

    public PayPresenter(Context context, PayView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 获取支付信息
     */
    public void getorderInfo(String package_type, String price, String pay_type) {

        RxObserver rxObserver = new RxObserver<PayResponseTaskBean>() {
            @Override
            public void onHandleSuccess(PayResponseTaskBean resultBean) {
                mSofwwareUserView.getorderInfo(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PayrequestBean bean = new PayrequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setFrom("2");
        bean.setPackage_type(package_type);
        bean.setPay_type(pay_type);
        bean.setPrice(price);
        Observable<BaseResponseEntity<PayResponseTaskBean>> observable = mRxSerivce.getorderInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PayResponseTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取法务涛谈支付信息
     */
    public void getLegalInfo(String price, String pay_type) {

        RxObserver rxObserver = new RxObserver<LegalPayResponseTaskBean>() {
            @Override
            public void onHandleSuccess(LegalPayResponseTaskBean resultBean) {
                mSofwwareUserView.getLegalInfo(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PayrequestBean bean = new PayrequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setFrom("2");
        bean.setPackage_type("10");
        bean.setPay_type(pay_type);
        bean.setPrice(price);
        Observable<BaseResponseEntity<LegalPayResponseTaskBean>> observable = mRxSerivce.getLegalInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LegalPayResponseTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 职位套餐购买
     */
    public void recruitPay_package(String package_num, String price, String pay_type) {

        RxObserver rxObserver = new RxObserver<LegalPayResponseTaskBean>() {
            @Override
            public void onHandleSuccess(LegalPayResponseTaskBean resultBean) {
                mSofwwareUserView.recruitPay_package(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PayrequestBean bean = new PayrequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setFrom("2");
        bean.setPackage_num(package_num);
        bean.setPay_type(pay_type);
        bean.setPrice(price);
        Observable<BaseResponseEntity<LegalPayResponseTaskBean>> observable = mRxSerivce.recruitPay_package(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LegalPayResponseTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取支付价格
     */
    public void orderGuild() {

        RxObserver rxObserver = new RxObserver<DaysPriceBean>() {
            @Override
            public void onHandleSuccess(DaysPriceBean resultBean) {
                mSofwwareUserView.orderGuild(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PayrequestBean bean = new PayrequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setFrom("2");
        Observable<BaseResponseEntity<DaysPriceBean>> observable = mRxSerivce.orderGuild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<DaysPriceBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 判断是否过期
     */
    public void memberLate() {
        RxObserver rxObserver = new RxObserver<timeBean>() {
            @Override
            public void onHandleSuccess(timeBean resultBean) {
                mSofwwareUserView.memberLate(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<timeBean>> observable = mRxSerivce.memberLate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<timeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

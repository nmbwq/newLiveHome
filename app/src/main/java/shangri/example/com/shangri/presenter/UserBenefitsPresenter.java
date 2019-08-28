package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AnchorsBean;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.FeedbackBean;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.InviteListDataBean;
import shangri.example.com.shangri.model.bean.response.PriceListBean;
import shangri.example.com.shangri.model.bean.response.SevenDayEarningsBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.view.UserBenefitsView;

public class UserBenefitsPresenter extends BasePresenter<UserBenefitsView> {


    private UserBenefitsView userBenefitsView;

    public UserBenefitsPresenter(Context context, UserBenefitsView view) {
        super(context, view);

        this.userBenefitsView = view;
    }


    public void getCredits(String token) {
        RxObserver rxObserver = new RxObserver<AmountBean>() {
            @Override
            public void onHandleSuccess(AmountBean resultBean) {

                userBenefitsView.Success(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<AmountBean>> observable = mRxSerivce.getAmount(token);
        Disposable disposable = observable
                .compose(RxHelper.<AmountBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void getInviteCode(String token) {
        RxObserver rxObserver = new RxObserver<InvitationCodeBean>() {
            @Override
            public void onHandleSuccess(InvitationCodeBean resultBean) {
                userBenefitsView.codeSuccess(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<InvitationCodeBean>> observable = mRxSerivce.getCode(token);
        Disposable disposable = observable
                .compose(RxHelper.<InvitationCodeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void benefit(String token) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

                userBenefitsView.receiveSuccess(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.receiveWeal(token);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 主播提现判断接口
     *
     * @param token
     */
    public void applyPondition(String token) {
        RxObserver rxObserver = new RxObserver<InvitationCodeBean>() {
            @Override
            public void onHandleSuccess(InvitationCodeBean resultBean) {

                userBenefitsView.applyPondition(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<InvitationCodeBean>> observable = mRxSerivce.applyPondition(token);
        Disposable disposable = observable
                .compose(RxHelper.<InvitationCodeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 主播邀请详细列表
     *
     * @param no_resume
     * @param page
     */

    public void invitationMyInvite(String no_resume, String page) {
        RxObserver rxObserver = new RxObserver<InviteListDataBean>() {
            @Override
            public void onHandleSuccess(InviteListDataBean resultBean) {
                userBenefitsView.invitationMyInvite(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        FeedbackBean bean = new FeedbackBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setNo_resume(no_resume);
        bean.setPage(page);
        BaseRequestEntity<FeedbackBean> requestEntity =
                new BaseRequestEntity<FeedbackBean>();
        requestEntity.setData(bean);

        Observable<BaseResponseEntity<InviteListDataBean>> observable = mRxSerivce.invitationMyInvite(bean);
        Disposable disposable = observable
                .compose(RxHelper.<InviteListDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播邀请详细列表
     *
     * @param
     * @param
     */

    public void sevenDayEarnings() {
        RxObserver rxObserver = new RxObserver<SevenDayEarningsBean>() {
            @Override
            public void onHandleSuccess(SevenDayEarningsBean resultBean) {
                userBenefitsView.sevenDayEarnings(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        FeedbackBean bean = new FeedbackBean();
        bean.setToken(UserConfig.getInstance().getToken());
        BaseRequestEntity<FeedbackBean> requestEntity =
                new BaseRequestEntity<FeedbackBean>();
        requestEntity.setData(bean);

        Observable<BaseResponseEntity<SevenDayEarningsBean>> observable = mRxSerivce.sevenDayEarnings(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SevenDayEarningsBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 主播邀请详细列表
     *
     * @param
     * @param page
     */

    public void userPriceList(String page) {
        RxObserver rxObserver = new RxObserver<PriceListBean>() {
            @Override
            public void onHandleSuccess(PriceListBean resultBean) {
                userBenefitsView.userPriceList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        FeedbackBean bean = new FeedbackBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        BaseRequestEntity<FeedbackBean> requestEntity =
                new BaseRequestEntity<FeedbackBean>();
        requestEntity.setData(bean);

        Observable<BaseResponseEntity<PriceListBean>> observable = mRxSerivce.userPriceList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PriceListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 弹出升级弹窗
     */
    public void upgradeAlert() {
        RxObserver rxObserver = new RxObserver<upgradeAlertBean>() {
            @Override
            public void onHandleSuccess(upgradeAlertBean resultBean) {
                userBenefitsView.upgradeAlert(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                userBenefitsView.requestFailed(message);
            }
        };
        AnchorsBean bean = new AnchorsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<upgradeAlertBean>> observable = mRxSerivce.upgradeAlert(bean);
        Disposable disposable = observable
                .compose(RxHelper.<upgradeAlertBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

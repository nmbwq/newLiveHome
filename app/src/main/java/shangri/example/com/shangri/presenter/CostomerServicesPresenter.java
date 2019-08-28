package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.CompanyRequestBean;
import shangri.example.com.shangri.model.bean.request.partSelectRequestBean;
import shangri.example.com.shangri.model.bean.response.AboutOursBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.presenter.view.companyView;
import shangri.example.com.shangri.presenter.view.costomerServiceView;

/**
 * 我的客服
 * Created by pc on 2017/6/27.
 */

public class CostomerServicesPresenter extends BasePresenter<costomerServiceView> {

    private costomerServiceView mSofwwareUserView;

    public CostomerServicesPresenter(Context context, costomerServiceView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 帮助中心
     *
     * @param
     */

    public void vipQaList() {

        RxObserver rxObserver = new RxObserver<RequestListBean>() {
            @Override
            public void onHandleSuccess(RequestListBean resultBean) {
                mSofwwareUserView.vipQaList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        CompanyRequestBean bean = new CompanyRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<RequestListBean>> observable = mRxSerivce.vipQaList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RequestListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 客服电话 & 微信
     *
     * @param
     */

    public void customLink() {

        RxObserver rxObserver = new RxObserver<RequestListBean>() {
            @Override
            public void onHandleSuccess(RequestListBean resultBean) {
                mSofwwareUserView.customLink(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        CompanyRequestBean bean = new CompanyRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<RequestListBean>> observable = mRxSerivce.customLink(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RequestListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 申请成为管理员
     *
     * @param
     */

    public void vipQaUseful(int qa_id, int is_useful) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.vipQaUseful();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        CompanyRequestBean bean = new CompanyRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setQa_id(qa_id);
        bean.setIs_useful(is_useful);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.vipQaUseful(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 申请成为管理员
     *
     * @param
     */

    public void aboutOurs() {

        RxObserver rxObserver = new RxObserver<AboutOursBean>() {
            @Override
            public void onHandleSuccess(AboutOursBean resultBean) {
                mSofwwareUserView.aboutOurs(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        CompanyRequestBean bean = new CompanyRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<AboutOursBean>> observable = mRxSerivce.aboutOurs(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AboutOursBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

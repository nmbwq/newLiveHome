package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AddCompanyBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;
import shangri.example.com.shangri.presenter.view.WelfareView;

public class WelfarePresenter extends BasePresenter<WelfareView> {

    private WelfareView welfareView;

    public WelfarePresenter(Context context, WelfareView view) {
        super(context, view);
        this.welfareView = view;
    }


    public void addCompany(String token,String type) {
        RxObserver rxObserver = new RxObserver<AddCompanyBean>() {
            @Override
            public void onHandleSuccess(AddCompanyBean resultBean) {

                welfareView.addSuccess(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                welfareView.requestFailed(message);
            }
        };

//        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<AddCompanyBean>> observable = mRxSerivce.addCompany(token,type);
        Disposable disposable = observable
                .compose(RxHelper.<AddCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void certification(String token) {
        RxObserver rxObserver = new RxObserver<CertificationBean>() {
            @Override
            public void onHandleSuccess(CertificationBean resultBean) {

                welfareView.certificationSuccess(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                welfareView.requestFailed(message);
            }
        };

//        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<CertificationBean>> observable = mRxSerivce.authentication(token);
        Disposable disposable = observable
                .compose(RxHelper.<CertificationBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长发送招聘公司认证
     *
     * @param
     */
    public void companyAdd(int type) {
        RxObserver rxObserver = new RxObserver<NewCompanyBean>() {

            @Override
            public void onHandleSuccess(NewCompanyBean resultBean) {
                welfareView.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                welfareView.requestFailed(message);
            }
        };
        CompanyAddBean bean = new CompanyAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        Observable<BaseResponseEntity<NewCompanyBean>> observable = mRxSerivce.companyAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * VIP套餐列表
     */
    public void getVIPPackagesList() {
        RxObserver rxObserver = new RxObserver<VIPCardBean>() {
            @Override
            public void onHandleSuccess(VIPCardBean resultBean) {
                welfareView.getVIPPackagesList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                welfareView.requestFailed(message);
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

    public void accountData() {
        RxObserver rxObserver = new RxObserver<AccountDataBean>() {
            @Override
            public void onHandleSuccess(AccountDataBean mAccountDataBean) {
                welfareView.accountData(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                welfareView.requestFailed(message);
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
}

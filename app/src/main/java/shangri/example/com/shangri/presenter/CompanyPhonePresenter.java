package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddPhoneBean;
import shangri.example.com.shangri.model.bean.request.MyAnchorListBean;
import shangri.example.com.shangri.model.bean.request.MyTaskZhuBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ListPhoenBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.presenter.view.CompanyPhoneView;
import shangri.example.com.shangri.presenter.view.MyTaskView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class CompanyPhonePresenter extends BasePresenter<CompanyPhoneView> {

    private CompanyPhoneView mSofwwareUserView;

    public CompanyPhonePresenter(Context context, CompanyPhoneView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 公司电话列表
     *
     * @param company_id
     */

    public void companyContacts(String company_id) {

        RxObserver rxObserver = new RxObserver<ListPhoenBean>() {
            @Override
            public void onHandleSuccess(ListPhoenBean resultBean) {
                mSofwwareUserView.companyContacts(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MyTaskZhuBean bean = new MyTaskZhuBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setCompany_id(company_id);
        Observable<BaseResponseEntity<ListPhoenBean>> observable = mRxSerivce.companyContacts(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ListPhoenBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 添加公司联系人
     *
     * @param company_id
     */

    public void companyAddcontact(String job, String name, String telephone, String company_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.companyAddContacts();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddPhoneBean bean = new AddPhoneBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setJob(job);
        bean.setName(name);
        bean.setTelephone(telephone);
        bean.setCompany_id(company_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyAddContacts(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 删除公司联系人
     *
     * @param company_id
     */

    public void companyDelcontact(String id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.companyAddContacts();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddPhoneBean bean = new AddPhoneBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyDelcontact(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }




}

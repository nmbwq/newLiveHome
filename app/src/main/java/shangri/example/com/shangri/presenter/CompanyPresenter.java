package shangri.example.com.shangri.presenter;

import android.content.Context;

import com.github.mikephil.charting.formatter.IFillFormatter;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddPhoneBean;
import shangri.example.com.shangri.model.bean.request.AnchorChectRequestBean;
import shangri.example.com.shangri.model.bean.request.CompanyRequestBean;
import shangri.example.com.shangri.model.bean.request.ManagerChectRequestBean;
import shangri.example.com.shangri.model.bean.request.PatrolBean;
import shangri.example.com.shangri.model.bean.request.partSelectRequestBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.presenter.view.anchorChectView;
import shangri.example.com.shangri.presenter.view.companyView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class CompanyPresenter extends BasePresenter<companyView> {

    private companyView mSofwwareUserView;

    public CompanyPresenter(Context context, companyView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 管理员申请公会 公司列表
     *
     * @param
     */

    public void companySearch(String search) {

        RxObserver rxObserver = new RxObserver<companyListBean>() {
            @Override
            public void onHandleSuccess(companyListBean resultBean) {
                mSofwwareUserView.companySearch(resultBean);
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
        bean.setSearch(search);
        Observable<BaseResponseEntity<companyListBean>> observable = mRxSerivce.companySearch(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司详情
     *
     * @param
     */

    public void companyDetail(String company_id) {

        RxObserver rxObserver = new RxObserver<companyDetailBean>() {
            @Override
            public void onHandleSuccess(companyDetailBean resultBean) {
                mSofwwareUserView.companyDetail(resultBean);
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
        bean.setCompany_id(company_id);
        Observable<BaseResponseEntity<companyDetailBean>> observable = mRxSerivce.companyDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 申请成为管理员
     *
     * @param
     */

    public void adminApply(String deacon_id, String guild_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.adminApply();
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
        bean.setDeacon_id(deacon_id);
        bean.setGuild_id(guild_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.adminApply(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 执行人搜索
     *
     * @param
     */

    public void partSelect(String task_id, String search) {

        RxObserver rxObserver = new RxObserver<partSelectBean>() {
            @Override
            public void onHandleSuccess(partSelectBean resultBean) {
                mSofwwareUserView.partSelect(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        partSelectRequestBean bean = new partSelectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setSearch(search);
        bean.setTask_id(task_id);
        Observable<BaseResponseEntity<partSelectBean>> observable = mRxSerivce.partSelect(bean);
        Disposable disposable = observable
                .compose(RxHelper.<partSelectBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司搜索人
     *
     * @param
     */

    public void personalSearch(String search) {

        RxObserver rxObserver = new RxObserver<personalSearchBean>() {
            @Override
            public void onHandleSuccess(personalSearchBean resultBean) {
                mSofwwareUserView.personalSearch(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        partSelectRequestBean bean = new partSelectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setSearch(search);
        Observable<BaseResponseEntity<personalSearchBean>> observable = mRxSerivce.personalSearch(bean);
        Disposable disposable = observable
                .compose(RxHelper.<personalSearchBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司搜索人
     *
     * @param
     */

    public void adminSelect(String search) {

        RxObserver rxObserver = new RxObserver<SearchBean>() {
            @Override
            public void onHandleSuccess(SearchBean resultBean) {
                mSofwwareUserView.adminSelect(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        partSelectRequestBean bean = new partSelectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (search.length() > 0) {
            bean.setSearch(search);
        }

        Observable<BaseResponseEntity<SearchBean>> observable = mRxSerivce.adminSelect(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SearchBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

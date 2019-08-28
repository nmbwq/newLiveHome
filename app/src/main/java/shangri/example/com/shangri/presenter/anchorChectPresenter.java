package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddPhoneBean;
import shangri.example.com.shangri.model.bean.request.AnchorChectRequestBean;
import shangri.example.com.shangri.model.bean.request.AnchorOrder;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.ManagerChectRequestBean;
import shangri.example.com.shangri.model.bean.request.MyTaskZhuBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.ListPhoenBean;
import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.presenter.view.anchorChectView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class anchorChectPresenter extends BasePresenter<anchorChectView> {

    private anchorChectView mSofwwareUserView;

    public anchorChectPresenter(Context context, anchorChectView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 主播申请列表
     *
     * @param
     */

    public void anchorApplys() {

        RxObserver rxObserver = new RxObserver<anchorChectBean>() {
            @Override
            public void onHandleSuccess(anchorChectBean resultBean) {
                mSofwwareUserView.anchorApplys(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorChectRequestBean bean = new AnchorChectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<anchorChectBean>> observable = mRxSerivce.anchorApplys(bean);
        Disposable disposable = observable
                .compose(RxHelper.<anchorChectBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }





    /**
     * 管理员申请列表
     *
     * @param
     */

    public void managerApplys() {

        RxObserver rxObserver = new RxObserver<ManagerChectBean>() {
            @Override
            public void onHandleSuccess(ManagerChectBean resultBean) {
                mSofwwareUserView.managerApplys(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorChectRequestBean bean = new AnchorChectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ManagerChectBean>> observable = mRxSerivce.managerApplys(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ManagerChectBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播审核
     *
     * @param
     */

    public void anchorCheck(String register_guild_id, String check_status, String check_mark) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.anchorCheck();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorChectRequestBean bean = new AnchorChectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (check_mark.length() > 0) {
            bean.setCheck_mark(check_mark);
        }

        bean.setRegister_guild_id(register_guild_id + "");
        bean.setCheck_status(check_status);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorCheck(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * @param
     */

    public void ManagerCheck(String admin_reg_id, String guild_id, String status, String mask) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.managerCheck();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ManagerChectRequestBean bean = new ManagerChectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (mask.length() > 0) {
            bean.setMask(mask);
        }
        bean.setAdmin_reg_id(admin_reg_id);
        bean.setGuild_id(guild_id);
        bean.setStatus(status);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.managerCheck(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * @param查看联系我的公司
     */

    public void viewCompany(String page) {

        RxObserver rxObserver = new RxObserver<LookMeCompanyBean>() {
            @Override
            public void onHandleSuccess(LookMeCompanyBean resultBean) {
                mSofwwareUserView.viewCompany(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ManagerChectRequestBean bean = new ManagerChectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);

        Observable<BaseResponseEntity<LookMeCompanyBean>> observable = mRxSerivce.viewCompany(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LookMeCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }





}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.CreatCompanyBean;
import shangri.example.com.shangri.model.bean.request.ManagementBean;
import shangri.example.com.shangri.model.bean.request.RequestLeaveBean;
import shangri.example.com.shangri.model.bean.request.SofwwareUserPresenterBeen;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.companyAdminListBean;
import shangri.example.com.shangri.model.bean.response.companyBreaklistListBean;
import shangri.example.com.shangri.model.bean.response.companyOrgBean;
import shangri.example.com.shangri.presenter.view.CreatCompanyView;
import shangri.example.com.shangri.presenter.view.SettingView;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.KeytUtil;

/**
 * 设置
 * Created by chengaofu on 2017/7/1.
 */

public class CreatPresenter extends BasePresenter<CreatCompanyView> {

    private CreatCompanyView mSettingView;

    public CreatPresenter(Context context, CreatCompanyView view) {
        super(context, view);
        mSettingView = view;
    }

    /**
     * 创建公司
     *
     * @param imagePath
     * @param name
     * @param company_scale
     * @param location
     */
    public void sofwwareUser(String imagePath, String name, String company_scale, String location) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object mObject) {

            }

            @Override
            public void onHandleComplete() {
                mSettingView.Create();
            }

            @Override
            public void onHandleFailed(String message) {
                mSettingView.requestFailed(message);
            }
        };
        CreatCompanyBean bean = new CreatCompanyBean();
        if (imagePath.length() != 0) {
            try {
                bean.setLogo(Base64Utils.encodeFile(imagePath) + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bean.setName(name);
        bean.setCompany_scale(company_scale);
        bean.setLocation(location);
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.CreatCompany(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 修改公司
     *
     * @param imagePath
     * @param name
     * @param company_scale
     * @param location
     */
    public void companyUpdate(String id, String imagePath, String name, String company_scale, String location) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object mObject) {

            }

            @Override
            public void onHandleComplete() {
                mSettingView.update();
            }

            @Override
            public void onHandleFailed(String message) {
                mSettingView.requestFailed(message);
            }
        };
        CreatCompanyBean bean = new CreatCompanyBean();
        if (imagePath.length() != 0) {
            try {
                bean.setLogo(Base64Utils.encodeFile(imagePath) + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bean.setName(name);
        bean.setCompany_scale(company_scale);
        bean.setLocation(location);
        bean.setCompany_id(id);
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyUpdate(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司组织架构
     */

    public void companyOrg() {

        RxObserver rxObserver = new RxObserver<companyOrgBean>() {
            @Override
            public void onHandleSuccess(companyOrgBean resultBean) {
                mSettingView.company(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyOrgBean>> observable = mRxSerivce.companyOrg(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyOrgBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司管理员列表
     */
    public void companyAdmins() {

        RxObserver rxObserver = new RxObserver<companyAdminListBean>() {
            @Override
            public void onHandleSuccess(companyAdminListBean resultBean) {
                mSettingView.adminsList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyAdminListBean>> observable = mRxSerivce.companyAdmins(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyAdminListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司离职列表
     */
    public void companyBreaklist() {

        RxObserver rxObserver = new RxObserver<companyBreaklistListBean>() {
            @Override
            public void onHandleSuccess(companyBreaklistListBean resultBean) {
                mSettingView.breaklist(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyBreaklistListBean>> observable = mRxSerivce.companyBreaklist(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyBreaklistListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 公司离职列表
     */
    public void companyBreakanchor(String guild_id, String anchor_string) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSettingView.AnchorLeave();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        RequestLeaveBean bean = new RequestLeaveBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setAnchor_string(anchor_string);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyBreakanchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 管理员离职
     */
    public void companyLizhi(String admin_reg_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSettingView.companyLizhi();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        RequestLeaveBean bean = new RequestLeaveBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setAdmin_reg_id(admin_reg_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyLizhi(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 管理员离职
     */
    public void companyJiebang(String guild_id, String admin_reg_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSettingView.companyJiebang();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        RequestLeaveBean bean = new RequestLeaveBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setAdmin_reg_id(admin_reg_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.companyJiebang(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 管理员离职主播
     */
    public void adminBreakanchor(String guild_id, String anchor_string) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSettingView.adminBreakanchor();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        RequestLeaveBean bean = new RequestLeaveBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setAnchor_string(anchor_string);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.adminBreakanchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 管理员添加
     */
    public void adminAddanchor(String guild_id, String anchor_string) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSettingView.adminAddanchors();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        RequestLeaveBean bean = new RequestLeaveBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setAnchor_string(anchor_string);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.adminAddanchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

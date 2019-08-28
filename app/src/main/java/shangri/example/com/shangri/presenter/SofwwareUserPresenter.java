package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddInfo;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.SofwwareUserPresenterBeen;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.SoftwareUserView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class SofwwareUserPresenter extends BasePresenter<SoftwareUserView> {

    private SoftwareUserView mSofwwareUserView;
    public SofwwareUserPresenter(Context context, SoftwareUserView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void sofwwareUser(String role){
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object mObject) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.softwareUser();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        SofwwareUserPresenterBeen bean = new SofwwareUserPresenterBeen();
        bean.setRole(role);
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.getRole(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //添加资料
    public void addInfo(String role, String nickname, String sex, String platfrom_name, String guild_name,
                        String tag_ids, String tags_content, String cover_url, String anchor_content) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.addInfo("");
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddInfo bean = new AddInfo();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRole(role);
//        bean.setNickname(nickname);
//        bean.setSex(sex);
//        bean.setPlatfrom_name(platfrom_name);
//        bean.setGuild_name(guild_name);
//        bean.setTag_ids(tag_ids);
////        bean.setTags_content(tags_content);
////        bean.setCover_url("");
//        bean.setAnchor_content(anchor_content);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
    /**
     * 更新最后一次登陆时间
     */

    public void memberLogin() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
//                mLoginUserView.memberLogin();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ResetPwdBean mResetPwdBean = new ResetPwdBean();
        mResetPwdBean.setToken(UserConfig.getInstance().getToken());
        mResetPwdBean.setFrom("android");
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.memberLogin(mResetPwdBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }



}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import com.github.mikephil.charting.formatter.IFillFormatter;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AnchorsBean;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.GivesGetBean;
import shangri.example.com.shangri.model.bean.request.OncilckBottomBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ChoiceAnchorsBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;
import shangri.example.com.shangri.presenter.view.ChoiceAnchorsView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class ChoiceAnchorsPresenter extends BasePresenter<ChoiceAnchorsView> {

    private ChoiceAnchorsView mSofwwareUserView;

    public ChoiceAnchorsPresenter(Context context, ChoiceAnchorsView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void getAnchorsList(String guildid, String page, String search) {
        RxObserver rxObserver = new RxObserver<ChoiceAnchorsBean>() {
            @Override
            public void onHandleSuccess(ChoiceAnchorsBean resultBean) {
                mSofwwareUserView.listAnchorsData(resultBean);
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorsBean bean = new AnchorsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guildid);
        bean.setPage(page);
        bean.setSearch(search);

        Observable<BaseResponseEntity<ChoiceAnchorsBean>> observable = mRxSerivce.getanchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChoiceAnchorsBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 获取未弹出的通知
     */
    public void noticePop() {
        RxObserver rxObserver = new RxObserver<NoticesResponseBean>() {
            @Override
            public void onHandleSuccess(NoticesResponseBean resultBean) {
                mSofwwareUserView.noticePop(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorsBean bean = new AnchorsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<NoticesResponseBean>> observable = mRxSerivce.noticePop(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NoticesResponseBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 新消息未读数量
     */
    public void myNoread() {
        RxObserver rxObserver = new RxObserver<NoticesResponseBean>() {
            @Override
            public void onHandleSuccess(NoticesResponseBean resultBean) {
                mSofwwareUserView.myNoread(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorsBean bean = new AnchorsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<NoticesResponseBean>> observable = mRxSerivce.myNoread(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NoticesResponseBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 弹出福利推送提示
     */
    public void invitationAlert(String alert) {
        RxObserver rxObserver = new RxObserver<NoticesResponseBean>() {
            @Override
            public void onHandleSuccess(NoticesResponseBean resultBean) {
                mSofwwareUserView.invitationAlert(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AnchorsBean bean = new AnchorsBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (alert.length() > 0) {
            bean.setAlert(alert);
        }
        Observable<BaseResponseEntity<NoticesResponseBean>> observable = mRxSerivce.invitationAlert(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NoticesResponseBean>handleObservableResult())
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
                mSofwwareUserView.upgradeAlert(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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


    /**
     * 登录是否弹出赠送好礼
     */
    public void popGives() {
        RxObserver rxObserver = new RxObserver<upgradeAlertBean>() {
            @Override
            public void onHandleSuccess(upgradeAlertBean resultBean) {
                mSofwwareUserView.popGives(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BaseBeen bean = new BaseBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<upgradeAlertBean>> observable = mRxSerivce.popGives(bean);
        Disposable disposable = observable
                .compose(RxHelper.<upgradeAlertBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 领取登陆福利
     */
    public void givesGet(String type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.givesGet();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        GivesGetBean bean = new GivesGetBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.givesGet(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 更新底导点击次数接口  底导 1招聘 2看看 3报表 4我的
     */
    public void oncilckBottom(String type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.oncilckBottom();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        OncilckBottomBean bean = new OncilckBottomBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        bean.setFrom("android");
        bean.setType(type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.oncilckBottom(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * alertSeting
     */
    public void alertSeting() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        OncilckBottomBean bean = new OncilckBottomBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.alertSeting(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.view.ResumeManageView;

/**
 * Description:
 * Data：2018/11/6-10:27
 * Author: lin
 */
public class ResumeManagePresent extends BasePresenter<ResumeManageView> {
    ResumeManageView resumeManageView;

    public ResumeManagePresent(Context context, ResumeManageView view) {
        super(context, view);
        resumeManageView = view;
    }

    /**
     * 主播投递接口
     */
    public void sendResume() {
        RxObserver rxObserver = new RxObserver<sendResumeBean>() {
            @Override
            public void onHandleSuccess(sendResumeBean resultBean) {
                resumeManageView.sendResume(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                resumeManageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<sendResumeBean>> observable = mRxSerivce.sendResume(bean);
        Disposable disposable = observable
                .compose(RxHelper.<sendResumeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 沟通过
     */
    public void upList() {
        RxObserver rxObserver = new RxObserver<upListBean>() {
            @Override
            public void onHandleSuccess(upListBean resultBean) {
                resumeManageView.upList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                resumeManageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<upListBean>> observable = mRxSerivce.upList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<upListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 会长拨打电话、留电话列表接口
     */
    public void getGuildList(int type) {
        RxObserver rxObserver = new RxObserver<WelfareGuildBean>() {
            @Override
            public void onHandleSuccess(WelfareGuildBean resultBean) {
                resumeManageView.getGuildList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                resumeManageView.requestFailed(message);
            }
        };

        Observable<BaseResponseEntity<WelfareGuildBean>> observable = mRxSerivce.getGuildList(UserConfig.getInstance().getToken(), type + "");
        Disposable disposable = observable
                .compose(RxHelper.<WelfareGuildBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 抢ta
     */
    public void gradeList() {
        RxObserver rxObserver = new RxObserver<WelfareGuildBean>() {
            @Override
            public void onHandleSuccess(WelfareGuildBean resultBean) {
                resumeManageView.gradeList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                resumeManageView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());

        Observable<BaseResponseEntity<WelfareGuildBean>> observable = mRxSerivce.gradeList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<WelfareGuildBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

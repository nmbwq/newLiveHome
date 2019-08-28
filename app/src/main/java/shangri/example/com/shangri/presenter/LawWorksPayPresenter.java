package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.view.LowWorksPayView;
import shangri.example.com.shangri.presenter.view.LowWorksView;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class LawWorksPayPresenter extends BasePresenter<LowWorksPayView> {

    private LowWorksPayView mLoginUserView;

    public LawWorksPayPresenter(Context context, LowWorksPayView view) {
        super(context, view);
        mLoginUserView = view;

    }

    public void legalmyPackage() {
        RxObserver rxObserver = new RxObserver<legalMypagerBean>() {
            @Override
            public void onHandleSuccess(legalMypagerBean resultBean) {
                mLoginUserView.legalmyPackage(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<legalMypagerBean>> observable = mRxSerivce.legalmyPackage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<legalMypagerBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 不考虑职位列表
     */
    public void noLikeList(String page) {
        RxObserver rxObserver = new RxObserver<BossDataBean>() {
            @Override
            public void onHandleSuccess(BossDataBean resultBean) {
                mLoginUserView.noLikeList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.noLikeList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 职位列表接口
     */

    public void positionList(String status, String page) {
        RxObserver rxObserver = new RxObserver<PositionListBean>() {
            @Override
            public void onHandleSuccess(PositionListBean resultBean) {
                mLoginUserView.positionList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setStatus(status);
        bean.setPage(page);
        Observable<BaseResponseEntity<PositionListBean>> observable = mRxSerivce.positionList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PositionListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播投递接口
     */
    public void sendResume() {
        RxObserver rxObserver = new RxObserver<sendResumeBean>() {
            @Override
            public void onHandleSuccess(sendResumeBean resultBean) {
                mLoginUserView.sendResume(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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
     * 主播投递接口
     */
    public void upList() {
        RxObserver rxObserver = new RxObserver<upListBean>() {
            @Override
            public void onHandleSuccess(upListBean resultBean) {
                mLoginUserView.upList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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
     * 主播投递接口
     */
    public void anchorDetail(String recruit_id) {
        RxObserver rxObserver = new RxObserver<anchorDetailBean>() {
            @Override
            public void onHandleSuccess(anchorDetailBean resultBean) {
                mLoginUserView.anchorDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<anchorDetailBean>> observable = mRxSerivce.anchorDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<anchorDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播投递接口
     */
    public void upAnchor(String anchor_id, String recruit_id, String resume_message_id, String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.upAnchor();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        bean.setAnchor_id(anchor_id);
        bean.setResume_id(resume_id);
        bean.setResume_message_id(resume_message_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.upAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播投递接口
     */
    public void whetherCheck(String anchor_id, String recruit_id, String resume_message_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.upAnchor();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        bean.setAnchor_id(anchor_id);
        bean.setResume_message_id(resume_message_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.whetherCheck(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
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
                mLoginUserView.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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
     * 会长剩余发布职位数接口
     */

    public void limitNumber() {
        RxObserver rxObserver = new RxObserver<IsFaceResponse>() {
            @Override
            public void onHandleSuccess(IsFaceResponse resultBean) {
                mLoginUserView.limitNumber(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<IsFaceResponse>> observable = mRxSerivce.limitNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.<IsFaceResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

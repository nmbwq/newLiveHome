package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.presenter.view.LowWorksView;
import shangri.example.com.shangri.util.MD5Util;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class LawWorksPresenter extends BasePresenter<LowWorksView> {

    private LowWorksView mLoginUserView;

    public LawWorksPresenter(Context context, LowWorksView view) {
        super(context, view);
        mLoginUserView = view;

    }

    public void legalIndex() {
        RxObserver rxObserver = new RxObserver<legalIndexBean>() {
            @Override
            public void onHandleSuccess(legalIndexBean resultBean) {
                mLoginUserView.legalIndex(resultBean);
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
        Observable<BaseResponseEntity<legalIndexBean>> observable = mRxSerivce.legalIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<legalIndexBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 免责协议
     */
    public void legalProtocol() {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mLoginUserView.legalProtocol(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        IdIdentBean bean = new IdIdentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.legalProtocol(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 签署流程返回地址
     */
    public void signProcess() {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mLoginUserView.signProcess(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        IdIdentBean bean = new IdIdentBean();
        if (UserConfig.getInstance().getRole().equals("1")) {
            bean.setType(1);
        } else {
            bean.setType(0);
        }
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.signProcess(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

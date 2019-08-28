package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AnchorsBean;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.RequestLeaveBean;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.positionAddBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.presenter.view.ResumeView;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class ChairmanRelesePresenter extends BasePresenter<ChairmanReleseView> {

    private ChairmanReleseView mLoginUserView;

    public ChairmanRelesePresenter(Context context, ChairmanReleseView view) {
        super(context, view);
        mLoginUserView = view;
    }

    /**
     * 会长发送招聘公司认证
     *
     * @param
     */
    public void companyAdd(String logo, String company_scale, String anchor_scale, int type, String company_name, String telephone, String company_description) {
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

        if (logo.length() > 0) {
            try {
                bean.setLogo(Base64Utils.encodeFile(logo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (company_scale.length() > 0) {
            bean.setCompany_scale(company_scale);
        }
        if (anchor_scale.length() > 0) {
            bean.setAnchor_scale(anchor_scale);
        }

        if (company_name.length() > 0) {
            bean.setCompany_name(company_name);
        }
        if (telephone.length() > 0) {
            bean.setTelephone(telephone);
        }
        if (company_description.length() > 0) {
            bean.setCompany_description(company_description);
        }
        Observable<BaseResponseEntity<NewCompanyBean>> observable = mRxSerivce.companyAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 获取福利列表
     */
    public void welfareList() {
        RxObserver rxObserver = new RxObserver<welfareListPlatBean>() {
            @Override
            public void onHandleSuccess(welfareListPlatBean mAccountDataBean) {
                mLoginUserView.welfareList(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<welfareListPlatBean>> observable = mRxSerivce.welfareList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<welfareListPlatBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 获取职位亮点
     */
    public void changeLightspot() {
        RxObserver rxObserver = new RxObserver<changeLightspotBean>() {
            @Override
            public void onHandleSuccess(changeLightspotBean mAccountDataBean) {
                mLoginUserView.changeLightspot(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<changeLightspotBean>> observable = mRxSerivce.changeLightspot(bean);
        Disposable disposable = observable
                .compose(RxHelper.<changeLightspotBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void platfromType() {
        RxObserver rxObserver = new RxObserver<BossPlatBean>() {
            @Override
            public void onHandleSuccess(BossPlatBean mAccountDataBean) {
                mLoginUserView.platfromType(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<BossPlatBean>> observable = mRxSerivce.platfromType(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossPlatBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 发布职位接口
     */
    public void jobAdd(positionAddBean bean) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.jobAdd();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.jobAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 发布职位接口
     */
    public void updatePosition(positionAddBean bean) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.updatePosition();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.updatePosition(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 判断是否认证
     */

    public void legalIsface() {
        RxObserver rxObserver = new RxObserver<IsFaceResponse>() {
            @Override
            public void onHandleSuccess(IsFaceResponse resultBean) {
                mLoginUserView.legalIsface(resultBean);

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
        Observable<BaseResponseEntity<IsFaceResponse>> observable = mRxSerivce.legalIsface(bean);
        Disposable disposable = observable
                .compose(RxHelper.<IsFaceResponse>handleObservableResult())
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

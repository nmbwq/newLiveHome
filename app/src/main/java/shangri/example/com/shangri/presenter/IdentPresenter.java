package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddTaskBean;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.ParticipateTaskBean;
import shangri.example.com.shangri.model.bean.request.UpdateTaskBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.presenter.view.AddTaskView;
import shangri.example.com.shangri.presenter.view.IdentView;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class IdentPresenter extends BasePresenter<IdentView> {

    private IdentView mSofwwareUserView;

    public IdentPresenter(Context context, IdentView view) {
        super(context, view);
        mSofwwareUserView = view;
    }


    /**
     * 获取公司香茗
     *
     * @param
     */
    public void companyAdd(int type) {
        RxObserver rxObserver = new RxObserver<NewCompanyBean>() {

            @Override
            public void onHandleSuccess(NewCompanyBean resultBean) {
                mSofwwareUserView.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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
     * 获取相似度
     */
    public void licenceDiscern(String company_name, String codeUSC, String license_photo) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.licenceDiscern();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setCompany_name(company_name);
        bean.setCodeUSC(codeUSC);
        try {
            bean.setLicense_photo(Base64Utils.encodeFile(license_photo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.licenceDiscern(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 营业执照认证存储
     */
    public void faceContrast(String live_img, String icd_img) {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mSofwwareUserView.faceContrast(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setLive_img(live_img);
        bean.setIcd_img(icd_img);
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.faceContrast(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 验证身份证和姓名是否一致
     */
    public void authIdcard(String name, String id_num, String legal_photo) {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mSofwwareUserView.authIdcard(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setName(name);
        bean.setId_num(id_num);
        bean.setId_img(legal_photo);
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.authIdcard(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 人脸认证剩余次数
     */
    public void face_num() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.face_num();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.face_num(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 企业认证剩余次数
     */
    public void company_num() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.company_num();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.company_num(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 识别存储
     */
    public void faceDiscern(IdIdentBean bean) {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mSofwwareUserView.faceDiscern(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.faceDiscern(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 识别存储
     */
    public void legalUploading(String imageurl) {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                mSofwwareUserView.legalUploading(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        IdIdentBean bean = new IdIdentBean();
        bean.setToken(UserConfig.getInstance().getToken()+"");
        bean.setImage(imageurl);
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.legalUploading(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

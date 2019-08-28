package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.ResumeDelBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.wantedStatusBean;
import shangri.example.com.shangri.presenter.view.LowWorksView;
import shangri.example.com.shangri.presenter.view.ResumeView;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class ResumePresenter extends BasePresenter<ResumeView> {

    private ResumeView mLoginUserView;

    public ResumePresenter(Context context, ResumeView view) {
        super(context, view);
        mLoginUserView = view;

    }

    public void resumeUploading(int place, String image, int capture, String resume_id) {
        RxObserver rxObserver = new RxObserver<legalIndexBean>() {
            @Override
            public void onHandleSuccess(legalIndexBean resultBean) {
                mLoginUserView.resumeUploading(resultBean);
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
        if (resume_id.length() > 0) {
            bean.setResume_id(resume_id);
        } else {
            bean.setResume_id("0");
        }
        bean.setPlace(place);
        bean.setCapture(capture);
        try {
            bean.setImage(Base64Utils.encodeFile(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable<BaseResponseEntity<legalIndexBean>> observable = mRxSerivce.resumeUploading(bean);
        Disposable disposable = observable
                .compose(RxHelper.<legalIndexBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 上传简历
     *
     * @param bean
     */
    public void resumeAdd(ResumeAddBean bean) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mLoginUserView.resumeAdd();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumeAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 简历照片删除
     *
     * @param
     */
    public void resumePhotoDel(int place) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mLoginUserView.resumePhotoDel();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };

        ResumeDelBean bean = new ResumeDelBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlace(place);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumePhotoDel(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 简历照片删除
     *
     * @param
     */
    public void resumePhotoFirst(int place) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mLoginUserView.resumePhotoFirst();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResumeDelBean bean = new ResumeDelBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlace(place);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumePhotoFirst(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 编辑简历
     *
     * @param bean
     */
    public void resumeUpdate(ResumeAddBean bean) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mLoginUserView.resumeUpdate();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumeUpdate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取简历图片接口
     */

    public void readPhoto() {
        RxObserver rxObserver = new RxObserver<ReadPhotoBean>() {
            @Override
            public void onHandleSuccess(ReadPhotoBean resultBean) {
                mLoginUserView.readPhoto(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ReadPhotoBean>> observable = mRxSerivce.readPhoto(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ReadPhotoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void resumeIndex() {
        RxObserver rxObserver = new RxObserver<ResumeIndexBean>() {
            @Override
            public void onHandleSuccess(ResumeIndexBean resultBean) {
                mLoginUserView.resumeIndex(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ResumeIndexBean>> observable = mRxSerivce.resumeIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeIndexBean>handleObservableResult())
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

//    求职状态接口

    public void wantedStatus() {
        RxObserver rxObserver = new RxObserver<wantedStatusBean>() {
            @Override
            public void onHandleSuccess(wantedStatusBean mAccountDataBean) {
                mLoginUserView.wantedStatus(mAccountDataBean);
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
        Observable<BaseResponseEntity<wantedStatusBean>> observable = mRxSerivce.wantedStatus(bean);
        Disposable disposable = observable
                .compose(RxHelper.<wantedStatusBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


}

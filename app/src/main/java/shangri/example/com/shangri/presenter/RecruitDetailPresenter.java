package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.PayrequestBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.presenter.view.RecruitDetailView;

/**
 * Description:
 * Data：2018/11/4-13:10
 * Author: lin
 */
public class RecruitDetailPresenter extends BasePresenter<RecruitDetailView> {
    RecruitDetailView detailView;
    public RecruitDetailPresenter(Context context, RecruitDetailView view) {
        super(context, view);
        detailView = view;
    }

    /**
     * 获取招聘详情
     * @param id
     */
    public void getRecruitDetail(String id){
        RxObserver rxObserver = new RxObserver<RecruitDetailBean>() {
            @Override
            public void onHandleSuccess(RecruitDetailBean resultBean) {
                detailView.getRecruitDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                detailView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(id);
        Observable<BaseResponseEntity<RecruitDetailBean>> observable = mRxSerivce.getRecruitDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RecruitDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
    /**
     * 获取招聘详情
     * @param id
     */
    public void getResumeDetailBean(String id){
        RxObserver rxObserver = new RxObserver<ResumeDetailBean>() {
            @Override
            public void onHandleSuccess(ResumeDetailBean resultBean) {
                detailView.getResumeDetailBean(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                detailView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
//        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(id);
        Observable<BaseResponseEntity<ResumeDetailBean>> observable = mRxSerivce.getResumeDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

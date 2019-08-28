package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.MyAnchorListBean;
import shangri.example.com.shangri.model.bean.request.MyTaskZhuBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.MyTaskView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class MyTaskPresenter extends BasePresenter<MyTaskView> {

    private MyTaskView mSofwwareUserView;

    public MyTaskPresenter(Context context, MyTaskView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void myTaskList(int currPage, String register_id, String guild_id) {

        RxObserver rxObserver = new RxObserver<MyTaskBean>() {
            @Override
            public void onHandleSuccess(MyTaskBean resultBean) {
                mSofwwareUserView.mvpExpleme(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MyTaskZhuBean bean = new MyTaskZhuBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRegister_id(register_id);
        bean.setGuild_id(guild_id);

        Observable<BaseResponseEntity<MyTaskBean>> observable = mRxSerivce.myTaskList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MyTaskBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 我的主播列表
     */
    public void myAnchorList() {

        RxObserver rxObserver = new RxObserver<MyAnchorListBeanResponse1>() {
            @Override
            public void onHandleSuccess(MyAnchorListBeanResponse1 resultBean) {
                mSofwwareUserView.AnchorList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MyAnchorListBean bean = new MyAnchorListBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<MyAnchorListBeanResponse1>> observable = mRxSerivce.anchorManage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MyAnchorListBeanResponse1>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 判断是否过期
     */
    public void memberLate() {
        RxObserver rxObserver = new RxObserver<timeBean>() {
            @Override
            public void onHandleSuccess(timeBean resultBean) {
                mSofwwareUserView.memberLate(resultBean);
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
        Observable<BaseResponseEntity<timeBean>> observable = mRxSerivce.memberLate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<timeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

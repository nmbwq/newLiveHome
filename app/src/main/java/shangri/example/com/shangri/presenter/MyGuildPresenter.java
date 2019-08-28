package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.JoinGuildBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.MyGuildView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class MyGuildPresenter extends BasePresenter<MyGuildView> {

    private MyGuildView mSofwwareUserView;

    public MyGuildPresenter(Context context, MyGuildView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void requestListData() {
        RxObserver rxObserver = new RxObserver<MyGuildListDataBean>() {
            @Override
            public void onHandleSuccess(MyGuildListDataBean resultBean) {
                mSofwwareUserView.myGuildDtaList(resultBean);
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
        BaseBeen bean = new BaseBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<MyGuildListDataBean>> observable = mRxSerivce.getGuildList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MyGuildListDataBean>handleObservableResult())
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

    /**
     * 修改公会比率
     */
    public void guildRatio(String guild_id, String ratio) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.guildRatio();
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
        bean.setGuild_id(guild_id);
        bean.setRatio(ratio);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.guildRatio(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //查看支持平台接口
    public void supportPlatfrom() {
        RxObserver rxObserver = new RxObserver<SupportFromList>() {
            @Override
            public void onHandleSuccess(SupportFromList resultBean) {
                mSofwwareUserView.SupportFromList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<SupportFromList>> observable = mRxSerivce.supportPlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SupportFromList>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }



}

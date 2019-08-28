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
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.ParticipateTaskBean;
import shangri.example.com.shangri.model.bean.request.UpdateTaskBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;
import shangri.example.com.shangri.presenter.view.NewAnchorView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class NewAnchorPresenter extends BasePresenter<NewAnchorView> {

    private NewAnchorView mSofwwareUserView;

    public NewAnchorPresenter(Context context, NewAnchorView view) {
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
     * 删除
     */
    public void anchorDelete(String guild_id, String anchor_uid) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.anchorDelete();
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
        bean.setAnchor_uid(anchor_uid);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorDelete(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 删除
     */
    public void mineAnchorDelete(String anchor_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.anchorDelete();
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
        bean.setAnchor_id(anchor_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.mineAnchorDelete(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 添加主播
     */
    public void anchorAdd(String guild_id, String anchor_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.anchorAdd();
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
        bean.setAnchor_id(anchor_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取任务详情
     */
    public void anchorList(String type, String guild_id, String table_flag, String page) {
        RxObserver rxObserver = new RxObserver<NewAndroidListDataBean>() {
            @Override
            public void onHandleSuccess(NewAndroidListDataBean resultBean) {
                mSofwwareUserView.anchorList(resultBean);
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
        bean.setType(type);
        bean.setPage(page);
        bean.setGuild_id(guild_id);
        if (type.equals("2")) {
            bean.setTable_flag(table_flag);
        }
        Observable<BaseResponseEntity<NewAndroidListDataBean>> observable = mRxSerivce.anchorList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewAndroidListDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播绑定列表接口
     */
    public void mineAnchorList() {
        RxObserver rxObserver = new RxObserver<mineAnchorListDataBean>() {
            @Override
            public void onHandleSuccess(mineAnchorListDataBean resultBean) {
                mSofwwareUserView.mineAnchorList(resultBean);
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
        Observable<BaseResponseEntity<mineAnchorListDataBean>> observable = mRxSerivce.mineAnchorList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<mineAnchorListDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

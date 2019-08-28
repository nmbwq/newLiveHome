package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.SelectGuildBean;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.presenter.view.GuildListView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class GuildListPresenter extends BasePresenter<GuildListView> {

    private GuildListView mSofwwareUserView;
    public GuildListPresenter(Context context, GuildListView view) {
        super(context, view);
        mSofwwareUserView = view;
    }
    //绑定公会
    public void getGuildList(){
        RxObserver rxObserver = new RxObserver<SortModelBean>() {
            @Override
            public void onHandleSuccess(SortModelBean resultBean) {
                mSofwwareUserView.listGuildData(resultBean);
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
        Observable<BaseResponseEntity<SortModelBean>> observable = mRxSerivce.getBindingListData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SortModelBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //快速绑定公会
    public void quickPlatfrom(){
        RxObserver rxObserver = new RxObserver<SortModelBean>() {
            @Override
            public void onHandleSuccess(SortModelBean resultBean) {
                mSofwwareUserView.listGuildData(resultBean);
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
        Observable<BaseResponseEntity<SortModelBean>> observable = mRxSerivce.quickPlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SortModelBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //选择公会
    public void selectGuild(String perview) {
        RxObserver rxObserver = new RxObserver<SelectGuildBean>() {
            @Override
            public void onHandleSuccess(SelectGuildBean resultBean) {
                mSofwwareUserView.selectGuild(resultBean);
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
        if (perview.length()>0){
            bean.setPerview(perview);
        }
        Observable<BaseResponseEntity<SelectGuildBean>> observable = mRxSerivce.selectGuild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SelectGuildBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

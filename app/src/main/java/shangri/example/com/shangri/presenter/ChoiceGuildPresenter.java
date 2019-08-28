package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.presenter.view.ChoiceGuildView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class ChoiceGuildPresenter extends BasePresenter<ChoiceGuildView> {

    private ChoiceGuildView mSofwwareUserView;
    public ChoiceGuildPresenter(Context context, ChoiceGuildView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void getGuildList(){
        RxObserver rxObserver = new RxObserver<ChoiceGuildBean>() {
            @Override
            public void onHandleSuccess(ChoiceGuildBean resultBean) {
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
        MyGuildBean bean = new MyGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPurview("self");
        Observable<BaseResponseEntity<ChoiceGuildBean>> observable = mRxSerivce.selectguild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChoiceGuildBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

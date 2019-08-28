package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddInfo;
import shangri.example.com.shangri.model.bean.request.SofwwareUserPresenterBeen;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CardRequestBean;
import shangri.example.com.shangri.presenter.view.MyCardView;
import shangri.example.com.shangri.presenter.view.SoftwareUserView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class MycardPresenter extends BasePresenter<MyCardView> {

    private MyCardView mSofwwareUserView;

    public MycardPresenter(Context context, MyCardView view) {
        super(context, view);
        mSofwwareUserView = view;
    }




    public void personalPetail() {
        RxObserver rxObserver = new RxObserver<CardRequestBean>() {
            @Override
            public void onHandleSuccess(CardRequestBean mObject) {
                mSofwwareUserView.personalPetail(mObject);
            }

            @Override
            public void onHandleComplete() {


            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        SofwwareUserPresenterBeen bean = new SofwwareUserPresenterBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<CardRequestBean>> observable = mRxSerivce.personalPetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CardRequestBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }




    public void otherDetail(String register_id) {
        RxObserver rxObserver = new RxObserver<CardRequestBean>() {
            @Override
            public void onHandleSuccess(CardRequestBean mObject) {
                mSofwwareUserView.otherDetail(mObject);
            }

            @Override
            public void onHandleComplete() {


            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        SofwwareUserPresenterBeen bean = new SofwwareUserPresenterBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        if (register_id.length()!=0){
            bean.setRegister_id(register_id);
        }
        Observable<BaseResponseEntity<CardRequestBean>> observable = mRxSerivce.otherDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CardRequestBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

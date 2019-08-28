package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.MineFragmentView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class MineFragmentPresenter extends BasePresenter<MineFragmentView> {

    private MineFragmentView mMineFragmentPresenter;
    public MineFragmentPresenter(Context context, MineFragmentView view) {
        super(context, view);
        mMineFragmentPresenter = view;
    }

    public void accountData(String tel, String password){
        RxObserver rxObserver = new RxObserver<AccountDataBean>() {
            @Override
            public void onHandleSuccess(AccountDataBean mAccountDataBean) {
                mMineFragmentPresenter.accountData(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        MineFragmentBeen bean = new MineFragmentBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<AccountDataBean>> observable = mRxSerivce.accountData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AccountDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

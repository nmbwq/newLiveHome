package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.UpdatePwdBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.UpdatePwdView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 修改密码
 * Created by chengaofu on 2017/7/2.
 */

public class UpdatePwdPresenter extends BasePresenter<UpdatePwdView> {

    private UpdatePwdView mUpdatePwdView;
    public UpdatePwdPresenter(Context context, UpdatePwdView view) {
        super(context, view);
        mUpdatePwdView = view;
    }
    //修改密码
    public void updatePwd(String oldPwd, String newPwd, String reNewPwd){
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object bean) {
            }

            @Override
            public void onHandleComplete() {
                mUpdatePwdView.updatePwd();
            }

            @Override
            public void onHandleFailed(String message) {
                mUpdatePwdView.requestFailed(message);
            }
        };

        UpdatePwdBean bean = new UpdatePwdBean();
        bean.setToken(UserConfig.getInstance().getToken().trim());
        bean.setOriginalpwd(oldPwd);
        bean.setNewpassword(newPwd);
        bean.setRepeatpwd(reNewPwd);
        BaseRequestEntity<UpdatePwdBean> requestEntity =
                new BaseRequestEntity<UpdatePwdBean>();
        requestEntity.setData(bean);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.updatePwd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

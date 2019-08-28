package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddInfo;
import shangri.example.com.shangri.model.bean.request.PersonalData;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.SettingPersonalDataView;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class SettingPersonalDataPresenter extends BasePresenter<SettingPersonalDataView> {

    private SettingPersonalDataView mSofwwareUserView;

    public SettingPersonalDataPresenter(Context context, SettingPersonalDataView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    //添加资料
    public void addUpdateInfo(String field, String value) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.addInfo("");
                ToastUtil.showShort("修改成功");
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        PersonalData bean = new PersonalData();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setField(field);
        bean.setValue(value);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.updateInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

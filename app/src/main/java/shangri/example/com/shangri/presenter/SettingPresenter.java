package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.presenter.view.SettingView;
import shangri.example.com.shangri.util.KeytUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 设置
 * Created by chengaofu on 2017/7/1.
 */

public class SettingPresenter extends BasePresenter<SettingView> {

    private SettingView mSettingView;
    public SettingPresenter(Context context, SettingView view) {
        super(context, view);
        mSettingView = view;
    }

    public void checkVersion(){ //检查版本
        RxObserver rxObserver = new RxObserver<List<AppVersionBean>>() {
            @Override
            public void onHandleSuccess(List<AppVersionBean> bean) {
                mSettingView.checkVersion(bean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSettingView.requestFailed(message);
            }
        };

        BaseRequestEntity<Object> requestEntity =
                new BaseRequestEntity<Object>();
        requestEntity.setData(new Object());

        //加密后的entity
        BaseRequestEntity<Object> entity =
                (BaseRequestEntity<Object>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<List<AppVersionBean>>> observable = mRxSerivce.checkVersion(entity);
        Disposable disposable = observable
                .compose(RxHelper.<List<AppVersionBean>>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void logout(){ //退出
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object bean) {

            }

            @Override
            public void onHandleComplete() {
                mSettingView.logout();
            }

            @Override
            public void onHandleFailed(String message) {
                mSettingView.requestFailed(message);
            }
        };

        BaseRequestEntity<Object> requestEntity =
                new BaseRequestEntity<Object>();
        requestEntity.setData(new Object());

        //加密后的entity
        BaseRequestEntity<Object> entity =
                (BaseRequestEntity<Object>) KeytUtil.getRSAKeyt(requestEntity, UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.logout(entity);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }
}

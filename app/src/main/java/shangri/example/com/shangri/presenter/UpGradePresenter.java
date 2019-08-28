package shangri.example.com.shangri.presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.ReqAnchoDetailBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.RespAnchoDetailBean;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.presenter.view.SettingView;
import shangri.example.com.shangri.presenter.view.UpgradeView;
import shangri.example.com.shangri.util.KeytUtil;

/**
 * 主播升级
 * Created by chengaofu on 2017/7/1.
 */

public class UpGradePresenter extends BasePresenter<UpgradeView> {

    private UpgradeView upgradeView;

    public UpGradePresenter(Context context, UpgradeView view) {
        super(context, view);
        upgradeView = view;
    }

    public void liftRule() {
        RxObserver<liftRuleBean> rxObserver = new RxObserver<liftRuleBean>() {
            @Override
            public void onHandleSuccess(liftRuleBean resultBean) {
                upgradeView.liftRule(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
            }
        };

        ReqAnchoDetailBean bean = new ReqAnchoDetailBean();
        bean.token = UserConfig.getInstance().getToken();
        Observable<BaseResponseEntity<liftRuleBean>> observable = mRxSerivce.liftRule(bean);
        Disposable disposable = observable
                .compose(RxHelper.<liftRuleBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 升级
     */
    public void handUpgrade() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                upgradeView.handUpgrade();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                upgradeView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.handUpgrade(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}
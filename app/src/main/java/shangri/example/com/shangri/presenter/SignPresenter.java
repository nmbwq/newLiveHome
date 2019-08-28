package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.BindingGuildBean;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.GivesGetBean;
import shangri.example.com.shangri.model.bean.request.JoinGuildBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.MoneyGainBean;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.HowMakeMoneyBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SignBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.everydayMissionBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.presenter.view.SignView;
import shangri.example.com.shangri.util.Aes;
import shangri.example.com.shangri.util.Base64Utils;


/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class SignPresenter extends BasePresenter<SignView> {

    private SignView mSofwwareUserView;

    public SignPresenter(Context context, SignView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 签到列表
     */

    public void signInList() {
        RxObserver rxObserver = new RxObserver<SignBean>() {
            @Override
            public void onHandleSuccess(SignBean resultBean) {
                mSofwwareUserView.signInList(resultBean);
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
        Observable<BaseResponseEntity<SignBean>> observable = mRxSerivce.signInList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SignBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 立即签到接口
     */
    public void inSign() {
        RxObserver rxObserver = new RxObserver<SignBean>() {
            @Override
            public void onHandleSuccess(SignBean resultBean) {
                mSofwwareUserView.inSign(resultBean);
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
        Observable<BaseResponseEntity<SignBean>> observable = mRxSerivce.inSign(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SignBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 主播赚钱接口
     */
    public void makeMoney() {
        RxObserver rxObserver = new RxObserver<HowMakeMoneyBean>() {
            @Override
            public void onHandleSuccess(HowMakeMoneyBean resultBean) {
                mSofwwareUserView.makeMoney(resultBean);
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
        Observable<BaseResponseEntity<HowMakeMoneyBean>> observable = mRxSerivce.makeMoney(bean);
        Disposable disposable = observable
                .compose(RxHelper.<HowMakeMoneyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 赚钱页面领取波币接口
     */
    public void makeMoneyGain(int type, int stage, int bb_num) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.makeMoneyGain();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        MoneyGainBean bean = new MoneyGainBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        bean.setStage(stage);
        bean.setBb_num(bb_num);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.makeMoneyGain(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 获取用户福利信息
     *
     * @param token
     */

    public void getCredits(String token) {
        RxObserver rxObserver = new RxObserver<AmountBean>() {
            @Override
            public void onHandleSuccess(AmountBean resultBean) {

                mSofwwareUserView.getCredits(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<AmountBean>> observable = mRxSerivce.getAmount(token);
        Disposable disposable = observable
                .compose(RxHelper.<AmountBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 领取投递简历波币接口
     */
    public void resumeDraw() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.resumeDraw();
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
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumeDraw(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 每日任务参数接口
     */
    public void everydayMission() {
        RxObserver rxObserver = new RxObserver<everydayMissionBean>() {
            @Override
            public void onHandleSuccess(everydayMissionBean resultBean) {
                mSofwwareUserView.everydayMission(resultBean);
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
        Observable<BaseResponseEntity<everydayMissionBean>> observable = mRxSerivce.everydayMission(bean);
        Disposable disposable = observable
                .compose(RxHelper.<everydayMissionBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 领取登陆福利
     */
    public void givesGet() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.givesGet();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        GivesGetBean bean = new GivesGetBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType("1");
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.givesGet(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 主播提现判断接口
     *
     * @param token
     */
    public void applyPondition(String token) {
        RxObserver rxObserver = new RxObserver<InvitationCodeBean>() {
            @Override
            public void onHandleSuccess(InvitationCodeBean resultBean) {

                mSofwwareUserView.applyPondition(resultBean);

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<InvitationCodeBean>> observable = mRxSerivce.applyPondition(token);
        Disposable disposable = observable
                .compose(RxHelper.<InvitationCodeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

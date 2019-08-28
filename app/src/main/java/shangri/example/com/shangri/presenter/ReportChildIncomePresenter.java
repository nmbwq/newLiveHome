package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.JoinGuildBean;
import shangri.example.com.shangri.model.bean.request.ReqReportBean;
import shangri.example.com.shangri.model.bean.request.ReqShare;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.ReportBean;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.ReportChildIncomeView;

public class ReportChildIncomePresenter extends BasePresenter<ReportChildIncomeView> {
    ReportChildIncomeView incomeView;

    public ReportChildIncomePresenter(Context context, ReportChildIncomeView view) {
        super(context, view);
        incomeView = view;
    }

    public void loadData(ReqReportBean reportBean) {
        RxObserver rxObserver = new RxObserver<GuildListBean>() {
            @Override
            public void onHandleSuccess(GuildListBean resultBean) {
                incomeView.loadData(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message); //检验校验码成功
            }
        };
        Observable<BaseResponseEntity<GuildListBean>> observable = mRxSerivce.reportData1(reportBean);
        Disposable disposable = observable
                .compose(RxHelper.<GuildListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void loadManagerData(ReqReportBean reportBean) {
        RxObserver rxObserver = new RxObserver<RespAdminBean>() {
            @Override
            public void onHandleSuccess(RespAdminBean resultBean) {
                incomeView.loadManagerData(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message); //检验校验码成功
            }
        };
        Observable<BaseResponseEntity<RespAdminBean>> observable = mRxSerivce.reportMagagerData(reportBean);
        Disposable disposable = observable
                .compose(RxHelper.<RespAdminBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void onShare(ReqShare reqShare) {
        RxObserver rxObserver = new RxObserver<ResShareBean>() {
            @Override
            public void onHandleSuccess(ResShareBean resultBean) {
                incomeView.onShare(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message); //检验校验码成功
            }
        };
        Observable<BaseResponseEntity<ResShareBean>> observable = mRxSerivce.onShare(reqShare);
        Disposable disposable = observable
                .compose(RxHelper.<ResShareBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 有没有公司
     */
    public void companyMy() {
        RxObserver rxObserver = new RxObserver<companyMyBean>() {
            @Override
            public void onHandleSuccess(companyMyBean resultBean) {
                incomeView.companyMy(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<companyMyBean>> observable = mRxSerivce.companyMy(bean);
        Disposable disposable = observable
                .compose(RxHelper.<companyMyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //查询消息以及框架里面主播管理员申请个数
    public void mineCount() {
        RxObserver rxObserver = new RxObserver<CountBean>() {
            @Override
            public void onHandleSuccess(CountBean resultBean) {
                incomeView.mineCount(resultBean);
            }

            @Override
            public void onHandleComplete() {
                incomeView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<CountBean>> observable = mRxSerivce.mineCount(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CountBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 判断是否过期
     */
    public void memberLate() {
        RxObserver rxObserver = new RxObserver<timeBean>() {
            @Override
            public void onHandleSuccess(timeBean resultBean) {
                incomeView.memberLate(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<timeBean>> observable = mRxSerivce.memberLate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<timeBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //查看支持平台接口
    public void supportPlatfrom() {
        RxObserver rxObserver = new RxObserver<SupportFromList>() {
            @Override
            public void onHandleSuccess(SupportFromList resultBean) {
                incomeView.SupportFromList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                incomeView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<SupportFromList>> observable = mRxSerivce.supportPlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SupportFromList>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

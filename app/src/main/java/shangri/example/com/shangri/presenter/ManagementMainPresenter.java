package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ManagementBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.view.ManagementMainView;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class ManagementMainPresenter extends BasePresenter<ManagementMainView> {

    private ManagementMainView mSofwwareUserView;

    public ManagementMainPresenter(Context context, ManagementMainView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void Management(String end_date, String guild_id, String start_date) {
        RxObserver rxObserver = new RxObserver<ManagementDataBean>() {
            @Override
            public void onHandleSuccess(ManagementDataBean resultBean) {
                mSofwwareUserView.managementData(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Calendar c = Calendar.getInstance();//
//        Integer mYear = c.get(Calendar.YEAR); // 获取当前年份
//        Integer mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
//        Integer mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (guild_id.length()>0){
            bean.setGuild_id(guild_id);
        }
        bean.setStart_date(start_date);
        bean.setEnd_date(end_date);
        Observable<BaseResponseEntity<ManagementDataBean>> observable = mRxSerivce.guild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ManagementDataBean>handleObservableResult())
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
                mSofwwareUserView.companyMy(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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


    /**
     * 判断是否过期
     */
    public void memberLate() {
        RxObserver rxObserver = new RxObserver<timeBean>() {
            @Override
            public void onHandleSuccess(timeBean resultBean) {
                mSofwwareUserView.memberLate(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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


}

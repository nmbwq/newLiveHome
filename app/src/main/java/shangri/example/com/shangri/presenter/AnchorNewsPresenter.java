package shangri.example.com.shangri.presenter;

import android.content.Context;

import java.util.Calendar;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ManagementBean;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ManagementChartBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.presenter.view.AnchorNewsView;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 报表
 * Created by pc on 2017/6/27.
 */

public class AnchorNewsPresenter extends BasePresenter<AnchorNewsView> {

    private AnchorNewsView mSofwwareUserView;

    public AnchorNewsPresenter(Context context, AnchorNewsView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void indexMain(String end_date, String guildid, String start_date, String quick, String table_flag) {
        RxObserver rxObserver = new RxObserver<ManagementDataBean>() {
            @Override
            public void onHandleSuccess(ManagementDataBean resultBean) {
                mSofwwareUserView.indexHead(resultBean);
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
        Integer mYear = c.get(Calendar.YEAR); // 获取当前年份
        Integer mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        Integer mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (guildid.length() > 0) {
            bean.setGuild_id(guildid);
        }
        bean.setEnd_date(end_date);
        bean.setStart_date(start_date);
        bean.setQuick(quick);
        if (quick.equals("1")) {
            bean.setTable_flag(table_flag);
        }
        Observable<BaseResponseEntity<ManagementDataBean>> observable = mRxSerivce.guild(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ManagementDataBean>handleObservableResult())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //top排行榜
    public void topData(String end_date, String mGuildid, String start_date, String page, String quick, String table_flag) {

        RxObserver rxObserver = new RxObserver<TopTenBean>() {
            @Override
            public void onHandleSuccess(TopTenBean resultBean) {
                mSofwwareUserView.topDta(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(mGuildid);
        bean.setEnd_date(end_date);
        bean.setStart_date(start_date);
        bean.setQuick(quick);
        if (quick.equals("1")) {
            bean.setTable_flag(table_flag);
        }
        if (UserConfig.getInstance().getRole().equals("2")) {

        } else {
//            if (quick.equals("1")) {
//
//            } else {
                bean.setPage(page);
//            }
        }
        Observable<BaseResponseEntity<TopTenBean>> observable = mRxSerivce.topData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<TopTenBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //报表-图表接口
    public void chart(String end_date, String guildid, String start_date, String time_slot, String chart_type) {

        RxObserver rxObserver = new RxObserver<ManagementChartBean>() {
            @Override
            public void onHandleSuccess(ManagementChartBean resultBean) {
                mSofwwareUserView.ChartBean(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ManagementBean bean = new ManagementBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guildid);
        bean.setStart_date(start_date);
        bean.setEnd_date(end_date);
        if (time_slot.equals("ziding")) {
        } else {
            bean.setTime_slot(time_slot);
        }
        bean.setChart_type(chart_type);
        Observable<BaseResponseEntity<ManagementChartBean>> observable = mRxSerivce.chart(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ManagementChartBean>handleObservableResult())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


}

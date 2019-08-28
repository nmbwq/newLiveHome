package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ManagementBean;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.DetailBean;
import shangri.example.com.shangri.presenter.view.DetailedDataView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 公表
 * Created by pc on 2017/6/27.
 */

public class DetailedDataPresenter extends BasePresenter<DetailedDataView> {

    private DetailedDataView mSofwwareUserView;

    public DetailedDataPresenter(Context context, DetailedDataView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    //报表-图表接口
    public void detail(String end_date, String mGuildid, String start_date, String time_slot) {

        RxObserver rxObserver = new RxObserver<DetailBean>() {
            @Override
            public void onHandleSuccess(DetailBean resultBean) {
                mSofwwareUserView.detail(resultBean);
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
        if (mGuildid.length() > 0) {
            bean.setGuild_id(mGuildid);
        }
        bean.setEnd_date(end_date);
        bean.setStart_date(start_date);
        if (time_slot.length() > 0) {
            bean.setTime_slot(time_slot);
        }
        Observable<BaseResponseEntity<DetailBean>> observable = mRxSerivce.detail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<DetailBean>handleObservableResult())
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


}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ReqAnchoDetailBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.RespAnchoDetailBean;
import shangri.example.com.shangri.presenter.view.ArchoDetailView;

public class AnchoDetailPresenter extends BasePresenter<ArchoDetailView> {
    ArchoDetailView archoDetailView;

    public AnchoDetailPresenter(Context context, ArchoDetailView view) {
        super(context, view);
        archoDetailView = view;
    }


    public void loadData(String guild_id, String anchor_uid, String time_slot, String start_date, String end_date) {
        RxObserver<RespAnchoDetailBean> rxObserver = new RxObserver<RespAnchoDetailBean>() {
            @Override
            public void onHandleSuccess(RespAnchoDetailBean resultBean) {
                archoDetailView.loadData(resultBean);
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
        bean.guild_id = guild_id;
        bean.anchor_uid = anchor_uid;
        if (time_slot.equals("ziding")) {
        } else {
            bean.time_slot = time_slot;
        }
        bean.start_date = start_date + "";
        bean.end_date = end_date + "";
        Observable<BaseResponseEntity<RespAnchoDetailBean>> observable = mRxSerivce.getAnchoDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RespAnchoDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 删除
     */
    public void anchorDelete(String guild_id, String anchor_uid) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                archoDetailView.anchorDelete();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                archoDetailView.requestFailed(message);
            }
        };
        alertBean bean = new alertBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setAnchor_uid(anchor_uid);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorDelete(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LookBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LookDetailBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.LookDetailsView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class LookDetailsPresenter extends BasePresenter<LookDetailsView> {

    private LookDetailsView mSofwwareUserView;

    public LookDetailsPresenter(Context context, LookDetailsView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void LookDetail(int currPage, String guild_id, String time_slot, String sort,String end_date) {
        RxObserver rxObserver = new RxObserver<LookDetailBean>() {
            @Override
            public void onHandleSuccess(LookDetailBean resultBean) {
                mSofwwareUserView.onDetails(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        LookBean bean = new LookBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setPage(String.valueOf(currPage));
        bean.setTime_slot(time_slot);
        bean.setSort(sort);
        bean.setEnd_date(end_date);
        Observable<BaseResponseEntity<LookDetailBean>> observable = mRxSerivce.LookDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LookDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

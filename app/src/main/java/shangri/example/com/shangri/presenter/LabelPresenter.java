package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LabelBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.presenter.view.LabelView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class LabelPresenter extends BasePresenter<LabelView> {

    private LabelView mSofwwareUserView;
    public LabelPresenter(Context context, LabelView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void getLabelData(String type){
        RxObserver rxObserver = new RxObserver<LabelDataBean>() {
            @Override
            public void onHandleSuccess(LabelDataBean resultBean) {
                mSofwwareUserView.labelData(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        LabelBean bean = new LabelBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);

        Observable<BaseResponseEntity<LabelDataBean>> observable = mRxSerivce.selecttags(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LabelDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

}

package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.presenter.view.OtherLoginView;

/**
 * 第三方登录
 */
public class OtherLoginPresenter extends BasePresenter<OtherLoginView> {
    private OtherLoginView mView;
    public OtherLoginPresenter(Context context, OtherLoginView view) {
        super(context, view);
        mView = view;
    }

    public void getWeChatCode() {
        RxObserver rxObserver=new RxObserver() {
            @Override
            public void onHandleSuccess(Object o) {

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
    }
}

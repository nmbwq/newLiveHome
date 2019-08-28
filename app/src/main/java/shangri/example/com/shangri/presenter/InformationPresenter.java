package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddInfo;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.InformationBean;
import shangri.example.com.shangri.model.bean.request.LabelBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.presenter.view.InformationView;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class InformationPresenter extends BasePresenter<InformationView> {

    private InformationView mSofwwareUserView;

    public InformationPresenter(Context context, InformationView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void nickname(String name) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.nickname();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        InformationBean bean = new InformationBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setNickname(name);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.nickname(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //选择平台
    public void getPlatfromList() {
        RxObserver rxObserver = new RxObserver<SortModelBean>() {
            @Override
            public void onHandleSuccess(SortModelBean resultBean) {
                mSofwwareUserView.PlatfromList(resultBean);
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

        Observable<BaseResponseEntity<SortModelBean>> observable = mRxSerivce.getBindingListData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SortModelBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //标签
    public void getLabelData() {
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
//        bean.setType(type);

        Observable<BaseResponseEntity<LabelDataBean>> observable = mRxSerivce.selecttags(bean);
        Disposable disposable = observable
                .compose(RxHelper.<LabelDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //添加资料
    public void addInfo(String role, String nickname, String sex, String platfrom_name, String guild_name,
                        String tag_ids, String tags_content, String cover_url, String anchor_content) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.addInfo("");
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddInfo bean = new AddInfo();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRole(role);
//        bean.setNickname(nickname);
//        bean.setSex(sex);
//        bean.setPlatfrom_name(platfrom_name);
//        bean.setGuild_name(guild_name);
//        bean.setTag_ids(tag_ids);
////        bean.setTags_content(tags_content);
////        bean.setCover_url("");
//        bean.setAnchor_content(anchor_content);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addInfo(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

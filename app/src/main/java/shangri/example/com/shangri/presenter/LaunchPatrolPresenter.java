package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.LabelBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.presenter.view.LaunchPatrolView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class LaunchPatrolPresenter extends BasePresenter<LaunchPatrolView> {

    private LaunchPatrolView mSofwwareUserView;

    public LaunchPatrolPresenter(Context context, LaunchPatrolView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void sendLaunch(String alert_admin, String audioUrl, String audio_time, String imageUrl, String guild_id, String register_guild_id,
                           String inspect_date, String good_tags_ids,
                           String bad_tags_ids, String comment) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.lacunch();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setRegister_guild_id(register_guild_id);
        bean.setInspect_date(inspect_date);
        bean.setGood_tags_ids(good_tags_ids);
        bean.setBad_tags_ids(bad_tags_ids);
        bean.setComment(comment);
        bean.setAudio_time(audio_time);
//        是否推送管理员 1推送 会长发起时有效
        if (UserConfig.getInstance().getRole().equals("1")) {
            bean.setAlert_admin(alert_admin);
        }
        try {
            bean.setPhoto_url(imageUrl);
            bean.setAudio_url(Base64Utils.encodeFile(audioUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.launchadd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void getLabelData(String type) {
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

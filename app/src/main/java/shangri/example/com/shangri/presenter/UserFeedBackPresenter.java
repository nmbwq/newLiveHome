package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.FeedbackBean;
import shangri.example.com.shangri.model.bean.request.ImageBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.presenter.view.UserFeedBackView;
import shangri.example.com.shangri.util.Base64Utils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用户反馈
 * Created by chengaofu on 2017/6/30.
 */

public class UserFeedBackPresenter  extends BasePresenter<UserFeedBackView> {

    private UserFeedBackView mUserFeedBackView;
    public UserFeedBackPresenter(Context context, UserFeedBackView view) {
        super(context, view);
        mUserFeedBackView = view;
    }

    public void uploadFeedBackContent(String tel, String content, String url){
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mUserFeedBackView.uploadFeedBcakContent();
            }

            @Override
            public void onHandleFailed(String message) {
                mUserFeedBackView.requestFailed(message);
            }
        };
        FeedbackBean bean = new FeedbackBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setContact(tel);
        bean.setContent(content);
        bean.setImages(url);
        BaseRequestEntity<FeedbackBean> requestEntity =
                new BaseRequestEntity<FeedbackBean>();
        requestEntity.setData(bean);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.uploadFeedBcakContent(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
    public void imageUpload(String imageUrl){
        RxObserver rxObserver = new RxObserver<ImageUpload>() {
            @Override
            public void onHandleSuccess(ImageUpload resultBean) {
                mUserFeedBackView.imageUpload(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mUserFeedBackView.requestFailed(message);
            }
        };



        ImageBean bean = new ImageBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType("question");
        try {
            File file = new File(imageUrl);
            String pathname = file.getName();
            bean.setFile_name(pathname);
            bean.setImage(Base64Utils.encodeFile(imageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable<BaseResponseEntity<ImageUpload>> observable = mRxSerivce.imageUpload(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ImageUpload>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

//        bean.setFile_name();
//        bean.setType(type);

//        Map<String, String> map = new HashMap<>();
//        map.put("token",UserConfig.getInstance().getToken());
//        map.put("type","question");
//        File file = new File(imageUrl);
//        String pathname = file.getName();
//        map.put("file_name",pathname);
//
//        Observable<BaseResponseEntity<ImageUpload>> observable = mRxSerivce.updaFamilyImage(
//                bean,GsonFrom.updataImages(null, imageUrl));
//        Disposable disposable = observable
//                .compose(RxHelper.<ImageUpload>handleObservableResult())
//                .subscribeWith(rxObserver);
//        addSubscribe(disposable);

    }
}

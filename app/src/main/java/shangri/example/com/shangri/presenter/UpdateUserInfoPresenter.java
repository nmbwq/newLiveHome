package shangri.example.com.shangri.presenter;

import android.content.Context;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.ImageBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.presenter.view.UpdateUserView;
import shangri.example.com.shangri.util.Base64Utils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 修改用户信息
 * Created by chengaofu on 2017/6/29.
 */

public class UpdateUserInfoPresenter extends BasePresenter<UpdateUserView> {

    private UpdateUserView mUpdateUserView;

    public UpdateUserInfoPresenter(Context context, UpdateUserView view) {
        super(context, view);
        mUpdateUserView = view;
    }

    public void updateUserInfo(String imageUrl) { //修改用户资料之 修改头像

        RxObserver rxObserver = new RxObserver<ImageUpload>() {
            @Override
            public void onHandleSuccess(ImageUpload resultBean) {
                mUpdateUserView.updateUser(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mUpdateUserView.requestFailed(message);
            }
        };
        ImageBean bean = new ImageBean();
        bean.setToken(UserConfig.getInstance().getToken());
//        bean.setType("avatar");
        try {
//            File file = new File(imageUrl);
//            String pathname = file.getName();
//            bean.setFile_name(pathname);
            bean.setImage(Base64Utils.encodeFile(imageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable<BaseResponseEntity<ImageUpload>> observable = mRxSerivce.imageUpload(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ImageUpload>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

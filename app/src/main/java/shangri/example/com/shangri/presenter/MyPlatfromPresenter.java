package shangri.example.com.shangri.presenter;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.UploadCover;
import shangri.example.com.shangri.model.bean.request.AddPlatfrom;
import shangri.example.com.shangri.model.bean.request.deletePlatfrom;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ImageUpload;
import shangri.example.com.shangri.model.bean.response.MyPlatfromList;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.presenter.view.MyPlatfromView;
import shangri.example.com.shangri.util.Base64Utils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class MyPlatfromPresenter extends BasePresenter<MyPlatfromView> {

    private MyPlatfromView mSofwwareUserView;

    public MyPlatfromPresenter(Context context, MyPlatfromView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void MyPlatfromList() {
        RxObserver rxObserver = new RxObserver<MyPlatfromList>() {
            @Override
            public void onHandleSuccess(MyPlatfromList resultBean) {
                mView.myPlatfromList(resultBean);
                mSofwwareUserView.requestFailed("");

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

        Observable<BaseResponseEntity<MyPlatfromList>> observable = mRxSerivce.minePlatfroms(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MyPlatfromList>handleObservableResult())
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

    public void updateUserInfo(String imageUrl) { //封面

        RxObserver rxObserver = new RxObserver<ImageUpload>() {
            @Override
            public void onHandleSuccess(ImageUpload resultBean) {
                mSofwwareUserView.uploadCover(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mView.requestFailed(message);
            }
        };


        UploadCover bean = new UploadCover();
        bean.setToken(UserConfig.getInstance().getToken());
        try {
            bean.setImage(Base64Utils.encodeFile(imageUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable<BaseResponseEntity<ImageUpload>> observable = mRxSerivce.uploadCover(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ImageUpload>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //添加平台
    public void addPlatfrom(String platfrom_name, String anchor_content, String guild_name,
                            String cover_url) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                ToastUtil.showShort("添加成功");
                mSofwwareUserView.addPlatfrom("");

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddPlatfrom bean = new AddPlatfrom();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlatfrom_name(platfrom_name);
        bean.setGuild_name(guild_name);
        bean.setCover_url(cover_url);
        bean.setAnchor_content(anchor_content);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.addPlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //更新平台
    public void updatePlatfrom(String platfrom_id, String platfrom_name, String anchor_content, String guild_name,
                               String cover_url) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.addPlatfrom("");
            }

            @Override
            public void onHandleComplete() {
                ToastUtil.showShort("修改成功");
                mSofwwareUserView.addPlatfrom("");

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        AddPlatfrom bean = new AddPlatfrom();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlatfrom_id(platfrom_id);
        bean.setPlatfrom_name(platfrom_name);
        bean.setGuild_name(guild_name);
        bean.setCover_url(cover_url);
        bean.setAnchor_content(anchor_content);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.updatePlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //删除平台
    public void deletePlatfrom(String platfrom_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                ToastUtil.showShort("删除成功");
                mSofwwareUserView.addPlatfrom("");
            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.addPlatfrom("");

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        deletePlatfrom bean = new deletePlatfrom();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlatfrom_id(platfrom_id);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.deletePlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
}

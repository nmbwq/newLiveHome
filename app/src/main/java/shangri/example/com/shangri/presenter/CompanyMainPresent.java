package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddStarBean;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * Description: 公司主页,上传明星主播，上传平台，上传公司照片
 * Data：2018/11/7-17:13
 * Author: lin
 */
public class CompanyMainPresent extends BasePresenter<CompanyMainView> {
    CompanyMainView companyMainView;

    public CompanyMainPresent(Context context, CompanyMainView view) {
        super(context, view);
        companyMainView = view;
    }

    /**
     * 公司主页
     */
    public void getCompanyMain() {
        RxObserver rxObserver = new RxObserver<CompanyMainBean>() {
            @Override
            public void onHandleSuccess(CompanyMainBean resultBean) {
                companyMainView.getCompanyMain(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyMainView.requestFailed(message);
            }
        };
        String token = UserConfig.getInstance().getToken();

        if (CompanyHomepageActivityTwo.COMPANY_TOKEN != null) {
            if (CompanyHomepageActivityTwo.COMPANY_TOKEN.length() > 0) {
                token = CompanyHomepageActivityTwo.COMPANY_TOKEN;
//                CompanyHomepageActivityTwo.COMPANY_TOKEN = "";
            }
        }
        Observable<BaseResponseEntity<CompanyMainBean>> observable = mRxSerivce.companyMain(token);
        Disposable disposable = observable
                .compose(RxHelper.<CompanyMainBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 上传平台
     *
     * @param plats
     * @param other_palt
     */
    public void enterPlatfrom(String plats, String other_palt) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                companyMainView.enterPlatfrom();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyMainView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.enterPlatfrom(UserConfig.getInstance().getToken(), plats, other_palt);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 上传明星主播
     *
     * @param starAnchor
     */
    public void setStarAnchor(List<CompanyMainBean.AnchorStar> starAnchor) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                companyMainView.setStarAnchor();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyMainView.requestFailed(message);
            }
        };
        AddStarBean starBean = new AddStarBean();
        starBean.setToken(UserConfig.getInstance().getToken());
        List<CompanyMainBean.AnchorStar> starList = new ArrayList<>();
        for (CompanyMainBean.AnchorStar anchor : starAnchor) {
            anchor.setAnchor_logo(Base64Utils.imageToBase64(anchor.getAnchor_logo()));
//            anchor.setAnchor_logo(Base64Utils.encodeFile(anchor.getAnchor_logo()));
            anchor.setAnchor_name(anchor.getAnchor_name());
            starList.add(anchor);
        }
        String jsonArray = new Gson().toJson(starList);
        starBean.setAnchor(jsonArray);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.setStarAnchor(starBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 上传公司照片
     *
     * @param photos
     */
    public void upPhotoAlbum(List<String> photos) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                companyMainView.upPhotoAlbum();

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyMainView.requestFailed(message);
            }
        };
        AddStarBean starBean = new AddStarBean();
        List<AddStarBean> list = new ArrayList<>();
        starBean.setToken(UserConfig.getInstance().getToken());
        for (int i = 0; i < photos.size(); i++) {
            try {
                AddStarBean bean = new AddStarBean();
                bean.setImage(Base64Utils.encodeFile(photos.get(i)));
                list.add(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String jsonArray = new Gson().toJson(list);
        starBean.setImage(jsonArray);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.upPhotoAlbum(starBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 删除以上信息
     */
    public void deleteImg(String type, String del_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                companyMainView.deleteImg();

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                companyMainView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        bean.setDel_id(del_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.deleteImg(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

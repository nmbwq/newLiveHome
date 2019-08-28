package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.positionAddBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * Description:
 * Data：2018/11/9-10:14
 * Author: lin
 */
public class ChairmanRelesePresenter2 extends BasePresenter<ChairmanReleseView> {

    private ChairmanReleseView mLoginUserView;

    public ChairmanRelesePresenter2(Context context, ChairmanReleseView view) {
        super(context, view);
        mLoginUserView = view;
    }

    /**
     * 会长发送招聘公司认证
     *
     * @param
     */
    public void companyAdd(String location,String logo, String company_scale, String anchor_scale, int type, String company_name, String telephone, String company_description,String company_short_name) {
        RxObserver rxObserver = new RxObserver<NewCompanyBean>() {

            @Override
            public void onHandleSuccess(NewCompanyBean resultBean) {
                mLoginUserView.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };

        CompanyAddBean bean = new CompanyAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        if (company_short_name.length() > 0) {
            bean.setCompany_short_name(company_short_name + "");
        }

        if (logo.length() > 0) {
            try {
                bean.setLogo(Base64Utils.encodeFile(logo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (company_scale.length() > 0) {
            bean.setCompany_scale(company_scale);
        }
        if (location.length() > 0) {
            bean.setLocation(location);
        }

        if (anchor_scale.length() > 0) {
            bean.setAnchor_scale(anchor_scale);
        }

        if (company_name.length() > 0) {
            bean.setCompany_name(company_name);
        }
        if (telephone.length() > 0) {
            bean.setTelephone(telephone);
        }
        if (company_description.length() > 0) {
            bean.setCompany_description(company_description);
        }
        Observable<BaseResponseEntity<NewCompanyBean>> observable = mRxSerivce.companyAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}
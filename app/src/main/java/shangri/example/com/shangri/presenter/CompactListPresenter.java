package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.MessageBean;
import shangri.example.com.shangri.model.bean.request.addRuzhuBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean1;
import shangri.example.com.shangri.model.bean.request.wikiCollectBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.model.bean.response.WebDates;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.view.CompactlistView;

/**
 * 法务合同
 * Created by pc on 2017/6/27.
 */

public class CompactListPresenter extends BasePresenter<CompactlistView> {

    private CompactlistView mSofwwareUserView;

    public CompactListPresenter(Context context, CompactlistView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 获取合同列表
     */

    public void legalGuildContract(int type, int page) {
        RxObserver rxObserver = new RxObserver<CompactListResponse>() {
            @Override
            public void onHandleSuccess(CompactListResponse resultBean) {
                mSofwwareUserView.legalGuildContract(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type);
        bean.setPage(page);
        Observable<BaseResponseEntity<CompactListResponse>> observable = mRxSerivce.legalGuildContract(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CompactListResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取详情数据
     */

    public void legalDetail(int id) {
        RxObserver rxObserver = new RxObserver<CompactDetailResponse>() {
            @Override
            public void onHandleSuccess(CompactDetailResponse resultBean) {
                mSofwwareUserView.legalDetail(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<CompactDetailResponse>> observable = mRxSerivce.legalDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CompactDetailResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取合同模板列表
     */

    public void templateList() {
        RxObserver rxObserver = new RxObserver<CompactMobanResponse>() {
            @Override
            public void onHandleSuccess(CompactMobanResponse resultBean) {
                mSofwwareUserView.templateList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<CompactMobanResponse>> observable = mRxSerivce.templateList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CompactMobanResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 判断是否认证
     */

    public void legalIsface() {
        RxObserver rxObserver = new RxObserver<IsFaceResponse>() {
            @Override
            public void onHandleSuccess(IsFaceResponse resultBean) {
                mSofwwareUserView.legalIsface(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<IsFaceResponse>> observable = mRxSerivce.legalIsface(bean);
        Disposable disposable = observable
                .compose(RxHelper.<IsFaceResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 模版H5展示
     */

    public void legalTemplate(String table_name, int type) {
        RxObserver rxObserver = new RxObserver<IsFaceResponse>() {
            @Override
            public void onHandleSuccess(IsFaceResponse resultBean) {
                mSofwwareUserView.legalTemplate(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTable_name(table_name);
        bean.setType(type);
        Observable<BaseResponseEntity<IsFaceResponse>> observable = mRxSerivce.legalTemplate(bean);
        Disposable disposable = observable
                .compose(RxHelper.<IsFaceResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长签章
     */

    public void legalContract_signature(WebDates bean) {
        RxObserver rxObserver = new RxObserver<ChairmanSignResponse>() {
            @Override
            public void onHandleSuccess(ChairmanSignResponse resultBean) {
                mSofwwareUserView.legalContract_signature(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<ChairmanSignResponse>> observable = mRxSerivce.legalContract_signature(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChairmanSignResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 生成 草稿箱
     */

    public void legalTxcontract(WebDates bean) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.legalTxcontract();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.legalTxcontract(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取验证码
     */

    public void legalContractVerify(int id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.legalContractVerify();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.legalContractVerify(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播获取验证码
     */

    public void legalanchorVerify(int id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.legalanchorVerify();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.legalanchorVerify(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 验证验证码
     */

    public void ontractVerifyCode(int id, String verify_code) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.ontractVerifyCode();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean1 bean = new encyclopediaRequestBean1();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        bean.setVerify_code(verify_code);
        if (UserConfig.getInstance().getRole().equals("1")) {
            bean.setType("legal_contract");
        } else {
            bean.setType("legal_anchor_agree");
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.ontractVerifyCode(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 合同验证通过,并推送
     */
    public void legalTxcontractPush(int id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.legalTxcontractPush();
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.legalTxcontractPush(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 主播掐签章
     */

    public void legalAnchor_signature(int id) {
        RxObserver rxObserver = new RxObserver<ChairmanSignResponse>() {
            @Override
            public void onHandleSuccess(ChairmanSignResponse resultBean) {
                mSofwwareUserView.legalAnchor_signature(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<ChairmanSignResponse>> observable = mRxSerivce.legalAnchor_signature(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChairmanSignResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 合同撤销
     */
    public void legalContractRepeal(int id) {
        RxObserver rxObserver = new RxObserver<ChairmanSignResponse>() {
            @Override
            public void onHandleSuccess(ChairmanSignResponse resultBean) {
                mSofwwareUserView.legalContractRepeal(resultBean);
            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<ChairmanSignResponse>> observable = mRxSerivce.legalContractRepeal(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ChairmanSignResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 加载pdf网页
     */
    public void legalContractBase(int id) {
        RxObserver rxObserver = new RxObserver<pdfResponse>() {
            @Override
            public void onHandleSuccess(pdfResponse resultBean) {
                mSofwwareUserView.legalContractBase(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setId(id);
        Observable<BaseResponseEntity<pdfResponse>> observable = mRxSerivce.legalContractBase(bean);
        Disposable disposable = observable
                .compose(RxHelper.<pdfResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 剩余签章数量
     */
    public void signNumber() {
        RxObserver rxObserver = new RxObserver<pdfResponse>() {
            @Override
            public void onHandleSuccess(pdfResponse resultBean) {
                mSofwwareUserView.signNumber(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };

        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<pdfResponse>> observable = mRxSerivce.signNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.<pdfResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

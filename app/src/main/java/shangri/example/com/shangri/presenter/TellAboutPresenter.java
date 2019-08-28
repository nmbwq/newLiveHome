package shangri.example.com.shangri.presenter;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AnchorChectRequestBean;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.ManagerChectRequestBean;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.tellAboutRequestBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.tellAboutResponseBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.view.TellAboutView;
import shangri.example.com.shangri.presenter.view.anchorChectView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class TellAboutPresenter extends BasePresenter<TellAboutView> {

    private TellAboutView mSofwwareUserView;

    public TellAboutPresenter(Context context, TellAboutView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 约聊列表接口
     *
     * @param
     */

    public void chatWithList(String chat_id, String page) {

        RxObserver rxObserver = new RxObserver<tellAboutResponseBean>() {
            @Override
            public void onHandleSuccess(tellAboutResponseBean resultBean) {
                mSofwwareUserView.chatWithList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        bean.setChat_id(chat_id);
        Observable<BaseResponseEntity<tellAboutResponseBean>> observable = mRxSerivce.chatWithList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<tellAboutResponseBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 投递简历接口
     *
     * @param chat_id
     * @param page
     */
    public void sendResume(String chat_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.sendResume();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setChat_id(chat_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.sendResume(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 投递简历接口
     *
     * @param chat_id
     * @param page 类型 0 拨电话 1留电话
     */
    public void dialTelephoneMessage(String chat_id,String type) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.dialTelephoneMessage();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setChat_id(chat_id);
        bean.setType(type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.dialTelephoneMessage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 交换电话微信接口
     *
     * @param chat_id
     * @param type
     * @param telephone
     * @param wx
     */
    public void telephoneWechat(String chat_id, String type, String telephone, String wx) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.sendResume();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setChat_id(chat_id);
        bean.setType(type);
        if (telephone.length() > 0) {
            bean.setTelephone(telephone);
        }
        if (wx.length() > 0) {
            bean.setWx(wx);
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.telephoneWechat(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 拨打电话接口
     *
     * @param chat_id
     * @param type
     * @param telephone
     * @param wx
     */
    public void telephoneResumet(String chat_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.telephoneResumet();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setChat_id(chat_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.telephoneResumet(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长约聊主播接口
     *
     * @param chat_id
     * @param page
     */
    public void chatAnchor(String resume_id, String type) {

        RxObserver rxObserver = new RxObserver<chatAnchorResponseBean>() {
            @Override
            public void onHandleSuccess(chatAnchorResponseBean resultBean) {
                mSofwwareUserView.chatAnchor(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id + "");
        bean.setType(type);
        Observable<BaseResponseEntity<chatAnchorResponseBean>> observable = mRxSerivce.chatAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<chatAnchorResponseBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长发送招聘公司认证
     *
     * @param
     */
    public void companyAdd(int type) {
        RxObserver rxObserver = new RxObserver<NewCompanyBean>() {

            @Override
            public void onHandleSuccess(NewCompanyBean resultBean) {
                mSofwwareUserView.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        CompanyAddBean bean = new CompanyAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setType(type + "");
        Observable<BaseResponseEntity<NewCompanyBean>> observable = mRxSerivce.companyAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 1查看剩余次数 2留电话(需传下面参数) 默认1
     */

    public void leaveAnchor(String is_leave, String resume_id, String telephone, String bd_num) {
        RxObserver rxObserver = new RxObserver<wantListBean>() {
            @Override
            public void onHandleSuccess(wantListBean mAccountDataBean) {
                mSofwwareUserView.leaveAnchor(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setIs_leave(is_leave);
        if (resume_id.length() > 0) {
            bean.setResume_id(resume_id);
        }
        if (telephone.length() > 0) {
            bean.setTelephone(telephone);
        }
        if (bd_num.length() > 0) {
            bean.setBd_num(bd_num);
        }
        Observable<BaseResponseEntity<wantListBean>> observable = mRxSerivce.leaveAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<wantListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //    会长沟通主播
    public void linkUpAnchor(String anchor_id, String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
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
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        if (anchor_id.length() == 0) {
            bean.setAnchor_id("0");
        } else {
            bean.setAnchor_id(anchor_id);
        }

        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.linkUpAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 打电话减少次数
     *
     * @param resume_id
     */

    public void residueNumber(String resume_id, String bd_num) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
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
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        if (bd_num.length() > 0) {
            bean.setBd_num(bd_num);
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.residueNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

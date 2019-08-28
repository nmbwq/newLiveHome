package shangri.example.com.shangri.presenter;

import android.content.Context;

import com.github.mikephil.charting.formatter.IFillFormatter;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AnchorOrder;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.request.tellAboutRequestBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.view.LoginUserView;
import shangri.example.com.shangri.util.MD5Util;

/**
 * Created by chengaofu on 2017/6/27.
 */

public class LookAnchorPresenter extends BasePresenter<LookAnchorView> {

    private LookAnchorView mLoginUserView;

    public LookAnchorPresenter(Context context, LookAnchorView view) {
        super(context, view);
        mLoginUserView = view;

    }


    /**
     * 留电话减少次数
     */

    public void leaveAnchor(String is_leave, String anchor_id, String resume_id, String telephone, String bd_num) {
        RxObserver rxObserver = new RxObserver<wantListBean>() {
            @Override
            public void onHandleSuccess(wantListBean mAccountDataBean) {
                mLoginUserView.leaveAnchor(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setIs_leave(is_leave);
        if (anchor_id.length() > 0) {
            bean.setAnchor_id(anchor_id);
        }
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


    /**
     * 安卓专用判断是否抢过接口
     *
     * @param resume_id
     */

    public void judgeGrade(String resume_id) {
        RxObserver rxObserver = new RxObserver<JudgeGradeBean>() {
            @Override
            public void onHandleSuccess(JudgeGradeBean resultBean) {
                mLoginUserView.judgeGrade(resultBean);
            }

            @Override
            public void onHandleComplete() {
                mLoginUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<JudgeGradeBean>> observable = mRxSerivce.judgeGrade(bean);
        Disposable disposable = observable
                .compose(RxHelper.<JudgeGradeBean>handleObservableResult())
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
                mLoginUserView.residueNumber();
            }

            @Override
            public void onHandleComplete() {
                mLoginUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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


    /**
     * 拨打电话接口
     *
     * @param chat_id
     * @param
     * @param
     * @param
     */
    public void telephoneResumet(String chat_id) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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
     * 获取招聘详情
     *
     * @param id
     */
    public void getResumeDetailBean(String id, String chat_id) {
        RxObserver rxObserver = new RxObserver<ResumeDetailBean>() {
            @Override
            public void onHandleSuccess(ResumeDetailBean resultBean) {
                mLoginUserView.getResumeDetailBean(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
//
        bean.setResume_id(id);
        if (chat_id!=null){
            if (chat_id.length() > 0) {
                bean.setChat_id(chat_id);
            }
        }
        Observable<BaseResponseEntity<ResumeDetailBean>> observable = mRxSerivce.getResumeDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeDetailBean>handleObservableResult())
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
        Observable<BaseResponseEntity<NewCompanyBean>> observable = mRxSerivce.companyAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.<NewCompanyBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void resumeDetail(String resume_id, String chat_id) {
        RxObserver rxObserver = new RxObserver<ResumeIndexBean>() {
            @Override
            public void onHandleSuccess(ResumeIndexBean resultBean) {
                mLoginUserView.resumeDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        if (chat_id.length() > 0) {
            bean.setChat_id(chat_id);
        }
        Observable<BaseResponseEntity<ResumeIndexBean>> observable = mRxSerivce.resumeDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeIndexBean>handleObservableResult())
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
                mLoginUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
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
     * 简历收藏接口
     *
     * @param resume_id
     */

    public void resumeDoCollect(String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.resumeDoCollect();
            }

            @Override
            public void onHandleComplete() {
                mLoginUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumeDoCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 简历取消收藏接口
     *
     * @param resume_id
     */

    public void resumeCancelCollect(String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mLoginUserView.resumeCancelCollect();
            }

            @Override
            public void onHandleComplete() {
                mLoginUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.resumeCancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    public void accountData() {
        RxObserver rxObserver = new RxObserver<AccountDataBean>() {
            @Override
            public void onHandleSuccess(AccountDataBean mAccountDataBean) {
                mLoginUserView.accountData(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        MineFragmentBeen bean = new MineFragmentBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<AccountDataBean>> observable = mRxSerivce.accountData(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AccountDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长约聊剩余次数接口
     *
     * @param
     */
    public void guildNumber() {
        RxObserver rxObserver = new RxObserver<sendSucceedResonse>() {

            @Override
            public void onHandleSuccess(sendSucceedResonse o) {
                mLoginUserView.guildNumber(o);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<sendSucceedResonse>> observable = mRxSerivce.guildNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.<sendSucceedResonse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 会长约聊主播
     *
     * @param
     */
    public void chatAnchor(String resume_id, String type) {
        RxObserver rxObserver = new RxObserver<chatAnchorResponseBean>() {

            @Override
            public void onHandleSuccess(chatAnchorResponseBean o) {
                mLoginUserView.chatAnchor(o);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        tellAboutRequestBean bean = new tellAboutRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        bean.setType(type);
        Observable<BaseResponseEntity<chatAnchorResponseBean>> observable = mRxSerivce.chatAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<chatAnchorResponseBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 抢at功能 会长抢主播
     */

    public void grabAnchor(String resume_id) {
        RxObserver rxObserver = new RxObserver<GrabAnchorBean>() {
            @Override
            public void onHandleSuccess(GrabAnchorBean mAccountDataBean) {
                mLoginUserView.grabAnchor(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        AnchorOrder bean = new AnchorOrder();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id + "");
        Observable<BaseResponseEntity<GrabAnchorBean>> observable = mRxSerivce.grabAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<GrabAnchorBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 抢at功能 会长抢主播
     */

    public void grabAnchorOrder(String resume_id, String type, String active_id, String price) {
        RxObserver rxObserver = new RxObserver<GrabAnchorOrderBean>() {
            @Override
            public void onHandleSuccess(GrabAnchorOrderBean mAccountDataBean) {
                mLoginUserView.grabAnchorOrder(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mLoginUserView.requestFailed(message);
            }
        };
        AnchorOrder bean = new AnchorOrder();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id + "");
        bean.setType(type);
        bean.setActive_id(active_id);
        bean.setPrice(price);
        Observable<BaseResponseEntity<GrabAnchorOrderBean>> observable = mRxSerivce.grabAnchorOrder(bean);
        Disposable disposable = observable
                .compose(RxHelper.<GrabAnchorOrderBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }




}

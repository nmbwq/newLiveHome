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
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CollectRequestBean;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.CompanyRequestBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.tellAboutRequestBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.presenter.view.MineFragmentView;
import shangri.example.com.shangri.util.Base64Utils;

/**
 * 主播招聘
 * Created by pc on 2017/6/27.
 */

public class BossFragmentPresenter extends BasePresenter<BossFragmentView> {

    private BossFragmentView mMineFragmentPresenter;

    public BossFragmentPresenter(Context context, BossFragmentView view) {
        super(context, view);
        mMineFragmentPresenter = view;
    }

    public void recruitList(String recommend_new, String anchor_tab, String publish_type, String job_method, boolean isCard, String offset, String page, String area_name, String plat_id, String anchor_type, String pay_low, String pay_high, String salary_type, String keep_pay) {
        RxObserver rxObserver = new RxObserver<BossDataBean>() {
            @Override
            public void onHandleSuccess(BossDataBean mAccountDataBean) {
                mMineFragmentPresenter.recruitList(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        if (recommend_new.length() > 0) {
            bean.setRecommend_new(recommend_new);
        }
        if (job_method.length() > 0) {
            bean.setJob_method(job_method);
        }
        if (isCard) {
            bean.setNolike("2");
        } else {
            bean.setNolike("1");
        }
        if (anchor_tab.length() > 0) {
            bean.setAnchor_tab(anchor_tab);
        }
        if (isCard) {
        } else {
            //列表和卡片没有关系的了  各自滑动各自的  offset参数不用传的
//            if (offset.length() > 0) {
//                bean.setOffset(offset);
//            }
        }
        if (page.length() > 0) {
            bean.setPage(page);
        }
        if (publish_type.length() > 0) {
            bean.setPublish_type(publish_type);
        }
        if (area_name.length() > 0) {
            bean.setArea_name(area_name);
        }
        if (plat_id.length() > 0) {
            bean.setPlat_id(plat_id);
        }
        if (anchor_type.length() > 0) {
            bean.setAnchor_type(anchor_type);
        }
        if (pay_low.length() > 0) {
            bean.setPay_low(pay_low);
        }
        if (pay_high.length() > 0) {
            bean.setPay_high(pay_high);
        }
        if (salary_type.length() > 0) {
            bean.setSalary_type(salary_type);
        }
        if (keep_pay.length() > 0) {
            bean.setKeep_pay(keep_pay);
        }
        //游客身份 和有身份请求借口不同
//        if (UserConfig.getInstance().getVisitor().equals("1")) {
        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.timingRecruitList(bean);
//        } else {
//            observable = mRxSerivce.recruitList(bean);
//        }
        Disposable disposable = observable
                .compose(RxHelper.<BossDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     *  急速回复 请求的借口  多了 response_level参数   好多页面调用上面借口，如果改 改动量太大 所以在下面直接增加一个方法
     * @param response_level
     * @param recommend_new
     * @param anchor_tab
     * @param publish_type
     * @param job_method
     * @param isCard
     * @param offset
     * @param page
     * @param area_name
     * @param plat_id
     * @param anchor_type
     * @param pay_low
     * @param pay_high
     * @param salary_type
     * @param keep_pay
     */

    public void recruitList(String response_level, String recommend_new, String anchor_tab, String publish_type, String job_method, boolean isCard, String offset, String page, String area_name, String plat_id, String anchor_type, String pay_low, String pay_high, String salary_type, String keep_pay) {
        RxObserver rxObserver = new RxObserver<BossDataBean>() {
            @Override
            public void onHandleSuccess(BossDataBean mAccountDataBean) {
                mMineFragmentPresenter.recruitList(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        if (response_level.length() > 0) {
            bean.setResponse_level(response_level);
        }

        if (recommend_new.length() > 0) {
            bean.setRecommend_new(recommend_new);
        }
        if (job_method.length() > 0) {
            bean.setJob_method(job_method);
        }
        if (isCard) {
            bean.setNolike("2");
        } else {
            bean.setNolike("1");
        }
        if (anchor_tab.length() > 0) {
            bean.setAnchor_tab(anchor_tab);
        }
        if (isCard) {
        } else {
            //列表和卡片没有关系的了  各自滑动各自的  offset参数不用传的
//            if (offset.length() > 0) {
//                bean.setOffset(offset);
//            }
        }
        if (page.length() > 0) {
            bean.setPage(page);
        }
        if (publish_type.length() > 0) {
            bean.setPublish_type(publish_type);
        }
        if (area_name.length() > 0) {
            bean.setArea_name(area_name);
        }
        if (plat_id.length() > 0) {
            bean.setPlat_id(plat_id);
        }
        if (anchor_type.length() > 0) {
            bean.setAnchor_type(anchor_type);
        }
        if (pay_low.length() > 0) {
            bean.setPay_low(pay_low);
        }
        if (pay_high.length() > 0) {
            bean.setPay_high(pay_high);
        }
        if (salary_type.length() > 0) {
            bean.setSalary_type(salary_type);
        }
        if (keep_pay.length() > 0) {
            bean.setKeep_pay(keep_pay);
        }
        //游客身份 和有身份请求借口不同
//        if (UserConfig.getInstance().getVisitor().equals("1")) {
        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.timingRecruitList(bean);
//        } else {
//            observable = mRxSerivce.recruitList(bean);
//        }
        Disposable disposable = observable
                .compose(RxHelper.<BossDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 职位列表接口
     */

    public void positionList() {
        RxObserver rxObserver = new RxObserver<PositionListBean>() {
            @Override
            public void onHandleSuccess(PositionListBean resultBean) {
                mMineFragmentPresenter.positionList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<PositionListBean>> observable = mRxSerivce.positionList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<PositionListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void NewrecruitList(String recommend_new, String anchor_tab, String publish_type, String job_method, boolean isCard, String offset, String page, String area_name, String plat_id, String anchor_type, String pay_low, String pay_high, String salary_type, String keep_pay) {
        RxObserver rxObserver = new RxObserver<newBossDataBean>() {
            @Override
            public void onHandleSuccess(newBossDataBean mAccountDataBean) {
                mMineFragmentPresenter.NewrecruitList(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        if (recommend_new.length() > 0) {
            bean.setRecommend_new(recommend_new);
        }
        if (job_method.length() > 0) {
            bean.setJob_method(job_method);
        }
        if (isCard) {
            bean.setNolike("2");
        } else {
            bean.setNolike("1");
        }
        if (anchor_tab.length() > 0) {
            bean.setAnchor_tab(anchor_tab);
        }
        if (isCard) {
        } else {
            //列表和卡片没有关系的了  各自滑动各自的  offset参数不用传的
//            if (offset.length() > 0) {
//                bean.setOffset(offset);
//            }
        }
        if (page.length() > 0) {
            bean.setPage(page);
        }
        if (publish_type.length() > 0) {
            bean.setPublish_type(publish_type);
        }
        if (area_name.length() > 0) {
            bean.setArea_name(area_name);
        }
        if (plat_id.length() > 0) {
            bean.setPlat_id(plat_id);
        }
        if (anchor_type.length() > 0) {
            bean.setAnchor_type(anchor_type);
        }
        if (pay_low.length() > 0) {
            bean.setPay_low(pay_low);
        }
        if (pay_high.length() > 0) {
            bean.setPay_high(pay_high);
        }
        if (salary_type.length() > 0) {
            bean.setSalary_type(salary_type);
        }
        if (keep_pay.length() > 0) {
            bean.setKeep_pay(keep_pay);
        }
        //游客身份 和有身份请求借口不同
//        if (UserConfig.getInstance().getVisitor().equals("1")) {
        Observable<BaseResponseEntity<newBossDataBean>> observable = mRxSerivce.NewTimingRecruitList(bean);
//        } else {
//            observable = mRxSerivce.recruitList(bean);
//        }
        Disposable disposable = observable
                .compose(RxHelper.<newBossDataBean>handleObservableResult())
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
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 安卓专用判断是否抢过接口
     *
     * @param resume_id
     */

    public void judgeGrade(String resume_id) {
        RxObserver rxObserver = new RxObserver<JudgeGradeBean>() {
            @Override
            public void onHandleSuccess(JudgeGradeBean resultBean) {
                mMineFragmentPresenter.judgeGrade(resultBean);
            }

            @Override
            public void onHandleComplete() {
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 一键分享接口
     *
     * @param
     */

    public void keyShare() {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.keyShare(bean);
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
                mMineFragmentPresenter.resumeDoCollect();
            }

            @Override
            public void onHandleComplete() {
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
                mMineFragmentPresenter.resumeCancelCollect();
            }

            @Override
            public void onHandleComplete() {
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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


    //    会长沟通主播
    public void linkUpAnchor(String anchor_id, String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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


    public void platfromType() {
        RxObserver rxObserver = new RxObserver<BossPlatBean>() {
            @Override
            public void onHandleSuccess(BossPlatBean mAccountDataBean) {
                mMineFragmentPresenter.platfromType(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<BossPlatBean>> observable = mRxSerivce.timingPlatfromType(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossPlatBean>handleObservableResult())
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
                mMineFragmentPresenter.grabAnchor(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
                mMineFragmentPresenter.grabAnchorOrder(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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


    /**
     * 主播招聘列表导航接口
     */
    public void anchorRecruitList() {
        RxObserver rxObserver = new RxObserver<anchorRecruitListBean>() {
            @Override
            public void onHandleSuccess(anchorRecruitListBean mAccountDataBean) {
                mMineFragmentPresenter.anchorRecruitList(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        Observable<BaseResponseEntity<anchorRecruitListBean>> observable = null;
        observable = mRxSerivce.anchorRecruitList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<anchorRecruitListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 获取求职列表
     */

    public void wantList(String page) {
        RxObserver rxObserver = new RxObserver<wantListBean>() {
            @Override
            public void onHandleSuccess(wantListBean mAccountDataBean) {
                mMineFragmentPresenter.wantList(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        Observable<BaseResponseEntity<wantListBean>> observable = mRxSerivce.wantList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<wantListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 1查看剩余次数 2留电话(需传下面参数) 默认1
     */

    public void leaveAnchor(String is_leave, String anchor_id, String resume_id, String telephone, String bd_num) {
        RxObserver rxObserver = new RxObserver<wantListBean>() {
            @Override
            public void onHandleSuccess(wantListBean mAccountDataBean) {
                mMineFragmentPresenter.leaveAnchor(mAccountDataBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 招募首页接口
     */

    public void conscribeIndex() {
        RxObserver rxObserver = new RxObserver<BossTongGaoBean>() {
            @Override
            public void onHandleSuccess(BossTongGaoBean mAccountDataBean) {
                mMineFragmentPresenter.conscribeIndex(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<BossTongGaoBean>> observable = mRxSerivce.timingConscribeIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossTongGaoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void recruitPage() {
        RxObserver rxObserver = new RxObserver<BossPlatBean>() {
            @Override
            public void onHandleSuccess(BossPlatBean mAccountDataBean) {
                mMineFragmentPresenter.url(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        Observable<BaseResponseEntity<BossPlatBean>> observable = mRxSerivce.recruitPage(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossPlatBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void doCollect(String recruit_id) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mMineFragmentPresenter.doCollect();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.doCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    /**
     * 不喜欢 卡片布局中
     *
     * @param recruit_id
     */
    public void noLike(String recruit_id) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mMineFragmentPresenter.noLike();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.noLike(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    public void cancelCollect(String recruit_id) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mMineFragmentPresenter.cancelCollect();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.cancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 预留电话以及发送形象卡片
     *
     * @param recruit_id
     */
    public void sendSucceed(String type, String recruit_id, String telephone, String image) {
        RxObserver rxObserver = new RxObserver<sendSucceedResonse>() {

            @Override
            public void onHandleSuccess(sendSucceedResonse o) {
                mMineFragmentPresenter.sendSucceed(o);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        bean.setType(type);
        if (type.equals("1")) {
            bean.setTelephone(telephone);
        }

        if (type.equals("2")) {
            if (image.length() > 0) {
                try {
                    bean.setImage(Base64Utils.encodeFile(image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        Observable<BaseResponseEntity<sendSucceedResonse>> observable = mRxSerivce.sendSucceed(bean);
        Disposable disposable = observable
                .compose(RxHelper.<sendSucceedResonse>handleObservableResult())
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
                mMineFragmentPresenter.guildNumber(o);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 分享增加次数
     *
     * @param recruit_id
     */

    public void anchorShare(String recruit_id) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mMineFragmentPresenter.anchorShare();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorShare(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
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
                mMineFragmentPresenter.chatAnchor(o);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 主播拨打电话
     *
     * @param recruit_id
     */

    public void anchorMakeTelephone(String recruit_id) {
        RxObserver rxObserver = new RxObserver<Object>() {

            @Override
            public void onHandleSuccess(Object o) {
                mMineFragmentPresenter.anchorMakeTelephone();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorMakeTelephone(bean);
        Disposable disposable = observable
                .compose(RxHelper.<Object>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //回答列表
    public void collectList(String currPage, String type) {
        RxObserver rxObserver = new RxObserver<CollectBean>() {
            @Override
            public void onHandleSuccess(CollectBean resultBean) {
                mMineFragmentPresenter.getCollect(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        CollectRequestBean bean = new CollectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(currPage);
        bean.setOp(type);
        Observable<BaseResponseEntity<CollectBean>> observable = mRxSerivce.collectList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<CollectBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //回答列表
    public void upList(String currPage) {
        RxObserver rxObserver = new RxObserver<BossDataBean>() {
            @Override
            public void onHandleSuccess(BossDataBean resultBean) {
                mMineFragmentPresenter.recruitList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        CollectRequestBean bean = new CollectRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(currPage);
        Observable<BaseResponseEntity<BossDataBean>> observable = mRxSerivce.upList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //招聘banager图
    public void recruitBanner() {
        RxObserver rxObserver = new RxObserver<BanagetBean>() {
            @Override
            public void onHandleSuccess(BanagetBean resultBean) {
                mMineFragmentPresenter.recruitBanner(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        CollectRequestBean bean = new CollectRequestBean();
        if (UserConfig.getInstance().getToken().length() > 0) {
            bean.setToken(UserConfig.getInstance().getToken());
        }
        Observable<BaseResponseEntity<BanagetBean>> observable = null;
        observable = mRxSerivce.timingBanner(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BanagetBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //主播拨打电话接口
    public void makeCall(String recruit_id) {
        RxObserver rxObserver = new RxObserver<BanagetBean>() {
            @Override
            public void onHandleSuccess(BanagetBean resultBean) {
                mMineFragmentPresenter.makeCall(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<BanagetBean>> observable = mRxSerivce.makeCall(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BanagetBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //分享成功后次数
    public void surplusMakeCall(String recruit_id) {
        RxObserver rxObserver = new RxObserver<BanagetBean>() {
            @Override
            public void onHandleSuccess(BanagetBean resultBean) {
                mMineFragmentPresenter.surplusMakeCall(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        BossBeen bean = new BossBeen();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(recruit_id);
        Observable<BaseResponseEntity<BanagetBean>> observable = mRxSerivce.surplusMakeCall(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BanagetBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 获取简历图片接口
     *
     * @param
     * @param
     */

    public void readPhoto() {
        RxObserver rxObserver = new RxObserver<ReadPhotoBean>() {
            @Override
            public void onHandleSuccess(ReadPhotoBean resultBean) {
                mMineFragmentPresenter.readPhoto(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ReadPhotoBean>> observable = mRxSerivce.readPhoto(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ReadPhotoBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 新版求职列表全部
     *
     * @param
     */

    public void resumeAllList(String page, String data_type) {

        RxObserver rxObserver = new RxObserver<AllListBean>() {
            @Override
            public void onHandleSuccess(AllListBean resultBean) {
                mMineFragmentPresenter.resumeAllList(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        Collect bean = new Collect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPage(page);
        bean.setData_type(data_type);
        Observable<BaseResponseEntity<AllListBean>> observable = mRxSerivce.resumeAllList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AllListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void resumeIndex() {
        RxObserver rxObserver = new RxObserver<ResumeIndexBean>() {
            @Override
            public void onHandleSuccess(ResumeIndexBean resultBean) {
                mMineFragmentPresenter.resumeIndex(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ResumeIndexBean>> observable = mRxSerivce.resumeIndex(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeIndexBean>handleObservableResult())
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
                mMineFragmentPresenter.companyAdd(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
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
     * 获取简历状态
     *
     * @param
     */
    public void resumeState() {
        RxObserver rxObserver = new RxObserver<ResumeIndexBean>() {
            @Override
            public void onHandleSuccess(ResumeIndexBean resultBean) {
                mMineFragmentPresenter.resumeState(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<ResumeIndexBean>> observable = mRxSerivce.resumeState(bean);
        Disposable disposable = observable
                .compose(RxHelper.<ResumeIndexBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 获取招聘详情
     *
     * @param id
     */
    public void getRecruitDetail(String id) {
        RxObserver rxObserver = new RxObserver<RecruitDetailBean>() {
            @Override
            public void onHandleSuccess(RecruitDetailBean resultBean) {
                mMineFragmentPresenter.getRecruitDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        LaunchPatrolBean bean = new LaunchPatrolBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setRecruit_id(id);
        Observable<BaseResponseEntity<RecruitDetailBean>> observable = mRxSerivce.getRecruitDetail(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RecruitDetailBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长剩余发布职位数接口
     */

    public void limitNumber() {
        RxObserver rxObserver = new RxObserver<IsFaceResponse>() {
            @Override
            public void onHandleSuccess(IsFaceResponse resultBean) {
                mMineFragmentPresenter.limitNumber(resultBean);

            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        encyclopediaRequestBean bean = new encyclopediaRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<IsFaceResponse>> observable = mRxSerivce.limitNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.<IsFaceResponse>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 客服电话 & 微信
     *
     * @param
     */

    public void customLink() {

        RxObserver rxObserver = new RxObserver<RequestListBean>() {
            @Override
            public void onHandleSuccess(RequestListBean resultBean) {
                mMineFragmentPresenter.customLink(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mMineFragmentPresenter.requestFailed(message);
            }
        };
        CompanyRequestBean bean = new CompanyRequestBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<RequestListBean>> observable = mRxSerivce.customLink(bean);
        Disposable disposable = observable
                .compose(RxHelper.<RequestListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

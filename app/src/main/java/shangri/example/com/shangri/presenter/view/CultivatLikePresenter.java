package shangri.example.com.shangri.presenter.view;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CommentBean;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;

/**
 * 点赞操作
 * Created by pc on 2017/6/27.
 */

public class CultivatLikePresenter extends BasePresenter<CultivateLikeView> {

    private CultivateLikeView mSofwwareUserView;

    public CultivatLikePresenter(Context context, CultivateLikeView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void createPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.praise();
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
        bean.setArticle_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.praise(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
//    修改职位状态接口

    public void jobStatus(String recruit_id, String status) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.jobStatus();
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
        bean.setRecruit_id(recruit_id);
        bean.setStatus(status);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.jobStatus(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //    修改职位状态接口
    public void commentNum(int type, String id) {
        RxObserver rxObserver = new RxObserver<BannerHomeLookBean>() {
            @Override
            public void onHandleSuccess(BannerHomeLookBean resultBean) {
                mSofwwareUserView.commentNum(resultBean);
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
        bean.setType(type);
        bean.setId(id);
        Observable<BaseResponseEntity<BannerHomeLookBean>> observable = mRxSerivce.commentNum(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BannerHomeLookBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    // 头条（文章）评论
    public void articleComment(String article_id, String comment, String nickname) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.comment();
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
        CommentBean bean = new CommentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(article_id);
        bean.setComment(comment);
        bean.setNickname(nickname);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articleComment(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    // 培训（干货）评论

    public void trainComment(String train_id, String comment, String nickname) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.comment();
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
        CommentBean bean = new CommentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(train_id);
        bean.setComment(comment);
        bean.setNickname(nickname);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.trainComment(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }
    // 头条评论回复

    public void articleReply(String article_id, String comment, String nickname, String reply_to_id, String to_nickname) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.comment();
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
        CommentBean bean = new CommentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(article_id);
        bean.setComment(comment);
        bean.setNickname(nickname);
        bean.setReply_to_id(reply_to_id);
        bean.setTo_nickname(to_nickname);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articleReply(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    // 培训评论回复

    public void trainReply(String train_id, String comment, String nickname, String reply_to_id, String to_nickname) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.comment();
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
        CommentBean bean = new CommentBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setTrain_id(train_id);
        bean.setComment(comment);
        bean.setNickname(nickname);
        bean.setReply_to_id(reply_to_id);
        bean.setTo_nickname(to_nickname);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.trainReply(bean);
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
                mSofwwareUserView.upAnchor();
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
        bean.setAnchor_id(anchor_id);
        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.linkUpAnchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void deletePraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.praise();
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
        bean.setArticle_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articlecancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void deleteCollect(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.collect();
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
        bean.setTrain_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.deletecollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 资讯分享量增加
     *
     * @param
     */

    public void consultShare(String id, String share_type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.consultShare();
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
        bean.setId(id);
        bean.setShare_type(share_type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.consultShare(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void collect(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.collect();
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
        bean.setTrain_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.collect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void residueNumber(String resume_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.residueNumber();
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
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.residueNumber(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //头条点赞
    public void requestPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.praise();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headPraise(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    public void requestCancel(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.praise();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);


        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //头条收藏
    public void requestCollect(String train_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.collect();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    //头条取消收藏
    public void requestCancelCollect(String train_id) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.collect();
                mSofwwareUserView.requestFailed("");
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        HeadCollect bean = new HeadCollect();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(train_id);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.headCancelCollect(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    /**
     * 会长发送招聘公司认证
     *
     * @param
     */
    public void companyAdd(int type, String company_name, String telephone, String company_description) {
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

    public void resumeDetail(String resume_id) {
        RxObserver rxObserver = new RxObserver<ResumeIndexBean>() {
            @Override
            public void onHandleSuccess(ResumeIndexBean resultBean) {
                mSofwwareUserView.resumeDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        ResumeAddBean bean = new ResumeAddBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setResume_id(resume_id);
        Observable<BaseResponseEntity<ResumeIndexBean>> observable = mRxSerivce.resumeDetail(bean);
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
                mSofwwareUserView.getRecruitDetail(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
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

}

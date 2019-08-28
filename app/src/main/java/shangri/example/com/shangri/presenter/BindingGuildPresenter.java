package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.BindingGuildBean;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.JoinGuildBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.view.BindingGuildView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.util.Aes;
import shangri.example.com.shangri.util.Base64Utils;


/**
 * 注册
 * Created by pc on 2017/6/27.
 */

public class BindingGuildPresenter extends BasePresenter<BindingGuildView> {

    private BindingGuildView mSofwwareUserView;

    public BindingGuildPresenter(Context context, BindingGuildView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    public void requestListData() {
        RxObserver rxObserver = new RxObserver<MyGuildListDataBean>() {
            @Override
            public void onHandleSuccess(MyGuildListDataBean resultBean) {
                mSofwwareUserView.myGuildDtaList(resultBean);
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
        Observable<BaseResponseEntity<MyGuildListDataBean>> observable = mRxSerivce.getGuildList(bean);
        Disposable disposable = observable
                .compose(RxHelper.<MyGuildListDataBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }

    //绑定公会
    public void bingGuild(String pingtainame, String guildname,
                          String accountnumber, String password, String ratio) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.onSuccess();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BindingGuildBean bean = new BindingGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlatfrom_name(pingtainame);
        bean.setGuild_name(guildname);
        try {
            Log.d("Debug", "需要加密的数据为" + accountnumber);
            Log.d("Debug", "aes加密后的数据" + Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345"));
            Log.d("Debug", "加密后的base64" + Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_account(Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_password(Base64Utils.encode(Aes.encrypt(password, "jizhoukejizbzjia", "zhibozhijia12345")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bean.setRatio(ratio);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.guildadd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //绑定公会
    public void againAuth(String guild_id, String accountnumber,
                          String password, String ratio) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.onSuccess();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BindingGuildBean bean = new BindingGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        if (ratio.length() > 0) {
            bean.setRatio(ratio);
        }
        try {
            Log.d("Debug", "需要加密的数据为" + accountnumber);
            Log.d("Debug", "aes加密后的数据" + Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345"));
            Log.d("Debug", "加密后的base64" + Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_account(Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_password(Base64Utils.encode(Aes.encrypt(password, "jizhoukejizbzjia", "zhibozhijia12345")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bean.setGuild_id(guild_id);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.againAuth(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //升级公会
    public void guildUpgrade(String guild_id,
                             String accountnumber, String password) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.onSuccess();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        BindingGuildBean bean = new BindingGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        try {
            Log.d("Debug", "需要加密的数据为" + accountnumber);
            Log.d("Debug", "aes加密后的数据" + Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345"));
            Log.d("Debug", "加密后的base64" + Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_account(Base64Utils.encode(Aes.encrypt(accountnumber, "jizhoukejizbzjia", "zhibozhijia12345")));
            bean.setPlatfrom_password(Base64Utils.encode(Aes.encrypt(password, "jizhoukejizbzjia", "zhibozhijia12345")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.guildUpgrade(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //加入公会
    public void joinGuild(String guildId, String uid, String guildname) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {
                mSofwwareUserView.onSuccess();
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guildId);

        bean.setUid(uid);
        bean.setNickname(guildname);

        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.injoguildadd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    //主播快速绑定公会
    public void anchorfastAdd(String platfrom_id, String anchor_id, String anchor_type) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object hostBindingBean) {
                mSofwwareUserView.bingSuccess();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlatfrom_id(platfrom_id);

        bean.setAnchor_id(anchor_id);
        bean.setAnchor_type(anchor_type);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.anchorfastAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //快速绑定公会接口
    public void quickbinding(String plat_id, String plat_name, String guild_name, String q_guild_id, String anchor_id, String table_flag) {
        RxObserver rxObserver = new RxObserver<AddSeccussBean>() {
            @Override
            public void onHandleSuccess(AddSeccussBean resultBean) {
                mSofwwareUserView.guildUpgrade(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setPlat_id(plat_id);
        bean.setTable_flag(table_flag);
        bean.setPlat_name(plat_name);
        bean.setGuild_name(guild_name);
        if (q_guild_id.length() > 0) {
            bean.setQ_guild_id(q_guild_id);
        }
        if (anchor_id.length() > 0) {
            bean.setAnchor_id(anchor_id);
        }
        Observable<BaseResponseEntity<AddSeccussBean>> observable = mRxSerivce.quickbinding(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AddSeccussBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    //查看支持平台接口
    public void supportPlatfrom() {
        RxObserver rxObserver = new RxObserver<SupportFromList>() {
            @Override
            public void onHandleSuccess(SupportFromList resultBean) {
                mSofwwareUserView.SupportFromList(resultBean);
            }

            @Override
            public void onHandleComplete() {
            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        JoinGuildBean bean = new JoinGuildBean();
        bean.setToken(UserConfig.getInstance().getToken());
        Observable<BaseResponseEntity<SupportFromList>> observable = mRxSerivce.supportPlatfrom(bean);
        Disposable disposable = observable
                .compose(RxHelper.<SupportFromList>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void platfromType() {
        RxObserver rxObserver = new RxObserver<BossPlatBean>() {
            @Override
            public void onHandleSuccess(BossPlatBean mAccountDataBean) {
                mSofwwareUserView.platfromType(mAccountDataBean);
//                mMineFragmentPresenter.requestFailed("");
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
        Observable<BaseResponseEntity<BossPlatBean>> observable = mRxSerivce.platfromType(bean);
        Disposable disposable = observable
                .compose(RxHelper.<BossPlatBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);

    }


    /**
     * 上传图片
     */
    public void UploadingImg(int place, String image, int capture) {
        RxObserver rxObserver = new RxObserver<legalIndexBean>() {
            @Override
            public void onHandleSuccess(legalIndexBean resultBean) {
                mSofwwareUserView.Uploading(resultBean);
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
        bean.setPlace(place);
        bean.setCapture(capture);
        try {
            bean.setImage(Base64Utils.encodeFile(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable<BaseResponseEntity<legalIndexBean>> observable = mRxSerivce.resumeUploading(bean);
        Disposable disposable = observable
                .compose(RxHelper.<legalIndexBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

}

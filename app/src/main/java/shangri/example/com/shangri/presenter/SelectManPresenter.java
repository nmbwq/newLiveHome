package shangri.example.com.shangri.presenter;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.base.BasePresenter;
import shangri.example.com.shangri.model.bean.request.AddNewBean;
import shangri.example.com.shangri.model.bean.request.CompanyRequestBean;
import shangri.example.com.shangri.model.bean.request.TaskResponseBean;
import shangri.example.com.shangri.model.bean.request.partSelectRequestBean;
import shangri.example.com.shangri.model.bean.response.AnchorSerchBean;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.taskSelectListBean;
import shangri.example.com.shangri.presenter.view.SelectmanView;
import shangri.example.com.shangri.presenter.view.companyView;

/**
 * 我的个人任务
 * Created by pc on 2017/6/27.
 */

public class SelectManPresenter extends BasePresenter<SelectmanView> {

    private SelectmanView mSofwwareUserView;

    public SelectManPresenter(Context context, SelectmanView view) {
        super(context, view);
        mSofwwareUserView = view;
    }

    /**
     * 为执行人分配任务量
     *
     * @param
     */

    public void taskSelectanchor(String guild_id, String search, String sort, String type, String start_time, String end_time) {

        RxObserver rxObserver = new RxObserver<taskSelectListBean>() {
            @Override
            public void onHandleSuccess(taskSelectListBean resultBean) {
                mSofwwareUserView.taskSelectanchor(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        TaskResponseBean bean = new TaskResponseBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        if (search.length() != 0) {
            bean.setSearch(search);
        }
        if (sort.length() != 0) {
            bean.setSort(sort);
        }
        bean.setType(type);
        bean.setStart_time(start_time);
        bean.setEnd_time(end_time);
        Log.d("Debug", "开始时间" + start_time);
        Log.d("Debug", "结束时间" + end_time);
        Observable<BaseResponseEntity<taskSelectListBean>> observable = mRxSerivce.taskSelectanchor(bean);
        Disposable disposable = observable
                .compose(RxHelper.<taskSelectListBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void cachAdd(String cache_string) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.cacheAdd();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        TaskResponseBean bean = new TaskResponseBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setCache_string(cache_string);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.cachAdd(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void anchorOplayer(String guild_id, String search) {

        RxObserver rxObserver = new RxObserver<AnchorSerchBean>() {
            @Override
            public void onHandleSuccess(AnchorSerchBean resultBean) {
                mSofwwareUserView.anchorOplayer(resultBean);
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        TaskResponseBean bean = new TaskResponseBean();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setGuild_id(guild_id);
        bean.setSearch(search);
        Observable<BaseResponseEntity<AnchorSerchBean>> observable = mRxSerivce.anchorOplayer(bean);
        Disposable disposable = observable
                .compose(RxHelper.<AnchorSerchBean>handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void taskIssue(AddNewBean NewBean) {

        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {
                mSofwwareUserView.Add();
            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {
                mSofwwareUserView.requestFailed(message);
            }
        };
        Log.d("Debug", "到达这里的数据");
        AddNewBean addNewBean = new AddNewBean();
        addNewBean.setToken(UserConfig.getInstance().getToken());
        addNewBean.setGuild_id(NewBean.getGuild_id());
        addNewBean.setTheme(NewBean.getTheme());
        addNewBean.setContent(NewBean.getContent());
        addNewBean.setStart_time(NewBean.getStart_time());
        addNewBean.setEnd_time(NewBean.getEnd_time());
        addNewBean.setExpect_aims(NewBean.getExpect_aims());
        addNewBean.setHide_expect(NewBean.getHide_expect());
        addNewBean.setExpire_remind(NewBean.getExpire_remind());
        addNewBean.setType(NewBean.getType());
        addNewBean.setAssigns(NewBean.getAssigns());
        if (NewBean.getTask_id().length() > 0) {
            addNewBean.setTask_id(NewBean.getTask_id());
        }
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.taskIssue(addNewBean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


}

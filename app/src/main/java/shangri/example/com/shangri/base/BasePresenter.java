package shangri.example.com.shangri.base;

import android.content.Context;

import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.api.RxService;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by chengaofu on 2017/6/19.
 */

public abstract class BasePresenter<T>{
    /**View接口类型的弱引用*/
    protected WeakReference<T> mViewRef; //View接口类型的弱引用
    protected Context mContext ;
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    protected RxService mRxSerivce;

    public BasePresenter(Context context,T view){
        mContext = context;
        mView = view;
        mRxSerivce = RxApi.getInstance().getService(RxService.class);
    }

    /**
     * 添加
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注销
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    /**
     * 建立关联
     * @param view
     */
    public void attachView(T view){
        mViewRef = new WeakReference<>(view); //建立关联
    }

    /**
     * 获取View
     * @return T
     */
    protected T getView(){
        return mViewRef.get(); //获取View
    }

    /**
     * 判断是否与View建立关联
     * @return boolean
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null; //判断是否与View建立关联
    }

    /**
     * 解除关联
     */
    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear(); //解除关联
            mViewRef = null;
            mView = null;
        }
        unSubscribe();
    }

}

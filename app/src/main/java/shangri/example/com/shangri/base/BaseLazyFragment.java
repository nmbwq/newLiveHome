package shangri.example.com.shangri.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import shangri.example.com.shangri.util.NetWorkUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载的Fragment基类
 * Created by chengaofu on 2017/6/21.
 */
public abstract class BaseLazyFragment<V, T extends BasePresenter<V>> extends Fragment {

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted;


    protected boolean isInit = false;//视图是否已经初初始化
    protected boolean isLoad = false;//是否加载


    public BaseController mBaseController;
    protected Unbinder mUnbinder;
    protected T mPresenter;

    public BaseLazyFragment() {
        super();
        //创建EventBus
        mBaseController = new BaseController();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = null;
        if (NetWorkUtil.isNetworkConnected(getActivity())) {
            root = inflater.inflate(getNormalLayoutId(), container, false);
        } else {
            root = inflater.inflate(getErrorLayoutId(), container, false);
        }
        //绑定控件
        mUnbinder = ButterKnife.bind(this, root);
        //注册EventBus
//        mBaseController.register(getActivity());
        //创建Presenter
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }

        return root;
    }

    /**
     * 是否可以加载数据
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        Log.d("Debug", "到达这里几次");
        if (getUserVisibleHint()) {
            loadData();
            Log.d("Debug", "getUserVisibleHint为true");
            isLoad = true;
        } else {
            Log.d("Debug", "getUserVisibleHint为false" + isLoad );
            if (isLoad) {
                loadData();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        //初始化View和数据
        initView();
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
//        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
//            isLoadDataCompleted = true;
//
//        }
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (getUserVisibleHint()) {
//            isLoadDataCompleted = true;
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        //解绑
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        //销毁EventBus
        mBaseController.unregister(getActivity());
        //解除presenter与view之间的关系
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //接收EventBus的消息
    @Subscribe
    public void onMessageEventMain(Message message) {
        mBaseController.onMessageEventMain(getActivity(), message);
    }

    //正常的页面
    protected abstract int getNormalLayoutId();

    //没有网络后加载的页面
    protected abstract int getErrorLayoutId();

    protected abstract T createPresenter();

    //加载视图
    protected abstract void initView();

    //加载数据
    protected abstract void loadData();

}

package shangri.example.com.shangri.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.umeng.analytics.MobclickAgent;

import shangri.example.com.shangri.util.NetWorkUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类fragment
 * Created by chengaofu on 2017/6/20.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    public BaseController mBaseController;
    protected Unbinder mUnbinder;
    protected T mPresenter;

    public BaseFragment() {
        super();
        //创建EventBus
        mBaseController = new BaseController();
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        MobclickAgent.onPageStart("page");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
        MobclickAgent.onPageEnd("page");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = null;
        Log.e("onCreate: ","this Fragment is "+this.getClass() );
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        //初始化View和数据
        initViewsAndEvents();
    }

    @Override
    public void onStart() {
        super.onStart();
//        initViewsAndEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解绑
        mUnbinder.unbind();
        //销毁EventBus
        mBaseController.unregister(getActivity());
        //解除presenter与view之间的关系
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //接收EventBus的消息
    @Subscribe
    public void onMessageEventMain(Message message){
        mBaseController.onMessageEventMain(getActivity(), message);
    }

    //正常的页面
    protected abstract int getNormalLayoutId();
    //没有网络后加载的页面
    protected abstract int getErrorLayoutId();

    protected abstract T createPresenter();
    //加载数据
    protected abstract void initViewsAndEvents();
}

package shangri.example.com.shangri.api;

import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;

/**
 * 观察者
 * Created by chengaofu on 2017/6/19.
 */

public abstract class RxObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T value) {
        Log.e("Wislie", "onNext");
        if (!isDisposed()) {
            onHandleSuccess(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Wislie", "onError:" + e.getMessage());
        //弹出提示信息
        _onError(e);
        //tanchu
        onHandleFailed(e.getMessage());
        //登录失效  重新登录操作
        if (e.getMessage().equals("token失效:-2")) {
            Intent intent = new Intent(GlobalApp.getAppContext(), NewLoginUserActivity.class);
            intent.putExtra("isFromToken", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            GlobalApp.getAppContext().startActivity(intent);
        }
    }

    @Override
    public void onComplete() {
        Log.e("Wislie", "onComplete");
        onHandleComplete();
    }

    public abstract void onHandleSuccess(T t);

    public abstract void onHandleComplete();

    public abstract void onHandleFailed(String message);

    void _onError(Throwable e) {
        if (!NetWorkUtil.isNetworkConnected(GlobalApp.getAppContext())) {
            ToastUtil.showShort("网络不可用");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showShort("请求超时,请稍后再试...");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            ToastUtil.showShort("网络连接异常,请稍后再试...");
        } else if (e instanceof HttpException) {
            ToastUtil.showShort("服务器异常,请稍后再试...");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
//            ToastUtil.showShort("解析错误,请稍后再试...");
        } else {
            if (e.getMessage().equals("token失效:-2")) {
                ToastUtil.showShort("请登录");
            } else {
                ToastUtil.showShort(e.getMessage());
            }

        }

    }
}

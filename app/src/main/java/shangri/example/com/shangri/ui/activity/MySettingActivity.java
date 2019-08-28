package shangri.example.com.shangri.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ezy.boost.update.UpdateManager;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.presenter.SettingPresenter;
import shangri.example.com.shangri.presenter.view.SettingView;
import shangri.example.com.shangri.ui.dialog.CenterConfirmDialog;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.DataCleanManager;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;


/**
 * Created by chengaofu on 2017/6/23.
 * do what to 2017/6/23
 */

public class MySettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {

    @BindView(R.id.switch_is_open)
    Button switchIsOpen;
    @BindView(R.id.switch_wifi)
    TextView mIsWifi;
    @BindView(R.id.setting_pwd)
    TextView mSettingPwd;
    @BindView(R.id.cache_clear)
    TextView mCacheClear;
    @BindView(R.id.tv_bound_state)
    TextView tvBoundState;
    @BindView(R.id.rl_bound_wx)
    RelativeLayout rlBoundWx;
    private RxPermissions rxPermissions;
    private static final String TAG = "MySettingActivity";
    private int wxinfoId;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        rxPermissions = new RxPermissions(this);
        if (UserConfig.getInstance().isWifiLimited()) {
            mIsWifi.setVisibility(View.VISIBLE);
            switchIsOpen.setBackgroundResource(R.mipmap.ic_wdsz_wifion);
        } else {
            mIsWifi.setVisibility(View.GONE);
            switchIsOpen.setBackgroundResource(R.mipmap.ic_wdsz_wifioff);
        }

        Log.i(TAG, "initViewsAndEvents: wxinfoId" + wxinfoId);
        wxinfoId = Integer.parseInt(UserConfig.getInstance().getWxinfoId());
        if (wxinfoId == 0) {
            tvBoundState.setText("未绑定");
        } else {
            tvBoundState.setText("已绑定");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (UserConfig.getInstance().getPwdIsSet() || UserConfig.getInstance().getLoginModel() == 2) { //已设置了密码
//            mSettingPwd.setText("修改密码");
//        } else {
        mSettingPwd.setText("设置新密码");
//        }
        clsan();
    }

    public void clsan() {
        String totalCacheSize = null;
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(getBaseContext());
            mCacheClear.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.setting_back, R.id.switch_is_open, R.id.rl_pass, R.id.rl_clsan,
            R.id.rl_updata, R.id.rl_my, R.id.rl_wx, R.id.tv_exit,
            R.id.rl_bound_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_pass:
                ActivityUtils.startActivity(this, UpdatePwdActivity.class); //修改密码

                break;
            case R.id.switch_is_open:
                isOpen();
                break;
            case R.id.rl_clsan:   //清除缓存
                CenterConfirmDialog mCenterConfirmDialog = new CenterConfirmDialog("确定要清除缓存");
                mCenterConfirmDialog.setCallBack(new CenterConfirmDialog.CallBack() {
                    @Override
                    public void confirm(String message) {
                        DataCleanManager.clearAllCache(getApplication()); //清理垃圾有延时
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clsan();//清理完text显示为0
                                ToastUtil.showShort("清除缓存已完成");
                            }
                        }, 1000);
                    }
                });
                mCenterConfirmDialog.show(this.getSupportFragmentManager());

//                new ClearCachePopopWindow(this, new ClearCacheListener() {
//                    @Override
//                    public void clearCache() {
//                        DataCleanManager.clearAllCache(getApplication()); //清理垃圾有延时
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                clsan();//清理完text显示为0
//                            }
//                        }, 1000);
//
//                    }
//                });
                break;
            case R.id.rl_updata:  //检查更新
                mPresenter.checkVersion();
                break;
            case R.id.rl_bound_wx:  //绑定微信
                //   ActivityUtils.startActivity(this, BoundTelActivity.class);
                break;
            case R.id.rl_my:  //关于我们
                startActivity(new Intent(this, AcitivityAboutUs.class));
                break;
            case R.id.rl_wx:  //微信关注
                startActivity(new Intent(this, ActivityWeiXin.class));
                break;
            case R.id.tv_exit:  //退出
                logout();
                break;
        }
    }

    public void isOpen() {
        boolean isWifiLimited = UserConfig.getInstance().isWifiLimited(); //获取当前状态
        UserConfig.getInstance().setWifiLimited(!isWifiLimited); //改变当前状态

        if (UserConfig.getInstance().isWifiLimited()) {
            mIsWifi.setVisibility(View.VISIBLE);
            switchIsOpen.setBackgroundResource(R.mipmap.ic_wdsz_wifion);
        } else {
            mIsWifi.setVisibility(View.GONE);
            switchIsOpen.setBackgroundResource(R.mipmap.ic_wdsz_wifioff);
        }

    }

    @Override
    public void checkVersion(final List<AppVersionBean> resultBean) { //版本返回信息
        if (resultBean != null && resultBean.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("检测到新版本!");
            builder.setMessage("版本号: " + resultBean.get(0).getCode());
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("马上更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(@NonNull Boolean aBoolean) throws Exception {
                                    if (aBoolean) {
                                        UpdateManager.create(getBaseContext()).setUrl(resultBean.get(0).getUrl()).check();
                                    } else {
                                        ToastUtil.showLong("下载应用需要文件写入权限哦~");
                                    }
                                }
                            });
                }
            });
            builder.show();
        }
    }

    @Override
    public void logout() { //退出
        UserConfig.getInstance().removeKey(UserConfig.APP_TOKEN);
//        UserConfig.getInstance().removeKey(UserConfig.USER_ID);
//        UserConfig.getInstance().removeKey(UserConfig.USER_MOBILE);
//        UserConfig.getInstance().removeKey(UserConfig.USER_AVATAR);
//        UserConfig.getInstance().removeKey(UserConfig.USER_GENDER);
//        UserConfig.getInstance().removeKey(UserConfig.USER_GENDER);
//        UserConfig.getInstance().removeKey(UserConfig.USER_ENABLE);
//        UserConfig.getInstance().removeKey(UserConfig.USER_ROLEID);
//        UserConfig.getInstance().removeKey(UserConfig.WIFI_LIMITED);
//        UserConfig.getInstance().removeKey(UserConfig.USER_PWD_IS_SET);
//        UserConfig.getInstance().removeKey(UserConfig.WXINFO_ID);
        SharedPreferences sharedPreferences = getSharedPreferences("getnowTime", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("date", "");
        editor.commit();//提交修改
        //
        UserConfig.getInstance().setRole("");
        UserConfig.getInstance().setToken("");
        //如果音乐在播放就停止
        if (GlobalApp.getMediaPlayer().isPlaying()) {
            GlobalApp.getMediaPlayer().pause();
        }
        Intent intent = new Intent(this, NewLoginUserActivity.class).putExtra("isFromToken", true);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    @Override
    public void requestFailed(String message) {
        if (TextUtils.isEmpty(message)) return;
        if (message.contains(String.valueOf(Constant.CODE_100027))) {
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

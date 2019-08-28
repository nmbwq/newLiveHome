package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.ui.dialog.WifiDialog;
import shangri.example.com.shangri.util.ActivityManager;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by chengaofu on 2017/7/11.
 */

public class CustomJCVideoPlayerStandard extends JCVideoPlayerStandard {

    private Context mContext;

    public CustomJCVideoPlayerStandard(Context context) {
        super(context);
        mContext = context;
    }

    public CustomJCVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == fm.jiecao.jcvideoplayer_lib.R.id.start || i == fm.jiecao.jcvideoplayer_lib.R.id.thumb) {
            Log.i(TAG, "onClick start [" + this.hashCode() + "] ");
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(getContext(), getResources().getString(fm.jiecao.jcvideoplayer_lib.R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentState == CURRENT_STATE_NORMAL || currentState == CURRENT_STATE_ERROR) {

                if (!url.startsWith("file") && !JCUtils.isWifiConnected(getContext()) &&
                        !WIFI_TIP_DIALOG_SHOWED && UserConfig.getInstance().isWifiLimited()) {
//                    showWifiDialog();
//                    ToastUtil.showShort("您已选择只在wife下播放");
                    WifiDialog dialog = new WifiDialog();
                    BaseActivity ac = (BaseActivity) ActivityManager.getInstance().getLastActivity();
                    dialog.show(ac.getSupportFragmentManager());
                    return;
                }
                prepareMediaPlayer();
                onEvent(currentState != CURRENT_STATE_ERROR ? JCUserAction.ON_CLICK_START_ICON : JCUserAction.ON_CLICK_START_ERROR);
            } else if (currentState == CURRENT_STATE_PLAYING) {
                onEvent(JCUserAction.ON_CLICK_PAUSE);
                Log.d(TAG, "pauseVideo [" + this.hashCode() + "] ");
                JCMediaManager.instance().mediaPlayer.pause();
                setUiWitStateAndScreen(CURRENT_STATE_PAUSE);
            } else if (currentState == CURRENT_STATE_PAUSE) {
                onEvent(JCUserAction.ON_CLICK_RESUME);
                JCMediaManager.instance().mediaPlayer.start();
                setUiWitStateAndScreen(CURRENT_STATE_PLAYING);
            } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
                onEvent(JCUserAction.ON_CLICK_START_AUTO_COMPLETE);
                prepareMediaPlayer();
            }

        } else {
            super.onClick(v);
        }

    }
}

package shangri.example.com.shangri.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;


import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.ui.FaceLivenessActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import shangri.example.com.shangri.model.bean.event.IdChangeBean;
import shangri.example.com.shangri.ui.dialog.DefaultDialog;

public class FaceLivenessExpActivity extends FaceLivenessActivity {

    private DefaultDialog mDefaultDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLivenessCompletion(FaceStatusEnum status, String message, HashMap<String, String> base64ImageMap) {
        super.onLivenessCompletion(status, message, base64ImageMap);
        if (status == FaceStatusEnum.OK && mIsCompletion) {
            Log.d("Debug", "到达这里" + base64ImageMap.size());
            Log.d("Debug", "返回的数据为" + base64ImageMap.toString());
            Log.d("Debug", "返回第一条数据" + base64ImageMap.get("bestImage0"));
            Log.d("Debug", "返回第二条数据" + base64ImageMap.get("bestImage1"));
//            String Liveimage= base64ImageMap.get("bestImage0");
            EventBus.getDefault().post(new IdChangeBean(base64ImageMap.get("bestImage0")));
//            showMessageDialog("活体检测", "检测成功");
            if (mDefaultDialog != null) {
                mDefaultDialog.dismiss();
                finish();
            } else {
                finish();
            }
        } else if (status == FaceStatusEnum.Error_DetectTimeout ||
                status == FaceStatusEnum.Error_LivenessTimeout ||
                status == FaceStatusEnum.Error_Timeout) {
            showMessageDialog("活体检测", "采集超时");
        }
    }

    private void showMessageDialog(String title, String message) {
        if (mDefaultDialog == null) {
            DefaultDialog.Builder builder = new DefaultDialog.Builder(this);
            builder.setTitle(title).
                    setMessage(message).
                    setNegativeButton("确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDefaultDialog.dismiss();
                                    finish();
                                }
                            });
            mDefaultDialog = builder.create();
            mDefaultDialog.setCancelable(true);
        }
        mDefaultDialog.dismiss();
        mDefaultDialog.show();
    }

    @Override
    public void finish() {
        super.finish();
    }

}

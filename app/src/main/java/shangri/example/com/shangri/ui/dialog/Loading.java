package shangri.example.com.shangri.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import shangri.example.com.shangri.R;

/**
 * Created by chengaofu on 17/7/5.
 */
public class Loading {
    private static Dialog mDialog;
    public static void show(Context context) {
        dismiss();
        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.loading_progress);
        mDialog.setCanceledOnTouchOutside(false);//dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mDialog.getWindow().setAttributes(lp);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public static void show(Context context,Boolean Cancelable) {
        dismiss();
        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.loading_progress);
        mDialog.setCancelable(Cancelable);//dialog弹出后会点击屏幕或物理返回键，dialog不消失
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mDialog.getWindow().setAttributes(lp);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public static void dismiss() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }
    }
}

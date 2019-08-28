package shangri.example.com.shangri.util;

import shangri.example.com.shangri.ui.adapter.PatrolAdapter;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CallBackUtils {

    public static PatrolAdapter.onClick mCallBack;

    public static void setCallBack(PatrolAdapter.onClick callBack) {
        mCallBack = callBack;
    }

}

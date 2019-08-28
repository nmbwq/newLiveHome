package shangri.example.com.shangri.util;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import shangri.example.com.shangri.R;


/**
 * 创    建:  lt  2018/1/8--11:51
 * 作    用:  对话框工具类
 * 注意事项:
 */

public class CommontDialog {


    /**
     * 详情弹框显示
     *
     * @return
     */
    public static AlertDialog homeDialog1(Context context, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_dialog_layout, null);
//        TextView mCancel = (TextView) view.findViewById(R.id.tv_commit);
//        TextView tv_benjin = (TextView) view.findViewById(R.id.tv_benjin);
//        TextView tv_weiyuejin = (TextView) view.findViewById(R.id.tv_weiyuejin);
//        TextView tv_sum = (TextView) view.findViewById(R.id.tv_sum);
//        mCancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(dip2px(context, 300), -1);
        dialog.setCanceledOnTouchOutside(false);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dp尺寸
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}

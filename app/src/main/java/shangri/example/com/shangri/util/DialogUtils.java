package shangri.example.com.shangri.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import shangri.example.com.shangri.R;

public class DialogUtils {


    @SuppressLint("ResourceType")
    public static AlertDialog showDialog(Activity activity, int need_base, int need_zcs, int need_cks) {


        View view = LayoutInflater.from(activity).inflate(R.layout.dialog, null);

        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        TextView tv_msg1 = (TextView) view.findViewById(R.id.tv_msg1);
        TextView tv_msg2 = (TextView) view.findViewById(R.id.tv_msg2);
        tv_msg.setText("1，满" + need_base + "波币可提现");
        tv_msg1.setText("2，至少成功邀请" + need_zcs + "个好友注册");
        tv_msg2.setText("3，简历至少被" + need_cks + "个公会获取过");
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        final Window window = alertDialog.getWindow();
        window.setContentView(view);
        window.setLayout(
                window.getContext().getResources().getDisplayMetrics().widthPixels,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        return alertDialog;
    }


    @SuppressLint("ResourceType")
    public static AlertDialog showDialog1(Activity activity, int type, int need_base, int need_zcs, int need_cks) {


        View view = LayoutInflater.from(activity).inflate(R.layout.dialog1, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        //type 1 查看提现规则 title隐藏  2是不满足提现规则
        if (type==1){
            tv_title.setVisibility(View.GONE);
        }else {
            tv_title.setVisibility(View.VISIBLE);
        }
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        TextView tv_msg1 = (TextView) view.findViewById(R.id.tv_msg1);
        TextView tv_msg2 = (TextView) view.findViewById(R.id.tv_msg2);
        tv_msg.setText("1，满" + need_base + "波币可提现");
        tv_msg1.setText("2，至少成功邀请" + need_zcs + "个好友注册");
        tv_msg2.setText("3，简历至少被" + need_cks + "个公会获取过");
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        final Window window = alertDialog.getWindow();
        window.setContentView(view);
        window.setLayout(
                window.getContext().getResources().getDisplayMetrics().widthPixels,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        return alertDialog;
    }
}

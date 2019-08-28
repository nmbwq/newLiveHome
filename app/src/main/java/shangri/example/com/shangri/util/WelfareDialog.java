package shangri.example.com.shangri.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;
import com.loveplusplus.update.DownloadService;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.ui.activity.SoftwareActivity;

public class WelfareDialog {
//    /**
//     * 注册好礼领取弹窗
//     *
//     * @param context
//     */
//    public static Dialog WelfareDialog1(final Context context, View.OnClickListener commitListener) {
//        LayoutInflater inflaterDl = LayoutInflater.from(context);
//        RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.layout_welfare_dialog, null);
//        //对话框
//        final Dialog dialogs = new AlertDialog.Builder(context, R.style.ActionDialogStyle).create();//
//
//        dialogs.getWindow().setContentView(layout);
//        dialogs.setCancelable(false);
//        dialogs.setCanceledOnTouchOutside(false);
//        ImageView alert_ok = layout.findViewById(R.id.alert_ok);
//        alert_ok.setOnClickListener(commitListener);
//        dialogs.show();
//        return dialogs;
//    }

//    /**
//     * 注册好礼提现弹窗
//     *
//     * @param context
//     */
//    public static Dialog WelfareDialog2(final Context context, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
//        LayoutInflater inflaterDl = LayoutInflater.from(context);
//        RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(R.layout.layout_welfare_dialog1, null);
//        //对话框
//        final Dialog dialogs = new AlertDialog.Builder(context, R.style.ActionDialogStyle).create();//
//
//        dialogs.getWindow().setContentView(layout);
//        dialogs.setCancelable(false);
//        dialogs.setCanceledOnTouchOutside(false);
//
//        ImageView im_commit = layout.findViewById(R.id.im_commit);
//        ImageView cancel = layout.findViewById(R.id.cancel);
//        im_commit.setOnClickListener(commitListener);
//        cancel.setOnClickListener(cancelListener);
//
//        dialogs.show();
//        return dialogs;
//
//
//    }

    /**
     * 注册好礼领取弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog1(Context context, View.OnClickListener commitListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_welfare_dialog, null);
        ImageView alert_ok = view.findViewById(R.id.alert_ok);
        alert_ok.setOnClickListener(commitListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
//        //显示在底部  （布局 much_parent 布局居中显示也可以的）
//        window.setGravity(Gravity.BOTTOM);
        //设置动画
        window.setWindowAnimations(R.style.dialog_anim);
        dialog.setCanceledOnTouchOutside(false);
        // 方法二：
        dialog.setCancelable(false);//调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
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
     * 抢他弹窗  type
     *
     * @param context
     */
    public static AlertDialog RobHimeDialog(Context context, GrabAnchorBean mAccountDataBean, String name, List<String> type_name, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_robhim_dialog, null);
        ImageView cancel = view.findViewById(R.id.cancel);

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_type = view.findViewById(R.id.tv_type);
        TextView tv_type1 = view.findViewById(R.id.tv_type1);
        TextView tv_type2 = view.findViewById(R.id.tv_type2);

        TextView tv_consume_bb = view.findViewById(R.id.tv_consume_bb);

        ImageView im_is_activity = view.findViewById(R.id.im_is_activity);
        TextView is_activity_text = view.findViewById(R.id.is_activity_text);
        TextView tv_commit = view.findViewById(R.id.tv_commit);

        tv_commit.setOnClickListener(commitListener);
        cancel.setOnClickListener(cancelListener);
//        是否有活动 1是 2否
        if (mAccountDataBean.getIs_activity() == 1) {
            //每天找抢ta 超过次数  活动图片以及文字隐藏
            if (mAccountDataBean.getXh_number() >= mAccountDataBean.getActivity().getNumber_days()) {
                im_is_activity.setVisibility(View.GONE);
                is_activity_text.setVisibility(View.GONE);
                tv_consume_bb.setText(mAccountDataBean.getXf_bd() + "");
            } else {
                im_is_activity.setVisibility(View.VISIBLE);
                is_activity_text.setVisibility(View.VISIBLE);
                tv_consume_bb.setText(mAccountDataBean.getActivity().getG_price() + "");
                String str1 = "(活动期间：享受活动价" + "<font color='#ffffff'>" + mAccountDataBean.getActivity().getNumber_days() + "次/天" + "</font>" + ")";
                is_activity_text.setTextSize(12);
                is_activity_text.setText(Html.fromHtml(str1));
            }
        } else {
            im_is_activity.setVisibility(View.GONE);
            is_activity_text.setVisibility(View.GONE);
            tv_consume_bb.setText(mAccountDataBean.getXf_bd() + "");
        }


        tv_name.setText(name + "");
        if (type_name.size() == 0) {
            tv_type.setVisibility(View.GONE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (type_name.size() == 1) {
            tv_type.setText(type_name.get(0) + "");
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (type_name.size() == 2) {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
            tv_type.setText(type_name.get(0) + "");
            tv_type1.setText(type_name.get(1) + "");
        } else {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.VISIBLE);
            tv_type.setText(type_name.get(0) + "");
            tv_type1.setText(type_name.get(1) + "");
            tv_type2.setText(type_name.get(2) + "");
        }

        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        //        //显示在底部  （布局 much_parent 布局居中显示也可以的）
        window.setGravity(Gravity.BOTTOM);
        //设置动画
        window.setWindowAnimations(R.style.dialog_anim);

        window.setContentView(view);
        window.setLayout(-1, -2);
        //true 点击布局以外的地方  dialog消失     false  点击外面的部分  不消失
        dialog.setCanceledOnTouchOutside(true);
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
     * 签到成功弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog4(Context context, String signMoney, View.OnClickListener commitListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sign_dialog, null);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_number = view.findViewById(R.id.tv_number);
        tv_commit.setOnClickListener(commitListener);
        tv_number.setText(signMoney + "");
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        //点击布局外面消失不消失
        dialog.setCanceledOnTouchOutside(true);
        // 点击返回键 dialog消失不消失
        dialog.setCancelable(true);
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
     * 注册好礼提现弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog2(Context context, NoticesResponseBean resultBean, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_welfare_dialog1, null);
        ImageView im_commit = view.findViewById(R.id.im_commit);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv_number = view.findViewById(R.id.tv_number);


        RelativeLayout rl_info = view.findViewById(R.id.rl_info);
        if (UserConfig.getInstance().getRole().equals("2")) {
            tv1.setText("注：您在直播之家获取的波");
            tv2.setText("币可直接提现成现金哦！");
            rl_info.setBackground(context.getResources().getDrawable(R.mipmap.hongbaodkbb));
            im_commit.setImageDrawable(context.getResources().getDrawable(R.mipmap.hongbaotixianan));
            tv_number.setText("");
        } else {
            tv1.setText("注：您可以使用波豆联系您");
            tv2.setText("心仪的主播哦！");
            rl_info.setBackground(context.getResources().getDrawable(R.mipmap.hongbaodk));
            im_commit.setImageDrawable(context.getResources().getDrawable(R.mipmap.hongbaotixianano));
            tv_number.setText(resultBean.getGet_bd() + "");
        }

        ImageView cancel = view.findViewById(R.id.cancel);
        im_commit.setOnClickListener(commitListener);
        cancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -1);
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


    /**
     * 主播升级弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog3(Context context, int type, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_welfare_dialog3, null);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_state = view.findViewById(R.id.tv_state);
        ImageView im_state = view.findViewById(R.id.im_state);
        ImageView cancel = view.findViewById(R.id.cancel);
//        1初级 2中级 3高级
        switch (type) {
            case 1:
                im_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.sdj_cj));
                tv_state.setText("初级经纪人");
                break;
            case 2:
                im_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.sdj_zj));
                tv_state.setText("中级经纪人");
                break;
            case 3:
                im_state.setImageDrawable(context.getResources().getDrawable(R.mipmap.sdj_gj));
                tv_state.setText("高级经纪人");
                break;
        }
        tv_commit.setOnClickListener(commitListener);
        cancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -1);
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
     * 登录是否弹出赠送好礼
     *
     * @param context
     */
    public static AlertDialog WelfareDialog5(Context context, String number, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_welfare_dialog5, null);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_number = view.findViewById(R.id.tv_number);
        ImageView cancel = view.findViewById(R.id.cancel);
        tv_number.setText("+" + number + "波豆");
        tv_commit.setOnClickListener(commitListener);
        cancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -1);
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
     * 全局弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog6(Context context, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_welfare_dialog6, null);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        TextView tv_content = view.findViewById(R.id.tv_content);
        ImageView cancel = view.findViewById(R.id.cancel);
        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_content.setText("您好，我们已为您推送了一名优质主播，要记得及时查看哦");
        } else {
            tv_content.setText("您好，我们已为您推送了一家优质公会，要记得及时查看哦");
        }
        tv_commit.setOnClickListener(commitListener);
        cancel.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -1);
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
     * 提示选择身份弹窗
     *
     * @param context
     */
    public static AlertDialog WelfareDialog7(Context context, View.OnClickListener commitListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.compact_add_gonghui9, null);
        //设置各个控件的点击响应
        TextView tv_next = view.findViewById(R.id.tv_next);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_content = view.findViewById(R.id.tv_content);
        TextView tv_content1 = view.findViewById(R.id.tv_content1);
        tv_content1.setText("尚未领取身份");
        String str1 = "你没有领取身份,无法进行招聘操作哦！赶紧领取自己的身份吧！";
        tv_content.setTextSize(15);
        tv_content.setText(Html.fromHtml(str1));
        tv_next.setText("马上领取身份");
        tv_cancle.setText("不,谢谢");
        tv_next.setOnClickListener(commitListener);
        tv_cancle.setOnClickListener(cancelListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        window.setLayout(-1, -1);
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


}

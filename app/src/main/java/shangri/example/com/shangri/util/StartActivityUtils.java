/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package shangri.example.com.shangri.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.ui.activity.AddJobActivity;
import shangri.example.com.shangri.ui.activity.BoBiActivity;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.ui.activity.MainActivity;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.activity.SoftwareActivity;
import shangri.example.com.shangri.ui.activity.VirtualCoinActivity;

/**
 * This provides methods to help Activities load their UI.
 */
public class StartActivityUtils {
    /**
     * 跳转到登录界面
     *
     * @param
     * @param
     */
    public static void startActivity() {
        ToastUtil.showShort("请登录");
        Intent intent = new Intent(GlobalApp.getAppContext(), NewLoginUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GlobalApp.getAppContext().startActivity(intent);
    }


    /**
     * 跳转到选择身份界面
     *
     * @param
     * @param
     */

    static AlertDialog dialog2;
    public static void startSelectRoleActivity(final  Context context) {
        dialog2 = WelfareDialog.WelfareDialog7(context, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SoftwareActivity.class);
                GlobalApp.getAppContext().startActivity(intent);
                dialog2.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });

    }

    /**
     * 我的页面和报表界面跳转的登录页面    返回键的操作是跳转到招聘界面 而不是返回操作
     *
     * @param
     * @param
     */
    public static void startActivity1() {
        ToastUtil.showShort("请登录");
        Intent intent = new Intent(GlobalApp.getAppContext(), NewLoginUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("isFromToken", true);
        GlobalApp.getAppContext().startActivity(intent);
    }


}

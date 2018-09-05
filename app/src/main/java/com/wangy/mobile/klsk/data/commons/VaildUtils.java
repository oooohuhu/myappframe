package com.wangy.mobile.klsk.data.commons;

import android.content.Intent;
import android.text.TextUtils;

import com.wangy.mobile.klsk.application.HApplication;
import com.wangy.mobile.klsk.data.entity.UserInfo;
import com.wangy.mobile.klsk.ui.activity.LoginActivity;

/**
 * Created by xueqili on 2018/6/30.
 */

public class VaildUtils {

    public static boolean isLogin() {
        boolean flag = false;
        if (!TextUtils.isEmpty(UserInfo.getUserInfo().getUserId())) {

            flag = true;
        } else {
           Intent intent = new Intent(HApplication.getInstances().getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            HApplication.getInstances().getApplicationContext().startActivity(intent);
        }
        return flag;
    }

}

package com.wangy.mobile.klsk.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.wangy.mobile.klsk.data.entity.BaseModel;
import com.wangy.mobile.klsk.data.entity.UserInfo;
import com.wangy.mobile.klsk.ui.view.LoginView;


import java.io.File;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xueqili on 2018/1/8.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        attachView(view);
    }

    //正则表达式：验证手机号
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    private String id;//获取短信验证码接口 的 “data”字段值

    //登陆测试
    public void login(final String userId, final String password) {
        if (TextUtils.isEmpty(userId)) {
            getView().error("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getView().error("密码不能为空");
            return;
        }
        getView().showProgressBar("正在登陆请稍后");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("password", password);
//        String str = Key64.encrypt(jsonObject.toJSONString());
//        addSubscription(apiStores.login(Key64.encrypt(jsonObject.toJSONString())),
//                o -> {
//                    getView().closeProgressBar();
//
//                });

    }




    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isTwicePwdEquals(String pwd, String pwdAgain) {
        return pwd.equals(pwdAgain);
    }
}

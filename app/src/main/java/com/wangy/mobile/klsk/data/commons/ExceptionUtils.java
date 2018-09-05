package com.wangy.mobile.klsk.data.commons;

import com.wangy.mobile.klsk.ui.view.BaseView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by xueqili on 2018/6/30.
 */

public class ExceptionUtils {

    public static void handleException(Object e, BaseView view) {
        view.closeProgressBar();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            try {
                msg = httpException.response().errorBody().string();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (code == 504)
                msg = "网络不给力";
            if (code == 404)
                msg = "服务器异常，请稍后重试...";
            if (code == 500)
                msg = "服务器错误，请稍后重试...";
            view.error(msg);
        } else if (e instanceof ConnectException) {
            view.netError();
        } else if (e instanceof SocketTimeoutException) {
            view.netError();
        } else if (e instanceof UnknownHostException) {
            view.netError();
        } else {
            view.error("系统错误，请稍后重试");
        }
    }
}

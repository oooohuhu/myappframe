package com.wangy.mobile.klsk.application;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.vondear.rxtool.RxTool;
import com.wangy.mobile.klsk.R;
import com.zxy.recovery.core.Recovery;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xueqili on 2018/6/22.
 */

public class HApplication extends MultiDexApplication {
    public static HApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .recoverEnabled(true)
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                .init(this);

        //android 7.0系统解决拍照的问题

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StrictMode.VmPolicy.Builder svBuilder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(svBuilder.build());
            svBuilder.detectFileUriExposure();
        }

        RxTool.init(this);

        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

    }

    public static RequestOptions getRequestOptions() {
        return new RequestOptions()
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.img_default)
                .error(R.mipmap.img_default);
        // .priority(Priority.HIGH);

    }

    public static HApplication getInstances() {
        return instances;
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }
}

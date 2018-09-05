package com.wangy.mobile.klsk.ui.activity.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.vondear.rxui.view.dialog.RxDialogLoading;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 皓然 on 2017/8/6.
 */

public abstract class BaseFragmentActivity extends RxAppCompatActivity {
    View NightModel;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;
    protected List<Fragment> fragments;
    RxDialogShapeLoading dialogLoading;
    protected boolean isTrans;

    public boolean isTrans() {
        return isTrans;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (isTransparent) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public void selectTab(int num) {
        fragmentTransaction = fragmentManager.beginTransaction();
        try {
            for (int i = 0; i < fragments.size(); i++) {
                fragmentTransaction.hide(fragments.get(i));
            }
            fragmentTransaction.show(fragments.get(num));
        } catch (ArrayIndexOutOfBoundsException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    protected abstract void initView();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }


    public abstract int getContentViewId();

    /**
     * 弹出加载框
     */
    public void showProgressBar(String... msg) {
        dialogLoading = new RxDialogShapeLoading(this);
        dialogLoading.show();
    }

    /**
     * 关闭加载框
     */
    public void closeProgressBar() {
        if (dialogLoading != null)
            dialogLoading.dismiss();
    }

}

package com.wangy.mobile.klsk.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wangy.mobile.klsk.R;
import com.wangy.mobile.klsk.presenter.BasePresenter;
import com.wangy.mobile.klsk.ui.custom.LoadingDialog;
import com.wangy.mobile.klsk.ui.view.BaseView;
import com.wangy.mobile.klsk.utils.IToast;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;
import java.util.concurrent.TimeUnit;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


/**
 * Created by lxq on 2018/6/21.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {


    protected View NightModel;
    protected boolean isNight;

    private ConnectivityManager manager;
    //未指定类型的Presenter
    protected P mPresenter;
    //初始化Presenter

    protected boolean isTrans;

    //protected abstract void initPresenter(Intent intent);

    //设置布局
    protected abstract int getLayout();

    //初始化布局
    protected abstract void initView();

    Unbinder binder;

    protected Activity mActivity;

    protected SweetAlertDialog sweetAlertDialog;
    RxDialogShapeLoading dialogLoading;

    private long delayTime = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(getLayout());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
        //初始化ButterKnife
        binder = ButterKnife.bind(this);
        //initPresenter(getIntent());
        mPresenter = createPresenter();
        initView();
        mActivity = this;
        initDialog();

    }

    private void initDialog() {
        sweetAlertDialog = new SweetAlertDialog(this);

    }

    public void showDialog(String title, int Type) {
        if (sweetAlertDialog != null) {
            sweetAlertDialog.changeAlertType(Type);
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.setTitleText(title);
            sweetAlertDialog.show();
        }
    }

    public void showDialog(int resource, int Type) {

        String content = getResources().getString(resource);
        if (TextUtils.isEmpty(content))
            return;
        if (sweetAlertDialog != null) {
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.changeAlertType(Type);
            sweetAlertDialog.setTitleText(content);
            sweetAlertDialog.show();
        }
    }

    public void dismissDialog(Runnable deylayTask) {
        if (sweetAlertDialog != null) {
            Observable.timer(delayTime, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {

                @Override
                public void accept(Long l) throws Exception {
                    sweetAlertDialog.dismiss();
                    if (deylayTask != null)
                        deylayTask.run();
                }
            });

        }
    }

    public void delayTime(long delayTime, Runnable task) {

        Observable.timer(delayTime, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long l) throws Exception {
                task.run();
            }
        });
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
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.base_bar));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }


    protected abstract P createPresenter();

    //设置打印方法
    public void showToast(String text) {
        IToast.makeText(this, text, 1000).show();
        //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /***
     * 获取屏幕宽度
     * @return 屏幕宽度（px）
     */
    public int getMobileWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度(px)
     */
    public int getMobileHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        return height;
    }

    /**
     * 获取状态栏高度
     *
     * @return 高度（px）
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resultId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resultId > 0) {
            result = getResources().getDimensionPixelSize(resultId);
        }
        return result;
    }

    /**
     * 检查网络
     *
     * @return 是否有网络
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

    /**
     * 网络错误页面
     */
    @Override
    public void netError() {
        if (sweetAlertDialog.isShowing())
            sweetAlertDialog.dismiss();
        showToast("网络错误");
    }

    @Override
    public void error(String message) {
        showToast(message);
        if (sweetAlertDialog.isShowing())
            sweetAlertDialog.dismiss();
    }

    public void doBack(View view) {
        finish();
    }

    /**
     * 弹出加载框
     */
    @Override
    public void showProgressBar(String... msg) {
        if (dialogLoading == null) {
            dialogLoading = new RxDialogShapeLoading(this);
        }
        if (msg.length > 0)
            dialogLoading.setLoadingText(msg[0]);
        else
            dialogLoading.setLoadingText("正在加载请稍后");
        dialogLoading.show();
    }

    /**
     * 关闭加载框
     */
    @Override
    public void closeProgressBar() {
        if (dialogLoading != null)
            dialogLoading.dismiss();
    }



    public void showDialog(String title,String message,String confirmText,String cancelText, int type, SweetAlertDialog.OnSweetClickListener confirmClick, SweetAlertDialog.OnSweetClickListener cancelClick) {
        sweetAlertDialog = new SweetAlertDialog(this, type);
        sweetAlertDialog.setTitleText(title);
        sweetAlertDialog.setContentText(message);
        sweetAlertDialog.setConfirmText(confirmText);
        sweetAlertDialog.setCancelText(cancelText);
        sweetAlertDialog.showCancelButton(true);
        sweetAlertDialog.setCancelable(false);
        if (confirmClick != null) {
            sweetAlertDialog.setConfirmClickListener(confirmClick);
        }
        if (cancelClick != null) {
            sweetAlertDialog.setCancelClickListener(cancelClick);
        }
        sweetAlertDialog.show();
    }


    public void showDialog(String message, int type, SweetAlertDialog.OnSweetClickListener confirmClick) {
        sweetAlertDialog = new SweetAlertDialog(this, type);
        sweetAlertDialog.setTitleText(message);
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.showCancelButton(true);
        sweetAlertDialog.setCancelable(false);
        if (confirmClick != null) {
            sweetAlertDialog.setConfirmClickListener(confirmClick);
        }
        sweetAlertDialog.show();
    }



    /**
//     * 弹出加载框
//     */

    public void showDownProgressBar(String... msg) {
        if (msg.length != 0)
            LoadingDialog.show(this, msg[0]);
        else
            LoadingDialog.show(this);
    }

    public void changeProgressBarText(String msg) {
        LoadingDialog.changeMessage(msg);
    }

    /**
     * 关闭加载框
     */

    public void closeDownProgressBar() {
        LoadingDialog.dismiss();
    }



}

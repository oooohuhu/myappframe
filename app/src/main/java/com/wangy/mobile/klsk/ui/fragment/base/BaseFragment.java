package com.wangy.mobile.klsk.ui.fragment.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.wangy.mobile.klsk.presenter.BasePresenter;
import com.wangy.mobile.klsk.ui.activity.base.BaseFragmentActivity;
import com.wangy.mobile.klsk.ui.view.BaseView;
import com.wangy.mobile.klsk.utils.IToast;
import com.vondear.rxui.view.dialog.RxDialogLoading;
import com.vondear.rxui.view.dialog.RxDialogShapeLoading;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by lxq on 2018/06/22.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    protected boolean isFirst;

    //初始化Presenter
    protected abstract void initPresenter();

    protected Activity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected Activity getHoldingActivity() {
        return mActivity;
    }

    RxDialogShapeLoading dialogLoading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        checkPresenterIsNull();
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!isFirst) {
                initRequest();
                isFirst = true;
            }
        }
    }

    private void checkPresenterIsNull() {
//        if (mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
//        }
    }

    public void showToast(String text) {
        IToast.makeText(getActivity(), text, 1000).show();
    }

    @Override
    public void error(String message) {

    }

    @Override
    public void netError() {

        showToast("网络错误");
    }

    protected abstract void initRequest();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 弹出加载框
     */
    @Override
    public void showProgressBar(String... msg) {
        if (dialogLoading == null) {
            dialogLoading = new RxDialogShapeLoading(mActivity);
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

    public void doBack(View view){}

}


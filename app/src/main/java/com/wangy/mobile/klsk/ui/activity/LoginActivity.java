package com.wangy.mobile.klsk.ui.activity;

import android.content.Intent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wangy.mobile.klsk.R;
import com.wangy.mobile.klsk.presenter.LoginPresenter;
import com.wangy.mobile.klsk.ui.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> {
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onActivityResult(int i, int i1, Intent intent) {
        super.onActivityResult(i, i1, intent);
    }


    @Override
    protected LoginPresenter createPresenter() {
        return null;
    }

}

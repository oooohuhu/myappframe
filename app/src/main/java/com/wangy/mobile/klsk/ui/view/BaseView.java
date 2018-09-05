package com.wangy.mobile.klsk.ui.view;


/**
 * Created by lxq on 2017/10/12
 */
public interface BaseView {

    void error(String message);

    void netError();

    void showProgressBar(String... msg);

    void closeProgressBar();

}

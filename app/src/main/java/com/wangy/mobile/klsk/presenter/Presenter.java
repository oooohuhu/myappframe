package com.wangy.mobile.klsk.presenter;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}

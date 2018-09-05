package com.wangy.mobile.klsk.presenter;

import com.alibaba.fastjson.JSONObject;
import com.wangy.mobile.klsk.data.commons.ExceptionUtils;
import com.wangy.mobile.klsk.net.ApiStores;
import com.wangy.mobile.klsk.net.RetrofitFactory;
import com.wangy.mobile.klsk.ui.view.BaseView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by lxq on 2018/6/21.
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {
    public WeakReference<V> mvpView;
    public ApiStores apiStores = RetrofitFactory.retrofitService();
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = new WeakReference<>(mvpView);
    }

    public V getView() {

        return mvpView.get();
    }


    @Override
    public void detachView() {
        this.mvpView = null;
        onUnSubscribe();
    }

    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    public void addSubscription(Observable observable, Consumer consumer) {

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, e ->{
                    if(mvpView!=null)
                    ExceptionUtils.handleException(e, mvpView.get());
                }));
    }


    public void addMultiSubscription(Observable observable1, Observable observable2, BiFunction biFunction, Consumer consumer) {

        Observable.zip(observable1, observable2, biFunction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, e -> ExceptionUtils.handleException(e, mvpView.get()));
    }


}

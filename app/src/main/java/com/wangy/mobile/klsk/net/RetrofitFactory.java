package com.wangy.mobile.klsk.net;

import android.text.TextUtils;
import android.util.Log;


import com.wangy.mobile.klsk.data.entity.UserInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.wangy.mobile.klsk.net.ApiStores.BASE_HTTP_URL;


public class RetrofitFactory {


    private static final long TIMEOUT = 30;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json;charset=utf-8");
                    if (!TextUtils.isEmpty(UserInfo.getUserInfo().getTicket())) {
                        builder.addHeader("ticket", UserInfo.getUserInfo().getTicket());
                    }

                    return chain.proceed(builder.build());
                }
            })

            //添加调试
            // .addInterceptor(new HttpLoggingInterceptor(message -> Log.d("HttpLoggingInterceptor", message)).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();


    public static ApiStores retrofitService() {

        //  httpClient.interceptors().add(new TokenInterceptor());
        ApiStores apiStores = new Retrofit.Builder()
                .baseUrl(ApiStores.BASE_URL)
                // 添加Gson转换器
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiStores.class);

        return apiStores;


    }

    public static ApiStores retrofitServiceImg() {
            //  httpClient.interceptors().add(new TokenInterceptor());
         ApiStores   apiStores = new Retrofit.Builder()
                    .baseUrl(BASE_HTTP_URL+"SaveFileProject/ ")//测试环境图片:http://192.168.217.48:8080  正式环境：BASE_HTTP_URL
                    // 添加Gson转换器
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(ApiStores.class);
        return apiStores;
    }

    public static ApiStores retrofitServicePush() {

        ApiStores apiStores = new Retrofit.Builder()
                .baseUrl(ApiStores.PUSH_URL)
                // 添加Gson转换器
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiStores.class);

        return apiStores;


    }


}


package com.wangy.mobile.klsk.utils;


import com.wangy.mobile.klsk.listener.DownloadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JsDownloadInterceptor implements Interceptor {

    private DownloadListener downloadListener;

    public JsDownloadInterceptor(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        chain.request().newBuilder().addHeader("Accept-Encoding", "identity");
        Response response = chain.proceed(chain.request());
        Response.Builder builder = response.newBuilder().body(
                new JsResponseBody(response.body(), downloadListener));

        return builder.build();
//        Response response = chain.proceed(chain.request());
//
//        Request.Builder builder = chain.request().newBuilder();
//        builder.addHeader("Accept-Encoding", "identity");
//        response.newBuilder().body().ad
//        return chain.proceed(builder.build());
    }


}
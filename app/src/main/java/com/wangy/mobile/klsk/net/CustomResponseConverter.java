package com.wangy.mobile.klsk.net;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;



import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private TypeAdapter<T> adapter;
    private Type mType;

    CustomResponseConverter(Gson gson, TypeAdapter<T> mAdapter, Type mType) {
        this.gson = gson;
        this.adapter = mAdapter;
        this.mType = mType;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        try {
            String body = value.string();
            return gson.fromJson(body, mType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
//        return gson.fromJson("", mType);
    }
}

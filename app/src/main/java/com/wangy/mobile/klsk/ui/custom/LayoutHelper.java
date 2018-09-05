//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wangy.mobile.klsk.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fingdo.statelayout.bean.EmptyItem;
import com.fingdo.statelayout.bean.ErrorItem;
import com.fingdo.statelayout.bean.LoadingItem;
import com.fingdo.statelayout.bean.LoginItem;
import com.fingdo.statelayout.bean.NoNetworkItem;
import com.fingdo.statelayout.bean.TimeOutItem;
import com.wangy.mobile.klsk.R;

public class LayoutHelper {
    public LayoutHelper() {
    }

    public static void parseAttr(Context context, AttributeSet attrs, StateLayout stateLayout) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateLayout, 0, 0);

        try {
            int errorImg = a.getResourceId(R.styleable.StateLayout_errorImg, -1);
            String errorText = a.getString(R.styleable.StateLayout_errorText);
            stateLayout.setErrorItem(new ErrorItem(errorImg, errorText));
            int timeOutImg = a.getResourceId(R.styleable.StateLayout_timeOutImg, -1);
            String timeOutText = a.getString(R.styleable.StateLayout_timeOutText);
            stateLayout.setTimeOutItem(new TimeOutItem(timeOutImg, timeOutText));
            int emptyImg = a.getResourceId(R.styleable.StateLayout_emptyImg, -1);
            String emptyText = a.getString(R.styleable.StateLayout_emptyText);
            stateLayout.setEmptyItem(new EmptyItem(emptyImg, emptyText));
            int noNetworkImg = a.getResourceId(R.styleable.StateLayout_noNetworkImg, -1);
            String noNetworkText = a.getString(R.styleable.StateLayout_noNetworkText);
            stateLayout.setNoNetworkItem(new NoNetworkItem(noNetworkImg, noNetworkText));
            int loginImg = a.getResourceId(R.styleable.StateLayout_loginImg, -1);
            String loginText = a.getString(R.styleable.StateLayout_loginText);
            stateLayout.setLoginItem(new LoginItem(loginImg, loginText));
            String loadingText = a.getString(R.styleable.StateLayout_loadingText);
            stateLayout.setLoadingItem(new LoadingItem(loadingText));
        } finally {
            a.recycle();
        }

    }

    public static View getErrorView(LayoutInflater layoutInflater, ErrorItem item, final StateLayout layout) {
        View view = layoutInflater.inflate(R.layout.layout_error, (ViewGroup) null);
        ImageView imageView = (ImageView) view.findViewById(R.id.failView);
        Glide.with(layoutInflater.getContext()).load(R.mipmap.img_empty).into(imageView);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (layout.getRefreshLListener() != null) {
                    layout.getRefreshLListener().refreshClick();
                }

            }
        });

        return view;
    }

    public static View getNoNetworkView(LayoutInflater layoutInflater, NoNetworkItem item, final StateLayout layout) {
        View view = layoutInflater.inflate(R.layout.layout_no_network, (ViewGroup) null);
        ImageView imageView = (ImageView) view.findViewById(R.id.noNetView);
/*        Button button = (Button) view.findViewById(R.id.btn);*/
        imageView.setImageResource(R.mipmap.img_net_error);
        /*AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();*/

        view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (layout.getRefreshLListener() != null) {
                    layout.getRefreshLListener().refreshClick();
                }

            }
        });

        return view;
    }

    public static View getLoadingView(LayoutInflater layoutInflater, LoadingItem item) {
        View view = layoutInflater.inflate(R.layout.layout_loading, (ViewGroup) null);
        ImageView imageView = (ImageView) view.findViewById(R.id.loadingView);
        imageView.setImageResource(R.mipmap.icon_load);
        Animation operatingAnim = AnimationUtils.loadAnimation(layoutInflater.getContext(), R.anim.load_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            imageView.startAnimation(operatingAnim);
        }

        return view;
    }

    public static View getEmptyView(LayoutInflater layoutInflater, EmptyItem item) {
        View view = layoutInflater.inflate(R.layout.layout_empty, (ViewGroup) null);
        ImageView imageView = (ImageView) view.findViewById(R.id.emptyView);
        imageView.setImageResource(R.mipmap.img_empty);
       /* AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();*/
        return view;
    }

}

package com.wangy.mobile.klsk.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.wangy.mobile.klsk.R;

/**
 * 加载中对话框
 * Created by lxq on 2016/11/7 0007 16:45.
 */
public class AppLoadingDialog extends AppDialog {

    private final TextView mContentView;
    ImageView gifView;
    AnimationDrawable animationDrawable;
    Animation operatingAnim;
    public AppLoadingDialog(Context context) {
        super(context, R.style.AppDialog_Loading); // 不要主题
        setContentView(R.layout.fragment_dialog_loading);
        mContentView = (TextView) findViewById(R.id.tv_loading);
        gifView = (ImageView) findViewById(R.id.iv_loading_dialog);

        dismiss();
    }

    public void setMessage(CharSequence msg) {
        if (TextUtils.isEmpty(msg)) {
            mContentView.setVisibility(View.GONE);
        } else {
            mContentView.setVisibility(View.VISIBLE);
        }
        mContentView.setText(msg);
    }

    @Override
    public void dismiss() {
        try {
            if (operatingAnim!=null)
                gifView.clearAnimation();
            super.dismiss();
        } catch (Exception e) {
            Log.e("rae", "dismiss loading dialog error!!", e);
        }
    }

    /**
     * 设置图标
     *
     * @param resId
     */
    public void setIconResource(int resId) {
        gifView.setVisibility(View.GONE);
    }

    public void setAutoDismiss(int autoDismiss) {
        mContentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, autoDismiss);
    }

    public void loading() {
        gifView.setVisibility(View.VISIBLE);
        //  gifView.setImageResource(R.drawable.frame);
        gifView.setImageResource(R.mipmap.icon_load);
        operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.load_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if(operatingAnim!=null){
            gifView.startAnimation(operatingAnim);
        }
        // Glide.with(getContext()).load(R.drawable.load).placeholder(R.drawable.load_1).diskCacheStrategy(DiskCacheStrategy.ALL).into(gifView);
    }

    private Context getBaseContext() {
        return ((ContextWrapper) getContext()).getBaseContext();
    }

    private Activity getActivity() {
        if (getBaseContext() instanceof Activity)
            return (Activity) getBaseContext();
        return null;
    }
}

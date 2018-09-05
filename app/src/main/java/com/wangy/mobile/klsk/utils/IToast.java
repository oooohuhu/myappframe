package com.wangy.mobile.klsk.utils;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wangy.mobile.klsk.R;
import com.wangy.mobile.klsk.ui.custom.ToastLayout;


public class IToast {

    private Activity mActivity;
    private RelativeLayout mToastLayout;
    private ToastLayout mToast;
    private ViewGroup mView;
    private String text;
    private long times;
    private static IToast mToastInstance;

    public IToast(Activity mActivity, String text, long times){
        this.mActivity = mActivity;
        this.text = text;
        this.times = times;
    }

    public IToast(ViewGroup mView, String text, long times){
        this.mView = mView;
        this.text = text;
        this.times = times;
    }

    public static IToast makeText(Activity mActivity, String text, long times){
        mToastInstance = new IToast(mActivity,text,times);
        return mToastInstance;
    }

    public static IToast makeText(ViewGroup mView, String text, long times){
        mToastInstance = new IToast(mView,text,times);
        return mToastInstance;
    }


    public void show(){
        if(mActivity!=null){
            mToastLayout = (RelativeLayout) mActivity.findViewById(R.id.rl_toast);
            if(mToastLayout==null){//判断是否已经添加进母VIEW里，没有则添加进去
                mToast = new ToastLayout(mActivity);
                mActivity.addContentView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }else{//如果有，则直接取出
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
            return;
        }else if(mView!=null){
            mToastLayout = (RelativeLayout) mView.findViewById(R.id.rl_toast);
            if(mToastLayout==null){
                mToast = new ToastLayout(mView.getContext());
                mView.addView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
            }else{
                mToast = (ToastLayout) mToastLayout.getParent();
            }
            mToast.setContent(text);
            mToast.showToast(times);
        }
    }

    private boolean isShowToast(){
        if(mToast == null){
            return false;
        }
        return  mToast.isShow();
    }

    public static boolean isShow(){
        if(mToastInstance == null){
            return false;
        }else{
            boolean isShow = mToastInstance.isShowToast();
            mToastInstance = null;
            return isShow;
        }
    }
}

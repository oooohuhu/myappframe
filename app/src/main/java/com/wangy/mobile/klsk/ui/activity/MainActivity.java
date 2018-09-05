package com.wangy.mobile.klsk.ui.activity;


import android.view.KeyEvent;
import android.view.View;
import com.wangy.mobile.klsk.R;
import com.wangy.mobile.klsk.ui.activity.base.BaseFragmentActivity;

import com.wangy.mobile.klsk.utils.IToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {


    private long nowTime;
    private long oldTime;

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
//        fragments.add(fragmentManager.findFragmentById(R.id.fm_service));
//        fragments.add(fragmentManager.findFragmentById(R.id.fm_zx));
//        fragments.add(fragmentManager.findFragmentById(R.id.fm_speech));
//        fragments.add(fragmentManager.findFragmentById(R.id.fm_hd));
//        fragments.add(fragmentManager.findFragmentById(R.id.fm_mine));
        selectTab(0);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            nowTime = System.currentTimeMillis();
            if (nowTime - oldTime < 3000) {
                this.finish();
            } else {
                IToast.makeText(this, getString(R.string.exit), 1000).show();
                oldTime = nowTime;
            }
        }
        return true;
    }


    public void doBack(View view) {
    }
}

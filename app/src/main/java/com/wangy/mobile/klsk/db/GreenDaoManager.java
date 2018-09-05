package com.wangy.mobile.klsk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wangy.mobile.klsk.data.entity.PushMessage;

import com.wangy.mobile.klsk.data.entity.UserInfo;
import com.wangy.mobile.klsk.db.UserInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongxiong on 2018/6/22.
 */

public class GreenDaoManager {

    private final static String dbName = "hft_db";
    private static GreenDaoManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private DaoSession mDaoSession;
    private DaoMaster mDaoMaster;
//
    public GreenDaoManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }


    public static GreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager(context);
                }
            }
        }
        return mInstance;
    }


    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }




    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            if (null == mDaoMaster) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    public DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            mDaoMaster = new DaoMaster(getWritableDatabase());
        }
        return mDaoMaster;
    }








    //修改用户信息表中的昵称字段
    public void modifyLoginName(String acId , String loginName) {
        DaoSession daoSession = getDaoSession();
        UserInfoDao userInfoDao = daoSession.getUserInfoDao();
        UserInfo userInfo = userInfoDao.queryBuilder().where(UserInfoDao.Properties.AcId.eq(acId)).unique();
        if (userInfo != null){
            userInfo.setLoginName(loginName);
            userInfoDao.update(userInfo);
        }

    }

    //修改用户信息表中的偏好设置
    public void modifyUserPrefer(String acId , String userPrefer) {
        DaoSession daoSession = getDaoSession();
        UserInfoDao userInfoDao = daoSession.getUserInfoDao();
        UserInfo userInfo = userInfoDao.queryBuilder().where(UserInfoDao.Properties.AcId.eq(acId)).unique();
        if (userInfo != null){
            userInfo.setUsePrefer(userPrefer);
            userInfoDao.update(userInfo);
        }

    }

    //修改用户表中的头像信息
    public void modifyIconUrl(String userId , String url) {
        DaoSession daoSession = getDaoSession();
        UserInfoDao userInfoDao = daoSession.getUserInfoDao();
        UserInfo userInfo = userInfoDao.queryBuilder().where(UserInfoDao.Properties.UserId.eq(userId)).unique();
        if (userInfo != null){
            userInfo.setIconUrl(url);
            userInfoDao.update(userInfo);
        }

    }
}

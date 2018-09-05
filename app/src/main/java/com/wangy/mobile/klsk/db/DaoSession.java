package com.wangy.mobile.klsk.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.wangy.mobile.klsk.data.entity.PushMessage;
import com.wangy.mobile.klsk.data.entity.UserInfo;

import com.wangy.mobile.klsk.db.PushMessageDao;
import com.wangy.mobile.klsk.db.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pushMessageDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final PushMessageDao pushMessageDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pushMessageDaoConfig = daoConfigMap.get(PushMessageDao.class).clone();
        pushMessageDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        pushMessageDao = new PushMessageDao(pushMessageDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(PushMessage.class, pushMessageDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        pushMessageDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
    }

    public PushMessageDao getPushMessageDao() {
        return pushMessageDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}

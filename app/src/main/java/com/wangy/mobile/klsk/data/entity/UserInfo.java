package com.wangy.mobile.klsk.data.entity;



import com.wangy.mobile.klsk.application.HApplication;
import com.wangy.mobile.klsk.db.GreenDaoManager;
import com.wangy.mobile.klsk.db.UserInfoDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

@Entity
public class UserInfo {
    @Id
    private String acId;

    private String acPwd;
    private String acType;
    private String account;
    private String appCode;
    private String certificationLevel;
    private String certificationLevelName;
    private String credentNo;
    private String credentType;
    private String deptSource;
    private String isExist;
    private String nation;
    private String nationName;
    private String oauthId;
    private String phone;
    private String phoneNo;
    private String relationId;
    private String sex;
    private String sexName;
    private String status;
    private String ukey;
    private String userId ="";
    private String userName;
    private String userType;
    private String validBegin;
    private String validEnd;
    private String usePrefer;
    private String loginName;
    private String iconUrl;
    private String ticket;

    @Generated(hash = 1561045094)
    public UserInfo(String acId, String acPwd, String acType, String account, String appCode, String certificationLevel, String certificationLevelName,
            String credentNo, String credentType, String deptSource, String isExist, String nation, String nationName, String oauthId, String phone,
            String phoneNo, String relationId, String sex, String sexName, String status, String ukey, String userId, String userName, String userType,
            String validBegin, String validEnd, String usePrefer, String loginName, String iconUrl, String ticket) {
        this.acId = acId;
        this.acPwd = acPwd;
        this.acType = acType;
        this.account = account;
        this.appCode = appCode;
        this.certificationLevel = certificationLevel;
        this.certificationLevelName = certificationLevelName;
        this.credentNo = credentNo;
        this.credentType = credentType;
        this.deptSource = deptSource;
        this.isExist = isExist;
        this.nation = nation;
        this.nationName = nationName;
        this.oauthId = oauthId;
        this.phone = phone;
        this.phoneNo = phoneNo;
        this.relationId = relationId;
        this.sex = sex;
        this.sexName = sexName;
        this.status = status;
        this.ukey = ukey;
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.validBegin = validBegin;
        this.validEnd = validEnd;
        this.usePrefer = usePrefer;
        this.loginName = loginName;
        this.iconUrl = iconUrl;
        this.ticket = ticket;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcPwd(String acPwd) {
        this.acPwd = acPwd;
    }

    public String getAcPwd() {
        return acPwd;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getAcType() {
        return acType;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevelName(String certificationLevelName) {
        this.certificationLevelName = certificationLevelName;
    }

    public String getCertificationLevelName() {
        return certificationLevelName;
    }

    public void setCredentNo(String credentNo) {
        this.credentNo = credentNo;
    }

    public String getCredentNo() {
        return credentNo;
    }

    public void setCredentType(String credentType) {
        this.credentType = credentType;
    }

    public String getCredentType() {
        return credentType;
    }

    public void setDeptSource(String deptSource) {
        this.deptSource = deptSource;
    }

    public String getDeptSource() {
        return deptSource;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }

    public String getIsExist() {
        return isExist;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setValidBegin(String validBegin) {
        this.validBegin = validBegin;
    }

    public String getValidBegin() {
        return validBegin;
    }

    public void setValidEnd(String validEnd) {
        this.validEnd = validEnd;
    }

    public String getValidEnd() {
        return validEnd;
    }


    public static void save(UserInfo userInfo) {
        UserInfoDao userInfoDao = GreenDaoManager.getInstance(HApplication.getInstances().getApplicationContext()).getDaoSession().getUserInfoDao();
        userInfoDao.insertOrReplace(userInfo);
    }

    public static void delete() {
        UserInfoDao userInfoDao = GreenDaoManager.getInstance(HApplication.getInstances().getApplicationContext()).getDaoSession().getUserInfoDao();
        userInfoDao.deleteAll();
    }


    public static UserInfo getUserInfo() {
        UserInfoDao userInfoDao = GreenDaoManager.getInstance(HApplication.getInstances().getApplicationContext()).getDaoSession().getUserInfoDao();
        List<UserInfo> list = userInfoDao.loadAll();
        if (list != null && list.size() != 0)
            return list.get(0);
        else
            return new UserInfo();
    }

    public String getUsePrefer() {
        return this.usePrefer;
    }

    public void setUsePrefer(String usePrefer) {
        this.usePrefer = usePrefer;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
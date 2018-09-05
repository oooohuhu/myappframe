package com.wangy.mobile.klsk.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PushMessage {
    @Id
    private String id; //消息id
    @NotNull
    private String title; //标题
    private String actionUrl; //跳转的url地址
    @NotNull
    private String msgType; // a:新闻咨询  b：政务互动  c：自定义的消息
    @NotNull
    private String message;  //消息内容
    private String date;  //日期
    private String sender;  //发送者
    private String pushType; // 1: 透传消息    2: 通知

    private Boolean isSelect; //在消息页面用于批量删除使用,true 代表选中，false代表为选中
    
    @Generated(hash = 1428165957)
    public PushMessage(String id, @NotNull String title, String actionUrl,
            @NotNull String msgType, @NotNull String message, String date,
            String sender, String pushType, Boolean isSelect) {
        this.id = id;
        this.title = title;
        this.actionUrl = actionUrl;
        this.msgType = msgType;
        this.message = message;
        this.date = date;
        this.sender = sender;
        this.pushType = pushType;
        this.isSelect = isSelect;
    }
    @Generated(hash = 1468533071)
    public PushMessage() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getActionUrl() {
        return this.actionUrl;
    }
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMsgType() {
        return this.msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getSender() {
        return this.sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getPushType() {
        return this.pushType;
    }
    public void setPushType(String pushType) {
        this.pushType = pushType;
    }
    public Boolean getIsSelect() {
        return this.isSelect;
    }
    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

}

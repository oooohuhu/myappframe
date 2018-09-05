package com.wangy.mobile.klsk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 *
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Bundle bundle = intent.getExtras();
            Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

           //收到自定义消息，就是透传消息，将数据存到数据库
            if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                //解析数据存放到数据库中
                saveMessage(context,bundle,"1");

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())){
                //解析数据存放到数据库中
                saveMessage(context,bundle,"2");

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
                openMessPage(context,bundle);
            } else {
                Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e){

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    //存数据
    public void saveMessage(Context context, Bundle bundle,String pushType){
//        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String title = TextUtils.equals(pushType,"1") ? bundle.getString(JPushInterface.EXTRA_TITLE) : bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String id = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        if (!TextUtils.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
                String actionUrl = extraJson.getString("actionUrl");
                String msgType = extraJson.getString("msgType");
                String time = extraJson.getString("time");
                String sender = extraJson.getString("sender");

//                PushMessage pushMessage = new PushMessage(id,title,actionUrl,msgType,"",time,sender,pushType,false);
//                GreenDaoManager.getInstance(context).insertMessage(pushMessage);

            } catch (JSONException e) {

            }
        }
    }

    //打开页面
    public void openMessPage(Context context, Bundle bundle){
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        if (!TextUtils.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
//                String actionUrl = extraJson.getString("actionUrl") != null ? extraJson.getString("actionUrl") : "";
//                if (!TextUtils.isEmpty(actionUrl)){
//                   Intent i = new Intent(context, WebViewActivity.class);
//                   i.putExtra("URL", actionUrl);i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                   context.startActivity(i);
//                }
            } catch (JSONException e) {

            }

        }

    }






}

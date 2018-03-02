package com.stateunion.eatshop.service;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by 青春 on 2017/12/28.
 */

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */

public class DemoIntentService extends GTIntentService{
    public static String cid;
    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        byte[] payload = msg.getPayload();
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        if (payload == null) {
        } else {
        }
        String data = new String(payload);
        Log.d("eatshop", "receiver payload = " + data);
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.v("eatshop", "onReceiveClientId -> " + "clientid = " + clientid);
        cid=clientid;
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

}

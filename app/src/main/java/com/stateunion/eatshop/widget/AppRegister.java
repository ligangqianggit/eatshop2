package com.stateunion.eatshop.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.stateunion.eatshop.commons.Constants;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by admini on 2018/1/26.
 */

public class AppRegister extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);

        // 将该app注册到微信
        api.registerApp(Constants.APP_ID);
    }
}

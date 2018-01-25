package com.stateunion.eatshop.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.stateunion.eatshop.commons.Constants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
/**
 * Created by admini on 2018/1/25.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
     IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq arg0) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";//成功
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "取消";//取消
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "微信拒绝";//被拒绝
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

                break;
            default:
                result = "返回";//返回
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                break;
        }
    }


}

package com.stateunion.eatshop.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.growingio.android.sdk.utils.LogUtil;
import com.stateunion.eatshop.commons.Constants;
import com.stateunion.eatshop.pay.PayService;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by admini on 2018/1/26.
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
 //        api.handleIntent(getIntent(), this);
        //payWithWXBack();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtil.d(TAG, "Luke = " + resp.toString());

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            switch (code) {
                case 0:
                     break;
                case -1:
                     finish();
                    break;
                case -2:
                     finish();
                    break;
                default:
                     setResult(RESULT_OK);
                    finish();
                    break;
            }
        }

    }

//    private void payWithWXBack() {
//        HashMap<String, String> hamap = new HashMap<String, String>();
//        hamap.put("memberId", PayService.memberId);
//        hamap.put("orderId", PayService.orderId);
//        hamap.put("accountId", ((DeKuShuApplication) getApplicationContext()).getUserlogininfo().getUserId());
//        new RequestCommant().requesWXPayOrderBack(new RequestHandler(this), this, hamap);
//    }

//    @SuppressLint("HandlerLeak")
//    private class RequestHandler extends BaseHandler {
//
//        public RequestHandler(Activity activity) {
//            super(activity);
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            // TODO Auto-generated method stub
//            super.handleMessage(msg);
//
//            if (msg.what == Constants.PAYWXBACK) {
//
//                if (command.isSuccess) {
//                    if (null != command.resData) {
//
//                        WXOrderBodyVo loginbody = (WXOrderBodyVo) command.resData;
//                        WXOrderVo bodvo = loginbody.getData();
//                        ToastUtil.showLong(getApplicationContext(), bodvo.getOrderStatus());
//                        if (!bodvo.getOrderStatus().equals("1")) {
//                            WXPayEntryActivity.this.finish();
//                            PayService.pservice.afterPaySuccess();
//                        } else {
//                            ToastUtil.showLong(getApplicationContext(), "未支付成功");
//                            WXPayEntryActivity.this.finish();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }

//    @Override
//    public void onReq(BaseReq req) {
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            if (resp.errCode == 0) {
//                //成功
//                if(PayService.pservice!=null){
//                    PayService.pservice.callWXPaySuccess();
//                }
//
//            }else{
//                //失败
//                if(PayService.pservice!=null){
//                    PayService.pservice.callWXPaySuccess();
//                }
//            }
//
//        }
//        this.finish();
//    }
}

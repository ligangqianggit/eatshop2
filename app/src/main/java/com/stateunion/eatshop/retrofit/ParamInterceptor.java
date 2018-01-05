package com.stateunion.eatshop.retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhangguozheng on 2017/8/25.
 */

public class ParamInterceptor implements Interceptor {
    private static String TAG = "ParamInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        RequestBody body = request.body();
        if (request.method().equalsIgnoreCase("POST")) {
            Map<String, String> oldParams = new HashMap<>();
            if (body instanceof FormBody) {
                FormBody formBody = (FormBody) body;
                for (int i = 0; i < formBody.size(); i++) {
                    oldParams.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
            }
            FormBody newFormBody = addParam(oldParams);
            okhttp3.Request newRequest = request.newBuilder().method(request.method(), newFormBody).build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);
    }

    FormBody addParam(Map<String, String> oldParams) {
//        oldParams.put("terminalType", "3");
//        oldParams.put("appVersion", AndroidUtil.getVersionName());
//        String session = AppSessionEngine.getSession();
//        if (null != session) {
//            oldParams.put("sessionId", session);
//        }
//        String useId = AppSessionEngine.getUseId();
//        if (null != useId) {
//            oldParams.put("useId", useId);
//        }
//        String deviceId = ((TelephonyManager) YiTongDaiApplication.sApplication.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//        if (deviceId != null) {
//            oldParams.put("idfa", deviceId);
//        }

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : oldParams.entrySet()) {
            builder.addEncoded(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}

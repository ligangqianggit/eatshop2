package com.stateunion.eatshop.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhang on 2017/12/14.
 */

public class ZFBPayUtil {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    // 商户PID
//    public static final String PARTNER = "2088021520076733";
//    // 商户收款账号
//    public static final String SELLER = "2088021520076733";
//    // 商户私钥，pkcs8格式
//    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOb6O6ebFlEjko3VtRyTZtNs8XuZdYZxnJ4cATwpQTmnuT+6ouUSH+7eQ/J1ISS55hr4ztMJzXeZ9YC2H+QWcpE4oITLuAskUZWr86/3Rpb1huyn2ADU2V2oImmWDcSFpr4vPbyYXX80wEP3dsiRCw2WoOxUfEcadYNyvFEYzo4ZAgMBAAECgYEAs+8P9dudIQxjKNboxiAWJHJkFHNR2LA83/bVB3bj9vQwHfNY3ral5cGEkSap8SBYn35gMIscYyWu2NwIL/lEuV2Rknrm5I9si+Q64AV5u8X7iQIVAiM+LvygxmwRCVRw7cFPTdgndu7Ku7KDI96zkKTjh2hC7iygMBnlndEo8OECQQD8WqJHyBxTcDEy8LR/iq25oXmc0snXDgh6IHDIVl8/IBa4y6+t/Gxgph7Htd+W656prfW/cc4F2As/eta9fIR1AkEA6lCI8pX9NJRGp164/xTJy7W35Ctkf/lQDRCg4O8T0NlhCmmCpNq5mlGr0HAEeeuHUBdGeJaM2/ETBXJRk5jelQJBAKH8O7cACM62cZ5VTEeiaSmDMWEtHUriQybBtCOk7bbDjlJIzRhNs/PGLnPblw51aH9anyiokKrHp1C0NSukxhECQQC936MKCTcVC26xpBV/RuY7eDba47rd5TbUT3w+qh3BqNV1LYjbdA7hGXuEPbGy5oRRB3torn7Yn+MND9VBKKh9AkASyR7/6qe/vHPAJGkYkomUkWhP4a6gIY+1q0LclgeoIRvgefI4g+TIA8e3lxKuspxHzO20Inww7WE65615WxNY";
//    // 支付宝公钥
//    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

//     商户PID
    public static final String PARTNER = "2088921321370581";
    // 商户收款账号
    public static final String SELLER = "2088921321370581";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCTQihABa2ahl4IK1E3VSWqGUH+XtewNpCg/+oVYYNpWSfIcQWp6OfsWMNJCCoSMXBYEF/I+0w5qZ9RyaIhMzE+RSVfSSU00yi3xgyI306fvk5LyVqkIrXL/lyju10l1MOGYF3DkiJPdt3i7QhGgN5tMd+1uKR63ESvqKRaFhQ4sJHrplM7Th4RadKDxelsoE9ETkPqKXRiDgrP9/nOw4nWYQoGVO87+PwTOQISDXcCykOcXmSjIEStLhit+6z+P9KCznfs2CHhg1jonk2zf+CJAovxCYdquSi+kaVv7fjN59GGcCQ3Bh2G/PANIpZ6JVhEQ7doHfPmQAQN4O4MhwjAgMBAAECggEAe2+GcR6oZX31SruJ6ObTLzWvH/LTUFvZZ5GsK0d5iovX2ngeTmfYtXtRni1PUpw92obZBRx/ogeeSKeSdVl4slBTk5nNgb75poE5ZqYAB94pMmbbRyxOmrOz1zZW9FA2OfEnmCVLyddzhLgvl/wQtsbvBdWDJUCpCVzWnHEA2P3dbXOP8onTbvfboXgH1Or1KQNl3HUm3I58nPZJt3ZERATORmKc4z/i/+n79T2BBnzktp1DAZ8ze/3kSQW6SChmCDUQC+5iUUU4fg25FmTIzE9aH6yaDetFeI1tGQqwL/LQOQBGC0AGQNv/fceCaZUyBUZcaD+id5URjrvMJiLg4QKBgQC7iC+y4EH88c9kmiOMmQWK3Oal18J/e4zhpfwdx9oICcyJ0Xm10ZncG0tslxO3CynzyWhLJ3NY83CEkPUqRlzwjFHbfjrskJacP4htr9eKG0Hn/sOeEAiymMXYr+cyNVlNzP7clG/iKLLbQLVP5QTJv9TM9lMELDHF4bSvDtp2VQKBgQCx37Q3/nusPY/2raluQ5JqU7pKQB3zdkVj5jg8Dcv0Rrb6ZCUN+GmVO+xBj2pnei40UKfk8XKQoUw3ng+dXPTRbmSKREAAgnEPL/wQGHJYw6CTxYip/LBh9Hyuhtvl+bo6qbXN2nQXBsqWyJn/ORGIyW3dIdr1CQX5qKiOfS0QlwKBgF+OS0/IOZeb12OZrxeLOWnCmbxTroWq/ORV4ZuDJDQIWjK3jF77CH1BYzNX3/HbPGs+a2gwF/RxRxoliucZp23i8ir3rSqxmizPqsLJYPi8Xt7Jz/+Qh/4JHncFWrcYvAKKvCrFcCU7O7fAxyKIveHXX6V4tmBf7JS6J4kz9llpAoGAFjImGP6mjB26gzu0cs/3y1DbZDyQADt3+TlXNxc5pifU2XX5sb6X3kl199BfpL34WgreiJEgjK+0BRSyOh7JZgDyhtZxbh7Y4N5KA/ApBK6UsRvrK9seX3rUvCqT8iSxenSG/UMPTXhJe3tYzCJg78LC9N8upXkUx8ymC/HO180CgYADVYXIyUwaD1jMytDQ80/UhN0ulGkJEnao5zqyuqyRqT8Q0cw8EVfjrPAVWJ/LxXZrFpt8bAOb+qbhhH4ue4FtGcNck3JJcnTdAjSTRjWp14MPdhqIDPD2ofnvShvTTbqD6Hlgm6OTSpWS+26LnWEKixa/MIaf9zxekoB9wbm/jQ==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg1hOu27wMVWJceYQkC0uy8li5GsYFkw2RGHaa0AgatThf2SZN9MGvsgmlIjhDk8HhqGIfsCN4t+3bi84SVCEh5/dGPhajG06LL7MLXzmjqpqPuVKBU54RCAhY7s0vvWmds4PWHrt6Ibw3vpVer+srwBe50w+a3ne+Zq6UXW0gEZHNu1AERDTGsb2t9kEdr8RoH6GQcLLD6D2/sIh426uGgl6tGdCfAjRto7UqzqWXuOdV8F4PwWqrM/mqE6KVM71zW2uLgLgZpNYhi4pfkAOnvTbgad/T56p+AL3w0WAdXbK9dyEQqEbjIdoYDNxniwLxOvHJNTRkgcm5N8Kod2DRwIDAQAB";

    private Context context;
    public DoSomeThing afterpay;
    private String price,name,ordernum,urlback;
    public void payZFB(Context context,String price,String name,String ordernum,String urlback) {
        this.context = context;
//        this.afterpay=(DoSomeThing) context;
        this.price=price;
        this.name=name;
        this.ordernum=ordernum;
        this.urlback=urlback;
        goToZFB();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
//				afterpay.afterPaySuccess();
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 支付成功后执行
//                        afterpay.callZFBPaySuccess();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(context, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(context, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    public void goToZFB() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder((Activity) context)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    ((Activity) context).finish();
                                }
                            }).show();
            return;
        }
        // 订单
//		String orderInfo = getOrderInfo("测试商品", "该测试商品的详细描述", "0.01");
        String orderInfo = getOrderInfo(name, "该测试商品的详细描述", price,urlback);
        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) context);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * check whether the device has authentication alipay account.
     * 查询终端设备是否存在支付宝认证账户
     *
     */
    public void check(View v) {
        Runnable checkRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask payTask = new PayTask((Activity) context);
                // 调用查询接口，获取查询结果
//                boolean isExist = payTask.checkAccountIfExist();  唯一改动

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = true;
                mHandler.sendMessage(msg);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask((Activity) context);
        String version = payTask.getVersion();
        // Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    public String getOrderInfo(String subject, String body, String price,String urlback) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号ordernum
        orderInfo += "&out_trade_no=" + "\"" + ordernum + "\"";
        //传  orderNum  后台返回 充值第二步请求 getOutTradeNo()

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + urlback + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }
}

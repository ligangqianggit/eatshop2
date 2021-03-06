package com.stateunion.eatshop.retrofit;

import com.stateunion.eatshop.retrofit.callback.RequestCallback;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by admini on 2017/11/6.
 */

public class RequestCommand {

    private static void send(Call call, RequestCallback callback) {
        callback.onPreRequest(call);
        call.enqueue(callback);
    }

    public static void call(RequestCallback callback, Call call) {
        send(call, callback);
    }

    private static ServiceApi getApi() {
        return RetrofitBuilder.getServiceApiInstance();
    }

    //密码登录
    public static void passWordLogin(RequestCallback callback, String useLoginName,
                                     String useLoginPswd, String identify, String uuid) {
        Call call = getApi().passwordLogin(useLoginName, useLoginPswd, identify, uuid);
        send(call, callback);
    }

    //密码登录
    public static void pswLogin(RequestCallback callback, String useLoginName,
                                String useLoginPswd,String userCid) {
        Call call = getApi().pawLogin(useLoginName, useLoginPswd,userCid);
        send(call, callback);
    }

    //获取点餐页的餐品
    public static void getDingCanInfo(RequestCallback callback, String type, String fenlei) {
        Call call = getApi().getdingcaninfo(type, fenlei);
        send(call, callback);
    }

    public static void zhifujiekou(RequestCallback callback, String user_id, String pay_time, String pay_lei, String all_money, String list) {
        Call call = getApi().goPay(user_id, pay_time, pay_lei, all_money, list);
        send(call, callback);
    }

    //获取个人信息
    public static void getPreson(RequestCallback callback, String user_id) {
        Call call = getApi().getPresonInfo(user_id);
        send(call, callback);
    }

    //意见反馈
    public static void FanKui(RequestCallback callback, String neirong, String user_id) {
        Call call = getApi().toFankui(neirong, user_id);
        send(call, callback);
    }

    //修改密码
    public static void ChangePwd(RequestCallback callback, String user_id, String oldpassword, String newpassword, String twopassword) {
        Call call = getApi().changePwd(user_id, oldpassword, newpassword, twopassword);
        send(call, callback);
    }

    //修改资料
    public static void Upziliao(RequestCallback callback, String user_id, MultipartBody.Part file, String phone, String zhuzhi, RequestBody description) {
        Call call = getApi().xiugaizilaio(user_id, phone, zhuzhi, description, file);
        send(call, callback);
    }

    //修改资料 文件
    public static void Upziliaos(RequestCallback callback, String user_id, String phone, String zhuzhi, String file) {
        Call call = getApi().xiugaizilaios(user_id, phone, zhuzhi, file);
        send(call, callback);
    }

    //    //修改资料 文件
//    public static void Upziliaoss(RequestCallback callback,Map<String, String> params,MultipartBody.Part files){
//        Call call=getApi().doFeedback(params,files);
//        send(call,callback);
//    }
//    //获取余额
//    public static void cesgi(RequestCallback callback, String a,String b,String c,Map<String, RequestBody> files){
//        Call call=getApi().ces(a,b,c,files);
//        send(call,callback);
//    }
    //获取余额
    public static void getYue(RequestCallback callback, String user_id) {
        Call call = getApi().yue(user_id);
        send(call, callback);
    }

    //历史订单
    public static void getHistoryList(RequestCallback callback, String user_id) {
        Call call = getApi().history(user_id);
        send(call, callback);
    }

    //余额支付
    public static void YuePay(RequestCallback callback, String user_id, String order_sn, String allmoney) {
        Call call = getApi().yuepay(user_id, order_sn, allmoney);
        send(call, callback);
    }


    //历史订单详情
    public static void getDDxiangqiang(RequestCallback callback, String user_id, String order_sn) {
        Call call = getApi().ddxiangqing(user_id, order_sn);
        send(call, callback);
    }

    //可评价列表
    public static void getYGPingjialist(RequestCallback callback, String order_sn) {
        Call call = getApi().kepinglist(order_sn);
        send(call, callback);
    }


    //取餐详情页
    public static void getQucanxiangqing(RequestCallback callback, String user_id, String order, String shijianduan, String fenlei) {
        Call call = getApi().qucanmingxi(user_id, order, shijianduan, fenlei);
        send(call, callback);
    }

    //推单审核
    public static void getTuiDanShengHe(RequestCallback callback, String user_id) {
        Call call = getApi().tuidanshenhe(user_id);
        send(call, callback);
    }


    //获取订单号
    public static void getDingdanhao(RequestCallback callback, String user_id) {
        Call call = getApi().dingdanhao(user_id);
        send(call, callback);
    }


    //员工评价接口
    public static void setYGPingjia(RequestCallback callback,  String goods_id, String shiwu_id, String foot_name, String user_id, String chushi, String content, String fen) {
        Call call = getApi().ygpingjia(goods_id, shiwu_id,foot_name,user_id,chushi,content,fen);
        send(call, callback);
    }

    //员工评价接口
    public static void setTuidan(RequestCallback callback, String user_id, String order_sn, String message) {
        Call call = getApi().tuidan(user_id, order_sn, message);
        send(call, callback);
    }


    //厨师长退单审核同意 拒绝
    public static void CSZTuiDanShenHe(RequestCallback callback, String order_sn, int jiekou, String juyuan) {
        Call call = getApi().tuichushishenhe(order_sn, jiekou, juyuan);
        send(call, callback);
    }

    //厨师端评价列表
    public static void getPingJiaList(RequestCallback callback, String user_id) {
        Call call = getApi().pingjiaList(user_id);
        send(call, callback);
    }

    //厨师端点餐统计页
    public static void getTongji(RequestCallback callback, String shijianduan) {
        Call call = getApi().tongji(shijianduan);
        send(call, callback);
    }


    //厨师端厨师排名
    public static void getPaiming(RequestCallback callback, String type) {
        Call call = getApi().paiming(type);
        send(call, callback);
    }

    //厨师长上传菜品
    public static void setFood(RequestCallback callback, String foot_name, String foot_jianjie, String foodtu, String chushi, String jiage, String shuliang, String fenlei, String shijianduan, String yuyue_start, String jiezhi, String riqi, String tui_time) {
        Call call = getApi().shangchuanfood(foot_name, foot_jianjie, foodtu, chushi, jiage, shuliang, fenlei, shijianduan, yuyue_start, jiezhi, riqi, tui_time);
        send(call, callback);
    }

    //支付结果回掉
    public static void setPayNotify(RequestCallback callback, String order_sn,String code) {
        Call call = getApi().paynotity(order_sn,code);
        send(call, callback);
    }
}

package com.stateunion.eatshop.retrofit;

import com.stateunion.eatshop.retrofit.callback.RequestCallback;

import java.io.File;
import java.util.List;

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
                                     String useLoginPswd) {
        Call call = getApi().pawLogin(useLoginName, useLoginPswd);
        send(call, callback);
    }
    //获取点餐页的餐品
    public static void getDingCanInfo(RequestCallback callback,String type){
        Call call=getApi().getdingcaninfo(type);
        send(call,callback);
    }
    public static void zhifujiekou(RequestCallback callback, String pay_time, String pay_lei, String all_money, List<String> list){
        Call call=getApi().goPay(pay_time,pay_lei,all_money,list);
        send(call,callback);
    }
    //获取个人信息
    public static void getPreson(RequestCallback callback,String user_id){
        Call call=getApi().getPresonInfo(user_id);
        send(call,callback);
    }

    //意见反馈
    public static void FanKui(RequestCallback callback,String neirong,String user_id){
        Call call=getApi().toFankui(neirong,user_id);
        send(call,callback);
    }

    //修改密码
    public static void ChangePwd(RequestCallback callback,String user_id,String oldpassword,String newpassword,String twopassword){
        Call call=getApi().changePwd(user_id,oldpassword,newpassword,twopassword);
        send(call,callback);
    }

    //修改资料
    public static void Upziliao(RequestCallback callback, String user_id, File file,String phone, String zhuzhi){
        Call call=getApi().xiugaizilaio(user_id,file,phone,zhuzhi);
        send(call,callback);
    }

    //获取余额
    public static void getYue(RequestCallback callback, String user_id){
        Call call=getApi().yue(user_id);
        send(call,callback);
    }
}

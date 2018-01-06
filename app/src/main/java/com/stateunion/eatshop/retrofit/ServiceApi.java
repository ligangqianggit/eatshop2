package com.stateunion.eatshop.retrofit;



import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.entiity.DingCanBean;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.retrofit.entiity.YueBean;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhangguozheng on 2017/8/23.
 */

public interface ServiceApi {
    /**
     * 密码登录
     *
     * @param useLoginName
     * @param useLoginPswd
     * @param identify
     * @param uuid
     * @return
     */
    /**
     *
     */
    @POST("service/user/login")
    @FormUrlEncoded
    Call<UserInfoBean> passwordLogin(@Field("useLoginName") String useLoginName,
                                     @Field("useLoginPswd") String useLoginPswd,
                                     @Field("identify") String identify,
                                     @Field("uuid") String uuid
    );

    /**
     * 密码登录
     *
     * @param useLoginName
     * @param useLoginPswd
     * @param identify
     * @param uuid
     * @return
     */
    /**
     *
     */
    @POST("login")
    @FormUrlEncoded
    Call<UserInfoBean> pawLogin(@Field("user") String useLoginName,
                            @Field("password") String useLoginPswd

    );

    /**
     * 食品列表页
     *
     * @param type 餐品分类
     * @return
     */
    /**
     *
     */
    @POST("food_list")
    @FormUrlEncoded
    Call<DingCanBean> getdingcaninfo(@Field("fen") String type);

    /**
     * 支付接口
     *
     */
    @POST("dingdan")
    @FormUrlEncoded
    Call<BaseBean> goPay(@Field("pay_time") String pay_time,@Field("pay_lei") String pay_lei,
                       @Field("all_money") String all_money, @Field("list") List<String> list);

    /**
     * 个人中心接口
     * @param user_id 用户工号
     */
    @POST("zhongxin")
    @FormUrlEncoded
    Call<PersonInfoBean> getPresonInfo(@Field("user_id") String user_id);

    /**
     * 意见反馈接口
     * @param user_id 用户工号
     * @param neirong 反馈内容
     */
    @POST("yijian")
    @FormUrlEncoded
    Call<BaseBean> toFankui(@Field("neirong") String neirong,@Field("user_id") String user_id);

    /**
     * 修改密码接口
     * @param user_id 用户工号
     * @param oldpassword 旧密码
     * @param newpassword 新密码密码
     * @param oldpassword 确认新密码
     */

    @POST("uppassword")
    @FormUrlEncoded
    Call<BaseBean> changePwd(@Field("user_id") String user_id,@Field("oldpassword") String oldpassword,
                             @Field("newpassword") String newpassword,@Field("twopassword") String twopassword);

    /**
     * 修改资料接口
     * @param
     */

    @POST("xiugai")
    @FormUrlEncoded
    Call<BaseBean> xiugaizilaio(@Field("user_id") String user_id, @Field("touxiang")File file, @Field("phone") String phone,
                                @Field("zhuzhi") String zhuzhi);

    /**
     * 获取余额
     * @param user_id 用户工号
     */
    @POST("yue")
    @FormUrlEncoded
     Call<YueBean> yue(@Field("user_id") String user_id);

    /**
     * 历史订单
      */
    @POST("dinglist")
    @FormUrlEncoded
    Call<BaseBean> history(@Field("user_id") String user_id);
}

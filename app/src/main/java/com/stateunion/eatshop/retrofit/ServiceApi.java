package com.stateunion.eatshop.retrofit;



import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.entiity.DingCanBean;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.retrofit.entiity.YueBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

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
    @Multipart
    Call<BaseBean> xiugaizilaio(@Query("user_id") String user_id,
                                @Query("phone") String phone,
                                @Query("zhuzhi") String zhuzhi,
                                @Part("touxiang") RequestBody description,
                                @Part MultipartBody.Part file
//                                @Part("avatar\"; filename=\"avatar.jpg") RequestBody avatar
                                );

    @FormUrlEncoded
    @POST("xiugai")
    Call<BaseBean> xiugaizilaios(@Field("gonghao") String user_id,
                                @Field("phone") String phone,
                                @Field("zhuzhi") String zhuzhi,
                                 @Field("touxiang") File touxiang

                                 //@Part("avatar\"; filename=\"avatar.jpg") RequestBody avatar
//                                  @PartMap() Map<String, RequestBody> files
    );



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
    Call<HisttoryBean> history(@Field("user_id") String user_id);

    /**
     * 余额支付
     * @param user_id
     *
     */
    @POST("yuezhifu")
    @FormUrlEncoded
    Call<BaseBean> yuepay(@Field("user_id") String user_id,@Field("allmoney") String allmoney);
}

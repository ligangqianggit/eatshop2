package com.stateunion.eatshop.retrofit;

import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.entiity.DIngDanHaoBean;
import com.stateunion.eatshop.retrofit.entiity.DingCanBean;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.entiity.PostOrderBean;
import com.stateunion.eatshop.retrofit.entiity.QuCanMingXiBean;
import com.stateunion.eatshop.retrofit.entiity.TuiDanShenHeBean;
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
import retrofit2.http.QueryMap;

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
    Call<DingCanBean> getdingcaninfo(@Field("riqi") String type,@Field("fenlei") String fenlei);

    /**
     * 支付接口
     *
     */
    @POST("dingdan")
    @FormUrlEncoded
    Call<BaseBean> goPay(@Field("pay_time") String pay_time,@Field("pay_lei") String pay_lei,
                       @Field("all_money") String all_money, @Field("list") String list);

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

    @Multipart
    @POST("xiugai")
    Call<BaseBean> doFeedback(@QueryMap() Map<String, String> params,
                                    @Part() MultipartBody.Part part);

    @POST("xiugai")
    @FormUrlEncoded
    Call<BaseBean> ces(@Field("phone") String user_id,@Field("zhuzhi") String oldpassword,
                             @Field("gonghao") String newpassword,@PartMap Map<String, RequestBody> files);

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


    /**
     * 历史订单详情
     * @param user_id
     *
     */
    @POST("dingxiang")
    @FormUrlEncoded
    Call<OrderBean> ddxiangqing(@Field("user_id") String user_id, @Field("order_sn") String order_sn);

//    /**
//     * 评价订单详情
//     * @param user_id
//     *
//     */
//    @POST("dingxiang")
//    @FormUrlEncoded
//    Call<OrderBean> ddxiangqing(@Field("user_id") String user_id, @Field("order_sn") String order_sn);
    /**
     * 取餐明细页
     * @param user_id
     *
     */
    @POST("saomiao")
    @FormUrlEncoded
    Call<QuCanMingXiBean> qucanmingxi(@Field("user_id") String user_id, @Field("order_sn") String order, @Field("shijianduan") String shijianduan, @Field("fenlei") String fenlei);


    /**
     * 退单审核list
     * @param
     *
     */
    @POST("tui")
    @FormUrlEncoded
    Call<TuiDanShenHeBean> tuidanshenhe(@Field("user_id") String user_id);


    /**
     * 取餐时获取订单号接口
     * @param
     *
     */
    @POST("zuihao")
    @FormUrlEncoded
    Call<DIngDanHaoBean> dingdanhao(@Field("user_id") String user_id);

    /**
     * 评价接口
     * @param
     *
     */
    @POST("pingjia")
    @FormUrlEncoded
    Call<BaseBean> ygpingjia(@Field("dingdan_id") String dingdan_id,@Field("dingdan_id") List<String> list);

    /**
     * 退单接口
     * @param
     *
     */
    @POST("shenqing")
    @FormUrlEncoded
    Call<BaseBean> tuidan(@Field("user_id") String user_id,@Field("order_sn") String order_sn,@Field("message") String message);
}

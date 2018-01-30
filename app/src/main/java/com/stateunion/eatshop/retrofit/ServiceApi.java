package com.stateunion.eatshop.retrofit;

import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.entiity.CSPingJIaBean;
import com.stateunion.eatshop.retrofit.entiity.DIngDanHaoBean;
import com.stateunion.eatshop.retrofit.entiity.DingCanBean;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.retrofit.entiity.PaiMingBean;
import com.stateunion.eatshop.retrofit.entiity.PersonInfoBean;
import com.stateunion.eatshop.retrofit.entiity.PostOrderBean;
import com.stateunion.eatshop.retrofit.entiity.QuCanMingXiBean;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;
import com.stateunion.eatshop.retrofit.entiity.TuiDanShenHeBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.retrofit.entiity.YGPingJIaBean;
import com.stateunion.eatshop.retrofit.entiity.YuZhiFuBean;
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
     * 生成订单接口
     *
     */
    @POST("dingdan")
    @FormUrlEncoded
    Call<YuZhiFuBean> goPay(@Field("user_id") String user_id, @Field("pay_time") String pay_time, @Field("pay_lei") String pay_lei,
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
                                 @Field("tou") String touxiang

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
    Call<BaseBean> yuepay(@Field("user_id") String user_id,@Field("order_sn") String order_sn,@Field("allmoney") String allmoney);


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
    Call<QuCanMingXiBean> qucanmingxi(@Field("user_id") String user_id,
                                      @Field("order_sn") String order,
                                      @Field("shijianduan") String shijianduan,
                                      @Field("fenlei") String fenlei);


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
    Call<BaseBean> ygpingjia(@Field("goods_id") String goods_id,
                             @Field("shiwu_id") String shiwu_id,
                             @Field("foot_name") String foot_name,
                             @Field("user_id") String user_id,
                             @Field("chushi") String chushi,
                             @Field("content") String content,
                             @Field("fen") String fen
                             );

    /**
     * 退单接口
     * @param
     *
     */
    @POST("shenqing")
    @FormUrlEncoded
    Call<BaseBean> tuidan(@Field("user_id") String user_id,@Field("order_sn") String order_sn,@Field("message") String message);

    /**
     * 退单接口
     * @param
     *
     */
    @POST("do_tui")
    @FormUrlEncoded
    Call<BaseBean> tuichushishenhe(@Field("order_sn") String order_sn,@Field("jiekou") int jiekou,@Field("jvyuan") String juyuan
            );


    /**
     * 厨师长端查看评价列表
     * @param
     *
     */
    @POST("ping_list")
    @FormUrlEncoded
    Call<CSPingJIaBean> pingjiaList(@Field("user_id") String user_id);

    /**
     * 厨师长端统计页
     * @param
     *
     */
    @POST("diantong")
    @FormUrlEncoded
    Call<TongJiBean> tongji(@Field("shijianduan") String shijianduan);


    /**
     * 厨师长厨师排名
     * @param
     *
     */
    @POST("chupai")
    @FormUrlEncoded
    Call<PaiMingBean> paiming(@Field("user_id") String user_id);

    /**
     * 可评价列表
     * @param
     *
     */
    @POST("pingxian")
    @FormUrlEncoded
    Call<YGPingJIaBean> kepinglist(@Field("order_sn") String order_sn);


    /**
     * 上报菜品
     * @param
     *
     */
    @POST("shangchuan")
    @FormUrlEncoded
    Call<BaseBean> shangchuanfood(@Field("foot_name") String foot_name,      //菜名
                               @Field("foot_jianjie") String foot_jianjie,//菜品简介
                               @Field("foodtu") String foodtu,            //菜品图片
                               @Field("chushi") String chushi,            //厨师姓名
                               @Field("jiage") String jiage,              //价格
                               @Field("shuliang") String shuliang,        //菜品的数量
                               @Field("fenlei") String fenlei,            //菜品的分类
                               @Field("shijianduan") String shijianduan,  //菜品所属的类型（早餐，晚餐。午餐）
                               @Field("yuyue_start") String yuyue_start,  //预约开始时间
                               @Field("jiezhi") String jiezhi,            //预约截至时间
                               @Field("riqi") String riqi,                //点餐日期
                               @Field("tui_time") String tui_time       //退单日期
                               );

    /**
     * 支付成功回掉
     * @param
     *
     */
    @POST("wx_notify")
    @FormUrlEncoded
    Call<BaseBean> paynotity(@Field("order_sn") String order_sn,@Field("code") String code);


}

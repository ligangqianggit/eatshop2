package com.stateunion.eatshop.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.PayListAdapter;
import com.stateunion.eatshop.custom_view.MyListView;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.PostOrderBean;
import com.stateunion.eatshop.retrofit.entiity.YuZhiFuBean;
import com.stateunion.eatshop.retrofit.entiity.YueBean;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;
import com.stateunion.eatshop.view.baseactivity.PayJieGuoActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

import static com.stateunion.eatshop.view.baseactivity.DingCanActivity.goodsAdapter;
import static com.stateunion.eatshop.view.baseactivity.DingCanActivity.selectedList;


/**
 * Created by zhangguozheng on 2017/12/14.
 */

public class PayService extends BaseActivity{
    private ImageView zfbPay,wxPay,iv_querendingdan_back;
    private RadioGroup radioGroup;
    public static RadioButton yue,zhifubao,weixin;
    private Button buyNow;
    private boolean isWxPay=false;
    private boolean isYEPay=false;

    private IWXAPI msgApi;
    private LinearLayout rl_zhifubao_pay,rl_weixin_pay;
    ListView lv_product;
    public static String money;
    Button bt_pay;
    private PayListAdapter payListAdapter;
    private List<String> list;
    public static String stryue;
   Gson gson;
    List<PostOrderBean> OrderList=new ArrayList();
String payType="微信支付";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent=getIntent();
        money=intent.getStringExtra("money");
        init();
    }
    private void init(){
        lv_product = (ListView)findViewById(R.id.lv_pay);
        iv_querendingdan_back= (ImageView) findViewById(R.id.iv_querendingdan_back);
        iv_querendingdan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayService.this.finish();
            }
        });

         GsonBuilder gsonBuilder=new GsonBuilder();
        gson=gsonBuilder.create();
        for (int i=0;i<selectedList.size();i++){
            PostOrderBean postOrderBean=new PostOrderBean();

            postOrderBean.setNum(selectedList.valueAt(i).getNum()+"");
            postOrderBean.setId(selectedList.valueAt(i).getProduct_id()+"");
             OrderList.add(postOrderBean);
          }
          payListAdapter=new PayListAdapter(PayService.this,goodsAdapter,selectedList);
        lv_product.setAdapter(payListAdapter);

          radioGroup= (RadioGroup) findViewById(R.id.mRadioGroup);
          yue= (RadioButton) findViewById(R.id.radio_yue);
          weixin=(RadioButton) findViewById(R.id.radio_weixin);
          zhifubao=(RadioButton) findViewById(R.id.radio_zhifubao);
          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                  if(yue.getId()==checkedId){
                      payType="余额支付";
                  }
                  if(weixin.getId()==checkedId){
                      payType="微信支付";

                  }
                  if(zhifubao.getId()==checkedId){
                      payType="支付宝支付";

                  }
              }
          });


        bt_pay= (Button) findViewById(R.id.bt_pay);
        bt_pay.setText(money+"元");
        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payType.equals("余额支付")){
                    SubmitOrder();
                }else if(payType.equals("微信支付")){
//                    if(msgApi==null) {

                        msgApi = WXAPIFactory.createWXAPI(PayService.this, null);
                    Log.d("---------",msgApi.isWXAppInstalled()+""+msgApi.isWXAppSupportAPI());
//                        if (msgApi.isWXAppInstalled() && msgApi.isWXAppSupportAPI()) {
                            payWithWX();
//                        } else {
//                            showError("微信客户端未安装，请确认");
//                        }
//                    }
                }else if(payType.equals("支付宝支付")){
                    Toast.makeText(PayService.this,"支付宝支付，请使用其他方式支付！",Toast.LENGTH_LONG).show();
//                    payWithZFB();
                    Pya();
                }
            }
        });
//        RequestCommand.getYue(new YueCallBack(PayService.this), AppSessionEngine.getLoginInfo().getGonghao().toString());
    }
   private void SubmitOrder(){
//       SubmitCallBackack
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
       String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
       Log.v("eatshop","用户名:"+AppSessionEngine.getLoginInfo().getGonghao().toString());
       Log.v("eatshop","时间戳:"+date);
       Log.v("eatshop","钱数："+money);
        Log.v("eatshop","list 样式:   "+gson.toJson(OrderList));

       Log.v("eatshop","支付类型："+ payType);

       RequestCommand.zhifujiekou(new SubmitCallBack(this),AppSessionEngine.getLoginInfo().getGonghao().toString(),date,payType,money,gson.toJson(OrderList));
   }
    //获取余额
    public class YueCallBack extends DialogCallback<YueBean,PayService> {
        public YueCallBack(PayService requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(YueBean yueBean, Call<YueBean> call) {
            super.onResponseSuccess(yueBean, call);
             Double.parseDouble(yueBean.getBody().getYumoney());
            yue.setText("当前余额："+yueBean.getBody().getYumoney()+"元");
            if(Double.parseDouble(yueBean.getBody().getYumoney())<Double.parseDouble(money)){
               //余额小于支付金额
                yue.setTextColor(PayService.this.getResources().getColor(R.color.gray));
                yue.setClickable(false);
                yue.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        Toast.makeText(getAttachTarget().getBaseActivity(),"余额不足，请使用其他方式支付！",Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
            }
        }
    }

    //获取时间戳
    public String getTime(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String  str=String.valueOf(time);
        return str;
    }

//    private View.OnClickListener listener=new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          switch (v.getId()){
//              case R.id.bt_pay:
//                if (isWxPay){
//                    if(msgApi==null)
//                        msgApi= WXAPIFactory.createWXAPI(PayService.this,null);
//                    if(msgApi.isWXAppInstalled()&&msgApi.isWXAppSupportAPI()){
//                        payWithWX();
//                    } else {
//                        showError("微信客户端未安装，请确认");
//                    }
//
//                } else {
//                    payWithZFB();
//                }
//                  break;
//              case R.id.rl_zhifubao_pay:
//                   zfbPay.setImageResource(R.mipmap.right_circle_true);
//                  wxPay.setImageResource(R.mipmap.right_circle_false);
//                  isWxPay = false;
//                  isYEPay=false;
//                  break;
//              case R.id.radio_weixin:
//                  wxPay.setImageResource(R.mipmap.right_circle_true);
//                  zfbPay.setImageResource(R.mipmap.right_circle_false);
////                  yue.setImageResource(R.mipmap.right_circle_false);
//                  isWxPay = true;
//                  isYEPay=false;
//                  break;
//
//              case R.id.radio_yue:
//                  wxPay.setImageResource(R.mipmap.right_circle_false);
//                  zfbPay.setImageResource(R.mipmap.right_circle_false);
//                  isYEPay=true;
//                  isWxPay = false;
//
//                  break;
//              default:
//                  break;
//          }
//          }
//      };

    private void payWithZFB() {
        ZFBPayUtil zfb = new ZFBPayUtil();
        zfb.payZFB(PayService.this, money, "商品", "1212121", "www.baidu.com"); // 调用
    }

    private void payWithWX() {
        WXPayUtil pay = new WXPayUtil();
         pay.payWX(getApplicationContext(), (int) 0.1 + "", "ceshi", "ceshi", msgApi);
    }
  private void Pya(){

         MyALipayUtils.ALiPayBuilder builder = new MyALipayUtils.ALiPayBuilder();
        builder.setAppid("2018010301535568")
                .setRsa(ZFBPayUtil.RSA_PRIVATE)//根据情况设置Rsa2与Rsa
                .setMoney("0.01")//单位时分
                .setTitle("支付测试")
                .setOrderTradeId("487456")//从服务端获取
                .setNotifyUrl("fdsfasdf")//从服务端获取
                .build()
                .toALiPay(PayService.this);

    }

    public void callWXPayFailure() {

     }
    public class SubmitCallBack extends DialogCallback<YuZhiFuBean,PayService> {

        public SubmitCallBack(PayService requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(YuZhiFuBean yuZhiFuBean, Call<YuZhiFuBean> call) {
            super.onResponseSuccess(yuZhiFuBean, call);
            if(yuZhiFuBean.getSuccess()==1){
                RequestCommand.YuePay(new YuePayCallBack(PayService.this),AppSessionEngine.getLoginInfo().getGonghao().toString(),yuZhiFuBean.getBody().toString(),money);
            }

        }
    }

    public class  YuePayCallBack extends DialogCallback<BaseBean,PayService>{
        public YuePayCallBack(PayService requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
            if(baseBean.getSuccess()==1){
                Intent intent=new Intent(PayService.this, PayJieGuoActivity.class);
                startActivity(intent);
                PayService.this.finish();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

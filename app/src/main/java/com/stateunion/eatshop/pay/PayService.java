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
    //    private IWXAPI msgApi;
    private LinearLayout rl_zhifubao_pay,rl_weixin_pay;
    MyListView lv_product;
    public static String money;
    Button bt_pay;
    private PayListAdapter payListAdapter;
    private List<String> list;
    public static String stryue;
   Gson gson;
    List<PostOrderBean> OrderList=new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent=getIntent();
        money=intent.getStringExtra("money");
        init();
    }
    private void init(){
        lv_product = (MyListView)findViewById(R.id.lv_pay);
        iv_querendingdan_back= (ImageView) findViewById(R.id.iv_querendingdan_back);
        iv_querendingdan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayService.this.finish();
            }
        });
        Log.v("eatshop","lv_product"+lv_product);
        Log.v("eatshop","selectedList"+selectedList);
        Log.v("eatshop","goodsAdapter"+goodsAdapter);
        PostOrderBean postOrderBean=new PostOrderBean("7","2");
        PostOrderBean postOrderBeans=new PostOrderBean("9","2");
        GsonBuilder gsonBuilder=new GsonBuilder();
        gson=gsonBuilder.create();
//        for (int i=0;i<selectedList.size();i++){
//           postOrderBean.setNum(selectedList.valueAt(i).getNum()+"");
//            postOrderBean.setId(selectedList.valueAt(i).getProduct_id()+"");
        OrderList.add(postOrderBean);
        OrderList.add(postOrderBeans);

//        }
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

                  }
                  if(weixin.getId()==checkedId){

                  }
                  if(zhifubao.getId()==checkedId){

                  }
              }
          });


        bt_pay= (Button) findViewById(R.id.bt_pay);
        bt_pay.setText(money+"元");
        bt_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitOrder();
//                Intent intent=new Intent(PayService.this, PayJieGuoActivity.class);
//                startActivity(intent);
//                PayService.this.finish();
            }
        });
        RequestCommand.getYue(new YueCallBack(PayService.this), AppSessionEngine.getLoginInfo().getGonghao().toString());
    }
   private void SubmitOrder(){
//       SubmitCallBackack
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
       String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
//       gson.toJson(OrderList)  list 以string 传递这样就可以
       Log.v("eatshop","========----===-===-=="+OrderList.toString());
       RequestCommand.zhifujiekou(new SubmitCallBack(this),date,"余额支付",money,OrderList);
   }
    //获取余额
    public class YueCallBack extends DialogCallback<YueBean,PayService> {
        public YueCallBack(PayService requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(YueBean yueBean, Call<YueBean> call) {
            super.onResponseSuccess(yueBean, call);
            Log.v("eatshop","====================="+yueBean.getBody().getYumoney());
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

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//          switch (v.getId()){
//              case R.id.bt_buy:
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
//
//                  zfbPay.setImageResource(R.mipmap.right_circle_true);
//                  wxPay.setImageResource(R.mipmap.right_circle_false);
//                  isWxPay = false;
//                  break;
//              case R.id.rl_weixin_pay:
//                  wxPay.setImageResource(R.mipmap.right_circle_true);
//                  zfbPay.setImageResource(R.mipmap.right_circle_false);
//                  isWxPay = true;
//                  break;
//              default:
//                  break;
//          }
          }
      };

    private void payWithZFB() {
        ZFBPayUtil zfb = new ZFBPayUtil();
//        zfb.payZFB(PayService.this, price, ss[type], ordernum, urlback);  调用
    }

    private void payWithWX() {
        WXPayUtil pay = new WXPayUtil();
 //        pay.payWX(getApplicationContext(), (int) f + "", ss[type], wxorder, msgApi);
    }


    public void callWXPayFailure() {

     }
    public class SubmitCallBack extends DialogCallback<BaseBean,PayService> {

        public SubmitCallBack(PayService requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
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

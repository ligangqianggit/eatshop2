package com.stateunion.eatshop.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.PayListAdapter;
import com.stateunion.eatshop.custom_view.MyListView;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.callback.RequestCallback;
import com.stateunion.eatshop.retrofit.entiity.DiangCanEntity;
import com.stateunion.eatshop.retrofit.entiity.DingCanBean;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;
import com.stateunion.eatshop.view.baseactivity.DingCanActivity;
import com.stateunion.eatshop.view.baseactivity.PayJieGuoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.stateunion.eatshop.view.baseactivity.DingCanActivity.goodsAdapter;
import static com.stateunion.eatshop.view.baseactivity.DingCanActivity.selectedList;


/**
 * Created by zhangguozheng on 2017/12/14.
 */

public class PayService extends BaseActivity{
   private ImageView zfbPay,wxPay;
   private RadioGroup radioGroup;
   private RadioButton yue,zhifubao,weixin;
    private Button buyNow;
    private boolean isWxPay=false;
//    private IWXAPI msgApi;
    private LinearLayout rl_zhifubao_pay,rl_weixin_pay;
    MyListView lv_product;
    String money;
    Button bt_pay;
    private PayListAdapter payListAdapter;
    private List<String> list;
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
          Log.v("eatshop","lv_product"+lv_product);
          Log.v("eatshop","selectedList"+selectedList);
          Log.v("eatshop","goodsAdapter"+goodsAdapter);

          payListAdapter=new PayListAdapter(PayService.this,goodsAdapter,selectedList);
          lv_product.setAdapter(payListAdapter);

//          radioGroup= (RadioGroup) findViewById(R.id.mRadioGroup);
//          yue= (RadioButton) findViewById(R.id.radio_yue);
//          weixin=(RadioButton) findViewById(R.id.radio_weixin);
//          zhifubao=(RadioButton) findViewById(R.id.radio_zhifubao);
//          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//              @Override
//              public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                  if(yue.getId()==checkedId){
//                      String pay_time=getTime();
//                      RequestCommand.zhifujiekou(new zhifuCallBack(PayService.this),pay_time,"余额",money,);
//                  }
//                  if(weixin.getId()==checkedId){
//
//                  }
//                  if(zhifubao.getId()==checkedId){
//
//                  }
//              }
//          });


          bt_pay= (Button) findViewById(R.id.bt_pay);
          bt_pay.setText(money+"元");
          bt_pay.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(PayService.this, PayJieGuoActivity.class);
                  startActivity(intent);
                  PayService.this.finish();
              }
          });


    }
    //获取时间戳
    public String getTime(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String  str=String.valueOf(time);
        return str;
    }
    public static class zhifuCallBack extends DialogCallback<BaseBean,PayService> {
        public zhifuCallBack(PayService requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
            Log.v("eatshop","====================="+baseBean);
        }
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
}

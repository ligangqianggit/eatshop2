package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.RowModel;
import com.stateunion.eatshop.adapter.DDxiangqingListAdapter;
import com.stateunion.eatshop.adapter.YGPingjiaListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.retrofit.entiity.YGPingJIaBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/11.
 */

public class YGPingjiaActivity extends BaseActivity{
    private ImageView iv_pngjia_back;
    String caiid,caiming,chushi,goods_id;
    private TextView tv_ygpingjiaitem_caipin,tv_ygpingjiaitem_chushi;
    private RatingBar ratingBar;
    private String barnum;
    private EditText et_ygpingjiaitem_message;
    private Button bt_ddxiangqiang_tuiorpoing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_yuangongpingjia);
        Intent intent=getIntent();
        caiid=intent.getStringExtra("shiwuid");
        caiming=intent.getStringExtra("caiming");
        chushi=intent.getStringExtra("chushi");
        goods_id=intent.getStringExtra("id");
        intview();
    }
    public void intview(){
        bt_ddxiangqiang_tuiorpoing= (Button) findViewById(R.id.bt_ddxiangqiang_tuiorpoing);

        et_ygpingjiaitem_message= (EditText) findViewById(R.id.et_ygpingjiaitem_message);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Float i=ratingBar.getRating();
                int num=Math.round(i);
                barnum=num+"";
            }
        });

        tv_ygpingjiaitem_caipin= (TextView) findViewById(R.id.tv_ygpingjiaitem_caipin);
        tv_ygpingjiaitem_chushi= (TextView) findViewById(R.id.tv_ygpingjiaitem_chushi);
        tv_ygpingjiaitem_caipin.setText(caiming);
        tv_ygpingjiaitem_chushi.setText("厨师:"+chushi);
        iv_pngjia_back= (ImageView) findViewById(R.id.iv_pngjia_back);
        iv_pngjia_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YGPingjiaActivity.this.finish();
            }
        });

        bt_ddxiangqiang_tuiorpoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_ygpingjiaitem_message.getText().toString().equals("")){
                    Toast.makeText(YGPingjiaActivity.this,"亲，说说您对这道菜的评价吧！",Toast.LENGTH_LONG).show();
                }else{
                    Log.v("eatshop","商品id"+goods_id);
                    Log.v("eatshop","食物id"+caiid);
                    Log.v("eatshop","菜名"+caiming);
                    Log.v("eatshop","厨师"+chushi);
                    Log.v("eatshop","内容："+et_ygpingjiaitem_message.getText().toString());
                    Log.v("eatshop","分数："+barnum);
                    RequestCommand.setYGPingjia(new YGPingjiaCallBack(YGPingjiaActivity.this),goods_id,caiid,caiming,AppSessionEngine.getLoginInfo().getGonghao().toString(),chushi,et_ygpingjiaitem_message.getText().toString(),barnum);
                }
            }
        });

    }

    public class YGPingjiaCallBack extends DialogCallback<BaseBean,YGPingjiaActivity> {
        public YGPingjiaCallBack(YGPingjiaActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
            if(baseBean.getSuccess()==1){
                Toast.makeText(getAttachTarget().getBaseActivity(),"感谢您的评价",Toast.LENGTH_LONG).show();
                YGPingjiaActivity.this.finish();
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

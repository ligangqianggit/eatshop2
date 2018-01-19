package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.DDxiangqingListAdapter;
import com.stateunion.eatshop.adapter.YGPingjiaListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/11.
 */

public class YGPingjiaActivity extends BaseActivity{
    private ImageView iv_ygpingjia_back;
    public static ListView list_ygpingjia;
    String order_sn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ygpingjia);
        Intent intent=getIntent();
        order_sn=intent.getStringExtra("order_sn");
        intview();
    }
    public void intview(){
        list_ygpingjia = (ListView) findViewById(R.id.list_ygpingjia);
        iv_ygpingjia_back= (ImageView) findViewById(R.id.iv_ygpingjia_back);
        iv_ygpingjia_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YGPingjiaActivity.this.finish();
            }
        });
        RequestCommand.getYGPingjialist(new YGPingjiaCallBack(this),order_sn);
//        RequestCommand.getDDxiangqiang(new YGPingjiaCallBack(this), AppSessionEngine.getLoginInfo().getGonghao().toString(),order_sn);
    }

    public class YGPingjiaCallBack extends DialogCallback<BaseBean,YGPingjiaActivity> {
//        YGPingjiaListAdapter ygPingjiaListAdapter;
        public YGPingjiaCallBack(YGPingjiaActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
//            ygPingjiaListAdapter=new YGPingjiaListAdapter(baseBean.getBody().getGoods(),getAttachTarget().getBaseActivity());
//            list_ygpingjia.setAdapter(ygPingjiaListAdapter);
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

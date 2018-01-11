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
import com.stateunion.eatshop.adapter.HistoryListItemAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/9.
 */

public class DDXiangQingActivity extends BaseActivity{
    String order_sn;
    public static ListView list_ddxiangqiang;

    private ImageView iv_ddxiangqiang_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddxiangqing);
        Intent intent=getIntent();
        order_sn=intent.getStringExtra("order_sn");
        intview();
    }
    public void intview(){
        list_ddxiangqiang= (ListView) findViewById(R.id.list_ddxiangqiang);
        iv_ddxiangqiang_back= (ImageView) findViewById(R.id.iv_ddxiangqiang_back);
        iv_ddxiangqiang_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DDXiangQingActivity.this.finish();
                }
        });
        RequestCommand.getDDxiangqiang(new DDxiangqingCallBack(this), AppSessionEngine.getLoginInfo().getGonghao().toString(),order_sn);
    }
    public class DDxiangqingCallBack extends DialogCallback<OrderBean,DDXiangQingActivity>{
       DDxiangqingListAdapter dDxiangqingListAdapter;
        public DDxiangqingCallBack(DDXiangQingActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(OrderBean orderBean, Call<OrderBean> call) {
            super.onResponseSuccess(orderBean, call);
            dDxiangqingListAdapter=new DDxiangqingListAdapter(orderBean.getBody().getGoods(),getAttachTarget().getBaseActivity());
            list_ddxiangqiang.setAdapter(dDxiangqingListAdapter);
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

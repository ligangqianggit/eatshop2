package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.QuCanxiangqingListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.QuCanMingXiBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoEntity;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/20.
 */

public class QuCanMingXiActivity extends BaseActivity{
    private ImageView iv_qucanmingxi_back;
    private TextView tv_qucanxingqiang_user_id;
    String shijianduan1,chuangkouhao,order1;
    public static ListView list_qucanmingxi;
    private QuCanxiangqingListAdapter quCanxiangqingListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucanmingxi);
        Intent intent=getIntent();
        chuangkouhao=intent.getStringExtra("chuankouhao");
        shijianduan1=intent.getStringExtra("type");
        order1=intent.getStringExtra("dingdanhao");
        iv_qucanmingxi_back= (ImageView) findViewById(R.id.iv_qucanmingxi_back);
        iv_qucanmingxi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuCanMingXiActivity.this.finish();
            }
        });
        intview();
    }
    public void intview(){
        tv_qucanxingqiang_user_id= (TextView) findViewById(R.id.tv_qucanxingqiang_user_id);
        tv_qucanxingqiang_user_id.setText(AppSessionEngine.getLoginInfo().getGonghao());
        list_qucanmingxi= (ListView) findViewById(R.id.list_qucanmingxi);
        quCanxiangqingListAdapter=new QuCanxiangqingListAdapter(QuCanActivity.body,this);
        list_qucanmingxi.setAdapter(quCanxiangqingListAdapter);

//        RequestCommand.getQucanxiangqing(new QuCanXiangQingCallBack(this),AppSessionEngine.getLoginInfo().getGonghao(),order1,shijianduan1,chuangkouhao);
    }

//    public class QuCanXiangQingCallBack extends DialogCallback<QuCanMingXiBean,QuCanMingXiActivity>{
//       QuCanxiangqingListAdapter quCanxiangqingListAdapter;
//        public QuCanXiangQingCallBack(QuCanMingXiActivity requestView) {
//            super(requestView);
//        }
//
//        @Override
//        protected void onResponseSuccess(QuCanMingXiBean quCanMingXiBean, Call<QuCanMingXiBean> call) {
//            super.onResponseSuccess(quCanMingXiBean, call);
//            quCanxiangqingListAdapter=new QuCanxiangqingListAdapter(quCanMingXiBean.getBody(),getAttachTarget().getBaseActivity());
//            list_qucanmingxi.setAdapter(quCanxiangqingListAdapter);
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

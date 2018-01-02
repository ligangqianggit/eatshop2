package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.retrofit.entiity.UserInfoEntity;
import com.stateunion.eatshop.util.AppSessionEngine;

/**
 * Created by 青春 on 2017/12/20.
 */

public class QuCanMingXiActivity extends BaseActivity{
    private TextView tv_mingxi_ceshi,user,order,type,haoma;
    private ImageView iv_qucanmingxi_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucanmingxi);
        Intent intent=getIntent();
        String chuangkouhao=intent.getStringExtra("chuankouhao");
        String userinfo=AppSessionEngine.getLoginInfo().getGonghao();
        String type1=intent.getStringExtra("type");
        String order1="";
        user= (TextView) findViewById(R.id.yonghuming);
        order= (TextView) findViewById(R.id.dingdanhao);
        type= (TextView) findViewById(R.id.shidianduan);
        haoma= (TextView) findViewById(R.id.chuankouhao);

        user.setText("工号："+userinfo);
        order.setText("订单号："+order1);
        type.setText("类型："+type1);
        haoma.setText("窗口号："+chuangkouhao);

        iv_qucanmingxi_back= (ImageView) findViewById(R.id.iv_qucanmingxi_back);
        iv_qucanmingxi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuCanMingXiActivity.this.finish();
            }
        });
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

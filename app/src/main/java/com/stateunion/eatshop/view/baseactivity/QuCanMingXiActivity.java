package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/20.
 */

public class QuCanMingXiActivity extends BaseActivity{
    private TextView tv_mingxi_ceshi;
    private ImageView iv_qucanmingxi_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucanmingxi);
        Intent intent=getIntent();
        String ceshi=intent.getStringExtra("ceshi");
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

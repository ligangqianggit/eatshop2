package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/20.
 */

public class QuCanMingXiActivity extends BaseActivity{
    private TextView tv_mingxi_ceshi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucanmingxi);
        Intent intent=getIntent();
        String ceshi=intent.getStringExtra("ceshi");
        tv_mingxi_ceshi= (TextView) findViewById(R.id.tv_mingxi_ceshi);
        tv_mingxi_ceshi.setText(ceshi);
    }
}

package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/13.
 */

public class QuCanActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_qucan_zaocan,bt_qucan_lunch,bt_qucan_dinner,bt_qucan_xiaochao,bt_qucan_jiaban;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucan);
        intview();
    }
        public void intview(){
        bt_qucan_zaocan= (Button) findViewById(R.id.bt_qucan_zaocan);
        bt_qucan_lunch= (Button) findViewById(R.id.bt_qucan_lunch);
        bt_qucan_dinner= (Button) findViewById(R.id.bt_qucan_dinner);
        bt_qucan_xiaochao= (Button) findViewById(R.id.bt_qucan_xiaochao);
        bt_qucan_jiaban= (Button) findViewById(R.id.bt_qucan_jiaban);
        bt_qucan_zaocan.setOnClickListener(this);
        bt_qucan_lunch.setOnClickListener(this);
        bt_qucan_dinner.setOnClickListener(this);
        bt_qucan_xiaochao.setOnClickListener(this);
        bt_qucan_jiaban.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_qucan_zaocan:
                Intent intent=new Intent(this,ErWeiMaActivity.class);
                intent.putExtra("cord","0000000000001");
                startActivity(intent);
                break;

            case R.id.bt_qucan_lunch:
                Intent intent2=new Intent(this,ErWeiMaActivity.class);
                intent2.putExtra("cord","0000000000002");
                startActivity(intent2);
                break;

            case R.id.bt_qucan_dinner:
                Intent intent3=new Intent(this,ErWeiMaActivity.class);
                intent3.putExtra("cord","0000000000003");
                startActivity(intent3);
                break;

            case R.id.bt_qucan_xiaochao:
                Intent intent4=new Intent(this,ErWeiMaActivity.class);
                intent4.putExtra("cord","0000000000004");
                startActivity(intent4);
                break;

            case R.id.bt_qucan_jiaban:
                Intent intent5=new Intent(this,ErWeiMaActivity.class);
                intent5.putExtra("cord","0000000000005");
                startActivity(intent5);
                break;
        }
    }
}


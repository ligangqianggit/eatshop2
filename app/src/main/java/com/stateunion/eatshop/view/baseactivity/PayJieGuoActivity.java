package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/26.
 */

public class PayJieGuoActivity extends BaseActivity{
    private ImageView iv_payjieguo_back;
    private Button go_main;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payjieguo);
        iv_payjieguo_back= (ImageView) findViewById(R.id.iv_payjieguo_back);
        iv_payjieguo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayJieGuoActivity.this.finish();
            }
        });

        go_main= (Button) findViewById(R.id.bt_gomain);
        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayJieGuoActivity.this, MainActivity.class);
                startActivity(intent);
                getBaseActivity().finish();
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

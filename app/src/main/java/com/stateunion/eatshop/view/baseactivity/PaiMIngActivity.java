package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2018/1/6.
 */

public class PaiMIngActivity extends BaseActivity{
    private ImageView iv_paiming_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiming);
        iv_paiming_back= (ImageView) findViewById(R.id.iv_paiming_back);
        iv_paiming_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaiMIngActivity.this.finish();
            }
        });

    }
}

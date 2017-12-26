package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/12/13.
 */

public class QueRenDIngDanActivity extends BaseActivity{
    private ImageView iv_confirmation_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        iv_confirmation_back= (ImageView) findViewById(R.id.iv_confirmation_back);
        iv_confirmation_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueRenDIngDanActivity.this.finish();
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

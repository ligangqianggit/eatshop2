package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;

import butterknife.BindView;

/**
 * Created by 青春 on 2017/12/12.
 */

public class OpinActivity extends BaseActivity{
    private ImageView iv_opin_back;
    @BindView(R.id.er_opin)
    EditText er_opin;
    @BindView(R.id.bt_opin)
    Button bt_opin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opin);
        iv_opin_back= (ImageView) findViewById(R.id.iv_opin_back);
        iv_opin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpinActivity.this.finish();
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

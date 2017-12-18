package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2017/11/28.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_login_submit;
    private long clickTime = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt_login_submit= (Button) findViewById(R.id.bt_login_submit);
        bt_login_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login_submit:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            Log.v("eatshop", "exit application");
            this.finish();
        }
    }
}

package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.activity.loginactivity.*;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.util.LoginHelp;

import butterknife.BindView;
import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/12.
 */

public class OpinActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.er_opin)
    EditText er_opin;
    @BindView(R.id.bt_opin)
    Button bt_opin;
    @BindView(R.id.iv_opin_back)
    ImageView iv_opin_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opin);
        intview();
    }
    public void intview(){
        bt_opin= (Button) findViewById(R.id.bt_opin);
        iv_opin_back= (ImageView) findViewById(R.id.iv_opin_back);
        er_opin= (EditText) findViewById(R.id.er_opin);
        bt_opin.setOnClickListener(this);
        iv_opin_back.setOnClickListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_opin:
                if(er_opin.getText().toString().equals("")){
                Toast.makeText(this,"反馈内容不能为空",Toast.LENGTH_SHORT).show();
            }else{
                    Log.v("eatshop","用户id=============="+AppSessionEngine.getLoginInfo().getGonghao());
                    Log.v("eatshop","输入框=============="+er_opin.getText().toString());
                RequestCommand.FanKui(new FanKuiCallBck(this),er_opin.getText().toString(),AppSessionEngine.getLoginInfo().getGonghao().toString());
            }
            break;
            case R.id.iv_opin_back:
                OpinActivity.this.finish();
                break;
        }
    }
    public static class FanKuiCallBck extends DialogCallback<BaseBean,OpinActivity> {

        public FanKuiCallBck(OpinActivity requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
        }
    }
}

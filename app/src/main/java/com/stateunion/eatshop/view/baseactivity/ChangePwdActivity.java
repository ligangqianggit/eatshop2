package com.stateunion.eatshop.view.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.activity.loginactivity.*;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.util.AppSessionEngine;
import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/5.
 */

public class ChangePwdActivity extends BaseActivity{
    public static EditText oldpwd,newpwd1,newped2;
    private Button bt_change;
    private ImageView iv_changepwd_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        intview();
    }
    public void intview(){
        iv_changepwd_back= (ImageView) findViewById(R.id.iv_changepwd_back);
        iv_changepwd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePwdActivity.this.finish();
            }
        });
        oldpwd= (EditText) findViewById(R.id.ed_changge_oldpwd);
        newpwd1= (EditText) findViewById(R.id.ed_changge_newpwd);
        newped2= (EditText) findViewById(R.id.ed_changge_newpwd2);
        bt_change= (Button) findViewById(R.id.bt_change);
        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldpwd.getText().toString().equals("")){
                    Toast.makeText(ChangePwdActivity.this,"请输入旧密码！",Toast.LENGTH_LONG).show();
                }else if(newpwd1.getText().toString().equals("")){
                    Toast.makeText(ChangePwdActivity.this,"请输入新密码！",Toast.LENGTH_LONG).show();
                }else if(newped2.getText().toString().equals("")){
                    Toast.makeText(ChangePwdActivity.this,"请确认新密码！",Toast.LENGTH_LONG).show();
                }else if(!newped2.getText().toString().equals(newpwd1.getText().toString())){
                    Toast.makeText(ChangePwdActivity.this,"两次输入密码不一致，请重新输入！",Toast.LENGTH_LONG).show();
                }else{
                    changepwd();
                }

            }
        });
    }
    public void changepwd(){
        RequestCommand.ChangePwd(new ChanePedCallBack(this), AppSessionEngine.getLoginInfo().getGonghao().toString(),oldpwd.getText().toString(),
                newpwd1.getText().toString(),newped2.getText().toString()
                );
    }
    public static class ChanePedCallBack extends DialogCallback<BaseBean,ChangePwdActivity>{
        public ChanePedCallBack(ChangePwdActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
            if(baseBean.getSuccess()==1){
                Toast.makeText(getAttachTarget().getBaseActivity(), baseBean.getInfo().toString(),Toast.LENGTH_LONG).show();
                oldpwd.setText("");
                newpwd1.setText("");
                newped2.setText("");
                AppSessionEngine.logout();
                Intent intent=new Intent(getAttachTarget().getBaseActivity(),LogoActivity.class);
                getAttachTarget().getBaseActivity().startActivity(intent);
                getAttachTarget().getBaseActivity().finish();
            }
        }
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

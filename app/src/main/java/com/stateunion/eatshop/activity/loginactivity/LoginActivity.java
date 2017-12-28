package com.stateunion.eatshop.activity.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;
 import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.LoginResultEntity;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.util.LoginHelp;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by admini on 2017/12/27.
 */

public class LoginActivity extends BaseActivity{
    private EditText etName,etPsw;
    private Button btLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
     }

     private void initView(){
         etName= (EditText) findViewById(R.id.et_login_gonghao);
         etPsw= (EditText) findViewById(R.id.et_login_pws);
         btLogin= (Button) findViewById(R.id.bt_login_submit);
         btLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 subLogin();
             }
         });

      }
 //提交登录
    private void subLogin(){
        String name=etName.getText().toString().trim();
        String psw=etPsw.getText().toString().trim();
        if(name.length()==0){
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(psw.length()==0){
            Toast.makeText(LoginActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            return;
        }

        RequestCommand.pswLogin(new LoginCallBck(this),name,psw);
    }

      public static class LoginCallBck extends DialogCallback<UserInfoBean,LoginActivity>{

          public LoginCallBck(LoginActivity requestView) {
              super(requestView);
          }

          @Override
          protected void onResponseSuccess(UserInfoBean userInfoBean, Call<UserInfoBean> call) {
              super.onResponseSuccess(userInfoBean, call);
              LoginHelp.saveUserInfo(userInfoBean.getBody(),getAttachTarget().getBaseActivity());
              //跳转 和finash
              Intent intent=new Intent(getAttachTarget().getBaseActivity(), MainActivity.class);
              getAttachTarget().getBaseActivity().startActivity(intent);
              getAttachTarget().getBaseActivity().finish();
          }
      }
}

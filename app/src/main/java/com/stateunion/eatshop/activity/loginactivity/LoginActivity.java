package com.stateunion.eatshop.activity.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.application.ProjectApplication;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.LoginResultEntity;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.service.DemoIntentService;
import com.stateunion.eatshop.util.LoginHelp;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by admini on 2017/12/27.
 */

public class LoginActivity extends BaseActivity {
    private EditText etName, etPsw;
    private Button btLogin;
    private CheckBox ck_login;
    private boolean isHidden = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        ck_login = (CheckBox) findViewById(R.id.ck_login);
        etName = (EditText) findViewById(R.id.et_login_gonghao);
        etPsw = (EditText) findViewById(R.id.et_login_pws);
        btLogin = (Button) findViewById(R.id.bt_login_submit);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subLogin();
            }
        });
        ck_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //设置EditText文本为可见的
                    etPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    etPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
    //提交登录
    private void subLogin() {
        String name = etName.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        if (name.length() == 0) {
            Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (psw.length() == 0) {
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!DemoIntentService.cid.equals("")) {
            RequestCommand.pswLogin(new LoginCallBck(this), name, psw, DemoIntentService.cid);
        }
    }
    public static class LoginCallBck extends DialogCallback<UserInfoBean, LoginActivity> {

        public LoginCallBck(LoginActivity requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(UserInfoBean userInfoBean, Call<UserInfoBean> call) {
            super.onResponseSuccess(userInfoBean, call);
            LoginHelp.saveUserInfo(userInfoBean.getBody(), getAttachTarget().getBaseActivity());
            //跳转 和finash
            Intent intent = new Intent(getAttachTarget().getBaseActivity(), MainActivity.class);
            getAttachTarget().getBaseActivity().startActivity(intent);
            getAttachTarget().getBaseActivity().finish();
        }
    }
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                ProjectApplication.sApplication.exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

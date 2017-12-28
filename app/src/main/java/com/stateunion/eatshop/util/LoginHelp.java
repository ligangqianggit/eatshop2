package com.stateunion.eatshop.util;

import android.content.Context;

import com.stateunion.eatshop.application.ProjectApplication;
import com.stateunion.eatshop.retrofit.entiity.LoginResultEntity;


/**
 * Created by zhangguozheng on 2017/12/28.
 */

public class LoginHelp {
    public static void saveUserInfo(LoginResultEntity loginResultEntity, Context context){
        ProjectApplication application=ProjectApplication.sApplication;
        AppSessionEngine.setLoginInfo(loginResultEntity);
    }
}

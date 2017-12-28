package com.stateunion.eatshop.bean;

import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.bean.IBaseBean;
import com.stateunion.eatshop.retrofit.entiity.LoginResultEntity;

/**
 * Created by admini on 2017/12/28.
 */

public class UserInfoBean extends BaseBean implements IBaseBean{
    private LoginResultEntity body;

    public LoginResultEntity getBody() {
        return body;
    }

    public void setBody(LoginResultEntity body) {
        this.body = body;
    }
}

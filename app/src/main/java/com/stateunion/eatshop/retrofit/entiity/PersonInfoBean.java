package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

/**
 * Created by 青春 on 2018/1/4.
 */

public class PersonInfoBean extends BaseBean{
    private PersonInfoEntity body;
     public PersonInfoEntity getBody() {
        return body;
    }

    public void setBody(PersonInfoEntity body) {
        this.body = body;
    }
}

package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

/**
 * Created by 青春 on 2017/12/28.
 */

/**
 * 订餐页实体
 */
public class DingCanBean extends BaseBean{
    private DiangCanEntity body;
    public DiangCanEntity getBody(){
        return body;
    }
    public void setBody(DiangCanEntity body) {
        this.body = body;
    }
}

package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2017/12/28.
 */

/**
 * 订餐页实体
 */
public class DingCanBean extends BaseBean{
    private List<DiangCanEntity> body;

    public List<DiangCanEntity> getBody() {
        return body;
    }

    public void setBody(List<DiangCanEntity> body) {
        this.body = body;
    }
}

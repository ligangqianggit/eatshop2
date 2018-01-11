package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/10.
 */

public class QuCanMingXiBean extends BaseBean{
    private List<QuCanMingXiEntity> body;
    public List<QuCanMingXiEntity> getBody() {
        return body;
    }

    public void setBody(List<QuCanMingXiEntity> body) {
        this.body = body;
    }
}

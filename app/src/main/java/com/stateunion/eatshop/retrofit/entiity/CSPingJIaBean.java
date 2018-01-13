package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;
import java.util.List;

/**
 * Created by 青春 on 2018/1/12.
 */

public class CSPingJIaBean extends BaseBean{
    private List<CSPingJiaListEntity> body;

    public List<CSPingJiaListEntity> getBody() {
        return body;
    }

    public void setBody(List<CSPingJiaListEntity> body) {
        this.body = body;
    }
}

package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

/**
 * Created by 青春 on 2018/1/13.
 */

public class YuZhiFuBean extends BaseBean{

    /**
     * time : 1517210433
     * code : null
     * body : {"prepayid":"","noncestr":"","sign":""}
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * prepayid :
         * noncestr :
         * sign :
         */

        private String prepayid;
        private String noncestr;
        private String sign;

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}

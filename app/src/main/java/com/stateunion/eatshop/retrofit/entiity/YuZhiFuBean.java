package com.stateunion.eatshop.retrofit.entiity;

import com.google.gson.annotations.SerializedName;
import com.stateunion.eatshop.retrofit.bean.BaseBean;

/**
 * Created by 青春 on 2018/1/13.
 */

public class YuZhiFuBean extends BaseBean{

    /**
     * time : 1515831662
     * code : null
     * body : 2018011316210281418
     */


    private String body;
    /**
     * time : 1516957717
     * code : null
     * body : {"prepayid":"wx20180126170836e3f065cad30993684179","noncestr":"jMiTA7DERnopKW2HklYwymfqtzrLs5eU","sign":"2B19E38E7F3C99371779F90FE7EF3405"}
     */

    @SerializedName("body")
    private BodyBean bodyX;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BodyBean getBodyX() {
        return bodyX;
    }

    public void setBodyX(BodyBean bodyX) {
        this.bodyX = bodyX;
    }

    public static class BodyBean {
        /**
         * prepayid : wx20180126170836e3f065cad30993684179
         * noncestr : jMiTA7DERnopKW2HklYwymfqtzrLs5eU
         * sign : 2B19E38E7F3C99371779F90FE7EF3405
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

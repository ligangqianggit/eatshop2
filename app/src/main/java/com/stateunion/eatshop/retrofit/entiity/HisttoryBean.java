package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by admini on 2018/1/8.
 */

public class HisttoryBean extends BaseBean {
    List<HistoryInfo> body;

    public List<HistoryInfo> getBody() {
        return body;
    }

    public void setBody(List<HistoryInfo> body) {
        this.body = body;
    }

    public class HistoryInfo{
         private String pay_time;

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }
        List<HistoryInfoBeanList> goods;

        public List<HistoryInfoBeanList> getGoods() {
            return goods;
        }

        public void setGoods(List<HistoryInfoBeanList> goods) {
            this.goods = goods;
        }

        public class HistoryInfoBeanList{
                private String jiezhi;
                private String  foot_name;

                public String getJiezhi() {
                    return jiezhi;
                }

                public void setJiezhi(String jiezhi) {
                    this.jiezhi = jiezhi;
                }

                public String getFoot_name() {
                    return foot_name;
                }

                public void setFoot_name(String foot_name) {
                    this.foot_name = foot_name;
                }

        }
    }
}

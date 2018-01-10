package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class OrderBean extends BaseBean{
    public OrderBeanInfo body;

    public OrderBeanInfo getBody() {
        return body;
    }

    public void setBody(OrderBeanInfo body) {
        this.body = body;
    }

    public class OrderBeanInfo {
        public String status;
        public String all_money;
        public List<OrderBeanInfoList> goods;
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getAll_money() {
            return all_money;
        }

        public void setAll_money(String all_money) {
            this.all_money = all_money;
        }

        public List<OrderBeanInfoList> getGoods() {
            return goods;
        }

        public void setGoods(List<OrderBeanInfoList> goods) {
            this.goods = goods;
        }

        public class OrderBeanInfoList {
            public String jiezhi;
            public String goods_num;
            private String foot_name;
            private String jiage;

            public String getJiezhi() {
                return jiezhi;
            }

            public void setJiezhi(String jiezhi) {
                this.jiezhi = jiezhi;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getFoot_name() {
                return foot_name;
            }

            public void setFoot_name(String foot_name) {
                this.foot_name = foot_name;
            }

            public String getJiage() {
                return jiage;
            }

            public void setJiage(String jiage) {
                this.jiage = jiage;
            }
        }
    }

}

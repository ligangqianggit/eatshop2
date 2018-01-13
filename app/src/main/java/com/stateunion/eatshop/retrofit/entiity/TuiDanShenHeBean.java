package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class TuiDanShenHeBean extends BaseBean{
    public List<TuiDanShenHeBeanInfo> body;

    public List<TuiDanShenHeBeanInfo> getBody() {
        return body;
    }

    public void setBody(List<TuiDanShenHeBeanInfo> body) {
        this.body = body;
    }

    public class TuiDanShenHeBeanInfo {

        public List<TuiDanShenHeBeanInfoList> goods;

        private String user_id;
        private String pay_time;
        private String message;
        private String all_money;
        private String order_sn;

        public List<TuiDanShenHeBeanInfoList> getGoods() {
            return goods;
        }

        public void setGoods(List<TuiDanShenHeBeanInfoList> goods) {
            this.goods = goods;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getAll_money() {
            return all_money;
        }

        public void setAll_money(String all_money) {
            this.all_money = all_money;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public class TuiDanShenHeBeanInfoList {


            private String foot_name;
            private String jiage;
            private String goods_num;

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

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }
        }
    }

}

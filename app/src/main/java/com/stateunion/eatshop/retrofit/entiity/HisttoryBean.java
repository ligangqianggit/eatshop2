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

        private String order_sn;
        private String all_money;
        private int id;
        private int zhuangtai;


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

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getAll_money() {
            return all_money;
        }

        public void setAll_money(String all_money) {
            this.all_money = all_money;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getZhuangtai() {
            return zhuangtai;
        }

        public void setZhuangtai(int zhuangtai) {
            this.zhuangtai = zhuangtai;
        }

        public class HistoryInfoBeanList{
            private String jiezhi;
            private String  foot_name;
            private String jiage;
            private String goods_num;

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

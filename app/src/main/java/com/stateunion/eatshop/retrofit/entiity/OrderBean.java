package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class OrderBean extends BaseBean{
    public OrderBeanInfo body;

    public  OrderBeanInfo getBody() {
        return body;
    }

    public void setBody(OrderBeanInfo body) {
        this.body = body;
    }

    public class OrderBeanInfo {
        public String status;
        public String all_money;
        public List<OrderBeanInfoList> goods;

        private int zhuangtai;

        private String juyuan;

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

        public int getZhuangtai() {
            return zhuangtai;
        }

        public void setZhuangtai(int zhuangtai) {
            this.zhuangtai = zhuangtai;
        }

        public String getJuyuan() {
            return juyuan;
        }

        public void setJuyuan(String juyuan) {
            this.juyuan = juyuan;
        }

        public class OrderBeanInfoList {
            public String jiezhi;
            public String goods_num;
            private String foot_name;
            private String jiage;
            private int id;
            private int cid;
            private String utime;
            private String ctime;
            private int num;
            private String foodtupian;
            private String foot_jianjie;
            private String riqi;
            private String fenlei;
            private String chushi;
            private String tui_time;
            private String yuyue_start;
            private String dingdanid;
            private String shiwu_id;
            private String shijianduan;


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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getUtime() {
                return utime;
            }

            public void setUtime(String utime) {
                this.utime = utime;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getFoodtupian() {
                return foodtupian;
            }

            public void setFoodtupian(String foodtupian) {
                this.foodtupian = foodtupian;
            }

            public String getFoot_jianjie() {
                return foot_jianjie;
            }

            public void setFoot_jianjie(String foot_jianjie) {
                this.foot_jianjie = foot_jianjie;
            }

            public String getRiqi() {
                return riqi;
            }

            public void setRiqi(String riqi) {
                this.riqi = riqi;
            }

            public String getFenlei() {
                return fenlei;
            }

            public void setFenlei(String fenlei) {
                this.fenlei = fenlei;
            }

            public String getChushi() {
                return chushi;
            }

            public void setChushi(String chushi) {
                this.chushi = chushi;
            }

            public String getTui_time() {
                return tui_time;
            }

            public void setTui_time(String tui_time) {
                this.tui_time = tui_time;
            }

            public String getYuyue_start() {
                return yuyue_start;
            }

            public void setYuyue_start(String yuyue_start) {
                this.yuyue_start = yuyue_start;
            }

            public String getDingdanid() {
                return dingdanid;
            }

            public void setDingdanid(String dingdanid) {
                this.dingdanid = dingdanid;
            }

            public String getShiwu_id() {
                return shiwu_id;
            }

            public void setShiwu_id(String shiwu_id) {
                this.shiwu_id = shiwu_id;
            }

            public String getShijianduan() {
                return shijianduan;
            }

            public void setShijianduan(String shijianduan) {
                this.shijianduan = shijianduan;
            }
        }
    }

}

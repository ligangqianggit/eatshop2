package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/19.
 */

public class YGPingJIaBean extends BaseBean{

    /**
     * time : 1516358805
     * code : null
     * body : {"goods":[{"id":414,"cid":19,"utime":"1516275581","ctime":"1516275581","num":50,"foodtupian":"upload/image/2018/01/14/5a5b0558246d4ceb7b88b56019596103aaf6eec8d994c.jpg","jiezhi":"1515931200","foot_name":"早餐工作餐","foot_jianjie":"早餐套餐有豆浆、油条等","jiage":"1","riqi":"1515945600","fenlei":"套餐","chushi":" 张三","tui_time":"1515938400","yuyue_start":"1515920400","dingdanid":"2018011819394167045","shiwu_id":"139","goods_num":"1","shijianduan":"早餐","ping_zhuang":"0"},{"id":415,"cid":19,"utime":"1516275581","ctime":"1516275581","num":50,"foodtupian":"upload/image/2018/01/15/5a5c2407a7be28ecb4019cfd101cb97295bc33b918681.jpg","jiezhi":"1516276800","foot_name":"午餐工作餐","foot_jianjie":"午餐工作餐午餐工作餐午餐工作餐","jiage":"2","riqi":"1516291200","fenlei":"套餐","chushi":"张三","tui_time":"1516284000","yuyue_start":"1516266000","dingdanid":"2018011819394167045","shiwu_id":"242","goods_num":"1","shijianduan":"午餐","ping_zhuang":"0"},{"id":416,"cid":19,"utime":"1516275581","ctime":"1516275581","num":50,"foodtupian":"upload/image/2018/01/15/5a5c24ee89e388069e6d2953fb6e096477d124217ec06.jpg","jiezhi":"1516276800","foot_name":"葱爆羊肉","foot_jianjie":"葱爆羊肉葱爆羊肉葱爆羊肉葱爆羊肉","jiage":"8","riqi":"1516291200","fenlei":"小炒","chushi":"张三","tui_time":"1516284000","yuyue_start":"1516266000","dingdanid":"2018011819394167045","shiwu_id":"244","goods_num":"1","shijianduan":"午餐","ping_zhuang":"0"},{"id":417,"cid":19,"utime":"1516275581","ctime":"1516275581","num":50,"foodtupian":"upload/image/2018/01/15/5a5c25388dd1b218091e8e846fa54ed14032231cce0ea.jpg","jiezhi":"1516276800","foot_name":"小白菜炒粉条","foot_jianjie":"小白菜炒粉条小白菜炒粉条小白菜炒粉条","jiage":"6","riqi":"1516291200","fenlei":"小炒","chushi":"张三","tui_time":"1516284000","yuyue_start":"1516266000","dingdanid":"2018011819394167045","shiwu_id":"245","goods_num":"1","shijianduan":"午餐","ping_zhuang":"0"},{"id":418,"cid":19,"utime":"1516275581","ctime":"1516275581","num":50,"foodtupian":"upload/image/2018/01/15/5a5c261c6066192b6e6499cd31ab4be20c02fd3bcf309.jpg","jiezhi":"1516276800","foot_name":"晚餐工作餐","foot_jianjie":"晚餐工作餐","jiage":"1","riqi":"1516291200","fenlei":"套餐","chushi":"张三","tui_time":"1516284000","yuyue_start":"1516266000","dingdanid":"2018011819394167045","shiwu_id":"248","goods_num":"1","shijianduan":"晚餐","ping_zhuang":"0"}]}
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 414
             * cid : 19
             * utime : 1516275581
             * ctime : 1516275581
             * num : 50
             * foodtupian : upload/image/2018/01/14/5a5b0558246d4ceb7b88b56019596103aaf6eec8d994c.jpg
             * jiezhi : 1515931200
             * foot_name : 早餐工作餐
             * foot_jianjie : 早餐套餐有豆浆、油条等
             * jiage : 1
             * riqi : 1515945600
             * fenlei : 套餐
             * chushi :  张三
             * tui_time : 1515938400
             * yuyue_start : 1515920400
             * dingdanid : 2018011819394167045
             * shiwu_id : 139
             * goods_num : 1
             * shijianduan : 早餐
             * ping_zhuang : 0
             */

            private int id;
            private int cid;
            private String utime;
            private String ctime;
            private int num;
            private String foodtupian;
            private String jiezhi;
            private String foot_name;
            private String foot_jianjie;
            private String jiage;
            private String riqi;
            private String fenlei;
            private String chushi;
            private String tui_time;
            private String yuyue_start;
            private String dingdanid;
            private String shiwu_id;
            private String goods_num;
            private String shijianduan;
            private String ping_zhuang;

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

            public String getFoot_jianjie() {
                return foot_jianjie;
            }

            public void setFoot_jianjie(String foot_jianjie) {
                this.foot_jianjie = foot_jianjie;
            }

            public String getJiage() {
                return jiage;
            }

            public void setJiage(String jiage) {
                this.jiage = jiage;
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

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getShijianduan() {
                return shijianduan;
            }

            public void setShijianduan(String shijianduan) {
                this.shijianduan = shijianduan;
            }

            public String getPing_zhuang() {
                return ping_zhuang;
            }

            public void setPing_zhuang(String ping_zhuang) {
                this.ping_zhuang = ping_zhuang;
            }
        }
    }
}

package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/12.
 */

public class TongJiBean extends BaseBean{

    /**
     * time : 1515763559
     * success : 1
     * code : 0
     * info : 获取成功！
     * body : {"goods":[{"num":0,"food_name":" 小白菜炒粉条1"}],"shenglist":[{"name":"这"},{"name":"222"}],"sheng_num":0}
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
         * goods : [{"num":0,"food_name":" 小白菜炒粉条1"}]
         * shenglist : [{"name":"这"},{"name":"222"}]
         * sheng_num : 0
         */

        private int sheng_num;
        private List<GoodsBean> goods;
        private List<ShenglistBean> shenglist;

        public int getSheng_num() {
            return sheng_num;
        }

        public void setSheng_num(int sheng_num) {
            this.sheng_num = sheng_num;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<ShenglistBean> getShenglist() {
            return shenglist;
        }

        public void setShenglist(List<ShenglistBean> shenglist) {
            this.shenglist = shenglist;
        }

        public static class GoodsBean {
            /**
             * num : 0
             * food_name :  小白菜炒粉条1
             */

            private int num;
            private String food_name;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getFood_name() {
                return food_name;
            }

            public void setFood_name(String food_name) {
                this.food_name = food_name;
            }
        }

        public static class ShenglistBean {
            /**
             * name : 这
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

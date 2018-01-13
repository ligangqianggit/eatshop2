package com.stateunion.eatshop.retrofit.entiity;

import com.stateunion.eatshop.retrofit.bean.BaseBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/13.
 */

public class PaiMingBean extends BaseBean{

    /**
     * time : 1515810850
     * code : 0
     * body : {"list":[{"chushi":"王二","zongfen":10},{"chushi":"王","zongfen":5},{"chushi":"张三","zongfen":3}]}
     */

    private BodyBean body;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * chushi : 王二
             * zongfen : 10
             */

            private String chushi;
            private int zongfen;

            public String getChushi() {
                return chushi;
            }

            public void setChushi(String chushi) {
                this.chushi = chushi;
            }

            public int getZongfen() {
                return zongfen;
            }

            public void setZongfen(int zongfen) {
                this.zongfen = zongfen;
            }
        }
    }
}

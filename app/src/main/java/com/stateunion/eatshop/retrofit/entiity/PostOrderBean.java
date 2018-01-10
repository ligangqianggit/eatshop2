package com.stateunion.eatshop.retrofit.entiity;

/**
 * Created by Village on 2018/1/10.
 */

public class PostOrderBean {
   private String id;
    private String num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public PostOrderBean(String id, String num) {
        this.id = id;
        this.num = num;
    }
}

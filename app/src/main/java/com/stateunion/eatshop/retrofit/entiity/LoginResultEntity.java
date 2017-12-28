package com.stateunion.eatshop.retrofit.entiity;

/**
 * Created by zhangguozheng on 2017/8/23.
 */

public class LoginResultEntity {


    private String sessionId;
    private UserInfoEntity sftUserMdl;
    private int id;
    private int cid;
    private String num;
    private String name;
    private String sex;
    private String gonghao;
    private String phone;
    private String zhiwei;
    private String chusheng;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UserInfoEntity getSftUserMdl() {
        return sftUserMdl;
    }

    public void setSftUserMdl(UserInfoEntity sftUserMdl) {
        this.sftUserMdl = sftUserMdl;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGonghao() {
        return gonghao;
    }

    public void setGonghao(String gonghao) {
        this.gonghao = gonghao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    public String getChusheng() {
        return chusheng;
    }

    public void setChusheng(String chusheng) {
        this.chusheng = chusheng;
    }
}

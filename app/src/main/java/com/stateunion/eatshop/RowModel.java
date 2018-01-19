package com.stateunion.eatshop;

/**
 * Created by admini on 2018/1/19.
 */

public class RowModel {
    String label;           //存储entry的当前文本显示内容，通过调用toString()给出，如果三星将提供大写显示。
    public  float rating = 2.0f; //存储entry的星级数据，对应RatingBar的星级显示
    RowModel(String label){
        this.label = label;
    }
    public String toString(){
        if(rating >= 3.0){
            return label.toUpperCase();
        }
        return label;
    }

}

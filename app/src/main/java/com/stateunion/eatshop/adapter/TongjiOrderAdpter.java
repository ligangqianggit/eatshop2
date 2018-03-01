package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;

import java.util.List;

/**
 * Created by admini on 2018/1/13.
 */

public class TongjiOrderAdpter  extends BaseAdapter {
    private List<TongJiBean.BodyBean.GoodsBean> GoodsBean;
    private Context context;
    private LayoutInflater layoutInflater;
    public TongjiOrderAdpter(List<TongJiBean.BodyBean.GoodsBean> GoodsBean, Context context) {
        this.GoodsBean = GoodsBean;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return GoodsBean.size();
    }

    @Override
    public Object getItem(int i) {
        return GoodsBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TongjiOrderAdpter.Zujian zujian=null;
        if(zujian==null){
            zujian=new TongjiOrderAdpter.Zujian();
            view=layoutInflater.inflate(R.layout.activity_order_item,null);
            zujian.tv_shengri_name= (TextView) view.findViewById(R.id.tv_buynum);
            zujian.tv_shengri_name.setText(GoodsBean.get(i).getFood_name()+" x "+GoodsBean.get(i).getNum());
        }
        return view;
    }
    public final class Zujian{
        public TextView tv_shengri_name;
    }
}

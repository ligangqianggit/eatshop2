package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.CSPingJiaListEntity;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;
import com.stateunion.eatshop.util.DateUtil;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class ShengRiListAdapter extends BaseAdapter{
    private List<TongJiBean.BodyBean.ShenglistBean> shenglistBeans;
    private Context context;
    private LayoutInflater layoutInflater;
    public ShengRiListAdapter(List<TongJiBean.BodyBean.ShenglistBean> shenglistBeans, Context context) {
        this.shenglistBeans = shenglistBeans;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return shenglistBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return shenglistBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Zujian zujian=null;
        if(zujian==null){
            zujian=new Zujian();
            view=layoutInflater.inflate(R.layout.item_shengri,null);
            zujian.tv_shengri_name= (TextView) view.findViewById(R.id.tv_tongjiitem_shengri);
            zujian.tv_shengri_name.setText("员工:"+shenglistBeans.get(i).getName());
        }
        return view;
    }
    public final class Zujian{
        public TextView tv_shengri_name;
    }
}

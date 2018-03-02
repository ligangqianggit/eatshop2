package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.PaiMingBean;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class PaiMingListAdapter extends BaseAdapter{
    private List<PaiMingBean.BodyBean.ListBean> listBeans;
    private Context context;
    private LayoutInflater layoutInflater;
    public PaiMingListAdapter(List<PaiMingBean.BodyBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeans.get(i);
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
            view=layoutInflater.inflate(R.layout.item_paiming,null);
            zujian.tv_paimingitem_chushi= (TextView) view.findViewById(R.id.tv_paimingitem_chushi);
            zujian.tv_paimingitem_paiming= (TextView) view.findViewById(R.id.tv_paimingitem_paiming);
            zujian.tv_paimingitem_fenshu= (TextView) view.findViewById(R.id.tv_paimingitem_fenshu);
            int num=i+1;
            zujian.tv_paimingitem_paiming.setText(""+num);
            zujian.tv_paimingitem_chushi.setText("厨师:"+listBeans.get(i).getChushi());
            zujian.tv_paimingitem_fenshu.setText("总分数:"+listBeans.get(i).getZongfen());
        }
        return view;
    }
    public final class Zujian{
        public TextView tv_paimingitem_chushi,tv_paimingitem_fenshu,tv_paimingitem_paiming;
    }
}

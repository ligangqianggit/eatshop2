package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.retrofit.entiity.TuiDanShenHeBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class TuiDanShenHeListItemAdapter extends BaseAdapter{
    private List<TuiDanShenHeBean.TuiDanShenHeBeanInfo.TuiDanShenHeBeanInfoList> tuiDanShenHeBeanInfoLists;
    private Context context;
    private LayoutInflater layoutInflater;
    public TuiDanShenHeListItemAdapter(List<TuiDanShenHeBean.TuiDanShenHeBeanInfo.TuiDanShenHeBeanInfoList> tuiDanShenHeBeanInfoLists, Context context) {
        this.tuiDanShenHeBeanInfoLists = tuiDanShenHeBeanInfoLists;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return tuiDanShenHeBeanInfoLists.size();
    }

    @Override
    public Object getItem(int i) {
        return tuiDanShenHeBeanInfoLists.get(i);
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
            view=layoutInflater.inflate(R.layout.item_history_two,null);
            zujian.tv_history_caipin= (TextView) view.findViewById(R.id.tv_history_caipin);

            zujian.tv_history_moneyone= (TextView) view.findViewById(R.id.tv_history_moneyone);

            zujian.tv_history_shuliang= (TextView) view.findViewById(R.id.tv_history_shuliang);
            zujian.tv_history_caipin.setText(tuiDanShenHeBeanInfoLists.get(i).getFoot_name());

            zujian.tv_history_moneyone.setText(tuiDanShenHeBeanInfoLists.get(i).getJiage()+"元");
            zujian.tv_history_shuliang.setText("数量:"+tuiDanShenHeBeanInfoLists.get(i).getGoods_num());

        }
        return view;
    }
    public final class Zujian{
        public TextView tv_history_caipin,tv_history_moneyone,tv_history_shuliang;
    }
}

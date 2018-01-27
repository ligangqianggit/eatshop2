package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.view.baseactivity.YGPingjiaActivity;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class DDxiangqingListAdapter extends BaseAdapter{
    private List<OrderBean.OrderBeanInfo.OrderBeanInfoList> orderBeanInfoList;
    private Context context;
    private LayoutInflater layoutInflater;
    public DDxiangqingListAdapter(List<OrderBean.OrderBeanInfo.OrderBeanInfoList> orderBeanInfoList, Context context) {
        this.orderBeanInfoList = orderBeanInfoList;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return orderBeanInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderBeanInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Zujian zujian=null;
        if(zujian==null){
            zujian=new Zujian();
            view=layoutInflater.inflate(R.layout.item_ddxingqiang,null);

            zujian.tv_history_caipin= (TextView) view.findViewById(R.id.tv_dingxiang_caipin);
            zujian.tv_history_moneyone= (TextView) view.findViewById(R.id.tv_dingxiang_moneyone);
            zujian.bt_dingxiang_pingjia= (Button) view.findViewById(R.id.bt_dingxiang_pingjia);
            zujian.tv_history_shuliang= (TextView) view.findViewById(R.id.tv_dingxiang_shuliang);
            if(orderBeanInfoList.get(i).getPing_zhuang().equals("0")){
                zujian.bt_dingxiang_pingjia.setVisibility(View.GONE);
            }
            if(orderBeanInfoList.get(i).getPing_zhuang().equals("1")){
                //未评
                zujian.bt_dingxiang_pingjia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, YGPingjiaActivity.class);

                        intent.putExtra("id",orderBeanInfoList.get(i).getId()+"");//商品id
                        intent.putExtra("shiwuid",orderBeanInfoList.get(i).getShiwu_id());
                        intent.putExtra("caiming",orderBeanInfoList.get(i).getFoot_name());
                        intent.putExtra("chushi",orderBeanInfoList.get(i).getChushi());

                        context.startActivity(intent);
                    }
                });

            }
            if(orderBeanInfoList.get(i).getPing_zhuang().equals("2")){
                //已评价
                zujian.bt_dingxiang_pingjia.setText("已评价");
                zujian.bt_dingxiang_pingjia.setEnabled(false);
            }
            zujian.tv_history_caipin.setText(orderBeanInfoList.get(i).getFoot_name());
            zujian.tv_history_moneyone.setText("单价:"+orderBeanInfoList.get(i).getJiage()+"元");
            zujian.tv_history_shuliang.setText("数量:"+orderBeanInfoList.get(i).getGoods_num());


        }
        return view;
    }
    public final class Zujian{
        public TextView tv_history_caipin,tv_history_moneyone,tv_history_shuliang;
        public Button bt_dingxiang_pingjia;
    }
}

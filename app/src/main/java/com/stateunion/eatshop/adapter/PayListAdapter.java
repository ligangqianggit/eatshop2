package com.stateunion.eatshop.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.bean.GoodsBean;
import com.stateunion.eatshop.pay.PayService;
import com.stateunion.eatshop.util.StringUtils;
import com.stateunion.eatshop.view.baseactivity.DingCanActivity;

/**
 * Created by 青春 on 2018/1/1.
 */

public class PayListAdapter extends BaseAdapter{
    GoodsAdapter goodsAdapter;
    private PayService payService;
    private SparseArray<GoodsBean> dataList;
    public PayListAdapter(PayService payService, GoodsAdapter goodsAdapter, SparseArray<GoodsBean> dataList) {
        this.goodsAdapter =goodsAdapter;
        this.payService = payService;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final PayListAdapter.Viewholder viewholder;
        if (view == null) {
            view = LayoutInflater.from(payService).inflate(R.layout.pay_item, null);
            viewholder = new PayListAdapter.Viewholder();
            viewholder.tv_name = (TextView) view.findViewById(R.id.tv_pay_name);
            viewholder.tv_price = (TextView) view.findViewById(R.id.tv_pay_price);
            viewholder.tv_count= (TextView) view.findViewById(R.id.tv_pay_count);

            view.setTag(viewholder);
        } else {
            viewholder = (PayListAdapter.Viewholder) view.getTag();
        }
        StringUtils.filtNull(viewholder.tv_name,dataList.valueAt(position).getTitle());//商品名称
        StringUtils.filtNull(viewholder.tv_price,"￥"+dataList.valueAt(position).getPrice());//商品价格
        viewholder.tv_count.setText(String.valueOf(dataList.valueAt(position).getNum()));//商品数量
        return view;
    }

    class Viewholder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add,iv_remove;
        TextView tv_count;
    }
}

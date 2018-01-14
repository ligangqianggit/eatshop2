package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class YGPingjiaListAdapter extends BaseAdapter{
    private List<OrderBean.OrderBeanInfo.OrderBeanInfoList> orderBeanInfoList;
    private Context context;
    private LayoutInflater layoutInflater;
    int index;
    public YGPingjiaListAdapter(List<OrderBean.OrderBeanInfo.OrderBeanInfoList> orderBeanInfoList, Context context) {
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
            view=layoutInflater.inflate(R.layout.item_yuangongpingjia,null);
            zujian.tv_ygpingjiaitem_caipin= (TextView) view.findViewById(R.id.tv_ygpingjiaitem_caipin);
            zujian.tv_ygpingjiaitem_chushi= (TextView) view.findViewById(R.id.tv_ygpingjiaitem_chushi);
            zujian.ratingBar= (RatingBar) view.findViewById(R.id.ratingBar);
            zujian.et_ygpingjiaitem_message= (EditText) view.findViewById(R.id.et_ygpingjiaitem_message);

//            holder.mEtComment.setTag(commentItem);//将bean与EditText进行绑定

            zujian.et_ygpingjiaitem_message.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        index = i;
                    }
                    return false;
                }
            });

            if (index == i) {
                zujian.et_ygpingjiaitem_message.requestFocus();
            } else {
                zujian.et_ygpingjiaitem_message.clearFocus();
            }

            zujian.tv_ygpingjiaitem_caipin.setText("餐品:"+orderBeanInfoList.get(i).getFoot_name());
            zujian.tv_ygpingjiaitem_chushi.setText("厨师:"+orderBeanInfoList.get(i).getChushi());
            zujian.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    Log.v("eatshop","星星："+v);
                    ratingBar.setRating(v);
                }
            });
        }
        return view;
    }
    public final static class Zujian{
        public TextView tv_ygpingjiaitem_caipin,tv_ygpingjiaitem_chushi;
        public static RatingBar ratingBar;
        public EditText et_ygpingjiaitem_message;
    }
}

package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.util.ChildLiatviewUtil;
import com.stateunion.eatshop.util.DateUtil;
import com.stateunion.eatshop.view.baseactivity.DDXiangQingActivity;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class HistoryListAdapter extends BaseAdapter{
    private List<HisttoryBean.HistoryInfo> historyInfo;
    private HistoryListItemAdapter historyListItemAdapter;
    private Context context;
    private LayoutInflater layoutInflater;
    public HistoryListAdapter(List<HisttoryBean.HistoryInfo> historyInfo, Context context) {
        this.historyInfo = historyInfo;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return historyInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return historyInfo.get(i);
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
            view=layoutInflater.inflate(R.layout.item_history_one,null);
            zujian.bt_xiangqing= (Button) view.findViewById(R.id.bt_history_xiangqing);
            zujian.tv_history_diangdanhao= (TextView) view.findViewById(R.id.tv_history_diangdanhao);
            zujian.tv_history_time= (TextView) view.findViewById(R.id.tv_history_time);
            zujian.tv_history_money= (TextView) view.findViewById(R.id.tv_history_money);
            zujian.list_item_history= (ListView) view.findViewById(R.id.list_item_history);
            zujian.tv_history_diangdanhao.setText("订单号:"+historyInfo.get(i)
                    .getOrder_sn());
            zujian.tv_history_time.setText(DateUtil.timedate(historyInfo.get(i).getPay_time()));
            historyListItemAdapter=new HistoryListItemAdapter(historyInfo.get(i).getGoods(),context);
            zujian.list_item_history.setAdapter(historyListItemAdapter);
//            ChildLiatviewUtil.setListViewHeightBasedOnChildren(zujian.list_item_history);
            zujian.tv_history_money.setText("金额:"+historyInfo.get(i).getAll_money()+"元");

            zujian.bt_xiangqing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DDXiangQingActivity.class);
                    intent.putExtra("order_sn",historyInfo.get(i).getOrder_sn());
                    context.startActivity(intent);
                }
            });
        }
        return view;
    }
    public final class Zujian{
        public TextView tv_history_diangdanhao,tv_history_time,tv_history_money;
        public ListView list_item_history;
        public Button bt_xiangqing;
    }
}

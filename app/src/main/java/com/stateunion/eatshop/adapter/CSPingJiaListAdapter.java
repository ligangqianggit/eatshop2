package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.entiity.CSPingJiaListEntity;
import com.stateunion.eatshop.retrofit.entiity.QuCanMingXiEntity;
import com.stateunion.eatshop.util.DateUtil;

import java.util.List;

/**
 * Created by 青春 on 2018/1/9.
 */

public class CSPingJiaListAdapter extends BaseAdapter{
    private List<CSPingJiaListEntity> csPingJiaListEntities;
    private Context context;
    private LayoutInflater layoutInflater;
    public CSPingJiaListAdapter(List<CSPingJiaListEntity> csPingJiaListEntities, Context context) {
        this.csPingJiaListEntities = csPingJiaListEntities;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return csPingJiaListEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return csPingJiaListEntities.get(i);
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
            view=layoutInflater.inflate(R.layout.item_chushipingjialist,null);
            zujian.tv_pingjialist_user= (TextView) view.findViewById(R.id.tv_pingjialist_user);
            zujian.tv_pingjialist_xiadantime= (TextView) view.findViewById(R.id.tv_pingjialist_xiadantime);
            zujian.tv_pingjialist_canpin1= (TextView) view.findViewById(R.id.tv_pingjialist_canpin1);
            zujian.tv_pingjialist_message= (TextView) view.findViewById(R.id.tv_pingjialist_message);
            zujian.tv_pingjialist_fenshu= (TextView) view.findViewById(R.id.tv_pingjialist_fenshu);

            zujian.tv_pingjialist_user.setText("用户:"+csPingJiaListEntities.get(i).getUser_id());
            zujian.tv_pingjialist_xiadantime.setText("评价时间:"+DateUtil.timedate(csPingJiaListEntities.get(i).getUtime()));
            zujian.tv_pingjialist_canpin1.setText("菜品:"+csPingJiaListEntities.get(i).getFoot_name()+"    厨师:"+csPingJiaListEntities.get(i).getChushi());
            zujian.tv_pingjialist_message.setText("评价:"+csPingJiaListEntities.get(i).getContent());
            zujian.tv_pingjialist_fenshu.setText("评分:"+csPingJiaListEntities.get(i).getFen());

        }
        return view;
    }
    public final class Zujian{
        public TextView tv_pingjialist_user,tv_pingjialist_xiadantime,tv_pingjialist_canpin1,tv_pingjialist_message,tv_pingjialist_fenshu;
    }
}

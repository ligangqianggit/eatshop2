package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.retrofit.entiity.TuiDanShenHeBean;
import com.stateunion.eatshop.util.ChildLiatviewUtil;
import com.stateunion.eatshop.util.DateUtil;
import com.stateunion.eatshop.view.baseactivity.TuiDanShenHe;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/9.
 */

public class TuiDanListAdapter extends BaseAdapter{
    public static List<TuiDanShenHeBean.TuiDanShenHeBeanInfo> tuiDanShenHeBeanInfo;
    private TuiDanShenHeListItemAdapter tuiDanShenHeListItemAdapter;
    public static TuiDanShenHe context;
    private LayoutInflater layoutInflater;
    public static int position;
    public TuiDanListAdapter(List<TuiDanShenHeBean.TuiDanShenHeBeanInfo> tuiDanShenHeBeanInfo, TuiDanShenHe context) {
        this.tuiDanShenHeBeanInfo = tuiDanShenHeBeanInfo;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return tuiDanShenHeBeanInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return tuiDanShenHeBeanInfo.get(i);
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

            view=layoutInflater.inflate(R.layout.item_tuidanshenhe,null);
            zujian.tv_tuidan_user= (TextView) view.findViewById(R.id.tv_tuidan_user);
            zujian.tv_tuidan_xiadantime= (TextView) view.findViewById(R.id.tv_tuidan_xiadantime);
            zujian.tv_tuidan_yuanyin= (TextView) view.findViewById(R.id.tv_tuidan_yuanyin);
            zujian.tv_tuidan_money= (TextView) view.findViewById(R.id.tv_tuidan_money);

            zujian.list_tuidanshenhe_item= (ListView) view.findViewById(R.id.list_tuidanshenhe_item);
            zujian.bt_tuidan_jvjue= (Button) view.findViewById(R.id.bt_tuidan_jvjue);
            zujian.bt_tuidan_tongyi= (Button) view.findViewById(R.id.bt_tuidan_tongyi);

            zujian.tv_tuidan_user.setText("用户:"+tuiDanShenHeBeanInfo.get(i).getUser_id());
            zujian.tv_tuidan_xiadantime.setText("下单时间"+ DateUtil.timedate(tuiDanShenHeBeanInfo.get(i).getPay_time()));
            zujian.tv_tuidan_yuanyin.setText("原因:"+tuiDanShenHeBeanInfo.get(i).getMessage());
            zujian.tv_tuidan_money.setText("金额"+tuiDanShenHeBeanInfo.get(i).getAll_money());

            tuiDanShenHeListItemAdapter=new TuiDanShenHeListItemAdapter(tuiDanShenHeBeanInfo.get(i).getGoods(),context);
            zujian.list_tuidanshenhe_item.setAdapter(tuiDanShenHeListItemAdapter);
            ChildLiatviewUtil.setListViewHeightBasedOnChildren(zujian.list_tuidanshenhe_item);

            zujian.bt_tuidan_jvjue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //拒绝
                    position=i;
                    RequestCommand.CSZTuiDanShenHe(new TuiDanShenHeCallBack(context),tuiDanShenHeBeanInfo.get(i).getOrder_sn(),2);
                }
            });
            zujian.bt_tuidan_tongyi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //同意
                    position=i;
                    RequestCommand.CSZTuiDanShenHe(new TuiDanShenHeCallBack(context),tuiDanShenHeBeanInfo.get(i).getOrder_sn(),1);

                }
            });

        }
        return view;
    }

    public class TuiDanShenHeCallBack extends DialogCallback<BaseBean,TuiDanShenHe>{
        public TuiDanShenHeCallBack(TuiDanShenHe requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
            if(baseBean.getSuccess()==1){
//                Toast.makeText(getAttachTarget().getBaseActivity(),baseBean.getInfo(),Toast.LENGTH_LONG).show();
                  if(tuiDanShenHeBeanInfo.size()>0){
                      Log.v("eatshop","===========++++---========"+position);
                      tuiDanShenHeBeanInfo.remove(position);
                      notifyDataSetChanged();
                      Log.v("satshop","====+++++++==="+tuiDanShenHeBeanInfo.size());
                  }
            }
        }
    }
    public final class Zujian{
        public TextView tv_tuidan_user,tv_tuidan_zhuangtai,tv_tuidan_xiadantime,tv_tuidan_qucantime,tv_tuidan_yuanyin,tv_tuidan_money;
        public Button bt_tuidan_jvjue,bt_tuidan_tongyi;
        public ListView list_tuidanshenhe_item;
    }
}

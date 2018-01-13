package com.stateunion.eatshop.view.baseactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.DDxiangqingListAdapter;
import com.stateunion.eatshop.adapter.HistoryListItemAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.OrderBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/9.
 */

public class DDXiangQingActivity extends BaseActivity{
      String order_sn;
    public static ListView list_ddxiangqiang;
    private ImageView iv_ddxiangqiang_back;
    public static Button bt_ddxiangqiang_tuiorpoing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddxiangqing);
        Intent intent=getIntent();
        order_sn=intent.getStringExtra("order_sn");
        intview();
    }
    //厨师还控件
    public void intview(){
        bt_ddxiangqiang_tuiorpoing= (Button) findViewById(R.id.bt_ddxiangqiang_tuiorpoing);
        list_ddxiangqiang= (ListView) findViewById(R.id.list_ddxiangqiang);
        iv_ddxiangqiang_back= (ImageView) findViewById(R.id.iv_ddxiangqiang_back);
        iv_ddxiangqiang_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DDXiangQingActivity.this.finish();
                }
        });
        RequestCommand.getDDxiangqiang(new DDxiangqingCallBack(this), AppSessionEngine.getLoginInfo().getGonghao().toString(),order_sn);
    }
    public class DDxiangqingCallBack extends DialogCallback<OrderBean,DDXiangQingActivity>{
       DDxiangqingListAdapter dDxiangqingListAdapter;
        public DDxiangqingCallBack(DDXiangQingActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(OrderBean orderBean, Call<OrderBean> call) {
            super.onResponseSuccess(orderBean, call);
            if(orderBean.getSuccess()==1){
                dDxiangqingListAdapter=new DDxiangqingListAdapter(orderBean.getBody().getGoods(),getAttachTarget().getBaseActivity());
                list_ddxiangqiang.setAdapter(dDxiangqingListAdapter);
                if(orderBean.getBody().getZhuangtai()==0){
                    bt_ddxiangqiang_tuiorpoing.setText("评价");
                    bt_ddxiangqiang_tuiorpoing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(DDXiangQingActivity.this,YGPingjiaActivity.class);
                            intent.putExtra("order_sn",order_sn);
                            startActivity(intent);
                        }
                    });
                }else
                if(orderBean.getBody().getZhuangtai()==1){
                    bt_ddxiangqiang_tuiorpoing.setText("已评价");
                    bt_ddxiangqiang_tuiorpoing.setEnabled(false);
                }else
                if(orderBean.getBody().getZhuangtai()==2){
                    //未消费不能评价不能退
                    bt_ddxiangqiang_tuiorpoing.setVisibility(View.GONE);
                }
                if(orderBean.getBody().getZhuangtai()==3){
                    //可退单
                    bt_ddxiangqiang_tuiorpoing.setText("退单");
                    bt_ddxiangqiang_tuiorpoing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlertDialog();
                        }
                    });
                }
                if(orderBean.getBody().getZhuangtai()==4){
                    bt_ddxiangqiang_tuiorpoing.setText("退单中");
                    bt_ddxiangqiang_tuiorpoing.setEnabled(false);
                }
                if(orderBean.getBody().getZhuangtai()==5){
                    // 已退单
                    bt_ddxiangqiang_tuiorpoing.setText("已退单");
                    bt_ddxiangqiang_tuiorpoing.setEnabled(false);
                }
            }


        }
    }

    private void showAlertDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialog, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.alert_dialog);
        Button btnPositive = (Button) dialog.findViewById(R.id.bt_dialog_tuidan);
        Button btnNegative = (Button) dialog.findViewById(R.id.bt_dialog_quxiao);
        final EditText etContent = (EditText) dialog.findViewById(R.id.et_dialog_message);
        btnPositive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String str = etContent.getText().toString();
                if (isNullEmptyBlank(str)) {
                    etContent.setError("输入内如不能为空");
                } else {
                    dialog.dismiss();
                    RequestCommand.setTuidan(new TuiDanCallBack(DDXiangQingActivity.this),AppSessionEngine.getLoginInfo().getGonghao().toString(),order_sn,etContent.getText().toString());
                    Toast.makeText(DDXiangQingActivity.this, etContent.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }

    public class TuiDanCallBack extends DialogCallback<BaseBean,DDXiangQingActivity>{
        public TuiDanCallBack(DDXiangQingActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

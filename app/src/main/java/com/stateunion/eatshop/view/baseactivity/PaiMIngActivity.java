package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.PaiMingListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.PaiMingBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/6.
 */

public class PaiMIngActivity extends BaseActivity{
    private ImageView iv_paiming_back;
    public static ListView list_paiming;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiming);
        list_paiming= (ListView) findViewById(R.id.list_paiming);
        iv_paiming_back= (ImageView) findViewById(R.id.iv_paiming_back);
        iv_paiming_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaiMIngActivity.this.finish();
            }
        });
        intview();
    }
    public void intview(){
        RequestCommand.getPaiming(new PaiMingCallBack(PaiMIngActivity.this), AppSessionEngine.getLoginInfo().getGonghao().toString());
    }

public class PaiMingCallBack extends DialogCallback<PaiMingBean,PaiMIngActivity>{
        PaiMingListAdapter paiMingListAdapter;
    public PaiMingCallBack(PaiMIngActivity requestView) {
        super(requestView);
    }
    @Override
    protected void onResponseSuccess(PaiMingBean paiMingBean, Call<PaiMingBean> call) {
        super.onResponseSuccess(paiMingBean, call);
        paiMingListAdapter=new PaiMingListAdapter(paiMingBean.getBody().getList(),getAttachTarget().getBaseActivity());
        list_paiming.setAdapter(paiMingListAdapter);

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

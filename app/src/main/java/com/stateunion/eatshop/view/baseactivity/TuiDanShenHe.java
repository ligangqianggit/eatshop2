package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.TuiDanListAdapter;
import com.stateunion.eatshop.commons.engine.AppSessionEngine;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.TuiDanShenHeBean;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/12.
 */

public class TuiDanShenHe extends BaseActivity{
    private ImageView iv_tuidan_back;
    public static ListView list_tuidanshenhe;
    TuiDanListAdapter tuiDanListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuidanshenhe);
                iv_tuidan_back= (ImageView) findViewById(R.id.iv_tuidan_back);
                iv_tuidan_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TuiDanShenHe.this.finish();
                    }
        });
        intview();
    }
    public void intview(){
        list_tuidanshenhe= (ListView) findViewById(R.id.list_tuidanshenhe);
        RequestCommand.getTuiDanShengHe(new TuiDanShenHeListCallBack(this), AppSessionEngine.getgonghao().toString());
    }

    public class TuiDanShenHeListCallBack extends DialogCallback<TuiDanShenHeBean,TuiDanShenHe>{
       TuiDanListAdapter tuiDanListAdapter;
        public TuiDanShenHeListCallBack(TuiDanShenHe requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(TuiDanShenHeBean tuiDanShenHeBean, Call<TuiDanShenHeBean> call) {
            super.onResponseSuccess(tuiDanShenHeBean, call);
            tuiDanListAdapter=new TuiDanListAdapter(tuiDanShenHeBean.getBody(),getAttachTarget().getBaseActivity());
            list_tuidanshenhe.setAdapter(tuiDanListAdapter);
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

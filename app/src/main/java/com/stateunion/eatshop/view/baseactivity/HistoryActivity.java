package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.HistoryListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.HisttoryBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/13.
 */

public class HistoryActivity extends BaseActivity{
    private ImageView iv_history_back;
    public static ListView list_history;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RequestCommand.getHistoryList(new HistoryCallback(this), com.stateunion.eatshop.commons.engine.AppSessionEngine.getgonghao().toString());
        intview();

    }
    //绑定控件
    public void intview(){
        list_history= (ListView) findViewById(R.id.list_history);
        iv_history_back= (ImageView) findViewById(R.id.iv_history_back);
        iv_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryActivity.this.finish();
            }
        });
    }

    //请求历史列表返回数据
    public class HistoryCallback extends DialogCallback<HisttoryBean,HistoryActivity>{
        private HistoryListAdapter historyListAdapter;
        public HistoryCallback(HistoryActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(HisttoryBean histtoryBean, Call<HisttoryBean> call) {
            super.onResponseSuccess(histtoryBean, call);
            historyListAdapter=new HistoryListAdapter(histtoryBean.getBody(),getAttachTarget().getBaseActivity());
            list_history.setAdapter(historyListAdapter);
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

package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/13.
 */

public class HistoryActivity extends BaseActivity{
    private ImageView iv_history_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        iv_history_back= (ImageView) findViewById(R.id.iv_history_back);
        iv_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               HistoryActivity.this.finish();
            }
        });
        RequestCommand.getHistoryList(new HistoryCallback(this), AppSessionEngine.getLoginInfo().getGonghao().toString());
    }
    public class HistoryCallback extends DialogCallback<BaseBean,HistoryActivity>{

        public HistoryCallback(HistoryActivity requestView) {
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

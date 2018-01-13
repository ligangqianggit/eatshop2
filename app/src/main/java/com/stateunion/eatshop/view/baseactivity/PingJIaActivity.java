package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.CSPingJiaListAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.CSPingJIaBean;
import com.stateunion.eatshop.util.AppSessionEngine;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/12.
 */

public class PingJIaActivity extends BaseActivity {
    private ImageView iv_pingjia_back;
    public static ListView list_chushipingjia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjia);

        list_chushipingjia= (ListView) findViewById(R.id.list_chushipingjia);

        iv_pingjia_back = (ImageView) findViewById(R.id.iv_pingjia_back);
        iv_pingjia_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PingJIaActivity.this.finish();
            }
        });
        intview();
    }

    public void intview() {
        RequestCommand.getPingJiaList(new PingJiaCallBack(this), AppSessionEngine.getLoginInfo().getGonghao().toString());
    }

    public class PingJiaCallBack extends DialogCallback<CSPingJIaBean, PingJIaActivity> {
        CSPingJiaListAdapter csPingJiaListAdapter;
        public PingJiaCallBack(PingJIaActivity requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(CSPingJIaBean csPingJIaBean, Call<CSPingJIaBean> call) {
            super.onResponseSuccess(csPingJIaBean, call);
            csPingJiaListAdapter=new CSPingJiaListAdapter(csPingJIaBean.getBody(),getAttachTarget().getBaseActivity());
            list_chushipingjia.setAdapter(csPingJiaListAdapter);
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

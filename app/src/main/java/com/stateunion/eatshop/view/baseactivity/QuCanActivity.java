package com.stateunion.eatshop.view.baseactivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.stateunion.eatshop.R;
import com.stateunion.eatshop.commons.engine.AppSessionEngine;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.DIngDanHaoBean;
import com.stateunion.eatshop.zxing.android.CaptureActivity;
import com.stateunion.eatshop.zxing.bean.ZxingConfig;
import com.stateunion.eatshop.zxing.common.Constant;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/13.
 */

public class QuCanActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_qucan_zaocan,bt_qucan_lunch,bt_qucan_dinner,bt_qucan_xiaochao,bt_qucan_jiaban;
    private int REQUEST_CODE_SCAN = 111;
    private ImageView iv_qucan_back;
    public  static String dingdanhao;
    int type=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucan);

        RequestCommand.getDingdanhao(new DingDanHaoCallBack(this), AppSessionEngine.getgonghao().toString());

        intview();
    }
        public void intview(){

        iv_qucan_back= (ImageView) findViewById(R.id.iv_qucan_back);
        bt_qucan_zaocan= (Button) findViewById(R.id.bt_qucan_zaocan);
        bt_qucan_lunch= (Button) findViewById(R.id.bt_qucan_lunch);
        bt_qucan_dinner= (Button) findViewById(R.id.bt_qucan_dinner);
        bt_qucan_xiaochao= (Button) findViewById(R.id.bt_qucan_xiaochao);
        bt_qucan_jiaban= (Button) findViewById(R.id.bt_qucan_jiaban);
        iv_qucan_back.setOnClickListener(this);
        bt_qucan_zaocan.setOnClickListener(this);
        bt_qucan_lunch.setOnClickListener(this);
        bt_qucan_dinner.setOnClickListener(this);
        bt_qucan_xiaochao.setOnClickListener(this);
        bt_qucan_jiaban.setOnClickListener(this);
    }

    public class DingDanHaoCallBack extends DialogCallback<DIngDanHaoBean,QuCanActivity>{

        public DingDanHaoCallBack(QuCanActivity requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(DIngDanHaoBean dIngDanHaoBean, Call<DIngDanHaoBean> call) {
            super.onResponseSuccess(dIngDanHaoBean, call);
            dingdanhao=dIngDanHaoBean.getBody().getOrder_sn();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_qucan_zaocan:
                type=1;
                Dianqisaoma();
                break;
            case R.id.bt_qucan_lunch:
                type=2;
                Dianqisaoma();
                break;

            case R.id.bt_qucan_dinner:
                type=3;
                Dianqisaoma();
                break;

            case R.id.bt_qucan_xiaochao:
                type=4;
                Dianqisaoma();
                break;

            case R.id.bt_qucan_jiaban:
                type=5;
                Dianqisaoma();
                break;
            case R.id.iv_qucan_back:
                QuCanActivity.this.finish();
                break;
        }
    }
    public void Dianqisaoma(){
        AndPermission.with(this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent = new Intent( QuCanActivity.this, CaptureActivity.class);
                                /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                                * 也可以不传这个参数
                                * 不传的话  默认都为默认不震动  其他都为true
                                * */
                        ZxingConfig config = new ZxingConfig();
                        config.setPlayBeep(true);
                        config.setShake(true);
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);

                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                        Toast.makeText(QuCanActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Intent intent=new Intent(QuCanActivity.this,QuCanMingXiActivity.class);
                intent.putExtra("chuankouhao",content);
                intent.putExtra("dingdanhao",dingdanhao);
                if(content.equals("1")){
                    if(type==1){
                        intent.putExtra("type","早餐");
                        startActivity(intent);
                    }else if(type==2){
                        intent.putExtra("type","午餐");
                        startActivity(intent);
                    }
                    else if(type==3){
                        intent.putExtra("type","晚餐");
                        startActivity(intent);
                    }else if(type==4){
                        Toast.makeText(this, "请去2窗口取小炒", Toast.LENGTH_LONG).show();
                    } else if(type==5){
                        intent.putExtra("type","加班");
                        startActivity(intent);
                    }
                }else if(content.equals("2")){
                    if(type==4){
                        intent.putExtra("type","小炒");
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "请去1窗口取套餐", Toast.LENGTH_LONG).show();
                    }
                }
            }
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


package com.stateunion.eatshop.view.baseactivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.zxing.android.CaptureActivity;
import com.stateunion.eatshop.zxing.bean.ZxingConfig;
import com.stateunion.eatshop.zxing.common.Constant;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

/**
 * Created by 青春 on 2017/12/13.
 */

public class QuCanActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_qucan_zaocan,bt_qucan_lunch,bt_qucan_dinner,bt_qucan_xiaochao,bt_qucan_jiaban;
    private int REQUEST_CODE_SCAN = 111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qucan);
        intview();
    }
        public void intview(){
        bt_qucan_zaocan= (Button) findViewById(R.id.bt_qucan_zaocan);
        bt_qucan_lunch= (Button) findViewById(R.id.bt_qucan_lunch);
        bt_qucan_dinner= (Button) findViewById(R.id.bt_qucan_dinner);
        bt_qucan_xiaochao= (Button) findViewById(R.id.bt_qucan_xiaochao);
        bt_qucan_jiaban= (Button) findViewById(R.id.bt_qucan_jiaban);
        bt_qucan_zaocan.setOnClickListener(this);
        bt_qucan_lunch.setOnClickListener(this);
        bt_qucan_dinner.setOnClickListener(this);
        bt_qucan_xiaochao.setOnClickListener(this);
        bt_qucan_jiaban.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_qucan_zaocan:
//                Intent intent=new Intent(this,ErWeiMaActivity.class);
//                intent.putExtra("cord","0000000000001");
//                startActivity(intent);

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



                break;

            case R.id.bt_qucan_lunch:
                Intent intent2=new Intent(this,ErWeiMaActivity.class);
                intent2.putExtra("cord","0000000000002");
                startActivity(intent2);
                break;

            case R.id.bt_qucan_dinner:
                Intent intent3=new Intent(this,ErWeiMaActivity.class);
                intent3.putExtra("cord","0000000000003");
                startActivity(intent3);
                break;

            case R.id.bt_qucan_xiaochao:
                Intent intent4=new Intent(this,ErWeiMaActivity.class);
                intent4.putExtra("cord","0000000000004");
                startActivity(intent4);
                break;

            case R.id.bt_qucan_jiaban:
                Intent intent5=new Intent(this,ErWeiMaActivity.class);
                intent5.putExtra("cord","0000000000005");
                startActivity(intent5);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Intent intent=new Intent(QuCanActivity.this,QuCanMingXiActivity.class);
                intent.putExtra("ceshi",content);
                startActivity(intent);

            }
        }

    }
}


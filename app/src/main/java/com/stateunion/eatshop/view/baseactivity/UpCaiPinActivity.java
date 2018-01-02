package com.stateunion.eatshop.view.baseactivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.util.TakePhone;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by 青春 on 2017/12/21.
 */

public class UpCaiPinActivity extends BaseActivity {
    @BindView(R.id.tv_buytime)
    TextView tvBuytime;
    @BindView(R.id.tv_tuitime)
    TextView tvTuitime;
    @BindView(R.id.img_food)
    ImageView imgFood;
    @BindView(R.id.bt_upfood)
    Button btUpfood;
    private TabLayout projectTabLayout;
    private EditText et_foodtitle, et_price, et_foodinfo, et_chushi;
    private TimeSelector timeSelector;
    private TakePhone takePhoneUtils;
    public final static String IMAGE_UTILS = "Android6.0权限";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadcaidan);
        ButterKnife.bind(this);
        initViews();
        initUtil();
    }

    private void initViews() {
        projectTabLayout = (TabLayout) findViewById(R.id.project_tablayout);
        et_foodtitle = (EditText) findViewById(R.id.et_foodtitle);
        et_price = (EditText) findViewById(R.id.et_price);
        et_foodinfo = (EditText) findViewById(R.id.et_foodinfo);
        et_chushi = (EditText) findViewById(R.id.et_chushi);
        tvBuytime = (TextView) findViewById(R.id.tv_buytime);
        tvTuitime = (TextView) findViewById(R.id.tv_tuitime);
        projectTabLayout.setTabMode(TabLayout.MODE_FIXED);
        projectTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        reaetcontrol();
                        break;
                    case 1:
                        reaetcontrol();
                        break;
                    case 2:
                        reaetcontrol();
                        break;
                    case 3:
                        reaetcontrol();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void reaetcontrol() {
        et_foodtitle.setText("");
        et_price.setText("");
        et_foodinfo.setText("");
        et_chushi.setText("");
    }

    @OnClick({R.id.tv_buytime, R.id.tv_tuitime, R.id.img_food})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buytime:
                Log.d("aa", "点击");
                timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tvBuytime.setText(time);
                    }
                }, "1989-01-30 00:00", "2018-12-31 00:00");
                timeSelector.show();

//        timeSelector.setIsLoop(false);
                break;
            case R.id.tv_tuitime:

                timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tvTuitime.setText(time);
                    }
                }, "1989-01-30 00:00", "2018-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.img_food:
                PermissionGen.with(UpCaiPinActivity.this)
                        .addRequestCode(TakePhone.REQ_TAKE_PHOTO)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
//                PermissionGen.needPermission(MainActivity.this,
//                        TakePhone.REQ_SELECT_PHOTO,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE}
                break;
            case R.id.bt_upfood:
                //提交菜品
                break;
        }
    }


    /**
     * 申请拍照权限
     */
    public void initUtil() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        takePhoneUtils = new TakePhone(this, new TakePhone.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                Glide.with(UpCaiPinActivity.this).load(outputUri).into(imgFood);
            }
        }, false);//true裁剪，false不裁剪

    }

    @PermissionSuccess(requestCode = TakePhone.REQ_TAKE_PHOTO)
    private void takePhoto() {
        takePhoneUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = TakePhone.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        takePhoneUtils.selectPhoto();
    }

    @PermissionFail(requestCode = TakePhone.REQ_TAKE_PHOTO)
    private void showTip1() {
        showDialog();
    }

    @PermissionFail(requestCode = TakePhone.REQ_SELECT_PHOTO)
    private void showTip2() {
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + UpCaiPinActivity.this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //使用构建器创建出对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }
}

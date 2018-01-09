package com.stateunion.eatshop.view.baseactivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.commons.engine.AppSessionEngine;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.util.CircleTransform;
import com.stateunion.eatshop.util.TakePhone;
import com.stateunion.eatshop.view.mainfrment.upMenuDragment3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by 青春 on 2018/1/1.
 */

public class ZiLiaoActivity extends BaseActivity {
    private EditText ed_ziliao_phone, ed_ziliao_dizhi;
    private ImageView iv_ziliao_back, tv_ziliao_touxiang;
    private String imageurl, shengri, phone, zhuzhi;
    private Button bt_ziliao;

    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 1;
    private long clickTime = 0;
    private Intent mSourceIntent;
    private ValueCallback<Uri> mUploadMsg;
    public ValueCallback<Uri[]> mUploadMsgForAndroid5;

    private static final int P_CODE_PERMISSIONS = 101;
    private TakePhone takePhoneUtils;
    private Context context = null;
    private String outputFiles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziliao);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        context=this;
        imageurl = intent.getStringExtra("imageurl");
        shengri = intent.getStringExtra("shengri");
        phone = intent.getStringExtra("phone");
        zhuzhi = intent.getStringExtra("zhuzhi");
        initUtil();
        intview();
    }

    public void intview() {
        tv_ziliao_touxiang = (ImageView) findViewById(R.id.tv_ziliao_touxiang);
        ed_ziliao_phone = (EditText) findViewById(R.id.ed_ziliao_phone);
        ed_ziliao_dizhi = (EditText) findViewById(R.id.ed_ziliao_dizhi);
        bt_ziliao = (Button) findViewById(R.id.bt_ziliao);

        ed_ziliao_phone.setText(phone);
        ed_ziliao_dizhi.setText(zhuzhi);
        Picasso.with(this).load(imageurl).resize(200, 200).transform
                (new CircleTransform()).into(tv_ziliao_touxiang);
        iv_ziliao_back = (ImageView) findViewById(R.id.iv_ziliao_back);


    }

    @OnClick({R.id.iv_ziliao_back, R.id.tv_ziliao_touxiang, R.id.bt_ziliao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_ziliao_back:

                break;
            case R.id.tv_ziliao_touxiang:
                PermissionGen.with(this)
                        .addRequestCode(TakePhone.REQ_SELECT_PHOTO)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
                break;
            case R.id.bt_ziliao:
                submitInfo();
                break;
        }
    }
    public void submitInfo(){
        String path1 = "";
      List<File> fileList = new ArrayList<>();
      fileList.add(new File(outputFiles));
// 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), outputFiles);

// MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", outputFiles.toString(), requestFile);
// 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

//
        File file = new File(outputFiles);//filePath 图片地址
//        String token = "ASDDSKKK19990SDDDSS";//用户token
//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)//表单类型
//                ;//ParamKey.TOKEN 自定义参数key常量类，即参数名
        Log.d("file",outputFiles+"");
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        List<MultipartBody.Part> parts = builder.build().parts();
        Map<String,RequestBody > map=new HashMap<>();
        map.put("touxiang",imageBody);

        RequestCommand.Upziliaos(new ChangeUserInfoCallback(this),AppSessionEngine.getgonghao(),map,ed_ziliao_phone.getText().toString(),
                ed_ziliao_dizhi.getText().toString(),file);
    }

   public static class ChangeUserInfoCallback extends DialogCallback<BaseBean,ZiLiaoActivity>{

       public ChangeUserInfoCallback(ZiLiaoActivity requestView) {
           super(requestView);
       }

       @Override
       protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
           super.onResponseSuccess(baseBean, call);
           getAttachTarget().getBaseActivity().finish();
       }
   }

    /*
     *选择头像相关
     */
    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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


    /**
     * 申请拍照权限
     */
    public void initUtil() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        takePhoneUtils = new TakePhone(this, new TakePhone.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                 outputFiles=outputFile.getAbsolutePath();
                Log.d("dizhi",outputFile.getAbsolutePath()+"==="+outputUri.toString());
                Glide.with(ZiLiaoActivity.this).load(outputUri).into(tv_ziliao_touxiang);
            }
        }, false);//true裁剪，false不裁剪

    }

    @PermissionSuccess(requestCode = TakePhone.REQ_TAKE_PHOTO)
    private void takePhoto() {
        takePhoneUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = TakePhone.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        takePhoneUtils.selectPhoto(TakePhone.REQ_SELECT_PHOTO);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
    }
    private List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("multipartFiles", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

}

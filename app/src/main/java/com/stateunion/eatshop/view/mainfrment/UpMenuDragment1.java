package com.stateunion.eatshop.view.mainfrment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.TakePhone;
import com.stateunion.eatshop.util.ZoomBitmap;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by admini on 2018/1/5.
 */

public class UpMenuDragment1 extends Fragment implements IBaseDialogView {


    @BindView(R.id.et_foodtitle)//食物名称
            EditText et_foodtitle;

    @BindView(R.id.et_price)//食物价格
            EditText et_price;

    @BindView(R.id.et_foodinfo)//食物简介
            EditText et_foodinfo;

    @BindView(R.id.et_chushi)//厨师名字
            EditText et_chushi;

    @BindView(R.id.tv_yuyuestar)
    TextView tv_yuyuestar;//预约开始时间

    @BindView(R.id.img_food)
     ImageView imgwucanFood;
    @BindView(R.id.tv_dingcantime)
    TextView tv_dingcantime;
    @BindView(R.id.tv_yuyuestop)
    TextView tv_yuyuestop;
    @BindView(R.id.tv_tuitime)
    TextView tvTuitime;
    @BindView(R.id.bt_upfood)
    Button btUpfood;
    Unbinder unbinder;
    private Context context = null;
    private boolean isAlive = false;

    private TimeSelector timeSelector;
    public static TakePhone takePhoneUtils;
    public static String outputFiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upmenu_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = view.getContext();
        initUtil();
        return view;
    }
    public void intview(View view){



    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


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
                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
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
        takePhoneUtils = new TakePhone(getActivity(), new TakePhone.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
//                Glide.with(UpMenuDragment.this).load(outputUri).into(imgFood);
                outputFiles=outputFile.getAbsolutePath();
                imgwucanFood.setImageBitmap(getBitmap(outputFiles));
                Log.d("aaaa",outputFile.getAbsolutePath()+"[[[["+outputFile.toString());
            }
        }, false);//true裁剪，false不裁剪

    }

    @PermissionSuccess(requestCode = TakePhone.REQ_SELECT_PHOTO1)
    private void takePhoto() {
        takePhoneUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = TakePhone.REQ_SELECT_PHOTO1)
    private void selectPhoto() {
        takePhoneUtils.selectPhoto(TakePhone.REQ_SELECT_PHOTO1);
    }

    @PermissionFail(requestCode = TakePhone.REQ_TAKE_PHOTO1)
    private void showTip1() {
        showDialog();
    }

    @PermissionFail(requestCode = TakePhone.REQ_SELECT_PHOTO1)
    private void showTip2() {
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(UpMenuDragment1.this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("lailema","ooooo"+requestCode);

        switch (requestCode){
            case TakePhone.REQ_SELECT_PHOTO1://该结果码与FragmentActivity中是保持一致的
                //在这里获取你需要的数据
                takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
                break;
            case 2:

                break;

        }
    }

    public static Fragment newInstance() {
        return new UpMenuDragment();
    }

    @OnClick({R.id.img_food, R.id.tv_yuyuestop, R.id.tv_tuitime, R.id.bt_upfood,R.id.tv_dingcantime,R.id.tv_yuyuestar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_food:
                PermissionGen.with(UpMenuDragment1.this)
                        .addRequestCode(TakePhone.REQ_SELECT_PHOTO1)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
                break;
            case R.id.tv_yuyuestar://预约开始时间
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_yuyuestar.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_dingcantime://订餐的日期
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_dingcantime.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_yuyuestop://预约截至时间
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_yuyuestop.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_tuitime:
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tvTuitime.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.bt_upfood:
                if(et_foodtitle.getText().toString().equals("")){
                    Toast.makeText(context,"请输入菜品的名称！",Toast.LENGTH_LONG).show();
                }else if(et_price.getText().toString().equals("")){
                    Toast.makeText(context,"请输入菜品的价格！",Toast.LENGTH_LONG).show();
                }
                else if(et_chushi.getText().toString().equals("")){
                    Toast.makeText(context,"请输入厨师姓名！",Toast.LENGTH_LONG).show();
                }
                else if(et_foodinfo.getText().toString().equals("")){
                    Toast.makeText(context,"请输入菜品简介！",Toast.LENGTH_LONG).show();
                }
                else if(outputFiles==null){
                    Toast.makeText(context,"请选择图片！",Toast.LENGTH_LONG).show();
                }
                else{
                    String imagefile=submitInfo();
                    Log.v("eatshop","图片："+imagefile);
                    String riqi= tv_dingcantime.getText().toString().substring(0,10);
                    Log.v("eatshop","日期截取:"+riqi);
                    RequestCommand.setFood(new SetFoodCallBack(UpMenuDragment1.this),
                            et_foodtitle.getText().toString(),
                            et_foodinfo.getText().toString(),
                            imagefile,
                            et_chushi.getText().toString(),
                            et_price.getText().toString(),
                            "0",
                            "套餐",
                            "午餐",
                            tv_yuyuestar.getText().toString(),
                            tv_yuyuestop.getText().toString(),
                            riqi,
                            tvTuitime.getText().toString()
                    );
                }
                break;
        }
    }

    @Override
    public Dialog createDialog(int themeResId) {
        Dialog dialog = new Dialog(getActivity(), themeResId);
        return dialog;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public BaseActivity getBaseActivity() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public class   SetFoodCallBack extends DialogCallback<BaseBean,UpMenuDragment1>{
        public SetFoodCallBack(UpMenuDragment1 requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);

        }
    }
    public String submitInfo() {
        String touxiang=null;
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(outputFiles));
// 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), "");

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
        try{
            FileInputStream fis = new FileInputStream(outputFiles);
            Log.v("eatshop","图片地址："+fis);
            Bitmap bitmap  = BitmapFactory.decodeStream(fis);
            bitmap= ZoomBitmap.zoomImage(bitmap,bitmap.getWidth()/8,bitmap.getHeight()/8);
            touxiang= bitmaptoString(bitmap);
        }catch (Exception e){

        }
        return touxiang;
    }

    public String bitmaptoString(Bitmap bitmap){
//将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,60,bStream);
        byte[]bytes=bStream.toByteArray();
        string= Base64.encodeToString(bytes,Base64.DEFAULT);
        return string;
    }
    private Bitmap getBitmap(String outputFiles){
        Bitmap bitmap=null;
        try{
            FileInputStream fis = new FileInputStream(outputFiles);
            Log.v("eatshop","图片地址："+fis);
            bitmap  = BitmapFactory.decodeStream(fis);
        }catch (Exception e){

        }
        return bitmap;
    }
}

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

public class upMenuDragment3 extends Fragment implements IBaseDialogView {


    @BindView(R.id.et_xiaochao_foodtitle)//食物名称
            EditText et_foodtitle;

    @BindView(R.id.et_xiaochao_price)//食物价格
            EditText et_price;

    @BindView(R.id.et_xiaochao_foodinfo)//食物简介
            EditText et_foodinfo;

    @BindView(R.id.et_xiaochao_chushi)//厨师名字
            EditText et_chushi;

    @BindView(R.id.et_xiaochao_num)//小炒的数量
            EditText et_xiaochao_num;

    @BindView(R.id.et_xiaochao_wuorwan)//所属的类型
            EditText et_xiaochao_wuorwan;

    @BindView(R.id.tv_xiaochao_yuyuestar)
    TextView tv_yuyuestar;//预约开始时间

    @BindView(R.id.img_xiaochao_food)
    ImageView imgxiaochaoFood;
    @BindView(R.id.tv_xiaochao_dingcantime)
    TextView tv_dingcantime;
    @BindView(R.id.tv_xiaochao_yuyuestop)
    TextView tv_yuyuestop;
    @BindView(R.id.tv_xiaochao_tuitime)
    TextView tvTuitime;
    @BindView(R.id.bt_xiaochao_upfood)
    Button btUpfood;
    Unbinder unbinder;
    private Context context = null;
    private boolean isAlive = false;

    private TimeSelector timeSelector;
    public static TakePhone takePhoneUtils;
    public static String outputFiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upmenuchao_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = view.getContext();
        initUtil();

        return view;
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
                 outputFiles = outputFile.getAbsolutePath();
                imgxiaochaoFood.setImageBitmap(getBitmap(outputFiles));
                Log.d("aaaa", outputFile.getAbsolutePath() + "[[[[" + outputFile.toString());
            }
        }, false);//true裁剪，false不裁剪

    }

    @PermissionSuccess(requestCode = TakePhone.REQ_TAKE_PHOTO3)
    private void takePhoto() {
        takePhoneUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = TakePhone.REQ_SELECT_PHOTO3)
    private void selectPhoto() {
        takePhoneUtils.selectPhoto(TakePhone.REQ_SELECT_PHOTO3);
    }

    @PermissionFail(requestCode = TakePhone.REQ_TAKE_PHOTO3)
    private void showTip1() {
        showDialog();
    }

    @PermissionFail(requestCode = TakePhone.REQ_SELECT_PHOTO3)
    private void showTip2() {
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(upMenuDragment3.this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("lailema", "ooooo" + requestCode);

        switch (requestCode) {
            case TakePhone.REQ_SELECT_PHOTO3://该结果码与FragmentActivity中是保持一致的
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

    @OnClick({R.id.img_xiaochao_food, R.id.tv_xiaochao_yuyuestop, R.id.tv_xiaochao_tuitime, R.id.bt_xiaochao_upfood, R.id.tv_xiaochao_dingcantime, R.id.tv_xiaochao_yuyuestar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_xiaochao_food:
                PermissionGen.with(upMenuDragment3.this)
                        .addRequestCode(TakePhone.REQ_SELECT_PHOTO3)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
                break;
            case R.id.tv_xiaochao_yuyuestar://预约开始时间
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_yuyuestar.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_xiaochao_dingcantime://订餐的日期
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_dingcantime.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_xiaochao_yuyuestop://预约截至时间
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tv_yuyuestop.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.tv_xiaochao_tuitime:
                timeSelector = new TimeSelector(context, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        tvTuitime.setText(time);
                    }
                }, "2018-01-01 00:00", "2030-12-31 00:00");
                timeSelector.show();
                break;
            case R.id.bt_xiaochao_upfood:
                if (et_foodtitle.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入菜品的名称！", Toast.LENGTH_LONG).show();
                } else if (et_price.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入菜品的价格！", Toast.LENGTH_LONG).show();
                } else if (et_chushi.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入厨师姓名！", Toast.LENGTH_LONG).show();
                } else if (et_foodinfo.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入菜品简介！", Toast.LENGTH_LONG).show();
                }
//                else if (outputFiles == null) {
//                    Toast.makeText(context, "请选择图片！", Toast.LENGTH_LONG).show();
//                }
                else if (et_xiaochao_num.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入小炒的数量！", Toast.LENGTH_LONG).show();
                } else if (et_xiaochao_wuorwan.getText().toString().equals("")) {
                    Toast.makeText(context, "请输入小炒是输属于早上或者晚上！", Toast.LENGTH_LONG).show();
                } else {
                    String imagefile = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAACXBIWXMAAAsTAAALEwEAmpwYAAAKfWlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDAgNzkuMTYwNDUxLCAyMDE3LzA1LzA2LTAxOjA4OjIxICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczpwaG90b3Nob3A9Imh0dHA6Ly9ucy5hZG9iZS5jb20vcGhvdG9zaG9wLzEuMC8iIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIiB4bWxuczpleGlmPSJodHRwOi8vbnMuYWRvYmUuY29tL2V4aWYvMS4wLyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxNyAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMTEtMjhUMTg6MDU6MDgrMDg6MDAiIHhtcDpNZXRhZGF0YURhdGU9IjIwMTctMTItMjhUMTU6MDk6NDYrMDg6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTEyLTI4VDE1OjA5OjQ2KzA4OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo3NWU2YzQ0OS03ODFhLTQ4NmEtYmQ2Yy1kY2ZkMzEyYmRmYmIiIHhtcE1NOkRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDo4MTBkYzgzMC00MGJjLTJhNDAtOGFkMS01YmEyZWIxNmQ0ODAiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozYTAyMjdjZS0xZWIyLTRlZmYtYmU2Ny0wZTE3MWUzODg4MzYiIHBob3Rvc2hvcDpDb2xvck1vZGU9IjMiIHRpZmY6T3JpZW50YXRpb249IjEiIHRpZmY6WFJlc29sdXRpb249IjcyMDAwMC8xMDAwMCIgdGlmZjpZUmVzb2x1dGlvbj0iNzIwMDAwLzEwMDAwIiB0aWZmOlJlc29sdXRpb25Vbml0PSIyIiBleGlmOkNvbG9yU3BhY2U9IjY1NTM1IiBleGlmOlBpeGVsWERpbWVuc2lvbj0iMTAwIiBleGlmOlBpeGVsWURpbWVuc2lvbj0iMTAwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY3JlYXRlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDozYTAyMjdjZS0xZWIyLTRlZmYtYmU2Ny0wZTE3MWUzODg4MzYiIHN0RXZ0OndoZW49IjIwMTctMTEtMjhUMTg6MDU6MDgrMDg6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE3IChNYWNpbnRvc2gpIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDo4YWVhNDNiZS1lZWVlLTQ3NzMtYWI1Yi00ZDAzZDFlMmU0ODQiIHN0RXZ0OndoZW49IjIwMTctMTItMjhUMTE6NDc6NTQrMDg6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDQyAoTWFjaW50b3NoKSIgc3RFdnQ6Y2hhbmdlZD0iLyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6YzNkOTJhMWEtNjIyMS00YTQxLWEyZTQtY2RlNWE0MzU4NmQ1IiBzdEV2dDp3aGVuPSIyMDE3LTEyLTI4VDE1OjA5OjQ2KzA4OjAwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgKE1hY2ludG9zaCkiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImNvbnZlcnRlZCIgc3RFdnQ6cGFyYW1ldGVycz0iZnJvbSBhcHBsaWNhdGlvbi92bmQuYWRvYmUucGhvdG9zaG9wIHRvIGltYWdlL3BuZyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iZGVyaXZlZCIgc3RFdnQ6cGFyYW1ldGVycz0iY29udmVydGVkIGZyb20gYXBwbGljYXRpb24vdm5kLmFkb2JlLnBob3Rvc2hvcCB0byBpbWFnZS9wbmciLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjc1ZTZjNDQ5LTc4MWEtNDg2YS1iZDZjLWRjZmQzMTJiZGZiYiIgc3RFdnQ6d2hlbj0iMjAxNy0xMi0yOFQxNTowOTo0NiswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpjM2Q5MmExYS02MjIxLTRhNDEtYTJlNC1jZGU1YTQzNTg2ZDUiIHN0UmVmOmRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDo0ZTA1OTU0NC0xNDExLTExN2ItOGE5Yy1lNTAyZTBjMjg5ZjQiIHN0UmVmOm9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozYTAyMjdjZS0xZWIyLTRlZmYtYmU2Ny0wZTE3MWUzODg4MzYiLz4gPC9yZGY6RGVzY3JpcHRp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";
                    Log.v("eatshop", "图片：" + imagefile);
                    String riqi = tv_dingcantime.getText().toString().substring(0, 10);

                    Log.v("eatshop", "菜名：" + et_foodtitle.getText().toString());
                    Log.v("eatshop", "价格：" + et_price.getText().toString());
                    Log.v("eatshop", "厨师：" + et_chushi.getText().toString());
                    Log.v("eatshop", "简介：" + et_foodinfo.getText().toString());
                    Log.v("eatshop", "数量：" + et_xiaochao_num.getText().toString());
                    Log.v("eatshop", "fenlei：" + "小炒");
                    Log.v("eatshop", "时间段：" + et_xiaochao_wuorwan.getText().toString());
                    Log.v("eatshop", "预约开始时间：" + tv_yuyuestar.getText().toString());
                    Log.v("eatshop", "预约截止时间：" + tv_yuyuestop.getText().toString());
                    Log.v("eatshop", "员工点餐日期：" + riqi);
                    Log.v("eatshop", "退单截止时间：" + tvTuitime.getText().toString());

                    RequestCommand.setFood(new SetFoodCallBack(upMenuDragment3.this),
                            et_foodtitle.getText().toString(),
                            et_price.getText().toString(),
                            imagefile,
                            et_chushi.getText().toString(),
                            et_foodinfo.getText().toString(),
                            et_xiaochao_num.getText().toString(),
                            "小炒",
                            et_xiaochao_wuorwan.getText().toString(),
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

    public class SetFoodCallBack extends DialogCallback<BaseBean, upMenuDragment3> {
        public SetFoodCallBack(upMenuDragment3 requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(BaseBean baseBean, Call<BaseBean> call) {
            super.onResponseSuccess(baseBean, call);

        }
    }

    public String submitInfo() {
        String touxiang = null;
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
        try {
            FileInputStream fis = new FileInputStream(outputFiles);
            Log.v("eatshop", "图片地址：" + fis);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            bitmap = ZoomBitmap.zoomImage(bitmap, bitmap.getWidth() / 8, bitmap.getHeight() / 8);
            touxiang = bitmaptoString(bitmap);
        } catch (Exception e) {

        }
        return touxiang;
    }

    public String bitmaptoString(Bitmap bitmap) {
//将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    private Bitmap getBitmap(String outputFiles) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(outputFiles);
            Log.v("eatshop", "图片地址：" + fis);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {

        }
        return bitmap;
    }

}

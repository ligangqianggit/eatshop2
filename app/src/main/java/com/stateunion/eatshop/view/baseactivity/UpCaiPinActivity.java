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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.UpMenuFragmentAdapter;
import com.stateunion.eatshop.util.TakePhone;
import com.stateunion.eatshop.view.basefrment.BaseFragmentActivity;
import com.stateunion.eatshop.view.mainfrment.UpMenuDragment;
import com.stateunion.eatshop.view.mainfrment.UpMenuDragment1;
import com.stateunion.eatshop.view.mainfrment.upMenuDragment2;
import com.stateunion.eatshop.view.mainfrment.upMenuDragment3;
import com.stateunion.eatshop.widget.MyViewPager;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by 青春 on 2017/12/21.
 */

public class UpCaiPinActivity extends FragmentActivity {

    private TabLayout projectTabLayout;
    private List<String> listString;//title
    private List<Fragment> listFragment;

    MyViewPager myViewPager;
    public final static String IMAGE_UTILS = "Android6.0权限";
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadcaidan);
        ButterKnife.bind(this);
        initFragment();
      }


    /**
     *初始化fragment
     */
    private void initFragment() {
        listString = new ArrayList<>();
        listFragment = new ArrayList<>();
        listString.add("早餐");
        listString.add("午餐");
        listString.add("晚餐");
        listString.add("加班");

        projectTabLayout = (TabLayout) findViewById(R.id.project_tablayout);

        myViewPager = (MyViewPager) findViewById(R.id.myviepage);


        UpMenuFragmentAdapter upMenuFragmentAdapter= new UpMenuFragmentAdapter(getSupportFragmentManager(), listString, listFragment);
        myViewPager.setAdapter(upMenuFragmentAdapter);
         projectTabLayout.setupWithViewPager(myViewPager);
//        one = projectTabLayout.getTabAt(0);
//        two = projectTabLayout.getTabAt(1);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("lailema","sssssssss"+requestCode+data.getAction());
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case TakePhone.REQ_SELECT_PHOTO:
                UpMenuDragment.takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
                break;
            case TakePhone.REQ_SELECT_PHOTO1:
                UpMenuDragment1.takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
                break;
            case TakePhone.REQ_SELECT_PHOTO2:
                UpMenuDragment.takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
                break;
            case TakePhone.REQ_SELECT_PHOTO3:
                UpMenuDragment.takePhoneUtils.attachToActivityForResult(requestCode, resultCode, data);
                break;
        }
        }


}

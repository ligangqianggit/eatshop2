package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.ShengRiListAdapter;
import com.stateunion.eatshop.adapter.TongJiPagerAdapter;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.BaseBean;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.TongJiBean;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 青春 on 2017/12/11.
 */

public class TongJiActivity extends BaseActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TongJiPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
//    private TabLayout.Tab four;
    private ImageView iv_tongji_back;
    public static ListView list_shengri;
    public static TextView tv_tongji_shengrinum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongji);
        initViews();
    }

    private void initViews() {
//        list_shengri= (ListView) findViewById(R.id.list_shengri);
//        tv_tongji_shengrinum= (TextView) findViewById(R.id.tv_tongji_shengrinum);

        iv_tongji_back= (ImageView) findViewById(R.id.iv_tongji_back);
        iv_tongji_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TongJiActivity.this.finish();
            }
        });
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new TongJiPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
//        four = mTabLayout.getTabAt(3);
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

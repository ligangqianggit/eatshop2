package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.UpCanPinAdapter;

/**
 * Created by 青春 on 2017/12/21.
 */

public class UpCaiPinActivity extends BaseActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private UpCanPinAdapter myadapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_uploadcaidan);
        initViews();

    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.up_viewPager);
        myadapter = new UpCanPinAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myadapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.up_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

    }

}

package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.TongJiPagerAdapter;

/**
 * Created by 青春 on 2018/2/27.
 */

public class PaiMingTwoActivity extends BaseActivity{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TongJiPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private ImageView iv_paimingtwo_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paimingtwo);

    }
    public void intview(){
        iv_paimingtwo_back= (ImageView) findViewById(R.id.iv_paimingtwo_back);
        iv_paimingtwo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaiMingTwoActivity.this.finish();
            }
        });
        mViewPager= (ViewPager) findViewById(R.id.vp_viewPager);
        myFragmentPagerAdapter=new TongJiPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout= (TabLayout) findViewById(R.id.tl_tabLayout);
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

package com.stateunion.eatshop.activity.loginactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.GuideChannelPagerAdapter;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;


/**
 * Created by lifei on 2017/12/5.
 */

public class GuideActivity extends BaseActivity {
    public static final String tag = "GuideActivity";
    private ViewPager pager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        pager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new GuideChannelPagerAdapter(this);
        pager.setAdapter(adapter);
    }
}

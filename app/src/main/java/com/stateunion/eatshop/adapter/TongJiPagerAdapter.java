package com.stateunion.eatshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stateunion.eatshop.view.mainfrment.BreakfastFragment;
import com.stateunion.eatshop.view.mainfrment.DinnerFragment;
import com.stateunion.eatshop.view.mainfrment.LunthFragment;
import com.stateunion.eatshop.view.mainfrment.OverTimeFragment;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class TongJiPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"早餐", "午餐", "晚餐","小炒"};

    public TongJiPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new LunthFragment();
        } else if (position == 2) {
            return new DinnerFragment();
        }else if (position==3){
            return new OverTimeFragment();
        }
        return new BreakfastFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

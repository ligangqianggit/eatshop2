package com.stateunion.eatshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stateunion.eatshop.view.mainfrment.BreakfastFragment;
import com.stateunion.eatshop.view.mainfrment.DinnerFragment;
import com.stateunion.eatshop.view.mainfrment.LunthFragment;
import com.stateunion.eatshop.view.mainfrment.OverTimeFragment;
import com.stateunion.eatshop.view.mainfrment.UpBreakfastFragment;
import com.stateunion.eatshop.view.mainfrment.UpDinnerFragment;
import com.stateunion.eatshop.view.mainfrment.UpLunthFragment;
import com.stateunion.eatshop.view.mainfrment.UpOverTimeFragment;

/**
 * Created by Carson_Ho on 16/7/22.
 * 此页面是厨师上传菜品切换早中晚的adapter
 */
public class UpCanPinAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"早餐", "午餐", "晚餐","加班"};

    public UpCanPinAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new UpLunthFragment();
        } else if (position == 2) {
            return new UpDinnerFragment();
        }else if (position==3){
            return new UpOverTimeFragment();
        }
        return new UpBreakfastFragment();
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

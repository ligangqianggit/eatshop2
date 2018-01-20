package com.stateunion.eatshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stateunion.eatshop.view.mainfrment.BreakfastFragment;
import com.stateunion.eatshop.view.mainfrment.DinnerFragment;
import com.stateunion.eatshop.view.mainfrment.LunthFragment;
import com.stateunion.eatshop.view.mainfrment.OverTimeFragment;
import com.stateunion.eatshop.view.mainfrment.UpMenuDragment;
import com.stateunion.eatshop.view.mainfrment.UpMenuDragment1;
import com.stateunion.eatshop.view.mainfrment.upMenuDragment2;
import com.stateunion.eatshop.view.mainfrment.upMenuDragment3;

import java.util.List;

/**
 * Created by VillageChief on 2018/1/4.
 */

public class UpMenuFragmentAdapter extends FragmentPagerAdapter {

        private List<String> tabNames;
        private List<Fragment> fragments;
    UpMenuDragment upMenuDragment;
    UpMenuDragment1 upMenuDragment1;
    upMenuDragment2 upMenuDragment2;
    upMenuDragment3 upMenuDragment3;
    public UpMenuFragmentAdapter(FragmentManager fm, List<String> tabNames, List<Fragment> fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new UpMenuDragment();
//                return upMenuDragment==null ?upMenuDragment=new UpMenuDragment():upMenuDragment;
            } else if (position == 1) {
//                return upMenuDragment1==null ?upMenuDragment1=new UpMenuDragment1():upMenuDragment1;
                return new UpMenuDragment1();
            }else if (position==2){
//                return upMenuDragment2==null ?upMenuDragment2=new upMenuDragment2():upMenuDragment2;
                return new upMenuDragment2();
            }
            return new upMenuDragment3();
            //            return   upMenuDragment3==null ?upMenuDragment3=new upMenuDragment3():upMenuDragment3;

     }

        @Override
        public int getCount() {
        return tabNames.size();
    }

        /**
         * 这个函数就是给TabLayout的Tab设定Title
         */
        @Override
        public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}

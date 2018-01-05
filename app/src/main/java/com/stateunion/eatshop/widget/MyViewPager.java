package com.stateunion.eatshop.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by admini on 2018/1/4.
 */

public class MyViewPager  extends ViewPager {

    private boolean scrollble = true;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return !scrollble && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (scrollble)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

}

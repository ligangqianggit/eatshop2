package com.stateunion.eatshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.activity.loginactivity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifei on 2017/12/5
 */

public class GuideChannelPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private List<View> arrayList;
    private Activity mContext;
    private ImageView guideRegist, guideHome;

    public GuideChannelPagerAdapter(Activity context) {
        this.mContext = context;
        initData(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // 给指定的viewpager添加一个view的方法
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // container就是viewpager对象
        container.addView(arrayList.get(position));
        return arrayList.get(position);
    }

    // 给指定的viewpager移除一个view的方法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    private void initData(Context context) {
        arrayList = new ArrayList<>();
        // 添加包含了图片，图片控件(ImageView)
        View view1 = LayoutInflater.from(context).inflate(R.layout.viewpage_guide_first, null);
        View view2 = LayoutInflater.from(context).inflate(R.layout.viewpage_guide_second, null);
        View view3 = LayoutInflater.from(context).inflate(R.layout.viewpage_guide_third, null);
        view3.findViewById(R.id.third_experience).setOnClickListener(this);
        arrayList.add(view1);
        arrayList.add(view2);
        arrayList.add(view3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.third_experience:
                setPageSkip();
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到登录
     */
    private void setPageSkip() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
        mContext.finish();
    }
}

package com.stateunion.eatshop.view.mainfrment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.baseactivity.DingCanActivity;
import com.stateunion.eatshop.view.baseactivity.PaiMIngActivity;
import com.stateunion.eatshop.view.baseactivity.PingJIaActivity;
import com.stateunion.eatshop.view.baseactivity.QuCanActivity;
import com.stateunion.eatshop.view.baseactivity.TongGaoActivity;
import com.stateunion.eatshop.view.baseactivity.TongJiActivity;
import com.stateunion.eatshop.view.baseactivity.TuiDanShenHe;
import com.stateunion.eatshop.view.baseactivity.UpCaiPinActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

/**
 * Created by admini on 2017/11/28.
 */

public class MainFragment extends BaseFragment {
  private Context context=null;
  private RollPagerView mRollViewPager;
  private ImageView iv_main_dingcan,iv_main_qucan,iv_main_tuidan,iv_main_shangchuan,iv_main_tongji,iv_main_pingjia,iv_main_paiming;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void createView(View rootView) {
      context=rootView.getContext();
      mRollViewPager = (RollPagerView) rootView.findViewById(R.id.roll_view_pager);
      //设置播放时间间隔
      mRollViewPager.setPlayDelay(3000);
      //设置透明度
      mRollViewPager.setAnimationDurtion(500);
      //设置适配器
      mRollViewPager.setAdapter(new TestNormalAdapter());
      //设置指示器（顺序依次）
      //自定义指示器图片
      //设置圆点指示器颜色
      //设置文字指示器
      //隐藏指示器
      //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
      mRollViewPager.setHintView(new ColorPointHintView(context, Color.RED,Color.WHITE));
      //mRollViewPager.setHintView(new TextHintView(this));
      //mRollViewPager.setHintView(null);
      mRollViewPager.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Log.v("eatshop","=======单击");
          Intent intent=new Intent(context, TongGaoActivity.class);
          startActivity(intent);
        }
      });
      //点餐统计
      iv_main_tongji= (ImageView) rootView.findViewById(R.id.iv_main_tongji);
      iv_main_tongji.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, TongJiActivity.class);
          startActivity(intent);
        }
      });
      //订餐
      iv_main_dingcan= (ImageView) rootView.findViewById(R.id.iv_main_dingcan);
      iv_main_dingcan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, DingCanActivity.class);
          startActivity(intent);
        }
      });
      //退单
      iv_main_tuidan= (ImageView) rootView.findViewById(R.id.iv_main_tuidan);
      iv_main_tuidan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, TuiDanShenHe.class);
          startActivity(intent);
        }
      });
      //评价
      iv_main_pingjia= (ImageView) rootView.findViewById(R.id.iv_main_pingjia);
      iv_main_pingjia.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, PingJIaActivity.class);
          startActivity(intent);
        }
      });

      iv_main_qucan= (ImageView) rootView.findViewById(R.id.iv_main_qucan);
      iv_main_qucan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, QuCanActivity.class);
          startActivity(intent);
        }
      });


      iv_main_shangchuan= (ImageView) rootView.findViewById(R.id.iv_main_shangchuan);
      iv_main_shangchuan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, UpCaiPinActivity.class);
          startActivity(intent);
        }
      });

      iv_main_paiming= (ImageView) rootView.findViewById(R.id.iv_main_paiming);
      iv_main_paiming.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, PaiMIngActivity.class);
          startActivity(intent);
        }
      });
    }
  private class TestNormalAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.bg_lunbo1,
            R.drawable.bg_lunbo2,
            R.drawable.bg_lunbo3,
    };

    @Override
    public View getView(ViewGroup container, int position) {
      ImageView view = new ImageView(container.getContext());
      view.setImageResource(imgs[position]);
      view.setScaleType(ImageView.ScaleType.CENTER_CROP);
      view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context,TongGaoActivity.class);
          startActivity(intent);
        }
      });
      return view;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//      return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//      return super.onOptionsItemSelected(item);
//    }
    @Override
    public int getCount() {
      return imgs.length;
    }
  }
    @Override
    public void refreshData(View rootView) {

    }
}

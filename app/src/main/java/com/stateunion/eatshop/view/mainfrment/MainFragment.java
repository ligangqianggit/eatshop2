package com.stateunion.eatshop.view.mainfrment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.stateunion.eatshop.view.baseactivity.DingCanActivity;
import com.stateunion.eatshop.view.baseactivity.PingJIaActivity;
import com.stateunion.eatshop.view.baseactivity.QuCanActivity;
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
  private Button bt_main_tongji,bt_main_dingcan,bt_main_tuidan,bt_main_pingjia,bt_main_qucan,bt_main_shangchuan;
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

      //点餐统计
      bt_main_tongji= (Button) rootView.findViewById(R.id.bt_main_tongji);
      bt_main_tongji.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, TongJiActivity.class);
          startActivity(intent);
        }
      });

      bt_main_dingcan= (Button) rootView.findViewById(R.id.bt_main_dingcan);
      bt_main_dingcan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, DingCanActivity.class);
          startActivity(intent);
        }
      });

      bt_main_tuidan= (Button) rootView.findViewById(R.id.bt_main_tuidan);
      bt_main_tuidan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, TuiDanShenHe.class);
          startActivity(intent);
        }
      });

      bt_main_pingjia= (Button) rootView.findViewById(R.id.bt_main_pingjia);
      bt_main_pingjia.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, PingJIaActivity.class);
          startActivity(intent);
        }
      });

      bt_main_qucan= (Button) rootView.findViewById(R.id.bt_main_qucan);
      bt_main_qucan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, QuCanActivity.class);
          startActivity(intent);
        }
      });


      bt_main_shangchuan= (Button) rootView.findViewById(R.id.bt_main_shangchuan);
      bt_main_shangchuan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, UpCaiPinActivity.class);
          startActivity(intent);
        }
      });

    }
  private class TestNormalAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.lunbo2,
            R.drawable.lunbo2,
            R.drawable.lunbo2,
    };


    @Override
    public View getView(ViewGroup container, int position) {
      ImageView view = new ImageView(container.getContext());
      view.setImageResource(imgs[position]);
      view.setScaleType(ImageView.ScaleType.CENTER_CROP);
      view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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

package com.stateunion.eatshop.view.mainfrment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.DIngDanHaoBean;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.baseactivity.DingCanActivity;
import com.stateunion.eatshop.view.baseactivity.QuCanActivity;
import com.stateunion.eatshop.view.baseactivity.TongGaoActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

import retrofit2.Call;

/**
 * Created by zhang on 2017/12/28.
 */

public class StaffHomeFragment extends BaseFragment implements IBaseDialogView {
    private Context context=null;
    private RollPagerView mRollViewPager;
    private ImageView iv_yuangong_dingcan,iv_yuangong_qucan;
    private boolean isAlive = false;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mainyuangong;
    }

    @Override
    public void createView(View rootView) {
        isAlive = true;
        context=rootView.getContext();
        mRollViewPager = (RollPagerView) rootView.findViewById(R.id.roll_yuangong_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new StaffHomeFragment.TestNormalAdapter());
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(context, Color.RED,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

        iv_yuangong_dingcan= (ImageView) rootView.findViewById(R.id.iv_yuangong_dingcan);
        iv_yuangong_dingcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DingCanActivity.class);
                startActivity(intent);
            }
        });
        iv_yuangong_qucan= (ImageView) rootView.findViewById(R.id.iv_yuangong_qucan);
        iv_yuangong_qucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestCommand.getDingdanhao(new DingDanHaoCallBack(StaffHomeFragment.this), AppSessionEngine.getLoginInfo().getGonghao());
            }
        });
        isAlive = true;

    }

    @Override
    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(getActivity(), themeResId);
        return dialog;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public boolean isAlive() {
        return isAlive;
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

    public  class DingDanHaoCallBack extends DialogCallback<DIngDanHaoBean,StaffHomeFragment> {

        public DingDanHaoCallBack(StaffHomeFragment requestView) {
            super(requestView);
        }
        @Override
        protected void onResponseSuccess(DIngDanHaoBean dIngDanHaoBean, Call<DIngDanHaoBean> call) {
            super.onResponseSuccess(dIngDanHaoBean, call);
            if(dIngDanHaoBean.getSuccess()==1){
                Intent intent=new Intent(context, QuCanActivity.class);
                intent.putExtra("dingdanhao",dIngDanHaoBean.getBody().getOrder_sn().toString());
                startActivity(intent);
            }
        }
    }

}

package com.stateunion.eatshop.view.baseactivity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stateunion.eatshop.APPKey;
import com.stateunion.eatshop.DataStore;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.TongJiPagerAdapter;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.basefrment.BaseFragment;
import com.stateunion.eatshop.view.mainfrment.MainFragment;

/**
 * Created by 青春 on 2018/2/27.
 */

public class PaiMingTwoActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
     private ImageView iv_paimingtwo_back;

    RadioButton radio0,radio2,radio3;
    private RadioButton [] arrRadios=null;

    DayPaiFragment dayPaiFragment=null;
    WeekPaiFragment weekPaiFragment=null;
    MonthPaiFragment monthPaiFragment=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paimingtwo);
        intview();
        setRadios();
    }
    public void intview(){
        iv_paimingtwo_back= (ImageView) findViewById(R.id.iv_paimingtwo_back);
        iv_paimingtwo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaiMingTwoActivity.this.finish();
            }
        });
        radio0= (RadioButton) findViewById(R.id.acty_paiming_radio0);
        radio2= (RadioButton) findViewById(R.id.acty_paiming_radio2);
        radio3= (RadioButton) findViewById(R.id.acty_paiming_radio3);
        arrRadios=new RadioButton[]{radio0,radio2,radio3};
        ((RadioGroup)findViewById(R.id.acty_paiming_radioGroups)).setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.acty_paiming_radio0:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,new DayPaiFragment()).commit();
                Toast.makeText(PaiMingTwoActivity.this,"点击0",Toast.LENGTH_SHORT).show();
                break;
            case R.id.acty_paiming_radio2:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getWeekFragment()).commit();
                Toast.makeText(PaiMingTwoActivity.this,"点击1",Toast.LENGTH_SHORT).show();

                break;
            case R.id.acty_paiming_radio3:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getMonthFragment()).commit();
                Toast.makeText(PaiMingTwoActivity.this,"点击2",Toast.LENGTH_SHORT).show();

                break;
        }
    }



    private DayPaiFragment getDayFragment(){
        return dayPaiFragment==null ?dayPaiFragment=new DayPaiFragment():dayPaiFragment;
    }
    private WeekPaiFragment getWeekFragment(){
        return weekPaiFragment==null ?weekPaiFragment=new WeekPaiFragment():weekPaiFragment;
    }
    private MonthPaiFragment getMonthFragment(){
        return monthPaiFragment==null ?monthPaiFragment=new MonthPaiFragment():monthPaiFragment;
    }

    /**
     *  根据初始值 获取对应fragment
     */
    private BaseFragment getFragment(int fr_id){
        switch (fr_id){
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                    return getDayFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                return getWeekFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                return getMonthFragment();
            default:
                return null;
        }
    }


    /**
     * 设置 自定义导航后的 radio button 样式
     *
     * @param radio      radio button
     * @param layoutType 对应静态数据的样式
     */
    private void setSelfNaviRadio(RadioButton radio, int layoutType) {
        Log.d("aaa",layoutType+"zzzz");

        switch (layoutType) {
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                Log.v("eatshop","日");
                radio.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_1 ))).commit();
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                Log.v("eatshop","周");
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                Log.v("eatshop","月");
                break;

        }
    }
    private void setRadios() {
        String[] arrMainRadioType = getResources().getStringArray(R.array.main_radio);
        Log.v("eatshop","11111");
        for (int i = 0; i < 3; i++) {
            setSelfNaviRadio(arrRadios[i], DataStore.getInt(arrMainRadioType[i]));
        }
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

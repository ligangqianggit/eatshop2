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

import com.stateunion.eatshop.APPKey;
import com.stateunion.eatshop.DataStore;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.adapter.TongJiPagerAdapter;
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
        initview();

    }
    public void intview(){
        iv_paimingtwo_back= (ImageView) findViewById(R.id.iv_paimingtwo_back);
        iv_paimingtwo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaiMingTwoActivity.this.finish();
            }
        });

    }
    private void initview(){
        radio0= (RadioButton) findViewById(R.id.acty_paiming_radio0);
//     radio1= (RadioButton) findViewById(R.id.acty_main_radio1);
        radio2= (RadioButton) findViewById(R.id.acty_paiming_radio2);
        radio3= (RadioButton) findViewById(R.id.acty_paiming_radio3);
        arrRadios=new RadioButton[]{radio0,radio2,radio3};
        ((RadioGroup)findViewById(R.id.acty_paiming_radioGroups)).setOnCheckedChangeListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.acty_main_radio0:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getDayFragment()).commit();
                break;
            case R.id.acty_main_radio2:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getWeekFragment()).commit();
                break;
            case R.id.acty_main_radio3:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_paiming_frame,getMonthFragment()).commit();
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
}

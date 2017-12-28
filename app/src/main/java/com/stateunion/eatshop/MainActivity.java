package com.stateunion.eatshop;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.util.AppSessionEngine;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;
import com.stateunion.eatshop.view.basefrment.BaseFragmentAct;
import com.stateunion.eatshop.view.mainfrment.CouPonFragment;
import com.stateunion.eatshop.view.mainfrment.MainFragment;
import com.stateunion.eatshop.view.mainfrment.PersFragment;
import com.stateunion.eatshop.view.mainfrment.StaffHomeFragment;
import com.stateunion.eatshop.view.mainfrment.TakeFragment;

import retrofit2.Call;

public class MainActivity extends BaseFragmentAct implements RadioGroup.OnCheckedChangeListener{
    private long clickTime = 0;
    /**
     *  首页 fragment
     */
    private MainFragment mainFragment=null;
     /**
     * 取餐 fragment
      */
//    private TakeFragment takeFragment=null;
    /**
     * 福利 fragment
     */
    private CouPonFragment couPonFragment=null;
    /**
     * 个人 fragment
     */
    private PersFragment persFragment=null;
    //会员
    private StaffHomeFragment staffHomeFragment;
    private RadioButton [] arrRadios=null;
    /**
     * 首页下面引导button
      */
    RadioButton radio0,radio2,radio3;
    String userType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);
        userType= AppSessionEngine.getUserInfo().getZhiwei();
        initview();
        setRadios();

    }
 private void initview(){
     radio0= (RadioButton) findViewById(R.id.acty_main_radio0);
//     radio1= (RadioButton) findViewById(R.id.acty_main_radio1);
     radio2= (RadioButton) findViewById(R.id.acty_main_radio2);
     radio3= (RadioButton) findViewById(R.id.acty_main_radio3);
     arrRadios=new RadioButton[]{radio0,radio2,radio3};
     ((RadioGroup)findViewById(R.id.acty_main_radioGroups)).setOnCheckedChangeListener(this);
 }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.acty_main_radio0:
                     getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame, getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_1))).commit();
                     break;
//            case R.id.acty_main_radio1:
//                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_2 ))).commit();
//
//                break;
            case R.id.acty_main_radio2:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_3 ))).commit();
                break;
            case R.id.acty_main_radio3:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_4))).commit();
                break;
        }
    }

    /**
     *  根据初始值 获取对应fragment
      */
    private BaseFragment getFragment(int fr_id){
        switch (fr_id){
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                if(userType.equals("厨师长")){
                    return getMainFragment();
                 }else {
                    return getStaffFragment();
                }
             case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                return getCouPonFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                return getPersFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_STAFF:
                return getStaffFragment();
            default:
                return null;
        }
     }

    /**
     *  获取 首页 取餐 福利 个人
     */
    private MainFragment getMainFragment(){
        return mainFragment==null ?mainFragment=new MainFragment():mainFragment;
    }
//    private TakeFragment getTakeFragment(){
//        return takeFragment==null?takeFragment=new TakeFragment():takeFragment;
//    }
    private CouPonFragment getCouPonFragment(){
        return couPonFragment==null?couPonFragment=new CouPonFragment():couPonFragment;
    }
    private PersFragment getPersFragment(){
        return persFragment==null?persFragment=new PersFragment():persFragment;
    }
    private StaffHomeFragment getStaffFragment(){
        return staffHomeFragment==null?staffHomeFragment=new StaffHomeFragment():staffHomeFragment;
    }
    /**
     * 设置 自定义导航后的 radio button 样式
     *
     * @param radio      radio button
     * @param layoutType 对应静态数据的样式
     */
    private void setSelfNaviRadio(RadioButton radio, int layoutType) {
        switch (layoutType) {
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                Log.v("eatshop","首页");
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_main), null, null);
                radio.setText(R.string.main_radio_main);
                radio.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_1 ))).commit();
                break;
//            case APPKey.SP_MAIN_RADIO_LAYOUT_TAKE:
//                Log.v("eatshop","取餐");
//                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_game), null, null);
//                radio.setText(R.string.main_radio_take);
//                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                Log.v("eatshop","福利");
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_shop), null, null);
                radio.setText(R.string.main_radio_coup);
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                Log.v("eatshop","个人中心");
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_social), null, null);
                radio.setText(R.string.main_radio_per);
                break;

        }
    }
    /**
     * 根据 drawable ID ，获取radio button 的 drawable 样式
     *
     * @param drawableID id
     * @return drawable
     */
    private Drawable getRadioDrawable(int drawableID) {
        Drawable drawable = getResources().getDrawable(drawableID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }
    /**
     * 设置 radio buttons 的样式
     */
    private void setRadios() {
        String[] arrMainRadioType = getResources().getStringArray(R.array.main_radio);
        Log.v("eatshop","11111");
        for (int i = 0; i < 3; i++) {
             setSelfNaviRadio(arrRadios[i], DataStore.getInt(arrMainRadioType[i]));
        }
    }

    @Override
    protected void onResume() {
        /**
         * 设置自定义导航
         */
        if (DataStore.getBoolean(APPKey.SP_MAIN_SELF_NAVI)) {
            setRadios();
            DataStore.put(APPKey.SP_MAIN_SELF_NAVI, false);
        }
        super.onResume();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            Log.v("eatshop", "exit application");
           this.finish();
        }
    }
}

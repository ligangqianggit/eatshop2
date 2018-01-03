package com.stateunion.eatshop.activity.loginactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.stateunion.eatshop.MainActivity;
import com.stateunion.eatshop.commons.engine.AppSessionEngine;
import com.stateunion.eatshop.util.ActivitySet;
import com.stateunion.eatshop.util.AndroidUtil;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

/**
 * Created by admini on 2018/1/2.
 */

public class LogoActivity  extends BaseActivity {
    Runnable stopoversRunnable;
    Handler handler;
    long stopoversStartTime;
    /**
     * 因为有可能是多个任务并发，所以用任务计数器来标识这个Activity里面的任务是否全部完成
     */
    private int task = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initStopovers();
        ActivitySet.getInstance().finishOther(getClass());
    }



    /**
     * 为了让用户在logo页面至少停留2秒
     */
    private void initStopovers() {
        startTask();
        handler = new Handler();
        stopoversRunnable = new Runnable() {
            @Override
            public void run() {
                finishTask();
            }
        };
        stopoversStartTime = System.currentTimeMillis();
    }


    protected void onStop() {
        super.onStop();
        cancelStopovers();
    }

    private void cancelStopovers() {
        handler.removeCallbacks(stopoversRunnable);
    }

    protected void onStart() {
        super.onStart();
        startStopovers();
    }

    private void startStopovers() {
        handler.postDelayed(stopoversRunnable, stopoversStartTime + 2000 - System.currentTimeMillis());
    }

    /**
     * 跳转到首页
     */
    public void startNextActivity() {

        if (isGoGuideView()) {
            // 第一次进入，进入导航页面,对指定的is_first变量修改成false
            // 跳转到放置了ViewPager的activity中
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
        } else {
            // 第一次之外进入应用，直接进入应用程序,
            /*Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);*/
            if (AppSessionEngine.isSignIn()){
                startActivity(new Intent(this, MainActivity.class));
            }else{
                startActivity(new Intent(this, LoginActivity.class));
            }

        }
        finish();
    }

    /**
     * 显示引导页条件为第一次安装和更新
     * 其他情况不显示引导页
     *
     * @return
     */
    boolean isGoGuideView() {
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String version = sharedPreferences.getString("version", "");
        if (("").equals(version)) {
            sharedPreferences.edit().putString("version", AndroidUtil.getVersionName()).apply();
            return true;
        } else {
            if (!AndroidUtil.getVersionName().equals(version)) {
                sharedPreferences.edit().putString("version", AndroidUtil.getVersionName()).apply();
                return true;
            }
        }
        return false;
//        if (GesturePasswordUtils.isCanUseSessionSignIn(this)) return true;
//        return true;
    }

    public void startTask() {
         task++;
    }

    public void finishTask() {
         task--;
        if (task <= 0) {
            startNextActivity();
        }
    }

}

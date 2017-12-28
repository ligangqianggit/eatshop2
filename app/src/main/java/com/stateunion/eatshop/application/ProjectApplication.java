package com.stateunion.eatshop.application;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.growingio.android.sdk.collection.Configuration;
import com.growingio.android.sdk.collection.GrowingIO;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.stateunion.eatshop.APPKey;
import com.stateunion.eatshop.DataStore;
import com.stateunion.eatshop.commons.Constants;
import com.stateunion.eatshop.retrofit.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admini on 2017/11/6.
 */

public class ProjectApplication extends MultiDexApplication {
    public static ProjectApplication sApplication;

    private static List<Activity> con_list = new ArrayList<Activity>();
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metric = getApplicationContext().getResources()
                .getDisplayMetrics();
        Constants.SCREEN_WIDTH_PX = metric.widthPixels;
        Constants.SCREEN_HEIGHT_PX = metric.heightPixels;
        // 获取系统版本
        Constants.SYSTEM_VERSION = android.os.Build.VERSION.SDK_INT;
        initBugly();
//        initGroWingIO();
//        initTencentX5();
        DataStore.init(getApplicationContext());
        initMainNavigationRadioGroup();

        sApplication = (ProjectApplication) getApplicationContext();

        //imageload初始化
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(
                defaultOptions).build();
        ImageLoader.getInstance().init(config);


    }

    /**
     * GrowingIo注入
     */
    private void initGroWingIO() {
        try {
            ApplicationInfo appInfo = getApplicationContext().getPackageManager().
                    getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            GrowingIO.startWithConfiguration(this, new Configuration()
                    .useID()
                    .trackAllFragments()
                    .setChannel(appInfo.metaData.getString("FRIEND_ID")));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * X5浏览器初始化
     */
//    private void initTencentX5() {
//        QbSdk.initX5Environment(this, null);
//    }

    /**
     * 热修复
     */
    private void initBugly() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(BuildConfig.FLAVOR);
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APP_ID, false, strategy);
    }
    /**
     * 初始化 首页 radio button
     */
    private void initMainNavigationRadioGroup() {
        if (DataStore.getInt(APPKey.SP_MAIN_RADIO_1) == -1) {
            DataStore.put(APPKey.SP_MAIN_RADIO_1, APPKey.SP_MAIN_RADIO_LAYOUT_MAIN);
            DataStore.put(APPKey.SP_MAIN_RADIO_2, APPKey.SP_MAIN_RADIO_LAYOUT_TAKE);
            DataStore.put(APPKey.SP_MAIN_RADIO_3, APPKey.SP_MAIN_RADIO_LAYOUT_COUP);
            DataStore.put(APPKey.SP_MAIN_RADIO_4, APPKey.SP_MAIN_RADIO_LAYOUT_PERS);
            DataStore.put(APPKey.SP_MAIN_RADIO_5, APPKey.SP_MAIN_RADIO_LAYOUT_STAFF);

        }
    }
}

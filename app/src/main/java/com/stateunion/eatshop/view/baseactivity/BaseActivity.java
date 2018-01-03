package com.stateunion.eatshop.view.baseactivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.stateunion.eatshop.application.ProjectApplication;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.ActivityTaskManager;
import com.stateunion.eatshop.util.ErrorDialogUtil;
import com.stateunion.eatshop.util.SysDiaLog;
import com.stateunion.eatshop.widget.SliderFrame;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admini on 2017/11/6.
 */

public class BaseActivity extends AppCompatActivity implements IBaseDialogView {
    protected FragmentManager mFragmentManager;
    protected FragmentTransaction mFragmentTransaction;
    private boolean isAlive = false;
    private boolean isCancel = false;

    // <-------------生命周期开始------------->
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        /**
         * 实现对状态栏的控制
         */
//        StatusBarController.wrapperStatusBar(getBaseActivity());
//        StatusBarCompat.setStatusBarColor(this, 0x99ffffff, 200);
        isAlive = true;
        ActivityTaskManager.getInstance().add(this);
        mFragmentManager = getSupportFragmentManager();

        ProjectApplication.sApplication.addActivity(this);
    }

    @Override
    protected void onStart() {
        logLifeCycle("onStart");
        super.onStart();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        logLifeCycle("onPostCreate");
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        logLifeCycle("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        logLifeCycle("onPause");
        super.onPause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        logLifeCycle("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onStop() {
        isCancel = true;
        logLifeCycle("onStop");
        super.onStop();
    }

    /**
     * 清理activity 和dialog
     */
    @Override
    protected void onDestroy() {
        isAlive = true;
        ActivityTaskManager.getInstance().remove(this);
        for (WeakReference<Dialog> weakReference : dialogManager) {
            if (weakReference.get() != null) {
                weakReference.get().dismiss();
            }
        }
        dialogManager.clear();
        dialogManager = null;
        ProjectApplication.sApplication.removeActivity(this);
        super.onDestroy();

    }
// <-------------生命周期结束------------->

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        logLifeCycle("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    List<WeakReference<Dialog>> dialogManager = new ArrayList<>();

    public Dialog createDialog(@StyleRes int themeResId) {
        Dialog dialog = new Dialog(this, themeResId);
        dialogManager.add(new WeakReference<>(dialog));
        return dialog;
    }

    /**
     * @param message 重写 IBaseDialogView方法
     */
    @Override
    public void showError(String message) {
        showError(message, new SysDiaLog.EmptyETongDaiDialogListener());

    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }

    public void showError(String errStr, SysDiaLog.EtdSingleListener listeners) {
        //做activity级的提示框
        ErrorDialogUtil.showErrorDialog(this, errStr, listeners);
    }



    @Override
    public boolean isAlive() {
        return isAlive;
    }



    /**
     * @param method 保存生命周期状态
     */
    private void logLifeCycle(String method) {
        StringBuilder sb = new StringBuilder(method);
        for (; sb.length() < 40; sb.append("-")) ;
        Log.e("life-cycle-activity", sb.toString() + toString());
    }



    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 验证密码
     *
     * @param pass 密码
     * @return 结果
     */
    public static boolean isPassword(String pass) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$).{6,20}$"); // 验证密码
            Matcher m = p.matcher(pass);
            return m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param phone 要验证的手机号
     * @return 验证结果
     */
    public static boolean isPhoneNumber(String phone) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9])|(17[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}

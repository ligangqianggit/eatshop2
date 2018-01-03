package com.stateunion.eatshop.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.stateunion.eatshop.application.ProjectApplication;

import java.io.File;

/**
 * Created by master on 2017/1/13.
 */

public class AndroidUtil {
    /**
     * 获取版本号
     *
     * @return
     */
    public static String getVersionName() {
        String result;
        Context context = ProjectApplication.sApplication;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            String version = info.versionName;
            result = version;
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    // 安装文件，一般固定写法
    public static void installApk(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        ProjectApplication.sApplication.startActivity(intent);
    }
}

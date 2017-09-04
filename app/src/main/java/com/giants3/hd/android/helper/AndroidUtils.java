package com.giants3.hd.android.helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;

/**
 * Created by davidleen29 on 2017/3/5.
 */

public class AndroidUtils {


    public static Application application;
    public static PackageInfo packageInfo;

    public static void init(Application newApplication) {
        application = newApplication;
        try {
            packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏关闭输入法
     *
     * @param v
     */
    public static void hideKeyboard(View v) {


        try {
            InputMethodManager inputMethodManager = (InputMethodManager) v.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 判断当前act是否在顶
     *
     * @return
     */
    public static boolean isActivityTop(Activity activity) {

        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);

        // get the info from the currently running flowActivity

        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

//        Log.d("topActivity", "CURRENT Activity ::"
//                + taskInfo.get(0).topActivity.getClassName());
        return taskInfo.get(0).topActivity.getClassName().equals(activity.getClass().getName());

    }


    public static File getCacheDir() {


        File cacheDir = application.getExternalCacheDir();
        if (cacheDir == null || !cacheDir.exists())

            cacheDir = application.getCacheDir();
        return cacheDir;


    }

    public static String getVersionName() {


        return packageInfo.versionName;

    }

    public static int getVersionCode() {


        return packageInfo.versionCode;

    }
}

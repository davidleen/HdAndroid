package com.giants3.hd.android.helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * Created by davidleen29 on 2017/3/5.
 */

public class AndroidUtils {
    /**
     * 隐藏关闭输入法
     * @param v
     */
    public static void hideKeyboard(View v ) {



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
     * @return
     */
    public static boolean isActivityTop(Activity activity)
    {

        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);

        // get the info from the currently running flowActivity

        List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);

//        Log.d("topActivity", "CURRENT Activity ::"
//                + taskInfo.get(0).topActivity.getClassName());
        return taskInfo.get(0).topActivity.getClassName().equals(activity.getClass().getName());

    }
}

package com.giants3.hd.android;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by david on 2016/3/5.
 */
public class Utils {

    private static Context mContext;


    public static int dp2px(float dp) {
        final float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static void init(Context context) {
        mContext=context;
    }



    /**
     * @return get device screen width and height 获取屏幕宽高
     */
    public static int[] getScreenWH() {
        int[] wh = new int[2];

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        wh[0] = metrics.widthPixels;
        wh[1] = metrics.heightPixels;
        return wh;
    }






}

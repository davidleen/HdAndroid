package com.giants3.hd.android;

import android.content.Context;

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
}

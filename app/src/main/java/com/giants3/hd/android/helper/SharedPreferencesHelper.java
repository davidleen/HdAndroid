package com.giants3.hd.android.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.utils.*;
import com.giants3.hd.data.utils.GsonUtils;
import com.google.zxing.common.StringUtils;

/**
 * Created by david on 2016/1/2.
 */
public class SharedPreferencesHelper {

    public static Context mContext;


    public static final String SHARED_PREFERENCE_APP="SHARED_PREFERENCE_APP";

    public static final String KEY_LOGIN_USER="LOGIN_USER";

    public static void  init(Context context)
    {
        mContext=context;

        aUser=getLoginUserFromCache();

    }


    private static final  AUser getLoginUserFromCache()
    {

        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREFERENCE_APP,Context.MODE_PRIVATE);


        String value=sharedPreferences.getString(KEY_LOGIN_USER, "");

        AUser  user=null;
        try {
               user=   GsonUtils.fromJson(value,AUser.class);
        } catch (Throwable e) {

        }

        return user;


    }

   private static  AUser aUser;

    public static void  saveLoginUser(AUser auser)
    {

        aUser=auser;
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREFERENCE_APP,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        String value=GsonUtils.toJson(auser);

        editor.putString(KEY_LOGIN_USER,value);


        editor.commit();



    }


    public static   AUser getLoginUser()
    {

        return aUser;
    }
}

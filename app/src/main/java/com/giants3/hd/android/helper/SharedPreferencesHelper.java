package com.giants3.hd.android.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.giants3.android.frame.util.StorageUtils;
import com.giants3.hd.android.entity.LoginHistory;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.utils.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 2016/1/2.
 */
public class SharedPreferencesHelper {

    public static Context mContext;


    public static final String SHARED_PREFERENCE_APP="SHARED_PREFERENCE_APP";

    public static final String KEY_LOGIN_USER="LOGIN_USER";
    public static final String KEY_LAST_LOGIN_TOKEN="LAST_LOGIN_TOKEN";
    public static final String KEY_INIT_DATA="INIT_DATA";
    public static final String KEY_LOGIN_HISTORY="LOGIN_HISTORY";
    public static void  init(Context context)
    {
        mContext=context;

        aUser=getLoginUserFromCache();

        AuthorityUtil.getInstance().setLoginUser(aUser);
        aBufferData=getInitDataFromCache();


    }


    private static final  AUser getLoginUserFromCache()
    {
      String value=  StorageUtils.readStringFromFile( KEY_LOGIN_USER);
      if(StringUtils.isEmpty(value)) {
          SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCE_APP, Context.MODE_PRIVATE);


            value = sharedPreferences.getString(KEY_LOGIN_USER, "");
            if(!StringUtils.isEmpty(value))
            {
                StorageUtils.writeString(value,KEY_LOGIN_USER);
            }
      }

        AUser  user=null;
        try {
               user=   GsonUtils.fromJson(value,AUser.class);
        } catch (Throwable e) {

        }

        return user;


    }

   private static  AUser aUser;
    private static  BufferData aBufferData;
    public static void  saveLoginUser(AUser auser)
    {

        aUser=auser;

        String value=GsonUtils.toJson(auser);
        StorageUtils.writeString(value,KEY_LOGIN_USER);


        AuthorityUtil.getInstance().setLoginUser(auser);
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREFERENCE_APP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(KEY_LOGIN_USER,value);




        editor.commit();

    }


    public static   AUser getLoginUser()
    {

        if(aUser==null)
        {
            aUser=getLoginUserFromCache();
        }
        return aUser;
    }


    public static void saveInitData(BufferData bufferData)
    {

        aBufferData=bufferData;
        String value=GsonUtils.toJson(bufferData);
        StorageUtils.writeString(value,KEY_INIT_DATA);



        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREFERENCE_APP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_INIT_DATA,value);
        editor.commit();
    }
    private static final  BufferData getInitDataFromCache()
    {

        String value=StorageUtils.readStringFromFile(KEY_INIT_DATA);

        if(StringUtils.isEmpty(value))
        {
            SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREFERENCE_APP,Context.MODE_PRIVATE);
              value=sharedPreferences.getString(KEY_INIT_DATA, "");
            if(!StringUtils.isEmpty(value))
            {
                StorageUtils.writeString(value,KEY_INIT_DATA);
            }

        }

       ;




        BufferData  bufferData=null;
        try {
            bufferData=   GsonUtils.fromJson(value,BufferData.class);
        } catch (Throwable e) {

        }

        return bufferData;


    }
    public static   BufferData getInitData()
    {

        if(aBufferData==null)
        {
            aBufferData=getInitDataFromCache();
        }
        return aBufferData;
    }


    private static List<LoginHistory> histories;


    public static List<LoginHistory> getLoginHistory()
    {

        if(histories==null)
        {
            histories=getLoginHistoryFromCache();
        }

        return histories;

    }

    private static List<LoginHistory> getLoginHistoryFromCache() {



        String value=StorageUtils.readStringFromFile(KEY_LOGIN_HISTORY);
        List<LoginHistory> result=null;
        try {
             result=GsonUtils.fromJson(value, new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{LoginHistory.class};
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }

                @Override
                public Type getRawType() {
                    return ArrayList.class;
                }
            });
        } catch (HdException e) {
            e.printStackTrace();
        }


        return result;


    }
    public static void addLoginHistory(LoginHistory aHistory)
    {

        if(histories==null)
        {
            histories=new ArrayList<>();
        }
        List<LoginHistory> removedList=new ArrayList<>();
        for(LoginHistory temp:histories)
        {
            if(temp.name.equals(aHistory.name))
            {
                removedList.add(temp);
            }
        }
        histories.removeAll(removedList);
        histories.add(aHistory);
        String value=GsonUtils.toJson(histories);
        StorageUtils.writeString(value,KEY_LOGIN_HISTORY);

    }



    public static void saveToken(String token)
    {

        StorageUtils.writeString(token,KEY_LAST_LOGIN_TOKEN);

    }

    public static String readToken()
    {

      return  StorageUtils.readStringFromFile(KEY_LAST_LOGIN_TOKEN);

    }

}

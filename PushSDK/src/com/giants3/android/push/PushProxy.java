package com.giants3.android.push;

import android.app.Activity;
import android.util.Log;

import com.giants3.android.api.push.RegisterCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import android.content.Context;
/**
 * Created by davidleen29 on 2018/3/31.
 */


public class PushProxy {
    private static PushAgent mPushAgent =null;
    public static  void config(Context  context,final RegisterCallback callback){

        if(mPushAgent==null)
          mPushAgent=   PushAgent.getInstance(context.getApplicationContext());
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token



                callback.onSuccess(deviceToken);


            }

            @Override
            public void onFailure(String s, String s1) {
                 callback.onFail(s,s1);
            }
        });




    }


    public static void onAppStart( )
    {
        mPushAgent .onAppStart();
    }
}

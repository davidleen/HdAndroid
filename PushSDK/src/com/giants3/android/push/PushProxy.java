package com.giants3.android.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.giants3.android.api.push.MessageCallback;
import com.giants3.android.api.push.RegisterCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;


/**
 * Created by davidleen29 on 2018/3/31.
 */


public class PushProxy {
    private static final String TAG = "PushProxy";
    private static PushAgent mPushAgent = null;
    private static Handler handler;

    public static void config(Context context, final RegisterCallback callback, final MessageCallback messageCallback) {

        if (mPushAgent == null) {
            mPushAgent = PushAgent.getInstance(context.getApplicationContext());
            handler = new Handler(Looper.getMainLooper());

        }
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token


                callback.onSuccess(deviceToken);


            }

            @Override
            public void onFailure(String s, String s1) {
                callback.onFail(s, s1);

            }
        });


        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {


                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 通知的回调方法
             * @param context
             * @param msg
             */
            @Override
            public void dealWithNotificationMessage(Context context, final UMessage msg) {

                Log.e(TAG, msg.toString());


                Log.e(TAG, msg.custom);


                //调用super则会走通知展示流程，不调用super则不展示通知
                super.dealWithNotificationMessage(context, msg);
            }

            @Override
            public void dealWithCustomMessage(Context context, final UMessage uMessage) {


                Log.e(TAG, uMessage.toString());


                Log.e(TAG, uMessage.custom);
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        messageCallback.onMessageReceived(uMessage.custom);

                    }
                });
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

    }


    public static void onAppStart() {

        mPushAgent.onAppStart();


    }
}

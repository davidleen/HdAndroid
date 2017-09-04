package com.giants3.hd.android;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.giants3.hd.android.helper.AndroidUtils;
import com.giants3.hd.android.helper.BitmapToolkit;
import com.giants3.hd.android.helper.ConnectionHelper;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

;

/**
 * Created by david on 2016/1/2.
 */
public class HdApplication extends Application {
    public static final String TAG="Application";
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */

    public static Context baseContext;

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = this;
        ToastHelper.init(this);
        BitmapToolkit.init(this);
        SharedPreferencesHelper.init(this);
        ConnectionHelper.init(this);
        HttpUrl.init(this);
        Utils.init(this);
        AndroidUtils.init(this);

        boolean autoUpdates = BuildConfig.AUTO_UPDATES;
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token

                SystemConst.device_token=deviceToken;

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG,"s:"+s);
            }
        });

    }


}

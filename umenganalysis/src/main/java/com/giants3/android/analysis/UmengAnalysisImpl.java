package com.giants3.android.analysis;

import android.content.Context;


import com.giants3.android.api.analytics.AnalysisApi;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by davidleen29 on 2018/3/31.
 */

public class UmengAnalysisImpl implements AnalysisApi {




    public UmengAnalysisImpl(Context context)
    {

//与微社区存在UMENG_APP_KEY上不一致的冲突
        //统计sdk 采用代码注入方式。
        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(context, BuildConfig.UMENG_APP_KEY,BuildConfig.UMENG_CHANNEL, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setDebugMode(true);
        // MobclickAgent.setCatchUncaughtExceptions(true);



    }
    @Override
    public void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    @Override
    public void onPause(Context context) {
        MobclickAgent.onPause(context);
    }
}

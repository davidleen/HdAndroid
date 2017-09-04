package com.giants3.hd.android.helper;

import android.content.Context;

import com.giants3.hd.android.BuildConfig;
import com.giants3.hd.android.HdApplication;
import com.umeng.analytics.MobclickAgent;
/**
 *
 * 分析工具包装类
 * Created by davidleen29 on 2016/11/3.
 */

public class AnalysisFactory {

    private  static  AnalysisFactory analysis;



    public static AnalysisFactory getInstance() {

        if(analysis==null)
        {
            //与微社区存在UMENG_APP_KEY上不一致的冲突
            //统计sdk 采用代码注入方式。
            MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(HdApplication.baseContext, BuildConfig.UMENG_APP_KEY,BuildConfig.UMENG_CHANNEL, MobclickAgent.EScenarioType.E_UM_NORMAL);
            MobclickAgent.startWithConfigure(config);
            MobclickAgent.setDebugMode(true);
            // MobclickAgent.setCatchUncaughtExceptions(true);

            analysis=new AnalysisFactory();
        }
        return analysis;
    }

    public  void onResume(Context context)
    {
        MobclickAgent.onResume(context);
    }


    public  void onPause(Context context)
    {
        MobclickAgent.onPause(context);
    }
}

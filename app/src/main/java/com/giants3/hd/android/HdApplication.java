package com.giants3.hd.android;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.giants3.hd.android.helper.ConnectionHelper;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.umeng.analytics.MobclickAgent;


import java.io.File;
import java.io.IOException;

/**
 * Created by david on 2016/1/2.
 */
public class HdApplication extends Application {
    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();

        ToastHelper.init(this);
        SharedPreferencesHelper.init(this);
        ConnectionHelper.init(this);
        HttpUrl.init(this);
        Utils.init(this);

        boolean autoUpdates = BuildConfig.AUTO_UPDATES;
        initImageLoader();


        //与微社区存在UMENG_APP_KEY上不一致的冲突
        //统计sdk 采用代码注入方式。
        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(this,BuildConfig.UMENG_APP_KEY,BuildConfig.UMENG_CHANNEL, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setDebugMode(true);
    }


    /**
     * 初始化ImageLoader组件
     */
    private void initImageLoader() {


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //默认显示属性。
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //.showImageForEmptyUri(R.mipmap.icon_image_empty)
                .showImageOnFail(R.mipmap.icon_image_lost).showImageOnLoading(R.mipmap.icon_image_loading).cacheInMemory(true).cacheOnDisk(true)
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(width, height) // default = device screen dimensions
                        //sd 卡上缓存图片大小无限制。
                .diskCacheExtraOptions(Integer.MAX_VALUE, Integer.MAX_VALUE, null)
                        //  .taskExecutor(...)
                        // .taskExecutorForCachedImages(...)
                .threadPoolSize(5) // default  线程池数
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 线程优先级别
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default 线程执行顺序
                .denyCacheImageMultipleSizesInMemory()
                        // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))  //内存缓存数  2M
                        //   .memoryCacheSize(10 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default


                        // .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .imageDownloader(new BaseImageDownloader(this)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(options) // default
                        // .writeDebugLogs()


                .build();
        ImageLoader.getInstance().init(config);
    }
}

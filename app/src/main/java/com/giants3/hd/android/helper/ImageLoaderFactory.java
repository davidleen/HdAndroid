package com.giants3.hd.android.helper;

import android.content.Context;
import android.util.DisplayMetrics;

import com.giants3.hd.android.HdApplication;
import com.giants3.hd.android.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * imageLoader 第三方库包装类
 *
 * Created by davidleen29 on 2016/11/3.
 */

public class ImageLoaderFactory {

  private static   ImageLoader imageLoader;

    public static ImageLoader getInstance() {
        if(imageLoader==null)
        {

            Context context= HdApplication.baseContext;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            //默认显示属性。
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    //.showImageForEmptyUri(R.mipmap.icon_image_empty)
                    .showImageOnFail(R.mipmap.icon_image_lost).showImageOnLoading(R.mipmap.icon_image_loading).cacheInMemory(true).cacheOnDisk(true)
                    .build();


            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
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
                    .discCacheExtraOptions(Integer.MAX_VALUE, Integer.MAX_VALUE, null)
                    // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))  //内存缓存数  2M
                    //   .memoryCacheSize(10 * 1024 * 1024)
                    .memoryCacheSizePercentage(13) // default


                    // .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    .imageDownloader(new BaseImageDownloader(context)) // default
                    .imageDecoder(new BaseImageDecoder(true)) // default
                    .defaultDisplayImageOptions(options) // default
                    .writeDebugLogs()


                    .build();
            ImageLoader.getInstance().init(config);
            imageLoader=imageLoader.getInstance();
        }
        return imageLoader;
    }
}

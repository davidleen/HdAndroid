package com.giants3.hd.android.helper;

import android.content.Context;
import android.content.Intent;

import com.giants3.hd.android.activity.ImageViewerActivity;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;

/**
 * Created by david on 2016/3/25.
 */
public class ImageViewerHelper {


    /**
     * 显示图片
     *
     * @param context
     * @param url     图片路径  这个是相对路径
     */
    public static void view(final Context context, String url) {


        if(StringUtils.isEmpty(url))
          return;
          String fullUrl=url;
        if( !url.startsWith("http://")&&!url.startsWith("file://"))
         fullUrl =  HttpUrl.completeUrl(url);

        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(ImageViewerActivity.EXTRA_URL, fullUrl);
        context.startActivity(intent);


    }


}

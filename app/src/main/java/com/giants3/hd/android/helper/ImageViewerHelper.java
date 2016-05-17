package com.giants3.hd.android.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;

import com.giants3.hd.android.activity.PictureViewActivity;
import com.giants3.hd.data.net.HttpUrl;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sigseg.android.map.ImageViewerActivity;

import java.io.File;

/**
 * Created by david on 2016/3/25.
 */
public class ImageViewerHelper {


    /**
     * 显示图片
     * @param context
     * @param url  图片路径  这个是相对路径
     */
    public static void view(Context context, String url) {

        File file = ImageLoader.getInstance().getDiskCache().get(HttpUrl.completeUrl(url));
        if (file == null && !file.exists()) return;
        BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
        tmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), tmpOptions);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        if (tmpOptions.outWidth > metrics.widthPixels  &&tmpOptions.outHeight  > metrics.heightPixels  ) {

            File newFile = file;

                Intent intent = new Intent(context, ImageViewerActivity.class);
                intent.setData(Uri.fromFile(newFile));

                context.startActivity(intent);

        } else {
            Intent intent = new Intent(context, PictureViewActivity.class);
            intent.putExtra(PictureViewActivity.EXTRA_URL, url);
            context.startActivity(intent);


            }

    }


}

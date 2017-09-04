package com.giants3.hd.android.helper;

import android.graphics.Bitmap;

import com.giants3.hd.android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by davidleen29 on 2015/7/27.
 */
public class ImageLoaderHelper {

    //默认显示属性。
   static DisplayImageOptions  localDisplayOptions;


    static DisplayImageOptions portraitDisplayOptions;

    static DisplayImageOptions localPortraitDisplayOptions;
    public static DisplayImageOptions getLocalDisplayOptions()
    {

        if(localDisplayOptions==null)
        {
            localDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.icon_image_empty).showImageOnFail(R.mipmap.icon_image_lost).showImageOnLoading(R.mipmap.icon_image_loading).cacheInMemory(true).cacheOnDisk(false)
                    .build();


        }

        return localDisplayOptions;


    }
    public static DisplayImageOptions getPortraitDisplayOptions()
    {

        if(portraitDisplayOptions==null)
        {
            portraitDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.icon_photo_small).showImageOnFail(R.mipmap.icon_photo_small).showImageOnLoading(R.mipmap.icon_image_loading).cacheInMemory(true).cacheOnDisk(true)
                    .build();


        }

        return portraitDisplayOptions;


    }
    public static DisplayImageOptions getLocalPortraitDisplayOptions()
    {

        if(localPortraitDisplayOptions==null)
        {
            localPortraitDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.icon_photo_small).showImageOnFail(R.mipmap.icon_photo_small).showImageOnLoading(R.mipmap.icon_image_loading).cacheInMemory(true).cacheOnDisk(false)
                    .build();


        }

        return localPortraitDisplayOptions;


    }



    private static DisplayImageOptions circleDisplayOptions;
    public static DisplayImageOptions getCircleDisplayOptions()
    {

        if(circleDisplayOptions==null)
        {
            circleDisplayOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.icon_photo_small).showImageOnFail(R.mipmap.icon_photo_small).showImageOnLoading(R.mipmap.icon_image_loading).displayer(new RoundedBitmapDisplayer(1000)).cacheInMemory(true).cacheOnDisk(true)
                    .build();


        }

        return circleDisplayOptions;


    }


    public static byte[] bitmap2Byte(Bitmap bitmap)
    {


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
       byte[] data = bos.toByteArray();
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  data;
    }






}

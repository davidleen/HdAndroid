package com.giants3.android.frame.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by davidleen29 on 2018/8/4.
 */

public class StorageUtils {


    private static String root;

    public static   void  setRoot(String root)
    {


        StorageUtils.root = root;
    }


    public static  void writeString(String data,String fileName)
    {
        String filePath=getFilePath(fileName);
        FileUtils.writeStringToFile(data,filePath);



    }




    public static final String getFilePath(String fileName)
    {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + root + File.separator + fileName;
        return filePath;

    }

    public static String readStringFromFile(String keyInitData) {
        String filePath=getFilePath(keyInitData);
        return  FileUtils.readStringFromFile(filePath);
    }
}

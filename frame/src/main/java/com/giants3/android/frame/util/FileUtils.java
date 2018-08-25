package com.giants3.android.frame.util;

import com.giants3.ByteArrayPool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by davidleen29 on 2018/8/4.
 */

public class FileUtils {


    public static final  String DEFAULT_CHARSET="UTF-8";

    public static void safeClose(InputStream inputStream) {

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void safeClose(OutputStream outputStream) {

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void writeStringToFile(String data, String filePath) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            if (!file.isFile()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(filePath);

            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            Log.e(e);
        } catch (IOException e) {

            Log.e(e);
        } catch (Throwable t) {
            Log.e(t);
        }

        FileUtils.safeClose(fileOutputStream);

    }

    public static String readStringFromFile(String filePath) {






        String result=null;

        byte[] bytes= ByteArrayPool.getInstance().getBuf(1024);

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {



            FileInputStream fileInputStream=new FileInputStream(filePath);
            int leng=0;
            while ((leng=fileInputStream.read(bytes))>0)
            {


                Log.e("leng:"+leng);
                byteArrayOutputStream.write(bytes,0,leng);

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ByteArrayPool.getInstance().returnBuf(bytes);

        try {
            result=new String( byteArrayOutputStream.toByteArray(),DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return result;
    }
}

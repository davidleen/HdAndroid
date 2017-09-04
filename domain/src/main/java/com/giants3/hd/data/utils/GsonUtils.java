package com.giants3.hd.data.utils;

import com.giants3.hd.exception.HdException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * 辅助类
 * Created by davidleen29 on 2015/7/6.
 */
public class GsonUtils {
    public static Gson gson=new Gson();


    public static String toJson(Object o)
    {

        return gson.toJson(o);

    }


    public static String toIOs(Object o)
    {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(out,
                CHARSET_ISO );
        gson.toJson(gson.toJsonTree(o),new JsonWriter(writer));
        return out.toString();

    }


    public   static  <T> T fromJson(String json, Type typeOfT)throws  HdException
    {

        try{
            return gson.fromJson(json,typeOfT);}catch (Throwable  e)
        {


            e.printStackTrace();
            throw HdException.create("error Json String：\n "+json);

        }
    }


    public static <T> T  fromReader( Reader reader, Type typeOfT) throws  Exception
    {

            return gson.fromJson(reader,typeOfT);

//        Reader json = new InputStreamReader(inputMessage.getBody(),
//                 CHARSET_ISO );
    }



    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static final Charset  CHARSET_ISO = Charset.forName("ISO-8859-1");
}

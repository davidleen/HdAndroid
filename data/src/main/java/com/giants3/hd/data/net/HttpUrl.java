package com.giants3.hd.data.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.giants3.hd.data.utils.StringUtils;

/**
 *  网络常量
 */
public class HttpUrl {

    public static final String SHARE_FILE="url_file";


    public static final  String DEFAULT_IPAddress="192.168.2.108";
    public static final String DEFAULT_IPPort="8080";
    public static final String DEFAULT_ServiceName="Service";

    public static   String IPAddress=DEFAULT_IPAddress;
    public static String IPPort=DEFAULT_IPPort;
    public static String ServiceName=DEFAULT_ServiceName;


    public static   String KEY_IPAddress="_IPAddress";
    public static String KEY_IPPort="_IPPort";
    public static String KEY_ServiceName="_ServiceName";


    public static final String BASE_URL_FORMAT="http://%s:%s/%s/";
    public static String BASE_URL="";
    static final String API_LOGIN ="/api/authority/aLogin" ;
    public static String token="";

    private static Context mContext;

    public static void init(Context context)
    {
        mContext=context;
        SharedPreferences sf= context.getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);
        String ip=sf.getString(KEY_IPAddress,""  );
        if(ip=="")
        {
            SharedPreferences.Editor text= sf.edit();
            text.putString(KEY_IPAddress,DEFAULT_IPAddress);
            text.putString(KEY_IPPort,DEFAULT_IPPort);
            text.putString(KEY_ServiceName,DEFAULT_ServiceName);
            text.commit();

        }


        IPAddress=sf.getString(KEY_IPAddress,DEFAULT_IPAddress);
        IPPort=sf.getString(KEY_IPPort,DEFAULT_IPPort);
        ServiceName=sf.getString(KEY_ServiceName,DEFAULT_ServiceName);
        generateBaseUrl();


    }


    public static void reset(String ip, String port, String service) {


        IPAddress =ip;
        IPPort=port;
        ServiceName=service;
        SharedPreferences sf= mContext.getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);


        SharedPreferences.Editor text= sf.edit();
        text.putString(KEY_IPAddress,IPAddress);
        text.putString(KEY_IPPort,IPPort);
        text.putString(KEY_ServiceName,ServiceName);
        text.commit();

        generateBaseUrl();


    }

    private static final  void  generateBaseUrl()
    {
        BASE_URL=String.format( BASE_URL_FORMAT,IPAddress,IPPort,ServiceName);
    }


    public static final String getBaseUrl()
    {

        return BASE_URL ;
    }

    public static void setToken(String mToken) {

        token=mToken;

    }


    public static String completeUrl(String url)
    {
        return additionInfo(BASE_URL+url );
    }


    public static String additionInfo(String url)
    {


        if(StringUtils.isEmpty(token))
        {
            return url;
        }

        if(url.contains("?"))
        {
            url+="&token="+token;
        }else
        {
            url+="?token="+token;
        }
//        if(url.contains("?"))
//        {
//            url+="&appVersion="+versionCode;
//        }else
//        {
//            url+="?appVersion="+versionCode;
//        }

        return url;
    }

    public static String login() {
        return completeUrl(API_LOGIN);
    }

    public static String getProductList(String name,int pageIndex,int pageSize)
    {
      return completeUrl(String.format(RestApi.API_URL_GET_PRODUCT_LIST, name, pageIndex, pageSize));
    }

    public static String getOrderList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(RestApi.API_URL_GET_ORDER_LIST, name, pageIndex, pageSize));
    }

    public static String getOrderItemList(String orderNo ) {
        return completeUrl(String.format(RestApi.API_URL_GET_ORDER_ITEM_LIST, orderNo ));
    }
}

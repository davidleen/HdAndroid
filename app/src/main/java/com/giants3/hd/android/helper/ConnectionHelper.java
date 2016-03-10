package com.giants3.hd.android.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by david on 2016/1/2.
 */
public class ConnectionHelper {


    public static Context mContext;



    public static void  init(Context context)
    {
        mContext=context;



    }




    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    public static  boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}

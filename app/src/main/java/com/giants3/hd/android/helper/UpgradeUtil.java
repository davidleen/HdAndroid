package com.giants3.hd.android.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;


import com.giants3.hd.utils.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * apk 下载安装帮助类。
 * <p/>
 * Created by davidleen29 on 2016/7/21.
 */
public class UpgradeUtil {


    public static final void startUpgrade2(Context context, int id,String name, String url,long fileLength) {
        Intent intent = new Intent(context, AppDownloadService.class);
        intent.putExtra(AppDownloadService.KEY_ID, id);
        intent.putExtra(AppDownloadService.KEY_URL, url);
        intent.putExtra(AppDownloadService.KEY_NAME, name);
        intent.putExtra(AppDownloadService.KEY_FILE_LENGTH, fileLength);
        intent.putExtra(AppDownloadService.KEY_PACKAGE, context.getPackageName());
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {



            }
        },Context.BIND_AUTO_CREATE);
    }

    private static AsyncTask asyncTask;


}

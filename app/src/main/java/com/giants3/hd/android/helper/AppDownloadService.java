package com.giants3.hd.android.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.giants3.hd.android.R;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.utils.IoUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * apk下载更新服务
 */
public class AppDownloadService extends Service {


    public final static String KEY_ID = "key_id";
    /**
     * 下载资源的地址
     */
    public final static String KEY_URL = "key_url";
    /**
     * 下载的资源名称
     */
    public final static String KEY_NAME = "key_name";

    public final static String KEY_HREF = "key_href";
    public final static String KEY_PACKAGE = "key_package";
    public final static String KEY_SHOW_NOTIFY = "key_show_notify";
    public static final String TAG = "AppDownloadService";
    public static final String KEY_FILE_LENGTH = "key_file_length";


    private PackageReceiver packageReceiver;
    private String packageName;
    public String name;

    private static NotificationManager mNotifiManager;
    private Notification mNotification;
    private MyHandler myHandler;
    private RemoteViews mRemoteViews;
    private static SparseIntArray download = new SparseIntArray();
    //public static SparseArray<AsyncTask<Void, Integer, Boolean>> downloadTasks = new SparseArray<AsyncTask<Void, Integer, Boolean>>();


    public static String strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Environment.getDownloadCacheDirectory() + "/";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        File file = new File(strPath);
        file.mkdirs();
        mNotifiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        myHandler = new MyHandler(Looper.myLooper(), AppDownloadService.this);
        packageReceiver = new PackageReceiver();
        IntentFilter mFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        mFilter.addDataScheme("package");
        registerReceiver(packageReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        packageName = null;
        unregisterReceiver(packageReceiver);
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 根据下载url 获取相应的本地地址。
     *
     * @param downloadUrl
     * @return
     */
    public static String getAppPath(String downloadUrl) {

        final String strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Environment.getDownloadCacheDirectory();
        String filename = String.valueOf(downloadUrl.hashCode());
        final String filePath = strPath + "/" + filename + ".apk";
        return filePath;
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        super.onStartCommand(i, flags, startId);
        if (i != null) {
            final int id = i.getIntExtra(KEY_ID, 0);
            String url = i.getStringExtra(KEY_URL);
            final String href = i.getStringExtra(KEY_HREF);
            packageName = i.getStringExtra(KEY_PACKAGE);
            name = i.getStringExtra(KEY_NAME);
            final long fileLength = i.getLongExtra(KEY_FILE_LENGTH, 0);
            final boolean notify = i.getBooleanExtra(KEY_SHOW_NOTIFY, true);


            final String softUrl = url;


           // ImageLoaderFactory.getInstance().getDiskCache().remove(softUrl);

            final File file = ImageLoaderFactory.getInstance().getDiskCache().get(softUrl);


            if (!(file != null && file.exists() && file.length() > 0)) {



                AsyncTask<Void, Integer, Boolean> task = new AsyncTask<Void, Integer, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... params) {

                        if (download.indexOfKey(id) > 0)
                            return false;

                        mNotifiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        if (notify) {
                            mNotification = new Notification();

                            mRemoteViews = new RemoteViews(getPackageName(), R.layout.app_dowmload_notification_layout);

                            //设置通知栏显示内容
                            mNotification.icon = android.R.drawable.stat_sys_download;
                            mNotification.tickerText = name + "开始下载";/*"开始下载"*/
                            ;
//						        mNotification.flags |= Notification.FLAG_NO_CLEAR;

                            mRemoteViews.setTextViewText(R.id.title, name + "正在下载");
                            mRemoteViews.setImageViewResource(R.id.image, android.R.drawable.stat_sys_download);
                            mRemoteViews.setProgressBar(R.id.progressBar, 100, 0, false);
                            mRemoteViews.setTextViewText(R.id.precent, "0%");
                            mNotification.contentView = mRemoteViews;
                            mNotification.contentIntent = getIntent(id);

                            mNotifiManager.notify(id, mNotification);
                        }

                        download.put(id, 0);

                        Boolean result = false;

                        URL newUrl = null;
                        InputStream inputStream = null;
                        ContentLengthInputStream contentLengthInputStream = null;
                        try {
                            newUrl = new URL(softUrl);
                            inputStream = newUrl.openStream();

                            contentLengthInputStream = new ContentLengthInputStream(inputStream, (int) fileLength);

                            ImageLoaderFactory.getInstance().getDiskCache().save(softUrl, contentLengthInputStream,
                            new IoUtils.CopyListener() {
                                @Override
                                public boolean onBytesCopied(int current, int total) {


                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    onProgressUpdate(new Integer[]{current, total});
                                    return true;
                                }
                            }
                            );

                            result = true;
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            IoUtils.closeSilently(contentLengthInputStream);
                            IoUtils.closeSilently(inputStream);
                        }


                        return result;
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);

                        int current = values[0];
                        int total = values[1];


                        downloadPropress(id, current * 100 / total, name);


                    }

                    protected void onPostExecute(Boolean result) {


                        if (result) {

                            downloadSuccess(file.getPath(), id, name, softUrl);
                             //ToastHelper.show("下载成功， 准备安装");
                            installSoftware(file.getPath(), ImageLoaderFactory.getInstance().getDiskCache().get(softUrl).getAbsolutePath());
                            //  downloadSuccess(ImageLoaderFactory.getInstance().getDiskCache().get(softUrl).getPath(),id,name,softUrl);
                        } else {
                            downloadFail(id, name, null);
                        }


                    }

                    ;
                };
                task.execute();
                //downloadTasks.put(id, task);

            } else {
                installSoftware(file.getPath(), ImageLoaderFactory.getInstance().getDiskCache().get(softUrl).getAbsolutePath());
            }
        }
        return START_STICKY;
    }


    /**
     * @param id
     * @return
     */
    private PendingIntent getIntent(int id) {
        Intent mIntent = new Intent();
//        mIntent.setComponent(new ComponentName(getPackageName(), DownloadAppDialogActivity.class.getName()));
//        mIntent.putExtra("id", id);
        PendingIntent mPendIntent = PendingIntent.getActivity(AppDownloadService.this, id, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return mPendIntent;
    }

    private void downloadSuccess(String filePath, int id, String name, String href) {
        Message message = myHandler.obtainMessage(1, filePath);
        message.arg1 = id;
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("href", href);
        message.setData(bundle);
        myHandler.sendMessage(message);
    }

    private void downloadSuccess2(String filePath, int id, String name, String href) {

        Message message = myHandler.obtainMessage(2, filePath);
        message.arg1 = id;
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("href", href);
        message.setData(bundle);
        myHandler.sendMessage(message);
    }

    private void downloadPropress(int id, int precent, String name) {
        download.put(id, precent);
        Message message = myHandler.obtainMessage(3, precent);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        message.setData(bundle);
        message.arg1 = id;
        myHandler.sendMessage(message);
    }

    public void downloadFail(int id, String name, File tempFile) {
        if (tempFile != null && tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
        Message message = myHandler.obtainMessage(4, name + "下载失败！"/*"下载失败！"*/);
        message.arg1 = id;
        myHandler.sendMessage(message);
    }

    private void installSoftware(String filePath, String strPath) {
        chmod777(strPath);
        try {
            Runtime.getRuntime().exec("chmod 777 " + filePath);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        AppDownloadService.this.startActivity(intent);
    }

    private void chmod777(String strPath) {
        if (!TextUtils.isEmpty(strPath)) {
            String[] paths = strPath.split("/");
            String root = "";
            for (String path : paths) {
                if (!TextUtils.isEmpty(path)) {
                    root += "/" + path;
                    try {
                        Runtime.getRuntime().exec("chmod 777 " + root);
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }

    class MyHandler extends Handler {
        private Context context;

        public MyHandler(Looper looper, Context c) {
            super(looper);
            this.context = c;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        download.delete(msg.arg1);
                        break;
                    case 1:
                        mRemoteViews.setTextViewText(R.id.title, msg.getData().getString("name") + getResources().getString(R.string.download_end)/*"下载完成"*/);
                        mRemoteViews.setImageViewResource(R.id.image, android.R.drawable.stat_sys_download);
                        mRemoteViews.setProgressBar(R.id.progressBar, 100, 100, false);
                        mRemoteViews.setTextViewText(R.id.precent, "100%");
                        mNotification.contentView = mRemoteViews;
                        mNotification.contentIntent = getIntent(msg.arg1);
                        mNotifiManager.notify(msg.arg1, mNotification);
                        mNotifiManager.cancel(msg.arg1);

//                        msg.arg1=2;
//                        myHandler.sendMessageDelayed(msg,300);

                    case 2:

                     //   downloadTasks.remove(msg.arg1);

                        // 下载完成后清除所有下载信息，执行安装提示
                        download.delete(msg.arg1);

                        String filePath = (String) msg.obj;
                        File srcFile = new File(filePath + ".temp");
                        File desFile = new File(filePath);
                        if (srcFile != null && srcFile.exists()) {
                            srcFile.renameTo(desFile);
                            if (msg.what != 2)
                                installSoftware(filePath, strPath);

                            mNotifiManager.cancel(msg.arg1);
                        }
                        break;
                    case 3:
                        int precent = download.get(msg.arg1);
                        mRemoteViews.setTextViewText(R.id.title, msg.getData().getString("name") + getResources().getString(R.string.download_in_progress)/*"正在下载"*/);
                        mRemoteViews.setImageViewResource(R.id.image, android.R.drawable.stat_sys_download);
                        mRemoteViews.setProgressBar(R.id.progressBar, 100, precent, false);
                        mRemoteViews.setTextViewText(R.id.precent, precent + "%");
                        mNotification.contentView = mRemoteViews;
                        mNotification.contentIntent = getIntent(msg.arg1);

//					mNotification.setLatestEventInfo(AppDownloadService.this, msg.getData().getString("name")+"正在下载",  download.get(msg.arg1) + "%", null);
                        mNotifiManager.notify(msg.arg1, mNotification);
                        break;
                    case 4:
                       // downloadTasks.remove(msg.arg1);

                        Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        download.delete(msg.arg1);
                        mNotifiManager.cancel(msg.arg1);
                        break;
                }
            }
        }
    }




    /**
     * 系统通知处理
     * <p/>
     * 针对新的package 添加通知进行拦截。
     */
    public class PackageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                // 获得安装的包名
                String package_name = intent.getDataString().substring(8);
                if (package_name.equals(packageName)) {
                    // 安装成功后直接开启应用
                    Intent mIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
                    if (mIntent != null) {
                        startActivity(mIntent);
                    }
                }
            }
        }
    }


    /**
     * 下载数据
     */
    public class DownloadData {

        public int id;
        public String name;
        public String url;
        public String packageName;
        boolean notify;
        int progress;
        int downloadSize;
        int totalSize;


        public DownloadData(int id, String name, String url, String packageName, boolean notify) {
            this.id = id;
            this.name = name;
            this.url = url;
            this.packageName = packageName;
            this.notify = notify;
        }
    }


    public class ServiceBinder extends Binder {
        public AppDownloadService getService() {
            return AppDownloadService.this;
        }

    }


}

package com.giants3.hd.android.message;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.RemoteViews;

import com.giants3.android.frame.util.Log;
import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.MainActivity;
import com.giants3.hd.android.activity.WorkFlowMainActivity;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.noEntity.app.PushMessage;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by davidleen29 on 2018/6/24.
 */

public class PushManager {

    static PushManager pushManager;
    private Context context;


    public static PushManager getInstance() {
        if (pushManager == null) pushManager = new PushManager();
        return pushManager;
    }

    public void init(Application application) {
        this.context = application;
    }


    public void handleMessage(PushMessage pushMessage) {


        Intent intent=null;
        switch (pushMessage.messageType)
        {
            case PushMessage.TYPE_MESSAGE_RECEIVE:
            case PushMessage.TYPE_MESSAGE_RECEIVE_ALERT:
                intent = new Intent(context, WorkFlowMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                long messageId=Long.valueOf(pushMessage.custom);
                intent.putExtra(WorkFlowMainActivity.EXTRA_FLOW_MESSAGE,messageId);
                break;


        }

        if(intent!=null)
            showWorkFlowMessageNotification(pushMessage.hashCode(), context, pushMessage.ticker, pushMessage.title, pushMessage.content, pushMessage.icon, intent);


    }


    // - 收到私信 在通知栏显示-
    private void showWorkFlowMessageNotification(final int notifyId, final Context context, final String tickerText, final String contentTitle,
                                  final String contentText, String thumbnail  , final Intent intent) {



        String url = HttpUrl.completeUrl(thumbnail);

       // Bitmap loadedImage=ImageLoaderFactory.getInstance().loadImageSync(url);

        ImageLoaderFactory.getInstance().loadImage(url, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                showNotification(  notifyId,   context,   tickerText,   contentTitle,
                        contentText,    null   ,   intent);

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                Log.e("currentThread:"+Thread.currentThread());

                final RemoteViews mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
                mRemoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);

                mRemoteViews.setTextViewText(R.id.title, contentTitle);
                mRemoteViews.setTextViewText(R.id.content, contentText);
                mRemoteViews.setImageViewBitmap(R.id.icon,loadedImage );

                showNotification(  notifyId,   context,   tickerText,   contentTitle,
                          contentText,    mRemoteViews   ,   intent);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                showNotification(  notifyId,   context,   tickerText,   contentTitle,
                        contentText,     null   ,   intent);

            }
        });



    }


    /**
     *
     * @param notifyId
     * @param context
     * @param tickerText
     * @param contentTitle
     * @param contentText
     * @param remoteView
     * @param intent
     */
    private void showNotification(int notifyId, Context context, String tickerText, String contentTitle,
                                  String contentText, RemoteViews remoteView   , Intent intent)
    {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(context)
                .setAutoCancel(true)
                .setContentTitle(contentTitle)
                .setContentText(contentText).setTicker(tickerText)
                .setContentIntent(pendingIntent)
                .setSmallIcon( R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContent(remoteView)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND;
        // 唤醒
        notificationManager.notify(notifyId, notification);
    }





}

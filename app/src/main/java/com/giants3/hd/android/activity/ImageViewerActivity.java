package com.giants3.hd.android.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.widget.ZoomImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sigseg.android.io.RandomAccessFileInputStream;
import com.sigseg.android.map.ImageSurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;


/**
 * 图片展示界面
 */
public class ImageViewerActivity extends BaseActivity {
    private static final String TAG = "ImageViewerActivity";


    /**
     * 大圖展示特殊定製的options  圖片只在sd卡上緩存。 不在内存中緩存
     */
    public static DisplayImageOptions displayImageOptions;

    static {


        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder().cacheInMemory(false).cacheOnDisk(true);
        displayImageOptions = builder.build();
    }


    public static final String EXTRA_URL = "URL";


    public Bitmap showBitmap;

    private AsyncTask<String, Void, Boolean> asyncTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final String url = getIntent().getStringExtra(EXTRA_URL);
        final File file = ImageLoaderFactory.getInstance().getDiskCache().get(url);
        final WeakReference<Context> weakReference = new WeakReference<Context>(this);
        if (file == null || !file.exists()) {
            showWaiting("正在加载大图，请稍候。。。");
            asyncTask = new AsyncTask<String, Void, Boolean>() {


                @Override
                protected Boolean doInBackground(String[] params) {

                    boolean result = false;
                    for (String url : params) {

                        try {
                            URL newUrl = new URL(url);
                            InputStream inputStream = newUrl.openStream();
                            result |= ImageLoaderFactory.getInstance().getDiskCache().save(url, inputStream, null);
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(Boolean o) {
                    super.onPostExecute(o);
                    hideWaiting();
                    if (weakReference.get() == null) {
                        return;
                    }
                    File file = null;
                    if (o) {


                        file = ImageLoaderFactory.getInstance().getDiskCache().get(url);
                    }
                    if (file == null || !file.exists()) {


                        ToastHelper.show("图片加载失败，请重试");
                        finish();
                        return;
                    }


                    handleOnPictureFile(weakReference.get(), file);


                }
            };
            asyncTask.execute(url);


            return;
        } else
            handleOnPictureFile(this, file);

    }


    /**
     * 根据图片文件的大小 决定显示不同的界面
     *
     * @param context
     * @param file
     */
    private void handleOnPictureFile(Context context, File file) {
        BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
        tmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), tmpOptions);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        if (tmpOptions.outWidth > metrics.widthPixels && tmpOptions.outHeight > metrics.heightPixels) {


            InputStream is = null;
            try {
                is = new RandomAccessFileInputStream(file);
                final ImageSurfaceView imageSurfaceView = new ImageSurfaceView(this);
                imageSurfaceView.setInputStream(is);
                setContentView(imageSurfaceView);
                imageSurfaceView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageSurfaceView.setViewportCenter();
                    }
                }, 10);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {


            ZoomImageView zoomImageView = new ZoomImageView(this);
            zoomImageView.setScaleType(ImageView.ScaleType.CENTER);

            zoomImageView.setIsZoomEnabled(true);

            if (file != null && file.exists()) {
                showBitmap = BitmapFactory.decodeFile(file.getPath());
            }

            if (showBitmap != null)
                zoomImageView.setImageBitmap(showBitmap);
            else {
                ToastHelper.show("图片加载失败，请重试");
                finish();
            }
            setContentView(zoomImageView);


        }


        FrameLayout frameLayout = (FrameLayout) findViewById(android.R.id.content);
        TextView textView = new TextView(this);
        textView.setTextColor(Color.BLUE);
        textView.setText(tmpOptions.outWidth + "X" + tmpOptions.outHeight);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        frameLayout.addView(textView, lp);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (showBitmap != null && !showBitmap.isRecycled()) {
            showBitmap.recycle();
        }
        if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            asyncTask.cancel(true);
        }
    }
}

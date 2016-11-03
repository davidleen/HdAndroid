package com.giants3.hd.android.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.widget.ZoomImageView;

import java.io.File;

/**
 * 图片展示act   接收的url 为全路径。
 */
public class PictureViewActivity extends BaseActivity {


    public static final String EXTRA_URL = "URL";

    //    @Bind(R.id.picture )
    ZoomImageView picture;


    Bitmap bitmapRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        picture = new ZoomImageView(this);
        setContentView(picture);
        picture.setScaleType(ImageView.ScaleType.CENTER);
        picture.setIsZoomEnabled(true);


        //  picture.setScaleType(ImageView.ScaleType.CENTER);
        final String url = getIntent().getStringExtra(EXTRA_URL);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);

        File file = ImageLoaderFactory.getInstance().getDiskCache().get(url);

        if (file != null && file.exists()) {
            bitmapRef = BitmapFactory.decodeFile(file.getPath());
        } else {
            finish();
            return;
        }
        if (bitmapRef != null)
            picture.setImageBitmap(bitmapRef);
        else {
            finish();
        }


    }


    @Override
    protected void onDestroy() {
        if (bitmapRef != null && !bitmapRef.isRecycled()) {
            bitmapRef.recycle();
        }
        super.onDestroy();

    }
}

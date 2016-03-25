package com.giants3.hd.android.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.giants3.hd.android.HdApplication;
import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.widget.ZoomImageView;
import com.giants3.hd.data.net.HttpUrl;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.io.File;

import butterknife.Bind;

public class PictureViewActivity extends BaseActivity {


    public static final String EXTRA_URL="URL";

//    @Bind(R.id.picture )
    ZoomImageView picture  ;



    Bitmap bitmapRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        picture=new ZoomImageView(this);
        setContentView(picture);
        picture.setScaleType(ImageView.ScaleType.CENTER);picture.setIsZoomEnabled(true);



      //  picture.setScaleType(ImageView.ScaleType.CENTER);
       final String url= getIntent().getStringExtra(EXTRA_URL);
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setIndeterminate(true);

        File file= ImageLoader.getInstance().getDiskCache().get(HttpUrl.completeUrl(url));

        if(file!=null&&file.exists()){
            bitmapRef=BitmapFactory.decodeFile(file.getPath());
        }else
        {
            finish();
            return;
        }
        if(bitmapRef!=null)
        picture.setImageBitmap(bitmapRef);
        else
        {
            finish();
        }


    }


    @Override
    protected void onDestroy() {
        if(bitmapRef!=null&&!bitmapRef.isRecycled())
        {
            bitmapRef.recycle();
        }
        super.onDestroy();

    }
}

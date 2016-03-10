package com.giants3.hd.android.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.widget.ScrollableImageView;
import com.giants3.hd.data.net.HttpUrl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;

public class PictureViewActivity extends BaseActivity {


    public static final String EXTRA_URL="URL";

    @Bind(R.id.picture )
    ImageView picture  ;



    Bitmap bitmapRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_picture_view);



      //  picture.setScaleType(ImageView.ScaleType.CENTER);
       String url= getIntent().getStringExtra(EXTRA_URL);
        Picasso.with(this).load(HttpUrl.completeUrl(url)).memoryPolicy(MemoryPolicy.NO_CACHE).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmapRef = bitmap;
                picture.setImageBitmap(bitmap);
                int bitmapWidth = bitmap.getWidth();
                int bitmapHeight = bitmap.getHeight();
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                int screenHeight = getResources().getDisplayMetrics().heightPixels;


                // set maximum scroll amount (based on center of image)


                int maxX = (int) ((bitmapWidth / 2) - (screenWidth / 2));
                int maxY = (int) ((bitmapHeight / 2) - (screenHeight / 2));

                // set scroll limits
                final int maxLeft = (maxX * -1);
                final int maxRight = maxX;
                final int maxTop = (maxY * -1);
                final int maxBottom = maxY;

                // set touchlistener
                picture.setOnTouchListener(new View.OnTouchListener() {
                    float downX, downY;
                    int totalX, totalY;
                    int scrollByX, scrollByY;

                    public boolean onTouch(View view, MotionEvent event) {
                        float currentX, currentY;
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                downX = event.getX();
                                downY = event.getY();
                                break;

                            case MotionEvent.ACTION_MOVE:
                                currentX = event.getX();
                                currentY = event.getY();
                                scrollByX = (int) (downX - currentX);
                                scrollByY = (int) (downY - currentY);

                                // scrolling to left side of image (pic moving to the right)
                                if (currentX > downX) {
                                    if (totalX == maxLeft) {
                                        scrollByX = 0;
                                    }
                                    if (totalX > maxLeft) {
                                        totalX = totalX + scrollByX;
                                    }
                                    if (totalX < maxLeft) {
                                        scrollByX = maxLeft - (totalX - scrollByX);
                                        totalX = maxLeft;
                                    }
                                }

                                // scrolling to right side of image (pic moving to the left)
                                if (currentX < downX) {
                                    if (totalX == maxRight) {
                                        scrollByX = 0;
                                    }
                                    if (totalX < maxRight) {
                                        totalX = totalX + scrollByX;
                                    }
                                    if (totalX > maxRight) {
                                        scrollByX = maxRight - (totalX - scrollByX);
                                        totalX = maxRight;
                                    }
                                }

                                // scrolling to top of image (pic moving to the bottom)
                                if (currentY > downY) {
                                    if (totalY == maxTop) {
                                        scrollByY = 0;
                                    }
                                    if (totalY > maxTop) {
                                        totalY = totalY + scrollByY;
                                    }
                                    if (totalY < maxTop) {
                                        scrollByY = maxTop - (totalY - scrollByY);
                                        totalY = maxTop;
                                    }
                                }

                                // scrolling to bottom of image (pic moving to the top)
                                if (currentY < downY) {
                                    if (totalY == maxBottom) {
                                        scrollByY = 0;
                                    }
                                    if (totalY < maxBottom) {
                                        totalY = totalY + scrollByY;
                                    }
                                    if (totalY > maxBottom) {
                                        scrollByY = maxBottom - (totalY - scrollByY);
                                        totalY = maxBottom;
                                    }
                                }

                                picture.scrollBy(scrollByX, scrollByY);
                                downX = currentX;
                                downY = currentY;
                                break;

                        }

                        return true;
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        // Picasso.with(this).load(HttpUrl.completeUrl(url)).fetch();
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

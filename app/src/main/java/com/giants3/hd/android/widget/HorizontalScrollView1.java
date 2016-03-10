package com.giants3.hd.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * 自定义scrollView 提供 滚动接口
 * Created by davidleen29   qq:67320337
 * on 14-5-16.
 */
public class HorizontalScrollView1 extends HorizontalScrollView {


    public HorizontalScrollView1(Context context) {
        super(context);
    }

    public HorizontalScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }


    public interface ScrollViewListener {

        void onScrollChanged(HorizontalScrollView scrollView, int x, int y, int oldx, int oldy);

    }


    private ScrollViewListener scrollViewListener;


    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


}

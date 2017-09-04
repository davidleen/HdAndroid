package com.giants3.hd.android.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by david on 2016/3/19.
 */
public class CustomScrollView extends ViewGroup {
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }

    public static final float MIN_DRAG = 5f;
    int lastX;
    int lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean handled = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                if (Math.abs(x - lastX) > MIN_DRAG || Math.abs(y - lastY) > MIN_DRAG) {
                    handled = true;
                }


//                        lastX=x;
//                        lastY=y;
                break;

        }
        if (handled) return true;
        return super.onInterceptTouchEvent(event);

    }

    View child;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child=getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        if (!mFillViewport) {
//            return;
//        }

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            return;
        }

        if (getChildCount() > 0) {
            final View child = getChildAt(0);
            int width = getMeasuredWidth();
            int height=getMeasuredHeight();
            if (child.getMeasuredWidth() < width||child.getHeight()<height) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);

                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);

                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childWidth = 0;
        int childHeight=0;
        int childMargins = 0;

        if (getChildCount() > 0) {
            childWidth = getChildAt(0).getMeasuredWidth();
            childHeight= getChildAt(0).getMeasuredHeight();

        }




        layoutChildren(l, t, r, b);


    }

    void layoutChildren(int left, int top, int right, int bottom
                      ) {
        final int count = getChildCount();






            final View child = getChildAt(0);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();

                int childLeft=0;
                int childTop=0;





                child.layout(childLeft, childTop, childLeft + width, childTop + height);
            }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean handled = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                View child=getChildAt(0);

                child.layout(x - lastX, y - lastY, child.getWidth() + (x - lastX), child.getHeight() + (y - lastY));
                child.invalidate();

                handled = true;

                break;

        }

        return handled;
    }
}

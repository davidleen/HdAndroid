package com.giants3.hd.android.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by david on 2016/3/5.
 */
public class FullWidthRecyclerView  extends RecyclerView{
    public FullWidthRecyclerView(Context context) {
        super(context);
    }

    public FullWidthRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullWidthRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


//    @Override
//    protected void onMeasure(int widthSpec, int heightSpec) {
//
//        widthSpec=MeasureSpec.makeMeasureSpec(2089,MeasureSpec.AT_MOST);
//       measureChildren(widthSpec,heightSpec);
//
//       // setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight());
//     super.onMeasure(widthSpec, heightSpec);
//        ViewGroup.LayoutParams layoutParams=getLayoutParams();
//        layoutParams.width=getMeasuredWidth();
//
//    }
}

package com.giants3.hd.android.ViewImpl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.viewer.BaseViewer;

import butterknife.ButterKnife;

/**
 * Created by david on 2016/4/12.
 */
public abstract class BaseViewerImpl implements BaseViewer {


    protected Context context;
    private View contentView;

    public BaseViewerImpl(Context context) {
        this.context = context;
    }

    protected void setContentView(int layoutId) {
        contentView = LayoutInflater.from(context).inflate(layoutId, null);
    }

    @Override
    public View getContentView() {
        return contentView;
    }


    protected void setContentView(View contentView) {
        this.contentView = contentView;

    }

    @Override
    public void onCreate() {
        ButterKnife.bind(this, contentView);

    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);

    }

    @Override
    public void showMessage(String message) {


        ToastHelper.showShort(message);

    }
}

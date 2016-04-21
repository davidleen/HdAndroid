package com.giants3.hd.android.ViewImpl;

import android.view.View;

import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.viewer.BaseViewer;
import android.content.Context;

import butterknife.ButterKnife;

/**
 * Created by david on 2016/4/12.
 */
public  abstract  class BaseViewerImpl implements BaseViewer{


    private Context context;
    public BaseViewerImpl( Context context)
    {

        this.context=context;

    }



    @Override
    public void onCreateView(View v)
    {
        ButterKnife.bind(this, v);

    }

    @Override
    public void onDestroyView()
    {
        ButterKnife.unbind(this );

    }

    @Override
    public void showMessage(String message) {


        ToastHelper.showShort(message);

    }
}

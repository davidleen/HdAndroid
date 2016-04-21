package com.giants3.hd.android.viewer;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by david on 2016/4/12.
 */
public  interface    BaseViewer  {







    public void onCreateView(View v);



    public void onDestroyView();


    public void showWaiting();

    public void hideWaiting();

    public void showMessage(String message);

}

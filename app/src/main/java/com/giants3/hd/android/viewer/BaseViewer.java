package com.giants3.hd.android.viewer;

import android.view.View;

/**
 * Created by david on 2016/4/12.
 */
public  interface    BaseViewer  {











      void onDestroy();
      void onCreate();

      void showWaiting();

      void hideWaiting();

      void showMessage(String message);
      View getContentView();
}

package com.giants3.hd.android.mvp;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public interface NewViewer extends  AndroidRouter {
    void hideWaiting();

    void showMessage(String message);

    void showWaiting();

    void startLoginActivity();

}

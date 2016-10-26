package com.giants3.hd.android.mvp;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public interface NewPresenter<T extends  NewViewer> {
    void attachView(T view);
    void start();
    void detachView();



}

package com.giants3.hd.android.mvp;

/**
 * presenter 层基类
 * @param <T>
 * @param <M>
 */
public abstract class BasePresenter<T extends NewViewer,M extends NewModel> implements NewPresenter<T> {

    protected static final String TAG = "BasePresenter";
    protected T mView;
    protected M mModel;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }
 
    public M getModel() {
       return mModel;
    }

}
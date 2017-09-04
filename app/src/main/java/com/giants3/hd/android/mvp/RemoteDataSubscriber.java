package com.giants3.hd.android.mvp;

import com.giants3.hd.noEntity.RemoteData;

import rx.Subscriber;

/**
 * subscriber    网络数据额统一处理。   未登录会自动跳转登录
 * <p>
 * <p>
 * Created by davidleen29 on 2017/6/4.
 */

public abstract class RemoteDataSubscriber<T> extends Subscriber<RemoteData<T>> {


    private BasePresenter viewer;

    public RemoteDataSubscriber(BasePresenter viewer) {


        this.viewer = viewer;
    }

    @Override
    public void onCompleted() {

        if (viewer.getView() == null) return;
        viewer.getView().hideWaiting();
    }

    @Override
    public void onError(Throwable e) {
        if (viewer.getView() == null) return;

        viewer.getView().hideWaiting();
        e.printStackTrace();

        viewer.getView().showMessage(e.getMessage());

    }

    @Override
    public void onNext(RemoteData<T> tRemoteData) {
        if (viewer.getView() == null) return;
        if (tRemoteData.isSuccess()) {

            handleRemoteData(tRemoteData);
            // getView().setData(remoteData);
        } else {


            viewer.getView().showMessage(tRemoteData.message);

            if (tRemoteData.code == RemoteData.CODE_UNLOGIN) {
                viewer.getView().startLoginActivity();
            }
        }

    }

    protected abstract void handleRemoteData(RemoteData<T> data);
}

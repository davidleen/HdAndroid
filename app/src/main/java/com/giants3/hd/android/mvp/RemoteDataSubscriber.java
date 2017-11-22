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


    private BasePresenter presenter;

    public RemoteDataSubscriber(BasePresenter viewer) {


        this.presenter = viewer;
    }

    @Override
    public void onCompleted() {

        if (presenter.getView() == null) return;
        presenter.getView().hideWaiting();
    }

    @Override
    public void onError(Throwable e) {
        if (presenter.getView() == null) return;

        presenter.getView().hideWaiting();
        e.printStackTrace();

        presenter.getView().showMessage(e.getMessage());

    }

    @Override
    public void onNext(RemoteData<T> tRemoteData) {
        if (presenter.getView() == null) return;
        if (tRemoteData.isSuccess()) {

            handleRemoteData(tRemoteData);
            // getView().setData(remoteData);
        } else {


            presenter.getView().showMessage(tRemoteData.message);

            if (tRemoteData.code == RemoteData.CODE_UNLOGIN) {
                presenter.getView().startLoginActivity();
            }
        }

    }

    protected abstract void handleRemoteData(RemoteData<T> data);
}

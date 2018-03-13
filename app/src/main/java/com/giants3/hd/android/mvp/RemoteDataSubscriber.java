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
    private boolean silence;

    public RemoteDataSubscriber(BasePresenter presenter) {


        this(presenter, false);

    }

    public RemoteDataSubscriber(BasePresenter presenter, boolean silence) {


        this.presenter = presenter;
        this.silence = silence;
    }

    @Override
    public void onCompleted() {

        if (!silence) {
            if (presenter.getView() == null) return;
            presenter.getView().hideWaiting();
        }
    }

    @Override
    public void onError(Throwable e) {

        if (!silence) {
            if (presenter.getView() == null) return;

            presenter.getView().hideWaiting();


            presenter.getView().showMessage(e.getMessage());
        }
        e.printStackTrace();

    }

    @Override
    public void onNext(RemoteData<T> tRemoteData) {
        if (presenter.getView() == null) return;
        if (tRemoteData.isSuccess()) {

            handleRemoteData(tRemoteData);
            // getView().setData(remoteData);
        } else {

            if (!silence) {
                presenter.getView().showMessage(tRemoteData.message);

                if (tRemoteData.code == RemoteData.CODE_UNLOGIN) {
                    presenter.getView().startLoginActivity();
                }
            }

        }

    }

    protected abstract void handleRemoteData(RemoteData<T> data);
}

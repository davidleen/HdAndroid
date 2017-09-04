package com.giants3.hd.android.mvp.MainAct;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.FileInfo;

import java.util.Calendar;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/21.
 */

public class MainActPresenter extends BasePresenter<MainActMvp.Viewer, MainActMvp.Model> implements MainActMvp.Presenter {
    @Override
    public void start() {

    }

    public MainActPresenter() {

    }

    @Override
    public MainActMvp.Model createModel() {
        return new MainActModel();
    }

    @Override
    public void checkAppUpdateInfo() {


        getModel().loadAppUpgradeInfo(new Subscriber<RemoteData<FileInfo>>() {
            @Override
            public void onCompleted() {
                getView().hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideWaiting();
                getView().showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<FileInfo> stringRemoteData) {

                getView().hideWaiting();
                if (stringRemoteData.isSuccess()) {


                    if (stringRemoteData.datas.size() > 0) {

                        FileInfo fileInfo = stringRemoteData.datas.get(0);


                        getView().showMessage("有新版本apk，开始下载。。。");
                        getView().startDownLoadApk(fileInfo);


                    } else {
                        getView().showMessage("当前已经是最新版本。");
                    }
                } else {
                    getView().showMessage(stringRemoteData.message);
                }


            }
        });
        getView().showWaiting();
    }

    //上次返回键点击时间
    long lastBackPressTime;

    @Override
    public boolean checkBack() {


        long time = Calendar.getInstance().getTimeInMillis();

        if (time - lastBackPressTime < 2000) {
            return true;
        }
        lastBackPressTime = time;
        return false;


    }
}

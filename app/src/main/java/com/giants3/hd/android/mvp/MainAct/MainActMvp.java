package com.giants3.hd.android.mvp.MainAct;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.FileInfo;

import rx.Subscriber;

/**
 * 主界面 mvp 接口
 * Created by davidleen29 on 2016/10/21.
 */

public interface MainActMvp {


    interface Model extends NewModel {


        /**
         * 返回app最新apk的路径
         * @param subscriber
         */
        void loadAppUpgradeInfo(Subscriber<RemoteData<FileInfo>> subscriber);
    }

    interface Presenter extends NewPresenter< Viewer> {

        /**
         * 檢查app應用更新信息
         */
        void checkAppUpdateInfo();

        boolean checkBack();
    }

    interface Viewer extends NewViewer {

        void startDownLoadApk(FileInfo newApkFileInfo);
    }
}

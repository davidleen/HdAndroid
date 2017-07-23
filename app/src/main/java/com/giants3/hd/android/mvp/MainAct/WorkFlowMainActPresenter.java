package com.giants3.hd.android.mvp.MainAct;

import android.app.Activity;

import com.giants3.hd.android.activity.UpdatePasswordActivity;
import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.noEntity.FileInfo;
import com.giants3.hd.utils.noEntity.MessageInfo;

import java.util.Calendar;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/21.
 */

public class WorkFlowMainActPresenter extends BasePresenter<WorkFlowMainActMvp.Viewer, WorkFlowMainActMvp.Model> implements WorkFlowMainActMvp.Presenter {
    @Override
    public void start() {

    }

    public WorkFlowMainActPresenter() {

    }

    @Override
    public WorkFlowMainActMvp.Model createModel() {
        return new WorkFlowMainActModel();
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


    @Override
    public void setLoginUser(AUser loginUser) {


        getModel().setLoginUser(loginUser);
        getView().bindUser(loginUser);

    }

    @Override
    public void attemptUpdateNewMessageCount() {
        UseCaseFactory.getInstance().createGetNewMessageInfoUseCase().execute(new RemoteDataSubscriber<MessageInfo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<MessageInfo> data) {


                int count=0;
                if(data.isSuccess())
                {
                    count=data.datas.get(0).newWorkFlowMessageCount;
                }

                getView().setNewWorkFlowMessageCount(count);
            }


        });;

    }

    @Override
    public void updatePassword() {


        UpdatePasswordActivity.startActivity((Activity) getView(),0);
    }
}

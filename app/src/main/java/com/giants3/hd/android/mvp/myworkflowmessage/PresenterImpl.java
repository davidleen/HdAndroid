package com.giants3.hd.android.mvp.myworkflowmessage;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.noEntity.RemoteData;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<MVP.Viewer, MVP.Model> implements MVP.Presenter {


    @Override
    public void start() {
        loadData();
        getView().showWaiting();
    }

    @Override
    public MVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void loadData() {



        int pageIndex=0;
        int pageSize=10;
        final RemoteData remoteData = getModel().getRemoteData();
        if(remoteData!=null) pageSize=Math.max(remoteData.pageSize,pageSize);

        UseCaseFactory.getInstance().createGetMyWorkFlowMessageCase(getModel().getKey(), pageIndex, pageSize).execute(new RemoteDataSubscriber<WorkFlowMessage>(this) {

            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMessage> data) {
                getModel().setRemoteData(data);

                getView().setData(getModel().getDatas());
            }
        });


    }

    @Override
    public void loadMore() {


        final RemoteData remoteData = getModel().getRemoteData();

        if (remoteData==null||remoteData.pageIndex + 1 >= remoteData.pageCount)
            getView().hideWaiting();

        UseCaseFactory.getInstance().createGetMyWorkFlowMessageCase(getModel().getKey(), remoteData.pageIndex + 1, remoteData.pageSize).execute(new RemoteDataSubscriber<WorkFlowMessage>(this) {

            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMessage> data) {

                getModel().setRemoteData(data);
                getView().setData(getModel().getDatas());
            }
        });


    }


    public void setKey(String key) {


        getModel().setKey(key);

    }
}


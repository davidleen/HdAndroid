package com.giants3.hd.android.mvp.myundoworkflowmessage;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.MyUndoWorkFlowMessageMVP;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<MyUndoWorkFlowMessageMVP.Viewer, MyUndoWorkFlowMessageMVP.Model> implements MyUndoWorkFlowMessageMVP.Presenter {

    @Override
    public void start() {
        loadData();
    }

    @Override
    public MyUndoWorkFlowMessageMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void loadData() {


        UseCaseFactory.getInstance().createGetUnHandleWorkFlowMessageCase().execute(new RemoteDataSubscriber<WorkFlowMessage>(this) {

            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMessage> data) {
                getView().setData(data);
            }
        });




        getView().showWaiting();





    }

}

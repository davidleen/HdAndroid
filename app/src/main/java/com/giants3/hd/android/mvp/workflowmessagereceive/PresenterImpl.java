package com.giants3.hd.android.mvp.workflowmessagereceive;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpWorkFlow;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

import java.io.File;
import java.util.List;

import rx.Subscriber;

import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Model;
import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Presenter;
import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Viewer;

/**
 * Created by davidleen29 on 2017/5/23.
 */

public class PresenterImpl extends BasePresenter<Viewer, Model> implements Presenter {


    @Override
    public void start() {


        //读取流程权限列表

    }

    @Override
    public void setWorkFlowMessage(WorkFlowMessage workFlowMessage) {
        getModel().setWorkFlowMessage(workFlowMessage);
        getView().bindWorkFlowMessage(getModel().getWorkFlowMessage());
    }


    @Override
    public void onNewPictureFileSelected(File file) {


        getModel().addNewPictureFile(file);

        getView().bindPicture(getModel().getFiles());
    }

    @Override
    public void receiveWorkFlow() {

        List<File> files = getModel().getFiles();
        if(files.size()< ErpWorkFlow.PICTURE_COUNT) {

            getView().showMessage("需要上传三个图片");
            return;
        }

        File[] param= new File[files.size()];
        files.toArray(param);


        WorkFlowMessage workFlowMessage=getModel().getWorkFlowMessage();


        UseCaseFactory.getInstance().createReceiveWorkFlow(workFlowMessage.id,param,"").execute(new Subscriber<RemoteData<Void>>() {
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
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {


                    getView().showMessage("接收成功");
                    getView().finishOk();

                }else
                {
                    getView().showMessage(remoteData.message);
                }

            }
        });

        getView().showWaiting();






    }


    @Override
    public void setWorkFlowMessageId(long workflowMessageId) {



        UseCaseFactory.getInstance().createGetWorkFlowMessageUseCase(workflowMessageId).execute(new RemoteDataSubscriber<WorkFlowMessage>(this) {
            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMessage> data) {


                if(data.isSuccess()&&data.datas.size()>0)

                setWorkFlowMessage(data.datas.get(0));
            }


        });



       /// setWorkFlowMessage();
    }

    //流程拒绝
    @Override
    public void rejectWorkFlow() {
        List<File> files = getModel().getFiles();
        if(files.size()<3) {

            getView().showMessage("需要上传三个图片");
            return;
        }

        File[] param= new File[files.size()];
        files.toArray(param);


        WorkFlowMessage workFlowMessage=getModel().getWorkFlowMessage();
            UseCaseFactory.getInstance().createRejectWorkFlowUseCase(workFlowMessage.id,param,"").execute(new Subscriber<RemoteData<Void>>() {
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
                public void onNext(RemoteData<Void> remoteData) {
                    if (remoteData.isSuccess()) {


                        getView().showMessage("操作成功");
                        getView().finishOk();

                    }else
                    {
                        getView().showMessage(remoteData.message);
                    }

                }
            });
        getView().showWaiting();

    }

    @Override
    public void deleteFile(int indexOfFile) {


        getModel().deleteFile(indexOfFile);

        getView().bindPicture(getModel().getFiles());

    }

    @Override
    public Model createModel() {
        return new ModelImpl();
    }
}

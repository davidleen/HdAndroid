package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.WorkFlowMessage;

import java.io.File;
import java.util.List;

/**
 * Created by davidleen29 on 2017/5/23.
 */

public interface WorkFlowMessageReceive {


    interface Model extends NewModel {


        void setWorkFlowMessage(WorkFlowMessage workFlowMessage);

        void addNewPictureFile(File file);

        List<File> getFiles();

        void deleteFile(int indexOfFile);

        WorkFlowMessage getWorkFlowMessage();
    }

    interface Presenter extends NewPresenter<WorkFlowMessageReceive.Viewer> {


        void setWorkFlowMessage(WorkFlowMessage workFlowMessage);

        void onNewPictureFileSelected(File file);

        void receiveWorkFlow();

        void deleteFile(int indexOfFile);

        void rejectWorkFlow();

        void setWorkFlowMessageId(long workflowMessageId);
    }

    interface Viewer extends NewViewer {



        public void bindPicture(List<File> files);

        void bindWorkFlowMessage(WorkFlowMessage workFlowMessage);

        void finishOk();
    }
}

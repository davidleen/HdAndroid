package com.giants3.hd.android.mvp.workflowmessagereceive;

import com.giants3.hd.android.mvp.WorkFlowMessageReceive;
import com.giants3.hd.entity.WorkFlowMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements WorkFlowMessageReceive.Model {
    @Override
    public List<File> getFiles() {
        return files;
    }

    private WorkFlowMessage workFlowMessage;

    List<File> files = new ArrayList<>();

    @Override
    public void setWorkFlowMessage(WorkFlowMessage workFlowMessage) {

        this.workFlowMessage = workFlowMessage;
    }

    @Override
    public void addNewPictureFile(File file) {


        files.add(file);
    }

    @Override
    public WorkFlowMessage getWorkFlowMessage() {
        return workFlowMessage;

    }

    @Override
    public void deleteFile(int indexOfFile) {


        if (indexOfFile >= 0 && indexOfFile < files.size()) {
            files.remove(indexOfFile);
        }
    }
}

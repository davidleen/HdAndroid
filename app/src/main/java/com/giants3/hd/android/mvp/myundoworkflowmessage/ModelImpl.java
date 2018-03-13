package com.giants3.hd.android.mvp.myundoworkflowmessage;

import com.giants3.hd.android.mvp.MyUndoWorkFlowMessageMVP;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements MyUndoWorkFlowMessageMVP.Model {

    String key="";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String value) {
        this.key=value;
    }
}

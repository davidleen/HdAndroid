package com.giants3.hd.android.mvp.workflowmessagereceive;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.WorkFlowMessageReceive;

import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Model;
import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Presenter;
import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Viewer;

/**
 * Created by davidleen29 on 2017/5/23.
 */

public class PresenterImpl extends BasePresenter<Viewer, Model> implements Presenter {


    @Override
    public void start() {

    }

    @Override
    public Model createModel() {
        return new ModelImpl();
    }
}

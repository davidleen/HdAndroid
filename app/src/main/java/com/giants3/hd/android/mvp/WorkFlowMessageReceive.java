package com.giants3.hd.android.mvp;


/**
 * Created by davidleen29 on 2017/5/23.
 */

public interface WorkFlowMessageReceive {


    interface Model extends NewModel {


    }

    interface Presenter extends NewPresenter<WorkFlowMessageReceive.Viewer> {


    }

    interface Viewer extends NewViewer {


    }
}

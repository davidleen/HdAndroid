package com.giants3.hd.android.mvp;


import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

/**代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface MyUndoWorkFlowMessageMVP {


    interface Model extends NewModel {



    }

    interface Presenter extends NewPresenter<MyUndoWorkFlowMessageMVP.Viewer> {


        void loadData();


    }

    interface Viewer extends NewViewer {


        void setData(RemoteData<WorkFlowMessage> remoteData);
    }
}

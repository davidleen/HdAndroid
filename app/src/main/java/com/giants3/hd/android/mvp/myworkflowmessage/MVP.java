package com.giants3.hd.android.mvp.myworkflowmessage;


import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;

/**代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface MVP {


    interface Model extends NewModel {



    }

    interface Presenter extends NewPresenter<MVP.Viewer> {


        void loadData();


    }

    interface Viewer extends NewViewer {


        void setData(RemoteData<WorkFlowMessage> remoteData);
    }
}
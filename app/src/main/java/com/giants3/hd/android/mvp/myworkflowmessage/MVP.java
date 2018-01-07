package com.giants3.hd.android.mvp.myworkflowmessage;


import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

import java.util.List;

/**代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface MVP {


    interface Model<T> extends PageModel<T> {


    }

    interface Presenter extends NewPresenter<MVP.Viewer> {


        void loadData();

        void  setKey(String key);
        void loadMore();
    }

    interface Viewer extends NewViewer {


        void setData(List<WorkFlowMessage> datas);
    }
}

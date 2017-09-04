package com.giants3.hd.android.mvp.workflowmessage;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.WorkFlowMessage;

import java.util.List;

/**
 * Created by davidleen29 on 2017/6/25.
 */

public interface OrderItemWorkFlowMessageMVP {


    interface Model extends NewModel {


        void setOrderItem(String os_no, int itm);

        String getOs_no();

        int getItm();

    }

    interface Presenter extends NewPresenter<Viewer> {


        void setOrderItem(ErpOrderItem orderItem);

        void loadWorkFlowMessageByOrderItem();
    }

    interface Viewer extends NewViewer {


        void bindData(List<WorkFlowMessage> datas);
    }
}

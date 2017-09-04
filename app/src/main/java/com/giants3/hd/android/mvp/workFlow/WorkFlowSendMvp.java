package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.WorkFlowArea;

import java.util.List;

/**
 * 生产流程管理界面mvp 构造
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowSendMvp {

    interface Model extends NewModel {


        void setAvailableItems(List<ErpOrderItemProcess> stateList);


        List<ErpOrderItemProcess> getStateList();

        void setStateList(List<ErpOrderItemProcess> stateList);

        ErpOrderItemProcess getLastPickItem();

        void setLastPickItem(ErpOrderItemProcess lastPickItem);

        void setSendQty(int qty);

        int getSendQty();

        String getMemo();

        void setMemo(String memo);

        void setArea(WorkFlowArea area);

        WorkFlowArea getArea();

        List<WorkFlowArea> getAreas();
        void  setAreas(List<WorkFlowArea> areas);
    }

    interface Presenter extends NewPresenter<Viewer> {


        void pickOrderItem();


        void setInitDta(List<ErpOrderItemProcess> workFlowStates);

        void setPickItem(ErpOrderItemProcess newValue);

        void updateQty(int qty);

        void sendWorkFlow();

        void updateMemo(String memo);



        void pickWorkFlowArea();

        void setPickItem(WorkFlowArea newValue);
    }

    interface Viewer extends NewViewer {


        void doPickItem(ErpOrderItemProcess lastPickItem, List<ErpOrderItemProcess> availableItems);
        void doPickItem(WorkFlowArea lastPickItem, List<WorkFlowArea> availableItems);

        void bindPickItem(ErpOrderItemProcess newValue);

        void updateSendQty(int qty);

        void warnQtyInput(String s);

        void doOnSuccessSend();

        void bindPickItem(WorkFlowArea newValue);
    }

}

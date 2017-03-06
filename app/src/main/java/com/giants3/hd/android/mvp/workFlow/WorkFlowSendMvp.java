package com.giants3.hd.android.mvp.workFlow;

import android.os.Bundle;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.WorkFlowReport;

import java.util.List;

/**
 * 生产流程管理界面mvp 构造
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowSendMvp {

    interface Model extends NewModel {


        void setAvailableItems(List<OrderItemWorkFlowState> stateList);


        List<OrderItemWorkFlowState> getStateList();

        void setStateList(List<OrderItemWorkFlowState> stateList);

        OrderItemWorkFlowState getLastPickItem();

        void setLastPickItem(OrderItemWorkFlowState lastPickItem);

        void setSendQty(int qty);

        int getSendQty();

        String getMemo();

    }

    interface Presenter extends NewPresenter<Viewer> {


        void pickOrderItem();


        void setInitDta(List<OrderItemWorkFlowState> workFlowStates);

        void setPickItem(OrderItemWorkFlowState newValue);

        void updateQty(int qty);

        void sendWorkFlow();

    }

    interface Viewer extends NewViewer {


        void doPickItem(OrderItemWorkFlowState lastPickItem, List<OrderItemWorkFlowState> availableItems);

        void bindPickItem(OrderItemWorkFlowState newValue);

        void updateSendQty(int qty);

        void warnQtyInput(String s);

        void doOnSuccessSend();
    }

}

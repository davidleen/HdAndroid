package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowWorker;

import java.util.List;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListModel implements WorkFlowListMvp.Model {


    @Override
    public boolean canSendWorkFlow(int workFlowStep) {


        List<WorkFlowWorker> workFlowWorkers = SharedPreferencesHelper.getInitData().workFlowWorkers;

        for (WorkFlowWorker workFlow : workFlowWorkers) {
            if (workFlow.workFlowStep == workFlowStep && workFlow.send)

                return true;

        }


        return false;
    }


    @Override
    public boolean canReceiveWorkFlow(int workFlowStep) {
        List<WorkFlowWorker> workFlowWorkers = SharedPreferencesHelper.getInitData().workFlowWorkers;

        for (WorkFlowWorker workFlow : workFlowWorkers) {
            if (workFlow.workFlowStep == workFlowStep && workFlow.receive)

                return true;

        }


        return false;
    }




    @Override
    public void setSelectOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    OrderItem orderItem;

    @Override
    public OrderItem getSelectOrderItem() {
        return orderItem;
    }


}

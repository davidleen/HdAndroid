package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.WorkFlow;

import java.util.List;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListModel implements WorkFlowListMvp.Model {

    @Override
    public boolean canSendWorkFlow(int workFlowStep) {


        AUser user = SharedPreferencesHelper.getLoginUser();

        List<WorkFlow> workFlows = SharedPreferencesHelper.getInitData().workFlows;

        for (WorkFlow workFlow : workFlows) {
            if (workFlow.flowStep == workFlowStep && workFlow.userId == user.id)

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

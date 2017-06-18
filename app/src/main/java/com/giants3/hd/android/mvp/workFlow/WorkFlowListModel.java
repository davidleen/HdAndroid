package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowWorker;

import java.util.List;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListModel implements WorkFlowListMvp.Model {


    private List<OrderItemWorkMemo> orderItemWorkMemos;
    private List<ProductWorkMemo> productWorkMemos;

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
    public void setProductWorkMemo(List<ProductWorkMemo> productWorkMemos) {

        this.productWorkMemos = productWorkMemos;
    }

    @Override
    public void setOrderItemWorkMemos(List<OrderItemWorkMemo> orderItemWorkMemos) {

        this.orderItemWorkMemos = orderItemWorkMemos;
    }

    @Override
    public ProductWorkMemo getSelectProductMemo(int workFlowStep) {
        if(productWorkMemos==null) return null;

        for (ProductWorkMemo memo:productWorkMemos)
        {
            if(memo.workFlowStep==workFlowStep)
            {
                return memo;
            }
        }
        return null;
    }

    @Override
    public OrderItemWorkMemo getSelectOrderItemMemo(int workFlowStep) {
        if(orderItemWorkMemos==null) return null;

        for (OrderItemWorkMemo memo:orderItemWorkMemos)
        {
            if(memo.workFlowStep==workFlowStep)
            {
                return memo;
            }
        }


        return null;
    }


    @Override
    public void setSelectOrderItem(ErpOrderItem orderItem) {
        this.orderItem = orderItem;
    }

    ErpOrderItem orderItem;

    @Override
    public ErpOrderItem getSelectOrderItem() {
        return orderItem;
    }


}

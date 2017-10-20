package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.entity.WorkFlowWorker;
import com.giants3.hd.noEntity.ProductType;

import java.util.List;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListModel implements WorkFlowListMvp.Model {


    private List<OrderItemWorkMemo> orderItemWorkMemos;
    private List<ProductWorkMemo> productWorkMemos;

    @Override
    public boolean canSendWorkFlow(ErpWorkFlowReport workFlowStep) {


        List<WorkFlowWorker> workFlowWorkers = SharedPreferencesHelper.getInitData().workFlowWorkers;

        for (WorkFlowWorker workFlowWorker : workFlowWorkers) {

            if(workFlowWorker.send) {
                if (isWorkerForStep(workFlowStep, workFlowWorker)) return true;
            }

        }


        return false;
    }


    @Override
    public boolean canReceiveWorkFlow(ErpWorkFlowReport erpWorkFlowReport) {
        List<WorkFlowWorker> workFlowWorkers = SharedPreferencesHelper.getInitData().workFlowWorkers;

        for (WorkFlowWorker workFlowWorker : workFlowWorkers) {

            if( workFlowWorker.receive) {


                if (isWorkerForStep(erpWorkFlowReport, workFlowWorker)) return true;
            }





        }


        return false;
    }

    private boolean isWorkerForStep(ErpWorkFlowReport erpWorkFlowReport, WorkFlowWorker workFlowWorker) {
        if (workFlowWorker.workFlowStep == erpWorkFlowReport.workFlowStep  && workFlowWorker.produceType == erpWorkFlowReport.produceType) {

            if (erpWorkFlowReport.productType == ProductType.TYPE_NONE)
                return true;
            if (workFlowWorker.tie && (erpWorkFlowReport.productType & ProductType.TYPE_TIE) == ProductType.TYPE_TIE)
                return true;
            if (workFlowWorker.mu && (erpWorkFlowReport.productType & ProductType.TYPE_MU) == ProductType.TYPE_MU)
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

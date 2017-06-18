package com.giants3.hd.android.mvp.workflowmemo;

import com.giants3.hd.utils.entity.ErpWorkFlow;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements WorkFlowOrderItemMemoMVP.Model {

    private ErpWorkFlowOrderItem orderItem;
    private List<OrderItemWorkMemo> datas;
    private List<ProductWorkMemo> productWorkMemos;

    int selectStep= ErpWorkFlow.STEPS[1];

    @Override
    public void setOrderItem(ErpWorkFlowOrderItem orderItem) {


        this.orderItem = orderItem;
    }

    @Override
    public void setOrderItemWorkMemos(List<OrderItemWorkMemo> datas) {

        this.datas = datas;
    }


    @Override
    public void setProductWorkMemo(List<ProductWorkMemo> productWorkMemos) {

        this.productWorkMemos = productWorkMemos;
    }


    @Override
    public ProductWorkMemo getSelectProductWorkMemo() {
        if(productWorkMemos==null) return null;

        for (ProductWorkMemo memo:productWorkMemos)
        {
            if(memo.workFlowStep==selectStep)
            {
                return memo;
            }
        }
        return null;
    }

    @Override
    public OrderItemWorkMemo getSelectOrderItemWorkMemo() {

        if(datas==null) return null;

        for (OrderItemWorkMemo memo:datas)
        {
            if(memo.workFlowStep==selectStep)
            {
                return memo;
            }
        }
        return null;
    }

    @Override
    public ErpWorkFlowOrderItem getOrderItem() {
        return orderItem;
    }

    @Override
    public int getSelectStep() {
        return selectStep;
    }

    @Override
    public void setSelectWorkFlowStep(int workFlowStep) {
        selectStep=workFlowStep;
    }
}

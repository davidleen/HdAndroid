package com.giants3.hd.android.mvp.workflowmemo;

import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpWorkFlow;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.noEntity.WorkFlowMemoAuth;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements WorkFlowOrderItemMemoMVP.Model {

    private ErpOrderItem orderItem;
    private List<OrderItemWorkMemo> datas;
    private List<ProductWorkMemo> productWorkMemos;

    int selectStep = ErpWorkFlow.STEPS[1];
    private String productWorkFlwoMemo = "";
    private String orderItemWorkFlwoMemo = "";
    private List<WorkFlowMemoAuth> workFlowMemoAuths;

    @Override
    public void setOrderItem(ErpOrderItem orderItem) {


        this.orderItem = orderItem;
    }

    @Override
    public void setOrderItemWorkMemos(List<OrderItemWorkMemo> datas) {

        this.datas = datas;
        setSelectWorkFlowStep(selectStep);
    }


    @Override
    public void setProductWorkMemo(List<ProductWorkMemo> productWorkMemos) {

        this.productWorkMemos = productWorkMemos;
        setSelectWorkFlowStep(selectStep);
    }


    @Override
    public ProductWorkMemo getSelectProductWorkMemo() {
        if (productWorkMemos == null) return null;

        for (ProductWorkMemo memo : productWorkMemos) {
            if (memo.workFlowStep == selectStep) {
                return memo;
            }
        }
        return null;
    }

    @Override
    public OrderItemWorkMemo getSelectOrderItemWorkMemo() {

        if (datas == null) return null;

        for (OrderItemWorkMemo memo : datas) {
            if (memo.workFlowStep == selectStep) {
                return memo;
            }
        }
        return null;
    }

    @Override
    public ErpOrderItem getOrderItem() {
        return orderItem;
    }

    @Override
    public int getSelectStep() {
        return selectStep;
    }

    @Override
    public void setSelectWorkFlowStep(int workFlowStep) {


        selectStep = workFlowStep;
        ProductWorkMemo productWorkMemo = getSelectProductWorkMemo();
        OrderItemWorkMemo orderItemWorkMemo = getSelectOrderItemWorkMemo();


        productWorkFlwoMemo = productWorkMemo == null ? "" : productWorkMemo.memo;
        orderItemWorkFlwoMemo = orderItemWorkMemo == null ? "" : orderItemWorkMemo.memo;
    }

    @Override
    public void setNewWorkFlowMemo(String productWorkFlwoMemo, String orderItemWorkFlwoMemo) {

        this.productWorkFlwoMemo = productWorkFlwoMemo;
        this.orderItemWorkFlwoMemo = orderItemWorkFlwoMemo;
    }

    @Override
    public boolean hasNewWorkFlowMemo() {


        ProductWorkMemo productWorkMemo = getSelectProductWorkMemo();
        OrderItemWorkMemo orderItemWorkMemo = getSelectOrderItemWorkMemo();

        String oldProductWorkFlwoMemo = productWorkMemo == null ? "" : productWorkMemo.memo;
        String oldOrderItemWorkMemo = orderItemWorkMemo == null ? "" : orderItemWorkMemo.memo;
        if (oldProductWorkFlwoMemo.equals(productWorkFlwoMemo) && oldOrderItemWorkMemo.equals(orderItemWorkFlwoMemo)) {
            return false;

        }

        return true;


    }

    @Override
    public void setWorkFlowMemoAuth(List<WorkFlowMemoAuth> workFlowMemoAuths) {

        this.workFlowMemoAuths = workFlowMemoAuths;
    }

    @Override
    public String[] getNewMemoString() {
        return new String[]{productWorkFlwoMemo, orderItemWorkFlwoMemo};
    }

    @Override
    public WorkFlowMemoAuth getSelectWorkFlowMemoAuth() {

        if (workFlowMemoAuths == null) return null;

        for (WorkFlowMemoAuth auth : workFlowMemoAuths) {
            if (auth.workFlowStep == selectStep) {
                return auth;
            }
        }
        return null;

    }
}

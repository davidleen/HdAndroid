package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowSendModel implements WorkFlowSendMvp.Model {

    private List<OrderItemWorkFlowState> stateList;
    private OrderItemWorkFlowState lastPickItem;
    private int qty;
    private String memo;

    @Override
    public void setAvailableItems(List<OrderItemWorkFlowState> stateList) {


        this.stateList = stateList;
    }

    @Override
    public List<OrderItemWorkFlowState> getStateList() {
        return stateList;
    }
    @Override
    public void setStateList(List<OrderItemWorkFlowState> stateList) {
        this.stateList = stateList;
    }
    @Override
    public OrderItemWorkFlowState getLastPickItem() {
        return lastPickItem;
    }
    @Override
    public void setLastPickItem(OrderItemWorkFlowState lastPickItem) {
        this.lastPickItem = lastPickItem;
    }

    @Override
    public void setSendQty(int qty) {

        this.qty = qty;
    }

    @Override
    public int getSendQty() {
        return qty;
    }

    @Override
    public String getMemo() {
        return memo;
    }
}

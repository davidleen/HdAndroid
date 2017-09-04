package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.WorkFlowArea;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowSendModel implements WorkFlowSendMvp.Model {

    private List<ErpOrderItemProcess> stateList;
    private ErpOrderItemProcess lastPickItem;
    private int qty;
    private String memo;
    private WorkFlowArea area;
    private List<WorkFlowArea> areas;

    @Override
    public void setAvailableItems(List<ErpOrderItemProcess> stateList) {


        this.stateList = stateList;
    }

    @Override
    public List<ErpOrderItemProcess> getStateList() {
        return stateList;
    }

    @Override
    public void setStateList(List<ErpOrderItemProcess> stateList) {
        this.stateList = stateList;
    }

    @Override
    public ErpOrderItemProcess getLastPickItem() {
        return lastPickItem;
    }

    @Override
    public void setLastPickItem(ErpOrderItemProcess lastPickItem) {
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

    @Override
    public void setMemo(String memo) {
        this.memo=memo;
    }

    @Override
    public void setArea(WorkFlowArea area) {

        this.area = area;
    }

    @Override
    public WorkFlowArea getArea() {
        return area;
    }

    @Override
    public List<WorkFlowArea> getAreas() {
        return areas;
    }


    @Override
    public void setAreas(List<WorkFlowArea> areas) {
        this.areas=areas;
    }
}

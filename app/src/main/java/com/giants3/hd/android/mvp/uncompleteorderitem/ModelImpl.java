package com.giants3.hd.android.mvp.uncompleteorderitem;

import android.util.SparseArray;

import com.giants3.hd.android.mvp.PageModelImpl;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl extends PageModelImpl<ErpOrderItem> implements UnCompleteOrderItemMVP.Model {


    private List<ErpOrderItem> datas;
    private int flowStep = -1;


    SparseArray<List<ErpOrderItem>> erpOrderItemSparseArray = new SparseArray<>();
    SparseArray<RemoteData<ErpOrderItem>> remoteDataSparseArray = new SparseArray<>();

    @Override
    public void setData(List<ErpOrderItem> datas) {

        this.datas = datas;
    }

    @Override
    public List<ErpOrderItem> getFilterData() {


        return erpOrderItemSparseArray.get(flowStep);


    }

    @Override
    public RemoteData<ErpOrderItem> getRemoteData() {


        return remoteDataSparseArray.get(flowStep);

    }

    @Override
    public int getSelectedStep() {
        return flowStep;
    }

    @Override
    public void setSelectedStep(int flowStep) {

        this.flowStep = flowStep;
    }

    @Override
    public void setRemoteData(RemoteData<ErpOrderItem> remoteData, int workFlowStep) {
        List<ErpOrderItem> erpOrderItems = erpOrderItemSparseArray.get(workFlowStep);
        if (erpOrderItems == null) erpOrderItems = new ArrayList<>();
        if (remoteData.pageIndex == 0) {
            //第一页
            erpOrderItems.clear();
        }

        erpOrderItems.addAll(remoteData.datas);
        erpOrderItemSparseArray.put(workFlowStep, erpOrderItems);

        remoteDataSparseArray.put(workFlowStep, remoteData);


    }

    @Override
    public boolean hasNextPage() {

        RemoteData<ErpOrderItem> remoteData = remoteDataSparseArray.get(flowStep);

        return remoteData != null && remoteData.pageIndex + 1 < remoteData.pageCount;

    }
}

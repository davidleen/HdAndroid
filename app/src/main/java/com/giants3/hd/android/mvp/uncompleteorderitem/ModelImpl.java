package com.giants3.hd.android.mvp.uncompleteorderitem;

import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.utils.entity.ErpOrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements UnCompleteOrderItemMVP.Model {


    private List<ErpOrderItem> datas;
    private int flowStep=-1;

    @Override
    public void setData(List<ErpOrderItem> datas) {

        this.datas = datas;
    }

    @Override
    public List<ErpOrderItem> getFilterData() {


        List<ErpOrderItem> result = new ArrayList<>();
        if (datas != null) {


            if (flowStep == -1) result.addAll(datas);
            else

                for (ErpOrderItem orderItem : datas) {

                    if (orderItem.maxWorkFlowStep == flowStep) {
                        result.add(orderItem);
                    }
                }
        }

        return result;

    }

    @Override
    public int getSelectedStep() {
        return flowStep;
    }

    @Override
    public void setSelectedStep(int flowStep) {

        this.flowStep = flowStep;
    }
}

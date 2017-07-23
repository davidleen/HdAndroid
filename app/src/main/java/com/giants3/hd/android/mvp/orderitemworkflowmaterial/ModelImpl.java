package com.giants3.hd.android.mvp.orderitemworkflowmaterial;

import com.giants3.hd.android.mvp.workflowmemo.WorkFlowOrderItemMemoMVP;
import com.giants3.hd.utils.entity.ErpWorkFlow;
import com.giants3.hd.utils.entity.ErpWorkFlowReport;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;


import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements MVP.Model {


    private String osNo;
    private int itm;
    private String code;

    @Override
    public void setWorkFlowInfo(String osNo,int itm ,String  code) {


        this.osNo = osNo;
        this.itm = itm;
        this.code = code;
    }


    @Override
    public String getOsNo() {
        return osNo;
    }

    @Override
    public int getItm() {
        return itm;
    }

    @Override
    public String getCode() {
        return code;
    }
}

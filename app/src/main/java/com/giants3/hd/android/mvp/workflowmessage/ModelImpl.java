package com.giants3.hd.android.mvp.workflowmessage;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements OrderItemWorkFlowMessageMVP.Model {


    private String os_no;
    private int itm;

    @Override
    public void setOrderItem(String os_no, int itm) {

        this.os_no = os_no;
        this.itm = itm;
    }

    public String getOs_no() {
        return os_no;
    }

    public int getItm() {
        return itm;
    }
}

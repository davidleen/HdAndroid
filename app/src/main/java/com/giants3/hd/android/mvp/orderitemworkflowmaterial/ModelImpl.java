package com.giants3.hd.android.mvp.orderitemworkflowmaterial;

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

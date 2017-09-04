package com.giants3.hd.entity;

/**
 * 订单生产备注
 * <p/>
 * table ：TF_POS   TF_POS_Z
 * Created by david on 2016/2/28.
 */

public class OrderItemWorkMemo {


    public long id;

    public String osNo;
    public int itm;

    public String productName;
    public String pVersion;
    public String workFlowCode;
    public String workFlowName;
    public int workFlowStep;


    public String memo;


    /**
     * 是否已经审核
     */
    public  boolean checked;




    public long lastModifierId;
    public String lastModifierName;
    public String lastModifyTimeString;
    public  long  lastModifyTime;



    public long lastCheckerId;
    public String lastCheckerName;
    public String lastCheckTimeString;
    public  long  lastCheckTime;

}

package com.giants3.hd.utils.entity_erp;

/**
 * 攜帶流程信息的訂單狀態數據
 * Created by davidleen29 on 2017/6/10.
 */
public class ErpWorkFlowOrderItem {


    /**
     * 订单的编号
     */

    public String os_no;
    /**
     * 序号
     */
    public int itm;
    /**
     * 客号
     */
    public String bat_no;

    /**
     * 货号
     */
    public String prd_no;


    public
     String prd_name;


    public String pversion;

    public String id_no;

    public  int qty;


    public  float amt;
    /**
     * 訂單狀態碼
     */
    public int workFlowState;

    /**
     * 訂單狀態值
     */
    public String workFlowDescribe;


}

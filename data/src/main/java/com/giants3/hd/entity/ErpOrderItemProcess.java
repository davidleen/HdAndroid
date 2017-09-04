package com.giants3.hd.entity;


/**
 * 订单生产流程
 * Created by davidleen29 on 2017/5/8.
 */

public class ErpOrderItemProcess {

    public long id;
    /**
     * 排产日期
     */
    public String moDd;
    /**
     * 单号
     */
    public String moNo;

    /**
     * 预开工日期
     */
    public String staDd;

    /**
     * 预完工日期
     */
    public String endDd;
    /**
     * 受订单号
     */
    public String osNo;
    /**
     * EST_ITM
     */
    public int itm;
    /**
     * MRP_NO
     */
    public String mrpNo;
    /**
     * MO_NO_ADD
     */
    public String prdNo;

    /**
     * 带版本号的货号
     */
    public String pVersion;
    /**
     * 带版本号的货号
     */
    public String idNo;
    /**
     * 数量
     */
    public int qty;

    /**
     * 订单数量
     */
    public int orderQty;


    /**
     * 生产厂家
     */
    public String jgh;
    /**
     * 生产属性
     */
    public String scsx;


    /**
     * 装箱数， 在出库阶段需要数量处理
     */
    public int so_zxs;

    public String  currentWorkFlowCode;
    public String nextWorkFlowCode;

    public int  currentWorkFlowStep;
    public int nextWorkFlowStep;

    public String  currentWorkFlowName;
    public String nextWorkFlowName;



    public String photoUrl;
    public String photoThumb;


    public int unSendQty;
    public int sendingQty;
    public int sentQty;


    @Override
    public String toString() {
        return mrpNo;
    }
}

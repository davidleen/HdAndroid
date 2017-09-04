package com.giants3.hd.entity_erp;

/**
 *流程的物料数据
 * Created by davidleen29 on 2017/6/25.
 */
public class WorkFlowMaterial {



    public String mo_dd;
    public String mo_no;
    public String prd_no;
    public String prd_name;
    public String prd_mark;
    public String rem;


    /**
     * 应领量
     */
    public  int qty_rsv;
    /**
     * 已领用量
     */
    public  int qty;
    /**
     * 单位用量
     */
    public  float qty_std;

    public String os_no;
    public String mrp_no;
    public String real_prd_name;
    public int itm;

    public String  ut;
}

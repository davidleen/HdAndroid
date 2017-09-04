package com.giants3.hd.entity;

/**
 *
 * erp 订单信息
 *
 * table ：MF_POS
 * Created by david on 2016/2/28.
 */

public class ErpOrder {

    /**
     * 合同日期
     */
    public String os_dd;

    /**
     * 合同编号
     */
    public String os_no;
    /**
     * 客户名称
     */
    public String cus_no;

    /**
     * 客户订单号
     */
    public String cus_os_no;

    /**
     * 业务员
     */
    public String sal_no;


    /**
     * 业务中文名
     */
    public String sal_name;
    /**
     * 预计交期
     */
    public String est_dd;

    /**
     * 生产交期
     */
    public String so_data;

    /**
     * 备注
     */
    public String rem;
}

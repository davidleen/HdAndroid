package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * 订单表细项目  额外附加数据存在本系统的
 * <p>
 * 有这个数据， 订单是保存到本系统中（订单是引用自erp）
 */

public class OrderItem implements Serializable {


    public long id;


    //订单单号

    public String osNo;

    //订单序号

    public int itm;


    /**
     * 验货日期
     */
    public String verifyDate;

    /**
     * 出柜日期
     */
    public String sendDate;

    /**
     * 包装信息
     */

    public String packageInfo;


    /**
     * 唛头
     */
    public String maitou;


    /**
     * 挂钩说明
     */
    public String guagou;


    //系统数据 附加，方便报表生成
    public String batNo;
    public int qty;
    public String prdNo;
    public String pVersion;

    public String thumbnail;
    public String url;
    public String ut;


    /**
     * 产品进度描述
     */
    public String workFlowDescribe;


    /**
     * 关联订单生产流程id
     */

    public long orderWorkFlowId;
}

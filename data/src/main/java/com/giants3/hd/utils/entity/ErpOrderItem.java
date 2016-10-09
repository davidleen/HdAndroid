package com.giants3.hd.utils.entity;

/**
 * erp 订单货款信息
 * <p/>
 * table ：TF_POS   TF_POS_Z
 * Created by david on 2016/2/28.
 */

public class ErpOrderItem {

    public long id;
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

    /**
     * 货号
     */
    public String prd_name            ;
    /**
     * 配方号
     */
    public String id_no;

    /**
     * 单位 这个属性从报价系统读取。
     */
    public String ut;

    /**
     * 单价
     */
    public float up;


    /**
     * 数量
     */
    public int qty;
    /**
     * 金额
     */
    public float amt;

    /**
     * 箱数
     * TF_POS_Z
     */
    public int htxs;

    /**
     * 每箱数
     * TF_POS_Z
     */
    public int so_zxs;
    /**
     * 箱规
     * TF_POS_Z
     */
    public String khxg;

    /**
     * 立方数
     * TF_POS_Z
     */
    public float xgtj;
    /**
     * 总立方数
     * TF_POS_Z
     */
    public float zxgtj;
    /**
     * 产品尺寸
     * TF_POS_Z
     */
    public String hpgg;
    /**
     * 图片路径
     */
    public String url;



    /**
     * 当前生产流程
     */
    public String currentWorkFlow;
    /**
     * 当前生产流程
     */
    public int currentWorkStep;


    /**
     * 当前生产状态  0 正常  1 已发送  2 已接收 3 已审核=0进入下一个流程
     */
    public int currentWorkState;

    /**
     * 订单当前数量    订单递交下流程 可能没有全部提交下去（特殊需求）
     */
    public int tranQty;


    @Override
    public String toString() {


        return os_no +"  "+ prd_name;
    }
}

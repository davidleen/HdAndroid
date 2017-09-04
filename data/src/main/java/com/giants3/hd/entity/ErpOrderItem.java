package com.giants3.hd.entity;

/**
 * erp 订单货款信息
 * <p/>
 * table ：TF_POS   TF_POS_Z
 * Created by david on 2016/2/28.
 */

public class ErpOrderItem {


    /**
     * 订单款项编号
     */
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



    public long productId;
    /**
     * 货号
     */
    public String prd_name            ;
    /**
     * 配方号
     */
    public String id_no;

    /**
     * 单位
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
    public String thumbnail;
    /**
     * 图片路径
     */
    public String url;


    public long  photoUpdateTime;
    /**
     * 产品配方号
     */
    public String pVersion;


    /**
     * 包装附件列表  由产品信息关联过来。
     */
    public String packAttaches;
    /**
     * 设定好的产品生产类型 0 未设定 1 自制  2 外购
     */
    public int produceType;


    /**
     * 生产交期
     */
    public String so_data;

    /**
     * 合同日期
     */
    public String os_dd;



    /**
     * 訂單狀態碼
     */
    public int workFlowState;
    /**
     * 货款流程步骤
     */
    public String maxWorkFlowCode;
    /**
     * 货款流程步骤
     */
    public String maxWorkFlowName;
    /**
     * 货款流程步骤
     */
    public int maxWorkFlowStep;

    /**
     * 訂單狀態值
     */
    public String workFlowDescribe;

    /**
     *
     * 客户代号
     */
    public String cus_no;


    public String getOs_no() {
        return os_no;
    }

    public void setOs_no(String os_no) {
        this.os_no = os_no;
    }

    public int getItm() {
        return itm;
    }

    public void setItm(int itm) {
        this.itm = itm;
    }

    public String getBat_no() {
        return bat_no;
    }

    public void setBat_no(String bat_no) {
        this.bat_no = bat_no;
    }

    public String getPrd_no() {
        return prd_no;
    }

    public void setPrd_no(String prd_no) {
        this.prd_no = prd_no;
    }

    public String getPrd_name() {
        return prd_name;
    }

    public void setPrd_name(String prd_name) {
        this.prd_name = prd_name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }

    public float getUp() {
        return up;
    }

    public void setUp(float up) {
        this.up = up;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public int getHtxs() {
        return htxs;
    }

    public void setHtxs(int htxs) {
        this.htxs = htxs;
    }

    public int getSo_zxs() {
        return so_zxs;
    }

    public void setSo_zxs(int so_zxs) {
        this.so_zxs = so_zxs;
    }

    public String getKhxg() {
        return khxg;
    }

    public void setKhxg(String khxg) {
        this.khxg = khxg;
    }

    public float getXgtj() {
        return xgtj;
    }

    public void setXgtj(float xgtj) {
        this.xgtj = xgtj;
    }

    public float getZxgtj() {
        return zxgtj;
    }

    public void setZxgtj(float zxgtj) {
        this.zxgtj = zxgtj;
    }

    public String getHpgg() {
        return hpgg;
    }

    public void setHpgg(String hpgg) {
        this.hpgg = hpgg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * 验货日期
     */
    public String verifyDate;

    /**
     *  出柜日期
     */
    public String sendDate;

    /**
     * 包装信息
     */
    public String packageInfo;


    /**
     * 唛头
     */
    public String  maitou;


    /**
     * 挂钩说明
     */
    public String   guagou ;








}

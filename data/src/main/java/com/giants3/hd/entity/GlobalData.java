package com.giants3.hd.entity;


import java.io.Serializable;

/**
 * 配置数据  提供一些常量值
 */

public class GlobalData implements Serializable {


    public long id;
    /**
     * 是稀释剂单价
     */
    public float price_of_diluent=9f;


    /**
     * 汇率比值
     */
    public  float exportRate=6;

    /**
     * 成本利润比值。
     */

    public float cost_price_ratio=0.65f;

    /**
     * 附加值
     *
     */

    public float  addition=0.15f;


    /**
     *出口运费   元/M3
     */

    public float price_of_export=95;


    /**
     * 稀释剂冗余量， 用为洗刷枪笔
     */
    public float extra_ratio_of_diluent=0.1f;


    /**
     * 修理单位工资  单位 元/立方米
     */
    public float repairPrice=25f;


    /**
     * 咸康产品管理费用
     */
    public float manageRatioXK=0.1f;


    /**
     * 普通产品管理费用
     */
    public float manageRatioNormal=0.15f;

    //public static GlobalData getInstance() {
    //    return instance;
    //}


    /**
     * 外厂产品管理费用
     */
    public float manageRatioForeign=0.15f;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GlobalData)) return false;

        GlobalData that = (GlobalData) o;

        if (id != that.id) return false;
        if (Float.compare(that.price_of_diluent, price_of_diluent) != 0) return false;
        if (Float.compare(that.exportRate, exportRate) != 0) return false;
        if (Float.compare(that.cost_price_ratio, cost_price_ratio) != 0) return false;
        if (Float.compare(that.addition, addition) != 0) return false;
        if (Float.compare(that.price_of_export, price_of_export) != 0) return false;
        if (Float.compare(that.extra_ratio_of_diluent, extra_ratio_of_diluent) != 0) return false;
        if (Float.compare(that.repairPrice, repairPrice) != 0) return false;
        if (Float.compare(that.manageRatioXK, manageRatioXK) != 0) return false;
        if (Float.compare(that.manageRatioForeign, manageRatioForeign) != 0) return false;
        return Float.compare(that.manageRatioNormal, manageRatioNormal) == 0;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (price_of_diluent != +0.0f ? Float.floatToIntBits(price_of_diluent) : 0);
        result = 31 * result + (exportRate != +0.0f ? Float.floatToIntBits(exportRate) : 0);
        result = 31 * result + (cost_price_ratio != +0.0f ? Float.floatToIntBits(cost_price_ratio) : 0);
        result = 31 * result + (addition != +0.0f ? Float.floatToIntBits(addition) : 0);
        result = 31 * result + (price_of_export != +0.0f ? Float.floatToIntBits(price_of_export) : 0);
        result = 31 * result + (extra_ratio_of_diluent != +0.0f ? Float.floatToIntBits(extra_ratio_of_diluent) : 0);
        result = 31 * result + (repairPrice != +0.0f ? Float.floatToIntBits(repairPrice) : 0);
        result = 31 * result + (manageRatioXK != +0.0f ? Float.floatToIntBits(manageRatioXK) : 0);
        result = 31 * result + (manageRatioNormal != +0.0f ? Float.floatToIntBits(manageRatioNormal) : 0);
        result = 31 * result + (manageRatioForeign != +0.0f ? Float.floatToIntBits(manageRatioForeign) : 0);
        return result;
    }
}

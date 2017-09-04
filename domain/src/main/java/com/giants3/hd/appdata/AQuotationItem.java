package com.giants3.hd.appdata;

import java.io.Serializable;

/**
 * 移动端使用的数据 报价子项
 * Created by david on 2016/1/2.
 */
public class AQuotationItem implements Serializable {


    public long id;


    /**
     *
     */

    public long productId = -1;


    /**
     *
     */

    public String productName;


    public String pVersion;


    public int inBoxCount;


    /**
     * 包装箱数
     */

    public int packQuantity;


    /**
     * 箱子规格
     */

    public String packageSize;


    /**
     * 单位
     */

    public String unit;

    /**
     * 成本价
     */

    public float cost;


    /**
     * 单价
     */

    public float price;


    /**
     * 立方数
     */

    public float volumeSize;
    /**
     * 净重
     */

    public float weight;


    /**
     * 货品规格
     */

    public String spec;


    /**
     * 材质
     */

    public String constitute;


    /**
     * 镜面尺寸
     */

    public String mirrorSize;


    public long quotationId;


    public String memo;


}

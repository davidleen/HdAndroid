package com.giants3.hd.entity;

/**
 * 材料类型（统计方面使用）
 */

public class MaterialType {




    /**
     *
     */

    public long id;
    /**
     * 类型编号
     */

    public int typeId;

    /**
     * 类型名称
     */

    public String typeName;


    @Override
    public String toString() {
        return  "  ["+typeId+"]    "+typeName;
    }
}

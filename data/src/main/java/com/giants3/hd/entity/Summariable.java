package com.giants3.hd.entity;

/**
 * 接口  支持材料汇总的对象 应该实现该接口
 */
public interface Summariable {


    /**
     * 汇总类型
     * @return
     */
    public int getType();

    /**
     * 汇总的值
     * @return
     */
    public float getAmount();
}

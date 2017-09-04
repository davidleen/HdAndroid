package com.giants3.hd.noEntity;

/** 生产流程备注权限
 * Created by davidleen29 on 2017/7/1.
 */

public class WorkFlowMemoAuth {

    /**
     * 对应流程
     */
    public int workFlowStep;
    /**
     * 是否有审核 撤销审核的权限
     */
    public boolean  checkable=false;
    /**
     * 是否有修改的权限
     */
    public  boolean modifiable=false;
}

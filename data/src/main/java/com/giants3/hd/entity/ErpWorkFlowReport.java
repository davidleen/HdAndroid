package com.giants3.hd.entity;


/**流程报告数据
 * Created by davidleen29 on 2017/2/28.
 */

public class ErpWorkFlowReport {


    public long id;
    public String workFlowCode;
    public int workFlowStep;
    public String workFlowName;
    public String osNo;
    public String prdNo;
    public int itm;
    public String pVersion;
    /**
     * 完成百分比。
     */
    public float percentage;


    public int produceType;
    public String produceTypeName;


}

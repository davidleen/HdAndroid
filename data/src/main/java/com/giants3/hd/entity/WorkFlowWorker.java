package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * 流程节点工作人员定位
 * <p>
 * Created by davidleen29 on 2015/7/1.
 */

public class WorkFlowWorker implements Serializable {

    /**
     * 单位 id
     */

    public long id;


    /**
     * 流程
     */
    public String workFlowCode;
    /**
     * 流程
     */
    public long workFlowStep;

    /**
     * 流程id
     */
    public String workFlowName;
    /**
     * 用户id
     */
    public long userId;
    /**
     * 用户name
     */
    public String userName;

    public boolean tie;
    public boolean mu;
    public boolean pu;


    public boolean receive;

    public boolean send;


}

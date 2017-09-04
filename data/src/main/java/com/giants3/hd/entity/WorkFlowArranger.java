package com.giants3.hd.entity;

import java.io.Serializable;

/**
 * 流程节点工作人员定位
 * <p>
 * Created by davidleen29 on 2015/7/1.
 */

public class WorkFlowArranger implements Serializable {

    /**
     * 单位 id
     */

    public long id;


    /**
     * 用户id
     */
    public long userId;
    /**
     * 用户name
     */
    public String userName;

    /**
     * 是否可以排自制
     */
    public boolean selfMade;

    /**
     * 是否可以排外厂
     */
    public boolean purchase;


}

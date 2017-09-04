package com.giants3.hd.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产流程
 * Created by davidleen29 on 2016/9/1.
 */

public class WorkFlow {


    //流程汇总
    public static final int STEP_GROUP = 2;
    //流程正常
    public static final int STEP_NORMAL = 1;
    //流程跳过
    public static final int STEP_SKIP = 0;
    public static final String STEP_1 = "白胚制作";


    public static final int FINAL_STEP = 3000;


    public long id;


    public String name;

    /**
     * 当前序号
     */
    public int flowStep;


    //流程负责人相关数据
    public long userId;
    public String userName;
    public String userCName;
    public String userCode;


    //审核人相关数据
    public long checkerId;
    public String checkerName;
    public String checkerCName;
    public String checkerCode;


    /**
     * 是否自制关联流程
     */
    public boolean isSelfMade;
    /**
     * 是否外购关联流程
     */
    public boolean isPurchased;


    public static final List<WorkFlow> initWorkFlowData() {

        ArrayList<WorkFlow> workFlows = new ArrayList<>();
        WorkFlow workFlow;
        workFlow = new WorkFlow();
        workFlow.name = STEP_1;
        workFlow.flowStep = 1101;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);


        workFlow = new WorkFlow();
        workFlow.name = "白胚仓";
        workFlow.flowStep = 1102;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);

        workFlow = new WorkFlow();
        workFlow.name = "喷塑";
        workFlow.flowStep = 1103;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);

        workFlow = new WorkFlow();
        workFlow.name = "颜色";
        workFlow.flowStep = 1104;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);


        workFlow = new WorkFlow();
        workFlow.name = "组装";
        workFlow.flowStep = 1105;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);


        workFlow = new WorkFlow();
        workFlow.name = "验收";
        workFlow.flowStep = 1106;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);

        workFlow = new WorkFlow();
        workFlow.name = "包装";
        workFlow.flowStep = 1107;
        workFlow.isPurchased = false;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);

        workFlow = new WorkFlow();
        workFlow.name = "成品制作";
        workFlow.flowStep = 1200;
        workFlow.isPurchased = true;
        workFlow.isSelfMade = false;
        workFlows.add(workFlow);

        workFlow = new WorkFlow();
        workFlow.name = "成品仓";
        workFlow.flowStep = 2000;
        workFlow.isPurchased = true;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);


        workFlow = new WorkFlow();
        workFlow.name = "出库";
        workFlow.flowStep = FINAL_STEP;
        workFlow.isPurchased = true;
        workFlow.isSelfMade = true;
        workFlows.add(workFlow);
        return workFlows;


    }


    @Override
    public String toString() {
        return "[" + flowStep + "]" + name;
    }

}

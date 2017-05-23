package com.giants3.hd.utils.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产流程
 * Created by davidleen29 on 2016/9/1.
 */
public class ErpWorkFlow {


    public static String[] CODES = new String[]{"A",  "D", "B","C",""};

    public static String[] NAMES = new String[]{"白胚", "颜色", "组装", "包装","成品仓"};
    public static final int FIRST_STEP = 1000;
    public static final int LAST_STEP = 5000;
    public static int[] STEPS = new int[]{FIRST_STEP, 2000, 3000, 4000, LAST_STEP};


    public ErpWorkFlow() {

    }

    public String code;
    public String name;
    public int step;



    public static List<ErpWorkFlow> WorkFlows;

    static {
        int count = CODES.length;
        WorkFlows = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {

            ErpWorkFlow workFlow;
            workFlow = new ErpWorkFlow();
            workFlow.code = CODES[i];
            workFlow.name = NAMES[i];
            workFlow.step = STEPS[i];
            WorkFlows.add(workFlow);
        }

    }


    @Override
    public String toString() {
        return code + "    " + name;
    }

    public static ErpWorkFlow findByStep(int flowStep) {

        for (ErpWorkFlow workFLow :
                WorkFlows) {
            if (workFLow.step == flowStep) return workFLow;
        }
        return null;
    } public static ErpWorkFlow findByCode(String flowCode) {

        for (ErpWorkFlow workFLow :
                WorkFlows) {
            if (workFLow.code.equals(flowCode)) return workFLow;
        }
        return null;
    }


    public static int findIndexByCode(String code) {

        final int length = CODES.length;
        int index= length -1;

        for (int i = 0; i < length; i++) {
            if(CODES[i].equals(code))
                return i;
        }
        return index;
    }
}

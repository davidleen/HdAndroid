package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产流程
 * Created by davidleen29 on 2016/9/1.
 */
public class ErpWorkFlow {


    public static final String FIRST_STEP_CODE = "S";
    public static final String SECOND_STEP_CODE = "A";
    public static final String CODE_ZUZHUANG = "B";
    //生产完成状态码
    public static final int STATE_COMPLETE = 99;
    //生产进行流程状态码
    public static final int STATE_WORKING= 9;


    public static final int PICTURE_COUNT = 3;
    public static final String CODE_CHENGPIN = "E";
    public static final String CODE_YANSE = "D";
    public static final String CODE_MU = "M";
    public static final String CODE_TIE = "T";
    public static final String CODE_BAOZHUANG = "C";
    public static String[] CODES = new String[]{FIRST_STEP_CODE, SECOND_STEP_CODE, CODE_YANSE, CODE_BAOZHUANG, CODE_CHENGPIN};


    public static String NAME_ZUZHUANG="组装";
    public static String NAME_BAOZHUANG="包装";
    public static String[] NAMES = new String[]{"胚体加工","白胚", "颜色",  NAME_ZUZHUANG+NAME_BAOZHUANG ,"成品仓"};
    public static final int FIRST_STEP = 1000;
    public static final int LAST_STEP = 6000;
    public static int[] STEPS = new int[]{FIRST_STEP,2000, 3000,   5000, LAST_STEP};


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

        if(StringUtils.isEmpty(code)) return "";
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

    public static int  findPrevious(int flowStep) {

        final int length = STEPS.length;


        for (int i = 0; i < length; i++) {
            if(STEPS[i]==flowStep)
                return STEPS[i-1];
        }
        return  -1;

    }
}

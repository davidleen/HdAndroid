package com.giants3.hd.noEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2017/6/18.
 */
public class CompanyPosition {
    public static List<CompanyPosition> POSITIONS;

    private static final int FACTORY_DIRECTOR_CODE = 0xf0000001;

    private static final String FACTORY_DIRECTOR_NAME = "厂长";

    private static final int WORK_FLOW_MANAGER_CODE = 0xf0000002;

    private static final String WORK_FLOW_MANAGER_NAME = "生产管理员";


    /**
     * 生产管理设置流程备注权限
     */
    public static final int PRIVILAGE_WORKFLOW_MEMO=0xf0000000;




    static {
        POSITIONS = new ArrayList<>();

        CompanyPosition e = new CompanyPosition();
        e.position = 0;
        e.positionName = "";
        POSITIONS.add(e);


        e = new CompanyPosition();
        e.position = FACTORY_DIRECTOR_CODE;
        e.positionName = FACTORY_DIRECTOR_NAME;
        POSITIONS.add(e);

        e = new CompanyPosition();
        e.position = WORK_FLOW_MANAGER_CODE;
        e.positionName = WORK_FLOW_MANAGER_NAME;
        POSITIONS.add(e);

    }

    public int position;
    public String positionName;


    @Override
    public String toString() {

        return positionName;
    }
}

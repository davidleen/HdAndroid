package com.giants3.hd.utils.noEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2017/6/18.
 */
public class CompanyPosition {
    public static List<CompanyPosition> POSITIONS;

    private static final int FACTORY_DIRECTOR_CODE = 1;

    private static final String FACTORY_DIRECTOR_NAME = "厂长";

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

    }

    public int position;
    public String positionName;


    @Override
    public String toString() {

        return positionName;
    }
}

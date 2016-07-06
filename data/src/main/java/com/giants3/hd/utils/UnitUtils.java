package com.giants3.hd.utils;

/**
 *
 * 单位换算功能类
 * Created by davidleen29 on 2015/8/19.
 */
public class UnitUtils {

    /**
     * 厘米与英寸转换
     */
    public static final float CM_TO_FEET =0.3937f;
    public static final float FEET_TO_CM =1/ CM_TO_FEET;



    public static final float INCH_TO_M =0.3048f;
    public static final float M_TO_INCH =1/INCH_TO_M;
    public static float RATIO_KG_TO_POUND=2.205f;


    public static float  kgToPound(float kg)
    {

        return FloatHelper.scale(kg*RATIO_KG_TO_POUND);

    }

    public static float inchToCm(float inchValue) {
        return FloatHelper.scale( inchValue* FEET_TO_CM);
    }

    public static float cmToInch(float cmValue) {
        return FloatHelper.scale( cmValue * CM_TO_FEET);
    }



    public static float volumeMeterToInch(float meterVolume)
    {

        return FloatHelper.scale(meterVolume*M_TO_INCH*M_TO_INCH*M_TO_INCH);


    }
}

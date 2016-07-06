package com.giants3.hd.utils;

/** 字符串的功能类。
 * Created by david on 2016/1/9.
 */
public class StringUtils {

    public static final String row_separator="\n";
    public static final String spec_separator="*";

    public static final String spec_separator_pattern="\\*";

    public static final String STRING_SPLIT_COMMON =";";
    public static boolean isEmpty(String value)
    {
        return value==null||value.trim().length()==0;
    }

    public static boolean isNull(Object o) {
        if (o==null) return true;
       String  s=o.toString().trim();
        return  s.length()==0||"Null".equals(s)||"null".equals(s)||"NULL".equals(s) ;
    }

    /**
     * 将数字用* 串联起来
     * @param value
     * @return
     */
    public static String combineNumberValue(Number... value)
    {

        String result="";

        int length = value.length;
        for (int i = 0; i < length; i++) {

            result+=value[i];
            if(i<length-1) {

                result+= spec_separator;
            }
        }

        return result;

    }


    /**
     * 解析包装串
     * @param packageString
     * @return
     */
    public static float[] decouplePackageString(String packageString)
    {
        float[] result=new float[3];
        if(StringUtils.isEmpty(packageString))
            return result;

        int firstIndex=packageString.indexOf(spec_separator);
        int lastIndex=packageString.lastIndexOf(spec_separator);



        try {
            result[0] =FloatHelper.scale( Float.valueOf(packageString.substring(0, firstIndex)));
            result[1] = FloatHelper.scale(Float.valueOf(packageString.substring(firstIndex + 1, lastIndex)));
            result[2] = FloatHelper.scale(Float.valueOf(packageString.substring(lastIndex + 1)));
        }catch (Throwable t)
        {
            t.printStackTrace();
        }
        return result;

    }


    /**
     * 厘米字符串 转换成 英寸字符串
     *格式如下 999*999*999\n 888*888*88
     *
     * @return
     */

    public static String convertCmStringToInchString(String cmString)
    {

        String[] rows = cmString.split("["+row_separator+"]+");

        String inchString="";

        for (int i = 0; i <rows.length; i++) {

            String[] specs=rows[i].split(spec_separator_pattern);

            int length = specs.length;
            for (int j = 0; j < length; j++) {
                String spec=specs[j];
                try {
                    float cmValue = Float.valueOf(spec.trim());
                    float inchValue=UnitUtils.cmToInch(cmValue);
                    inchString+=inchValue;

                }catch (Throwable t)
                {

                    inchString+=spec;
                }
                if(j<length-1)
                    inchString+=spec_separator;
            }





            inchString+=row_separator;


        }


        return inchString;

    }


}

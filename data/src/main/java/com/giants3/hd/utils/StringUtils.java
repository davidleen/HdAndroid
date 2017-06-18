package com.giants3.hd.utils;

/**
 * 字符串的功能类。
 */

public class StringUtils {



    public static boolean  isEmpty(String s)
    {
        return s==null||s.trim().length()==0;
    }

    public static final String row_separator="\n";
    public static final String spec_separator="*";

    public static final String spec_separator_pattern="\\*";

    public static final String STRING_SPLIT_SEMICOLON =";";

    public static final String STRING_SPLIT_DOT =".";
    public static final String PRODUCT_NAME_SPILT ="-";
    public static final String STRING_SPLIT_COMMA =",";
    public static final String PRODUCT_NAME_COMMA =STRING_SPLIT_COMMA;


    public static final String SPLIT_PRODUCT_MODIFY_LOG_FILENAME ="___";

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


    /**
     * 厘米字符串 转换成 英寸字符串
     *格式如下 999*999*999\n 888*888*888
     *
     * @return
     */

    public static String convertInchStringToCmString(String inchString)
    {

        String[] rows = inchString.split("["+row_separator+"]+");

        String cmString="";

        for (int i = 0; i <rows.length; i++) {

            String[] specs=rows[i].split(spec_separator_pattern);

            int length = specs.length;
            for (int j = 0; j < length; j++) {
                String spec=specs[j];
                try {
                    float inchValue = Float.valueOf(spec.trim());
                    float cmValue= UnitUtils.inchToCm(inchValue);
                    cmString+=cmValue;

                }catch (Throwable t)
                {

                    cmString+=spec;
                }
                if(j<length-1)
                    cmString+=spec_separator;
            }





            cmString+=row_separator;


        }


        return  cmString;


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
            System.out.println(packageString);
            t.printStackTrace();
        }
        return result;

    }






    /**
     * 解析产品规格串   23*66*99 69*89*12 95*78*12
     * @param specString
     * @return
     */
    public static float[][] decoupleSpecString(String specString)
    {

        String[] rows = specString.trim().split("[" + row_separator + "]+");

        int length = rows.length;
        float[][] result=new float[length][];

        for (int i = 0; i < length; i++) {

            result[i]= decouplePackageString(rows[i]);
        }


        return result;




    }



    public static int index(String[] array,String s)
    {

        int length = array.length;
        for (int i = 0; i < length; i++) {

            if(array[i].equals(s))
                return i;

        }
        return -1;
    }



    public static final String[] split(String string)
    {

        if(StringUtils.isEmpty(string)) return new String[]{};
        return string.split(STRING_SPLIT_SEMICOLON);

    }

    public static final String[] split(String string,String  separator)
    {

        if(StringUtils.isEmpty(string)) return new String[]{};
        return string.split(separator);

    }

    public static final String combine(String[] strings )
    {


        StringBuilder stringBuilder=new StringBuilder();
        for(String s:strings)
            stringBuilder.append(s).append(STRING_SPLIT_SEMICOLON);
        if(stringBuilder.length()>0)
            stringBuilder.setLength(stringBuilder.length()-1);

        return stringBuilder.toString();
    } public static final String combine(int[] intArrays )
    {


        StringBuilder stringBuilder=new StringBuilder();
        for(int s:intArrays)
            stringBuilder.append(s).append(STRING_SPLIT_SEMICOLON);
        if(stringBuilder.length()>0)
            stringBuilder.setLength(stringBuilder.length()-1);

        return stringBuilder.toString();
    }
    public   static final <T> String combine(T[] strings )
    {


        StringBuilder stringBuilder=new StringBuilder();
        for(T s:strings)
            stringBuilder.append(s).append(STRING_SPLIT_SEMICOLON);
        if(stringBuilder.length()>0)
            stringBuilder.setLength(stringBuilder.length()-1);

        return stringBuilder.toString();
    }

    public static final <T> String combine(Iterable<T> strings )
    {




        StringBuilder stringBuilder=new StringBuilder();
        for(T s:strings)
            stringBuilder.append(s).append(STRING_SPLIT_SEMICOLON);
        if(stringBuilder.length()>0)
            stringBuilder.setLength(stringBuilder.length()-1);

        return stringBuilder.toString();
    }

    /**
     * 比较字符串是否相同
     * @param memo
     * @param rem
     * @return
     */
    public static boolean compare(String memo, String rem) {

        if(memo==null&&rem==null)
            return true;
        if(memo==null) return false;
        return
                memo.equalsIgnoreCase(rem);

    }






    /**
     * 产品规格 并分段显示
     * @param spec
     * @return
     */
    public  static String[] groupSpec(float[][] spec)
    {


        return groupSpec(spec, false);



    }

    /**
     * 产品规格转  并分段显示
     * @param spec
     * @param  toCm  是否转换成cm
     * @return
     */
    public  static String[] groupSpec(float[][] spec, boolean toCm)
    {



        int length=spec.length;
        String[] result=new String[ ]{"","",""};
        for (int i = 0; i < length; i++) {

            for(int j=0;j<3;j++) {
                result[j] +=toCm? UnitUtils.inchToCm(spec[i][j]):spec[i][j];

                if (i < length-1)
                    result[j] += StringUtils.row_separator;
            }

        }


        return result;

    }




    /**
     * 版本号， id_no  格式为 13A0760->223311  使用不方便，
     */
    public static final String SPLITE_ID_NO="->";
    /**
     * 版本号， id_no  格式为 13A0760->223311  使用不方便， 拆分方法
     */
    public static final String[] spliteId_no(String id_no)
    {


        int index=id_no.indexOf(SPLITE_ID_NO);
        if(index==-1)
        {
            return new String[]{"",""};
        }

        return new String[]{

                id_no.substring(0,index),
                id_no.substring(index+SPLITE_ID_NO.length())

        };


    }
    /**
     * 版本号， id_no  格式为 13A0760->223311  使用不方便， 合并方法
     */
    public static final String combineToId_No(String productName,String pVersion)

    {


        return productName+SPLITE_ID_NO+pVersion;


    }



    public static final String  SQL_LIKE="%";
    /**
     * 数据库查询  模糊处理
     * @param s
     * @return
     */
    public static String sqlLike(String s) {


        return SQL_LIKE+s.trim()+SQL_LIKE;

    } /**
     * 数据库查询  模糊处理
     * @param s
     * @return
     */
    public static String sqlLeftLike(String s) {


        return SQL_LIKE+s.trim();

    }
}

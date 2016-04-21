package com.giants3.hd.utils;

import java.text.SimpleDateFormat;

/**
 * 日期格式
 * Created by davidleen29 on 2015/8/21.
 */
public class DateFormats {


    public static final SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_SS_LOG=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public static final SimpleDateFormat FORMAT_YYYY_MM_DD=new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat FORMAT_YYYY_MM_DD_HH_MM_CHINESE=new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
}

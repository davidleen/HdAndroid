package com.giants3.hd.android.entity;

import android.content.Context;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;

/**
 * 表格列表支持的数据类型
 * Created by david on 2016/3/6.
 */
public class TableData {


    public static final  int TYPE_IMAGE=2;
    private static final  String DIVIDER="\\|\\|";
    public int size;

    /**
     * 列标题
     */
    public String[] headNames;
    /**
     * 对应字段名
     */
    public  String[] fields;
    /**
     * 列宽度
     */
    public int[] width;
    /**
     * 数据类型
     */
    public int[] type;


    /**
     * 从系统资源中解析出相应数据
     * @return
     */
    public static TableData resolveData(Context context, int arrayResourceId)
    {
        TableData tableData=new TableData();
        String[] data=context.getResources().getStringArray(R.array.table_head_order_item);
        int size=data.length;
        tableData.size=size;
        tableData.fields=new String[size];
        tableData.headNames=new String[size];
        tableData.type=new int[size];
        tableData.width=new int[size];
        for(int i=0;i<size;i++)
        {
            String[] temp=data[i].split(DIVIDER);
            tableData.headNames[i]=temp[0];
            tableData.fields[i]=temp[1];
            tableData.type[i]=Integer.valueOf(temp[2]);
            tableData.width[i]= Utils.dp2px(Integer.valueOf(temp[3])) ;


        }



        return  tableData;



    }
}

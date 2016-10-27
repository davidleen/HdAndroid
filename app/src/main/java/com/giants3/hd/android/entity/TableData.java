package com.giants3.hd.android.entity;

import android.content.Context;

import com.giants3.hd.android.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格列表支持的数据类型
 * Created by david on 2016/3/6.
 */
public class TableData {


    public static final int TYPE_IMAGE = 2;
    private static final String DIVIDER = "\\|\\|";
    public int size;

    /**
     * 列标题
     */
    public List<String> headNames;
    /**
     * 对应字段名
     */
    public List<String> fields;
    /**
     * 列宽度
     */
    public List<Integer> width;
    /**
     * 数据类型
     */
    public List<Integer> type;

    /**
     * 当前要显示的字段的关联字段
     * <p>
     * 为图片字段时候  这个字段可以配置大图路径
     */
    public List<String> relateField;


    /**
     * 从系统资源中解析出相应数据
     *
     * @return
     */
    public static TableData resolveData(Context context, int arrayResourceId) {
        TableData tableData = new TableData();
        String[] data = context.getResources().getStringArray(arrayResourceId);
        int size = data.length;
        tableData.size = size;
        tableData.fields = new ArrayList<>(size);
        tableData.headNames = new ArrayList<>(size);
        tableData.type = new ArrayList<>(size);
        tableData.width = new ArrayList<>(size);
        tableData.relateField = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String[] temp = data[i].split(DIVIDER);
            tableData.headNames.add(temp[0]);
            tableData.fields.add(temp[1]);

            tableData.type.add(Integer.valueOf(temp[2]));
            tableData.width.add(Utils.dp2px(Integer.valueOf(temp[3])));
             tableData.relateField.add(temp.length > 4 ? temp[4] : null);
        }


        return tableData;


    }


    /**
     * 特殊移除部分字段。
     * @param fieldName
     */
    public void removeField(String fieldName) {

        int index = -1;
        for (int i = 0; i < fields.size(); i++) {


            String name = fields.get(i);
            if (fieldName.equals(name)) {
                index = i;

                break;
            }
        }

        if (index > -1) {
            fields.remove(index);
            headNames.remove(index);
            type.remove(index);
            width.remove(index);
            relateField.remove(index);
            size--;

        }


    }
}

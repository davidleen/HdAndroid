package com.giants3.hd.android.entity;

/**
 * Created by david on 2016/4/14.
 */
public class CustomFieldData
{

    public String  fileName;
    public String fieldCName;
    public Object object;
    public Object type;

    public String getValue() {

        try {
            return String.valueOf( object.getClass().getField(fileName).get(object));
        } catch (IllegalAccessException e) {


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return "";
    }
}

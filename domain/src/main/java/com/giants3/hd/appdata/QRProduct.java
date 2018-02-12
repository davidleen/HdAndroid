package com.giants3.hd.appdata;

import java.io.Serializable;

/**
 *
 * 二维码数据
 * Created by david on 2015/12/24.
 */

public class QRProduct implements Serializable
{


    public long id;
    public String name;
    public String pVersion;
    public String unitName;
    public String className;


    @Override
    public String toString() {
        return "QRProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pVersion='" + pVersion + '\'' +
                ", unitName='" + unitName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}

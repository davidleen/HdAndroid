package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 *
 * 客户表
 * Created by davidleen29 on 2015/7/1.
 */

public class Customer implements Serializable ,Valuable{
    public static final String CODE_TEMP = "000";
    /**
     * 单位 id
     */

    public long id;




    public String code;

    public String name;


    @Override
    public String toString() {
        return "["+code+"]"+name;
    }

    @Override
    public boolean isEmpty() {
        return StringUtils.isEmpty(code)&&StringUtils.isEmpty(name
        );
    }
}

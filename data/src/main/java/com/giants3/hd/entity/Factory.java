package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 *
 * 厂家信息
 * Created by david on 2015/10/25.
 */


public class Factory implements Serializable,Valuable {
    public static final String CODE_LOCAl = "001";
    /**
     *  id
     */

    public long id;


    /**
     * 厂家编号， 本厂000
     */

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

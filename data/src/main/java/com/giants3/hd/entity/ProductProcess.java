package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 * 工序表
 */


public class ProductProcess implements Serializable,Valuable {


    public static final String XISHUA="洗枪";


    public long id;



    public String code;



    public String name;



    public String memo;


    @Override
    public boolean isEmpty() {


        return StringUtils.isEmpty(code)&&StringUtils.isEmpty(name)&&StringUtils.isEmpty(memo
        );


    }
}

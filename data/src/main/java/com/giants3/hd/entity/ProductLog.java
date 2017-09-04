package com.giants3.hd.entity;

import com.giants3.hd.utils.DateFormats;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * 产品操作记录
 * Created by davidleen29 on 2015/8/21.
 */


public class ProductLog implements Serializable {



    public long id;


    public long productId;
    public String productName;
    public String pVersion;
    public long creatorId;
    public String creatorCode;
    public String creatorName;
    public String creatorCName;
    public long createTime;
    public String createTimeString;


    public long updatorId;
    public String updatorCode;
    public String updatorName;
    public String updatorCName;
    public long updateTime;
    public String updateTimeString;

    /**
     * 设置创建时间
     * @param user
     */
    public void setCreator(User user)
    {
        creatorId=user.id;
        creatorCode=user.code;
        creatorName=user.name;
        creatorCName=user.chineseName;
        createTime= Calendar.getInstance().getTimeInMillis();
        createTimeString= DateFormats.FORMAT_YYYY_MM_DD_HH_MM.format(new Date(createTime));

    }

    /**
     * 设置最新更新时间
     * @param user
     */
    public void setUpdator(User user)
    {

        //如果无创建信息  最近次更新者设置为创建者
        if(creatorId<=0)
        {
            setCreator(user);
        }

       updatorId=user.id;
        updatorCode=user.code;
        updatorName=user.name;
        updatorCName=user.chineseName;
        updateTime= Calendar.getInstance().getTimeInMillis();
        updateTimeString= DateFormats.FORMAT_YYYY_MM_DD_HH_MM.format(new Date(updateTime));



    }



}

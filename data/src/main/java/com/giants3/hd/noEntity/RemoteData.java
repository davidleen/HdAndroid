package com.giants3.hd.noEntity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络数据交换格式。
 */
public class RemoteData<T> implements Serializable{


    /**
     * 请求状态
     */
    public int code;


    /**
     * 状态的消息说明
     */
    public String message;

    public int pageIndex=0;
    public int pageCount=1;
    public int pageSize=1;
    public List<T> datas;
    public int totalCount;


    public String token;


    public RemoteData()
    {

        code=CODE_SUCCESS;
        datas=new ArrayList<>();

    }

    /**
     * 请求成功状态码
     */
    public  static final int CODE_SUCCESS=0;

    /**
     * 请求失败状态码
     */
    public  static final int CODE_FAIL=-1;

    /**
     * 请求失败状态码
     */
    public  static final int CODE_UNLOGIN=2;
    /**
     * 是否成功的调用
     * @return
     */
    public boolean isSuccess() {
        return code==CODE_SUCCESS;
    }
}

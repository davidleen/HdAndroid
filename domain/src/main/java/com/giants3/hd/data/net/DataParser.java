package com.giants3.hd.data.net;

/**
 *
 * 数据交换接口
 * Created by david on 2016/2/13.
 */
public interface DataParser<T>{


    public T parse(byte[] data)throws Exception;
    public byte[] parse( Object data)throws Exception;


}

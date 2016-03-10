package com.giants3.hd.data.net;

import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.entity.ErpOrder;
import com.giants3.hd.data.entity.ErpOrderItem;
import com.giants3.hd.data.entity.RemoteData;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 2016/2/13.
 */
public class ApiManager {

    public Map<Class<?>, Type> tokenMaps;

    @Inject
    ApiConnection apiConnection;


    @Inject
    public ApiManager() {
        tokenMaps = new HashMap<>();
        tokenMaps.put(Void.class, new TypeToken<RemoteData<Void>>() {
        }.getType());

        tokenMaps.put(String.class, new TypeToken<RemoteData<String>>() {
        }.getType());

        tokenMaps.put(AUser.class, new TypeToken<RemoteData<AUser>>() {
        }.getType());

        tokenMaps.put(AProduct.class, new TypeToken<RemoteData<AProduct>>() {
        }.getType());


        tokenMaps.put(ErpOrder.class, new TypeToken<RemoteData<ErpOrder>>() {
        }.getType());
        tokenMaps.put(ErpOrderItem.class, new TypeToken<RemoteData<ErpOrderItem>>() {
        }.getType());
    }

    /**
     * 登录
     *
     * @param loginData
     * @return RemoteData<AUser>
     * @throws HdException
     */
    public RemoteData<AUser> login(Map<String, String> loginData) throws HdException {

        String url = HttpUrl.login();
        String result = apiConnection.post(url, GsonUtils.toJson(loginData));
        RemoteData<AUser> remoteData = invokeByReflect(result, AUser.class);

        return remoteData;
    }


    /**  获取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws HdException
     */
    public RemoteData<AProduct> getProductList(String name, int pageIndex, int pageSize) throws HdException {

        String url = HttpUrl.getProductList(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<AProduct> remoteData = invokeByReflect(result, AProduct.class);

        return remoteData;
    }


    /**
     * 通用方法 将字符串转换成指定类型的对象
     *
     * @param result
     * @param aClass
     * @param <T>
     * @return
     * @throws HdException
     */
    public <T> RemoteData<T> invokeByReflect(String result, Class<T> aClass) throws HdException {

        Type generateType = tokenMaps.get(aClass);

        if(generateType==null)
        {
            throw HdException.create("未配置"+aClass.getName()+"对应的通配类型");
        }
        RemoteData<T> remoteData = GsonUtils.fromJson(result, generateType);
        return remoteData;
    }

    public RemoteData<ErpOrder> getOrderList(String name, int pageIndex, int pageSize) throws HdException {

        String url = HttpUrl.getOrderList(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrder> remoteData = invokeByReflect(result, ErpOrder.class);
        return remoteData;
    }

    public RemoteData<ErpOrderItem> getOrderItemList(String orderNo) throws HdException {

        String url = HttpUrl.getOrderItemList(orderNo );
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);
        return remoteData;
    }
}

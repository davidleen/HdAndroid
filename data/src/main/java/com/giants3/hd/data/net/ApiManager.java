package com.giants3.hd.data.net;

import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.ProductProcess;
import com.giants3.hd.utils.entity.Quotation;
import com.giants3.hd.utils.entity.QuotationDetail;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;
import com.giants3.hd.utils.noEntity.BufferData;
import com.giants3.hd.utils.noEntity.ErpOrderDetail;
import com.giants3.hd.utils.noEntity.FileInfo;
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
        tokenMaps.put(ProductDetail.class, new TypeToken<RemoteData<ProductDetail>>() {
        }.getType());

        tokenMaps.put(Quotation.class, new TypeToken<RemoteData<Quotation>>() {
        }.getType());
        tokenMaps.put(QuotationDetail.class, new TypeToken<RemoteData<QuotationDetail>>() {
        }.getType());
        tokenMaps.put(Material.class, new TypeToken<RemoteData<Material>>() {
        }.getType());

        tokenMaps.put(BufferData.class, new TypeToken<RemoteData<BufferData>>() {
        }.getType());
        tokenMaps.put(ProductProcess.class, new TypeToken<RemoteData<ProductProcess>>() {
        }.getType());
        tokenMaps.put(ErpOrderDetail.class, new TypeToken<RemoteData<ErpOrderDetail>>() {
        }.getType());
        tokenMaps.put(WorkFlowMessage.class, new TypeToken<RemoteData<WorkFlowMessage>>() {
        }.getType());
        tokenMaps.put(FileInfo.class, new TypeToken<RemoteData<FileInfo>>() {
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


    /**
     * 获取产品列表
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

        if (generateType == null) {
            throw HdException.create("未配置" + aClass.getName() + "对应的通配类型");
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

    public RemoteData<ErpOrderDetail> getOrderDetail(String orderNo) throws HdException {

        String url = HttpUrl.getOrderDetail(orderNo);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderDetail> remoteData = invokeByReflect(result, ErpOrderDetail.class);
        return remoteData;
    }

    public RemoteData<ProductDetail> getProductDetail(long productId) throws HdException {

        String url = HttpUrl.getProductDetail(productId);
        String result = apiConnection.getString(url);
        RemoteData<ProductDetail> remoteData = invokeByReflect(result, ProductDetail.class);
        return remoteData;
    }

    public RemoteData<Quotation> getQuotationList(String name, int pageIndex, int pageSize) throws HdException {

        String url = HttpUrl.getQuotationList(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<Quotation> remoteData = invokeByReflect(result, Quotation.class);
        return remoteData;
    }

    /**
     * 、
     * 获取报价详情
     *
     * @param quotationId
     * @return
     */
    public RemoteData<QuotationDetail> getQuotationDetail(long quotationId) throws HdException {


        String url = HttpUrl.getQuotationDetail(quotationId);
        String result = apiConnection.getString(url);
        RemoteData<QuotationDetail> remoteData = invokeByReflect(result, QuotationDetail.class);
        return remoteData;


    }

    /**
     * 读取材料列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws HdException
     */
    public RemoteData<Material> getMaterialList(String name, int pageIndex, int pageSize) throws HdException {

        String url = HttpUrl.getMaterialList(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<Material> remoteData = invokeByReflect(result, Material.class);
        //移动端不需要photo
        if (remoteData.isSuccess()) {
            for (Material material : remoteData.datas) {
                material.photo = null;
            }
        }
        return remoteData;
    }

    /**
     * 读取未停用的材料列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws HdException
     */
    public RemoteData<Material> getMaterialListInService(String name, int pageIndex, int pageSize) throws HdException {

        String url = HttpUrl.getMaterialListInService(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<Material> remoteData = invokeByReflect(result, Material.class);
        //移动端不需要photo
        if (remoteData.isSuccess()) {
            for (Material material : remoteData.datas) {
                material.photo = null;
            }
        }
        return remoteData;
    }

    /**
     * 上传材料图片
     *
     * @param bytes
     * @return
     * @throws HdException
     */
    public RemoteData<Void> uploadMaterialPicture(long materialId, byte[] bytes) throws HdException {

        String url = HttpUrl.uploadMaterialPicture(materialId);
        String result = "";

        result = apiConnection.postBytes(url, bytes);

        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }


    public RemoteData<BufferData> getInitData(long userId) throws HdException {

        String url = HttpUrl.loadInitData(userId);
        String result = apiConnection.getString(url);

        RemoteData<BufferData> remoteData = invokeByReflect(result, BufferData.class);

        return remoteData;
    }

    public RemoteData<ProductDetail> saveProductDetail(ProductDetail productDetail) throws HdException {
        String url = HttpUrl.saveProductDetail();
        String result = apiConnection.post(url, GsonUtils.toJson(productDetail));
        RemoteData<ProductDetail> remoteData = invokeByReflect(result, ProductDetail.class);
        return remoteData;
    }

    public RemoteData<ProductProcess> getProductProcessList(String name, int pageIndex, int pageSize) throws HdException {
        String url = HttpUrl.getProductProcessList(name, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<ProductProcess> remoteData = invokeByReflect(result, ProductProcess.class);
        //移动端不需要photo
        return remoteData;
    }

    /**
     * 获取未处理的流程信息
     *
     * @return
     * @throws HdException
     */
    public RemoteData<WorkFlowMessage> getUnHandleWorkFlowList() throws HdException {

        String url = HttpUrl.getUnHandleWorkFlowList();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);
        //移动端不需要photo
        return remoteData;
    }

    /**
     * 审核流程传递
     */
    public RemoteData<Void> checkWorkFlowMessage(long workFlowMessageId) throws HdException {

        String url = HttpUrl.checkWorkFlowMessage(workFlowMessageId);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    /**
     * 接受流程传递
     */
    public RemoteData<Void> receiveWorkFlowMessage(long workFlowMessageId) throws HdException {
        String url = HttpUrl.receiveWorkFlowMessage(workFlowMessageId);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    /**
     * 获取可以传递流程的订单item
     *
     * @return
     */
    public RemoteData<ErpOrderItem> getAvailableOrderItemForTransform() throws HdException {

        String url = HttpUrl.getAvailableOrderItemForTransform();
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);
        //移动端不需要photo
        return remoteData;

    }

    public RemoteData<Void> sendWorkFlowMessage(long orderItemId, int flowStep, int tranQty, String memo) throws HdException {
        String url = HttpUrl.sendWorkFlowMessage(orderItemId, flowStep, tranQty, memo);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    public RemoteData<WorkFlowMessage> mySendWorkFlowMessage() throws HdException {

        String url = HttpUrl.mySendWorkFlowMessage();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);

        return remoteData;

    }

    public RemoteData<Void> rejectWorkFlowMessage(long workFlowMessageId, int toWorkFlowStep, String reason) throws HdException {
        String url = HttpUrl.rejectWorkFlowMessage(workFlowMessageId, toWorkFlowStep, reason);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    /**
     * 读取未出库订单货款列表
     *
     * @return
     */
    public RemoteData<ErpOrderItem> loadUnCompleteOrderItemWorkFlowReport() throws HdException {

        String url = HttpUrl.loadUnCompleteOrderItemWorkFlowReport();
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);

        return remoteData;
    }

    public RemoteData<ErpOrderItem> loadOrderWorkFlowReport(String key, int pageIndex, int pageSize) throws HdException {
        String url = HttpUrl.loadOrderWorkFlowReport(key, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);

        return remoteData;
    }

    public RemoteData<FileInfo> loadAppUpgradeInfo() throws HdException {

        String url = HttpUrl.loadAppUpgradeInfo();
        String result = apiConnection.getString(url);
        RemoteData<FileInfo> remoteData = invokeByReflect(result, FileInfo.class);

        return remoteData;
    }
}

package com.giants3.hd.data.net;

import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrder;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.Material;
import com.giants3.hd.entity.OrderItem;
import com.giants3.hd.entity.OrderItemWorkFlowState;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.ProductProcess;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.entity.Quotation;
import com.giants3.hd.noEntity.QuotationDetail;
import com.giants3.hd.entity.User;
import com.giants3.hd.entity.WorkFlowArea;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.entity_erp.WorkFlowMaterial;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.noEntity.ErpOrderDetail;
import com.giants3.hd.noEntity.FileInfo;
import com.giants3.hd.noEntity.MessageInfo;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.WorkFlowMemoAuth;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

import java.io.File;
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
        tokenMaps.put(User.class, new TypeToken<RemoteData<User>>() {
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
        tokenMaps.put(WorkFlowMemoAuth.class, new TypeToken<RemoteData<WorkFlowMemoAuth>>() {
        }.getType());
        tokenMaps.put(OrderItemWorkFlowState.class, new TypeToken<RemoteData<OrderItemWorkFlowState>>() {
        }.getType());
        tokenMaps.put(FileInfo.class, new TypeToken<RemoteData<FileInfo>>() {
        }.getType());
        tokenMaps.put(ErpWorkFlowReport.class, new TypeToken<RemoteData<ErpWorkFlowReport>>() {
        }.getType());

        tokenMaps.put(OrderItem.class, new TypeToken<RemoteData<OrderItem>>() {
        }.getType());
        tokenMaps.put(ErpOrderItemProcess.class, new TypeToken<RemoteData<ErpOrderItemProcess>>() {
        }.getType());


        tokenMaps.put(ProductWorkMemo.class, new TypeToken<RemoteData<ProductWorkMemo>>() {
        }.getType());

        tokenMaps.put(OrderItemWorkMemo.class, new TypeToken<RemoteData<OrderItemWorkMemo>>() {
        }.getType());

        tokenMaps.put(WorkFlowArea.class, new TypeToken<RemoteData<WorkFlowArea>>() {
        }.getType());
        tokenMaps.put(MessageInfo.class, new TypeToken<RemoteData<MessageInfo>>() {
        }.getType());
        tokenMaps.put(WorkFlowMaterial.class, new TypeToken<RemoteData<WorkFlowMaterial>>() {
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

        if (remoteData.code == RemoteData.CODE_UNLOGIN) {
        }
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
    public RemoteData<Void> receiveWorkFlowMessage(long workFlowMessageId, File[] files, String area) throws HdException {
        String url = HttpUrl.receiveWorkFlowMessage(workFlowMessageId);
        String result = apiConnection.updatePictures(url, files);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    /**
     * 获取可以传递流程的订单item
     *
     * @return
     */
    public RemoteData<OrderItemWorkFlowState> getAvailableOrderItemForTransform() throws HdException {

        String url = HttpUrl.getAvailableOrderItemForTransform();
        String result = apiConnection.getString(url);
        RemoteData<OrderItemWorkFlowState> remoteData = invokeByReflect(result, OrderItemWorkFlowState.class);
        //移动端不需要photo
        return remoteData;

    }

    public RemoteData<Void> sendWorkFlowMessage(ErpOrderItemProcess erpOrderItemProcess, int tranQty, long area, String memo) throws HdException {
        String url = HttpUrl.sendWorkFlowMessage(tranQty, area, memo);
        String result = apiConnection.post(url, GsonUtils.toJson(erpOrderItemProcess));
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    public RemoteData<WorkFlowMessage> mySendWorkFlowMessage() throws HdException {

        String url = HttpUrl.mySendWorkFlowMessage();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);

        return remoteData;

    }

    public RemoteData<Void> rejectWorkFlowMessage(long workFlowMessageId, final File[] file, final String memo) throws HdException {
        String url = HttpUrl.rejectWorkFlowMessage(workFlowMessageId, memo);
        String result = apiConnection.updatePictures(url, file);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);

        return remoteData;
    }

    /**
     * 读取未出库订单货款列表
     *
     * @return
     */
    public RemoteData<OrderItemWorkFlowState> loadUnCompleteOrderItemWorkFlowReport() throws HdException {

        String url = HttpUrl.loadUnCompleteOrderItemWorkFlowReport();
        String result = apiConnection.getString(url);
        RemoteData<OrderItemWorkFlowState> remoteData = invokeByReflect(result, OrderItemWorkFlowState.class);

        return remoteData;
    }

    public RemoteData<OrderItemWorkFlowState> loadOrderWorkFlowReport(String key, int pageIndex, int pageSize) throws HdException {
        String url = HttpUrl.loadOrderWorkFlowReport(key, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<OrderItemWorkFlowState> remoteData = invokeByReflect(result, OrderItemWorkFlowState.class);

        return remoteData;
    }

    public RemoteData<FileInfo> loadAppUpgradeInfo() throws HdException {

        String url = HttpUrl.loadAppUpgradeInfo();
        String result = apiConnection.getString(url);
        RemoteData<FileInfo> remoteData = invokeByReflect(result, FileInfo.class);

        return remoteData;
    }

    /**
     * 查询订单的报表
     *

     * @return
     */

    public RemoteData<ErpWorkFlowReport> getOrderItemWorkFlowReport(final String os_no, final int itm) throws HdException {

        String url = HttpUrl.getOrderItemWorkFlowReport(os_no, itm);
        String result = apiConnection.getString(url);
        RemoteData<ErpWorkFlowReport> remoteData = invokeByReflect(result, ErpWorkFlowReport.class);

        return remoteData;
    }

    /**
     * 查询订单
     *
     * @param key
     * @return
     */
    public RemoteData<ErpOrderItem> searchErpOrderItems(String key, final int pageIndex, final int pageSize) throws HdException {
        String url = HttpUrl.searchErpOrderItem(key, pageIndex, pageSize);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);

        return remoteData;
    }

    public RemoteData<ErpOrderItemProcess> getAvailableOrderItemProcess(final String osNo, final int itm, int workFlowStep) throws HdException {


        String url = HttpUrl.getAvailableOrderItemProcess(osNo, itm, workFlowStep);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItemProcess> remoteData = invokeByReflect(result, ErpOrderItemProcess.class);

        return remoteData;


    }

    /**
     * 读取指定订单，流程的消息列表
     *
     * @param os_no
     * @param workFlowStep
     * @return
     */
    public RemoteData<WorkFlowMessage> getOrderItemWorkFlowMessage(String os_no, int itm, int workFlowStep) throws HdException {

        String url = HttpUrl.getOrderItemWorkFlowMessage(os_no, itm, workFlowStep);
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);

        return remoteData;


    }


    public RemoteData<User> loadUsers() throws HdException {


        String url = HttpUrl.loadUsers();
        String result = apiConnection.getString(url);
        RemoteData<User> remoteData = invokeByReflect(result, User.class);

        return remoteData;


    }

    public RemoteData<ErpOrderItem> getUnCompleteWorkFlowOrderItems(String key) throws HdException {
        String url = HttpUrl.getUnCompleteWorkFlowOrderItems(key);
        String result = apiConnection.getString(url);
        RemoteData<ErpOrderItem> remoteData = invokeByReflect(result, ErpOrderItem.class);

        return remoteData;
    }

    public RemoteData<OrderItemWorkMemo> getOrderItemWorkMemoList(String os_no, int itm) throws HdException {
        String url = HttpUrl.getOrderItemWorkMemoList(os_no, itm);
        String result = apiConnection.getString(url);
        RemoteData<OrderItemWorkMemo> remoteData = invokeByReflect(result, OrderItemWorkMemo.class);
        return remoteData;

    }

    public RemoteData<ProductWorkMemo> getProductWorkMemoList(String productName, String pversion) throws HdException {
        String url = HttpUrl.getProductWorkMemoList(productName, pversion);
        String result = apiConnection.getString(url);
        RemoteData<ProductWorkMemo> remoteData = invokeByReflect(result, ProductWorkMemo.class);
        return remoteData;

    }

    public RemoteData<Void> saveWorkMemo(int workFlowStep, String os_no, int itm, String orderItemWorkMemo, String prd_name, String pVersion, String productWorkMemo) throws HdException {

        String url = HttpUrl.saveWorkMemo();


        Map<String, Object> map = new HashMap<>();
        map.put("workFlowStep", workFlowStep);
        map.put("os_no", os_no);
        map.put("itm", itm);
        map.put("orderItemWorkMemo", orderItemWorkMemo);
        map.put("prd_name", prd_name);
        map.put("pVersion", pVersion);
        map.put("productWorkMemo", productWorkMemo);
        String result = apiConnection.post(url, GsonUtils.toJson(map));
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);
        return remoteData;
    }


    public RemoteData<WorkFlowArea> getWorkFlowAreaList() throws HdException {


        String url = HttpUrl.getWorkFlowAreaList();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowArea> remoteData = invokeByReflect(result, WorkFlowArea.class);
        return remoteData;

    }

    public RemoteData<MessageInfo> getNewMessageInfo() throws HdException {

        String url = HttpUrl.getNewMessageInfo();
        String result = apiConnection.getString(url);
        RemoteData<MessageInfo> remoteData = invokeByReflect(result, MessageInfo.class);
        return remoteData;
    }

    public RemoteData<WorkFlowMaterial> getWorkFlowMaterials(String osNo, int itm, String workFlowCode) throws HdException {
        String url = HttpUrl.getWorkFlowMaterials(osNo, itm, workFlowCode);
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMaterial> remoteData = invokeByReflect(result, WorkFlowMaterial.class);
        return remoteData;
    }

    public RemoteData<WorkFlowMessage> getWorkFlowMessageByOrderItem(String osNo, int itm) throws HdException {
        String url = HttpUrl.getWorkFlowMessageByOrderItem(osNo, itm);
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);
        return remoteData;
    }

    public RemoteData<WorkFlowMessage> getMyWorkFlowMessage() throws HdException {

        String url = HttpUrl.getMyWorkFlowMessage();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMessage> remoteData = invokeByReflect(result, WorkFlowMessage.class);
        return remoteData;
    }

    public RemoteData<WorkFlowMemoAuth> getWorkFlowMemoAuth() throws HdException {

        String url = HttpUrl.getWorkFlowMemoAuth();
        String result = apiConnection.getString(url);
        RemoteData<WorkFlowMemoAuth> remoteData = invokeByReflect(result, WorkFlowMemoAuth.class);
        return remoteData;
    }

    public RemoteData<Void> checkWorkFlowMemo(long orderItemWorkMemoId, boolean check) throws HdException {

        String url = HttpUrl.checkWorkFlowMemo(orderItemWorkMemoId,check);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);
        return remoteData;
    }

    public RemoteData<Void> updatePassword(String oldPasswordMd5, String newPasswordMd5) throws HdException {
        String url = HttpUrl.updatePassword(oldPasswordMd5,newPasswordMd5);
        String result = apiConnection.getString(url);
        RemoteData<Void> remoteData = invokeByReflect(result, Void.class);
        return remoteData;
    }
}

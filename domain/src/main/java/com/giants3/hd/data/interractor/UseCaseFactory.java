package com.giants3.hd.data.interractor;

import com.giants3.hd.data.module.AppModule;
import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.noEntity.ProductDetail;
import com.google.inject.Guice;
import com.google.inject.Inject;

import java.io.File;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 用例工厂类
 * Created by david on 2015/9/16.
 */
public class UseCaseFactory {


    @Inject
    RestApi restApi;


    private UseCaseFactory() {


        Guice.createInjector(new AppModule()).injectMembers(this);

    }


    public static UseCaseFactory factory = null;


    public synchronized static UseCaseFactory getInstance() {


        if (factory == null) {

            factory = new UseCaseFactory();

        }
        return factory;
    }


    public UseCase createLogin(Map<String, String> map) {


        return new GetLoginData(Schedulers.newThread(), AndroidSchedulers.mainThread(), map, restApi);
    }

    public UseCase createLoginUseCase(long userId,String passwordMd5,String deviceToken) {


        return new LoginUseCase(    userId,  passwordMd5,deviceToken, restApi );
    }
    public UseCase createProductListCase(String name, int pageIndex, int pageSize) {

      return   createProductListCase(name,pageIndex,pageSize,true);

    }

    public UseCase createProductListCase(String name, int pageIndex, int pageSize,boolean withCopy) {


        return new GetProductListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, withCopy,restApi);
    }

    public UseCase createOrderListCase(String name, int pageIndex, int pageSize) {


        return new GetOrderListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, restApi);
    }

    /**
     * 读取订单详情货款列表
     *
     * @param orderNo
     * @return
     */
    public UseCase createOrderDetailCase(String orderNo) {
        return new GetOrderDetailCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), orderNo, restApi);
    }

    /***
     * 读取订单详情
     *
     * @param productId
     * @return
     */
    public UseCase createGetProductDetailCase(long productId) {
        return new GetProductDetailCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), productId, restApi);
    }

    public UseCase createGetQuotationList(String name, int pageIndex, int pageSize) {

        return new GetQuotationListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, restApi);
    }

    public UseCase createGetQuotationDetail(long quotationId) {
        return new GetQuotationDetailCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), quotationId, restApi);

    }

    public UseCase createMaterialListCase(String name, int pageIndex, int pageSize) {

        return new GetMaterialListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, true, restApi);
    }


    public UseCase createMaterialListInServiceCase(String name, int pageIndex, int pageSize) {

        return new GetMaterialListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, false, restApi);
    }

    public UseCase createUploadMaterialPictureCase(byte[] bytes, long id) {
        return new UploadMaterialPictureCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), id, bytes, restApi);
    }


    public UseCase createGetInitDataCase(long userId) {
        return new GetInitDataCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), userId, restApi);
    }

    public UseCase saveProductDetailCase(ProductDetail productDetail) {
        return new SaveProductDetailCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), productDetail, restApi);
    }

    public UseCase createProductProcessListCase(String key, int pageIndex, int pageSize) {
        return new GetProductProcessListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), key, pageIndex, pageSize, restApi);

    }

    public UseCase createGetUnHandleWorkFlowMessageCase(String key) {

        return new GetUnHandleWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),key, restApi);
    }


    public UseCase createGetMyWorkFlowMessageCase(String key,int pageIndex,int pageSize) {

        return new GetMyWorkFlowMessageCase(key, restApi ,  pageIndex,  pageSize);
    }

    /**
     * 审核流程传递
     *
     * @param workFlowMessageId
     * @return
     */
    public UseCase createCheckWorkFlow(long workFlowMessageId) {
        return new CheckWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), workFlowMessageId, restApi);
    }

    /**
     * 流程审核 返工
     *
     * @param workFlowMessageId
     * @return
     */
    public UseCase createRejectWorkFlowUseCase(long workFlowMessageId, File[] file, String memo) {
        return new RejectWorkFlowMessageCase(workFlowMessageId, file, memo, restApi);
    }

    /**
     * 接受流程传递
     *
     * @param workFlowMessageId
     * @return
     */
    public UseCase createReceiveWorkFlow(long workFlowMessageId) {
        return new ReceiveWorkFlowMessageCase(workFlowMessageId, null, "", restApi);
    }

    /**
     * 接受流程传递
     *
     * @param workFlowMessageId
     * @return
     */
    public UseCase createReceiveWorkFlow(long workFlowMessageId, File[] file, String memo) {
        return new ReceiveWorkFlowMessageCase(workFlowMessageId, file, memo, restApi);
    }



    /**
     * 订单查询
     *
     * @return
     */
    public UseCase createSearchOrderItemUseCase(String key, int pageIndex, int pageSize) {

        return new SearchOrderItemUseCase(key, pageIndex, pageSize, restApi);

    }

    /**
     * 提交订单至目标流程
     *
     * @param orderItemProcess
     * @param tranQty
     * @return
     */
    public UseCase createSendWorkFlowMessageCase(ErpOrderItemProcess orderItemProcess, int tranQty, long area, String memo) {
        return new SendWorkFlowMessageCase(orderItemProcess, tranQty, area, memo, restApi);

    }

    public UseCase createGetMySendWorkFlowMessageCase() {

        return new MySendWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);
    }

    public UseCase createUnCompleteOrderWorkFlowReportUseCase() {
        return new UnCompleteOrderWorkFlowReportUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);
    }


    /**
     * 订单的生产进度报表
     *
     * @return
     */
    public UseCase createGetOrderItemWorkFlowReportUseCase(String os_no, int itm) {
        return new GetOrderItemWorkFlowReportUseCase(os_no, itm, restApi);
    }

    public UseCase loadOrderWorkFlowReportUseCase(String key, int pageIndex, int pageSize) {
        return new LoadOrderWorkFlowReportUseCase(key, pageIndex, pageSize, Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);
    }

    /**
     * 读取最新apk包路径
     *
     * @return
     */
    public UseCase createLoadAppUpgradeInfoUseCase() {
        return new LoadAppUpgradeInfoUseCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);
    }

    /**
     * //获取关联的流程信息
     *
     * @param osNo
     * @param itm
     * @param workFlowStep
     * @return
     */
    public UseCase createGetAvailableOrderItemProcessUseCase(String osNo, int itm, int workFlowStep) {


        return new GetAvailableOrderItemProcessUseCase(osNo, itm, workFlowStep, restApi);

    }

    public UseCase createGetWorkFlowMessageCase(String os_no, int itm, int workFlowStep) {
        return new GetOrderItemWorkFlowMessageUseCase(os_no, itm, workFlowStep, restApi);
    }

    public UseCase createLoadUsersUseCase() {

        return new LoadUsersUseCase(restApi);
    }

    public UseCase createGetUnCompleteWorkFlowOrderItemsUseCase(String key, int workFlowStep, int pageIndex, int pageSize) {

        return new GetUnCompleteWorkFlowOrderItemsUseCase(key, restApi,   workFlowStep,   pageIndex,   pageSize);
    }
    public UseCase createGetCompleteWorkFlowOrderItemsUseCase(String key,int pageIndex,int pageSize) {

        return new GetCompleteWorkFlowOrderItemsUseCase(key, restApi,  pageIndex,  pageSize);
    }

    public UseCase createGetProductWorkMemoUseCase(String prd_name, String pversion) {

        return new GetProductWorkMemoUseCase(prd_name,pversion, restApi);

    }

    public UseCase createGetOrderItemWorkMemoUseCase(String os_no, int itm) {
        return new  GetOrderItemWorkMemoUseCase(os_no,itm,restApi);
    }

    public UseCase createSaveWorkMemoUseCase(int workFlowStep,String os_no, int itm, String orderItemWorkMemo, String prd_name, String pversion, String productWorkMemo) {


            return new SaveWorkMemoUseCase( workFlowStep, os_no,   itm,   orderItemWorkMemo,   prd_name,   pversion,   productWorkMemo,restApi);
    }

    public UseCase createGetWorkFlowAreaListUseCase() {
        return new GetWorkFlowAreaListUseCase( restApi);
    }

    public UseCase createGetNewMessageInfoUseCase() {
        return new GetNewMessageInfoUseCase( restApi);
    }

    public UseCase createGetWorkFlowMaterialsUseCase(String osNo, int itm, String workFlowCode) {
        return new GetWorkFlowMaterialsUseCase(osNo,itm,workFlowCode, restApi);
    }

    public UseCase createGetWorkFlowMessageByOrderItemUseCase(String osNO, int itm) {


        return new GetWorkFlowMessageByOrderItemUseCase(osNO,itm, restApi);
    }


    public UseCase createGetWorkFlowMemoAuthUseCase() {


        return new GetWorkFlowMemoAuthUseCase( restApi);
    }

    public UseCase createCheckWorkFlowMemoUseCase(long orderItemWorkMemoId, boolean check) {

        return new CheckWorkFlowMemoUseCase( orderItemWorkMemoId,check,restApi);
    }

    public UseCase createUpdatePasswordUseCase(String oldPasswordMd5,String newPasswordMd5) {

        return new UpdatePasswordUseCase(  oldPasswordMd5,  newPasswordMd5, restApi);
    }

    public UseCase createSearchSampleData(String prd_name, String pVersion) {
        return new SearchSampleDataUseCase(  prd_name,  pVersion, restApi);
    }

    public UseCase createGetClearWorkFlowUseCase(String os_no, int itm) {
        return new ClearWorkFlowUseCase(  os_no,  itm, restApi);
    }

    public UseCase createGetAppQuotationsUseCase(String key, int pageIndex, int pageSize) {

        return new GetAppQuotationsUseCase(key,pageIndex,pageSize,restApi);

    }

    public UseCase createTempQuotation() {
        return new CreateAppQuotationCase(restApi);
    }

    public UseCase getAppQuotationDetailCase(long quotationId) {


        return new GetAppQuotationDetailCase(quotationId,restApi);
    }

    public UseCase createAddProductToQuotationUseCase(long quotationId, long productId) {

        return new AddProductToQuotationUseCase(quotationId,productId,restApi);
    }

    public UseCase createRemoveItemFromQuotationUseCase(long quotationId, int item) {
        return new RemoveItemFromQuotationUseCase(quotationId,item,restApi);
    }

    public UseCase createUpdateQuotationItemPriceUseCase(long quotationId, int itm, float price) {
        return new UpdateQuotationItemPriceUseCase(quotationId,itm,price,restApi);
    }

    public UseCase createUpdateQuotationItemQtyUseCase(long quotationId, int itm, int newQty) {
        return new UpdateQuotationItemQtyUseCase(quotationId,itm,newQty,restApi);
    }

    public UseCase createUpdateQuotationItemDiscountUseCase(long quotationId, int itm, float newDisCount) {


        return new UpdateQuotationItemDiscountUseCase(quotationId,itm,newDisCount,restApi);

    }

    public UseCase createUpdateQuotationDiscountUseCase(long quotationId, float newDisCount) {
        return new UpdateQuotationDiscountUseCase(quotationId,newDisCount,restApi);

    }

    public UseCase createSaveQuotationUseCase(long quotationId) {

        return new SaveQuotationUseCase(quotationId,restApi);
    }

    public UseCase createPrintQuotationUseCase(long quotationId,String filePath) {
        return new PrintQuotationUseCase(quotationId,  filePath,restApi);

    }

    public UseCase createGetCustomerListUseCase() {
        return createGetCustomerListUseCase("");
    }public UseCase createGetCustomerListUseCase(String key) {
        return new GetCustomerListUseCase( key,restApi);
    }

    public UseCase createUpdateQuotationCustomerUseCase(long quotationId, long customerId) {

        return new UpdateQuotationCustomerUseCase(quotationId, customerId,restApi);

    }

    public UseCase createSaveCustomerUseCase(Customer customer) {

        return new SaveCustomerUseCase( customer,restApi);
    }

    public UseCase createUpdateQuotationItemMemoUseCase(long quotationId, int itm, String memo) {
        return  new UpdateQuotationItemMemoUseCase(quotationId,itm,memo,restApi);
    }

    public UseCase createDeleteQuotationUseCase(long quotationId) {
        return new DeleteQuotationUseCase(quotationId,restApi);
    }

    public UseCase createUpdateQuotationFieldUseCase(long quotationId, String field, String data) {
        return new UpdateQuotationFieldUseCase(quotationId,field,data,restApi);
    }

    public UseCase createGetWorkFlowMessageUseCase(long workflowMessageId) {
        return new GetWorkFlowMessageUseCase(workflowMessageId,restApi);
    }
}

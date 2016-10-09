package com.giants3.hd.data.interractor;

import com.giants3.hd.data.module.AppModule;
import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.utils.entity.ProductDetail;
import com.google.inject.Guice;
import com.google.inject.Inject;

import java.util.Map;
import java.util.concurrent.Executor;

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


    public UseCase createProductListCase(String name, int pageIndex, int pageSize) {


        return new GetProductListCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), name, pageIndex, pageSize, restApi);
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

    public UseCase createGetUnHandleWorkFlowMessageCase() {

        return new GetUnHandleWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);
    }

    /**
     * 审核流程传递
     * @param workFlowMessageId
     * @return
     */
    public UseCase createCheckWorkFlow(long workFlowMessageId) {
        return new CheckWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),workFlowMessageId, restApi);
    }

    /**
     * 流程审核 返工
     * @param workFlowMessageId
     * @return
     */
    public UseCase createRejectWorkFlowUseCase(long workFlowMessageId,int toWorkFlowStep,String reason) {
        return new RejectWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),workFlowMessageId,toWorkFlowStep,  reason, restApi);
    }
    /**
     * 接受流程传递
     * @param workFlowMessageId
     * @return
     */
    public UseCase createReceiveWorkFlow(long workFlowMessageId) {
        return new ReceiveWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),workFlowMessageId, restApi);
    }

    /**
     * 获取可以传递流程的订单item
     * @return
     */
    public UseCase createGetAvailableOrderItemForTransformCase() {

        return new GetAvailableOrderItemForTransformCase(Schedulers.newThread(), AndroidSchedulers.mainThread(), restApi);

    }

    /**
     * 提交订单至目标流程
     * @param orderItemId
     * @param flowStep
     * @param tranQty
     * @return
     */
    public UseCase createSendWorkFlowMessageCase(long orderItemId, int flowStep, int tranQty,String memo) {
        return new SendWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread(),  orderItemId,   flowStep,   tranQty ,  memo,restApi);

    }

    public UseCase createGetMySendWorkFlowMessageCase() {

        return new MySendWorkFlowMessageCase(Schedulers.newThread(), AndroidSchedulers.mainThread() ,restApi);
    }
}

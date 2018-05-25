/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giants3.hd.data.net;


import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.exception.NetworkConnectionException;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.ErpOrder;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.Material;
import com.giants3.hd.entity.OrderItemWorkFlowState;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity_erp.SampleState;
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
import com.google.inject.Inject;

import java.io.File;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {


    private static final String TAG = "RestApiImpl";
    @Inject
    ApiManager apiManager;

    @Inject
    public RestApiImpl() {


    }

    @Override
    public Observable<List<Quotation>> userEntityList() {
        return Observable.create(new Observable.OnSubscribe<List<Quotation>>() {
            @Override
            public void call(Subscriber<? super List<Quotation>> subscriber) {


            }
        });
    }

    @Override
    public Observable<Quotation> userEntityById(final int userId) {
        return Observable.create(new Observable.OnSubscribe<Quotation>() {
            @Override
            public void call(Subscriber<? super Quotation> subscriber) {


            }
        });
    }

    /**
     * 登录接口
     *
     * @param map
     * @return
     */
    @Override
    public Observable<RemoteData<AUser>> login(final Map<String, String> map) {


        return create(new ApiInvoker<AUser>() {
            @Override
            public RemoteData<AUser> invoker() throws HdException {
                return apiManager.login(map);
            }
        });


    }

    @Override
    public Observable<RemoteData<User>> loginByUserId(final long userId, final String passwordMd5, final String deviceToken) {

        return create(new ApiInvoker<User>() {
            @Override
            public RemoteData<User> invoker() throws HdException {
                return apiManager.loginByUserId(userId,passwordMd5,  deviceToken);
            }
        });



    }

    /**
     * 读取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Observable<RemoteData<AProduct>> getProductList(final String name, final int pageIndex, final int pageSize, final boolean withCopy) {

        return create(new ApiInvoker<AProduct>() {
            @Override
            public RemoteData<AProduct> invoker() throws HdException {
                return apiManager.getProductList(name, pageIndex, pageSize,withCopy);
            }
        });
    }

    @Override
    public Observable<RemoteData<ErpOrder>> getOrderList(final String name, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<ErpOrder>() {
            @Override
            public RemoteData<ErpOrder> invoker() throws HdException {
                return apiManager.getOrderList(name, pageIndex, pageSize);
            }
        });
    }

    @Override
    public Observable getOrderDetail(final String orderNo) {
        return create(new ApiInvoker<ErpOrderDetail>() {
            @Override
            public RemoteData<ErpOrderDetail> invoker() throws HdException {
                return apiManager.getOrderDetail(orderNo);
            }
        });
    }

    @Override
    public Observable getProductDetail(final long productId) {
        return create(new ApiInvoker<ProductDetail>() {
            @Override
            public RemoteData<ProductDetail> invoker() throws HdException {
                return apiManager.getProductDetail(productId);
            }
        });
    }

    /**
     * 通用调用命令接口
     *
     * @param <T>
     */
    public interface ApiInvoker<T> {
        public RemoteData<T> invoker() throws HdException;
    }



    public static <T> Observable<RemoteData<T>> create(final ApiInvoker<T> apiInvoker) {
        return Observable.create(new Observable.OnSubscribe<RemoteData<T>>() {
            @Override
            public void call(Subscriber<? super RemoteData<T>> subscriber) {


                try {
                    RemoteData<T> data = apiInvoker.invoker();
                    if (data != null) {
                        subscriber.onNext(data);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (HdException e) {
                    e.printStackTrace();
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }

            }
        });
    }


    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {

        return true;

    }


    /**
     * 读取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Observable<RemoteData<Quotation>> getQuotationList(final String name, final int pageIndex, final int pageSize) {

        return create(new ApiInvoker<Quotation>() {
            @Override
            public RemoteData<Quotation> invoker() throws HdException {
                return apiManager.getQuotationList(name, pageIndex, pageSize);
            }
        });
    }


    @Override
    public Observable<RemoteData<QuotationDetail>> getQuotationDetail(final long quotationId) {
        return create(new ApiInvoker<QuotationDetail>() {
            @Override
            public RemoteData<QuotationDetail> invoker() throws HdException {
                return apiManager.getQuotationDetail(quotationId);
            }
        });
    }


    /**
     * 读取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Observable<RemoteData<Material>> getMaterialList(final String name, final int pageIndex, final int pageSize, final boolean loadAll) {

        return create(new ApiInvoker<Material>() {
            @Override
            public RemoteData<Material> invoker() throws HdException {
                if(loadAll)
                    return apiManager.getMaterialList(name,pageIndex,pageSize);
                return apiManager.getMaterialListInService(name, pageIndex, pageSize);
            }
        });
    }

    @Override
    public Observable<RemoteData<Void>> uploadMaterialPicture(final long materialId, final byte[] data) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {
                return apiManager.uploadMaterialPicture(materialId, data);
            }
        });
    }

    @Override
    public Observable<RemoteData<BufferData>> getInitData(final long userId) {
        return create(new ApiInvoker<BufferData>() {
            @Override
            public RemoteData<BufferData> invoker() throws HdException {
                return apiManager.getInitData(userId);
            }
        });
    }

    @Override
    public Observable saveProductDetail(final ProductDetail productDetail) {
        return create(new ApiInvoker<ProductDetail>() {
            @Override
            public RemoteData<ProductDetail> invoker() throws HdException {
                return apiManager.saveProductDetail(productDetail);
            }
        });
    }


    /**
     * 读取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Observable<RemoteData<ProductProcess>> getProductProcessList(final String name, final int pageIndex, final int pageSize ) {

        return create(new ApiInvoker<ProductProcess>() {
            @Override
            public RemoteData<ProductProcess> invoker() throws HdException {


                return apiManager.getProductProcessList(name, pageIndex, pageSize);
            }
        });
    }

    @Override
    public Observable getUnHandleWorkFlowList(final String key) {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.getUnHandleWorkFlowList(key );
            }
        });
    }

    @Override
    public Observable checkWorkFlowMessageCase(final long workFlowMessageId) {
          return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.checkWorkFlowMessage( workFlowMessageId);
            }
        });
    }

    @Override
    public Observable receiveWorkFlowMessageCase(final long workFlowMessageId, final File[] files, final String area) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.receiveWorkFlowMessage(workFlowMessageId ,   files,  area);
            }
        });
    }

    @Override
    public Observable getAvailableOrderItemForTransformCase() {
        return create(new ApiInvoker<OrderItemWorkFlowState>() {
            @Override
            public RemoteData<OrderItemWorkFlowState> invoker() throws HdException {


                return apiManager.getAvailableOrderItemForTransform(  );
            }
        });

    }

    @Override
    public Observable sendWorkFlowMessageCase(final ErpOrderItemProcess erpOrderItemProcess,  final int tranQty,final long area,final String memo) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.sendWorkFlowMessage(   erpOrderItemProcess,     tranQty,area,memo );
            }
        });
    }


    @Override
    public Observable mySendWorkFlowMessageCase() {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.mySendWorkFlowMessage(    );
            }
        });
    }

    @Override
    public Observable rejectWorkFlowMessage(final long workFlowMessageId, final File[] file, final String memo) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.rejectWorkFlowMessage( workFlowMessageId,   file,   memo);
            }
        });
    }


    @Override
    public Observable loadUnCompleteOrderItemWorkFlowReport() {
        return create(new ApiInvoker<OrderItemWorkFlowState>() {
            @Override
            public RemoteData<OrderItemWorkFlowState> invoker() throws HdException {


                return apiManager.loadUnCompleteOrderItemWorkFlowReport( );
            }
        });
    }


    @Override
    public Observable loadOrderWorkFlowReport(final String key, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<OrderItemWorkFlowState>() {
            @Override
            public RemoteData<OrderItemWorkFlowState> invoker() throws HdException {


                return apiManager.loadOrderWorkFlowReport(   key,   pageIndex,   pageSize);
            }
        });
    }

    @Override
    public Observable loadAppUpgradeInfo() {
        return create(new ApiInvoker<FileInfo>() {
            @Override
            public RemoteData<FileInfo> invoker() throws HdException {


                return apiManager.loadAppUpgradeInfo(   );
            }
        });
    }


    @Override
    public Observable getOrderItemWorkFlowReport(final String os_no, final int  itm  ) {
        return create(new ApiInvoker<ErpWorkFlowReport>() {
            @Override
            public RemoteData<ErpWorkFlowReport> invoker() throws HdException {


                return apiManager.getOrderItemWorkFlowReport(   os_no,itm);
            }
        });
    }


    @Override
    public Observable searchErpOrderItems(final String key, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<ErpOrderItem>() {
            @Override
            public RemoteData<ErpOrderItem> invoker() throws HdException {


                return apiManager.searchErpOrderItems(   key,pageIndex,pageSize);
            }
        });
    }


    @Override
    public Observable getAvailableOrderItemProcess(final String osNo, final int  itm, final int workFlowStep) {
        return create(new ApiInvoker<ErpOrderItemProcess>() {
            @Override
            public RemoteData<ErpOrderItemProcess> invoker() throws HdException {


                return apiManager.getAvailableOrderItemProcess(       osNo,  itm,workFlowStep);
            }
        });
    }


    @Override
    public Observable getOrderItemWorkFlowMessage(final String os_no, final int itm  ,  final int workFlowStep) {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.getOrderItemWorkFlowMessage(     os_no,  itm,  workFlowStep);
            }
        });
    }

    @Override
    public Observable loadUsers() {
        return create(new ApiInvoker<User>() {
            @Override
            public RemoteData<User> invoker() throws HdException {


                return apiManager.loadUsers(   );
            }
        });
    }

    @Override
    public Observable getUnCompleteWorkFlowOrderItems(final String key, final int workFlowStep, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<ErpOrderItem>() {
            @Override
            public RemoteData<ErpOrderItem> invoker() throws HdException {


                return apiManager.getUnCompleteWorkFlowOrderItems(   key,   workFlowStep,   pageIndex,   pageSize);
            }
        });
    }


    @Override
    public Observable getCompleteWorkFlowOrderItems(final String key, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<ErpOrderItem>() {
            @Override
            public RemoteData<ErpOrderItem> invoker() throws HdException {


                return apiManager.getCompleteWorkFlowOrderItems(   key,  pageIndex,  pageSize);
            }
        });
    }

    @Override
    public Observable getOrderItemWorkMemoList(final String os_no, final int itm) {
        return create(new ApiInvoker<OrderItemWorkMemo>() {
            @Override
            public RemoteData<OrderItemWorkMemo> invoker() throws HdException {


                return apiManager.getOrderItemWorkMemoList(   os_no,itm);
            }
        });
    }

    @Override
    public Observable getProductWorkMemoList(final String productName, final String pversion) {
        return create(new ApiInvoker<ProductWorkMemo>() {
            @Override
            public RemoteData<ProductWorkMemo> invoker() throws HdException {


                return apiManager.getProductWorkMemoList(   productName,pversion);
            }
        });
    }



    @Override
    public Observable saveWorkMemo(final int workFlowStep, final String os_no, final int itm, final String orderItemWorkMemo, final String prd_name, final String pversion, final String productWorkMemo) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.saveWorkMemo(   workFlowStep, os_no,   itm,   orderItemWorkMemo,   prd_name,   pversion,   productWorkMemo);
            }
        });
    }


    @Override
    public Observable getWorkFlowAreaList() {
        return create(new ApiInvoker<WorkFlowArea>() {
            @Override
            public RemoteData<WorkFlowArea> invoker() throws HdException {


                return apiManager.getWorkFlowAreaList(   );
            }
        });
    }


    @Override
    public Observable getNewMessageInfo() {
        return create(new ApiInvoker<MessageInfo>() {
            @Override
            public RemoteData<MessageInfo> invoker() throws HdException {


                return apiManager.getNewMessageInfo(   );
            }
        });
    }


    @Override
    public Observable getWorkFlowMaterials(final String osNo, final int itm, final String workFlowCode) {
        return create(new ApiInvoker<WorkFlowMaterial>() {
            @Override
            public RemoteData<WorkFlowMaterial> invoker() throws HdException {


                return apiManager.getWorkFlowMaterials(  osNo,   itm,     workFlowCode);
            }
        });
    }

    @Override
    public Observable getWorkFLowMessageByOrderItem(final String osNO, final int itm) {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.getWorkFlowMessageByOrderItem(  osNO,   itm);
            }
        });
    }
    @Override
    public Observable getMyWorkFlowMessage (final String key, final int pageIndex, final int pageSize ) {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.getMyWorkFlowMessage( key,  pageIndex,  pageSize )  ;
            }
        });
    }


    @Override
    public Observable getWorkFlowMemoAuth() {
        return create(new ApiInvoker<WorkFlowMemoAuth>() {
            @Override
            public RemoteData<WorkFlowMemoAuth> invoker() throws HdException {


                return apiManager.getWorkFlowMemoAuth(  )  ;
            }
        });
    }

    @Override
    public Observable checkWorkFlowMemoCase(final long orderItemWorkMemoId, final boolean check) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.checkWorkFlowMemo( orderItemWorkMemoId,check )  ;
            }
        });
    }


    @Override
    public Observable updatePassword(final String oldPasswordMd5, final String newPasswordMd5) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.updatePassword( oldPasswordMd5,newPasswordMd5 )  ;
            }
        });
    }  @Override
    public Observable searchSampleData(final String prdNo, final String pVersion) {
        return create(new ApiInvoker<SampleState>() {
            @Override
            public RemoteData<SampleState> invoker() throws HdException {


                return apiManager.searchSampleData( prdNo,pVersion )  ;
            }
        });
    }


    @Override
    public Observable clearWorkFlow(final String os_no, final int itm) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.clearWorkFlow( os_no,itm )  ;
            }
        });
    }
    @Override
    public Observable getAppQuotations(final String key, final int pageIndex, final int pageSize) {
        return create(new ApiInvoker<com.giants3.hd.entity.app.Quotation>() {
            @Override
            public RemoteData<com.giants3.hd.entity.app.Quotation> invoker() throws HdException {


                return apiManager.getAppQotations( key,pageIndex,pageSize )  ;
            }
        });
    }


    @Override
    public Observable getAppQuotationDetail(final long quotationId) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.getAppQuotationDetail( quotationId  )  ;
            }
        });
    }

    @Override
    public Observable createAppQuotation() {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.createAppQuotation(    )  ;
            }
        });
    }


    @Override
    public Observable addProductToQuotation(final long quotationId, final long productId) {

        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.addProductToQuotation( quotationId,  productId )  ;
            }
        });


    }

    @Override
    public Observable removeItemFromQuotation(final long quotationId, final int item) {


        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.removeItemFromQuotation( quotationId,  item )  ;
            }
        });

    }


    @Override
    public Observable updateQuotationItemPrice(final long quotationId, final int itm, final float price) {

        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationItemPrice( quotationId,  itm, price)  ;
            }
        });
    }

    @Override
    public Observable updateQuotationItemQty(final long quotationId, final int itm, final int newQty) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationItemQty( quotationId,  itm, newQty)  ;
            }
        });
    }
    @Override
    public Observable updateQuotationItemMemo(final long quotationId, final int itm, final String memo) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationItemMemo( quotationId,  itm, memo)  ;
            }
        });
    }
    @Override
    public Observable updateQuotationFieldValue(final long quotationId, final String  field, final String data) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationFieldValue( quotationId,  field, data)  ;
            }
        });
    }

    @Override
    public Observable deleteQuotation(final long quotationId ) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.deleteQuotation( quotationId)  ;
            }
        });
    }

    @Override
    public Observable updateQuotationItemDiscount(final long quotationId, final int itm, final float newDisCount) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationItemDiscount( quotationId,  itm, newDisCount)  ;
            }
        });
    }

    @Override
    public Observable updateQuotationDiscount(final long quotationId, final float newDisCount) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationDiscount( quotationId,   newDisCount)  ;
            }
        });
    }

    @Override
    public Observable saveAppQuotation(final long quotationId) {
        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.saveAppQuotation( quotationId)  ;
            }
        });
    }

    @Override
    public Observable printQuotation(final long quotationId, final String filePath) {
        return create(new ApiInvoker<Void>() {
            @Override
            public RemoteData<Void> invoker() throws HdException {


                return apiManager.printQuotation( quotationId,  filePath)  ;
            }
        });
    } @Override
    public Observable getCustomerList(final String key ) {
        return create(new ApiInvoker<Customer>() {
            @Override
            public RemoteData<Customer> invoker() throws HdException {


                return apiManager.getCustomerList(key )  ;
            }
        });
    }


    @Override
    public Observable updateQuotationCustomer(final long quotationId, final long customerId) {

        return create(new ApiInvoker<com.giants3.hd.noEntity.app.QuotationDetail>() {
            @Override
            public RemoteData<com.giants3.hd.noEntity.app.QuotationDetail> invoker() throws HdException {


                return apiManager.updateQuotationCustomer( quotationId,  customerId)  ;
            }
        });
    }

    @Override
    public Observable saveCustomer(final Customer customer) {
        return create(new ApiInvoker<Customer>() {
            @Override
            public RemoteData<Customer> invoker() throws HdException {


                return apiManager.saveCustomer( customer)  ;
            }
        });
    }
}

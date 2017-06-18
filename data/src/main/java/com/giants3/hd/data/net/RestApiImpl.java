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
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ErpOrderItemProcess;
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.ProductProcess;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity.Quotation;
import com.giants3.hd.utils.entity.QuotationDetail;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.User;
import com.giants3.hd.utils.entity.WorkFlowArea;
import com.giants3.hd.utils.entity.WorkFlowMessage;
import com.giants3.hd.utils.entity.ErpWorkFlowReport;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;
import com.giants3.hd.utils.noEntity.BufferData;
import com.giants3.hd.utils.noEntity.ErpOrderDetail;
import com.giants3.hd.utils.noEntity.FileInfo;
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

    /**
     * 读取产品列表
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Observable<RemoteData<AProduct>> getProductList(final String name, final int pageIndex, final int pageSize) {

        return create(new ApiInvoker<AProduct>() {
            @Override
            public RemoteData<AProduct> invoker() throws HdException {
                return apiManager.getProductList(name, pageIndex, pageSize);
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
    public Observable getUnHandleWorkFlowList() {
        return create(new ApiInvoker<WorkFlowMessage>() {
            @Override
            public RemoteData<WorkFlowMessage> invoker() throws HdException {


                return apiManager.getUnHandleWorkFlowList( );
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
    public Observable getUnCompleteWorkFlowOrderItems(final String key) {
        return create(new ApiInvoker<ErpWorkFlowOrderItem>() {
            @Override
            public RemoteData<ErpWorkFlowOrderItem> invoker() throws HdException {


                return apiManager.getUnCompleteWorkFlowOrderItems(   key);
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
}

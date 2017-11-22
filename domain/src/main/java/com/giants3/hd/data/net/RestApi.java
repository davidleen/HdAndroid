/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giants3.hd.data.net;


import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.Material;
import com.giants3.hd.entity.User;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.Quotation;
import com.giants3.hd.noEntity.QuotationDetail;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.noEntity.RemoteData;

import java.io.File;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {


  /**
   * Retrieves an {@link rx.Observable} which will emit a List of {@link Quotation}.
   */
  Observable<List<Quotation>> userEntityList();

  /**
   * Retrieves an {@link rx.Observable} which will emit a {@link Quotation}.
   *
   * @param userId The user id used to get user data.
   */
  Observable<Quotation> userEntityById(final int userId);


  /**
   * 登录接口
   * @param map
   * @return
   */
  Observable<RemoteData<AUser>> login(final Map<String,String> map)  ;
  /**
   * 登录接口
   * @param userId
   * @param passwordMd5
   * @return
   */
  Observable<RemoteData<User>> loginByUserId(final long userId,String passwordMd5,String deviceToken)  ;

  /**
   * 读取产品列表
   * @param name
   * @param pageIndex
   * @param pageSize
   * @return
   */
  Observable<RemoteData<AProduct>> getProductList(String name, int pageIndex, int pageSize)  ;

  Observable getOrderList(String name, int pageIndex, int pageSize);

  Observable getOrderDetail(String orderNo);

  Observable getProductDetail(long productId);

  Observable getQuotationList(String name, int pageIndex, int pageSize);

  Observable<RemoteData<QuotationDetail>> getQuotationDetail(long quotationId);

  Observable<RemoteData<Material>> getMaterialList(String name, int pageIndex, int pageSize,boolean loadAll);

  Observable<RemoteData<Void>> uploadMaterialPicture(long materialId, byte[] data);

  Observable<RemoteData<BufferData>> getInitData(long userId);

  Observable saveProductDetail(ProductDetail productDetail);

  Observable getProductProcessList(String name, int pageIndex, int pageSize);

  Observable getUnHandleWorkFlowList();

  Observable checkWorkFlowMessageCase(long workFlowMessageId);

  Observable receiveWorkFlowMessageCase(long workFlowMessageId, File[] files, String area);

  /**
   * 获取可以传递流程的订单item
   * @return
     */
  Observable getAvailableOrderItemForTransformCase();

  Observable sendWorkFlowMessageCase(ErpOrderItemProcess orderItemProcess, int tranQty,long area, String memo);

  Observable mySendWorkFlowMessageCase();

  Observable rejectWorkFlowMessage(long workFlowMessageId,File[] file, String memo);

  Observable loadUnCompleteOrderItemWorkFlowReport();

  Observable loadOrderWorkFlowReport(String key, int pageIndex, int pageSize);

  Observable loadAppUpgradeInfo();

  /**
   * 订单的生产进度报表
   * @param orderItemId
   * @return
     */
  Observable getOrderItemWorkFlowReport(String os_no,int itm);



  Observable getAvailableOrderItemProcess(String osNo, int  itm, int workFlowStep);

  /**
   * 读取指定订单，流程的消息列表
   * @param os_no
   * @param workFlowStep
   * @return
   */
  Observable getOrderItemWorkFlowMessage(String os_no,int  itm,  int workFlowStep);

    Observable loadUsers();

    Observable searchErpOrderItems(String key, int pageIndex, int pageSize);

  /**
   * 读取已排产未完工的订单
   * @param key
   * @return
   */
    Observable getUnCompleteWorkFlowOrderItems(String key);


  Observable getCompleteWorkFlowOrderItems(String key);

  Observable getOrderItemWorkMemoList(String os_no, int itm);

  Observable getProductWorkMemoList(String prd_name, String pversion);

  Observable saveWorkMemo(int workFlowStep,String os_no, int itm, String orderItemWorkMemo, String prd_name, String pversion, String productWorkMemo);

  Observable getWorkFlowAreaList();

    Observable getNewMessageInfo();

  Observable getWorkFlowMaterials(String osNo, int itm, String workFlowCode);

    Observable getWorkFLowMessageByOrderItem(String osNO, int itm);

  Observable getMyWorkFlowMessage(String key);

    Observable getWorkFlowMemoAuth();

    Observable checkWorkFlowMemoCase(long orderItemWorkMemoId, boolean check);

    Observable updatePassword(String oldPasswordMd5, String newPasswordMd5);

    Observable searchSampleData(String prd_name, String pVersion);

    Observable clearWorkFlow(String os_no, int itm);
}

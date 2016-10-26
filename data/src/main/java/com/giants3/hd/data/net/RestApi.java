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
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.Quotation;
import com.giants3.hd.utils.entity.QuotationDetail;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.noEntity.BufferData;

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

  Observable receiveWorkFlowMessageCase(long workFlowMessageId);

  /**
   * 获取可以传递流程的订单item
   * @return
     */
  Observable getAvailableOrderItemForTransformCase();

  Observable sendWorkFlowMessageCase(long orderItemId, int flowStep, int tranQty,String memo);

  Observable mySendWorkFlowMessageCase();

  Observable rejectWorkFlowMessage(long workFlowMessageId, int toWorkFlowStep, String reason);

  Observable loadUnCompleteOrderItemWorkFlowReport();

  Observable loadOrderWorkFlowReport(String key, int pageIndex, int pageSize);

  Observable loadAppUpgradeInfo();
}

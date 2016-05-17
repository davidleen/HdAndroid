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
  static final String API_BASE_URL = "";


  /** Api url for getting a user profile: Remember to concatenate id + 'json' */
  static final String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";


  static final String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";

  static String API_URL_GET_PRODUCT_LIST = "/api/product/appSearch?name=%s&pageIndex=%d&pageSize=%d";
  static String API_URL_GET_QUOTATION_LIST = "/api/quotation/search?searchValue=%s&pageIndex=%d&pageSize=%d";
  static String API_URL_GET_MATERIAL_LIST = "/api/material/search?codeOrName=%s&pageIndex=%d&pageSize=%d";
  static String API_URL_UPLOAD_MATERIAL_PICTURE = "/api/file/uploadMaterialPicture?materialId=%d";
  static String API_URL_GET_INITDATA = "/api/user/getInitData?userId=%d";
  static String API_URL_GET_QUOTATION_DETAIL = "/api/quotation/detail?id=%d";
  String API_URL_GET_ORDER_LIST = "/api/order/list?key=%s&pageIndex=%d&pageSize=%d";
  String API_URL_GET_ORDER_ITEM_LIST = "/api/order/findOrderItems?orderNo=%s";
  String API_URL_GET_PRODUCT_DETAIL = "/api/product/detail?id=%d";

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

  Observable getOrderItemList(String orderNo);

  Observable getProductDetail(long productId);

  Observable getQuotationList(String name, int pageIndex, int pageSize);

  Observable<RemoteData<QuotationDetail>> getQuotationDetail(long quotationId);

  Observable<RemoteData<Material>> getMaterialList(String name, int pageIndex, int pageSize);

  Observable<RemoteData<Void>> uploadMaterialPicture(long materialId, byte[] data);

  Observable<RemoteData<BufferData>> getInitData(long userId);
}

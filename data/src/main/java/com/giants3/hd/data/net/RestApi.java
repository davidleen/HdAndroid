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
import com.giants3.hd.data.entity.Quotation;
import com.giants3.hd.data.entity.RemoteData;

import java.net.MalformedURLException;
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
  String API_URL_GET_ORDER_LIST = "/api/order/list?key=%s&pageIndex=%d&pageSize=%d";
  String API_URL_GET_ORDER_ITEM_LIST = "/api/order/findOrderItems?orderNo=%s";

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
}

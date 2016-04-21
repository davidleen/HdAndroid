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
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.Quotation;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.data.exception.NetworkConnectionException;
import com.giants3.hd.exception.HdException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {


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

        return create( new ApiInvoker<AProduct>() {
            @Override
            public RemoteData<AProduct> invoker() throws HdException {
                return apiManager.getProductList(name, pageIndex, pageSize);
            }
        });
    }

    @Override
    public Observable<RemoteData<ErpOrder>> getOrderList(final  String name, final int pageIndex,final int pageSize) {
        return create( new ApiInvoker<ErpOrder>() {
            @Override
            public RemoteData<ErpOrder> invoker() throws HdException {
                return apiManager.getOrderList(name, pageIndex, pageSize);
            }
        });
    }

    @Override
    public Observable getOrderItemList(final String orderNo) {
        return create( new ApiInvoker<ErpOrderItem>() {
            @Override
            public RemoteData<ErpOrderItem> invoker() throws HdException {
                return apiManager.getOrderItemList(orderNo);
            }
        });
    }

    @Override
    public Observable getProductDetail(final long productId) {
        return create( new ApiInvoker<ProductDetail>() {
            @Override
            public RemoteData<ProductDetail> invoker() throws HdException {
                return apiManager.getProductDetail(productId);
            }
        });
    }

    /**
     * 通用调用命令接口
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
}

package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.noEntity.ProductDetail;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class SaveProductDetailCase extends UseCase {




    RestApi restApi;
    ProductDetail productDetail;


    public SaveProductDetailCase(Scheduler threadExecutor, Scheduler postExecutionThread, ProductDetail productDetail, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.productDetail = productDetail;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.saveProductDetail(productDetail);



    }
}

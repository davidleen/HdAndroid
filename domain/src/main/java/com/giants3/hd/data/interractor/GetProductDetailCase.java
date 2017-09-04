package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class GetProductDetailCase extends UseCase {


    private final long productId;

    RestApi restApi;



    public GetProductDetailCase(Scheduler threadExecutor, Scheduler postExecutionThread,long productId, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.productId = productId;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getProductDetail(productId);



    }
}

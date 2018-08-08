package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class FindProductByIdUseCase extends DefaultUseCase {


    private final long productId;

    RestApi restApi;



    public FindProductByIdUseCase( long productId, RestApi restApi) {
        super( );
        this.productId = productId;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.findProductById(productId);



    }
}

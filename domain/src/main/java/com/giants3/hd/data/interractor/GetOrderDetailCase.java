package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class GetOrderDetailCase extends UseCase {


    private final String orderNo;

    RestApi restApi;



    public GetOrderDetailCase(Scheduler threadExecutor, Scheduler postExecutionThread, String orderNo, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.orderNo = orderNo;


        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getOrderDetail(orderNo);



    }
}

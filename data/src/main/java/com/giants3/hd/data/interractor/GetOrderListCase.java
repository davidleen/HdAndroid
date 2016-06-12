package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class GetOrderListCase extends UseCase {


    private final String name;
    private final int pageIndex;
    private final int pageSize;
    RestApi restApi;



    public GetOrderListCase(Scheduler threadExecutor, Scheduler postExecutionThread, String name, int pageIndex, int pageSize, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.name = name;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {

       return  restApi.getOrderList(name,pageIndex,pageSize);



    }
}

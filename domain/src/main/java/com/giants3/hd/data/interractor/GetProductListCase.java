package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class GetProductListCase extends UseCase {


    private final String name;
    private final int pageIndex;
    private final int pageSize;
    private final boolean withCopy;
    RestApi restApi;



    public GetProductListCase(Scheduler threadExecutor, Scheduler postExecutionThread, String name,int pageIndex,int pageSize,boolean withCopy, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.name = name;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.withCopy = withCopy;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getAProductList(name,pageIndex,pageSize,withCopy);



    }
}

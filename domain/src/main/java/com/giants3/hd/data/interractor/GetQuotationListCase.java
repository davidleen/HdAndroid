package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 获取报价单列表
 *
 * Created by david on 2015/9/14.
 */
public class GetQuotationListCase extends UseCase {


    private final String name;
    private final int pageIndex;
    private final int pageSize;
    RestApi restApi;



    public GetQuotationListCase(Scheduler threadExecutor, Scheduler postExecutionThread, String name, int pageIndex, int pageSize, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.name = name;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getQuotationList(name,pageIndex,pageSize);



    }
}

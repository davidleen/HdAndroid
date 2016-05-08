package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 获取报价单列表
 *
 * Created by david on 2015/9/14.
 */
public class GetQuotationDetailCase extends UseCase {



    private final long quotationId  ;
    RestApi restApi;



    public GetQuotationDetailCase(Scheduler threadExecutor, Scheduler postExecutionThread, long quotationId, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.quotationId = quotationId;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getQuotationDetail(quotationId);



    }
}

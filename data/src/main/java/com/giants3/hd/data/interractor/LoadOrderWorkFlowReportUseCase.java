package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 获取未处理的流程消息列表
 *
 * Created by david on 2015/9/14.
 */
public class LoadOrderWorkFlowReportUseCase extends UseCase {


    private String key;
    private int pageIndex;
    private int pageSize;
    RestApi restApi;



    public LoadOrderWorkFlowReportUseCase(String key, int pageIndex, int pageSize,Scheduler threadExecutor, Scheduler postExecutionThread, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.key = key;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.loadOrderWorkFlowReport( key,pageIndex,pageSize);



    }
}

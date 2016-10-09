package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 获取未处理的流程消息列表
 *
 * Created by david on 2015/9/14.
 */
public class GetUnHandleWorkFlowMessageCase extends UseCase {




    RestApi restApi;



    public GetUnHandleWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread,  RestApi restApi) {
        super(threadExecutor, postExecutionThread);

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getUnHandleWorkFlowList( );



    }
}

package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;


/**
 *  我发送流程消息列表
 *
 *
 */
public class MySendWorkFlowMessageCase extends UseCase {



    RestApi restApi;
    public MySendWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread ,RestApi restApi) {
        super(threadExecutor, postExecutionThread);


        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.mySendWorkFlowMessageCase(  );


    }
}

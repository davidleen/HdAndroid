package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 接受流程传递
 *
 *
 */
public class ReceiveWorkFlowMessageCase extends UseCase {


    private final long workFlowMessageId;
    RestApi restApi;



    public ReceiveWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread,long workFlowMessageId, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.workFlowMessageId = workFlowMessageId;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.receiveWorkFlowMessageCase( workFlowMessageId);



    }
}

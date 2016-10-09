package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;


/**
 * 审核流程传递
 *
 *
 */
public class CheckWorkFlowMessageCase extends UseCase {


    private final long workFlowMessageId;
    RestApi restApi;
    public CheckWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread, long messageId,RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.workFlowMessageId = messageId;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.checkWorkFlowMessageCase( workFlowMessageId);


    }
}

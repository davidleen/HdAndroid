package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;


/**
 * 审核流程传递
 *
 *
 */
public class RejectWorkFlowMessageCase extends UseCase {


    private final long workFlowMessageId;
    private final int toWorkFlowStep;
    private final String reason;
    RestApi restApi;
    public RejectWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread, long messageId, int toWorkFlowStep,String reason,RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.workFlowMessageId = messageId;
        this.toWorkFlowStep = toWorkFlowStep;
        this.reason = reason;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.rejectWorkFlowMessage ( workFlowMessageId,toWorkFlowStep,reason);


    }
}

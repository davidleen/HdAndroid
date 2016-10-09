package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;


/**
 * 审核流程传递
 *
 *
 */
public class SendWorkFlowMessageCase extends UseCase {


    private final long orderItemId;
    private final int flowStep;
    private final int tranQty;
    private final String memo;
    RestApi restApi;
    public SendWorkFlowMessageCase(Scheduler threadExecutor, Scheduler postExecutionThread,long orderItemId, int flowStep, int tranQty,String memo, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.orderItemId = orderItemId;
        this.flowStep = flowStep;
        this.tranQty = tranQty;
        this.memo = memo;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.sendWorkFlowMessageCase( orderItemId,   flowStep,   tranQty,memo);


    }
}

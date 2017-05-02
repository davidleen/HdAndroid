package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by davidleen29 on 2017/5/1.
 */

class GetOrderItemWorkFlowMessageUseCase extends DefaultUseCase {
    private final long orderItemWorkFlowId;
    private final int workFlowStep;
    private final RestApi restApi;

    public GetOrderItemWorkFlowMessageUseCase(long orderItemWorkFlowId, int workFlowStep, RestApi restApi) {
        super();
        this.orderItemWorkFlowId = orderItemWorkFlowId;
        this.workFlowStep = workFlowStep;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getOrderItemWorkFlowMessage(orderItemWorkFlowId,workFlowStep);
    }
}

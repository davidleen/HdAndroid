package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by davidleen29 on 2017/3/5.
 */
public class GetOrderItemWorkFlowStateUseCase extends UseCase {
    private final long orderItemId;
    private final int workFlowStep;
    private final RestApi restApi;

    public GetOrderItemWorkFlowStateUseCase(Scheduler scheduler, Scheduler postExecutionThread, long orderItemId, int workFlowStep, RestApi restApi) {
        super(scheduler,postExecutionThread);
        this.orderItemId = orderItemId;
        this.workFlowStep = workFlowStep;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

      return   restApi.getOrderItemWorkFlowState(orderItemId,workFlowStep);

    }
}

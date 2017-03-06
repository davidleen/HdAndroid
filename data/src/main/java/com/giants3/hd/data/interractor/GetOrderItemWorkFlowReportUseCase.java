package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 订单的生产进度报表
 *
 * @return Created by davidleen29 on 2017/3/4.
 */
public class GetOrderItemWorkFlowReportUseCase extends UseCase {
    private final long orderItemId;
    private final RestApi restApi;

    public GetOrderItemWorkFlowReportUseCase(Scheduler scheduler, Scheduler postExecutionThread, long orderItemId, RestApi restApi) {
        super(scheduler,postExecutionThread);
        this.orderItemId = orderItemId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getOrderItemWorkFlowReport(orderItemId);
    }
}
